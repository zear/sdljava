/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version: 1.3.22
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdljava.x.swig;

public class SDL_ExposeEvent {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected SDL_ExposeEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_ExposeEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      SWIG_SDLEventJNI.delete_SDL_ExposeEvent(swigCPtr);
    }
    swigCPtr = 0;
  }

  public void setType(short type) {
    SWIG_SDLEventJNI.set_SDL_ExposeEvent_type(swigCPtr, type);
  }

  public short getType() {
    return SWIG_SDLEventJNI.get_SDL_ExposeEvent_type(swigCPtr);
  }

  public SDL_ExposeEvent() {
    this(SWIG_SDLEventJNI.new_SDL_ExposeEvent(), true);
  }

}
