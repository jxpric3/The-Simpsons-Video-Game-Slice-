import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int elaspedTime = 0;
    
    boolean blowingUp = false;
    boolean soundPlayed = false;
    public void act()
    {
        if (blowingUp) {
            getWorld().removeObject(this);
            return;
        }
        if (elaspedTime < 90) { // 1.5 seconds
            flashWarning();
        } else {
            sendRocket();
        }
        elaspedTime = elaspedTime + 1;
        
    }
    
    public void flashWarning() {
        if (!soundPlayed) {         // check if sound hasn't played
            Greenfoot.playSound("warning.wav");
            soundPlayed = true;     // stops it from playing again
        }  
        if (elaspedTime % 15 == 0) {
            getImage().setTransparency(255);
        } else if (elaspedTime % 25 == 0) {
            getImage().setTransparency(150);
        }
    }
    
    public void sendRocket() {
        if (elaspedTime == 90) { // first frame
            setImage("bomb_0.png");
            setLocation(getX(), 0);
            turn(90);
        }
        else {
            move(5);
            if (getY() > getWorld().getHeight() - 5) {
                setRotation(0);
                setLocation(getX(), getY() - 30);
                setImage("boom_0.png");
                Greenfoot.playSound("explode.wav");
                sleepFor(20);
                blowingUp = true;
                HurtBox hitbox = new HurtBox();
                getWorld().addObject(hitbox, getX(), getY());
            }
        }
    }
}
