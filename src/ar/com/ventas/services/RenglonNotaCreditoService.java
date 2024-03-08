/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.RenglonNotaCreditoBO;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.RenglonNotaCredito;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class RenglonNotaCreditoService {
    
    public List<RenglonNotaCredito> getAllRenglonNotaCreditoFromIvaVentas(IvaVentas ivaVentas) throws Exception {
        List<RenglonNotaCredito> renglones = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            renglones = new RenglonNotaCreditoBO().getAllRenglonNotaCreditoFromIvaVentas(ivaVentas);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return renglones;
    }
}
