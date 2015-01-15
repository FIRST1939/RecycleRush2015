package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Doors extends Subsystem {

	private static final double SPEED = 1.0;

	private Talon right = new Talon(RobotMap.doorsRight);
	private Talon left = new Talon(RobotMap.doorsLeft);

	private DigitalInput open = new DigitalInput(RobotMap.doorsOpen);
	private DigitalInput closed = new DigitalInput(RobotMap.doorsClosed);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
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
		right.set(0);
		left.set(0);
	}

	public boolean areOpen() {
		return open.get();
	}

	public boolean areClosed() {
		return closed.get();
	}
}
