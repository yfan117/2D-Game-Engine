cd class
del /S *.class
cd ../src
javac -d ../class Character.java Control.java Draw.java Display.java Map.java Game.java
cd ../class
java Diablo/Game
=======
cd class
del /S *.class
cd ../src
javac -d ../class Sprite.java
javac -d ../class Arrow.java
javac -d ../class Enemy.java
javac -d ../class Player.java
javac -d ../class Control.java
javac -d ../class Draw.java
javac -d ../class Display.java
javac -d ../class Map.java
javac -d ../class Game.java
cd ../class
java Game
