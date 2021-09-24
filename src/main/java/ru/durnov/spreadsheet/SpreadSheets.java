package ru.durnov.spreadsheet;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;

import java.util.HashMap;
import java.util.Map;

public class SpreadSheets {
    private final Map<String, Object[][]> spreadSheetsDataMap = new HashMap<>();

    public SpreadSheets(String[] args) throws BootstrapException, Exception {
        for (String arg : args) {
            this.spreadSheetsDataMap.put(arg, SpreadSheetUtils.getSourceProtokolData(arg));
        }
    }

    public Data spreadSheetData(String str){
        return new SimpleData(this.spreadSheetsDataMap.get(str));
    }
}
