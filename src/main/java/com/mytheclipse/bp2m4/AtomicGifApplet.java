/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Applet.java to edit this template
 */
package com.mytheclipse.bp2m4;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author PREDATOR
 */
public class AtomicGifApplet extends Applet {

    private Image atomicGif;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        atomicGif = getImage(getDocumentBase(), "atomic.gif");
    }

    public void paint(Graphics g) {
        if (atomicGif != null) {
            g.drawImage(atomicGif, 10, 10, this);
        } else {
            g.drawString("Gambar tidak ditemukan.", 10, 25);
        }
    }

    // TODO overwrite start(), stop() and destroy() methods
}
