SWIG = swig

CC = gcc

CFLAGS=-O3

INCLUDE=-I. -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -I/usr/include -I/usr/local/include -I/usr/include/freetype2 -I/usr/include/FTGL

FTGLJAVA_OBJ=FTGL_wrap.o
FTGLJAVA_LIB=-lGLU -lGL -lfreetype -lz -lftgl

all:	libftgljava.so

#
#
# NOTE:  if you want to work on the ftgljava native layer please un-comment the following line
#
#FTGL_wrap.cxx: FTGL.i FTFont.i FTFace.i FTGLBitmapFont.i FTGLExtrdFont.i FTGLOutlineFont.i FTGLPixmapFont.i FTGLPolygonFont.i FTGLTextureFont.i
#	$(SWIG) -Wall -c++ $(INCLUDE) -importall -java -package org.gljava.opengl.ftgl -outdir ../../../../../org/gljava/opengl/ftgl FTGL.i
#	./post-process.rb

%.o: %.cxx
	$(CC) $(CFLAGS) $(INCLUDE) -c $< -o $@

libftgljava.so: $(FTGLJAVA_OBJ)
	$(CC) -shared -o libftgljava.so $(FTGLJAVA_OBJ) $(FTGLJAVA_LIB)
	cp libftgljava.so ../../../../../../lib

clean:
	rm *.o *.so
