/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.CuentaCorrienteBO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.CuentaCorriente;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Marcela
 */
public class CuentaCorrienteService {
    
    public List<CuentaCorriente> getAllByCliente(Cliente cliente) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<CuentaCorriente> ctaCte = null;
        try{
            ctaCte = new CuentaCorrienteBO().getAllByCliente(cliente);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return ctaCte;
    }
    
}
