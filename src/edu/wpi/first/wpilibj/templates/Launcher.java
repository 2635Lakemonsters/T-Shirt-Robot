/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Siren
 */
public class Launcher 
{
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
