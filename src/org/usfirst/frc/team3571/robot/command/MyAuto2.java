package org.usfirst.frc.team3571.robot.command;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3571.robot.OI;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class MyAuto2 extends Command {

	Timer jaz = new Timer();
	double timeElapsed;
	
    public MyAuto2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	jaz.reset();
    	jaz.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timeElapsed = jaz.get();
    
    	if (timeElapsed < 3){
    		OI.drive.arcadeDrive(0.75,0);
    	}
    	else if (timeElapsed < 5){
    		OI.drive.arcadeDrive(0.75,0.5);
    	}
    	else {
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
