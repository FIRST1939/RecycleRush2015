package org.usfirst.frc.team1939.robot.commands.poker;

import org.usfirst.frc.team1939.robot.subsystems.Poker;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PokerOut extends CommandGroup {

    public PokerOut() {
        this.addSequential(new SetPokerPosition(Poker.OUT_REVOLUTIONS));
    }

}

