package sdljava.example.spaceinvaders;

import java.io.IOException;
import java.io.File;

import org.gljava.opengl.GL;
import org.gljava.opengl.Sprite;
/**
 * A GameEntity is something which may appear in the game.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: GameEntity.java,v 1.1 2005/02/18 03:05:00 ivan_ganza Exp $
 */
public abstract class GameEntity {
    
    protected float  x = 0.0f;
    protected float  y = 0.0f;
    
    protected float  width = 0.0f;
    protected float height = 0.0f;

    protected float dx = 0.0f;
    protected float dy = 0.0f;

    protected Sprite sprite;

    public GameEntity(GL gl, String texturePath) throws IOException{
	sprite = new Sprite(gl, texturePath);
	height = sprite.getHeight();
	width  = sprite.getWidth();
    }

    public void draw(GL gl) {
	sprite.draw(gl, x, y);
    }

    public abstract boolean nextFrame();

    public boolean willCollide(GameEntity other) {
	return x >= other.getX() &&
	       x <= other.getX() + other.getWidth() &&
	       y >= other.getY() &&
	       y <= other.getY() + other.getHeight()
	    ? true : false;
    }

    public abstract void collidedWith(GameEntity other);

    
    /**
     * Gets the value of x
     *
     * @return the value of x
     */
    public float getX()  {
	return this.x;
    }

    /**
     * Sets the value of x
     *
     * @param argX Value to assign to this.x
     */
    public void setX(float argX) {
	this.x = argX;
    }

    /**
     * Gets the value of y
     *
     * @return the value of y
     */
    public float getY()  {
	return this.y;
    }

    /**
     * Sets the value of y
     *
     * @param argY Value to assign to this.y
     */
    public void setY(float argY) {
	this.y = argY;
    }

    /**
     * Gets the value of dx
     *
     * @return the value of dx
     */
    public float getDx()  {
	return this.dx;
    }

    /**
     * Sets the value of dx
     *
     * @param argDx Value to assign to this.dx
     */
    public void setDx(float argDx) {
	this.dx = argDx;
    }

    /**
     * Gets the value of dy
     *
     * @return the value of dy
     */
    public float getDy()  {
	return this.dy;
    }

    /**
     * Sets the value of dy
     *
     * @param argDy Value to assign to this.dy
     */
    public void setDy(float argDy) {
	this.dy = argDy;
    }
    
    /**
     * Gets the value of width
     *
     * @return the value of width
     */
    public float getWidth()  {
	return this.width;
    }

    /**
     * Sets the value of width
     *
     * @param argWidth Value to assign to this.width
     */
    public void setWidth(float argWidth) {
	this.width = argWidth;
    }

    /**
     * Gets the value of height
     *
     * @return the value of height
     */
    public float getHeight()  {
	return this.height;
    }

    /**
     * Sets the value of height
     *
     * @param argHeight Value to assign to this.height
     */
    public void setHeight(float argHeight) {
	this.height = argHeight;
    }

}