package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.tail.SpinTailForTime;
import org.usfirst.frc.team1939.robot.subsystems.Tail;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabContainersFromStep extends CommandGroup {
    
    public  GrabContainersFromStep() {
        this.addSequential(new SpinTailForTime(Tail.OUT, 3.6));
        this.addSequential(new DriveByInches(-20, 0.25));
        this.addSequential(new SpinTailForTime(Tail.IN, 2));
        this.addSequential(new DriveByInches(26, 0.5));
        this.addParallel(new DriveByInches(36, 0.5));
        this.addSequential(new SpinTailForTime(Tail.OUT, 1));
        this.addParallel(new DriveByInches(36, 0.5));
        this.addSequential(new SpinTailForTime(Tail.IN, 4));
    }
}
