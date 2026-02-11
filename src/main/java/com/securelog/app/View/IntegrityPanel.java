package com.securelog.app.View;

import javax.swing.*;
import java.awt.*;

public class IntegrityPanel extends JPanel {
    private final JButton chooseButton = new JButton("Choose File...");
    private final JButton hashButton = new JButton("Compute SHA-256");
    private final JButton saveBaselineButton = new JButton("Save Baseline");
    private final JButton compareButton = new JButton("Compare with Baseline");

    private final JTextField pathField = new JTextField();
    private final JTextField shaField = new JTextField();
    private final JLabel statusLabel = new JLabel("Status: -");

    public IntegrityPanel() {
        setLayout(new BorderLayout(10, 10));

        // Top controls
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(chooseButton);
        top.add(hashButton);
        top.add(saveBaselineButton);
        top.add(compareButton);

        // Info fields
        pathField.setEditable(false);
        shaField.setEditable(false);

        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0; c.weightx = 0;
        center.add(new JLabel("File:"), c);

        c.gridx = 1; c.gridy = 0; c.weightx = 1;
        center.add(pathField, c);

        c.gridx = 0; c.gridy = 1; c.weightx = 0;
        center.add(new JLabel("SHA-256:"), c);

        c.gridx = 1; c.gridy = 1; c.weightx = 1;
        center.add(shaField, c);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.add(statusLabel);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        hashButton.setEnabled(false);
        saveBaselineButton.setEnabled(false);
        compareButton.setEnabled(false);
    }

    // Getters for controller
    public JButton getChooseButton() { return chooseButton; }
    public JButton getHashButton() { return hashButton; }
    public JButton getSaveBaselineButton() { return saveBaselineButton; }
    public JButton getCompareButton() { return compareButton; }

    public JTextField getPathField() { return pathField; }
    public JTextField getShaField() { return shaField; }
    public JLabel getStatusLabel() { return statusLabel; }
}
