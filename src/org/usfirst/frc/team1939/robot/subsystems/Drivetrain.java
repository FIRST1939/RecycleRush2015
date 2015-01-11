package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	private CANTalon frontLeft = new CANTalon(RobotMap.talonFrontLeft);
	private CANTalon rearLeft = new CANTalon(RobotMap.talonRearLeft);
	private CANTalon frontRight = new CANTalon(RobotMap.talonFrontRight);
	private CANTalon rearRight = new CANTalon(RobotMap.talonRearRight);

	private RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft,
			frontRight, rearRight);

	private Gyro gyro = new Gyro(RobotMap.gyro);

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void drive(double x, double y, double z) {
		this.drive(x, y, z, 1.0);
	}

	public void drive(double x, double y, double z, double multi) {
		robotDrive.mecanumDrive_Cartesian(x * multi, y * multi, z * multi,
				-gyro.getAngle());
	}

	public void resetGyro() {
		gyro.reset();
	}
}
