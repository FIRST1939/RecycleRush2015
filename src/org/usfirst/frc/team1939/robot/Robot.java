package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.commands.auton.BombSquad;
import org.usfirst.frc.team1939.robot.commands.auton.DriveFromLine;
import org.usfirst.frc.team1939.robot.commands.auton.DriveOverPlatform;
import org.usfirst.frc.team1939.robot.commands.auton.OneContainer;
import org.usfirst.frc.team1939.robot.commands.auton.OneContainerOneTote;
import org.usfirst.frc.team1939.robot.commands.auton.OneYellowTote;
import org.usfirst.frc.team1939.robot.commands.auton.TwoTotes;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc.team1939.robot.commands.drivetrain.Turn90;
import org.usfirst.frc.team1939.robot.commands.lifter.ResetLifterEncoder;
import org.usfirst.frc.team1939.robot.commands.rollerclaw.ResetRollerClawEncoder;
import org.usfirst.frc.team1939.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1939.robot.subsystems.Lifter;
import org.usfirst.frc.team1939.robot.subsystems.RollerClaw;
import org.usfirst.frc.team1939.robot.subsystems.SmartDashboardSubsystem;
import org.usfirst.frc.team1939.util.Direction;
import org.usfirst.frc.team1939.util.LEDs;
import org.usfirst.frc.team1939.util.Wait;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
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
	public static AHRS ahrs;

	public static Robot robot;
	public static OI oi;

	public static final SendableChooser rotateMode = new SendableChooser();
	public static final SendableChooser forwardMode = new SendableChooser();
	private static final SendableChooser autonChooser = new SendableChooser();

	private Command autonomousCommand;

	public CameraServer server;

	@Override
	public void robotInit() {
		System.out.println("/n===================================");
		System.out.println("Started Intializing RecycleRush2015");
		robot = this;
		oi = new OI();

		System.out.println("Intializing SmartDashboard");
		Command[] commands = { new ResetGyro(), new ResetLifterEncoder(), new ResetRollerClawEncoder() };
		for (Command c : commands)
			SmartDashboard.putData(c);
		SmartDashboard.putData(Scheduler.getInstance());

		rotateMode.addDefault("Left Rotate", "Left");
		rotateMode.addObject("Right Rotate", "Right");
		SmartDashboard.putData("Rotate Joystick", rotateMode);

		forwardMode.addDefault("Left Forward", "Left");
		forwardMode.addObject("Right Forward", "Right");
		SmartDashboard.putData("Forward Joystick", forwardMode);

		autonChooser.addObject("One Container One Tote", new OneContainerOneTote());
		autonChooser.addObject("Drive From Line", new DriveFromLine());
		autonChooser.addObject("Drive Over Platform", new DriveOverPlatform());
		autonChooser.addObject("One Container !NO TOTE!", new OneContainer());
		autonChooser.addObject("Bomb Squad", new BombSquad());
		autonChooser.addObject("One Yellow Tote", new OneYellowTote());
		autonChooser.addObject("Two Yellow Totes", new TwoTotes());
		autonChooser.addObject("Turn Left", new Turn90(Direction.LEFT));
		autonChooser.addObject("Turn Right", new Turn90(Direction.RIGHT));
		autonChooser.addDefault("Do Nothing", new Wait(0));
		SmartDashboard.putData("Autonomous Chooser", autonChooser);

		System.out.println("Intialzing Camera");
		try {
			this.server = CameraServer.getInstance();
			this.server.setQuality(50);
			this.server.startAutomaticCapture("cam0");
		} catch (Exception e) {
			System.out.println("Camera not plugged in");
		}

		System.out.println("Intializing navX");
		try {
			ahrs = new AHRS(SerialPort.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}

		System.out.println("Intializing LEDs");
		Thread leds = new Thread(new LEDs());
		leds.start();

		System.out.println("Started RecycleRush2015");
		System.out.println("========================/n");
	}

	@Override
	public void disabledInit() {
		System.out.println("disabledInit()");
		stopAuton();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		System.out.println("autonomousInit()");
		try {
			this.autonomousCommand = (Command) autonChooser.getSelected();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.autonomousCommand != null)
			this.autonomousCommand.start();
		else
			System.out.println("No autonomous selected!");
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		System.out.println("teleopInit()");
		stopAuton();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		System.out.println("testInit()");
		stopAuton();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void stopAuton() {
		if (this.autonomousCommand != null)
			this.autonomousCommand.cancel();
	}

}
