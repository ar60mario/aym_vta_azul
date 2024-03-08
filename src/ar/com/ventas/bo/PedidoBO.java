/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.PedidoDAO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Pedido;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcela
 */
public class PedidoBO {
    
    private final PedidoDAO dao = new PedidoDAO();
    
    private static final Logger logger = Logger.getLogger("PedidoBO");
    
    
    public Pedido savePedido(Pedido pedido) throws Exception{
        try{
          dao.save(pedido);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return pedido;
    }

    public List<Pedido> getAllPedidos() throws Exception {
        
        List<Pedido> listPedidos = null;
        
        try{
            listPedidos = dao.getAll(Pedido.class);			
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listPedidos;
    }

    public List<Pedido> getAllPedidosByCodigoYFecha() throws Exception {
        List<Pedido> listPedidos = null;
        try{
            listPedidos = dao.getAll(Pedido.class);			
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listPedidos;
    }

    public List<Pedido> getAllPedidosByCliente(Cliente cliente) throws Exception  {
        List<Pedido> listPedidos = null;
        try{
            listPedidos = dao.getAllPedidosByCliente(cliente);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listPedidos;
    }
    
    public void updatePedido(Pedido pedido) throws Exception {
        try{
            pedido = (Pedido) dao.update(pedido);        
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        
    }
    
    public List<Pedido> getAllPedidosDisponiblesByCliente(Cliente cliente) throws Exception  {
        List<Pedido> listPedidos = null;
        try{
            listPedidos = dao.getAllPedidosDisponiblesByCliente(cliente);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listPedidos;
    }
    
    public List<Pedido> getPedidosDisponiblesByClienteAndFechas(Cliente cliente, Date fechaD, Date fechaA) throws Exception  {
        List<Pedido> listPedidos = null;
        try{
            listPedidos = dao.getPedidosDisponiblesByClienteAndFechas(cliente, fechaD, fechaA);
            }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listPedidos;
    }
    
    public List<Pedido> getAllPedidosDisponibles() throws Exception  {
        List<Pedido> listPedidos = null;
        try{
            listPedidos = dao.getAllPedidosDisponibles();
            }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listPedidos;
    }
}
