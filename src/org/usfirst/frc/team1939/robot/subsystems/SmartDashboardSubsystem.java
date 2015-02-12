package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.commands.SmartDashboardUpdater;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SmartDashboardSubsystem extends Subsystem {

    public void initDefaultCommand() {
        this.setDefaultCommand(new SmartDashboardUpdater());
    }
}

