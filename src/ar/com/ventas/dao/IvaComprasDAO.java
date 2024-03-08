/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.IvaCompras;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Mario
 */
public class IvaComprasDAO extends GenericDAO{

    
    public List<IvaCompras> getIvaComprasByFiltro(int mes, int anio) {
        List<IvaCompras> ivaCompras = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        
        StringBuffer sb = new StringBuffer();
        sb.append("from IvaCompras ic ");
        sb.append("where month(ic.fechaPeriodo) = :mes ");
        sb.append("and year(ic.fechaPeriodo) = :anio ");
        sb.append("order by ic.fechaPeriodo asc");
        
        Query query = session.createQuery(sb.toString());
        query.setParameter("mes",  mes );
        query.setParameter("anio", anio);
        
        ivaCompras = (List<IvaCompras>) query.list();
                
        return ivaCompras;
    }
    
}
