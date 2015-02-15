package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnDegrees;
import org.usfirst.frc.team1939.robot.commands.lifter.SetLifterHeight;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneContainerOneTote extends CommandGroup {

	public OneContainerOneTote() {
		this.addSequential(new SetLifterHeight(Lifter.CONTAINER_ABOVE_TOTE)); // Pick up container
		this.addSequential(new DriveByInches(30)); // Drive over tote
		this.addSequential(new SetLifterHeight(Lifter.CORRAL)); // Corral tote
		this.addSequential(new TurnDegrees(90)); // Turn towards auto zone  TURNS RIGHT
		this.addSequential(new DriveByInches(96)); // Drive into auto zone
		this.addSequential(new SetLifterHeight(Lifter.CONTAINER_ABOVE_TOTE)); // Pick up container but leave tote down
		this.addSequential(new DriveByInches(-36)); // Pull away from totes
		this.addSequential(new TurnDegrees(180)); // Turn back towards alliance station
	}
	
}
