package Actividad1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class LecturaObjeto {

    public static void main(String[] args) {
        List<Articulo> listaArticulos = cargarArticulos();
        

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú");
            System.out.println("1. Añadir un nuevo artículo");
            System.out.println("2. Borrar artículo por ID");
            System.out.println("3. Consultar artículo por ID");
            System.out.println("4. Listado de todos los artículos");
            System.out.println("5. Exportar a CSV");
            System.out.println("6. Fin del programa");

            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    añadirArticulo(listaArticulos);
                    break;
                case 2:
                    borrarArticulo(listaArticulos);
                    break;
                case 3:
                   consultarArticulo(listaArticulos);
                    break;
                case 4:
                    listarArticulo(listaArticulos);
                    break;
                    
                case 5:   
                	exportarCSV(listaArticulos);
            }
        } while (opcion != 6);
      escribirArticulos(listaArticulos);
        System.out.println("Fin del programa");
    }

    private static List<Articulo> cargarArticulos() {
        List<Articulo> listaArticulos = new ArrayList<>();

        try (FileInputStream fichero = new FileInputStream("articulos.dat");
             ObjectInputStream buffer = new ObjectInputStream(fichero)) {

            while (true) {
                try {
                    Articulo art = (Articulo) buffer.readObject();
                    System.out.println(art);
                    listaArticulos.add(art);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error al cargar los artículos: " + e.getMessage());
        }

        return listaArticulos;
    }

    
    //Añadir nuevo artículo 
    private static void añadirArticulo(List<Articulo> listaArticulos) {
        Scanner sc1 = new Scanner(System.in);

        System.out.println("Añadir Nuevo Artículo");
        System.out.print("Nombre del Artículo: ");
        String nombre = sc1.nextLine();

        System.out.print("ID Artículo: ");
         
        String id = sc1.nextLine();
        
        for(Articulo articulo: listaArticulos)
        	if(id.equals(articulo.getId()))
        		System.out.println("El id duplicado Introduzca de nuevo un id");
        String id2 = sc1.nextLine();

        System.out.print("Descripción del Artículo: ");
        String categoria = sc1.nextLine();

        System.out.print("Stock del Artículo: ");
        double precio = sc1.nextDouble();

        System.out.print("Precio del Artículo: ");
        int cantidad = sc1.nextInt();

        Articulo nuevoArticulo = new Articulo(nombre, id, categoria, cantidad, precio);
        listaArticulos.add(nuevoArticulo);

        System.out.println("Artículo añadido con éxito:\n" + nuevoArticulo);
    }
    
    private static void borrarArticulo(List<Articulo> listaArticulos) {
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Ingrese el ID del artículo a borrar");
        String id = sc2.nextLine();

       //Crear una lista de una copia que va a recorrer el for. 
        //Y la eliminación se hace sobre la listaArticulos
        List<Articulo> copiaLista = new ArrayList<>(listaArticulos);

        for (Articulo articulo : copiaLista) {
            if (id.equals(articulo.getId())) {
                listaArticulos.remove(articulo);
                System.out.println("Artículo borrado con éxito");
            }
        }
       
    }

    //Consultar por articulo para esto le pasamos un id y recorremos el Array
    private static void consultarArticulo(List<Articulo>listaArticulos) {
    	Scanner sc3 = new Scanner(System.in);
    	System.out.println("Inserte el id del artículo que quiere consultar");
    	String id = sc3.nextLine();
    	for(Articulo articulo:listaArticulos) {
    	  if(id.equals(articulo.getId()))
    		  System.out.println(articulo);
    	}
    }
    //Listamos el artículo
    private static void listarArticulo(List<Articulo>listaArticulos) {
    	for(Articulo articulo: listaArticulos) {
    		System.out.println(articulo);
    	}
    }
    //La opción 6 leerá todo lo que contenga el fichero "articulos"
    private static void escribirArticulos(List<Articulo> listaArticulos) {
        try (FileOutputStream fichero = new FileOutputStream("articulos.dat");
             ObjectOutputStream dependiente = new ObjectOutputStream(fichero)) {

            for (Articulo articulo : listaArticulos) {
                dependiente.writeObject(articulo);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al escribir los artículos: " + e.getMessage());
        }
    }
   
    
    //Exportar en CSV
    public static void exportarCSV(List<Articulo> listaArticulos) {
        File archivoCSV = new File("./articulos.csv");

        try (FileWriter fw = new FileWriter(archivoCSV, true)) {
            
            fw.write("Nombre,ID,Categoría,Stock,Precio\n");

           
            for (Articulo articulo : listaArticulos) {
            	fw.write(articulo.toCSV() + System.lineSeparator());
            }

            System.out.println("Exportación a CSV correcta.");
        } catch (IOException e) {
            System.out.println("Error al exportar a CSV: " + e.getMessage());
        }
    }
}
