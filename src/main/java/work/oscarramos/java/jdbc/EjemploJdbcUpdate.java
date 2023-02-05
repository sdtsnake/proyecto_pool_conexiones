package work.oscarramos.java.jdbc;

import work.oscarramos.java.jdbc.models.Categoria;
import work.oscarramos.java.jdbc.models.Producto;
import work.oscarramos.java.jdbc.repository.ProductoRepositorioImpl;
import work.oscarramos.java.jdbc.repository.Repositorio;
import work.oscarramos.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcUpdate {
    public static void main(String[] args) {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("================ Listar ================");
            repositorio.findAll().forEach(System.out::println);
            System.out.println("================ ver por id ================");
            System.out.println(repositorio.findById(2L));
            System.out.println("================ Update por id ================");
            Producto producto = new Producto();
            producto.setId(9L);
            producto.setNombres("Teclado mecanico K95");
            producto.setPrecio(180000);
            producto.setFech_registro(new Date());
            Categoria categoria = new Categoria();
            categoria.setId(3L);
            producto.setCategoria(categoria);
            repositorio.save(producto);
            System.out.println("Producto actiañozado con exito");
            System.out.println("================ Nuevo listado ================");
            repositorio.findAll().forEach(System.out::println);
            System.out.println("================ Actializar producto ================");
            producto.setId(3L);
            producto.setNombres("Teclado mecanico Red Dragon");
            producto.setPrecio(150000);
            producto.setFech_registro(new Date());
            repositorio.save(producto);
            System.out.println("================ Nuevo listado ================");
            repositorio.findAll().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
