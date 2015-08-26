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
  public int time = 0;
  
  public void tick()
  {
      ++time;
  }
  
  public int getTime()
  {
      return time;
  }
  
  public void reset()
  {
      time = 0;
  }
}
