
package anticristo2009;

/**
 *
 * @author DELL
 */
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
