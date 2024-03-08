/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.bo;

import ar.com.ventas.dao.RubroDAO;
import ar.com.ventas.entities.Rubro;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Administrador
 */
public class RubroBO {
    RubroDAO dao = new RubroDAO();

public List<Rubro> getAllRubros() throws Exception {
        List<Rubro> listaRubro = new ArrayList();
        try{
            listaRubro = dao.getAll(Rubro.class);
        }
        catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listaRubro;
    }

    public Rubro guardarRubros(Rubro rubro) throws Exception {
        try{
            rubro = (Rubro) dao.save(rubro);
        }
        catch(HibernateException ex){
            throw new Exception();
        }
        return rubro;
    }

    public Rubro saveRubro(Rubro rubro) throws Exception{
        
        try{
            rubro = (Rubro) dao.save(rubro);
        }catch (HibernateException ex){
            throw new Exception(ex);
        }
        return rubro;
    }

    public void updateRubro(Rubro rubro) throws HibernateException{
        try{
            dao.update(rubro);
        } catch(HibernateException ex){
            throw new HibernateException (ex);
        }
    }

    public void deleteRubro(Rubro rubroABorrar) throws Exception{
        try{ 
            dao.delete(rubroABorrar);
        } catch(HibernateException ex){
            throw new HibernateException (ex);    
        }
    }
    
    public Rubro getRubroByCodigo(Integer codigo) throws Exception {
        Rubro rubro = null;
        try{
            rubro = dao.getByCodigo(codigo);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return rubro;
    }
}
