/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.RenglonFcReservedDAO;
import ar.com.ventas.entities.FcReserved;
import ar.com.ventas.entities.RenglonFcReserved;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcela
 */
public class RenglonFcReservedBO {

    private final RenglonFcReservedDAO dao = new RenglonFcReservedDAO();

    private static final Logger logger = Logger.getLogger("RenglonFcReservedBO");

    public RenglonFcReserved saveRenglonFcReserved(RenglonFcReserved renglon) throws Exception {
        try {
            dao.save(renglon);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return renglon;
    }

    public List<RenglonFcReserved> getRenglonDeReserved(FcReserved fcr) throws Exception {
        List<RenglonFcReserved> fc = null;
        try {
            fc = dao.getRenglonDeFcReserved(fcr);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return fc;
    }

    public void deleteRenglonFcReserved(RenglonFcReserved renglon) throws Exception {
        try {
            dao.delete(renglon);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }
    
}
