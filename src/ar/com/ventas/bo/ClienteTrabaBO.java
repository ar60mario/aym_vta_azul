/*
 * Aqui va toda la lógica de validaciones respecto a los Administradores.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.ClienteTrabaDAO;
import ar.com.ventas.entities.ClienteTraba;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mar y Mar Informatica
 */
public class ClienteTrabaBO {

    private final ClienteTrabaDAO dao = new ClienteTrabaDAO();

    private static final Logger logger = Logger.getLogger("ClienteTrabaBO");

    public List<ClienteTraba> getAllClientes() throws Exception {
        List<ClienteTraba> listClientes = null;

        try {
            listClientes = dao.getAll(ClienteTraba.class);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listClientes;
    }

    public void saveCliente(ClienteTraba cliente) throws Exception {
        try {
            dao.save(cliente);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public void updateCliente(ClienteTraba cliente) throws Exception {
        try {
            dao.update(cliente);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public ClienteTraba getClienteByCodigo(String codigo) throws Exception {
        ClienteTraba cliente = null;
        try {
            cliente = dao.getByCodigo(codigo);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return cliente;
    }

}
