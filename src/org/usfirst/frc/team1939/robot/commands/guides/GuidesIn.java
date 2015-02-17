package org.usfirst.frc.team1939.robot.commands.guides;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Guides;

import edu.wpi.first.wpilibj.command.Command;

public class GuidesIn extends Command {

    public GuidesIn() {
        requires(Robot.guides);
    }

    protected void initialize() {
    	this.setTimeout(Guides.SPIN_TIME);
    	Robot.guides.spinIn();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    	Robot.guides.stop();
    }

    protected void interrupted() {
    	Robot.guides.stop();
    }
    
}
