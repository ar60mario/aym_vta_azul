/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.ClienteTraba;
import ar.com.ventas.entities.Configuracion;
import ar.com.ventas.entities.CtaCteCliente;
import ar.com.ventas.entities.CustomerTraba;
import ar.com.ventas.entities.FcReserved;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.Producto;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.entities.RenglonFcReserved;
import ar.com.ventas.entities.RenglonNotaCredito;
import ar.com.ventas.entities.Rubro;
import ar.com.ventas.entities.Usuario;
import ar.com.ventas.entities.Vendedor;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.ClienteTrabaService;
import ar.com.ventas.services.ConfiguracionService;
import ar.com.ventas.services.CtaCteClienteService;
import ar.com.ventas.services.CustomerTrabaService;
import ar.com.ventas.services.FcReservedService;
import ar.com.ventas.services.NotaCreditoService;
import ar.com.ventas.services.ProductoService;
import ar.com.ventas.services.RenglonFacturaService;
import ar.com.ventas.services.RenglonFcReservedService;
import ar.com.ventas.services.UsuarioService;
import ar.com.ventas.services.VendedorService;
import ar.com.ventas.util.Constantes;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.rint;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class NotaCreditoWebFrame extends javax.swing.JFrame {

    public List<RenglonNotaCredito> renglonNotaCredito = new ArrayList<RenglonNotaCredito>();
    private String textoNotaCreditoPapel;
    private List<Cliente> clientes;
    private Double dto = 0.0;
    private final Integer nivel = 1;
    private Float descuento = 0F;
    private Double totalGravadoCompleto = 0.0;
    private Double totalDescuentoGravado = 0.0;
    private Double totalDescuentoNoGravado = 0.0;
    private Usuario usuario;
    private String lineaTitulos = "";
    private String modeloFcPapel;
    private String fechaNotaCreditoPapel;
    private String clienteNotaCreditoPapel;
    private String codigoClienteNotaCreditoPapel;
    private String direccionNotaCreditoPapel;
    private String cuitNotaCreditoPapel;
    private String condicionVentaNotaCreditoPapel;
    private String inscripcionClienteNotaCreditoPapel;
    private String nombresColumnaNotaCreditoPapel;
    private String[] renglones = null;
    private String texto1NotaCreditoPapel;
    private String texto2NotaCreditoPapel;
    private String texto3NotaCreditoPapel;
    //private String totalDeudaNotaCreditoPapel;
    private String lineaTotalesNotaCreditoPapel;
    //private String totalPagarNotaCreditoPapel;
    private String importeTotalNotaCreditoPapel;
    private String cantidadesNotaCreditoPapel;
    private Date fecha;
    private SimpleDateFormat sdf;
    private Cliente clienteNotaCredito = null;
    private Producto producto = null;
    private DefaultTableModel tabla = null;
    private Double totalNotaCredito = 0.00;
    private Double totalImpuesto = 0.00;
    private Double totalIva = 0.00;
    private Double totalGravado = 0.00;
    private Double totalNoGravado = 0.0;
    private Double gravado = 0.00;
    private Double noGravado = 0.00;
    private Double iva = 0.00;
    private Float porcentualIva;
    private Double impuesto = null;
    private Double totalLinea = 0.00;
    private Boolean tieneDto = false;
    private Float descuentoCliente = 0F;
    private Cliente clienteSeleccionado;
    private Producto productoSeleccionado;
    private Float cantidad;
    private Integer categoriaIva = 1;
    private DecimalFormat df = new DecimalFormat("#0.00");
    private final DecimalFormat df1 = new DecimalFormat("#0");
    private final DecimalFormat df2 = new DecimalFormat("#0.0");
    private Double saldoCliente = 0.00;
    private String filtro = "";
    private String letraNotaCredito;
    private Integer sucursalNotaCredito;
    private Integer numeroNotaCredito;
    private Configuracion config = null;
    private Double precioFinal = 0.0;
    private Integer nro = 0;
    private Integer maxNro = 41;
    private Integer cantidadAtadosMassalin;
    private Integer cantidadAtadosNobleza;
    public Boolean encontrado;
    private IvaVentas factura;
    private List<RenglonFactura> renglonF = null;
    //private int traba = 0;
    private String texto1Cae = "";
    private String vencCae = "";
    private String texto2Cae = "";
    private String numCae;
    private String sucursalNotaCreditoPapel;
    private String numeroNotaCreditoPapel;
    private String letraNotaCreditoPapel;
    private Double totalGravadoCigarrillos = 0.00;
    private final int tst = 0; // 1 esta en test
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form NotaCreditoFrame
     *
     * @param cli
     * @param fac
     */
    public NotaCreditoWebFrame(Cliente cli, IvaVentas fac) {
        getContentPane().setBackground(new java.awt.Color(135, 206, 235));
        initComponents();
        this.setLocationRelativeTo(null);
        this.clienteSeleccionado = cli;
        this.factura = fac;
//        this.order_name = order_name;
//        this.order_num = order_num;
        bloquearCampos();
//      levanto el modelo creado del frame
        tabla = (DefaultTableModel) tablaNotaCredito.getModel();
        if (clienteSeleccionado == null && fac == null) {
            limpiarCampos();
        } else {
            cargarDatos();
            cargarRenglones();
        }
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
        tablaNotaCredito = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        jLabel8 = new javax.swing.JLabel();
        agregarBtn = new javax.swing.JButton();
        totalTxt = new javax.swing.JTextField();
        terminarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        buscarClienteBtn = new javax.swing.JButton();
        incorporarANotaCreditoBtn = new javax.swing.JButton();
        eliminarItemBtn = new javax.swing.JButton();
        buscarClienteXNombre = new javax.swing.JButton();
        buscarProductoXNombreBtn = new javax.swing.JButton();
        descuentoGlobalLbl = new javax.swing.JLabel();
        descuentoGlobalTxt = new javax.swing.JTextField();
        comboClientes = new javax.swing.JComboBox();
        comboProductos = new javax.swing.JComboBox();
        nombreClienteABuscarTxt = new javax.swing.JTextField();
        nombreProductoABuscarTxt = new javax.swing.JTextField();
        texto1PieNcTxt = new javax.swing.JTextField();
        texto2PieNcTxt = new javax.swing.JTextField();
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
        leerPrecioBtn = new javax.swing.JButton();
        nuevaCantidadTxt = new javax.swing.JTextField();
        nuevoPrecioTxt = new javax.swing.JTextField();
        grabarCantidadBtn = new javax.swing.JButton();
        grabarPrecioBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        importeMassalinTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        importeNoblezaTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        descuentoLineaTxt = new javax.swing.JTextField();
        descuentoBtn = new javax.swing.JButton();
        descuentoVolumenTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        imprimeChk = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("AyM - Nota Crédito Web");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tablaNotaCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cant", "Detalle", "P.Unit", "Gravado", "Impuesto", "IVA", "dto.", "SubTotal", "Sug."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        jScrollPane1.setViewportView(tablaNotaCredito);
        if (tablaNotaCredito.getColumnModel().getColumnCount() > 0) {
            tablaNotaCredito.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaNotaCredito.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablaNotaCredito.getColumnModel().getColumn(2).setPreferredWidth(330);
            tablaNotaCredito.getColumnModel().getColumn(3).setPreferredWidth(20);
            tablaNotaCredito.getColumnModel().getColumn(4).setPreferredWidth(25);
            tablaNotaCredito.getColumnModel().getColumn(5).setPreferredWidth(25);
            tablaNotaCredito.getColumnModel().getColumn(6).setPreferredWidth(25);
            tablaNotaCredito.getColumnModel().getColumn(7).setPreferredWidth(20);
            tablaNotaCredito.getColumnModel().getColumn(8).setPreferredWidth(30);
            tablaNotaCredito.getColumnModel().getColumn(9).setPreferredWidth(20);
        }

        jLabel1.setText("Cliente:");

        jLabel2.setText("Iva:");

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

        fechaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fechaTxt.setText("FECHA");

        ivaTxt.setText("IVA");

        jLabel8.setText("TOTAL NOTA CREDITO:");

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
        totalTxt.setForeground(new java.awt.Color(204, 0, 0));
        totalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTxt.setText("TOTAL");

        terminarBtn.setText("Terminar Nota Credito");
        terminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar NC");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        buscarClienteBtn.setText("Buscar");
        buscarClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteBtnActionPerformed(evt);
            }
        });

        incorporarANotaCreditoBtn.setText("Incorporar a NC");
        incorporarANotaCreditoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incorporarANotaCreditoBtnActionPerformed(evt);
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

        buscarProductoXNombreBtn.setText("Buscar x nombre");
        buscarProductoXNombreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProductoXNombreBtnActionPerformed(evt);
            }
        });

        descuentoGlobalLbl.setText("Descuento:");

        descuentoGlobalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentoGlobalTxt.setText("Descu");

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

        texto1PieNcTxt.setText("TEXTO 1 PIE FACTURA");

        texto2PieNcTxt.setText("TEXTO 2 PIE FACTURA");

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
        cantidadItemsTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadItemsTxtActionPerformed(evt);
            }
        });

        leerCantidadBtn.setText("leer Cantidad");
        leerCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerCantidadBtnActionPerformed(evt);
            }
        });

        leerPrecioBtn.setText("leer Precio");
        leerPrecioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerPrecioBtnActionPerformed(evt);
            }
        });

        nuevaCantidadTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevaCantidadTxt.setText("NUE CANT");

        nuevoPrecioTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevoPrecioTxt.setText("NUE PREC");
        nuevoPrecioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevoPrecioTxtKeyPressed(evt);
            }
        });

        grabarCantidadBtn.setText("Grabar Cantidad");
        grabarCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarCantidadBtnActionPerformed(evt);
            }
        });

        grabarPrecioBtn.setText("Grabar Precio");
        grabarPrecioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarPrecioBtnActionPerformed(evt);
            }
        });

        jLabel9.setText("Importe:");

        importeMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeMassalinTxt.setText("IMP MASS");

        jLabel10.setText("Importe:");

        importeNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeNoblezaTxt.setText("IMP NOBL");

        jLabel3.setText("Dscto:");

        descuentoLineaTxt.setText("DTO");

        descuentoBtn.setText("Dto");
        descuentoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoBtnActionPerformed(evt);
            }
        });

        descuentoVolumenTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentoVolumenTxt.setText("DESCUENTO");

        jLabel11.setText("Descuento:");

        imprimeChk.setText("Imprime");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(205, 205, 205)
                        .addComponent(nombreClienteABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarClienteXNombre)
                        .addGap(18, 18, 18)
                        .addComponent(comboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(texto1PieNcTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                                    .addComponent(texto2PieNcTxt)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(descuentoLineaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(incorporarANotaCreditoBtn))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(agregarBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(eliminarItemBtn))
                                            .addComponent(descuentoBtn))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imprimeChk)
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cancelarBtn)
                                    .addComponent(terminarBtn)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(75, 75, 75))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(249, 249, 249)
                                .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscarProductoXNombreBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
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
                                                .addGap(38, 38, 38)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(nombreProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(precioProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(76, 76, 76)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(leerPrecioBtn)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(grabarPrecioBtn))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGap(1, 1, 1)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(jLabel18)
                                                                    .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(leerCantidadBtn)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(grabarCantidadBtn))))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(117, 117, 117)
                                                                .addComponent(jLabel8)
                                                                .addGap(18, 18, 18))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jLabel11)
                                                                .addGap(48, 48, 48)))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(descuentoVolumenTxt)
                                                            .addComponent(totalTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel3))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscarClienteBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(razonSocialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(descuentoGlobalLbl)
                                .addGap(46, 46, 46)
                                .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(volverBtn))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                    .addComponent(buscarClienteBtn)
                    .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoGlobalLbl)
                    .addComponent(volverBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarClienteXNombre)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreClienteABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarProductoXNombreBtn)
                    .addComponent(jLabel6)
                    .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoVolumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
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
                    .addComponent(precioProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(descuentoLineaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoBtn)
                    .addComponent(grabarCantidadBtn)
                    .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leerCantidadBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarBtn)
                    .addComponent(eliminarItemBtn)
                    .addComponent(incorporarANotaCreditoBtn)
                    .addComponent(leerPrecioBtn)
                    .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarPrecioBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto1PieNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto2PieNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(terminarBtn)
                    .addComponent(imprimeChk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteBtnActionPerformed
        buscar();
        cancelarBtn.setEnabled(true);
    }//GEN-LAST:event_buscarClienteBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la NC?", "Atencion", JOptionPane.YES_NO_OPTION);
        // 0 = si; 1 = no
        if (escape == 0) {
//            if (totalNotaCredito > 0.00) {
//                guardarRepositorio();
//            }
            desbloquearCliente();
            abandonarNotaCredito();
        } else {
            prepararCampos();
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        eliminarItemBtn.setEnabled(false);
        texto1PieNcTxt.setEnabled(false);
        texto2PieNcTxt.setEnabled(false);
        codigoBarrasTxt.setText("");
        agregarProducto();
    }//GEN-LAST:event_agregarBtnActionPerformed

    private void incorporarANotaCreditoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incorporarANotaCreditoBtnActionPerformed
        if (!cantidadTxt.getText().isEmpty()) {
            if (!codigoBarrasTxt.getText().isEmpty()) {
                buscarProducto();
            } else {
                if (!codigoProductoTxt.getText().isEmpty()) {
                    buscarProducto();
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código producto");
                    codigoBarrasTxt.requestFocus();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad");
            cantidadTxt.requestFocus();
        }
    }//GEN-LAST:event_incorporarANotaCreditoBtnActionPerformed

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
                    Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboClientesActionPerformed

    private void buscarProductoXNombreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProductoXNombreBtnActionPerformed
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        llenarComboProductos();
        comboProductos.addFocusListener(null);
        comboProductos.showPopup();
    }//GEN-LAST:event_buscarProductoXNombreBtnActionPerformed

    private void comboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductosActionPerformed
        if (evt.getModifiers() == 16) {
            if (comboProductos.getSelectedIndex() > 0) {
                nombreProductoABuscarTxt.setText("");
                nombreProductoABuscarTxt.setEnabled(false);
                buscarProductoXNombreBtn.setEnabled(false);
                try {
                    Integer seleccion = comboProductos.getSelectedIndex();
                    productoSeleccionado = new ProductoService().getProductosByFiltroSin90(filtro).get(seleccion - 1);
                    codigoProductoTxt.setText(String.valueOf(productoSeleccionado.getCodigo()));
                    consultarProducto();
                    cantidadTxt.requestFocus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar producto");
                    Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboProductosActionPerformed

    private void terminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Quiere ingresar un texto antes de Imprimir?",
                "Texto en el pie de Nota de Crédito",
                JOptionPane.YES_NO_OPTION);
        if (escape == 0) {
            texto1PieNcTxt.setEnabled(true);
            texto2PieNcTxt.setEnabled(true);
            texto1PieNcTxt.requestFocus();
        } else {
            terminarNotaCredito();
        }
    }//GEN-LAST:event_terminarBtnActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void codigoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoTxt.getText().isEmpty()) {
                buscar();
                cancelarBtn.setEnabled(true);
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                volver();
            }
        }
    }//GEN-LAST:event_codigoTxtKeyPressed

    private void codigoProductoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoProductoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoProductoTxt.getText().isEmpty()) {
                consultarProducto();
                incorporarANotaCreditoBtn.setEnabled(false);
            } else {
                codigoBarrasTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la carga de NC?", "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape != 1) {
                    limpiarCampos();
                    bloquearCampos();
                    borrarTablaProductos();
                    volverBtn.setEnabled(true);
                    buscarClienteXNombre.setEnabled(true);
                    nombreClienteABuscarTxt.setEnabled(true);
                    comboClientes.setEnabled(true);
                    codigoTxt.requestFocus();
                } else {
                    codigoProductoTxt.setText("");
                    codigoProductoTxt.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_codigoProductoTxtKeyPressed

    private void cantidadTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!cantidadTxt.getText().isEmpty()) {
                if (!codigoBarrasTxt.getText().isEmpty()) {
                    buscarProducto();
                    leerCantidadBtn.setEnabled(true);
                    leerPrecioBtn.setEnabled(true);
                    nuevaCantidadTxt.setEnabled(true);
                    nuevoPrecioTxt.setEnabled(true);
                    agregarBtn.requestFocus();
                } else {
                    if (!codigoProductoTxt.getText().isEmpty()) {
                        buscarProducto();
                        leerCantidadBtn.setEnabled(true);
                        leerPrecioBtn.setEnabled(true);
                        nuevaCantidadTxt.setEnabled(true);
                        nuevoPrecioTxt.setEnabled(true);
                        agregarBtn.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código producto");
                        codigoBarrasTxt.requestFocus();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad");
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
                codigoBarrasTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_cantidadTxtKeyPressed

    private void eliminarItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarItemBtnActionPerformed
        int selectRow = tablaNotaCredito.getSelectedRow();
        if (selectRow != -1) {
            int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de Eliminar Item?", "Atencion", JOptionPane.YES_NO_OPTION);
            // 0 = si; 1 = no
            if (escape == 0) {
                tabla.removeRow(selectRow);
                renglonNotaCredito.remove(selectRow);
                calcularTotales();
                agregarBtn.requestFocus();
                if (nro > 0) {
                    terminarBtn.setEnabled(true);
                } else {
                    terminarBtn.setEnabled(false);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto para eliminar");
        }
    }//GEN-LAST:event_eliminarItemBtnActionPerformed

    private void agregarBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agregarBtnKeyPressed
        eliminarItemBtn.setEnabled(false);
        texto1PieNcTxt.setEnabled(false);
        texto2PieNcTxt.setEnabled(false);
        codigoBarrasTxt.setText("");

        agregarProducto();
    }//GEN-LAST:event_agregarBtnKeyPressed

    private void nombreClienteABuscarTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreClienteABuscarTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
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
                consultarProductoBarras();
                incorporarANotaCreditoBtn.setEnabled(true);
                cantidadTxt.requestFocus();
            } else {
                nombreProductoABuscarTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la NC?", "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape == 0) {
                    limpiarCampos();
                    bloquearCampos();
                    borrarTablaProductos();
                    volverBtn.setEnabled(true);
                    codigoTxt.requestFocus();
                } else {
                    codigoProductoTxt.setText("");
                    codigoBarrasTxt.setText("");
                    codigoBarrasTxt.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_codigoBarrasTxtKeyPressed

    private void comboProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (comboProductos.getSelectedIndex() > 0) {
                nombreProductoABuscarTxt.setText("");
                nombreProductoABuscarTxt.setEnabled(false);
                buscarProductoXNombreBtn.setEnabled(false);
                try {
                    Integer seleccion = comboProductos.getSelectedIndex();
                    productoSeleccionado = new ProductoService().getProductosByFiltroSin90(filtro).get(seleccion - 1);
                    codigoProductoTxt.setText(String.valueOf(productoSeleccionado.getCodigo()));
                    consultarProducto();
                    cantidadTxt.requestFocus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar producto");
                    Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboProductosKeyPressed

    private void leerCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerCantidadBtnActionPerformed
        int lin = tablaNotaCredito.getSelectedRow();
        if (lin > -1) {
            RenglonNotaCredito rf = renglonNotaCredito.get(lin);
            nuevaCantidadTxt.setText(String.valueOf(rf.getCantidad().intValue()));
            leerCantidadBtn.setEnabled(false);
            grabarCantidadBtn.setEnabled(true);
            grabarPrecioBtn.setEnabled(false);
            leerPrecioBtn.setEnabled(false);
            nuevoPrecioTxt.setEnabled(false);
            terminarBtn.setEnabled(false);
            tablaNotaCredito.setEnabled(false);
            agregarBtn.setEnabled(false);
            eliminarItemBtn.setEnabled(false);
        }
    }//GEN-LAST:event_leerCantidadBtnActionPerformed

    private void leerPrecioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerPrecioBtnActionPerformed
        leerPrecio();
    }//GEN-LAST:event_leerPrecioBtnActionPerformed

    private void grabarCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarCantidadBtnActionPerformed
        int lin = tablaNotaCredito.getSelectedRow();
        if (lin > -1) {
            if (!nuevaCantidadTxt.getText().isEmpty()) {
                leerCantidadBtn.setEnabled(true);
                grabarCantidadBtn.setEnabled(false);
                RenglonNotaCredito rf = renglonNotaCredito.get(lin);
                Float cantidadAnterior = rf.getCantidad();
                Float cant = Float.valueOf(nuevaCantidadTxt.getText());
                rf.setCantidad(cant);
                if (!(cant > 0.0)) {
                    JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                    cantidadTxt.requestFocus();
                    return;
                }
                Double precioUnitario = rf.getGravado() / cantidadAnterior;
                Double impuestoUnitario = rf.getImpuesto() / cantidadAnterior;
                renglonNotaCredito.set(lin, rf);
                tablaNotaCredito.setValueAt(cant.intValue(), lin, 1);
                // en unidad
                dto = 0.0;
                calcularLinea(cant, precioUnitario, impuestoUnitario, rf.getProducto());

                tablaNotaCredito.setValueAt(df.format(precioFinal), lin, 3);
                nuevaCantidadTxt.setText("");

                rf.setGravado(gravado);
                rf.setImpuesto(impuesto);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaNotaCredito.setValueAt(df.format(gravado), lin, 4);
                tablaNotaCredito.setValueAt(df.format(impuesto), lin, 5);
                tablaNotaCredito.setValueAt(df.format(iva), lin, 6);
                tablaNotaCredito.setValueAt(df.format(totalLinea), lin, 8);
                leerPrecioBtn.setEnabled(true);
                nuevoPrecioTxt.setEnabled(true);
                agregarBtn.setEnabled(true);
                eliminarItemBtn.setEnabled(true);
                terminarBtn.setEnabled(true);
                tablaNotaCredito.setEnabled(true);
                calcularTotales();
                agregarBtn.requestFocus();
            }
        }
    }//GEN-LAST:event_grabarCantidadBtnActionPerformed

    private void grabarPrecioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarPrecioBtnActionPerformed
        int lin = tablaNotaCredito.getSelectedRow();
        if (lin > -1) {
            if (!nuevoPrecioTxt.getText().isEmpty()) {
                Double test = Double.valueOf(nuevoPrecioTxt.getText().replaceAll("\\,", "\\."));
                if (test < 0.00) {
                    JOptionPane.showMessageDialog(this, "Ingrese importe en positivo");
                    return;
                }
                leerPrecioBtn.setEnabled(true);
                grabarPrecioBtn.setEnabled(false);
                leerCantidadBtn.setEnabled(true);
                RenglonNotaCredito rf = renglonNotaCredito.get(lin);
                Float cant = rf.getCantidad();
                Double impuestoUnitario = rf.getImpuesto() / cant;
                Double nuevoImporte = Double
                        .valueOf(nuevoPrecioTxt.getText()
                                .replaceAll("\\,", "\\."))
                        / (1 + (porcentualIva / 100));
                nuevoImporte = nuevoImporte * -1;
                dto = 0.0;
                calcularLinea(cant, nuevoImporte, impuestoUnitario, rf.getProducto());
                rf.setGravado(gravado);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaNotaCredito.setValueAt(df.format(-precioFinal), lin, 3);
                tablaNotaCredito.setValueAt(df.format(gravado), lin, 4);
                if (impuesto < 0.00) {
                    tablaNotaCredito.setValueAt(df.format(impuesto), lin, 5);
                } else {
                    tablaNotaCredito.setValueAt(df.format(0.0), lin, 5);
                }
                tablaNotaCredito.setValueAt(df.format(iva), lin, 6);
                tablaNotaCredito.setValueAt(df.format(totalLinea), lin, 8);
                tablaNotaCredito.setEnabled(true);
                nuevoPrecioTxt.setText("");
                terminarBtn.setEnabled(true);
                nuevaCantidadTxt.setEnabled(true);
                leerCantidadBtn.setEnabled(true);
                nuevoPrecioTxt.setEnabled(true);
                agregarBtn.setEnabled(true);
                eliminarItemBtn.setEnabled(true);
                calcularTotales();
                tablaNotaCredito.requestFocus();
            }
        }
    }//GEN-LAST:event_grabarPrecioBtnActionPerformed

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
                    Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboClientesKeyPressed

    private void descuentoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoBtnActionPerformed
        int row = tablaNotaCredito.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un Producto");
            return;
        }
        if (renglonNotaCredito.get(row).getDescuento() > 0.00) {
            JOptionPane.showMessageDialog(this, "DESCUENTO YA REALIZADO");
            return;
        }
        Producto p = renglonNotaCredito.get(row).getProducto();
