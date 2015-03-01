package org.usfirst.frc.team1939.robot.commands.rollerclaw;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.RollerClaw;

import edu.wpi.first.wpilibj.command.Command;

public class CloseRollerClaw extends Command {

    public CloseRollerClaw() {
    }

    protected void initialize() {
    	this.setTimeout(RollerClaw.TIME);
    	Robot.rollerClaw.move(-RollerClaw.FULL_SPEED);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    	Robot.rollerClaw.move(-RollerClaw.PART_SPEED);
    }

    protected void interrupted() {
    	Robot.rollerClaw.move(0);
    }
}
