/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.DomicilioBO;
import ar.com.ventas.entities.Domicilio;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mcvalls
 */
public class DomicilioService {

    public Domicilio saveDomicilio(Domicilio domicilio) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            domicilio = new DomicilioBO().saveDomicilio(domicilio);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return domicilio;
    }

    public Domicilio updateDomicilio(Domicilio domicilio) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            domicilio = new DomicilioBO().updateDomicilio(domicilio);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }

        return domicilio;
    }

    public List<Domicilio> getAllDomicilio() throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Domicilio> domicilios = null;
        try {
            domicilios = new DomicilioBO().getAllDomicilios();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return domicilios;
    }
}
