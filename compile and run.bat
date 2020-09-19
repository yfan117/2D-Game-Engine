cd class
del /S *.class
cd ../src
javac -d ../class Control.java
javac -d ../class Display.java
javac -d ../class Map.java
javac -d ../class Game.java
cd ../class
java Game
pause