package work.oscarramos.java.jdbc.repository;

import work.oscarramos.java.jdbc.models.Categoria;
import work.oscarramos.java.jdbc.models.Producto;
import work.oscarramos.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getConnection();
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre AS categoria FROM productos AS p " +
                     "INNER JOIN categorias AS c ON (p.categoria_id = c.id)")) {
            while (rs.next()) {
                Producto p = getProducto(rs);

                productos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto findById(Long id) {
        Producto producto = null;
        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT p.*, c.nombre AS categoria FROM productos AS p INNER JOIN categorias AS c ON (p.categoria_id = c.id) WHERE p.id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void save(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, categoria_id, fecha_registro) VALUES(?,?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, producto.getNombres());
            stmt.setLong(2, producto.getPrecio());
            stmt.setLong(3,producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(4, producto.getId());
            } else {
                stmt.setDate(4, new Date(producto.getFech_registro().getTime()));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection()
                .prepareStatement("DELETE FROM productos WHERE id=?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombres(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFech_registro(rs.getDate("fecha_registro"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombres(rs.getString("categoria"));
        p.setCategoria(categoria);
        return p;
    }
}
