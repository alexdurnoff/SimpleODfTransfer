package ru.durnov.writer;

import org.odftoolkit.simple.style.Font;
import org.odftoolkit.simple.style.StyleTypeDefinitions;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Row;

public class BoldCell {
    private final Row row;
    private final Object[] dataRow;

    public BoldCell(Row row, Object[] dataRow) {
        this.row = row;
        this.dataRow = dataRow;
    }

    public void writePanelHeaderValue() {
        String value = String.valueOf(dataRow[1]);
        Cell cell = row.getCellByIndex(1);
        cell.setStringValue(value);
        cell.setFont(new Font("Liberation Serif", StyleTypeDefinitions.FontStyle.BOLD, 10));
    }
}
