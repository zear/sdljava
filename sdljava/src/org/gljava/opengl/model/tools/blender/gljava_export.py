#!BPY

""" Registration info for Blender menus:
Name: 'gljava'
Blender: 232
Group: 'Export'
Tip: 'Export to gljava (.xml) format.'
"""

import Blender

def export(filename):
    outfile = open(filename, "w")
    
    outfile.write("<model>\n")

    selectedObject = Blender.Object.GetSelected()
    for child in selectedObject:
        if child.getType() == "Mesh":
            outfile.write("\t<mesh>\n")
            exportObject(child, outfile)
            outfile.write("\t</mesh>\n\n")
            
    outfile.write("</model>\n")

def exportObject(object, outfile):
    print object
    mesh = object.getData()
    writeVertices(mesh.verts, outfile)
    writeFaces(mesh.faces, outfile)

def writeVertices(vertices, outfile):
    outfile.write("\t\t<vertices count=\"%d\">\n" % len(vertices))
    for i in vertices:
        outfile.write("\t\t\t<vertex x=\"%f\" y=\"%f\" z=\" %f\"/>\n" % (i[0], i[1], i[2]))
    outfile.write("\t\t</vertices>\n\n")

def writeFaces(faces, outfile):
    outfile.write("\t\t<faces count=\"%d\">\n" % (len(faces)))
    for i in faces:
        outfile.write("\t\t\t<face v1=\"%d\" v2=\"%d\" v3=\"%d\"/>\n" % (i.v[0].index, i.v[1].index, i.v[2].index))
    outfile.write("\t\t</faces>\n")
        
def fs_callback(filename):
    if filename.find('.xml', -4) <= 0: filename += '.xml'
    export(filename)

Blender.Window.FileSelector(fs_callback, "GLJava Export")
