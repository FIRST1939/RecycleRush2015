package org.usfirst.frc.team1939.util;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.SerialPort;

public class LEDs implements Runnable {

	
	private SerialPort port;

	public LEDs() {
		try{
			port = new SerialPort(9600, SerialPort.Port.kUSB);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Didn't find Arduino");
		}
	}
	
	public void run() {
		while(port!=null){
			byte height = (byte) Robot.lifter.getPosition();
			height = writeBit(height, 0, 7);
			
			byte b = 0;
			if (Robot.robot.isEnabled())
				b += 1;
			if (DriverStation.getInstance().isDSAttached())
				b += 2;
			if (DriverStation.getInstance().getAlliance() == Alliance.Blue)
				b += 4;
			if (Robot.robot.isAutonomous())
				b += 8;
			b = writeBit(b, 1, 7);
			
			byte[] buffer = new byte[2];
			buffer[0] = height;
			buffer[1] = b;
			port.write(buffer, 1);
			port.flush();
			System.out.println("Wrote data to Arduino: b="+b+" height="+height);

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static byte writeBit(byte b, int value, int pos){
		return (byte) (b | (value << pos));
	}
	
	
}