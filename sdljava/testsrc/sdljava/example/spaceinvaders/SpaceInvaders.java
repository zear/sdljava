package sdljava.example.spaceinvaders;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import sdljava.SDLMain;
import sdljava.SDLException;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

import sdljava.event.SDLEvent;
import sdljava.event.SDLQuitEvent;
import sdljava.event.SDLKeyboardEvent;
import sdljava.event.SDLEventType;
import sdljava.event.SDLKey;

import org.gljava.opengl.GL;
import org.gljava.opengl.Sprite;
import org.gljava.opengl.Texture;
import org.gljava.opengl.TextureFactory;

/**
 * Main class of Space Invaders.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: SpaceInvaders.java,v 1.3 2005/09/18 01:04:10 ivan_ganza Exp $
 */
public class SpaceInvaders {

    public static final int FRAME_TICKS = (1000 / 29); // 30 frames/second

    SDLSurface   framebuffer;
    GL           gl;
    boolean      runGame = true;

    Sprite       starfield;
    GameEntity   playerShip;
    PlayerMissle playerMissle;
    
    List         entities   = new ArrayList();
    List         removeList = new ArrayList();

    public SpaceInvaders(String args[]) {
    }

    public void init() throws SDLException, IOException {
	initDisplay();
	initResources();

	SDLEvent.enableKeyRepeat(250, 30);
    }

