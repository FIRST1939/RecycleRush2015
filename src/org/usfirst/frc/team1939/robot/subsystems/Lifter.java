package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.lifter.GamepadLifter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lifter extends Subsystem {
	
	public static final double BOTTOM = 0;
	private static final double TOP = 37.5;
	public static final double HOLD = 6.25; // Hold tote on ground but locked in
	public static final double ONE_TOTE = 22.5; // Hold with space for one tote
	public static final double PICK_UP_SECOND = 19; // Pick up height for second tote
	public static final double CORRAL = 8.2; // Hold tote on ground but above locked in
	
	public static final double UP = 1;
	public static final double DOWN = -UP;

	private static final double P = 0.3;
	private static final double I = 0;
	private static final double D = 0;

	private static final double INCHES_PER_REVOLUTION = 0.5;
	private static final double PULSES_PER_REVOLUTION = 250 * 4;

	private CANTalon left = new CANTalon(RobotMap.talonLifterLeft);
	private CANTalon right = new CANTalon(RobotMap.talonLifterRight);

	public Lifter() {
		left.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		left.enableLimitSwitch(true, true);
		left.ConfigRevLimitSwitchNormallyOpen(false);
		left.ConfigFwdLimitSwitchNormallyOpen(false);
		left.reverseSensor(true);
		left.setPID(P, I, D);

		right.changeControlMode(ControlMode.Follower);
		right.set(RobotMap.talonLifterLeft);
	}

	public void initDefaultCommand() {
		this.setDefaultCommand(new GamepadLifter());
	}

	public void setPosition(double inches) {
		left.set(inches / INCHES_PER_REVOLUTION * PULSES_PER_REVOLUTION);
	}

	public double getPosition() {
		return left.getPosition() / PULSES_PER_REVOLUTION * INCHES_PER_REVOLUTION;
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
		left.setPID(0, 0, 0);
	}

	public void positionMode() {
		left.changeControlMode(ControlMode.Position);
		left.set(0);
		left.setPID(P, I, D);
	}

	public void setSpeed(double speed) {
		left.set(speed);
	}

	public boolean isUp() {
		return left.isFwdLimitSwitchClosed();
	}

	public boolean isDown() {
		return left.isRevLimitSwitchClosed();
	}

	public void resetEncoder() {
		left.setPosition(0);
	}
}
