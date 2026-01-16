package ar.com.ventas.dao;

import ar.com.ventas.entities.EquipoActivo;
import ar.com.ventas.entities.StatusPermiso;
import ar.com.ventas.util.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class StatusPermisoDao extends GenericDAO{

    public StatusPermiso getStatusByEquipo(EquipoActivo ea) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(StatusPermiso.class);
        criteria.add(Restrictions.eq("equipoActivo", ea));
        StatusPermiso statusPermiso = (StatusPermiso) criteria.uniqueResult();
        return statusPermiso;
    }
    
    public StatusPermiso getStatus() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(StatusPermiso.class);
        criteria.add(Restrictions.eq("id", 1L));
        StatusPermiso statusPermiso = (StatusPermiso) criteria.uniqueResult();
        return statusPermiso;
    }
    
//    public <T> List getAllOrdenado(Class<T> clase) throws HibernateException {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(clase);
//        criteria.addOrder(Order.asc("codigo"));
//        return criteria.list();
//    }
}
