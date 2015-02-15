package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	private static final double margin = 0.1;

	public DriveWithJoystick() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		// Get raw joystick values
		String rotateMode = (String) Robot.rotateMode.getSelected();
		String forwardMode = (String) Robot.forwardMode.getSelected();
		double x = 0;
		double y = 0;
		double z = 0;
		if(forwardMode.equals("Left")){
			y = Robot.oi.leftStick.getY();
		}else{
			y = Robot.oi.rightStick.getY();
		}
		if(rotateMode.equals("Left")){
			x = Robot.oi.rightStick.getX();
			z = Robot.oi.leftStick.getX();
		}else{
			x = Robot.oi.leftStick.getX();
			z = Robot.oi.rightStick.getX();
		}

		// Zero low values
		if (Math.abs(x) < margin)
			x = 0;
		if (Math.abs(y) < margin)
			y = 0;
		if (Math.abs(z) < margin)
			z = 0;

		// Map raw values to motor values
		if (x > 0)
			x = map(x, .1, 1, 0, 1);
		else if (x < 0)
			x = map(x, -1, -.1, -1, 0);
		if (y > 0)
			y = map(y, .1, 1, 0, 1);
		else if (y < 0)
			y = map(y, -1, -.1, -1, 0);
		if (z > 0)
			z = map(z, .1, 1, 0, 1);
		else if (z < 0)
			z = map(z, -1, -.1, -1, 0);

		// Turbo multiplier
		double multi = 1.0;
		if (!Robot.oi.turbo.get())
			multi = .5;

		// Drive
		Robot.drivetrain.drive(x, y, z, multi);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

	private static double map(double x, double in_min, double in_max,
			double out_min, double out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
