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
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.Producto;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.entities.RenglonNotaCredito;
import ar.com.ventas.entities.Usuario;
import ar.com.ventas.entities.Vendedor;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.ClienteTrabaService;
import ar.com.ventas.services.ConfiguracionService;
import ar.com.ventas.services.CtaCteClienteService;
import ar.com.ventas.services.FacturaService;
import ar.com.ventas.services.NotaCreditoService;
import ar.com.ventas.services.ProductoService;
import ar.com.ventas.services.RenglonNotaCreditoService;
import ar.com.ventas.services.UsuarioService;
import ar.com.ventas.util.DesktopApi;
import ar.com.ventas.util.PDFBuilder2;
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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class NotaDebitoWebDeNcFrame extends javax.swing.JFrame {

    public List<RenglonFactura> renglonFactura = new ArrayList<RenglonFactura>();
    
    private static final int qrTamAncho = 150;
    private static final int qrTamAlto = 150;
    private static final String formato = "png";
    private static final String ruta = "c://qr//codigoQR";
    private static final String extension = ".png";
    private static final SimpleDateFormat sdf_qr = new SimpleDateFormat("yyyy-MM-dd");
    private final DecimalFormat df_qr = new DecimalFormat("00000000");
    private DecimalFormat df_matriz = new DecimalFormat("00000000");
    private final String url_qr = "https://www.afip.gob.ar/fe/qr/?p=";
    private final String ver_qr = "1";
    private String fecha_qr;
    private final String cuit_qr = "20124127581";
    private String puntoVenta_qr = "5";
    private String tipoComprobante_qr;
    private String numeroComprobante_qr;
    private String importe_qr;
    private final String moneda_qr = "PES";
    private final String cotiz_qr = "1";
    private String tipoDoc_qr;
    private String numeroDoc_qr;
    private final String tipoCodigoAutoriz_qr = "E";
    private String nroCae_qr;
    
    private String lineaTitulos = "";
    private String textoFacturaPapel;
    private String modeloFcPapel;
    private String fechaFacturaPapel;
    private String clienteFacturaPapel;
    private String codigoClienteFacturaPapel;
    private String direccionFacturaPapel;
    private String cuitFacturaPapel;
    private String condicionVentaFacturaPapel;
    private String inscripcionClienteFacturaPapel;
    private String nombresColumnaFacturaPapel;
    private String[] renglones = null;
    private String texto1FacturaPapel;
    private String texto2FacturaPapel;
    private String texto3FacturaPapel;
    private String lineaTotalesFacturaPapel;
    private String importeTotalFacturaPapel;
    private String cantidadesFacturaPapel;
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
    private Double totalNoGravado = 0.0;
    private Double gravado = 0.00;
    private Double noGravado = 0.00;
    private Double iva = 0.00;
    private Float porcentualIva;
    private Double impuesto = null;
    private Double totalLinea = 0.00;
    private Boolean tieneDto = false;
    private Integer categoriaIva = 1;
    private DecimalFormat df = new DecimalFormat("#0.00");
    private DecimalFormat df1 = new DecimalFormat("#0");
    private DecimalFormat dfs = new DecimalFormat("0000");
    private DecimalFormat dfn = new DecimalFormat("00000000");
    private Double saldoCliente = 0.00;
    private String letraFactura;
    private Integer numeroFactura;
    private Configuracion config = null;
    private Double precioFinal = 0.0;
    private Integer nro = 0;
    private Integer maxNro = 41;
    private Integer cantidadAtadosMassalin;
    private Integer cantidadAtadosNobleza;
    public Boolean encontrado;
    private List<RenglonNotaCredito> renglonP = null;
    private String numCae;
    private String texto1Cae = "";
    private String texto2Cae = "";
    private String vencCae = "";
    private String tipoComprob = "";
    private Double totalGravadoCigarrillos = 0.00;
    private IvaVentas nc = null;
    private String documentoReferencia = "";
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form FacturaFrame
     *
     * @param nc
     */
    public NotaDebitoWebDeNcFrame(IvaVentas nc) {
        //Cliente cli, Pedido pe
        getContentPane().setBackground(new java.awt.Color(135, 206, 235));
        initComponents();
        this.nc = nc;
        this.setLocationRelativeTo(null);
//        this.order_name = order_name;
//        this.order_num = order_num;
        limpiarCampos();
        bloquearCampos();
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
        codigoTxt = new javax.swing.JTextField();
        razonSocialTxt = new javax.swing.JTextField();
        fechaTxt = new javax.swing.JTextField();
        ivaTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        totalTxt = new javax.swing.JTextField();
        terminarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        eliminarItemBtn = new javax.swing.JButton();
        descuentoGlobalLbl = new javax.swing.JLabel();
        descuentoGlobalTxt = new javax.swing.JTextField();
        texto1PieFacturaTxt = new javax.swing.JTextField();
        texto2PieFacturaTxt = new javax.swing.JTextField();
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
        setTitle("NOTA DE DEBITO");
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

        totalTxt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalTxt.setForeground(new java.awt.Color(255, 0, 0));
        totalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTxt.setText("TOTAL");

        terminarBtn.setText("Terminar ND");
        terminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar ND");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        eliminarItemBtn.setText("Eliminar Item selecc.");
        eliminarItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarItemBtnActionPerformed(evt);
            }
        });

        descuentoGlobalLbl.setText("Descuento:");

        descuentoGlobalTxt.setText("DESCUENTO");

        texto1PieFacturaTxt.setText("TEXTO 1 PIE FACTURA");

        texto2PieFacturaTxt.setText("TEXTO 2 PIE FACTURA");

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
                        .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(524, 524, 524)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(texto1PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(terminarBtn))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(leerCantidadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(leerPrecioBtn))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(grabarPrecioBtn)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(imprimeChk))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(grabarCantidadBtn)
                                            .addGap(377, 377, 377)
                                            .addComponent(jLabel18)))
                                    .addGap(43, 43, 43)
                                    .addComponent(cancelarBtn))))
                        .addContainerGap(13, Short.MAX_VALUE))
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(eliminarItemBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(texto2PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                    .addComponent(eliminarItemBtn)
                    .addComponent(jLabel8)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leerCantidadBtn)
                    .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarCantidadBtn)
                    .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leerPrecioBtn)
                    .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarPrecioBtn)
                    .addComponent(imprimeChk)
                    .addComponent(cancelarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(terminarBtn)
                    .addComponent(texto1PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texto2PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la ND?", "Atencion", JOptionPane.YES_NO_OPTION);
        // 0 = si; 1 = no
        if (escape == 0) {
            desbloquearCliente();
            VerNcByClienteFrame ppf = new VerNcByClienteFrame();
            ppf.setVisible(true);
            this.dispose();
        } else {
            prepararCampos();
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void terminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarBtnActionPerformed
        double xSaldo = clienteFactura.getSaldo();
        double ySaldo = rint(xSaldo * 100);
        if (ySaldo != 0) {
            JOptionPane.showMessageDialog(this, "Cliente: "
                    + clienteFactura.getRazonSocial()
                    + "\n Saldo: " + df.format(xSaldo));
        }
        int escape = JOptionPane.showConfirmDialog(null, "Quiere ingresar un texto antes de Imprimir?",
                "Texto en el pie de ND",
                JOptionPane.YES_NO_OPTION);
        if (escape == 0) {
            texto1PieFacturaTxt.setEnabled(true);
            texto2PieFacturaTxt.setEnabled(true);
            texto1PieFacturaTxt.requestFocus();
        } else {
            terminarBtn.setEnabled(false);
            terminarFactura();
        }
    }//GEN-LAST:event_terminarBtnActionPerformed

    private void codigoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTxtKeyPressed

    }//GEN-LAST:event_codigoTxtKeyPressed

    private boolean isNumeric(KeyEvent evt) {
        String cod = String.valueOf(evt.getKeyChar());
        try {
            Integer.parseInt(cod);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private void eliminarItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarItemBtnActionPerformed
        int selectRow = tablaFactura.getSelectedRow();
        if (selectRow != -1) {
            int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de Eliminar Item?", "Atencion", JOptionPane.YES_NO_OPTION);
            // 0 = si; 1 = no
            if (escape == 0) {
                tabla.removeRow(selectRow);
                renglonFactura.remove(selectRow);
                calcularTotales();
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

    private void leerCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerCantidadBtnActionPerformed
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            RenglonFactura rf = renglonFactura.get(lin);
            nuevaCantidadTxt.setText(String.valueOf(rf.getCantidad().intValue()));
            nuevaCantidadTxt.setEnabled(true);
            leerCantidadBtn.setEnabled(false);
            grabarCantidadBtn.setEnabled(true);
            tablaFactura.setEnabled(false);
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
                eliminarItemBtn.setEnabled(true);
                leerPrecioBtn.setEnabled(true);
                nuevoPrecioTxt.setEnabled(true);
                terminarBtn.setEnabled(true);
                calcularTotales();
            }
        }
    }//GEN-LAST:event_grabarCantidadBtnActionPerformed

    private void leerPrecioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerPrecioBtnActionPerformed
        FileReader fr = null;
        try {
            fr = new FileReader("c:/Users/permisos.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        String acceso = "";
        try {
            acceso = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                    tablaFactura.setEnabled(true);
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
            java.util.logging.Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new NotaDebitoWebDeNcFrame(null).setVisible(true);
                //null, null
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cantidadAtadosMassalinTxt;
    private javax.swing.JTextField cantidadAtadosNoblezaTxt;
    private javax.swing.JTextField cantidadItemsTxt;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JTextField cuitTxt;
    private javax.swing.JLabel descuentoGlobalLbl;
    private javax.swing.JTextField descuentoGlobalTxt;
    private javax.swing.JButton eliminarItemBtn;
    private javax.swing.JTextField fechaTxt;
    private javax.swing.JButton grabarCantidadBtn;
    private javax.swing.JButton grabarPrecioBtn;
    private javax.swing.JTextField importeMassalinTxt;
    private javax.swing.JTextField importeNoblezaTxt;
    private javax.swing.JCheckBox imprimeChk;
    private javax.swing.JTextField ivaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leerCantidadBtn;
    private javax.swing.JButton leerPrecioBtn;
    private javax.swing.JTextField nuevaCantidadTxt;
    private javax.swing.JTextField nuevoPrecioTxt;
    private javax.swing.JTextField razonSocialTxt;
    private javax.swing.JTable tablaFactura;
    private javax.swing.JButton terminarBtn;
    private javax.swing.JTextField texto1PieFacturaTxt;
    private javax.swing.JTextField texto2PieFacturaTxt;
    private javax.swing.JTextField totalTxt;
    // End of variables declaration//GEN-END:variables

    private void bloquearCliente() {
        String co = clienteFactura.getCodigo();
        ClienteTraba ct = null;
        try {
            ct = new ClienteTrabaService().getClienteByCodigo(co);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ct.setTraba1(true);
        try {
            new ClienteTrabaService().updateCliente(ct);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al bloquear Cliente");
        }
    }

    private void desbloquearCliente() {
        String co = clienteFactura.getCodigo();
        ClienteTraba ct = null;
        try {
            ct = new ClienteTrabaService().getClienteByCodigo(co);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ct.setTraba1(false);
        try {
            new ClienteTrabaService().updateCliente(ct);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        codigoTxt.setText("");
        fechaTxt.setText("");
        razonSocialTxt.setText("");
        totalTxt.setText("");
        totalTxt.setText("0.00");
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        cantidadItemsTxt.setText("");
        descuentoGlobalTxt.setText("");
        nuevoPrecioTxt.setText("");
        importeMassalinTxt.setText("");
        importeNoblezaTxt.setText("");
        importeMassalinTxt.setEditable(false);
        importeNoblezaTxt.setEditable(false);
        imprimeChk.setSelected(true);
    }

    private void bloquearCampos() {
        grabarCantidadBtn.setEnabled(false);
        cancelarBtn.setEnabled(false);
        terminarBtn.setEnabled(false);
        eliminarItemBtn.setEnabled(false);
        ivaTxt.setEditable(false);
        codigoTxt.setEnabled(true);
        codigoTxt.setEditable(true);
        fechaTxt.setEditable(false);
        razonSocialTxt.setEditable(false);
        totalTxt.setEnabled(false);
        cantidadItemsTxt.setEnabled(false);
        texto1PieFacturaTxt.setEnabled(false);
        texto2PieFacturaTxt.setEnabled(false);
        cantidadAtadosMassalinTxt.setEditable(false);
        cantidadAtadosNoblezaTxt.setEditable(false);
        descuentoGlobalTxt.setEditable(false);
    }

    private void terminarFactura() {
        numCae = "0";
        sucursalFacturaPapel = "0";
        numeroFacturaPapel = "0";
        letraFacturaPapel = "L";
        String letraRef = nc.getLetra();
        String tipo_cpbte_ref = "2";
        if (letraRef.equals("B")) {
            tipo_cpbte_ref = "7";
        }
        String numSucRef = dfs.format(nc.getNumeroSucursal());
        String numNcRef = dfn.format(nc.getNumeroFactura());
        documentoReferencia = letraRef + " " + numSucRef + "-" + numNcRef;
        // presentacion web

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
            String tipo_cbte = "2";
            if (categoriaIva.equals(1)) {
                tipo_cbte = "2"; //Nota Debito A
                letraFacturaPapel = "A";

            } else {
                tipo_cbte = "7"; //Nota Debito B
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
//            System.out.println(clienteFactura.getCuit());
            String cui = clienteFactura.getCuit();
            String cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
//            System.out.println(cuit1);
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
System.out.println(totalFactura);
            System.out.println(totalGravado);
            System.out.println(totalIva);
            System.out.println(totalImpuesto);
            System.out.println(tipo_cpbte_ref);
            System.out.println(pto_vta);
            System.out.println(numNcRef);
//System.exit(0);
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

            Variant cbte_asoc_tipo = new Variant(tipo_cpbte_ref),
                    cbte_asoc_pto_vta = new Variant(pto_vta),
                    cbte_asoc_nro = new Variant(numNcRef);
            Dispatch.call(wsfev1, "AgregarCmpAsoc",
                    cbte_asoc_tipo, cbte_asoc_pto_vta, cbte_asoc_nro);
//OTROS TRIBUTOS 4 INTERNOS 99 OTROS
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
            texto2Cae = txtCadenaRP;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: 1772");
            return;
        }

        // fin presentacion web
        Integer categoriaIva = 0;
        IvaVentas ivaVentas = new IvaVentas();
        categoriaIva = clienteFactura.getCategoriaDeIva();
        saldoCliente = clienteFactura.getSaldo();
        saldoCliente += totalFactura;
        clienteFactura.setSaldo(saldoCliente);
        try {
            new ClienteService().updateCliente(clienteFactura);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1728");
        }
        desbloquearCliente();
        nc.setHd(false);
        try {
            new NotaCreditoService().updateNotaCredito(nc);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1735");
        }
        Long id = (long) 1;
        try {
            config = new ConfiguracionService().getFacturas(id);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1742");
        }
        config.setUltimaFechaSistema(fecha);
        if (categoriaIva.equals(1)) {
            letraFactura = "A";
            // es inscripto
            //sucursalFactura = config.getSucursalA();
//            numeroFactura = config.getNumeroFacturaA();
//            numeroFactura += 0;
            config.setNumeroNotaDebitoA(numeroFactura);
            try {
                config = new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1756");
            }
            ivaVentas.setCodigoTipoDoc(2);
        } else {
            letraFactura = "B";
            // el resto de las categorias
            //sucursalFactura = config.getSucursalB();
//            numeroFactura = config.getNumeroFacturaB();
//            numeroFactura += 0;
            config.setNumeroNotaDebitoB(numeroFactura);
            try {
                config = new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1769");
            }
            ivaVentas.setCodigoTipoDoc(7);
        }
        Vendedor ve = clienteFactura.getVendedor();
        ivaVentas.setCliente(clienteFactura);
        ivaVentas.setVendedor(ve);
        //tipo-cpte-ref;
//        ivaVentas.setCodigoTipoDoc(Integer.valueOf(tipo_cpbte_ref));
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
        ivaVentas.setTotal(totalFactura);
        ivaVentas.setLetra(letraFactura);
        ivaVentas.setNumeroSucursal(Integer.valueOf(sucursalFacturaPapel));
        ivaVentas.setNumeroFactura(Integer.valueOf(numeroFacturaPapel));
//        ivaVentas.setHd(false);
        ivaVentas.setDocAsociado(nc.getId());
        ivaVentas.setHd(true);
        for (RenglonFactura reFa : renglonFactura) {
            reFa.setIvaVentas(ivaVentas);
            Integer cod = reFa.getProducto().getCodigo();
            try {
                producto = new ProductoService().getProductoByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
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
            producto.setStock(stock);
            try {
                new ProductoService().updateProducto(producto);
            } catch (Exception ex) {
                Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1894");
            }
        }
        try {
            new FacturaService().saveFactura(ivaVentas, renglonFactura);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1824");
        }
        CtaCteCliente ccc = new CtaCteCliente();
        ccc.setCliente(clienteFactura);
        ccc.setFactura(ivaVentas);
        ccc.setFecha(fecha);
        ccc.setDebe(totalFactura);
        ccc.setHaber(0.0);
        ccc.setTipo("ND");
        ccc.setSaldo(saldoCliente);
        try {
            new CtaCteClienteService().saveCtaCteCliente(ccc);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1838");
        }
        
        int reproceso = 0;
        try {
            new FacturaService().saveFacturaCompleta(clienteFactura, config, ccc, ivaVentas, renglonFactura);
            reproceso = 1;
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Errora actualizando datos Factura - REPROCESO");
            reproceso = 0;
        }
        if (reproceso == 0) {
            try {
                new FacturaService().saveFacturaCompleta(clienteFactura, config, ccc, ivaVentas, renglonFactura);
                JOptionPane.showMessageDialog(this, "Factura Registrada correctamente");
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error actualizando datos Factura - el Documento ingreso en AFIP");
            }
        }
        desbloquearCliente();
        
        generarNotaDebitoPdf(ivaVentas, renglonFactura);
        
        
        
        //generarFactura();
        volver();
    }

    private void generarNotaDebitoPdf(IvaVentas iv, List<RenglonFactura> rf) {
        fecha_qr = sdf_qr.format(iv.getFecha());
        String cui = iv.getCliente().getCuit();
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
        numeroDoc_qr = pri + med + fin;
        puntoVenta_qr = iv.getNumeroSucursal().toString();
        tipoComprobante_qr = iv.getCodigoTipoDoc().toString();
        numeroComprobante_qr = iv.getNumeroFactura().toString();
        String nc = df_matriz.format(iv.getNumeroFactura());
        importe_qr = df.format(iv.getTotal());
        tipoDoc_qr = iv.getCliente().getTipo();
        nroCae_qr = iv.getCae().toString();
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
            generarQR(data, nc);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        String code = iv.getCliente().getCodigo();
        //String numeroFactura = iv.getNumeroFactura().toString();
        Cliente cli = null;
        try {
            cli = new ClienteService().getClienteByCodigo(code);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
                File pdf = new PDFBuilder2().armarNdA(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            } else {
                File pdf = new PDFBuilder2().armarNdB(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            }
            JOptionPane.showMessageDialog(this, "PDF GENERADO CORRECTAMENTE");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err1");
            JOptionPane.showMessageDialog(this, "ERROR FILE 3554");
//            JOptionPane.showMessageDialog(null, System.getProperty("user.dir"));
//            JOptionPane.showMessageDialog(this, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err2");
            JOptionPane.showMessageDialog(this, "ERROR DOCUMENT 3557");
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err3");
            JOptionPane.showMessageDialog(this, "ERROR EXCEPTION 3558");
        }
    }
    
    private void generarQR(String data, String numeroFactura) throws Exception {
        String cadenaCodificada = Base64.getEncoder().encodeToString(data.getBytes());
        BitMatrix matriz;
        Writer writer = new QRCodeWriter();
        try {
            matriz = writer.encode(url_qr + cadenaCodificada, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, "ERROR GENERANDO QR");
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
        //String nf_qr = numeroFactura;
        qrCode = new FileOutputStream(ruta + numeroFactura + extension);
        ImageIO.write(imagen, formato, qrCode);
        qrCode.close();
    }
    
    private void generarFactura() {
        renglones = new String[maxNro];
        textoFacturaPapel = "NOTA DE DEBITO";
        fechaFacturaPapel = sdf.format(fecha);
        clienteFacturaPapel = razonSocialTxt.getText();
        codigoClienteFacturaPapel = clienteFactura.getCodigo();
        direccionFacturaPapel = clienteFactura.getDomicilio().getCalle() + " " + clienteFactura.getDomicilio().getNumero() + " - " + clienteFactura.getDomicilio().getLocalidad();
        cuitFacturaPapel = clienteFactura.getCuit();
        String condVta = "";
        Date fechaVto = fecha;
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        if (clienteFactura.getFormaDePago().equals(1)) {
            condVta = "CONTADO               ";
        }
        if (clienteFactura.getFormaDePago().equals(2)) {
            condVta = "7 DIAS F.F            ";
            cal.add(Calendar.DATE, 7);
            fechaVto = cal.getTime();
        }
        if (clienteFactura.getFormaDePago().equals(3)) {
            condVta = "14 DIAS F.F.          ";
            cal.add(Calendar.DATE, 14);
            fechaVto = cal.getTime();
        }
        if (clienteFactura.getFormaDePago().equals(4)) {
            condVta = "OTRO                  ";
            fechaVto = null;
        }
        condicionVentaFacturaPapel = condVta;
        //vencimientoFacturaPapel = sdf.format(fechaVto);
        String catego = "";
        modeloFcPapel = "Cod.Nro.";
        if (clienteFactura.getCategoriaDeIva().equals(1)) {
            catego = "Responsable Inscripto       ";
            modeloFcPapel += "2";
        }
        if (clienteFactura.getCategoriaDeIva().equals(2)) {
            catego = "Monotributo                 ";
            modeloFcPapel += "7";
        }
        if (clienteFactura.getCategoriaDeIva().equals(3)) {
            catego = "Exento                      ";
            modeloFcPapel += "7";
        }
        if (clienteFactura.getCategoriaDeIva().equals(4)) {
            catego = "Consumidor Final            ";
            modeloFcPapel += "7";
        }
        inscripcionClienteFacturaPapel = catego;
        if (categoriaIva != 1) {
            //                                    1         2         3         4         5         6         7         8         9        10
            //                           1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            nombresColumnaFacturaPapel = "  IT   CANT                   DETALLE                  P.UNIT.    DESC.   IMPORTE       IMP.     TOTAL     SUG";
        } else {
            nombresColumnaFacturaPapel = "  IT   CANT                   DETALLE                 P.UNIT.    DESC.   GRAVADO      IVA       IMP.     TOTAL     SUG";
        }
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
                if (categoriaIva != 1) {
//                  aqui detalle de importes no inscripto en IVA           *****
// Precio Unitario
                    str0 = tablaFactura.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
                    str0 = tablaFactura.getValueAt(r, 7).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
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
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Impuesto
                    str0 = tablaFactura.getValueAt(r, 5).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "        ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaFactura.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaFactura.getValueAt(r, 9).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
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
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
                    str0 = tablaFactura.getValueAt(r, 7).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Gravado
                    str0 = tablaFactura.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Iva
                    str0 = tablaFactura.getValueAt(r, 6).toString();
                    str0 = str0.replace(",", ".");
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
                    espacio = "        ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaFactura.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaFactura.getValueAt(r, 9).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
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
        String espacio = "           ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        //totalDeudaFacturaPapel = espacio + df.format(doble);
// Total Factura
        str0 = String.valueOf(totalFactura);
        str0 = str0.replace(",", ".");
        doble = Double.valueOf(str0);
        largo = doble.intValue();
        espacio = "         ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        importeTotalFacturaPapel = espacio + df.format(doble);
// Linea Totales
        if (categoriaIva != 1) {
            lineaTitulos = "                                                                   Impuesto           Total Nota de Debito";
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                                                            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesFacturaPapel = espacio + df.format(doble);
        } else {
            lineaTitulos = "   Gravado      Impuesto                                 Iva                          Total Nota de Debito";
            str0 = String.valueOf(totalGravado);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesFacturaPapel = espacio + df.format(doble);
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesFacturaPapel += espacio + df.format(doble);
            str0 = String.valueOf(totalIva);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                 ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesFacturaPapel += espacio + df.format(doble);
        }
// Total a Pagar
        Double totalPagar = saldoCliente;
        str0 = String.valueOf(totalPagar);
        str0 = str0.replace(",", ".");
        doble = Double.valueOf(str0);
        largo = doble.intValue();
        espacio = "           ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        //totalPagarFacturaPapel = espacio + df.format(doble);
// Cantidades atados
        cantidadesFacturaPapel = "                  CANT.ATADOS NOBLEZA: " + String.valueOf(cantidadAtadosNobleza);
        cantidadesFacturaPapel += "                 CANT ATADOS MASSALIN: " + String.valueOf(cantidadAtadosMassalin);
        texto1FacturaPapel = texto1PieFacturaTxt.getText();
        texto2FacturaPapel = texto2PieFacturaTxt.getText();
        texto3FacturaPapel = "-";
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
        Double importeTotalMassalin = 0.0;
        Double importeTotalNobleza = 0.0;
        for (RenglonFactura renFa : renglonFactura) {
            totalFactura += renFa.getTotal();
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
            nro += 1;
            renFa.setItemNro(nro);
        }
        totalGravadoCigarrillos = rint(((importeTotalMassalin
                + importeTotalNobleza
                - totalImpuesto) / (1 + (porcentualIva / 100)))
                * 100) / 100;
        totalGravado = rint(((totalFactura
                - totalImpuesto) / (1 + (porcentualIva / 100)))
                * 100) / 100;
        totalIva = rint(totalGravado
                * (porcentualIva / 100) * 100) / 100;
        cantidadItemsTxt.setText(String.valueOf(nro));
        cantidadAtadosNoblezaTxt.setText(String.valueOf(cantidadAtadosNobleza));
        importeNoblezaTxt.setText(String.valueOf(df.format(importeTotalNobleza)));
        cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
        importeMassalinTxt.setText(String.valueOf(df.format(importeTotalMassalin)));
        cantidadItemsTxt.setText(String.valueOf(nro));
        totalTxt.setText(String.valueOf(df.format(totalFactura)));
    }

    private void cargarCliente() {
        clienteFactura = nc.getCliente();
        codigoClienteFacturaPapel = clienteFactura.getCodigo();
        codigoTxt.setText(codigoClienteFacturaPapel);
        razonSocialTxt.setText(clienteFactura.getRazonSocial());
        cuitTxt.setText(clienteFactura.getCuit());

        if (clienteFactura.getCategoriaDeIva() == 1) {
            ivaTxt.setText("Resp. Inscripto");
        }
        if (clienteFactura.getCategoriaDeIva() == 2) {
            ivaTxt.setText("Monotributo");
        }
        if (clienteFactura.getCategoriaDeIva() == 4) {
            ivaTxt.setText("Consumidor Final");
        }
        categoriaIva = clienteFactura.getCategoriaDeIva();
        fecha = Calendar.getInstance().getTime();
        fechaTxt.setText(sdf.format(fecha));
    }

    private void cargarRenglones() {
        if (nc.getTextoPieFactura1() != null) {
            texto1PieFacturaTxt.setText(nc.getTextoPieFactura1());
        }
        if (nc.getTextoPieFactura2() != null) {
            texto2PieFacturaTxt.setText(nc.getTextoPieFactura2());
        }
        try {
            renglonP = new RenglonNotaCreditoService().getAllRenglonNotaCreditoFromIvaVentas(nc);
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabla = (DefaultTableModel) tablaFactura.getModel();
        for (RenglonNotaCredito rp : renglonP) {
            RenglonFactura rf = new RenglonFactura();
            rf.setCantidad(rp.getCantidad());
            rf.setProducto(rp.getProducto());
            rf.setItemNro(rp.getItemNro());
            rf.setDescripcion(rp.getDescripcion());
            if (rp.getGravado() < 0.00) {
                rf.setGravado(rp.getGravado() * -1);
            } else {
                rf.setGravado(rp.getGravado());
            }
            if (rp.getNoGravado() < 0.00) {
                rf.setNoGravado(rp.getNoGravado() * -1);
            } else {
                rf.setNoGravado(rp.getNoGravado());
            }
            if (rp.getExento() < 0.00) {
                rf.setExento(rp.getExento() * -1);
            } else {
                rf.setExento(rp.getExento());
            }
            if (rp.getImpuesto() < 0.00) {
                rf.setImpuesto(rp.getImpuesto() * -1);
            } else {
                rf.setImpuesto(rp.getImpuesto());
            }
            if (rp.getDescuento() < 0.00) {
                rf.setDescuento(rp.getDescuento() * -1);
            } else {
                rf.setDescuento(rp.getDescuento());
            }
            if (rp.getIva() < 0.00) {
                rf.setIva(rp.getIva() * -1);
            } else {
                rf.setIva(rp.getIva());
            }
            if (rp.getTotal() < 0.00) {
                rf.setTotal(rp.getTotal() * -1);
            } else {
                rf.setTotal(rp.getTotal());
            }
            rf.setSugerido(rp.getSugerido());
            renglonFactura.add(rf);
            Object linea[] = new Object[10];
            linea[0] = rp.getProducto().getCodigo();
            linea[1] = rp.getCantidad().intValue();
            linea[2] = rp.getDescripcion();
            Double l0 = rp.getGravado() + rp.getImpuesto() + rp.getIva();
            if (l0 < 0.00) {
                linea[3] = df.format((l0 * -1) / rp.getCantidad());
            } else {
                linea[3] = df.format((l0) / rp.getCantidad());
            }
            if (rp.getGravado() < 0.00) {
                linea[4] = df.format(rp.getGravado() * -1);
            } else {
                linea[4] = df.format(rp.getGravado());
            }
            if (rp.getImpuesto() < 0.00) {
                linea[5] = df.format(rp.getImpuesto() * -1);
            } else {
                linea[5] = df.format(rp.getImpuesto());
            }
            if (rp.getIva() < 0.00) {
                linea[6] = df.format(rp.getIva() * -1);
            } else {
                linea[6] = df.format(rp.getIva());
            }
            linea[7] = 0.00;
            if (rp.getTotal() < 0.00) {
                linea[8] = df.format(rp.getTotal() * -1);
            } else {
                linea[8] = df.format(rp.getTotal());
            }
            linea[9] = df.format(rp.getSugerido());
            tabla.addRow(linea);
        }
        tablaFactura.setModel(tabla);
        cancelarBtn.setEnabled(true);
        codigoTxt.setEditable(false);
        totalTxt.setEnabled(true);
        totalTxt.setEditable(false);
        calcularTotales();
        cancelarBtn.setEnabled(true);
        terminarBtn.setEnabled(true);
        eliminarItemBtn.setEnabled(true);
        grabarPrecioBtn.setEnabled(false);
    }

    private void cargarDatosIniciales() {
        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(NotaDebitoWebDeNcFrame.class.getName()).log(Level.SEVERE, null, ex);
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

    private void prepararCampos() {
        eliminarItemBtn.setEnabled(true);
        leerCantidadBtn.setEnabled(true);
        leerPrecioBtn.setEnabled(true);
        nuevaCantidadTxt.setText("");
        nuevoPrecioTxt.setText("");
        nuevaCantidadTxt.setEnabled(true);
        nuevoPrecioTxt.setEnabled(true);
        grabarCantidadBtn.setEnabled(false);
        grabarPrecioBtn.setEnabled(false);
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
        MainFrame ff = new MainFrame();
        ff.setVisible(true);
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
            g2.drawString(espacio + letraFacturaPapel, 30, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 6));
            g2.drawString(modeloFcPapel, 271, 53);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 8));
            espacio = "                                                         ";
            g2.setFont(new Font("Monospaced", Font.BOLD, 9));
            g2.drawString(espacio + textoFacturaPapel, 30, row);
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
                    + sucursalFacturaPapel + "-"
                    + numeroFacturaPapel, 30, row);
            row += 15;
            g2.drawString(espacio + fechaFacturaPapel, 30, row);
            espacio = "            ";
            row += 50;
            g2.drawString(espacio + clienteFacturaPapel, 30, row);
            g2.drawString(codigoClienteFacturaPapel, 480, row);
            row += 15;
            espacio = "            ";
            g2.drawString(espacio + direccionFacturaPapel, 30, row);
            row += 15;
            g2.drawString(cuitFacturaPapel, 100, row);
            g2.drawString(inscripcionClienteFacturaPapel, 360, row);
            row += 25;
            g2.drawString(condicionVentaFacturaPapel, 150, row);
            //g2.drawString(vencimientoFacturaPapel, 400, row);
            g2.drawString("Docum.Ref.Nota Credito: " + documentoReferencia, 350, row);
            row += 25;
            g2.drawString(nombresColumnaFacturaPapel, 30, row);
            row += 15;
            for (int x = 0; x < maxNro; x++) {
                if (renglones[x] != null) {
                    g2.drawString(renglones[x], 40, row);
                }
                row += 10;
            }
            row += 20;
            g2.drawString(lineaTitulos, 50, row);
            row += 20;
            g2.drawString(lineaTotalesFacturaPapel, 30, row);
            g2.setFont(new Font("Monospaced", Font.BOLD, 11));
            g2.drawString(importeTotalFacturaPapel, 490, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            row += 21;
//            g2.drawString("SALDO ANTERIOR: " + totalDeudaFacturaPapel, 403, row);
//            row += 10;
//            g2.drawString("SALDO TOTAL:    " + totalPagarFacturaPapel, 403, row);
            row += 10;
            espacio = "     ";
            g2.setFont(new Font("Monospaced", Font.BOLD, 9));
            g2.drawString(espacio + texto1FacturaPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto2FacturaPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto3FacturaPapel, 30, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            row += 15;
            g2.drawString(cantidadesFacturaPapel, 30, row);
            row += 20;
            g2.drawString(" CAE " + texto1Cae + "  Venc. CAE " + vencCae, 30, row);
            g2.setFont(new Font("PF Interleavev 2 of 5 Text", Font.PLAIN, 18));
            g2.drawString("           " + texto2Cae, 160, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            return PAGE_EXISTS;
        }
    }
}
