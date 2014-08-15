package sdljava.event;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

public class SDLEventTest extends SDLTest {

    SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF);
    }

    public void destroy() {
	SDLMain.quit();
    }
    
    public static void main(String[] args) {
	SDLEventTest t = null;
	try {
	    t = new SDLEventTest();

	    t.init();

	    // first test waiting for one event thats not returned
	    System.out.println("...waiting for event that is not returned");
	    SDLEvent.waitEvent(false);

	    while (true) {
		SDLEvent e = SDLEvent.waitEvent();
		System.out.println(e);

		if (e instanceof SDLKeyboardEvent) {
		    SDLKeyboardEvent keyboardEvent = (SDLKeyboardEvent) e;
		    if (keyboardEvent.getSym() == SDLKey.SDLK_ESCAPE) break;
		}
	    }
	    

	} catch (Exception e) {
	    e.printStackTrace();
	} // try-catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}