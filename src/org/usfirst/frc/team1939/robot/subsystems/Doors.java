package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Doors extends Subsystem {

	private static final double SPEED = 1.0;

	private Talon right = new Talon(RobotMap.doorsRight);
	private Talon left = new Talon(RobotMap.doorsLeft);

	private DigitalInput leftOpen = new DigitalInput(RobotMap.doorsLeftOpen);
	private DigitalInput leftClosed = new DigitalInput(RobotMap.doorsLeftClosed);
	private DigitalInput rightOpen = new DigitalInput(RobotMap.doorsRightOpen);
	private DigitalInput rightClosed = new DigitalInput(
			RobotMap.doorsRightClosed);

	public void initDefaultCommand() {
	}

	public void spinOpen() {
		right.set(SPEED);
		left.set(-SPEED);
	}

	public void spinClosed() {
		right.set(-SPEED);
		left.set(SPEED);
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

	public boolean leftOpen() {
		return leftOpen.get();
	}

	public boolean leftClosed() {
		return leftClosed.get();
	}

	public boolean rightOpen() {
		return rightOpen.get();
	}

	public boolean rightClosed() {
		return rightClosed.get();
	}

	public boolean areOpen() {
		return leftOpen() && rightOpen();
	}

	public boolean areClosed() {
		return leftClosed() && rightClosed();
	}
}