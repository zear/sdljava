#!/usr/bin/ruby -w
require "find"

############################################################
class BuildTarget
 attr_reader :iFile, :jPackage, :packageDir, :wrapFile

 def initialize(iFile, jPackage)
   @iFile      = iFile
   @jPackage   = jPackage
   @packageDir = jPackage.gsub(".", "/")
   @wrapFile   = iFile[0..iFile.length-3] + "_wrap.c"
 end
end

targets = [
 BuildTarget.new("SDLMain.i",  "sdljava.x.swig"),
 BuildTarget.new("SDLEvent.i", "sdljava.x.swig"),
 BuildTarget.new("SDLVideo.i", "sdljava.x.swig"),
 BuildTarget.new("SDLAudio.i", "sdljava.x.swig"),
 BuildTarget.new("SDLCdrom.i", "sdljava.x.swig"),
 BuildTarget.new("SDLJoystick.i", "sdljava.x.swig"),
 BuildTarget.new("SDLMixer.i", "sdljava.x.swig"),
 BuildTarget.new("SDLImage.i", "sdljava.x.swig"),
 BuildTarget.new("SDLTTF.i",   "sdljava.x.swig"),
 BuildTarget.new("SDLGFX.i",   "sdljava.x.swig"),
 BuildTarget.new("glew.i",     "org.gljava.opengl.x.swig")
]
############################################################





# edit includes to suite setup
includes = ""
includes += " -I/usr/include"
includes += " -I/usr/include/SDL"
includes += " -I/usr/local/include/SDL"
includes += " -I/usr/local/include"

############################################################
# DO NOT EDIT BELOW THIS POINT UNLESS ADDING/REMOVING TARGETS
############################################################
# the swig command
command = "swig -importall #{includes} -outdir $outdir -java"


############################################################
# Build CLEAN
if ARGV[0] and ARGV[0] =~ /clean/
  print "BUILD CLEAN\n"
  Find.find("src/generated") {|f|
    next if f == "."
    File.delete(f) if FileTest.file? f
  }
  Find.find("src/sdljava/native") {|f|
    next if not f.index ".c"
    File.delete(f) if FileTest.file? f
  }
end

############################################################
# cd to appropriate directory and build them
# the text between the `` is executed as shell command
def checkStatus(val)
  return false if not val
  return false if val.exitstatus / 256 != 0
  return true
end


Dir.chdir("src/sdljava/native")
targets.each{|t|
 swigCommand = command + " -package " + t.jPackage + " " + t.iFile
  if not FileTest.exists?(t.wrapFile)
   print "Building Target #{t.iFile}...\n"
    swigCommand.sub!("$outdir", "../../#{t.packageDir}")
    print `#{swigCommand}`
    if not checkStatus($?)
      print "FAILED TO BUILD #{t.iFile}, exit code=#{$?}\n"
      exit(-1)
    end
    print "\t#{t.iFile} rebuilt with success\n"

  elsif test(?>, t.iFile , t.wrapFile)
    print "Target #{t.iFile} out of date, rebuilding...\n"
    swigCommand.sub!("$outdir", "../../#{t.packageDir}")
    print `#{swigCommand}`
    if not checkStatus($?)
      print "FAILED TO BUILD #{t.iFile}\n"
      exit(-1)
    end
    print "\t#{t.iFile} rebuilt with success\n"
  end
} 

Dir.chdir("../../..")

