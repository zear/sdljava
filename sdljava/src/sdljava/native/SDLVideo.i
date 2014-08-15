%module SWIG_SDLVideo
%{
#include "SDL_video.h"
#include "SDL_mouse.h"

  JavaVM *cachedVM;

  SDL_Rect srcBlitRect;
  SDL_Rect dstBlitRect;

  // wrap Load_BMP define
  SDL_Surface * SWIG_SDL_LoadBMP(char * file) {
    return SDL_LoadBMP(file);
  }

  int SWIG_SDL_SaveBMP(SDL_Surface *surface, const char *file) {
    return SDL_SaveBMP(surface, file);
  }

  int SWIG_SDL_FillRect_FAST(SDL_Surface *dst, int x, int y, int w, int h, Uint32 color) {
    srcBlitRect.x = x;
    srcBlitRect.y = y;
    srcBlitRect.w = w;
    srcBlitRect.h = h;

    return SDL_FillRect(dst, &srcBlitRect, color);
  }

  // define a fast version of blit
  // we can't afford to have too many jni calls here so rects are unwrapped and passed
  // directly into the function
  // also note that we use pre-allocated SDL_Rects so no new memory needs to be allocated
  // during the blit!  This means only one thread should be doing the blits!
  int SWIG_SDL_BlitSurface_FAST(SDL_Surface * src, int sX, int sY, int sWidth, int sHeight, SDL_Surface *dst, int dX, int dY, int dWidth, int dHeight) {
    
    srcBlitRect.x = sX;
    srcBlitRect.y = sY;
    srcBlitRect.w = (sWidth > -1 ? sWidth : 0);
    srcBlitRect.h = (sHeight > -1 ? sHeight : 0);

    dstBlitRect.x = dX;
    dstBlitRect.y = dY;
    dstBlitRect.w = (dWidth > -1 ? dWidth : 0);
    dstBlitRect.h = (dHeight > -1 ? dHeight: 0);

    return SDL_BlitSurface(src, &srcBlitRect, dst, &dstBlitRect);
  }

  int SWIG_SDL_SetClipRect(SDL_Surface * surface, SDL_Rect *rect) {
    SDL_bool result = SDL_SetClipRect(surface, rect);
    return result == SDL_TRUE ? 1 : 0;
  }

  int SWIG_SDL_SetColors(SDL_Surface * surface, SDL_Color colors[], int firstcolor, int ncolors) {
    return SDL_SetColors(surface, (SDL_Color*)&colors, firstcolor, ncolors);
  }

  int SWIG_SDL_SetPalette(SDL_Surface *surface, int flags, SDL_Color colors[], int firstcolor, int ncolors) {
    return SDL_SetPalette(surface, flags, (SDL_Color*)&colors, firstcolor, ncolors);
  }

  int SWIG_SDL_SetGammaRamp(Uint16 red[], Uint16 green[], Uint16 blue[])
  {
    return SDL_SetGammaRamp((Uint16*)&red, (Uint16*)&green, (Uint16*)&blue);
  }

  SDL_Surface * SWIG_SDL_CreateRGBSurfaceFrom(Uint16 pixels[], int width, int height, int depth, int pitch, Uint32 Rmask, Uint32 Gmask, Uint32 Bmask, Uint32 Amask) {
    return SDL_CreateRGBSurfaceFrom((void*)&pixels, width, height, depth, pitch, Rmask, Gmask, Bmask, Amask);
  }

  void SWIG_GetPaletteColors(SDL_Palette * palette, SDL_Color colors[]) {
    int i;

    for (i = 0; i < palette->ncolors; i++) {
      colors[i].r = palette->colors[i].r;
      colors[i].g = palette->colors[i].g;
      colors[i].b = palette->colors[i].b;
    }
  }

  void SWIG_GetPixelData32(SDL_Surface *surface, int data[]) {
    Uint32 * buf = (Uint32*) surface->pixels;
    Uint32 * pixelData = (Uint32*)&data;
    
    int      x = 0;
    int length = surface->w * surface->h * surface->format->BytesPerPixel / 4; // 4 bytes per pixel

    for (x = 0; x < length; x++) {
      pixelData[x] = buf[x];
    }
  }

  void SWIG_GetPixelData16(SDL_Surface *surface, Uint16 pixelData[]) {
    Uint16 * buf = (Uint16*) surface->pixels;
    
    int      x = 0;
    int length = surface->w * surface->h * surface->format->BytesPerPixel / 2; // 2 bytes per pxiel

    for (x = 0; x < length; x++) {
      pixelData[x] = buf[x];
    }
  }

  void SWIG_GetPixelData8(SDL_Surface *surface, Uint8 pixelData[]) {
    Uint8 * buf = (Uint8*) surface->pixels;
    
    int      x = 0;
    int length = surface->format->BytesPerPixel * surface->w * surface->h;

    for (x = 0; x < length; x++) {
      pixelData[x] = buf[x];
    }
  }

  void SWIG_SetPixelData32(SDL_Surface *surface, int data[]) {
    Uint32 * buf = (Uint32*)surface->pixels;
    Uint32 * pixelData = (Uint32*)data;

    int x = 0;
    int length = surface->w * surface->h * surface->format->BytesPerPixel / 4; // 4 bytes per pixel
    for (x = 0; x < length; x++) {
      buf[x] = pixelData[x];
    }
  }

  void SWIG_SetPixelData16(SDL_Surface *surface, Uint16 pixelData[]) {
    Uint16 * buf = (Uint16*)surface->pixels;

    int x = 0;
    int length = surface->w * surface->h * surface->format->BytesPerPixel / 2; // 2 bytes per pxiel
    for (x = 0; x < length; x++) {
      buf[x] = pixelData[x];
    }
  }

  void SWIG_SetPixelData8(SDL_Surface *surface, Uint8 pixelData[]) { // 1 byte per pixel
    Uint8 * buf = (Uint8*)surface->pixels;

    int x = 0;
    int length = surface->w * surface->h * surface->format->BytesPerPixel;
    for (x = 0; x < length; x++) {
      buf[x] = pixelData[x];
    }
  }

  void SWIG_SDL_WM_GetCaption(char * title, char *icon) {
    SDL_WM_GetCaption(&title, &icon);
  }

  void SWIG_executeBlitQueue(void *vsrcX, void *vsrcY, void *vsrcWidth, void *vsrcHeight, SDL_Surface *src, void *vdstX, void *vdstY, void *vdstWidth, void *vdstHeight, SDL_Surface *dst, int count) {

    Uint16 *srcX = (Uint16 *)vsrcX;
    Uint16 *srcY = (Uint16 *)vsrcY;
    Uint16 *srcWidth  = (Uint16 *)vsrcWidth;
    Uint16 *srcHeight = (Uint16 *)vsrcHeight;

    Uint16 *dstX = (Uint16 *)vdstX;
    Uint16 *dstY = (Uint16 *)vdstY;
    Uint16 *dstWidth  = (Uint16 *)vdstWidth;
    Uint16 *dstHeight = (Uint16 *)vdstHeight;

    int x = 0;
    for (x = 0; x < count; x++) {
      srcBlitRect.x = srcX[x];
      srcBlitRect.y = srcY[x];
      srcBlitRect.w = srcWidth[x];
      srcBlitRect.h = srcHeight[x];

      dstBlitRect.x = dstX[x];
      dstBlitRect.y = dstY[x];
      dstBlitRect.w = dstWidth[x];
      dstBlitRect.h = dstHeight[x];

      printf("src --> x,y,w,h=%i,%i,%i,%i\n", srcBlitRect.x, srcBlitRect.y, srcBlitRect.w, srcBlitRect.h);
      printf("dst --> x,y,w,h=%i,%i,%i,%i\n", dstBlitRect.x, dstBlitRect.y, dstBlitRect.w, dstBlitRect.h);

      SDL_BlitSurface(src, &srcBlitRect, dst, &dstBlitRect);
    }
  }

  int SWIG_SDL_MUSTLOCK(SDL_Surface *surface) {
    return SDL_MUSTLOCK(surface);
  }
  
  JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    cachedVM = vm;

    return JNI_VERSION_1_4;
  }

  jobject SWIG_getPixelDirectByteBuffer(SDL_Surface *surface) {
    JNIEnv * jenv;
    jobject buffer;

    (*cachedVM)->GetEnv(cachedVM, (void**)&jenv, JNI_VERSION_1_4);

    buffer = (*jenv)->NewDirectByteBuffer(jenv, surface->pixels, surface->w * surface->h * surface->format->BytesPerPixel);
    if (buffer == NULL) {
      jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
      (*jenv)->ThrowNew(jenv, clazz, "null returned from NewDirectByteBuffer call!");
      return NULL;
    }
    
    return buffer;
  }

  jobject SWIG_getOverlayPixelsDirectByteBuffer(SDL_Overlay* overlay, int plane) {
      JNIEnv * jenv;
      jobject buffer;

      (*cachedVM)->GetEnv(cachedVM, (void**)&jenv, JNI_VERSION_1_4);

      buffer = (*jenv)->NewDirectByteBuffer(jenv, overlay->pixels[plane], 1);
      if (buffer == NULL) {
	  jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	  (*jenv)->ThrowNew(jenv, clazz, "null returned from NewDirectByteBuffer call!");
	  return NULL;
      }
    
      return buffer;
  }

  int SWIG_displayYUVOverlay(SDL_Overlay* overlay, int x, int y, int w, int h)
  {
      SDL_Rect r;
      r.x = x;
      r.y = y;
      r.w = w;
      r.h = h;
      
      return SDL_DisplayYUVOverlay(overlay, &r);
  }
  
