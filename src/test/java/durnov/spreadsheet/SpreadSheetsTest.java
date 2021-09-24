package durnov.spreadsheet;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;
import org.junit.jupiter.api.Test;
import ru.durnov.spreadsheet.Data;
import ru.durnov.spreadsheet.SpreadSheets;

class SpreadSheetsTest {

    @Test
    void testSpreadSheetData() throws BootstrapException, Exception {
        String[] args = {"Изоляция", "Петля", "УЗО", "Автомат"};
        SpreadSheets spreadSheets = new SpreadSheets(args);
        Data data = spreadSheets.spreadSheetData("Изоляция");
        System.out.println("Изоляция " + data.data().length);
        data = spreadSheets.spreadSheetData("Петля");
        System.out.println("Петля " + data.data().length);
        data = spreadSheets.spreadSheetData("УЗО");
        System.out.println("УЗО " + data.data().length);
        data = spreadSheets.spreadSheetData("Автомат");
        System.out.println("Автомат " + data.data().length);
    }

}