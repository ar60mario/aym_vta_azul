/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.IvaVentasDAO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.IvaVentas;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcela
 */
public class IvaVentasBO {

    private final IvaVentasDAO dao = new IvaVentasDAO();

    private static final Logger logger = Logger.getLogger("IvaVentasBO");

    public IvaVentas saveIvaVentas(IvaVentas ivaVentas) throws Exception {
        try {
            dao.save(ivaVentas);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ivaVentas;
    }

    public void updateIvaVentas(IvaVentas ivaVentas) throws Exception {
        try {
            dao.update(ivaVentas);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public List<IvaVentas> getAllIvaVentas() throws Exception {

        List<IvaVentas> listIvaVentas = null;

        try {
            listIvaVentas = dao.getAll(IvaVentas.class);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listIvaVentas;

    }

    public List<IvaVentas> getAllIvaVentasByCodigoYFecha(Cliente cod, Date fechaDe, Date fechaA) throws Exception {
        List<IvaVentas> listIvaVentas = null;

        try {
            listIvaVentas = dao.getFacturasByFiltro(cod, fechaDe, fechaA);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listIvaVentas;
    }

    public IvaVentas getIVById(Long iv) throws Exception {
        IvaVentas ivaVentas = null;
        try {
            ivaVentas = (IvaVentas) dao.getById(IvaVentas.class, iv);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ivaVentas;
    }

    public IvaVentas getFacturaByNumero(String letra, Integer sucursal, Integer numero) throws Exception {
        IvaVentas ivaVentas = null;
        try {
            ivaVentas = (IvaVentas) dao.getByLetraNumero(letra, sucursal, numero);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ivaVentas;
    }

    public IvaVentas getByLetraNumeroAndTipo(String letra, Integer numero, Integer tipo) throws Exception {
        IvaVentas ivaVentas = null;
        try {
            ivaVentas = (IvaVentas) dao.getByLetraNumeroAndTipo(letra, numero, tipo);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ivaVentas;
    }

    public List<IvaVentas> getByError(String letra, Integer sucursal, Integer numero) throws Exception {
        List<IvaVentas> ivaVentas = null;
        try {
            ivaVentas = (List<IvaVentas>) dao.getByError(letra, sucursal, numero);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ivaVentas;
    }

    public List<IvaVentas> getFacturasEntreFechas(Date fd, Date fa) throws Exception {
        List<IvaVentas> fact = null;
        try {
            fact = (List<IvaVentas>) dao.getFacturasEntreFechas(fd, fa);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return fact;
    }

    public List<IvaVentas> getNcByCliente(Cliente c) throws Exception {
        List<IvaVentas> listIvaVentas = null;
        try {
            listIvaVentas = dao.getNcByCliente(c);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listIvaVentas;
    }
}
