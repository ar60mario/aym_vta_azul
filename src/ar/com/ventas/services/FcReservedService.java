/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.FcReservedBO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.FcReserved;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Marcela
 */
public class FcReservedService {

    public FcReserved saveFcReserved(FcReserved fc) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        FcReserved fcre = null;
        Transaction tx = session.beginTransaction();
        try {
            fcre = new FcReservedBO().saveFcReserved(fc);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return fcre;
    }

    public List<FcReserved> getAllFcReserved() throws Exception {

        List<FcReserved> fc = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            fc = new FcReservedBO().getAllReserved();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return fc;

    }

    public List<FcReserved> getAllFcReservedByCliente(Cliente cli) throws Exception {
        List<FcReserved> fc = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            fc = new FcReservedBO().getAllReservedByCliente(cli);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return fc;
    }

    public FcReserved getFcReservedById(Long id) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        FcReserved fc = null;
        try {
            fc = new FcReservedBO().getIVById(id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return fc;
    }

    public void deleteFcReserved(FcReserved fc) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new FcReservedBO().deleteFcReserved(fc);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

}
