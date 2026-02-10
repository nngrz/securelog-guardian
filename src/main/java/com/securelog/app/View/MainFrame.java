package com.securelog.app.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.securelog.app.Controller.LogController;
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

        LogModel logModel = new LogModel();
        LogViewerPanel logView = new LogViewerPanel();
        new LogController(logModel, logView);

        tabs.addTab("Log Viewer", logView);
        tabs.addTab("Integrity", new JPanel());
        tabs.addTab("Alerts", new JPanel());

        setContentPane(tabs);
    }
}
