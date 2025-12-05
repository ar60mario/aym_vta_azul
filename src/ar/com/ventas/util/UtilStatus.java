package ar.com.ventas.util;

import ar.com.ventas.entities.EquipoActivo;
import ar.com.ventas.entities.StatusPermiso;
import ar.com.ventas.services.StatusPermisoService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UtilStatus {

    public static Boolean bloquearEquipo(EquipoActivo ea) {
        StatusPermiso sp;
        try {
            sp = new StatusPermisoService().getStatus();
            Boolean disponible = sp.getStatus();
            if (!disponible) {
                return false;
            }
            String compu = ea.getNombre();
            Integer orden = ea.getOrden();
            sp.setStatus(true);
            sp.setEquipoActivo(ea);
            sp.setCompu(compu);
            sp.setOrden(orden);
//            StatusPermiso estado;
            new StatusPermisoService().saveStatusPermiso(sp);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UtilStatus.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR Nro. 27 - BLOQUEAR EQUIPO");
            return false;
        }
    }

    public static Boolean desBloquearEquipo() {
        return true;
    }
}
