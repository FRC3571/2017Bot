package org.usfirst.frc.team3571.robot;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.CameraServer;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
public class CameraModule {
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	public static double turn = 0;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	
	private final Object imgLock = new Object();
	
	public void init() {
	    AxisCamera camera = CameraServer.getInstance().addAxisCamera("10.35.71.101");
	    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
	    
	    visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	                centerX = r.x + (r.width / 2);
	            }
	        }
	       turn = centerX - (IMG_WIDTH / 2);
	    
	    });
	    visionThread.start();
	        
	  
	}

}
