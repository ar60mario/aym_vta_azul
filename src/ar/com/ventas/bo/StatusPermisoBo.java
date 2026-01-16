package ar.com.ventas.bo;

import ar.com.ventas.dao.StatusPermisoDao;
import ar.com.ventas.entities.EquipoActivo;
import ar.com.ventas.entities.StatusPermiso;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

public class StatusPermisoBo {

    StatusPermisoDao dao = new StatusPermisoDao();

    public StatusPermiso getStatusByEquipo(EquipoActivo ea) throws Exception {
        StatusPermiso sp;
        try {
            sp = dao.getStatusByEquipo(ea);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return sp;
    }
    
    public StatusPermiso getStatus() throws Exception {
        StatusPermiso sp;
        try {
            sp = dao.getStatus();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return sp;
    }

    public StatusPermiso saveStatusPermiso(StatusPermiso sp) throws Exception {
        try {
            sp = (StatusPermiso) dao.save(sp);
        } catch (HibernateException ex) {
            throw new Exception();
        }
        return sp;
    }

//    public Rubro saveRubro(Rubro rubro) throws Exception {
//
//        try {
//            rubro = (Rubro) dao.save(rubro);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return rubro;
//    }
//
//    public void updateRubro(Rubro rubro) throws HibernateException {
//        try {
//            dao.update(rubro);
//        } catch (HibernateException ex) {
//            throw new HibernateException(ex);
//        }
//    }
//
//    public void deleteRubro(Rubro rubroABorrar) throws Exception {
//        try {
//            dao.delete(rubroABorrar);
//        } catch (HibernateException ex) {
//            throw new HibernateException(ex);
//        }
//    }
//
//    public Rubro getRubroByCodigo(Integer codigo) throws Exception {
//        Rubro rubro = null;
//        try {
//            rubro = dao.getByCodigo(codigo);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return rubro;
//    }
}
