
package proyecto_petsmarket;

import javax.swing.JOptionPane;

public class Menu_Principal {
    
    Ventas misVentas = new Ventas();
    Inventario MiInventario1 = new Inventario();
    
    Cliente misClientes = new Cliente("Nombre", "Apellido", 0, "Email");
    
    public void Menu_Principal() {
        
        misClientes.inicializarArreglo();
        
        boolean seguir = true;
        while(seguir) {
            int opc = Integer.parseInt(JOptionPane.showInputDialog(null,"Bienvenido a Pets Market, selecciona una opción \n"+
                                                                      "1. Menú Inventario \n" +
                                                                      "2. Menú Ventas \n" +
                                                                      "3. Menú Control Usuarios \n" +
                                                                      "4. Salir del Sistema"));
            
            switch(opc) {
                case 1:
                    MiInventario1.MenuInventario();
                    break;
                case 2:
                    misVentas.menuVentas();
                    break;
                    
                case 3:
                    //menu usuarios
                    break;
                case 4:
                    seguir = false;
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null,"Opción inválida, escoja una opción del menú");
                    break;
            }
        }
    }
}
