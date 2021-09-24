package ru.durnov.spreadsheet;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.container.XNameAccess;
import com.sun.star.frame.XDesktop;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.sheet.XCellRangeData;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.table.XCellRange;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

public class SpreadSheetUtils {
    public static XSpreadsheet getCurrentSpreadsheet(String spreadsheetname)
            throws Exception, BootstrapException {
        XComponent model = getCurrentComponent();
        XSpreadsheetDocument xSpreadsheetDocument = UnoRuntime.queryInterface(XSpreadsheetDocument.class,model);
        XSpreadsheets xSpreadsheets = xSpreadsheetDocument.getSheets();
        XNameAccess xNameAccess = UnoRuntime.queryInterface(XNameAccess.class, xSpreadsheets);
        return UnoRuntime.queryInterface(XSpreadsheet.class, xNameAccess.getByName(spreadsheetname));
    }

    private static XComponent getCurrentComponent() throws Exception, BootstrapException {
        XComponentContext xComponentContext = com.sun.star.comp.helper.Bootstrap.bootstrap();
        XMultiComponentFactory xMultiComponentFactory = xComponentContext.getServiceManager();
        Object oDesktop = xMultiComponentFactory.createInstanceWithContext("com.sun.star.frame.Desktop",
                xComponentContext);
        XDesktop xDesktop = UnoRuntime.queryInterface(XDesktop.class, oDesktop);
        XComponent model = xDesktop.getCurrentComponent();
        return model;
    }

    public static Object[][] getSourceProtokolData(String sheetName) throws Exception, BootstrapException {
        XSpreadsheet sheet = getCurrentSpreadsheet(sheetName);
        int startRow = 0;
        int endColumn = 0;
        switch (sheetName){
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
        XCellRange xCellRange = sheet.getCellRangeByPosition(0, startRow,
                endColumn, getNumberOfProtocolLines(sheetName));
        XCellRangeData xCellRangeData = UnoRuntime.queryInterface(XCellRangeData.class, xCellRange);
        Object[][] sourceIsolationData = xCellRangeData.getDataArray();
        return sourceIsolationData;
    }

    private static int getNumberOfProtocolLines(String sheetName)
            throws Exception, BootstrapException {
        XSpreadsheet protokolSheet = getCurrentSpreadsheet(sheetName);
        int number = 0;
        switch (sheetName){
            case "Изоляция":
                number = 19;
                break;
            case "УЗО":
                number = 14;
                break;
            case "Петля":
            case "Автомат":
                number = 21;
                break;
        }
        String B = protokolSheet.getCellByPosition(1, number).getFormula();
        while (!(B.equals(""))){
            number ++;
            B = protokolSheet.getCellByPosition(1, number).getFormula();
        }
        return number - 1;
    }
}
