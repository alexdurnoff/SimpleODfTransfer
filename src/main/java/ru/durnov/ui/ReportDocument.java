package ru.durnov.ui;

import java.awt.*;
import java.io.File;

public class ReportDocument {

    public File outputFile() throws Exception {
        FileDialog fileDialog = new FileDialog((Dialog) null, "Выбираем файл для экспорта протоколов");
        fileDialog.setVisible(true);
        return new File(fileDialog.getDirectory() + fileDialog.getFile());
    }
}
