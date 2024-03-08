/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.dao;

import ar.com.ventas.entities.CustomerTraba;
import ar.com.ventas.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mar y Mar Informatica
 */
public class CustomerTrabaDAO extends GenericDAO{


    public CustomerTraba getByCodigo(String codigo) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(CustomerTraba.class);
        criteria.add(Restrictions.eq("codigo", codigo));
        
        CustomerTraba cliente = (CustomerTraba) criteria.uniqueResult();
        return cliente;
    }
    
}
