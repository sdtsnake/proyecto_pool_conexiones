package work.oscarramos.java.jdbc.service;

import work.oscarramos.java.jdbc.models.Categoria;
import work.oscarramos.java.jdbc.models.Producto;

import java.sql.SQLException;

public interface ServicioProducto extends Servicio<Producto>{
    void saveProductoWhitCategoria(Producto producto, Categoria categoria) throws SQLException;
}
