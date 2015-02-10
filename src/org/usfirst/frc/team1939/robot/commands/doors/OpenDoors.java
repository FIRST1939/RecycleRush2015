package org.usfirst.frc.team1939.robot.commands.doors;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OpenDoors extends Command {

	public OpenDoors() {
		requires(Robot.doors);
	}

	protected void initialize() {
		Robot.doors.spinOpen();
	}

	protected void execute() {
		if (Robot.doors.leftOpen()) {
			Robot.doors.stopLeft();
		}
		if (Robot.doors.rightOpen()) {
			Robot.doors.stopRight();
		}
	}

	protected boolean isFinished() {
		return Robot.doors.areOpen();
	}

	protected void end() {
		Robot.doors.areOpen = true;
		Robot.doors.stop();
	}

	protected void interrupted() {
		Robot.doors.stop();
	}
}
