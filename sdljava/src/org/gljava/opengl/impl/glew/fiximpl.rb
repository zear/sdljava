#!/usr/bin/ruby -w

f = File.new("GLeeImpl.java")

lines = f.readlines

for i in 0..lines.size

  line = lines[i]

  if line =~ /void/
    print "#{line[0..line.length-3]} {\n"
    start = line.index("(")
    last  = line.index(")")

    next if last - start == 1
    
    args = line[start+1..last-1]
    args = args.split(",")

#    print "#{args.length}\n"
#    print "#{args}\n"
    
    last  = start -1
    start = line.index("gl")
    method = line[start..last] + "("

    next if method =~ /\/\//

    print "\tGLee.#{method}"

    args_str = ""

    args.each{|pair|
      (drop, param) = pair.split(" ")
      args_str += "#{param},"
    }
    
    args_str = args_str[0..args_str.length-2] + ");"
    print args_str
    print "\n"
    print "\t}\n\n"
  end
end
