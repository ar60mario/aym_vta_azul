package ar.com.ventas.entities;

import java.util.Date;

public class Producto {

    private Long id;
    private Integer codigo;
    private Long codigoBarras;
    private String detalle;
    private Double precio;
    private Float impuesto;
    private Double sugerido;
    private Boolean inactivo;
    private Boolean ivaCero;
    private Integer ivaProducto;
    private Float stock;
    private Float stockMinimo;
    private Rubro rubro;
    private SubRubro subRubro;
    private Boolean llevaDto;
    private Integer descuento;
    private Integer cantidadDto;
    private Boolean unidad;
    private Integer cantidadCaja;
    private Producto productoCaja;
    private Double costo;
    private Integer cantidadPorBulto;
    private Float dcto;
    private Float margenAyM;
    private Float MargenCli;
    private Float impuestoCompra;
    private Date fechaUltima;
    private Boolean actualizarWeb;
    private Boolean actualizarGondola;
    private Boolean actualizarListaPrecios;
    private Boolean listaPdf;
    private Boolean bloqueado;
    private Integer terminal;

    public Producto() {
    }

    public Producto(Long id, Integer codigo, Long codigoBarras, String detalle, Double precio, Float impuesto, Double sugerido, Boolean inactivo, Boolean ivaCero, Integer ivaProducto, Float stock, Float stockMinimo, Rubro rubro, SubRubro subRubro, Boolean llevaDto, Integer descuento, Integer cantidadDto, Boolean unidad, Integer cantidadCaja, Producto productoCaja, Double costo, Integer cantidadPorBulto, Float dcto, Float margenAyM, Float MargenCli, Float impuestoCompra, Date fechaUltima, Boolean actualizarWeb, Boolean actualizarGondola, Boolean actualizarListaPrecios, Boolean listaPdf, Boolean bloqueado, Integer terminal) {
        this.id = id;
        this.codigo = codigo;
        this.codigoBarras = codigoBarras;
        this.detalle = detalle;
        this.precio = precio;
        this.impuesto = impuesto;
        this.sugerido = sugerido;
        this.inactivo = inactivo;
        this.ivaCero = ivaCero;
        this.ivaProducto = ivaProducto;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.rubro = rubro;
        this.subRubro = subRubro;
        this.llevaDto = llevaDto;
        this.descuento = descuento;
        this.cantidadDto = cantidadDto;
        this.unidad = unidad;
        this.cantidadCaja = cantidadCaja;
        this.productoCaja = productoCaja;
        this.costo = costo;
        this.cantidadPorBulto = cantidadPorBulto;
        this.dcto = dcto;
        this.margenAyM = margenAyM;
        this.MargenCli = MargenCli;
        this.impuestoCompra = impuestoCompra;
        this.fechaUltima = fechaUltima;
        this.actualizarWeb = actualizarWeb;
        this.actualizarGondola = actualizarGondola;
        this.actualizarListaPrecios = actualizarListaPrecios;
        this.listaPdf = listaPdf;
        this.bloqueado = bloqueado;
        this.terminal = terminal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Long codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Float impuesto) {
        this.impuesto = impuesto;
    }

    public Double getSugerido() {
        return sugerido;
    }

    public void setSugerido(Double sugerido) {
        this.sugerido = sugerido;
    }

    public Boolean getInactivo() {
        return inactivo;
    }

    public void setInactivo(Boolean inactivo) {
        this.inactivo = inactivo;
    }

    public Boolean getIvaCero() {
        return ivaCero;
    }

    public void setIvaCero(Boolean ivaCero) {
        this.ivaCero = ivaCero;
    }

    public Integer getIvaProducto() {
        return ivaProducto;
    }

    public void setIvaProducto(Integer ivaProducto) {
        this.ivaProducto = ivaProducto;
    }

    public Float getStock() {
        return stock;
    }

    public void setStock(Float stock) {
        this.stock = stock;
    }

    public Float getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Float stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public SubRubro getSubRubro() {
        return subRubro;
    }

    public void setSubRubro(SubRubro subRubro) {
        this.subRubro = subRubro;
    }

    public Boolean getLlevaDto() {
        return llevaDto;
    }

    public void setLlevaDto(Boolean llevaDto) {
        this.llevaDto = llevaDto;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Integer getCantidadDto() {
        return cantidadDto;
    }

    public void setCantidadDto(Integer cantidadDto) {
        this.cantidadDto = cantidadDto;
    }

    public Boolean getUnidad() {
        return unidad;
    }

    public void setUnidad(Boolean unidad) {
        this.unidad = unidad;
    }

    public Integer getCantidadCaja() {
        return cantidadCaja;
    }

    public void setCantidadCaja(Integer cantidadCaja) {
        this.cantidadCaja = cantidadCaja;
    }

    public Producto getProductoCaja() {
        return productoCaja;
    }

    public void setProductoCaja(Producto productoCaja) {
        this.productoCaja = productoCaja;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getCantidadPorBulto() {
        return cantidadPorBulto;
    }

    public void setCantidadPorBulto(Integer cantidadPorBulto) {
        this.cantidadPorBulto = cantidadPorBulto;
    }

    public Float getDcto() {
        return dcto;
    }

    public void setDcto(Float dcto) {
        this.dcto = dcto;
    }

    public Float getMargenAyM() {
        return margenAyM;
    }

    public void setMargenAyM(Float margenAyM) {
        this.margenAyM = margenAyM;
    }

    public Float getMargenCli() {
        return MargenCli;
    }

    public void setMargenCli(Float MargenCli) {
        this.MargenCli = MargenCli;
    }

    public Float getImpuestoCompra() {
        return impuestoCompra;
    }

    public void setImpuestoCompra(Float impuestoCompra) {
        this.impuestoCompra = impuestoCompra;
    }

    public Date getFechaUltima() {
        return fechaUltima;
    }

    public void setFechaUltima(Date fechaUltima) {
        this.fechaUltima = fechaUltima;
    }

    public Boolean getActualizarWeb() {
        return actualizarWeb;
    }

    public void setActualizarWeb(Boolean actualizarWeb) {
        this.actualizarWeb = actualizarWeb;
    }

    public Boolean getActualizarGondola() {
        return actualizarGondola;
    }

    public void setActualizarGondola(Boolean actualizarGondola) {
        this.actualizarGondola = actualizarGondola;
    }

    public Boolean getActualizarListaPrecios() {
        return actualizarListaPrecios;
    }

    public void setActualizarListaPrecios(Boolean actualizarListaPrecios) {
        this.actualizarListaPrecios = actualizarListaPrecios;
    }

    public Boolean getListaPdf() {
        return listaPdf;
    }

    public void setListaPdf(Boolean listaPdf) {
        this.listaPdf = listaPdf;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }
    
}