package ru.durnov.writer;

import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;

import java.util.List;


public class RowFormat {
    private final String boldStyleName;

    public RowFormat(OdfTable odfTable){
        List<OdfTableRow> rowList = odfTable.getRowList();
        this.boldStyleName = odfTable.getRowList().get(rowList.size() - 1).getCellByIndex(1).getStyleName();
        System.out.println("boldStyleName is " + boldStyleName);
    }

    public void formatRow(OdfTableRow odfTableRow) {
        String firstValue = odfTableRow.getCellByIndex(0).getStringValue();
        if (firstValue == null || firstValue.isEmpty()){
            OdfTableCell cell = odfTableRow.getCellByIndex(1);
            String value = cell.getStringValue();
            cell.setDisplayText(value, "Заголовок_щита");
        }
    }
}
