/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.dao;

import ar.com.ventas.entities.Proveedor;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrador
 */
public class ProveedorDAO extends GenericDAO{
    
    public List<Proveedor> getProveedoresByFiltro(String filtro) {
        List<Proveedor> proveedores = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        
        StringBuffer sb = new StringBuffer();
        sb.append("from Proveedor prove ");
        sb.append("where prove.razonSocial like :filtro ");
        sb.append("order by prove.razonSocial asc");

        Query query = session.createQuery(sb.toString());
        query.setParameter("filtro", "%"+filtro+"%");
        proveedores = (List<Proveedor>) query.list();
        return proveedores;
    }
    
    public Proveedor getByCodigo(Integer codigo) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Proveedor.class);
        criteria.add(Restrictions.eq("codigo", codigo));
        
        Proveedor prove = (Proveedor) criteria.uniqueResult();
        return prove;
    }
}
