package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Guides extends Subsystem {
	
	private static final double LEFT_OUT = 1.0;
	private static final double RIGHT_OUT = -LEFT_OUT;
	
	public static final double SPIN_TIME = 2;
	
	private Victor left = new Victor(RobotMap.guideLeft);
	private Victor right = new Victor(RobotMap.guideRight);
	
	private boolean isIn = true;
	
    public void initDefaultCommand() {
        
    }
    
    public void spinOut(){
    	isIn = false;
    	this.left.set(LEFT_OUT);
    	this.right.set(RIGHT_OUT);
    }
    
    public void spinIn(){
    	isIn = true;
    	this.left.set(-LEFT_OUT);
    	this.right.set(-RIGHT_OUT);
    }
    
    public void stop(){
    	this.left.set(0);
    	this.right.set(0);
    }
    
    public boolean isIn(){
    	return isIn;
    }
    
}

