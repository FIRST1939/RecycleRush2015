package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainTester extends Command {

	public DrivetrainTester() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
		Robot.drivetrain.resetEncoders();
		SmartDashboard.putNumber("Setpoint", 0);
		Robot.drivetrain.movePID.setSetpoint(0);
		Robot.drivetrain.movePID.enable();
	}

	protected void execute() {
		Robot.drivetrain.movePID.setSetpoint(SmartDashboard
				.getNumber("Setpoint"));

		SmartDashboard.putNumber("FL",
				Robot.drivetrain.frontLeft.getEncPosition());
		SmartDashboard.putNumber("RL",
				Robot.drivetrain.rearLeft.getEncPosition());
		SmartDashboard.putNumber("FR",
				Robot.drivetrain.frontRight.getEncPosition());
		SmartDashboard.putNumber("RR",
				Robot.drivetrain.rearRight.getEncPosition());
		SmartDashboard.putNumber("Current Point",
				Robot.drivetrain.getForwardDistance());
		SmartDashboard.putNumber("Current Setpoint",
				Robot.drivetrain.movePID.getSetpoint());

		double y = Robot.drivetrain.movePID.get();
		SmartDashboard.putNumber("Y", y);
		Robot.drivetrain.drive(0, -y, 0);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drivetrain.movePID.disable();
	}

	protected void interrupted() {
		Robot.drivetrain.movePID.disable();
	}
}
