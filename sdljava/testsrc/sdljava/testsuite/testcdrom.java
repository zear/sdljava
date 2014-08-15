package sdljava.testsuite;
/**
 *  sdljava - a java binding to the SDL API
 *
 *  Copyright (C) 2004  Ivan Z. Ganza
 * 
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA
 *
 *  Ivan Z. Ganza (ivan_ganza@yahoo.com)
 */
import sdljava.SDLTest;
import sdljava.SDLMain;
import sdljava.SDLException;
import sdljava.cdrom.SDLCdrom;
import sdljava.cdrom.SDLCdTrack;
import sdljava.cdrom.CDstatus;
import sdljava.cdrom.FrameInfo;

import java.util.List;
import java.util.Arrays;

/**
 * Port of testcdrom.c test program from SDL distribution
 * 
 * 
 *
 * @author  Ivan Z. Ganza
 * @version $Id: testcdrom.java,v 1.1 2005/09/16 23:21:35 ivan_ganza Exp $
 */
public class testcdrom extends SDLTest {

    public static void status(SDLCdrom cdrom) {
	CDstatus status = cdrom.cdStatus();

	System.out.println("Drive " + cdrom.getDriveIndex() + " status: " + status);

	if (status == CDstatus.PLAYING) {
	    FrameInfo frameInfo = cdrom.getCurrentFrameInfo();

	    System.out.println("Currently playing track " + cdrom.getCurrentTrack() + ", " + frameInfo.getMinutes() + ": " + frameInfo.getSeconds());
	}
    }

    public static void listTracks(SDLCdrom cdrom) {

	cdrom.cdStatus();

	List tracks = cdrom.getTracks();
	System.out.println("Drive tracks: " + tracks.size());

	for(int i = 0; i < tracks.size(); i++) {
	    SDLCdTrack track = (SDLCdTrack) tracks.get(i);
	    FrameInfo frameInfo = track.getFrameInfo();

	    String minuteString  = (frameInfo.getMinutes() < 10 ? "0"+frameInfo.getMinutes() : ""+frameInfo.getMinutes());
	    String secondsString = (frameInfo.getSeconds() < 10 ? "0"+frameInfo.getSeconds() : ""+frameInfo.getSeconds());
	    
	    System.out.println("\tTrack (index " + track.getId() + ") " + minuteString + ": " + secondsString + " / " + track.getLength() + " [" + track.getType() + " track]");
	}
    }

    public static void play(SDLCdrom cdrom, int firstTrack, int firstFrame, int numTracks, int numFrames) throws SDLException {
	CDstatus status = cdrom.cdStatus();
	if(status == CDstatus.TRAYEMPTY) {
	    System.out.println("No CD in drive!\n");
	    return;
	}

	cdrom.cdPlayTracks(firstTrack, firstFrame, numTracks, numFrames);
    }

    public static void printUsage() {
	System.out.println("Usage: testcdrom [drive#] [command] [command] ...");
	System.out.println("Where 'command' is one of:");

	System.out.println("        -status");
	System.out.println("        -list");
	System.out.println("        -play [first_track] [first_frame] [num_tracks] [num_frames]");
	System.out.println("        -pause");
	System.out.println("        -resume");
	System.out.println("        -stop");
	System.out.println("        -eject");
	System.out.println("        -sleep <milliseconds>");
    }
    
    public static void main(String[] args) {
	try {
	    List argsList = Arrays.asList(args);
	    
	    /* Initialize SDL first */
	    SDLMain.init(SDLMain.SDL_INIT_CDROM);

	    /* Find out how many CD-ROM drives are connected to the system */
	    int driveCount = SDLCdrom.numDrives();
	    if (driveCount == 0) {
		System.out.println("No CD-ROM devices detected\n");
		return;
	    }

	    System.out.println("Drives available: " + driveCount);
	    for (int i = 0; i < driveCount; i++) {
		System.out.println("Drive " + i + ": \"" + SDLCdrom.cdName(i) + "\"\n");
	    }

	    if (argsList.size() < 2) return; // nothing to do

	    /* Open the CD-ROM */
	    int drive = 0;
	    try {
		if(argsList.size() > 0) {
		    drive = Integer.parseInt((String)argsList.get(0));
		}
	    } catch (NumberFormatException e) {
		e.printStackTrace();
	    }

	    SDLCdrom cdrom = SDLCdrom.cdOpen(drive);

	    /* Find out which function to perform */
	    for(int i = 1; i < argsList.size(); i++) {
		String arg = (String)argsList.get(i);

		if (arg.equals("-status")) status(cdrom);
		else if (arg.equals("-list")) listTracks(cdrom);
		else if (arg.equals("-play")) {
		    int firstTrack = Integer.parseInt((String)argsList.get(++i));
		    int firstFrame = Integer.parseInt((String)argsList.get(++i));
		    int numTracks  = Integer.parseInt((String)argsList.get(++i));
		    int numFrames  = Integer.parseInt((String)argsList.get(++i));

		    play(cdrom, firstTrack, firstFrame, numTracks, numFrames);
		}
		else if (arg.equals("-pause")) {
		    cdrom.cdPause();
		}
		else if (arg.equals("-resume")) {
		    cdrom.cdResume();
		}
		else if (arg.equals("-stop")) {
		    cdrom.cdStop();
		}
		else if (arg.equals("-eject")) {
		    cdrom.cdEject();
		}
		else if (arg.equals("-sleep")) {
		    int time = Integer.parseInt((String)argsList.get(++i));
		    Thread.currentThread().sleep(time);
		    System.out.println("Delayed " + time + " milliseconds\n");
		}
		else {
		    printUsage();
		}
	    }
	    
	}
	catch (NumberFormatException e) {
	    System.out.println(e.getMessage());
	    printUsage();
	}
	
	catch (Exception e) {
	    e.printStackTrace();
	}
	finally {
	    SDLMain.quit();
	}
    }
}