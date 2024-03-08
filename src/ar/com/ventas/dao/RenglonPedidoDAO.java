/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Pedido;
import ar.com.ventas.entities.RenglonPedido;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mario
 */
public class RenglonPedidoDAO extends GenericDAO {
    
    public List<RenglonPedido> getRenglonPedidoFromPedido(Pedido pedido) {
        List<RenglonPedido> renglonPedido = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(RenglonPedido.class);
        criteria.add(Restrictions.eq("pedido", pedido));
//        
//        StringBuffer sb = new StringBuffer();
//        sb.append("from RenglonPedido rp ");
//        sb.append("where rp.pedido = :pedido ");
//        Query query = session.createQuery(sb.toString());
//        query.setParameter("pedido",  pedido );
        renglonPedido = (List<RenglonPedido>) criteria.list();
        return renglonPedido;
    }

}
