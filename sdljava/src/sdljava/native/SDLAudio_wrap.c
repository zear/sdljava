/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.22
 * 
 * This file is not intended to be easily readable and contains a number of 
 * coding conventions designed to improve portability and efficiency. Do not make
 * changes to this file unless you know what you are doing--modify the SWIG 
 * interface file instead. 
 * ----------------------------------------------------------------------------- */


#if defined(__GNUC__)
    typedef long long __int64; /*For gcc on Windows */
#endif
#include <jni.h>
#include <stdlib.h>
#include <string.h>


/* Support for throwing Java exceptions */
typedef enum {
  SWIG_JavaOutOfMemoryError = 1, 
  SWIG_JavaIOException, 
  SWIG_JavaRuntimeException, 
  SWIG_JavaIndexOutOfBoundsException,
  SWIG_JavaArithmeticException,
  SWIG_JavaIllegalArgumentException,
  SWIG_JavaNullPointerException,
  SWIG_JavaDirectorPureVirtual,
  SWIG_JavaUnknownError
} SWIG_JavaExceptionCodes;

typedef struct {
  SWIG_JavaExceptionCodes code;
  const char *java_exception;
} SWIG_JavaExceptions_t;


static void SWIG_JavaThrowException(JNIEnv *jenv, SWIG_JavaExceptionCodes code, const char *msg) {
  jclass excep;
  static const SWIG_JavaExceptions_t java_exceptions[] = {
    { SWIG_JavaOutOfMemoryError, "java/lang/OutOfMemoryError" },
    { SWIG_JavaIOException, "java/io/IOException" },
    { SWIG_JavaRuntimeException, "java/lang/RuntimeException" },
    { SWIG_JavaIndexOutOfBoundsException, "java/lang/IndexOutOfBoundsException" },
    { SWIG_JavaArithmeticException, "java/lang/ArithmeticException" },
    { SWIG_JavaIllegalArgumentException, "java/lang/IllegalArgumentException" },
    { SWIG_JavaNullPointerException, "java/lang/NullPointerException" },
    { SWIG_JavaDirectorPureVirtual, "java/lang/RuntimeException" },
    { SWIG_JavaUnknownError,  "java/lang/UnknownError" },
    { (SWIG_JavaExceptionCodes)0,  "java/lang/UnknownError" } };
  const SWIG_JavaExceptions_t *except_ptr = java_exceptions;

  while (except_ptr->code != code && except_ptr->code)
    except_ptr++;

  (*jenv)->ExceptionClear(jenv);
  excep = (*jenv)->FindClass(jenv, except_ptr->java_exception);
  if (excep)
    (*jenv)->ThrowNew(jenv, excep, msg);
}


/* Contract support */

#define SWIG_contract_assert(nullreturn, expr, msg) if (!(expr)) {SWIG_JavaThrowException(jenv, SWIG_JavaIllegalArgumentException, msg); return nullreturn; } else


#include "SDL_audio.h"

  //extern void (*callback)(void *userdata, Uint8 *stream, int len);
  //extern void * SDL_audioCallback;

  //  extern void SDL_audioCallback(void *user_data, Uint8 * audio, int length);
//  {
//    //    jclass cls = NULL;
//    //    jmethodID mid = NULL;
//    // 
//    //    JNIEnv *env = NULL;
//    //
//    //    (*cached_jvm)->AttachCurrentThread(cached_jvm,(void **)&env,NULL);
//    //    (*env)->CallVoidMethod(env,objCallback,midCallback,(int)audio,length);
//  }

  int SWIG_SDL_OpenAudio(SDL_AudioSpec * desired, SDL_AudioSpec * obtained) {
    //desired->callback=SDL_audioCallback;
    
    return SDL_OpenAudio(desired, obtained);
  }

//  SWIG_SDL_AudioObject * SWIG_SDL_LoadWAV(const char *file, SDL_AudioSpec *spec) {
//    SWIG_SDL_AudioObject * audioObject = malloc(sizeof(SWIG_SDL_AudioObject));
//
//    if (SDL_LoadWAV(file, spec, &audioObject->audioBuffer, &audioObject->audioLength) == NULL) return NULL;
//    
//    printf("SWIG_SDL_LoadWAV: audio_len=%i\n", audioObject->audioLength);
//
//    return audioObject;
//  }

extern int SWIG_SDL_OpenAudio(SDL_AudioSpec *,SDL_AudioSpec *);
extern void SDL_PauseAudio(int);

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_set_1SDL_1AudioSpec_1freq(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    int arg2 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = (int)jarg2; 
    if (arg1) (arg1)->freq = arg2;
    
}


