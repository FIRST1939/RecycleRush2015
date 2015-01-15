package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Lifter extends PIDSubsystem {

	public static final double UP = 1.0;
	public static final double DOWN = -UP;

	private static final double P = 0;
	private static final double I = 0;
	private static final double D = 0;

	private static final double INCHES_PER_PULSE = 1000;

	private Talon motor = new Talon(RobotMap.lifterMotor);
	private DigitalInput limit = new DigitalInput(RobotMap.lifterLimit);
	private Encoder encoder = new Encoder(RobotMap.lifterA, RobotMap.lifterB);

	// Initialize your subsystem here
	public Lifter() {
		super(P, I, D);
		encoder.setDistancePerPulse(INCHES_PER_PULSE);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return encoder.getDistance();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		motor.set(output);
	}

	public void setSpeed(double speed) {
		motor.set(speed);
	}

	public boolean isAtBottom() {
		return limit.get();
	}

	public void resetEncoder() {
		encoder.reset();
	}
}
