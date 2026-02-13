package com.securelog.app.View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.securelog.app.Controller.AlertsController;
import com.securelog.app.Controller.IntegrityController;
import com.securelog.app.Controller.LogController;
import com.securelog.app.Model.AlertModel;
import com.securelog.app.Model.IntegrityModel;
import com.securelog.app.Model.LogModel;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("SecureLog Guardian");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        initUI();

        setVisible(true);
    }

    private void initUI() {
        JTabbedPane tabs = new JTabbedPane();

        // Alerts MVC
        AlertModel alertModel = new AlertModel();
        AlertsPanel alertsView = new AlertsPanel();
        AlertsController alertsController = new AlertsController(alertModel, alertsView);

        //Log MVC
        LogModel logModel = new LogModel();
        LogViewerPanel logView = new LogViewerPanel();
        new LogController(logModel, logView, alertsController);

        IntegrityModel integrityModel = new IntegrityModel();
        IntegrityPanel integrityView = new IntegrityPanel();
        new IntegrityController(integrityModel, integrityView);

        tabs.addTab("Log Viewer", logView);
        tabs.addTab("Integrity", integrityView);
        tabs.addTab("Alerts", alertsView);

        setContentPane(tabs);
    }
}
