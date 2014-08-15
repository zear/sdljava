package sdljava.example.spaceinvaders;

import java.io.IOException;
import java.io.File;
import java.util.List;

import org.gljava.opengl.GL;
import org.gljava.opengl.Sprite;
/**
 * The player fired missle
 *
 * @author  Ivan Z. Ganza
 * @version $Id: PlayerMissle.java,v 1.1 2005/02/18 03:05:00 ivan_ganza Exp $
 */
public class PlayerMissle extends GameEntity {

    final static int TOP_BORDER = 20;

    Sprite missleSprite;

    int frameWait    = 0;
    int waitedFrames = 1;

    boolean fired    = false;
    boolean collided = false;

    public PlayerMissle(GL gl) throws IOException {
	super(gl, "testdata" + File.separator + "space_invaders" + File.separator + "player_missle.png");

	dy = -20;
    }

    public boolean nextFrame() {
	if (collided) {
	    fired    = false;
	    collided = false;
	    
	    return true;
	}
	
	if (waitedFrames > frameWait) {
	    x += dx;
	    y += dy;
	    waitedFrames = 0;
	}
	waitedFrames += 1;

	if (y <= TOP_BORDER) {
	    fired = false;
	    return true;
	}

	return false;
    }

    public void collidedWith(GameEntity other) {
    }

    public void setCollided(boolean b) { this.collided = true ;}

    public void setFired(boolean f) { this.fired = f;}
    public boolean isFired() { return this.fired ;}
}