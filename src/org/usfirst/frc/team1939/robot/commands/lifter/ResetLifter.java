package org.usfirst.frc.team1939.robot.commands.lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ResetLifter extends CommandGroup {
    
    public  ResetLifter() {
    	this.addSequential(new MoveLifterToBottom());
    	this.addSequential(new ResetLifterEncoder());
    }
}
