import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Smithers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Smithers extends Actor
{
    /**
     * Act - do whatever the Smithers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    boolean horz = true;
    boolean vert = true;
    boolean invulnerable = false;
    boolean canBeHurt = true;
    boolean spamAttack = false;
    boolean soundPlayed = false;
    boolean activateDeath = false;
    int healTime = 0;
    int hurtTime = 0;
    int elaspedTime = 0;
    int health = 100;
    int horzSpeed = 3;
    int vertSpeed = 1;
    
    
    private int goonSpawnTime = 120;
    private int bombSpawnTime = 350;
    public void act()
    {
        if (!activateDeath) {
            idle();
            spawnObstacles();
            checkHit();
        } else {
            deathScene();
        }
        
        elaspedTime = elaspedTime + 1;
    }
    
    public void spawnObstacles() {
        if (elaspedTime > goonSpawnTime) {
            Goon s = new Goon();
            getWorld().addObject(s, 600, 250);
            if (spamAttack) {
                goonSpawnTime = elaspedTime + 240;
            } else {
                goonSpawnTime = elaspedTime + Greenfoot.getRandomNumber(300) + 120;
            }
            
         }
        if (elaspedTime > bombSpawnTime && elaspedTime != 0) {
            Bomb s = new Bomb();
            Homer homer = getWorld().getObjects(Homer.class).get(0);
            getWorld().addObject(s, homer.getX(), homer.getY());
            if (spamAttack) {
                bombSpawnTime = elaspedTime + 60;
            } else {
                bombSpawnTime = elaspedTime + Greenfoot.getRandomNumber(350) + 120;
            }
            
         }
    }
    
    public void idle() {
        getWorld().showText("Smithers HP: " + health,150,50);
        if (horz) {
            setLocation(getX() - horzSpeed, getY());
            if (getX() <= 100) {
                horz = !horz;
            }
        } else {
            setLocation(getX() + horzSpeed, getY());
            if (getX() > getWorld().getWidth() - 100) {
                horz = !horz;
            }
        }
        
        if (vert) {
            setLocation(getX(), getY() + vertSpeed);
            if (getY() >= 93) {
                vert = !vert;
            }
        } else {
            setLocation(getX(), getY() - vertSpeed);
            if (getY() < 40) {
                vert = !vert;
            }
        }
        
        if (health <= 30) {
            if (!soundPlayed) {         
                Greenfoot.playSound("204912__noxdl__didgeridoo-monster-roar.mp3");
                soundPlayed = true;     
            } 
            horzSpeed = 7;
            vertSpeed = 3;
            if (elaspedTime % (25 * 60) == 0) {
                spamAttack = true;
                invulnerable = true;
            }
            if (elaspedTime % (28 * 60) == 0) {
                spamAttack = false;
                invulnerable = false;
            }
            if (elaspedTime > healTime || healTime == 0) {
                if (healTime != 0 && !invulnerable && canBeHurt) {
                    health = Math.min(health + 1, 30);
                }
                healTime = elaspedTime + 120;
            }
        }
        else if (health <= 60) {
            if (!soundPlayed) {         
                Greenfoot.playSound("204912__noxdl__didgeridoo-monster-roar.mp3");
                soundPlayed = true;     
            } 
            horzSpeed = 6;
            vertSpeed = 2;
            if (elaspedTime % (35 * 60) == 0) {
                spamAttack = true;
                invulnerable = true;
            }
            if (elaspedTime % (38 * 60) == 0) {
                spamAttack = false;
                invulnerable = false;
            }
        }
        if (!canBeHurt) {
            if (elaspedTime % 60 == 0) {
                getImage().setTransparency(255);
            }
            else if (elaspedTime % 30 == 0) {
                getImage().setTransparency(150);
            }
        } else {
                getImage().setTransparency(255);
        }
        
        
    }
    
    public void checkHit() {
        if (isTouching (Homer.class) && canBeHurt && !invulnerable)
        {
            canBeHurt = false;
            Greenfoot.playSound("enemy_scream.wav");
            if (health <= 30) {
                health = health - 5;
            }
            else {
                health = health - 10;
            }
            hurtTime = (5 * 60);
            
            if (health <= 60 && hurtTime == (5 * 60)) {
                setImage("smithers_hurt_0.png");
            }
        }
        if (!canBeHurt) {
            hurtTime = hurtTime - 1;
        }
        if (hurtTime <= 0) {
            canBeHurt = true;
        }
        if (health <= 0) {
            activateDeath = true;
        }
    }
    
    int deathSceneLength = 0;
    int transp = 255;
    Finisher glove = new Finisher();
    GreenfootSound sound2 = new GreenfootSound("Snap.mp3");
    
    GreenfootSound sound3 = new GreenfootSound("IllusionFade.mp3");
    public void deathScene() {
        if (deathSceneLength == 0) {
            deathSceneLength = elaspedTime + (4*60);
            Stage1 world = (Stage1)getWorld();
            world.stopMusic();
            GreenfootSound sound = new GreenfootSound("inevitable.wav");
            sound.setVolume(75);
            sound.play();
            Homer homer = (Homer) getWorld().getObjects(Homer.class).get(0);
            if (homer != null) {
                homer.setLocation(world.getWidth() / 2, world.getHeight() - 30);
                world.addObject(glove, homer.getX() + 62, homer.getY() - 22);
            }
        }
        if (elaspedTime >= deathSceneLength) {
            if (elaspedTime == deathSceneLength) {
                Homer homer = (Homer) getWorld().getObjects(Homer.class).get(0);
                if (homer != null) {
                    glove.setImage("boom_0.png");
                }
                sound2.setVolume(50);
                sound2.play();
                sound3.play();
            }
            getImage().setTransparency(transp);
            transp = transp - 1;
        }
        if (transp <= 0) {
            sleepFor(240);
            Stage1 world = (Stage1)getWorld();
            Greenfoot.setWorld(new WinScreen());
        }
    }
}
