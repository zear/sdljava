@ECHO OFF
@setLocal
cd ..
set JARS="%CD%/lib/jdom.jar;%CD%/lib/bsh.jar"

if EXIST %CD%/lib/sdljava.jar (
    echo "******** Reading classes from sdljava.jar"
    java -cp %CD%/lib/sdljava.jar;%JARS% -Djava.library.path=%CD%/lib bsh.Interpreter %1 %2 %3 %4 %5 %6 %7 %8 %9
) else (
    java -cp %CD%/classes;%JARS% -Djava.library.path=%CD%/lib bsh.Interpreter %1 %2 %3 %4 %5 %6 %7 %8 %9
)
cd bin
@endLocal
pause