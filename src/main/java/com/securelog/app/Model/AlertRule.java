package com.securelog.app.Model;

public class AlertRule {
    private final String keyword;

    public AlertRule(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public String toString() {
        return keyword;
    }
}
