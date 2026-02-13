package com.securelog.app.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlertModel {

    private final List<AlertRule> rules = new ArrayList<>();
    private final List<String> history = new ArrayList<>();

    public void addRule(AlertRule rule) {
        rules.add(rule);
    }

    public void removeRule(AlertRule rule) {
        rules.removeIf(r -> r.getKeyword().equalsIgnoreCase(rule.getKeyword()));
    }

    public List<AlertRule> getRules() {
        return new ArrayList<>(rules);
    }

    public void addHistory(String item) {
        history.add(item);
    }

    public void clearHistory() {
        history.clear();
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    public boolean matchesAnyRule(LogEntry entry) {
        String msg = (entry.getMessage() == null) ? "" : entry.getMessage().toLowerCase(Locale.ROOT);
        String src = (entry.getSource() == null) ? "" : entry.getSource().toLowerCase(Locale.ROOT);

        for (AlertRule r : rules) {
            String kw = r.getKeyword().toLowerCase(Locale.ROOT);
            if (msg.contains(kw) || src.contains(kw)) return true;
        }
        return false;
    }
}
