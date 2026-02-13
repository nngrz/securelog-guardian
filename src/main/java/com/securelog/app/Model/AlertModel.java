package com.securelog.app.Model;

import java.util.ArrayList;
import java.util.List;

public class AlertModel {
    private final List<AlertRule> rules = new ArrayList<>();

    public void addRule(AlertRule rule) {
        rules.add(rule);
    }

    public void removeRule(AlertRule rule) {
        rules.remove(rule);
    }

    public List<AlertRule> getRules() {
        return new ArrayList<>(rules);
    }
}
