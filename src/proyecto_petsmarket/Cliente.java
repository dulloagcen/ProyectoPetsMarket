
package proyecto_petsmarket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                String nombreC = JOptionPane.showInputDialog("Ingrese el Nombre del cliente: ");
                String apellidoC = JOptionPane.showInputDialog("Ingrese el Apellido del cliente: ");
                int cedulaC = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cédula del cliente: "));
                String emailC = JOptionPane.showInputDialog("Ingrese el correo eléctronico (email) del cliente: ");
                
                clientes[i] = new Cliente(nombreC, apellidoC, cedulaC, emailC);
                
                i = clientes.length;
            } else {
                counter += 1;
            }
        }
        
        if (counter == clientes.length) {
            JOptionPane.showMessageDialog(null, "No hay espacios adicionales para agregar más clientes. ");
        }
        
    }
    
    public boolean Consultar_Cliente(int Cedula) {
        boolean existeCliente = false;
        int counter = 0;
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].getCedula() == Cedula) {
                existeCliente = true;
                JOptionPane.showMessageDialog(null, "¡Cliente existente!"
                                                    + "\nNombre completo: " + clientes[i].getNombre() + " " + clientes[i].getApellido() 
                                                    + "\nEmail: " + clientes[i].getEmail());
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
        boolean seguir = true;
        boolean seguir2 = true;
        
        while(seguir)
        {
            int contador = 0;
            int CedulaC = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del cliente a modificar"));
            
            for(int i=0;i<clientes.length;i++)
            {
                if(clientes[i].Cedula == CedulaC)
                {
                    while(seguir2)
                    {
                       int opc = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el dato que desea modificar: \n"+
                                                                          "1. Nombre \n"+
                                                                          "2. Apellidos \n"+
                                                                          "3. Cédula \n"+
                                                                          "4. Correo"));
                       switch(opc)
                       {
                           case 1:
                            String nombre = JOptionPane.showInputDialog("Ingrese el nombre a modificar: ");
                            clientes[i].Nombre = nombre;
                              break;
                           case 2:
                            String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos: ");
                            clientes[i].Apellido = apellidos;
                            break;
                           case 3:
                            int cedula = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cédula: "));
                            clientes[i].Cedula = cedula;
                            break;
                            case 4:
                            String correo = JOptionPane.showInputDialog("Ingrese el correo electronico: ");
                            clientes[i].Email = correo;
                            break;
                            
                           default:
                                JOptionPane.showMessageDialog(null, "Seleccione una opción correcta ");
                                break; 
                            
                       }
                       
                       int OtroM = JOptionPane.showConfirmDialog(null, "¿Desea modificar más información del Cliente?");
                       if(OtroM == 1)
                       {
                           seguir2 = false;
                       }
                    }
                }
                else
                {
                    ++contador;
                }
            }
            
            if(contador == clientes.length)
            {
               JOptionPane.showMessageDialog(null, "La cédula consultada no pertenece a ningun cliente registrado."); 
            }
            
            int OtroIntento = JOptionPane.showConfirmDialog(null, "¿Desea modificar la información de otro cliente?");
            if(OtroIntento == 1)
            {
                seguir = false;
            }
        
        }
    
    }
    
    public void Eliminar_Cliente()
    {
    
    }
    
    public void inicializarArreglo() {
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente("Nombre", "Apellido", 0, "Email");
        }
        
        FileReader lectorArchivo;
        try {
            lectorArchivo = new FileReader("C:\\Users\\pc\\OneDrive\\Documents\\NetBeansProjects\\Proyecto petsmarket V1.0\\Proyecto_PetsMarket\\clientesExistentes.txt");
        } catch (FileNotFoundException err) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo\n" + err);
            return;
        }

        BufferedReader textoArchivo;
        textoArchivo = new BufferedReader(lectorArchivo);

        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].Nombre.equals("Nombre")) {
                String LineaTxt;
                try {
                    LineaTxt = textoArchivo.readLine();
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, err);
                    return;
                }

                if (LineaTxt == null) {
                    break;
                }

                String Valores[] = LineaTxt.split(";");
                
                System.out.println(Arrays.toString(Valores));

                int cedula = Integer.parseInt(Valores[2]);
                
                clientes[i] = new Cliente(Valores[0], Valores[1], cedula, Valores[3]);
            }
        }
        
        

        System.out.println("Clientes existentes cargados correctamente.");
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




    public void MenuCliente(){
        boolean Seguir = true;
        int opcMenu;

         while (Seguir == true) {   
            opcMenu = Integer.parseInt(JOptionPane.showInputDialog("Menú Cliente.\n" + 
                                                                   "Seleccione la opción deseada:\n" + 

                                                                   "1. Registar Cliente.\n" + 
                                                                   "2. Consultar Cliente.\n" + 
                                                                   "3. Modificar Cliente.\n" + 
                                                                   "4. Menu principal"));
            switch (opcMenu) {
                case 1 :
                    Registrar_Cliente();
                    break;
                case 2 :
                    int idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Por favor ingrese la cédula del cliente: "));
                    Consultar_Cliente(idCliente);
                    break;
                case 3 :
                    Modificar_Cliente();
                    break;
                
                case 4: 
                    Seguir = false;
                        break;

                default:
                    JOptionPane.showMessageDialog(null,"Ingrese una opcion válida.");    
                    break;
            }
         }
}
    
}