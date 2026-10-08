import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Donut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Donut extends Actor
{
    private int fallSpeed = 1;
    private boolean onGround = false;
    private int groundY = 350;  

    public Donut() {
        GreenfootImage img = getImage();
        setImage(img);
    }    
    
    public void act()
    {
        if (!onGround) {
            setLocation(getX(), getY() + fallSpeed);

            if (getY() >= groundY) {
                setLocation(getX(), groundY);
                onGround = true;
            }
        }

        // Check collision with Donker
        Homer d = (Homer) getOneIntersectingObject(Homer.class);
        if (d != null) {
            d.gainHealth();
            getWorld().removeObject(this);
        }
    }
}
