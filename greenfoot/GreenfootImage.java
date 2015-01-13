package greenfoot;
import javafx.application.Platform;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import snap.javafx.JFXUtils;
import snap.studio.*;
import java.util.*;

/**
 * A custom class.
 */
public class GreenfootImage extends SnapImage {
    
/**
 * Creates a new Greenfoot image for size.
 */
public GreenfootImage(int aWidth, int aHeight)  { super(aWidth, aHeight); }

/**
 * Creates a new greenfoot image for file name.
 */
public GreenfootImage(String aName)  { super(Greenfoot.getWorld().getClass(), aName); }

/**
 * Creates a new greenfoot image for greenfoot image.
 */
public GreenfootImage(SnapImage anImage)  { super(anImage.getImage()); }

/**
 * Creates a new greenfoot image for greenfoot image.
 */
public GreenfootImage(String aString, int aSize, java.awt.Color fg, java.awt.Color bg)
{
    super(aString, aSize, color(fg), color(bg));
}

/**
 * Sets the color.
 */
public void setColor(java.awt.Color aColor)  { Color c = color(aColor); setFill(c); setStroke(c); }

/**
 * Returns the AWT Font.
 */
public java.awt.Font getFontAWT()
{
    return new java.awt.Font(getFont().getName(), java.awt.Font.PLAIN, (int)getFont().getSize());
}

/** Sets the font. */
public void setFont(java.awt.Font aFont)  { setFont(new Font(aFont.getFontName(), aFont.getSize2D())); }

/** Fill image. */
public void fill()  { fillRect(0, 0, getWidth(), getHeight()); }

/** Fill Polygon (int). */
public void fillPolygon(int xPoints[], int yPoints[], int nPoints)
{
    double xp[] = new double[nPoints]; for(int i=0;i<nPoints;i++) xp[i] = xPoints[i];
    double yp[] = new double[nPoints]; for(int i=0;i<nPoints;i++) xp[i] = yPoints[i];
    fillPolygon(xp, yp, nPoints);
}

/** Draw line. */
public void drawLine(int x, int y, int w, int h)  { strokeLine(x, y, w, h); }

/** Draw rect. */
public void drawRect(int x, int y, int w, int h)  { strokeRect(x, y, w, h); }

/** Draw oval. */
public void drawOval(int x, int y, int w, int h)  { strokeOval(x, y, w, h); }

/** Draw Polygon (int). */
public void drawPolygon(int xPoints[], int yPoints[], int nPoints)
{
    double xp[] = new double[nPoints]; for(int i=0;i<nPoints;i++) xp[i] = xPoints[i];
    double yp[] = new double[nPoints]; for(int i=0;i<nPoints;i++) xp[i] = yPoints[i];
    strokePolygon(xp, yp, nPoints);
}

/** Draw string. */
public void drawString(String aString, int anX, int aY)  { fillText(aString, anX, aY); }

/** Scales the image. */
public void scale(int aW, int aH)  { setSize(aW, aH); }

/** Flips the image so that it points left instead of right. */
public void mirrorHorizontally()  { flipImageX(); }

/** Flips the image so that it point down instead of up. */
public void mirrorVertically()  { flipImageY(); }

/** Rotates image around center. */
public void rotate(int theDeg)  { rotateImage(theDeg%360); }

/** Returns the AWT color at given x/y. */
public java.awt.Color getColorAt(int anX, int aY)  { return color(getColor(anX, aY)); }

/** Sets the AWT color at given x/y. */
public void setColorAt(int aX, int aY, java.awt.Color aColor)  { setColor(aX, aY, color(aColor)); }

/** Returns the image opacity. */
public int getTransparency()  { return (int)Math.round(getOpacity()*255); }

/** Makes the image semi transparent. */
public void setTransparency(int aValue)  { setOpacity(aValue/255d); }

/** Returns a JavaFX color for awt color. */
static Color color(java.awt.Color c)  { return Color.rgb(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()/255d); }

/** Returns an AWT color for JavaFX color. */
static java.awt.Color color(Color c)
{ return new java.awt.Color((float)c.getRed(), (float)c.getGreen(), (float)c.getBlue(), (float)c.getOpacity()); }

}