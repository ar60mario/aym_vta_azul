/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.ProductoBO;
import ar.com.ventas.entities.Producto;
import ar.com.ventas.util.HibernateUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class ProductoService {

    public List<Producto> getAllProductos() throws Exception {
        List<Producto> productoLista = new ArrayList();
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ProductoBO bo = new ProductoBO();
            productoLista = bo.getAllProductos();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productoLista;
    }
    
    public List<Producto> getProductosByIvaCeroAndFiltro(String f) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Producto> productos = null;
        try {
            productos = new ProductoBO().getProductosByIvaCeroAndFiltro(f);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productos;
    }

    public void guardarProducto(Producto producto) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ProductoBO bo = new ProductoBO();
            bo.guardarProducto(producto);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public Producto getProductoByCodigo(Integer codigo) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Producto producto = null;
        try {
            producto = new ProductoBO().getProductoByCodigo(codigo);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return producto;
    }

    public void updateProducto(Producto producto) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new ProductoBO().updateProducto(producto);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public void deleteProducto(Producto producto) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new ProductoBO().deleteProducto(producto);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public void saveListaProductos(List<Producto> listaProductos) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new ProductoBO().saveListaProductos(listaProductos);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public List<Producto> getProductosByFiltro(String filtro) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Producto> productos = null;
        try {
            productos = new ProductoBO().getProductosByFiltro(filtro);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productos;
    }
    
    public List<Producto> getProductosByFiltroSin90(String filtro) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Producto> productos = null;
        try {
            productos = new ProductoBO().getProductosByFiltroSin90(filtro);
            System.out.println(productos);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productos;
    }
    
    public List<Producto> getProductosByFiltroSin90SinDepo(String filtro) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Producto> productos = null;
        try {
            productos = new ProductoBO().getProductosByFiltroSin90SinDepo(filtro);
            System.out.println(productos);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productos;
    }
    
    public List<Producto> getAllProductosOrdenadoByCodigo(String filtro) throws Exception {
        List<Producto> productoLista = new ArrayList();
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ProductoBO bo = new ProductoBO();
            productoLista = bo.getAllProductosOrdByCodigo(filtro);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productoLista;
    }

    public List<Producto> getAllProductosOrdenadoByNombre(String filtro) throws Exception {
        List<Producto> productoLista = new ArrayList();
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ProductoBO bo = new ProductoBO();
            productoLista = bo.getAllProductosOrdByNombre(filtro);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productoLista;
    }

    public List<Producto> getAllProductosOrdenadoByRubro(String filtro) throws Exception {
        List<Producto> productoLista = new ArrayList();
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ProductoBO bo = new ProductoBO();
            productoLista = bo.getAllProductosOrdByRubro(filtro);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productoLista;
    }

    public List<Producto> getAllProductosOrdenadoByNombreInactivo() throws Exception {
        List<Producto> productoLista = new ArrayList();
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ProductoBO bo = new ProductoBO();
            productoLista = bo.getProductosInactivos();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productoLista;
    }

    public List<Producto> getAllProductosSinCodigoBarras(String filtro) throws Exception {
        List<Producto> productoLista = new ArrayList();
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ProductoBO bo = new ProductoBO();
            productoLista = bo.getProductoSinCodigoBarras(filtro);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productoLista;
    }

    public List<Producto> getAllProductosEnCero(String filtro) throws Exception {
        List<Producto> productoLista = new ArrayList();
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ProductoBO bo = new ProductoBO();
            productoLista = bo.getAllProductosEnCero(filtro);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return productoLista;
    }
    
    public Producto getProductoByCodigoBarras(Long codigoBarras) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Producto producto = null;
        try {
            producto = new ProductoBO().getProductoByCodigoBarras(codigoBarras);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return producto;
    }
    
}
