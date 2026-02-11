package com.securelog.app.Controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.securelog.app.Model.IntegrityModel;
import com.securelog.app.View.IntegrityPanel;

public class IntegrityController {

    private final IntegrityModel model;
    private final IntegrityPanel view;

    public IntegrityController(IntegrityModel model, IntegrityPanel view) {
        this.model = model;
        this.view = view;
        bindEvents();
    }

    private void bindEvents() {
        view.getChooseButton().addActionListener(e -> chooseFile());
        view.getHashButton().addActionListener(e -> computeHash());
        view.getSaveBaselineButton().addActionListener(e -> saveBaseline());
        view.getCompareButton().addActionListener(e -> compareBaseline());
    }

    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(view) != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();
        model.setSelectedFile(file);

        view.getPathField().setText(file.getAbsolutePath());
        view.getShaField().setText("");
        view.getStatusLabel().setText("Status: file selected");

        view.getHashButton().setEnabled(true);
        view.getSaveBaselineButton().setEnabled(false);
        view.getCompareButton().setEnabled(false);
    }

    private void computeHash() {
        File file = model.getSelectedFile().orElse(null);
        if (file == null) return;

        try {
            String sha = HashUtil.sha256Hex(file);
            model.setCurrentSha256(sha);

            view.getShaField().setText(sha);
            view.getStatusLabel().setText("Status: hash computed");

            view.getSaveBaselineButton().setEnabled(true);
            view.getCompareButton().setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                    "Failed to compute SHA-256:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveBaseline() {
        File file = model.getSelectedFile().orElse(null);
        String sha = model.getCurrentSha256().orElse(null);
        if (file == null || sha == null) return;

        try {
            model.saveBaseline(file.toPath(), sha);
            view.getStatusLabel().setText("Status: baseline saved");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                    "Failed to save baseline:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void compareBaseline() {
        File file = model.getSelectedFile().orElse(null);
        if (file == null) return;

        try {
            String current = HashUtil.sha256Hex(file);
            String baseline = model.readBaseline(file.toPath()).orElse(null);

            if (baseline == null) {
                view.getStatusLabel().setText("Status: no baseline found (save baseline first)");
                return;
            }

            if (baseline.equalsIgnoreCase(current)) {
                view.getStatusLabel().setText("Status: PASS (no change detected)");
            } else {
                view.getStatusLabel().setText("Status: FAIL (file changed!)");
            }

            model.setCurrentSha256(current);
            view.getShaField().setText(current);

            view.getSaveBaselineButton().setEnabled(true);
            view.getCompareButton().setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                    "Compare failed:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
