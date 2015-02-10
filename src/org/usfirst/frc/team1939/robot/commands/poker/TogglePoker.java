package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TogglePoker extends Command {

	private Command command;
	
    public TogglePoker() {
    }

    protected void initialize() {
    	if(Robot.poker.isOut){
    		command = new PokerIn();
    	}else{
    		command = new PokerOut();
    	}
    	command.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return !command.isRunning();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
