package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.CameraModule;
import org.usfirst.frc.team3571.robot.OI;
import org.usfirst.frc.team3571.robot.Teleop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DefaultAuto extends Command {
    public DefaultAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {        
    	//
    
	    //double distance = Teleop.getDistance();
		//if (distance >= 500 ){
			//OI.drive.arcadeDrive(1,0);
	    SmartDashboard.putBoolean("Checker", Teleop.test);
	//	}
	    if (Teleop.test = true){
	    	
	    	if( OI.camera.turn*0.005 != 0){
	    	 OI.drive.arcadeDrive(-.2,OI.camera.turn*0.005);// camera.turn * 0.005);
	 		
	    	}
	   
	    }
	    SmartDashboard.putNumber("angle", OI.camera.turn *0.005);
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
