package org.usfirst.frc.team3571.robot.command;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3571.robot.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LeftStart extends Command {

	Timer t1 = new Timer();
	static double timeElapsed = 0;
    public LeftStart() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t1.reset();
    	t1.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timeElapsed = t1.get();
    	if (timeElapsed < 4){
    		OI.drive.arcadeDrive(0.5, 0);
    	}
    	else if (timeElapsed < 5){
    		OI.drive.arcadeDrive(0, 0.5);
    	}
    	else if (timeElapsed < 7){
    		OI.drive.arcadeDrive(0.5,0);
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