%}


%pragma(java) jniclassimports=%{
import java.nio.*;
%}

%pragma(java) moduleimports=%{
import java.nio.*;
%}

//%ignore(JNI_OnLoad)

%javaconst(1);
%import "SDL_types.h"
%import "typemaps.i"
%include "arrays_java.i";
%import "common.i"

// typemap power!
%typemap(jni) SDL_Color[] "jobjectArray"
%typemap(jtype) SDL_Color[] "SDL_Color[]"
%typemap(jstype) SDL_Color[] "SDL_Color[]"
%typemap(javain) SDL_Color[] "$javainput"

%typemap(jni) SDL_Surface[] "jobjectArray"
%typemap(jtype) SDL_Surface[] "SDL_Surface[]"
%typemap(jstype) SDL_Surface[] "SDL_Surface[]"
%typemap(javain) SDL_Surface[] "$javainput"

 ////////////////////////////////////////////////////////////////////////
 // STATIC CONSTANTS

 /* Transparency definitions: These define alpha as the opacity of a surface */
#define SDL_ALPHA_OPAQUE 255
#define SDL_ALPHA_TRANSPARENT 0

 /* These are the currently supported flags for the SDL_surface */
 /* Available for SDL_CreateRGBSurface() or SDL_SetVideoMode() */
