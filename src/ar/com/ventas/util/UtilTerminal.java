package ar.com.ventas.util;

import ar.com.ventas.entities.EquipoActivo;
import ar.com.ventas.entities.EquipoBloqueado;
import ar.com.ventas.services.EquipoActivoService;
import ar.com.ventas.services.EquipoBloqueadoService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MARIO
 */
public class UtilTerminal {

    public static Boolean getPermisoGrabar() {
        Boolean libres;
        Boolean existe;
        EquipoBloqueado equipo;
        EquipoBloqueado eb = null;
        String nombre = Globals.USR_NOMBRE.get();
        Integer orden = Integer.valueOf(Globals.USR_ORDEN.get());
        try {
            libres = new EquipoBloqueadoService().getEquiposLibres();
        } catch (Exception ex) {
            Logger.getLogger(UtilTerminal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR 21 - LEYENDO PERMISOS");
            return false;
        }
        if (libres != null) {
            if (libres) {
                try {
                    existe = new EquipoBloqueadoService().getExisteEquipoByNombreAndOrden(nombre, orden);
                } catch (Exception ex) {
                    Logger.getLogger(UtilTerminal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "ERROR 30 - LEYENDO PERMISOS");
                    return false;
                }
                if (existe) {
                    try {
                        new EquipoBloqueadoService().bloquearEquipoExistente2(nombre, orden);
                    } catch (Exception ex) {
                        Logger.getLogger(UtilTerminal.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "ERROR 38 - BLOQUEANDO EQUIPO");
                        return false;
                    }
                } else {
                    try {
                        new EquipoBloqueadoService().bloquearEquipoNuevo2(nombre, orden);
                    } catch (Exception ex) {
                        Logger.getLogger(UtilTerminal.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "ERROR 46 - BLOQUEANDO EQUIPO");
                        return false;
                    }
                }
                try {
                    equipo = new EquipoBloqueadoService().getEquipoBloqueadoByNombreAndOrden(nombre, orden);
                } catch (Exception ex) {
                    Logger.getLogger(UtilTerminal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "ERROR 56 - LEYENDO BLOQUEO EQUIPO");
                    return false;
                }
                String nombre1 = equipo.getNombre();
                Integer orden1 = equipo.getOrden();
                if (nombre.equals(nombre1)) {
                    if (orden.equals(orden1)) {
                        return true;
                    }
                }
            }
        } else {
            try {
                new EquipoBloqueadoService().bloquearEquipoNuevo2(nombre, orden);
                return true;
            } catch (Exception ex) {
                Logger.getLogger(UtilTerminal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "ERROR 46 - BLOQUEANDO EQUIPO");
                return false;
            }
        }
        return false;
    }

    public static Boolean desbloquearEquipo() {
        EquipoActivo equipo;
        String nombre = Globals.USR_NOMBRE.get();
        Integer orden = Integer.valueOf(Globals.USR_ORDEN.get());
        try {
            equipo = new EquipoActivoService().getEquipoActivoByNombreAndOrden(nombre, orden, "A");
        } catch (Exception ex) {
            Logger.getLogger(UtilTerminal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR 56 - LEYENDO BLOQUEO EQUIPO");
            return false;
        }
        System.out.println(nombre);
        System.out.println(orden);
        System.out.println(equipo);
        System.out.println(equipo.getNombre());
        equipo.setActivo(false);
        try {
            new EquipoActivoService().updateEquipoActivo(equipo);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UtilTerminal.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
