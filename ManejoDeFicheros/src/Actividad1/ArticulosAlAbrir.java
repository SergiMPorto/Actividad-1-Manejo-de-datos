package Actividad1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArticulosAlAbrir implements Serializable {

    private static final long serialVersionUID = 3047170725207906012L;

    public static void main(String[] args) {
        Articulo art1 = new Articulo("Disco Duro", "01", "Informatica", 60, 100);
        Articulo art2 = new Articulo("Televisor", "02", "Audio Visual", 1300, 100);
        Articulo art3 = new Articulo("Iphone", "03", "Móviles", 1500, 100);
        Articulo art4 = new Articulo("PlayStation 5", "04", "Videojuegos", 600, 50);
        Articulo art5 = new Articulo("Conga", "05", "Hogar", 450, 150);

        List<Articulo> listaArticulos = new ArrayList<>();
        listaArticulos.add(art1);
        listaArticulos.add(art2);
        listaArticulos.add(art3);
        listaArticulos.add(art4);
        listaArticulos.add(art5);
        
        //Creamos el fichero articulos.dat y ponemos como segundo argumento false para poder
        //poder sobreescribir sobre él.

        try (FileOutputStream fichero = new FileOutputStream("articulos.dat", false);
             ObjectOutputStream dependiente = new ObjectOutputStream(fichero)) {
//Le pasamos un for para que nos pase por consola los articulos que ya tenemos en el fichero "articulos.dat
            for (Articulo articulo : listaArticulos) {
                dependiente.writeObject(articulo);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Fin del programa");
    }
}
