package org.usfirst.frc.team1939.robot.commands.doors;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleDoors extends Command {

	private Command command;
	
    public ToggleDoors() {
    }

    protected void initialize() {
    	if(Robot.doors.areOpen){
    		command = new CloseDoors();
    	}else{
    		command = new OpenDoors();
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
