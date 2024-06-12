# Minesweeper Game

A Simple implement of the classic Minesweeper game using Java and Swing for the GUI.

## Table of contents
- [Features](#features)
- [Installation](#installation)
- [How to play](#howtoplay)
- [Acknowledgements](#Acknowledgements)


## Features
- 9x9 grid with 10 mines.
- Reveal cells by clicking.
- Automatically reveal adjacent cells if there are no adjacent mines.
- Mark safe cells with a GREEN background.
- Mark mine cells with a RED background upon game over.
- Undo funtionality to revert the last move


## Installation
1. Clone the repository
    ```sh 
    git clone https://github.com/NTThanhThao/Algorithm_Project.git
    ```
2. Navigate to the project directory:
    ```sh
    cd minesweepergame
    ```
3. Compile the Java source files:
    ```sh
    javac minesweepergame\Game.java
    ```
4. Run the game
    ```sh
    java minesweepergame.Game
    ```

## How to play

1. **Start the game**: Upon launching, the game window will display a 9x9 grid.
2. **Click on cells**: Left-click on a cell to reveal it.
3. **Reveal safe areas**: Safe cells (no mines) will turn green.
4. **Undo move**: Use the `Undo` button to revert your last move.
5. **Win/Lose condition**:
    - **Win**: Reveal all safe cells without hitting a mine.
    - **Lose**: Click on a mine.


## Acknowledgements

- Minesweeper game logic and GUI implementation.
- Java Swing for the graphical user interface.

