package org.usfirst.frc.team3571.robot;

import org.usfirst.frc.team3571.robot.utilities.XboxController.Button;

public class Teleop extends OI {

	// Only a reference is being passed
	// therefore the button keeps on functioning
	static Button driveStopButton = driver.Buttons.B;

	// Holds value for if triggers should be used instead of LeftStick Y
	static boolean triggerDrive = false;
	static Button triggerSwitchButton = driver.Buttons.LeftStick;

	// Holds the current drive value
	// which is invalid if the driver is holding button B
	static double driveY = 0;
	
	static double volts =0;

	/**
	 * The initialization code for Teleop
	 */
	public static void init() {
		// TODO Make Teleop Init
	}

	/**
	 * Runs at a maximum rate of 50Hz during Teleop
	 */
	public static void periodic() {
		volts = proximityAnalog.getVoltage();
		
		// TODO Make Teleop Code

		// Flips the state of triggerDrive when Button B changes state to
		// pressed
		if (triggerSwitchButton.changedDown) {
			triggerDrive = !triggerDrive;
		}

		// Controls which Axis controls the robot base drive
		if (triggerDrive) {
			driveY = driver.Triggers.Combined;
		} else {
			driveY = driver.LeftStick.Y;
		}
		// The Above is the same as
		// driveY = triggerDrive ? driver.Triggers.Combined : driver.LeftStick.Y;

		if (!driveStopButton.current) {
			drive.arcadeDrive(driveY, -driver.LeftStick.X);
		} else {
			drive.stopMotor();
		}
	}
}
