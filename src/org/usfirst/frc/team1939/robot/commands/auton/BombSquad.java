package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.StrafeByTime;
import org.usfirst.frc.team1939.robot.commands.lifter.ResetLifterEncoder;
import org.usfirst.frc.team1939.robot.commands.lifter.SetLifterHeight;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BombSquad extends CommandGroup {
    
    public  BombSquad() {
    	this.addSequential(new ResetLifterEncoder());
    	this.addSequential(new SetLifterHeight(5));
    	this.addSequential(new DriveByInches(-9, 0.4));
    	this.addSequential(new StrafeByTime(2.5));
    	this.addSequential(new SetLifterHeight(-5));
    	this.addParallel(new DriveByInches(36, 0.5));
    }
}
