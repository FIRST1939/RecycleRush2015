package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LifterTester extends Command {

	public LifterTester() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		Robot.lifter.resetEncoder();
		SmartDashboard.putNumber("Setpoint", 0);
		Robot.lifter.enable();
		Robot.lifter.positionMode();
	}

	protected void execute() {
		Robot.lifter.setPosition(SmartDashboard.getNumber("Setpoint"));
		SmartDashboard.putNumber("Encoder", Robot.lifter.getPosition());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.lifter.disable();
	}

	protected void interrupted() {
		Robot.lifter.disable();
	}
}
