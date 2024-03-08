/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Configuracion;
import ar.com.ventas.entities.Pedido;
import ar.com.ventas.entities.Producto;
import ar.com.ventas.entities.RenglonPedido;
import ar.com.ventas.entities.Usuario;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.ConfiguracionService;
import ar.com.ventas.services.PedidoService;
import ar.com.ventas.services.ProductoService;
import ar.com.ventas.services.UsuarioService;
import ar.com.ventas.util.Constantes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class PedidoFrame extends javax.swing.JFrame {

    public List<RenglonPedido> renglonPedido = new ArrayList<RenglonPedido>();
    private String textoPedidoPapel;
    private String fechaPedidoPapel;
    private String clientePedidoPapel;
    private String codigoClientePedidoPapel;
    private String direccionPedidoPapel;
    private String cuitPedidoPapel;
    //private String condicionVentaPedidoPapel;
    private Double dto = 0.0;
    private String vencimientoPedidoPapel;
    private String inscripcionClientePedidoPapel;
    private String nombresColumnaPedidoPapel;
    private String[] renglones = null;
    private String texto1PedidoPapel;
    private String texto2PedidoPapel;
    private String texto3PedidoPapel;
    private String totalDeudaPedidoPapel;
    private String lineaTotalesPedidoPapel;
    private String totalPagarPedidoPapel;
    private String importeTotalPedidoPapel;
    private String cantidadesPedidoPapel;
    private Date fecha;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Cliente clientePedido = null;
    private Producto producto = null;
    private DefaultTableModel tabla = null;
    private Double totalPedido = 0.00;
    private Double totalImpuesto = 0.00;
    private Double totalIva = 0.00;
    private Double totalGravado = 0.00;
    //private Double totalGravadoCigarrillos = 0.00;
    private Double totalNoGravado = 0.0;
    private Double gravado = 0.00;
    private Double noGravado = 0.00;
    private Double iva = 0.00;
    private Float porcentualIva;
    private Double impuesto = null;
    private Double totalLinea = 0.00;
    private Boolean tieneDto = false;
    //private Float descuentoGlobal = 0F;
    private Cliente clienteSeleccionado;
    private Producto productoSeleccionado;
    private Float cantidad;
    private Integer categoriaIva = 1;
    private DecimalFormat df = new DecimalFormat("#0.00");
    private DecimalFormat df1 = new DecimalFormat("#0");
    private DecimalFormat df2 = new DecimalFormat("#0.0");
    private Double saldoCliente = 0.00;
    private String filtro = "";
    private String letraPedido;
    private Integer numeroPedido;
    private Configuracion config = null;
    private Double precioFinal = 0.0;
    private Integer nro = 0;
    private Integer maxNro = 40;
    private Integer cantidadAtadosMassalin;
    private Integer cantidadAtadosNobleza;
    public Boolean encontrado;
    private Double totalGravadoCigarrillos = 0.00;
    private Float descuento = 0F;
    private Double totalDescuento = 0.0;
    private Double gravadoVarios = 0.00;
    private Double totalGravadoCompleto = 0.0;
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form PedidoFrame
     */
    public PedidoFrame() {
        getContentPane().setBackground(new java.awt.Color(192, 192, 192));
        initComponents();
        this.setLocationRelativeTo(null);
//        this.order_name = order_name;
//        this.order_num = order_num;
        limpiarCampos();
        bloquearCampos();
//      levanto el modelo creado del frame
        tabla = (DefaultTableModel) tablaPedido.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedido = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        codigoProductoTxt = new javax.swing.JTextField();
        codigoBarrasTxt = new javax.swing.JTextField();
        cantidadTxt = new javax.swing.JTextField();
        codigoTxt = new javax.swing.JTextField();
        razonSocialTxt = new javax.swing.JTextField();
        fechaTxt = new javax.swing.JTextField();
        ivaTxt = new javax.swing.JTextField();
        saldoTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        agregarBtn = new javax.swing.JButton();
        totalTxt = new javax.swing.JTextField();
        terminarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        buscarBtn = new javax.swing.JButton();
        incorporarProductoBtn = new javax.swing.JButton();
        eliminarItemBtn = new javax.swing.JButton();
        buscarClienteXNombre = new javax.swing.JButton();
        buscarProductoXNombre = new javax.swing.JButton();
        descuentoGlobalLbl = new javax.swing.JLabel();
        descuentoGlobalTxt = new javax.swing.JTextField();
        descuentoLineaLbl = new javax.swing.JLabel();
        descuentoLineaTxt = new javax.swing.JTextField();
        comboClientes = new javax.swing.JComboBox();
        comboProductos = new javax.swing.JComboBox();
        nombreClienteABuscarTxt = new javax.swing.JTextField();
        nombreProductoABuscarTxt = new javax.swing.JTextField();
        descuentoBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        descuentoVolumenTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        totalDeudaTxt = new javax.swing.JTextField();
        texto1PiePedidoTxt = new javax.swing.JTextField();
        texto2PiePedidoTxt = new javax.swing.JTextField();
        volverBtn = new javax.swing.JButton();
        nombreProductoConsultaTxt = new javax.swing.JTextField();
        precioProductoConsultaTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cantidadAtadosMassalinTxt = new javax.swing.JTextField();
        cantidadAtadosNoblezaTxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cantidadItemsTxt = new javax.swing.JTextField();
        leerCantidadBtn = new javax.swing.JButton();
        nuevaCantidadTxt = new javax.swing.JTextField();
        grabarCantidadBtn = new javax.swing.JButton();
        leerPrecioBtn = new javax.swing.JButton();
        nuevoPrecioTxt = new javax.swing.JTextField();
        grabarPrecioBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        totalMassalinTxt = new javax.swing.JTextField();
        totalNoblezaTxt = new javax.swing.JTextField();
        cuitTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("A Y M . PEDIDO");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tablaPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cant", "Detalle", "P.Unit", "Gravado", "Impuesto", "IVA", "dto.", "SubTotal", "Sug."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPedido);
        if (tablaPedido.getColumnModel().getColumnCount() > 0) {
            tablaPedido.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaPedido.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablaPedido.getColumnModel().getColumn(2).setPreferredWidth(330);
            tablaPedido.getColumnModel().getColumn(3).setPreferredWidth(20);
            tablaPedido.getColumnModel().getColumn(4).setPreferredWidth(25);
            tablaPedido.getColumnModel().getColumn(5).setPreferredWidth(25);
            tablaPedido.getColumnModel().getColumn(6).setPreferredWidth(25);
            tablaPedido.getColumnModel().getColumn(7).setPreferredWidth(20);
            tablaPedido.getColumnModel().getColumn(8).setPreferredWidth(30);
            tablaPedido.getColumnModel().getColumn(9).setPreferredWidth(20);
        }

        jLabel1.setText("Cliente:");

        jLabel2.setText("Iva:");

        jLabel3.setText("Saldo:");

        jLabel4.setText("Fecha:");

        jLabel5.setText("Código:");

        jLabel6.setText("C.Barras:");

        jLabel7.setText("Cantidad:");

        codigoProductoTxt.setText("CODIGO P");
        codigoProductoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoProductoTxtKeyPressed(evt);
            }
        });

        codigoBarrasTxt.setText("COD BARRAS");
        codigoBarrasTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoBarrasTxtKeyPressed(evt);
            }
        });

        cantidadTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadTxt.setText("CANT");
        cantidadTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cantidadTxtKeyPressed(evt);
            }
        });

        codigoTxt.setText("CODIGO");
        codigoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoTxtKeyPressed(evt);
            }
        });

        razonSocialTxt.setText("RAZON SOCIAL");

        fechaTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaTxt.setText("FECHA");

        ivaTxt.setText("IVA");

        saldoTxt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saldoTxt.setForeground(new java.awt.Color(51, 0, 204));
        saldoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saldoTxt.setText("SALDO");

        jLabel8.setText("TOTAL PEDIDO:");

        agregarBtn.setText("Agregar otro Item");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });
        agregarBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                agregarBtnKeyPressed(evt);
            }
        });

        totalTxt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalTxt.setForeground(new java.awt.Color(255, 0, 0));
        totalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTxt.setText("TOTAL");

        terminarBtn.setText("Terminar Pedido");
        terminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar Pedido");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        incorporarProductoBtn.setText("Incorporar a Pedido");
        incorporarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incorporarProductoBtnActionPerformed(evt);
            }
        });

        eliminarItemBtn.setText("Eliminar Item seleccionado");
        eliminarItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarItemBtnActionPerformed(evt);
            }
        });

        buscarClienteXNombre.setText("Buscar x nombre");
        buscarClienteXNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteXNombreActionPerformed(evt);
            }
        });

        buscarProductoXNombre.setText("Buscar x nombre");
        buscarProductoXNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProductoXNombreActionPerformed(evt);
            }
        });

        descuentoGlobalLbl.setText("Desc.:");

        descuentoGlobalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentoGlobalTxt.setText("DESC");

        descuentoLineaLbl.setText("Dscto:");

        descuentoLineaTxt.setText("DESCUENTO");

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClientesActionPerformed(evt);
            }
        });
        comboClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboClientesKeyPressed(evt);
            }
        });

        comboProductos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductosActionPerformed(evt);
            }
        });
        comboProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboProductosKeyPressed(evt);
            }
        });

        nombreClienteABuscarTxt.setText("NOMBRE CLIENTE A BUSCAR");
        nombreClienteABuscarTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreClienteABuscarTxtKeyPressed(evt);
            }
        });

        nombreProductoABuscarTxt.setText("NOMBRE PRODUCTO A BUSCAR");
        nombreProductoABuscarTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreProductoABuscarTxtKeyPressed(evt);
            }
        });

        descuentoBtn.setText("Dto");

        jLabel12.setText("Descuento");

        descuentoVolumenTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentoVolumenTxt.setText("DESCUENTO");

        jLabel13.setText("Total Deuda:");

        totalDeudaTxt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        totalDeudaTxt.setForeground(new java.awt.Color(51, 0, 204));
        totalDeudaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalDeudaTxt.setText("TOTAL DEUDA");

        texto1PiePedidoTxt.setText("TEXTO 1 PIE PEDIDO");

        texto2PiePedidoTxt.setText("TEXTO 2 PIE PEDIDO");

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        nombreProductoConsultaTxt.setText("NOMBRE PRODUCTO CONSULTA");

        precioProductoConsultaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        precioProductoConsultaTxt.setText("PRECIO PROD CONSU");

        jLabel15.setText("Massalin:");

        jLabel17.setText("Nobleza:");

        cantidadAtadosMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadAtadosMassalinTxt.setText("Cant Mass");

        cantidadAtadosNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadAtadosNoblezaTxt.setText("Cant Nobl");

        jLabel18.setText("Cant. Items:");

        cantidadItemsTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadItemsTxt.setText("Cantidad Items");

        leerCantidadBtn.setText("Leer Cantidad");
        leerCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerCantidadBtnActionPerformed(evt);
            }
        });

        nuevaCantidadTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevaCantidadTxt.setText("CANTIDAD");
        nuevaCantidadTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevaCantidadTxtKeyPressed(evt);
            }
        });

        grabarCantidadBtn.setText("Grabar Cant");
        grabarCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarCantidadBtnActionPerformed(evt);
            }
        });

        leerPrecioBtn.setText("Leer Precio");
        leerPrecioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerPrecioBtnActionPerformed(evt);
            }
        });

        nuevoPrecioTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevoPrecioTxt.setText("NUE PREC");
        nuevoPrecioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevoPrecioTxtKeyPressed(evt);
            }
        });

        grabarPrecioBtn.setText("Grab.Prec.");
        grabarPrecioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarPrecioBtnActionPerformed(evt);
            }
        });

        jLabel9.setText("Importe:");

        jLabel10.setText("Importe:");

        totalMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalMassalinTxt.setText("IMP.MASS.");

        totalNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalNoblezaTxt.setText("IMP.NOBL.");

        cuitTxt.setText("CUIT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(texto1PiePedidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(descuentoLineaLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(descuentoLineaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(texto2PiePedidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cancelarBtn)
                            .addComponent(terminarBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalDeudaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel18))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarProductoXNombre)
                        .addGap(178, 178, 178)
                        .addComponent(descuentoBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(codigoProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nombreProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(precioProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)
                                                .addComponent(leerCantidadBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(grabarCantidadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(incorporarProductoBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agregarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eliminarItemBtn)
                                .addGap(18, 18, 18)
                                .addComponent(leerPrecioBtn)
                                .addGap(18, 18, 18)
                                .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(grabarPrecioBtn)))))
                .addGap(18, 18, 18)
                .addComponent(descuentoVolumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(razonSocialTxt)
                                .addGap(18, 18, 18)
                                .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(descuentoGlobalLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(volverBtn))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 991, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saldoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(nombreClienteABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarClienteXNombre)
                        .addGap(18, 18, 18)
                        .addComponent(comboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(94, 94, 94))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(razonSocialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn)
                    .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoGlobalLbl)
                    .addComponent(volverBtn)
                    .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saldoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarClienteXNombre)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreClienteABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarProductoXNombre)
                    .addComponent(jLabel6)
                    .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoBtn)
                    .addComponent(jLabel12)
                    .addComponent(descuentoVolumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descuentoLineaLbl)
                    .addComponent(descuentoLineaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leerCantidadBtn)
                    .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarCantidadBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarBtn)
                    .addComponent(eliminarItemBtn)
                    .addComponent(incorporarProductoBtn)
                    .addComponent(leerPrecioBtn)
                    .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarPrecioBtn)
                    .addComponent(totalDeudaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto1PiePedidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto2PiePedidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(terminarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(totalMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        cancelarBtn.setEnabled(true);
        buscar();
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar el Pedido?", "Atención", JOptionPane.YES_NO_OPTION);
        // 0 = si; 1 = no
        if (escape == 0) {
            abandonarPedido();

        } else {
            nuevaCantidadTxt.setText("");
            agregarBtn.setEnabled(true);
            codigoBarrasTxt.setEnabled(false);
            codigoProductoTxt.setEnabled(false);
            cantidadTxt.setEnabled(false);
            nombreProductoABuscarTxt.setEnabled(false);
            nombreProductoConsultaTxt.setEnabled(false);
            comboProductos.setEnabled(false);
            incorporarProductoBtn.setEnabled(false);
            buscarProductoXNombre.setEnabled(false);
            eliminarItemBtn.setEnabled(true);
            agregarBtn.requestFocus();
            grabarCantidadBtn.setEnabled(false);
            nuevaCantidadTxt.setEnabled(true);
            leerPrecioBtn.setEnabled(true);
            nuevoPrecioTxt.setEnabled(true);
            nuevoPrecioTxt.setText("");
            grabarPrecioBtn.setEnabled(false);
            leerCantidadBtn.setEnabled(true);
            terminarBtn.setEnabled(true);
            tablaPedido.setEnabled(true);
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        eliminarItemBtn.setEnabled(false);
        texto1PiePedidoTxt.setEnabled(false);
        texto2PiePedidoTxt.setEnabled(false);
        codigoBarrasTxt.setText("");
        leerPrecioBtn.setEnabled(false);
        leerCantidadBtn.setEnabled(false);
        agregarProducto();
    }//GEN-LAST:event_agregarBtnActionPerformed

    private void incorporarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incorporarProductoBtnActionPerformed
        if (!cantidadTxt.getText().isEmpty()) {
            if (!codigoBarrasTxt.getText().isEmpty()) {
                buscarProducto();
            } else {
                if (!codigoProductoTxt.getText().isEmpty()) {
                    buscarProducto();
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código Producto");
                    codigoProductoTxt.requestFocus();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una Cantidad");
            cantidadTxt.requestFocus();
        }
    }//GEN-LAST:event_incorporarProductoBtnActionPerformed

    private void buscarClienteXNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteXNombreActionPerformed
        comboClientes.removeAllItems();
        comboClientes.addItem("");
        llenarComboClientes();
        comboClientes.addFocusListener(null);
        comboClientes.showPopup();
    }//GEN-LAST:event_buscarClienteXNombreActionPerformed

    private void comboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClientesActionPerformed
        if (evt.getModifiers() == 16) {
            if (comboClientes.getSelectedIndex() > 0) {
                nombreClienteABuscarTxt.setText("");
                try {
                    Integer seleccion = comboClientes.getSelectedIndex();
                    clienteSeleccionado = new ClienteService().getClientesByFiltro(filtro).get(seleccion - 1);
                    codigoTxt.setText(clienteSeleccionado.getCodigo());
                    buscar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar cliente");
                    Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboClientesActionPerformed

    private void buscarProductoXNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProductoXNombreActionPerformed
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        llenarComboProductos();
        comboProductos.addFocusListener(null);
        comboProductos.showPopup();
    }//GEN-LAST:event_buscarProductoXNombreActionPerformed

    private void comboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductosActionPerformed
        if (evt.getModifiers() == 16) {
            if (comboProductos.getSelectedIndex() > 0) {
                nombreProductoABuscarTxt.setText("");
                nombreProductoABuscarTxt.setEnabled(false);
                buscarProductoXNombre.setEnabled(false);
                try {
                    Integer seleccion = comboProductos.getSelectedIndex();
                    productoSeleccionado = new ProductoService().getProductosByFiltroSin90SinDepo(filtro).get(seleccion - 1);
                    codigoProductoTxt.setText(String.valueOf(productoSeleccionado.getCodigo()));
                    consultarProducto();
                    cantidadTxt.requestFocus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar producto");
                    Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboProductosActionPerformed

    private void terminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Quiere ingresar un texto antes de Imprimir?",
                "Texto en el pie de Pedido",
                JOptionPane.YES_NO_OPTION);
        if (escape == 0) {
            texto1PiePedidoTxt.setEnabled(true);
            texto2PiePedidoTxt.setEnabled(true);
            texto1PiePedidoTxt.requestFocus();
        } else {
            terminarPedido();
        }
    }//GEN-LAST:event_terminarBtnActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

    private boolean isNumeric(KeyEvent evt) {
        String cod = String.valueOf(evt.getKeyChar());
        try {
            Integer.parseInt(cod);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private void codigoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoTxt.getText().isEmpty()) {
                cancelarBtn.setEnabled(true);
                buscar();
            } else {
                nombreClienteABuscarTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                volver();
            } else {
                if (evt.getKeyCode() != 8 // back space
                        && evt.getKeyCode() != 37 //flecha izquierda
                        && evt.getKeyCode() != 39 //flecha derecha
                        && evt.getKeyCode() != 17 //Ctrl
                        && evt.getKeyCode() != 16 //Mayuscula
                        && evt.getKeyCode() != 38 //flecha arriba
                        && evt.getKeyCode() != 40 //flecha abajo
                        && evt.getKeyCode() != 67 //C
                        && evt.getKeyCode() != 20 //traba mayusculas
                        && evt.getKeyCode() != 27 //Escape
                        && evt.getKeyCode() != 86 //V
                        && evt.getKeyCode() != 36 //Inicio
                        && evt.getKeyCode() != 35 //fin
                        && evt.getKeyCode() != 155 //Insert
                        // && evt.getKeyCode() != 110  // punto decimal
                        //&& evt.getKeyCode() != 45 // Menos
                        && evt.getKeyCode() != 127) { // Suprimir
                    if (!isNumeric(evt)) {
                        JOptionPane.showMessageDialog(this, "Solo números");
                        codigoTxt.setText("");
                        codigoTxt.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_codigoTxtKeyPressed

    private void codigoProductoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoProductoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoProductoTxt.getText().isEmpty()) {
                consultarProducto();
                //incorporarProductoBtn.setEnabled(false);
            } else {
                codigoBarrasTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de abandonar la carga de pedido?",
                        "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape != 1) {
                    abandonarPedido();
                } else {
                    codigoProductoTxt.setText("");
                    codigoProductoTxt.requestFocus();
                }
            } else {
                if (evt.getKeyCode() != 8 // back space
                        && evt.getKeyCode() != 37 //flecha izquierda
                        && evt.getKeyCode() != 39 //flecha derecha
                        && evt.getKeyCode() != 17 //Ctrl
                        && evt.getKeyCode() != 16 //Mayuscula
                        && evt.getKeyCode() != 38 //flecha arriba
                        && evt.getKeyCode() != 40 //flecha abajo
                        && evt.getKeyCode() != 67 //C
                        && evt.getKeyCode() != 20 //traba mayusculas
                        && evt.getKeyCode() != 27 //Escape
                        && evt.getKeyCode() != 86 //V
                        && evt.getKeyCode() != 36 //Inicio
                        && evt.getKeyCode() != 35 //fin
                        && evt.getKeyCode() != 155 //Insert
                        // && evt.getKeyCode() != 110  // punto decimal
                        //&& evt.getKeyCode() != 45 // Menos
                        && evt.getKeyCode() != 127) { // Suprimir
                    if (!isNumeric(evt)) {
                        JOptionPane.showMessageDialog(this, "Solo números");
                        codigoProductoTxt.setText("");
                        codigoProductoTxt.requestFocus();

                    }
                }
            }
        }
    }//GEN-LAST:event_codigoProductoTxtKeyPressed

    private void cantidadTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!cantidadTxt.getText().isEmpty()) {
                if (!codigoBarrasTxt.getText().isEmpty()) {
                    buscarProducto();
                    if (nro > 0) {
                        terminarBtn.setEnabled(true);
                    } else {
                        terminarBtn.setEnabled(false);
                    }
                    leerPrecioBtn.setEnabled(true);
                    leerCantidadBtn.setEnabled(true);
                    agregarBtn.requestFocus();
                } else {
                    if (!codigoProductoTxt.getText().isEmpty()) {
                        buscarProducto();
                        if (nro > 0) {
                            terminarBtn.setEnabled(true);
                        } else {
                            terminarBtn.setEnabled(false);
                        }
                        agregarBtn.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código Producto");
                        codigoProductoTxt.requestFocus();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese una Cantidad");
                cantidadTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                codigoBarrasTxt.setText("");
                codigoProductoTxt.setText("");
                nombreProductoABuscarTxt.setText("");
                nombreProductoABuscarTxt.setEnabled(true);
                comboProductos.removeAllItems();
                comboProductos.addItem("");
                nombreProductoABuscarTxt.requestFocus();
            } else {
                if (evt.getKeyCode() != 8 // back space
                        && evt.getKeyCode() != 37 //flecha izquierda
                        && evt.getKeyCode() != 39 //flecha derecha
                        && evt.getKeyCode() != 17 //Ctrl
                        && evt.getKeyCode() != 16 //Mayuscula
                        && evt.getKeyCode() != 38 //flecha arriba
                        && evt.getKeyCode() != 40 //flecha abajo
                        && evt.getKeyCode() != 67 //C
                        && evt.getKeyCode() != 20 //traba mayusculas
                        && evt.getKeyCode() != 27 //Escape
                        && evt.getKeyCode() != 86 //V
                        && evt.getKeyCode() != 36 //Inicio
                        && evt.getKeyCode() != 35 //fin
                        && evt.getKeyCode() != 155 //Insert
                        //&& evt.getKeyCode() != 110  // punto decimal
                        //&& evt.getKeyCode() != 45 // Menos
                        && evt.getKeyCode() != 127) { // Suprimir
                    if (!isNumeric(evt)) {
                        JOptionPane.showMessageDialog(this, "Solo números");
                        cantidadTxt.setText("");
                        cantidadTxt.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_cantidadTxtKeyPressed

    private void eliminarItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarItemBtnActionPerformed
        int selectRow = tablaPedido.getSelectedRow();
        if (selectRow != -1) {
            int escape = JOptionPane.showConfirmDialog(null,
                    "Esta seguro de Eliminar Item?",
                    "Atencion", JOptionPane.YES_NO_OPTION);
            // 0 = si; 1 = no
            if (escape == 0) {
                tabla.removeRow(selectRow);
                renglonPedido.remove(selectRow);
                calcularTotales();
                if (nro > 0) {
                    terminarBtn.setEnabled(true);
                } else {
                    terminarBtn.setEnabled(false);
                }
                agregarBtn.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto para eliminar");
        }
    }//GEN-LAST:event_eliminarItemBtnActionPerformed

    private void agregarBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agregarBtnKeyPressed
        eliminarItemBtn.setEnabled(false);
        texto1PiePedidoTxt.setEnabled(false);
        texto2PiePedidoTxt.setEnabled(false);
        codigoBarrasTxt.setText("");
        leerPrecioBtn.setEnabled(false);
        agregarProducto();
    }//GEN-LAST:event_agregarBtnKeyPressed

    private void nombreClienteABuscarTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreClienteABuscarTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (nombreClienteABuscarTxt.getText().isEmpty()) {
                codigoTxt.requestFocus();
                return;
            }
            comboClientes.removeAllItems();
            comboClientes.addItem("");
            llenarComboClientes();
            comboClientes.requestFocus();
            comboClientes.addFocusListener(null);
            comboClientes.showPopup();
        }
    }//GEN-LAST:event_nombreClienteABuscarTxtKeyPressed

    private void nombreProductoABuscarTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreProductoABuscarTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!nombreProductoABuscarTxt.getText().isEmpty()) {
                comboProductos.removeAllItems();
                comboProductos.addItem("");
                llenarComboProductos();
                DefaultComboBoxModel mdl = (DefaultComboBoxModel) comboProductos.getModel();
                int rows = mdl.getSize();
                if (rows < 2) {
                    nombreProductoABuscarTxt.setText("");
                    nombreProductoABuscarTxt.requestFocus();
                    return;
                }
                comboProductos.requestFocus();
                comboProductos.addFocusListener(null);
                comboProductos.showPopup();
            } else {
                codigoProductoTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_nombreProductoABuscarTxtKeyPressed

    private void codigoBarrasTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoBarrasTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoBarrasTxt.getText().isEmpty()) {
                nombreProductoConsultaTxt.setText(" ");
                consultarProductoBarras();
                if (nombreProductoConsultaTxt.getText().equals(" ")) {
                    codigoBarrasTxt.setText("");
                    codigoBarrasTxt.requestFocus();
                    return;
                }
                incorporarProductoBtn.setEnabled(true);
                cantidadTxt.requestFocus();
            } else {
                nombreProductoABuscarTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de abandonar el Pedido?",
                        "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape == 0) {
                    abandonarPedido();
                } else {
                    codigoProductoTxt.setText("");
                    codigoBarrasTxt.setText("");
                    agregarBtn.requestFocus();

                    nuevaCantidadTxt.setText("");
                    agregarBtn.setEnabled(true);
                    codigoBarrasTxt.setEnabled(false);
                    codigoProductoTxt.setEnabled(false);
                    cantidadTxt.setEnabled(false);
                    nombreProductoABuscarTxt.setEnabled(false);
                    nombreProductoConsultaTxt.setEnabled(false);
                    comboProductos.setEnabled(false);
                    incorporarProductoBtn.setEnabled(false);
                    buscarProductoXNombre.setEnabled(false);
                    eliminarItemBtn.setEnabled(true);
                    agregarBtn.requestFocus();
                    grabarCantidadBtn.setEnabled(false);
                    nuevaCantidadTxt.setEnabled(true);
                    leerPrecioBtn.setEnabled(true);
                    nuevoPrecioTxt.setEnabled(true);
                    nuevoPrecioTxt.setText("");
                    grabarPrecioBtn.setEnabled(false);
                    leerCantidadBtn.setEnabled(true);
                    terminarBtn.setEnabled(true);
                    tablaPedido.setEnabled(true);

                }
            } else {
                if (evt.getKeyCode() != 8 // back space
                        && evt.getKeyCode() != 37 //flecha izquierda
                        && evt.getKeyCode() != 39 //flecha derecha
                        && evt.getKeyCode() != 17 //Ctrl
                        && evt.getKeyCode() != 16 //Mayuscula
                        && evt.getKeyCode() != 38 //flecha arriba
                        && evt.getKeyCode() != 40 //flecha abajo
                        && evt.getKeyCode() != 67 //C
                        && evt.getKeyCode() != 20 //traba mayusculas
                        && evt.getKeyCode() != 27 //Escape
                        && evt.getKeyCode() != 86 //V
                        && evt.getKeyCode() != 36 //Inicio
                        && evt.getKeyCode() != 35 //fin
                        && evt.getKeyCode() != 155 //Insert
                        // && evt.getKeyCode() != 110  // punto decimal
                        //&& evt.getKeyCode() != 45 // Menos
                        && evt.getKeyCode() != 127) { // Suprimir
                    if (!isNumeric(evt)) {
                        JOptionPane.showMessageDialog(this, "Solo números");
                        codigoBarrasTxt.setText("");
                        codigoBarrasTxt.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_codigoBarrasTxtKeyPressed

    private void leerCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerCantidadBtnActionPerformed

        int lin = tablaPedido.getSelectedRow();
        if (lin > -1) {
            RenglonPedido rf = renglonPedido.get(lin);
            nuevaCantidadTxt.setText(String.valueOf(rf.getCantidad().intValue()));
            leerCantidadBtn.setEnabled(false);
            grabarCantidadBtn.setEnabled(true);
            leerPrecioBtn.setEnabled(false);
            nuevoPrecioTxt.setEnabled(false);
            eliminarItemBtn.setEnabled(false);
            terminarBtn.setEnabled(false);
            agregarBtn.setEnabled(false);
            tablaPedido.setEnabled(false);
        }

    }//GEN-LAST:event_leerCantidadBtnActionPerformed

    private void grabarCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarCantidadBtnActionPerformed
        int lin = tablaPedido.getSelectedRow();
        if (lin > -1) {
            if (!nuevaCantidadTxt.getText().isEmpty()) {
                leerCantidadBtn.setEnabled(true);
                nuevoPrecioTxt.setEnabled(true);
                grabarCantidadBtn.setEnabled(false);
                leerPrecioBtn.setEnabled(true);
                tablaPedido.setEnabled(true);
                agregarBtn.setEnabled(true);
                eliminarItemBtn.setEnabled(true);
                terminarBtn.setEnabled(true);
                RenglonPedido rf = renglonPedido.get(lin);
                Float cantidadAnterior = rf.getCantidad();
                Float cant = Float.valueOf(nuevaCantidadTxt.getText());
                if (!(cant > 0.0)) {
                    JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                    return;
                }
                rf.setCantidad(cant);
                Double precioUnitario = rf.getGravado() / cantidadAnterior;
                Double impuestoUnitario = rf.getImpuesto() / cantidadAnterior;
                renglonPedido.set(lin, rf);
                tablaPedido.setValueAt(cant.intValue(), lin, 1);
                calcularLinea(cant, precioUnitario, impuestoUnitario.floatValue(), rf.getProducto());
                nuevaCantidadTxt.setText("");
                rf.setGravado(gravado);
                rf.setImpuesto(impuesto);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaPedido.setValueAt(df.format(gravado), lin, 4);
                tablaPedido.setValueAt(df.format(impuesto), lin, 5);
                tablaPedido.setValueAt(df.format(iva), lin, 6);
                tablaPedido.setValueAt(df.format(totalLinea), lin, 8);
                calcularTotales();
                agregarBtn.requestFocus();
            }
        }
    }//GEN-LAST:event_grabarCantidadBtnActionPerformed

    private void comboClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboClientesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (comboClientes.getSelectedIndex() > 0) {
                nombreClienteABuscarTxt.setText("");
                try {
                    Integer seleccion = comboClientes.getSelectedIndex();
                    clienteSeleccionado = new ClienteService().getClientesByFiltro(filtro).get(seleccion - 1);
                    codigoTxt.setText(clienteSeleccionado.getCodigo());
                    buscar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar cliente");
                    Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            comboClientes.removeAllItems();
        }
    }//GEN-LAST:event_comboClientesKeyPressed

    private void comboProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (comboProductos.getSelectedIndex() > 0) {
                nombreProductoABuscarTxt.setText("");
                nombreProductoABuscarTxt.setEnabled(false);
                buscarProductoXNombre.setEnabled(false);
                try {
                    Integer seleccion = comboProductos.getSelectedIndex();
                    productoSeleccionado = new ProductoService().getProductosByFiltroSin90SinDepo(filtro).get(seleccion - 1);
                    codigoProductoTxt.setText(String.valueOf(productoSeleccionado.getCodigo()));
                    consultarProducto();
                    cantidadTxt.requestFocus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar producto");
                    Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboProductosKeyPressed

    private void leerPrecioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerPrecioBtnActionPerformed
        FileReader fr = null;
        try {
            fr = new FileReader("c:/ventas/permisos.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        String acceso = "";
        try {
            acceso = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (acceso.equals("1")) {
            leerPrecio();
        } else {
            if (acceso.equals("3")) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                JTextField field = new JTextField("");
                field.setFont(new Font("Tahoma", Font.PLAIN, 11));
                String[] options = {"Ingresar", "Regresar"};
                int result = JOptionPane.showOptionDialog(
                        null,
                        field,
                        "Autorización de USUARIO",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        0);
                switch (result) {
                    case 0:
                        int cod = Integer.valueOf(field.getText());
                        Usuario usu = null;
                        try {
                            usu = new UsuarioService().getUsuarioByCodigo(cod);
                        } catch (Exception ex) {
                            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (usu != null) {
                            int cla = usu.getNivel();
                            if (usu.getActivo()) {
                                if (cla > 3) {
                                    JOptionPane.showMessageDialog(this, "Acceso Prohibido");
                                    break;
                                }
                                Date a = new Date();
                                if (cla == 2) {
                                    if (usu.getFecha() != null) {
                                        Date b = usu.getFecha();
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        if (!sdf.format(a).equals(sdf.format(b))) {
                                            JOptionPane.showMessageDialog(this, "Acceso Vencido");
                                            break;
                                        }
                                    }
                                }
                                JTextField field2 = new JTextField("");
                                field2.setFont(new Font("Symbol", Font.PLAIN, 12));
                                String[] opts = {"Ingresar", "Regresar"};
                                int resulta = JOptionPane.showOptionDialog(
                                        null,
                                        field2,
                                        "CONTRASEÑA: " + usu.getNombre() + ", Autorización",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        opts,
                                        0);
                                switch (resulta) {
                                    case 0:
                                        int contra = Integer.valueOf(field2.getText());
                                        if (contra == usu.getContrasena()) {
                                            leerPrecio();
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
                                        }
                                        break;
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "USUARIO Inactivo");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "No existe el Usuario");
                        }
                        break;
                }
            }
        }
    }//GEN-LAST:event_leerPrecioBtnActionPerformed

    private void grabarPrecioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarPrecioBtnActionPerformed
        int lin = tablaPedido.getSelectedRow();
        if (lin > -1) {
            if (!nuevoPrecioTxt.getText().isEmpty()) {
                leerPrecioBtn.setEnabled(true);
                grabarPrecioBtn.setEnabled(false);
                leerCantidadBtn.setEnabled(true);
                tablaPedido.setEnabled(true);
                RenglonPedido rf = renglonPedido.get(lin);
                Float cant = rf.getCantidad();
                Double impuestoUnitario = rf.getImpuesto() / cant;
                Double nuevoImporte = Double.valueOf(nuevoPrecioTxt.getText().replaceAll("\\,", "\\.")) / (1 + (porcentualIva / 100));

                calcularLinea(cant, nuevoImporte, impuestoUnitario.floatValue(), rf.getProducto());

                rf.setGravado(gravado);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaPedido.setValueAt(df.format(precioFinal), lin, 3);
                tablaPedido.setValueAt(df.format(gravado), lin, 4);
                tablaPedido.setValueAt(df.format(impuesto), lin, 5);
                tablaPedido.setValueAt(df.format(iva), lin, 6);
                tablaPedido.setValueAt(df.format(totalLinea), lin, 8);
                nuevoPrecioTxt.setText("");
                nuevoPrecioTxt.setEnabled(false);
                eliminarItemBtn.setEnabled(true);
                terminarBtn.setEnabled(true);
                calcularTotales();
            }
        }
    }//GEN-LAST:event_grabarPrecioBtnActionPerformed

    private void nuevaCantidadTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevaCantidadTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int lin = tablaPedido.getSelectedRow();
            if (lin > -1) {
                if (!nuevaCantidadTxt.getText().isEmpty()) {
                    leerCantidadBtn.setEnabled(true);
                    grabarCantidadBtn.setEnabled(false);
                    RenglonPedido rf = renglonPedido.get(lin);
                    Float cantidadAnterior = rf.getCantidad();
                    Float cant = Float.valueOf(nuevaCantidadTxt.getText());
                    if (!(cant > 0.0)) {
                        JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                        return;
                    }
                    rf.setCantidad(cant);
                    Double precioUnitario = rf.getGravado() / cantidadAnterior;
                    Double impuestoUnitario = rf.getImpuesto() / cantidadAnterior;
                    renglonPedido.set(lin, rf);
                    tablaPedido.setValueAt(cant.intValue(), lin, 1);
                    calcularLinea(cant, precioUnitario, impuestoUnitario.floatValue(), rf.getProducto());

                    tablaPedido.setValueAt(df.format(precioFinal), lin, 3);
                    nuevaCantidadTxt.setText("");
                    rf.setGravado(gravado);
                    rf.setImpuesto(impuesto);
                    rf.setIva(iva);
                    rf.setTotal(totalLinea);
                    tablaPedido.setValueAt(df.format(gravado), lin, 4);
                    tablaPedido.setValueAt(df.format(impuesto), lin, 5);
                    tablaPedido.setValueAt(df.format(iva), lin, 6);
                    tablaPedido.setValueAt(df.format(totalLinea), lin, 8);
                    leerPrecioBtn.setEnabled(true);
                    nuevoPrecioTxt.setEnabled(true);
                    nuevaCantidadTxt.setEnabled(true);
                    leerCantidadBtn.setEnabled(true);
                    terminarBtn.setEnabled(true);
                    eliminarItemBtn.setEnabled(true);
                    agregarBtn.setEnabled(true);
                    tablaPedido.setEnabled(true);
                    agregarBtn.requestFocus();
                    calcularTotales();
                } else {
                    JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad");
                    nuevaCantidadTxt.requestFocus();
                }
            }
        } else {
            if (evt.getKeyCode() != 8 // back space
                    && evt.getKeyCode() != 37 //flecha izquierda
                    && evt.getKeyCode() != 39 //flecha derecha
                    && evt.getKeyCode() != 17 //Ctrl
                    && evt.getKeyCode() != 16 //Mayuscula
                    && evt.getKeyCode() != 38 //flecha arriba
                    && evt.getKeyCode() != 40 //flecha abajo
                    && evt.getKeyCode() != 67 //C
                    && evt.getKeyCode() != 20 //traba mayusculas
                    && evt.getKeyCode() != 27 //Escape
                    && evt.getKeyCode() != 86 //V
                    && evt.getKeyCode() != 36 //Inicio
                    && evt.getKeyCode() != 35 //fin
                    && evt.getKeyCode() != 155 //Insert
                    //&& evt.getKeyCode() != 110  // punto decimal
                    //&& evt.getKeyCode() != 45 // Menos
                    && evt.getKeyCode() != 127) { // Suprimir
                if (!isNumeric(evt)) {
                    JOptionPane.showMessageDialog(this, "Solo números");
                    nuevaCantidadTxt.setText("");
                    nuevaCantidadTxt.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_nuevaCantidadTxtKeyPressed

    private void nuevoPrecioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevoPrecioTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int lin = tablaPedido.getSelectedRow();
            if (lin > -1) {
                if (!nuevoPrecioTxt.getText().isEmpty()) {
                    leerPrecioBtn.setEnabled(true);
                    grabarPrecioBtn.setEnabled(false);
                    leerCantidadBtn.setEnabled(true);
                    tablaPedido.setEnabled(true);
                    RenglonPedido rf = renglonPedido.get(lin);
                    Float cant = rf.getCantidad();
                    Double impuestoUnitario = rf.getImpuesto() / cant;
                    Double nuevoImporte = Double.valueOf(nuevoPrecioTxt.getText()
                            .replaceAll("\\,", "\\."))
                            / (1 + (porcentualIva / 100));

                    calcularLinea(cant, nuevoImporte, impuestoUnitario.floatValue(), rf.getProducto());

                    rf.setGravado(gravado);
                    rf.setIva(iva);
                    rf.setTotal(totalLinea);
                    tablaPedido.setValueAt(df.format(precioFinal), lin, 3);
                    tablaPedido.setValueAt(df.format(gravado), lin, 4);
                    tablaPedido.setValueAt(df.format(impuesto), lin, 5);
                    tablaPedido.setValueAt(df.format(iva), lin, 6);
                    tablaPedido.setValueAt(df.format(totalLinea), lin, 8);
                    nuevoPrecioTxt.setText("");
                    nuevoPrecioTxt.setEnabled(true);
                    nuevaCantidadTxt.setEnabled(true);
                    eliminarItemBtn.setEnabled(true);
                    terminarBtn.setEnabled(true);
                    agregarBtn.setEnabled(true);
                    agregarBtn.requestFocus();
                    calcularTotales();
                } else {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un importe");
                }
            }
        } else {
            if (evt.getKeyCode() != 8 // back space
                    && evt.getKeyCode() != 37 //flecha izquierda
                    && evt.getKeyCode() != 39 //flecha derecha
                    && evt.getKeyCode() != 17 //Ctrl
                    && evt.getKeyCode() != 16 //Mayuscula
                    && evt.getKeyCode() != 38 //flecha arriba
                    && evt.getKeyCode() != 40 //flecha abajo
                    && evt.getKeyCode() != 67 //C
                    && evt.getKeyCode() != 20 //traba mayusculas
                    && evt.getKeyCode() != 27 //Escape
                    && evt.getKeyCode() != 86 //V
                    && evt.getKeyCode() != 36 //Inicio
                    && evt.getKeyCode() != 35 //fin
                    && evt.getKeyCode() != 155 //Insert
                    && evt.getKeyCode() != 110 // punto decimal
                    //&& evt.getKeyCode() != 45 // Menos
                    && evt.getKeyCode() != 127) { // Suprimir
                if (!isNumeric(evt)) {
                    JOptionPane.showMessageDialog(this, "Solo números");
                    nuevoPrecioTxt.setText("");
                    nuevoPrecioTxt.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_nuevoPrecioTxtKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PedidoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton buscarClienteXNombre;
    private javax.swing.JButton buscarProductoXNombre;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cantidadAtadosMassalinTxt;
    private javax.swing.JTextField cantidadAtadosNoblezaTxt;
    private javax.swing.JTextField cantidadItemsTxt;
    private javax.swing.JTextField cantidadTxt;
    private javax.swing.JTextField codigoBarrasTxt;
    private javax.swing.JTextField codigoProductoTxt;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JComboBox comboClientes;
    private javax.swing.JComboBox comboProductos;
    private javax.swing.JTextField cuitTxt;
    private javax.swing.JButton descuentoBtn;
    private javax.swing.JLabel descuentoGlobalLbl;
    private javax.swing.JTextField descuentoGlobalTxt;
    private javax.swing.JLabel descuentoLineaLbl;
    private javax.swing.JTextField descuentoLineaTxt;
    private javax.swing.JTextField descuentoVolumenTxt;
    private javax.swing.JButton eliminarItemBtn;
    private javax.swing.JTextField fechaTxt;
    private javax.swing.JButton grabarCantidadBtn;
    private javax.swing.JButton grabarPrecioBtn;
    private javax.swing.JButton incorporarProductoBtn;
    private javax.swing.JTextField ivaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leerCantidadBtn;
    private javax.swing.JButton leerPrecioBtn;
    private javax.swing.JTextField nombreClienteABuscarTxt;
    private javax.swing.JTextField nombreProductoABuscarTxt;
    private javax.swing.JTextField nombreProductoConsultaTxt;
    private javax.swing.JTextField nuevaCantidadTxt;
    private javax.swing.JTextField nuevoPrecioTxt;
    private javax.swing.JTextField precioProductoConsultaTxt;
    private javax.swing.JTextField razonSocialTxt;
    private javax.swing.JTextField saldoTxt;
    private javax.swing.JTable tablaPedido;
    private javax.swing.JButton terminarBtn;
    private javax.swing.JTextField texto1PiePedidoTxt;
    private javax.swing.JTextField texto2PiePedidoTxt;
    private javax.swing.JTextField totalDeudaTxt;
    private javax.swing.JTextField totalMassalinTxt;
    private javax.swing.JTextField totalNoblezaTxt;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        texto1PiePedidoTxt.setText("");
        cuitTxt.setText("");
        texto2PiePedidoTxt.setText("");
        cantidadAtadosMassalinTxt.setText("");
        cantidadAtadosNoblezaTxt.setText("");
        ivaTxt.setText("");
        codigoBarrasTxt.setText("");
        codigoProductoTxt.setText("");
        codigoTxt.setText("");
        fechaTxt.setText("");
        razonSocialTxt.setText("");
        cantidadTxt.setText("");
        totalTxt.setText("");
        saldoTxt.setText("0.00");
        totalTxt.setText("0.00");
        comboClientes.removeAllItems();
        comboClientes.addItem("");
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        nombreClienteABuscarTxt.setText("");
        nombreProductoABuscarTxt.setText("");
        descuentoVolumenTxt.setText("0.00");
        descuentoLineaTxt.setText("");
        totalDeudaTxt.setText("0.00");
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        nombreProductoConsultaTxt.setText("");
        precioProductoConsultaTxt.setText("");
        cantidadItemsTxt.setText("");
        descuentoGlobalTxt.setText("");
        nuevaCantidadTxt.setText("");
        nuevoPrecioTxt.setText("");
        totalMassalinTxt.setText("");
        totalNoblezaTxt.setText("");
    }

    private void bloquearCampos() {
        ivaTxt.setEditable(false);
        codigoBarrasTxt.setEnabled(false);
        codigoTxt.setEnabled(true);
        codigoTxt.setEditable(true);
        codigoProductoTxt.setEnabled(false);
        fechaTxt.setEditable(false);
        razonSocialTxt.setEditable(false);
        cantidadTxt.setEnabled(false);
        totalTxt.setEnabled(false);
        saldoTxt.setEditable(false);
        descuentoVolumenTxt.setEnabled(false);
        totalDeudaTxt.setEnabled(false);
        nombreProductoABuscarTxt.setEnabled(false);
        nombreProductoConsultaTxt.setEnabled(false);
        agregarBtn.setEnabled(false);
        cancelarBtn.setEnabled(false);
        if (nro > 0) {
            terminarBtn.setEnabled(true);
        } else {
            terminarBtn.setEnabled(false);
        }
        descuentoLineaTxt.setEnabled(false);
        incorporarProductoBtn.setEnabled(false);
        buscarProductoXNombre.setEnabled(false);
        eliminarItemBtn.setEnabled(false);
        descuentoBtn.setEnabled(false);
        comboProductos.setEnabled(false);
        precioProductoConsultaTxt.setEnabled(false);
        cantidadItemsTxt.setEnabled(false);
        texto1PiePedidoTxt.setEnabled(false);
        texto2PiePedidoTxt.setEnabled(false);
        cantidadAtadosMassalinTxt.setEditable(false);
        cantidadAtadosNoblezaTxt.setEditable(false);
        descuentoGlobalTxt.setEditable(false);
        nuevaCantidadTxt.setEditable(false);
        leerCantidadBtn.setEnabled(false);
        grabarCantidadBtn.setEnabled(false);
        nuevaCantidadTxt.setEnabled(false);
        nuevoPrecioTxt.setEnabled(false);
        leerPrecioBtn.setEnabled(false);
        grabarPrecioBtn.setEnabled(false);
        totalMassalinTxt.setEditable(false);
        totalNoblezaTxt.setEditable(false);
    }

    private void llenarComboClientes() {
        filtro = nombreClienteABuscarTxt.getText();
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            clientes = new ClienteService().getClientesByFiltro(filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - leyendo Clientes");
            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboClientes.getModel();
        if (clientes != null && !clientes.isEmpty()) {
            for (Cliente cli : clientes) {
                if (cli.getAlias() != null) {
                    model.addElement(cli.getAlias() + " "
                            + cli.getRazonSocial());
                } else {
                    model.addElement(cli.getRazonSocial());
                }
            }
            comboClientes.setModel(model);
        }
    }

    private void llenarComboProductos() {
        filtro = nombreProductoABuscarTxt.getText();
        List<Producto> productos = new ArrayList<Producto>();
        try {
            productos = new ProductoService().getProductosByFiltroSin90SinDepo(filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - leyendo Productos");
            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboProductos.getModel();
        if (productos != null && !productos.isEmpty()) {
            for (Producto pro : productos) {
                model.addElement(pro.getDetalle());
            }
            comboProductos.setModel(model);
        }
    }

    private void buscar() {
        filtro = "";
        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //sdf = new SimpleDateFormat("dd/MM/yyyy");
        fecha = Calendar.getInstance().getTime();
        Cliente cli = new Cliente();
        categoriaIva = 4;
        try {
            clientePedido = new ClienteService().getClienteByCodigo(codigoTxt.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - cliente");
            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clientePedido != null) {
            razonSocialTxt.setText(clientePedido.getRazonSocial());
            cuitTxt.setText(clientePedido.getCuit());
            fechaTxt.setText(sdf.format(fecha));
            if (clientePedido.getCategoriaDeIva() != null) {
                if (clientePedido.getCategoriaDeIva() == 1) {
                    ivaTxt.setText("Resp. Inscripto");
                }
                if (clientePedido.getCategoriaDeIva() == 2) {
                    ivaTxt.setText("Monotributo");
                }
                if (clientePedido.getCategoriaDeIva() == 4) {
                    ivaTxt.setText("Consumidor Final");
                }
                categoriaIva = clientePedido.getCategoriaDeIva();
            } else {
                ivaTxt.setText("Consumidor Final");
            }
            if (clientePedido.getSaldo() != null) {
                saldoTxt.setText(String.valueOf(df.format(clientePedido.getSaldo())));
                saldoCliente = clientePedido.getSaldo();
            } else {
                saldoTxt.setText("0.00");
                saldoCliente = 0.0;
            }
            if (clientePedido.getTieneDescuento() != null) {
                if (clientePedido.getTieneDescuento()) {
                    descuentoGlobalTxt.setText(df2.format(clientePedido.getDescuento()));
                    descuento = clientePedido.getDescuento();
                } else {
                    descuentoGlobalTxt.setText(df2.format(0.0));
                    descuento = 0F;
                }
            } else {
                descuentoGlobalTxt.setText(df2.format(0.0));
                descuento = 0F;
            }
            tieneDto = true;
            descuento = clientePedido.getDescuento();
            agregarBtn.setEnabled(true);
            buscarBtn.setEnabled(false);
            buscarClienteXNombre.setEnabled(false);
            nombreClienteABuscarTxt.setEnabled(false);
            comboClientes.setEnabled(false);
            nombreProductoConsultaTxt.setEditable(false);
            precioProductoConsultaTxt.setEditable(false);
            codigoTxt.setEditable(false);
            descuentoVolumenTxt.setEnabled(true);
            descuentoVolumenTxt.setEditable(false);
            totalTxt.setEnabled(true);
            totalTxt.setEditable(false);
            totalDeudaTxt.setEnabled(true);
            totalDeudaTxt.setEditable(false);
            volverBtn.setEnabled(false);
            cancelarBtn.setEnabled(true);
            leerCantidadBtn.setEnabled(false);
            nuevaCantidadTxt.setEditable(true);
            nuevaCantidadTxt.setEnabled(true);
            nuevaCantidadTxt.setEnabled(false);
            agregarProducto();
        } else {
            JOptionPane.showMessageDialog(this, "Error - cliente no existe");
            buscarBtn.setEnabled(true);
            codigoTxt.requestFocus();
        }
    }

    private void terminarPedido() {
        Integer categoriaIva = 0;
        Pedido pedido = new Pedido();

//        System.out.println(totalGravado);
//        System.out.println(totalIva);
//        System.out.println(totalImpuesto);
//        System.out.println(totalPedido);
//        System.exit(0);
        categoriaIva = clientePedido.getCategoriaDeIva();
        saldoCliente = clientePedido.getSaldo();
        Long id = (long) 1;
        try {
            config = new ConfiguracionService().getFacturas(id);
        } catch (Exception ex) {
            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (categoriaIva.equals(1)) {
            letraPedido = "A";
            // es inscriptp
            numeroPedido = config.getNumeroPedido();
            numeroPedido += 1;
            config.setNumeroPedido(numeroPedido);
            try {
                new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            letraPedido = "B";
            // el resto de las categorias
            numeroPedido = config.getNumeroPedido();
            numeroPedido += 1;
            config.setNumeroPedido(numeroPedido);
            try {
                new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        pedido.setCliente(clientePedido);
        pedido.setDescuentoGlobal(totalDescuento);
        pedido.setPorcentualDescuentoGlobal(descuento);
        pedido.setExento(0.0);
        pedido.setFecha(fecha);
        pedido.setGravado(totalGravado);
        pedido.setImpuesto(totalImpuesto);
        pedido.setIva(totalIva);
        pedido.setNoGravado(0.0);
        pedido.setTotal(totalPedido);
        pedido.setLetra(letraPedido);
        pedido.setNumeroPedido(numeroPedido);
        pedido.setFacturado(false);
        pedido.setTextoPiePedido1(texto1PiePedidoTxt.getText());
        pedido.setTextoPiePedido2(texto2PiePedidoTxt.getText());
        for (RenglonPedido rePe : renglonPedido) {
            rePe.setPedido(pedido);
            Integer cod = rePe.getProducto().getCodigo();
            try {
                producto = new ProductoService().getProductoByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            rePe.setProducto(producto);
        }
        try {
            new PedidoService().savePedidoCompleto(pedido, renglonPedido);
        } catch (Exception ex) {
            Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        generarPedido();
        volver();
    }

    private void generarPedido() {
        renglones = new String[maxNro];
        textoPedidoPapel = "PEDIDO WEB";
        fechaPedidoPapel = sdf.format(fecha);
        clientePedidoPapel = razonSocialTxt.getText();
        codigoClientePedidoPapel = clientePedido.getCodigo();
        direccionPedidoPapel = clientePedido.getDomicilio().getCalle() + " " + clientePedido.getDomicilio().getNumero() + " - " + clientePedido.getDomicilio().getLocalidad();
        cuitPedidoPapel = clientePedido.getCuit();
        String condVta = "";
        Date fechaVto = fecha;
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        condVta = "                      ";
        //condicionVentaPedidoPapel = condVta;
        vencimientoPedidoPapel = sdf.format(fechaVto);
        String catego = "";
        if (clientePedido.getCategoriaDeIva().equals(1)) {
            catego = "Responsable Inscripto       ";
        }
        if (clientePedido.getCategoriaDeIva().equals(2)) {
            catego = "Monotributo                 ";
        }
        if (clientePedido.getCategoriaDeIva().equals(3)) {
            catego = "Exento                      ";
        }
        if (clientePedido.getCategoriaDeIva().equals(4)) {
            catego = "Consumidor Final            ";
        }
        inscripcionClientePedidoPapel = catego;
        if (categoriaIva != 1) {
            //                                    1         2         3         4         5         6         7         8         9        10
            //                           1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            nombresColumnaPedidoPapel = "  IT  CANT                   DETALLE                  P.UNIT.    DESC.   IMPORTE       IMP.     TOTAL     SUG";
        } else {
            nombresColumnaPedidoPapel = "  IT  CANT                   DETALLE                 P.UNIT.    DESC.   GRAVADO      IVA       IMP.     TOTAL     SUG";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        int maxTabla = tablaPedido.getRowCount();
        for (int r = 0; r < maxNro; r++) {
            if (r < maxTabla) {
                String str0 = String.valueOf(r + 1);
                int largo = str0.length();
                if (largo < 2) {
                    renglones[r] = " " + str0 + " ";
                } else {
                    renglones[r] = str0 + " ";
                }
                str0 = tablaPedido.getValueAt(r, 1).toString();
                largo = str0.length();
                if (largo == 1) {
                    renglones[r] = renglones[r] + "    " + str0;
                }
                if (largo == 2) {
                    renglones[r] = renglones[r] + "   " + str0;
                }
                if (largo == 3) {
                    renglones[r] = renglones[r] + "  " + str0;
                }
                if (largo == 4) {
                    renglones[r] = renglones[r] + " " + str0;
                }
                if (largo == 5) {
                    renglones[r] = renglones[r] + str0;
                }
                if (largo == 6) {
                    renglones[r] = renglones[r] + str0;
                }
                str0 = tablaPedido.getValueAt(r, 2).toString();
                String espacio = " ";
                largo = str0.length();
                if (largo > 40) {
                    str0 = str0.substring(0, 40);
                    tablaPedido.setValueAt(str0, r, 2);
                }
                for (int l = largo; l < 40; l++) {
                    espacio += " ";
                }
                renglones[r] = renglones[r] + " " + tablaPedido.getValueAt(r, 2) + espacio;;
                if (categoriaIva != 1) {
//                  aqui detalle de importes no inscripto en IVA           *****
// Precio Unitario
                    str0 = tablaPedido.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
                    str0 = tablaPedido.getValueAt(r, 7).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Importe
                    str0 = tablaPedido.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    Double calculo = Double.valueOf(str0);
                    str0 = tablaPedido.getValueAt(r, 6).toString();
                    str0 = str0.replace(",", ".");
                    calculo += Double.valueOf(str0);
                    str0 = String.valueOf(calculo);
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Impuesto
                    str0 = tablaPedido.getValueAt(r, 5).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaPedido.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaPedido.getValueAt(r, 9).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "    ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
                } else {
                    // aqui detalle importes inscripto
// Precio Unitario
                    str0 = tablaPedido.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
                    str0 = tablaPedido.getValueAt(r, 7).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Gravado
                    str0 = tablaPedido.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Iva
                    str0 = tablaPedido.getValueAt(r, 6).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Impuesto
                    str0 = tablaPedido.getValueAt(r, 5).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaPedido.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaPedido.getValueAt(r, 9).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "    ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble);
                }
            } else {
                // agregar renglon en blanco
                renglones[r] = " ";
            }
        }
// Saldo Cliente
        String str0 = String.valueOf(saldoCliente);
        str0 = str0.replace(",", ".");
        Double doble = Double.valueOf(str0);
        int largo = doble.intValue();
        String espacio = "          ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        totalDeudaPedidoPapel = espacio + df.format(doble);
// Total Pedido
        str0 = String.valueOf(totalPedido);
        str0 = str0.replace(",", ".");
        doble = Double.valueOf(str0);
        largo = doble.intValue();
        espacio = "        ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        importeTotalPedidoPapel = espacio + df.format(doble);
// Linea Totales
        if (categoriaIva != 1) {
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                                                            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesPedidoPapel = espacio + df.format(doble);
        } else {
            str0 = String.valueOf(totalGravado);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "           ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesPedidoPapel = espacio + df.format(doble);
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "           ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesPedidoPapel += espacio + df.format(doble);
            str0 = String.valueOf(totalIva);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                 ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesPedidoPapel += espacio + df.format(doble);
        }
// Total a Pagar
        Double totalPagar = saldoCliente;
        str0 = String.valueOf(totalPagar);
        str0 = str0.replace(",", ".");
        doble = Double.valueOf(str0);
        largo = doble.intValue();
        espacio = "          ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        totalPagarPedidoPapel = espacio + df.format(doble);
// Cantidades atados
        cantidadesPedidoPapel = "                  CANT.ATADOS NOBLEZA: " + String.valueOf(cantidadAtadosNobleza);
        cantidadesPedidoPapel += "                 CANT ATADOS MASSALIN: " + String.valueOf(cantidadAtadosMassalin);
        texto1PedidoPapel = texto1PiePedidoTxt.getText();
        texto2PedidoPapel = texto2PiePedidoTxt.getText();
        texto3PedidoPapel = "-";
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.defaultPage();
        Paper paper = new Paper();
        double margin = 8;
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
        pf.setPaper(paper);
        pj.setPrintable(new MyPrintable(), pf);
//        if (pj.printDialog()) {
        try {
            pj.print();
        } catch (PrinterException e) {
            System.out.println(e);
        }
//        }
    }

    private void agregarProducto() {
        codigoProductoTxt.setEnabled(true);
        codigoBarrasTxt.setEnabled(true);
        cantidadTxt.setEnabled(true);
        agregarBtn.setEnabled(false);
        if (nro > 0) {
            terminarBtn.setEnabled(true);
        } else {
            terminarBtn.setEnabled(false);
        }
        buscarProductoXNombre.setEnabled(true);
        incorporarProductoBtn.setEnabled(true);
        nombreProductoABuscarTxt.setEnabled(true);
        nombreProductoConsultaTxt.setEnabled(true);
        comboProductos.setEnabled(true);
        cancelarBtn.setEnabled(true);
        nombreProductoABuscarTxt.requestFocus();
    }

    private void buscarProducto() {
        filtro = "";
        if (nro < maxNro - 1) {
            agregarBtn.setEnabled(true);
        } else {
            agregarBtn.setEnabled(false);
            System.out.print("\007");
            System.out.flush();
            JOptionPane.showMessageDialog(this, "Cantidad de artículos excedido");
            return;
        }
        if (nro > 0) {
            terminarBtn.setEnabled(true);
        } else {
            terminarBtn.setEnabled(false);
        }
        eliminarItemBtn.setEnabled(true);
        cancelarBtn.setEnabled(true);
        leerPrecioBtn.setEnabled(true);
        nuevoPrecioTxt.setEnabled(true);
        leerCantidadBtn.setEnabled(true);
        nuevaCantidadTxt.setEnabled(true);
        if (cantidadTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error - ingrese cantidad");
            cantidadTxt.requestFocus();
        } else {
            Producto pro = null;
            encontrado = false;
            if (!codigoBarrasTxt.getText().isEmpty()) {
                try {
                    pro = new ProductoService().getProductoByCodigoBarras(Long.valueOf(codigoBarrasTxt.getText()));
                    encontrado = true;
                } catch (Exception ex) {
                    Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                    codigoProductoTxt.requestFocus();
                }
            } else {
                if (!codigoProductoTxt.getText().isEmpty()) {
                    try {
                        pro = new ProductoService().getProductoByCodigo(Integer.valueOf(codigoProductoTxt.getText()));
                        encontrado = true;
                    } catch (Exception ex) {
                        Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                        codigoProductoTxt.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese Código de Producto o Código de Barras");
                    codigoProductoTxt.requestFocus();
                }
            }
            if (pro != null) {
                if (pro.getInactivo() != null) {
                    if (!pro.getInactivo()) {
                        if (!(pro.getSubRubro().getCodigo().equals(5099))) {
                            cantidad = Float.valueOf(cantidadTxt.getText());
                            // en unidad
                            if (!(cantidad > 0.0)) {
                                JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                                cantidadTxt.requestFocus();
                                return;
                            }
                            dto = 0.0;
                            Double precio = pro.getPrecio();
                            Float impuest = pro.getImpuesto();
                            calcularLinea(cantidad, precio, impuest, pro);
                            RenglonPedido rf = new RenglonPedido();
                            rf.setCantidad(cantidad);
                            rf.setDescripcion(pro.getDetalle());
                            rf.setDescuento(dto);
                            rf.setExento(0.0);
                            rf.setGravado(gravado);
                            rf.setImpuesto(impuesto);
                            rf.setIva(iva);
                            rf.setNoGravado(noGravado);
                            rf.setProducto(pro);
                            rf.setSugerido(pro.getSugerido());
                            rf.setTotal(totalLinea);
                            nro += 1;
                            renglonPedido.add(rf);
                            calcularTotales();
                            Object[] fila = new Object[10];
                            fila[0] = pro.getCodigo();
                            fila[1] = df1.format(cantidad);
                            fila[2] = pro.getDetalle();
                            fila[3] = df.format(precioFinal);
                            fila[4] = df.format(gravado);
                            fila[5] = df.format(impuesto);
                            fila[6] = df.format(iva);
                            fila[7] = df.format(0.0);
                            fila[8] = df.format(totalLinea);
                            fila[9] = df.format(pro.getSugerido());
                            tabla.addRow(fila); // Agrego la fila a la tabla
                            Rectangle rect = tablaPedido.getCellRect(nro - 1, 0, true);
                            tablaPedido.scrollRectToVisible(rect);
                            tablaPedido.clearSelection();
                            tablaPedido.setRowSelectionInterval(nro - 1, nro - 1);
                            tablaPedido.setModel(tabla); // poner visible la tabla
                            codigoProductoTxt.setEnabled(false);
                            codigoBarrasTxt.setEnabled(false);
                            cantidadTxt.setEnabled(false);
                            incorporarProductoBtn.setEnabled(false);
                            cantidadTxt.setText("");
                            codigoProductoTxt.setText("");
                            buscarProductoXNombre.setEnabled(false);
                            nombreProductoABuscarTxt.setEnabled(false);
                            comboProductos.setEnabled(false);
                            texto1PiePedidoTxt.setEnabled(true);
                            texto2PiePedidoTxt.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(this, "Error - producto no disponible");
                            codigoProductoTxt.setText("");
                            cantidadTxt.setText("");
                            nombreProductoABuscarTxt.setText("");
                            buscarBtn.setEnabled(false);
                            agregarBtn.setEnabled(false);
                            codigoProductoTxt.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                        codigoProductoTxt.setText("");
                        cantidadTxt.setText("");
                        buscarBtn.setEnabled(false);
                        //codigoBarrasTxt.requestFocus();
                        codigoProductoTxt.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                    codigoProductoTxt.setText("");
                    cantidadTxt.setText("");
                    buscarBtn.setEnabled(false);
                    codigoProductoTxt.requestFocus();
                    //codigoProductoTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error - producto no existe");
                codigoProductoTxt.setText("");
                codigoProductoTxt.requestFocus();
                //codigoProductoTxt.requestFocus();
            }
        }
        comboProductos.removeAllItems();
        comboProductos.addItem("");
    }

    private void borrarTablaProductos() {
        int cantidadRow = tablaPedido.getRowCount();
        DefaultTableModel model1 = (DefaultTableModel) tablaPedido.getModel();
        if (cantidadRow > 0) {
            for (int i = 0; i < cantidadRow; i++) {
                model1.removeRow(0);
            }
            tablaPedido.setModel(model1);
            nro = 0;
        }
        renglonPedido = new ArrayList<RenglonPedido>();
    }

    private void consultarProducto() {
        nombreProductoConsultaTxt.setEnabled(true);
        precioProductoConsultaTxt.setEnabled(true);
        if (!codigoProductoTxt.getText().isEmpty()) {
            Integer codi = Integer.valueOf(codigoProductoTxt.getText());
            try {
                Producto prod = new ProductoService().getProductoByCodigo(codi);
                if (!(prod.getSubRubro().getCodigo().equals(5099))) {
                    nombreProductoConsultaTxt.setText(prod.getDetalle());
                    Double precioProductoConsulta = prod.getPrecio();
                    precioProductoConsulta += precioProductoConsulta * porcentualIva / 100;
                    if (prod.getImpuesto() != null) {
                        precioProductoConsulta += prod.getImpuesto();
                    }
                    precioProductoConsultaTxt.setText(String.valueOf(df.format(precioProductoConsulta)));
                    cantidadTxt.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "No existe Producto");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No existe Producto");
                //Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoProductoTxt.setText("");
                codigoProductoTxt.requestFocus();
            }
        }
    }

    private void calcularTotales() {
        totalGravado = 0.0;
//        totalGravadoCigarrillos = 0.00;
        totalIva = 0.0;
        totalImpuesto = 0.0;
        totalPedido = 0.0;
        nro = 0;
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        Double acumuladoParaDescuentoGlobal = 0.0;
        Double importeTotalMassalin = 0.0;
        Double importeTotalNobleza = 0.0;
        for (RenglonPedido renFa : renglonPedido) {
            //totalPedido += renFa.getTotal();
            if (renFa.getProducto().getRubro().getCodigo().equals(1)) {
                cantidadAtadosMassalin += renFa.getCantidad().intValue();
                importeTotalMassalin += renFa.getTotal();
                totalImpuesto += renFa.getImpuesto();
            }
            if (renFa.getProducto().getRubro().getCodigo().equals(2)) {
                cantidadAtadosNobleza += renFa.getCantidad().intValue();
                importeTotalNobleza += renFa.getTotal();
                totalImpuesto += renFa.getImpuesto();
            }
            if (clientePedido.getCategoriaDeIva().equals(1)) {
//          IVA DISCRIMINADO                
                totalPedido += renFa.getTotal();
                if (renFa.getProducto().getRubro().getCodigo().equals(5)) {
                    acumuladoParaDescuentoGlobal += renFa.getGravado();
                }
            } else {
//          CONSUMIDOR FINAL
                totalPedido += renFa.getTotal();
                if (renFa.getProducto().getRubro().getCodigo().equals(5)) {
                    acumuladoParaDescuentoGlobal += renFa.getTotal();
                }
            }
            nro += 1;
            renFa.setItemNro(nro);
        }
        String tgcig = df.format(totalGravadoCigarrillos);
        totalGravadoCigarrillos = Double.valueOf(tgcig.replace(",", "."));
        totalDescuento = acumuladoParaDescuentoGlobal * descuento / 100;
        String td = df.format(totalDescuento);
        descuentoVolumenTxt.setText(td);
        totalDescuento = Double.valueOf(td.replace(",", "."));
        if (clientePedido.getCategoriaDeIva().equals(1)) {
            Double t1 = totalPedido - totalImpuesto;
            totalGravadoCompleto = t1 / (1 + (porcentualIva / 100));
            String tgc = df.format(totalGravadoCompleto);
            totalGravadoCompleto = Double.valueOf(tgc.replace(",", "."));
            totalGravado = totalGravadoCompleto - totalDescuento;
            String tg = df.format(totalGravado);
            totalGravado = Double.valueOf(tg.replace(",", "."));
            totalIva = totalGravado * porcentualIva / 100;
            String ti = df.format(totalIva);
            totalIva = Double.valueOf(ti.replace(",", "."));
            String tim = df.format(totalImpuesto);
            totalImpuesto = Double.valueOf(tim.replace(",", "."));
            totalPedido = totalGravado + totalIva + totalImpuesto;
            String tf = df.format(totalPedido);
            totalPedido = Double.valueOf(tf.replace(",", "."));
            gravadoVarios = totalGravado - totalGravadoCigarrillos;
            String gv = df.format(gravadoVarios);
            gravadoVarios = Double.valueOf(gv.replace(",", "."));
//            System.out.println("gravado Compl " + totalGravadoCompleto);
//            System.out.println("gravado       " + totalGravado);
//            System.out.println("iva           " + totalIva);
//            System.out.println("impuesto      " + totalImpuesto);
//            System.out.println("descuento     " + totalDescuento);
//            System.out.println("total factura " + totalFactura);
//            System.out.println("grav.cig      " + totalGravadoCigarrillos);
//            System.out.println("grv.vs        " + gravadoVarios);
        } else {
            String tf = df.format(totalPedido - totalDescuento);
            totalPedido = Double.valueOf(tf.replace(",", "."));
            totalTxt.setText(tf);
            String ti = df.format(totalImpuesto);
            totalImpuesto = Double.valueOf(ti.replace(",", "."));
            totalGravado = (totalPedido - totalImpuesto) / (1 + (porcentualIva / 100));
            String tg = df.format(totalGravado);
            totalGravado = Double.valueOf(tg.replace(",", "."));
            totalIva = totalGravado * porcentualIva / 100;
            String tiv = df.format(totalIva);
            totalIva = Double.valueOf(tiv.replace(",", "."));
            gravadoVarios = totalGravado - totalGravadoCigarrillos;
            String gv = df.format(gravadoVarios);
            gravadoVarios = Double.valueOf(gv.replace(",", "."));
//            System.out.println("gravado       " + totalGravado);
//            System.out.println("iva           " + totalIva);
//            System.out.println("impuesto      " + totalImpuesto);
//            System.out.println("descuento     " + totalDescuento);
//            System.out.println("total factura " + totalFactura);
//            System.out.println("grav.cig      " + totalGravadoCigarrillos);
//            System.out.println("grv.vs        " + gravadoVarios);
        }
//        JOptionPane.showMessageDialog(this, "ver");
        cantidadAtadosNoblezaTxt.setText(String.valueOf(cantidadAtadosNobleza));
        totalNoblezaTxt.setText(String.valueOf(df.format(importeTotalNobleza)));
        cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
        totalMassalinTxt.setText(String.valueOf(df.format(importeTotalMassalin)));
        cantidadItemsTxt.setText(String.valueOf(nro));
        totalTxt.setText(String.valueOf(df.format(totalPedido)));
//        clientePedido.setImporteMostrador(totalPedido);

//        String tg0 = df.format((((totalPedido
//                - totalImpuesto) / (1 + (porcentualIva / 100)))
//                * 100) / 100);
//        totalGravado = Double.valueOf(tg0.replace(",","."));
//        tg0 = df.format((totalGravado
//                * (porcentualIva / 100) * 100) / 100);
//        totalIva = Double.valueOf(tg0.replace(",","."));
//        cantidadAtadosNoblezaTxt.setText(String.valueOf(cantidadAtadosNobleza));
//        totalNoblezaTxt.setText(String.valueOf(df.format(importeTotalNobleza)));
//        cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
//        totalMassalinTxt.setText(String.valueOf(df.format(importeTotalMassalin)));
//        cantidadItemsTxt.setText(String.valueOf(nro));
//        totalTxt.setText(String.valueOf(df.format(totalPedido)));
        totalDeudaTxt.setText(df.format(saldoCliente + totalPedido));
    }

    private void consultarProductoBarras() {
        nombreProductoConsultaTxt.setEnabled(true);
        precioProductoConsultaTxt.setEnabled(true);
        if (!codigoBarrasTxt.getText().isEmpty()) {
            Long codigoBarras = Long.valueOf(codigoBarrasTxt.getText());
            try {
                Producto prod = new ProductoService().getProductoByCodigoBarras(codigoBarras);
                nombreProductoConsultaTxt.setText(prod.getDetalle());
                Double precioProductoConsulta = prod.getPrecio();
                precioProductoConsulta += precioProductoConsulta * porcentualIva / 100;
                if (prod.getImpuesto() != null) {
                    precioProductoConsulta += prod.getImpuesto();
                }
                precioProductoConsultaTxt.setText(String.valueOf(df.format(precioProductoConsulta)));
                cantidadTxt.requestFocus();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No existe Producto");
                //Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoProductoTxt.setText("");
                codigoProductoTxt.requestFocus();
            }
        }
    }

    private void abandonarPedido() {
        limpiarCampos();
        bloquearCampos();
        borrarTablaProductos();
        volverBtn.setEnabled(true);
        codigoTxt.requestFocus();
        buscarBtn.setEnabled(true);
        cancelarBtn.setEnabled(false);
        leerCantidadBtn.setEnabled(false);
        leerPrecioBtn.setEnabled(false);
        nuevaCantidadTxt.setEditable(false);
        nuevaCantidadTxt.setEnabled(false);
        nombreClienteABuscarTxt.setEnabled(true);
        buscarClienteXNombre.setEnabled(true);
        comboClientes.setEnabled(true);
        terminarBtn.setEnabled(false);
    }

    private void calcularLinea(Float cantid, Double precio, Float impuest, Producto p) {
        dto = 0.0;
        Integer cd = 0;
        String pf = "";
        precioFinal = (precio
                * (1 + (porcentualIva / 100)))
                + impuest;
        pf = df.format(precioFinal);
        precioFinal = Double.valueOf(pf.replace(",", "."));
        String tg1 = df.format((((precio
                * (1 + (porcentualIva / 100)))
                + impuest) * 100) / 100);
        if (p.getLlevaDto() != null) {
            if (p.getLlevaDto()) {
                cd = p.getCantidadDto();
                if (!(cantid < cd)) {
                    dto = (precioFinal * cantid) * p.getDescuento() / 100;
                    String cdt = df.format(dto);
                    dto = Double.valueOf(cdt.replace(",", "."));
                }
            }
        }
        //precioFinal = Double.valueOf(tg1.replace(",", "."));
        // por cantidad
        tg1 = df.format((precioFinal * cantid * 100) / 100);
        totalLinea = Double.valueOf(tg1.replace(",", "."));
        if (totalLinea > Constantes.maximoVenta) {
            System.out.print("\007");
            System.out.flush();
            JOptionPane.showMessageDialog(this, "Importe supera maximo de linea");
            codigoProductoTxt.setText("");
            cantidadTxt.setText("");
            codigoBarrasTxt.requestFocus();
            return;
        }
        tg1 = df.format((impuest * cantid * 100) / 100);
        impuesto = Double.valueOf(tg1.replace(",", "."));
        Double calculo = (totalLinea - impuesto)
                / (1 + (porcentualIva / 100));
        tg1 = df.format(((calculo) * 100) / 100);
        gravado = Double.valueOf(tg1.replace(",", "."));
        tg1 = df.format(((gravado * porcentualIva
                / 100) * 100) / 100);
        iva = Double.valueOf(tg1.replace(",", "."));
    }

    private void leerPrecio() {
        int lin = tablaPedido.getSelectedRow();
        if (lin > -1) {
            RenglonPedido rf = renglonPedido.get(lin);
            int cod = rf.getProducto().getCodigo();
            Producto pro = null;
            try {
                pro = new ProductoService().getProductoByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(PedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            int codRub = pro.getRubro().getCodigo();
            if (codRub < 3) {
                JOptionPane.showMessageDialog(this, "No puede modificar Precio de este producto");
                leerPrecioBtn.setEnabled(true);
                grabarPrecioBtn.setEnabled(false);
                leerCantidadBtn.setEnabled(true);
                agregarBtn.requestFocus();
                return;
            }
            Float cant = rf.getCantidad();
            Double iva = rf.getIva() / cant;
            Double prec = rf.getGravado() / cant;
            nuevoPrecioTxt.setEnabled(true);
            nuevoPrecioTxt.setText(String.valueOf(df.format(prec + iva)));
            leerPrecioBtn.setEnabled(false);
            grabarPrecioBtn.setEnabled(true);
            leerCantidadBtn.setEnabled(false);
            nuevaCantidadTxt.setEnabled(false);
            eliminarItemBtn.setEnabled(false);
            terminarBtn.setEnabled(false);
        }
    }

    private void volver() {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        this.dispose();
    }

    class MyPrintable implements Printable {

        public int print(Graphics g, PageFormat pf, int pageIndex) {
            if (pageIndex != 0) {
                return NO_SUCH_PAGE;
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(new Font("Monospaced", Font.PLAIN, 8));
            g2.setPaint(Color.black);
            int row = 45;
            //                1234567890123456789012345678901234567890123456789012345678901234567890
            g2.setFont(new Font("Monospaced", Font.PLAIN, 14));
            String espacio = "                              ";
            g2.drawString(espacio + "X", 30, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 8));
            espacio = "                                                         ";
            g2.drawString(espacio + textoPedidoPapel, 30, row);
            row += 25;
            //         123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            espacio = "                                                                           ";
            g2.drawString(espacio + fechaPedidoPapel, 30, row);
            espacio = "            ";
            row += 50;
            g2.drawString(espacio + clientePedidoPapel, 30, row);
            g2.drawString(codigoClientePedidoPapel, 480, row);
            row += 15;
            espacio = "            ";
            g2.drawString(espacio + direccionPedidoPapel, 30, row);
            row += 15;
            g2.drawString(cuitPedidoPapel, 100, row);
            g2.drawString(inscripcionClientePedidoPapel, 360, row);
            row += 25;
            g2.drawString("Ref.Interna:" + letraPedido + " " + numeroPedido, 150, row);
            //g2.drawString(condicionVentaPedidoPapel, 150, row);
            g2.drawString(vencimientoPedidoPapel, 400, row);
            row += 25;
            g2.drawString(nombresColumnaPedidoPapel, 30, row);
            row += 15;
            for (int x = 0; x < maxNro; x++) {
                if (renglones[x] != null) {
                    g2.drawString(renglones[x], 40, row);
                }
                row += 10;
            }
            row += 20;
            if (clientePedido.getTieneDescuento()) {
                g2.drawString("Descuento: " + descuento + "% Total descuento: " + df.format(totalDescuento), 30, row);
            }
            row += 20;
            g2.drawString(lineaTotalesPedidoPapel, 30, row);
            g2.setFont(new Font("Monospaced", Font.BOLD, 11));
            g2.drawString(importeTotalPedidoPapel, 490, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            row += 21;
            g2.drawString("SALDO ANTERIOR: " + totalDeudaPedidoPapel, 403, row);
            row += 10;
            g2.drawString("SALDO TOTAL:    " + totalPagarPedidoPapel, 403, row);
            row += 10;
            espacio = "     ";
            g2.setFont(new Font("Monospaced", Font.BOLD, 9));
            g2.drawString(espacio + texto1PedidoPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto2PedidoPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto3PedidoPapel, 30, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            row += 15;
//            g2.drawString(espacio + texto4PedidoPapel, 30, row);
//            row += 20;
            g2.drawString(cantidadesPedidoPapel, 30, row);
            return PAGE_EXISTS;
        }
    }
}
