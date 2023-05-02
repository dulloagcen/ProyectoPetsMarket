
package proyecto_petsmarket;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Inventario {
    // enum para tipo de animal
    public enum TipoAnimal {
        PERRO, 
        GATO, 
        ROEDOR, 
        PAJARO, 
        OTRO
    }
    
    //enum para el area del producto
    public enum Area {
        ALIMENTOS, 
        ARTICULOS,
        MEDICAMENTOS, 
        GRANEL
    }
    
    //Clase producto con metodos get/set/tostring para el manejo del objeto
    
    public static class Producto {
        private String codigo;
        private String nombre;
        private TipoAnimal tipoAnimal;
        private double precio;
        private int cantidad;
        private Area area;
        
        public Producto(String codigo, String nombre, TipoAnimal tipoAnimal, double precio, int cantidad, Area area) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.tipoAnimal = tipoAnimal;
            this.precio = precio;
            this.cantidad = cantidad;
            this.area = area;
        }
        
        public String getCodigo() {
            return codigo;
        }
        
        public String getNombre() {
            return nombre;
        }
        
        public TipoAnimal getTipoAnimal() {
            return tipoAnimal;
        }
        
        public double getPrecio() {
            return precio;
        }
        
        public int getCantidad() {
            return cantidad;
        }
        
        public Area getArea() {
            return area;
        }
        
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        
        public void setTipoAnimal(TipoAnimal tipoAnimal) {
            this.tipoAnimal = tipoAnimal;
        }
        
        public void setPrecio(double precio) {
            this.precio = precio;
        }
        
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
        
        public String toString() {
            return  "***************************\n"+
                    "Código: " + codigo + "\n" +
                    "Nombre: " + nombre + "\n" +
                    "Tipo de Animal: " + tipoAnimal + "\n" +
                    "Precio: " + precio + "\n" +
                    "Cantidad: " + cantidad + "\n" +
                    "Área: " + area+ "\n"+
                    "***************************\n";
        }
    }
    
    
    private static int numProductos = 0;
    public static Producto[] inventario = new Producto[100];
   
    
    //metodo para agregar un producto
    public static void agregarProducto() {
        for (int i=0; i < inventario.length; i++) {
            if (inventario[i].getNombre().equals("Nombre")) {
                
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto: ");
                String tipoAnimalString = JOptionPane.showInputDialog(null, "Ingrese el tipo de animal para el que está destinado el producto:\n" +
                                                                            "1. Perro\n" +
                                                                            "2. Gato\n" +
                                                                            "3. Roedor\n" +
                                                                            "4. Pájaro\n" +
                                                                            "5. Otro");
                TipoAnimal tipoAnimal = null;
                switch(tipoAnimalString) {
                    case "1":
                        tipoAnimal = TipoAnimal.PERRO;
                        break;
                    case "2":
                        tipoAnimal = TipoAnimal.GATO;
                        break;
                    case "3":
                        tipoAnimal = TipoAnimal.ROEDOR;
                        break;
                    case "4":
                        tipoAnimal = TipoAnimal.PAJARO;
                        break;
                    case "5":
                        tipoAnimal = TipoAnimal.OTRO;
                        break;
                }
                double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del producto: "));
                String cantidadString = JOptionPane.showInputDialog(null, "Ingrese la cantidad de producto o kilogramos: ");
                int cantidad = Integer.parseInt(cantidadString);
                String areaString = JOptionPane.showInputDialog(null, "Ingrese el área a la que pertenece el producto:  \n"+
                                                                        "Producto:\n" +
                                                                        "1. Alimentos\n" +
                                                                        "2. Artículos\n" +
                                                                        "3. Medicamentos\n" +
                                                                        "4. Granel");
                
                Area area = null;
                switch(areaString) {
                    case "1":
                        area = Area.ALIMENTOS;
                        break;
                    case "2":
                        area = Area.ARTICULOS;
                        break;
                    case "3":
                        area = Area.MEDICAMENTOS;
                        break;
                    case "4":
                        area = Area.GRANEL;
                    break;
                }
                String codigo = "";
                if (nombre.indexOf(' ') != -1) {
                    int inEspacio = nombre.indexOf(' ');
                    codigo += nombre.charAt(0) + nombre.substring(inEspacio+1, inEspacio+3) + "." + area.name().substring(0,3) + "." + (i+1);
                } else {
                    codigo += nombre.substring(0, 3) + "." + area.name().substring(0,3) + "." + (i+1);
                }
                
                inventario[i] = new Producto(codigo.toUpperCase(), nombre.toUpperCase(), tipoAnimal, precio, cantidad, area);
                JOptionPane.showMessageDialog(null, "Producto agregado exitosamente! ");
                i = inventario.length;
            } else if(!inventario[i].getNombre().equals("Nombre")) {
                JOptionPane.showMessageDialog(null, "No hay más espacio en el inventario.");
            }
        }   
    }
    
    /**
 * Método para modificar un producto existente en el inventario
 
    public void modificarProducto() {

        Producto producto = buscarProducto();

        if (producto != null) {
            // Se encontró el producto, se procede a modificar sus datos
            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto: ");
            String tipoAnimalString = JOptionPane.showInputDialog(null,"Ingrese el tipo de animal para el que está destinado el producto: \n" +
                    "1. Perro\n" +
                    "2. Gato\n" +
                    "3. Roedor\n" +
                    "4. Pájaro\n" +
                    "5. Otro");
            TipoAnimal tipoAnimal = null;
            switch(tipoAnimalString) {
                case "1":
                    tipoAnimal = TipoAnimal.PERRO;
                    break;
                case "2":
                    tipoAnimal = TipoAnimal.GATO;
                    break;
                case "3":
                    tipoAnimal = TipoAnimal.ROEDOR;
                    break;
                case "4":
                    tipoAnimal = TipoAnimal.PAJARO;
                    break;
                case "5":
                    tipoAnimal = TipoAnimal.OTRO;
                    break;
            }

            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio del producto: "));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto: "));

            // Se actualizan los datos del producto
            producto.setNombre(nombre);
            producto.setTipoAnimal(tipoAnimal);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);

            JOptionPane.showMessageDialog(null, "Producto modificado exitosamente. ");
        } else {
            JOptionPane.showMessageDialog(null, "El producto con el código  no fue encontrado. ");
        }
    }*/

    
     public Producto buscarProductoCodigo(String codigo) {
        Producto productoEnc = null;
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i].getCodigo().equals(codigo.toUpperCase())) {
                productoEnc = inventario[i];
                JOptionPane.showMessageDialog(null, inventario[i].toString());
            }
        }
        return productoEnc;
    }
    
     public Producto buscarProducto() {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del producto: ");
        Producto productoEnc = null;
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i].getCodigo().equals(codigo.toUpperCase())) {
                productoEnc = inventario[i];
                JOptionPane.showMessageDialog(null, inventario[i].toString());
            }
        }
        return null;
    }
    
    public static void mostrarInventario() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventario:\n");
        for(int i=0; i < inventario.length; i++) {
            if(inventario[i].getNombre().equals("Nombre")) {
                i = inventario.length;
            } else {
                Producto producto = inventario[i];
                sb.append(producto.toString());
                sb.append("\n\n");
            }
        }
        
        JOptionPane.showMessageDialog(null, sb.toString());
    }
    
    public void inicializarInventario() {
        for (int i=0; i < inventario.length; i++) {
            inventario[i] = new Producto("Codigo", "Nombre", null, 0, 0, null);
        }
        
        FileReader lectorArchivo;
        try {
            lectorArchivo = new FileReader("C:\\Users\\pc\\OneDrive\\Documents\\NetBeansProjects\\Proyecto petsmarket V1.0\\Proyecto_PetsMarket\\inventario.txt");
        } catch (FileNotFoundException err) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo\n" + err);
            return;
        }

        BufferedReader textoArchivo;
        textoArchivo = new BufferedReader(lectorArchivo);

        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i].getNombre().equals("Nombre")) {
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

                double precio = Double.parseDouble(Valores[3]);
                int cantidad = Integer.parseInt(Valores[4]);
                TipoAnimal animal = TipoAnimal.valueOf(Valores[2]);
                Area area = Area.valueOf(Valores[5]);
                
                inventario[i] = new Producto(Valores[0], Valores[1], animal, precio, cantidad, area);
            }
        }
    }
    
    public String listaAlimentos() {
        int counter = 1;
        String listaAlimentos = "Cantidad | Alimento | Precio\n";
        for(int i=0; i<inventario.length; i++) {
            if(inventario[i].getArea() == Area.ALIMENTOS) {
                listaAlimentos += "["+(i+1)+"] " + inventario[i].getCantidad()+" - "+inventario[i].getNombre()+" - C."+inventario[i].getPrecio()+"\n";
                counter += 1;
            }
        }
        return listaAlimentos;
    }
    
    public String listaArticulos() {
        int counter = 1;
        String listaArticulos = "Cantidad | Artículo | Precio\n";
        for(int i=0; i<inventario.length; i++) {
            if(inventario[i].getArea() == Area.ARTICULOS) {
                listaArticulos += "["+(i+1)+"] " + inventario[i].getCantidad()+" - "+inventario[i].getNombre()+" - C."+inventario[i].getPrecio()+"\n";
                counter += 1;
            }
        }
        return listaArticulos;
    }
    
    public String listaMedicamentos() {
        int counter = 1;
        String listaMedicamentos = "Cantidad | Medicamento | Precio\n";
        for(int i=0; i<inventario.length; i++) {
            if(inventario[i].getArea() == Area.MEDICAMENTOS && inventario[i].getCantidad() > 0) {
                listaMedicamentos += "["+counter+"] " + inventario[i].getCantidad()+" - "+inventario[i].getNombre()+" - C."+inventario[i].getPrecio()+"\n";
                counter += 1;
            }
        }
        return listaMedicamentos;
    }
    
    public String listaGranel() {
        int counter = 1;
        String listaGranel = "Cantidad (kg) | Tipo Granel | Precio\n";
        for(int i=0; i<inventario.length; i++) {
            if(inventario[i].getArea() == Area.GRANEL && inventario[i].getCantidad() > 0) {
                listaGranel += "["+counter+"] " + inventario[i].getCantidad()+" - "+inventario[i].getNombre()+" - C."+inventario[i].getPrecio()+"\n";
                counter += 1;
            }
        }
        return listaGranel;
    }
    
    public Producto retornarProducto(String area, int indice) {
        Producto producto = null;
        Area areaProducto = Area.valueOf(area);
        int counter = 1;
        for(int i=0; i<inventario.length; i++) {
            if(inventario[i].getArea() == areaProducto && i == indice) {
                producto = inventario[i];
            } else {
                counter += 1;
            }
        }
        if(counter == inventario.length) {
            JOptionPane.showMessageDialog(null, "No se encontró este producto en inventario.");
        }
        return producto;
    }
    
    public void MenuInventario() {
        boolean continuar = true;
        while (continuar) {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opción: \n"
                    + "1. Agregar producto\n"
                    + "2. Mostrar inventario\n"
                    + "3. Buscar producto\n"
                    + "4. Modificar producto\n"
                    + "5. Salir"));

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    mostrarInventario();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    //modificarProducto();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. ");
                    break;
            }
    }
}

    
}
                
        
   
        
