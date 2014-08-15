package sdljava.example.spaceinvaders;

import java.io.IOException;

import java.io.IOException;
import java.io.File;
import java.util.List;

import org.gljava.opengl.GL;
/**
 * The player fired missle
 *
 * @author  Ivan Z. Ganza
 * @version $Id: AlienShip.java,v 1.2 2005/03/13 23:18:54 ivan_ganza Exp $
 */
public class AlienShip extends GameEntity {

    final static int BOTTOM_BORDER = 20;
    final static int RIGHT_BORDER  = 790;
    final static int LEFT_BORDER   = 10;

    /** direction to travel, left-to-right is the standard direction.  reversed meants right-to-left */
    static boolean directionReversed = false;
    /** how far down the ship has moved */
    static int     yOffset = 0;
    /** if the ship is alive */
    boolean dead = false;
    /** how many frames to wait before processing the next frame (controls the speed of the ship) */
    int frameWait    = 0;
    /** how many have elapsed since last the ship moved */
    int waitedFrames = 0;

    public AlienShip(GL gl, String texturePath) throws IOException {
	super(gl, texturePath);
    }

    public void draw(GL gl) {
	sprite.draw(gl, x, y+yOffset);
    }

    public boolean nextFrame() {
	return dead ? true : false;
    }

    public void collidedWith(GameEntity other) {
	if (other instanceof PlayerMissle) {
	    PlayerMissle missle = (PlayerMissle)other;

	    missle.setCollided(true);
	    dead = true;
	}
    }

    public void move() {
	if (waitedFrames < frameWait) {
	    waitedFrames += 1;
	    return;
	}
	waitedFrames = 0;

	if (x +getWidth() >= RIGHT_BORDER) {
	    directionReversed = true;
	    yOffset += 4;
	}
	else if(x <= LEFT_BORDER) {
	    directionReversed = false;
	    yOffset += 4;
	}

	if (directionReversed) {
	    x -= 1;
	}else {
	    x += 1;
	}
    }
}