package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Drivetrain extends Subsystem {

	public CANTalon frontLeft = new CANTalon(RobotMap.talonFrontLeft);
	public CANTalon rearLeft = new CANTalon(RobotMap.talonRearLeft);
	public CANTalon frontRight = new CANTalon(RobotMap.talonFrontRight);
	public CANTalon rearRight = new CANTalon(RobotMap.talonRearRight);
	{
		frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		frontRight.reverseSensor(true);
		rearRight.reverseSensor(true);
		
		//LiveWindow.addActuator("Drivetrain", "FrontLeft", new SpeedControllerSendable(frontLeft));
		//LiveWindow.addActuator("Drivetrain", "RearLeft", new SpeedControllerSendable(rearLeft));
		//LiveWindow.addActuator("Drivetrain", "FrontRight", new SpeedControllerSendable(frontRight));
		//LiveWindow.addActuator("Drivetrain", "RearRight", new SpeedControllerSendable(rearRight));
	}

	private static final double INCHES_PER_REVOLUTION = 8.0 * Math.PI;
	private static final double PULSES_PER_REVOLUTION = 250;

	private RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft,
			frontRight, rearRight);
	{
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
		robotDrive.setSafetyEnabled(true);
		robotDrive.setExpiration(0.1);
		robotDrive.setSensitivity(0.5);
		robotDrive.setMaxOutput(1.0);
	}

	private Gyro gyro = new Gyro(RobotMap.gyro);

	private static final double moveMaxSpeed = 0.5;
	private static final double moveP = 0.01;
	private static final double moveI = 0;
	private static final double moveD = 0;
	private PIDSource moveSource = new PIDSource() {
		public double pidGet() {
			return getForwardDistance();
		}
	};
	private PIDOutput moveOutput = new PIDOutput() {
		public void pidWrite(double output) {
			// Do nothing because it is only read from
		}
	};
	public PIDController movePID = new PIDController(moveP, moveI, moveD,
			moveSource, moveOutput);
	{
		movePID.setOutputRange(-moveMaxSpeed, moveMaxSpeed);
		LiveWindow.addActuator("Drivetrain", "Move PID", movePID);
	}

	private static final double rotateMaxSpeed = 0.5;
	private static final double rotateP = 0.05;
	private static final double rotateI = 0;
	private static final double rotateD = 0;
	private PIDSource rotateSource = new PIDSource() {
		public double pidGet() {
			double leftTurn = (frontLeft.getPosition() + rearLeft.getPosition()) / 2;
			double rightTurn = (frontRight.getPosition() + rearRight
					.getPosition()) / 2;
			return leftTurn - rightTurn;
		}
	};
	private PIDOutput rotateOutput = new PIDOutput() {
		public void pidWrite(double output) {
			// Do nothing because it is only read from
		}
	};
	public PIDController rotatePID = new PIDController(rotateP, rotateI,
			rotateD, rotateSource, rotateOutput);
	{
		rotatePID.setOutputRange(-rotateMaxSpeed, rotateMaxSpeed);
		LiveWindow.addActuator("Drivetrain", "Rotate PID", rotatePID);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public double getForwardDistance() {
		return (frontLeft.getPosition() + rearLeft.getPosition() +
		frontRight.getPosition() + rearRight.getPosition()) / 4 /
		PULSES_PER_REVOLUTION * INCHES_PER_REVOLUTION;
	}

	public void driveWithGyro(double x, double y, double z, double multi) {
		this.drive(x, y, z, multi, -this.gyro.getAngle());
	}

	public void drive(double x, double y, double z) {
		this.drive(x, y, z, 1.0);
	}

	public void drive(double x, double y, double z, double multi) {
		this.drive(x, y, z, multi, 0);
	}

	public void drive(double x, double y, double z, double multi, double gyro) {
		robotDrive.mecanumDrive_Cartesian(x * multi, y * multi, z * multi, gyro);
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void resetEncoders() {
		this.frontLeft.setPosition(0);
		this.rearLeft.setPosition(0);
		this.frontRight.setPosition(0);
		this.rearRight.setPosition(0);
	}
}
