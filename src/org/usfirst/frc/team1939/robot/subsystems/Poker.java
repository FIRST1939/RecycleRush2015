package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.poker.PokerGamepad;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Poker extends Subsystem {

	private static final double TICKS_PER_REVOLUTION = 360 * 4;
	public static final double OUT_REVOLUTIONS = 2.5;
	public static final double IN_REVOLUTIONS = 0;
	
	public static final double LOWER_LIMIT = 8;
	public static final double UPPER_LIMIT = 23;
	
	private static final double P = 0.7;
	private static final double I = 0;
	private static final double D = 0;
	
	public static boolean isIn = true;
	
	private CANTalon talon = new CANTalon(RobotMap.talonPoker);
	{
		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talon.changeControlMode(ControlMode.Position);
		talon.set(0);
		talon.setPID(P, I, D);
	}
	
    public Poker() {
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new PokerGamepad());
    }
    
    public void setPosition(double rotations){
    	talon.set(rotations * TICKS_PER_REVOLUTION);
    }
    
    public double getPosition(){
    	return talon.getPosition() / TICKS_PER_REVOLUTION;
    }
    
    public double getTicks(){
    	return talon.getPosition();
    }
    
    public double getSpeed(){
    	return talon.getSpeed();
    }
    
    public void enable(){
    	talon.enableControl();
    }
    
    public void disable(){
    	talon.disable();
    }
    
    public void resetEncoder(){
    	talon.setPosition(0);
    }
   
}
