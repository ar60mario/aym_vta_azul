package ar.com.ventas.util;

import ar.com.ventas.entities.Usuario;
import ar.com.ventas.services.UsuarioService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UtilPermisos {

    private static Integer nivel = 1;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean habilitado(Usuario usuario) {
        FileReader fr = null;
        try {
            fr = new FileReader("c:/ventas/permisos.txt");
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        String acceso = "";
        try {
            acceso = br.readLine();
        } catch (IOException ex) {
//            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
//            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (acceso.equals("1")) {
            return true;
        }
        habilitar(usuario);
        if (usuario != null) {
            return true;
        } else {
            return false;
        }
    }

    private static void habilitar(Usuario usuario) {
        usuario = null;
        JTextField field = new JTextField("");
        String[] options = {"Ingresar"};
        int result = JOptionPane.showOptionDialog(
                null,
                field,
                "Autorización de USUARIO",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                0);
        if (field.getText().isEmpty()) {
            usuario = null;
            return;
        }
        switch (result) {
            case 0:
                int cod = Integer.valueOf(field.getText());
                try {
                    usuario = new UsuarioService().getUsuarioByCodigo(cod);
                } catch (Exception ex) {
//                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (usuario != null) {
                    if (usuario.getActivo()) {
                        JPasswordField field2 = new JPasswordField("");
                        String[] opts = {"Ingresar"};
                        int resulta = JOptionPane.showOptionDialog(
                                null,
                                field2,
                                "CONTRASEÑA: " + usuario.getNombre() + ", Autorización",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opts,
                                0);
                        switch (resulta) {
                            case 0:
                                int contra = Integer.valueOf(new String(field2.getPassword()));
                                if (contra == usuario.getContrasena()) {
                                    if (usuario.getNivel() > nivel) {
                                        JOptionPane.showMessageDialog(null, "Usuario no Habilitado");
                                        usuario = null;
                                    } else {
                                        String f1 = sdf.format(usuario.getFecha());
                                        String f2 = sdf.format(new Date());
                                        if (usuario.getNivel() == 2) {
                                            if (!f1.equals(f2)) {
                                                JOptionPane.showMessageDialog(null, "Permiso de Usuario Vencido");
                                                usuario = null;
                                            }
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                                    usuario = null;
                                }
                                break;
                            case 1:
                                usuario = null;
                                break;
                            case -1:
                                usuario = null;
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "USUARIO Inactivo");
                        usuario = null;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el Usuario");
                    usuario = null;
                }
                break;
            case 1:
                usuario = null;
                break;
            case -1:
                usuario = null;
                break;
        }
    }

    public static Boolean habilitar2() {
        Usuario usuario = null;
        JTextField field = new JTextField("");
        String[] options = {"Ingresar"};
        int result = JOptionPane.showOptionDialog(
                null,
                field,
                "Autorización de USUARIO",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                0);
        if (field.getText().isEmpty()) {
            usuario = null;
            return false;
        }
        if (result == 0) {
            int cod = Integer.valueOf(field.getText());
            try {
                usuario = new UsuarioService().getUsuarioByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(UtilPermisos.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            if (usuario != null) {
                if (usuario.getActivo()) {
                    JPasswordField field2 = new JPasswordField("");
                    String[] opts = {"Ingresar"};
                    int resulta = JOptionPane.showOptionDialog(
                            null,
                            field2,
                            "CONTRASEÑA: " + usuario.getNombre() + ", Autorización",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opts,
                            0);
                    if (resulta == 0) {
                        int contra = Integer.valueOf(new String(field2.getPassword()));
                        if (contra == usuario.getContrasena()) {
                            if (usuario.getNivel() > nivel) {
                                JOptionPane.showMessageDialog(null, "Usuario no Habilitado");
                                usuario = null;
                                return false;
                            } else {
                                String f1 = sdf.format(usuario.getFecha());
                                String f2 = sdf.format(new Date());
                                if (usuario.getNivel() == 2) {
                                    if (!f1.equals(f2)) {
                                        JOptionPane.showMessageDialog(null, "Permiso de Usuario Vencido");
                                        usuario = null;
                                        return false;
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                            usuario = null;
                            return false;
                        }
                    } else {
                        usuario = null;
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "USUARIO Inactivo");
                    usuario = null;
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe el Usuario");
                usuario = null;
                return false;
            }
        }
        switch (result) {
            case 0:

                break;
            case 1:
                usuario = null;
                break;
            case -1:
                usuario = null;
                break;
        }
        return true;
    }
}
