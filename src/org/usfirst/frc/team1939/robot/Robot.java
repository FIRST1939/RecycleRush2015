package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.subsystems.Doors;
import org.usfirst.frc.team1939.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;
import org.usfirst.frc.team1939.robot.subsystems.Poker;
import org.usfirst.frc.team1939.robot.subsystems.SmartDashboardSubsystem;
import org.usfirst.frc.team1939.robot.subsystems.Tail;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Doors doors = new Doors();
	public static final Lifter lifter = new Lifter();
	public static final Tail tail = new Tail();
	public static final Poker poker = new Poker();
	public static final SmartDashboardSubsystem sms = new SmartDashboardSubsystem();

	public static OI oi;

	private SendableChooser chooser;
	private Command autonomousCommand;
	
	public CameraServer server;

	public void robotInit() {
		oi = new OI();

		chooser = new SendableChooser();
		// Add commands to chooser
		// chooser.add(command);
		SmartDashboard.putData("Autonomous Chooser", chooser);

		server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");
		
		System.out.println("\n========================");
		System.out.println("Started RecycleRush2015");
		System.out.println("========================\n");
	}

	public void disabledInit() {
		System.out.println("disabledInit()");
		stopAuton();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		System.out.println("autonomousInit()");
		try {
			autonomousCommand = (Command) chooser.getSelected();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (autonomousCommand != null)
			autonomousCommand.start();
		else
			System.out.println("No autonomous selected!");
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		System.out.println("teleopInit()");
		stopAuton();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testInit() {
		System.out.println("testInit()");
		stopAuton();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}

	public void stopAuton() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}
}
