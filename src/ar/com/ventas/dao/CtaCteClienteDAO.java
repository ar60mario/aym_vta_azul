/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.CtaCteCliente;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Mario
 */
public class CtaCteClienteDAO extends GenericDAO {
    
    public List<CtaCteCliente> getCtaCteClienteByFiltro(Cliente cli, Date fechaDe, Date fechaA) {
        List<CtaCteCliente> ctaCteCliente = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        
        StringBuffer sb = new StringBuffer();
        sb.append("from CtaCteCliente ccc ");
        sb.append("where ccc.cliente = :cli ");
        //sb.append("and ccc.fecha >= :fechaDe ");
        //sb.append("and ccc.fecha <= :fechaA ");
        sb.append("order by ccc.fecha");
        Query query = session.createQuery(sb.toString());
        query.setParameter("cli", cli);
        //query.setParameter("fechaDe", fechaDe);
        //query.setParameter("fechaA", fechaA);

        ctaCteCliente = (List<CtaCteCliente>) query.list();
                
        return ctaCteCliente;
    }
    
}
