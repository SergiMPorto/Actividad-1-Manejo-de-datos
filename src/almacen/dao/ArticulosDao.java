package almacen.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import almacen.entidad.Articulos;

public class ArticulosDao {
	public static void main(String[] args) {
        Articulos producto1 = new Articulos("Teclado", "01", "Informática", 50, 30);
        Articulos producto2 = new Articulos("Monitor", "02", "Informática", 30, 200);
        Articulos producto3 = new Articulos("Altavoces", "03", "Audio", 20, 50);
        Articulos producto4 = new Articulos("Mouse", "04", "Informática", 60, 15);
        Articulos producto5 = new Articulos("Impresora", "05", "Informática", 15, 100);

        List<Articulos> listaProductos = new ArrayList<>();
        listaProductos.add(producto1);
        listaProductos.add(producto2);
        listaProductos.add(producto3);
        listaProductos.add(producto4);
        listaProductos.add(producto5);

        try (FileOutputStream fileOutputStream = new FileOutputStream("productos.dat", false);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            for (Articulos producto : listaProductos) {
                objectOutputStream.writeObject(producto);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

        System.out.println("Fin del programa");
    }

}
