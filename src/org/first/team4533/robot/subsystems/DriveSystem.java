package org.first.team4533.robot.subsystems;

import org.first.team4533.robot.RobotMap;
import org.first.team4533.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSystem extends Subsystem {

	private static DriveSystem INSTANCE;
	private SpeedController leftFront;
	private SpeedController rightFront;
	private SpeedController leftRear;
	private SpeedController rightRear;
	private RobotDrive robotDrive;

	private static final double DEFAULT_SPEED_ADJUSTMENT = 0.60;

	private DriveSystem() {
		leftFront = new Talon(RobotMap.MOTOR_LEFT_FRONT);
		rightFront = new Talon(RobotMap.MOTOR_RIGHT_FRONT);
		leftRear = new Talon(RobotMap.MOTOR_LEFT_REAR);
		rightRear = new Talon(RobotMap.MOTOR_RIGHT_REAR);
		robotDrive = new RobotDrive(this.leftFront, this.leftRear,
				this.rightFront, this.rightRear);
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
	}

	public static void initialize() {
		if (INSTANCE == null) {
			INSTANCE = new DriveSystem();
		}
	}

	public static DriveSystem getInstance() {
		return INSTANCE;
	}

	public void driveWithJoystick(Joystick driver) {
		double x = driver.getX();
		double y = driver.getY();
		double rotation = driver.getZ();

		double xAdjusted = Math.pow(x, 2) * DEFAULT_SPEED_ADJUSTMENT;
		double yAdjusted = Math.pow(y, 2) * DEFAULT_SPEED_ADJUSTMENT;
		double rotationAdjusted = Math.pow(rotation, 2) * 0.50;

		if (x < 0) {
			xAdjusted *= -1;
		}

		if (y < 0) {
			yAdjusted *= -1;
		}

		if (rotation < 0) {
			rotationAdjusted *= -1;
		}

		this.robotDrive.mecanumDrive_Cartesian(xAdjusted, yAdjusted,
				rotationAdjusted, 0.0);
	}

	public void forward(double value) {
		this.robotDrive.mecanumDrive_Cartesian(0.0, -value, 0.0, 0.0);
	}

	public void forward() {
		this.forward(1.0);
	}

	public void backward(double value) {
		this.robotDrive.mecanumDrive_Cartesian(0.0, value, 0.0, 0.0);
	}

	public void backward() {
		this.backward(1.0);
	}

	public void stop() {
		this.robotDrive.mecanumDrive_Cartesian(0.0, 0.0, 0.0, 0.0);
	}

	public void driveLeft(double value) {
		this.robotDrive.mecanumDrive_Cartesian(-value, 0.0, 0.0, 0.0);
	}

	public void driveLeft() {
		this.driveLeft(1.0);
	}

	public void driveRight(double value) {
		this.robotDrive.mecanumDrive_Cartesian(value, 0.0, 0.0, 0.0);
	}

	public void driveRight() {
		this.driveRight(1.0);
	}

	public void initDefaultCommand() {
		this.setDefaultCommand(new DriveWithJoystick());
	}
}
