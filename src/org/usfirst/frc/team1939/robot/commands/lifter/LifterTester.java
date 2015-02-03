package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LifterTester extends Command {

	public LifterTester() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		SmartDashboard.putNumber("Speed", 0);
		Robot.lifter.throttleMode();
		Robot.lifter.enable(); // Robot.lifter.enableControl();
	}

	protected void execute() {
		Robot.lifter.setSpeed(SmartDashboard.getNumber("Speed"));
		SmartDashboard.putNumber("Encoder", Robot.lifter.getPosition());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.lifter.setSpeed(0);
		Robot.lifter.disable();
	}

	protected void interrupted() {
		Robot.lifter.setSpeed(0);
		Robot.lifter.disable();
	}
}
