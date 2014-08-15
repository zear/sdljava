package sdljavax.gui;
/**
 *  sdljava - a java binding to the SDL API
 *
 *  Copyright (C) 2004  Ivan Z. Ganza
 * 
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA
 *
 *  Ivan Z. Ganza (ivan_ganza@yahoo.com)
 */
import java.io.IOException;

import sdljava.video.SDLRect;

import org.gljava.opengl.GL;
import org.gljava.opengl.TextureFactory;

public class Window extends Container {

    public Window(GL gl, String name, SDLRect rect, SDLRect screenDimensions, String backgroundTexturePath) throws IOException {
	super(gl, name, rect, screenDimensions);
	
	if (backgroundTexturePath != null) {
	    backgroundTexture = TextureFactory.getFactory().loadTexture(gl, backgroundTexturePath); // set in the Widget
	}
    }

    public void draw(GL gl) {
	super.draw(gl);
	
	// store current model matrix
	gl.glPushMatrix();
	
	// move to appropriate position
	gl.glTranslatef(xPos, yPos, 0);
	
	// draw the window title bar
	gl.glDisable(gl.GL_TEXTURE_2D);
	
	if (defaultFont != null) {
	    defaultFont.faceSize(14, 72);
	    gl.glColor3f( 1.0f, 1.0f, 1.0f);
	    gl.glRasterPos2f(0f + 2.0f, height - defaultFont.ascender() + 2.0f);
	    defaultFont.render(name);
	}

	// restore the model view matrix
	gl.glPopMatrix();
    }
}