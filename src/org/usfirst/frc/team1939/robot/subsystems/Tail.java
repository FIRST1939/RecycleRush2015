package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Tail extends Subsystem {

	public CANTalon tail = new CANTalon(RobotMap.talonTail);

	public void initDefaultCommand() {

	}
}
