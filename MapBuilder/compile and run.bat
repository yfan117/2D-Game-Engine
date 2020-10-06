cd class
del /S *.class
cd ../src
javac -d ../class MapBuilder.java
cd ../class
java MapBuilder/MapBuilder
pause
