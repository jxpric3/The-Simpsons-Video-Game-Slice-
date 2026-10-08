import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinScreen extends World
{
    public WinScreen()
    {
        super(800, 512, 1);
        showText("YOU WON!", getWidth()/2, getHeight()/2 - 20);
        showText("Press [SPACE] to Play Again", getWidth()/2, getHeight()/2 + 20);
        GreenfootSound sound = new GreenfootSound("victory.mp3");
        sound.setVolume(50);
        sound.play();
    }

    public void act()
    {
        showText("YOU WON!", getWidth()/2, getHeight()/2 - 20);
        showText("Press [SPACE] to Play Again", getWidth()/2, getHeight()/2 + 20);
        WinDonut a = new WinDonut();
        addObject(a, Greenfoot.getRandomNumber(getWidth()),Greenfoot.getRandomNumber(getHeight()) );
        WinHomer b = new WinHomer();
        addObject(b, Greenfoot.getRandomNumber(getWidth()),Greenfoot.getRandomNumber(getHeight()) );
        WinBall c = new WinBall();
        addObject(c, Greenfoot.getRandomNumber(getWidth()),Greenfoot.getRandomNumber(getHeight()) );
        if (Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new Stage1());
        }
    }
}
