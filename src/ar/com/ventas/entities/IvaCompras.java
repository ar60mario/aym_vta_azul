package ar.com.ventas.entities;

import java.util.Calendar;
import java.util.Date;

public class IvaCompras {
    private Long id;
    private Date fechaPeriodo;
    private Date fechaFactura;
    private Proveedor proveedor;
    private String letra;
    private Integer numeroSucursal;
    private Integer numeroFactura;
    private Double gravado;
    private Double noGravado;
    private Double impuestoInterno;
    private Double percepcionIiBb;
    private Double percepcionIva;
    private Double otro;
    private Double iva;
    private Double total;

    public IvaCompras() {
    }

    public IvaCompras(Long id, Date fechaPeriodo, Date fechaFactura, Proveedor proveedor, String letra, Integer numeroSucursal, Integer numeroFactura, Double gravado, Double noGravado, Double impuestoInterno, Double percepcionIiBb, Double percepcionIva, Double otro, Double iva, Double total) {
        this.id = id;
        this.fechaPeriodo = fechaPeriodo;
        this.fechaFactura = fechaFactura;
        this.proveedor = proveedor;
        this.letra = letra;
        this.numeroSucursal = numeroSucursal;
        this.numeroFactura = numeroFactura;
        this.gravado = gravado;
        this.noGravado = noGravado;
        this.impuestoInterno = impuestoInterno;
        this.percepcionIiBb = percepcionIiBb;
        this.percepcionIva = percepcionIva;
        this.otro = otro;
        this.iva = iva;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPeriodo() {
        return fechaPeriodo;
    }

    public void setFechaPeriodo(Date fechaPeriodo) {
        this.fechaPeriodo = fechaPeriodo;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Integer getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(Integer numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Double getGravado() {
        return gravado;
    }

    public void setGravado(Double gravado) {
        this.gravado = gravado;
    }

    public Double getNoGravado() {
        return noGravado;
    }

    public void setNoGravado(Double noGravado) {
        this.noGravado = noGravado;
    }

    public Double getImpuestoInterno() {
        return impuestoInterno;
    }

    public void setImpuestoInterno(Double impuestoInterno) {
        this.impuestoInterno = impuestoInterno;
    }

    public Double getPercepcionIiBb() {
        return percepcionIiBb;
    }

    public void setPercepcionIiBb(Double percepcionIiBb) {
        this.percepcionIiBb = percepcionIiBb;
    }

    public Double getPercepcionIva() {
        return percepcionIva;
    }

    public void setPercepcionIva(Double percepcionIva) {
        this.percepcionIva = percepcionIva;
    }

    public Double getOtro() {
        return otro;
    }

    public void setOtro(Double otro) {
        this.otro = otro;
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

}