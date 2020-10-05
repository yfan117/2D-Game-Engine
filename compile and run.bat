cd class
del /S *.class
cd ../src
javac -d ../class Character.java Control.java Draw.java Display.java Map.java Game.java MusicPlayer.java MainMenu.java
cd ../class
java Diablo/Game
pause
