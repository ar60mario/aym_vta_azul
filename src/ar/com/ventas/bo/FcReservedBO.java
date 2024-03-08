/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.FcReservedDAO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.FcReserved;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcela
 */
public class FcReservedBO {

    private final FcReservedDAO dao = new FcReservedDAO();

    private static final Logger logger = Logger.getLogger("FcReservedBO");

    public FcReserved saveFcReserved(FcReserved factura) throws Exception {
        try {
            dao.save(factura);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return factura;
    }

    public List<FcReserved> getAllReserved() throws Exception {
        List<FcReserved> fc = null;
        try {
            fc = dao.getAll(FcReserved.class);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return fc;

    }

    public List<FcReserved> getAllReservedByCliente(Cliente cliente) throws Exception {
        List<FcReserved> fc = null;
        try {
            fc = dao.getFcReservedByCliente(cliente);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return fc;
    }

    public FcReserved getIVById(Long fc) throws Exception {
        FcReserved fcr = null;
        try {
            fcr = (FcReserved) dao.getById(FcReserved.class, fc);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return fcr;
    }

    public void deleteFcReserved(FcReserved fc) throws Exception {
        try {
            dao.delete(fc);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }
    
}
