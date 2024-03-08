/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.IvaComprasDAO;
import ar.com.ventas.entities.IvaCompras;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class IvaComprasBO {
    
    private final IvaComprasDAO dao = new IvaComprasDAO();
    
    private static final Logger logger = Logger.getLogger("IvaComprasBO");

    public IvaCompras getIvaComprasById(Long id) throws Exception {
        IvaCompras ic = null;
        try {
            ic = (IvaCompras) dao.getById(IvaCompras.class, id);
        } catch(HibernateException ex){
            throw new Exception(ex);
        }           
        
        return ic;
    }
    
    public IvaCompras saveIvaCompras(IvaCompras ic) throws Exception{
        try{
          dao.save(ic);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return ic;
    }
    
    public List<IvaCompras> getAllIvaCompras() throws Exception{
        List<IvaCompras> listIvaCompras = null;
        
        try{
            listIvaCompras = dao.getAll(IvaCompras.class);			
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        
        return listIvaCompras;
    }
    
    public List<IvaCompras> getIvaComprasByFiltro(int mes, int anio) throws Exception {
        List<IvaCompras> ivaCompras = null;
        try{
            ivaCompras = dao.getIvaComprasByFiltro(mes, anio);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return ivaCompras;
    }
    
}
