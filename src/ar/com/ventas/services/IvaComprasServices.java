/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.IvaComprasBO;
import ar.com.ventas.entities.IvaCompras;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class IvaComprasServices {
    
    public void getIvaComprasById(Long id) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
            new IvaComprasBO().getIvaComprasById(id);
        
    }
    
    public void saveIvaCompras(IvaCompras ic) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new IvaComprasBO().saveIvaCompras(ic);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }
    
    
    public List<IvaCompras> getAllIvaCompras() throws Exception {
        List<IvaCompras> ic = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ic = new IvaComprasBO().getAllIvaCompras();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return ic;
    }
    
    public List<IvaCompras> getIvaComprasByFiltro(int mes, int anio) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<IvaCompras> ic = null;
        try{
            ic = new IvaComprasBO().getIvaComprasByFiltro(mes, anio);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return ic;
    }
    
}
