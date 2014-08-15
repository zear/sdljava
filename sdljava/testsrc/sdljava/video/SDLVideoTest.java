package sdljava.video;

import java.io.File;

import sdljava.SDLMain;
import sdljava.SDLException;
import sdljava.SDLTest;

public class SDLVideoTest extends SDLTest {

    SDLSurface framebuffer;
    
    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_VIDEO);
	System.out.println("Video subsystem initialized");
	
	framebuffer = SDLVideo.setVideoMode(800, 600, 32, (long)SDLVideo.SDL_HWSURFACE|SDLVideo.SDL_DOUBLEBUF|SDLVideo.SDL_RESIZABLE);

	// videoDriverName causes crash right now...not sure why
	//System.out.println("Video Driver=" + SDLVideo.videoDriverName());
	System.out.println("framebuffer=" + framebuffer);
    }

    public void testGetVideoInfo() throws SDLException {
	SDLVideoInfo videoInfo = SDLVideo.getVideoInfo();
	System.out.println("testGetVideoInfo(): info=" + videoInfo);
    }

    public void testGetVideoSurface() throws SDLException {
	SDLSurface surface = SDLVideo.getVideoSurface();
	System.out.println("testGetVideoSurface(): surface=" + surface);
    }

    public void testLoadBMP() throws SDLException {
	SDLSurface bmp = SDLVideo.loadBMP("testdata" + File.separator + "test_bmp.bmp");
	SDLRect src = new SDLRect(0,0,bmp.getWidth(),bmp.getHeight());
	
	long start = System.currentTimeMillis();
	bmp.blitSurface(src, framebuffer, src);
	long end = System.currentTimeMillis();
	framebuffer.flip();
    }

    public void testVideoModeOK() throws SDLException {
	int result = SDLVideo.videoModeOK(1024, 768, 32, 0);
	System.out.println("testVideoModeOK(): 1024/768/32/0=" + result);
    }

    public void testFillRect() throws SDLException {
	framebuffer.fillRect(new SDLRect(0, 0, framebuffer.getWidth(), framebuffer.getHeight()), 0xFFFFFFFF);
	framebuffer.updateRect();
    }

    public void testGetGammaRamp() throws SDLException {
	GammaTable table = SDLVideo.getGammaRamp();
	System.out.println(table);
    }

    public void destroy() {
	SDLMain.quit();
    }

    public SDLVideoTest() {
    }

    public static void main(String[] args) {
	SDLVideoTest t = null;
	try {
	    t = new SDLVideoTest();
	    t.init();
	    t.testGetVideoInfo();
	    t.testGetVideoSurface();
	    t.testVideoModeOK();
	    t.testLoadBMP();
	    t.testFillRect();
	    t.testGetGammaRamp();

	    try{Thread.currentThread().sleep(5000);}catch (Exception e) {}
	}
	catch (Exception e) {
	    e.printStackTrace();
	} // catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}