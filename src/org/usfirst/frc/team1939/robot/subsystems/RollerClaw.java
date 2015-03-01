package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.rollerclaw.GamepadRollerClaw;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RollerClaw extends Subsystem {
	
	private Victor victorLeft = new Victor(RobotMap.victorRollerClawLeft);
	private Victor victorRight = new Victor(RobotMap.victorRollerClawRight);
	
	private CANTalon talonLeft = new CANTalon(RobotMap.talonRollerClawLeft);
	private CANTalon talonRight = new CANTalon(RobotMap.talonRollerClawRight);
	
    public void initDefaultCommand() {
    	this.setDefaultCommand(new GamepadRollerClaw());
    }
    
    public void move(double speed){
    	victorLeft.set(speed);
    	victorRight.set(speed);
    }
    
    public void spin(double left, double right){
    	talonLeft.set(left);
    	talonRight.set(right);
    }
    
}

