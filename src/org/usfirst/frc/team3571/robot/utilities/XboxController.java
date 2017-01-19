package org.usfirst.frc.team3571.robot.utilities;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * Handles Input from the Xbox Controller connected to the driver station.
 * 
 * @author TomasR
 */
public class XboxController {
	DriverStation dStation;
	int port = 0, buttonState = 0, buttonCount = 0;
	Button[] buttons = new Button[10];
	short lRumble = 0, rRumble = 0;

	public Axis LeftStick = new Axis(0, 0), RightStick = new Axis(0, 0);
	public triggers Triggers = new triggers(0, 0);
	public ButtonRemap Buttons;
	public POV DPad = new POV();

	/**
	 * Sets up the controller
	 * 
	 * @param port
	 *            Controller port
	 */
	public XboxController(int port) {
		this(port, 0, 0);
	}

	/**
	 * Sets up the controller with dead zones
	 * 
	 * @param port
	 *            Controller port
	 * @param deadZone
	 *            The magnitude of the dead zone on the each stick
	 */
	public XboxController(int port, double deadZone) {
		this(port, deadZone, deadZone);
	}

	/**
	 * Sets up the controller with dead zones
	 * 
	 * @param port
	 *            Controller port
	 * @param leftDeadZone
	 *            The magnitude of the dead zone on the left stick
	 * @param rightDeadZone
	 *            The magnitude of the dead zone on the right stick
	 */
	public XboxController(int port, double leftDeadZone, double rightDeadZone) {
		dStation = DriverStation.getInstance();
		setDeadZones(leftDeadZone, rightDeadZone);
		this.port = port;
		for (int ii = 0; ii < 10; ii++) {
			buttons[ii] = new Button();
		}
		refresh();
		Buttons = new ButtonRemap();
		buttonCount = dStation.getStickButtonCount(port);
	}

	/**
	 * Sends values to the rumble motors in the controller
	 * 
	 * @param type
	 *            either left or right
	 * @param value
	 *            (0 to 1) rumble intensity value
	 */
	public void vibrate(Sides type, double value) {
		if (value < 0)
			value = 0;
		else if (value > 1)
			value = 1;
		if (type == Sides.Left || type == Sides.Combined)
			lRumble = (short) (value * 65535);
		if (type == Sides.Right || type == Sides.Combined)
			rRumble = (short) (value * 65535);
		HAL.setJoystickOutputs((byte) port, 0, lRumble, rRumble);
	}

	/**
	 * Returns the state of a specific button
	 * 
	 * @param i
	 *            The button number starting with 1
	 * @return True if the button is pressed else False
	 * @throws Exception
	 *             if the button does not exist
	 */
	public boolean getRawButton(int i) throws Exception {
		i -= 1;
		if (i >= 0 && i < buttonCount)
			return (buttonState & (1 << i)) != 0;
		throw new Exception(String.format("Button %d does not exist", i));
	}

	/**
	 * Gets all 10 buttons in an array format
	 * 
	 * @return The entire button array
	 */
	public Button[] getButtonArray() {
		return buttons;
	}

	/**
	 * Returns the state of a specific axis
	 * 
	 * @param i
	 *            Axis number starting with 0
	 * @return Returns a double between -1 and 1
	 */
	public double getRawAxis(int i) {
		return dStation.getStickAxis(port, i);
	}

	/**
	 * @return The number of buttons that the controller has
	 */
	public int getButtonCount() {
		return buttonCount;
	}

	/**
	 * Reacquires the values for all inputs
	 */
	public void refresh() {
		//getDpad();
		getLeftStick();
		getRightStick();
		getTrigger();
		getButtons();
	}

	/**
	 * Sets the dead zones of the two sticks<br/>
	 * Set either to 0 to turn the dead zone off or anything up to 0.5
	 * 
	 * @param leftStick
	 *            The magnitude of the dead zone in the LeftStick
	 * @param rightStick
	 *            The magnitude of the dead zone in the RightStick
	 */
	public void setDeadZones(double leftStick, double rightStick) {
		leftStick = Math.max(Math.abs(leftStick), 0.5);
		rightStick = Math.max(Math.abs(rightStick), 0.5);
		LeftStick.deadZone = leftStick * leftStick;
		RightStick.deadZone = rightStick * rightStick;
	}

	/**
	 * Calculates the magnitude of on of the sticks
	 * 
	 * @param stick
	 *            The side of the stick
	 * @return The magnitude of the stick
	 */
	public double getMagnitude(StickSides stick) {
		if (stick == StickSides.Left)
			return Math.sqrt(LeftStick.X * LeftStick.X + LeftStick.Y * LeftStick.Y);
		else
			return Math.sqrt(RightStick.X * RightStick.X + RightStick.Y * RightStick.Y);
	}

	void getDpad() {
		DPad.set(dStation.getStickPOV(port, 0));
	}

	void getLeftStick() {
		LeftStick.set(dStation.getStickAxis(port, 0), dStation.getStickAxis(port, 1));
	}

	void getRightStick() {
		RightStick.set(dStation.getStickAxis(port, 4), dStation.getStickAxis(port, 5));
	}

	void getTrigger() {
		Triggers.set(dStation.getStickAxis(port, 2), dStation.getStickAxis(port, 3));
	}

