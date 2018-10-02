package com.tictactoe;

import sun.awt.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeFrame extends JFrame implements ActionListener {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 525;
    private static final int NUM_OF_BUTTONS = 9;

    private int x_score = 0;
    private int o_score = 0;


    private boolean reverse = false;

    private String SYMBOL = "X";
    private final String X_SYMBOL = "X";
    private final String O_SYMBOL = "O";

    public int x_pos = 50;
    public int y_pos = 50;


    MyCustomButton[] buttons = new MyCustomButton[NUM_OF_BUTTONS];
    JPanel panel = new JPanel();


    JLabel label_x;
    JLabel label_o;




    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);

        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);


        add_score();
        add_buttons();


        this.getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void add_score() {
        label_o = new JLabel("Player \"O\" score is "+String.valueOf(o_score));
        label_o.setBounds(175,10,200,30);
        label_o.setFont(new Font("Arial", Font.BOLD, 14));
        label_o.setForeground(Color.WHITE);
        this.panel.add(label_o);

        label_x = new JLabel("Player \"X\" score is "+String.valueOf(x_score));
        label_x.setBounds(175,460,200,30);
        label_x.setFont(new Font("Arial", Font.BOLD, 14));
        label_x.setForeground(Color.WHITE);
        this.panel.add(label_x);
    }

    public void add_buttons() {

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new MyCustomButton("");
            buttons[i].addActionListener(this);
            buttons[i].setBounds(x_pos, y_pos, 100, 100);
            this.panel.add(buttons[i]);

            x_pos += 150;
            if ((i + 1) % 3 == 0) {
                x_pos = 50;
                y_pos += 150;
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        for (MyCustomButton x : buttons) {
            if (e.getSource() == x) {
                if (x.getText() == "") {
                    if (reverse) {
                        SYMBOL = X_SYMBOL;
                    } else {
                        SYMBOL = O_SYMBOL;
                    }
                    x.setText(SYMBOL);
                    check_game_status();
                    reverse = !reverse;
                }
            }
        }
    }

    private void check_game_status() {
        ArrayList<String> list = new ArrayList<String>(buttons.length);
        for (MyCustomButton x : buttons) {
            list.add(x.getText());
        }

        ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();

        ArrayList<String> row1 = new ArrayList<String>(Arrays.asList(list.get(0), list.get(1), list.get(2)));
        ArrayList<String> row2 = new ArrayList<String>(Arrays.asList(list.get(3), list.get(4), list.get(5)));
        ArrayList<String> row3 = new ArrayList<String>(Arrays.asList(list.get(6), list.get(7), list.get(8)));
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        if (isWon(matrix)) {
            if(SYMBOL == X_SYMBOL){
                x_score++;
                this.label_x.setText("Player \""+ SYMBOL+"\" score is "+String.valueOf(x_score));
            } else if(SYMBOL == O_SYMBOL){
                o_score++;
                this.label_o.setText("Player \""+ SYMBOL+"\" score is "+String.valueOf(o_score));
            }
            JOptionPane.showMessageDialog(this, "Player with " + SYMBOL + " won!");
            reset();
        } else if (isFull(matrix)) {
            JOptionPane.showMessageDialog(this, "Draw");
            reset();
        }
    }

    private boolean isFull(ArrayList<ArrayList<String>> matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix.get(i).get(j) == "") {
                    return false;
                }
            }
        }
        return true;
    }

    private void reset() {
        for (MyCustomButton x : buttons) {
            x.setText("");
        }
        JOptionPane.showMessageDialog(this, "Restarting...");
    }

    private boolean isWon(ArrayList<ArrayList<String>> matrix) {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (matrix.get(i).get(0) == SYMBOL && matrix.get(i).get(1) == SYMBOL &&
                    matrix.get(i).get(2) == SYMBOL) {
                return true;
            }
        }
        // check columns
        for (int i = 0; i < 3; i++) {
            if (matrix.get(0).get(i) == SYMBOL && matrix.get(1).get(i) == SYMBOL &&
                    matrix.get(2).get(i) == SYMBOL) {
                return true;
            }
        }
        // check diagonal
        if (matrix.get(0).get(0) == SYMBOL &&
                matrix.get(2).get(2) == SYMBOL && matrix.get(1).get(1) == SYMBOL) {
            return true;
        } else if (matrix.get(0).get(2) == SYMBOL &&
                matrix.get(2).get(0) == SYMBOL && matrix.get(1).get(1) == SYMBOL) {
            return true;
        }
        return false;
    }
}

