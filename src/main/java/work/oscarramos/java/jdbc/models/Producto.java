package work.oscarramos.java.jdbc.models;

import java.util.Date;

public class Producto {
    private Long id;
    private String nombres;
    private Integer precio;
    private Date fech_registro;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(Long id, String nombres, Integer precio, Date fech_registro) {
        this.id = id;
        this.nombres = nombres;
        this.precio = precio;
        this.fech_registro = fech_registro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Date getFech_registro() {
        return fech_registro;
    }

    public void setFech_registro(Date fech_registro) {
        this.fech_registro = fech_registro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return  id +
                "|" + nombres +
                "|" + precio +
                "|" + fech_registro +
                "|" + categoria.getNombres();
    }
}
