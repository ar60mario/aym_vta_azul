/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 *
 * @author Mario
 */
public class Util {

    class MyPrintable implements Printable {

        public int print(Graphics g, PageFormat pf, int pageIndex) {
            if (pageIndex != 0) {
                return NO_SUCH_PAGE;
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(new Font("Monospaced", Font.PLAIN, 8));
            g2.setPaint(Color.black);
            int row = 45;
            //                1234567890123456789012345678901234567890123456789012345678901234567890
            g2.setFont(new Font("Monospaced", Font.PLAIN, 14));
            g2.drawString("Linea1", 30, row);
            g2.setFont(new Font("Monospaced", Font.BOLD, 9));
            row += 12;
            g2.drawString("Linea2", 30, row);
            row += 12;
            g2.drawString("Linea3", 50, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 8));
            row += 12;
            g2.drawString("Av.San Martín 3284", 50, row);
            row += 12;
            g2.drawString("1678 - Caseros Prov. Buenos Aires", 50, row);
            row += 12;
            g2.drawString("Tel: 4759-6677 - mail: distaym@yahoo.com.ar", 50, row);
            row += 12;
            g2.drawString("CUIT:20-12412758-1 inic.activ.18/04/2005", 50, row);
            row = 70;
            //         123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            g2.drawString("linea 3", 30, row);
            row += 15;
            g2.drawString("4", 30, row);
            row += 50;
            g2.drawString("5", 30, row);
            g2.drawString("6", 480, row);
            row += 15;
            row += 15;
            for (int x = 0; x < 50; x++) {

            }
            g2.setFont(new Font("Monospaced", Font.BOLD, 11));
            g2.setFont(new Font("Monospaced", Font.BOLD, 9));
            return PAGE_EXISTS;
        }
    }
}
