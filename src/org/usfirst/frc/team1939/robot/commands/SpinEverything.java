package org.usfirst.frc.team1939.robot.commands;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinEverything extends Command {

	public SpinEverything() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.tail.tail.set(Robot.oi.leftStick.getX());
		Robot.doors.left.set(Robot.oi.rightStick.getX());
		Robot.doors.right.set(Robot.oi.rightStick.getY());
		Robot.poker.poker.set(Robot.oi.leftStick.getY());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.tail.tail.set(0);
		Robot.doors.left.set(0);
		Robot.doors.right.set(0);
		Robot.poker.poker.set(0);
	}

	protected void interrupted() {
		Robot.tail.tail.set(0);
		Robot.doors.left.set(0);
		Robot.doors.right.set(0);
		Robot.poker.poker.set(0);
	}
}
