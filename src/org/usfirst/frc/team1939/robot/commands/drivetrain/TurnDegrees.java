package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class TurnDegrees extends Command {
	
	private double degrees;
	private PIDTimer timer;
	
    public TurnDegrees(double degrees) {
    	this.degrees = degrees;
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	timer = new PIDTimer(()->Robot.drivetrain.getAngle(), degrees, 3, 100);
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.turnPID.enable();
    	Robot.drivetrain.turnPID.setSetpoint(degrees);
    }

    protected void execute() {
    	Robot.drivetrain.drive(0, 0, Robot.drivetrain.turnPID.get());
    	timer.update();
    }

    protected boolean isFinished() {
        return timer.isDone();
    }

    protected void end() {
    	Robot.drivetrain.turnPID.disable();
    }

    protected void interrupted() {
    	Robot.drivetrain.turnPID.disable();
    }
}
