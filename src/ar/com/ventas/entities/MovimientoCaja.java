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
public class MovimientoCaja {
    private Long id;
    private Date fecha;
    private Double totalFacturado;
    private Double saldoDeudorAnterior;
    private Double cajaInicial;
    private Double totalDeudoresHoy;
    private Double descuentos;
    private Double deposito;
    private Double totalVales;
    private Boolean cerrado;

    public MovimientoCaja() {
    }

    public MovimientoCaja(Long id, Date fecha, Double totalFacturado, Double saldoDeudorAnterior, Double cajaInicial, Double totalDeudoresHoy, Double descuentos, Double deposito, Double totalVales, Boolean cerrado) {
        this.id = id;
        this.fecha = fecha;
        this.totalFacturado = totalFacturado;
        this.saldoDeudorAnterior = saldoDeudorAnterior;
        this.cajaInicial = cajaInicial;
        this.totalDeudoresHoy = totalDeudoresHoy;
        this.descuentos = descuentos;
        this.deposito = deposito;
        this.totalVales = totalVales;
        this.cerrado = cerrado;
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

    public Double getTotalFacturado() {
        return totalFacturado;
    }

    public void setTotalFacturado(Double totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    public Double getSaldoDeudorAnterior() {
        return saldoDeudorAnterior;
    }

    public void setSaldoDeudorAnterior(Double saldoDeudorAnterior) {
        this.saldoDeudorAnterior = saldoDeudorAnterior;
    }

    public Double getCajaInicial() {
        return cajaInicial;
    }

    public void setCajaInicial(Double cajaInicial) {
        this.cajaInicial = cajaInicial;
    }

    public Double getTotalDeudoresHoy() {
        return totalDeudoresHoy;
    }

    public void setTotalDeudoresHoy(Double totalDeudoresHoy) {
        this.totalDeudoresHoy = totalDeudoresHoy;
    }

    public Double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(Double descuentos) {
        this.descuentos = descuentos;
    }

    public Double getDeposito() {
        return deposito;
    }

    public void setDeposito(Double deposito) {
        this.deposito = deposito;
    }

    public Double getTotalVales() {
        return totalVales;
    }

    public void setTotalVales(Double totalVales) {
        this.totalVales = totalVales;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    
    
}
