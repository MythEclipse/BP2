/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/JApplet.java to edit this template
 */
package com.mytheclipse.bp2m4;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author PREDATOR
 */
public class Aritmatika extends Applet implements ActionListener {

    TextField input1, input2;
    Choice operator;
    Button hitung;
    Label hasil;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        setLayout(new FlowLayout());

        add(new Label("Bilangan 1:"));
        input1 = new TextField(10);
        add(input1);

        add(new Label("Operator:"));
        operator = new Choice();
        operator.add("+");
        operator.add("-");
        operator.add("*");
        operator.add("/");
        add(operator);

        add(new Label("Bilangan 2:"));
        input2 = new TextField(10);
        add(input2);

        hitung = new Button("Hitung");
        add(hitung);
        hitung.addActionListener(this);

        hasil = new Label("Hasil = ");
        add(hasil);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double bil1 = Double.parseDouble(input1.getText());
            double bil2 = Double.parseDouble(input2.getText());
            double result = 0;
            String op = operator.getSelectedItem();

            switch (op) {
                case "+":
                    result = bil1 + bil2;
                    break;
                case "-":
                    result = bil1 - bil2;
                    break;
                case "*":
                    result = bil1 * bil2;
                    break;
                case "/":
                    if (bil2 != 0) {
                        result = bil1 / bil2;
                    } else {
                        hasil.setText("Hasil = Tidak bisa bagi 0");
                        return;
                    }
                    break;
            }

            hasil.setText("Hasil = " + result);
        } catch (NumberFormatException ex) {
            hasil.setText("Hasil = Input tidak valid");
        }
    }

    // Kamu juga bisa override start(), stop(), dan destroy() jika diperlukan
}
