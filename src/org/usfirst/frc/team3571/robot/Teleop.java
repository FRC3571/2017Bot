package org.usfirst.frc.team3571.robot;

import org.usfirst.frc.team3571.robot.utilities.XboxController.Button;




import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Teleop extends OI {

	// Only a reference is being passed
	// therefore the button keeps on functioning
	static Button driveStopButton = driver.Buttons.B;

	// Holds value for if triggers should be used instead of LeftStick Y
	static boolean triggerDrive = false;
	static Button triggerSwitchButton = driver.Buttons.LeftStick;
    static boolean limit;
	// Holds the current drive value
	// which is invalid if the driver is holding button B
	static double driveY = 0;
	 
	static double volts =0;
	static double distance =0;
	/**
	 * The initialization code for Teleop
	 */
	public static void init() {
		// TODO Make Teleop Init
	}
	public static boolean test = false;
	/**
	 * Runs at a maximum rate of 50Hz during Teleop
	 */
	public static void periodic() {
		distance = getDistance();
		limit = OI.limit_button.get();
		volts = proximityAnalog.getVoltage();
		SmartDashboard.putNumber("Volltage", volts);
		SmartDashboard.putNumber("Distance in mm", distance);
		SmartDashboard.putBoolean("Status", test );
		// TODO Make Teleop Code

		// Flips the state of triggerDrive when Button B changes state to
		// pressed
		if (distance <= 357 && driveY > 0){
			drive.arcadeDrive(0,0);
		}

		if (limit == true && driveY > 0){
			drive.arcadeDrive(0,0);
		}
		if(driver.Triggers.Right > 0.9){
		shooter.set(-1);			
		agitator.set(true);
	    }
		else{
		shooter.set(0);
		agitator.set(false);
		}		
		if(driver.Triggers.Left > 0.9){
			intake.set(1);
		    }
		else{
			intake.set(0);
		}
		if (driver.Buttons.LB.changedDown){
			climber1.set(1);
			climber2.set(1);
		}
		else{
			climber1.set(0);
			climber2.set(0);
		}
		if (driver.Buttons.RB.changedDown){
			
		}
		
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
			drive.arcadeDrive(driveY, driver.LeftStick.X);
		} else {
			drive.stopMotor();
		}
	}
}
