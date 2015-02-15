package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByInches extends Command {

	private static final double MARGIN = 0.05;
	private static final double SETLE_TIME = 100;
	
	private double inches;
	private long timeSettled=0;
	
    public DriveByInches(double inches) {
    	this.inches = inches;
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	System.out.println("Started Driving Inches: " + inches);
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.movePID.enable();
    	Robot.drivetrain.movePID.setSetpoint(inches);
    }

    protected void execute() {
    	Robot.drivetrain.drive(0, Robot.drivetrain.movePID.get(), 0);
    	
    	if (Math.abs(Robot.drivetrain.movePID.get()) <= MARGIN) {
			timeSettled = System.currentTimeMillis();
		} else {
			timeSettled = 0;
		}
    }

    protected boolean isFinished() {
        return timeSettled != 0 && System.currentTimeMillis() - timeSettled > SETLE_TIME;
    }

    protected void end() {
    	System.out.println("Finished Driving Inches: " + inches);
    	Robot.drivetrain.movePID.disable();
    }

    protected void interrupted() {
    	Robot.drivetrain.movePID.disable();
    }
}
