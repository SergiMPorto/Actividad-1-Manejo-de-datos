import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Almacen {
    private List<Articulo> articulos;

    public Almacen() {
        this.articulos = new ArrayList<>();
        cargarArticulosDesdeArchivo();
    }

    public void agregarArticulo(Articulo articulo) {
        // Implementar lógica para verificar duplicados
        if (!existeArticuloConId(articulo.getId())) {
            articulos.add(articulo);
        } else {
            System.out.println("Error: Ya existe un artículo con el mismo ID.");
        }
    }

    public void borrarArticuloPorId(int id) {
        articulos.removeIf(articulo -> articulo.getId() == id);
    }

    public Articulo consultarArticuloPorId(int id) {
        return articulos.stream()
                .filter(articulo -> articulo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Articulo> listarArticulos() {
        return new ArrayList<>(articulos);
    }

    public void exportarACSV(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Articulo articulo : articulos) {
                writer.println(articulo.getId() + "," + articulo.getNombre() + "," +
                        articulo.getDescripcion() + "," + articulo.getStock() + "," + articulo.getPrecio());
            }
            System.out.println("Exportación a CSV exitosa.");
        } catch (IOException e) {
            System.out.println("Error al exportar a CSV: " + e.getMessage());
        }
    }

    public void guardarArticulosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("articulos.dat"))) {
            oos.writeObject(articulos);
            System.out.println("Guardado exitoso en artículos.dat.");
        } catch (IOException e) {
            System.out.println("Error al guardar en artículos.dat: " + e.getMessage());
        }
    }

    private void cargarArticulosDesdeArchivo() {
        File file = new File("articulos.dat");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                articulos = (List<Articulo>) ois.readObject();
                System.out.println("Carga exitosa desde artículos.dat.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar desde artículos.dat: " + e.getMessage());
            }
        }
    }

    private boolean existeArticuloConId(int id) {
        return articulos.stream().anyMatch(articulo -> articulo.getId() == id);
    }
}
