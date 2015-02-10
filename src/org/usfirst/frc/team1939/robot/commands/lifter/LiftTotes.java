package org.usfirst.frc.team1939.robot.commands.lifter;

import org.usfirst.frc.team1939.robot.commands.Wait;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftTotes extends CommandGroup {
    
	/**
	 * Go down and lift back up to a height in totes
	 * @param totes number of totes
	 */
    public  LiftTotes(int totes) {
       this.addSequential(new ResetLifter()); // Go to bottom
       this.addSequential(new Wait(0.2)); // Sleep
       this.addSequential(new SetLifterHeight(Lifter.TOTE_HEIGHT*totes + Lifter.HOLD_HEIGHT)); // Go up
    }
}
