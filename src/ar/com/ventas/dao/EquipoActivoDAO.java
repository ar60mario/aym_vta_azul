/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.EquipoActivo;
import ar.com.ventas.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

/**
 *
 * @author Mar y Mar Informatica
 */
public class EquipoActivoDAO extends GenericDAO {

    public List<EquipoActivo> getEquiposActivos(String nombre, String tipo) {
        List<EquipoActivo> equipos;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(EquipoActivo.class);
        criteria.add(Restrictions.eq("activo", true));
        criteria.add(Restrictions.eq("tipo", tipo));
        equipos = (List<EquipoActivo>) criteria.list();
        return equipos;
    }
    
    public EquipoActivo getEquipoActivoByNombreAndOrden(String nombre, Integer orden, String sistema) {
        EquipoActivo equipo;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(EquipoActivo.class);
        criteria.add(Restrictions.eq("nombre", nombre));
        criteria.add(Restrictions.eq("orden", orden));
        criteria.add(Restrictions.eq("tipo", sistema));
        equipo = (EquipoActivo) criteria.uniqueResult();
        return equipo;
    }
//    public Cliente getByCodigo(String codigo) {
//        Cliente cliente = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.add(Restrictions.eq("codigo", codigo));
//        criteria.add(Restrictions.eq("activo", true));
//        cliente = (Cliente) criteria.uniqueResult();
//        return cliente;
//    }
//    
//    public Cliente getByCodigoTodos(String codigo) {
//        Cliente cliente = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.add(Restrictions.eq("codigo", codigo));
//        //criteria.add(Restrictions.eq("activo", true));
//        cliente = (Cliente) criteria.uniqueResult();
//        return cliente;
//    }
//
//    public List<Cliente> getAllClientesOrdenado() {
//        List<Cliente> cli = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.add(Restrictions.eq("activo", true));
//        criteria.addOrder(Order.asc("razonSocial"));
//        cli = (List<Cliente>) criteria.list();
//        return cli;
//    }
//
//    public List<Cliente> getAllClientesInactivosOrdenado() {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.add(Restrictions.eq("activo", false));
//        criteria.addOrder(Order.asc("razonSocial"));
//        return (List<Cliente>) criteria.list();
//    }
//
//    public List<Cliente> getAllClientesNumerico() {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.addOrder(Order.asc("codigo"));
//        criteria.add(Restrictions.eq("activo", true));
//        return (List<Cliente>) criteria.list();
//    }
//
//    public List<Cliente> getAllClientesNumericoInactivos() {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.addOrder(Order.asc("codigo"));
//        criteria.add(Restrictions.eq("activo", false));
//        return (List<Cliente>) criteria.list();
//    }
//
//    public List<Cliente> getClientesByFiltro(String filtro) {
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//
//        StringBuffer sb = new StringBuffer();
//        sb.append("from Cliente clie ");
//        sb.append("where clie.razonSocial like :filtro ");
//        sb.append("and clie.activo = true ");
//        sb.append("order by clie.razonSocial asc");
//
//        Query query = session.createQuery(sb.toString());
//        query.setParameter("filtro", "%" + filtro + "%");
//
//        clientes = (List<Cliente>) query.list();
//
//        return clientes;
//    }
//
//    public List<Cliente> getClientesByFiltroInactivos(String filtro) {
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        StringBuffer sb = new StringBuffer();
//        sb.append("from Cliente clie ");
//        sb.append("where clie.razonSocial like :filtro ");
//        sb.append("and clie.activo = false ");
//        sb.append("order by clie.razonSocial asc");
//        Query query = session.createQuery(sb.toString());
//        query.setParameter("filtro", "%" + filtro + "%");
//        clientes = (List<Cliente>) query.list();
//        return clientes;
//    }
//
//    public Cliente getByCuit(String cuit) {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.add(Restrictions.eq("cuit", cuit));
//        List<Cliente> clientes = (List<Cliente>) criteria.list();
//        Cliente cliente = clientes.get(0);
//        return cliente;
//    }
}
