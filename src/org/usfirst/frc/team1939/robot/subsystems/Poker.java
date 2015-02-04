package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Poker extends Subsystem {

	public CANTalon poker = new CANTalon(RobotMap.talonPoker);

	protected void initDefaultCommand() {

	}

}
