
package ar.com.ventas.entities;

import java.util.Date;

public class IvaVentas {
    private Long id;
    private Date fecha;
    private String letra;
    private Integer numeroSucursal;
    private Integer numeroFactura;
    private Integer codigoTipoDoc;
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
    private Date fechaCae;
    private Long cae;
    private String textoPieFactura1;
    private String textoPieFactura2;
    private Vendedor vendedor;
    private Boolean hd;
    private Long docAsociado;

    public IvaVentas() {
    }

    public IvaVentas(Long id, Date fecha, String letra, Integer numeroSucursal, Integer numeroFactura, Integer codigoTipoDoc, Cliente cliente, Double gravado, Double gravadoCigarrillos, Double exento, Double noGravado, Double impuesto, Double iva, Double total, Double descuentoGlobal, Float porcentualDescuentoGlobal, Date fechaCae, Long cae, String textoPieFactura1, String textoPieFactura2, Vendedor vendedor, Boolean hd, Long docAsociado) {
        this.id = id;
        this.fecha = fecha;
        this.letra = letra;
        this.numeroSucursal = numeroSucursal;
        this.numeroFactura = numeroFactura;
        this.codigoTipoDoc = codigoTipoDoc;
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
        this.fechaCae = fechaCae;
        this.cae = cae;
        this.textoPieFactura1 = textoPieFactura1;
        this.textoPieFactura2 = textoPieFactura2;
        this.vendedor = vendedor;
        this.hd = hd;
        this.docAsociado = docAsociado;
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

    public Integer getCodigoTipoDoc() {
        return codigoTipoDoc;
    }

    public void setCodigoTipoDoc(Integer codigoTipoDoc) {
        this.codigoTipoDoc = codigoTipoDoc;
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

    public Float getPorcentualDescuentoGlobal() {
        return porcentualDescuentoGlobal;
    }

    public void setPorcentualDescuentoGlobal(Float porcentualDescuentoGlobal) {
        this.porcentualDescuentoGlobal = porcentualDescuentoGlobal;
    }

    public Date getFechaCae() {
        return fechaCae;
    }

    public void setFechaCae(Date fechaCae) {
        this.fechaCae = fechaCae;
    }

    public Long getCae() {
        return cae;
    }

    public void setCae(Long cae) {
        this.cae = cae;
    }

    public String getTextoPieFactura1() {
        return textoPieFactura1;
    }

    public void setTextoPieFactura1(String textoPieFactura1) {
        this.textoPieFactura1 = textoPieFactura1;
    }

    public String getTextoPieFactura2() {
        return textoPieFactura2;
    }

    public void setTextoPieFactura2(String textoPieFactura2) {
        this.textoPieFactura2 = textoPieFactura2;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Boolean getHd() {
        return hd;
    }

    public void setHd(Boolean hd) {
        this.hd = hd;
    }

    public Long getDocAsociado() {
        return docAsociado;
    }

    public void setDocAsociado(Long docAsociado) {
        this.docAsociado = docAsociado;
    }
    
}