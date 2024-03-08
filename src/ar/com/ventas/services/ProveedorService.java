/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.services;

import ar.com.ventas.bo.ProveedorBO;
import ar.com.ventas.entities.Proveedor;
import ar.com.ventas.util.HibernateUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrador
 */
public class ProveedorService {

    public List<Proveedor> getAllProveedores() throws Exception {
     List<Proveedor> listaProveedor = new ArrayList();  
     ProveedorBO BO = new ProveedorBO();
      Session session = HibernateUtils.getSessionFactory().getCurrentSession();
      Transaction tx = session.beginTransaction();
     try{
         listaProveedor = BO.getAllProveedores();
         tx.commit();
     }
     catch(Exception ex){
         tx.rollback();
         tx.commit();
         throw new Exception(ex);
     }
     
     
     return listaProveedor;
    }

    
    
    public void saveProveedor(Proveedor proveedor) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        try{
            new ProveedorBO().guardarProveedor(proveedor);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception (ex);
        }
    }

    public void updateProveedor(Proveedor proveedor) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            new ProveedorBO().updateProveedor(proveedor);
            tx.commit();
        }
        catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public void deleteProveedor(Proveedor proveedor) throws Exception{
       Session session = HibernateUtils.getSessionFactory().getCurrentSession();
       Transaction tx = session.beginTransaction();
       
       try{
          
          new ProveedorBO().deleteProveedor(proveedor);
          tx.commit();
       }
       catch(Exception ex){
           tx.rollback();
           throw new Exception (ex);
       }
    }
    
    public List<Proveedor> getProveedoresByFiltro(String filtro) throws Exception {
        
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Proveedor> proveedores = null;
        try{
            proveedores = new ProveedorBO().getProveedoresByFiltro(filtro);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return proveedores;
    }
    
    public Proveedor getProveedorByCodigo(Integer codigo) throws Exception{
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Proveedor prove = null;
        try {
            prove = new ProveedorBO().getProveedorByCodigo(codigo);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return prove;
    }
}
