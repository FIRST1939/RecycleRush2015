package org.usfirst.frc.team1939.robot.commands.rollerclaw;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetRollerClawSpeed extends Command {

	private double speed;
	
    public SetRollerClawSpeed(double speed) {
    	this.speed = speed;
    }

    protected void initialize() {
    	Robot.rollerClaw.spin(speed, 0);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
