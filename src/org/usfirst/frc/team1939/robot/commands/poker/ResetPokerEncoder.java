package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetPokerEncoder extends Command {

    public ResetPokerEncoder() {
        requires(Robot.poker);
    }

    protected void initialize() {
    	Robot.poker.resetEncoder();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
