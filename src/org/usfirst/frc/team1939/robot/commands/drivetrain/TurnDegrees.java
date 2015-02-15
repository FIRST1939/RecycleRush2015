package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnDegrees extends Command {

	private static final double MARGIN = 0.05;
	private static final double SETLE_TIME = 100;
	
	private double degrees;
	private double timeSettled = 0;
	
    public TurnDegrees(double degrees) {
    	this.degrees = degrees;
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.turnPID.enable();
    	Robot.drivetrain.turnPID.setSetpoint(degrees);
    }

    protected void execute() {
    	Robot.drivetrain.drive(0, 0, Robot.drivetrain.turnPID.get());
    	
    	if (Math.abs(Robot.drivetrain.turnPID.get()) <= MARGIN) {
			timeSettled = System.currentTimeMillis();
		} else {
			timeSettled = 0;
		}
    }

    protected boolean isFinished() {
        return timeSettled != 0 && System.currentTimeMillis() - timeSettled > SETLE_TIME;
    }

    protected void end() {
    	Robot.drivetrain.turnPID.disable();
    }

    protected void interrupted() {
    	Robot.drivetrain.turnPID.disable();
    }
}
