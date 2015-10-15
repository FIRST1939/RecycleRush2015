package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.Direction;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class Turn90 extends Command {

	private Direction d;
	private boolean useGyro;

	private PIDTimer timer;
	
	private double speed;
	
    public Turn90(Direction d) {
        requires(Robot.drivetrain);
        this.d = d;
    }

    protected void initialize() {
		double degrees = 0;
		if(d==Direction.LEFT){
			degrees = -90;
		}else if(d==Direction.RIGHT){
			degrees = 90;
		}
		timer = new PIDTimer(()->Robot.drivetrain.getSpeed(), 0, 1, 500);
    	Robot.ahrs.reset();
    	Robot.drivetrain.turnPID.enable();
    	Robot.drivetrain.turnPID.setSetpoint(degrees);
    }

    protected void execute() {
    	if(useGyro){
    		Robot.drivetrain.drive(0, 0, Robot.drivetrain.turnPID.get());
        	timer.update();
    	}else{
    		Robot.drivetrain.drive(0, 0, speed);
    	}
    }

    protected boolean isFinished() {
        if(useGyro && timer.isDone()) return true;
        else if(!useGyro && this.isTimedOut()) return true;
        else return false;
    }

    protected void end() {
    	Robot.drivetrain.turnPID.disable();
    	Robot.drivetrain.drive(0, 0, 0);
    }

    protected void interrupted() {
    	Robot.drivetrain.turnPID.disable();
    	Robot.drivetrain.drive(0, 0, 0);
    }
}
