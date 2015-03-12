package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.commands.auton.BombSquad;
import org.usfirst.frc.team1939.robot.commands.auton.DriveFromLine;
import org.usfirst.frc.team1939.robot.commands.auton.DriveOverPlatform;
import org.usfirst.frc.team1939.robot.commands.auton.GrabContainersFromStep;
import org.usfirst.frc.team1939.robot.commands.auton.OneContainer;
import org.usfirst.frc.team1939.robot.commands.auton.OneContainerOneTote;
import org.usfirst.frc.team1939.robot.commands.auton.OneYellowTote;
import org.usfirst.frc.team1939.robot.commands.auton.TwoTotes;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnByTime;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnDegrees;
import org.usfirst.frc.team1939.robot.commands.lifter.ResetLifterEncoder;
import org.usfirst.frc.team1939.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;
import org.usfirst.frc.team1939.robot.subsystems.RollerClaw;
import org.usfirst.frc.team1939.robot.subsystems.SmartDashboardSubsystem;
import org.usfirst.frc.team1939.robot.subsystems.Tail;
import org.usfirst.frc.team1939.util.LEDs;
import org.usfirst.frc.team1939.util.Wait;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Lifter lifter = new Lifter();
	public static final RollerClaw rollerClaw = new RollerClaw();
	public static final SmartDashboardSubsystem sds = new SmartDashboardSubsystem();
	public static final Tail tail = new Tail();

	public static Robot robot;
	public static OI oi;

	public static final SendableChooser rotateMode = new SendableChooser();
	public static final SendableChooser forwardMode = new SendableChooser();
	private static final SendableChooser autonChooser = new SendableChooser();
	
	private Command autonomousCommand;
	
	public CameraServer server;

	public void robotInit() {
		robot = this;
		oi = new OI();
		
		Command[] commands = {
				new ResetGyro(),
				new ResetLifterEncoder()
			};
		for (Command c : commands) SmartDashboard.putData(c);
		SmartDashboard.putData(Scheduler.getInstance());

		rotateMode.addDefault("Left Rotate", "Left");
		rotateMode.addObject("Right Rotate", "Right");
		SmartDashboard.putData("Rotate Joystick", rotateMode);
		
		forwardMode.addDefault("Left Forward", "Left");
		forwardMode.addObject("Right Forward", "Right");
		SmartDashboard.putData("Forward Joystick", forwardMode);
		
		autonChooser.addObject("One Container One Tote", new OneContainerOneTote());
		autonChooser.addObject("Grab Containers", new GrabContainersFromStep());
		autonChooser.addObject("Drive From Line", new DriveFromLine());
		autonChooser.addObject("Drive Over Platform", new DriveOverPlatform());
		autonChooser.addObject("One Container !NO TOTE!", new OneContainer());
		autonChooser.addObject("Bomb Squad", new BombSquad());
		autonChooser.addObject("One Yellow Tote", new OneYellowTote());
		autonChooser.addObject("Two Yellow Totes", new TwoTotes());
		autonChooser.addObject("Turn By Time", new TurnByTime(0.75, 0.5));
		autonChooser.addObject("Turn By Gyro", new TurnDegrees(90));
		autonChooser.addDefault("Do Nothing", new Wait(0));
		SmartDashboard.putData("Autonomous Chooser", autonChooser);

		try{
			server = CameraServer.getInstance();
			server.setQuality(50);
			server.startAutomaticCapture("cam0");
		}catch(Exception e){
			System.out.println("Camera not plugged in");
		}
		
		Thread leds = new Thread(new LEDs());
		leds.start();
		
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
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}
}
