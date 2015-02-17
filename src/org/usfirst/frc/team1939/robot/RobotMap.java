package org.usfirst.frc.team1939.robot;

public class RobotMap {

	// PWM
	public static int guideLeft = 0;
	public static int guideRight = 0;
	
	// DIO
	public static int doorsLeftOpen = 1;
	public static int doorsLeftClosed = 0;
	public static int doorsRightOpen = 3;
	public static int doorsRightClosed = 2;
	public static int tailUp = 6;
	public static int tailDown = 7;

	// Analog
	public static int gyro = 0;

	// CAN
	public static int talonFrontLeft = 0;
	public static int talonRearLeft = 1;
	public static int talonFrontRight = 2;
	public static int talonRearRight = 3;
	public static int talonLifterLeft = 4;
	public static int talonLifterRight = 5;
	public static int talonDoorsLeft = 6;
	public static int talonDoorsRight = 7;
	public static int talonTail = 8;

}
