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
 *  Encapsulation for the Launcher. Call fire() to fire the cannon. If it refuses to fire,
 *  it is likely failing the checkYourPrivilege() safety check. 
 *   @author Siren
 */
public class Launcher 
{
    private final Relay relay;
    private final Timer timer;
    public int currentTime = 0;
 /**
 *   @param barrelMotor Pass the variable containing the barrel rotation motor
 *   @param solenoid Pass the variable containing the firing solenoid
 *   @param override PASS THIS FALSE UNLESS YOU LIKE THINGS KILLING PEOPLE.
 */
    public Launcher(SpeedController barrelMotor, Relay solenoid, boolean override)
    {
        relay = solenoid;
        timer = new Timer();
    }
    
    public void fire()
    {
        if(checkYourPrivilege())
        {
           //Firing code here
           System.out.println("Firing...");
           relay.set(Value.kForward);
        }
        else
        {
           System.out.println("Uh, check your privilege.");
        }
    }
    
    public boolean checkYourPrivilege()
    {
        boolean privileged;
        //TODO, add sensors
        if(1 == 1)       
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
        if(timer.getTime() > 1)
        {
            timer.tick();
            currentTime = timer.getTime();              
        }
        
        if(currentTime == 3)
        {
            relay.set(Value.kReverse);
            
            //Last Command, resetting timer
            timer.reset();
        }
        
        //Implement other timed stuff here
    }
    
}
