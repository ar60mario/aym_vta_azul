/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.ReciboBO;
import ar.com.ventas.bo.ReciboBO;
import ar.com.ventas.bo.RenglonFacturaBO;
import ar.com.ventas.entities.Recibo;
import ar.com.ventas.entities.Recibo;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class ReciboService {

    public void saveRecibo(Recibo recibo) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new ReciboBO().saveRecibo(recibo);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }
    
    public Recibo getReciboById(Long id) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Recibo recibo = null;
        try {
            recibo = new ReciboBO().getReciboById(id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return recibo;
    }
    
}
