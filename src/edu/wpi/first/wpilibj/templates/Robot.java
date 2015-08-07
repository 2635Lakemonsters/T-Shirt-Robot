/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    //CONSTANTS
    final int id_LEFTMOTOR1 = 1;
    final int id_LEFTMOTOR2 = 2;
    
    final int id_RIGHTMOTOR1 = 3;
    final int id_RIGHTMOTOR2 = 4;
    
    final int id_BARRELMOTOR = 5;
    
    //DEVICE DECLARATIONS
    Talon LEFTMOTOR1;
    Talon LEFTMOTOR2;
    Talon RIGHTMOTOR1;
    Talon RIGHTMOTOR2;
    Talon BARRELMOTOR;
           
    public void robotInit() 
    {
        LEFTMOTOR1 = new Talon(id_LEFTMOTOR1);
        LEFTMOTOR2 = new Talon(id_LEFTMOTOR2);
        RIGHTMOTOR1 = new Talon(id_RIGHTMOTOR1);
        RIGHTMOTOR2 = new Talon(id_RIGHTMOTOR2);
        BARRELMOTOR = new Talon(id_BARRELMOTOR);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
