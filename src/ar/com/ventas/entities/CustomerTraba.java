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
public class CustomerTraba {
    private Long id;
    private String codigo;
    private Boolean traba2;

    public CustomerTraba() {
    }

    public CustomerTraba(Long id, String codigo, Boolean traba2) {
        this.id = id;
        this.codigo = codigo;
        this.traba2 = traba2;
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

    public Boolean getTraba2() {
        return traba2;
    }

    public void setTraba2(Boolean traba2) {
        this.traba2 = traba2;
    }
    
}
