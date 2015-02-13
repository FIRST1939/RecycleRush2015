package org.usfirst.frc.team1939.robot.commands;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdater extends Command {

    public SmartDashboardUpdater() {
    	this.requires(Robot.sms);
    }

    protected void initialize() {
    }

    protected void execute() {
    	SmartDashboard.putBoolean("Lifter Up", Robot.lifter.isUp());
    	SmartDashboard.putBoolean("Lifter Down", Robot.lifter.isDown());
    	
    	SmartDashboard.putBoolean("Left Open", Robot.doors.leftOpen());
    	SmartDashboard.putBoolean("Left Closed", Robot.doors.leftClosed());
    	SmartDashboard.putBoolean("Right Open", Robot.doors.rightOpen());
    	SmartDashboard.putBoolean("Right Closed", Robot.doors.rightClosed());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}