/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Pedido;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marcela
 */
public class PedidoDAO extends GenericDAO{

    public List<Pedido> getAllPedidosByCliente(Cliente cliente) {
        List<Pedido> pedidos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        StringBuffer sb = new StringBuffer();
        sb.append("from Pedido pe ");
        sb.append("where pe.cliente = :cliente");
//        sb.append("order by iv.fecha asc");
        Query query = session.createQuery(sb.toString());
        query.setParameter("cliente", cliente);
        pedidos = (List<Pedido>) query.list();
        return pedidos;
    }
    
    public List<Pedido> getAllPedidosDisponiblesByCliente(Cliente cliente) {
        List<Pedido> pedidos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        StringBuffer sb = new StringBuffer();
        sb.append("from Pedido pe ");
        sb.append("where pe.cliente = :cliente");
        sb.append(" and pe.facturado = false");
//        sb.append("order by iv.fecha asc");
        Query query = session.createQuery(sb.toString());
        query.setParameter("cliente", cliente);
        pedidos = (List<Pedido>) query.list();
        return pedidos;
    }
    
    public List<Pedido> getPedidosDisponiblesByClienteAndFechas(Cliente cliente, Date fechaD, Date fechaA) {
        List<Pedido> pedido = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        pedido = (List<Pedido>) 
                session.createCriteria(Pedido.class)
                        .add(Restrictions.eq("cliente", cliente))
                        .add(Restrictions.eq("facturado",false))
                        .add(Restrictions.between("fecha", fechaD, fechaA))
                        .list();
        return pedido;
    }
    
    public List<Pedido> getAllPedidosDisponibles() {
        List<Pedido> pedido = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        pedido = (List<Pedido>) 
                session.createCriteria(Pedido.class)
                        .add(Restrictions.eq("facturado",false))
                        .list();
        return pedido;
    }
}
