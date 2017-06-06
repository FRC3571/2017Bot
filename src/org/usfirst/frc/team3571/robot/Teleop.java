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
    static boolean limit = true;
	// Holds the current drive value
	// which is invalid if the driver is holding button B
	static double driveY = 0;
	static double driveY2 = 0;
	static double volts =0;
	static double distance =0;
	static boolean gear = false;

	static double turner;
	static double a = 1;
	static double b = 1;
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
		//SmartDashboard.putDouble("distaces", distance);
		// TODO Make Teleop Code

		// Flips the state of triggerDrive when Button B changes state to
		// pressed
		
		//if (limit == true && driveY < 0){
		//drive.arcadeDrive(0,0);
		//}
		if (driver.Buttons.A.changedDown)
		{
			if (a == 1)
			{
				a = 0.5;
				b = 0.75;
			}
			else
			{
				a = 1;
				b = 1;
			}
		}
		
		//if(driver.Triggers.Right > 0.9){
		//shooter.set(-1);			
		//agitator.set(true);
	    //}
		//else{
		//shooter.set(0);
		//agitator.set(false);
		//}		
		//if(driver.Triggers.Left > 0.9){
			//
	// intake.set(1);
		    //}
		//else{
			//intake.set(0);
	//	}
		if (driver.Buttons.Y.changedDown)
		{
		gear = false;
		}
		if (driver.Buttons.LB.changedDown){
			climber1.set(-.2);
			climber2.set(-.2);
		}
		if (driver.Buttons.LB.changedUp){
			climber1.set(0);
			climber2.set(0);
		}
		if (driver.Buttons.B.changedDown){
			climber1.set(-.7);
			climber2.set(-.7);
		}
		if (driver.Buttons.B.changedUp){
			climber1.set(0);
			climber2.set(0);
		}
		
		
		// Controls which Axis controls the robot base drive
	if (triggerDrive) {
			driveY = driver.Triggers.Combined;
		} else {
			driveY = driver.LeftStick.Y;
		
		}
		if (driver.Buttons.X.changedDown){
			gear=!gear;
		}
		turner = OI.cameras.turn * 0.006;
		if (gear == true && limit == true){
			    drive.arcadeDrive(0.5,turner );
			}
			else{
				 drive.arcadeDrive(0, 0);
				 gear = false;
		}
		// The Above is the same as
		driveY = triggerDrive ? driver.Triggers.Combined : driver.LeftStick.Y;

		if (!driveStopButton.current) {
			drive.arcadeDrive(-driveY*a, driver.LeftStick.X*b);
			
		} else {
			drive.stopMotor();
		}
		
	}
}
