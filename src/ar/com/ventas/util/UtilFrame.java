/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.util;

import ar.com.ventas.main.MainOneFrame;
import ar.com.ventas.services.EquipoActivoService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author argia
 */
public class UtilFrame {
    public static String getUsuario() {
        String str1 = Globals.USR.get();
        Integer largo = str1.length();
        String str0 = str1.substring(0, 3);
        String str3 = str1.substring(4, largo);
        String str5 = str0 + " - " + str3;
        return str5;
    }
    
    public static String getNombreEquipo() {
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(UtilFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        String str1 = "";
        if (localHost != null) {
            if (localHost.getHostName() != null) {
                str1 = localHost.getHostName();
            }
        }
        return str1;
    }
    
    public static String establecerNombre() {
        InetAddress localHost;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainOneFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR INGRESANDO AL SISTEMA,\nINGRESE NUEVAMENTE");
            return "";
        }
        return localHost.getHostName();
    }
    
    public static Integer establecerOrden() {
        Integer i = 0;
        String order_name = establecerNombre();
        try {
            i = new EquipoActivoService().calcularOrden(order_name.trim(), "A");
        } catch (Exception ex) {
            Logger.getLogger(MainOneFrame.class.getName()).log(Level.SEVERE, null, ex);
            i = 0;
        }
        return i;
    }
}
