
package org.usfirst.frc.team3571.robot;
import org.usfirst.frc.team3571.robot.command.DefaultAuto;
import org.usfirst.frc.team3571.robot.command.MyAuto;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Robot extends IterativeRobot {
	SendableChooser<Command> chooser;
	Command auto;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser = new SendableChooser<Command>();
		chooser.addDefault("Default Auto", new DefaultAuto());
		chooser.addObject("My Auto", new MyAuto());
		SmartDashboard.putData("Auto choices", chooser);
		
	}

	/**
	 * This is called once when the autonomous mode is started
	 */
	@Override
	public void autonomousInit() {
		auto = chooser.getSelected();
		auto.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This is called once when the Teleop mode is started
	 */
	@Override
	public void teleopInit() {
		Teleop.init();
	}

	/**
	 * This function is called periodically during operator control at a maximum
	 * rate of 50Hz
	 */
	@Override
	public void teleopPeriodic() {
		OI.refreshAll();
		Teleop.periodic();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		// Put code you want to test here
		// It is recommended to put functions here in order to keep it clean
	}
    
}
