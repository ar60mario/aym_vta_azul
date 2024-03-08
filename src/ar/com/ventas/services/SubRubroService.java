/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.services;

import ar.com.ventas.bo.SubRubroBO;
import ar.com.ventas.entities.SubRubro;
import ar.com.ventas.util.HibernateUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrador
 */
public class SubRubroService {

    public List<SubRubro> getAllSubRubros() throws Exception {
      List<SubRubro> listaSubRubro = new ArrayList();
      Session session = HibernateUtils.getSessionFactory().getCurrentSession();
      Transaction tx = session.beginTransaction();
      try{
          SubRubroBO bo = new SubRubroBO();
          listaSubRubro = bo.getAllRubros();
          tx.commit();
      }
      catch(Exception ex){
          tx.rollback();
         throw new Exception(ex);
      }
      
      
      return listaSubRubro;   
    }
    public void saveSubRubro(SubRubro subRubro) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new SubRubroBO().saveSubRubro(subRubro);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public void updateSubRubro(SubRubro subRubro) throws Exception {
       Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            new SubRubroBO().updateSubrubro(subRubro);
            tx.commit();
        }
        catch(Exception ex){
            tx.rollback();
            throw new Exception (ex);
        }
    }

    public void deleteSubRubro(SubRubro subRubroABorrar) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            new SubRubroBO().deleteSelectedSubRubro(subRubroABorrar);
            tx.commit();
        }
        catch (Exception ex){
            tx.rollback();
            throw new Exception (ex); 
        }
    }
    
    public SubRubro getSubRubroByCodigo(Integer codigo) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        SubRubro subRubro = null;
        try {
            subRubro = new SubRubroBO().getSubRubroByCodigo(codigo);
            tx.commit();
        } catch (Exception ex) {
            subRubro = null;
            throw new Exception(ex);
        }
        return subRubro;
    }
}
