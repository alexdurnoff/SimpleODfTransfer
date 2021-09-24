package ru.durnov.writer;

public class HeaderPanel {
    private final Object[] dataRow;

    public HeaderPanel(Object[] dataRow) {
        this.dataRow = dataRow;
    }

    public boolean isHeader() {
        String value0 = String.valueOf(dataRow[0]);
        if (value0 == null || value0.isEmpty()){
            String value1 = String.valueOf(dataRow[1]);
            return !value1.contains("Полюс");
        }
        return false;
    }
}
