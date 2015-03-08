package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnByTime;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnDegrees;
import org.usfirst.frc.team1939.robot.commands.lifter.SetLifterHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneYellowTote extends CommandGroup {
    
    public  OneYellowTote() {
        this.addSequential(new SetLifterHeight(15));
        this.addSequential(new TurnByTime(0.75, 0.5));
        this.addSequential(new DriveByInches(130, 0.5));
        this.addSequential(new SetLifterHeight(5));
    }
}
