package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.rollerclaw.GamepadRollerClaw;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RollerClaw extends Subsystem {

	public static final int OPEN = 0;
	public static final int CLOSED = 150;

	private static final double P = 0.04;
	private static final double I = 0;
	private static final double D = 0;

	private CANTalon talonLeft = new CANTalon(RobotMap.talonRollerClawLeft);
	private CANTalon talonRight = new CANTalon(RobotMap.talonRollerClawRight);
	private CANTalon talon = new CANTalon(RobotMap.talonRollerClaw);
	private RobotDrive drive = new RobotDrive(this.talonLeft, this.talonRight);
	private Encoder encoder = new Encoder(RobotMap.rollerClawEncoderA, RobotMap.rollerClawEnoderB);

	public PIDController controller = new PIDController(P, I, D, new PIDSource() {
		@Override
		public double pidGet() {
			return RollerClaw.this.encoder.get();
		}
	}, new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			// Do nothing
		}
	});

	public RollerClaw() {
		super();
		this.drive.setSafetyEnabled(false);
		this.controller.setOutputRange(-0.3, 0.3);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new GamepadRollerClaw());
	}

	public void move(double speed) {
		this.talon.set(speed);
	}

	public int getEncoder() {
		return this.encoder.get();
	}

	public void resetEncoder() {
		this.encoder.reset();
	}

	public void spin(double move, double turn) {
		this.drive.arcadeDrive(move, turn);
	}

}
