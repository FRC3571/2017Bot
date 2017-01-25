package org.usfirst.frc.team3571.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/**
	 * This is everything that is connected to the PWM ports on the RoboRIO
	 */
	public static class PWM {
		public static final int FRONT_LEFT_DRIVE_MOTOR = 1;
		public static final int REAR_LEFT_DRIVE_MOTOR = 2;
		public static final int FRONT_RIGHT_DRIVE_MOTOR = 0;
		public static final int REAR_RIGHT_DRIVE_MOTOR = 3;
		// 4-9 Free
		// 0-9 Free
		//Turn error
	}

	/**
	 * This is everything that is connected to the Digital ports on the RoboRIO
	 */
	public static class Digital {
		// 0-9 Free
	}

	/**
	 * This is everything that is connected to the Relay ports on the RoboRIO
	 */
	public static class Relay {
		// 0-3 Free
	}

	/**
	 * This is everything that is connected to the Solenoid ports on the RoboRIO
	 */
	public static class Solenoid {
		// 0-7 Free
	}

	/**
	 * This is everything that is connected to the USB's on the driver station
	 */
	public static class DriverUSB {
		public static final int DRIVER_CONTROLLER = 0;
		public static final int OPERATOR_CONTROLLER = 1;
	}
	
	public static class DEFAULT{
		public static final double CONTROLLER_DEADZONE = 0.25;
	}
	public static class Analog{
		public static final int EXAMPLE_ANALOG = 0;
	}
	public static class IP{
		public static final String CAMERA = "10.35.71.101";
	}
}

