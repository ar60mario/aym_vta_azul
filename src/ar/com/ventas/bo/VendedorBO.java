/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.bo;

import ar.com.ventas.dao.VendedorDAO;
import ar.com.ventas.entities.Vendedor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Administrador
 */
public class VendedorBO {
    VendedorDAO dao = new VendedorDAO();

public List<Vendedor> getAllVendedores() throws Exception {
        List<Vendedor> listaVendedor = new ArrayList();
        try{
            listaVendedor = dao.getAll(Vendedor.class);
        }
        catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listaVendedor;
    }

    public Vendedor guardarVendedors(Vendedor vendedor) throws Exception {
        try{
            vendedor = (Vendedor) dao.save(vendedor);
        }
        catch(HibernateException ex){
            throw new Exception();
        }
        return vendedor;
    }

    public Vendedor saveVendedor(Vendedor vendedor) throws Exception{
        
        try{
            vendedor = (Vendedor) dao.save(vendedor);
        }catch (HibernateException ex){
            throw new Exception(ex);
        }
        return vendedor;
    }

    public void updateVendedor(Vendedor vendedor) throws HibernateException{
        try{
            dao.update(vendedor);
        } catch(HibernateException ex){
            throw new HibernateException (ex);
        }
    }

    public void deleteVendedor(Vendedor vendedorABorrar) throws Exception{
        try{ 
            dao.delete(vendedorABorrar);
        } catch(HibernateException ex){
            throw new HibernateException (ex);    
        }
    }
    
    public Vendedor getVendedorByCodigo(Integer codigo) throws Exception {
        Vendedor vendedor = null;
        try{
            vendedor = dao.getByCodigo(codigo);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return vendedor;
    }
}
