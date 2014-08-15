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
import sdljava.event.SDLEvent;
import sdljava.event.SDLMouseButtonEvent;
import sdljava.x.swig.SDLPressedState;

import org.gljava.opengl.GL;
import org.gljava.opengl.TextureFactory;
import org.gljava.opengl.Texture;

import org.gljava.opengl.ftgl.BoundingBox;
/**
 * Describe class <code>Button</code> here.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: Button.java,v 1.3 2005/09/03 23:07:00 ivan_ganza Exp $
 */
public class Button extends Widget {

    protected Texture  unSelectedTexture;
    protected Texture  selectedTexture;

    public Button(GL gl, String name, SDLRect dimensions, String unSelectedTexturePath, String selectedTexturePath) throws IOException {
	super(gl, name, dimensions, null);

	drawBorder = false;

	this.unSelectedTexture = TextureFactory.getFactory().loadTexture(gl, unSelectedTexturePath);
	this.selectedTexture   = TextureFactory.getFactory().loadTexture(gl, selectedTexturePath);

	backgroundTexture = unSelectedTexture;
    }

    public void mouseEntered() {
	System.out.println("Widget[" + getName() + "]  mouse entered");
    }

    public void mouseExit() {
	System.out.println("Widget[" + getName() + "]  mouse exit");
	backgroundTexture = unSelectedTexture;
    }

    public void handleEvent(SDLEvent event) {
	//System.out.println("handleEvent: " + event);
	
	if (event instanceof  SDLMouseButtonEvent) {
	    SDLMouseButtonEvent buttonEvent = (SDLMouseButtonEvent) event;
	    
	    if (buttonEvent.getState() == SDLPressedState.PRESSED) {
		if (buttonEvent.getButton() == SDLEvent.SDL_BUTTON_LEFT) {
		    backgroundTexture = selectedTexture;
		}
	    }

	    else if (buttonEvent.getState() == SDLPressedState.RELEASED) {
		if (buttonEvent.getButton() == SDLEvent.SDL_BUTTON_LEFT) {
		    backgroundTexture = unSelectedTexture;
		}
	    }
	}
    }

    public void draw(GL gl, int x, int y) {
	super.draw(gl, x, y);

	// store current model matrix
	gl.glPushMatrix();
	
	// move to appropriate position
	//gl.glTranslatef(x+container.xPos, y+container.yPos, 0);
	
	// draw the window title bar
	gl.glDisable(gl.GL_TEXTURE_2D);

	if (defaultFont != null) {
	    gl.glColor3f( 0.0f, 0.0f, 0.0f);

	    defaultFont.faceSize(16,72);

	    BoundingBox box  = defaultFont.getBoundingBox(name);

	    float textWidth  = box.getUrx() - box.getLlx();
	    float textHeight = box.getUry() - box.getLly();

	    gl.glRasterPos2f(x+container.xPos+(width / 2 - textWidth / 2)-1,y+container.yPos+(height /2 - textHeight / 2));
	    defaultFont.render(name);
	}

	// restore the model view matrix
	gl.glPopMatrix();
    }
}