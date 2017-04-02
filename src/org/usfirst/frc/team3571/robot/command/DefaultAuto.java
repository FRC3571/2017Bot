package org.usfirst.frc.team3571.robot.command;

//
import org.usfirst.frc.team3571.robot.OI;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class DefaultAuto extends Command {
	long i = 0;
	boolean limit = true;
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
			/* 
		
    	SmartDashboard.putNumber("Gyro", OI.gyro.getAngle());
    	i = System.currentTimeMillis();
    	while (System.currentTimeMillis() < ( i + 1250))
    	{
    		OI.drive.arcadeDrive(1, 0);
    	}
    	OI.drive.arcadeDrive(0,0);
    	i = System.currentTimeMillis();
    	while (System.currentTimeMillis() < (i + 800))
    	{
    		OI.drive.arcadeDrive(0, 0.75);
    	}
    	OI.drive.arcadeDrive(0, 0);
    	i = System.currentTimeMillis();
    	while(System.currentTimeMillis() < (i + 450))
    	{
    		OI.drive.arcadeDrive(0.75, 0);
    	}
    	OI.drive.arcadeDrive(0,0);
    	try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
    	// dankdankdankdankdank
    /*	limit = OI.limit_button.get();
    	i = System.currentTimeMillis();
    	if (limit == false)
    	{
    		OI.drive.arcadeDrive(0.3, 0);
    	}
    	else if (System.currentTimeMillis() < ( i + 1100) && limit == true)
    	{
    		limit = OI.limit_button.get();
    		OI.drive.arcadeDrive(-1, 0); 
    	}
    	else if (System.currentTimeMillis() < (i + 1900) && limit == true)
    	{
    		limit = OI.limit_button.get();
    		OI.drive.arcadeDrive(0, -0.75);
    	}
    	else if(System.currentTimeMillis() < (i + 2600) && limit == true)
    	{
    		limit = OI.limit_button.get();
    		OI.drive.arcadeDrive(-0.6, 0);
    	}
    	OI.drive.arcadeDrive(0,0);
    	} */
    	if (i == 0)
    	{
    		i = System.currentTimeMillis();
    	}
    	if (System.currentTimeMillis() < (i + 5000))
    	{
    		OI.drive.arcadeDrive(0.75, 0);
    	}
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
