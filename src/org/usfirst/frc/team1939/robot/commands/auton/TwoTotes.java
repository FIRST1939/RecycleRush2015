package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.StrafeByTime;
import org.usfirst.frc.team1939.robot.commands.lifter.SetLifterHeight;
import org.usfirst.frc.team1939.robot.commands.rollerclaw.MoveRollerClaw;
import org.usfirst.frc.team1939.robot.commands.rollerclaw.SpinRollerClaw;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoTotes extends CommandGroup {
    
    public  TwoTotes() {
        this.addSequential(new SetLifterHeight(Lifter.ONE_TOTE));
        this.addSequential(new StrafeByTime(1, StrafeByTime.RIGHT));
        this.addSequential(new DriveByInches(20, 0.5));
        this.addSequential(new StrafeByTime(1, StrafeByTime.LEFT));
        this.addSequential(new DriveByInches(28, 0.5));
        //this.addSequential(new MoveRollerClaw(1.0, MoveRollerClaw.CLOSE));
        //this.addSequential(new SpinRollerClaw(1.0, -1.0));
        //this.addSequential(new MoveRollerClaw(1.0, MoveRollerClaw.OPEN));
        this.addSequential(new SetLifterHeight(Lifter.BOTTOM));
        this.addSequential(new SetLifterHeight(Lifter.HOLD));
        this.addSequential(new StrafeByTime(2.5, StrafeByTime.RIGHT));
    }
}
