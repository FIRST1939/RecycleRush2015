package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnByTime extends CommandGroup {

	private double time;
	private double speed;
	
    public TurnByTime(double time, double speed) {
        requires(Robot.drivetrain);
        this.time=time;
        this.speed = speed;
    }
    
    protected void initialize() {
    	this.setTimeout(time);
    }
    
    protected void execute() {
    	Robot.drivetrain.drive(0, 0, speed);
    }
    
    protected boolean isFinished() {
    	return this.isTimedOut();
    }
    
}
