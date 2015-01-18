package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Drivetrain extends Subsystem {

	private CANTalon frontLeft = new CANTalon(RobotMap.talonFrontLeft);
	private CANTalon rearLeft = new CANTalon(RobotMap.talonRearLeft);
	private CANTalon frontRight = new CANTalon(RobotMap.talonFrontRight);
	private CANTalon rearRight = new CANTalon(RobotMap.talonRearRight);

	private static final double INCHES_PER_REVOLUTION = 8.0;
	private static final double PULSES_PER_REVOLUTION = 360;
	private Encoder frontLeftEncoder = new Encoder(RobotMap.frontLeftA,
			RobotMap.frontLeftB);
	private Encoder rearLeftEncoder = new Encoder(RobotMap.rearLeftA,
			RobotMap.rearLeftB);
	private Encoder frontRightEncoder = new Encoder(RobotMap.frontRightA,
			RobotMap.frontRightB);
	private Encoder rearRightEncoder = new Encoder(RobotMap.rearRightA,
			RobotMap.rearRightB);
	{
		frontLeftEncoder.setDistancePerPulse(INCHES_PER_REVOLUTION
				/ PULSES_PER_REVOLUTION);
		rearLeftEncoder.setDistancePerPulse(INCHES_PER_REVOLUTION
				/ PULSES_PER_REVOLUTION);
		frontRightEncoder.setDistancePerPulse(INCHES_PER_REVOLUTION
				/ PULSES_PER_REVOLUTION);
		rearRightEncoder.setDistancePerPulse(INCHES_PER_REVOLUTION
				/ PULSES_PER_REVOLUTION);

		frontRightEncoder.setReverseDirection(true);
		rearRightEncoder.setReverseDirection(true);

		LiveWindow.addSensor("Drivetrain", "Front Left Encoder",
				frontLeftEncoder);
		LiveWindow
				.addSensor("Drivetrain", "Rear Left Encoder", rearLeftEncoder);
		LiveWindow.addSensor("Drivetrain", "Front Right Encoder",
				frontRightEncoder);
		LiveWindow.addSensor("Drivetrain", "Rear Right Encoder",
				rearRightEncoder);
	}

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
	private static final double moveP = 0;
	private static final double moveI = 0;
	private static final double moveD = 0;
	private PIDSource moveSource = new PIDSource() {
		public double pidGet() {
			return (frontLeftEncoder.getDistance()
					+ rearLeftEncoder.getDistance()
					+ frontRightEncoder.getDistance() + rearRightEncoder
					.getDistance()) / 4;
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
			return ((frontLeftEncoder.getDistance() + rearLeftEncoder
					.getDistance()) / 2)
					- ((frontRightEncoder.getDistance() + rearRightEncoder
							.getDistance()) / 2);
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
		robotDrive
				.mecanumDrive_Cartesian(x * multi, y * multi, z * multi, gyro);
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void resetEncoders() {
		this.frontLeftEncoder.reset();
		this.rearLeftEncoder.reset();
		this.frontRightEncoder.reset();
		this.rearLeftEncoder.reset();
	}
}
