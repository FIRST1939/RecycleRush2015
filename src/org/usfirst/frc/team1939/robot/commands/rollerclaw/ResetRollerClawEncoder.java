package org.usfirst.frc.team1939.robot.commands.rollerclaw;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetRollerClawEncoder extends Command {

	public ResetRollerClawEncoder() {

	}

	@Override
	protected void initialize() {
		Robot.rollerClaw.resetEncoder();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
