#!/usr/bin/ruby -w

#   input is a .i file which is processed such that any functions taking a pointer 
# are mapped

ARGV.each{|file|

  buffer = []
  val = ""

  # first make sure each function is on ONLY one line
  IO::foreach(file){|line|
    next if line =~ /define/
    
#    print line
 #   print "\n" if line =~ /[)];/

    val += line

    if line =~ /[)];/
      val += "\n"
      buffer << val
      val = String.new
    end

#    if line =~ /[a-zA-Z]*\sgl[a-zA-Z0-9]*[(]/
#      print line
#
#    end
  }

  buffer.each{|line|
    line.gsub!(/\*([a-zA-Z]*)[ ),]/, "\0\1\[\]")

    if line =~ /[a-zA-Z]\s[*][a-zA-Z,]*/
      print line
    end
  }

#  IO::foreach(file){|line|
#    if line =~ /[a-zA-Z]\s[*][a-zA-Z,]*/
#      print line
#    end
 # }
}
