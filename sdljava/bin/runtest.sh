#!/bin/sh

JARS="./lib/jdom.jar:./lib/bsh.jar"

cd ..

if test -a ./lib/sdljava.jar
then
    echo "******** Reading classes from sdljava.jar"
    java -cp ./lib/sdljava.jar:${JARS} -Djava.library.path=./lib $@ 
else
    java -mx500m -cp ./classes:${JARS} -Djava.library.path=./lib $@ 
fi
