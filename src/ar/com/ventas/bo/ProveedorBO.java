/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.bo;

import ar.com.ventas.dao.ProveedorDAO;
import ar.com.ventas.entities.Domicilio;
import ar.com.ventas.entities.Proveedor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Administrador
 */
public class ProveedorBO {
    ProveedorDAO dao = new ProveedorDAO();
    
    public List<Proveedor> getAllProveedores() throws Exception {
    List<Proveedor> listaProveedor = new ArrayList();    
    
    try{
        listaProveedor = dao.getAll(Proveedor.class);
    }
    catch(HibernateException ex){
        throw new Exception(ex);
    }
    
    return listaProveedor;
    }

    public void guardarProveedor(Proveedor proveedor) throws Exception {
        DomicilioBO domicilioBO = new DomicilioBO();
        Domicilio domicilioProveedor = proveedor.getDomicilio();
        domicilioProveedor = domicilioBO.saveDomicilio(domicilioProveedor);
        proveedor.setDomicilio(domicilioProveedor);
        try{ 
           dao.save(proveedor);
        }
        catch(HibernateException ex){
        throw new Exception (ex);
        }
    }

    public void updateProveedor(Proveedor proveedor) throws Exception{
        try{
            dao.update(proveedor);
        }
        catch(HibernateException ex){
        throw new HibernateException (ex);
        }
    }
    
    public void deleteProveedor(Proveedor proveedor) throws Exception {
        
        try{
          dao.delete(proveedor);
        }
        catch(HibernateException ex){
           
            throw new Exception (ex);
        }
    }
    
    
    public List<Proveedor> getProveedoresByFiltro(String filtro) throws Exception {
        List<Proveedor> proveedores = null;
        try{
            proveedores = dao.getProveedoresByFiltro(filtro);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return proveedores;
    }
    
    public Proveedor getProveedorByCodigo(Integer codigo) throws Exception {
        Proveedor prove = null;
        try{
            prove = dao.getByCodigo(codigo);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return prove;
    }
}
