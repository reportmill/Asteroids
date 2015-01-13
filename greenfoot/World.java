package greenfoot;
import com.reportmill.base.RMArrayUtils;
import java.util.Collection;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import snap.javafx.JFXEvent;
import snap.studio.*;

/**
 * A custom class.
 */
public class World extends SnapScene {
    
    // The Width/Height/CellSize
    int      _width, _height, _cellSize = 1;
    
/**
 * Creates a new world.
 */
public World(int aW, int aH, int aCellSize)  { this(aW, aH, aCellSize, false); }

/**
 * Creates a new world.
 */
public World(int aW, int aH, int aCellSize, boolean aValue)
{
    // Set sizing info
    _width = aW; _height = aH; _cellSize = aCellSize;
    
    // If first world, manually set it
    if(Greenfoot._world==null) Greenfoot.setWorld(this);
    
    // Set default FrameRate
    setFrameRate(Greenfoot.getFrameRate());
    
    // Set background image
    String iname = Greenfoot.getProperty("class." + getClass().getSimpleName() + ".image");
    if(iname!=null) setBackground(new GreenfootImage(iname));
    else setBackground(new GreenfootImage(aW*aCellSize, aH*aCellSize));
}

/**
 * Returns the cell size.
 */
public int getCellSize()  { return _cellSize; }

/**
 * Returns the background image.
 */
public GreenfootImage getBackground()  { return (GreenfootImage)getImage(); }

/**
 * Sets the greenfoot image.
 */
public void setBackground(SnapImage anImage)
{
    setImage(anImage instanceof GreenfootImage? anImage : new GreenfootImage(anImage));
}

/**
 * Sets the image to named image.
 */
public void setBackground(String aName)  { setBackground(new GreenfootImage(aName)); }

/**
 * Adds an object.
 */
public void addObject(Actor anActor, double anX, double aY)
{
    addActor(anActor, anX, aY);
    anActor.setLocation((int)anX, (int)aY);
    anActor.addedToWorld(this);
}

/**
 * Removes an Actor.
 */
public void removeObject(Actor anActor)  { removeActor(anActor); }

/**
 * Returns the actors of a given class.
 */
public List getObjects(Class aClass)  { return getActors(aClass); }

/**
 * Removes the objects of given class.
 */
public void removeObjects(Collection theActors)  { for(Object actor : theActors) removeObject((Actor)actor); }

/**
 * Override to reverse.
 */
public void setPaintOrder(Class ... theClasses)  { RMArrayUtils.reverse(theClasses); super.setPaintOrder(theClasses); }

/**
 * Override to Have PageAdded setWorld.
 */
public void respondUI(JFXEvent event)
{
    if(event.equals("PageAdded") && Greenfoot.getWorld()!=this) Greenfoot.setWorld(this);
    super.respondUI(event);
}

/**
 * Override to track Greenfoot.LastKey.
 */
protected void processKeyEvent(KeyEvent anEvent)
{
    super.processKeyEvent(anEvent);
    if(anEvent.getEventType()==KeyEvent.KEY_TYPED) Greenfoot._lastKey = anEvent.getCode().getName().toLowerCase();
}

/**
 * Override to track Greenfoot.MouseInfo.
 */
protected void processMouseEvent(MouseEvent anEvent)
{
    super.processMouseEvent(anEvent);
    Greenfoot._mouseX = anEvent.getX(); Greenfoot._mouseY = anEvent.getY();
}

}