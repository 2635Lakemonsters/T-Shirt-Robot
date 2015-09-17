/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */

    //CONSTANTS
    final int id_LEFTMOTOR1 = 1;
    final int id_LEFTMOTOR2 = 2;

    final int id_RIGHTMOTOR1 = 3;
    final int id_RIGHTMOTOR2 = 4;

    final int id_ELEVATIONMOTOR = 5;
    final int id_BARRELMOTOR = 6;

    final int id_JOYSTICK = 1;

    final int id_LEFTSTICKXAXIS = 1;
    final int id_LEFTSTICKYAXIS = 2;
    final int id_RIGHTSTICKYAXIS = 6;
    final int id_LEFTTRIGGER = 5;
    final int id_RIGHTTRIGGER = 6;
    final int id_KLAXONBUTTON = 1;
    final int id_TRAINHORNBUTTON = 4;

    final int id_SOLENOID = 1;
    final int id_TRAINHORN = 2;
    final int id_KLAXON = 3;
    
    final int id_TRIGGER = 1;

    //DEVICE DECLARATIONS
    Talon leftMotor1;
    Talon leftMotor2;
    Talon rightMotor1;
    Talon rightMotor2;
    Talon barrelMotor;
    Talon elevationMotor;

    Relay solenoid;
    Relay trainHorn;
    Relay klaxon;
    
    DigitalInput trigger;

    Joystick joystick;
    ArcadeDrive Drive;
    Launcher Launcher;

    public void robotInit()
    {
        leftMotor1 = new Talon(id_LEFTMOTOR1);
        leftMotor2 = new Talon(id_LEFTMOTOR2);
        rightMotor1 = new Talon(id_RIGHTMOTOR1);
        rightMotor2 = new Talon(id_RIGHTMOTOR2);
        barrelMotor = new Talon(id_BARRELMOTOR);
        elevationMotor = new Talon(id_ELEVATIONMOTOR);

        solenoid = new Relay(id_SOLENOID);
        trainHorn = new Relay(id_TRAINHORN);
        klaxon = new Relay(id_KLAXON);
        
        trigger = new DigitalInput(id_TRIGGER);

        joystick = new Joystick(id_JOYSTICK);
        Drive = new ArcadeDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
        Launcher = new Launcher(barrelMotor, solenoid, false);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void teleopInit()
    {
        System.out.println("[RobOS] Teleop enabled.");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        double leftStickXAxis = joystick.getRawAxis(id_LEFTSTICKXAXIS);
        double leftStickYAxis = joystick.getRawAxis(id_LEFTSTICKYAXIS);
        double rightStickYAxis = joystick.getRawAxis(id_RIGHTSTICKYAXIS);
        boolean leftTrigger = joystick.getRawButton(id_LEFTTRIGGER);
        boolean rightTrigger = joystick.getRawButton(id_RIGHTTRIGGER);
        boolean triggered = trigger.get();

        Drive.drive(-leftStickYAxis, leftStickXAxis);
        elevationMotor.set(-rightStickYAxis);

        if (leftTrigger & rightTrigger || triggered)
        {
            //System.out.println("Joystick fire command, attempting to fire...");
            Launcher.fire(false);
        }
        Launcher.timedActions();

        if (joystick.getRawButton(id_KLAXONBUTTON))
        {
            klaxon.set(Value.kForward);
        }
        else
        {
            klaxon.set(Value.kReverse);
        }

        if (joystick.getRawButton(id_TRAINHORNBUTTON))
        {
            trainHorn.set(Value.kForward);
        }
        else
        {
            trainHorn.set(Value.kReverse);
        }

    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {

    }

}
