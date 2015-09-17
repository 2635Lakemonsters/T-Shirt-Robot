/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

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
    public int currentTick = 0;

    /**
     * @param rotateMotor Pass the variable containing the barrel rotation motor
     * @param solenoid Pass the variable containing the firing solenoid
     * @param overrideLogic PASS THIS FALSE UNLESS YOU LIKE THINGS KILLING
     * PEOPLE.
     */
    public Launcher(SpeedController rotateMotor, Relay solenoid, boolean overrideLogic)
    {
        relay = solenoid;
        timer = new Timer();
        barrelMotor = rotateMotor;
        override = overrideLogic;

    }

    public void fire(boolean im_override)
    {
        if (checkYourPrivilege() || im_override)
        {
            //Firing code here
            System.out.println("Firing...");
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
        if (timer.getTick() > 0)
        {
            //Timer initialized, ticking...
            timer.tick();
            currentTick = timer.getTick();
            System.out.println(currentTick);
        }

        if (currentTick == 5)
        {
            System.out.println("Resetting...");
            relay.set(Value.kOff);
            barrelMotor.set(.25);
        }

        if (currentTick == 53)
        {
            //1000ms later, stopping rotation motor
            barrelMotor.set(0);

            //Last Command, resetting timer
            timer.reset();
        }

        //Implement other timed stuff here
    }

}
