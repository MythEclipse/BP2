/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/JApplet.java to edit this template
 */
package com.mytheclipse.bp2m4;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author PREDATOR
 */
public class Applet1 extends Applet {
    public void paint(Graphics g) {
        Font f = new Font("sansSerif", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.BLUE);
        int xPusat = this.getSize().width / 2;
        int yPusat = this.getSize().height / 2;
        String s = "Selamat Belajar Java Applet";
        FontMetrics fm = this.getFontMetrics(f);
        int posisiX = xPusat - (fm.stringWidth(s) / 2);
        g.drawString("Selamat Belajar Java Applet", posisiX, yPusat);
    }
}
