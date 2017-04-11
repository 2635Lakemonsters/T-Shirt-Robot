/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Encapsulation for the Launcher. Call fire() to fire the cannon. If it refuses
 * to fire, it is likely failing the checkYourPrivilege() safety check.
 *
 * @author Siren
 */
public class Launcher
{

    private final Relay relay;
    //private final SpeedController barrelMotor;
    private final Timer timer;
    private final PIDController rotator;
    public int currentTick = 0;
    private int relayDelay = 5;
    
    //TODO: Calibrate. Is this michael's value?
    public double deviation = -687.17;

    /**
     * @param rotateMotor Pass the variable containing the barrel rotation motor
     * @param solenoid Pass the variable containing the firing solenoid
     * @param barrelEncoder Encoder PID Loop for barrel rotation   
     */
    public Launcher(SpeedController rotateMotor, Relay solenoid, PIDController barrelEncoder)
    {
        relay = solenoid;
        timer = new Timer();
        //barrelMotor = rotateMotor;
        rotator = barrelEncoder;
    }
    
    /**
     * @param m_override PASS THIS AS FALSE UNLESS YOU LIKE KILLING PEOPLE AND BREAKING THINGS. SERIOUSLY. NEVER PASS THIS AS TRUE.
     */
    public void fire(boolean m_override)
    {
        if(checkYourPrivilege()|| m_override)
        {
            //Passed safety check, fire cannon
            //System.out.println("Firing...");
            relay.set(Value.kForward);
            timer.tick();
        }
        else
        {
            System.out.println("Uh, check your privilege.");
        }
    }

    public boolean checkYourPrivilege()
    {
        boolean privileged;
        //TODO, add sensors. Maybe even use another class and method.
        if (timer.getTick() < 1)
        {
         //Sensors OK, allowing firing
                privileged = true;
        }
        else
        {
            //Sensors not clear, not allowing fire.
            privileged = false;
        }        
        return privileged;
    }

    public void timedActions()
    {
        /**
         * TODO:
         * Implement variable delay system
         */
        
        if(timer.getTick() == 1)
        {
            //First tick triggered by firing method, not ticking to avoid skipping second step
            currentTick = timer.getTick();
        }
        if(timer.getTick() > 1)
        {
            //Normal ticking
            timer.tick();
            currentTick = timer.getTick();
            //System.out.println(currentTick);
        }

        if (currentTick == relayDelay)
        {
            //System.out.println("Resetting...");
            relay.set(Value.kOff);
            
            //Indexing barrel forward
            rotator.reset();
            rotator.setSetpoint(deviation);
            
            /**
            double current = rotator.get();
            rotator.setSetpoint(current + deviation);
            * **/
        }

        if (currentTick == 53)
        {
            //Last Command, resetting timer and incrementing barrel
            timer.reset();       
        }
        
        if (currentTick == 1)
        {
            //Special case tick, just don't ask
            timer.tick();
        }

        //Implement other timed stuff here
    }
    
    public void raiseDelay()
    {
        ++relayDelay;
        System.out.println("[RobOS] Relay Delay: " + relayDelay);
    }
    
    public void lowerDelay()
    {
        --relayDelay;
        System.out.println("[RobOS] Relay Delay: " + relayDelay);
    }
}
