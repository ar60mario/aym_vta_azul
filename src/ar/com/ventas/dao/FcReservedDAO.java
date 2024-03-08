/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.FcReserved;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Marcela
 */
public class FcReservedDAO extends GenericDAO{
    
    public List<FcReserved> getFcReservedByCliente(Cliente cliente) {
        List<FcReserved> facturas = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        StringBuffer sb = new StringBuffer();
        sb.append("from FcReserver fc ");
        sb.append("where fc.cliente = :cliente ");
        sb.append("order by fc.fecha asc");
        Query query = session.createQuery(sb.toString());
        query.setParameter("cliente", cliente);
        facturas = (List<FcReserved>) query.list();
        return facturas;
    }
    
}
