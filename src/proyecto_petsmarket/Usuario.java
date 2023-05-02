
package proyecto_petsmarket;
import javax.swing.JOptionPane;


public class Usuario {
    
    
    Menu_Principal miMenu = new Menu_Principal();
    
   
    
    public static String User = "Admin";
    private static String Pass = "123";
    
    public static String UserVendedor = "Vendedor";
    private static String PassVendedor = "123";
    
    public void Validar_Tipo_Usuario()
    {
        boolean seguir = true;
        
        while(seguir)
        {
            JOptionPane.showMessageDialog(null, "Bienvenid@ a nuestro sistema de Pets Market");
            String Dato1 = JOptionPane.showInputDialog("Ingrese el usuario: ");
            String Dato2 = JOptionPane.showInputDialog("Ingrese la contraseña: ");
            
            if(Dato1.equals(User) && Dato2.equals(Pass))
            {
                JOptionPane.showMessageDialog(null, "Bienvenido al Sistema de Pets Market \n "+
                                                    "Usuario: " + User);
                miMenu.Menu_Principal();
                seguir = false;
                
            }else if(Dato1.equals(UserVendedor) && Dato2.equals(PassVendedor))
            {
                JOptionPane.showMessageDialog(null, "Bienvenido al Sistema \n "+
                                                    "Usuario: " + UserVendedor);
                miMenu.Menu_Principal_Vendedor();
                seguir = false;
                
                
                
            } else
            {
                JOptionPane.showMessageDialog(null, "Ingresó el usuario o la contraseña de manera incorrecta. ");
                
                int intento = JOptionPane.showConfirmDialog(null, "¿ Desea intentar de nuevo ?");
                
                if(intento == 1)
                {
                   JOptionPane.showMessageDialog(null, " Se va cerrar el sistema. ");
                   seguir=false;
                }
            }
            
        }
        
    }

    
   
    
    
    
    
     
}
