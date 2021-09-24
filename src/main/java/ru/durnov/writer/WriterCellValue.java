package ru.durnov.writer;

import org.odftoolkit.simple.style.Font;
import org.odftoolkit.simple.style.StyleTypeDefinitions;
import org.odftoolkit.simple.table.Cell;

public class WriterCellValue {
    private final Cell cell;
    private final Object dataCell;

    public WriterCellValue(Cell cell, Object dataCell) {
        this.cell = cell;
        this.dataCell = dataCell;
    }

    public void setCellValue() {
        String value = new CellValue(dataCell).value();
        cell.setStringValue(value);
        cell.setFont(new Font("Liberation Serif", StyleTypeDefinitions.FontStyle.REGULAR,10));
        cell.setHorizontalAlignment(StyleTypeDefinitions.HorizontalAlignmentType.LEFT);
        cell.setVerticalAlignment(StyleTypeDefinitions.VerticalAlignmentType.MIDDLE);
    }
}

class CellValue {
    private final Object dataCell;

    public CellValue(Object dataCell) {
        this.dataCell = dataCell;

    }

    public String value() {
        if (dataCell instanceof Double){
            String result = String.valueOf(dataCell);
            if (result.endsWith(".0")){
                result = result.substring(0,result.length()-2);
            }
            return result.replace(".", ",");
        }
        return String.valueOf(dataCell);
    }
}
