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
import ar.com.ventas.entities.FcReserved;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.Pedido;
import ar.com.ventas.entities.Producto;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.entities.RenglonFcReserved;
import ar.com.ventas.entities.RenglonPedido;
import ar.com.ventas.entities.Usuario;
import ar.com.ventas.entities.Vendedor;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.ClienteTrabaService;
import ar.com.ventas.services.ConfiguracionService;
import ar.com.ventas.services.FacturaService;
import ar.com.ventas.services.FcReservedService;
import ar.com.ventas.services.PedidoService;
import ar.com.ventas.services.ProductoService;
import ar.com.ventas.services.RenglonFcReservedService;
import ar.com.ventas.services.RenglonPedidoService;
import ar.com.ventas.services.UsuarioService;
import ar.com.ventas.util.DesktopApi;
import ar.com.ventas.util.PDFBuilder1;
import ar.com.ventas.util.PdfBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.rint;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
public class FacturaWebPedidoFrame extends javax.swing.JFrame {

    private final List<RenglonFactura> renglonFactura = new ArrayList<RenglonFactura>();
    private static final int qrTamAncho = 150;
    private static final int qrTamAlto = 150;
    private static final String formato = "png";
    private static final String ruta = "c:/qr/codigoQR";
    private static final String extension = ".png";
    private static SimpleDateFormat sdf_qr = new SimpleDateFormat("yyyy-MM-dd");
    private String url_qr = "https://www.afip.gob.ar/fe/qr/?p=";
    private String ver_qr = "1";
    private String fecha_qr;
    private String cuit_qr;
    private String puntoVenta_qr = "5";
    private String tipoComprobante_qr;
    private String numeroComprobante_qr;
    private String importe_qr;
    private String moneda_qr = "PES";
    private String cotiz_qr = "1";
    private String tipoDoc_qr;
    private String numeroDoc_qr;
    private String tipoCodigoAutoriz_qr = "E";
    private String nroCae_qr;
    private String modeloFcPapel;
    private String codigoClienteFacturaPapel;
    private String[] renglones = null;
    private String letraFacturaPapel;
    private String sucursalFacturaPapel;
    private String numeroFacturaPapel;
    private Date fecha;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Cliente clienteFactura = null;
    private Producto producto = null;
    private DefaultTableModel tabla = null;
    private Double totalFactura = 0.00;
    private Double totalImpuesto = 0.00;
    private Double totalIva = 0.00;
    private Double totalGravado = 0.00;
    private Double gravado = 0.00;
    private final Double noGravado = 0.00;
    private Double gravadoVarios = 0.00;
    private Double totalGravadoCompleto = 0.0;
    private Double iva = 0.00;
    private Float porcentualIva;
    private Double impuesto = null;
    private Double totalLinea = 0.00;
    //private Boolean tieneDto = false;
    private Float descuento = 0F;
    private Double totalDescuento = 0.0;
    //private Cliente clienteSeleccionado;
    private Producto productoSeleccionado;
    private Float cantidad;
    private Integer categoriaIva = 4;
    private final DecimalFormat df = new DecimalFormat("#0.00");
    private final DecimalFormat df1 = new DecimalFormat("#0");
    private final DecimalFormat df2 = new DecimalFormat("#0.0");
    private final DecimalFormat dfn = new DecimalFormat("00000000");
    private Double saldoCliente = 0.00;
    private String filtro = "";
    private String letraFactura;
    //private Integer sucursalFactura;
    private Integer numeroFactura;
    private Configuracion config = null;
    private Double precioFinal = 0.0;
    private Integer nro = 0;
    private final Integer maxNro = 41;
    private Integer cantidadAtadosMassalin;
    private Integer cantidadAtadosNobleza;
    public Boolean encontrado;
    private List<RenglonPedido> renglonP = null;
    private final Pedido pedido;
    private String numCae;
//    private String texto1Cae = "";
//    private String texto2Cae = "";
    private String vencCae = "";
    private String tipoComprob = "";
    private Double totalGravadoCigarrillos = 0.00;
    private final Integer tst = 0; // 1 está en test
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form FacturaFrame
     *
     * @param cli
     * @param pe
     */
    public FacturaWebPedidoFrame(Cliente cli, Pedido pe) {
        //Cliente cli, Pedido pe
        getContentPane().setBackground(new java.awt.Color(135, 206, 235));
        initComponents();
        this.clienteFactura = cli;
        this.pedido = pe;
//        this.order_name = order_name;
//        this.order_num = order_num;
        this.setLocationRelativeTo(null);
        limpiarCampos();
        bloquearCampos();
//        codigoTxt.requestFocus();
//      levanto el modelo creado del frame
        tabla = (DefaultTableModel) tablaFactura.getModel();
        cargarDatosIniciales();
        cargarCliente();
        bloquearCliente();
        cargarRenglones();
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
        tablaFactura = new javax.swing.JTable();
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
        incorporarAFacturaBtn = new javax.swing.JButton();
        eliminarItemBtn = new javax.swing.JButton();
        buscarProductoXNombreBtn = new javax.swing.JButton();
        descuentoGlobalLbl = new javax.swing.JLabel();
        descuentoGlobalTxt = new javax.swing.JTextField();
        descuentoLineaLbl = new javax.swing.JLabel();
        descuentoLineaTxt = new javax.swing.JTextField();
        comboProductos = new javax.swing.JComboBox();
        nombreProductoABuscarTxt = new javax.swing.JTextField();
        descuentoBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        descuentoVolumenTxt = new javax.swing.JTextField();
        texto1PieFacturaTxt = new javax.swing.JTextField();
        texto2PieFacturaTxt = new javax.swing.JTextField();
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
        grabarPrecioBtn = new javax.swing.JButton();
        nuevoPrecioTxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        importeNoblezaTxt = new javax.swing.JTextField();
        importeMassalinTxt = new javax.swing.JTextField();
        cuitTxt = new javax.swing.JTextField();
        imprimeChk = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("AYM - FACTURA WEB - PEDIDO");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tablaFactura.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaFactura);
        if (tablaFactura.getColumnModel().getColumnCount() > 0) {
            tablaFactura.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaFactura.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablaFactura.getColumnModel().getColumn(2).setPreferredWidth(330);
            tablaFactura.getColumnModel().getColumn(3).setPreferredWidth(20);
            tablaFactura.getColumnModel().getColumn(4).setPreferredWidth(25);
            tablaFactura.getColumnModel().getColumn(5).setPreferredWidth(25);
            tablaFactura.getColumnModel().getColumn(6).setPreferredWidth(25);
            tablaFactura.getColumnModel().getColumn(7).setPreferredWidth(20);
            tablaFactura.getColumnModel().getColumn(8).setPreferredWidth(30);
            tablaFactura.getColumnModel().getColumn(9).setPreferredWidth(20);
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

        jLabel8.setText("TOTAL FACTURA:");

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

        terminarBtn.setText("Terminar Factura");
        terminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar Factura");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        incorporarAFacturaBtn.setText("Incorporar a Factura");
        incorporarAFacturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incorporarAFacturaBtnActionPerformed(evt);
            }
        });

        eliminarItemBtn.setText("Eliminar Item selecc.");
        eliminarItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarItemBtnActionPerformed(evt);
            }
        });

        buscarProductoXNombreBtn.setText("Buscar x nombre");
        buscarProductoXNombreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProductoXNombreBtnActionPerformed(evt);
            }
        });

        descuentoGlobalLbl.setText("Descuento:");

        descuentoGlobalTxt.setText("DESCUENTO");

        descuentoLineaLbl.setText("Dscto:");

        descuentoLineaTxt.setText("DESCUENTO");

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

        texto1PieFacturaTxt.setText("TEXTO 1 PIE FACTURA");

        texto2PieFacturaTxt.setText("TEXTO 2 PIE FACTURA");

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
        cantidadAtadosMassalinTxt.setText("Cant. Mas.");

        cantidadAtadosNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadAtadosNoblezaTxt.setText("Cant. Nobl.");

        jLabel18.setText("Cant. Items:");

        cantidadItemsTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadItemsTxt.setText("Cant Items");

        leerCantidadBtn.setText("Leer Cantidad");
        leerCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerCantidadBtnActionPerformed(evt);
            }
        });

        nuevaCantidadTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevaCantidadTxt.setText("NUE.CANT.");
        nuevaCantidadTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevaCantidadTxtKeyPressed(evt);
            }
        });

        grabarCantidadBtn.setText("Grabar Cantidad");
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

        grabarPrecioBtn.setText("Grabar Precio");
        grabarPrecioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarPrecioBtnActionPerformed(evt);
            }
        });

        nuevoPrecioTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevoPrecioTxt.setText("NUE.PREC.");
        nuevoPrecioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevoPrecioTxtKeyPressed(evt);
            }
        });

        jLabel14.setText("Importe:");

        jLabel16.setText("Importe:");

        importeNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeNoblezaTxt.setText("IMP.NOBL.");

        importeMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeMassalinTxt.setText("IMP.MASS.");

        cuitTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cuitTxt.setText("CUIT");

        imprimeChk.setText("Imprime");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(texto1PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(incorporarAFacturaBtn)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(agregarBtn)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(eliminarItemBtn))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(descuentoLineaLbl)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(descuentoLineaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(texto2PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(249, 249, 249)
                                    .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buscarProductoXNombreBtn)
                                    .addGap(18, 18, 18)
                                    .addComponent(descuentoBtn))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(terminarBtn)
                            .addComponent(cancelarBtn))
                        .addGap(114, 114, 114))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(razonSocialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(descuentoGlobalLbl)
                        .addGap(18, 18, 18)
                        .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(volverBtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                .addGap(29, 29, 29)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(nombreProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(139, 139, 139)
                                                        .addComponent(jLabel18)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(leerPrecioBtn)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(precioProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(leerCantidadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(grabarPrecioBtn)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(imprimeChk))
                                                            .addComponent(grabarCantidadBtn)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel12)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(descuentoVolumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel8)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(524, 524, 524)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(13, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(razonSocialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoGlobalLbl)
                    .addComponent(volverBtn)
                    .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarProductoXNombreBtn)
                    .addComponent(jLabel6)
                    .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(descuentoVolumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoBtn))
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
                    .addComponent(incorporarAFacturaBtn)
                    .addComponent(leerPrecioBtn)
                    .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarPrecioBtn)
                    .addComponent(imprimeChk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto1PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto2PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(terminarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la factura?", "Atencion", JOptionPane.YES_NO_OPTION);
        // 0 = si; 1 = no
        if (escape == 0) {
            if (totalFactura > 0.00) {
                guardarRepositorio();
            }
            desbloquearCliente();
            PedidosPendientesFrame ppf = new PedidosPendientesFrame();
            ppf.setVisible(true);
            this.dispose();
        } else {
            prepararCampos();
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        eliminarItemBtn.setEnabled(false);
        texto1PieFacturaTxt.setEnabled(false);
        texto2PieFacturaTxt.setEnabled(false);
        codigoBarrasTxt.setText("");
        agregarProducto();
    }//GEN-LAST:event_agregarBtnActionPerformed

    private void incorporarAFacturaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incorporarAFacturaBtnActionPerformed
        if (!cantidadTxt.getText().isEmpty()) {
            if (!codigoBarrasTxt.getText().isEmpty()) {
                buscarProducto();
            } else {
                if (!codigoProductoTxt.getText().isEmpty()) {
                    buscarProducto();
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código producto");
                    codigoProductoTxt.requestFocus();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad");
            cantidadTxt.requestFocus();
        }
    }//GEN-LAST:event_incorporarAFacturaBtnActionPerformed

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
                    productoSeleccionado = new ProductoService().getProductosByFiltroSin90SinDepo(filtro).get(seleccion - 1);
                    codigoProductoTxt.setText(String.valueOf(productoSeleccionado.getCodigo()));
                    consultarProducto();
                    cantidadTxt.requestFocus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar producto");
                    Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboProductosActionPerformed

    private void terminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarBtnActionPerformed
        double xSaldo = clienteFactura.getSaldo();
//        if (clienteFactura.getEntrega() != null) {
//            entrega = clienteFactura.getEntrega();
//        } else {
//            entrega = "";
//        }
        double ySaldo = rint(xSaldo * 100);
        if (ySaldo != 0) {
            JOptionPane.showMessageDialog(this, "Cliente: "
                    + clienteFactura.getRazonSocial()
                    + "\n Saldo: " + df.format(xSaldo));
        }
        int escape = JOptionPane.showConfirmDialog(null, "Quiere ingresar un texto antes de Imprimir?",
                "Texto en el pie de Pedido",
                JOptionPane.YES_NO_OPTION);
        if (escape == 0) {
            texto1PieFacturaTxt.setEnabled(true);
            texto2PieFacturaTxt.setEnabled(true);
            texto1PieFacturaTxt.requestFocus();
        } else {
            terminarBtn.setEnabled(false);
            escape = JOptionPane.showConfirmDialog(null, "Confirma Terminar Factura?",
                    "FINALIZAR FACTURA",
                    JOptionPane.YES_NO_OPTION);
            if (escape == 0) {
                terminarFactura();
            } else {
                agregarProducto();
                terminarBtn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_terminarBtnActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        MainFrame ff = new MainFrame();
        ff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void codigoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTxtKeyPressed

    }//GEN-LAST:event_codigoTxtKeyPressed

    private void codigoProductoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoProductoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoProductoTxt.getText().isEmpty()) {
                consultarProducto();
                incorporarAFacturaBtn.setEnabled(true);
            } else {
                nombreProductoABuscarTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la carga factura?", "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape != 1) {
                    PedidosPendientesFrame ppf = new PedidosPendientesFrame();
                    ppf.setVisible(true);
                    this.dispose();
                } else {
                    prepararCampos();
                }
            } else {
                if (evt.getKeyCode() != 8) {
                    if (!isNumeric(evt)) {
                        JOptionPane.showMessageDialog(this, "Solo números");
                        codigoBarrasTxt.setText("");
                        codigoBarrasTxt.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_codigoProductoTxtKeyPressed

    private boolean isNumeric(KeyEvent evt) {
        String cod = String.valueOf(evt.getKeyChar());
        try {
            Integer.parseInt(cod);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private void cantidadTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!cantidadTxt.getText().isEmpty()) {
                if (!codigoBarrasTxt.getText().isEmpty()) {
                    buscarProducto();
                } else {
                    if (!codigoProductoTxt.getText().isEmpty()) {
                        buscarProducto();
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código producto");
                        codigoProductoTxt.requestFocus();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad");
                cantidadTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                codigoBarrasTxt.setText("");
                nombreProductoABuscarTxt.setEnabled(true);
                codigoProductoTxt.requestFocus();
            } else {
                if (evt.getKeyCode() != 8) {
                    if (!isNumeric(evt)) {
                        JOptionPane.showMessageDialog(this, "Solo números");
                        codigoTxt.setText("");
                    }
                }
            }
        }
    }//GEN-LAST:event_cantidadTxtKeyPressed

    private void eliminarItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarItemBtnActionPerformed
        int selectRow = tablaFactura.getSelectedRow();
        if (selectRow != -1) {
            int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de Eliminar Item?", "Atencion", JOptionPane.YES_NO_OPTION);
            // 0 = si; 1 = no
            if (escape == 0) {
                tabla.removeRow(selectRow);
                renglonFactura.remove(selectRow);
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
        texto1PieFacturaTxt.setEnabled(false);
        texto2PieFacturaTxt.setEnabled(false);
        codigoBarrasTxt.setText("");
        agregarProducto();
    }//GEN-LAST:event_agregarBtnKeyPressed

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
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la factura?", "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape == 0) {
                    PedidosPendientesFrame ppf = new PedidosPendientesFrame();
                    ppf.setVisible(true);
                    this.dispose();
                } else {
                    agregarProducto();
                }
            }
        }
    }//GEN-LAST:event_nombreProductoABuscarTxtKeyPressed

    private void codigoBarrasTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoBarrasTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoBarrasTxt.getText().isEmpty()) {
                consultarProductoBarras();
                incorporarAFacturaBtn.setEnabled(true);
                cantidadTxt.requestFocus();
            } else {
                nombreProductoABuscarTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la factura?", "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape == 0) {
//                    if (totalFactura > 0.00) {
//                        guardarRepositorio();
//                    }
                    PedidosPendientesFrame ppf = new PedidosPendientesFrame();
                    ppf.setVisible(true);
                    this.dispose();
                } else {
                    prepararCampos();
                }
            } else {
                if (evt.getKeyCode() != 8) {
                    if (!isNumeric(evt)) {
                        JOptionPane.showMessageDialog(this, "Solo números");
                        codigoTxt.setText("");
                    }
                }
            }
        }
    }//GEN-LAST:event_codigoBarrasTxtKeyPressed

    private void leerCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerCantidadBtnActionPerformed
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            RenglonFactura rf = renglonFactura.get(lin);
            nuevaCantidadTxt.setText(String.valueOf(rf.getCantidad().intValue()));
            nuevaCantidadTxt.setEnabled(true);
            leerCantidadBtn.setEnabled(false);
            grabarCantidadBtn.setEnabled(true);
            tablaFactura.setEnabled(false);
            agregarBtn.setEnabled(false);
            eliminarItemBtn.setEnabled(false);
            leerPrecioBtn.setEnabled(false);
            nuevoPrecioTxt.setEnabled(false);
            terminarBtn.setEnabled(false);
        }
    }//GEN-LAST:event_leerCantidadBtnActionPerformed

    private void grabarCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarCantidadBtnActionPerformed
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            if (!nuevaCantidadTxt.getText().isEmpty()) {
                leerCantidadBtn.setEnabled(true);
                grabarCantidadBtn.setEnabled(false);
                RenglonFactura rf = renglonFactura.get(lin);
                Float cantidadAnterior = rf.getCantidad();
                Float cant = Float.valueOf(nuevaCantidadTxt.getText());
                if (!(cant > 0.0)) {
                    JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                    return;
                }
                rf.setCantidad(cant);
                Double precioUnitario = rf.getGravado() / cantidadAnterior;
                Double impuestoUnitario = rf.getImpuesto() / cantidadAnterior;
                renglonFactura.set(lin, rf);
                tablaFactura.setValueAt(cant.intValue(), lin, 1);
                // en unidad
                calcularLinea(cant, precioUnitario, impuestoUnitario.floatValue());
                tablaFactura.setValueAt(df.format(precioFinal), lin, 3);
                nuevaCantidadTxt.setText("");
                rf.setGravado(gravado);
                rf.setImpuesto(impuesto);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaFactura.setValueAt(df.format(gravado), lin, 4);
                tablaFactura.setValueAt(df.format(impuesto), lin, 5);
                tablaFactura.setValueAt(df.format(iva), lin, 6);
                tablaFactura.setValueAt(df.format(totalLinea), lin, 8);
                tablaFactura.setEnabled(true);
                agregarBtn.setEnabled(true);
                eliminarItemBtn.setEnabled(true);
                leerPrecioBtn.setEnabled(true);
                nuevoPrecioTxt.setEnabled(true);
                terminarBtn.setEnabled(true);
                calcularTotales();
            }
        }
    }//GEN-LAST:event_grabarCantidadBtnActionPerformed

    private void comboProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (comboProductos.getSelectedIndex() > 0) {
                nombreProductoABuscarTxt.setText("");
                nombreProductoABuscarTxt.setEnabled(false);
                buscarProductoXNombreBtn.setEnabled(false);
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
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        String acceso = "";
        try {
            acceso = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            if (!nuevoPrecioTxt.getText().isEmpty()) {
                leerPrecioBtn.setEnabled(true);
                grabarPrecioBtn.setEnabled(false);
                leerCantidadBtn.setEnabled(true);
                tablaFactura.setEnabled(true);
                RenglonFactura rf = renglonFactura.get(lin);
                Float cant = rf.getCantidad();
                Double impuestoUnitario = rf.getImpuesto() / cant;
                Double nuevoImporte = Double.valueOf(nuevoPrecioTxt.getText().replaceAll("\\,", "\\.")) / (1 + (porcentualIva / 100));

                calcularLinea(cant, nuevoImporte, impuestoUnitario.floatValue());

                rf.setGravado(gravado);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaFactura.setValueAt(df.format(precioFinal), lin, 3);
                tablaFactura.setValueAt(df.format(gravado), lin, 4);
                tablaFactura.setValueAt(df.format(impuesto), lin, 5);
                tablaFactura.setValueAt(df.format(iva), lin, 6);
                tablaFactura.setValueAt(df.format(totalLinea), lin, 8);
                nuevoPrecioTxt.setText("");
                nuevoPrecioTxt.setEnabled(false);
                eliminarItemBtn.setEnabled(true);
                terminarBtn.setEnabled(true);
                agregarBtn.setEnabled(true);
                agregarBtn.requestFocus();
                calcularTotales();
            }
        }
    }//GEN-LAST:event_grabarPrecioBtnActionPerformed

    private void nuevaCantidadTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevaCantidadTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int lin = tablaFactura.getSelectedRow();
            if (lin > -1) {
                if (!nuevaCantidadTxt.getText().isEmpty()) {
                    leerCantidadBtn.setEnabled(true);
                    grabarCantidadBtn.setEnabled(false);
                    RenglonFactura rf = renglonFactura.get(lin);
                    Float cantidadAnterior = rf.getCantidad();
                    Float cant = Float.valueOf(nuevaCantidadTxt.getText());
                    if (!(cant > 0.0)) {
                        JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                        return;
                    }
                    rf.setCantidad(cant);
                    Double precioUnitario = rf.getGravado() / cantidadAnterior;
                    Double impuestoUnitario = rf.getImpuesto() / cantidadAnterior;
                    renglonFactura.set(lin, rf);
                    tablaFactura.setValueAt(cant.intValue(), lin, 1);

                    calcularLinea(cant, precioUnitario, impuestoUnitario.floatValue());

                    tablaFactura.setValueAt(df.format(precioFinal), lin, 3);
                    nuevaCantidadTxt.setText("");

                    rf.setGravado(gravado);
                    rf.setImpuesto(impuesto);
                    rf.setIva(iva);
                    rf.setTotal(totalLinea);
                    tablaFactura.setValueAt(df.format(gravado), lin, 4);
                    tablaFactura.setValueAt(df.format(impuesto), lin, 5);
                    tablaFactura.setValueAt(df.format(iva), lin, 6);
                    tablaFactura.setValueAt(df.format(totalLinea), lin, 8);
                    leerPrecioBtn.setEnabled(true);
                    nuevoPrecioTxt.setEnabled(true);
                    nuevaCantidadTxt.setEnabled(true);
                    leerCantidadBtn.setEnabled(true);
                    terminarBtn.setEnabled(true);
                    eliminarItemBtn.setEnabled(true);
                    agregarBtn.setEnabled(true);
                    tablaFactura.setEnabled(true);
                    agregarBtn.requestFocus();
                    calcularTotales();
                } else {
                    JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad");
                    nuevaCantidadTxt.requestFocus();
                }
            }
        } else {
            if (evt.getKeyCode() != 8) {
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
            int lin = tablaFactura.getSelectedRow();
            if (lin > -1) {
                if (!nuevoPrecioTxt.getText().isEmpty()) {
                    leerPrecioBtn.setEnabled(true);
                    grabarPrecioBtn.setEnabled(false);
                    leerCantidadBtn.setEnabled(true);
                    tablaFactura.setEnabled(true);
                    RenglonFactura rf = renglonFactura.get(lin);
                    Float cant = rf.getCantidad();
                    Double impuestoUnitario = rf.getImpuesto() / cant;
                    Double nuevoImporte = Double.valueOf(nuevoPrecioTxt.getText()
                            .replaceAll("\\,", "\\."))
                            / (1 + (porcentualIva / 100));

                    calcularLinea(cant, nuevoImporte, impuestoUnitario.floatValue());

                    rf.setGravado(gravado);
                    rf.setIva(iva);
                    rf.setTotal(totalLinea);
                    tablaFactura.setValueAt(df.format(precioFinal), lin, 3);
                    tablaFactura.setValueAt(df.format(gravado), lin, 4);
                    tablaFactura.setValueAt(df.format(impuesto), lin, 5);
                    tablaFactura.setValueAt(df.format(iva), lin, 6);
                    tablaFactura.setValueAt(df.format(totalLinea), lin, 8);
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
            if (evt.getKeyCode() != 8 && evt.getKeyCode() != 44 && evt.getKeyCode() != 110) {
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
            java.util.logging.Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturaWebPedidoFrame(null, null).setVisible(true);
                //null, null
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton buscarProductoXNombreBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cantidadAtadosMassalinTxt;
    private javax.swing.JTextField cantidadAtadosNoblezaTxt;
    private javax.swing.JTextField cantidadItemsTxt;
    private javax.swing.JTextField cantidadTxt;
    private javax.swing.JTextField codigoBarrasTxt;
    private javax.swing.JTextField codigoProductoTxt;
    private javax.swing.JTextField codigoTxt;
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
    private javax.swing.JTextField importeMassalinTxt;
    private javax.swing.JTextField importeNoblezaTxt;
    private javax.swing.JCheckBox imprimeChk;
    private javax.swing.JButton incorporarAFacturaBtn;
    private javax.swing.JTextField ivaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leerCantidadBtn;
    private javax.swing.JButton leerPrecioBtn;
    private javax.swing.JTextField nombreProductoABuscarTxt;
    private javax.swing.JTextField nombreProductoConsultaTxt;
    private javax.swing.JTextField nuevaCantidadTxt;
    private javax.swing.JTextField nuevoPrecioTxt;
    private javax.swing.JTextField precioProductoConsultaTxt;
    private javax.swing.JTextField razonSocialTxt;
    private javax.swing.JTable tablaFactura;
    private javax.swing.JButton terminarBtn;
    private javax.swing.JTextField texto1PieFacturaTxt;
    private javax.swing.JTextField texto2PieFacturaTxt;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void bloquearCliente() {
        String co = clienteFactura.getCodigo();
        ClienteTraba ct = null;
        try {
            ct = new ClienteTrabaService().getClienteByCodigo(co);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ct.setTraba1(true);
        try {
            new ClienteTrabaService().updateCliente(ct);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al bloquear Cliente");
        }
    }

    private void desbloquearCliente() {
        String co = clienteFactura.getCodigo();

        ClienteTraba ct = null;
        try {
            ct = new ClienteTrabaService().getClienteByCodigo(co);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ct.setTraba1(false);
        clienteFactura.setImporteMostrador(0.0);
        try {
            new ClienteTrabaService().updateCliente(ct);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al desbloquear Cliente");
        }
    }

    private void limpiarCampos() {
        nuevaCantidadTxt.setText("");
        texto1PieFacturaTxt.setText("");
        texto2PieFacturaTxt.setText("");
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
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        nombreProductoABuscarTxt.setText("");
        descuentoVolumenTxt.setText("0.00");
        descuentoLineaTxt.setText("");
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        nombreProductoConsultaTxt.setText("");
        precioProductoConsultaTxt.setText("");
        cantidadItemsTxt.setText("");
        descuentoGlobalTxt.setText("");
        nuevoPrecioTxt.setText("");
        importeMassalinTxt.setText("");
        importeNoblezaTxt.setText("");
        importeMassalinTxt.setEditable(false);
        importeNoblezaTxt.setEditable(false);
        imprimeChk.setSelected(false);
        imprimeChk.setVisible(false);
    }

    private void bloquearCampos() {
        grabarCantidadBtn.setEnabled(false);
        agregarBtn.setEnabled(false);
        cancelarBtn.setEnabled(false);
        terminarBtn.setEnabled(false);
        incorporarAFacturaBtn.setEnabled(false);
        buscarProductoXNombreBtn.setEnabled(false);
        eliminarItemBtn.setEnabled(false);
        descuentoBtn.setEnabled(false);
        ivaTxt.setEditable(false);
        codigoBarrasTxt.setEnabled(false);
        codigoTxt.setEnabled(true);
        codigoTxt.setEditable(true);
        codigoProductoTxt.setEnabled(false);
        fechaTxt.setEditable(false);
        razonSocialTxt.setEditable(false);
        cantidadTxt.setEnabled(false);
        totalTxt.setEnabled(false);
        descuentoVolumenTxt.setEnabled(false);
        nombreProductoABuscarTxt.setEnabled(false);
        nombreProductoConsultaTxt.setEnabled(false);
        descuentoLineaTxt.setEnabled(false);
        comboProductos.setEnabled(false);
        precioProductoConsultaTxt.setEnabled(false);
        cantidadItemsTxt.setEnabled(false);
        texto1PieFacturaTxt.setEnabled(false);
        texto2PieFacturaTxt.setEnabled(false);
        cantidadAtadosMassalinTxt.setEditable(false);
        cantidadAtadosNoblezaTxt.setEditable(false);
        descuentoGlobalTxt.setEditable(false);
    }

    private void llenarComboProductos() {
        filtro = nombreProductoABuscarTxt.getText();
        List<Producto> productos = new ArrayList<Producto>();
        try {
            productos = new ProductoService().getProductosByFiltroSin90SinDepo(filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - leyendo Productos");
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboProductos.getModel();
        if (productos != null && !productos.isEmpty()) {
            for (Producto pro : productos) {
                model.addElement(pro.getDetalle());
            }
            comboProductos.setModel(model);
        }
    }

//    private void buscar() {
//        cargarDatosIniciales();
//        filtro = "";
//        Long id = (long) 1;
//        try {
//            Configuracion conf = new ConfiguracionService().getFacturas(id);
//            porcentualIva = conf.getIva();
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        fecha = Calendar.getInstance().getTime();
//        Cliente cli = new Cliente();
//        categoriaIva = 4;
//        try {
//            clienteFactura = new ClienteService().getClienteByCodigo(codigoTxt.getText());
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error - cliente");
//            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (clienteFactura != null) {
//            razonSocialTxt.setText(clienteFactura.getRazonSocial());
//            fechaTxt.setText(sdf.format(fecha));
//            
//            if (clienteFactura.getCategoriaDeIva() != null) {
//                categoriaIva = clienteFactura.getCategoriaDeIva();
//                if (categoriaIva.equals(1)) {
//                    ivaTxt.setText("Resp. Inscripto");
//                }
//                if (categoriaIva.equals(2)) {
//                    ivaTxt.setText("Monotributo");
//                }
//                if (categoriaIva.equals(3)) {
//                    ivaTxt.setText("Exento");
//                }
//                if (categoriaIva.equals(4)) {
//                    ivaTxt.setText("Consumidor Final");
//                }
//            } else {
//                ivaTxt.setText("Consumidor Final");
//            }
//            if (clienteFactura.getSaldo() != null) {
//                saldoCliente = clienteFactura.getSaldo();
//            } else {
//                saldoCliente = 0.0;
//            }
//            agregarBtn.setEnabled(true);
//            volverBtn.setEnabled(false);
//            cancelarBtn.setEnabled(true);
//            nombreProductoConsultaTxt.setEditable(false);
//            precioProductoConsultaTxt.setEditable(false);
//            codigoTxt.setEditable(false);
//            descuentoVolumenTxt.setEnabled(true);
//            descuentoVolumenTxt.setEditable(false);
//            totalTxt.setEnabled(true);
//            totalTxt.setEditable(false);
//            agregarProducto();
//        } else {
//            JOptionPane.showMessageDialog(this, "Error - cliente no existe");
//            codigoTxt.requestFocus();
//        }
//    }
    private void terminarFactura() {
        numCae = "0";
        sucursalFacturaPapel = "5";
        try {
            config = new ConfiguracionService().getFacturas(1L);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        vencCae = sdf.format(fecha);
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
            numeroFactura = config.getNumeroFacturaA();
            numeroFactura += 1;
            letraFacturaPapel = "A";
        } else {
            numeroFactura = config.getNumeroFacturaB();
            numeroFactura += 1;
            letraFacturaPapel = "B";
        }
        numeroFacturaPapel = numeroFactura.toString();
        try {
            config = new ConfiguracionService().updateConfiguracion(config);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        // presentacion web
        if (tst == 0) {
            try {
                LibraryLoader.loadJacobLibrary();

                ActiveXComponent wsaa = new ActiveXComponent("WSAA");
                System.out.println(Dispatch.get(wsaa, "InstallDir").toString()
                        + " "
                        + Dispatch.get(wsaa, "Version").toString()
                );

                String wsdl = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
                String userdir = "c:/certifaym";
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

                String token = Dispatch.get(wsaa, "Token").toString();
                String sign = Dispatch.get(wsaa, "Sign").toString();

                ActiveXComponent wsfev1 = new ActiveXComponent("WSFEv1");

                Dispatch.put(wsfev1, "Cuit", new Variant("20124127581"));
                Dispatch.put(wsfev1, "Token", new Variant(token));
                Dispatch.put(wsfev1, "Sign", new Variant(sign));

                String cache = "";
                wsdl = "https://servicios1.afip.gov.ar/wsfev1/service.asmx?WSDL";

                Dispatch.call(wsfev1, "Conectar",
                        new Variant(cache),
                        new Variant(wsdl)
                );
                String tipo_cbte = "1";
                if (categoriaIva.equals(1)) {
                    tipo_cbte = "1"; //Factura A
                    letraFacturaPapel = "A";

                } else {
                    tipo_cbte = "6"; //Factura B
                    letraFacturaPapel = "B";
                }
                tipoComprob = tipo_cbte;
                String pto_vta = "5"; // Sucursal declarada WS
                sucursalFacturaPapel = "0005";
                Variant ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                        new Variant(tipo_cbte),
                        new Variant(pto_vta));
                excepcion = Dispatch.get(wsfev1, "Excepcion").toString();

                String fechaWs = new SimpleDateFormat("yyyyMMdd").format(new Date());
                String concepto = "1";// producto 
                System.out.println(clienteFactura.getCuit());
                String cui = clienteFactura.getCuit();
                String cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
                System.out.println(cuit1);
                String tipoD = clienteFactura.getTipo();
                String tipo_doc = tipoD, nro_doc = cuit1; //tipo y numero
                int cbte_nro = Integer.parseInt(ult.toString()) + 1,
                        cbt_desde = cbte_nro,
                        cbt_hasta = cbte_nro;
                numeroFactura = cbte_nro;
                numeroFacturaPapel = String.valueOf(cbte_nro);
                int largo = ("00000000" + numeroFacturaPapel).length();
                numeroFacturaPapel = ("00000000" + numeroFacturaPapel).substring(largo - 8, largo);
//            System.out.println(df.format(totalFactura).toString().replaceAll("\\,", "\\."));
                String imp_total = df.format(totalFactura).toString().replaceAll("\\,", "\\.");//"124.00";
                String imp_tot_conc = "0.00";
                String imp_neto = df.format(totalGravado).toString().replaceAll("\\,", "\\.");
                String imp_iva = df.format(totalIva).toString().replaceAll("\\,", "\\.");//"21.00"
                int internos = (int) rint(totalImpuesto * 100);
//            System.out.println(internos);
                String imp_trib = "", imp_op_ex = "0.00";
                if (internos > 0) {
                    imp_trib = df.format(totalImpuesto).toString().replaceAll("\\,", "\\.");

                } else {
                    imp_trib = "0.00";
                }
                String fecha_cbte = fechaWs, fecha_venc_pago = "";
                String fecha_serv_desde = "", fecha_serv_hasta = "";
                String moneda_id = "PES", moneda_ctz = "1.000";

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

                if (internos > 0) {
                    Variant tributo_id = new Variant(4),
                            tributo_desc = new Variant("Impuestos internos"),
                            tributo_base_imp = new Variant("0.00"),
                            tributo_alic = new Variant("0.00"),
                            tributo_importe = new Variant(df.format(totalImpuesto).toString().replaceAll("\\,", "\\."));
                    Dispatch.call(wsfev1, "AgregarTributo",
                            tributo_id, tributo_desc, tributo_base_imp,
                            tributo_alic, tributo_importe);
                }

                Variant iva_id = new Variant(5),
                        iva_base_imp = new Variant(df.format(totalGravado).toString().replaceAll("\\,", "\\.")),
                        iva_importe = new Variant(df.format(totalIva).toString().replaceAll("\\,", "\\."));
                Dispatch.call(wsfev1, "AgregarIva",
                        iva_id, iva_base_imp, iva_importe);

                Dispatch.put(wsfev1, "Reprocesar", new Variant(false));

                Variant cae = Dispatch.call(wsfev1, "CAESolicitar");

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

                String resultado = Dispatch.get(wsfev1, "Resultado").toString();

                numCae = cae.toString();
                if (!resultado.equals("A")) {
                    JOptionPane.showMessageDialog(this, "Obs: " + obs + "\nError: " + errmsg);
                    return;
                }
                if (vto != "" && vto != null) {
                    vencCae = vto.substring(6, 8) + "/" + vto.substring(4, 6) + "/" + vto.substring(0, 4);
                }
                numCae = cae.toString();
                String ruta1 = "c:/comprobantes/" + tipoComprob
                        + letraFacturaPapel + sucursalFacturaPapel
                        + numeroFacturaPapel + ".xm1";
                String ruta2 = "c:/comprobantes/" + tipoComprob
                        + letraFacturaPapel + sucursalFacturaPapel
                        + numeroFacturaPapel + ".xm2";
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

                for (int i = 0; i < 39; i++) {
                    if (x == 0) {
//                    System.out.println(cadena.substring(i, i + 1).toString());
                        int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                        suma1 += num;
                        x = 1;
                    } else {
//                    System.out.println(cadena.substring(i, i + 1).toString());
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
//                texto2Cae = txtCadenaRP;
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: 1772");
                desbloquearCliente();
                return;
            }
            //99
        }
        // fin presentacion web
        IvaVentas ivaVentas = new IvaVentas();
        saldoCliente = clienteFactura.getSaldo();
        saldoCliente += totalFactura;
        clienteFactura.setSaldo(saldoCliente);
        try {
            new ClienteService().updateCliente(clienteFactura);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1728");
        }
        desbloquearCliente();
        pedido.setFacturado(true);
        try {
            new PedidoService().updatePedido(pedido);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1735");
        }
        Long id = (long) 1;
        try {
            config = new ConfiguracionService().getFacturas(id);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1742");
        }
        config.setUltimaFechaSistema(fecha);
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
            letraFactura = "A";
            // es inscripto
            //sucursalFactura = config.getSucursalA();
//            numeroFactura = config.getNumeroFacturaA();
//            numeroFactura += 0;
            config.setNumeroFacturaA(numeroFactura);
            try {
                config = new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1756");
            }
            ivaVentas.setCodigoTipoDoc(1);
        } else {
            letraFactura = "B";
            // el resto de las categorias
            //sucursalFactura = config.getSucursalB();
//            numeroFactura = config.getNumeroFacturaB();
//            numeroFactura += 0;
            config.setNumeroFacturaB(numeroFactura);
            try {
                config = new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1769");
            }
            ivaVentas.setCodigoTipoDoc(6);
        }
        Vendedor ve = clienteFactura.getVendedor();
        ivaVentas.setCliente(clienteFactura);
        ivaVentas.setVendedor(ve);
        ivaVentas.setDescuentoGlobal(totalDescuento);
        ivaVentas.setPorcentualDescuentoGlobal(descuento);
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
        ivaVentas.setGravadoCigarrillos(totalGravadoCigarrillos);
        ivaVentas.setIva(totalIva);
        ivaVentas.setNoGravado(0.0);
        ivaVentas.setTotal(totalFactura);
        ivaVentas.setLetra(letraFactura);
        ivaVentas.setNumeroSucursal(Integer.valueOf(sucursalFacturaPapel));
        ivaVentas.setNumeroFactura(Integer.valueOf(numeroFacturaPapel));
        if (texto1PieFacturaTxt.getText().isEmpty()) {
            ivaVentas.setTextoPieFactura1("");
        } else {
            ivaVentas.setTextoPieFactura1(texto1PieFacturaTxt.getText());
        }
        if (texto2PieFacturaTxt.getText().isEmpty()) {
            ivaVentas.setTextoPieFactura2("");
        } else {
            ivaVentas.setTextoPieFactura2(texto2PieFacturaTxt.getText());
        }
        ivaVentas.setHd(true);
        for (RenglonFactura reFa : renglonFactura) {
            reFa.setIvaVentas(ivaVentas);
            Integer cod = reFa.getProducto().getCodigo();
            try {
                producto = new ProductoService().getProductoByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1874");
            }
            Float stock = 0.0F;
            if (producto.getStock() != null) {
                stock = producto.getStock();
            } else {
                stock = 0.0F;
            }
            reFa.setProducto(producto);
            stock -= reFa.getCantidad();
            //stock -= reFa.getCantidad();
            if (stock < 0) {
                if (producto.getUnidad() != null) {
                    if (producto.getUnidad()) {
                        if (producto.getProductoCaja() != null) {
                            Producto caja = producto.getProductoCaja();
                            int can = producto.getCantidadCaja();
                            float stk1 = caja.getStock();
                            stk1 -= 1;
                            caja.setStock(stk1);
                            try {
                                new ProductoService().updateProducto(caja);
                            } catch (Exception ex) {
                                Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            stock += can;
                        }
                    }
                }
            }
            producto.setStock(stock);
            try {
                new ProductoService().updateProducto(producto);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1894");
            }
        }
//        try {
//            new FacturaService().saveFactura(ivaVentas, renglonFactura);
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, "Error: 1824");
//        }
        CtaCteCliente ccc = new CtaCteCliente();
        ccc.setCliente(clienteFactura);
        ccc.setFactura(ivaVentas);
        ccc.setFecha(fecha);
        ccc.setDebe(totalFactura);
        ccc.setHaber(0.0);
        ccc.setTipo("FC");
        ccc.setSaldo(saldoCliente);
//        try {
//            new CtaCteClienteService().saveCtaCteCliente(ccc);
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, "Error: 1838");
//        }
        int reproceso = 0;
        try {
            new FacturaService().saveFacturaCompleta(clienteFactura, config, ccc, ivaVentas, renglonFactura);
            reproceso = 1;
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Errora acualizando datos Factura - REPROCESO");
            reproceso = 0;
        }
        if (reproceso == 0) {
            try {
                new FacturaService().saveFacturaCompleta(clienteFactura, config, ccc, ivaVentas, renglonFactura);
                JOptionPane.showMessageDialog(this, "Factura Registrada correctamente");
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error acualizando datos Factura - el Documento ingreso en AFIP");
            }
        }
        desbloquearCliente();
        generarFactura(ivaVentas);
        MainFrame ff = new MainFrame();
        ff.setVisible(true);
        this.dispose();
    }

    private void generarFactura(IvaVentas i_v) {
//        String catego = "";
        if (categoriaIva.equals(1)) {
//            catego = "Responsable Inscripto       ";
            modeloFcPapel += "1";
        }
        if (categoriaIva.equals(2)) {
//            catego = "Monotributo                 ";
            modeloFcPapel += "1";
        }
        if (categoriaIva.equals(3)) {
//            catego = "Exento                      ";
            modeloFcPapel += "6";
        }
        if (categoriaIva.equals(4)) {
//            catego = "Consumidor Final            ";
            modeloFcPapel += "6";
        }
        String cui = clienteFactura.getCuit();
        String pri = "";
        String med = "";
        String fin = "";
        int lgo = cui.length();
        if (lgo != 13) {
            cui = "0000000000000" + cui;
            int lgo1 = cui.length();
            fin = cui.substring(lgo1 - 11, lgo1);
        }
        if (lgo > 11) {
            pri = cui.substring(0, 2);
            med = cui.substring(3, 11);
            fin = cui.substring(12, 13);
        }
        fecha_qr = sdf_qr.format(fecha);
        cuit_qr = pri + med + fin;
        tipoComprobante_qr = modeloFcPapel;
        numeroComprobante_qr = i_v.getNumeroFactura().toString();
        importe_qr = df.format(i_v.getTotal());
        tipoDoc_qr = clienteFactura.getTipo();
        numeroDoc_qr = clienteFactura.getCuit();
        nroCae_qr = numCae;
        String data = "{\"ver\":" + ver_qr
                + ",\"fecha\":\"" + fecha_qr + "\""
                + ",\"cuit\":" + cuit_qr
                + ",\"ptoVta\":" + puntoVenta_qr
                + ",\"tipoCmp\":" + tipoComprobante_qr
                + ",\"nroCmp\":" + numeroComprobante_qr
                + ",\"importe\":" + importe_qr
                + ",\"moneda\":\"" + moneda_qr + "\""
                + ",\"ctz\":" + cotiz_qr
                + ",\"tipoDocRec\":" + tipoDoc_qr
                + ",\"nroDocRec\":" + numeroDoc_qr
                + ",\"tipoCodAut\":\"" + tipoCodigoAutoriz_qr + "\""
                + ",\"codAut\":" + nroCae_qr + "}";
        try {
            generarQR(data);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        renglones = new String[maxNro];
//        textoFacturaPapel = "FACTURA";
//        fechaFacturaPapel = sdf.format(fecha);
//        clienteFacturaPapel = razonSocialTxt.getText();
        codigoClienteFacturaPapel = clienteFactura.getCodigo();
//        direccionFacturaPapel = clienteFactura.getDomicilio().getCalle() + " " + clienteFactura.getDomicilio().getNumero() + " - " + clienteFactura.getDomicilio().getLocalidad();
//        cuitFacturaPapel = clienteFactura.getCuit();
//        String condVta = "";
//        Date fechaVto = fecha;
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
//        if (clienteFactura.getFormaDePago().equals(1)) {
//            condVta = "CONTADO               ";
//        }
//        if (clienteFactura.getFormaDePago().equals(2)) {
//            condVta = "7 DIAS F.F            ";
//            cal.add(Calendar.DATE, 7);
//            fechaVto = cal.getTime();
//        }
//        if (clienteFactura.getFormaDePago().equals(3)) {
//            condVta = "14 DIAS F.F.          ";
//            cal.add(Calendar.DATE, 14);
//            fechaVto = cal.getTime();
//        }
//        if (clienteFactura.getFormaDePago().equals(4)) {
//            condVta = "OTRO                  ";
//            fechaVto = null;
//        }
//        condicionVentaFacturaPapel = condVta;
        //vencimientoFacturaPapel = sdf.format(fechaVto);
//        catego = "";
        modeloFcPapel = "Cod.Nro.";
        if (categoriaIva.equals(1)) {
//            catego = "Responsable Inscripto       ";
            modeloFcPapel += "1";
        }
        if (categoriaIva.equals(2)) {
//            catego = "Monotributo                 ";
            modeloFcPapel += "1";
        }
        if (categoriaIva.equals(3)) {
//            catego = "Exento                      ";
            modeloFcPapel += "6";
        }
        if (categoriaIva.equals(4)) {
//            catego = "Consumidor Final            ";
            modeloFcPapel += "6";
        }
//        inscripcionClienteFacturaPapel = catego;
//        if (categoriaIva != 1) {
//            //                                    1         2         3         4         5         6         7         8         9        10
//            //                           1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
//            nombresColumnaFacturaPapel = "  IT   CANT                   DETALLE                  P.UNIT.    DESC.   IMPORTE       IMP.     TOTAL     SUG";
//        } else {
//            nombresColumnaFacturaPapel = "  IT   CANT                   DETALLE                 P.UNIT.    DESC.   GRAVADO      IVA       IMP.     TOTAL     SUG";
//        }
        //DecimalFormat df = new DecimalFormat("#0.00");
        int maxTabla = tablaFactura.getRowCount();
        for (int r = 0; r < maxNro; r++) {
            if (r < maxTabla) {
                String str0 = String.valueOf(r + 1);
                int largo = str0.length();
                if (largo < 2) {
                    renglones[r] = " " + str0 + " ";
                } else {
                    renglones[r] = str0 + " ";
                }
                str0 = tablaFactura.getValueAt(r, 1).toString();
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
                str0 = tablaFactura.getValueAt(r, 2).toString();
                String espacio = " ";
                largo = str0.length();
                if (largo > 40) {
                    str0 = str0.substring(0, 40);
                    tablaFactura.setValueAt(str0, r, 2);
                } else {
                    for (int l = largo; l < 40; l++) {
                        espacio += " ";
                    }
                }
                renglones[r] = renglones[r] + " " + tablaFactura.getValueAt(r, 2).toString() + espacio;
                if (categoriaIva != 1 && categoriaIva != 2) {
//                  aqui detalle de importes no inscripto en IVA           *****
// Precio Unitario
                    str0 = tablaFactura.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
                    str0 = tablaFactura.getValueAt(r, 7).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Importe
                    str0 = tablaFactura.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    Double calculo = Double.valueOf(str0);
                    str0 = tablaFactura.getValueAt(r, 6).toString();
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
                    str0 = tablaFactura.getValueAt(r, 5).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaFactura.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaFactura.getValueAt(r, 9).toString();
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
                    str0 = tablaFactura.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
                    str0 = tablaFactura.getValueAt(r, 7).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Gravado
                    str0 = tablaFactura.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Iva
                    str0 = tablaFactura.getValueAt(r, 6).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Impuesto
                    str0 = tablaFactura.getValueAt(r, 5).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaFactura.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaFactura.getValueAt(r, 9).toString();
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
        String str0 = String.valueOf(saldoCliente - totalFactura);
        str0 = str0.replace(",", ".");
        Double doble = Double.valueOf(str0);
        int largo = doble.intValue();
        String espacio = "          ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
// Total Factura
        str0 = String.valueOf(totalFactura);
        str0 = str0.replace(",", ".");
        doble = Double.valueOf(str0);
        largo = doble.intValue();
        espacio = "        ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
//        importeTotalFacturaPapel = espacio + df.format(doble);
// Linea Totales
        if (categoriaIva != 1) {
//            lineaTitulos = "                                                           Impuesto                  Total Factura";
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                                                            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
//            lineaTotalesFacturaPapel = espacio + df.format(doble);
        } else {
//            lineaTitulos = "                  Gravado       Impuesto                            Iva             Total Factura";
            str0 = df.format(totalGravado);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                 ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
//            lineaTotalesFacturaPapel = espacio + df.format(doble);
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "           ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
//            lineaTotalesFacturaPapel += espacio + df.format(doble);
            str0 = String.valueOf(totalIva);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                 ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
//            lineaTotalesFacturaPapel += espacio + df.format(doble);
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
// Cantidades atados
//        cantidadesFacturaPapel = "                  CANT.ATADOS NOBLEZA: " + String.valueOf(cantidadAtadosNobleza);
//        cantidadesFacturaPapel += "                 CANT ATADOS MASSALIN: " + String.valueOf(cantidadAtadosMassalin);
//        texto1FacturaPapel = texto1PieFacturaTxt.getText();
//        texto2FacturaPapel = texto2PieFacturaTxt.getText();
//        texto3FacturaPapel = "-";
//        texto1Cae = String.valueOf(numCae);
        pdf(i_v, renglonFactura);
        if (imprimeChk.isSelected()) {
            PrinterJob pj = PrinterJob.getPrinterJob();
            PageFormat pf = pj.defaultPage();
            Paper paper = new Paper();
            double margin = 8;
            paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
            pf.setPaper(paper);
//            pj.setPrintable(new MyPrintable(), pf);
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
        incorporarAFacturaBtn.setEnabled(true);
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
        filtro = "";
        if (nro < maxNro - 1) {
            agregarBtn.setEnabled(true);
        } else {
            agregarBtn.setEnabled(false);
        }
        terminarBtn.setEnabled(true);
        eliminarItemBtn.setEnabled(true);
        cancelarBtn.setEnabled(true);
        leerCantidadBtn.setEnabled(true);
        leerPrecioBtn.setEnabled(true);
        nuevoPrecioTxt.setEnabled(true);
        nuevaCantidadTxt.setEnabled(true);
        Producto pro = null;
        encontrado = false;
        if (!codigoBarrasTxt.getText().isEmpty()) {
            try {
                pro = new ProductoService().getProductoByCodigoBarras(Long.valueOf(codigoBarrasTxt.getText()));
                encontrado = true;
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoProductoTxt.requestFocus();
            }
        } else {
            if (!codigoProductoTxt.getText().isEmpty()) {
                try {
                    pro = new ProductoService().getProductoByCodigo(Integer.valueOf(codigoProductoTxt.getText()));
                    encontrado = true;
                } catch (Exception ex) {
                    Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
                    codigoProductoTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese Código de Producto o Código de Barras");
                codigoProductoTxt.requestFocus();
            }
        }
        if (pro != null) {
            if (pro.getInactivo() != null) {
                if (!pro.getInactivo() || pro.getSubRubro().getCodigo() == 5099) {
                    cantidad = Float.valueOf(cantidadTxt.getText());
                    Double prec = pro.getPrecio();
                    Float impu = pro.getImpuesto();

                    if (!(cantidad > 0.0)) {
                        JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                        cantidadTxt.requestFocus();
                        return;
                    }
                    calcularLinea(cantidad, prec, impu);

                    RenglonFactura rf = new RenglonFactura();
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
                    renglonFactura.add(rf);
                    calcularTotales();
                    Object[] fila = new Object[10];
                    fila[0] = pro.getCodigo();
                    Float can = Float.valueOf(cantidadTxt.getText());
                    fila[1] = df1.format(can);
                    fila[2] = pro.getDetalle();
                    fila[3] = df.format(precioFinal);
                    fila[4] = df.format(gravado);
                    fila[5] = df.format(impuesto);
                    fila[6] = df.format(iva);
                    fila[7] = df.format(0.0);
                    fila[8] = df.format(totalLinea);
                    fila[9] = df.format(pro.getSugerido());
                    tabla.addRow(fila);
                    Rectangle rect = tablaFactura.getCellRect(nro - 1, 0, true);
                    tablaFactura.scrollRectToVisible(rect);
                    tablaFactura.clearSelection();
                    tablaFactura.setRowSelectionInterval(nro - 1, nro - 1);
                    tablaFactura.setModel(tabla);
                    codigoProductoTxt.setEnabled(false);
                    codigoBarrasTxt.setEnabled(false);
                    cantidadTxt.setEnabled(false);
                    incorporarAFacturaBtn.setEnabled(false);
                    cantidadTxt.setText("");
                    codigoProductoTxt.setText("");
                    buscarProductoXNombreBtn.setEnabled(false);
                    nombreProductoABuscarTxt.setEnabled(false);
                    comboProductos.setEnabled(false);
                    texto1PieFacturaTxt.setEnabled(true);
                    texto2PieFacturaTxt.setEnabled(true);
                    agregarBtn.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                    codigoProductoTxt.setText("");
                    cantidadTxt.setText("");
                    nombreProductoABuscarTxt.requestFocus();
                    //codigoProductoTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                codigoProductoTxt.setText("");
                cantidadTxt.setText("");
                nombreProductoABuscarTxt.requestFocus();
                //codigoProductoTxt.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error - producto no existe");
            codigoProductoTxt.setText("");
            //codigoBarrasTxt.requestFocus();
            codigoProductoTxt.requestFocus();
        }
        comboProductos.removeAllItems();
        comboProductos.addItem("");
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
                    precioProductoConsulta += precioProductoConsulta * porcentualIva / 100;
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
        grabarCantidadBtn.setEnabled(false);
        leerCantidadBtn.setEnabled(true);
        totalGravado = 0.0;
        totalGravadoCigarrillos = 0.00;
        totalIva = 0.0;
        totalImpuesto = 0.0;
        totalFactura = 0.0;
        nro = 0;
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        Double acumuladoParaDescuentoGlobal = 0.0;
        Double importeTotalMassalin = 0.0;
        Double importeTotalNobleza = 0.0;
        for (RenglonFactura renFa : renglonFactura) {
            //totalFactura += renFa.getTotal();
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
            if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
//          IVA DISCRIMINADO                
                totalFactura += renFa.getTotal();
                if (renFa.getProducto().getRubro().getCodigo().equals(5)) {
                    acumuladoParaDescuentoGlobal += renFa.getGravado();
                }
            } else {
//          CONSUMIDOR FINAL
                totalFactura += renFa.getTotal();
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
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
            Double t1 = totalFactura - totalImpuesto;
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
            totalFactura = totalGravado + totalIva + totalImpuesto;
            String tf = df.format(totalFactura);
            totalFactura = Double.valueOf(tf.replace(",", "."));
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
            String tf = df.format(totalFactura - totalDescuento);
            totalFactura = Double.valueOf(tf.replace(",", "."));
            totalTxt.setText(tf);
            String ti = df.format(totalImpuesto);
            totalImpuesto = Double.valueOf(ti.replace(",", "."));
            totalGravado = (totalFactura - totalImpuesto) / (1 + (porcentualIva / 100));
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
        importeNoblezaTxt.setText(String.valueOf(df.format(importeTotalNobleza)));
        cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
        importeMassalinTxt.setText(String.valueOf(df.format(importeTotalMassalin)));
        cantidadItemsTxt.setText(String.valueOf(nro));
        totalTxt.setText(String.valueOf(df.format(totalFactura)));
        clienteFactura.setImporteMostrador(totalFactura);
        try {
            new ClienteService().updateCliente(clienteFactura);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 2512");
        }
//        totalGravadoCigarrillos = rint(((importeTotalMassalin
//                + importeTotalNobleza
//                - totalImpuesto) / (1 + (porcentualIva / 100)))
//                * 100) / 100;
//        totalGravado = rint(((totalFactura
//                - totalImpuesto) / (1 + (porcentualIva / 100)))
//                * 100) / 100;
//        totalIva = rint(totalGravado
//                * (porcentualIva / 100) * 100) / 100;
//        cantidadItemsTxt.setText(String.valueOf(nro));
//        cantidadAtadosNoblezaTxt.setText(String.valueOf(cantidadAtadosNobleza));
//        importeNoblezaTxt.setText(String.valueOf(df.format(importeTotalNobleza)));
//        cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
//        importeMassalinTxt.setText(String.valueOf(df.format(importeTotalMassalin)));
//        cantidadItemsTxt.setText(String.valueOf(nro));
//        totalTxt.setText(String.valueOf(df.format(totalFactura)));
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
                //Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoProductoTxt.setText("");
                codigoProductoTxt.requestFocus();
            }
        }
    }

    private void cargarCliente() {
        codigoClienteFacturaPapel = clienteFactura.getCodigo();
        codigoTxt.setText(codigoClienteFacturaPapel);
        razonSocialTxt.setText(clienteFactura.getRazonSocial());
        cuitTxt.setText(clienteFactura.getCuit());
        if (clienteFactura.getCategoriaDeIva() != null) {
            categoriaIva = clienteFactura.getCategoriaDeIva();
        }
        if (clienteFactura.getTieneDescuento() != null) {
//            tieneDto = clienteFactura.getTieneDescuento();
            descuento = clienteFactura.getDescuento();
        }
        if (pedido.getPorcentualDescuentoGlobal() != null) {
            if (pedido.getPorcentualDescuentoGlobal() > 0) {
                descuentoGlobalTxt.setText(df2.format(pedido.getPorcentualDescuentoGlobal()));
                descuento = pedido.getPorcentualDescuentoGlobal();
            } else {
                descuentoGlobalTxt.setText("0.0");
                descuento = 0F;
            }
        } else {
            descuentoGlobalTxt.setText("0.0");
            descuento = 0F;
        }
        if (categoriaIva.equals(1)) {
            ivaTxt.setText("Resp. Inscripto");
        }
        if (categoriaIva.equals(2)) {
            ivaTxt.setText("Monotributo");
        }
        if (categoriaIva.equals(3)) {
            ivaTxt.setText("Exento");
        }
        if (categoriaIva.equals(4)) {
            ivaTxt.setText("Consumidor Final");
        }
        fecha = Calendar.getInstance().getTime();
        fechaTxt.setText(sdf.format(fecha));
    }

    private void cargarRenglones() {
        if (pedido.getTextoPiePedido1() != null) {
            texto1PieFacturaTxt.setText(pedido.getTextoPiePedido1());
        }
        if (pedido.getTextoPiePedido2() != null) {
            texto2PieFacturaTxt.setText(pedido.getTextoPiePedido2());
        }
        try {
            renglonP = new RenglonPedidoService().getAllRenglonPedidoFromPedido(pedido);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabla = (DefaultTableModel) tablaFactura.getModel();

        for (RenglonPedido rp : renglonP) {
            RenglonFactura rf = new RenglonFactura();
            rf.setCantidad(rp.getCantidad());
            rf.setProducto(rp.getProducto());
            rf.setItemNro(rp.getItemNro());
            rf.setDescripcion(rp.getDescripcion());
            rf.setGravado(rp.getGravado());
            rf.setNoGravado(rp.getNoGravado());
            rf.setExento(rp.getExento());
            rf.setImpuesto(rp.getImpuesto());
            rf.setDescuento(rp.getDescuento());
            rf.setIva(rp.getIva());
            rf.setTotal(rp.getTotal());
            rf.setSugerido(rp.getSugerido());
            renglonFactura.add(rf);
            Object linea[] = new Object[10];
            linea[0] = rp.getProducto().getCodigo();
            linea[1] = rp.getCantidad().intValue();
            linea[2] = rp.getDescripcion();
            linea[3] = df.format((rp.getGravado() + rp.getImpuesto() + rp.getIva()) / rp.getCantidad());
            linea[4] = df.format(rp.getGravado());
            linea[5] = df.format(rp.getImpuesto());
            linea[6] = df.format(rp.getIva());
            linea[7] = 0.00;
            linea[8] = df.format(rp.getTotal());
            linea[9] = df.format(rp.getSugerido());
            tabla.addRow(linea);
        }
        tablaFactura.setModel(tabla);
        agregarBtn.setEnabled(true);
        volverBtn.setEnabled(false);
        cancelarBtn.setEnabled(true);
        nombreProductoConsultaTxt.setEditable(false);
        precioProductoConsultaTxt.setEditable(false);
        codigoTxt.setEditable(false);
        descuentoVolumenTxt.setEnabled(true);
        descuentoVolumenTxt.setEditable(false);
        totalTxt.setEnabled(true);
        totalTxt.setEditable(false);

        calcularTotales();
        cancelarBtn.setEnabled(true);
        terminarBtn.setEnabled(true);
        eliminarItemBtn.setEnabled(true);
        grabarPrecioBtn.setEnabled(false);

        agregarBtn.requestFocus();
    }

    private void cargarDatosIniciales() {
        filtro = "";
        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        fecha = Calendar.getInstance().getTime();
    }

    private void calcularLinea(Float cantid, Double precio, Float impuest) {
        precioFinal = rint(((precio
                * (1 + (porcentualIva / 100)))
                + impuest) * 100) / 100;

        // por cantidad
        totalLinea = rint((precioFinal * cantid) * 100) / 100;
        impuesto = rint(impuest * cantid * 100) / 100;
        Double calculo = (totalLinea - impuesto)
                / (1 + (porcentualIva / 100));
        gravado = rint((calculo) * 100) / 100;
        iva = rint((gravado * porcentualIva
                / 100) * 100) / 100;
    }

    private void guardarRepositorio() {
        FcReserved fcr = new FcReserved();
        fcr.setCliente(clienteFactura);
        fcr.setFecha(fecha);
        fcr.setTotal(totalFactura);
        fcr.setImpuesto(totalImpuesto);
        fcr.setGravado(totalGravado);
        fcr.setGravadoCigarrillos(totalGravadoCigarrillos);
        fcr.setIva(totalIva);
        fcr.setDescuentoGlobal(totalDescuento);
        fcr.setPorcentualDescuentoGlobal(descuento);
        fcr.setExento(0.00);
        fcr.setNoGravado(0.00);
        try {
            fcr = new FcReservedService().saveFcReserved(fcr);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (RenglonFactura rf : renglonFactura) {
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

    private void prepararCampos() {
        incorporarAFacturaBtn.setEnabled(false);
        eliminarItemBtn.setEnabled(true);
        agregarBtn.setEnabled(true);
        leerCantidadBtn.setEnabled(true);
        leerPrecioBtn.setEnabled(true);
        nuevaCantidadTxt.setText("");
        nuevoPrecioTxt.setText("");
        nuevaCantidadTxt.setEnabled(true);
        nuevoPrecioTxt.setEnabled(true);
        grabarCantidadBtn.setEnabled(false);
        grabarPrecioBtn.setEnabled(false);
        codigoBarrasTxt.setEnabled(false);
        codigoProductoTxt.setEnabled(false);
        cantidadTxt.setEnabled(false);
        nombreProductoABuscarTxt.setEnabled(false);
        comboProductos.setEnabled(false);
        agregarBtn.requestFocus();
        tablaFactura.setEnabled(true);
    }

    private void leerPrecio() {
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            RenglonFactura rf = renglonFactura.get(lin);
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
            agregarBtn.setEnabled(false);
        }
    }

    private void generarQR(String data) throws Exception {
        String cadenaCodificada = Base64.getEncoder().encodeToString(data.getBytes());
        BitMatrix matriz;
        Writer writer = new QRCodeWriter();
        try {
            matriz = writer.encode(url_qr + cadenaCodificada, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return;
        }
        BufferedImage imagen = new BufferedImage(qrTamAncho,
                qrTamAlto, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < qrTamAlto; y++) {
            for (int x = 0; x < qrTamAncho; x++) {
                int valor = (matriz.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (valor == 0 ? 0 : 0xFFFFFF));
            }
        }
        //99
        FileOutputStream qrCode;
        String numQr1 = dfn.format(numeroFactura);
        qrCode = new FileOutputStream(ruta + numQr1 + extension);
        ImageIO.write(imagen, formato, qrCode);
        qrCode.close();
    }

    private void pdf(IvaVentas iv, List<RenglonFactura> rf) {
        String code = iv.getCliente().getCodigo();
        Cliente cli = null;
        try {
            cli = new ClienteService().getClienteByCodigo(code);
        } catch (Exception ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
                File pdf = new PDFBuilder1().armarFcA(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            } else {
                File pdf = new PDFBuilder1().armarFcB(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            }
            JOptionPane.showMessageDialog(this, "PDF GENERADO CORRECTAMENTE");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err1");
            JOptionPane.showMessageDialog(this, "ERROR FILE 3044");
        } catch (DocumentException ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err2");
            JOptionPane.showMessageDialog(this, "ERROR DOCUMENT 3048");
        } catch (Exception ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err3");
            JOptionPane.showMessageDialog(this, "ERROR EXCEPTION 3052");
        }
    }
}
