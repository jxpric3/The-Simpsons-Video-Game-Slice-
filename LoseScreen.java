import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoseScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoseScreen extends World
{
    public LoseScreen()
    {
        super(800, 512, 1);
        showText("YOU LOST!", getWidth()/2, getHeight()/2 - 20);
        showText("Press [SPACE] to Restart", getWidth()/2, getHeight()/2 + 20);
        GreenfootSound sound = new GreenfootSound("violin.mp3");
        sound.setVolume(50);
        sound.play();
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new Stage1());
        }
    }
}
