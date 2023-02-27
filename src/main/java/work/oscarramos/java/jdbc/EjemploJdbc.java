package work.oscarramos.java.jdbc;

import work.oscarramos.java.jdbc.models.Categoria;
import work.oscarramos.java.jdbc.models.Producto;
import work.oscarramos.java.jdbc.repository.CategoriaRespotorioImpl;
import work.oscarramos.java.jdbc.repository.ProductoRepositorioImpl;
import work.oscarramos.java.jdbc.repository.Repositorio;
import work.oscarramos.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                System.out.println("================ Insertar categoria ================");
                Repositorio<Categoria> categoriaRepositorio = new CategoriaRespotorioImpl(conn);
                Categoria categoria = new Categoria();
                categoria.setNombres("Electrodomesticos");
                Categoria nuevaCatergoria = categoriaRepositorio.save(categoria);


                System.out.println("================ Listar ================");
                repositorio.findAll().forEach(System.out::println);
                System.out.println("================ ver por id ================");
                System.out.println(repositorio.findById(2L));
                System.out.println("================ insertar por id ================");
                Producto producto = new Producto();
                producto.setNombres("Refrigerador Samsung");
                producto.setPrecio(5400000);
                producto.setFech_registro(new Date());
                producto.setCategoria(nuevaCatergoria);
                producto.setSku("zxw987654");
                repositorio.save(producto);
                System.out.println("Producto grabado con exito id : " + producto.getId());
                System.out.println("================ Nuevo listado ================");
                repositorio.findAll().forEach(System.out::println);
                conn.commit();
            }catch (SQLException ex){
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
