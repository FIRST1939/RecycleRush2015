package org.usfirst.frc.team1939.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	// Left Joystick
	public Joystick leftStick = new Joystick(0);
	public JoystickButton turbo = new JoystickButton(leftStick, 1);

	// Right Joystick
	public Joystick rightStick = new Joystick(1);

}