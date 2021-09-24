package ru.durnov.writer;

import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;

import java.util.List;

public class WriterTableList {
    private final List<Table> tableList;

    public WriterTableList(TextDocument textDocument) {
        this.tableList = textDocument.getTableList();
    }

    public Table getTableByName(String protocolName) {
        String rootstring = new TableName(protocolName).tableSearchString();
        for (Table table : tableList) {
            List<Row> rowList = table.getRowList();
            for (Row row : rowList) {
                int cellCount = row.getCellCount();
                for (int i = 0; i < cellCount; i++){
                    Cell cell = row.getCellByIndex(i);
                    if (cell.getStringValue().contains(rootstring)){
                        return table;
                    }
                }
            }
        }
        throw new IllegalStateException("Не найдена таблица " + protocolName);
    }
}
