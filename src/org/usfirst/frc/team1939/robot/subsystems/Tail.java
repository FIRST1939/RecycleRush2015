package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.tail.GamepadTail;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Tail extends Subsystem {

	public CANTalon tail = new CANTalon(RobotMap.talonTail);

	public Tail() {
	}
	
	public void initDefaultCommand() {
		this.setDefaultCommand(new GamepadTail());
	}
}
