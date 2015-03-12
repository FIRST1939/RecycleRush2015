package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StrafeByTime extends Command {

	public static final double RIGHT = 1.0;
	public static final double LEFT = -RIGHT;
	
	private double time;
	private double speed;
	
    public StrafeByTime(double time, double speed) {
    	requires(Robot.drivetrain);
    	this.time = time;
    	this.speed = speed;
    }

    protected void initialize() {
    	this.setTimeout(time);
    	Robot.drivetrain.resetGyro();
    }

    protected void execute() {
    	Robot.drivetrain.driveWithGyro(speed, 0, 0, 1);
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    	Robot.drivetrain.drive(0, 0, 0);
    }

    protected void interrupted() {
    	Robot.drivetrain.drive(0, 0, 0);
    }
}
