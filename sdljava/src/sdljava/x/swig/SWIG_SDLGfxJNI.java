/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version: 1.3.22
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdljava.x.swig;

import java.nio.*;
import java.util.Locale;

class SWIG_SDLGfxJNI {

  static {
    try {
      // if set don't loadLibrary ourselves, let client of library do it
      if (System.getProperty("sdljava.bootclasspath") == null) {
	String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
	if(OS.indexOf("mac") >= 0 || OS.indexOf("darwin") >= 0) {
		System.load(System.getProperty("user.dir")+ "/" + "libsdljava_gfx.so");
	}
	else {
		System.loadLibrary("sdljava_gfx");
	}
      }
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load. \n" + e);
      System.exit(1);
    }
  }

  public final static native int pixelColor(long jarg1, short jarg2, short jarg3, long jarg4);
  public final static native int pixelRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7);
  public final static native int hlineColor(long jarg1, short jarg2, short jarg3, short jarg4, long jarg5);
  public final static native int hlineRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int vlineColor(long jarg1, short jarg2, short jarg3, short jarg4, long jarg5);
  public final static native int vlineRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int rectangleColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, long jarg6);
  public final static native int rectangleRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9);
  public final static native int boxColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, long jarg6);
  public final static native int boxRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9);
  public final static native int lineColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, long jarg6);
  public final static native int lineRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9);
  public final static native int aalineColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, long jarg6);
  public final static native int aalineRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9);
  public final static native int circleColor(long jarg1, short jarg2, short jarg3, short jarg4, long jarg5);
  public final static native int circleRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int aacircleColor(long jarg1, short jarg2, short jarg3, short jarg4, long jarg5);
  public final static native int aacircleRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int filledCircleColor(long jarg1, short jarg2, short jarg3, short jarg4, long jarg5);
  public final static native int filledCircleRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int ellipseColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, long jarg6);
  public final static native int ellipseRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9);
  public final static native int aaellipseColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, long jarg6);
  public final static native int aaellipseRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9);
  public final static native int filledEllipseColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, long jarg6);
  public final static native int filledEllipseRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9);
  public final static native int filledPieColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, long jarg7);
  public final static native int filledPieRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9, short jarg10);
  public final static native int trigonColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, long jarg8);
  public final static native int trigonRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9, short jarg10, short jarg11);
  public final static native int aatrigonColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, long jarg8);
  public final static native int aatrigonRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9, short jarg10, short jarg11);
  public final static native int filledTrigonColor(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, int jarg8);
  public final static native int filledTrigonRGBA(long jarg1, short jarg2, short jarg3, short jarg4, short jarg5, short jarg6, short jarg7, short jarg8, short jarg9, short jarg10, short jarg11);
  public final static native int polygonColor(long jarg1, long jarg2, long jarg3, int jarg4, long jarg5);
  public final static native int polygonRGBA(long jarg1, long jarg2, long jarg3, int jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int aapolygonColor(long jarg1, long jarg2, long jarg3, int jarg4, long jarg5);
  public final static native int aapolygonRGBA(long jarg1, long jarg2, long jarg3, int jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int filledPolygonColor(long jarg1, long jarg2, long jarg3, int jarg4, int jarg5);
  public final static native int filledPolygonRGBA(long jarg1, long jarg2, long jarg3, int jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int bezierColor(long jarg1, long jarg2, long jarg3, int jarg4, int jarg5, long jarg6);
  public final static native int bezierRGBA(long jarg1, long jarg2, long jarg3, int jarg4, int jarg5, short jarg6, short jarg7, short jarg8, short jarg9);
  public final static native int characterColor(long jarg1, short jarg2, short jarg3, char jarg4, long jarg5);
  public final static native int characterRGBA(long jarg1, short jarg2, short jarg3, char jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int stringColor(long jarg1, short jarg2, short jarg3, String jarg4, long jarg5);
  public final static native int stringRGBA(long jarg1, short jarg2, short jarg3, String jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native long rotozoomSurface(long jarg1, double jarg2, double jarg3, int jarg4);
  public final static native void rotozoomSurfaceSize(int jarg1, int jarg2, double jarg3, double jarg4, int[] jarg5, int[] jarg6);
  public final static native long zoomSurface(long jarg1, double jarg2, double jarg3, int jarg4);
  public final static native void zoomSurfaceSize(int jarg1, int jarg2, double jarg3, double jarg4, int[] jarg5, int[] jarg6);
  public final static native int SWIG_polygonColor(long jarg1, Buffer jarg2, Buffer jarg3, int jarg4, long jarg5);
  public final static native int SWIG_polygonRGBA(long jarg1, Buffer jarg2, Buffer jarg3, int jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int SWIG_aapolygonColor(long jarg1, Buffer jarg2, Buffer jarg3, int jarg4, long jarg5);
  public final static native int SWIG_aapolygonRGBA(long jarg1, Buffer jarg2, Buffer jarg3, int jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int SWIG_filledPolygonColor(long jarg1, Buffer jarg2, Buffer jarg3, int jarg4, int jarg5);
  public final static native int SWIG_filledPolygonRGBA(long jarg1, Buffer jarg2, Buffer jarg3, int jarg4, short jarg5, short jarg6, short jarg7, short jarg8);
  public final static native int SWIG_bezierColor(long jarg1, Buffer jarg2, Buffer jarg3, int jarg4, int jarg5, long jarg6);
  public final static native int SWIG_bezierRGBA(long jarg1, Buffer jarg2, Buffer jarg3, int jarg4, int jarg5, short jarg6, short jarg7, short jarg8, short jarg9);
}
