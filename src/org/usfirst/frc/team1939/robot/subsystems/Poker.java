package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.SpeedControllerSendable;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Poker extends Subsystem {

	private static final double IN_SPEED = 1.0;
	private static final double OUT_SPEED = -IN_SPEED;
	
	public CANTalon poker = new CANTalon(RobotMap.talonPoker);
	
	public boolean isOut = false;

	public Poker() {
		LiveWindow.addActuator("Poker", "motor", new SpeedControllerSendable(poker));
	}
	
	protected void initDefaultCommand() {
	}

	public void spinIn(){
		poker.set(IN_SPEED);
	}
	
	public void spinOut(){
		poker.set(OUT_SPEED);
	}
	
	public void stop(){
		poker.set(0);
	}
	
}
