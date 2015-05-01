package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.Turn90;
import org.usfirst.frc.team1939.robot.commands.lifter.SetLifterHeight;
import org.usfirst.frc.team1939.robot.commands.rollerclaw.MoveRollerClaw;
import org.usfirst.frc.team1939.robot.commands.rollerclaw.SetRollerClawSpeed;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;
import org.usfirst.frc.team1939.util.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneContainerOneTote extends CommandGroup {

	public OneContainerOneTote() {
		this.addSequential(new SetLifterHeight(Lifter.CONTAINER_ABOVE_TOTE)); // Pick up container
		this.addSequential(new DriveByInches(30, 0.25)); // Drive over tote
		this.addSequential(new SetRollerClawSpeed(-1.0));
		this.addSequential(new MoveRollerClaw(1.0, MoveRollerClaw.CLOSE));
		this.addSequential(new Turn90(Direction.LEFT)); // Turn towards auto zone  TURNS LEFT
		this.addSequential(new DriveByInches(130, 0.5)); // Drive into auto zone
		this.addSequential(new SetRollerClawSpeed(0));
	}
	
}
