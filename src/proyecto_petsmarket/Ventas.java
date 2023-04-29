
package proyecto_petsmarket;

import javax.swing.JOptionPane;
import proyecto_petsmarket.Inventario.Producto;


public class Ventas {
    
    Cliente clienteVenta = new Cliente("Nombre", "Apellido", 0, "Email");
    Inventario miInventarioVenta = new Inventario();
    public static Carrito miCarrito[] = new Carrito[50];
    Producto miProducto = new Producto("codigo", "nombre", null, 0, 0, null);
    
    public void menuVentas() {
        inicializarCarrito();
        
        boolean flag = false;
        
        int idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Antes de proceder con la venta, por favor ingrese la cédula del cliente"));
        if (clienteVenta.Consultar_Cliente(idCliente) == true) {
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor registrar este cliente nuevo para poder continuar con la venta");
            clienteVenta.Registrar_Cliente();
            flag = true;
        }
                
        while (flag) {
            
            int opcArea = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione un área para ver los productos respectivos"
                                                                        + "\n[1] Alimentos"
                                                                        + "\n[2] Artículos"
                                                                        + "\n[3] Medicamentos"
                                                                        + "\n[4] Granel"
                                                                        + "\n[5] Regresar"));
            switch (opcArea) {
                case 1:
                    int opcAlimentos = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el Producto que desea facturar:"
                                                                                      + "\n[1] Super Perro ______ C. 35,000"
                                                                                      + "\n[2] Dog Chow ______ C. 42,000"
                                                                                      + "\n[3] Hills ______ C. 60,000"
                                                                                      + "\n[4] Pro Plan ______ C. 68,000"
                                                                                      + "\n[5] Royal Canin ______ C. 75,000"
                                                                                      + "\n[6] Regresar."));
                    facturarProducto(opcAlimentos);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Seleccione una de las opciones en el listado.");
            }
            
            int otraCompra = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro producto más al carrito?");
            if (otraCompra == 1){
                flag = false;
            }
        }
        
        mostrarFactura(idCliente);
    }
    
    public void facturarProducto(int producto) {
        
        switch(producto) {
            case 1:
                //miInventarioVenta.buscarProducto();
                break;
            case 2:
                Producto productoE = miInventarioVenta.buscarProducto("DCH.ALI.1");
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
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }
        
    }
    
    public void mostrarFactura(int cedula) {
        String factura = "";
        
        String carrito = "";
        for (int i=0; i < miCarrito.length; i++) {
            if(!miCarrito[i].getNombre().equals("Nombre")) {
                carrito += "Productos Comprados:\n" + miCarrito[i].getCantidad() + " " + miCarrito[i].getNombre() + " " + miCarrito[i].getPrecio() + "\n";
                i = miCarrito.length;
            } else {
                JOptionPane.showMessageDialog(null, "Sin espacio adicional en el carrito.");
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
            } else {
                infoCliente += "Sin información de cliente";
            }
        }
        
        double precioTotal = 0;
        double precioIVA = 0;
        for(int i=0; i < miCarrito.length; i++) {
            precioTotal += miCarrito[i].getPrecio();
            precioIVA = (precioTotal * 0.13) + precioTotal;
        }
        
        factura += infoCliente + carrito;
        JOptionPane.showMessageDialog(null, "__________ FACTURA ELÉCTRONICA __________"
                                        + "\n¡Gracias por su compra!\n" + factura
                                        + "\nPrecio Total: C. " + precioTotal
                                        + "\nPrecio IVA: C. " + precioIVA);
    }
    
    public void agregarACarrito(String nombre, int cantidad, double precio) {
        for (int i=0; i < miCarrito.length; i++) {
            if (miCarrito[i].getNombre().equals("Nombre")) {
                if (cantidad > 1) {
                    precio = precio * cantidad;
                } 
                miCarrito[i] = new Carrito(nombre, cantidad, precio);
                i = miCarrito.length;
                
            } else {
                JOptionPane.showMessageDialog(null, "Ya no tiene más espacio en su carrito.");
            }
        }
    }
    
    public void inicializarCarrito() {
        for (int i=0; i < miCarrito.length; i++) {
            miCarrito[i] = new Carrito("Nombre", 0, 0);
        }
    }
    
}
