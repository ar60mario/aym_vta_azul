/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.FacturaDAO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Configuracion;
import ar.com.ventas.entities.CtaCteCliente;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.RenglonFactura;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class FacturaBO {

    private final FacturaDAO dao = new FacturaDAO();
    private static final Logger logger = Logger.getLogger("FacturaBO");

    public void saveFacturaCompleta(Cliente cl, Configuracion co, CtaCteCliente cc, IvaVentas iv, List<RenglonFactura> rf) throws Exception {
        try {
            // actualizo cliente
            new ClienteBO().updateCliente(cl);
            // actualizo la configuracion
            new ConfiguracionBO().updateConfiguracion(co);
            // grabo factura en libro
            iv = new IvaVentasBO().saveIvaVentas(iv);
            // grabo cuenta Corriente
            cc.setFactura(iv);
            new CtaCteClienteBO().saveCtaCteCliente(cc);
            // grabo renglones
            for (RenglonFactura re : rf) {
                re.setIvaVentas(iv);
                new RenglonFacturaBO().saveRenglon(re);
            }
            // grabar vendedor?
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    /*
    private final ClienteDAO dao = new ClienteDAO();
    private static final Logger logger = Logger.getLogger("ClienteBO");
    
    public Cliente saveCliente(Cliente cliente) throws Exception{
        // Primero guardo la dirección del cliente.
        DomicilioBO domicilioBO = new DomicilioBO();
        Domicilio domicilioCliente = cliente.getDomicilio();
	domicilioCliente = domicilioBO.saveDomicilio(domicilioCliente);
        cliente.setDomicilio(domicilioCliente);
        
        try{
          dao.save(cliente);            
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return cliente;
    }
     */
}
