/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package anticristo2009;
import java.io.*;
import java.util.ArrayList;
import java.io.*;

public class anticristo2009 {
       public static void main(String[] args) {
        String nombreUsuario = "";
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Panaderia panaderia = new Panaderia();
            panaderia.agregarProducto(new ProductoPan("Pan ", 5, 10, "Integral"));
            panaderia.agregarProducto(new ProductoPan("Pan ", 5, 10, "Blanco"));
            panaderia.agregarProducto(new ProductoPan("Pan ", 5, 10, "Concha"));
            panaderia.agregarProducto(new ProductoPan("Empanada", 12, 10, "Manzana"));
            panaderia.agregarProducto(new ProductoPan("Empanada", 12, 10, "Pinnia"));
            panaderia.agregarProducto(new ProductoPan("Empanada", 12, 10, "Camote"));
            panaderia.agregarProducto(new ProductoPan("Empanada", 12, 10, "Fresa"));
            panaderia.agregarProducto(new ProductoPan("Empanada", 12, 10, "Cajeta"));
            panaderia.agregarProducto(new ProductoPan("Dona", 5, 10, "Azucar"));
            panaderia.agregarProducto(new ProductoPan("Dona", 5, 10, "Chocolate"));
            panaderia.agregarProducto(new ProductoPastel("Pastel ", 20.0, 5, "Chocolate"));
            panaderia.agregarProducto(new ProductoPastel("Pastel ", 20.0, 5, "Vainilla"));
            panaderia.agregarProducto(new ProductoPastel("Pastel ", 20.0, 5, "Cafe"));
            panaderia.agregarProducto(new ProductoPastel("Pastel ", 20.0, 5, "Tres leches"));
            panaderia.agregarProducto(new ProductoPastel("Pastel ", 20.0, 5, "Nieve"));
            panaderia.agregarProducto(new ProductoPastel("Pay ", 20.0, 5, "Limon"));
            // Agregar más productos aquí...
            
            nombreUsuario = "";
            System.out.print("Ingrese su nombre: ");
            nombreUsuario = reader.readLine();

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
            System.out.println("\nSeleccione una opcion:");
            System.out.println("1. Mostrar Inventario");
            System.out.println("2. Actualizar Stock");
            System.out.println("3. Actualizar Precio");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");
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
                     String nombreUsuario = "";
                    nombreUsuario = "";
            System.out.print("Ingrese su nombre: ");
            nombreUsuario = reader.readLine();
                   if (nombreUsuario.equalsIgnoreCase("admin")) {
                modoAdministrador(panaderia, reader);
            } else {
                modoUsuario(panaderia, nombreUsuario, reader);
            } 
                   reader.close();
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


