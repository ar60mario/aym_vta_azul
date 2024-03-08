/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.RenglonFacturaDAO;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.RenglonFactura;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class RenglonFacturaBO {

    private final RenglonFacturaDAO dao = new RenglonFacturaDAO();

    private static final Logger logger = Logger.getLogger("RenglonFacturaBO");

    public RenglonFactura saveRenglon(RenglonFactura renglonFactura) throws Exception {
        try {
            dao.save(renglonFactura);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return renglonFactura;
    }

    public List<RenglonFactura> getAllRenglonFacturaFromIvaVentas(IvaVentas iv) throws Exception {
        List<RenglonFactura> listRenglonFactura = null;
        try {
            listRenglonFactura = dao.getAllRenglonFacturaFromIvaVentas(iv);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listRenglonFactura;
    }

    public Double getTotalGravadoCigarrillosByFactura(IvaVentas fc) throws Exception {
        Double totalGravCig = 0.00;
        try {
            totalGravCig = dao.getTotalGravadoCigarrillosByFactura(fc);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return totalGravCig;
    }
}