    public void initDisplay() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_EVERYTHING);
	framebuffer = SDLVideo.setVideoMode(800, 600, 0, SDLVideo.SDL_OPENGL|SDLVideo.SDL_HWACCEL|SDLVideo.SDL_DOUBLEBUF);
	if (framebuffer.isHardwareAccelerated()) {
		System.out.println("Surface is Hardware Accelerated.");
	}
	else {
		System.out.println("Surface is NOT Harwdare Accelerated!");
	}
	
	gl = framebuffer.getGL();

	gl.glMatrixMode(gl.GL_PROJECTION);
	gl.glLoadIdentity();
	gl.glOrtho(0.0, (double) framebuffer.getWidth(), 0.0, (double) framebuffer.getHeight(), -1.0, 1.0);
	gl.glMatrixMode(gl.GL_MODELVIEW);
	gl.glLoadIdentity();
	gl.glViewport(0, 0, framebuffer.getWidth(), framebuffer.getHeight());

	// enable 2D textures since we're using them
	gl.glEnable(gl.GL_TEXTURE_2D);

	// we're using 2D graphics so no need to depth test
	gl.glDisable(gl.GL_DEPTH_TEST);

	gl.glMatrixMode(gl.GL_PROJECTION);
	gl.glLoadIdentity();

	gl.glOrtho(0, framebuffer.getWidth(), framebuffer.getHeight(), 0, -1, 1);

	gl.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);

	gl.glEnable(gl.GL_BLEND);
	gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void initResources() throws IOException {
	playerShip = new PlayerShip(gl);
	
	playerShip.setX(framebuffer.getWidth() / 2);
	playerShip.setY(framebuffer.getHeight() - (6 + playerShip.getHeight() * 2));

	playerMissle = new PlayerMissle(gl);

	// load the screen with invaders
	for (int i = 0; i < 15; i++) {
	    AlienShip ship = new AlienShip(gl, "testdata" + File.separator + "space_invaders" + File.separator + "small_alien.png");
	
	    ship.setX(i * ship.getWidth() + 10);
	    ship.setY(200);
	
	    entities.add(ship);
	}

	for (int i = 0; i < 15; i++) {
	    AlienShip ship = new AlienShip(gl, "testdata" + File.separator + "space_invaders" + File.separator + "small_alien.png");
	
	    ship.setX(i * ship.getWidth() + 10);
	    ship.setY(200 - (36 + 8));
	
	    entities.add(ship);
	}
	
	for (int i = 0; i < 15; i++) {
	    AlienShip ship = new AlienShip(gl, "testdata" + File.separator + "space_invaders" + File.separator + "medium_alien.png");
	
	    ship.setX(i * ship.getWidth() + 10);
	    ship.setY(200 - (36 + 8) * 2);
	
	    entities.add(ship);
	}
	
	for (int i = 0; i < 15; i++) {
	    AlienShip ship = new AlienShip(gl, "testdata" + File.separator + "space_invaders" + File.separator + "medium_alien.png");
	
	    ship.setX(i * ship.getWidth() + 10);
	    ship.setY(200 - (36 + 8) * 3);
	
	    entities.add(ship);
	}
	
	for (int i = 0; i < 15; i++) {
	    AlienShip ship = new AlienShip(gl, "testdata" + File.separator + "space_invaders" + File.separator + "large_alien.png");
	
	    ship.setX(i * ship.getWidth() + 10);
	    ship.setY(200 - (36 + 8) * 4);
	
	    entities.add(ship);
	}

	starfield = new Sprite(gl, "testdata" + File.separator + "space_invaders" + File.separator + "starfield.png");
    }

    public void run() {
	long lastnow = System.currentTimeMillis();
	
	while (runGame) {
	    try {
		long now = System.currentTimeMillis();
		if (now - lastnow >= FRAME_TICKS) {
		    nextFrame();
		    
		    drawScreen();

		    framebuffer.glSwapBuffers();
		    
		    lastnow = now;
		}
		processInput();
		
		//now = System.currentTimeMillis();
		//long next = FRAME_TICKS - (now - lastnow);
		//if(next < FRAME_TICKS) {
		//    Thread.currentThread().sleep(next/4);
		//}
		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public void nextFrame() {
	Iterator i = entities.iterator();
	while (i.hasNext()) {
	    GameEntity e = (GameEntity) i.next();
	    boolean remove = e.nextFrame();
	    if (remove) {
		removeList.add(e);
	    }
	}

	if (removeList.size() > 0) {
	    entities.removeAll(removeList);
	    removeList.clear();
	}

	moveAliens();

	checkCollisions();
    }

    public void moveAliens() {
	Iterator i = entities.iterator();

	while (i.hasNext()) {
	    GameEntity e = (GameEntity)i.next();
	    if (e instanceof AlienShip) {
		AlienShip alien = (AlienShip)e;

		alien.move();
	    }
	}
    }

    public void checkCollisions() {
	Iterator i = entities.iterator();
	while (i.hasNext()) {
	    Object o = i.next();
	    if(o instanceof PlayerMissle) continue; // don't check the missle against itself

	    if(playerMissle.isFired()) {
		if (o instanceof AlienShip) {
		    AlienShip ship = (AlienShip)o;

		    if (playerMissle.willCollide(ship)) {
			ship.collidedWith(playerMissle);
		    }
		}
	    }
	}
    }

    public void drawScreen() {
	gl.glClear (gl.GL_COLOR_BUFFER_BIT);

	// draw the starfield
	//starfield.draw(gl, 0, 0);
	
	playerShip.draw(gl);

	Iterator i = entities.iterator();
	while (i.hasNext()) {
	    GameEntity e = (GameEntity) i.next();
	    e.draw(gl);
	}

	//explosion.draw(gl, 0, 0);
    }

    public void processInput() throws SDLException {
	while (true) {
	    SDLEvent event = SDLEvent.pollEvent();
	    if (event == null) break;

	    if (event instanceof SDLQuitEvent) {
		runGame = false;
	    }

	    else if (event instanceof SDLKeyboardEvent) {
		processKeyboardEvent((SDLKeyboardEvent)event);
	    }
	}
    }

    public void processKeyboardEvent(SDLKeyboardEvent keyEvent) {
	if (keyEvent.getType() != SDLEventType.KEYDOWN) return;

	switch (keyEvent.getSym()) {
	    case SDLKey.SDLK_ESCAPE:
		runGame = false;
		break;

	    case SDLKey.SDLK_LEFT:
		playerShip.setX( playerShip.getX() - playerShip.getWidth() /2);
		break;
		
	    case SDLKey.SDLK_RIGHT:
		playerShip.setX( playerShip.getX() + playerShip.getWidth()/ 2);
		break;

	    case SDLKey.SDLK_SPACE:
		fireMissle();
		break;
	}
    }

    public void fireMissle() {
	if (playerMissle.isFired()) return;
	
	playerMissle.setX(playerShip.getX() - playerShip.getWidth() / 2);
	playerMissle.setY(playerShip.getY() - playerShip.getHeight());
	playerMissle.setFired(true);

	entities.add(playerMissle);
    }

    public void destroy() {
	SDLMain.quit();
    }

    public static void main(String[] args) {
	SpaceInvaders invaders = null;
	try {
	    invaders = new SpaceInvaders(args);
	    invaders.init();
	    invaders.run();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	finally {
	    if (invaders != null) invaders.destroy();
	} // finally
	
    }
}