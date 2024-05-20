/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package anticristo2009;
import java.io.*;
import java.util.ArrayList;

abstract class Producto {
    protected String nombre;
    protected double precio;
    protected int stock;

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
}

class ProductoPan extends Producto {
    private String tipo;

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

class Cliente {
    private String nombre;
    private String direccion;
    private Pedido pedido;

    public Cliente(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.pedido = new Pedido();
    }

    public void mostrarDetalles() {
        System.out.println("Nombre del cliente: " + nombre);
        System.out.println("Dirección del cliente: " + direccion);
    }

    public void realizarPedido(Panaderia panaderia, BufferedReader reader) throws IOException {
        boolean continuarComprando = true;
        while (continuarComprando) {
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
        }
    }

    public void realizarPago() {
        System.out.println("Monto a pagar: " + pedido.calcularTotal());
        System.out.println("Pago realizado. Gracias por su compra.");
        guardarTicket(pedido);
    }

    private void guardarTicket(Pedido pedido) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("historial_compras.txt", true));
            writer.write("----\n");
            for (Producto producto : pedido.getProductos()) {
                writer.write("Nombre: " + producto.nombre + ", Precio: " + producto.getPrecio() + "\n");
            }
            writer.write("Total: " + pedido.calcularTotal() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el ticket: " + e.getMessage());
        }
    }
}

class Empleado {
    private String nombre;
    private String cargo;

    public Empleado(String nombre, String cargo) {
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public void mostrarDetalles() {
        System.out.println("Nombre del empleado: " + nombre);
        System.out.println("Cargo del empleado: " + cargo);
    }
}

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
        }
    }

    private static void modoUsuario(Panaderia panaderia, String nombreUsuario, BufferedReader reader) throws IOException {
        System.out.println("Modo Usuario:");
        Cliente cliente = new Cliente(nombreUsuario, "Dirección de " + nombreUsuario);
        cliente.realizarPedido(panaderia, reader);
        cliente.realizarPago();
    }
}


