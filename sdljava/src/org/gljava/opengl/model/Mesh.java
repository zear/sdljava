package org.gljava.opengl.model;
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
import org.gljava.opengl.GL;
/**
 * A Mesh.  Contains a list of vertices and faces.
 *
 * @author  Ivan Z. Ganza
 * @version $Id: Mesh.java,v 1.1 2005/02/10 04:18:42 ivan_ganza Exp $
 */
public class Mesh {

    public Vector[] vertices;
    public Face[]   faces;

    public Mesh() {
    }

    public Mesh(int vertexCount, int faceCount) {
	vertices = new Vector[vertexCount];
	faces    = new Face[faceCount];
    }

    public void render(GL gl) {
	gl.glBegin(gl.GL_TRIANGLES);
	{
	    for (int i = 0; i < faces.length; i++) {
		gl.glVertex3f(vertices[faces[i].vertex1].x, vertices[faces[i].vertex1].y, vertices[faces[i].vertex1].z);
		gl.glVertex3f(vertices[faces[i].vertex2].x, vertices[faces[i].vertex2].y, vertices[faces[i].vertex2].z);
		gl.glVertex3f(vertices[faces[i].vertex3].x, vertices[faces[i].vertex3].y, vertices[faces[i].vertex3].z);
	    }
	}
	gl.glEnd();
    }
    
    /**
     * Gets the value of vertices
     *
     * @return the value of vertices
     */
    public Vector[] getVertices()  {
	return this.vertices;
    }

    /**
     * Sets the value of vertices
     *
     * @param argVertices Value to assign to this.vertices
     */
    public void setVertices(Vector[] argVertices) {
	this.vertices = argVertices;
    }

    /**
     * Gets the value of faces
     *
     * @return the value of faces
     */
    public Face[] getFaces()  {
	return this.faces;
    }

    /**
     * Sets the value of faces
     *
     * @param argFaces Value to assign to this.faces
     */
    public void setFaces(Face[] argFaces) {
	this.faces = argFaces;
    }

    public String toString() {
	StringBuffer buf = new StringBuffer();

	buf.append("Mesh{Vertices=").append(vertices.length).
	    append(", Faces=").append(faces.length).append("}");

	return buf.toString();
    }
}