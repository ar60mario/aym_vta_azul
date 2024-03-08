/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Marcela
 */
public class ReciboDAO extends GenericDAO{
    
    public List<IvaVentas> getFacturasByFiltro(Cliente cod, Date fechaDe, Date fechaA) {
        List<IvaVentas> facturas = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        
        StringBuffer sb = new StringBuffer();
        sb.append("from IvaVentas iv ");
        sb.append("where iv.cliente = :cod ");
        sb.append("and iv.fecha >= :fechaDe ");
        sb.append("and iv.fecha <= :fechaA");
        //sb.append("order by iv.fecha asc");
        
        Query query = session.createQuery(sb.toString());
        query.setParameter("cod", cod);
        query.setParameter("fechaDe", fechaDe);
        query.setParameter("fechaA", fechaA);
        
        facturas = (List<IvaVentas>) query.list();
                
        return facturas;
    }
    
}
