
package ar.com.ventas.entities;

import java.util.Date;

public class Configuracion {
    private Long id;
    private Float iva;
    private Integer sucursalA;
    private Integer numeroFacturaA;
    private Integer sucursalB;
    private Integer numeroFacturaB;
    private Integer numeroNotaCreditoB;
    private Integer numeroNotaCreditoA;
    private Integer numeroNotaDebitoB;
    private Integer numeroNotaDebitoA;
    private Integer numeroRecibo;
    private Integer numeroPedido;
    private Integer cantidadFacturasA;
    private Integer cantidadFacturasB;
    private Integer cantidadPedidos;
    private Integer cantidadNotasCredito;
    private Integer cantidadRecibos;
    private Date ultimaFechaCierre;
    private Date ultimaFechaSistema;
    private Boolean cajaAnteriorCerrada;
    private Integer numeroReciboCompras;
    private Long comprobante;
    
    public Configuracion() {
    }

    public Configuracion(Long id, Float iva, Integer sucursalA, Integer numeroFacturaA, Integer sucursalB, Integer numeroFacturaB, Integer numeroRecibo, Integer numeroPedido, Integer cantidadFacturasA, Integer cantidadFacturasB, Integer cantidadPedidos, Integer cantidadNotasCredito, Integer cantidadRecibos, Date ultimaFechaCierre, Date ultimaFechaSistema, Boolean cajaAnteriorCerrada, Integer numeroReciboCompras, Long comprobante) {
        this.id = id;
        this.iva = iva;
        this.sucursalA = sucursalA;
        this.numeroFacturaA = numeroFacturaA;
        this.sucursalB = sucursalB;
        this.numeroFacturaB = numeroFacturaB;
        this.numeroNotaCreditoB = numeroNotaCreditoB;
        this.numeroNotaCreditoA = numeroNotaCreditoA;
        this.numeroNotaDebitoB = numeroNotaDebitoB;
        this.numeroNotaDebitoA = numeroNotaDebitoA;
        this.numeroRecibo = numeroRecibo;
        this.numeroPedido = numeroPedido;
        this.cantidadFacturasA = cantidadFacturasA;
        this.cantidadFacturasB = cantidadFacturasB;
        this.cantidadPedidos = cantidadPedidos;
        this.cantidadNotasCredito = cantidadNotasCredito;
        this.cantidadRecibos = cantidadRecibos;
        this.ultimaFechaCierre = ultimaFechaCierre;
        this.ultimaFechaSistema = ultimaFechaSistema;
        this.cajaAnteriorCerrada = cajaAnteriorCerrada;
        this.numeroReciboCompras = numeroReciboCompras;
        this.comprobante = comprobante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Integer getSucursalA() {
        return sucursalA;
    }

    public void setSucursalA(Integer sucursalA) {
        this.sucursalA = sucursalA;
    }

    public Integer getNumeroFacturaA() {
        return numeroFacturaA;
    }

    public void setNumeroFacturaA(Integer numeroFacturaA) {
        this.numeroFacturaA = numeroFacturaA;
    }

    public Integer getSucursalB() {
        return sucursalB;
    }

    public void setSucursalB(Integer sucursalB) {
        this.sucursalB = sucursalB;
    }

    public Integer getNumeroFacturaB() {
        return numeroFacturaB;
    }

    public void setNumeroFacturaB(Integer numeroFacturaB) {
        this.numeroFacturaB = numeroFacturaB;
    }

    public Integer getNumeroNotaCreditoB() {
        return numeroNotaCreditoB;
    }

    public void setNumeroNotaCreditoB(Integer numeroNotaCreditoB) {
        this.numeroNotaCreditoB = numeroNotaCreditoB;
    }

    public Integer getNumeroNotaCreditoA() {
        return numeroNotaCreditoA;
    }

    public void setNumeroNotaCreditoA(Integer numeroNotaCreditoA) {
        this.numeroNotaCreditoA = numeroNotaCreditoA;
    }

    public Integer getNumeroNotaDebitoB() {
        return numeroNotaDebitoB;
    }

    public void setNumeroNotaDebitoB(Integer numeroNotaDebitoB) {
        this.numeroNotaDebitoB = numeroNotaDebitoB;
    }

    public Integer getNumeroNotaDebitoA() {
        return numeroNotaDebitoA;
    }

    public void setNumeroNotaDebitoA(Integer numeroNotaDebitoA) {
        this.numeroNotaDebitoA = numeroNotaDebitoA;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Integer getCantidadFacturasA() {
        return cantidadFacturasA;
    }

    public void setCantidadFacturasA(Integer cantidadFacturasA) {
        this.cantidadFacturasA = cantidadFacturasA;
    }

    public Integer getCantidadFacturasB() {
        return cantidadFacturasB;
    }

    public void setCantidadFacturasB(Integer cantidadFacturasB) {
        this.cantidadFacturasB = cantidadFacturasB;
    }

    public Integer getCantidadPedidos() {
        return cantidadPedidos;
    }

    public void setCantidadPedidos(Integer cantidadPedidos) {
        this.cantidadPedidos = cantidadPedidos;
    }

    public Integer getCantidadNotasCredito() {
        return cantidadNotasCredito;
    }

    public void setCantidadNotasCredito(Integer cantidadNotasCredito) {
        this.cantidadNotasCredito = cantidadNotasCredito;
    }

    public Integer getCantidadRecibos() {
        return cantidadRecibos;
    }

    public void setCantidadRecibos(Integer cantidadRecibos) {
        this.cantidadRecibos = cantidadRecibos;
    }

    public Date getUltimaFechaCierre() {
        return ultimaFechaCierre;
    }

    public void setUltimaFechaCierre(Date ultimaFechaCierre) {
        this.ultimaFechaCierre = ultimaFechaCierre;
    }

    public Date getUltimaFechaSistema() {
        return ultimaFechaSistema;
    }

    public void setUltimaFechaSistema(Date ultimaFechaSistema) {
        this.ultimaFechaSistema = ultimaFechaSistema;
    }

    public Boolean getCajaAnteriorCerrada() {
        return cajaAnteriorCerrada;
    }

    public void setCajaAnteriorCerrada(Boolean cajaAnteriorCerrada) {
        this.cajaAnteriorCerrada = cajaAnteriorCerrada;
    }

    public Integer getNumeroReciboCompras() {
        return numeroReciboCompras;
    }

    public void setNumeroReciboCompras(Integer numeroReciboCompras) {
        this.numeroReciboCompras = numeroReciboCompras;
    }

    public Long getComprobante() {
        return comprobante;
    }

    public void setComprobante(Long comprobante) {
        this.comprobante = comprobante;
    }
}