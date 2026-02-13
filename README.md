# SecureLog Guardian (Java Swing)

A lightweight **Java Swing desktop tool** for **security log triage** and **file integrity verification** (SHA-256 baseline/compare).
Built with a clean **MVC (Model–View–Controller)** structure to match exam-focused GUI architecture best practices.

---

## Features

### Log Viewer
- Open and view logs in a **JTable**
- Filter by level (ALL/INFO/WARN/ERROR)
- Keyword search (message/source)
- Double-click a row to open a **details dialog**
- Connected to Alerts: rules are evaluated when a log file is loaded

### Alerts
- Manage keyword-based alert rules (**JList + DefaultListModel**)
- Automatically detects matching log entries on log load
- Shows **triggered alert history** and displays warning popups

### Integrity (SHA-256)
- Compute SHA-256 for a selected file
- Save a baseline hash locally (`baseline.properties`)
- Compare current hash with baseline to detect file changes