#define SDL_SWSURFACE	0x00000000	/* Surface is in system memory */
#define SDL_HWSURFACE	0x00000001	/* Surface is in video memory */
#define SDL_ASYNCBLIT	0x00000004	/* Use asynchronous blits if possible */
/* Available for SDL_SetVideoMode() */
#define SDL_ANYFORMAT	0x10000000	/* Allow any video depth/pixel-format */
#define SDL_HWPALETTE	0x20000000	/* Surface has exclusive palette */
#define SDL_DOUBLEBUF	0x40000000	/* Set up double-buffered video mode */
#define SDL_FULLSCREEN	0x80000000	/* Surface is a full screen display */
#define SDL_OPENGL      0x00000002      /* Create an OpenGL rendering context */
#define SDL_OPENGLBLIT	0x0000000A	/* Create an OpenGL rendering context and use it for blitting */
#define SDL_RESIZABLE	0x00000010	/* This video mode may be resized */
#define SDL_NOFRAME	0x00000020	/* No window caption or edge frame */
/* Used internally (read-only) */
#define SDL_HWACCEL	0x00000100	/* Blit uses hardware acceleration */
#define SDL_SRCCOLORKEY	0x00001000	/* Blit uses a source color key */
#define SDL_RLEACCELOK	0x00002000	/* Private flag */
#define SDL_RLEACCEL	0x00004000	/* Surface is RLE encoded */
#define SDL_SRCALPHA	0x00010000	/* Blit uses source alpha blending */
#define SDL_PREALLOC	0x01000000	/* Surface uses preallocated memory */

