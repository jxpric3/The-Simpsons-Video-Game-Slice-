import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HurtBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HurtBox extends Actor
{
    /**
     * Act - do whatever the HurtBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int e = 0;
    public void act()
    {
        getImage().setTransparency(0);
        if (e >= 1) {
            getWorld().removeObject(this);
            return;
        }
        e = e + 1;
    }
}
