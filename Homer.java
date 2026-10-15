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
    private int Lives = 5;
    private int Points = 0;
    boolean hitDebounce = false;
    int hitDebounceTick = 0;
    int elaspedTime = 0;
    boolean facingRight = true; 
    int throwCooldown = 0; 
    
    
    
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
        movement();
        showStatus();
        hitObjects();
        testEndGame();
        
        if(throwCooldown > 0)
        {
            throwCooldown -= 1; 
            if(throwCooldown == 0)
            {
                setImage("homer_stand.png");
                if(!facingRight){
                    getImage().mirrorHorizontally();
                }
            }
        }
       
    }
    
    public boolean movement() {
        boolean moving = false;
        
        if (Greenfoot.isKeyDown("right") && getX() < 750) {
            setLocation(getX() + speed, getY());
            moving = true;
            if(!facingRight){
                facingRight = true; 
                setImage("homer_stand.png");
            }
                
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
            moving = true;
            if(facingRight == true){
                facingRight = false; 
                setImage("homer_stand.png"); 
                getImage().mirrorHorizontally(); 
            }
        }
        if (Greenfoot.isKeyDown("up") && getY() > 250) {
            setLocation(getX(), getY()-2);
        }
        if (Greenfoot.isKeyDown("down") && getY() < 440) {
            setLocation(getX(), getY() + 2);
        }
        
        if (Greenfoot.isKeyDown("space") && throwCooldown == 0) {
            int ballX; 
            if(facingRight){
                ballX = getX() +40; 
                
            }
            else{
                ballX = getX() -40; 
            }
            getWorld().addObject(new BowlingBall(facingRight), ballX, getY() - 10); 
            throwCooldown = 20; 
            
            if(facingRight == true)
            {
                setImage("homer_throw_1.png");
            }
            else
            {
                setImage("homer_throw_1.png");
                getImage().mirrorHorizontally(); 
            }
        }

       
        return moving;
    }
    
    
    public void showStatus()
    {
        
    }
    
    public void loseLife()
    {
        Lives = Lives - 1;
    }
    
    public void gainHealth() {
        if (Lives >= 5) {
            GreenfootSound sound = new GreenfootSound("nom.mp3");
            sound.setVolume(75);
            sound.play();
        } else {
            GreenfootSound heal = new GreenfootSound("677571__fejluh__life-restored.wav");
            heal.setVolume(75);
            heal.play();
        }
        
        Lives = Math.min(5,Lives + 1);
    } 
    
    public int getLives()
    {
        return Lives;
    }
    
    public void hitObjects()
    {
        Actor goon = getOneIntersectingObject(Goon.class);
        if(goon != null){
            if(!hitDebounce){
                hitDebounce = true; 
                hitDebounceTick = elaspedTime +180; 
                loseLife();
                Greenfoot.playSound("homer_doh.wav"); 
                
            }
            if(goon.getX() > getX()){
                goon.setLocation(goon.getX() + 40, goon.getY());
                }
            else{
                goon.setLocation(goon.getX() - 40, goon.getY()); 
            }
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
