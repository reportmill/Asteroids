package greenfoot;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import snap.studio.*;
import java.util.List;

/**
 * A custom class.
 */
public class Actor extends SnapActor {
    
/**
 * Move.
 */
public void move(int aValue)  { moveBy(aValue); }

/**
 * Turn.
 */
public void turn(int aDeg)  { turnBy(aDeg); }

/**
 * Set Location.
 */
public void setLocation(int anX, int aY)  { setXY(anX, aY); }

/**
 * Set Location.
 */
public void setLocation(double anX, double aY)  { setLocation((int)anX, (int)aY); }

/**
 * Returns the rotation.
 */
public int getRotation()  { int r = ((int)Math.round(getRotate()))%360; return r>=0? r : (r + 360); }

/**
 * Sets the rotation.
 */
public void setRotation(int aRotation)  { setRotate(aRotation); }

/**
 * Returns the greenfoot image.
 */
public GreenfootImage getImage()  { return (GreenfootImage)super.getImage(); }

/**
 * Returns the greenfoot image.
 */
public void setImage(SnapImage anImage)
{
    super.setImage(anImage instanceof GreenfootImage? anImage : new GreenfootImage(anImage));
}

/**
 * Sets an image by name.
 */
public void setImage(String aName)  { setImage(new GreenfootImage(aName)); }

/**
 * Create the greenfoot image.
 */
protected GreenfootImage createImage()
{
    String iname = Greenfoot.getProperty("class." + getClass().getSimpleName() + ".image");
    if(iname==null) iname = getClass().getSimpleName() + ".png";
    return new GreenfootImage(iname);
}

/**
 * Returns the world.
 */
public World getWorld()  { return (World)getScene(); }

/**
 * Returns on intersecting Actor.
 */
protected List getIntersectingObjects(Class aClass)  { return getIntersectingActors(aClass); }

/**
 * Returns peer actors at given offset from this actor's center.
 */
protected List getObjectsAtOffset(int anX, int aY, Class aClass)
{
    Point2D point = localToParent(anX + getOffsetX(), aY + getOffsetY());
    return getParent().getActorsAt(point.getX(), point.getY(), aClass);
}
/**
 * Returns actors in given range.
 */
protected List getObjectsInRange(int aR, Class aClass)  { return getActorsInRange(aR, aClass); }

/**
 * Returns on intersecting Actor.
 */
protected Actor getOneIntersectingObject(Class aClass)  { return (Actor)getIntersectingActor(aClass); }

/**
 * Returns peer actor at given offset from this actor's center.
 */
protected Actor getOneObjectAtOffset(int anX, int aY, Class aClass)
{
    Point2D point = localToParent(anX + getOffsetX(), aY + getOffsetY());
    return (Actor)getParent().getActorAt(point.getX(), point.getY(), aClass);
}

/**
 * Notification for when actor is added to a world.
 */
protected void addedToWorld(World aWorld)  { }

}