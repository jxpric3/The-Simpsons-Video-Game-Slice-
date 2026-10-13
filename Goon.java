import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goon extends Actor
{
    int hp =2; 
    int hurtTimer = 0; 
    boolean facingRight = true; 
    /**
     * Act - do whatever the Goon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Goon()
    {
        GreenfootImage image = getImage();  
        setImage(image);    
    }
    
    public void act()
    {
        checkDestroy();
        if(getWorld() == null){
            return;
        }
        
        if(hurtTimer > 0){
            return; 
        }
        Homer h = getWorld().getObjects(Homer.class).get(0);
        
        if(h.getX() < getX())
        {
            setLocation(getX() -2, getY()); 
            if(facingRight){
                facingRight = false; 
                setImage("goon_walk_0.png"); 
                getImage().mirrorHorizontally();
                
            }
        }
        else if(h.getX() > getX()){
            setLocation(getX() + 2, getY());
            if(!facingRight){
                facingRight = true;
                setImage("goon_walk_0.png");
            }
        }
        
        if(h.getY() < getY()){
            setLocation(getX(), getY() -1); 
    
        }
        else if(h.getY() > getY()){
            setLocation(getX(), getY() +1); 
            
        }
        
        
        
    }
    //                       back to original spot when contacted with Homer     ---------
    public void checkDestroy()
    {
       if(hp > 0 && isTouching(BowlingBall.class)){
           removeTouching(BowlingBall.class);
           hp = hp -1; 
           if(hp ==0){
               setImage("goon_fall_2.png");
               hurtTimer = 20;
           }
           else{
               setImage("goon_hurt.png");
               hurtTimer = 15; 
           }
           if(!facingRight){
               getImage().mirrorHorizontally();
           }
       }
       
       if (hurtTimer > 0) {
          hurtTimer = hurtTimer - 1;
          if (hurtTimer == 0) {
              if (hp == 0) {
                  getWorld().removeObject(this);
              } else {
                  setImage("goon_walk_0.png");
                  if (!facingRight) {
                      getImage().mirrorHorizontally();
                  }
              }
          }
      }
    }
}
