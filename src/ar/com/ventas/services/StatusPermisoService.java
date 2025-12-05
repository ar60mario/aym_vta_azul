package ar.com.ventas.services;

import ar.com.ventas.bo.RubroBO;
import ar.com.ventas.bo.StatusPermisoBo;
import ar.com.ventas.entities.EquipoActivo;
import ar.com.ventas.entities.Rubro;
import ar.com.ventas.entities.StatusPermiso;
import ar.com.ventas.util.HibernateUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StatusPermisoService {

    private StatusPermisoBo bo = new StatusPermisoBo();

    public StatusPermiso getStatusByEquipo(EquipoActivo ea) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        StatusPermiso sp;
        try {
            sp = bo.getStatusByEquipo(ea);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return sp;
    }
    
    public StatusPermiso getStatus() throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        StatusPermiso sp;
        try {
            sp = bo.getStatus();
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return sp;
    }

    public StatusPermiso saveStatusPermiso(StatusPermiso sp) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            sp = bo.saveStatusPermiso(sp);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return sp;
    }
    
//    public List<Rubro> getAllRubros() throws Exception {
//        List<Rubro> listaRubro = new ArrayList();
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try{
//            
//            listaRubro = bo.getAllRubros();
//            tx.commit();
//        }
//        catch(Exception ex){
//           tx.rollback();
//            throw new Exception(ex);   
//        }
//        return listaRubro;
//    }
//
//    public void updateRubro(Rubro rubro) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try{
//            new RubroBO().updateRubro(rubro);
//            tx.commit();
//        }
//        catch(HibernateException ex){
//            tx.rollback();
//            throw new HibernateException (ex);
//        }
//    }
//
//    public void deleteRubro(Rubro rubroABorrar) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try{
//            new RubroBO().deleteRubro(rubroABorrar);
//            tx.commit();
//        }
//        catch (Exception ex){
//            tx.rollback();
//            throw new Exception (ex);
//        }
//    }
//    
//    public Rubro getRubroByCodigo(Integer codigo) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        Rubro rubro = null;
//        try {
//            rubro = new RubroBO().getRubroByCodigo(codigo);
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return rubro;
//    }
    }
