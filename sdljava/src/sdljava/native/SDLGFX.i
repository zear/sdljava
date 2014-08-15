%module SWIG_SDLGfx
%{
  #include "SDL_gfxPrimitives.h"
  #include "SDL_rotozoom.h"

  int SWIG_polygonColor(SDL_Surface *dst, void* vx, void* vy, int n, Uint32 color) {
    return polygonColor(dst, (Sint16*)vx, (Sint16*)vy, n, color);
  }

  int SWIG_polygonRGBA(SDL_Surface *dst, void* vx, void* vy, int n, Uint8 r, Uint8 g, Uint8 b, Uint8 a) {
    return polygonRGBA(dst, (Sint16*)vx, (Sint16*)vy, n, r, g, b, a);
  }

  int SWIG_aapolygonColor(SDL_Surface *dst, void* vx, void* vy, int n, Uint32 color) {
    return aapolygonColor(dst, (Sint16*)vx, (Sint16*)vy, n, color);
  }

  int SWIG_aapolygonRGBA(SDL_Surface *dst, void* vx, void* vy, int n, Uint8 r, Uint8 g, Uint8 b, Uint8 a) {
    return aapolygonRGBA(dst, (Sint16*)vx, (Sint16*)vy, n, r, g, b, a);
  }

  int SWIG_filledPolygonColor(SDL_Surface *dst, void* vx, void* vy, int n, int color) {
    return filledPolygonColor(dst, (Sint16*)vx, (Sint16*)vy, n, color);
  }

  int SWIG_filledPolygonRGBA(SDL_Surface *dst, void* vx, void* vy, int n, Uint8 r, Uint8 g, Uint8 b, Uint8 a) {
    return filledPolygonRGBA(dst, (Sint16*)vx, (Sint16*)vy, n, r, g, b, a);
  }

  int SWIG_bezierColor(SDL_Surface *dst, void* vx, void* vy, int n, int s, Uint32 color) {
    return bezierColor(dst, (Sint16*)vx, (Sint16*)vy, n, s, color);
  }

  int SWIG_bezierRGBA(SDL_Surface *dst, void* vx, void* vy, int n, int s, Uint8 r, Uint8 g, Uint8 b, Uint8 a) {
    return bezierRGBA(dst, (Sint16*)vx, (Sint16*)vy, n, s, r, g, b, a);
  }
%}

%javaconst(1);
%import "typemaps.i"
%import "SDL_types.h"
%import "SDLVideo.i"
%include "arrays_java.i";

%pragma(java) jniclassimports=%{
import java.nio.*;
%}

%pragma(java) moduleimports=%{
import java.nio.*;
%}

%pragma(java) jniclasscode=%{
  static {
    try {
      // if set don't loadLibrary ourselves, let client of library do it
      if (System.getProperty("sdljava.bootclasspath") == null) {
        System.loadLibrary("sdljava_gfx");
      }
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load. \n" + e);
      System.exit(1);
    }
  }
%}

/* Note: all ___Color routines expect the color to be in format 0xRRGGBBAA */

