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
    private final SpeedController barrelMotor;
    private final Timer timer;
    private final boolean override;
    private final PIDController rotator;
    public int currentTick = 0;
    private int relayDelay = 5;
    public int[] setPointValues;
    public int barrelPosition = 0;

    /**
     * @param rotateMotor Pass the variable containing the barrel rotation motor
     * @param solenoid Pass the variable containing the firing solenoid
     * @param overrideLogic PASS THIS FALSE UNLESS YOU LIKE THINGS KILLING PEOPLE
     * @param barrelEncoder Encoder PID Loop for barrel rotation   
     */
    public Launcher(SpeedController rotateMotor, Relay solenoid, boolean overrideLogic, PIDController barrelEncoder)
    {
        relay = solenoid;
        timer = new Timer();
        barrelMotor = rotateMotor;
        override = overrideLogic;
        rotator = barrelEncoder;
        
        setPointValues = new int[6];
        setPointValues[0] = 0;
        setPointValues[1] = 60;
        setPointValues[2] = 120;
        setPointValues[3] = 180;
        setPointValues[4] = 240;
        setPointValues[5] = 300;

    }
    public void fire(boolean im_override)
    {
        if(checkYourPrivilege()|| im_override)
        {
            //Firing code here
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
        if (override == true)
        {
            privileged = true;
        }
        else
        {
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
            rotator.setSetpoint(setPointValues[barrelPosition]);
            ++barrelPosition;
        }

        if (currentTick == 53)
        {
            //1000ms later, stopping rotation motor
            if(barrelPosition == 5)
            {
                barrelPosition = 0;
            }

            //Last Command, resetting timer
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