#define SDL_YV12_OVERLAY  0x32315659	/* Planar mode: Y + V + U  (3 planes) */
#define SDL_IYUV_OVERLAY  0x56555949	/* Planar mode: Y + U + V  (3 planes) */
#define SDL_YUY2_OVERLAY  0x32595559	/* Packed mode: Y0+U0+Y1+V0 (1 plane) */
#define SDL_UYVY_OVERLAY  0x59565955	/* Packed mode: U0+Y0+V0+Y1 (1 plane) */
#define SDL_YVYU_OVERLAY  0x55595659	/* Packed mode: Y0+V0+Y1+U0 (1 plane) */

 ////////////////////////////////////////////////////////////////////////
 // STRUCTURES
 /* Useful data types */
typedef struct {
	Sint16 x, y;
	Uint16 w, h;
} SDL_Rect;

typedef struct {
	Uint8 r;
	Uint8 g;
	Uint8 b;
	Uint8 unused;
} SDL_Color;

typedef struct {
	int       ncolors;
	SDL_Color *colors;
} SDL_Palette;

/* Everything in the pixel format structure is read-only */
typedef struct SDL_PixelFormat {
	SDL_Palette *palette;
	Uint8  BitsPerPixel;
	Uint8  BytesPerPixel;
	Uint8  Rloss;
	Uint8  Gloss;
	Uint8  Bloss;
	Uint8  Aloss;
	Uint8  Rshift;
	Uint8  Gshift;
	Uint8  Bshift;
	Uint8  Ashift;
	Uint32 Rmask;
	Uint32 Gmask;
	Uint32 Bmask;
	Uint32 Amask;

	/* RGB color key information */
	Uint32 colorkey;
	/* Alpha value information (per-surface alpha) */
	Uint8  alpha;
} SDL_PixelFormat;

/* This structure should be treated as read-only, except for 'pixels',
   which, if not NULL, contains the raw pixel data for the surface.
*/
typedef struct SDL_Surface {
	Uint32 flags;				/* Read-only */
	SDL_PixelFormat *format;		/* Read-only */
	int w, h;				/* Read-only */
	Uint16 pitch;				/* Read-only */
  //void *pixels;				/* Read-write */
	int offset;				/* Private */

	/* Hardware-specific surface info */
	struct private_hwdata *hwdata;

	/* clipping information */
	SDL_Rect clip_rect;			/* Read-only */
	Uint32 unused1;				/* for binary compatibility */

	/* Allow recursive locks */
	Uint32 locked;				/* Private */

	/* info for fast blit mapping to other surfaces */
	struct SDL_BlitMap *map;		/* Private */

	/* format version, bumped at every change to invalidate blit maps */
	unsigned int format_version;		/* Private */

	/* Reference count -- used when freeing surface */
	int refcount;				/* Read-mostly */
} SDL_Surface;

/* Useful for determining the video hardware capabilities */
typedef struct {
	Uint32 hw_available :1;	/* Flag: Can you create hardware surfaces? */
	Uint32 wm_available :1;	/* Flag: Can you talk to a window manager? */
	Uint32 UnusedBits1  :6;
	Uint32 UnusedBits2  :1;
	Uint32 blit_hw      :1;	/* Flag: Accelerated blits HW --> HW */
	Uint32 blit_hw_CC   :1;	/* Flag: Accelerated blits with Colorkey */
	Uint32 blit_hw_A    :1;	/* Flag: Accelerated blits with Alpha */
	Uint32 blit_sw      :1;	/* Flag: Accelerated blits SW --> HW */
	Uint32 blit_sw_CC   :1;	/* Flag: Accelerated blits with Colorkey */
	Uint32 blit_sw_A    :1;	/* Flag: Accelerated blits with Alpha */
	Uint32 blit_fill    :1;	/* Flag: Accelerated color fill */
	Uint32 UnusedBits3  :16;
	Uint32 video_mem;	/* The total amount of video memory (in K) */
	SDL_PixelFormat *vfmt;	/* Value: The format of the video surface */
} SDL_VideoInfo;

