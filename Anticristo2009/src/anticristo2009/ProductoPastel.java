
package anticristo2009;

/**
 *
 * @author DELL
 */
class ProductoPastel extends Producto {
    private String sabor;

    public ProductoPastel(String nombre, double precio, int stock, String sabor) {
        super(nombre, precio, stock);
        this.sabor = sabor;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Sabor: " + sabor);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
    }
}