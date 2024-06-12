/*  Name: Nguyen Thi Thanh Thao
    IU code
    Purpose: Classical game from MS WindowsXP 
*/
package minesweepergame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Stack;

public class Game extends JFrame {
    private static final int ROWS = 9;
    private static final int COLS = 9;
    private static final int MINES = 10;

    private JPanel gamePanel;
    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] revealed;
    private Stack<Point> moves;
    private JButton undoButton;

    public Game() {
        setPreferredSize(new Dimension(480, 640));
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new JPanel(new GridLayout(ROWS, COLS));
        add(gamePanel, BorderLayout.CENTER);

        undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> undo());
        add(undoButton, BorderLayout.SOUTH);

        buttons = new JButton[ROWS][COLS];
        mines = new boolean[ROWS][COLS];
        revealed = new boolean[ROWS][COLS];
        moves = new Stack<>();

        initializeBoard();
        createButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeBoard() {
        Random random = new Random();
        for (int i = 0; i < MINES; i++) {
            int row = random.nextInt(ROWS);
            int col = random.nextInt(COLS);
            if (!mines[row][col]) {
                mines[row][col] = true;
            } else {
                i--;
            }
        }
    }

    private void createButtons() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JButton button = new JButton("");
                button.addMouseListener(new ButtonMouseListener(row, col));
                buttons[row][col] = button;
                gamePanel.add(button);
            }
        }
    }

    private int getAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = Math.max(0, row - 1); i <= Math.min(ROWS - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(COLS - 1, col + 1); j++) {
                if (mines[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void revealCell(int row, int col) {
        if (!revealed[row][col]) {
            revealed[row][col] = true;
            moves.push(new Point(row, col));
            if (mines[row][col]) {
                gameOver();
            } else if (getAdjacentMines(row, col) == 0) {
                for (int i = Math.max(0, row - 1); i <= Math.min(ROWS - 1, row + 1); i++) {
                    for (int j = Math.max(0, col - 1); j <= Math.min(COLS - 1, col + 1); j++) {
                        revealCell(i, j);
                    }
                }
            }
            updateButtonText(row, col);
        }
    }

    private void updateButtonText(int row, int col) {
        if (revealed[row][col]) {
            int adjacentMines = getAdjacentMines(row, col);
            if (mines[row][col]) {
                buttons[row][col].setText("X");
                buttons[row][col].setBackground(Color.RED);
            } else if (adjacentMines > 0) {
                buttons[row][col].setText(Integer.toString(adjacentMines));
            } else {
                buttons[row][col].setText("");
                buttons[row][col].setBackground(Color.GREEN);
            }
        }
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over!");
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                revealed[row][col] = true;
                updateButtonText(row, col);
            }
        }
    }

    private boolean isLose() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (!mines[row][col] && !revealed[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void undo(){
        if (!moves.isEmpty()) {
            Point lastMove = moves.pop();
            revealed[lastMove.x][lastMove.y] = false;
            buttons[lastMove.x][lastMove.y].setText("");
            buttons[lastMove.x][lastMove.y].setBackground(null);
        } else {
            JOptionPane.showMessageDialog(this, "No move to undo");
        }
    }

    private class ButtonMouseListener extends MouseAdapter {
        private int row;
        private int col;

        public ButtonMouseListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            revealCell(row, col);
            if (isLose()) {
                JOptionPane.showMessageDialog(Game.this, "You lose!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}
