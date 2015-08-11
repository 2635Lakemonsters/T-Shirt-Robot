/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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
    
    final int id_JOYSTICK = 1;
    
    final int id_LEFTSTICKXAXIS = 0;
    final int id_LEFTSTICKYAXIS = 1;
    final int id_RIGHTSTICKYAXIS = 5;
    
    
    
    //DEVICE DECLARATIONS
    Talon leftMotor1;
    Talon leftMotor2;
    Talon rightMotor1;
    Talon rightMotor2;
    Talon barrelMotor;
    
    Joystick joystick;
    ArcadeDrive drive;
           
    public void robotInit() 
    {
        leftMotor1 = new Talon(id_LEFTMOTOR1);
        leftMotor2 = new Talon(id_LEFTMOTOR2);
        rightMotor1 = new Talon(id_RIGHTMOTOR1);
        rightMotor2 = new Talon(id_RIGHTMOTOR2);
        barrelMotor = new Talon(id_BARRELMOTOR);
        
        joystick = new Joystick(id_JOYSTICK);
        drive = new ArcadeDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        double leftStickXAxis = joystick.getRawAxis(id_LEFTSTICKXAXIS);
        double leftStickYAxis = joystick.getRawAxis(id_LEFTSTICKYAXIS);
        double rightStickYAxis = joystick.getRawAxis(id_RIGHTSTICKYAXIS);
        
        drive.Drive(leftStickXAxis, leftStickYAxis);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
        
    }
    
}
