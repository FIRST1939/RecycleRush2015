package org.usfirst.frc.team1939.robot.commands.smartdashboard;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdater extends Command {

	public SmartDashboardUpdater() {
		requires(Robot.sds);
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean("Override", Lifter.OVERRIDE);
	}

	@Override
	protected void execute() {
		SmartDashboard.putBoolean("Lifter Up", Robot.lifter.isUp());
		SmartDashboard.putBoolean("Lifter Down", Robot.lifter.isDown());
		SmartDashboard.putNumber("Lifter Position", Robot.lifter.getPosition());
		SmartDashboard.putNumber("Lifter Speed", Robot.lifter.getSpeed());
		SmartDashboard.putNumber("Gyro", Robot.ahrs.getAngle());

		SmartDashboard.putNumber("Drivetrain forward", Robot.drivetrain.getForwardDistance());
		SmartDashboard.putNumber("Drivetrain Speed", Robot.drivetrain.getSpeed());

		SmartDashboard.putNumber("Roller Claw Encoder", Robot.rollerClaw.getEncoder());

		Lifter.OVERRIDE = SmartDashboard.getBoolean("Override");
		if (Lifter.OVERRIDE) {
			Robot.lifter.disableLimits();
		} else {
			Robot.lifter.enableLimits();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
