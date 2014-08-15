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
 * @version $Id: PlayerShip.java,v 1.2 2005/04/19 01:45:05 ivan_ganza Exp $
 */
public class PlayerShip extends GameEntity {

    final static int TOP_BORDER = 20;

    public PlayerShip(GL gl) throws IOException {
	super(gl, "testdata" + File.separator + "space_invaders" + File.separator + "player_ship.png");
    }

    public boolean nextFrame() {
	return false;
	// do nothing
    }

    public void collidedWith(GameEntity other) {
    }
}