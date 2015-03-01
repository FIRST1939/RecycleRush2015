package org.usfirst.frc.team1939.robot.commands.smartdashboard;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdater extends Command {
	
    public SmartDashboardUpdater() {
    	this.requires(Robot.sds);
    }

    protected void initialize() {
    	SmartDashboard.putBoolean("Override", Lifter.OVERRIDE);
    }

    protected void execute() {
    	SmartDashboard.putBoolean("Lifter Up", Robot.lifter.isUp());
    	SmartDashboard.putBoolean("Lifter Down", Robot.lifter.isDown());
    	SmartDashboard.putNumber("Lifter Position", Robot.lifter.getPosition());
    	SmartDashboard.putNumber("Lifter Speed", Robot.lifter.getSpeed());
    	
    	SmartDashboard.putNumber("Drivetrain forward", Robot.drivetrain.getForwardDistance());
    	
    	Lifter.OVERRIDE = SmartDashboard.getBoolean("Override");
    	if(Lifter.OVERRIDE){
    		Robot.lifter.disableLimits();
    	}else{
    		Robot.lifter.enableLimits();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
