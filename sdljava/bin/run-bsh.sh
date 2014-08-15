#!/bin/sh

JARS="../lib/jdom.jar:../lib/bsh.jar"

if test -a ../lib/sdljava.jar
then
    echo "******** Reading classes from sdljava.jar"
    java -cp ../lib/sdljava.jar:${JARS} -Djava.library.path=../lib bsh.Interpreter $@ 
else
    java -cp ../classes:${JARS} -Djava.library.path=../lib bsh.Interpreter $@ 
fi