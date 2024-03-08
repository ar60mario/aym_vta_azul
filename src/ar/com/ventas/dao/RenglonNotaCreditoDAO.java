/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.RenglonNotaCredito;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mario
 */
public class RenglonNotaCreditoDAO extends GenericDAO {

    public List<RenglonNotaCredito> getAllRenglonNotaCreditoFromIvaVentas(IvaVentas nc) {
        List<RenglonNotaCredito> renglonFactura = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(RenglonNotaCredito.class);
        criteria.add(Restrictions.eq("ivaVentas", nc));
        renglonFactura = (List<RenglonNotaCredito>) criteria.list();
        return renglonFactura;
    }
}
