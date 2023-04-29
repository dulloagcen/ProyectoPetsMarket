
package proyecto_petsmarket;



import javax.swing.JOptionPane;

public class Inventario {
    
    //Alimentos
    public double Super_Perro = 35000;
    public double Dog_Chow = 42000;
    public double Hills= 60000;
    public double Pro_Plan= 68000;
    public double Royal_Canin = 75000;
    
    //Artículos
    public double Juguetes = 5000;
    public double Correas = 8000;
    public double Trits = 6000;
    public double Camas = 15000;
    public double Comederos = 10000;
    
    
    //Medicamentos
    public double Doloalivio = 12500;
    public double Aquazell = 3500;
    public double Credelio = 9500;
    public double Higado_Cat = 13000;
    public double Neocan = 3500;
    
    //Granel
    public double GSuper_Perro = 0;
    public double GDog_Chow = 0;
    public double GHills= 0;
    public double GPro_Plan= 0;
    public double GRoyal_Canin = 0;
    
    
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
        //String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del producto: ");
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
            codigo += nombre.charAt(0) + nombre.substring(inEspacio+1, inEspacio+3) + "." + area.name().substring(0,3) + "." + (numProductos+1);
        } else {
            codigo += nombre.substring(0, 3) + "." + area.name().substring(0,3) + "." + (numProductos+1);
        }
        Producto producto = new Producto(codigo.toUpperCase(), nombre.toUpperCase(), tipoAnimal, precio, cantidad, area);
        inventario[numProductos] = producto;
        numProductos++;
        JOptionPane.showMessageDialog(null, "Producto agregado exitosamente! ");
    };
    
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

    
     public  Producto buscarProductoCodigo() {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del producto: ");
        
        for (int i = 0; i < numProductos; i++) {
            if (inventario[i].getCodigo().equals(codigo)) {
                JOptionPane.showMessageDialog(null, inventario[i].toString() );
            }
        }
        return null;
    }
    
    public Producto buscarProducto(String codigo) {
        //String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del producto: ");
        Producto productoEnc = null;
        for (int i = 0; i < numProductos; i++) {
            if (inventario[i].getCodigo().equals(codigo)) {
                productoEnc = inventario[i];
            }
        }
        return productoEnc;
    }


   /* public void mostrarInventario() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventario:\n");
        for (Producto producto : inventario) {
            sb.append(producto.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }*/
    
    public static void mostrarInventario() {
        if (numProductos == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos en el inventario. ");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Inventario:\n");
        for (int i = 0; i < numProductos; i++) {
            Producto producto = inventario[i];
            sb.append(producto.toString());
            sb.append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
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
                buscarProductoCodigo();
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
                
        
   
        
