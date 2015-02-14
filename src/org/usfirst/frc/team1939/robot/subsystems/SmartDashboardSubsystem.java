package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.commands.SmartDashboardUpdater;
import org.usfirst.frc.team1939.robot.commands.SpinEverything;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DrivetrainTester;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc.team1939.robot.commands.lifter.MoveLifterToBottom;
import org.usfirst.frc.team1939.robot.commands.lifter.MoveLifterToTop;
import org.usfirst.frc.team1939.robot.commands.lifter.ResetLifterEncoder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardSubsystem extends Subsystem {

	public SmartDashboardSubsystem() {
		Command[] commands = {
				//new CloseDoors(),
				//new OpenDoors(),
				//new PokerIn(),
				//new PokerOut(),
				new ResetGyro(),
				new ResetLifterEncoder(),
				new MoveLifterToBottom(),
				new MoveLifterToTop(),
				new DrivetrainTester(),
				new SpinEverything()
			};
		for (Command c : commands) SmartDashboard.putData(c);
		SmartDashboard.putData(Scheduler.getInstance());
	}
	
    public void initDefaultCommand() {
        this.setDefaultCommand(new SmartDashboardUpdater());
    }
}

