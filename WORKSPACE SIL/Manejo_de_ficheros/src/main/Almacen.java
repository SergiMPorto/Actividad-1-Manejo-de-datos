package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Almacen {

	private List<Articulo> articulos;

    public Almacen() {
        this.articulos = new ArrayList<>();
        cargarArticulosDesdeArchivo();
    }
  
    

    /*
     * VERIFICAR LA EXISTENCIA DE DUPLICADOS Y AGREGAR EL ARTICULO SI NO EXISTE 
     */
    public void agregarArticulo(Articulo articulo) {
        if (!existeArticuloConId(articulo.getId())) {
            articulos.add(articulo);
        } else {
            System.out.println("HA OCURRIDO UN ERROR! EXISTE UN ARTICULO CO EL MISMO ID.");
        }
    }
    
    
/*
 * METODO BORRAR ARTICULO
 */
    public void borrarArticuloPorId(int id) {
        articulos.removeIf(articulo -> articulo.getId() == id);
    }

    public Articulo consultarArticuloPorId(int id) {
        return articulos.stream()
                .filter(articulo -> articulo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    
    /*
     * METODO LISTAR ARTICULOS
     */
    public List<Articulo> listarArticulos() {
        return new ArrayList<>(articulos);
    }

    public void exportarACSV(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Articulo articulo : articulos) {
                writer.println(articulo.getId() + "," + articulo.getNombre() + "," +
                        articulo.getDescripcion() + "," + articulo.getCantidad() + "," + articulo.getPrecio());
            }
            System.out.println("EL PROCESO DE -EXPORTACION A CSV- SE HA REALIZADO CON EXITO.");
        } catch (IOException e) {
            System.out.println("HA OCURRIDO UN ERROR AL REALIZAR EL PROCESO DE -EXPORTACION A CSV-: " + e.getMessage());
        }
    }
/*
 * METODO PARA GUARDAR ARTICULO EN ARCHIVOS 
 */
    public void guardarArticulosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("articulos.dat"))) {
            oos.writeObject(articulos);
            System.out.println("EL ARTÑICULO SE HA GUARDADO CON EXITO EN ARTICULO.DAT");
        } catch (IOException e) {
            System.out.println("HA OCURRIDO UN ERROR EN EL PROCESO DE -GUARDAR EL ARTÍCULO EN ARTÑICULO.DAT- : " + e.getMessage());
        }
    }

    /*
     * METODO CARGAR ARTICULOS DESDE ARCHIVOS
     */
    private void cargarArticulosDesdeArchivo() {
        File file = new File("articulos.dat");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                articulos = (List<Articulo>) ois.readObject();
                System.out.println("EL PROCESO DE -CARGAR EN ARTÍCULO.DAT- SE HA REALIZADO CON EXITO");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("HA OCURRIDO UN ERROR AL CARGAR EL ARCHIVO EN ERTICULO.DAT: " + e.getMessage());
            }
        }
    }

    private boolean existeArticuloConId(int id) {
        return articulos.stream().anyMatch(articulo -> articulo.getId() == id);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
