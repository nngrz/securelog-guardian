package com.securelog.app.View;

import java.awt.*;
import javax.swing.*;

public class AlertsPanel extends JPanel {

    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> ruleList = new JList<>(listModel);

    private final JButton addButton = new JButton("Add Rule");
    private final JButton removeButton = new JButton("Remove Rule");

    public AlertsPanel() {
        setLayout(new BorderLayout(10, 10));

        add(new JScrollPane(ruleList), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(addButton);
        bottom.add(removeButton);

        add(bottom, BorderLayout.SOUTH);
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public JList<String> getRuleList() {
        return ruleList;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }
}
