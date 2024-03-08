/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.CtaCteClienteBO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.CtaCteCliente;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class CtaCteClienteService {
    
    public void saveCtaCteCliente(CtaCteCliente ctaCteCliente) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new CtaCteClienteBO().saveCtaCteCliente(ctaCteCliente);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }
    
    public List<CtaCteCliente> getAllCtaCteClienteByCodigoYFecha(Cliente cli,Date fechaDe,Date fechaA) throws Exception {
        List<CtaCteCliente> ctaCteCliente = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ctaCteCliente = new CtaCteClienteBO().getAllCtaCteClienteByCodigoYFecha(cli, fechaDe, fechaA);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return ctaCteCliente;
    }
    
}
