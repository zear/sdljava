package sdljava.video;

import java.io.File;
import java.awt.Rectangle;

import sdljava.SDLMain;
import sdljava.SDLException;
import sdljava.SDLTest;

import java.nio.ByteBuffer;

public class SDLDirectBufferPixelDataTest extends SDLTest {

    static SDLSurface framebuffer;
    
    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	System.out.println("Video subsystem initialized");
	
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF|SDLVideo.SDL_RESIZABLE);

	// videoDriverName causes crash right now...not sure why
	//System.out.println("Video Driver=" + SDLVideo.videoDriverName());
	System.out.println("framebuffer=" + framebuffer);
    }

    public void destroy() {
	SDLMain.quit();
    }

    public SDLDirectBufferPixelDataTest() {
    }

    public static void main(String[] args) {
	SDLDirectBufferPixelDataTest t = null;
	try {
	    t = new SDLDirectBufferPixelDataTest();
	    t.init();


	    framebuffer.fillRect(127);
	    framebuffer.flip();

	    System.out.println("setting pixel data...");
	    ByteBuffer pixelBuffer = framebuffer.getPixelData();
	    System.out.println(pixelBuffer);
	    pixelBuffer.clear();
	    framebuffer.flip();
	    
	    for(int i = 0; i < pixelBuffer.capacity(); i++) {
	        pixelBuffer.put((byte)122);
	    }
	    
	    framebuffer.flip();
	    
	    try{Thread.currentThread().sleep(5000);}catch (Exception e) {}
	}
	catch (Exception e) {
	    e.printStackTrace();
	} // catch
	finally {
	    //if (t != null) t.destroy();
	} // finally
    }
}