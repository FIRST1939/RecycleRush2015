package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Poker;

import edu.wpi.first.wpilibj.command.Command;

public class SetPokerPosition extends Command {

	private static final double MARGIN = 0.05;
	
	private double rotations;
	
    public SetPokerPosition(double rotations) {
    	this.rotations = rotations;
        requires(Robot.poker);
    }

    protected void initialize() {
    	Poker.isIn = false;
    	Robot.poker.enable();
    	Robot.poker.setPosition(rotations);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.poker.getSpeed()<MARGIN;
    }

    protected void end() {
    	if(rotations==Poker.IN_REVOLUTIONS) Poker.isIn = true;
    	Robot.poker.disable();
    }

    protected void interrupted() {
    	Robot.poker.disable();
    }
}
