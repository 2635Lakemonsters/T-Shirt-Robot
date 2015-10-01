
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author localuser
 */
public class ArcadeDrive extends RobotDrive
{

    /**
     *
     */
    public ArcadeDrive(SpeedController leftMotor1, SpeedController leftMotor2, SpeedController rightMotor1, SpeedController rightMotor2)
    {
        super(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
        
        /**
         * If problems continue, uncomment line below.
         */
        //setSafetyEnabled(false);
    }

    public void drive(double yAxis, double rotation)
    {
        arcadeDrive(yAxis, rotation);
    }
}
