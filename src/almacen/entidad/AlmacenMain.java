package almacen.entidad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlmacenMain {

	
		public static void main(String[] args) {
	        List<Articulos> listaProductos = cargarProductos();

	        Scanner scanner = new Scanner(System.in);
	        int opcion;

	        do {
	            System.out.println("Menú");
	            System.out.println("1. Añadir un nuevo producto");
	            System.out.println("2. Borrar producto por código");
	            System.out.println("3. Consultar producto por código");
	            System.out.println("4. Listado de todos los productos");
	            System.out.println("5. Exportar a CSV");
	            System.out.println("6. Fin del programa");
	            System.out.print("Elige una opción: ");
	            opcion = scanner.nextInt();

	            switch (opcion) {
	                case 1:
	                    añadirProducto(listaProductos);
	                    break;
	                case 2:
	                    borrarProducto(listaProductos);
	                    break;
	                case 3:
	                    consultarProducto(listaProductos);
	                    break;
	                case 4:
	                    listarProductos(listaProductos);
	                    break;
	                case 5:
	                    exportarCSV(listaProductos);
	                    break;
	            }
	        } while (opcion != 6);

	        guardarProductos(listaProductos);
	        System.out.println("Fin del programa");
	    }

	    private static List<Articulos> cargarProductos() {
	        List<Articulos> listaProductos = new ArrayList<>();

	        try (FileInputStream fileInputStream = new FileInputStream("productos.dat");
	             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

	            while (true) {
	                try {
	                	Articulos producto = (Articulos) objectInputStream.readObject();
	                    System.out.println(producto);
	                    listaProductos.add(producto);
	                } catch (EOFException e) {
	                    break;
	                }
	            }

	        } catch (IOException | ClassNotFoundException e) {
	            System.err.println("Error al cargar los productos: " + e.getMessage());
	        }

	        return listaProductos;
	    }

	    private static void añadirProducto(List<Articulos> listaProductos) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.println("Añadir Nuevo Producto");
	        System.out.print("Nombre del Producto: ");
	        String nombre = scanner.nextLine();

	        String codigo;
	        boolean codigoDuplicado;
	        do {
	            System.out.print("Código del Producto: ");
	            codigo = scanner.nextLine();
	            codigoDuplicado = false;

	            // Validar código duplicado
	            for (Articulos producto : listaProductos) {
	                if (codigo.equals(producto.getCodigo())) {
	                    System.out.println("El código está duplicado. Introduce otro código.");
	                    codigoDuplicado = true;
	                    break;
	                }
	            }
	        } while (codigoDuplicado);

	        System.out.print("Categoría del Producto: ");
	        String categoria = scanner.nextLine();

	        System.out.print("Cantidad Disponible: ");
	        int cantidadDisponible = scanner.nextInt();

	        System.out.print("Precio del Producto: ");
	        double precio = scanner.nextDouble();

	        Articulos nuevoProducto = new Articulos(nombre, codigo, categoria, cantidadDisponible, precio);
	        listaProductos.add(nuevoProducto);

	        System.out.println("Producto añadido con éxito:\n" + nuevoProducto);
	    }

	    private static void borrarProducto(List<Articulos> listaProductos) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Ingrese el código del producto a borrar");
	        String codigo = scanner.nextLine();

	        
	        List<Articulos> copiaLista = new ArrayList<>(listaProductos);

	        for (Articulos producto : copiaLista) {
	            if (codigo.equals(producto.getCodigo())) {
	                listaProductos.remove(producto);
	                System.out.println("Producto borrado con éxito");
	            }
	        }
	    }

	    private static void consultarProducto(List<Articulos> listaProductos) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Inserte el código del producto que quiere consultar");
	        String codigo = scanner.nextLine();
	        for (Articulos producto : listaProductos) {
	            if (codigo.equals(producto.getCodigo())) {
	                System.out.println(producto);
	                return;
	            }
	        }
	        System.out.println("Producto no encontrado");
	    }

	    private static void listarProductos(List<Articulos> listaProductos) {
	        for (Articulos producto : listaProductos) {
	            System.out.println(producto);
	        }
	    }

	    private static void guardarProductos(List<Articulos> listaProductos) {
	        try (FileOutputStream fileOutputStream = new FileOutputStream("productos.dat");
	             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

	            for (Articulos producto : listaProductos) {
	                objectOutputStream.writeObject(producto);
	            }

	        } catch (IOException e) {
	            System.err.println("Error al escribir los productos: " + e.getMessage());
	        }
	    }

	    public static void exportarCSV(List<Articulos> listaProductos) {
	        File archivoCSV = new File("./productos.csv");

	        try (FileWriter fileWriter = new FileWriter(archivoCSV, true)) {

	            fileWriter.write("Nombre,Código,Categoría,Cantidad Disponible,Precio\n");

	            for (Articulos producto : listaProductos) {
	                fileWriter.write(producto.toCSV() + System.lineSeparator());
	            }

	            System.out.println("Exportación a CSV correcta.");
	        } catch (IOException e) {
	            System.out.println("Error al exportar a CSV: " + e.getMessage());
	        }

	}

}
