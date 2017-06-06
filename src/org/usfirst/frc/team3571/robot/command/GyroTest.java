package org.usfirst.frc.team3571.robot.command;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3571.robot.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class GyroTest extends Command {

	long i = 0;
	boolean limit = true;
	Timer t1 = new Timer();
	double timeElapsed = 0;
	double angle;
	
    public GyroTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.gyro.reset();
    	t1.reset();
    	t1.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	angle = OI.gyro.getAngle();
    	timeElapsed = t1.get();
    	SmartDashboard.putNumber("angle", angle);
    	if (timeElapsed < 1){
    		OI.drive.arcadeDrive(0.1, 0);
    	}
    	else{
    		OI.drive.arcadeDrive(0,0.2);
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
