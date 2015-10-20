package org.usfirst.frc.team1939.util;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.SerialPort;

public class LEDs implements Runnable {

	private SerialPort port;

	@Override
	public void run() {
		while (true) {
			if (this.port == null) {
				try {
					this.port = new SerialPort(9600, SerialPort.Port.kUSB);
				} catch (Exception e) {
					e.printStackTrace();
					DriverStation.reportError("Couldn't Find Arduino: " + System.currentTimeMillis(), false);
				}
			} else {
				byte b = 0;
				if (Robot.robot.isEnabled())
					b = writeBit(b, 1, 0);
				if (DriverStation.getInstance().isDSAttached())
					b = writeBit(b, 1, 1);
				if (DriverStation.getInstance().getAlliance() == Alliance.Blue)
					b = writeBit(b, 1, 2);
				if (Robot.robot.isAutonomous())
					b = writeBit(b, 1, 3);

				byte[] buffer = new byte[1];
				buffer[0] = b;
				this.port.write(buffer, 1);
				this.port.flush();

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static byte writeBit(byte b, int value, int pos) {
		return (byte) (b | value << pos);
	}

}
