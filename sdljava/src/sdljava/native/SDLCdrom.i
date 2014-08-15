%module SWIG_SDLCdrom
%{
  #include "SDL_cdrom.h"

  SDL_CDtrack SWIG_SDL_getTrack(SDL_CD cd, int index) {
    return cd.track[index];
  }

  void SWIG_framesToMSF(int frame, int* m, int* s, int* f) {
    FRAMES_TO_MSF(frame, m, s, f);
  }

  int SWIG_msfToFrames(int m, int s, int f) {
    return MSF_TO_FRAMES(m, s, f);
  }
%}

%javaconst(1);
%import "SDL_types.h"
%import "typemaps.i"

%javaconst(1);

#define SDL_MAX_TRACKS	99

enum TrackType {
  SDL_AUDIO_TRACK = 0x00,
  SDL_DATA_TRACK  = 0x04
};

typedef int CDstatus;

enum CDstatusValues{
	TRAYEMPTY,
	STOPPED,
	PLAYING,
	PAUSED,
	ERROR = -1
};

typedef struct {
	Uint8 id;		/* Track number */
	Uint8 type;		/* Data or audio track */
	Uint16 unused;
	Uint32 length;		/* Length, in frames, of this track */
	Uint32 offset;		/* Offset, in frames, from start of disk */
} SDL_CDtrack;

typedef struct SDL_CD {
	int id;			/* Private drive identifier */
	CDstatus status;	/* Current drive status */

	/* The rest of this structure is only valid if there's a CD in drive */
	int numtracks;		/* Number of tracks on disk */
	int cur_track;		/* Current track position */
	int cur_frame;		/* Current frame offset within current track */
	SDL_CDtrack track[SDL_MAX_TRACKS+1];
} SDL_CD;



extern int  SDL_CDNumDrives(void);
extern const char *  SDL_CDName(int drive);
extern SDL_CD *  SDL_CDOpen(int drive);
extern CDstatus  SDL_CDStatus(SDL_CD *cdrom);
extern int  SDL_CDPlayTracks(SDL_CD *cdrom, int start_track, int start_frame, int ntracks, int nframes);
extern int  SDL_CDPlay(SDL_CD *cdrom, int start, int length);
extern int  SDL_CDPause(SDL_CD *cdrom);
extern int  SDL_CDResume(SDL_CD *cdrom);
extern int  SDL_CDStop(SDL_CD *cdrom);
extern int  SDL_CDEject(SDL_CD *cdrom);
extern void  SDL_CDClose(SDL_CD *cdrom);

extern SDL_CDtrack SWIG_SDL_getTrack(SDL_CD cd, int index);
extern void SWIG_framesToMSF(int frame, int* OUTPUT, int* OUTPUT, int* OUTPUT);
extern int SWIG_msfToFrames(int m, int s, int f);
