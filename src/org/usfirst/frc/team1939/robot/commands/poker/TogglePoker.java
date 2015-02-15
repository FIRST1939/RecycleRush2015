package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.subsystems.Poker;

import edu.wpi.first.wpilibj.command.Command;

public class TogglePoker extends Command {

	private Command command;
	
    public TogglePoker() {
    }

    protected void initialize() {
    	if(Poker.isIn){
    		command = new PokerOut();
    	}else{
    		command = new PokerIn();
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
