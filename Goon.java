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
    private int speed = 3;
    public Goon()
    {
        GreenfootImage image = getImage();  
        setImage(image); 
    }
    
    public void act()
    {
        moveGoon();
        checkDestroy();
    }
    
    private void moveGoon()
    {
        setLocation(getX() + speed, getY());

        // Bounce off the right wall
        if (getX() >= getWorld().getWidth() - 1) {
            speed = -Math.abs(speed);
            getImage().mirrorHorizontally();
        }

        // Bounce off the left wall
        else if (getX() <= 0) {
            speed = Math.abs(speed);
            getImage().mirrorHorizontally();
        }
    }
    //                       back to original spot when contacted with Homer     ---------
    public void checkDestroy()
    {
        if (isTouching (Homer.class) )
        {
            getWorld().removeObject(this);
        }
    }
}
