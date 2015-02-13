package org.usfirst.frc.team1939.robot.commands.tail;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadTail extends Command {

    public GamepadTail() {
        requires(Robot.tail);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.tail.tail.set(Robot.oi.gamepad.getRightY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.tail.tail.set(0);
    }

    protected void interrupted() {
    	Robot.tail.tail.set(0);
    }
}
