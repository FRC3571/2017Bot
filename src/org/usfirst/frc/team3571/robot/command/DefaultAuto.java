package org.usfirst.frc.team3571.robot.command;

//
import org.usfirst.frc.team3571.robot.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class DefaultAuto extends Command {
	long i = 0;
	boolean limit = true;
	Timer t1 = new Timer();
	double timeElapsed = 0;
	double angle;
	
    public DefaultAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.gyro.reset();
    	t1.reset();
    	t1.start();
    }
    //public static final CameraModule camera = new CameraModule();
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	angle = OI.gyro.getAngle();
    	
    	SmartDashboard.putNumber("Angle", angle);
    	
    	timeElapsed = t1.get();
    	if (timeElapsed < 2){
    		OI.drive.arcadeDrive(0.3, 0);
    	}
    	else{
    		OI.drive.arcadeDrive(0,0);
    	}
    	
    }

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
