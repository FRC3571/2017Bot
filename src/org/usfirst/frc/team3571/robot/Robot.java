
package org.usfirst.frc.team3571.robot;
import org.usfirst.frc.team3571.robot.command.DefaultAuto;
import org.usfirst.frc.team3571.robot.command.LeftStart;
import org.usfirst.frc.team3571.robot.command.RightStart;
import org.usfirst.frc.team3571.robot.command.CenterStart;
import org.usfirst.frc.team3571.robot.command.MyAuto;
import org.usfirst.frc.team3571.robot.command.GyroTest;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Robot extends IterativeRobot {
	private AxisCamera camera;
	SendableChooser<Command> chooser;
	Command auto;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		camera = CameraServer.getInstance().addAxisCamera(RobotMap.IP.CAMERA);
		
		chooser = new SendableChooser<Command>();
		chooser.addDefault("MyAuto", new MyAuto());
		chooser.addObject("Left Start", new LeftStart());
		chooser.addObject("Right Start", new RightStart());
		chooser.addObject("Center Start", new CenterStart());
		chooser.addObject("Default Auto", new DefaultAuto());
		chooser.addObject("GyroTest", new GyroTest());
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
