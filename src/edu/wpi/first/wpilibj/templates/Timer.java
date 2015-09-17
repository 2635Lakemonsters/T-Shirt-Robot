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
public class Timer
{

    public int tick = 0;

    public void tick()
    {
        ++tick;
    }

    public int getTick()
    {
        return tick;
    }

    public void reset()
    {
        tick = 0;
    }
}
