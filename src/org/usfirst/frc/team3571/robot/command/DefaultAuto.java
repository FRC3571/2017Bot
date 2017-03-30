package org.usfirst.frc.team3571.robot.command;

//import org.usfirst.frc.team3571.robot.CameraModule;
import org.usfirst.frc.team3571.robot.OI;
import org.usfirst.frc.team3571.robot.Teleop;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Date;
/**
 *
 */
public class DefaultAuto extends Command {
	long i = 0;
    public DefaultAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.gyro.reset();
    }
    //public static final CameraModule camera = new CameraModule();
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {        
	   // SmartDashboard.putBoolean("Checker", Teleop.test);
	//  if (Teleop.test = true){
	    	//if( camera.turn*0.005 != 0){
	   // 	Timer.delay(0.45);
    	SmartDashboard.putNumber("Gyro", OI.gyro.getAngle());
    	i = System.currentTimeMillis();
    	while (System.currentTimeMillis() < ( i + 4000))
    	{
    		OI.drive.arcadeDrive(0.5, 0);
    	}
    	OI.drive.arcadeDrive(0,0);
    	i = System.currentTimeMillis();
    	while (System.currentTimeMillis() < (i + 1000))
    	{
    		OI.drive.arcadeDrive(0, -0.5);
    	}
    	OI.drive.arcadeDrive(0, 0);
    	i = System.currentTimeMillis();
    	
    }
	    
	  // } 
   //}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
