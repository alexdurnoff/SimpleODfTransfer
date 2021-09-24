package ru.durnov.spreadsheet;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.sheet.XCellRangeData;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.table.XCellRange;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;

public class SpreadSheetData implements Data{
    private final String spreadSheetName;
    private final XSpreadsheet xSpreadsheet;

    public SpreadSheetData(String spreadSheetName) throws BootstrapException, Exception {
        this.spreadSheetName = spreadSheetName;
        this.xSpreadsheet = SpreadSheetUtils.getCurrentSpreadsheet(this.spreadSheetName);
        System.out.println("Data constructor is done");
    }

    @Override
    public Object[][] data() throws BootstrapException, Exception {
        int startRow = 0;
        int endColumn = 0;
        switch (this.spreadSheetName){
            case "Изоляция":
                startRow = 19;
                endColumn = 14;
                break;
            case "УЗО":
                startRow = 14;
                endColumn = 14;
                break;
            case "Петля":
                startRow = 21;
                endColumn = 13;
                break;
            case "Автомат":
                startRow = 21;
                endColumn = 16;
                break;
        }
        XCellRange xCellRange = xSpreadsheet.getCellRangeByPosition(0, startRow,
                endColumn, getNumberOfProtocolLines());
        XCellRangeData xCellRangeData = UnoRuntime.queryInterface(XCellRangeData.class, xCellRange);
        return xCellRangeData.getDataArray();
    }

    private int getNumberOfProtocolLines() throws IndexOutOfBoundsException {
        int number = 0;
        switch (this.spreadSheetName){
            case "Изоляция":
                number = 19;
                break;
            case "УЗО":
                number = 12;
                break;
            case "Петля":
            case "Автомат":
                number = 21;
                break;
        }
        String B = xSpreadsheet.getCellByPosition(1, number).getFormula();
        while (!(B.equals(""))){
            number ++;
            B = xSpreadsheet.getCellByPosition(1, number).getFormula();
        }
        return number - 1;
    }


}
