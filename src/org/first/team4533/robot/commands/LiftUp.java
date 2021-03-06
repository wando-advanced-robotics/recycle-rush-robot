package org.first.team4533.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.first.team4533.robot.subsystems.LiftSystem;

/**
 * This will make the lifter go up?
 */
public class LiftUp extends Command {
	private LiftSystem lift;

	public LiftUp() {
		this.lift = LiftSystem.getInstance();
		requires(lift);
	}

	protected void execute() {
		this.lift.up();
	}

	protected void end() {
		this.lift.stop();
	}

	protected void interrupted() {
		this.lift.stop();
	}

	protected void initialize() {
	}

	protected boolean isFinished() {
		return false;
	}
}
