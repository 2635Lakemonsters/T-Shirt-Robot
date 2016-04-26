/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    final int id_ELEVATIONMOTOR = 6;
    final int id_BARRELMOTOR = 5;

    final int id_JOYSTICK = 1;

    //Joystick constants
    final int id_LEFTSTICKXAXIS = 1;
    final int id_LEFTSTICKYAXIS = 2;
    final int id_RIGHTSTICKYAXIS = 6;
    final int id_LEFTTRIGGER = 5;
    final int id_RIGHTTRIGGER = 6;
    final int id_KLAXONBUTTON = 1;
    final int id_TRAINHORNBUTTON = 4;
    final int id_RAISEDELAYBUTTON = 2;
    final int id_LOWERDELAYBUTTON = 3;
    
    final int id_WARNINGLIGHTS = 4;

    final int id_SOLENOID = 1;
    final int id_TRAINHORN = 2;
    final int id_KLAXON = 3;
    
    final int id_LIFTUPPERLIMITSWITCH = 2;
    final int id_LIFTLOWERLIMITSWITCH = 1;
    
    final int id_LIFTENCODER1 = 10;
    final int id_LIFTENCODER2 = 9;
    
    final int id_ROTATIONENCODER1 = 7;
    final int id_ROTATIONENCODER2 = 8;

    //DEVICE DECLARATIONS
    Talon leftMotor1;
    Talon leftMotor2;
    Talon rightMotor1;
    Talon rightMotor2;
    Talon barrelMotor;
    Talon elevationMotor;
    
    Encoder rotationEncoder;
    Encoder liftEncoder;
    PIDController liftPID;
    PIDController rotationPID;

    Relay solenoid;
    Relay trainHorn;
    Relay klaxon;
    
    DigitalInput liftUpperLimitSwitch;
    DigitalInput liftLowerLimitSwitch;
    Relay warningLights;

    Joystick joystick;
    ArcadeDrive Drive;
    Launcher Launcher;
    StateTracker Tracker;
    
    boolean liftPIDEnabled = false;

    public void robotInit()
    {
        leftMotor1 = new Talon(id_LEFTMOTOR1);
        leftMotor2 = new Talon(id_LEFTMOTOR2);
        rightMotor1 = new Talon(id_RIGHTMOTOR1);
        rightMotor2 = new Talon(id_RIGHTMOTOR2);
        barrelMotor = new Talon(id_BARRELMOTOR);
        elevationMotor = new Talon(id_ELEVATIONMOTOR);
        
        rotationEncoder = new Encoder(id_ROTATIONENCODER1, id_ROTATIONENCODER2);
        liftEncoder = new Encoder(id_LIFTENCODER1, id_LIFTENCODER2);
        liftPID = new PIDController(.1, .1, .1, liftEncoder, elevationMotor);
        rotationPID = new PIDController(.1, .1, .1, rotationEncoder, barrelMotor);


        solenoid = new Relay(id_SOLENOID);
        trainHorn = new Relay(id_TRAINHORN);
        klaxon = new Relay(id_KLAXON);
        
        liftUpperLimitSwitch = new DigitalInput(id_LIFTUPPERLIMITSWITCH);
        liftLowerLimitSwitch = new DigitalInput(id_LIFTLOWERLIMITSWITCH);
        warningLights = new Relay(id_WARNINGLIGHTS);

        joystick = new Joystick(id_JOYSTICK);
        Drive = new ArcadeDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
        Launcher = new Launcher(barrelMotor, solenoid, false, rotationPID);
        Tracker = new StateTracker();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void teleopInit()
    {
        //liftPID.enable();
        rotationPID.enable();
        System.out.println("[RobOS] Teleop enabled.");
    }
    
    public void autonomousPeriodic()
    {
        System.out.println("[RobOS] Autonomous mode. Robot will not function!");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        double leftStickXAxis = joystick.getRawAxis(id_LEFTSTICKXAXIS);
        double leftStickYAxis = joystick.getRawAxis(id_LEFTSTICKYAXIS);
        double rightStickYAxis = -joystick.getRawAxis(id_RIGHTSTICKYAXIS)/3;
        boolean leftTrigger = joystick.getRawButton(id_LEFTTRIGGER);
        boolean rightTrigger = joystick.getRawButton(id_RIGHTTRIGGER);
        boolean liftLowerLimitBool = !liftLowerLimitSwitch.get();
        boolean liftUpperLimitBool = !liftUpperLimitSwitch.get();
        
        System.out.println(liftEncoder.getRaw());
        
        
        double liftEncoderCounts = liftEncoder.getDistance();       
        System.out.println(liftEncoderCounts);

        Drive.drive(-leftStickYAxis, -leftStickXAxis);
        //System.out.println(liftLowerLimit.get());
        
        /**
        if(liftPIDEnabled && (rightStickYAxis != 0 || liftUpperLimitBool || liftLowerLimitBool))
        {
            liftPID.disable();
            liftPIDEnabled = false;
        }
        
//        if(!liftPIDEnabled && rightStickYAxis == 0 /* && !liftUpperLimitBool && !liftLowerLimitBool)
        {
            liftPID.setSetpoint(liftEncoder.getValue());
            liftPID.enable();
            liftPIDEnabled = true;
        }
        **/
        
        if(rightStickYAxis < 0)
        {
            if(!liftLowerLimitBool)
            //OK to move down, setting value...
                elevationMotor.set(rightStickYAxis);
            else
                elevationMotor.set(0);
        }
        else if(rightStickYAxis > 0)
        {
            if(!liftUpperLimitBool )
            //OK to move up, setting value...
                elevationMotor.set(rightStickYAxis);
            else
                elevationMotor.set(0);
        }

        if (leftTrigger && rightTrigger) 
        {
            //System.out.println("Joystick fire command, attempting to fire...");
            Launcher.fire(false);
        }
        Launcher.timedActions();

        //Light Strip activiation; based on either trigger being pressed
        if(leftTrigger || rightTrigger)
        {
            warningLights.set(Value.kForward); //Relays use Values instead of normal booleans. Nerds.
        }
        else
        {
            warningLights.set(Value.kReverse);
        }
        
        //Klaxon activation; based on pressing controller button
        if (joystick.getRawButton(id_KLAXONBUTTON))
        {
            klaxon.set(Value.kForward);
        }
        else
        {
            klaxon.set(Value.kOff);
        }

        //Train Horn activation; based on pressing controller button
        if (joystick.getRawButton(id_TRAINHORNBUTTON))
        {
            trainHorn.set(Value.kForward);
        }
        else
        {
            trainHorn.set(Value.kOff);
        }
        
        //Changes relay delay if button is pressed
        if(joystick.getRawButton(id_RAISEDELAYBUTTON))
        {
            Launcher.raiseDelay();
        }
        
        if(joystick.getRawButton(id_LOWERDELAYBUTTON))
        {
            Launcher.lowerDelay();
        }
    }
    /**
     * <Something Sexy>
     * Thyme
     * Oregano 
     * Nutmeg
     * Cinnamon
     * Sage
     * Water
     * Basil
     * Pizza
     * Mint
     * (I wanted to spice up my code)
     */
}
