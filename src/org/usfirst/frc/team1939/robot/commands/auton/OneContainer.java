package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.lifter.SetLifterHeight;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneContainer extends CommandGroup {

	public OneContainer() {
		this.addSequential(new SetLifterHeight(Lifter.CONTAINER_ABOVE_TOTE)); // Pick up container
		this.addSequential(new DriveByInches(130, 0.5)); // Drive into auto zone
	}
	
}
