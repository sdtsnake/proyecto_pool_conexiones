package work.oscarramos.java.jdbc;

import work.oscarramos.java.jdbc.models.Categoria;
import work.oscarramos.java.jdbc.models.Producto;
import work.oscarramos.java.jdbc.repository.CategoriaRespotorioImpl;
import work.oscarramos.java.jdbc.repository.ProductoRepositorioImpl;
import work.oscarramos.java.jdbc.repository.Repositorio;
import work.oscarramos.java.jdbc.service.CategoriaServicio;
import work.oscarramos.java.jdbc.service.ProductoServicio;
import work.oscarramos.java.jdbc.service.Servicio;
import work.oscarramos.java.jdbc.service.ServicioProducto;
import work.oscarramos.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) throws SQLException {
        Servicio categoriaServicio = new CategoriaServicio();
        ServicioProducto productoServicio = new ProductoServicio();
        System.out.println("================ Listar ================");
        categoriaServicio.findAll().forEach(System.out::println);

        System.out.println("================ Insertar categoria ================");
        Categoria categoria = new Categoria();
        categoria.setNombres("Iluminacion");

        System.out.println("================ insertar por id ================");
        Producto producto = new Producto();
        producto.setNombres("Lampara Inteligente Samsung");
        producto.setPrecio(180000);
        producto.setFech_registro(new Date());
        producto.setSku("zxw987654");
        productoServicio.saveProductoWhitCategoria(producto,categoria);


        System.out.println("Producto grabado con exito id : " + producto.getId());
        productoServicio.findAll().forEach(System.out::println);

    }
}
