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
public class PorcentualVendedor {
    private Long id;
    private Rubro rubro;
    private Vendedor vendedor;
    private Float porcentual;

    public PorcentualVendedor() {
    }

    public PorcentualVendedor(Long id, Rubro rubro, Vendedor vendedor, Float porcentual) {
        this.id = id;
        this.rubro = rubro;
        this.vendedor = vendedor;
        this.porcentual = porcentual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Float getPorcentual() {
        return porcentual;
    }

    public void setPorcentual(Float porcentual) {
        this.porcentual = porcentual;
    }
    
}
