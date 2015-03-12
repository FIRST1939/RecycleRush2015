package org.usfirst.frc.team1939.robot.commands.rollerclaw;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.RollerClaw;

import edu.wpi.first.wpilibj.command.Command;

public class MoveRollerClaw extends Command {

	public static final double CLOSE = RollerClaw.FULL_SPEED;
	public static final double OPEN = -CLOSE;
	
	private double time;
	private double speed;
	
    public MoveRollerClaw(double time, double speed) {
        requires(Robot.rollerClaw);
        this.time = time;
        this.speed = speed;
    }

    protected void initialize() {
    	this.setTimeout(time);
    }

    protected void execute() {
    	Robot.rollerClaw.move(speed);
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    	Robot.rollerClaw.move(0);
    }

    protected void interrupted() {
    	Robot.rollerClaw.move(0);
    }
}
