package org.usfirst.frc.team1939.robot.commands.rollerclaw;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinRollerClaw extends Command {

	private double time;
	private double speed;
	
    public SpinRollerClaw(double time, double speed) {
        requires(Robot.rollerClaw);
        this.time = time;
        this.speed = speed;
    }

    protected void initialize() {
    	this.setTimeout(time);
    }

    protected void execute() {
    	Robot.rollerClaw.spin(speed, 0);
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    	Robot.rollerClaw.spin(0, 0);
    }

    protected void interrupted() {
    	Robot.rollerClaw.spin(0, 0);
    }
}
