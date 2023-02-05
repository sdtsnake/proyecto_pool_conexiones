package work.oscarramos.java.jdbc;

import work.oscarramos.java.jdbc.models.Categoria;
import work.oscarramos.java.jdbc.models.Producto;
import work.oscarramos.java.jdbc.repository.ProductoRepositorioImpl;
import work.oscarramos.java.jdbc.repository.Repositorio;
import work.oscarramos.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("================ Listar ================");
            repositorio.findAll().forEach(System.out::println);
            System.out.println("================ ver por id ================");
            System.out.println(repositorio.findById(2L));
            System.out.println("================ insertar por id ================");
            Producto producto = new Producto();
            producto.setNombres("Teclado mecanico TermalTake");
            producto.setPrecio(245000);
            producto.setFech_registro(new Date());
            Categoria categoria = new Categoria();
            categoria.setId(3L);
            producto.setCategoria(categoria);
            repositorio.save(producto);
            System.out.println("Producto grabado con exito");
            System.out.println("================ Nuevo listado ================");
            repositorio.findAll().forEach(System.out::println);
            System.out.println("================ Actializar producto ================");
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
