#!/usr/bin/ruby -w

require "find"
require "ftools"

# temporary hack until I can figure out a better way to do this
#    process the given files, change the methods names to start
#    with lower case, remove the methods in remove_methods list
#    then cat the contents of .post file to redefine or add new
#    methods

files=[
  "FTFont.java",
  "FTGLBitmapFont.java",
  "FTGLExtrdFont.java",
  "FTGLPixmapFont.java",
  "FTGLPolygonFont.java",
  "FTGLPixmapFont.java",
  "FTGLPolygonFont.java",
  "FTGLTextureFont.java"
]

# these are the methods that need to be removed since they are redefined in the .post files
remove_methods=[
  "public FTGLBitmapFont(String fontFilePath)"
]

java_src="../../../../../org/gljava/opengl/ftgl"

if not Dir::chdir java_src
  print "Failed to change directory to: #{java_src}\n"
  exit
end

print "Changed directory to: #{java_src}\n"

def fixMethodNames(lines)
  for i in 0..lines.length
    line = lines[i]

    if line =~ /(public|protected) \w+ (\w+)[(]/

      (access,return_type,name,r1,r2) = line.split(" ")      

      index = line.index name
      
      line[index,1] = line[index,1].downcase
    end
  end
end

def doRemove(lines, remove_methods)
  for i in 0..lines.length
    line = lines[i]

    next if line == nil

    remove_methods.each{|m|
      if line.index m
	endOfMethod = findEndOfMethod(i, lines)
	print "end=#{endOfMethod}\n"

	remove = endOfMethod = i
	for x in 0..remove
	  lines[i+x] = nil
	end
	i += remove
      end
    }
  end
end

def findEndOfMethod(index, lines)
  for i in index..lines.length
    line = lines[i]
    return i if line.index "}"
  end
  0
end


Find.find(".") do |path|
  path = path[2..path.length]		# strip off leading ./

  next if not files.include? path
  
  print "Processing #{path}\n"

  lines = File.new(path).readlines
  # get rid of last line
  lines[lines.length-1] = nil

  fixMethodNames(lines)
  doRemove(lines, remove_methods)

  # add any code from .post file if it exists
  post_path = path[0..path.length-6] + ".post"
  if File.exist?post_path
    post = File.new(post_path).readlines
  end

  f = File.new("#{path}.tmp", "w+")
  
  f << lines
  
  f << post if not post == nil

  f << "\n}"

  f.close

  # move it to the actual file now
  File.move("#{path}.tmp", path)
end


