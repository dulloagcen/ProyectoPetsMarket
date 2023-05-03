
package proyecto_petsmarket;

import java.util.Arrays;
import javax.swing.JOptionPane;
import proyecto_petsmarket.Inventario.Producto;


public class Ventas {
    
    Cliente clienteVenta = new Cliente("Nombre", "Apellido", 0, "Email");
    Inventario miInventarioVenta = new Inventario();
    public static Carrito miCarrito[] = new Carrito[20];
    Producto miProducto = new Producto("codigo", "nombre", null, 0, 0, null);
    
    public void menuVentas() {
        inicializarCarrito();
        
        boolean flag = false;
        
        int idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Antes de proceder con la venta, por favor ingrese la cédula del cliente: "));
        if (clienteVenta.Consultar_Cliente(idCliente) == true) {
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor registrar este cliente para poder continuar con la venta");
            clienteVenta.Registrar_Cliente();
            flag = true;
        }
                
        while (flag) {
            int opcArea = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el area que desea comprar: "
                                                                        + "\n1. Alimentos"
                                                                        + "\n2. Artículos"
                                                                        + "\n3. Medicamentos"
                                                                        + "\n4. Granel"
                                                                        + "\n5. Regresar"));
            
            int otraCompra = 0;
            switch (opcArea) {
                case 1:
                    String listaAlimentos = miInventarioVenta.listaAlimentos();
                    int opcAlimentos = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el Producto que desea facturar:\n" + listaAlimentos));
                    facturarProducto("ALIMENTOS", opcAlimentos);
                    
                    otraCompra = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro producto más al carrito?");
                    if (otraCompra == 1){
                        flag = false;
                    }
                    break;
                case 2:
                    String listaArticulos = miInventarioVenta.listaArticulos();
                    int opcArticulo = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el Artículo que desea facturar:\n" + listaArticulos));
                    facturarProducto("ARTICULOS", opcArticulo);
                    
                    otraCompra = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro artículo más al carrito?");
                    if (otraCompra == 1){
                        flag = false;
                    }
                    break;
                case 3:
                    String listaMedicamentos = miInventarioVenta.listaMedicamentos();
                    int opcMedicamento = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el Medicamento que desea facturar:\n" + listaMedicamentos));
                    facturarProducto("MEDICAMENTOS", opcMedicamento);
                    
                    otraCompra = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro artículo más al carrito?");
                    if (otraCompra == 1){
                        flag = false;
                    }
                    break;
                case 4:
                    String listaGranel = miInventarioVenta.listaGranel();
                    int opcGranel = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el producto a granel que desea facturar:\n" + listaGranel));
                    facturarProducto("Productos a granel", opcGranel);
                    
                    otraCompra = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro artículo más al carrito?");
                    if (otraCompra == 1){
                        flag = false;
                    }
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Seleccione una de las opciones en el listado.");
            }
        }
        mostrarFactura(idCliente);
    }
    
    public void facturarProducto(String area, int producto) {
        Producto productoE = miInventarioVenta.retornarProducto(area, (producto-1));
        if (productoE.getCantidad() != 0) {
            int unidades = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántas unidades desea facturar?"));
            if(unidades <= productoE.getCantidad()) {
                agregarACarrito(productoE.getNombre(), unidades, productoE.getPrecio());
                productoE.setCantidad(productoE.getCantidad() - unidades);
                JOptionPane.showMessageDialog(null, "¡Producto agregado al carrito de compras!");
            } else {
                JOptionPane.showMessageDialog(null, "No hay suficientes unidades de " + productoE.getNombre() + " en inventario. Por favor ingrese menos unidades.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay " + productoE.getNombre() + " en inventario.");
        }
    }
    
    public void mostrarFactura(int cedula) {
        String factura = "";
        
        String carrito = "Productos Comprados:\n";
        for (int i=0; i < miCarrito.length; i++) {
            if(!miCarrito[i].getNombre().equals("Nombre")) {
                carrito += miCarrito[i].getCantidad() + " " + miCarrito[i].getNombre() + " " + miCarrito[i].getPrecio() + "\n";
                //i = miCarrito.length;
            } 
        }
        
        Cliente[] clientes = Cliente.getClientes();
        String infoCliente = "";
        for (int i=0; i < clientes.length; i++) {
            if (clientes[i].getCedula() == cedula) {
                infoCliente += "Nombre Completo: " + clientes[i].getNombre() + " " + clientes[i].getApellido()
                            + "\nCédula: " + clientes[i].getCedula()
                            + "\nEmail: " + clientes[i].getEmail() 
                            + "\n________________________\n";
                i = clientes.length;
            }
        }
        
        double precioTotal = 0;
        double precioIVA = 0;
        for(int i=0; i < miCarrito.length; i++) {
            precioTotal += miCarrito[i].getPrecio();
            precioIVA = (precioTotal * 0.13) + precioTotal;
        }
        
        factura += infoCliente + carrito;
        if(!miCarrito[0].getNombre().equals("Nombre")) {
            JOptionPane.showMessageDialog(null, "__________ FACTURA ELÉCTRONICA __________"
                                        + "\n¡Gracias por su compra!\n" + factura
                                        + "\nPrecio Total: C. " + precioTotal
                                        + "\nPrecio IVA: C. " + precioIVA);
        }
    }
    
    public void agregarACarrito(String nombre, int cantidad, double precio) {
        for (int i=0; i < miCarrito.length; i++) {
            if (miCarrito[i].getNombre().equals("Nombre")) {
                if (cantidad > 1) {
                    precio = precio * cantidad;
                } 
                miCarrito[i] = new Carrito(nombre, cantidad, precio);
                i = miCarrito.length;  
            } if(!miCarrito[miCarrito.length-1].getNombre().equals("Nombre")) {
                JOptionPane.showMessageDialog(null, "Carrito sin espacio adicional.");
            }
        }
    }
    
    public void inicializarCarrito() {
        for (int i=0; i < miCarrito.length; i++) {
            miCarrito[i] = new Carrito("Nombre", 0, 0);
        }
    }
    
}
