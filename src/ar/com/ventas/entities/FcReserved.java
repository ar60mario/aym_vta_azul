
package ar.com.ventas.entities;

import java.util.Date;

public class FcReserved {
    private Long id;
    private Date fecha;
    private Cliente cliente;
    private Double gravado;
    private Double gravadoCigarrillos;
    private Double exento;
    private Double noGravado;
    private Double impuesto;
    private Double iva;
    private Double total;
    private Double descuentoGlobal;
    private Float porcentualDescuentoGlobal;

    public FcReserved() {
    }

    public FcReserved(Long id, Date fecha, Cliente cliente, Double gravado, Double gravadoCigarrillos, Double exento, Double noGravado, Double impuesto, Double iva, Double total, Double descuentoGlobal, Float porcentualDescuentoGlobal) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.gravado = gravado;
        this.gravadoCigarrillos = gravadoCigarrillos;
        this.exento = exento;
        this.noGravado = noGravado;
        this.impuesto = impuesto;
        this.iva = iva;
        this.total = total;
        this.descuentoGlobal = descuentoGlobal;
        this.porcentualDescuentoGlobal = porcentualDescuentoGlobal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getGravado() {
        return gravado;
    }

    public void setGravado(Double gravado) {
        this.gravado = gravado;
    }

    public Double getGravadoCigarrillos() {
        return gravadoCigarrillos;
    }

    public void setGravadoCigarrillos(Double gravadoCigarrillos) {
        this.gravadoCigarrillos = gravadoCigarrillos;
    }

    public Double getExento() {
        return exento;
    }

    public void setExento(Double exento) {
        this.exento = exento;
    }

    public Double getNoGravado() {
        return noGravado;
    }

    public void setNoGravado(Double noGravado) {
        this.noGravado = noGravado;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDescuentoGlobal() {
        return descuentoGlobal;
    }

    public void setDescuentoGlobal(Double descuentoGlobal) {
        this.descuentoGlobal = descuentoGlobal;
    }
    //porcentualDescuentoGlobal
    public Float getPorcentualDescuentoGlobal() {
        return porcentualDescuentoGlobal;
    }

    public void setPorcentualDescuentoGlobal(Float porcentualDescuentoGlobal) {
        this.porcentualDescuentoGlobal = porcentualDescuentoGlobal;
    }
}