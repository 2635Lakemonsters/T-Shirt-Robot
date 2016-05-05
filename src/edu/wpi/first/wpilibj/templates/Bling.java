package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;

/**
 *  Heavily based on Team 3574's LED system
 *  Adapted for use by 2635
 */
public class Bling 
{
    //TODO: Improve buttons bindings for manual control
    //ID's to send to arduino for each lighting scene. Used for reference
    public static final int rainbowFade = 0,
                            firing = 1,
                            marchRWB = 2;
    private I2C chat;
    private int previousScene = 0;
    private int currentScene = 0;
    private int defaultScene = 0;
    
    public Bling()
    {
        
        DigitalModule digiMod = DigitalModule.getInstance(1);
        chat = digiMod.getI2C(168);
        
        
        chat.setCompatabilityMode(true);
    }
    
    public void set(int scene)
    {
        previousScene = currentScene;
        currentScene = scene;
        //System.out.println("Setting scene to " + scene);
        byte[] msg = new byte[1];
        msg[0] = (byte) scene;

        chat.transaction(msg, 1, null, 0);
    }
    
    public void revert()
    {
        set(previousScene);
    }
    
    public void setDefault(int scene)
    {
        defaultScene = scene;
    }
    
    public void defaultScene()
    {
        set(defaultScene);
    }
                            
}
