package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {

	private static final double MARGIN = 0.25;
	private static final double SETTLE_TIME = 0.25 * 1000;

	private double distance;

	private long timeSettled = 0;

	public Drive(double distance) {
		requires(Robot.drivetrain);
		this.distance = distance;
	}

	protected void initialize() {
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.movePID.setSetpoint(distance);
		Robot.drivetrain.movePID.enable();
		Robot.drivetrain.rotatePID.setSetpoint(0);
		Robot.drivetrain.rotatePID.enable();
	}

	protected void execute() {
		if (Robot.drivetrain.movePID.getError() < MARGIN) {
			if (timeSettled == 0) {
				timeSettled = System.currentTimeMillis();
			}
		} else {
			timeSettled = 0;
		}
		Robot.drivetrain.drive(0, Robot.drivetrain.movePID.get(),
				Robot.drivetrain.rotatePID.get());
	}

	protected boolean isFinished() {
		return timeSettled != 0
				&& (System.currentTimeMillis() - timeSettled) > SETTLE_TIME;
	}

	protected void end() {
		Robot.drivetrain.movePID.disable();
		Robot.drivetrain.rotatePID.disable();
	}

	protected void interrupted() {
		Robot.drivetrain.movePID.disable();
		Robot.drivetrain.rotatePID.disable();
	}
}
