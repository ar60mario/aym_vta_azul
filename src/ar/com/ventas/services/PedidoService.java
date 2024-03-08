/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.PedidoBO;
import ar.com.ventas.bo.RenglonPedidoBO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Pedido;
import ar.com.ventas.entities.RenglonPedido;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Marcela
 */
public class PedidoService {

    public void savePedido(Pedido pedido) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new PedidoBO().savePedido(pedido);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public List<Pedido> getAllPedidos() throws Exception {

        List<Pedido> pedidos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            pedidos = new PedidoBO().getAllPedidos();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return pedidos;

    }

    public List<Pedido> getAllPedidosByCodigoYFecha(Long cod, Date fechaDe, Date FechaA) throws Exception {

        List<Pedido> pedido = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            pedido = new PedidoBO().getAllPedidosByCodigoYFecha();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return pedido;
    }

    public void savePedidoCompleto(Pedido pe, List<RenglonPedido> rp) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        PedidoBO peBO = new PedidoBO();
        Pedido pedido = peBO.savePedido(pe);
        Boolean bolean = true;
        for (RenglonPedido renglon : rp) {
            renglon.setPedido(pedido);
            try {
                RenglonPedidoBO bo = new RenglonPedidoBO();
                bo.saveRenglonPedido(renglon);
            } catch (Exception ex) {
                bolean = false;
                tx.rollback();
                throw new Exception(ex);
            }
        }
        if (bolean) {
            tx.commit();
        }
    }

    public List<Pedido> getAllPedidosByCliente(Cliente cliente) throws Exception {
        List<Pedido> pedidos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            pedidos = new PedidoBO().getAllPedidosByCliente(cliente);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return pedidos;
    }
    
    public void updatePedido(Pedido pedido) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new PedidoBO().updatePedido(pedido);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }
    
    public List<Pedido> getAllPedidosDisponiblesByCliente(Cliente cliente) throws Exception {
        List<Pedido> pedidos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            pedidos = new PedidoBO().getAllPedidosDisponiblesByCliente(cliente);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return pedidos;
    }
    
    public List<Pedido> getPedidosDisponiblesByClienteAndFechas(Cliente cliente, Date fechaD, Date fechaA) throws Exception {
        List<Pedido> pedidos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            pedidos = new PedidoBO().getPedidosDisponiblesByClienteAndFechas(cliente, fechaD, fechaA);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return pedidos;
    }
    
    public List<Pedido> getAllPedidosDisponibles() throws Exception {
        List<Pedido> pedidos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            pedidos = new PedidoBO().getAllPedidosDisponibles();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return pedidos;
    }
}
