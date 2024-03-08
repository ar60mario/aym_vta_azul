/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.FcReserved;
import ar.com.ventas.entities.RenglonFcReserved;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Marcela
 */
public class RenglonFcReservedDAO extends GenericDAO{
    
    public List<RenglonFcReserved> getRenglonDeFcReserved(FcReserved fac) {
        List<RenglonFcReserved> renglon = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        StringBuffer sb = new StringBuffer();
        sb.append("from RenglonFcReserved fc ");
        sb.append("where fc.facturaReservada = :fcr ");
        sb.append("order by fc.itemNro asc");
        Query query = session.createQuery(sb.toString());
        query.setParameter("fcr", fac);
        renglon = (List<RenglonFcReserved>) query.list();
        return renglon;
    }
    
}
