package org.usfirst.frc.team1939.robot.commands.rollerclaw;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.RollerClaw;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadRollerClaw extends Command {

	double partSpeed = RollerClaw.PART_SPEED;
	boolean opened = false;
	
    public GamepadRollerClaw() {
        requires(Robot.rollerClaw);
    }

    protected void initialize() {
    }

    protected void execute() {
    	boolean open = Robot.oi.gamepad.leftTrigger.get();
    	boolean close = Robot.oi.gamepad.leftButton.get();
    	double speed = partSpeed;
    	if(open && !close){
    		speed = -RollerClaw.FULL_SPEED;
    		partSpeed = -RollerClaw.PART_SPEED;
    	}else if(close && !open){
    		speed = RollerClaw.FULL_SPEED;
    		partSpeed = 0;
    	}
    	Robot.rollerClaw.move(speed);
    	Robot.rollerClaw.spin(-Robot.oi.gamepad.getRightY()*0.75, -Robot.oi.gamepad.getRightX()*0.75);
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
