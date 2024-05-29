
package anticristo2009;

/**
 *
 * @author DELL
 */
class ProductoPan extends Producto {
    

    public ProductoPan(String nombre, double precio, int stock, String tipo) {
        super(nombre, precio, stock);
        this.tipo = tipo;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Tipo: " + tipo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
    }
}
