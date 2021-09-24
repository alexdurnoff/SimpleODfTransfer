package ru.durnov.writer;

import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Row;

import java.util.List;


public class ReportProtocol implements Protocol{
    private final org.odftoolkit.simple.table.Table table;



    public ReportProtocol(TextDocument textDocument, String protocolName) {
        this.table = new WriterTableList(textDocument).getTableByName(protocolName);
    }


    @Override
    public void copyData(Object[][] data) {
        List<Row> rows = table.appendRows(data.length);
        for (int j = 0; j < data.length; j++) {
            Row row =rows.get(j);
            Object[] dataRow = data[j];
            HeaderPanel headerPanel = new HeaderPanel(dataRow);
            if (headerPanel.isHeader()){
                new BoldCell(row, dataRow).writePanelHeaderValue();
            } else {
                for (int i = 0; i < dataRow.length; i++){
                    Cell cell = row.getCellByIndex(i);
                    new WriterCellValue(cell, dataRow[i]).setCellValue();
                }
            }

        }
    }
}
