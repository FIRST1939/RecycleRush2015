package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetLifterHeight extends Command {

	private double height;

	public SetLifterHeight(double height) {
		this.height = height;
		requires(Robot.lifter);
	}

	protected void initialize() {
		Robot.lifter.enable();
		Robot.lifter.setSetpoint(height);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Robot.lifter.onTarget();
	}

	protected void end() {
		Robot.lifter.disable();
	}

	protected void interrupted() {
		Robot.lifter.disable();
	}
}
