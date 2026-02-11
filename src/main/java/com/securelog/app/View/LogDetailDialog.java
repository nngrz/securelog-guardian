package com.securelog.app.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.securelog.app.Model.LogEntry;

public class LogDetailDialog extends JDialog {

    public LogDetailDialog(Window owner, LogEntry entry) {
        super(owner, "Log Details", ModalityType.APPLICATION_MODAL);

        setSize(500, 300);
        setLocationRelativeTo(owner);

        setLayout(new BorderLayout(10, 10));

        JPanel info = new JPanel(new GridLayout(3, 2, 5, 5));
        info.add(new JLabel("Time:"));
        info.add(new JLabel(entry.getTime()));
        info.add(new JLabel("Level:"));
        info.add(new JLabel(entry.getLevel()));
        info.add(new JLabel("Source:"));
        info.add(new JLabel(entry.getSource()));

        JTextArea messageArea = new JTextArea(entry.getMessage());
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);

        add(info, BorderLayout.NORTH);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());

        JPanel buttom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttom.add(close);

        add(buttom, BorderLayout.SOUTH);

    }
}
