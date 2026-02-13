package com.securelog.app.Controller;

import javax.swing.SwingUtilities;

import com.securelog.app.Model.AlertModel;
import com.securelog.app.Model.AlertRule;
import com.securelog.app.View.AddRuleDialog;
import com.securelog.app.View.AlertsPanel;

public class AlertsController {

    private final AlertModel model;
    private final AlertsPanel view;

    public AlertsController(AlertModel model, AlertsPanel view) {
        this.model = model;
        this.view = view;
        bindEvents();
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
                    AlertRule rule = new AlertRule(keyword);
                    model.addRule(rule);
                    view.getListModel().addElement(keyword);
                }
            }
        });

        view.getRemoveButton().addActionListener(e -> {
            int index = view.getRuleList().getSelectedIndex();
            if (index >= 0) {
                String keyword = view.getListModel().get(index);
                model.removeRule(new AlertRule(keyword));
                view.getListModel().remove(index);
            }
        });
    }
}
