package org.usfirst.frc.team1939.robot.commands.rollerclaw;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadRollerClaw extends Command {

    public GamepadRollerClaw() {
        requires(Robot.rollerClaw);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.rollerClaw.spin(-Robot.oi.gamepad.getRightY()*
    			0.6, -Robot.oi.gamepad.getRightX()*0.6);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.rollerClaw.move(0);
    	Robot.rollerClaw.spin(0, 0);
    }

    protected void interrupted() {
    	Robot.rollerClaw.move(0);
    	Robot.rollerClaw.spin(0, 0);
    }
}
