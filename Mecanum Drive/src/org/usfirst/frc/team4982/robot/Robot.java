
package org.usfirst.frc.team4982.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/* Wheel Numbering
 * 1   0
 * 
 * 3   2
 */

public class Robot extends IterativeRobot {
    Joystick driver = new Joystick(0);  //Set first joystick plugged in as driver
    Drive drive = new Drive();
    
    public void robotInit() {

    }

    public void autonomousPeriodic() {

    }

   
    public void teleopPeriodic() {
        driveBase();
    }
    
    
    public void testPeriodic() {
    	
    }
    
    
    /**
     * Calculates the desired values for each wheel.
     * Uses joystick named 'driver' and calls methods from the drive class.
     * @author Jake Crozier
     */
    public void driveBase(){  //Axis 4 is small switch on the front, Top direction pad is axis 5 & 6
    	double x = driver.getRawAxis(1);	
    	double y = driver.getRawAxis(2);
    	double t = driver.getRawAxis(3);
    	
    	if(x > -0.1 && x < 0.1){		//10% null zone for the x value
    		x = 0;
    	}
    	if(y > -0.1 && y < 0.1){		//10% null zone for the y value
    		y = 0;
    	}
    	if(t > -0.1 && t < 0.1){		//10% null zone for the twist value
    		t = 0;
    	}
    	double[] wheels = new double[4]; //Double array storing desired values for the wheels
    	
    	wheels[0] = y-(t+x);    //Front right wheel
    	wheels[1] = y+t+x;		//Front left wheel
    	wheels[2] = y+x-t;		//Rear right wheel
    	wheels[3] = y+t-x;		//Rear left wheel
    	
    	double max = 1;				    	
    	for(int i = 0; i < 4; i++){		//Finds the max motor value, only if it is above 1 otherwise just store 1 as max
    		if(Math.abs(wheels[i]) > max){
    			max = Math.abs(wheels[i]);
    		}
    	}
    	
    	for(int i = 0; i < 4; i++){	
    		wheels[i] /= max;	 		//Divides motor values by max
    		if(driver.getRawButton(1)){	//If pressing trigger divide all values by 2, allows for an option of finer control
    			wheels[i] /= 2;
    		}
    		drive.setMotor(wheels[i], i);	//Set each motor
    	}
    	drive.calculateMotors();
    	drive.assignMotors();
    }
}
