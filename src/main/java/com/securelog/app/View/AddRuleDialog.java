package com.securelog.app.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.concurrent.Flow;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddRuleDialog extends JDialog {

    private boolean confirmed = false;
    private final JTextField keywordField = new JTextField(20);

    public AddRuleDialog(Window owner) {
        super(owner, "Add Alert Rule", ModalityType.APPLICATION_MODAL);

        setSize(300, 150);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        JPanel center = new JPanel(new FlowLayout());
        center.add(new JLabel("Keyword:"));
        center.add(keywordField);

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancel.addActionListener((e -> dispose()));

        JPanel bottom = new JPanel();
        bottom.add(ok);
        bottom.add(cancel);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getKeyword() {
        return keywordField.getText();
    }
}