	void getButtons() {
		buttonState = dStation.getStickButtons(port);
		for (int i = 0; i < 10; i++) {
			buttons[i].set((buttonState & (1 << i)) != 0);
		}
	}

	/**
	 * The triggers of the Controller
	 */
	public class triggers {
		/** (0 to 1) value for the individual trigger **/
		public double Right, Left;
		/** (-1 to 1) combined value equivalent to (Right - Left) **/
		public double Combined;
		private Axis trig;
		private int xs, ys;
		private double[] val = new double[3];

		private triggers(double r, double l) {
			Right = r;
			Left = l;
			combine();
		}

		public Axis getAxis(Sides x, Sides y) {
			if (trig != null) {
				return trig;
			} else {
				val[0] = Left;
				val[1] = Right;
				val[2] = Combined;
				xs = x.val;
				ys = y.val;
				trig = new Axis(val[xs], val[ys]);
				return trig;
			}
		}

		void set(double left, double right) {
			Left = left;
			Right = right;
			combine();
			if (trig != null) {
				val[0] = Left;
				val[1] = Right;
				val[2] = Combined;
				trig.set(val[xs], val[ys]);
			}
		}

		void combine() {
			Combined = Right - Left;
		}
	}

	/** The Dpad of the controller **/
	public class POV {
		public boolean Up = false, Down = false, Left = false, Right = false;
		/**
		 * Returns -1 if the Direction Pad is not pressed else it returns a
		 * compass orientation starting with up being 0
		 **/
		public int degrees = -1;

		void set(int degree) {
			Up = degree == 315 || degree == 0 || degree == 45;
			Down = degree <= 225 && degree >= 135;
			Left = degree <= 315 && degree >= 225;
			Right = degree <= 135 && degree >= 45;
			degrees = degree;
		}
	}

	/**
	 * Contains the values of a single Stick
	 */
	public class Axis {
		public double X, Y;
		double deadZone = 0;

		private Axis(double x, double y) {
			X = x;
			Y = y;
		}

		void set(double x, double y) {
			X = x;
			Y = y;
			applyDeadzone();
		}

		void applyDeadzone() {
			if (deadZone != 0 && X * X + Y * Y < deadZone) {
				X = 0;
				Y = 0;
			}
		}
	}

	/** Contains the controllers button **/
	public class Button {
		public boolean current = false, last = false, changedDown = false, changedUp = false;
		private Trigger buttonT;

		/**
		 * Runs your command automatically<br/>
		 * Acts when pressed<br/>
		 * Should only be called once when setting the command<br/>
		 * Requires <u>Scheduler.getInstance().run()</u>
		 * 
		 * @param command
		 *            your custom command
		 * @param state
		 *            A selection of when to run the command
		 */
		public void runCommand(Command command, CommandState state) {
			if (buttonT == null)
				buttonT = new Trigger() {
					@Override
					public boolean get() {
						return current;
					}
				};
			switch (state) {
			case WhenPressed:
				buttonT.whenActive(command);
				break;
			case Toggle:
				buttonT.toggleWhenActive(command);
				break;
			case WhilePressed:
				buttonT.whileActive(command);
				break;
			case WhileNotPressed:
				buttonT.whenInactive(command);
				buttonT.cancelWhenActive(command);
				break;
			case Cancel:
				buttonT.cancelWhenActive(command);
				break;
			}
		}

		void set(boolean current) {
			last = this.current;
			this.current = current;
			changedDown = !last && this.current;
			changedUp = last && !this.current;
		}
	}

	/** Gives names to the buttons **/
	public class ButtonRemap {
		public Button A = buttons[ButtonNumbers.A];
		public Button B = buttons[ButtonNumbers.B];
		public Button X = buttons[ButtonNumbers.X];
		public Button Y = buttons[ButtonNumbers.Y];
		/** Left Bumper **/
		public Button LB = buttons[ButtonNumbers.LB];
		/** Right Bumper **/
		public Button RB = buttons[ButtonNumbers.RB];
		public Button Back = buttons[ButtonNumbers.Back];
		public Button Start = buttons[ButtonNumbers.Start];
		public Button LeftStick = buttons[ButtonNumbers.LeftStick];
		public Button RightStick = buttons[ButtonNumbers.RightStick];

	}

	/** The states of commends given to buttons **/
	public enum CommandState {
		/**
		 * Runs the command every time it the button is pressed<br/>
		 * Does not cancel
		 **/
		WhenPressed,
		/**
		 * Runs the command on every first press<br/>
		 * Cancels the command on every second press
		 **/
		Toggle,
		/** Runs the command while the button is pressed **/
		WhilePressed,
		/** Runs the command while the button is not pressed **/
		WhileNotPressed,
		/** Cancels the command once the button is pressed **/
		Cancel;
	}

	public enum StickSides {
		Left, Right
	}

	public enum Sides {
		Left(0), Right(1), Combined(2);

		int val;

		Sides(int v) {
			val = v;
		}
	}

	public static class ButtonNumbers {
		public static final int A = 0;
		public static final int B = 1;
		public static final int X = 2;
		public static final int Y = 3;
		/** Left Bumper **/
		public static final int LB = 4;
		/** Right Bumper **/
		public static final int RB = 5;
		public static final int Back = 6;
		public static final int Start = 7;
		public static final int LeftStick = 8;
		public static final int RightStick = 9;
	}
}
