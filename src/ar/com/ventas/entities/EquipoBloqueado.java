package ar.com.ventas.entities;

/**
 *
 * @author argia
 */
public class EquipoBloqueado {
    Long id;
    Integer orden;
    String nombre;
    Boolean bloqueado;

    public EquipoBloqueado() {
    }

    public EquipoBloqueado(Long id, Integer orden, String nombre, Boolean bloqueado) {
        this.id = id;
        this.orden = orden;
        this.nombre = nombre;
        this.bloqueado = bloqueado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
    
}
