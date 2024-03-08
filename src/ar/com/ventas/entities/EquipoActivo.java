/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.entities;

/**
 *
 * @author argia
 */
public class EquipoActivo {
    private Long id;
    private String nombre;
    private Integer orden;
    private String tipo;
    private Boolean activo;

    public EquipoActivo() {
    }

    public EquipoActivo(Long id, String nombre, Integer orden, Boolean activo, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
        this.activo = activo;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
