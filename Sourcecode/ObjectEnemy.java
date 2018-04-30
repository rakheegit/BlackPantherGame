  import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fighter here.
 * 
 * @author (Manisha) 
 * @version (a version number or a date)
 */
public class ObjectEnemy extends Players implements ISubject
{
    IObserver observer;
    boolean isFighterMoved;
    boolean isHealtScore;
    GifImage hero = new GifImage("panthergif.gif");
    public ObjectEnemy(){
        isHealtScore = true;
        isFighterMoved = false;
    }
   
    /**
     * Act - do whatever the Fighter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        String worldname = getWorld().getClass().getName();
        if (worldname == "Level0")
        setImage(hero.getCurrentImage());
      
       checkCollision();
        if (Greenfoot.mousePressed(this) && !isFighterMoved)
        {
            isFighterMoved = true;
        }
        //dragging mouse
        if ((Greenfoot.mouseDragged(this)) && isFighterMoved)
        {
            MouseInfo mi = Greenfoot.getMouseInfo();
            setLocation(mi.getX(), mi.getY());
        
        }
        // check for collision after moving object 
        if (Greenfoot.mouseDragEnded(this) && isFighterMoved)
        {
            isFighterMoved = false;
            checkCollision();
        }
       
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - 7);
            isFighterMoved = true;
            checkCollision();
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + 7);
            isFighterMoved = true;
            checkCollision();
        }
        if (Greenfoot.isKeyDown("left")) {
            move(-7);
            isFighterMoved = true;
            checkCollision(); 
        }
        if (Greenfoot.isKeyDown("right")) {
            move(7);
            isFighterMoved = true;
            checkCollision();
        }
     
    }   
    
    private void checkCollision()
    {
        //Rocket rocket = (Rocket)getOneIntersectingObject(Rocket.class);
        if(isTouching(Rocket.class))
        {
            removeTouching(Rocket.class);
            Level0 level0 = (Level0)getWorld(); 
            isHealtScore = true;
            notifyObservers(20);
        }
        else if(isTouching(Fire.class))
        {
            removeTouching(Fire.class);
            Level0 level0 = (Level0)getWorld();
            isHealtScore = true;
            notifyObservers(-20);
        }
        else if(isTouching(Sword.class))
        {
            removeTouching(Sword.class);
            Level0 level0 = (Level0)getWorld();
            isHealtScore = false;
            notifyObservers(20);
        }
    }
    
    public void notifyObservers(int points){
        
        
        if (isHealtScore) {
             ((HealthScore)observer).addHealthScore(points);

        } else {
            ((HealthScore)observer).addSwordScore(points);

        }
    
        
    }
    
    public void registerObserver(IObserver o){ 
        observer = o;
    } 
   

}

