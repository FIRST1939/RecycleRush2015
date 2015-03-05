package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveFromLine extends CommandGroup {
    
    public  DriveFromLine() {
        this.addSequential(new DriveByInches(53, 0.3));
    }
}
