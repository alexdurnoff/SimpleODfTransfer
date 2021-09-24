package durnov.writer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.durnov.writer.TableName;

class TableNameTest {

    @Test
    void testIsolationString(){
        String string = new TableName("Изоляция").tableSearchString();
        Assertions.assertEquals(string, "Наименование линий, электрических машин по проекту, рабочее напряжение.");
    }
    @Test
    void testLoopString(){
        String string = new TableName("Петля").tableSearchString();
        Assertions.assertEquals(string, "Проверяемый участок цепи, место установки аппарата защиты");
    }
    @Test
    void testUzoString(){
        String string = new TableName("УЗО").tableSearchString();
        Assertions.assertEquals(string, "Типовое обозначение УЗО, место установки по проекту");
    }
    @Test
    void testBreakerString(){
        String string = new TableName("Автомат").tableSearchString();
        Assertions.assertEquals(string, "Типовое обозначение (маркировка)");
    }

    @Test
    void testBindString(){
        String string = new TableName("Связь").tableSearchString();
        Assertions.assertEquals(string, "Количество проверенных элементов");
    }

}