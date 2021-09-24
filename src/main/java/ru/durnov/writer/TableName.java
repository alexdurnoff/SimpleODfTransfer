package ru.durnov.writer;

public class TableName {
    private final String protocolName;

    public TableName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String tableSearchString(){
        switch (protocolName) {
            case "Изоляция":
                return "Наименование линий, электрических машин по проекту, рабочее напряжение.";
            case "Петля":
                return "Проверяемый участок цепи, место установки аппарата защиты";
            case "УЗО":
                return "Типовое обозначение УЗО, место установки по проекту";
            case "Автомат":
                return "Типовое обозначение (маркировка)";
            case "Связь":
                return "Количество проверенных элементов";
        }
        throw new IllegalStateException("Неверное имя протокола");
    }
}
