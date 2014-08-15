# Makefile for using SWIG and Java for C code

FTGL_I        = ftgl.i
FTGL_SRCS     = ftgl_wrap.cxx
FTGL_OBJ      = ftgl_wrap.obj
FTGL_LIB      = ftgl_dynamic_MTD.lib  
FTGL_DLL      = ftgljava.dll

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
JAVA_INCLUDE  = -Ic:\jdk1.5.0\include -Ic:\jdk1.5.0\include\win32 -Ic:\vi\sdljava\src\org\gljava\opengl\native\ftgl

# NOTE:  if you want to build the native layer please add "ftgl_wrap.cxx" the all line below
all::FTGL

ftgl_wrap.cxx::  FTGL.i FTFont.i FTFace.i FTGLBitmapFont.i FTGLExtrdFont.i FTGLOutlineFont.i FTGLPixmapFont.i FTGLPolygonFont.i FTGLTextureFont.i
				$(SWIG) -v -Wall -c++ $(INCLUDE) -importall -java -package org.gljava.opengl.ftgl -outdir ../../ftgl FTGL.i

FTGL::
        $(CC) $(CFLAGS) $(JAVA_INCLUDE) $(FTGL_SRCS)
	set LIB=$(TOOLS)\lib
        $(LINK) $(LOPT) -out:$(FTGL_DLL) $(LIBS) $(FTGL_LIB) $(FTGL_OBJ)
        copy $(FTGL_DLL) ..\..\..\..\..\lib
        del /F *.exp *.pdb *.lib
        
clean::
	del /F *.dll *.lib *.pch
