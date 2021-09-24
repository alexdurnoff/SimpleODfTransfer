package ru.durnov.spreadsheet;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;

public class ReportTemplate implements Data{
    private final String spreadSheetName;

    public ReportTemplate(String spreadSheetName) {
        this.spreadSheetName = spreadSheetName;
    }

    @Override
    public Object[][] data() throws BootstrapException, Exception {
        Object[][] data = SpreadSheetUtils.getSourceProtokolData(spreadSheetName);
        return data;
    }
}
