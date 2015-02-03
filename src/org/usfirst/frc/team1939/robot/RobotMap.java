package org.usfirst.frc.team1939.robot;

public class RobotMap {

	// PWM
	public static int doorsLeft = 0;
	public static int doorsRight = 1;
	public static int tailLeft = 2;
	public static int tailRight = 3;

	// DIO
	public static int doorsLeftOpen = 0;
	public static int doorsLeftClosed = 1;
	public static int doorsRightOpen = 2;
	public static int doorsRightClosed = 3;
	public static int lifterUp = 4;
	public static int lifterDown = 5;
	public static int tailUp = 6;
	public static int tailDown = 7;

	// Analog
	public static int gyro = 0;
	public static int stringPot = 1;

	// CAN
	public static int talonFrontLeft = 0;
	public static int talonRearLeft = 1;
	public static int talonFrontRight = 2;
	public static int talonRearRight = 3;
	public static int talonLifterLeft = 4;
	public static int talonLifterRight = 5;

}
