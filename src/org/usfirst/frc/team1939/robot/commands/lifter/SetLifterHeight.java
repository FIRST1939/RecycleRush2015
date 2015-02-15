package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetLifterHeight extends Command {

	private static final double MARGIN = 10;
	private static final double SETLE_TIME = 100;

	private double height;
	private long timeSettled = 0;

	public SetLifterHeight(double height) {
		this.height = height;
		requires(Robot.lifter);
	}

	protected void initialize() {
		Robot.lifter.positionMode();
		Robot.lifter.enable();
		Robot.lifter.setPosition(height);
	}

	protected void execute() {
		if (Math.abs(Robot.lifter.getSpeed()) <= MARGIN) {
			timeSettled = System.currentTimeMillis();
		} else {
			timeSettled = 0;
		}
	}

	protected boolean isFinished() {
		return timeSettled != 0 && System.currentTimeMillis() - timeSettled > SETLE_TIME;
	}

	protected void end() {
		Robot.lifter.disable();
	}

	protected void interrupted() {
		Robot.lifter.disable();
	}
}
