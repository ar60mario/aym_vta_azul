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
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marcela
 */
public class IvaVentasDAO extends GenericDAO {

    public List<IvaVentas> getFacturasByFiltro(Cliente cod, Date fechaDe, Date fechaA) {
        List<IvaVentas> fact = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(IvaVentas.class);
        criteria.add(Restrictions.between("fecha", fechaDe, fechaA));
        criteria.add(Restrictions.eq("cliente", cod));
        criteria.addOrder(Order.asc("fecha"));
        criteria.addOrder(Order.asc("letra"));
        criteria.addOrder(Order.asc("numeroFactura"));
        fact = (List<IvaVentas>) criteria.list();
        return fact;
    }

    public IvaVentas getByLetraNumero(String letra, Integer sucursal, Integer numero) {
        IvaVentas factura = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        StringBuffer sb = new StringBuffer();
        sb.append("from IvaVentas iv ");
        sb.append("where iv.letra = '" + letra + "' and iv.numeroSucursal = '" + sucursal + "' and iv.numeroFactura = '" + numero + "' ");
        Query query = session.createQuery(sb.toString());
        factura = (IvaVentas) query.uniqueResult();
        return factura;
    }
    
    public IvaVentas getByLetraNumeroAndTipo(String letra, Integer numero, Integer tipo) {
        IvaVentas factura = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(IvaVentas.class);
        criteria.add(Restrictions.eq("letra", letra));
        criteria.add(Restrictions.eq("numeroFactura", numero));
        criteria.add(Restrictions.eq("codigoTipoDoc", tipo));
        factura = (IvaVentas) criteria.uniqueResult();
        return factura;
    }

    public List<IvaVentas> getByError(String letra, Integer sucursal, Integer numero) {
        List<IvaVentas> factura = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        StringBuffer sb = new StringBuffer();
        sb.append("from IvaVentas iv ");
        sb.append("where iv.letra = '" + letra + "' and iv.numeroSucursal = '" + sucursal + "' and iv.numeroFactura = '" + numero + "' ");
        Query query = session.createQuery(sb.toString());
        factura = (List<IvaVentas>) query.list();
        return factura;
    }

    public List<IvaVentas> getFacturasEntreFechas(Date fd, Date fa) {
        List<IvaVentas> fact = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        fact = (List<IvaVentas>) session.createCriteria(IvaVentas.class)
                .add(Restrictions.between("fecha", fd, fa))
                .addOrder(Order.asc("fecha"))
                .addOrder(Order.asc("letra"))
                .addOrder(Order.asc("numeroFactura"))
                .list();
        return fact;
    }

    public List<IvaVentas> getNcByCliente(Cliente c) {
        List<IvaVentas> facturas = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from IvaVentas iv ");
        sb.append("where iv.cliente = :cod ");
        sb.append("and iv.total < 0.00 ");
        sb.append("order by iv.fecha asc");

        Query query = session.createQuery(sb.toString());
        query.setParameter("cod", c);

        facturas = (List<IvaVentas>) query.list();

        return facturas;
    }
}
