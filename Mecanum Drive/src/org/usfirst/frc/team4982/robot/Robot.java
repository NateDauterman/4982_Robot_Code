
package org.usfirst.frc.team4982.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/* Wheel Numbering
 * 0    1
 * 
 * 2    3
 */

public class Robot extends IterativeRobot {
    Joystick driver = new Joystick(0);
    Drive drive = new Drive();
    
    public void robotInit() {

    }

    
    public void autonomousPeriodic() {

    }

   
    public void teleopPeriodic() {
        
    }
    
    
    public void testPeriodic() {
    
    }
    
    public void driveBase(){
    	double x = driver.getRawAxis(0);
    	double y = driver.getRawAxis(1);
    	double t = driver.getRawAxis(2);
    	
    	if(x > -0.15 && x < 0.15){
    		x = 0;
    	}
    	if(y > -0.15 && y < 0.15){
    		y = 0;
    	}
    	if(t > -0.15 && t < 0.15){
    		t = 0;
    	}
    	double[] wheels = new double[4];
    	wheels[0] = y-(t+x);
    	wheels[1] = y+t+x;
    	wheels[2] = y+x-t;
    	wheels[3] = y+t-x;
    	double max = wheels[0];
    	for(int i = 0; i < 4; i++){
    		if(wheels[i] > max){
    			max = wheels[i];
    		}
    	}
    	for(int i = 0; i < 4; i++){
    		wheels[i] /= max;
    		drive.setMotor(wheels[i], i);
    	}
    }
}
