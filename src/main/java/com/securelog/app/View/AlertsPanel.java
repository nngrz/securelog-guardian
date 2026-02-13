package com.securelog.app.View;

import javax.swing.*;
import java.awt.*;

public class AlertsPanel extends JPanel {

    // Rules list
    private final DefaultListModel<String> rulesModel = new DefaultListModel<>();
    private final JList<String> rulesList = new JList<>(rulesModel);

    // Triggered history list
    private final DefaultListModel<String> historyModel = new DefaultListModel<>();
    private final JList<String> historyList = new JList<>(historyModel);

    private final JButton addButton = new JButton("Add Rule");
    private final JButton removeButton = new JButton("Remove Rule");
    private final JButton clearHistoryButton = new JButton("Clear History");

    public AlertsPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel center = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel left = new JPanel(new BorderLayout(5, 5));
        left.add(new JLabel("Rules"), BorderLayout.NORTH);
        left.add(new JScrollPane(rulesList), BorderLayout.CENTER);

        JPanel right = new JPanel(new BorderLayout(5, 5));
        right.add(new JLabel("Triggered Alerts"), BorderLayout.NORTH);
        right.add(new JScrollPane(historyList), BorderLayout.CENTER);

        center.add(left);
        center.add(right);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(clearHistoryButton);
        bottom.add(addButton);
        bottom.add(removeButton);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    public DefaultListModel<String> getRulesModel() { return rulesModel; }
    public JList<String> getRulesList() { return rulesList; }

    public DefaultListModel<String> getHistoryModel() { return historyModel; }
    public JList<String> getHistoryList() { return historyList; }

    public JButton getAddButton() { return addButton; }
    public JButton getRemoveButton() { return removeButton; }
    public JButton getClearHistoryButton() { return clearHistoryButton; }
}
