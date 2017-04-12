/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Matthew
 */
public class LiftController
{
    SpeedController motor;
    public LiftController(SpeedController liftMotor)
    {
        motor = liftMotor;
    }
    
    public void run()
    {
        
    }
}
