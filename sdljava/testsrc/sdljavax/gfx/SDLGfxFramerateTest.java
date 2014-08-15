package sdljavax.gfx;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.SDLTimer;
import sdljava.event.SDLEvent;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import sdljavax.gfx.SDLGfx;

/**
 * Framerate manager from SDL_gfx library test Class
 * <P>
 * 
 * @author Bart LEBOEUF
 * @version $Id: SDLGfxFramerateTest.java,v 1.1 2005/02/18 03:16:46 doc_alton Exp $
 */
public class SDLGfxFramerateTest {

	private int i, rate, x, y, dx, dy, r, g, b;
	private SDLSurface screen = null;
	private FPSmanager fpsm = null;
	private static SecureRandom sr = null;
	private final static String TITLE = "SDLGFX framerate test - ";

	static {
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) { }
	}

	public SDLGfxFramerateTest() {
		fpsm = new FPSmanager();
		sdl_init(800, 600, 32, (long) SDLVideo.SDL_HWSURFACE
				| SDLVideo.SDL_DOUBLEBUF);
	}

	public void sdl_init(int X, int Y, int bbp, long flags) {
		// Initialize SDL's subsystems - in this case, only video.
		try {
			SDLMain.init(SDLMain.SDL_INIT_VIDEO);
		} catch (SDLException e) {
			System.err.println("Unable initialize SDL: " + SDLMain.getError());
			System.exit(1);
		}

		try {
			// Attempt to create a window with 32bit pixels.
			screen = SDLVideo.setVideoMode(X, Y, bbp, flags);
		} catch (SDLException se) {
			System.err.println("Unable to set video: " + SDLMain.getError());
			System.exit(1);
		}

		try {
			init();
		} catch (SDLException e2) {
			System.err.println("Unable to initialize : " + SDLMain.getError());
			System.exit(1);
		}
	}

	public void destroy() {
		SDLMain.quit();
		System.exit(0);
	}

	protected void work() {
		// Main loop: loop forever.
		while (true) {
			try {
				// Render stuff
				render();
			} catch (SDLException e1) {
				System.err.println("Error while rendering : "
						+ SDLMain.getError());
				System.exit(1);
			}

			try {
				// Poll for events, and handle the ones we care about.
				SDLEvent event = SDLEvent.pollEvent();
				if (event != null) eventOccurred(event);
				else
					try {
						SDLTimer.delay(50);
					} catch (InterruptedException e) {
					}
			} catch (SDLException se) {
				System.err.println("Error while polling event : "
						+ SDLMain.getError());
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		SDLGfxFramerateTest j = null;
		try {
			j = new SDLGfxFramerateTest();
			j.work();
		} finally {
			j.destroy();
		}
	}

	void init() throws SDLException {
		i = 0;
		x = screen.getWidth() / 2;
		y = screen.getHeight() / 2;
		dx = 7;
		dy = 5;
		r = g = b = 255;
		fpsm.initFramerate();
	}

	void render() throws SDLException {
		while (true) {
			/* Set/switch framerate */
			i -= 1;
			if (i < 0) {
				/* Set new rate */
				rate = 5 + 5 * (rand() % 10);
				fpsm.setFramerate(rate);
				System.out.println("\nFramerate set to " + rate + "Hz ...");
				SDLVideo.wmSetCaption(TITLE + "Framerate set to " + rate + "Hz ...", null);
				/* New timeout */
				i = 2 * rate;
				/* New Color */
				r = rand() & 255;
				g = rand() & 255;
				b = rand() & 255;
			}

			/* Black screen */
			screen.fillRect(SDLVideo.mapRGB(screen.getFormat(), 0, 0, 0));

			/* Move */
			x += dx;
			y += dy;

			/* Reflect */
			if ((x < 0) || (x > screen.getWidth())) {
				dx = -dx;
			}
			if ((y < 0) || (y > screen.getHeight())) {
				dy = -dy;
			}

			/* Draw */
			SDLGfx.filledCircleRGBA(screen, x, y, 30, r, g, b, 255);
			SDLGfx.circleRGBA(screen, x, y, 30, 255, 255, 255, 255);

			/* Display by flipping screens */
			screen.updateRect();

			/* Delay to fix rate */
			fpsm.framerateDelay();
		}
	}

	public void eventOccurred(SDLEvent event) {
		if (event == null)
			return;
		switch (event.getType()) {
		case SDLEvent.SDL_QUIT:
			destroy();
		}
	}

	protected int rand() {
		int i;
		for (i = sr.nextInt(Integer.MAX_VALUE); i <= 0;) ;
		return i;
	}
}