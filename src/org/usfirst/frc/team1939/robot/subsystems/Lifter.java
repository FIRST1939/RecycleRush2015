package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Lifter extends Subsystem {
    
	private static final double upSpeed = 1;
	
    DigitalInput limit = new DigitalInput(RobotMap.lifterLimit);
    Talon motor = new Talon(RobotMap.lifter);
    AnalogPotentiometer pot = new AnalogPotentiometer(RobotMap.pot);
    
    public void initDefaultCommand() {
    
    }
}

