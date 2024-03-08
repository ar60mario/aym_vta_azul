/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.bo;

import ar.com.ventas.dao.SubRubroDAO;
import ar.com.ventas.entities.Producto;
import ar.com.ventas.entities.SubRubro;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Administrador
 */
public class SubRubroBO {
    SubRubroDAO dao = new SubRubroDAO();

    public List<SubRubro> getAllRubros() throws Exception {
       List<SubRubro> listaSubRubro = new ArrayList();
       try{
        listaSubRubro = dao.getAllOrdenado(SubRubro.class);
       }
       catch(HibernateException ex){
           throw new Exception (ex);
       }
       return listaSubRubro;
    }

    public SubRubro guardarSubRubros(SubRubro subRubro) throws Exception {
        try{
            subRubro = (SubRubro) dao.save(subRubro);
        }
        catch(HibernateException ex){
            throw new Exception();
        }
        return subRubro;
    }

    public SubRubro saveSubRubro(SubRubro subRubro) throws Exception{
        
        try{
            dao.save(subRubro);
        }catch (HibernateException ex){
            throw new Exception(ex);
        }
        return subRubro;
    }

    public void updateSubrubro(SubRubro subRubro) throws HibernateException{
       try{
            dao.update(subRubro);
       }
       catch(HibernateException Ex){
           throw new HibernateException (Ex);
       }
    }

    public void deleteSelectedSubRubro(SubRubro subRubroABorrar) {
        try{
            dao.delete(subRubroABorrar);
        }catch(HibernateException ex){
            throw new HibernateException (ex);
        }
    }
    
    public SubRubro getSubRubroByCodigo(Integer codigo) throws Exception{
        SubRubro subRubro = null;
        try{
            subRubro = dao.getByCodigo(codigo);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return subRubro;
    }
    
    
}
