/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.CuentaCorriente;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Marcela
 */
public class CuentaCorrienteDAO  extends GenericDAO{
    
    
    public List<CuentaCorriente> getAllByCliente(Cliente cliente) {
        List<CuentaCorriente> ctaCte = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        
        StringBuffer sb = new StringBuffer();
        sb.append("from CuentaCorriente cuentaCte ");
        sb.append("where cuentaCte.cliente like :cliente ");
        sb.append("order by cuentaCte.fecha asc");
        
        Query query = session.createQuery(sb.toString());
        query.setParameter("filtro", "%"+cliente+"%");
        
        ctaCte = (List<CuentaCorriente>) query.list();
                
        return ctaCte;
    }
    
    
}
