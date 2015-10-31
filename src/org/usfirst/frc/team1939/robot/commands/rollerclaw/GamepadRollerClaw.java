package org.usfirst.frc.team1939.robot.commands.rollerclaw;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.RollerClaw;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GamepadRollerClaw extends Command {

	public GamepadRollerClaw() {
		requires(Robot.rollerClaw);
	}

	@Override
	protected void initialize() {
		Robot.rollerClaw.controller.enable();
	}

	@Override
	protected void execute() {
		boolean open = Robot.oi.gamepad.rightTrigger.get();
		boolean close = Robot.oi.gamepad.leftTrigger.get();
		if (open && !close) {
			Robot.rollerClaw.controller.setSetpoint(RollerClaw.OPEN);
		} else if (close && !open) {
			Robot.rollerClaw.controller.setSetpoint(RollerClaw.CLOSED);
		}
		if (!SmartDashboard.getBoolean("Roller Claw Override")) {
			Robot.rollerClaw.move(Robot.rollerClaw.controller.get());
		} else {
			if (open && !close) {
				Robot.rollerClaw.move(0.3);
			} else if (close && !open) {
				Robot.rollerClaw.move(-0.3);
			} else {
				Robot.rollerClaw.move(0);
			}
		}
		Robot.rollerClaw.spin(-Robot.oi.gamepad.getRightY() * 0.85, -Robot.oi.gamepad.getRightX() * 0.85);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.rollerClaw.controller.disable();
		Robot.rollerClaw.move(0);
		Robot.rollerClaw.spin(0, 0);
	}

	@Override
	protected void interrupted() {
		Robot.rollerClaw.controller.disable();
		Robot.rollerClaw.move(0);
		Robot.rollerClaw.spin(0, 0);
	}
}
