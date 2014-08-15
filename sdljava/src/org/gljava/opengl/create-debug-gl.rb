#!/usr/bin/ruby -w

f = File.new "GL.java"

def extract_params(method)
  list = []

  start  = method.index("(")
  finish = method.index(")")

  method = method[start+1..finish-1]

  args = method.split(",")

  args.each{|arg|
    (type,arg_name) = arg.split(" ")
    list << arg_name
  }

  list
end

def implement_method(line,return_type, method_name)
  return if method_name =~ /glGetError/

  index = line.index method_name
  line = line[index..line.length-3]

  params = extract_params(line)

  print "\n\tpublic ", return_type, " ", line, " {\n"
  s = "\t"
  if not return_type =~ /void/
    s += "#{return_type} value = "
  end
  s += "\tgl.#{method_name}("
  params.each{|param|
    s += param
    s += ","
  }
  s = s[0..s.length-2] if params.length > 0
  print s, ");"
  if method_name =~ /glBegin/
    print "\n\tinsideBeginEndPair = true;\n"
  elsif method_name =~ /glEnd/
    print "\n\tinsideBeginEndPair = false;\n"
  end
  print "\n\tcheckGLError(\"#{method_name}\");\n"
  if not return_type =~ /void/
    print "\t\treturn value;\n"
  end
  print "\n\t}\n"
end

################################################################################
print <<FOO
package org.gljava.opengl;

import org.gljava.opengl.glu.GLUnurbs;
import org.gljava.opengl.glu.GLUtesselator;
import org.gljava.opengl.glu.GLUquadric;

import org.gljava.opengl.x.swig.*;

import java.nio.*;
/**
 * <P> A <I>Composable</I> pipeline which wraps an underlying {@link GL} implementation.
 * Provides error checking after each OpenGL method call. If an error occurs,
 * causes a {@link GLException} to be thrown at exactly the point of failure.
 * To install this wrap an existing GL instance: </P>
 * <PRE>
 *     gl = new DebugGL(framebuffer.getGL());
 * </PRE>
 * @author  Ivan Z. Ganza
 * @version $Id: create-debug-gl.rb,v 1.5 2005/09/25 17:47:18 ivan_ganza Exp $
 */
public class DebugGL implements GL {

        boolean insideBeginEndPair = false;
        GL      gl;

        public DebugGL(GL gl) {
            this.gl = gl;
        }
FOO


f.each_line{|line|
  next if line =~ /[\/\/]/

  if line =~ /(public|protected)? (\w+) (\w+)[(]/
    implement_method line, $2, $3
  end
}

print <<FOO

    public long glGetError() {
        return gl.glGetError();
    }

    void checkGLError(String caller) {
      if (insideBeginEndPair) {
          return;
      }
    
      int error = (int) gl.glGetError();
      if (error == gl.GL_NO_ERROR) return;
    
      StringBuffer buf = new StringBuffer("glGetError() return the following error codes after call to " + caller + "(): ");
    
        // Loop repeatedly to allow for distributed GL implementations,
        // as detailed in the glGetError() specification
        do {
          switch (error) {
            case GL.GL_INVALID_ENUM: buf.append("GL_INVALID_ENUM "); break;
            case GL.GL_INVALID_VALUE: buf.append("GL_INVALID_VALUE "); break;
            case GL.GL_INVALID_OPERATION: buf.append("GL_INVALID_OPERATION "); break;
            case GL.GL_STACK_OVERFLOW: buf.append("GL_STACK_OVERFLOW "); break;
            case GL.GL_STACK_UNDERFLOW: buf.append("GL_STACK_UNDERFLOW "); break;
            case GL.GL_OUT_OF_MEMORY: buf.append("GL_OUT_OF_MEMORY "); break;
            case GL.GL_NO_ERROR: throw new InternalError("Should not be treating GL_NO_ERROR as error");
            default: throw new InternalError("Unknown glGetError() return value: " + error);
          }
        }while ((error = (int)gl.glGetError()) != gl.GL_NO_ERROR);
    
        throw new GLException(buf.toString());
        }

}
FOO
