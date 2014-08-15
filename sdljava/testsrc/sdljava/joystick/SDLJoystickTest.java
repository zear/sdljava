package sdljava.joystick;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

import sdljava.event.SDLEvent;
import sdljava.event.SDLKeyboardEvent;
import sdljava.event.SDLKey;
import sdljava.event.SDLEventState;
import sdljava.event.SDLJoyAxisEvent;
import sdljava.event.SDLJoyBallEvent;
import sdljava.event.SDLJoyButtonEvent;
import sdljava.event.SDLJoyHatEvent;
import sdljava.joystick.SDLJoystick;

import java.io.File;

public class SDLJoystickTest extends SDLTest {

    SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_JOYSTICK);
	System.out.println("SDL initialized");
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF|SDLVideo.SDL_RESIZABLE);
	System.out.println("framebuffer=" + framebuffer);
    }

    public void destroy() {
	SDLMain.quit();
    }

    public static void main(String[] args) {
	SDLJoystickTest t = null;
	try {
	    t = new SDLJoystickTest();
	    t.init();

	    SDLEvent.joystickEventState(SDLEventState.ENABLE);

	    int count = SDLJoystick.numJoysticks();
	    System.out.println("numJoysticks=" + count);
	    if (count < 1) {
		System.out.println("No joystick devices available");
		return;
	    }
	    
	    System.out.println("opening first joystick device");
	    SDLJoystick joystick = SDLJoystick.joystickOpen(0);
	    System.out.println("joystick opened");
					
	    System.out.println("press ESC to exit");

	    while (true) {
		SDLEvent event = SDLEvent.waitEvent();
		
		if (event instanceof SDLKeyboardEvent) {
		    SDLKeyboardEvent keyboardEvent = (SDLKeyboardEvent) event;
		    if (keyboardEvent.getSym() == SDLKey.SDLK_ESCAPE) break;
		}

		else if (event instanceof SDLJoyAxisEvent) {
		    System.out.println(event);
		}

		else if (event instanceof SDLJoyBallEvent) {
		    System.out.println(event);
		}

		else if (event instanceof SDLJoyButtonEvent) {
		    System.out.println(event);
		}

		else if (event instanceof SDLJoyHatEvent) {
		    System.out.println(event);
		}
	    }
	}
	catch (Exception e) {
	    e.printStackTrace();
	} // catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }
    
}