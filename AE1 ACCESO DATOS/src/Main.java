import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            mostrarMenu();
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después del nextInt

            switch (opcion) {
                case 1:
                    // Añadir nuevo artículo
                    agregarArticulo(almacen, scanner);
                    break;
                case 2:
                    // Borrar artículo por id
                    borrarArticulo(almacen, scanner);
                    break;
                case 3:
                    // Consultar artículo por id
                    consultarArticulo(almacen, scanner);
                    break;
                case 4:
                    // Listado de todos los artículos
                    listarArticulos(almacen);
                    break;
                case 5:
                    // Exportar artículos a archivo CSV y terminar programa
                    exportarACSV(almacen);
                    almacen.guardarArticulosEnArchivo();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("\n1. Añadir nuevo artículo");
        System.out.println("2. Borrar artículo por id");
        System.out.println("3. Consulta artículo por id");
        System.out.println("4. Listado de todos los artículos");
        System.out.println("5. Exportar artículos a archivo CSV y Terminar el programa");
    }

    private static void agregarArticulo(Almacen almacen, Scanner scanner) {
        System.out.print("Ingrese el ID del artículo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después del nextInt

        System.out.print("Ingrese el nombre del artículo: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la descripción del artículo: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese el stock del artículo: ");
        int stock = scanner.nextInt();

        System.out.print("Ingrese el precio del artículo: ");
        double precio = scanner.nextDouble();

        Articulo nuevoArticulo = new Articulo(id, nombre, descripcion, stock, precio);
        almacen.agregarArticulo(nuevoArticulo);
    }

    private static void borrarArticulo(Almacen almacen, Scanner scanner) {
        System.out.print("Ingrese el ID del artículo a borrar: ");
        int id = scanner.nextInt();
        almacen.borrarArticuloPorId(id);
        System.out.println("Artículo borrado correctamente.");
    }

    private static void consultarArticulo(Almacen almacen, Scanner scanner) {
        System.out.print("Ingrese el ID del artículo a consultar: ");
        int id = scanner.nextInt();
        Articulo articulo = almacen.consultarArticuloPorId(id);
        if (articulo != null) {
            System.out.println("Información del artículo:");
            System.out.println(articulo.toString());
        } else {
            System.out.println("Artículo no encontrado.");
        }
    }

    private static void listarArticulos(Almacen almacen) {
        System.out.println("Listado de todos los artículos:");
        for (Articulo articulo : almacen.listarArticulos()) {
            System.out.println(articulo.toString());
        }
    }

    private static void exportarACSV(Almacen almacen) {
        almacen.exportarACSV("articulos.csv");
    }
}
