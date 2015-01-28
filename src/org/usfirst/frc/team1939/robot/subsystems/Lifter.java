package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Lifter extends PIDSubsystem {

	public static final double UP = 1.0;
	public static final double DOWN = -UP;

	private static final double P = -1;
	private static final double I = 0;
	private static final double D = 0;

	private static final double INCHES_PER_REVOLUTION = 2;
	private static final double PULSES_PER_REVOLUTION = 360;

	private CANTalon left = new CANTalon(RobotMap.talonLifterLeft);
	private CANTalon right = new CANTalon(RobotMap.talonLifterRight);
	private DigitalInput top = new DigitalInput(RobotMap.lifterTop);
	private DigitalInput bottom = new DigitalInput(RobotMap.lifterBottom);

	public Lifter() {
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		super(P, I, D);
		this.setOutputRange(-1, 1);

		LiveWindow.addActuator("Horn", "PID", this.getPIDController());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return left.getPosition();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		this.setSpeed(output);
	}

	public void setSpeed(double speed) {
		left.set(speed);
		right.set(speed);
	}

	public boolean isAtTop() {
		return top.get();
	}

	public boolean isAtBottom() {
		return bottom.get();
	}

	public void resetEncoder() {
		left.setPosition(0);
	}
}
