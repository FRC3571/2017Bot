package org.usfirst.frc.team3571.robot.utilities;

public abstract class Worker {
	public int to, from;
	public abstract void run();
	public Worker(int from, int to){
		this.to = to;
		this.from = from;
	}
}
