package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PokerOut extends Command {

    public PokerOut() {
        requires(Robot.poker);
    }

    protected void initialize() {
    	Robot.poker.spinOut();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;//Robot.poker.isOut();
    }

    protected void end() {
    	Robot.poker.isOut = true;
    	Robot.poker.stop();
    }

    protected void interrupted() {
    	Robot.poker.stop();
    }
}