/* The YUV hardware video overlay */
typedef struct {
	Uint32 format;				/* Read-only */
	int w, h;				/* Read-only */
	int planes;				/* Read-only */
	Uint16 *pitches;			/* Read-only */
	Uint8 **pixels;				/* Read-write */

	/* Hardware-specific surface info */
	struct private_yuvhwfuncs *hwfuncs;
	struct private_yuvhwdata *hwdata;

	/* Special flags */
	Uint32 hw_overlay :1;	/* Flag: This overlay hardware accelerated? */
	Uint32 UnusedBits :31;
} SDL_Overlay;

/* The YUV hardware video overlay */
//typedef struct SDL_Overlay {
//	Uint32 format;				/* Read-only */
//	int w, h;				/* Read-only */
//	int planes;				/* Read-only */
//	Uint16 *pitches;			/* Read-only */
//	Uint8 **pixels;				/* Read-write */
//
//	/* Hardware-specific surface info */
//	struct private_yuvhwfuncs *hwfuncs;
//	struct private_yuvhwdata *hwdata;
//
//	/* Special flags */
//	Uint32 hw_overlay :1;	/* Flag: This overlay hardware accelerated? */
//	Uint32 UnusedBits :31;
//} SDL_Overlay;

/* Public enumeration for setting the OpenGL window attributes. */
typedef enum {
  SDL_GL_RED_SIZE,
  SDL_GL_GREEN_SIZE,
  SDL_GL_BLUE_SIZE,
  SDL_GL_ALPHA_SIZE,
  SDL_GL_BUFFER_SIZE,
  SDL_GL_DOUBLEBUFFER,
  SDL_GL_DEPTH_SIZE,
  SDL_GL_STENCIL_SIZE,
  SDL_GL_ACCUM_RED_SIZE,
  SDL_GL_ACCUM_GREEN_SIZE,
  SDL_GL_ACCUM_BLUE_SIZE,
  SDL_GL_ACCUM_ALPHA_SIZE,
  SDL_GL_STEREO,
  SDL_GL_MULTISAMPLEBUFFERS,
  SDL_GL_MULTISAMPLESAMPLES
} SDL_GLattr;

/* flags for SDL_SetPalette() */
#define SDL_LOGPAL 0x01
#define SDL_PHYSPAL 0x02


////////////////////////////////////////////////////////////////////////
// FUNCTIONS
extern char * SDL_VideoDriverName(char *namebuf, int maxlen);
extern SDL_Surface * SDL_GetVideoSurface(void);
extern const SDL_VideoInfo * SDL_GetVideoInfo(void);

