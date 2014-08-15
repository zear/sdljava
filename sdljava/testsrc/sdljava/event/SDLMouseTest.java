package sdljava.event;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

public class SDLMouseTest extends SDLTest {

    SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_EVERYTHING);
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF);
    }

    public void destroy() {
	SDLMain.quit();
    }
    
    public static void main(String[] args) {
	SDLMouseTest t = null;
	try {
	    t = new SDLMouseTest();

	    t.init();

	    System.out.println("polling for events...");

	    MouseState state = null;
	    while (true) {
		SDLEvent e = SDLEvent.waitEvent();

		state = SDLEvent.getMouseState();
		System.out.println("state=" + state);

		state = SDLEvent.getRelativeMouseState();
		System.out.println("relative=" + state);

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