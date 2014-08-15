# Makefile for using SWIG and Java for C code

GLJAVA_I 	= glew.i
GLJAVA_SRCS = glew_wrap.c opengl-1_1_arrays_wrap.c
GLJAVA_OBJ 	= glew_wrap.obj opengl-1_1_arrays_wrap.obj
GLJAVA_LIB 	= glew32.lib glu32.lib opengl32.lib 
GLJAVA_DLL 	= gljava.dll

# Location of the Visual C++ tools (32 bit assumed)
SWIG          = C:\SWIG\swig.exe
TOOLS         = C:\PROGRA~1\MIAF9D~1\VC98
CC            = $(TOOLS)\bin\cl.exe
LINK          = $(TOOLS)\bin\link.exe
INCLUDE32     = -I$(TOOLS)\include -I$(TOOLS)\include\GL
MACHINE       = IX86

# C Library needed to build a DLL
DLLIBC        = msvcrt.lib oldnames.lib  

# Windows libraries that are apparently needed
WINLIB        = kernel32.lib advapi32.lib user32.lib gdi32.lib comdlg32.lib winspool.lib

# Libraries common to all DLLs
LIBS          = $(DLLIBC) $(WINLIB) 

# Linker options
LOPT      = -debug:full -debugtype:cv /NODEFAULTLIB /RELEASE /NOLOGO \
             /MACHINE:$(MACHINE) -entry:_DllMainCRTStartup@12 -dll

# C compiler flags

CFLAGS        = /Z7 /Od /c /nologo
JAVA_INCLUDE  = -Ic:\jdk1.5.0\include -Ic:\jdk1.5.0\include\win32

# NOTE:  if you want to build the native layer please add "glew_wrap.c" and/or "opengl-1_1_arrays.i" the all line below
all::gljava

glew_wrap.c:: glew.i gljava_common_typemaps.i opengl-1_1.i
	$(SWIG) -importall -java -package org.gljava.opengl.x.swig -outdir ../../../../org/gljava/opengl/x/swig $(GLJAVA_I)

opengl-1_1_arrays_wrap.c: opengl-1_1_arrays.i
	$(SWIG) -importall -java -package org.gljava.opengl.x.swig -outdir ../../../../org/gljava/opengl/x/swig opengl-1_1_arrays.i

gljava::
	$(CC) $(CFLAGS) $(JAVA_INCLUDE) $(GLJAVA_SRCS)
	set LIB=$(TOOLS)\lib
	$(LINK) $(LOPT) -out:$(GLJAVA_DLL) $(LIBS) $(GLJAVA_LIB) $(GLJAVA_OBJ)
        copy $(GLJAVA_DLL) ..\..\..\..\..\lib
        del /F *.exp *.pdb 
        
clean::
	del /F *.dll *.lib *.pch
