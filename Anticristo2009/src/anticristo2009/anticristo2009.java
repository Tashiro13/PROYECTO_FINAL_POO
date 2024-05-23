/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package anticristo2009;
import java.io.*;
import java.util.ArrayList;



public class anticristo2009 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Panaderia panaderia = new Panaderia();
            panaderia.agregarProducto(new ProductoPan("Pan Integral", 2.5, 10, "Integral"));
            panaderia.agregarProducto(new ProductoPastel("Pastel de Chocolate", 20.0, 5, "Chocolate"));
            // Agregar más productos aquí...

            System.out.print("Ingrese su nombre: ");
            String nombreUsuario = reader.readLine();

            if (nombreUsuario.equalsIgnoreCase("admin")) {
                modoAdministrador(panaderia, reader);
            } else {
                modoUsuario(panaderia, nombreUsuario, reader);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void modoAdministrador(Panaderia panaderia, BufferedReader reader) throws IOException {
        System.out.println("Modo Administrador:");
        boolean continuarModoAdmin = true;
        while (continuarModoAdmin) {
            try {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Mostrar Inventario");
            System.out.println("2. Actualizar Stock");
            System.out.println("3. Actualizar Precio");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            int opcionAdmin = Integer.parseInt(reader.readLine());
            switch (opcionAdmin) {
                case 1:
                    panaderia.mostrarInventario();
                    break;
                case 2:
                    System.out.println("Ingrese el número del producto para actualizar el stock:");
                    panaderia.mostrarInventario();
                    System.out.print("Producto: ");
                    int indexStock = Integer.parseInt(reader.readLine()) - 1;
                    System.out.print("Nuevo stock: ");
                    int nuevoStock = Integer.parseInt(reader.readLine());
                    panaderia.actualizarStock(indexStock, nuevoStock);
                    System.out.println("Stock actualizado.");
                    break;
                case 3:
                    System.out.println("Ingrese el número del producto para actualizar el precio:");
                    panaderia.mostrarInventario();
                    System.out.print("Producto: ");
                    int indexPrecio = Integer.parseInt(reader.readLine()) - 1;
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = Double.parseDouble(reader.readLine());
                    panaderia.actualizarPrecio(indexPrecio, nuevoPrecio);
                    System.out.println("Precio actualizado.");
                    break;
                case 4:
                    continuarModoAdmin = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            } catch (Exception e) {
                System.out.println("[!] Error al ingresar los datos. Intente de nuevo.");
            }
        }
    }

    private static void modoUsuario(Panaderia panaderia, String nombreUsuario, BufferedReader reader){
        System.out.println("Modo Usuario:");
        Cliente cliente = new Cliente(nombreUsuario, "Dirección de " + nombreUsuario);
        cliente.realizarPedido(panaderia, reader);
        cliente.realizarPago();
    }
}


