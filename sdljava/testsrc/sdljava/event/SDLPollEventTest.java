package sdljava.event;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

public class SDLPollEventTest extends SDLTest {

    SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_EVERYTHING);
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF);
    }

    public void destroy() {
	SDLMain.quit();
    }
    
    public static void main(String[] args) {
	SDLPollEventTest t = null;
	try {
	    t = new SDLPollEventTest();

	    t.init();

	    System.out.println("polling for events...");

	    // note:  this is a busy loop eating all cpu power
	    //        only meant to test that polling works
	    while (true) {
		SDLEvent e = SDLEvent.pollEvent();

		if (e != null) {
		    System.out.println(e);

		    if (e instanceof SDLKeyboardEvent) {
			SDLKeyboardEvent keyboardEvent = (SDLKeyboardEvent) e;
			if (keyboardEvent.getSym() == SDLKey.SDLK_ESCAPE) break;
		    }
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