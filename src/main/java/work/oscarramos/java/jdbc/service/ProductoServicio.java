package work.oscarramos.java.jdbc.service;

import work.oscarramos.java.jdbc.models.Categoria;
import work.oscarramos.java.jdbc.models.Producto;
import work.oscarramos.java.jdbc.repository.CategoriaRespotorioImpl;
import work.oscarramos.java.jdbc.repository.ProductoRepositorioImpl;
import work.oscarramos.java.jdbc.repository.Repositorio;
import work.oscarramos.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductoServicio implements ServicioProducto {
    private Repositorio<Producto> productoRepositorio;
    private Repositorio<Categoria> categoriaRepositorio;

    public ProductoServicio() {
        this.productoRepositorio = new ProductoRepositorioImpl();
        this.categoriaRepositorio = new CategoriaRespotorioImpl();
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            productoRepositorio.setConn(conn);
            return productoRepositorio.findAll();
        }
    }

    @Override
    public Producto findById(Long id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            productoRepositorio.setConn(conn);
            return productoRepositorio.findById(id);
        }
    }

    @Override
    public Producto save(Producto producto) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            productoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            Producto nuevoProdcuto = null;
            try {
                nuevoProdcuto = productoRepositorio.save(producto);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
            return nuevoProdcuto;
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            productoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                productoRepositorio.delete(id);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveProductoWhitCategoria(Producto producto, Categoria categoria) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            categoriaRepositorio.setConn(conn);
            productoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                Categoria nuevaCategoria = categoriaRepositorio.save(categoria);
                producto.setCategoria(nuevaCategoria);
                productoRepositorio.save(producto);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }
    }
}
