import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToeGUI extends JFrame {

    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }

        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**@Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (checkDraw()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetGame();
                } else {
                    currentPlayer = 'X';
                    computerMove();
                    if (checkWin()) {
                        JOptionPane.showMessageDialog(null, "Computer wins!");
                        resetGame();
                    } else if (checkDraw()) {
                        JOptionPane.showMessageDialog(null, "It's a draw!");
                        resetGame();
                    }
                }
            }
        }**/
        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));

                if (checkWin()) {
                    if (currentPlayer == 'X') {
                        JOptionPane.showMessageDialog(null, "Player X wins!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Computer wins!");
                    }
                    resetGame();
                } else if (checkDraw()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetGame();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    if (currentPlayer == 'O') {
                        computerMove();
                    }
                }
            }
        }
    }

    private boolean checkDraw() {
        // Check if all buttons are filled
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // Not a draw, there are still empty buttons
                }
            }
        }
        return true; // It's a draw
    }

    private void computerMove() {
        int zeroIsPut = 0;

        // Winning move or block the opponent
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][0].getText().equals("O") && buttons[i][2].getText().equals("")) {
                buttons[i][2].setText("O");
                zeroIsPut = 1;
                break;
            } else if (buttons[i][0].getText().equals(buttons[i][2].getText()) && buttons[i][0].getText().equals("O") && buttons[i][1].getText().equals("")) {
                buttons[i][1].setText("O");
                zeroIsPut = 1;
                break;
            } else if (buttons[i][1].getText().equals(buttons[i][2].getText()) && buttons[i][1].getText().equals("O") && buttons[i][0].getText().equals("")) {
                buttons[i][0].setText("O");
                zeroIsPut = 1;
                break;
            }

            // Check columns
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[0][i].getText().equals("O") && buttons[2][i].getText().equals("")) {
                buttons[2][i].setText("O");
                zeroIsPut = 1;
                break;
            } else if (buttons[0][i].getText().equals(buttons[2][i].getText()) && buttons[0][i].getText().equals("O") && buttons[1][i].getText().equals("")) {
                buttons[1][i].setText("O");
                zeroIsPut = 1;
                break;
            } else if (buttons[1][i].getText().equals(buttons[2][i].getText()) && buttons[1][i].getText().equals("O") && buttons[0][i].getText().equals("")) {
                buttons[0][i].setText("O");
                zeroIsPut = 1;
                break;
            }
        }

        // Diagonal win check
        if ((buttons[0][0].getText().equals(buttons[2][2].getText())) && (buttons[0][0].getText().equals("O")) && buttons[1][1].getText().equals("")) {
            buttons[1][1].setText("O");
            zeroIsPut = 1;
        } else if ((buttons[0][2].getText().equals(buttons[2][0].getText())) && (buttons[0][2].getText().equals("O")) && buttons[1][1].getText().equals("")) {
            buttons[1][1].setText("O");
            zeroIsPut = 1;
        } else if ((buttons[0][0].getText().equals(buttons[1][1].getText())) && (buttons[0][0].getText().equals("O")) && buttons[2][2].getText().equals("")) {
            buttons[2][2].setText("O");
            zeroIsPut = 1;
        } else if ((buttons[0][2].getText().equals(buttons[1][1].getText())) && (buttons[0][2].getText().equals("O")) && buttons[2][0].getText().equals("")) {
            buttons[2][0].setText("O");
            zeroIsPut = 1;
        } else if ((buttons[2][0].getText().equals(buttons[1][1].getText())) && (buttons[2][0].getText().equals("O")) && buttons[0][2].getText().equals("")) {
            buttons[1][1].setText("O");
            zeroIsPut = 1;
        } else if ((buttons[2][2].getText().equals(buttons[1][1].getText())) && (buttons[2][2].getText().equals("O")) && buttons[0][0].getText().equals("")) {
            buttons[0][0].setText("O");
            zeroIsPut = 1;
        }

        // Check for empty center and take it
        if (zeroIsPut == 0 && buttons[1][1].getText().equals("")) {
            buttons[1][1].setText("O");
            zeroIsPut = 1;
        }

        // Check for empty corners and take one
        if (zeroIsPut == 0) {
            if (buttons[0][0].getText().equals("")) {
                buttons[0][0].setText("O");
                zeroIsPut = 1;
            } else if (buttons[0][2].getText().equals("")) {
                buttons[0][2].setText("O");
                zeroIsPut = 1;
            } else if (buttons[2][0].getText().equals("")) {
                buttons[2][0].setText("O");
                zeroIsPut = 1;
            } else if (buttons[2][2].getText().equals("")) {
                buttons[2][2].setText("O");
                zeroIsPut = 1;
            }
        }

        // Check for any remaining empty space
        if (zeroIsPut == 0) {
            Random random = new Random();
            int randomRow, randomCol;

            do {
                randomRow = random.nextInt(3);
                randomCol = random.nextInt(3);
            } while (!buttons[randomRow][randomCol].getText().equals(""));

            buttons[randomRow][randomCol].setText("O");
        }

        if (checkWin()) {
            JOptionPane.showMessageDialog(null, "Computer wins!");
            resetGame();
            return;  // Exit the method to avoid further player's turn
        }

        // Check for draw after computer's move
        if (checkDraw()) {
            JOptionPane.showMessageDialog(null, "It's a draw!");
            resetGame();
        } else {
            currentPlayer = 'X';  // Switch to player's turn
        }
    }


    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Row win
            }

            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Column win
            }
        }

        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal win (top-left to bottom-right)
        }

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal win (top-right to bottom-left)
        }

        return false;
    }
    private void resetGame() {
        // Reset the board and current player
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}

