package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.commands.auton.DriveFromLine;
import org.usfirst.frc.team1939.robot.commands.auton.DriveOverPlatform;
import org.usfirst.frc.team1939.robot.commands.auton.GrabContainersFromStep;
import org.usfirst.frc.team1939.robot.commands.auton.OneContainer;
import org.usfirst.frc.team1939.robot.commands.auton.OneContainerOneTote;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc.team1939.robot.commands.lifter.ResetLifterEncoder;
import org.usfirst.frc.team1939.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;
import org.usfirst.frc.team1939.robot.subsystems.RollerClaw;
import org.usfirst.frc.team1939.robot.subsystems.SmartDashboardSubsystem;
import org.usfirst.frc.team1939.robot.subsystems.Tail;
import org.usfirst.frc.team1939.util.LEDs;

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
		
		autonChooser.addDefault("One Container One Tote", new OneContainerOneTote());
		autonChooser.addDefault("Grab Containers", new GrabContainersFromStep());
		autonChooser.addDefault("Drive From Line", new DriveFromLine());
		autonChooser.addDefault("Drive Over Platform", new DriveOverPlatform());
		autonChooser.addDefault("One Container !NO TOTE!", new OneContainer());
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
