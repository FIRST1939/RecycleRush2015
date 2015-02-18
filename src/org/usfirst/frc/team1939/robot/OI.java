package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.commands.doors.CloseDoors;
import org.usfirst.frc.team1939.robot.commands.doors.OpenDoors;
import org.usfirst.frc.team1939.util.Gamepad;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	// Left Joystick
	public Joystick leftStick = new Joystick(0);
	public JoystickButton turbo = new JoystickButton(leftStick, 1);
	public JoystickButton open = new JoystickButton(leftStick, 7);
	public JoystickButton close = new JoystickButton(leftStick, 8);
	{
		open.whenPressed(new OpenDoors());
		close.whenPressed(new CloseDoors());
	}

	// Right Joystick
	public Joystick rightStick = new Joystick(1);

	// Gamepad
	public Gamepad gamepad = new Gamepad(2);

}