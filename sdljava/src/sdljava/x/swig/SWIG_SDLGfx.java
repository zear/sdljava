/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version: 1.3.22
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdljava.x.swig;

import java.nio.*;

public class SWIG_SDLGfx {
  public static int pixelColor(SDL_Surface dst, short x, short y, long color) {
    return SWIG_SDLGfxJNI.pixelColor(SDL_Surface.getCPtr(dst), x, y, color);
  }

  public static int pixelRGBA(SDL_Surface dst, short x, short y, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.pixelRGBA(SDL_Surface.getCPtr(dst), x, y, r, g, b, a);
  }

  public static int hlineColor(SDL_Surface dst, short x1, short x2, short y, long color) {
    return SWIG_SDLGfxJNI.hlineColor(SDL_Surface.getCPtr(dst), x1, x2, y, color);
  }

  public static int hlineRGBA(SDL_Surface dst, short x1, short x2, short y, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.hlineRGBA(SDL_Surface.getCPtr(dst), x1, x2, y, r, g, b, a);
  }

  public static int vlineColor(SDL_Surface dst, short x, short y1, short y2, long color) {
    return SWIG_SDLGfxJNI.vlineColor(SDL_Surface.getCPtr(dst), x, y1, y2, color);
  }

  public static int vlineRGBA(SDL_Surface dst, short x, short y1, short y2, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.vlineRGBA(SDL_Surface.getCPtr(dst), x, y1, y2, r, g, b, a);
  }

  public static int rectangleColor(SDL_Surface dst, short x1, short y1, short x2, short y2, long color) {
    return SWIG_SDLGfxJNI.rectangleColor(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, color);
  }

  public static int rectangleRGBA(SDL_Surface dst, short x1, short y1, short x2, short y2, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.rectangleRGBA(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, r, g, b, a);
  }

  public static int boxColor(SDL_Surface dst, short x1, short y1, short x2, short y2, long color) {
    return SWIG_SDLGfxJNI.boxColor(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, color);
  }

  public static int boxRGBA(SDL_Surface dst, short x1, short y1, short x2, short y2, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.boxRGBA(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, r, g, b, a);
  }

  public static int lineColor(SDL_Surface dst, short x1, short y1, short x2, short y2, long color) {
    return SWIG_SDLGfxJNI.lineColor(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, color);
  }

  public static int lineRGBA(SDL_Surface dst, short x1, short y1, short x2, short y2, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.lineRGBA(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, r, g, b, a);
  }

  public static int aalineColor(SDL_Surface dst, short x1, short y1, short x2, short y2, long color) {
    return SWIG_SDLGfxJNI.aalineColor(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, color);
  }

  public static int aalineRGBA(SDL_Surface dst, short x1, short y1, short x2, short y2, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.aalineRGBA(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, r, g, b, a);
  }

  public static int circleColor(SDL_Surface dst, short x, short y, short r, long color) {
    return SWIG_SDLGfxJNI.circleColor(SDL_Surface.getCPtr(dst), x, y, r, color);
  }

  public static int circleRGBA(SDL_Surface dst, short x, short y, short rad, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.circleRGBA(SDL_Surface.getCPtr(dst), x, y, rad, r, g, b, a);
  }

  public static int aacircleColor(SDL_Surface dst, short x, short y, short r, long color) {
    return SWIG_SDLGfxJNI.aacircleColor(SDL_Surface.getCPtr(dst), x, y, r, color);
  }

  public static int aacircleRGBA(SDL_Surface dst, short x, short y, short rad, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.aacircleRGBA(SDL_Surface.getCPtr(dst), x, y, rad, r, g, b, a);
  }

  public static int filledCircleColor(SDL_Surface dst, short x, short y, short r, long color) {
    return SWIG_SDLGfxJNI.filledCircleColor(SDL_Surface.getCPtr(dst), x, y, r, color);
  }

  public static int filledCircleRGBA(SDL_Surface dst, short x, short y, short rad, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.filledCircleRGBA(SDL_Surface.getCPtr(dst), x, y, rad, r, g, b, a);
  }

  public static int ellipseColor(SDL_Surface dst, short x, short y, short rx, short ry, long color) {
    return SWIG_SDLGfxJNI.ellipseColor(SDL_Surface.getCPtr(dst), x, y, rx, ry, color);
  }

  public static int ellipseRGBA(SDL_Surface dst, short x, short y, short rx, short ry, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.ellipseRGBA(SDL_Surface.getCPtr(dst), x, y, rx, ry, r, g, b, a);
  }

  public static int aaellipseColor(SDL_Surface dst, short xc, short yc, short rx, short ry, long color) {
    return SWIG_SDLGfxJNI.aaellipseColor(SDL_Surface.getCPtr(dst), xc, yc, rx, ry, color);
  }

