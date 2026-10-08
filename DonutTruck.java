import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DonutTruck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DonutTruck extends Actor
{
    /**
     * Act - do whatever the DonutTruck wants to do. Th is method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean hasDropped = false;

    public DonutTruck()
    {
        GreenfootImage image = getImage();  
        image.scale(50, 50);
        setImage(image);    
    }
    
    public void act()
    {
        move(5); // move to the right
        // Drop the health pack at a specific x position or condition
        if (!hasDropped && getX() > 300) {  // you can adjust 300
            dropHealthPack();
            hasDropped = true;
        }
        int rightEdge = getX() + getImage().getWidth() / 2;// Remove the DonutTruck if it leaves the screen
        if (rightEdge > getWorld().getWidth()) {
            getWorld().removeObject(this);
        }
    }
    
    private void dropHealthPack() {
        Donut pack = new Donut();
        getWorld().addObject(pack, getX(), getY() + 20);
    }
    
}

