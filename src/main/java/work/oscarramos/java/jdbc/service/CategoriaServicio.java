package work.oscarramos.java.jdbc.service;

import work.oscarramos.java.jdbc.models.Categoria;
import work.oscarramos.java.jdbc.repository.CategoriaRespotorioImpl;
import work.oscarramos.java.jdbc.repository.Repositorio;
import work.oscarramos.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoriaServicio implements Servicio<Categoria> {

    private Repositorio<Categoria> categoriaRepositorio;

    public CategoriaServicio() {
        this.categoriaRepositorio = new CategoriaRespotorioImpl();
    }

    @Override
    public List<Categoria> findAll() throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.findAll();
        }
    }

    @Override
    public Categoria findById(Long id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.findById(id);
        }
    }

    @Override
    public Categoria save(Categoria categoria) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            Categoria nuvaCategoria = null;
            try {
                nuvaCategoria = categoriaRepositorio.save(categoria);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
            return nuvaCategoria;
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                categoriaRepositorio.delete(id);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }
    }
}
