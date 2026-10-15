import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Homer homer;
    GreenfootImage donut = new GreenfootImage("donut.png"); 
    GreenfootImage emptyDonut;
    
    public HealthBar(Homer h)
    {
        homer = h; 
        donut.scale(24,24); 
        emptyDonut = new GreenfootImage(donut); 
        emptyDonut.setTransparency(70); 
        setImage(new GreenfootImage(130,24)); 
        
    }
    
    public void act()
    {
       GreenfootImage img = getImage(); 
       img.clear(); 
       for(int i =0; i < 5; i++){
           if(i < homer.getLives()){
            img.drawImage(donut, i *26, 0);   
           }
           else{
               img.drawImage(emptyDonut, i * 26, 0); 
           }
       }
           
}
}
