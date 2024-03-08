/*
 * Aqui va toda la lógica de validaciones respecto a los Administradores.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.CustomerTrabaDAO;
import ar.com.ventas.entities.CustomerTraba;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mar y Mar Informatica
 */
public class CustomerTrabaBO {

    private final CustomerTrabaDAO dao = new CustomerTrabaDAO();

    private static final Logger logger = Logger.getLogger("CustomerTrabaBO");

    public List<CustomerTraba> getAllClientes() throws Exception {
        List<CustomerTraba> listClientes = null;

        try {
            listClientes = dao.getAll(CustomerTraba.class);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listClientes;
    }

    public CustomerTraba saveCliente(CustomerTraba cliente) throws Exception {
        try {
            dao.save(cliente);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return cliente;
    }

    public void updateCliente(CustomerTraba cliente) throws Exception {

        try {
            cliente = (CustomerTraba) dao.update(cliente);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }

    }

    public CustomerTraba getClienteByCodigo(String codigo) throws Exception {
        CustomerTraba cliente = null;
        try {
            cliente = dao.getByCodigo(codigo);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return cliente;
    }

}
