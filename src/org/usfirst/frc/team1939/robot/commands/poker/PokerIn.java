package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PokerIn extends Command {

    public PokerIn() {
        requires(Robot.poker);
    }

    protected void initialize() {
    	Robot.poker.spinIn();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.poker.isIn();
    }

    protected void end() {
    	Robot.poker.isOut = false;
    	Robot.poker.stop();
    }

    protected void interrupted() {
    	Robot.poker.stop();
    }
}
