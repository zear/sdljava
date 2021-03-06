/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version: 1.3.22
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdljava.x.swig;

public class SDL_Color {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected SDL_Color(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_Color obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      SWIG_SDLVideoJNI.delete_SDL_Color(swigCPtr);
    }
    swigCPtr = 0;
  }

  public void setR(short r) {
    SWIG_SDLVideoJNI.set_SDL_Color_r(swigCPtr, r);
  }

  public short getR() {
    return SWIG_SDLVideoJNI.get_SDL_Color_r(swigCPtr);
  }

  public void setG(short g) {
    SWIG_SDLVideoJNI.set_SDL_Color_g(swigCPtr, g);
  }

  public short getG() {
    return SWIG_SDLVideoJNI.get_SDL_Color_g(swigCPtr);
  }

  public void setB(short b) {
    SWIG_SDLVideoJNI.set_SDL_Color_b(swigCPtr, b);
  }

  public short getB() {
    return SWIG_SDLVideoJNI.get_SDL_Color_b(swigCPtr);
  }

  public void setUnused(short unused) {
    SWIG_SDLVideoJNI.set_SDL_Color_unused(swigCPtr, unused);
  }

  public short getUnused() {
    return SWIG_SDLVideoJNI.get_SDL_Color_unused(swigCPtr);
  }

  public SDL_Color() {
    this(SWIG_SDLVideoJNI.new_SDL_Color(), true);
  }

}
