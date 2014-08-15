%typemap(in) (void *) {
    void * buf = (*jenv)->GetDirectBufferAddress(jenv, $input);
    if (buf == NULL) {
        jclass clazz = (*jenv)->FindClass(jenv, "java/lang/NullPointerException");
	(*jenv)->ThrowNew(jenv, clazz, "null address returned from GetDirectBufferAddress() call.  Make sure the buffer is a _direct_ buffer.");
        return $null;
    }

    $1 = buf;
}

%typemap(jni) void * "jobject"
%typemap(jtype) void * "Buffer"
%typemap(jstype) void *"Buffer"
%typemap(javain) void * "$javainput"