JNIEXPORT jint JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_get_1SDL_1AudioSpec_1freq(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jint jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    int result;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    result = (int) ((arg1)->freq);
    
    jresult = (jint)result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_set_1SDL_1AudioSpec_1format(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint16 arg2 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = (Uint16)jarg2; 
    if (arg1) (arg1)->format = arg2;
    
}


JNIEXPORT jint JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_get_1SDL_1AudioSpec_1format(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jint jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint16 result;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    result = (Uint16) ((arg1)->format);
    
    jresult = (jint)result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_set_1SDL_1AudioSpec_1channels(JNIEnv *jenv, jclass jcls, jlong jarg1, jshort jarg2) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint8 arg2 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = (Uint8)jarg2; 
    if (arg1) (arg1)->channels = arg2;
    
}


JNIEXPORT jshort JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_get_1SDL_1AudioSpec_1channels(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jshort jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint8 result;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    result = (Uint8) ((arg1)->channels);
    
    jresult = (jshort)result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_set_1SDL_1AudioSpec_1silence(JNIEnv *jenv, jclass jcls, jlong jarg1, jshort jarg2) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint8 arg2 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = (Uint8)jarg2; 
    if (arg1) (arg1)->silence = arg2;
    
}


JNIEXPORT jshort JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_get_1SDL_1AudioSpec_1silence(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jshort jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint8 result;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    result = (Uint8) ((arg1)->silence);
    
    jresult = (jshort)result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_set_1SDL_1AudioSpec_1samples(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint16 arg2 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = (Uint16)jarg2; 
    if (arg1) (arg1)->samples = arg2;
    
}


JNIEXPORT jint JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_get_1SDL_1AudioSpec_1samples(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jint jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint16 result;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    result = (Uint16) ((arg1)->samples);
    
    jresult = (jint)result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_set_1SDL_1AudioSpec_1padding(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint16 arg2 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = (Uint16)jarg2; 
    if (arg1) (arg1)->padding = arg2;
    
}


JNIEXPORT jint JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_get_1SDL_1AudioSpec_1padding(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jint jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint16 result;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    result = (Uint16) ((arg1)->padding);
    
    jresult = (jint)result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_set_1SDL_1AudioSpec_1size(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint32 arg2 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = (Uint32)jarg2; 
    if (arg1) (arg1)->size = arg2;
    
}


JNIEXPORT jlong JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_get_1SDL_1AudioSpec_1size(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jlong jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    Uint32 result;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    result = (Uint32) ((arg1)->size);
    
    jresult = (jlong)result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_set_1SDL_1AudioSpec_1callback(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    void (*arg2)(void *,Uint8 *,int) = (void (*)(void *,Uint8 *,int)) 0 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = *(void (**)(void *,Uint8 *,int))&jarg2; 
    if (arg1) (arg1)->callback = arg2;
    
}


JNIEXPORT jlong JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_get_1SDL_1AudioSpec_1callback(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jlong jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    void (*result)(void *,Uint8 *,int);
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    result = (void (*)(void *,Uint8 *,int)) ((arg1)->callback);
    
    *(void (**)(void *,Uint8 *,int))&jresult = result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_set_1SDL_1AudioSpec_1userdata(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    void *arg2 = (void *) 0 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = *(void **)&jarg2; 
    if (arg1) (arg1)->userdata = arg2;
    
}


JNIEXPORT jlong JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_get_1SDL_1AudioSpec_1userdata(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jlong jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    void *result;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    result = (void *) ((arg1)->userdata);
    
    *(void **)&jresult = result; 
    return jresult;
}


JNIEXPORT jlong JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_new_1SDL_1AudioSpec(JNIEnv *jenv, jclass jcls) {
    jlong jresult = 0 ;
    SDL_AudioSpec *result;
    
    (void)jenv;
    (void)jcls;
    result = (SDL_AudioSpec *)(SDL_AudioSpec *) calloc(1, sizeof(SDL_AudioSpec));
    
    *(SDL_AudioSpec **)&jresult = result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_delete_1SDL_1AudioSpec(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    free((char *) arg1);
    
}


JNIEXPORT jint JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_SWIG_1SDL_1OpenAudio(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    jint jresult = 0 ;
    SDL_AudioSpec *arg1 = (SDL_AudioSpec *) 0 ;
    SDL_AudioSpec *arg2 = (SDL_AudioSpec *) 0 ;
    int result;
    
    (void)jenv;
    (void)jcls;
    arg1 = *(SDL_AudioSpec **)&jarg1; 
    arg2 = *(SDL_AudioSpec **)&jarg2; 
    result = (int)SWIG_SDL_OpenAudio(arg1,arg2);
    
    jresult = (jint)result; 
    return jresult;
}


JNIEXPORT void JNICALL Java_sdljava_x_swig_SWIG_1SDLAudioJNI_SDL_1PauseAudio(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 ;
    
    (void)jenv;
    (void)jcls;
    arg1 = (int)jarg1; 
    SDL_PauseAudio(arg1);
    
}


#ifdef __cplusplus
}
#endif

