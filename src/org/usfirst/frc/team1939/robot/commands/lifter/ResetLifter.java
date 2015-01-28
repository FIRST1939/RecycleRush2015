package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

public class ResetLifter extends Command {

	public ResetLifter() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		Robot.lifter.disable();
		Robot.lifter.setSpeed(Lifter.DOWN);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Robot.lifter.isAtBottom();
	}

	protected void end() {
		Robot.lifter.setSpeed(0);
	}

	protected void interrupted() {
		Robot.lifter.setSpeed(0);
	}
}