  public static int aaellipseRGBA(SDL_Surface dst, short x, short y, short rx, short ry, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.aaellipseRGBA(SDL_Surface.getCPtr(dst), x, y, rx, ry, r, g, b, a);
  }

  public static int filledEllipseColor(SDL_Surface dst, short x, short y, short rx, short ry, long color) {
    return SWIG_SDLGfxJNI.filledEllipseColor(SDL_Surface.getCPtr(dst), x, y, rx, ry, color);
  }

  public static int filledEllipseRGBA(SDL_Surface dst, short x, short y, short rx, short ry, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.filledEllipseRGBA(SDL_Surface.getCPtr(dst), x, y, rx, ry, r, g, b, a);
  }

  public static int filledPieColor(SDL_Surface dst, short x, short y, short rad, short start, short end, long color) {
    return SWIG_SDLGfxJNI.filledPieColor(SDL_Surface.getCPtr(dst), x, y, rad, start, end, color);
  }

  public static int filledPieRGBA(SDL_Surface dst, short x, short y, short rad, short start, short end, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.filledPieRGBA(SDL_Surface.getCPtr(dst), x, y, rad, start, end, r, g, b, a);
  }

  public static int trigonColor(SDL_Surface dst, short x1, short y1, short x2, short y2, short x3, short y3, long color) {
    return SWIG_SDLGfxJNI.trigonColor(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, x3, y3, color);
  }

  public static int trigonRGBA(SDL_Surface dst, short x1, short y1, short x2, short y2, short x3, short y3, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.trigonRGBA(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, x3, y3, r, g, b, a);
  }

  public static int aatrigonColor(SDL_Surface dst, short x1, short y1, short x2, short y2, short x3, short y3, long color) {
    return SWIG_SDLGfxJNI.aatrigonColor(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, x3, y3, color);
  }

  public static int aatrigonRGBA(SDL_Surface dst, short x1, short y1, short x2, short y2, short x3, short y3, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.aatrigonRGBA(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, x3, y3, r, g, b, a);
  }

  public static int filledTrigonColor(SDL_Surface dst, short x1, short y1, short x2, short y2, short x3, short y3, int color) {
    return SWIG_SDLGfxJNI.filledTrigonColor(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, x3, y3, color);
  }

  public static int filledTrigonRGBA(SDL_Surface dst, short x1, short y1, short x2, short y2, short x3, short y3, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.filledTrigonRGBA(SDL_Surface.getCPtr(dst), x1, y1, x2, y2, x3, y3, r, g, b, a);
  }

  public static int polygonColor(SDL_Surface dst, SWIGTYPE_p_short vx, SWIGTYPE_p_short vy, int n, long color) {
    return SWIG_SDLGfxJNI.polygonColor(SDL_Surface.getCPtr(dst), SWIGTYPE_p_short.getCPtr(vx), SWIGTYPE_p_short.getCPtr(vy), n, color);
  }

  public static int polygonRGBA(SDL_Surface dst, SWIGTYPE_p_short vx, SWIGTYPE_p_short vy, int n, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.polygonRGBA(SDL_Surface.getCPtr(dst), SWIGTYPE_p_short.getCPtr(vx), SWIGTYPE_p_short.getCPtr(vy), n, r, g, b, a);
  }

  public static int aapolygonColor(SDL_Surface dst, SWIGTYPE_p_short vx, SWIGTYPE_p_short vy, int n, long color) {
    return SWIG_SDLGfxJNI.aapolygonColor(SDL_Surface.getCPtr(dst), SWIGTYPE_p_short.getCPtr(vx), SWIGTYPE_p_short.getCPtr(vy), n, color);
  }

  public static int aapolygonRGBA(SDL_Surface dst, SWIGTYPE_p_short vx, SWIGTYPE_p_short vy, int n, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.aapolygonRGBA(SDL_Surface.getCPtr(dst), SWIGTYPE_p_short.getCPtr(vx), SWIGTYPE_p_short.getCPtr(vy), n, r, g, b, a);
  }

  public static int filledPolygonColor(SDL_Surface dst, SWIGTYPE_p_short vx, SWIGTYPE_p_short vy, int n, int color) {
    return SWIG_SDLGfxJNI.filledPolygonColor(SDL_Surface.getCPtr(dst), SWIGTYPE_p_short.getCPtr(vx), SWIGTYPE_p_short.getCPtr(vy), n, color);
  }

  public static int filledPolygonRGBA(SDL_Surface dst, SWIGTYPE_p_short vx, SWIGTYPE_p_short vy, int n, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.filledPolygonRGBA(SDL_Surface.getCPtr(dst), SWIGTYPE_p_short.getCPtr(vx), SWIGTYPE_p_short.getCPtr(vy), n, r, g, b, a);
  }

