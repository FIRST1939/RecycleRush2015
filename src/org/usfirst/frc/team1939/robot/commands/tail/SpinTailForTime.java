package org.usfirst.frc.team1939.robot.commands.tail;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinTailForTime extends Command {

	private double speed;
	private double time;
	
    public SpinTailForTime(double speed, double time) {
        this.speed = speed;
        this.time = time;
        this.requires(Robot.tail);
    }

    protected void initialize() {
    	this.setTimeout(time);
    	Robot.tail.spin(speed);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    	Robot.tail.spin(0);
    }

    protected void interrupted() {
    	Robot.tail.spin(0);
    }
}
