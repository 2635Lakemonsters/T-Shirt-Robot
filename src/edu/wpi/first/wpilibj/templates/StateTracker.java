/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Siren
 */
public class StateTracker 
{
    double lastState = 0;
    
    public StateTracker()
    {
        
    }
    
    public boolean checkState(double joystick)
    {
        if(joystick < .3 || joystick > -.3)
        {
            if(lastState != joystick)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
       
    }
}
