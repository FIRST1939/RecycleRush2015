package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByInches extends Command {

	private double inches;
	private double speed;
	private PIDTimer timer;

	public DriveByInches(double inches, double speed) {
		this.inches = inches;
		this.speed = speed;
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.setMoveMaxSpeed(this.speed);
		this.timer = new PIDTimer(() -> Robot.drivetrain.getSpeed(), 0, 1, 500);
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.movePID.enable();
		Robot.drivetrain.movePID.setSetpoint(this.inches);

		Robot.ahrs.reset();
	}

	@Override
	protected void execute() {
		Robot.drivetrain.driveWithGyro(0, -Robot.drivetrain.movePID.get(), 0, 1.0);
		this.timer.update();
	}

	@Override
	protected boolean isFinished() {
		return this.timer.isDone();
	}

	@Override
	protected void end() {
		Robot.drivetrain.movePID.disable();
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.movePID.disable();
	}
}
