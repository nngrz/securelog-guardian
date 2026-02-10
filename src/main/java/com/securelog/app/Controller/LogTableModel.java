package com.securelog.app.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.securelog.app.Model.LogEntry;

public class LogTableModel extends AbstractTableModel{
    private final String[] cols = {"Time", "Level", "Source", "Message"};
    private final List<LogEntry> rows = new ArrayList<>();

    @Override public int getRowCount() { return rows.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int col) { return cols[col]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LogEntry e = rows.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> e.getTime();
            case 1 -> e.getLevel();
            case 2 -> e.getSource();
            case 3 -> e.getMessage();
            default -> "";
        };
    }

    public void setData(List<LogEntry> data) {
        rows.clear();
        rows.addAll(data);
        fireTableDataChanged();
    }
}
