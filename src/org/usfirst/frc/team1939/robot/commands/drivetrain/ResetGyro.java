package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetGyro extends Command {

	public ResetGyro() {
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.ahrs.reset();
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
