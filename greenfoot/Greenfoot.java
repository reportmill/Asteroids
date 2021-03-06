package greenfoot;
import com.reportmill.base.*;
import java.util.*;
import snap.javafx.*;
import snap.web.*;

/**
 * A custom class.
 */
public class Greenfoot extends Object {
    
    // The current world
    static World      _world;
    
    // The current speed
    static int        _speed = 50, _frate = 62;
    
    // The last key pressed (as name)
    static String     _lastKey;
    
    // The mouse location
    static double     _mouseX, _mouseY;
    
    // The mouse info
    static MouseInfo  _mouseInfo = new MouseInfo();

    // The project properties
    static Map <String,String>  _props;

/**
 * Get the most recently pressed key, since the last time this method was called.
 */
public static String getKey()  { String lkey = _lastKey; _lastKey = null; return lkey; }

/**
 * Returns a random number.
 */
public static int getRandomNumber(int aNum)  { return RMMath.randomInt()%(aNum); }

/**
 * Returns a random number.
 */
public static int getRandomNumber(double aNum)  { return getRandomNumber((int)aNum); }

/**
 * Returns whether key is down.
 */
public static boolean isKeyDown(String aName)  { return getWorld().isKeyDown(aName); }

/**
 * Plays a sound.
 */
public static void playSound(String aName)  { getWorld().playSound(aName); }

/**
 * Initialize Greenfoot.
 */
static void initGreenfoot()
{
    int speed = getIntProperty("simulation.speed");
    setSpeed(speed>0? speed : _speed);
}

/**
 * Returns a world.
 */
public static World getWorld()
{
    WebBrowser browser = _world!=null? _world.getBrowser() : null;
    WebPage page = browser!=null? browser.getPage() : null;
    if(page!=null && page.getPeer() instanceof World) _world = (World)page.getPeer();
    return _world;
}

/**
 * Sets a world.
 */
public static void setWorld(World aWorld)
{
    // Get old world - if there, stop it
    World oworld = _world; if(oworld!=null) oworld.stop();
    
    // Set world
    _world = aWorld;
    
    // If no props, init greenfoot
    if(_props==null) initGreenfoot();
    
    // Set new world in browser
    WebBrowser browser = oworld!=null? oworld.getBrowser() : null; if(browser==null) return;
    browser.setURL(_world.getURL());
}

public static void start()  { if(_world!=null) getWorld().start(); }

/**
 * Stops Greenfoot from playing.
 */
public static void stop()  { getWorld().stop(); }

/**
 * Delays the execution by given number of time steps.
 */
public static void delay(int aValue)  { }

/**
 * Returns the speed.
 */
public int getSpeed()  { return _speed; }

/**
 * Sets the speed of greenfoot playback.
 */
public static void setSpeed(int aValue)
{
    long delay = calculateDelay(aValue); _speed = aValue;
    _frate = (int)Math.round(1000000000d/delay);
    getWorld().setFrameRate(_frate);
}

/**
 * Returns the delay as a function of the speed.
 */
static long calculateDelay(int speed)
{
    // Make the speed into a delay
    long rawDelay = 100 - speed;
    long min = 30 * 1000L; // Delay at MAX_SIMULATION_SPEED - 1
    long max = 10000 * 1000L * 1000L; // Delay at slowest speed
    double a = Math.pow(max / (double) min, 1D / (100 - 1));
    long delay = 0; if (rawDelay > 0) delay = (long) (Math.pow(a, rawDelay - 1) * min);
    return delay;
}

/**
 * Returns a framerate.
 */
static int getFrameRate()  { return _frate; }

/**
 * Returns the MouseInfo.
 */
public static MouseInfo getMouseInfo()  { return _mouseInfo; }

/**
 * Returns whether mouse was clicked on given actor/world.
 */
public static boolean mouseClicked(Object anObj)
{
   if(anObj==null) return getWorld()!=null? getWorld().isMouseClicked() : false;
   System.out.println("Mouse Clicked not supported"); return false;
}
    
/**
 * Returns the project properties map.
 */
static Map <String,String> getProps()  { return _props!=null? _props : (_props=createProps()); }

/**
 * Returns the project properties map.
 */
static Map <String,String> createProps()
{
    // Create map
    Map props = new HashMap();
    
    // Get project file
    WebSite site = _world.getSite();
    WebFile file = site.getFile("/project.greenfoot"); if(file==null) return props;
        
    // Get file text
    String text = file.getText();
    if(text!=null && text.length()>0) {
        String lines[] = text.split("\\n");
        for(String line : lines) {
            String parts[] = line.split("=");
            if(parts.length>1)
                props.put(parts[0], parts[1]);
        }
    }
    
    // Return properties
    return props;
}

/**
 * Returns a property for a given key.
 */
static String getProperty(String aKey)  { return getProps().get(aKey); }

/**
 * Returns an int property.
 */
static int getIntProperty(String aKey)  { return RMUtils.intValue(getProperty(aKey)); }

}