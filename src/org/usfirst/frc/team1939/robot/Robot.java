package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.commands.auton.OneContainerOneTote;
import org.usfirst.frc.team1939.robot.commands.auton.TestAuton;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DrivetrainTester;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnDegrees;
import org.usfirst.frc.team1939.robot.commands.lifter.MoveLifterToBottom;
import org.usfirst.frc.team1939.robot.commands.lifter.MoveLifterToTop;
import org.usfirst.frc.team1939.robot.commands.lifter.ResetLifterEncoder;
import org.usfirst.frc.team1939.robot.commands.poker.ResetPokerEncoder;
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

	public static final SendableChooser rotateMode = new SendableChooser();
	public static final SendableChooser forwardMode = new SendableChooser();
	private static final SendableChooser autonChooser = new SendableChooser();
	
	private Command autonomousCommand;
	
	public CameraServer server;

	public void robotInit() {
		oi = new OI();
		
		Command[] commands = {
				//new CloseDoors(),
				//new OpenDoors(),
				//new PokerIn(),
				//new PokerOut(),
				new ResetGyro(),
				new ResetLifterEncoder(),
				new MoveLifterToBottom(),
				new MoveLifterToTop(),
				new DrivetrainTester(),
				new ResetPokerEncoder()
			};
		for (Command c : commands) SmartDashboard.putData(c);
		SmartDashboard.putData(Scheduler.getInstance());

		rotateMode.addDefault("Left Rotate", "Left");
		rotateMode.addObject("Right Rotate", "Right");
		SmartDashboard.putData("Rotate Joystick", rotateMode);
		
		forwardMode.addDefault("Left Forward", "Left");
		forwardMode.addObject("Right Forward", "Right");
		SmartDashboard.putData("Forward Joystick", forwardMode);
		
		autonChooser.addDefault("One Container One Tote", new OneContainerOneTote());
		autonChooser.addObject("Drive 100", new DriveByInches(100));
		autonChooser.addObject("Turn 90", new TurnDegrees(90));
		autonChooser.addObject("Test Auton", new TestAuton());
		SmartDashboard.putData("Autonomous Chooser", autonChooser);

		try{
			server = CameraServer.getInstance();
			server.setQuality(50);
			server.startAutomaticCapture("cam0");
		}catch(Exception e){
			System.out.println("Camera not plugged in");
		}
		
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
			autonomousCommand = (Command) autonChooser.getSelected();
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
		/*
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		*/
	}
}
