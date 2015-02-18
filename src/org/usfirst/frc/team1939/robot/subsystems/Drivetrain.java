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
	}

	private static final double INCHES_PER_REVOLUTION = 8.0 * Math.PI;
	private static final double PULSES_PER_REVOLUTION = 250 * 4;

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

	private static final double moveMaxSpeed = 0.25;
	private static final double moveP = 0.05;
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

	private static final double turnMaxSpeed = 0.5;
	private static final double turnP = 0.03;
	private static final double turnI = 0;
	private static final double turnD = 0;
	private PIDSource turnSource = new PIDSource() {
		public double pidGet() {
			return gyro.getAngle();
		}
	};
	private PIDOutput turnOutput = new PIDOutput() {
		public void pidWrite(double output) {
			// Do nothing because it is only read from
		}
	};
	public PIDController turnPID = new PIDController(turnP, turnI, turnD, turnSource, turnOutput);
	{
		turnPID.setOutputRange(-turnMaxSpeed, turnMaxSpeed);
		LiveWindow.addActuator("Drivetrain", "turn PID", turnPID);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public double getForwardDistance() {
		return rearLeft.getPosition() / PULSES_PER_REVOLUTION * INCHES_PER_REVOLUTION;
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

	public double getAngle(){
		return gyro.getAngle();
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
