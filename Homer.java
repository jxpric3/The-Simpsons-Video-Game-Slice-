import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Homer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Homer extends Actor
{
    private int speed = 3;
    private int Lives = 3;
    private int Points = 0;
    int gravity = 1;
    int jumpPower = 8;
    boolean holdingJump = false;
    boolean falling = true;
    boolean hitDebounce = false;
    int hitDebounceTick = 0;
    int yVelocity = 0;
    int tickRate = 15; // If you want speeds slower than 1 pixel per frame.
    int elaspedTime = 0;
    int maxJumpDist = 100;
    int rotateAnimTickRate = 15;
    int movingRotation = 25;
    
    /**
     * Act - do whatever the Homer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Homer()
    {
        GreenfootImage image = getImage();  
        setImage(image);    
    }
    
    public void act()
    {
        elaspedTime += 1;
        if (movement() && !(holdingJump || falling)) {
            if (elaspedTime % rotateAnimTickRate == 0) {
                movingRotation = -movingRotation;
                setRotation(movingRotation);
            }
        } else if (holdingJump || falling){
            if (falling) {
                if (!(getRotation() % 360 == 0)) {
                    setRotation(getRotation() + 30);   
                }
            } else {
                setRotation(getRotation() + 30);
            }
        } else {
            setRotation(0);
        }
        gravity();
        showStatus();
        hitObjects();
        testEndGame();
    }
    
    public boolean movement() {
        boolean moving = false;
        if (Greenfoot.isKeyDown("right") && getX() < 450) {
            setLocation(getX() + speed, getY());
            moving = true;
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - Math.max(speed + 2, 1), getY());
            moving = true;
        }
        if (Greenfoot.isKeyDown("up")) {
            if (!falling) {
                holdingJump = true;
                setLocation(getX(), getY() - jumpPower);
                if (getY() < maxJumpDist    ) {
                    falling = true;
                }
            }
        }
        else if (holdingJump) {
            falling = true;
            holdingJump = false;
            yVelocity = 0;
        }
        return moving;
    }
    
    public void gravity() {
        if (!falling) {
            return;
        }
        setLocation(getX(), Math.min(getY() + yVelocity, 250));
        if (getY() == 250) {
            falling = false;
        }
        if (elaspedTime % tickRate != 0) {
            return;
        }
        yVelocity = Math.max(yVelocity - gravity, 5);
    }
    
    public void showStatus()
    {
        getWorld().showText("Lives: "+Lives,50,30);
    }
    
    public void loseLife()
    {
        Lives = Lives - 1;
    }
    
    public void gainHealth() {
        if (Lives >= 3) {
            GreenfootSound sound = new GreenfootSound("nom.mp3");
            sound.setVolume(75);
            sound.play();
        } else {
            GreenfootSound heal = new GreenfootSound("677571__fejluh__life-restored.wav");
            heal.setVolume(75);
            heal.play();
        }
        
        Lives = Math.min(3,Lives + 1);
    } 
    
    public void hitObjects()
    {
        if (isTouching (Goon.class) )
        {
            loseLife();
            Greenfoot.playSound("homer_doh.wav");
        }
        if (isTouching (HurtBox.class) && !hitDebounce )
        {
            hitDebounce = true;
            hitDebounceTick = elaspedTime + 180;
            loseLife();
            Greenfoot.playSound("homer_doh.wav");
        }
        if (elaspedTime > hitDebounceTick) {
            hitDebounce = false;
        }
    }
    
    public void testEndGame()
    {
        if (Lives<1)
        {
            Greenfoot.playSound("homer_scream.wav");
            Stage1 world = (Stage1)getWorld();
            world.stopMusic();
            Greenfoot.setWorld(new LoseScreen());
        }
    }
}
