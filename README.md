Vim Questions:
1Vim Questions:
1. To jump to a certain line in vim just go into command mode and press : then
   the page number you want to go to such as :4
2. To undo changes in vim just go to command mode and press :u
3. To search for a word just press / then the word you want to search for.
4. In normal mode press gg=G to indent an entire file.
5. To switch between two files opened in vim go to command mode and press the
   file name you want to switch to.
6. a) To move forward one word in command mode press w b) and to go backwards
      press b.
Unix Commands
7. To remove all .class files use the command rm astrich class to remove
   all files ending with the string class while in the current directory.
8. a) Use the command rmdir -p directoryName to remove a directory if it is 
   empty.
   b) To delete a directory that is not empty simply press  
      rmdir -r directoryName.
10. To clear a unix terminal screen type clear or press control + l.
11. Swap files are files that are swapped into swap space to make more temporary
    space on the RAM. They exist in order to create more files temporarily in
    order to provide more space on the RAM. I also read that swap files are
    automatically created if vim crashes while you are working on a file.
    To recover a file in swap space open the swap file and press, in command
    mode, recover. To delete a swap file just press rm -f filename. 
Program Descriptions
1. Board.java:In Board.java I design methods used to create a board
   for the game 2048. First I design the constructors how I would like to
   initialize the board, and then from there I design multiple methods that
   would allow the board to move and to change in order to play the game
   2048. I also designed a method that would save the board for the game you
   are playing.  Overall, Board.java was made primarily to assist
   playing the game 2048.
2. GameManager.java:At first I designed the constructors for GameManager.java
   which will be used to initialize the GameManager.  In GameManageer.java, I
   implemented only two mehtods.  The first one is a method called play. This 
   mehtod's main task is the actualplaying of the game. This method uses 
   multiple methods in Board.java in order to play the game. I also designed a 
   method getDirection which takes awsd and translates these into left, up,
   down, right.

Summary: This program was created to mimic the game 2048. The program used a
board in order to create a grid that is filled with power of twos. The user
can move the grid and add the numbers together with the goal of making it to 
the number 2048. You can create a grid of any size to play on also.



