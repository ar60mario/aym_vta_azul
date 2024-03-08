/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.entities;

import java.util.Date;

/**
 *
 * @author Marcela
 */
public class CuentaCorriente {
    private Long id;
    private Cliente cliente;
    private Date fecha;
    private Date fechaVencimiento;
    private String letra;
    private Integer sucursal;
    private Integer numero;
    private Integer tipo;
    private Double debe;
    private Double haber;
    private Double saldo;
    private Double ImporteSaldado;
    
    /*
    Tipos de documento:
    1 Factura
    2 Recibo
    3 NC
    */

    public CuentaCorriente() {
    }

    public CuentaCorriente(Long id, Cliente cliente, Date fecha, Date fechaVencimiento, String letra, Integer sucursal, Integer numero, Integer tipo, Double debe, Double haber, Double saldo, Double ImporteSaldado) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.fechaVencimiento = fechaVencimiento;
        this.letra = letra;
        this.sucursal = sucursal;
        this.numero = numero;
        this.tipo = tipo;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
        this.ImporteSaldado = ImporteSaldado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    public Double getImporteSaldado() {
        return ImporteSaldado;
    }

    public void setImporteSaldado(Double ImporteSaldado) {
        this.ImporteSaldado = ImporteSaldado;
    }

}