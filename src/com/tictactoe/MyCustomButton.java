package com.tictactoe;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MyCustomButton extends JButton {
    private Color pressedColor = Color.GREEN;
    private Color rolloverColor = Color.RED;
    private Color normalColor = Color.green;

    public MyCustomButton (String text) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);

        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 32));
        setText(text);
    }
}

