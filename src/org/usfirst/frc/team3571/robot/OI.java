package org.usfirst.frc.team3571.robot;

import org.usfirst.frc.team3571.robot.utilities.*;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI extends RobotMap {

	public static final XboxController driver = new XboxController(DriverUSB.DRIVER_CONTROLLER, DEFAULT.CONTROLLER_DEADZONE);
	public static final XboxController operator = new XboxController(DriverUSB.OPERATOR_CONTROLLER, DEFAULT.CONTROLLER_DEADZONE);

	public static final RobotDrive drive = new RobotDrive(PWM.FRONT_LEFT_DRIVE_MOTOR, PWM.REAR_LEFT_DRIVE_MOTOR,
			PWM.FRONT_RIGHT_DRIVE_MOTOR, PWM.REAR_RIGHT_DRIVE_MOTOR);
	
	public static final AnalogInput proximityAnalog = new AnalogInput(Analog.EXAMPLE_ANALOG);
	
	
	
	/**
	 * Calls All Refresh Methods
	 */
	public static void refreshAll() {
		driver.refresh();
		operator.refresh();
	}
	
	/** Returns distance in mm **/
	public static double getDistance(){
		return ((double)proximityAnalog.getValue() * 1.24941) + 0.10889;
	}
}
