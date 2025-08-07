import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SudokuGame {
    private static final int SIZE = 9;
    private JFrame frame;
    private JTextField[][] grid;
    private int[][] puzzle;
    private int[][] solution;

    public SudokuGame() {
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        grid = new JTextField[SIZE][SIZE];
        SudokuGenerator generator = new SudokuGenerator();
        puzzle = generator.getPuzzle();
        solution = generator.getSolution();

        JPanel boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        Font font = new Font("Arial", Font.BOLD, 18);

        // Build Sudoku Grid
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(font);

                if (puzzle[row][col] != 0) {
                    cell.setText(String.valueOf(puzzle[row][col]));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                }

                grid[row][col] = cell;
                boardPanel.add(cell);
            }
        }

        JButton checkButton = new JButton("Check Solution");
        checkButton.addActionListener(e -> validateUserInput());

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(checkButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void validateUserInput() {
        boolean isCorrect = true;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = grid[row][col];
                if (cell.isEditable()) {
                    String input = cell.getText();
                    if (!input.matches("\\d")) {
                        cell.setForeground(Color.RED);
                        isCorrect = false;
                    } else {
                        int value = Integer.parseInt(input);
                        if (value != solution[row][col]) {
                            cell.setForeground(Color.RED);
                            isCorrect = false;
                        } else {
                            cell.setForeground(Color.BLACK);
                        }
                    }
                }
            }
        }

        String message = isCorrect ? "Correct! Well done!" : "Some entries are incorrect.";
        JOptionPane.showMessageDialog(frame, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGame::new);
    }
}
