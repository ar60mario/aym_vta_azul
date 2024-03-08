/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.ventas.bo;

import ar.com.ventas.dao.UsuarioDAO;
import ar.com.ventas.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Administrador
 */
public class UsuarioBO {
    UsuarioDAO dao = new UsuarioDAO();

public List<Usuario> getAllUsuarios() throws Exception {
        List<Usuario> listaUsuario = new ArrayList();
        try{
            listaUsuario = dao.getAll(Usuario.class);
        }
        catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listaUsuario;
    }

    public Usuario guardarUsuarios(Usuario usuario) throws Exception {
        try{
            usuario = (Usuario) dao.save(usuario);
        }
        catch(HibernateException ex){
            throw new Exception();
        }
        return usuario;
    }

    public Usuario saveUsuario(Usuario usuario) throws Exception{
        
        try{
            usuario = (Usuario) dao.save(usuario);
        }catch (HibernateException ex){
            throw new Exception(ex);
        }
        return usuario;
    }

    public void updateUsuario(Usuario usuario) throws HibernateException{
        try{
            dao.update(usuario);
        } catch(HibernateException ex){
            throw new HibernateException (ex);
        }
    }

    public void deleteUsuario(Usuario usuarioABorrar) throws Exception{
        try{ 
            dao.delete(usuarioABorrar);
        } catch(HibernateException ex){
            throw new HibernateException (ex);    
        }
    }
    
    public Usuario getUsuarioByCodigo(Integer codigo) throws Exception {
        Usuario usuario = null;
        try{
            usuario = dao.getByCodigo(codigo);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return usuario;
    }
}
