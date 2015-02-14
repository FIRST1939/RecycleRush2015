package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLifterToTop extends Command {

    public MoveLifterToTop() {
        requires(Robot.lifter);
    }

    protected void initialize() {
    	Robot.lifter.enable();
    	Robot.lifter.throttleMode();
    	Robot.lifter.setSpeed(Lifter.UP);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.lifter.isUp();
    }

    protected void end() {
    	Robot.lifter.setSpeed(0);
    	Robot.lifter.disable();
    }

    protected void interrupted() {
    	Robot.lifter.setSpeed(0);
    	Robot.lifter.disable();
    }
}
