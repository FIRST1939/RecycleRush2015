package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.rollerclaw.GamepadRollerClaw;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RollerClaw extends Subsystem {
	
	public static final double TIME = 0.3;
	public static final double FULL_SPEED = 1;
	public static final double PART_SPEED = 0.2;
	
	private Victor victorLeft = new Victor(RobotMap.victorRollerClawLeft);
	private Victor victorRight = new Victor(RobotMap.victorRollerClawRight);
	
	private CANTalon talonLeft = new CANTalon(RobotMap.talonRollerClawLeft);
	private CANTalon talonRight = new CANTalon(RobotMap.talonRollerClawRight);
	private RobotDrive drive = new RobotDrive(talonLeft, talonRight);
	
	public RollerClaw() {
		super();
		drive.setSafetyEnabled(false);
	}
	
    public void initDefaultCommand() {
    	this.setDefaultCommand(new GamepadRollerClaw());
    }
    
    public void move(double speed){
    	victorLeft.set(speed);
    	victorRight.set(speed);
    }
    
    public void spin(double move, double turn){
    	drive.arcadeDrive(move, turn);
    }
    
}

