%module SWIG_SDLMain
%{
#include "SDL.h"
#include "SDL_error.h"

  SDL_version SWIG_SDL_VERSION() {
    SDL_version version;
    
    SDL_VERSION(&version);

    return version;
  }
%}

%javaconst(1);
%import "SDL_types.h"

%pragma(java) jniclasscode=%{
  static {
    try {
      // if set don't loadLibrary ourselves, let client of library do it
      if (System.getProperty("sdljava.bootclasspath") == null) {
        System.loadLibrary("sdljava");
      }
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load. \n" + e);
      System.exit(1);
    }
  }
%}

typedef struct {
	Uint8 major;
	Uint8 minor;
	Uint8 patch;
} SDL_version;

#define	SDL_INIT_TIMER		0x00000001
#define SDL_INIT_AUDIO		0x00000010
#define SDL_INIT_VIDEO		0x00000020
#define SDL_INIT_CDROM		0x00000100
#define SDL_INIT_JOYSTICK	0x00000200
#define SDL_INIT_NOPARACHUTE	0x00100000	/* Don't catch fatal signals */
#define SDL_INIT_EVENTTHREAD	0x01000000	/* Not supported on all OS's */
#define SDL_INIT_EVERYTHING	0x0000FFFF

extern int     SDL_Init(Uint32 flags);
extern int     SDL_InitSubSystem(Uint32 flags);
extern void    SDL_QuitSubSystem(Uint32 flags);
extern Uint32  SDL_WasInit(Uint32 flags);
extern void    SDL_Quit(void);

extern SDL_version SWIG_SDL_VERSION();

char * SDL_GetError(void);
