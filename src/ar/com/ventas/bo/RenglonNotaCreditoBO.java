/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.RenglonNotaCreditoDAO;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.RenglonNotaCredito;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class RenglonNotaCreditoBO {

    private final RenglonNotaCreditoDAO dao = new RenglonNotaCreditoDAO();

    private static final Logger logger = Logger.getLogger("RenglonNotaCreditoBO");

    public RenglonNotaCredito saveRenglon(RenglonNotaCredito renglonNotaCredito) throws Exception {
        try {
            dao.save(renglonNotaCredito);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return renglonNotaCredito;
    }

    public List<RenglonNotaCredito> getAllRenglonNotaCreditoFromIvaVentas(IvaVentas iv) throws Exception {
        List<RenglonNotaCredito> listRenglonFactura = null;
        try {
            listRenglonFactura = dao.getAllRenglonNotaCreditoFromIvaVentas(iv);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listRenglonFactura;
    }
}
