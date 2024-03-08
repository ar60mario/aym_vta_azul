/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.entities.RenglonPedido;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mario
 */
public class RenglonFacturaDAO extends GenericDAO {

    public List<RenglonFactura> getAllRenglonFacturaFromIvaVentas(IvaVentas factura) {
        List<RenglonFactura> renglonFactura = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(RenglonFactura.class);
        criteria.add(Restrictions.eq("ivaVentas", factura));
        renglonFactura = (List<RenglonFactura>) criteria.list();
        return renglonFactura;
    }

    public Double getTotalGravadoCigarrillosByFactura(IvaVentas fc) {
        Double totalGravadoCigarrillos = 0.00;
        List<RenglonFactura> renglones = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(RenglonFactura.class);
        criteria.add(Restrictions.eq("ivaVentas", fc));
        renglones = (List<RenglonFactura>) criteria.list();
        if (renglones != null && !renglones.isEmpty()) {
            for (RenglonFactura rngl : renglones) {
                if (rngl.getImpuesto() != 0.00) {
                    totalGravadoCigarrillos += rngl.getGravado();
                }
            }
        }
        return totalGravadoCigarrillos;
    }
}
