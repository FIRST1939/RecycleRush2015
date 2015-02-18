package org.usfirst.frc.team1939.robot.commands.doors;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Doors;

import edu.wpi.first.wpilibj.command.Command;

public class OpenDoors extends Command {

	public OpenDoors() {
		requires(Robot.doors);
	}

	protected void initialize() {
		Robot.doors.spinOpen();
		this.setTimeout(Doors.TIME);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return this.isTimedOut();
	}

	protected void end() {
		Robot.doors.stop();
	}

	protected void interrupted() {
		Robot.doors.stop();
	}
}
