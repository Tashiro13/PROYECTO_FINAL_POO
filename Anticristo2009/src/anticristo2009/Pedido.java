
package anticristo2009;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
class Pedido {
    private ArrayList<Producto> productos;

    public Pedido() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}
