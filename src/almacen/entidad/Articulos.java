package almacen.entidad;

public class Articulos {
	 private static final long serialVersionUID = -6507195478141516125L;

	    private String nombre;
	    private String codigo;
	    private String categoria;
	    private int cantidadDisponible;
	    private double precio;

	    public Articulos(String nombre, String codigo, String categoria, int cantidadDisponible, double precio) {
	        this.nombre = nombre;
	        this.codigo = codigo;
	        this.categoria = categoria;
	        this.cantidadDisponible = cantidadDisponible;
	        this.precio = precio;
	    }

	    @Override
	    public String toString() {
	        return "Producto{" +
	                "nombre='" + nombre + '\'' +
	                ", codigo='" + codigo + '\'' +
	                ", categoria='" + categoria + '\'' +
	                ", cantidadDisponible=" + cantidadDisponible +
	                ", precio=" + precio +
	                '}';
	    }

	    public String toCSV() {
	        return nombre + "," + codigo + "," + categoria + "," + cantidadDisponible + "," + precio;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getCodigo() {
	        return codigo;
	    }

	    public void setCodigo(String codigo) {
	        this.codigo = codigo;
	    }

	    public String getCategoria() {
	        return categoria;
	    }

	    public void setCategoria(String categoria) {
	        this.categoria = categoria;
	    }

	    public int getCantidadDisponible() {
	        return cantidadDisponible;
	    }

	    public void setCantidadDisponible(int cantidadDisponible) {
	        this.cantidadDisponible = cantidadDisponible;
	    }

	    public double getPrecio() {
	        return precio;
	    }

	    public void setPrecio(double precio) {
	        this.precio = precio;
	    }
}
