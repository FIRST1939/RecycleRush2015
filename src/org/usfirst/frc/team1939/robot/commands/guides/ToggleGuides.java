package org.usfirst.frc.team1939.robot.commands.guides;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleGuides extends Command {

	private Command command;
	
    public ToggleGuides() {
        requires(Robot.guides);
    }

    protected void initialize() {
    	if(Robot.guides.isIn()){
    		command = new GuidesOut();
    	}else{
    		command = new GuidesIn();
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
