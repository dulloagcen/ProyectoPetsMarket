
package proyecto_petsmarket;

import java.util.Arrays;
import javax.swing.JOptionPane;

public class Cliente {
    
    private String Nombre;
    private String Apellido;
    private int Cedula;
    private String Email;
    
    public static Cliente clientes[] = new Cliente[50];
    
    public Cliente(String nombre, String apellido, int cedula, String email) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Cedula = cedula;
        this.Email = email;
    }
    
    public static Cliente[] getClientes() {
        return clientes;
    }
    
    public void Registrar_Cliente() {
        
        int counter = 0;
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].getNombre().equals("Nombre")) {
                String nombreC = JOptionPane.showInputDialog("Ingrese el Nombre del cliente");
                String apellidoC = JOptionPane.showInputDialog("Ingrese el Apellido del cliente");
                int cedulaC = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cédula del cliente"));
                String emailC = JOptionPane.showInputDialog("Ingrese el correo eléctronico (email) del cliente");
                
                clientes[i] = new Cliente(nombreC, apellidoC, cedulaC, emailC);
                
                i = clientes.length;
            } else {
                counter += 1;
            }
        }
        
        if (counter == clientes.length) {
            JOptionPane.showMessageDialog(null, "No hay espacios adicionales para agregar más clientes");
        }
        
    }
    
    public boolean Consultar_Cliente(int Cedula) {
        boolean existeCliente = false;
        int counter = 0;
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].getCedula() == Cedula) {
                existeCliente = true;
                i = clientes.length;
            } else {
                counter += 1;
            }
        }
        if (counter == clientes.length) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con cédula # " + Cedula);
        }
        return existeCliente;
    }
    
    public void Modificar_Cliente()
    {
    
    }
    
    public void Eliminar_Cliente()
    {
    
    }
    
    public void inicializarArreglo() {
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente("Nombre", "Apellido", 0, "Email");
        }
    }

    
    public String getNombre() {
        return Nombre;
    }

    
    public String getApellido() {
        return Apellido;
    }

   
    public int getCedula() {
        return Cedula;
    }

    
    public String getEmail() {
        return Email;
    }
}
