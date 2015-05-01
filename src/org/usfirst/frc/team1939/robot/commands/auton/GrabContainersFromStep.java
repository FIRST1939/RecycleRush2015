package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.tail.SpinTailForTime;
import org.usfirst.frc.team1939.robot.subsystems.Tail;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabContainersFromStep extends CommandGroup {
    
    public  GrabContainersFromStep() {
        this.addSequential(new SpinTailForTime(Tail.OUT, 4.5));
        this.addSequential(new DriveByInches(-20, 0.2));
        this.addSequential(new SpinTailForTime(Tail.IN, 3));
        this.addParallel(new SpinTailForTime(Tail.IN, 1));
        this.addSequential(new DriveByInches(83, 0.5));
        this.addSequential(new SpinTailForTime(Tail.OUT, 2));
    }
}
