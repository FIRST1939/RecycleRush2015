package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.SpeedControllerSendable;
import org.usfirst.frc.team1939.robot.commands.tail.GamepadTail;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Tail extends Subsystem {

	public CANTalon tail = new CANTalon(RobotMap.talonTail);

	public Tail() {
		LiveWindow.addActuator("Tail", "motor", new SpeedControllerSendable(tail));
	}
	
	public void initDefaultCommand() {
		this.setDefaultCommand(new GamepadTail());
	}
}
