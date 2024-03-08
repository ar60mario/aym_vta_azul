/*
 * Aqui va toda la lógica de validaciones respecto a los Administradores.
 */

package ar.com.ventas.bo;

import ar.com.ventas.dao.ConfiguracionDAO;
import ar.com.ventas.entities.Configuracion;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mar y Mar Informatica
 */
public class ConfiguracionBO {
    
    private final ConfiguracionDAO dao = new ConfiguracionDAO();
    private static final Logger logger = Logger.getLogger("ConfiguracionBO");
    
//    public Configuracion getFacturas() throws Exception{
//        Configuracion facturas = dao.getFacturas();
//        return facturas;
//    }
    

    public Configuracion getFacturasById(Long id) throws Exception {
        Configuracion configuracion = null;
        try{
            configuracion = (Configuracion) dao.getById(Configuracion.class, id);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return configuracion;
    }
    
    public void updateFacturas(Configuracion factura) throws Exception {
        
        try{
            factura = (Configuracion) dao.update(factura);        
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
    }
    
    
    public Configuracion saveConfiguracion(Configuracion configuracion) throws Exception{
        try{
          dao.save(configuracion);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return configuracion;
    }

    public Configuracion updateConfiguracion(Configuracion config) throws Exception {
        try{
            config = (Configuracion) dao.update(config);        
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return config;
    }
    
    /*
    private final ClienteDAO dao = new ClienteDAO();
    
    private static final Logger logger = Logger.getLogger("ClienteBO");
    
    public List<Cliente> getAllClientes() throws Exception{
        List<Cliente> listClientes = null;
        
        try{
            listClientes = dao.getAll(Cliente.class);			
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        
        return listClientes;
    }

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

    public void updateCliente(Cliente cliente) throws Exception {
        
        // Primero guardo la dirección del administrador.
        DomicilioBO domicilioBO = new DomicilioBO();        
        Domicilio domicilioCliente = cliente.getDomicilio();
        domicilioCliente = domicilioBO.updateDomicilio(domicilioCliente);
        cliente.setDomicilio(domicilioCliente);
              
        try{
            cliente = (Cliente) dao.update(cliente);        
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        
    }
    
    public List<Cliente> getClienteByPagina(int paginaActual) throws Exception{
        
        List<Cliente> listadoClientes = null;
        int start = 0;
        if(paginaActual > 1){
            start = ((paginaActual - 1) * Constantes.MAX_RESULTS) + 1;            
        }
        
        try{
            listadoClientes = dao.getAll(Cliente.class, start);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        
        return listadoClientes;
        
    }

    public int getClientesCount() throws Exception {
        
        int cantidad = 0;
        
        try{
            cantidad = dao.getCount(Cliente.class);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        
        return cantidad;
        
    }

    public void deleteCliente(Cliente cliente) throws Exception {
        
        try{
          dao.delete(cliente);
          
        }
        catch(HibernateException ex){
           
            throw new Exception (ex);
        }
    }
    
    public Cliente getClienteByCodigo(String codigo) throws Exception {
        Cliente cliente = null;
        try{
            cliente = dao.getByCodigo(codigo);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return cliente;
    }
    
    public List<Cliente> getClientesOrdenado() throws Exception{
        
        List<Cliente> listadoClientes = null;
        try{
            listadoClientes = dao.getAllClientesOrdenado();
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listadoClientes;
        
    }
    
    public List<Cliente> getClientesByFiltro(String filtro) throws Exception {
        List<Cliente> clientes = null;
        try{
            clientes = dao.getClientesByFiltro(filtro);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return clientes;
    }
    }
    */
}
