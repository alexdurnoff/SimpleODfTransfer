package ru.durnov.ui;

import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.simple.TextDocument;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class OdtTransferProtocolDialog extends Frame implements ActionListener, ItemListener {
    private String[] args;
    private final Button cancel;
    private final Checkbox isolation;
    private final Checkbox loop;
    private final Checkbox uzo;
    private final Checkbox breaker;
    private final ProcessingWindow processingWindow;

    public OdtTransferProtocolDialog(){
        setLayout(new FlowLayout());
        processingWindow = new ProcessingWindow();
        processingWindow.setTitle("Идет трансфер протоколов. Ждем");
        Button ok = new Button("Ок");
        cancel = new Button("Отмена");
        isolation = new Checkbox("Изоляция");
        loop = new Checkbox("Петля");
        uzo = new Checkbox("УЗО");
        breaker = new Checkbox("Автомат");
        add(isolation); add(loop); add(uzo); add(breaker); add(ok); add(cancel);
        ok.addActionListener(this); cancel.addActionListener(this);
        isolation.addItemListener(this); loop.addItemListener(this); uzo.addItemListener(this);
        breaker.addItemListener(this);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        this.setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(cancel)) System.exit(0);
        makeStringArray();
        processingWindow.setVisible(true);
        this.setVisible(false);
        processingWindow.setFocusable(true);
        TextDocument textDocument = null;
        File file = null;
        try {
            file = new ReportDocument().outputFile();
        } catch (Exception e) {
            e.printStackTrace();
            new ExceptionWindow("Ошибка при выборе файла для импорта протоколов");
        }
        try {
            assert file != null;
            textDocument = TextDocument.loadDocument(file);
        } catch (Exception e) {
            e.printStackTrace();
            new ExceptionWindow("Ошибка при чтении файла для импорта протоколов");
        }
        try {
            new Protocols(textDocument, args).copyData();
        } catch (Exception e) {
            e.printStackTrace();
            assert textDocument != null;
            textDocument.close();
            System.exit(-1);
        }
        try {
            assert textDocument != null;
            textDocument.save(file);
        } catch (Exception ex) {
            ex.printStackTrace();
            new ExceptionWindow("Ошибка при сохранении файла для импорта протоколов");
        }
        textDocument.close();
        new EndOfWorkWindow("Трансфер протоколов завершен. Жмем Ок или закрываем окно").setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        repaint();
    }


    public static void main(String[] args) {
        OdtTransferProtocolDialog dialogWindow = new OdtTransferProtocolDialog();
        dialogWindow.setTitle("Выбор генерируемых протоколов");
        dialogWindow.setSize(400, 200);
        dialogWindow.setBackground(Color.LIGHT_GRAY);
        dialogWindow.setForeground(Color.BLACK);
        dialogWindow.setLocation(900, 300);
        dialogWindow.setVisible(true);
    }

    private void makeStringArray(){
        ArrayList<String> arguments = new ArrayList<>();
        if (isolation.getState()) arguments.add("Изоляция");
        if (loop.getState()) arguments.add("Петля");
        if (uzo.getState()) arguments.add("УЗО");
        if (breaker.getState()) arguments.add("Автомат");
        args = new String[arguments.size()];
        arguments.toArray(args);
    }

}
