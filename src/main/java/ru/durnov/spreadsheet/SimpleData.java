package ru.durnov.spreadsheet;


public class SimpleData implements Data {
    private final Object[][] data;

    public SimpleData(Object[][] data) {
        this.data = data;
    }

    @Override
    public Object[][] data() {
        return this.data;
    }
}
