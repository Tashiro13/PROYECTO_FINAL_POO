
package anticristo2009;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
class Panaderia {
    private ArrayList<Producto> inventario;

    public Panaderia() {
        inventario = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        inventario.add(producto);
    }

    public void mostrarInventario() {
        int i = 1;
        for (Producto producto : inventario) {
            System.out.println(i + ". " + producto.nombre + " - Precio: " + producto.getPrecio() + " - Stock: " + producto.getStock());
            i++;
        }
    }

    public Producto getProducto(int index) {
        return inventario.get(index);
    }

    public int getCantidadProductos() {
        return inventario.size();
    }

    public void actualizarStock(int index, int nuevoStock) {
        Producto producto = inventario.get(index);
        producto.stock = nuevoStock;
    }

    public void actualizarPrecio(int index, double nuevoPrecio) {
        Producto producto = inventario.get(index);
        producto.setPrecio(nuevoPrecio);
    }
}
