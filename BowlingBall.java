import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BowlingBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BowlingBall extends Actor 
{
    int dx;
    
    public BowlingBall(boolean right) 
    {
        if(right){
            dx = 8; 
        }
        else
        {
            dx = -8; 
        }
    }
    /**
     * Act - do whatever the BowlingBall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX() + dx, getY()); 
        if(isAtEdge())
        {
            getWorld().removeObject(this); 
            return; 
        }
    }
}
