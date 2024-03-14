/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.EquipoBloqueado;
import ar.com.ventas.util.HibernateUtils;
//import org.hibernate.classic.Session;
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
public class EquipoBloqueadoDAO extends GenericDAO {

//    public Boolean getEquiposLibres() {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        List<EquipoBloqueado> equipos;
//        Boolean libre = true;
//        Criteria criteria = session.createCriteria(EquipoBloqueado.class);
//        criteria.add(Restrictions.eq("bloqueado", true));
//        equipos = (List<EquipoBloqueado>) criteria.list();
//        for(EquipoBloqueado eb:equipos){
//            if(eb.getBloqueado()){
//                libre = false;
//            }
//        }
//        return libre;
//    }
    
    public EquipoBloqueado getEquipoBloqueadoByNombreAndOrden(String nombre, Integer orden) {
        EquipoBloqueado eb;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(EquipoBloqueado.class);
        criteria.add(Restrictions.eq("nombre", nombre));
        criteria.add(Restrictions.eq("orden", orden));
        eb = (EquipoBloqueado) criteria.uniqueResult();
//        System.out.println(eb);
//        System.out.println(nombre);
//        System.out.println(orden);
        return eb;
    }
    
//    public void bloquearEquipo(EquipoBloqueado equipo, Boolean bloqueo) {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(EquipoBloqueado.class);
//    }
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
