package ru.durnov.ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProcessingWindow extends Frame {

    public ProcessingWindow(){
        //setLayout(new FlowLayout());
        setLayout(new BorderLayout());
        Label status = new Label("Please wait");
        add(status);
        status.setLocation(950,320);
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.BLACK);
        this.setSize(500, 200);
        this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        this.setLocation(900,300);
        this.setTitle("Идет процесс генерации протоколов, ждем");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics graphics){
        graphics.drawString("Набираемся тепения, идет генерация протоколов, окно не закрываем", 980, 300);
    }

}
