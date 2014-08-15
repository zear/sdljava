%typemap(in) (const GLubyte *mask) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(out) (GLubyte *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, NULL);
    $1 = buf;
}

%typemap(jni) GLubyte * "jobject"
%typemap(jtype) GLubyte * "ShortBuffer"
%typemap(jstype) GLubyte *"ShortBuffer"
%typemap(javain) GLubyte * "$javainput"

// WARNING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// not better fix this!  not sure its going to work, just set it so
// this will compile
%typemap(javaout) GLubyte * {
		  return $jnicall;
}


////////////////////////////////////////
%typemap(in) (GLvoid *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLvoid * "jobject"
%typemap(jtype) GLvoid * "Buffer"
%typemap(jstype) GLvoid *"Buffer"
%typemap(javain) GLvoid * "$javainput"

////////////////////////////////////////
%typemap(in) (GLfloat *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLfloat * "jobject"
%typemap(jtype) GLfloat * "FloatBuffer"
%typemap(jstype) GLfloat *"FloatBuffer"
%typemap(javain) GLfloat * "$javainput"

////////////////////////////////////////
%typemap(in) (GLdouble *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLdouble * "jobject"
%typemap(jtype) GLdouble * "DoubleBuffer"
%typemap(jstype) GLdouble *"DoubleBuffer"
%typemap(javain) GLdouble * "$javainput"


////////////////////////////////////////
%typemap(in) (GLint *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLint * "jobject"
%typemap(jtype) GLint * "IntBuffer"
%typemap(jstype) GLint *"IntBuffer"
%typemap(javain) GLint * "$javainput"

////////////////////////////////////////
%typemap(in) (GLshort *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLshort * "jobject"
%typemap(jtype) GLshort * "ShortBuffer"
%typemap(jstype) GLshort *"ShortBuffer"
%typemap(javain) GLshort * "$javainput"

////////////////////////////////////////
%typemap(in) (GLboolean *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLboolean * "jobject"
%typemap(jtype) GLboolean * "ShortBuffer"
%typemap(jstype) GLboolean *"ShortBuffer"
%typemap(javain) GLboolean * "$javainput"

////////////////////////////////////////
%typemap(in) (GLbyte *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLbyte * "jobject"
%typemap(jtype) GLbyte * "ByteBuffer"
%typemap(jstype) GLbyte *"ByteBuffer"
%typemap(javain) GLbyte * "$javainput"

////////////////////////////////////////
%typemap(in) (GLuint *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLuint * "jobject"
%typemap(jtype) GLuint * "IntBuffer"
%typemap(jstype) GLuint *"IntBuffer"
%typemap(javain) GLuint * "$javainput"

////////////////////////////////////////
%typemap(in) (GLushort *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLushort * "jobject"
%typemap(jtype) GLushort * "IntBuffer"
%typemap(jstype) GLushort *"IntBuffer"
%typemap(javain) GLushort * "$javainput"

////////////////////////////////////////
%typemap(in) (GLclampf *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLclampf * "jobject"
%typemap(jtype) GLclampf * "FloatBuffer"
%typemap(jstype) GLclampf *"FloatBuffer"
%typemap(javain) GLclampf * "$javainput"

////////////////////////////////////////
%typemap(in) (GLenum *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) GLenum * "jobject"
%typemap(jtype) GLenum * "IntBuffer"
%typemap(jstype) GLenum *"IntBuffer"
%typemap(javain) GLenum * "$javainput"

////////////////////////////////////////
%typemap(jni) GLdouble[] "jobjectArray"
%typemap(jtype) GLdouble[] "GLdouble[]"
%typemap(jstype) GLdouble[] "GLdouble[]"
%typemap(javain) GLdouble[] "$javainput"

%typemap(jni) GLfloat[] "jobjectArray"
%typemap(jtype) GLfloat[] "GLfloat[]"
%typemap(jstype) GLfloat[] "GLfloat[]"
%typemap(javain) GLfloat[] "$javainput"
