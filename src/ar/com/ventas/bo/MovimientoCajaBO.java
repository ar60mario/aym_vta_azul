/*
 * Aqui va toda la lógica de validaciones respecto a los Administradores.
 */

package ar.com.ventas.bo;

import ar.com.ventas.dao.ClienteDAO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Domicilio;
import ar.com.ventas.bo.DomicilioBO;
import ar.com.ventas.dao.MovimientoCajaDAO;
import ar.com.ventas.entities.MovimientoCaja;
import ar.com.ventas.util.Constantes;
import ar.com.ventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mar y Mar Informatica
 */
public class MovimientoCajaBO {
    
    private final MovimientoCajaDAO dao = new MovimientoCajaDAO();
    
    private static final Logger logger = Logger.getLogger("MovimientoCajaBO");
    
//    public List<Cliente> getAllClientes() throws Exception{
//        List<Cliente> listClientes = null;
//        
//        try{
//            listClientes = dao.getAll(Cliente.class);			
//        }catch(HibernateException ex){
//            throw new Exception(ex);
//        }
//        return listClientes;
//    }

    public MovimientoCaja saveMovimientoCaja(MovimientoCaja movim) throws Exception{
        // Primero guardo la dirección del cliente.
        try{
          dao.save(movim);            
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return movim;
    }

    public void updateMovimientoCaja(MovimientoCaja movim) throws Exception {
        try{
            movim = (MovimientoCaja) dao.update(movim);        
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
    }
    
//    public List<Cliente> getClienteByPagina(int paginaActual) throws Exception{
//        
//        List<Cliente> listadoClientes = null;
//        int start = 0;
//        if(paginaActual > 1){
//            start = ((paginaActual - 1) * Constantes.MAX_RESULTS) + 1;            
//        }
//        
//        try{
//            listadoClientes = dao.getAll(Cliente.class, start);
//        }catch(HibernateException ex){
//            throw new Exception(ex);
//        }
//        
//        return listadoClientes;
//        
//    }

//    public int getClientesCount() throws Exception {
//        
//        int cantidad = 0;
//        
//        try{
//            cantidad = dao.getCount(Cliente.class);
//        }catch(HibernateException ex){
//            throw new Exception(ex);
//        }
//        
//        return cantidad;
//        
//    }

//    public void deleteCliente(Cliente cliente) throws Exception {
//        
//        try{
//          dao.delete(cliente);
//          
//        }
//        catch(HibernateException ex){
//           
//            throw new Exception (ex);
//        }
//    }
    
    public MovimientoCaja getMovimientoCajaByFecha(Date fecha) throws Exception {
        MovimientoCaja movim = null;
        try{
            movim = dao.getByFecha(fecha);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return movim;
    }
    
//    public List<Cliente> getClientesOrdenado() throws Exception{
//        
//        List<Cliente> listadoClientes = null;
//        try{
//            listadoClientes = dao.getAllClientesOrdenado();
//        }catch(HibernateException ex){
//            throw new Exception(ex);
//        }
//        return listadoClientes;
//        
//    }
    
//    public List<Cliente> getClientesByFiltro(String filtro) throws Exception {
//        List<Cliente> clientes = null;
//        try{
//            clientes = dao.getClientesByFiltro(filtro);
//        }catch(HibernateException ex){
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
    
//    public void saveListaClientes(List<Cliente> listaClientes) throws Exception {
//        DomicilioBO db = new DomicilioBO();
//        
//        if(listaClientes != null && !listaClientes.isEmpty()){
//            for(Cliente cliente : listaClientes){
//                Domicilio domicilio = cliente.getDomicilio();
//                try{
//                    domicilio = db.saveDomicilio(domicilio);
//                    cliente.setDomicilio(domicilio);
//                    dao.save(cliente);
//                }catch(HibernateException ex){
//                    throw new Exception("Ha ocurrido un problema intentando guardar el Cliente.\nPor favor intente nuevamente mas tarde.");
//                }
//            }
//        }
//    }

}
