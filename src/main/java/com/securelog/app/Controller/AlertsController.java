package com.securelog.app.Controller;

import com.securelog.app.Model.AlertModel;
import com.securelog.app.Model.AlertRule;
import com.securelog.app.Model.LogEntry;
import com.securelog.app.View.AddRuleDialog;
import com.securelog.app.View.AlertsPanel;

import javax.swing.*;
import java.util.List;

public class AlertsController {

    private final AlertModel model;
    private final AlertsPanel view;

    public AlertsController(AlertModel model, AlertsPanel view) {
        this.model = model;
        this.view = view;
        bindEvents();
        refreshView();
    }

    private void bindEvents() {

        view.getAddButton().addActionListener(e -> {
            AddRuleDialog dialog = new AddRuleDialog(
                    SwingUtilities.getWindowAncestor(view)
            );
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                String keyword = dialog.getKeyword().trim();
                if (!keyword.isEmpty()) {
                    model.addRule(new AlertRule(keyword));
                    refreshRules();
                }
            }
        });

        view.getRemoveButton().addActionListener(e -> {
            int index = view.getRulesList().getSelectedIndex();
            if (index >= 0) {
                String keyword = view.getRulesModel().get(index);
                model.removeRule(new AlertRule(keyword));
                refreshRules();
            }
        });

        view.getClearHistoryButton().addActionListener(e -> {
            model.clearHistory();
            refreshHistory();
        });
    }

    public void checkLogs(List<LogEntry> entries) {
        if (model.getRules().isEmpty()) return;
        if (entries == null || entries.isEmpty()) return;

        int hitCount = 0;

        for (LogEntry e : entries) {
            if (model.matchesAnyRule(e)) {
                hitCount++;

                String item = String.format("[%s] %s | %s",
                        e.getLevel(), e.getTime(), e.getMessage());

                model.addHistory(item);
                view.getHistoryModel().addElement(item);

                JOptionPane.showMessageDialog(view,
                        "Alert matched a rule:\n" + item,
                        "Security Alert",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        if (hitCount == 0) {
        }
    }

    private void refreshView() {
        refreshRules();
        refreshHistory();
    }

    private void refreshRules() {
        view.getRulesModel().clear();
        for (AlertRule r : model.getRules()) {
            view.getRulesModel().addElement(r.getKeyword());
        }
    }

    private void refreshHistory() {
        view.getHistoryModel().clear();
        for (String h : model.getHistory()) {
            view.getHistoryModel().addElement(h);
        }
    }
}
