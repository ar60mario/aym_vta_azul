/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.ProductoDAO;
import ar.com.ventas.entities.Producto;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class ProductoBO {

    private final ProductoDAO dao = new ProductoDAO();

    public List<Producto> getAllProductos() throws Exception {
        ProductoDAO dao = new ProductoDAO();
        List<Producto> losProductos = new ArrayList<Producto>();

        try {
            losProductos = dao.getAll(Producto.class);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return losProductos;

    }

    public Producto guardarProducto(Producto producto) throws Exception {
        try {
            dao.save(producto);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return producto;
    }

    public Producto getProductoByCodigo(Integer codigo) throws Exception {
        Producto producto = null;
        try {
            producto = dao.getByCodigo(codigo);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return producto;
    }

    public void updateProducto(Producto producto) throws Exception {
        try {
            producto = (Producto) dao.update(producto);
        } catch (HibernateException ex) {
            throw new Exception(ex);

        }
    }

    public void deleteProducto(Producto producto) throws Exception {
        try {
            dao.delete(producto);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public void saveListaProductos(List<Producto> listaProductos) throws Exception {
        if (listaProductos != null && !listaProductos.isEmpty()) {
            for (Producto prod : listaProductos) {
                if (prod.getRubro() == null) {
                    throw new Exception("El RUBRO asociado al Producto " + prod.getRubro().getCodigo() + " no es válido.");
                }
                if (prod.getSubRubro() == null) {
                    throw new Exception("El SUB-RUBRO asociado al Producto " + prod.getSubRubro().getCodigo() + " no es válido.");
                }
                try {
                    dao.save(prod);
                } catch (HibernateException ex) {
                    throw new Exception("Ha ocurrido un problema intentando guardar los PRODUCTOS.\nPor favor intente nuevamente mas tarde.");
                }
            }
        }
    }

    public List<Producto> getProductosByFiltro(String filtro) throws Exception {
        List<Producto> productos = null;
        try {
            productos = dao.getProductosByFiltro(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return productos;
    }
    
    public List<Producto> getProductosByIvaCeroAndFiltro(String f) throws Exception {
        List<Producto> productos = null;
        try {
            productos = dao.getProductosByIvaCeroAndFiltro(f);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return productos;
    }
    
    public List<Producto> getProductosByFiltroSin90(String filtro) throws Exception {
        List<Producto> productos = null;
        try {
            productos = dao.getProductosByFiltroSin90(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return productos;
    }
    
    public List<Producto> getProductosByFiltroSin90SinDepo(String filtro) throws Exception {
        List<Producto> productos = null;
        try {
            productos = dao.getProductosByFiltroSin90SinDepo(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return productos;
    }
    
    public List<Producto> getProductoSinCodigoBarras(String filtro) throws Exception {
        List<Producto> productos = null;
        try {
            productos = dao.getProductosSinCodigoBarras(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return productos;
    }

    public List<Producto> getAllProductosOrdByCodigo(String filtro) throws Exception {
        ProductoDAO dao = new ProductoDAO();
        List<Producto> losProductos = new ArrayList<Producto>();

        try {
            losProductos = dao.getAllOrdByCodigo(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return losProductos;
    }

    public List<Producto> getAllProductosOrdByNombre(String filtro) throws Exception {
        ProductoDAO dao = new ProductoDAO();
        List<Producto> losProductos = new ArrayList<Producto>();

        try {
            losProductos = dao.getAllProductosOrdenado(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return losProductos;
    }

    public List<Producto> getAllProductosOrdByRubro(String filtro) throws Exception {
        ProductoDAO dao = new ProductoDAO();
        List<Producto> losProductos = new ArrayList<Producto>();

        try {
            losProductos = dao.getAllOrdByRubro(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return losProductos;
    }

    public List<Producto> getAllProductosEnCero(String filtro) throws Exception {
        List<Producto> productos = null;
        try {
            productos = dao.getAllProductosEnCero(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return productos;
    }

    public List<Producto> getProductosInactivos() throws Exception {
        List<Producto> productos = null;
        try {
            productos = dao.getProductosInactivos();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return productos;
    }
    
    public Producto getProductoByCodigoBarras(Long codigoBarras) throws Exception {
        Producto producto = null;
        try {
            producto = dao.getByCodigoBarras(codigoBarras);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return producto;
    }
    
}
