/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.entities;

import java.util.Date;

/**
 *
 * @author Mario
 */
public class CtaCteCliente {
    private Long id;
    private Date fecha;
    private Double debe;
    private Double haber;
    private Double saldo;
    private Cliente cliente;
    private String tipo;
    private IvaVentas factura;
    private IvaVentas notaCredito;
    private Recibo recibo;

    public CtaCteCliente() {
    }

    public CtaCteCliente(Long id, Date fecha, Double debe, Double haber, Double saldo, Cliente cliente, String tipo, IvaVentas factura, IvaVentas notaCredito, Recibo recibo) {
        this.id = id;
        this.fecha = fecha;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
        this.cliente = cliente;
        this.tipo = tipo;
        this.factura = factura;
        this.notaCredito = notaCredito;
        this.recibo = recibo;
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

    public Double getDebe() {
        return debe;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public IvaVentas getFactura() {
        return factura;
    }

    public void setFactura(IvaVentas factura) {
        this.factura = factura;
    }

    public IvaVentas getNotaCredito() {
        return notaCredito;
    }

    public void setNotaCredito(IvaVentas notaCredito) {
        this.notaCredito = notaCredito;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }
    
}