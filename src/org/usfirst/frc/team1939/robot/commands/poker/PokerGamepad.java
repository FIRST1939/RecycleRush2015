package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Poker;

import edu.wpi.first.wpilibj.command.Command;

public class PokerGamepad extends Command {

    public PokerGamepad() {
        requires(Robot.poker);
    }

    protected void initialize() {
    	Robot.poker.enable();
    	Robot.poker.setPosition(Poker.IN_REVOLUTIONS);
    	Poker.isIn = true;
    }

    protected void execute() {
    	if(Robot.oi.gamepad.rightTrigger.get() && Robot.lifter.getPosition()<Poker.MAXIMUM_LIFTER_HEIGHT){
    		Robot.poker.setPosition(Poker.OUT_REVOLUTIONS);
    		Poker.isIn = false;
    	}else{
    		Robot.poker.setPosition(Poker.IN_REVOLUTIONS);
    		Poker.isIn = true;
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.poker.disable();
    }

    protected void interrupted() {
    	Robot.poker.disable();
    }
}
