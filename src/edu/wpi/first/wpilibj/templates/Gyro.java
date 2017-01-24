package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogGyro;

public class Gyro {
	
	public AnalogGyro gyro;
	
	double Kp = 0.03;
	
	 public Gyro() {
	        gyro = new AnalogGyro(1);             // Gyro on Analog Channel 1
	        gyro.reset();
	 }
}

