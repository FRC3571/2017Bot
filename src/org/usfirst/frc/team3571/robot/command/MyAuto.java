package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MyAuto extends Command {

	Timer zayd = new Timer(); 
	double timeElapsed;
	
    public MyAuto() {
       
    }
    
    protected void initialize() {
    zayd.reset();
    zayd.start();
    }
   
    protected void execute() {  
    timeElapsed = zayd.get();
    if (timeElapsed < 5.46808511){
    	OI.drive.arcadeDrive(0.75,0);
    }
    
    else if (timeElapsed < 10.93617022){
    	OI.drive.arcadeDrive(-0.75,0);
    }
    
    else {
    	OI.drive.arcadeDrive(0,0);
    }
}
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

