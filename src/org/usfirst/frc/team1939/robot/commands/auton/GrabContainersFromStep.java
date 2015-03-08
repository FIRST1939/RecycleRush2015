package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.tail.SpinTailForTime;
import org.usfirst.frc.team1939.robot.subsystems.Tail;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabContainersFromStep extends CommandGroup {
    
    public  GrabContainersFromStep() {
        this.addSequential(new SpinTailForTime(Tail.OUT, 4.4));
        this.addSequential(new DriveByInches(-14, 0.25));
        this.addSequential(new SpinTailForTime(Tail.IN, 3));
        this.addSequential(new DriveByInches(83, 0.25));
        this.addSequential(new SpinTailForTime(Tail.OUT, 5));
    }
}
