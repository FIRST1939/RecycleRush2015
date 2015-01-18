package org.usfirst.frc.team1939.robot.commands.doors;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CloseDoors extends Command {

	public CloseDoors() {
		requires(Robot.doors);
	}

	protected void initialize() {
		Robot.doors.spinClosed();
	}

	protected void execute() {
		if (Robot.doors.leftClosed()) {
			Robot.doors.stopLeft();
		}
		if (Robot.doors.rightClosed()) {
			Robot.doors.stopRight();
		}
	}

	protected boolean isFinished() {
		return Robot.doors.areClosed();
	}

	protected void end() {
		Robot.doors.stop();
	}

	protected void interrupted() {
		Robot.doors.stop();
	}
}