extern int SDL_VideoModeOK(int width, int height, int bpp, Uint32 flags);
extern SDL_Rect ** SDL_ListModes(SDL_PixelFormat *format, Uint32 flags);
extern SDL_Surface * SDL_SetVideoMode(int width, int height, int bpp, Uint32 flags);
extern void SDL_UpdateRects(SDL_Surface *screen, int numrects, SDL_Rect *rects);
extern void SDL_UpdateRect(SDL_Surface *screen, Sint32 x, Sint32 y, Uint32 w, Uint32 h);
extern int SDL_Flip(SDL_Surface *screen);
extern  int  SDL_SetGamma(float red, float green, float blue);
extern  int  SDL_GetGammaRamp(Uint16 *INOUT, Uint16 *INOUT, Uint16 *INOUT);
extern  int  SDL_SetColors(SDL_Surface *surface, SDL_Color *colors, int firstcolor, int ncolors);
extern  int  SDL_SetPalette(SDL_Surface *surface, int flags, SDL_Color *colors, int firstcolor, int ncolors);
extern  Uint32  SDL_MapRGB (SDL_PixelFormat *format, Uint8 r, Uint8 g, Uint8 b);
extern  Uint32  SDL_MapRGBA(SDL_PixelFormat *format, Uint8 r, Uint8 g, Uint8 b, Uint8 a);
extern  void  SDL_GetRGB(Uint32 pixel, SDL_PixelFormat *fmt, Uint8 *OUTPUT, Uint8 *OUTPUT, Uint8 *OUTPUT);
extern  void  SDL_GetRGBA(Uint32 pixel, SDL_PixelFormat *fmt, Uint8 *OUTPUT, Uint8 *OUTPUT, Uint8 *OUTPUT, Uint8 *OUTPUT);
extern  SDL_Surface *  SDL_CreateRGBSurface (Uint32 flags, int width, int height, int depth, Uint32 Rmask, Uint32 Gmask, Uint32 Bmask, Uint32 Amask);
//extern  SDL_Surface *  SDL_CreateRGBSurfaceFrom(void *pixels, int width, int height, int depth, int pitch, Uint32 Rmask, Uint32 Gmask, Uint32 Bmask, Uint32 Amask);
extern  void  SDL_FreeSurface(SDL_Surface *surface);
extern  int  SDL_LockSurface(SDL_Surface *surface);
extern  void  SDL_UnlockSurface(SDL_Surface *surface);
extern  int  SDL_SetColorKey
			(SDL_Surface *surface, Uint32 flag, Uint32 key);
extern  int  SDL_SetAlpha(SDL_Surface *surface, Uint32 flag, Uint8 alpha);
extern int SWIG_SDL_SetClipRect(SDL_Surface * surface, SDL_Rect *rect);
extern  void  SDL_GetClipRect(SDL_Surface *surface, SDL_Rect *rect);
extern  SDL_Surface *  SDL_ConvertSurface
			(SDL_Surface *src, SDL_PixelFormat *fmt, Uint32 flags);

extern  SDL_Surface *  SDL_DisplayFormat(SDL_Surface *surface);
extern  SDL_Surface *  SDL_DisplayFormatAlpha(SDL_Surface *surface);

extern void SDL_WarpMouse(Uint16 x, Uint16 y);
extern SDL_Cursor * SDL_CreateCursor(Uint8 *data, Uint8 *mask, int w, int h, int hot_x, int hot_y);

extern  SDL_Overlay *SDL_CreateYUVOverlay(int width, int height, Uint32 format, SDL_Surface *display);
extern  int  SDL_LockYUVOverlay(SDL_Overlay *overlay);
extern  void  SDL_UnlockYUVOverlay(SDL_Overlay *overlay);
extern  int  SDL_DisplayYUVOverlay(SDL_Overlay *overlay, SDL_Rect *dstrect);
extern  void  SDL_FreeYUVOverlay(SDL_Overlay *overlay);

extern  int  SDL_GL_LoadLibrary(const char *path);
//extern  void *  SDL_GL_GetProcAddress(const char* proc);
extern  int  SDL_GL_SetAttribute(SDL_GLattr attr, int value);
extern  int  SDL_GL_GetAttribute(SDL_GLattr attr, int* value);
extern  void  SDL_GL_SwapBuffers(void);
extern  void  SDL_GL_UpdateRects(int numrects, SDL_Rect* rects);
extern  void  SDL_GL_Lock(void);
extern  void  SDL_GL_Unlock(void);

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/* These functions allow interaction with the window manager, if any.        */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*
 * Sets/Gets the title and icon text of the display window
 */
extern  void  SDL_WM_SetCaption(const char *title, const char *icon);
extern  void SWIG_SDL_WM_GetCaption(char * OUTPUT, char *OUTPUT);

/*
 * Sets the icon for the display window.
 * This function must be called before the first call to SDL_SetVideoMode().
 * It takes an icon surface, and a mask in MSB format.
 * If 'mask' is NULL, the entire icon surface will be used as the icon.
 */
extern  void  SDL_WM_SetIcon(SDL_Surface *icon, Uint8 *INPUT);

