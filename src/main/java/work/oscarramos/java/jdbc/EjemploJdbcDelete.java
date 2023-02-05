package work.oscarramos.java.jdbc;

import work.oscarramos.java.jdbc.models.Producto;
import work.oscarramos.java.jdbc.repository.ProductoRepositorioImpl;
import work.oscarramos.java.jdbc.repository.Repositorio;
import work.oscarramos.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcDelete {
    public static void main(String[] args) {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("================ Listar ================");
            repositorio.findAll().forEach(System.out::println);
            System.out.println("================ ver por id ================");
            System.out.println(repositorio.findById(2L));
            System.out.println("================ Borrar por id ================");
            repositorio.delete(8L);
            System.out.println("Producto borrado con exito");
            System.out.println("================ Nuevo listado ================");
            repositorio.findAll().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
