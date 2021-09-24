package ru.durnov.ui;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;
import org.odftoolkit.simple.TextDocument;
import ru.durnov.spreadsheet.ReportTemplate;
import ru.durnov.writer.ReportProtocol;


public class Protocols {
    private final String[] args;
    private final TextDocument textDocument;


    public Protocols(TextDocument textDocument, String[] args) {
        this.textDocument = textDocument;
        this.args = args;
    }

    public void copyData() throws BootstrapException, Exception {
        for (String str : args) {
            System.out.println(str);
            new ReportProtocol(textDocument, str).copyData(
                    new ReportTemplate(str).data()
            );
        }
    }
}
