package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.lifter.SetLifterHeight;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAuton extends CommandGroup {
    
    public  TestAuton() {
        this.addSequential(new SetLifterHeight(Lifter.TOP));
        this.addSequential(new DriveByInches(12));
        this.addSequential(new SetLifterHeight(Lifter.ONE_TOTE));
    }
}
