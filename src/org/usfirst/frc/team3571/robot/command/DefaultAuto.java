package org.usfirst.frc.team3571.robot.command;


import java.text.DecimalFormat;

import org.usfirst.frc.team3571.robot.OI;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.Gyro;


/**
 *
 */
public class DefaultAuto extends Command {

	public double Kp = 0.006;
	
    public DefaultAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
   
    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {        
    	double angle = OI.gyro.getAngle(); // get current heading
        if (angle > -5 & angle < 5){
        OI.drive.arcadeDrive(0, 0); // drive towards heading 0
        Timer.delay(0.004);
        }
        else if(angle >= 5){
        	OI.drive.arcadeDrive(0.02, 0.50); // drive towards heading 0
        Timer.delay(0.004);
        }
        else {
            OI.drive.arcadeDrive(0.02, -0.50); // drive towards heading 0
            Timer.delay(0.004);
            }     double numb = -angle*Kp*1000;
        SmartDashboard.putNumber("value", numb);
        SmartDashboard.putNumber("RawValue", OI.gyro.getAngle());
        SmartDashboard.putNumber("Angle", angle);
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
