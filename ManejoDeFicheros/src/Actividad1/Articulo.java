package Actividad1;

import java.io.Serializable;

public class Articulo implements Serializable {

/*
 * @autor Sergi
 * @version 1.0
 * @since 26-01-2024
 */

	private static final long serialVersionUID = -6507195478141516125L;




@Override
public String toString() {
	return "Articulo [nombre=" + nombre + ", id=" + id + ", descrip=" + getCategoría() + ", stock=" + stock + ", precio="
			+ precio + "]";
}



	
private String nombre;
private String id;
private String categoría;
private int stock;
private double precio;


public Articulo(String nombre, String id, String categoria, int stock, double precio) {
	super();
	this.nombre = nombre;
	this.id = id;
	this.categoría = categoria;
	this.stock = stock;
	this.precio = precio;
}

public String toCSV() {
	  return nombre + "," + id + "," + categoría + "," + stock + "," + precio;
	}



public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}





public String getCategoría() {
	return categoría;
}


public void setCategoría(String categoría) {
	this.categoría = categoría;
}


public int getStock() {
	return stock;
}


public void setStock(int stock) {
	this.stock = stock;
}


public double getPrecio() {
	return precio;
}


public void setPrecio(double precio) {
	this.precio = precio;
}








}
