package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.Direction;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class Turn90 extends Command {

	private Direction d;
	private PIDTimer timer;

	public Turn90(Direction d) {
		requires(Robot.drivetrain);
		this.d = d;
	}

	@Override
	protected void initialize() {
		double degrees = 0;
		if (this.d == Direction.LEFT) {
			degrees = -90;
		} else if (this.d == Direction.RIGHT) {
			degrees = 90;
		}
		this.timer = new PIDTimer(() -> Robot.drivetrain.turnPID.getError(), 0, 5, 500);
		Robot.ahrs.reset();
		Robot.drivetrain.turnPID.enable();
		Robot.drivetrain.turnPID.setSetpoint(degrees);
	}

	@Override
	protected void execute() {
		Robot.drivetrain.drive(0, 0, Robot.drivetrain.turnPID.get());
		this.timer.update();
	}

	@Override
	protected boolean isFinished() {
		return this.timer.isDone();
	}

	@Override
	protected void end() {
		Robot.drivetrain.turnPID.disable();
		Robot.drivetrain.drive(0, 0, 0);
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.turnPID.disable();
		Robot.drivetrain.drive(0, 0, 0);
	}
}
