package sdljava.event;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.SDLTest;
import sdljava.SDLTimer;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;

public class SDLEventManagerTest extends SDLTest {

    SDLSurface framebuffer;
    SDLEventManager manager;
    boolean isStopped = false;
    ByteBuffer pixelData = null;
    int pitch = 0;

    public void init() throws SDLException {
		SDLMain.init(SDLMain.SDL_INIT_EVERYTHING|SDLMain.SDL_INIT_NOPARACHUTE);
		framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_SWSURFACE|SDLVideo.SDL_DOUBLEBUF);
		pixelData = framebuffer.getPixelData();
		pixelData.order(ByteOrder.nativeOrder());
		pitch = (framebuffer.getPitch()/ 4);
		// Get the manager instance
    	manager = SDLEventManager.getInstance();
    	// Initialize the listener
    	SDLEventListenerTest testListener = new SDLEventListenerTest(this);
    	// Register external class listener on Keyboard Events type
    	manager.register(testListener,SDLKeyboardEvent.class);
    }

    public void destroy() {
    	if (manager!=null) manager.stop();
		SDLMain.quit();
    }
    
    public void stopIt() {
    	isStopped = true;
    }
    
    public void work() throws SDLException, InterruptedException {
    	// Start event manager
    	manager.startAndWait();
    	while(!isStopped) {
    		// Here we can easily do something else, like a psycho rendering
    		// Lock surface if needed
			if(framebuffer.lockSurface()==false) return;
		    // Ask SDL for the time in milliseconds
			int yofs = 0, tick = (int)SDLTimer.getTicks();

		    // Draw a hypnotic screen
		    for (int i = 0; i < framebuffer.getHeight(); i++)
		    {
		        for (int j = 0, ofs = yofs; j < framebuffer.getWidth(); j++, ofs++)
		        {
		        	pixelData.putInt(ofs*4,i * i + j * j + tick);
		        }
		        yofs += pitch;
		    }
		    
		    // Unlock if needed
		    if (framebuffer.mustLock()) framebuffer.unlockSurface();

		    // Tell SDL to update the whole screen
		    framebuffer.updateRect();
    		
		    SDLTimer.delay(10);
			System.out.println("Press any key ... 'ESC' key to quit.");
    	}
    }
    
    public static void main(String[] args) {
		SDLEventManagerTest t = null;
		try {
			t = new SDLEventManagerTest();
			t.init();
			t.work();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (t != null) t.destroy();
		} 
    }
}