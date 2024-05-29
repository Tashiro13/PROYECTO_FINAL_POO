
package anticristo2009;

/**
 *
 * @author DELL
 */
abstract class Producto {
    protected String nombre;
    protected double precio;
    protected int stock;
    protected String tipo;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public abstract void mostrarDetalles();

    public void reducirStock(int cantidad) {
        stock -= cantidad;
    }

    public int getStock() {
        return stock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
    public String toCsv() {
        return nombre + "," + precio + "," + stock + "," + tipo;
    }

    public static Producto fromCsv(String csv) {
        String[] parts = csv.split(",");
        if (parts[0].startsWith("Pan")) {
            return new ProductoPan(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), parts[3]);
        } else if (parts[0].startsWith("Empanada")) {
            return new ProductoPan(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), parts[3]);
        } else if (parts[0].startsWith("Dona")) {
            return new ProductoPan(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), parts[3]);
        } else if (parts[0].startsWith("Pastel")) {
            return new ProductoPastel(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), parts[3]);
        }
        return null; 
    }
}

