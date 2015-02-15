package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByInches extends Command {
	
	private double inches;
	private PIDTimer timer;
	
    public DriveByInches(double inches) {
    	this.inches = inches;
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	this.timer = new PIDTimer(()->Robot.drivetrain.getForwardDistance(), inches, 3, 100);
    	System.out.println("Started Driving Inches: " + inches);
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.movePID.enable();
    	Robot.drivetrain.movePID.setSetpoint(inches);
    }

    protected void execute() {
    	Robot.drivetrain.drive(0, Robot.drivetrain.movePID.get(), 0);
    	timer.update();
    }

    protected boolean isFinished() {
        return timer.isDone();
    }

    protected void end() {
    	System.out.println("Finished Driving Inches: " + inches);
    	Robot.drivetrain.movePID.disable();
    }

    protected void interrupted() {
    	Robot.drivetrain.movePID.disable();
    }
}
