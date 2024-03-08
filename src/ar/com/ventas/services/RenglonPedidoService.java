/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.RenglonPedidoBO;
import ar.com.ventas.entities.Pedido;
import ar.com.ventas.entities.RenglonPedido;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class RenglonPedidoService {
    
    public List<RenglonPedido> getAllRenglonPedidoFromPedido(Pedido pedido) throws Exception {
        List<RenglonPedido> renglonPedido = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            renglonPedido = new RenglonPedidoBO().getAllRenglonPedidoFromPedido(pedido);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return renglonPedido;
    }
}
