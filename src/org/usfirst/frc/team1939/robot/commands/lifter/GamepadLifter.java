package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Poker;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadLifter extends Command {

    public GamepadLifter() {
    	requires(Robot.lifter);
    }

    protected void initialize() {
    	Robot.lifter.throttleMode();
    	Robot.lifter.enable();
    }

    protected void execute() {
    	if(Poker.isIn){
    		Robot.lifter.setSpeed(-Robot.oi.gamepad.getLeftY());
    	}
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
