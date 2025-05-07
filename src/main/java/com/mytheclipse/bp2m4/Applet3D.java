/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mytheclipse.bp2m4;

/**
 *
 * @author PREDATOR
 */
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class Point3D {
    public int x, y, z;

    public Point3D(int X, int Y, int Z) {
        x = X;
        y = Y;
        z = Z;
    }
}

class Edge3D {
    public int a, b;

    public Edge3D(int A, int B) {
        a = A;
        b = B;
    }
}

public class Applet3D extends Applet implements MouseListener, MouseMotionListener {
    int width, height;
    int mx, my;

    Image backbuffer;
    Graphics backg;

    int azimuth = 35, elevation = 30;

    Point3D[] vertices;
    Edge3D[] edges;

    public void init() {
        width = getSize().width;
        height = getSize().height;

        vertices = new Point3D[8];
        vertices[0] = new Point3D(-1, -1, -1);
        vertices[1] = new Point3D(1, -1, -1);
        vertices[2] = new Point3D(1, 1, -1);
        vertices[3] = new Point3D(-1, 1, -1);
        vertices[4] = new Point3D(-1, -1, 1);
        vertices[5] = new Point3D(1, -1, 1);
        vertices[6] = new Point3D(1, 1, 1);
        vertices[7] = new Point3D(-1, 1, 1);

        edges = new Edge3D[12];
        edges[0] = new Edge3D(0, 1);
        edges[1] = new Edge3D(0, 3);
        edges[2] = new Edge3D(0, 4);
        edges[3] = new Edge3D(1, 2);
        edges[4] = new Edge3D(1, 5);
        edges[5] = new Edge3D(2, 3);
        edges[6] = new Edge3D(2, 6);
        edges[7] = new Edge3D(3, 7);
        edges[8] = new Edge3D(4, 5);
        edges[9] = new Edge3D(4, 7);
        edges[10] = new Edge3D(5, 6);
        edges[11] = new Edge3D(6, 7);

        backbuffer = createImage(width, height);
        backg = backbuffer.getGraphics();
        drawWireframe(backg);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    void drawWireframe(Graphics g) {
        double theta = Math.PI * azimuth / 180.0;
        double phi = Math.PI * elevation / 180.0;

        float cosT = (float) Math.cos(theta), sinT = (float) Math.sin(theta);
        float cosP = (float) Math.cos(phi), sinP = (float) Math.sin(phi);
        float cosTcosP = cosT * cosP, cosTsinP = cosT * sinP;
        float sinTcosP = sinT * cosP, sinTsinP = sinT * sinP;

        Point[] points = new Point[vertices.length];
        int scaleFactor = width / 4;
        float near = 3;
        float nearToObj = 1.5f;

        for (int j = 0; j < vertices.length; ++j) {
            int x0 = vertices[j].x;
            int y0 = vertices[j].y;
            int z0 = vertices[j].z;

            float x1 = cosT * x0 + sinT * z0;
            float y1 = -sinTsinP * x0 + cosP * y0 + cosTsinP * z0;
            float z1 = cosTcosP * z0 - sinTcosP * x0 - sinP * y0;

            x1 = x1 * near / (z1 + near + nearToObj);
            y1 = y1 * near / (z1 + near + nearToObj);

            points[j] = new Point(
                    (int) (width / 2 + scaleFactor * x1 + 0.5),
                    (int) (height / 2 - scaleFactor * y1 + 0.5));
        }

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.white);
        for (int j = 0; j < edges.length; ++j) {
            g.drawLine(
                    points[edges[j].a].x, points[edges[j].a].y,
                    points[edges[j].b].x, points[edges[j].b].y);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        e.consume();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        int new_mx = e.getX();
        int new_my = e.getY();
        azimuth -= new_mx - mx;
        elevation += new_my - my;

        // Clamp elevation to avoid gimbal lock
        if (elevation > 89)
            elevation = 89;
        if (elevation < -89)
            elevation = -89;

        drawWireframe(backg);
        mx = new_mx;
        my = new_my;
        repaint();
        e.consume();
    }

    public void update(Graphics g) {
        g.drawImage(backbuffer, 0, 0, this);
        showStatus("Elev: " + elevation + "deg, Azim: " + azimuth + "deg");
    }

    public void paint(Graphics g) {
        update(g);
    }
}