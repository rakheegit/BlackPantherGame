import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HeroWithSword here.
 * 
 * @author (Saranya) 
 * @version (a version number or a date)
 */
public class HeroWithSword extends Hero implements ISubject
{
    IObserver observer;
    boolean isFighterMoved;
    int weaponCount;
    
    public HeroWithSword() {
        isFighterMoved = false;
        weaponCount = 0;
    }
    
    /**
     * Act - do whatever the HeroWithSword wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    } 
    
    
    @Override
    public void collectWeapons() {
       // EnemyObject enemyObject = (EnemyObject)getOneIntersectingObject(EnemyObject.class);
        if(isTouching(EnemyObject.class))
        {
            MyWorld myWorld = (MyWorld)getWorld();
            weaponCount += 1;
        }
        //if(enemyObject != null){
        //   weaponCount++;
        //}
    }
    
    @Override
    public void displayWeaponCount() {
        getWorld().showText("WeaponCount: " + weaponCount, 100, 35);
    }
    
    @Override
    public void notifyObservers(int points){
        ((ScoreBoard)observer).addScore(points);
    }
    
    @Override
    public void registerObserver(IObserver o){
        observer = o;
    }
}