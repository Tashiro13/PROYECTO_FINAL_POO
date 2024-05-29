
package anticristo2009;

/**
 *
 * @author DELL
 */
class ProductoPastel extends Producto {

    public ProductoPastel(String nombre, double precio, int stock, String tipo) {
        super(nombre, precio, stock);
        this.tipo = tipo;
           }

    @Override
    public void mostrarDetalles() {
        System.out.println("Sabor: " + tipo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
    }
}