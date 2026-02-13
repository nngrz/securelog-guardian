package com.securelog.app.View;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

    public AboutDialog(Window owner) {
        super(owner, "About SecureLog Guardian", ModalityType.APPLICATION_MODAL);

        setSize(420, 240);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        JTextArea text = new JTextArea(
                "SecureLog Guardian\n" +
                "A Java Swing security tool for log triage and file integrity checking.\n\n" +
                "Features:\n" +
                "- Log Viewer (filter/search + details)\n" +
                "- Alerts (keyword rules + triggered history)\n" +
                "- Integrity (SHA-256 baseline/compare)\n\n" +
                "Author: Ningrui Zhang"
        );
        text.setEditable(false);
        text.setOpaque(false);

        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(close);

        add(text, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
