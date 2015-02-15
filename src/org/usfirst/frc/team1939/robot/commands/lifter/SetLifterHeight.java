package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class SetLifterHeight extends Command {

	private double height;
	private PIDTimer timer;

	public SetLifterHeight(double height) {
		this.height = height;
		requires(Robot.lifter);
	}

	protected void initialize() {
		timer = new PIDTimer(()->Robot.lifter.getPosition(), height, 0.5, 100);
		Robot.lifter.positionMode();
		Robot.lifter.enable();
		Robot.lifter.setPosition(height);
	}

	protected void execute() {
		timer.update();
	}

	protected boolean isFinished() {
		return timer.isDone();
	}

	protected void end() {
		Robot.lifter.disable();
	}

	protected void interrupted() {
		Robot.lifter.disable();
	}
}
