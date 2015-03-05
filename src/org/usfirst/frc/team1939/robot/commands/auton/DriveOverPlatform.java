package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveOverPlatform extends CommandGroup {
    
    public  DriveOverPlatform() {
        this.addSequential(new DriveByInches(87.25, 0.3));
    }
}
