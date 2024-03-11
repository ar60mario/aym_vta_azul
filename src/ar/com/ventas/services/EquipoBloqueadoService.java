package ar.com.ventas.services;

import ar.com.ventas.bo.ClienteBO;
import ar.com.ventas.bo.EquipoBloqueadoBO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EquipoBloqueadoService {
    
    private final EquipoBloqueadoBO bo = new EquipoBloqueadoBO();
    
    public Boolean getEquiposLibres() throws Exception{
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Boolean libres = true;
        try {
            libres = bo.getEquiposLibres();
//            cliente = new ClienteBO().getClienteByCodigo(codigo);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return libres;
    }
//    public Cliente getClienteByCodigo(String codigo) throws Exception{
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        Cliente cliente = null;
//        try {
//            cliente = new ClienteBO().getClienteByCodigo(codigo);
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return cliente;
//    }
//    
//    public Cliente getClienteByCodigoTodos(String codigo) throws Exception{
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        Cliente cliente = null;
//        try {
//            cliente = new ClienteBO().getClienteByCodigoTodos(codigo);
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return cliente;
//    }
//    
//    public void deleteCliente(Cliente cliente) throws Exception{
//       Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//       Transaction tx = session.beginTransaction();
//       try{
//          new ClienteBO().deleteCliente(cliente);
//          tx.commit();
//       }
//       catch(Exception ex){
//           tx.rollback();
//           throw new Exception (ex);
//       }
//    }
//
//    public void saveCliente(Cliente cliente) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            new ClienteBO().saveCliente(cliente);
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//    }
//
//    public List<Cliente> getAllClientes() throws Exception {
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            clientes = new ClienteBO().getAllClientes();
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//
//    public void updateCliente(Cliente cliente) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            new ClienteBO().updateCliente(cliente);
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//    }
//    
//    public List<Cliente> getClienteByPagina(int paginaActual) throws Exception{
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try{
//            clientes = new ClienteBO().getClienteByPagina(paginaActual);
//            tx.commit();
//        }catch(Exception ex){
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//    
//    public int getClientesCount() throws Exception{
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        int count = 0;
//        try{
//            count = new ClienteBO().getClientesCount();
//            tx.commit();
//        }catch(Exception ex){
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return count;
//    }
//    
//    public List<Cliente> getClienteOrdenado() throws Exception{
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try{
//            clientes = new ClienteBO().getClientesOrdenado();
//            tx.commit();
//        }catch(Exception ex){
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//    
//    public List<Cliente> getClientesInactivosOrdenado() throws Exception{
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try{
//            clientes = new ClienteBO().getClientesInactivosOrdenado();
//            tx.commit();
//        }catch(Exception ex){
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//    
//    public List<Cliente> getClienteNumerico() throws Exception{
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try{
//            clientes = new ClienteBO().getClientesNumerico();
//            tx.commit();
//        }catch(Exception ex){
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//    
//    public List<Cliente> getClienteNumericoInactivos() throws Exception{
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try{
//            clientes = new ClienteBO().getClientesNumericoInactivos();
//            tx.commit();
//        }catch(Exception ex){
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//    
//    public List<Cliente> getClientesByFiltro(String filtro) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        List<Cliente> clientes = null;
//        try{
//            clientes = new ClienteBO().getClientesByFiltro(filtro);
//            tx.commit();
//        }catch(Exception ex){
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//    
//    public List<Cliente> getClientesByFiltroInactivos(String filtro) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        List<Cliente> clientes = null;
//        try{
//            clientes = new ClienteBO().getClientesByFiltroInactivos(filtro);
//            tx.commit();
//        }catch(Exception ex){
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//    
//    
//    public void saveListaClientes(List<Cliente> listaClientes) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            new ClienteBO().saveListaClientes(listaClientes);
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//    }
//    
//    public Cliente getClienteByCuit(String cuit) throws Exception{
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        Cliente cliente = null;
//        try {
//            cliente = new ClienteBO().getClienteByCuit(cuit);
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return cliente;
//    }
}
