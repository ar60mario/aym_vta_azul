/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.CuentaCorrienteDAO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.CuentaCorriente;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcela
 */
public class CuentaCorrienteBO {
    
    private final CuentaCorrienteDAO dao = new CuentaCorrienteDAO();
    
    private static final Logger logger = Logger.getLogger("CuentaCorrienteBO");
    
    
    public List<CuentaCorriente> getAllByCliente(Cliente cliente) throws Exception {
        List<CuentaCorriente> ctaCte = null;
        try{
            ctaCte = dao.getAllByCliente(cliente);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return ctaCte;
    }
    
    
}
