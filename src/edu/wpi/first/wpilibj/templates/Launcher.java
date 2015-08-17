/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *  Encapsulation for the Launcher. Call fire() to fire the cannon. If it refuses to fire,
 *  it is likely failing the checkYourPrivilege() safety check. 
 *   @author Siren
 */
public class Launcher 
{
 /**
 *   @param barrelMotor Pass the variable containing the barrel rotation motor
 *   @param solenoid Pass the variable containing the firing solenoid
 *   @param override PASS THIS FALSE UNLESS YOU LIKE THINGS KILLING PEOPLE.
 */
    public Launcher(SpeedController barrelMotor, Relay solenoid, boolean override)
    {
        
    }
    
    public void fire()
    {
        if(checkYourPrivilege())
        {
           //FIRE
           System.out.println("FIRE!");
        }
        else
        {
           System.out.println("Uh, check your privilege.");
        }
    }
    
    public boolean checkYourPrivilege()
    {
        boolean privileged;
        if(1 == 0)       
        {
            privileged = true;
        }
        else
        {
            privileged = false;
        }
        
        return privileged;
    }
    
}
