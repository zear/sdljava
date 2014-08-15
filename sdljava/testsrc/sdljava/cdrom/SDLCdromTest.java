package sdljava.cdrom;

import sdljava.x.swig.*;

import sdljava.SDLTest;
import sdljava.SDLException;
import sdljava.SDLMain;

import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;

import sdljava.cdrom.SDLCdrom;
import sdljava.cdrom.SDLCdTrack;
import sdljava.cdrom.CDstatus;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

public class SDLCdromTest extends SDLTest {

    SDLSurface framebuffer;

    public void init() throws SDLException {
	SDLMain.init(SDLMain.SDL_INIT_CDROM);
    }
    
    public void destroy() {
	SDLMain.quit();
    }

    public static void main(String[] args) {
	SDLCdromTest t = null;
	try {
	    t = new SDLCdromTest();
	    t.init();

	    int count = SDLCdrom.numDrives();
	    System.out.println("numCdroms=" + count);
	    if (count < 1) {
		System.out.println("No cd drom devices available");
		return;
	    }

	    System.out.println("opening first cdrom device");
	    SDLCdrom cdrom = SDLCdrom.cdOpen(0);
	    System.out.println("cdrom opened");

	    CDstatus status = cdrom.cdStatus();
	    System.out.println("cdrom status: " + status);

	    List tracks = cdrom.getTracks();
	    Iterator i = tracks.iterator();
	    while (i.hasNext()) {
		SDLCdTrack track = (SDLCdTrack)i.next();
		System.out.println(track);
	    }

//	    System.out.println("Playing random track...");
//	    int playme = new Random(System.currentTimeMillis()).nextInt(tracks.size());
//	    System.out.println("...play track: " + playme);
//	    SDLCdTrack track = tracks.get(playme);
//	    cdrom.cdPlay((int)track.getOffset(), (int)track.getLength());
//	    
//	    try{Thread.currentThread().sleep(5000);}catch (Exception e) {}
	}
	catch (Exception e) {
	    e.printStackTrace();
	} // catch
	finally {
	    if (t != null) t.destroy();
	} // finally
    }
}