/* Pixel */

     int pixelColor(SDL_Surface * dst, Sint16 x, Sint16 y, Uint32 color);
     int pixelRGBA(SDL_Surface * dst, Sint16 x, Sint16 y, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Horizontal line */

     int hlineColor(SDL_Surface * dst, Sint16 x1, Sint16 x2, Sint16 y, Uint32 color);
     int hlineRGBA(SDL_Surface * dst, Sint16 x1, Sint16 x2, Sint16 y, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Vertical line */

     int vlineColor(SDL_Surface * dst, Sint16 x, Sint16 y1, Sint16 y2, Uint32 color);
     int vlineRGBA(SDL_Surface * dst, Sint16 x, Sint16 y1, Sint16 y2, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Rectangle */

     int rectangleColor(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Uint32 color);
     int rectangleRGBA(SDL_Surface * dst, Sint16 x1, Sint16 y1,
				   Sint16 x2, Sint16 y2, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Filled rectangle (Box) */

     int boxColor(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Uint32 color);
     int boxRGBA(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2,
			     Sint16 y2, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Line */

     int lineColor(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Uint32 color);
     int lineRGBA(SDL_Surface * dst, Sint16 x1, Sint16 y1,
			      Sint16 x2, Sint16 y2, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* AA Line */
     int aalineColor(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Uint32 color);
     int aalineRGBA(SDL_Surface * dst, Sint16 x1, Sint16 y1,
				Sint16 x2, Sint16 y2, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Circle */

     int circleColor(SDL_Surface * dst, Sint16 x, Sint16 y, Sint16 r, Uint32 color);
     int circleRGBA(SDL_Surface * dst, Sint16 x, Sint16 y, Sint16 rad, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* AA Circle */

     int aacircleColor(SDL_Surface * dst, Sint16 x, Sint16 y, Sint16 r, Uint32 color);
     int aacircleRGBA(SDL_Surface * dst, Sint16 x, Sint16 y,
				  Sint16 rad, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Filled Circle */

     int filledCircleColor(SDL_Surface * dst, Sint16 x, Sint16 y, Sint16 r, Uint32 color);
     int filledCircleRGBA(SDL_Surface * dst, Sint16 x, Sint16 y,
				      Sint16 rad, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Ellipse */

     int ellipseColor(SDL_Surface * dst, Sint16 x, Sint16 y, Sint16 rx, Sint16 ry, Uint32 color);
     int ellipseRGBA(SDL_Surface * dst, Sint16 x, Sint16 y,
				 Sint16 rx, Sint16 ry, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* AA Ellipse */

     int aaellipseColor(SDL_Surface * dst, Sint16 xc, Sint16 yc, Sint16 rx, Sint16 ry, Uint32 color);
     int aaellipseRGBA(SDL_Surface * dst, Sint16 x, Sint16 y,
				   Sint16 rx, Sint16 ry, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Filled Ellipse */

     int filledEllipseColor(SDL_Surface * dst, Sint16 x, Sint16 y, Sint16 rx, Sint16 ry, Uint32 color);
     int filledEllipseRGBA(SDL_Surface * dst, Sint16 x, Sint16 y,
				       Sint16 rx, Sint16 ry, Uint8 r, Uint8 g, Uint8 b, Uint8 a);
/* Filled Pie */

     int filledPieColor(SDL_Surface * dst, Sint16 x, Sint16 y, Sint16 rad,
				    Sint16 start, Sint16 end, Uint32 color);
     int filledPieRGBA(SDL_Surface * dst, Sint16 x, Sint16 y, Sint16 rad,
				   Sint16 start, Sint16 end, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Trigon */

     int trigonColor(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Sint16 x3, Sint16 y3, Uint32 color);
     int trigonRGBA(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Sint16 x3, Sint16 y3,
				 Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* AA-Trigon */

     int aatrigonColor(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Sint16 x3, Sint16 y3, Uint32 color);
     int aatrigonRGBA(SDL_Surface * dst,  Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Sint16 x3, Sint16 y3,
				   Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Filled Trigon */

     int filledTrigonColor(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Sint16 x3, Sint16 y3, int color);
     int filledTrigonRGBA(SDL_Surface * dst, Sint16 x1, Sint16 y1, Sint16 x2, Sint16 y2, Sint16 x3, Sint16 y3,
				       Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Polygon */

     int polygonColor(SDL_Surface * dst, Sint16 * vx, Sint16 * vy, int n, Uint32 color);
     int polygonRGBA(SDL_Surface * dst, Sint16 * vx, Sint16 * vy, int n, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* AA-Polygon */

     int aapolygonColor(SDL_Surface * dst, Sint16 * vx, Sint16 * vy, int n, Uint32 color);
     int aapolygonRGBA(SDL_Surface * dst, Sint16 * vx, Sint16 * vy,
				   int n, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Filled Polygon */

     int filledPolygonColor(SDL_Surface * dst, Sint16 * vx, Sint16 * vy, int n, int color);
     int filledPolygonRGBA(SDL_Surface * dst, Sint16 * vx,
				       Sint16 * vy, int n, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* Bezier */
/* s = number of steps */

     int bezierColor(SDL_Surface * dst, Sint16 * vx, Sint16 * vy, int n, int s, Uint32 color);
     int bezierRGBA(SDL_Surface * dst, Sint16 * vx, Sint16 * vy, int n, int s, Uint8 r, Uint8 g, Uint8 b, Uint8 a);


/* 8x8 Characters/Strings */

     int characterColor(SDL_Surface * dst, Sint16 x, Sint16 y, char c, Uint32 color);
     int characterRGBA(SDL_Surface * dst, Sint16 x, Sint16 y, char c, Uint8 r, Uint8 g, Uint8 b, Uint8 a);
     int stringColor(SDL_Surface * dst, Sint16 x, Sint16 y, char *c, Uint32 color);
     int stringRGBA(SDL_Surface * dst, Sint16 x, Sint16 y, char *c, Uint8 r, Uint8 g, Uint8 b, Uint8 a);

/* 
 
 rotozoomSurface()

 Rotates and zoomes a 32bit or 8bit 'src' surface to newly created 'dst' surface.
 'angle' is the rotation in degrees. 'zoom' a scaling factor. If 'smooth' is 1
 then the destination 32bit surface is anti-aliased. If the surface is not 8bit
 or 32bit RGBA/ABGR it will be converted into a 32bit RGBA format on the fly.

*/

     SDL_Surface *rotozoomSurface(SDL_Surface * src, double angle, double zoom, int smooth);


/* Returns the size of the target surface for a rotozoomSurface() call */

     void rotozoomSurfaceSize(int width, int height, double angle, double zoom, int *OUTPUT, int *OUTPUT);

/* 
 
 zoomSurface()

 Zoomes a 32bit or 8bit 'src' surface to newly created 'dst' surface.
 'zoomx' and 'zoomy' are scaling factors for width and height. If 'smooth' is 1
 then the destination 32bit surface is anti-aliased. If the surface is not 8bit
 or 32bit RGBA/ABGR it will be converted into a 32bit RGBA format on the fly.

*/

     SDL_Surface *zoomSurface(SDL_Surface * src, double zoomx, double zoomy, int smooth);

/* Returns the size of the target surface for a zoomSurface() call */

     void zoomSurfaceSize(int width, int height, double zoomx, double zoomy, int *OUTPUT, int *OUTPUT);


int SWIG_polygonColor(SDL_Surface *dst, void* vx, void* vy, int n, Uint32 color);
int SWIG_polygonRGBA(SDL_Surface *dst, void* vx, void* vy, int n, Uint8 r, Uint8 g, Uint8 b, Uint8 a);
int SWIG_aapolygonColor(SDL_Surface *dst, void* vx, void* vy, int n, Uint32 color);
int SWIG_aapolygonRGBA(SDL_Surface *dst, void *vx, void *vy, int n, Uint8 r, Uint8 g, Uint8 b, Uint8 a);
int SWIG_filledPolygonColor(SDL_Surface *dst, void* vx, void* vy, int n, int color);
int SWIG_filledPolygonRGBA(SDL_Surface *dst, void* vx, void* vy, int n, Uint8 r, Uint8 g, Uint8 b, Uint8 a);
int SWIG_bezierColor(SDL_Surface *dst, void* vx, void* vy, int n, int s, Uint32 color);
int SWIG_bezierRGBA(SDL_Surface *dst, void* vx, void* vy, int n, int s, Uint8 r, Uint8 g, Uint8 b, Uint8 a);
