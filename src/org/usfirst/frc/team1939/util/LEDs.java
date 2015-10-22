package org.usfirst.frc.team1939.util;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;

public class LEDs implements Runnable {

	private SerialPort port;

	public LEDs() {
		try {
			this.port = new SerialPort(9600, SerialPort.Port.kUSB);
		} catch (Exception e) {
			e.printStackTrace();
			DriverStation.reportError("Couldn't Find Arduino", false);
		}
	}

	@Override
	public void run() {
		while (true) {
			int state = 0;
			if (!DriverStation.getInstance().isDSAttached()) {
				state = 1;
			} else if (!Robot.robot.isEnabled()) {
				state = 2;
			} else if (Robot.robot.isAutonomous()) {
				state = 3;
			} else if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue) {
				state = 4;
			} else if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
				state = 5;
			}

			byte[] buffer = new byte[1];
			buffer[0] = writeBit(buffer[0], 1, state);
			this.port.write(buffer, 1);
			this.port.flush();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static byte writeBit(byte b, int value, int pos) {
		return (byte) (b | value << pos);
	}

}
