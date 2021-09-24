package ru.durnov.spreadsheet;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;

public interface Data {
    Object[][] data() throws BootstrapException, Exception;
}
