/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.CtaCteClienteDAO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.CtaCteCliente;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class CtaCteClienteBO {
    
    private final CtaCteClienteDAO dao = new CtaCteClienteDAO();
    
    private static final Logger logger = Logger.getLogger("CtaCteClienteBO");
    
    public CtaCteCliente saveCtaCteCliente(CtaCteCliente ctaCteCliente) throws Exception{
        try{
          dao.save(ctaCteCliente);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return ctaCteCliente;
    }
    
    public List<CtaCteCliente> getAllCtaCteClienteByCodigoYFecha(Cliente cli, Date fechaDe, Date fechaA) throws Exception {
        List<CtaCteCliente> listCtaCteCliente = null;
        try {
            listCtaCteCliente = dao.getCtaCteClienteByFiltro(cli, fechaDe, fechaA);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listCtaCteCliente;
    }
    
}
