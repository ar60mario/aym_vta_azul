/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.util;

import ar.com.ventas.entities.Usuario;
import ar.com.ventas.services.UsuarioService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author argia
 */
public class UtilFrame {
    public static String getUsuario() {
        String str1 = Globals.USR.get();
        String str3 = str1.substring(3, 9);
        String str5 = str3;
        Integer codigo = Integer.valueOf(str3);
        Usuario u = null;
        try {
            u = new UsuarioService().getUsuarioByCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(UtilFrame.class.getName()).log(Level.SEVERE, null, ex);
            return str5;
        }
        str5 = str5 + " - " + u.getNombre();
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
}
