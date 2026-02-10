package com.securelog.app.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class LogModel {
    private final List<LogEntry> all = new ArrayList<>();

    public void setAll(List<LogEntry> entries) {
        all.clear();
        all.addAll(entries);
    }

    public List<LogEntry> getFiltered(String level, String keyword) {
        String lv = (level == null) ? "ALL" : level;
        String kw = (keyword == null) ? "" : keyword.trim().toLowerCase(Locale.ROOT);

        return all.stream()
            .filter(e -> "ALL".equals(lv) || e.getLevel().equalsIgnoreCase(lv))
            .filter(e -> kw.isEmpty()
                    || e.getMessage().toLowerCase(Locale.ROOT).contains(kw)
                    || e.getSource().toLowerCase(Locale.ROOT).contains(kw))
            .collect(Collectors.toList());
    }
}
