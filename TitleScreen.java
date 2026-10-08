import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    public TitleScreen()
    {
        super(800, 512, 1);
        setBackground(new GreenfootImage("stage1_bg.png")); 
        
        showInstructions();
    }

    private void showInstructions()
    {
        showText("  THE SIMPSONS: SPRINGFIELD STREET  ", getWidth()/2, 50);
        showText("Goal: Defeat Smithers without losing all 3 lives!", getWidth()/2, 90);
        showText("Use ARROW KEYS to move:", getWidth()/2, 130);
        showText("↑ Jump   ← Move Left   → Move Right", getWidth()/2, 150);
        showText("Avoid: Goons and Smithers' bombs", getWidth()/2, 180);
        showText("Touching some of them makes you lose lives!", getWidth()/2, 200);
        showText("Press [SPACE] to Start", getWidth()/2, 240);
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new Stage1());
        }
    }
}
