package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByInches extends Command {
	
	private double inches;
	private double speed;
	private PIDTimer timer;
	
    public DriveByInches(double inches, double speed) {
    	this.inches = inches;
    	this.speed = speed;
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	Robot.drivetrain.setMoveMaxSpeed(speed);
    	this.timer = new PIDTimer(()->Robot.drivetrain.getSpeed(), 0, 1, 100);
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.movePID.enable();
    	Robot.drivetrain.movePID.setSetpoint(inches);
    	
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.turnPID.enable();
    	Robot.drivetrain.turnPID.setSetpoint(0);
    }

    protected void execute() {
    	Robot.drivetrain.drive(0, -Robot.drivetrain.movePID.get(), Robot.drivetrain.turnPID.get());
    	timer.update();
    }

    protected boolean isFinished() {
        return timer.isDone();
    }

    protected void end() {
    	Robot.drivetrain.movePID.disable();
    	Robot.drivetrain.turnPID.disable();
    }

    protected void interrupted() {
    	Robot.drivetrain.movePID.disable();
    	Robot.drivetrain.turnPID.disable();
    }
}
