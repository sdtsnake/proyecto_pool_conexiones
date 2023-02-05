package work.oscarramos.java.jdbc.models;

public class Categoria {
    private Long id;
    private String nombres;

    public Categoria(Long id, String nombres) {
        this.id = id;
        this.nombres = nombres;
    }

    public Categoria() {
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
}
