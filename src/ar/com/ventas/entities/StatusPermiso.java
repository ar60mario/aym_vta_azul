package ar.com.ventas.entities;

public class StatusPermiso {

    private Long id;
    private Boolean status;
    private String compu;
    private Integer orden;
    private EquipoActivo equipoActivo;

    public StatusPermiso() {
    }

    public StatusPermiso(Long id, Boolean status, String compu, Integer orden, EquipoActivo equipoActivo) {
        this.id = id;
        this.status = status;
        this.compu = compu;
        this.orden = orden;
        this.equipoActivo = equipoActivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCompu() {
        return compu;
    }

    public void setCompu(String compu) {
        this.compu = compu;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public EquipoActivo getEquipoActivo() {
        return equipoActivo;
    }

    public void setEquipoActivo(EquipoActivo equipoActivo) {
        this.equipoActivo = equipoActivo;
    }
}
