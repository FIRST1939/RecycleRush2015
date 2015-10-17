package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
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
		this.frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.rearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.rearRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		this.frontRight.reverseSensor(true);
		this.rearRight.reverseSensor(true);
	}

	private static final double INCHES_PER_REVOLUTION = 8.0 * Math.PI;
	private static final double PULSES_PER_REVOLUTION = 250 * 4;

	private RobotDrive robotDrive = new RobotDrive(this.frontLeft, this.rearLeft, this.frontRight, this.rearRight);

	{
		this.robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		this.robotDrive.setInvertedMotor(MotorType.kRearRight, true);
		this.robotDrive.setSafetyEnabled(true);
		this.robotDrive.setExpiration(0.1);
		this.robotDrive.setSensitivity(0.5);
		this.robotDrive.setMaxOutput(1.0);
	}

	private static final double moveP = 0.05;
	private static final double moveI = 0;
	private static final double moveD = 0;
	private PIDSource moveSource = new PIDSource() {
		@Override
		public double pidGet() {
			return getForwardDistance();
		}
	};
	private PIDOutput moveOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			// Do nothing because it is only read from
		}
	};
	public PIDController movePID = new PIDController(moveP, moveI, moveD, this.moveSource, this.moveOutput);

	{
		this.movePID.setOutputRange(-1, 1);
		LiveWindow.addActuator("Drivetrain", "Move PID", this.movePID);
	}

	private static final double turnMaxSpeed = 0.5;
	private static final double turnP = 0.03;
	private static final double turnI = 0;
	private static final double turnD = 0;
	private PIDSource turnSource = new PIDSource() {
		@Override
		public double pidGet() {
			return Robot.ahrs.getAngle();
		}
	};
	private PIDOutput turnOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			// Do nothing because it is only read from
		}
	};
	public PIDController turnPID = new PIDController(turnP, turnI, turnD, this.turnSource, this.turnOutput);

	{
		this.turnPID.setInputRange(0, 360);
		this.turnPID.setContinuous();
		this.turnPID.setOutputRange(-turnMaxSpeed, turnMaxSpeed);
		LiveWindow.addActuator("Drivetrain", "turn PID", this.turnPID);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public double getForwardDistance() {
		return this.rearLeft.getPosition() / PULSES_PER_REVOLUTION * INCHES_PER_REVOLUTION;
	}

	public void drive(double x, double y, double z) {
		this.drive(x, y, z, 1.0);
	}

	public void drive(double x, double y, double z, double multi) {
		this.robotDrive.mecanumDrive_Cartesian(x * multi, y * multi, z * multi, 0);// Robot.ahrs.getAngle());
	}

	public void resetEncoders() {
		this.frontLeft.setPosition(0);
		this.rearLeft.setPosition(0);
		this.frontRight.setPosition(0);
		this.rearRight.setPosition(0);
	}

	public void setMoveMaxSpeed(double speed) {
		speed = Math.abs(speed);
		this.movePID.setOutputRange(-speed, speed);
	}

	public double getSpeed() {
		return this.rearLeft.getSpeed();
	}

}
