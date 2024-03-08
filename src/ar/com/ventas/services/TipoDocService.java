/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.services;

import ar.com.ventas.bo.TipoDocBO;
import ar.com.ventas.entities.TipoDoc;
import ar.com.ventas.util.HibernateUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author Administrador
 */
public class TipoDocService {

    public List<TipoDoc> getAllTipoDocs() throws Exception {
      List<TipoDoc> listaTipoDoc = new ArrayList();
      Session session = HibernateUtils.getSessionFactory().getCurrentSession();
      Transaction tx = session.beginTransaction();
      try{
          TipoDocBO bo = new TipoDocBO();
          listaTipoDoc = bo.getAllDocs();
          tx.commit();
      }
      catch(Exception ex){
          tx.rollback();
         throw new Exception(ex);
      }
      
      
      return listaTipoDoc;   
    }
    public void saveTipoDoc(TipoDoc tipoDoc) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new TipoDocBO().saveTipoDoc(tipoDoc);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public void updateTipoDoc(TipoDoc tipoDoc) throws Exception {
       Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            new TipoDocBO().updateTipoDoc(tipoDoc);
            tx.commit();
        }
        catch(Exception ex){
            tx.rollback();
            throw new Exception (ex);
        }
    }

    public void deleteTipoDoc(TipoDoc tipoDocABorrar) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            new TipoDocBO().deleteSelectedTipoDoc(tipoDocABorrar);
            tx.commit();
        }
        catch (Exception ex){
            tx.rollback();
            throw new Exception (ex); 
        }
    }
    
    public TipoDoc getTipoDocByCodigo(String codigo) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        TipoDoc tipoDoc = null;
        try {
            tipoDoc = new TipoDocBO().getTipoDocByCodigo(codigo);
            tx.commit();
        } catch (Exception ex) {
            tipoDoc = null;
            throw new Exception(ex);
        }
        return tipoDoc;
    }
}
