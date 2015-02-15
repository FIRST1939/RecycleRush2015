package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.subsystems.Poker;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PokerIn extends CommandGroup {

    public PokerIn() {
        this.addSequential(new SetPokerPosition(Poker.IN_REVOLUTIONS));
    }

}
