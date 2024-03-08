/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.entities;

/**
 *
 * @author Mario
 */
public class ClienteTraba {
    public Long id;
    public String codigo;
    public Boolean traba1;

    public ClienteTraba() {
    }

    public ClienteTraba(Long id, String codigo, Boolean traba1) {
        this.id = id;
        this.codigo = codigo;
        this.traba1 = traba1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getTraba1() {
        return traba1;
    }

    public void setTraba1(Boolean traba1) {
        this.traba1 = traba1;
    }
    
}
