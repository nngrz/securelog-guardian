package com.securelog.app.View;

import java.awt.*;
import javax.swing.*;

public class LogViewerPanel extends JPanel {
    private final JButton openButton = new JButton("Open Log...");
    private final JComboBox<String> levelBox = new JComboBox<>(new String[]{"ALL", "INFO", "WARN", "ERROR"});
    private final JTextField searchField = new JTextField(20);

    private final JTable table = new JTable();

    public LogViewerPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(openButton);
        top.add(new JLabel("Level:"));
        top.add(levelBox);
        top.add(new JLabel("Search:"));
        top.add(searchField);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public JButton getOpenButton() { return openButton; }
    public JComboBox<String> getLevelBox() { return levelBox; }
    public JTextField getSearchField() { return searchField; }
    public JTable getTable() { return table; }
}