  public static int bezierColor(SDL_Surface dst, SWIGTYPE_p_short vx, SWIGTYPE_p_short vy, int n, int s, long color) {
    return SWIG_SDLGfxJNI.bezierColor(SDL_Surface.getCPtr(dst), SWIGTYPE_p_short.getCPtr(vx), SWIGTYPE_p_short.getCPtr(vy), n, s, color);
  }

  public static int bezierRGBA(SDL_Surface dst, SWIGTYPE_p_short vx, SWIGTYPE_p_short vy, int n, int s, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.bezierRGBA(SDL_Surface.getCPtr(dst), SWIGTYPE_p_short.getCPtr(vx), SWIGTYPE_p_short.getCPtr(vy), n, s, r, g, b, a);
  }

  public static int characterColor(SDL_Surface dst, short x, short y, char c, long color) {
    return SWIG_SDLGfxJNI.characterColor(SDL_Surface.getCPtr(dst), x, y, c, color);
  }

  public static int characterRGBA(SDL_Surface dst, short x, short y, char c, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.characterRGBA(SDL_Surface.getCPtr(dst), x, y, c, r, g, b, a);
  }

  public static int stringColor(SDL_Surface dst, short x, short y, String c, long color) {
    return SWIG_SDLGfxJNI.stringColor(SDL_Surface.getCPtr(dst), x, y, c, color);
  }

  public static int stringRGBA(SDL_Surface dst, short x, short y, String c, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.stringRGBA(SDL_Surface.getCPtr(dst), x, y, c, r, g, b, a);
  }

  public static SDL_Surface rotozoomSurface(SDL_Surface src, double angle, double zoom, int smooth) {
    long cPtr = SWIG_SDLGfxJNI.rotozoomSurface(SDL_Surface.getCPtr(src), angle, zoom, smooth);
    return (cPtr == 0) ? null : new SDL_Surface(cPtr, true);
  }

  public static void rotozoomSurfaceSize(int width, int height, double angle, double zoom, int[] arg4, int[] arg5) {
    SWIG_SDLGfxJNI.rotozoomSurfaceSize(width, height, angle, zoom, arg4, arg5);
  }

  public static SDL_Surface zoomSurface(SDL_Surface src, double zoomx, double zoomy, int smooth) {
    long cPtr = SWIG_SDLGfxJNI.zoomSurface(SDL_Surface.getCPtr(src), zoomx, zoomy, smooth);
    return (cPtr == 0) ? null : new SDL_Surface(cPtr, true);
  }

  public static void zoomSurfaceSize(int width, int height, double zoomx, double zoomy, int[] arg4, int[] arg5) {
    SWIG_SDLGfxJNI.zoomSurfaceSize(width, height, zoomx, zoomy, arg4, arg5);
  }

  public static int SWIG_polygonColor(SDL_Surface dst, Buffer vx, Buffer vy, int n, long color) {
    return SWIG_SDLGfxJNI.SWIG_polygonColor(SDL_Surface.getCPtr(dst), vx, vy, n, color);
  }

  public static int SWIG_polygonRGBA(SDL_Surface dst, Buffer vx, Buffer vy, int n, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.SWIG_polygonRGBA(SDL_Surface.getCPtr(dst), vx, vy, n, r, g, b, a);
  }

  public static int SWIG_aapolygonColor(SDL_Surface dst, Buffer vx, Buffer vy, int n, long color) {
    return SWIG_SDLGfxJNI.SWIG_aapolygonColor(SDL_Surface.getCPtr(dst), vx, vy, n, color);
  }

  public static int SWIG_aapolygonRGBA(SDL_Surface dst, Buffer vx, Buffer vy, int n, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.SWIG_aapolygonRGBA(SDL_Surface.getCPtr(dst), vx, vy, n, r, g, b, a);
  }

  public static int SWIG_filledPolygonColor(SDL_Surface dst, Buffer vx, Buffer vy, int n, int color) {
    return SWIG_SDLGfxJNI.SWIG_filledPolygonColor(SDL_Surface.getCPtr(dst), vx, vy, n, color);
  }

  public static int SWIG_filledPolygonRGBA(SDL_Surface dst, Buffer vx, Buffer vy, int n, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.SWIG_filledPolygonRGBA(SDL_Surface.getCPtr(dst), vx, vy, n, r, g, b, a);
  }

  public static int SWIG_bezierColor(SDL_Surface dst, Buffer vx, Buffer vy, int n, int s, long color) {
    return SWIG_SDLGfxJNI.SWIG_bezierColor(SDL_Surface.getCPtr(dst), vx, vy, n, s, color);
  }

  public static int SWIG_bezierRGBA(SDL_Surface dst, Buffer vx, Buffer vy, int n, int s, short r, short g, short b, short a) {
    return SWIG_SDLGfxJNI.SWIG_bezierRGBA(SDL_Surface.getCPtr(dst), vx, vy, n, s, r, g, b, a);
  }

}
