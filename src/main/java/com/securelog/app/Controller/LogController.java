package com.securelog.app.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.securelog.app.Model.LogEntry;
import com.securelog.app.Model.LogModel;
import com.securelog.app.View.LogViewerPanel;

public class LogController {
    private final LogModel model;
    private final LogViewerPanel view;
    private final LogTableModel tableModel = new LogTableModel();

    public LogController(LogModel model, LogViewerPanel view) {
        this.model = model;
        this.view = view;

        this.view.getTable().setModel(tableModel);

        bindEvents();
        refreshTable();
    }

    public void bindEvents() {
        view.getOpenButton().addActionListener((e -> openAndLoad()));
        view.getLevelBox().addActionListener(e -> refreshTable());

        view.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { refreshTable(); }
            @Override public void removeUpdate(DocumentEvent e) { refreshTable(); }
            @Override public void changedUpdate(DocumentEvent e) { refreshTable(); }
        });
    }

    public void refreshTable() {
        String level = (String) view.getLevelBox().getSelectedItem();
        String keyword = view.getSearchField().getText();
        tableModel.setData(model.getFiltered(level, keyword));
    }

    private void openAndLoad() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(view) != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();

        List<LogEntry> loaded = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", 4);
                if (p.length < 4) continue;
                loaded.add(new LogEntry(p[0], p[1], p[2], p[3]));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                "Failed to read file:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.setAll(loaded);
        refreshTable();
    }
}
