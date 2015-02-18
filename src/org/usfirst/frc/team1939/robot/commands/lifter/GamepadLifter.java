package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GamepadLifter extends Command {

    public GamepadLifter() {
    	requires(Robot.lifter);
    }

    protected void initialize() {
    	Robot.lifter.throttleMode();
    	Robot.lifter.enable();
    }

    protected void execute() {
    	double speed = -Robot.oi.gamepad.getLeftY();
		if(speed>0){
			if(Robot.lifter.getPosition()>=Lifter.TOP-1){
				speed*=0.25;
			}else if(Robot.lifter.getPosition()>=Lifter.TOP-2){
				speed*=0.5;
			}else if(Robot.lifter.getPosition()>=Lifter.TOP-3){
				speed*=0.75;
			}
		}
		if(speed<0){
			if(Robot.lifter.getPosition()<1){
				speed*=0.25;
			}else if(Robot.lifter.getPosition()<2){
				speed*=0.5;
			}else if(Robot.lifter.getPosition()<3){
				speed*=0.75;
			}
		}
		Robot.lifter.setSpeed(speed);
		SmartDashboard.putNumber("Lifter Gamepad Speed", speed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.lifter.disable();
    }

    protected void interrupted() {
    	Robot.lifter.disable();
    }
}
