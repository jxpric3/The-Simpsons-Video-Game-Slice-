import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stage1 extends World
{
     private static final int scrollSpeed = 1; 
     
     private GreenfootSound bgMusic = new GreenfootSound("651797__gis_sweden__background-music-loop-120bpm-220928.wav");
     private GreenfootImage scrollingImage; 
     private int scrollPosition = 0; 
     private int elaspedTime = 0;
     private int nextSpawn = Greenfoot.getRandomNumber(600) + 300; 
     private int spawnTimer = 0;
    private int wave = 0;
    private boolean bossOut = false;
    /**
     * Constructor for objects of class Stage1.
     * 
     */
    public Stage1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 512, 1); 
        scrollingImage = new GreenfootImage(getBackground());
   
        bgMusic.setVolume(70);
        bgMusic.playLoop();
        
        Homer d = new Homer();
        addObject(d, 80, 380);
        
        addObject(new HealthBar(d), 75, 22);
        
       
    }
    public void act() 
    { 
        checkWave(); 
        if(scrollPosition < 0) { 
             scrollPosition = scrollingImage.getWidth(); 
         }
         scrollPosition -= scrollSpeed; 
         paint(scrollPosition); 
         
        elaspedTime += 1;   
        
        spawnTimer++;
        if (spawnTimer >= nextSpawn) {
            spawnAmbulance();
            spawnTimer = 0;
            nextSpawn = Greenfoot.getRandomNumber(1000) + 600; 
        }
    }
    private void spawnAmbulance() {
        DonutTruck a = new DonutTruck();
        addObject(a, 0, 430); 
    }
    
    private void checkWave() {
          if (getObjects(Goon.class).isEmpty() && !bossOut) {
              wave++;

              if (wave == 1) {
                  spawnGoons(3);
              }
              else if (wave == 2) {
                  spawnGoons(5);
              }
              else if (wave == 3) {
                  spawnGoons(7);
              }
              else if (wave == 4) {
                  addObject(new Smithers(), 700, 340);
                  bossOut = true;
              }
          }

          showText("Wave " + wave, 700, 30);

          if (bossOut) {
              showText("BOSS", 700, 50);
          }
      }
      
      private void spawnGoons(int n) {
          for (int i = 0; i < n; i++) {
              int x = getWidth() - 1 - (i * 60);
              int y = 300 + Greenfoot.getRandomNumber(140)
                  + (i * 10);

              // Keep each Goon within the world's vertical bounds.
              y = Math.min(y, getHeight() - 1);

              addObject(new Goon(), x, y);
          }
      }
    /** 
      * Paint scrolling image at given position and make sure the rest of 
      * the background is also painted with the same image. 
      */
    private void paint(int position) 
    { 
        GreenfootImage bg = getBackground(); 
        bg.drawImage(scrollingImage, position, 0); 
        if(position > 0) { 
            bg.drawImage(scrollingImage, position - scrollingImage.getWidth(), 0); 
        } 
        else { 
            bg.drawImage(scrollingImage, position + scrollingImage.getWidth(), 0); 
        } 
    }    
    public void started()
    {
        bgMusic.setVolume(70);
        bgMusic.playLoop();  
    }
    public void stopped()
    {
        bgMusic.pause();     
    }
    public void stopMusic()
    {
        bgMusic.stop(); 
    }
}
