package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Poker;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class SetPokerPosition extends Command {
	
	private double rotations;
	private PIDTimer timer;
	
    public SetPokerPosition(double rotations) {
    	this.rotations = rotations;
        requires(Robot.poker);
    }

    protected void initialize() {
    	timer = new PIDTimer(()->Robot.poker.getPosition(), rotations, 0.1, 100);
    	Poker.isIn = false;
    	Robot.poker.enable();
    	Robot.poker.setPosition(rotations);
    }

    protected void execute() {
    	timer.update();
    }

    protected boolean isFinished() {
        return timer.isDone();
    }

    protected void end() {
    	if(rotations==Poker.IN_REVOLUTIONS) Poker.isIn = true;
    	Robot.poker.disable();
    }

    protected void interrupted() {
    	Robot.poker.disable();
    }
}
