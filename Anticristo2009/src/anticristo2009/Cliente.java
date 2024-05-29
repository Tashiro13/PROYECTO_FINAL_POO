
package anticristo2009;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author DELL
 */
class Cliente {
    final static String miRuta = "D:\\Ticket\\";
    private String nombre;
        private Pedido pedido;

    public Cliente(String nombre, String direccion) {
        this.nombre = nombre;
        this.pedido = new Pedido();
    }

    public void mostrarDetalles() {
        System.out.println("Nombre del cliente: " + nombre);
           }

    public void realizarPedido(Panaderia panaderia, BufferedReader reader) {
        boolean continuarComprando = true;
        while (continuarComprando) {
            try {
                System.out.println("Seleccione un producto:");
                panaderia.mostrarInventario();
                System.out.print("Ingrese el número del producto que desea comprar (0 para finalizar): ");
                int opcion = Integer.parseInt(reader.readLine());
                if (opcion == 0) {
                    continuarComprando = false;
                } else if (opcion >= 1 && opcion <= panaderia.getCantidadProductos()) {
                    Producto productoElegido = panaderia.getProducto(opcion - 1);
                    if (productoElegido.getStock() > 0) {
                        pedido.agregarProducto(productoElegido);
                        productoElegido.reducirStock(1);
                        System.out.println("Producto agregado al carrito: " + productoElegido.nombre);
                    } else {
                        System.out.println("Lo siento, el producto seleccionado está agotado.");
                    }
                } else {
                    System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("[!] Error al ingresar los datos. Intente de nuevo.");
            }
        }
    }

    public void realizarPago() {
        System.out.println("Monto a pagar: " + pedido.calcularTotal());
        System.out.println("Pago realizado. Gracias por su compra.");
        guardarTicket(pedido);
    }

    public void guardarTicket(Pedido pedido) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(miRuta + "historial_compras.txt", true));
            writer.write("\n" + "Nombre de cliente: " + nombre + "\n");
            for (Producto producto : pedido.getProductos()) {
                writer.write("Producto: " + producto.nombre + " " + producto.tipo + ", Precio: " + producto.getPrecio() + "\n");
            }
            writer.write("Total a pagar: " + pedido.calcularTotal() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("[!] Error al guardar el ticket: " + e.getMessage());
        }
    }
}
