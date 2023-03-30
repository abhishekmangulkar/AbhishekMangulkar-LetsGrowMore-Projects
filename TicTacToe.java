import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JLabel message;
    private int turn;

    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        message = new JLabel("Player 1's turn");
        turn = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        add(message);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getText().equals("")) {
            if (turn == 1) {
                button.setText("X");
                message.setText("Player 2's turn");
                turn = 2;
            } else {
                button.setText("O");
                message.setText("Player 1's turn");
                turn = 1;
            }

            checkForWin();
        }
    }

    public void checkForWin() {
        String winner = "";

        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][1].getText().equals(buttons[i][2].getText()) && !buttons[i][0].getText().equals("")) {
                winner = buttons[i][0].getText();
            }
        }

        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(buttons[1][j].getText()) && buttons[1][j].getText().equals(buttons[2][j].getText()) && !buttons[0][j].getText().equals("")) {
                winner = buttons[0][j].getText();
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().equals("")) {
            winner = buttons[0][0].getText();
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[0][2].getText().equals("")) {
            winner = buttons[0][2].getText();
        }

        if (!winner.equals("")) {
            message.setText(winner + " wins!");
            disableButtons();
        } else if (isBoardFull()) {
            message.setText("Tie game!");
        }
    }

    public boolean isBoardFull() {
        boolean full = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    full = false;
                }
            }
        }

        return full;
    }

    public void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

