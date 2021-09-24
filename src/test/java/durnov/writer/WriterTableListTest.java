package durnov.writer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.table.Table;
import ru.durnov.writer.WriterTableList;

import java.io.File;

class WriterTableListTest {
    private final File file = new File("TestFiles/Отчет ИП Палагин.odt");
    private static TextDocument textDocument;

    @BeforeEach
    void setUpDocument() throws Exception {
        this.textDocument = TextDocument.loadDocument(file);
    }

    @AfterEach
    void closeDocument(){
        this.textDocument.close();
    }

    @Test
    void testIsolationTableMustBeFound() {
        WriterTableList writerTableList = new WriterTableList(textDocument);
        Table table = writerTableList.getTableByName("Изоляция");
        String tableName = table.getTableName();
        Assertions.assertEquals(tableName, "Таблица8");
    }

    @Test
    void testLoopTableMustBeFound() {
        WriterTableList writerTableList = new WriterTableList(textDocument);
        Table table = writerTableList.getTableByName("Петля");
        String tableName = table.getTableName();
        Assertions.assertEquals(tableName, "Таблица6");
    }

    @Test
    void testUZOTableMustBeFound() {
        WriterTableList writerTableList = new WriterTableList(textDocument);
        Table table = writerTableList.getTableByName("УЗО");
        String tableName = table.getTableName();
        Assertions.assertEquals(tableName, "Таблица10");
    }

    @Test
    void testBreakerTableMustBeFound() {
        WriterTableList writerTableList = new WriterTableList(textDocument);
        Table table = writerTableList.getTableByName("Автомат");
        String tableName = table.getTableName();
        Assertions.assertEquals(tableName, "Таблица12");
    }

    @Test
    void testBindTableMustBeFound() {
        WriterTableList writerTableList = new WriterTableList(textDocument);
        Table table = writerTableList.getTableByName("Связь");
        String tableName = table.getTableName();
        Assertions.assertEquals(tableName, "Таблица2");
    }

}