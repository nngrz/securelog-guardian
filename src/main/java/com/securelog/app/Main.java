package com.securelog.app;

import javax.swing.SwingUtilities;
import com.securelog.app.View.MainFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
