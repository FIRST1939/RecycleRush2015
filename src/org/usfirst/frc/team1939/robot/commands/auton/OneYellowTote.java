package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.Turn90;
import org.usfirst.frc.team1939.robot.commands.lifter.SetLifterHeight;
import org.usfirst.frc.team1939.util.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneYellowTote extends CommandGroup {
    
    public  OneYellowTote() {
        this.addSequential(new SetLifterHeight(15));
        this.addSequential(new Turn90(Direction.RIGHT));
        this.addSequential(new DriveByInches(130, 0.5));
        this.addSequential(new SetLifterHeight(5));
    }
}
