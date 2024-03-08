/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.RenglonPedidoDAO;
import ar.com.ventas.entities.Pedido;
import ar.com.ventas.entities.RenglonPedido;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class RenglonPedidoBO {
    
    private final RenglonPedidoDAO dao = new RenglonPedidoDAO();

    private static final Logger logger = Logger.getLogger("RenglonPedidoBO");
    
    public RenglonPedido saveRenglonPedido(RenglonPedido renglon) throws Exception {
        try {
            dao.save(renglon);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return renglon;
    }

    public List<RenglonPedido> getAllRenglonPedidoFromPedido(Pedido pedido) throws Exception {
        List<RenglonPedido> listRenglonPedido = null;
        try {
            listRenglonPedido = dao.getRenglonPedidoFromPedido(pedido);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listRenglonPedido;
    }

}
