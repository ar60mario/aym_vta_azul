/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.bo;

import ar.com.ventas.dao.TipoDocDAO;
import ar.com.ventas.entities.TipoDoc;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;



/**
 *
 * @author Administrador
 */
public class TipoDocBO {
    TipoDocDAO dao = new TipoDocDAO();

    public List<TipoDoc> getAllDocs() throws Exception {
       List<TipoDoc> listaTipoDoc = new ArrayList();
       try{
        listaTipoDoc = dao.getAllOrdenado(TipoDoc.class);
       }
       catch(HibernateException ex){
           throw new Exception (ex);
       }
       return listaTipoDoc;
    }

    public TipoDoc guardarTipoDoc(TipoDoc tipoDoc) throws Exception {
        try{
            tipoDoc = (TipoDoc) dao.save(tipoDoc);
        }
        catch(HibernateException ex){
            throw new Exception();
        }
        return tipoDoc;
    }

    public TipoDoc saveTipoDoc(TipoDoc tipoDoc) throws Exception{
        
        try{
            dao.save(tipoDoc);
        }catch (HibernateException ex){
            throw new Exception(ex);
        }
        return tipoDoc;
    }

    public void updateTipoDoc(TipoDoc tipoDoc) throws HibernateException{
       try{
            dao.update(tipoDoc);
       }
       catch(HibernateException Ex){
           throw new HibernateException (Ex);
       }
    }

    public void deleteSelectedTipoDoc(TipoDoc tipoDocABorrar) {
        try{
            dao.delete(tipoDocABorrar);
        }catch(HibernateException ex){
            throw new HibernateException (ex);
        }
    }
    
    public TipoDoc getTipoDocByCodigo(String codigo) throws Exception{
        TipoDoc tipoDoc = null;
        try{
            tipoDoc = dao.getByCodigo(codigo);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return tipoDoc;
    }
    
}
