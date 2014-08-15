# build file for mingw on windows platform, assumed you are running from within MinGW Unix-like Env
CC = gcc

# assumed you have java mounted in /java 
# add a line like so in your /etc/fstab and restart the MinGW
# c:/<path to your java>	/java

INCLUDE = -I/usr/include/SDL -I/java/include/ -I/java/include/win32

SDLJAVA_SRC=SDLAudio_wrap.c\
            SDLEvent_wrap.c\
            SDLMain_wrap.c\
            SDLVideo_wrap.c\
            SDLJoystick_wrap.c\
            SDLCdrom_wrap.c

SDLJAVA_MIXER_SRC=SDLMixer_wrap.c
SDLJAVA_MIXER_LIB=-lSDLMain -lSDL -lSDL_mixer -lmingw32 -mwindows

SDLJAVA_TTF_SRC=SDLTTF_wrap.c
SDLJAVA_TTF_LIB=-lSDLMain -lSDL -lSDL_ttf -lmingw32 -mwindows

SDLJAVA_IMAGE_SRC=SDLImage_wrap.c
SDLJAVA_IMAGE_LIB=-lSDLMain -lSDL -lSDL_image -lmingw32 -mwindows

SDLJAVA_GFX_SRC=SDLGFX_wrap.c
SDLJAVA_GFX_LIB=-lSDLMain -lSDL -lSDL_gfx -lmingw32 -mwindows

SDLJAVA_LIB=-lSDLMain -lSDL -lmingw32 -mwindows
LIBDIR=-L/usr/lib

all:	sdljava.dll sdljava_mixer.dll sdljava_ttf.dll sdljava_image.dll

sdljava.dll: $(SDLJAVA_SRC)
	gcc -Wall -D_JNI_IMPLEMENTATION_ -Wl,--kill-at $(INCLUDE) -shared -o sdljava.dll $(SDLJAVA_SRC) $(LIBDIR) $(SDLJAVA_LIB)
	cp sdljava.dll ../../../lib

sdljava_mixer.dll: $(SDLJAVA_MIXER_SRC)
	gcc -Wall -D_JNI_IMPLEMENTATION_ -Wl,--kill-at $(INCLUDE) -shared -o sdljava.dll $(SDLJAVA_MIXER_SRC) $(LIBDIR) $(SDLJAVA_MIXER_LIB)
	cp sdljava_mixer.dll ../../../lib

sdljava_ttf.dll: $(SDLJAVA_TTF_SRC)
	gcc -Wall -D_JNI_IMPLEMENTATION_ -Wl,--kill-at $(INCLUDE) -shared -o sdljava.dll $(SDLJAVA_TTF_SRC) $(LIBDIR) $(SDLJAVA_TTF_LIB)
	cp sdljava_ttf.dll ../../../lib

sdljava_image.dll: $(SDLJAVA_IMAGE_SRC)
	gcc -Wall -D_JNI_IMPLEMENTATION_ -Wl,--kill-at $(INCLUDE) -shared -o sdljava.dll $(SDLJAVA_IMAGE_SRC) $(LIBDIR) $(SDLJAVA_IMAGE_LIB)
	cp sdljava_image.dll ../../../lib

sdljava_gfx.dll: $(SDLJAVA_GFX_SRC)
	gcc -Wall -D_JNI_IMPLEMENTATION_ -Wl,--kill-at $(INCLUDE) -shared -o sdljava.dll $(SDLJAVA_GFX__SRC)
	cp sdljava_gfx.dll ../../../lib
