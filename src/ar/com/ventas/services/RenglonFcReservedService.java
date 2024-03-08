/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.RenglonFcReservedBO;
import ar.com.ventas.entities.FcReserved;
import ar.com.ventas.entities.RenglonFcReserved;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Marcela
 */
public class RenglonFcReservedService {

    public void saveRenglonFcReserved(RenglonFcReserved renglon) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new RenglonFcReservedBO().saveRenglonFcReserved(renglon);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }
    
    public List<RenglonFcReserved> getRenglonesDeReserved(FcReserved fcr) throws Exception {
        List<RenglonFcReserved> fc = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            fc = new RenglonFcReservedBO().getRenglonDeReserved(fcr);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return fc;
    }

    public void deleteRenglonFcReserved(RenglonFcReserved renglon) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new RenglonFcReservedBO().deleteRenglonFcReserved(renglon);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

}