/*
 * This function iconifies the window, and returns 1 if it succeeded.
 * If the function succeeds, it generates an SDL_APPACTIVE loss event.
 * This function is a noop and returns 0 in non-windowed environments.
 */
extern  int  SDL_WM_IconifyWindow(void);

/*
 * Toggle fullscreen mode without changing the contents of the screen.
 * If the display surface does not require locking before accessing
 * the pixel information, then the memory pointers will not change.
 *
 * If this function was able to toggle fullscreen mode (change from 
 * running in a window to fullscreen, or vice-versa), it will return 1.
 * If it is not implemented, or fails, it returns 0.
 *
 * The next call to SDL_SetVideoMode() will set the mode fullscreen
 * attribute based on the flags parameter - if SDL_FULLSCREEN is not
 * set, then the display will be windowed by default where supported.
 *
 * This is currently only implemented in the X11 video driver.
 */
extern  int  SDL_WM_ToggleFullScreen(SDL_Surface *surface);

/*
 * This function allows you to set and query the input grab state of
 * the application.  It returns the new input grab state.
 */
typedef int SDL_GrabMode;

enum SDL_GrabModeValues{
	SDL_GRAB_QUERY = -1,
	SDL_GRAB_OFF = 0,
	SDL_GRAB_ON = 1,
	SDL_GRAB_FULLSCREEN	/* Used internally */
};
/*
 * Grabbing means that the mouse is confined to the application window,
 * and nearly all keyboard input is passed directly to the application,
 * and not interpreted by a window manager, if any.
 */
extern  SDL_GrabMode  SDL_WM_GrabInput(SDL_GrabMode mode);

//////////////////////////////////////////////////////////////////////
extern SDL_Surface * SWIG_SDL_LoadBMP(char * file);
extern int SWIG_SDL_SaveBMP(SDL_Surface *surface, const char *file);
extern int SWIG_SDL_FillRect_FAST(SDL_Surface *dst, int x, int y, int w, int h, Uint32 color);
extern int SWIG_SDL_BlitSurface_FAST(SDL_Surface * src, int sX, int sY, int sWidth, int sHeight, SDL_Surface *dst, int dX, int dY, int dWidth, int dHeight);
extern int SWIG_SDL_SetColors(SDL_Surface * surface, SDL_Color colors[], int firstcolor, int ncolors);
extern int SWIG_SDL_SetPalette(SDL_Surface *surface, int flags, SDL_Color colors[], int firstcolor, int ncolors);
extern void SWIG_GetPaletteColors(SDL_Palette * palette, SDL_Color colors[]);

extern int SWIG_SDL_SetGammaRamp( Uint16 red[],  Uint16 green[],  Uint16 blue[]);
extern SDL_Surface * SWIG_SDL_CreateRGBSurfaceFrom(Uint16 pixels[], int width, int height, int depth, int pitch, Uint32 Rmask, Uint32 Gmask, Uint32 bMask, Uint32 aMask);

extern void SWIG_GetPixelData32(SDL_Surface *surface, int data[]);
extern void SWIG_GetPixelData16(SDL_Surface *surface, Uint16 pixelData[]);
extern void SWIG_GetPixelData8(SDL_Surface *surface, Uint8 pixelData[]);
extern void SWIG_SetPixelData32(SDL_Surface *surface, int data[]);
extern void SWIG_SetPixelData16(SDL_Surface *surface, Uint16 pixelData[]);
extern void SWIG_SetPixelData8(SDL_Surface *surface, Uint8 pixelData[]);


extern void SWIG_executeBlitQueue(void *vsrcX, void *vsrcY, void *vsrcWidth, void *vsrcHeight, SDL_Surface *src, void *vdstX, void *vdstY, void *vdstWidth, void *vdstHeight, SDL_Surface *dst, int count);

extern int SWIG_SDL_MUSTLOCK(SDL_Surface *surface);
extern jobject SWIG_getPixelDirectByteBuffer(SDL_Surface *surface);
extern jobject SWIG_getOverlayPixelsDirectByteBuffer(SDL_Overlay* overlay, int plane);
int SWIG_displayYUVOverlay(SDL_Overlay* overlay, int x, int y, int w, int h);
