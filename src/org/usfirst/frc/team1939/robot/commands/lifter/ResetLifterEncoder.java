package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetLifterEncoder extends Command {

    public ResetLifterEncoder() {
        requires(Robot.lifter);
    }

    protected void initialize() {
    	Robot.lifter.enable();
    	Robot.lifter.resetEncoder();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.lifter.disable();
    }

    protected void interrupted() {
    	Robot.lifter.disable();
    }
}
