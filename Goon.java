import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goon extends Actor
{
    /**
     * Act - do whatever the Goon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Goon()
    {
        GreenfootImage image = getImage();  
        setImage(image);    
    }
    
    public void act()
    {
        checkDestroy();
        move(-4);
        
    }
    //                       back to original spot when contacted with Homer     ---------
    public void checkDestroy()
    {
        if (isTouching (Homer.class) )
        {
            getWorld().removeObject(this);
            
        }
        else if (getX() < 5)
        {
            getWorld().removeObject(this);
        }
    }
}
