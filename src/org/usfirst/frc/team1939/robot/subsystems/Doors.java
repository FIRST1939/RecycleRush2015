package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Doors extends Subsystem {

	public static final double TIME = 0.2;
	private static final double SPEED = 0.5;

	public CANTalon right = new CANTalon(RobotMap.talonDoorsRight);
	public CANTalon left = new CANTalon(RobotMap.talonDoorsLeft);

	private DigitalInput leftClosed = new DigitalInput(RobotMap.doorsLeftClosed);
	private DigitalInput rightClosed = new DigitalInput(RobotMap.doorsRightClosed);
	
	public boolean areOpen = true;
	
	public Doors() {
	}

	public void initDefaultCommand() {
	}

	public void spinOpen() {
		areOpen = true;
		right.set(SPEED);
		left.set(SPEED);
	}

	public void spinClosed() {
		areOpen = false;
		right.set(-SPEED);
		left.set(-SPEED);
	}

	public void stop() {
		this.stopLeft();
		this.stopRight();
	}

	public void stopLeft() {
		left.set(0);
	}

	public void stopRight() {
		right.set(0);
	}

	public boolean leftClosed() {
		return leftClosed.get();
	}

	public boolean rightClosed() {
		return rightClosed.get();
	}

	public boolean areClosed() {
		return leftClosed() && rightClosed();
	}
}