//        if (p.getIvaCero()) {
//            JOptionPane.showMessageDialog(this, "DESCUENTO NO HABILITADO");
//            return;
//        }
        if (p.getLlevaDto() != null) {
            if (p.getLlevaDto()) {
                JOptionPane.showMessageDialog(this, "DESCUENTO NO PERMITIDO");
                return;
            }
        }
        if (p != null) {
            Rubro ru = p.getRubro();
            int cR = ru.getCodigo();
            if (cR != 5) {
                JOptionPane.showMessageDialog(this, "DESCUENTO NO PERMITIDO");
                return;
            }
            if (habilitado()) {
                descuentoLineaTxt.setEnabled(true);
                descuentoBtn.setEnabled(false);
                tablaNotaCredito.setEnabled(false);
                descuentoLineaTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_descuentoBtnActionPerformed

    private void cantidadItemsTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadItemsTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadItemsTxtActionPerformed

    private void nuevoPrecioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevoPrecioTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            int lin = tablaNotaCredito.getSelectedRow();
            if (lin > -1) {
                if (!nuevoPrecioTxt.getText().isEmpty()) {
                    Double test = Double.valueOf(nuevoPrecioTxt.getText().replaceAll("\\,", "\\."));
                    if (test < 0.00) {
                        JOptionPane.showMessageDialog(this, "Ingrese importe en positivo");
                        return;
                    }
                    leerPrecioBtn.setEnabled(true);
                    grabarPrecioBtn.setEnabled(false);
                    leerCantidadBtn.setEnabled(true);
                    RenglonNotaCredito rf = renglonNotaCredito.get(lin);
                    Float cant = rf.getCantidad();
                    Double impuestoUnitario = rf.getImpuesto() / cant;
                    Double nuevoImporte = Double
                            .valueOf(nuevoPrecioTxt.getText()
                                    .replaceAll("\\,", "\\."))
                            / (1 + (porcentualIva / 100));
                    nuevoImporte = nuevoImporte * -1;
                    dto = 0.0;
                    calcularLinea(cant, nuevoImporte, impuestoUnitario, rf.getProducto());
                    rf.setGravado(gravado);
                    rf.setIva(iva);
                    rf.setTotal(totalLinea);
                    tablaNotaCredito.setValueAt(df.format(-precioFinal), lin, 3);
                    tablaNotaCredito.setValueAt(df.format(gravado), lin, 4);
                    if (impuesto < 0.00) {
                        tablaNotaCredito.setValueAt(df.format(impuesto), lin, 5);
                    } else {
                        tablaNotaCredito.setValueAt(df.format(0.0), lin, 5);
                    }
                    tablaNotaCredito.setValueAt(df.format(iva), lin, 6);
                    tablaNotaCredito.setValueAt(df.format(totalLinea), lin, 8);
                    tablaNotaCredito.setEnabled(true);
                    nuevoPrecioTxt.setText("");
                    terminarBtn.setEnabled(true);
                    nuevaCantidadTxt.setEnabled(true);
                    leerCantidadBtn.setEnabled(true);
                    nuevoPrecioTxt.setEnabled(true);
                    agregarBtn.setEnabled(true);
                    eliminarItemBtn.setEnabled(true);
                    calcularTotales();
                    tablaNotaCredito.requestFocus();
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
            java.util.logging.Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotaCreditoWebFrame(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton buscarClienteBtn;
    private javax.swing.JButton buscarClienteXNombre;
    private javax.swing.JButton buscarProductoXNombreBtn;
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
    private javax.swing.JButton descuentoBtn;
    private javax.swing.JLabel descuentoGlobalLbl;
    private javax.swing.JTextField descuentoGlobalTxt;
    private javax.swing.JTextField descuentoLineaTxt;
    private javax.swing.JTextField descuentoVolumenTxt;
    private javax.swing.JButton eliminarItemBtn;
    private javax.swing.JTextField fechaTxt;
    private javax.swing.JButton grabarCantidadBtn;
    private javax.swing.JButton grabarPrecioBtn;
    private javax.swing.JTextField importeMassalinTxt;
    private javax.swing.JTextField importeNoblezaTxt;
    private javax.swing.JCheckBox imprimeChk;
    private javax.swing.JButton incorporarANotaCreditoBtn;
    private javax.swing.JTextField ivaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTable tablaNotaCredito;
    private javax.swing.JButton terminarBtn;
    private javax.swing.JTextField texto1PieNcTxt;
    private javax.swing.JTextField texto2PieNcTxt;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        texto1PieNcTxt.setText("");
        texto2PieNcTxt.setText("");
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

        totalTxt.setText("0.00");
        comboClientes.removeAllItems();
        comboClientes.addItem("");
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        nombreClienteABuscarTxt.setText("");
        nombreProductoABuscarTxt.setText("");

        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        nombreProductoConsultaTxt.setText("");
        precioProductoConsultaTxt.setText("");
        cantidadItemsTxt.setText("");
        descuentoGlobalTxt.setText("");
        nuevoPrecioTxt.setText("");
        nuevaCantidadTxt.setText("");
        importeNoblezaTxt.setText("");
        importeMassalinTxt.setText("");

        imprimeChk.setSelected(true);
        descuentoLineaTxt.setText("");
        descuentoGlobalTxt.setText("");
        descuentoVolumenTxt.setText("");
    }

    private void bloquearCampos() {
        agregarBtn.setEnabled(false);
        cancelarBtn.setEnabled(false);
        terminarBtn.setEnabled(false);
        incorporarANotaCreditoBtn.setEnabled(false);
        buscarProductoXNombreBtn.setEnabled(false);
        eliminarItemBtn.setEnabled(false);
        ivaTxt.setEditable(false);
        codigoBarrasTxt.setEnabled(false);
        codigoTxt.setEnabled(true);
        codigoTxt.setEditable(true);
        codigoProductoTxt.setEnabled(false);
        fechaTxt.setEditable(false);
        razonSocialTxt.setEditable(false);
        cantidadTxt.setEnabled(false);
        totalTxt.setEnabled(false);

        nombreProductoABuscarTxt.setEnabled(false);
        nombreProductoConsultaTxt.setEnabled(false);
        comboProductos.setEnabled(false);
        precioProductoConsultaTxt.setEnabled(false);
        cantidadItemsTxt.setEnabled(false);
        texto1PieNcTxt.setEnabled(false);
        texto2PieNcTxt.setEnabled(false);
        cantidadAtadosMassalinTxt.setEditable(false);
        cantidadAtadosNoblezaTxt.setEditable(false);
        descuentoGlobalTxt.setEditable(false);
        leerCantidadBtn.setEnabled(false);
        leerPrecioBtn.setEnabled(false);
        nuevaCantidadTxt.setEnabled(false);
        nuevoPrecioTxt.setEnabled(false);
        grabarCantidadBtn.setEnabled(false);
        grabarPrecioBtn.setEnabled(false);
        importeNoblezaTxt.setEditable(false);
        importeMassalinTxt.setEditable(false);
    }

    private void llenarComboClientes() {
        filtro = nombreClienteABuscarTxt.getText();
        clientes = new ArrayList<Cliente>();
        try {
            clientes = new ClienteService().getClientesByFiltro(filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - leyendo Clientes");
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboClientes.getModel();
        if (clientes != null && !clientes.isEmpty()) {
            for (Cliente cli : clientes) {
                model.addElement(cli.getRazonSocial());
            }
            comboClientes.setModel(model);
        }
    }

    private void llenarComboProductos() {
        filtro = nombreProductoABuscarTxt.getText();
        List<Producto> productos = new ArrayList<Producto>();
        try {
            productos = new ProductoService().getProductosByFiltroSin90(filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - leyendo Productos");
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboProductos.getModel();
        if (productos != null && !productos.isEmpty()) {
            for (Producto pro : productos) {
                model.addElement(pro.getDetalle());
            }
            comboProductos.setModel(model);
        }
    }

    private void bloquearCliente() {
        String cod = clienteNotaCredito.getCodigo();
        ClienteTraba ct = null;
        CustomerTraba cuTr = null;
        try {
            ct = new ClienteTrabaService().getClienteByCodigo(cod);
            cuTr = new CustomerTrabaService().getClienteByCodigo(cod);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ct.setTraba1(true);
        cuTr.setTraba2(true);
        try {
            new ClienteTrabaService().updateCliente(ct);
            new CustomerTrabaService().updateCliente(cuTr);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al bloquear Cliente");
        }
    }

    private void desbloquearCliente() {
        String cod = clienteNotaCredito.getCodigo();
        ClienteTraba ct = null;
        CustomerTraba cuTr = null;
        try {
            ct = new ClienteTrabaService().getClienteByCodigo(cod);
            cuTr = new CustomerTrabaService().getClienteByCodigo(cod);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ct.setTraba1(false);
        cuTr.setTraba2(false);
        try {
            new ClienteTrabaService().updateCliente(ct);
            new CustomerTrabaService().updateCliente(cuTr);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al desbloquear Cliente");
        }
    }

    private void buscar() {
        filtro = "";
        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        fecha = Calendar.getInstance().getTime();
        //Cliente cli = new Cliente();
        categoriaIva = 4;
        try {
            clienteNotaCredito = new ClienteService().getClienteByCodigo(codigoTxt.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - cliente");
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        if (clienteNotaCredito != null) {
            String cod = clienteNotaCredito.getCodigo();
            ClienteTraba ct = null;
            CustomerTraba cuTr = null;
            try {
                ct = new ClienteTrabaService().getClienteByCodigo(cod);
                cuTr = new CustomerTrabaService().getClienteByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ct.getTraba1() != null) {
                if (ct.getTraba1()) {
                    JOptionPane.showMessageDialog(this, "Cliente bloqueado para esta Operación.");
                    return;
                }
            }
            if (cuTr.getTraba2() != null) {
                if (cuTr.getTraba2()) {
                    JOptionPane.showMessageDialog(this, "Cliente bloqueado para esta Operación.");
                    return;
                }
            }
            if (clienteNotaCredito.getSaldo() != null) {
                saldoCliente = clienteNotaCredito.getSaldo();
            } else {
                saldoCliente = 0.0;
            }
            String s0 = df.format(saldoCliente);
            Double s1 = Double.valueOf(s0.replace(",", "."));
            if (s1 < 0.01) {
                int s3 = JOptionPane.showConfirmDialog(this, "SALDO: " + s0 + "\nVerifique el saldo antes de CONFIRMAR!!", "Atención", JOptionPane.YES_NO_OPTION);
                if (s3 == 1) {
                    return;
                }
            }
            bloquearCliente();
            if (clienteNotaCredito.getDescuento() != null) {
                descuentoCliente = clienteNotaCredito.getDescuento();
            } else {
                descuentoCliente = 0F;
            }
            razonSocialTxt.setText(clienteNotaCredito.getRazonSocial());
            if (clienteNotaCredito.getDescuento() != null) {
                descuentoGlobalTxt.setText(df2.format(clienteNotaCredito.getDescuento()));
                descuento = clienteNotaCredito.getDescuento();
            } else {
                descuentoGlobalTxt.setText(df2.format(0F));
                descuento = 0F;
            }
            fechaTxt.setText(sdf.format(fecha));
            if (clienteNotaCredito.getCategoriaDeIva() != null) {
                if (clienteNotaCredito.getCategoriaDeIva() == 1) {
                    ivaTxt.setText("Resp. Inscripto");
                }
                if (clienteNotaCredito.getCategoriaDeIva() == 2) {
                    ivaTxt.setText("Monotributo");
                }
                if (clienteNotaCredito.getCategoriaDeIva() == 4) {
                    ivaTxt.setText("Consumidor Final");
                }
                categoriaIva = clienteNotaCredito.getCategoriaDeIva();
            } else {
                ivaTxt.setText("Consumidor Final");
            }
            descuentoGlobalTxt.setText(df.format(descuentoCliente));
            Boolean terminar = false;
            agregarBtn.setEnabled(true);
            buscarClienteBtn.setEnabled(false);
            volverBtn.setEnabled(false);
            cancelarBtn.setEnabled(true);
            buscarClienteXNombre.setEnabled(false);
            nombreClienteABuscarTxt.setEnabled(false);
            comboClientes.setEnabled(false);
            nombreProductoConsultaTxt.setEditable(false);
            precioProductoConsultaTxt.setEditable(false);
            codigoTxt.setEditable(false);
            totalTxt.setEnabled(true);
            totalTxt.setEditable(false);
            agregarProducto();
        } else {
            JOptionPane.showMessageDialog(this, "Error - cliente no existe");
            buscarClienteBtn.setEnabled(true);
            codigoTxt.requestFocus();
        }
    }

    private void terminarNotaCredito() {
        numCae = "0";
        sucursalNotaCreditoPapel = "0";
        numeroNotaCreditoPapel = "0";
        letraNotaCreditoPapel = "L";
        if (tst == 0) {
            // nc web inicio
            // presentacion web
            try {
                LibraryLoader.loadJacobLibrary();
                // Crear objeto WSAA: Web Service de Autenticación y Autorización 
                ActiveXComponent wsaa = new ActiveXComponent("WSAA");
                System.out.println(Dispatch.get(wsaa, "InstallDir").toString()
                        + " "
                        + Dispatch.get(wsaa, "Version").toString()
                );
                // Solicitar Ticket de Acceso a AFIP (cambiar URL producción) 
                String wsdl = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
                //https://servicios1.afip.gov.ar/wsfev1/service.asmx
                //String userdir = System.getProperty("user.dir");
                String userdir = "c:/certifaym";
                ///AliasVentas_fbd285a15e3f318.crt
                ///certificado_jose_wsaa.crt
                Dispatch.call(wsaa, "Autenticar",
                        new Variant("wsfe"),
                        //new Variant(userdir + "/NUEVO_1d6437bd48efa031.crt"),
                        //new Variant(userdir + "/clave_privada_20124127581_201611015439.key"),
                        new Variant(userdir + "/aym2020_4232d12f5f615fc0.crt"),
                        new Variant(userdir + "/clave_privada_20124127581_202010294251.key"),
                        /*
                        new Variant(userdir + "/nuevo_2018_52c1402efa235ce.crt"),
                        new Variant(userdir + "/clave_privada_20124127581_201811014935.key"),
                         */
                        new Variant(wsdl));
                String excepcion = Dispatch.get(wsaa, "Excepcion").toString();
                //System.out.println("Excepcion: " + excepcion);
                String token = Dispatch.get(wsaa, "Token").toString();
                String sign = Dispatch.get(wsaa, "Sign").toString();
                //System.out.println("Token: " + token + "Sign: " + sign);

                // Instanciar WSFEv1: WebService de Factura Electrónica version 1 
                ActiveXComponent wsfev1 = new ActiveXComponent("WSFEv1");

                // Establecer parametros de uso: 
                Dispatch.put(wsfev1, "Cuit", new Variant("20124127581"));
                Dispatch.put(wsfev1, "Token", new Variant(token));
                Dispatch.put(wsfev1, "Sign", new Variant(sign));

                // Conectar al websrvice (cambiar URL para producción) 
                String cache = "";
                wsdl = "https://servicios1.afip.gov.ar/wsfev1/service.asmx?WSDL";
                //https://servicios1.afip.gov.ar/wsfev1/service.asmx?WSDL
                Dispatch.call(wsfev1, "Conectar",
                        new Variant(cache),
                        new Variant(wsdl)
                );
//            Dispatch.call(wsfev1, "LoginCMS",
//                    new Variant(wsaa)
//            );
                // Consultar último comprobante autorizado en AFIP 
                String tipo_cbte = "1";
                if (categoriaIva.equals(1)) {
                    tipo_cbte = "3"; //Nc A
                    letraNotaCreditoPapel = "A";
                } else {
                    tipo_cbte = "8"; //Nc B
                    letraNotaCreditoPapel = "B";
                }

                String pto_vta = "5"; // Sucursal declarada WS
                sucursalNotaCreditoPapel = "0005";
                Variant ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                        new Variant(tipo_cbte),
                        new Variant(pto_vta));
//            System.out.println("Ultimo comprobante: " + ult.toString());
                excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
//            System.out.println("Excepcion: " + excepcion);

                // CAE 
                String fechaWs = new SimpleDateFormat("yyyyMMdd").format(new Date());
                String concepto = "1";// producto 
//            System.out.println(clienteNotaCredito.getCuit());
                String cui = clienteNotaCredito.getCuit();
                String cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
//            System.out.println(cuit1);
                String tipoD = clienteNotaCredito.getTipo();
                String tipo_doc = tipoD, nro_doc = cuit1; //tipo y numero
                int cbte_nro = Integer.parseInt(ult.toString()) + 1,
                        cbt_desde = cbte_nro,
                        cbt_hasta = cbte_nro;
                numeroNotaCredito = cbte_nro;
                numeroNotaCreditoPapel = String.valueOf(cbte_nro);
                int largo = ("00000000" + numeroNotaCreditoPapel).length();
                numeroNotaCreditoPapel = ("00000000" + numeroNotaCreditoPapel).substring(largo - 8, largo);
//            System.out.println(df.format(-totalNotaCredito).toString().replaceAll("\\,", "\\."));
                String imp_total = df.format(-totalNotaCredito).toString().replaceAll("\\,", "\\.");//"124.00";
                String imp_tot_conc = "0.00";
                String imp_neto = df.format(-totalGravado - totalNoGravado).toString().replaceAll("\\,", "\\.");
                String imp_iva = df.format(-totalIva).toString().replaceAll("\\,", "\\.");//"21.00"
                int internos = (int) rint(totalImpuesto * 100);
//            System.out.println(internos);
                String imp_trib = "", imp_op_ex = "0.00";
                if (internos < 0) {
                    imp_trib = df.format(-totalImpuesto).toString().replaceAll("\\,", "\\.");
                    //String.valueOf(totalImpuesto);
                } else {
                    imp_trib = "0.00";
                }
                String fecha_cbte = fechaWs, fecha_venc_pago = "";
                // Fechas período del servicio facturado (solo si concepto> 1) 
                String fecha_serv_desde = "", fecha_serv_hasta = "";
                String moneda_id = "PES", moneda_ctz = "1.000";
//System.exit(0);
                Variant ok = Dispatch.call(wsfev1, "CrearFactura",
                        new Variant(concepto), new Variant(tipo_doc),
                        new Variant(nro_doc), new Variant(tipo_cbte),
                        new Variant(pto_vta),
                        new Variant(cbt_desde), new Variant(cbt_hasta),
                        new Variant(imp_total), new Variant(imp_tot_conc),
                        new Variant(imp_neto), new Variant(imp_iva),
                        new Variant(imp_trib), new Variant(imp_op_ex),
                        new Variant(fecha_cbte), new Variant(fecha_venc_pago),
                        new Variant(fecha_serv_desde), new Variant(fecha_serv_hasta),
                        new Variant(moneda_id), new Variant(moneda_ctz));

                if (internos < 0) {
                    Variant tributo_id = new Variant(4),
                            tributo_desc = new Variant("Impuestos internos"),
                            tributo_base_imp = new Variant("0.00"),
                            tributo_alic = new Variant("0.00"),
                            tributo_importe = new Variant(df.format(-totalImpuesto).toString().replaceAll("\\,", "\\."));
                    Dispatch.call(wsfev1, "AgregarTributo",
                            tributo_id, tributo_desc, tributo_base_imp,
                            tributo_alic, tributo_importe);
                }
                // Agrego tasas de IVA 
                Variant iva_id = new Variant(5), // 21% 
                        iva_base_imp = new Variant(df.format(-totalGravado).toString().replaceAll("\\,", "\\.")),
                        iva_importe = new Variant(df.format(-totalIva).toString().replaceAll("\\,", "\\."));
                Dispatch.call(wsfev1, "AgregarIva",
                        iva_id, iva_base_imp, iva_importe);
                if (totalNoGravado < 0) {
                    iva_id = new Variant(3); // 0%
                    iva_base_imp = new Variant(df.format(-totalNoGravado).toString().replaceAll("\\,", "\\."));
                    iva_importe = new Variant(df.format(0.0).toString().replaceAll("\\,", "\\."));
                    Dispatch.call(wsfev1, "AgregarIva",
                            iva_id, iva_base_imp, iva_importe);
                }
                // Habilito reprocesamiento automático (predeterminado): 
                Dispatch.put(wsfev1, "Reprocesar", new Variant(false));

                // Solicito CAE (llamando al webservice de AFIP): 
                Variant cae = Dispatch.call(wsfev1, "CAESolicitar");
//            Variant vto = Dispatch.call(wsfev1, "Vencimiento");
//            System.out.println(vto);
                // Mostrar mensajes XML enviados y recibidos (depuración) 
                String requ = Dispatch.get(wsfev1, "XmlRequest").toString();
                String resp = Dispatch.get(wsfev1, "XmlResponse").toString();
//            System.out.println("XmlRequest: " + requ);
//            System.out.println("XmlResponse: " + resp);

                excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
//            System.out.println("Excepcion: " + excepcion);

                String errmsg = Dispatch.get(wsfev1, "ErrMsg").toString();
//            System.out.println("ErrMsg: " + errmsg);
                String obs = Dispatch.get(wsfev1, "Obs").toString();
                String vto = Dispatch.get(wsfev1, "Vencimiento").toString();

//            System.out.println("Obs: " + obs);
//            System.out.println(vto);
                // datos devueltos 
//            System.out.println("CAE: " + cae.toString());
                String resultado = Dispatch.get(wsfev1, "Resultado").toString();
//            System.out.println("Resultado: " + resultado);
                numCae = cae.toString();
                if (!resultado.equals("A")) {
                    JOptionPane.showMessageDialog(this, "Obs: " + obs + "\nError: " + errmsg);
                    return;
                }
                if (vto != "" && vto != null) {
                    vencCae = vto.substring(6, 8) + "/" + vto.substring(4, 6) + "/" + vto.substring(0, 4);
                }
                //numCae = cae.toString();
                String ruta1 = "c:/comprobantes/" + cae + ".xm1";
                String ruta2 = "c:/comprobantes/" + cae + ".xm2";
                File archivo1 = new File(ruta1);
                File archivo2 = new File(ruta2);
                BufferedWriter bw1, bw2;
                bw1 = new BufferedWriter(new FileWriter(archivo1));
                bw2 = new BufferedWriter(new FileWriter(archivo2));
                bw1.write(requ);
                bw2.write(resp);
                bw1.close();
                bw2.close();
                int x = 0;
                Integer suma1 = 0;
                Integer suma2 = 0;
                String cadena = cuit1 + "0" + tipo_cbte + "0005" + cae + vto;
//                System.out.println(cadena);
                for (int i = 0; i < 39; i++) {
                    if (x == 0) {
//                        System.out.println(cadena.substring(i, i + 1).toString());
                        int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                        suma1 += num;
                        x = 1;
                    } else {
//                        System.out.println(cadena.substring(i, i + 1).toString());
                        int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                        suma2 += num;
                        x = 0;
                    }
                }
                suma1 = suma1 * 3;
                int total = suma1 + suma2;
                int dv = (int) (rint(total / 10 + .9) * 10);
                dv = dv - total;
                cadena += String.valueOf(dv);
                String txtCadenaRP = "";
                for (int i = 0; i < 40; i = i + 2) {
                    String charNum = cadena.substring(i, i + 2);
                    int numChar = Integer.valueOf(charNum);
                    if (numChar < 50) {
                        numChar += 48;
                    } else {
                        numChar += 142;
                    }
                    char c = (char) numChar;
                    txtCadenaRP = txtCadenaRP + c;
                }
                txtCadenaRP = (char) 40 + txtCadenaRP + (char) 41;
                texto2Cae = txtCadenaRP;
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: 1641");
                return;
            }
            // fin nc web
        }
        Integer categoriaIva = 0;
        IvaVentas ivaVentas = new IvaVentas();
        Vendedor v = null;
        if (clienteNotaCredito.getVendedor() != null) {
            v = clienteNotaCredito.getVendedor();
        } else {
            try {
                v = new VendedorService().getVendedorByCodigo(99);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        categoriaIva = clienteNotaCredito.getCategoriaDeIva();
        saldoCliente = clienteNotaCredito.getSaldo();
        saldoCliente += totalNotaCredito;
        clienteNotaCredito.setSaldo(saldoCliente);
        clienteNotaCredito.setImporteMostrador(0.0);
        try {
            new ClienteService().updateCliente(clienteNotaCredito);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1656");
            return;
        }
        desbloquearCliente();
        Long id = (long) 1;
        try {
            config = new ConfiguracionService().getFacturas(id);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1664");
            return;
        }
        config.setUltimaFechaSistema(fecha);
        if (categoriaIva.equals(1)) {
            letraNotaCredito = "A";
            // es inscriptp
            sucursalNotaCredito = config.getSucursalA();
//            numeroNotaCredito = config.getNumeroFacturaA();
//            numeroNotaCredito += 1;
            config.setNumeroFacturaA(numeroNotaCredito);
            try {
                config = new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1678");
                return;
            }
            ivaVentas.setCodigoTipoDoc(3);
        } else {
            letraNotaCredito = "B";
            // el resto de las categorias
            sucursalNotaCredito = config.getSucursalB();
//            numeroNotaCredito = config.getNumeroFacturaB();
//            numeroNotaCredito += 1;
            config.setNumeroFacturaB(numeroNotaCredito);
            try {
                config = new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1692");
                return;
            }
            ivaVentas.setCodigoTipoDoc(8);
        }
        ivaVentas.setCliente(clienteNotaCredito);
        ivaVentas.setPorcentualDescuentoGlobal(descuentoCliente);
        ivaVentas.setVendedor(v);
        ivaVentas.setDescuentoGlobal(0.0);
        ivaVentas.setExento(0.0);
        ivaVentas.setFecha(fecha);
        try {
            ivaVentas.setFechaCae(sdf.parse(vencCae));
        } catch (ParseException ex) {
            ivaVentas.setFechaCae(fecha);
        }
        ivaVentas.setCae(Long.valueOf(numCae));
        ivaVentas.setGravado(totalGravado);
        ivaVentas.setImpuesto(totalImpuesto);
        ivaVentas.setIva(totalIva);
        ivaVentas.setNoGravado(0.0);
        ivaVentas.setTotal(totalNotaCredito);
        ivaVentas.setLetra(letraNotaCredito);
        ivaVentas.setNumeroSucursal(sucursalNotaCredito);
        ivaVentas.setNumeroFactura(Integer.valueOf(numeroNotaCreditoPapel));
        if (texto1PieNcTxt.getText().isEmpty()) {
            ivaVentas.setTextoPieFactura1("");
        } else {
            ivaVentas.setTextoPieFactura1(texto1PieNcTxt.getText());
        }
        if (texto2PieNcTxt.getText().isEmpty()) {
            ivaVentas.setTextoPieFactura2("");
        } else {
            ivaVentas.setTextoPieFactura2(texto2PieNcTxt.getText());
        }
        ivaVentas.setHd(false);
        for (RenglonNotaCredito reFa : renglonNotaCredito) {
            reFa.setIvaVentas(ivaVentas);
            Integer cod = reFa.getProducto().getCodigo();
            try {
                producto = new ProductoService().getProductoByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1877");
                return;
            }
            Float stock = 0.0F;
            if (producto.getStock() != null) {
                stock = producto.getStock();
            } else {
                stock = 0.0F;
            }
            reFa.setProducto(producto);
            stock += reFa.getCantidad();
            producto.setStock(stock);
            try {
                new ProductoService().updateProducto(producto);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1893");
                return;
            }
        }
        try {
            new NotaCreditoService().saveFactura(ivaVentas, renglonNotaCredito);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1901");
            return;
        }
        CtaCteCliente ccc = new CtaCteCliente();
        ccc.setCliente(clienteNotaCredito);
        ccc.setNotaCredito(ivaVentas);
        ccc.setFecha(fecha);
        ccc.setDebe(0.0);
        ccc.setHaber(-totalNotaCredito);
        ccc.setTipo("NC");
        ccc.setSaldo(saldoCliente);
        try {
            new CtaCteClienteService().saveCtaCteCliente(ccc);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1760");
            return;
        }
        generarNotaCredito();
        volver();
    }

    private void generarNotaCredito() {
        renglones = new String[maxNro];
        textoNotaCreditoPapel = "Nota de Crédito";
        fechaNotaCreditoPapel = sdf.format(fecha);
        clienteNotaCreditoPapel = razonSocialTxt.getText();
        codigoClienteNotaCreditoPapel = clienteNotaCredito.getCodigo();
        direccionNotaCreditoPapel = clienteNotaCredito.getDomicilio().getCalle() + " " + clienteNotaCredito.getDomicilio().getNumero() + " - " + clienteNotaCredito.getDomicilio().getLocalidad();
        cuitNotaCreditoPapel = clienteNotaCredito.getCuit();
        String condVta = "";
        Date fechaVto = fecha;
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        if (clienteNotaCredito.getFormaDePago().equals(1)) {
            condVta = "CONTADO               ";
        }
        if (clienteNotaCredito.getFormaDePago().equals(2)) {
            condVta = "7 DIAS F.F            ";
            cal.add(Calendar.DATE, 7);
            fechaVto = cal.getTime();
        }
        if (clienteNotaCredito.getFormaDePago().equals(3)) {
            condVta = "14 DIAS F.F.          ";
            cal.add(Calendar.DATE, 14);
            fechaVto = cal.getTime();
        }
        if (clienteNotaCredito.getFormaDePago().equals(4)) {
            condVta = "OTRO                  ";
            fechaVto = null;
        }
        condicionVentaNotaCreditoPapel = condVta;
//        vencimientoNotaCreditoPapel = sdf.format(fechaVto);
        String catego = "";
        modeloFcPapel = "Cod.Nro.";
        if (clienteNotaCredito.getCategoriaDeIva().equals(1)) {
            catego = "Responsable Inscripto       ";
            modeloFcPapel += "3";
        }
        if (clienteNotaCredito.getCategoriaDeIva().equals(2)) {
            catego = "Monotributo                 ";
            modeloFcPapel += "8";
        }
        if (clienteNotaCredito.getCategoriaDeIva().equals(3)) {
            catego = "Exento                      ";
            modeloFcPapel += "8";
        }
        if (clienteNotaCredito.getCategoriaDeIva().equals(4)) {
            catego = "Consumidor Final            ";
            modeloFcPapel += "8";
        }
        inscripcionClienteNotaCreditoPapel = catego;
        if (categoriaIva != 1) {
            //                                    1         2         3         4         5         6         7         8         9        10
            //                           1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            nombresColumnaNotaCreditoPapel = "  IT   CANT                DETALLE                     P.UNIT.    DESC.   IMPORTE        IMP.     TOTAL     SUG";
        } else {
            nombresColumnaNotaCreditoPapel = "  IT   CANT                 DETALLE                   P.UNIT.    DESC.   GRAVADO      IVA        IMP.     TOTAL     SUG";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        int maxTabla = tablaNotaCredito.getRowCount();
        for (int r = 0; r < maxNro; r++) {
            if (r < maxTabla) {
                String str0 = String.valueOf(r + 1);
                int largo = str0.length();
                if (largo < 2) {
                    renglones[r] = " " + str0 + " ";
                } else {
                    renglones[r] = str0 + " ";
                }

                str0 = tablaNotaCredito.getValueAt(r, 1).toString();
                largo = str0.length();
                if (largo == 1) {
                    renglones[r] = renglones[r] + "     " + str0;
                }
                if (largo == 2) {
                    renglones[r] = renglones[r] + "    " + str0;
                }
                if (largo == 3) {
                    renglones[r] = renglones[r] + "   " + str0;
                }
                if (largo == 4) {
                    renglones[r] = renglones[r] + "  " + str0;
                }
                if (largo == 5) {
                    renglones[r] = renglones[r] + " " + str0;
                }
                if (largo == 6) {
                    renglones[r] = renglones[r] + str0;
                }
                str0 = tablaNotaCredito.getValueAt(r, 2).toString();
                String espacio = " ";
                largo = str0.length();
                if (largo > 40) {
                    str0 = str0.substring(0, 40);
                    tablaNotaCredito.setValueAt(str0, r, 2);
                } else {
                    for (int l = largo; l < 40; l++) {
                        espacio += " ";
                    }
                }
                renglones[r] = renglones[r] + " " + tablaNotaCredito.getValueAt(r, 2) + espacio;
                if (categoriaIva != 1) {
//                  aqui detalle de importes no inscripto en IVA           *****
// Precio Unitario
                    str0 = tablaNotaCredito.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
                    str0 = tablaNotaCredito.getValueAt(r, 7).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Importe
                    str0 = tablaNotaCredito.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    Double calculo = Double.valueOf(str0);
                    str0 = tablaNotaCredito.getValueAt(r, 6).toString();
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
                    str0 = tablaNotaCredito.getValueAt(r, 5).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaNotaCredito.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaNotaCredito.getValueAt(r, 9).toString();
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
                    str0 = tablaNotaCredito.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
                    str0 = tablaNotaCredito.getValueAt(r, 7).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Gravado
                    str0 = tablaNotaCredito.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Iva
                    str0 = tablaNotaCredito.getValueAt(r, 6).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Impuesto
                    str0 = tablaNotaCredito.getValueAt(r, 5).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaNotaCredito.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaNotaCredito.getValueAt(r, 9).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "    ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
                }
            } else {
                // agregar renglon en blanco
                renglones[r] = " ";
            }
        }
// Saldo Cliente
        String str0 = String.valueOf(saldoCliente - totalNotaCredito);
        str0 = str0.replace(",", ".");
        Double doble = Double.valueOf(str0);
        int largo = doble.intValue();
        String espacio = "          ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        //totalDeudaNotaCreditoPapel = espacio + df.format(doble);
// Total Nota de Crédito
        str0 = String.valueOf(totalNotaCredito);
        str0 = str0.replace(",", ".");
        doble = Double.valueOf(str0);
        largo = doble.intValue();
        espacio = "        ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        importeTotalNotaCreditoPapel = espacio + df.format(doble);
// Linea Totales
        if (categoriaIva != 1) {
            lineaTitulos = "                                                           Impuesto                  Total Factura";
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                                                            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesNotaCreditoPapel = espacio + df.format(doble);
        } else {
            lineaTitulos = "                  Gravado       Impuesto                            Iva             Total Factura";
            str0 = String.valueOf(totalGravado);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "           ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesNotaCreditoPapel = espacio + df.format(doble);
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "           ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesNotaCreditoPapel += espacio + df.format(doble);
            str0 = String.valueOf(totalIva);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                 ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesNotaCreditoPapel += espacio + df.format(doble);
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
        //totalPagarNotaCreditoPapel = espacio + df.format(doble);
// Cantidades atados
        cantidadesNotaCreditoPapel = "                  CANT.ATADOS NOBLEZA: " + String.valueOf(cantidadAtadosNobleza);
        cantidadesNotaCreditoPapel += "                 CANT ATADOS MASSALIN: " + String.valueOf(cantidadAtadosMassalin);
        texto1NotaCreditoPapel = texto1PieNcTxt.getText();
        texto2NotaCreditoPapel = texto2PieNcTxt.getText();
        texto3NotaCreditoPapel = "-";
        texto1Cae = String.valueOf(numCae);
        if (imprimeChk.isSelected()) {
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
        }
//        }
    }

    private void agregarProducto() {
        agregarBtn.setEnabled(false);
        if (nro > 0) {
            terminarBtn.setEnabled(true);
        } else {
            terminarBtn.setEnabled(false);
        }
        incorporarANotaCreditoBtn.setEnabled(true);
        if (nro > 0) {
            cancelarBtn.setEnabled(true);
        }
        buscarProductoXNombreBtn.setEnabled(true);
        codigoProductoTxt.setEnabled(true);
        codigoBarrasTxt.setEnabled(true);
        cantidadTxt.setEnabled(true);
        nombreProductoABuscarTxt.setEnabled(true);
        nombreProductoConsultaTxt.setEnabled(true);
        comboProductos.setEnabled(true);
        leerCantidadBtn.setEnabled(false);
        leerPrecioBtn.setEnabled(false);
        nuevoPrecioTxt.setEnabled(false);
        nuevaCantidadTxt.setEnabled(false);
        nombreProductoABuscarTxt.requestFocus();
    }

    private void buscarProducto() {
        //traba = 0;
        filtro = "";
        if (nro < maxNro - 1) {
            agregarBtn.setEnabled(true);
        } else {
            agregarBtn.setEnabled(false);
        }
        terminarBtn.setEnabled(true);
        eliminarItemBtn.setEnabled(true);
        cancelarBtn.setEnabled(true);
        Producto pro = null;
        encontrado = false;
        if (!codigoBarrasTxt.getText().isEmpty()) {
            try {
                pro = new ProductoService().getProductoByCodigoBarras(Long.valueOf(codigoBarrasTxt.getText()));
                encontrado = true;
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoBarrasTxt.requestFocus();
            }
        } else {
            if (!codigoProductoTxt.getText().isEmpty()) {
                try {
                    pro = new ProductoService().getProductoByCodigo(Integer.valueOf(codigoProductoTxt.getText()));
                    encontrado = true;
                } catch (Exception ex) {
                    Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                    codigoBarrasTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese Código de Producto o Código de Barras");
                codigoBarrasTxt.requestFocus();
            }
        }
        if (pro != null) {
            if (pro.getInactivo() != null) {
                if (!pro.getInactivo()) {
                    if (!pro.getSubRubro().getCodigo().equals(5099)) {
                        cantidad = Float.valueOf(cantidadTxt.getText());
                        Double prec = -pro.getPrecio();
                        Double impu = -pro.getImpuesto().doubleValue();

                        if (!(cantidad > 0.0)) {
                            JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                            cantidadTxt.requestFocus();
                            return;
                        }
                        dto = 0.0;
                        calcularLinea(cantidad, prec, impu, pro);
                        if (cantidadTxt.getText().isEmpty()) {
                            //si la cantidad supera 9999 unidades, cuando codigo barras ingreso en cantidad
                            return;
                        }
//                        precioFinal = rint(((-pro.getPrecio() * (1 + (porcentualIva / 100))) + -pro.getImpuesto()) * 100) / 100;
                        // por cantidad

                        RenglonNotaCredito rf = new RenglonNotaCredito();
                        rf.setCantidad(cantidad);
                        rf.setDescripcion(pro.getDetalle());
                        rf.setDescuento(0.0);
                        rf.setExento(0.0);
                        rf.setGravado(gravado);
                        rf.setImpuesto(impuesto);
                        rf.setIva(iva);
                        rf.setNoGravado(noGravado);
                        rf.setProducto(pro);
                        rf.setSugerido(pro.getSugerido());
                        rf.setTotal(totalLinea);
                        nro += 1;
                        renglonNotaCredito.add(rf);
                        calcularTotales();
                        Object[] fila = new Object[10];
                        fila[0] = pro.getCodigo();
                        fila[1] = cantidadTxt.getText();
                        fila[2] = pro.getDetalle();
                        fila[3] = df.format(-precioFinal - impuesto);
                        fila[4] = df.format(gravado);
                        if (impuesto < 0) {
                            fila[5] = df.format(impuesto);
                        } else {
                            fila[5] = df.format(0.00);
                        }

                        fila[6] = df.format(iva);
                        fila[7] = df.format(0.0);
                        fila[8] = df.format(totalLinea);
                        fila[9] = df.format(pro.getSugerido());
                        tabla.addRow(fila); // Agrego la fila a la tabla
                        Rectangle rect = tablaNotaCredito.getCellRect(nro - 1, 0, true);
                        tablaNotaCredito.scrollRectToVisible(rect);
                        tablaNotaCredito.clearSelection();
                        tablaNotaCredito.setRowSelectionInterval(nro - 1, nro - 1);
                        tablaNotaCredito.setModel(tabla); // poner visible la tabla
                        codigoProductoTxt.setEnabled(false);
                        codigoBarrasTxt.setEnabled(false);
                        cantidadTxt.setEnabled(false);
                        incorporarANotaCreditoBtn.setEnabled(false);
                        cantidadTxt.setText("");
                        codigoProductoTxt.setText("");
                        buscarProductoXNombreBtn.setEnabled(false);
                        nombreProductoABuscarTxt.setEnabled(false);
                        comboProductos.setEnabled(false);
                        texto1PieNcTxt.setEnabled(true);
                        texto2PieNcTxt.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error - producto no disponible");
                        codigoProductoTxt.setText("");
                        cantidadTxt.setText("");
                        nombreProductoABuscarTxt.setText("");
                        agregarBtn.setEnabled(false);
                        codigoBarrasTxt.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                    codigoProductoTxt.setText("");
                    cantidadTxt.setText("");
                    buscarClienteBtn.setEnabled(false);
                    codigoBarrasTxt.requestFocus();
                    //codigoProductoTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                codigoProductoTxt.setText("");
                cantidadTxt.setText("");
                buscarClienteBtn.setEnabled(false);
                codigoBarrasTxt.requestFocus();
                //codigoProductoTxt.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error - producto no existe");
            codigoProductoTxt.setText("");
            codigoBarrasTxt.requestFocus();
            //codigoProductoTxt.requestFocus();
        }
        comboProductos.removeAllItems();
        comboProductos.addItem("");
    }

    private void borrarTablaProductos() {
        int cantidadRow = tablaNotaCredito.getRowCount();
        DefaultTableModel model1 = (DefaultTableModel) tablaNotaCredito.getModel();
        if (cantidadRow > 0) {
            for (int i = 0; i < cantidadRow; i++) {
                model1.removeRow(0);
            }
            tablaNotaCredito.setModel(model1);
            nro = 0;
        }
        renglonNotaCredito = new ArrayList<RenglonNotaCredito>();
    }

    private void consultarProducto() {
        nombreProductoConsultaTxt.setEnabled(true);
        precioProductoConsultaTxt.setEnabled(true);
        if (!codigoProductoTxt.getText().isEmpty()) {
            Integer codi = Integer.valueOf(codigoProductoTxt.getText());
            try {
                Producto prod = new ProductoService().getProductoByCodigo(codi);
                if (!prod.getSubRubro().getCodigo().equals(5099)) {
                    nombreProductoConsultaTxt.setText(prod.getDetalle());
                    Double precioProductoConsulta = prod.getPrecio();
                    if (!prod.getIvaCero()) {
                        precioProductoConsulta += precioProductoConsulta * porcentualIva / 100;
                    }
                    if (prod.getImpuesto() != null) {
                        precioProductoConsulta += prod.getImpuesto();
                    }
                    precioProductoConsultaTxt.setText(String.valueOf(df.format(precioProductoConsulta)));
                    cantidadTxt.requestFocus();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No existe Producto");
                //Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoProductoTxt.setText("");
                codigoProductoTxt.requestFocus();
            }
        }
    }

    private void calcularTotales() {
        //    99
        totalGravado = 0.0;
        totalNoGravado = 0.0;
        totalGravadoCigarrillos = 0.00;
        totalIva = 0.0;
        totalImpuesto = 0.0;
        totalNotaCredito = 0.0;
        nro = 0;
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        Double acumuladoParaDescuentoGlobalGravado = 0.0;
        Double acumuladoParaDescuentoGlobalNoGravado = 0.0;
        Double importeTotalMassalin = 0.0;
        Double importeTotalNobleza = 0.0;
        for (RenglonNotaCredito renFa : renglonNotaCredito) {
            /*
            separar el total gravado, totalGravadocigarrillos, y el total no gravado.
             */
            if (renFa.getProducto().getIvaCero()) {
                //     if()
                totalNoGravado += renFa.getTotal();
            }
            if (renFa.getProducto().getRubro().getCodigo().equals(1)) {
                cantidadAtadosMassalin += renFa.getCantidad().intValue();
                importeTotalMassalin += renFa.getTotal();
                totalImpuesto += renFa.getImpuesto();
                totalGravadoCigarrillos += renFa.getGravado();
            }
            if (renFa.getProducto().getRubro().getCodigo().equals(2)) {
                cantidadAtadosNobleza += renFa.getCantidad().intValue();
                importeTotalNobleza += renFa.getTotal();
                totalImpuesto += renFa.getImpuesto();
                totalGravadoCigarrillos += renFa.getGravado();
            }
            if (clienteNotaCredito.getCategoriaDeIva().equals(1)) {
//          IVA DISCRIMINADO                
                totalNotaCredito += renFa.getTotal();
                if (renFa.getProducto().getRubro().getCodigo().equals(5)) {
                    acumuladoParaDescuentoGlobalGravado += renFa.getGravado();
                    acumuladoParaDescuentoGlobalNoGravado += renFa.getNoGravado();
                }
            } else {
//          CONSUMIDOR FINAL
                totalNotaCredito += renFa.getTotal();
                if (renFa.getProducto().getRubro().getCodigo().equals(5)) {
                    if (renFa.getProducto().getIvaCero()) {
                        acumuladoParaDescuentoGlobalNoGravado += renFa.getTotal();
                    } else {
                        acumuladoParaDescuentoGlobalGravado += renFa.getTotal();
                    }
                }
            }
            nro += 1;
            renFa.setItemNro(nro);
        }
        String tgcig = df.format(totalGravadoCigarrillos);
        totalGravadoCigarrillos = Double.valueOf(tgcig.replace(",", "."));
        totalDescuentoGravado = acumuladoParaDescuentoGlobalGravado * descuento / 100;
        totalDescuentoNoGravado = acumuladoParaDescuentoGlobalNoGravado * descuento / 100;
        String td = df.format(totalDescuentoGravado);
        totalDescuentoGravado = Double.valueOf(td.replace(",", "."));
        td = df.format(totalDescuentoNoGravado);
        totalDescuentoNoGravado = Double.valueOf(td.replace(",", "."));
        descuentoVolumenTxt.setText(df.format(totalDescuentoGravado + totalDescuentoNoGravado));
        if (clienteNotaCredito.getCategoriaDeIva().equals(1)) {
            Double t1 = totalNotaCredito - totalImpuesto - totalNoGravado;
            totalGravadoCompleto = t1 / (1 + (porcentualIva / 100));
            String tgc = df.format(totalGravadoCompleto);
            totalGravadoCompleto = Double.valueOf(tgc.replace(",", "."));
            totalGravado = totalGravadoCompleto - totalDescuentoGravado;
            totalNoGravado -= totalDescuentoNoGravado;
            String tg = df.format(totalGravado);
            totalGravado = Double.valueOf(tg.replace(",", "."));
            tg = df.format(totalNoGravado);
            totalNoGravado = Double.valueOf(tg.replace(",", "."));
            totalIva = totalGravado * porcentualIva / 100;
            String ti = df.format(totalIva);
            totalIva = Double.valueOf(ti.replace(",", "."));
            String tim = df.format(totalImpuesto);
            totalImpuesto = Double.valueOf(tim.replace(",", "."));
            totalNotaCredito = totalGravado + totalNoGravado + totalIva
                    + totalImpuesto; // - totalDescuentoGravado - totalDescuentoNoGravado;
            String tf = df.format(totalNotaCredito);
            totalNotaCredito = Double.valueOf(tf.replace(",", "."));
//            gravadoVarios = totalGravado - totalGravadoCigarrillos;
//            String gv = df.format(gravadoVarios);
//            gravadoVarios = Double.valueOf(gv.replace(",", "."));
//            System.out.println("gravado Compl " + totalGravadoCompleto);
//            System.out.println("gravado       " + totalGravado);
//            System.out.println("no gravado    " + totalNoGravado);
//            System.out.println("iva           " + totalIva);
//            System.out.println("impuesto      " + totalImpuesto);
//            System.out.println("descuentoG    " + totalDescuentoGravado);
//            System.out.println("descuentoNG   " + totalDescuentoNoGravado);
//            System.out.println("total factura " + totalFactura);
//            System.out.println("grav.cig      " + totalGravadoCigarrillos);
//            System.out.println("grv.vs        " + gravadoVarios);
//            JOptionPane.showMessageDialog(this, "ver ahora");
        } else {
            String tf = df.format(totalNotaCredito - totalDescuentoGravado - totalDescuentoNoGravado);
            totalNotaCredito = Double.valueOf(tf.replace(",", "."));
            totalTxt.setText(tf);
            String ti = df.format(totalImpuesto);
            totalImpuesto = Double.valueOf(ti.replace(",", "."));
            totalGravado = (totalNotaCredito - totalImpuesto - totalNoGravado) / (1 + (porcentualIva / 100));
            String tg = df.format(totalGravado);
            totalGravado = Double.valueOf(tg.replace(",", "."));
            totalIva = totalGravado * porcentualIva / 100;
            String tiv = df.format(totalIva);
            totalIva = Double.valueOf(tiv.replace(",", "."));
//            gravadoVarios = totalGravado - totalGravadoCigarrillos;
//            String gv = df.format(gravadoVarios);
//            gravadoVarios = Double.valueOf(gv.replace(",", "."));
//            System.out.println("gravado       " + totalGravado);
//            System.out.println("iva           " + totalIva);
//            System.out.println("impuesto      " + totalImpuesto);
//            System.out.println("descuento     " + totalDescuento);
//            System.out.println("total factura " + totalFactura);
//            System.out.println("grav.cig      " + totalGravadoCigarrillos);
//            System.out.println("grv.vs        " + gravadoVarios);
        }
//            gravadoVarios = totalGravado - totalGravadoCigarrillos;
//            String gv = df.format(gravadoVarios);
//            gravadoVarios = Double.valueOf(gv.replace(",", "."));
//        System.out.println("gravado Compl " + totalGravadoCompleto);
//        System.out.println("gravado       " + totalGravado);
//        System.out.println("no gravado    " + totalNoGravado);
//        System.out.println("iva           " + totalIva);
//        System.out.println("impuesto      " + totalImpuesto);
//        System.out.println("descuentoG    " + totalDescuentoGravado);
//        System.out.println("descuentoNG   " + totalDescuentoNoGravado);
//        System.out.println("total factura " + totalNotaCredito);
//        System.out.println("grav.cig      " + totalGravadoCigarrillos);
//        System.out.println("grv.vs        " + gravadoVarios);
//        JOptionPane.showMessageDialog(this, "ver ahora");
        cantidadAtadosNoblezaTxt.setText(String.valueOf(cantidadAtadosNobleza));
        importeNoblezaTxt.setText(String.valueOf(df.format(importeTotalNobleza)));
        cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
        importeMassalinTxt.setText(String.valueOf(df.format(importeTotalMassalin)));
        cantidadItemsTxt.setText(String.valueOf(nro));
        totalTxt.setText(String.valueOf(df.format(totalNotaCredito)));
        clienteNotaCredito.setImporteMostrador(totalNotaCredito);
        try {
            new ClienteService().updateCliente(clienteNotaCredito);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 2512");
        }
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
                if (!prod.getIvaCero()) {
                    precioProductoConsulta += precioProductoConsulta * porcentualIva / 100;
                }
                if (prod.getImpuesto() != null) {
                    precioProductoConsulta += prod.getImpuesto();
                }
                precioProductoConsultaTxt.setText(String.valueOf(df.format(precioProductoConsulta)));
                cantidadTxt.requestFocus();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No existe Producto");
                //Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoProductoTxt.setText("");
                codigoProductoTxt.requestFocus();
            }
        }
    }

    private void cargarDatos() {
        // CLIENTEsELECCIONADO Y FACTURA
        limpiarCampos();
        codigoTxt.setText(clienteSeleccionado.getCodigo());
        razonSocialTxt.setText(clienteSeleccionado.getRazonSocial());

        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        fecha = Calendar.getInstance().getTime();
        categoriaIva = 4;
        clienteNotaCredito = clienteSeleccionado;
        fechaTxt.setText(sdf.format(fecha));
        if (clienteNotaCredito.getCategoriaDeIva() != null) {
            if (clienteNotaCredito.getCategoriaDeIva() == 1) {
                ivaTxt.setText("Resp. Inscripto");
            }
            if (clienteNotaCredito.getCategoriaDeIva() == 2) {
                ivaTxt.setText("Monotributo");
            }
            if (clienteNotaCredito.getCategoriaDeIva() == 4) {
                ivaTxt.setText("Consumidor Final");
            }
            categoriaIva = clienteNotaCredito.getCategoriaDeIva();
        } else {
            ivaTxt.setText("Consumidor Final");
        }
        agregarBtn.setEnabled(true);
        buscarClienteBtn.setEnabled(false);
        volverBtn.setEnabled(false);
        cancelarBtn.setEnabled(true);
        buscarClienteXNombre.setEnabled(false);
        nombreClienteABuscarTxt.setEnabled(false);
        comboClientes.setEnabled(false);
        nombreProductoConsultaTxt.setEditable(false);
        precioProductoConsultaTxt.setEditable(false);
        codigoTxt.setEditable(false);
        totalTxt.setEnabled(true);
        totalTxt.setEditable(false);

        agregarProducto();
    }

    private void cargarRenglones() {
        try {
            renglonF = new RenglonFacturaService().getAllRenglonFacturaFromIvaVentas(factura);
            tabla = (DefaultTableModel) tablaNotaCredito.getModel();
            for (RenglonFactura rp : renglonF) {
                RenglonNotaCredito rf = new RenglonNotaCredito();
                rf.setCantidad(rp.getCantidad());
                rf.setProducto(rp.getProducto());
                rf.setItemNro(rp.getItemNro());
                rf.setDescripcion(rp.getDescripcion());
                rf.setGravado(-rp.getGravado());
                rf.setNoGravado(-rp.getNoGravado());
                rf.setExento(-rp.getExento());
                rf.setImpuesto(-rp.getImpuesto());
                rf.setDescuento(-rp.getDescuento());
                rf.setIva(-rp.getIva());
                rf.setTotal(-rp.getTotal());
                rf.setSugerido(-rp.getSugerido());
                renglonNotaCredito.add(rf);
                Object linea[] = new Object[10];
                linea[0] = rp.getProducto().getCodigo();
                linea[1] = rp.getCantidad().intValue();
                linea[2] = rp.getDescripcion();
                linea[3] = df.format((rp.getGravado() + rp.getImpuesto() + rp.getIva()) / rp.getCantidad());
                linea[4] = df.format(-rp.getGravado());
                linea[5] = df.format(-rp.getImpuesto());
                linea[6] = df.format(-rp.getIva());
                linea[7] = 0.00;
                linea[8] = df.format(-rp.getTotal());
                linea[9] = df.format(rp.getSugerido());
                tabla.addRow(linea);
                tablaNotaCredito.setModel(tabla);
            }
            agregarBtn.setEnabled(true);
            buscarClienteBtn.setEnabled(false);
            volverBtn.setEnabled(false);
            cancelarBtn.setEnabled(true);
            buscarClienteXNombre.setEnabled(false);
            nombreClienteABuscarTxt.setEnabled(false);
            comboClientes.setEnabled(false);
            nombreProductoConsultaTxt.setEditable(false);
            precioProductoConsultaTxt.setEditable(false);
            codigoTxt.setEditable(false);
            totalTxt.setEnabled(true);
            totalTxt.setEditable(false);

            calcularTotales();
            cancelarBtn.setEnabled(true);
            terminarBtn.setEnabled(true);
            eliminarItemBtn.setEnabled(true);
            agregarBtn.requestFocus();
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void abandonarNotaCredito() {
        limpiarCampos();
        bloquearCampos();
        borrarTablaProductos();
        volverBtn.setEnabled(true);
        buscarClienteBtn.setEnabled(true);
        codigoTxt.requestFocus();
        leerCantidadBtn.setEnabled(false);
        leerPrecioBtn.setEnabled(false);
        nuevaCantidadTxt.setEnabled(false);
        nuevoPrecioTxt.setEnabled(false);
        grabarCantidadBtn.setEnabled(false);
        grabarPrecioBtn.setEnabled(false);
        nombreClienteABuscarTxt.setEnabled(true);
        buscarClienteXNombre.setEnabled(true);
        comboClientes.setEnabled(true);
    }

    private void prepararCampos() {
        incorporarANotaCreditoBtn.setEnabled(false);
        eliminarItemBtn.setEnabled(true);
        agregarBtn.setEnabled(true);
        agregarBtn.requestFocus();
        codigoBarrasTxt.setEnabled(false);
        codigoProductoTxt.setEnabled(false);
        cantidadTxt.setEnabled(false);
        nombreProductoABuscarTxt.setEnabled(false);
        comboProductos.setEnabled(false);
        //nombreProductoConsultaTxt.setEnabled(false);
        leerCantidadBtn.setEnabled(true);
        leerPrecioBtn.setEnabled(true);
        nuevaCantidadTxt.setEnabled(true);
        nuevoPrecioTxt.setEnabled(true);
        grabarCantidadBtn.setEnabled(false);
        grabarPrecioBtn.setEnabled(false);
        tablaNotaCredito.setEnabled(true);
        agregarBtn.requestFocus();
    }

    private void guardarRepositorio() {
        FcReserved fcr = new FcReserved();
        fcr.setCliente(clienteNotaCredito);
        fcr.setFecha(fecha);
        fcr.setTotal(totalNotaCredito);
        fcr.setImpuesto(totalImpuesto);
        fcr.setGravado(totalGravado);
        fcr.setGravadoCigarrillos(totalGravadoCigarrillos);
        fcr.setIva(totalIva);
        fcr.setDescuentoGlobal(0.00);
        fcr.setExento(0.00);
        fcr.setNoGravado(0.00);
        try {
            fcr = new FcReservedService().saveFcReserved(fcr);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (RenglonNotaCredito rf : renglonNotaCredito) {
            RenglonFcReserved rfcr = new RenglonFcReserved();
            rfcr.setCantidad(rf.getCantidad());
            rfcr.setDescripcion(rf.getDescripcion());
            rfcr.setDescuento(rf.getDescuento());
            rfcr.setExento(rf.getExento());
            rfcr.setFacturaReservada(fcr);
            rfcr.setGravado(rf.getGravado());
            rfcr.setImpuesto(rf.getImpuesto());
            rfcr.setItemNro(rf.getItemNro());
            rfcr.setIva(rf.getIva());
            rfcr.setNoGravado(rf.getNoGravado());
            rfcr.setPrecioUnitario(rf.getGravado() / rf.getCantidad());
            rfcr.setProducto(rf.getProducto());
            rfcr.setSugerido(rf.getSugerido());
            rfcr.setTotal(rf.getTotal());
            try {
                new RenglonFcReservedService().saveRenglonFcReserved(rfcr);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void calcularLinea(Float cantid, Double prec, Double impu, Producto p) {
        Float desc_calc = 0F;
        Double dt = 0.0;
        if (!descuentoLineaTxt.getText().isEmpty()) {
            desc_calc = Float.valueOf(descuentoLineaTxt.getText());
            String p_calc = df.format(prec * (1 - desc_calc / 100));
            Double dp = Double.valueOf(p_calc.replace(",", "."));
            dt = prec - dp;
            prec = dp;
        }
        if (p.getIvaCero()) {
            precioFinal = prec;
            String cdt = df.format(precioFinal);
            precioFinal = Double.valueOf(cdt.replace(",", "."));
            totalLinea = rint((precioFinal * cantid) * 100) / 100;
            if (totalLinea > Constantes.maximoVenta) {
                System.out.print("\007");
                System.out.flush();
                JOptionPane.showMessageDialog(this, "Importe supera maximo de linea");
                codigoProductoTxt.setText("");
                cantidadTxt.setText("");
                buscarClienteBtn.setEnabled(false);
                codigoBarrasTxt.requestFocus();
                return;
            }
            dto = dt * cantidad;
            impuesto = 0.0;
            noGravado = totalLinea;
            gravado = 0.0;
            iva = 0.0;
        } else {
            String spf = df.format(prec
                    * (1 + (porcentualIva / 100)));
            precioFinal = Double.valueOf(spf.replace(",", "."));
            // por cantidad
            Double t_lin = precioFinal * cantid;
            String t_l = df.format(t_lin);
            t_lin = Double.valueOf(t_l.replace(",", "."));
            if (t_lin > Constantes.maximoVenta) {
                System.out.print("\007");
                System.out.flush();
                JOptionPane.showMessageDialog(this, "Importe supera maximo de linea");
                codigoProductoTxt.setText("");
                cantidadTxt.setText("");
                buscarClienteBtn.setEnabled(false);
                codigoBarrasTxt.requestFocus();
                return;
            }
            String s_cal = df.format(dt * cantid);
            dto = Double.valueOf(s_cal.replace(",", "."));
            s_cal = df.format(impu * cantid);
            impuesto = Double.valueOf(s_cal.replace(",", "."));
            Double calculo = t_lin / (1 + porcentualIva / 100);
            s_cal = df.format(calculo);
            noGravado = 0.0;
            gravado = Double.valueOf(s_cal.replace(",", "."));
            s_cal = df.format(t_lin - gravado);
            iva = Double.valueOf(s_cal.replace(",", "."));
            String t_l1 = df.format(gravado + impuesto + iva);
            totalLinea = Double.valueOf(t_l1.replace(",", "."));
        }
//        System.out.println(precioFinal);
//        System.out.println(totalLinea);
//        System.out.println(gravado);
//        System.out.println(noGravado);
//        System.out.println(dto);
//        System.out.println(impuesto);
//        System.out.println(iva);
//        JOptionPane.showMessageDialog(this, "VER");
    }

    private void leerPrecio() {
        int lin = tablaNotaCredito.getSelectedRow();
        if (lin > -1) {
            RenglonNotaCredito rf = renglonNotaCredito.get(lin);
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
                terminarBtn.requestFocus();
                return;
            }
            Float cant = rf.getCantidad();
            Double iva = rf.getIva() / cant;
            Double prec = rf.getGravado() / cant;
            nuevoPrecioTxt.setText(String.valueOf(df.format(-(prec + iva))));
            leerPrecioBtn.setEnabled(false);
            grabarPrecioBtn.setEnabled(true);
            leerCantidadBtn.setEnabled(false);
            terminarBtn.setEnabled(false);
            tablaNotaCredito.setEnabled(false);
            grabarCantidadBtn.setEnabled(false);
            agregarBtn.setEnabled(false);
            eliminarItemBtn.setEnabled(false);
        }
    }

    private boolean habilitado() {
        FileReader fr = null;
        //Boolean habilita = false;
        try {
            fr = new FileReader("c:/ventas/permisos.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        String acceso = "";
        try {
            acceso = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (acceso.equals("1")) {
            return true;
        }
        habilitar();
        if (usuario != null) {
            return true;
        } else {
            return false;
        }
    }

    private void habilitar() {
        usuario = null;
        JTextField field = new JTextField("");
        String[] options = {"Ingresar"};
        int result = JOptionPane.showOptionDialog(
                null,
                field,
                "Autorización de USUARIO",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                0);
        if (field.getText().isEmpty()) {
            usuario = null;
            return;
        }
        switch (result) {
            case 0:
                int cod = Integer.valueOf(field.getText());
                try {
                    usuario = new UsuarioService().getUsuarioByCodigo(cod);
                } catch (Exception ex) {
                    Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (usuario != null) {
                    if (usuario.getActivo()) {
                        JPasswordField field2 = new JPasswordField("");
                        String[] opts = {"Ingresar"};
                        int resulta = JOptionPane.showOptionDialog(
                                null,
                                field2,
                                "CONTRASEÑA: " + usuario.getNombre() + ", Autorización",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opts,
                                0);
                        switch (resulta) {
                            case 0:
                                int contra = Integer.valueOf(new String(field2.getPassword()));
                                if (contra == usuario.getContrasena()) {
                                    if (usuario.getNivel() > nivel) {
                                        JOptionPane.showMessageDialog(this, "Usuario no Habilitado");
                                        usuario = null;
                                    } else {
                                        String f1 = sdf.format(usuario.getFecha());
                                        String f2 = sdf.format(new Date());
                                        if (usuario.getNivel() == 2) {
                                            if (!f1.equals(f2)) {
                                                JOptionPane.showMessageDialog(this, "Permiso de Usuario Vencido");
                                                usuario = null;
                                            }
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
                                    usuario = null;
                                }
                                break;
                            case 1:
                                usuario = null;
                                break;
                            case -1:
                                usuario = null;
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "USUARIO Inactivo");
                        usuario = null;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No existe el Usuario");
                    usuario = null;
                }
                break;
            case 1:
                usuario = null;
                break;
            case -1:
                usuario = null;
                break;
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
            g2.drawString(espacio + letraNotaCreditoPapel, 30, row);
            espacio = "                                                         ";
            g2.setFont(new Font("Monospaced", Font.PLAIN, 6));
            g2.drawString(modeloFcPapel, 271, 53);
            g2.setFont(new Font("Monospaced", Font.BOLD, 9));
            g2.drawString(espacio + textoNotaCreditoPapel, 30, row);
            g2.drawString("Distribuidora A & M", 50, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 8));
            row += 12;
            g2.drawString("Av.San Martín 3284", 50, row);
            row += 12;
            g2.drawString("1678 - Caseros Prov. Buenos Aires", 50, row);
            row += 12;
            g2.drawString("Tel: 4759-6677 - mail: distaym@yahoo.com.ar", 50, row);
            row += 12;
            g2.drawString("CUIT:20-12412758-1 inic.activ.18/04/2005", 50, row);
            row = 70;
            //         123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            espacio = "                                                                           ";
            g2.drawString(espacio + " "
                    + sucursalNotaCreditoPapel + "-"
                    + numeroNotaCreditoPapel, 30, row);
            row += 15;
            g2.drawString(espacio + fechaNotaCreditoPapel, 30, row);
            espacio = "            ";
            row += 50;
            g2.drawString(espacio + clienteNotaCreditoPapel, 30, row);
            g2.drawString(codigoClienteNotaCreditoPapel, 480, row);
            row += 15;
            espacio = "            ";
            g2.drawString(espacio + direccionNotaCreditoPapel, 30, row);
            row += 15;
            g2.drawString(cuitNotaCreditoPapel, 100, row);
            g2.drawString(inscripcionClienteNotaCreditoPapel, 360, row);
            row += 25;
            g2.drawString(condicionVentaNotaCreditoPapel, 150, row);
//            g2.drawString("Ref.Interna:" + letraNotaCredito + " " + sucursalNotaCredito
//                    + "-" + numeroNotaCredito, 350, row);
            //g2.drawString(vencimientoNotaCreditoPapel, 400, row);
            row += 25;
            g2.drawString(nombresColumnaNotaCreditoPapel, 30, row);
            row += 15;
            for (int x = 0; x < maxNro; x++) {
                if (renglones[x] != null) {
                    g2.drawString(renglones[x], 40, row);
                }
                row += 10;
            }
            row += 12;
            if (!clienteNotaCredito.getCategoriaDeIva().equals(1)) {
                if (clienteNotaCredito.getTieneDescuento()) {
                    Double td2 = totalDescuentoGravado + totalDescuentoNoGravado;
                    g2.drawString("Descuento: " + descuento + "% Total descuento: " + df.format(td2), 30, row);
                }
            }
            row += 12;
            if (clienteNotaCredito.getCategoriaDeIva().equals(1)) {
                g2.drawString("Subtotal      Descuento", 40, row);
                g2.drawString(df.format(totalGravadoCompleto), 40, row + 20);
                Double td2 = totalDescuentoGravado + totalDescuentoNoGravado;
                g2.drawString("    " + df.format(td2), 90, row + 20);
            }
            g2.drawString(lineaTitulos, 90, row);
            row += 20;
            g2.drawString(lineaTotalesNotaCreditoPapel, 30, row);
            g2.setFont(new Font("Monospaced", Font.BOLD, 11));
            g2.drawString(importeTotalNotaCreditoPapel, 490, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            row += 21;
//            g2.drawString("SALDO ANTERIOR: " + totalDeudaNotaCreditoPapel, 403, row);
//            row += 10;
//            g2.drawString("SALDO TOTAL:    " + totalPagarNotaCreditoPapel, 403, row);
//            row += 10;
            espacio = "     ";
            g2.setFont(new Font("Monospaced", Font.BOLD, 9));
            g2.drawString(espacio + texto1NotaCreditoPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto2NotaCreditoPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto3NotaCreditoPapel, 30, row);
            row += 15;
//            g2.drawString(espacio + texto4NotaCreditoPapel, 30, row);
            g2.drawString(cantidadesNotaCreditoPapel, 30, row);
            row += 20;
            g2.drawString(" CAE " + texto1Cae + "  Venc. CAE " + vencCae, 30, row);
            g2.setFont(new Font("PF Interleavev 2 of 5 Text", Font.PLAIN, 18));
            g2.drawString("           " + texto2Cae, 160, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            return PAGE_EXISTS;
        }
    }
}
