# Battleships
Simple version of the game Battleships which allows a single human player to play a one-sided game against ships placed by the computer. <br> <p align="center"> ![Battleships](https://user-images.githubusercontent.com/33400631/143935961-714555b5-253f-4188-b076-4f22ec68f702.png) </p>
## Prerequisites
Make sure you have installed the following tools on your computer:
- Java (solution was created using Java 15 - https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html)
- Maven - https://maven.apache.org/download.cgi
## Building and running
Download the repository and open the command line in the main project directory. Then run the following command:
```
mvn clean install
```
After that go to the target directory and run created .jar file. <br>
![directory](https://user-images.githubusercontent.com/33400631/143943588-9f31c25d-bcc3-4169-94f2-6c1bebbbeb9f.PNG)

## Game rules
The program should create a 10x10 grid, and place several ships on the grid at random with the following sizes: <br>
- 1x Battleship (5 squares)
- 2x Destroyers (4 squares)
<p>The player selects coordinates on the grid using a mouse to specify a square to target. Every square has one of the following state: Unknown, Missed and Hit (shots result in misses or hit). The game ends when all ships are sunk. </p>
<b>Important! </b> Battleships are located on the grid in such a way that they  can't  touch each other either on the sides or on the corners!
