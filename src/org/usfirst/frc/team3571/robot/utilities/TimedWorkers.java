package org.usfirst.frc.team3571.robot.utilities;

import java.util.Vector;
import edu.wpi.first.wpilibj.Timer;

public class TimedWorkers {
	private Vector<Worker> workers=new Vector<Worker>();
	private Timer time=new Timer();
	
	public void add(Worker w){
		workers.add(w);
	}
	public void run(){
		double t = time.get();
		workers.forEach(x -> {
			if(x.from >= t && x.to <= t)x.run();
		});
	}
	public void start(){
		time.start();
	}
	public void stop(){
		time.stop();
	}

}
