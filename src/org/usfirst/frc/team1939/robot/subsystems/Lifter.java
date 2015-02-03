package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Lifter extends Subsystem {

	public static final double UP = 1.0;
	public static final double DOWN = -UP;

	private static final double P = 0.5;
	private static final double I = 0;
	private static final double D = 0;

	private static final double INCHES_PER_REVOLUTION = 0.5;
	private static final double PULSES_PER_REVOLUTION = 250 * 4;

	private CANTalon left = new CANTalon(RobotMap.talonLifterLeft);
	private CANTalon right = new CANTalon(RobotMap.talonLifterRight);
	private DigitalInput up = new DigitalInput(RobotMap.lifterUp);
	private DigitalInput down = new DigitalInput(RobotMap.lifterDown);

	public Lifter() {
		LiveWindow.addSensor("Lifter", "Up", up);
		LiveWindow.addSensor("Lifter", "Down", down);

		left.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		left.reverseSensor(true);
		left.setPID(P, I, D);

		right.changeControlMode(ControlMode.Follower);
		right.set(RobotMap.talonLifterLeft);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setPosition(double inches) {
		left.set(inches / INCHES_PER_REVOLUTION * PULSES_PER_REVOLUTION);
	}

	public double getPosition() {
		return left.getEncPosition() / PULSES_PER_REVOLUTION
				* INCHES_PER_REVOLUTION;
	}

	public void enable() {
		left.enableControl();
	}

	public void disable() {
		left.disableControl();
	}

	public void throttleMode() {
		left.changeControlMode(ControlMode.PercentVbus);
		left.set(0);
		left.setPID(P, I, D);
	}

	public void positionMode() {
		left.changeControlMode(ControlMode.Position);
		left.set(0);
		left.setPID(0, 0, 0);
	}

	public void setSpeed(double speed) {
		left.set(speed);
	}

	public boolean isUp() {
		return up.get();
	}

	public boolean isDown() {
		return down.get();
	}

	public void resetEncoder() {
		left.setPosition(0);
	}
}
