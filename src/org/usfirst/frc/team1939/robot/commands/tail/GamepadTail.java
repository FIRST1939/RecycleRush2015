package org.usfirst.frc.team1939.robot.commands.tail;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Tail;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadTail extends Command {

    public GamepadTail() {
        requires(Robot.tail);
    }

    protected void initialize() {
    }

    protected void execute() {
    	boolean in = Robot.oi.gamepad.rightButton.get();
    	boolean out = Robot.oi.gamepad.rightTrigger.get();
    	if(in && !out){
    		Robot.tail.spin(Tail.IN);
    	}else if(out && !in){
    		Robot.tail.spin(Tail.OUT);
    	}else{
    		Robot.tail.spin(0);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.tail.spin(0);
    }

    protected void interrupted() {
    	Robot.tail.spin(0);
    }
}
