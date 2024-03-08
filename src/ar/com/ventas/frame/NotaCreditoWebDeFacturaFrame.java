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
import ar.com.ventas.services.VendedorService;
import ar.com.ventas.util.DesktopApi;
import ar.com.ventas.util.PDFBuilder1;
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
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class NotaCreditoWebDeFacturaFrame extends javax.swing.JFrame {

    private Double totalGravadoCigarrillos = 0.00;
    private Double totalNoGravado = 0.0;
    private Float descuento = 0F;
    private String td_f = "";
    private String pv = "";
    private String nc = "";
    private final List<RenglonNotaCredito> renglonNotaCredito = new ArrayList<RenglonNotaCredito>();
    private static final int qrTamAncho = 150;
    private static final int qrTamAlto = 150;
    private static final String formato = "png";
    private static final String ruta = "c:/qr/codigoQR";
    private static final String extension = ".png";
    private static SimpleDateFormat sdf_qr = new SimpleDateFormat("yyyy-MM-dd");
    private String url_qr = "https://www.afip.gob.ar/fe/qr/?p=";
    private String ver_qr = "1";
    private String fecha_qr;
    private final String cuit_qr = "20124127581";
    private String puntoVenta_qr = "5";
    private String tipoComprobante_qr;
    private String numeroComprobante_qr;
    private String importe_qr;
    private String moneda_qr = "PES";
    private String cotiz_qr = "1";
    private String tipoDoc_qr;
    private String tipoDoc;
    private String numeroDoc_qr;
    private String tipoCodigoAutoriz_qr = "E";
    private String nroCae_qr;
    private final DecimalFormat df1 = new DecimalFormat("#0");
    private final DecimalFormat df_desc = new DecimalFormat("#0.0");
    private String modeloFcPapel;
    private String[] renglones = null;
    private Date fecha;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Cliente clienteNotaCredito = null;
    private Producto producto = null;
    private Double totalNotaCredito = 0.00;
    private Double totalImpuesto = 0.00;
    private Double totalIva = 0.00;
    private Double totalGravado = 0.00;
    private Double gravado = 0.00;
    private Double iva = 0.00;
    private Float porcentualIva;
    private Double impuesto = null;
    private Double totalLinea = 0.00;
    private Boolean tieneDto = false;
    private final Cliente clienteSeleccionado;
    private Integer categoriaIva = 4;
    private final DecimalFormat df = new DecimalFormat("#0.00");
    private final DecimalFormat dfs = new DecimalFormat("0000");
    private final DecimalFormat dfn = new DecimalFormat("00000000");
    private Double saldoCliente = 0.00;
    private Integer numeroNotaCredito;
    private Configuracion config = null;
    private Double precioFinal = 0.0;
    private Integer nro = 0;
    private final Integer maxNro = 41;
    private Integer cantidadAtadosMassalin;
    private Integer cantidadAtadosNobleza;
    private IvaVentas factura;
    private List<RenglonFactura> renglonF = null;
    private String vencCae;
    private String numeroNotaCreditoPapel;
    private String letraNotaCreditoPapel;
    private String sucursalNotaCreditoPapel;
    private String numCae;
    private String texto1Cae = "";
    private float descuentoCliente = 0F;
    private Double totalDescuento = 0.0;
    private final int tst = 0; // 1 esta en test
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form NotaCreditoFrame
     *
     * @param cli
     * @param fac
     */
    public NotaCreditoWebDeFacturaFrame(Cliente cli, IvaVentas fac) {
        getContentPane().setBackground(new java.awt.Color(135, 206, 235));
        initComponents();
        this.setLocationRelativeTo(null);
        this.clienteSeleccionado = cli;
        this.factura = fac;
//        this.order_name = order_name;
//        this.order_num = order_num;
        cod_prodTxt.setText("");
        bloquearCampos();

        if (clienteSeleccionado == null && fac == null) {
            volver();
        } else {
            cargarDatos();
            bloquearCliente();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codigoTxt = new javax.swing.JTextField();
        razonSocialTxt = new javax.swing.JTextField();
        fechaTxt = new javax.swing.JTextField();
        ivaTxt = new javax.swing.JTextField();
        saldoTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        totalTxt = new javax.swing.JTextField();
        terminarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        texto1PieNcTxt = new javax.swing.JTextField();
        texto2PieNcTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cantidadAtadosMassalinTxt = new javax.swing.JTextField();
        cantidadAtadosNoblezaTxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cantidadItemsTxt = new javax.swing.JTextField();
        borrarNoSeleccionadoBtn = new javax.swing.JButton();
        leerCantidadBtn = new javax.swing.JButton();
        nuevaCantidadTxt = new javax.swing.JTextField();
        grabarCantidadBtn = new javax.swing.JButton();
        leerPrecioBtn = new javax.swing.JButton();
        grabarPrecioBtn = new javax.swing.JButton();
        nuevoPrecioTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        importeMassalinTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        importeNoblezaTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cod_prodTxt = new javax.swing.JTextField();
        agregarBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        porcentajeDtoTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        descuentoGlobalTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        facturaTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("A Y M - NOTA DE CREDITO DESDE FACTURA");
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
                false, false, false, false, false, false, false, false, true, false
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

        jLabel3.setText("Saldo:");

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

        saldoTxt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saldoTxt.setForeground(new java.awt.Color(0, 0, 153));
        saldoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saldoTxt.setText("SALDO");

        jLabel8.setText("TOTAL NOTA CREDITO:");

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

        texto1PieNcTxt.setText("TEXTO 1 PIE FACTURA");

        texto2PieNcTxt.setText("TEXTO 2 PIE FACTURA");

        jLabel15.setText("Massalin:");

        jLabel17.setText("Nobleza:");

        cantidadAtadosMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadAtadosMassalinTxt.setText("Cantidad Massalin");

        cantidadAtadosNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadAtadosNoblezaTxt.setText("Cantidad Nobleza");

        jLabel18.setText("Cant. Items:");

        cantidadItemsTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadItemsTxt.setText("Cantidad Items");

        borrarNoSeleccionadoBtn.setText("Borrar no Seleccionado");
        borrarNoSeleccionadoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarNoSeleccionadoBtnActionPerformed(evt);
            }
        });

        leerCantidadBtn.setText("Leer Cantidad");
        leerCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerCantidadBtnActionPerformed(evt);
            }
        });

        nuevaCantidadTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevaCantidadTxt.setText("NUEVA CANTIDAD");
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
        nuevoPrecioTxt.setText("NUEVO PRECIO");
        nuevoPrecioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevoPrecioTxtKeyPressed(evt);
            }
        });

        jLabel5.setText("Importe:");

        importeMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeMassalinTxt.setText("IMP.MASS");

        jLabel6.setText("Importe:");

        importeNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeNoblezaTxt.setText("IMP.NOBL");

        jLabel7.setText("Código:");

        cod_prodTxt.setText("COD");
        cod_prodTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cod_prodTxtKeyPressed(evt);
            }
        });

        agregarBtn.setText("Agregar");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });

        jLabel9.setText("Descuento:");

        porcentajeDtoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        porcentajeDtoTxt.setText("DTO");

        jLabel10.setText("Descuento:");

        descuentoGlobalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentoGlobalTxt.setText("DETO_GLOB");

        jLabel11.setText("Factura:");

        facturaTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facturaTxt.setText("NRO FC");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saldoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(132, 132, 132)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(facturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(texto1PieNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(texto2PieNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(117, 117, 117)
                                        .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(borrarNoSeleccionadoBtn)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(leerCantidadBtn)
                                                    .addComponent(leerPrecioBtn))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(grabarCantidadBtn))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(grabarPrecioBtn))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cod_prodTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(agregarBtn)))
                                        .addGap(193, 193, 193)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel8))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(razonSocialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(porcentajeDtoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(306, 306, 306)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelarBtn)
                    .addComponent(terminarBtn))
                .addGap(81, 81, 81))
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
                    .addComponent(jLabel9)
                    .addComponent(porcentajeDtoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saldoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(facturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cod_prodTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarBtn)
                    .addComponent(jLabel10)
                    .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrarNoSeleccionadoBtn)
                    .addComponent(leerCantidadBtn)
                    .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarCantidadBtn)
                    .addComponent(jLabel8)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leerPrecioBtn)
                    .addComponent(grabarPrecioBtn)
                    .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto1PieNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto2PieNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(terminarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la NC?", "Atencion", JOptionPane.YES_NO_OPTION);
        // 0 = si; 1 = no
        if (escape == 0) {
            desbloquearCliente();
            guardarRepositorio();
            volver();
        } else {
            //nombreProductoConsultaTxt.setEnabled(false);
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void volver() {
        NotaCreditoSelectFrame ncpf = new NotaCreditoSelectFrame();
        ncpf.setVisible(true);
        this.dispose();
    }

    private void bloquearCliente() {
        String cod = clienteSeleccionado.getCodigo();
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
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al bloquear Cliente");
        }
    }

    private void desbloquearCliente() {
        String cod = clienteSeleccionado.getCodigo();
        ClienteTraba ct = null;
        CustomerTraba cuTr = null;
        try {
            ct = new ClienteTrabaService().getClienteByCodigo(cod);
            cuTr = new CustomerTrabaService().getClienteByCodigo(cod);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al desbloquear Cliente - DESBLOQUEAR CLIENTE");
            return;
        }
        ct.setTraba1(false);
        cuTr.setTraba2(false);
        try {
            new ClienteTrabaService().updateCliente(ct);
            new CustomerTrabaService().updateCliente(cuTr);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al desbloquear Cliente - DESBLOQUEAR CLIENTE");
            return;
        }
    }

    private void terminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Quiere ingresar un texto antes de Imprimir?",
                "Texto en el pie de Nota de Crédito",
                JOptionPane.YES_NO_OPTION);
        if (escape == 0) {
            texto1PieNcTxt.setEnabled(true);
            texto2PieNcTxt.setEnabled(true);
            texto1PieNcTxt.requestFocus();
        } else {
            terminarBtn.setEnabled(false);
            escape = JOptionPane.showConfirmDialog(null, "Confirma Nota de Crédito?",
                    "FINALIZAR NOTA DE CREDITO",
                    JOptionPane.YES_NO_OPTION);
            if (escape == 0) {
                terminarNotaCredito();
            } else {
                agregarProducto();
                terminarBtn.setEnabled(true);
            }

        }
    }//GEN-LAST:event_terminarBtnActionPerformed

    private void codigoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTxtKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            if (!codigoTxt.getText().isEmpty()) {
//                buscar();
//                cancelarBtn.setEnabled(true);
//            }
//        } else {
//            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                MainFrame ff = new MainFrame();
//                ff.setVisible(true);
//                this.dispose();
//            }
//        }
    }//GEN-LAST:event_codigoTxtKeyPressed

    private void borrarNoSeleccionadoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarNoSeleccionadoBtnActionPerformed
        borrarNoSeleccionado();
    }//GEN-LAST:event_borrarNoSeleccionadoBtnActionPerformed

    private void leerCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerCantidadBtnActionPerformed
        int lin = tablaNotaCredito.getSelectedRow();
        if (lin > -1) {
            RenglonNotaCredito rf = renglonNotaCredito.get(lin);
            nuevaCantidadTxt.setText(String.valueOf(rf.getCantidad().intValue()));
            leerCantidadBtn.setEnabled(false);
            grabarCantidadBtn.setEnabled(true);
        }
    }//GEN-LAST:event_leerCantidadBtnActionPerformed

    private void grabarCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarCantidadBtnActionPerformed
        int lin = tablaNotaCredito.getSelectedRow();
        if (lin > -1) {
            if (!nuevaCantidadTxt.getText().isEmpty()) {
                leerCantidadBtn.setEnabled(true);
                grabarCantidadBtn.setEnabled(false);
                RenglonNotaCredito rf = renglonNotaCredito.get(lin);
                Float cantidadAnterior = rf.getCantidad();
                Float cant = Float.valueOf(nuevaCantidadTxt.getText());
                if (!(cant > 0.0)) {
                    JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                    return;
                }
                if (cant > cantidadAnterior) {
                    JOptionPane.showMessageDialog(this, "Esta colocando una cantidad Mayor");
                    nuevaCantidadTxt.requestFocus();
                    return;
                } else {
                    rf.setCantidad(cant);
                }
                Double precioUnitario = rf.getGravado() / cantidadAnterior;
                Double impuestoUnitario = rf.getImpuesto() / cantidadAnterior;
                renglonNotaCredito.set(lin, rf);
                tablaNotaCredito.setValueAt(cant.intValue(), lin, 1);
                // en unidad
                precioFinal = rint(((precioUnitario * (1 + (porcentualIva / 100))) + impuestoUnitario) * 100) / 100;
                tablaNotaCredito.setValueAt(df.format(-precioFinal), lin, 3);
                nuevaCantidadTxt.setText("");

                // por cantidad
                gravado = rint((precioUnitario * cant) * 100) / 100;
                impuesto = rint((impuestoUnitario * cant) * 100) / 100;
                iva = rint(gravado * porcentualIva) / 100;
                totalLinea = rint((gravado + impuesto + iva) * 100) / 100;
                rf.setGravado(gravado);
                rf.setImpuesto(impuesto);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaNotaCredito.setValueAt(df.format(gravado), lin, 4);
                tablaNotaCredito.setValueAt(df.format(impuesto), lin, 5);
                tablaNotaCredito.setValueAt(df.format(iva), lin, 6);
                tablaNotaCredito.setValueAt(df.format(totalLinea), lin, 8);
                calcularTotales();
            }
        }
    }//GEN-LAST:event_grabarCantidadBtnActionPerformed

    private void nuevaCantidadTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevaCantidadTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int lin = tablaNotaCredito.getSelectedRow();
            if (lin > -1) {
                if (!nuevaCantidadTxt.getText().isEmpty()) {
                    leerCantidadBtn.setEnabled(true);
                    grabarCantidadBtn.setEnabled(false);
                    RenglonNotaCredito rf = renglonNotaCredito.get(lin);
                    Float cantidadAnterior = rf.getCantidad();
                    Float cant = Float.valueOf(nuevaCantidadTxt.getText());
                    if (!(cant > 0.0)) {
                        JOptionPane.showMessageDialog(this, "Debe colocar cantidad mayor que cero");
                        return;
                    }
                    rf.setCantidad(cant);
                    Double precioUnitario = rf.getGravado() / cantidadAnterior;
                    Double impuestoUnitario = rf.getImpuesto() / cantidadAnterior;
                    renglonNotaCredito.set(lin, rf);
                    tablaNotaCredito.setValueAt(cant.intValue(), lin, 1);

                    calcularLinea(cant, precioUnitario, impuestoUnitario.floatValue());

                    tablaNotaCredito.setValueAt(df.format(-precioFinal), lin, 3);
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
                    nuevaCantidadTxt.setEnabled(true);
                    leerCantidadBtn.setEnabled(true);
                    terminarBtn.setEnabled(true);
                    borrarNoSeleccionadoBtn.setEnabled(true);
                    tablaNotaCredito.setEnabled(true);
                    terminarBtn.requestFocus();
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

    private void leerPrecioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerPrecioBtnActionPerformed
        leerPrecio();
    }//GEN-LAST:event_leerPrecioBtnActionPerformed

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
                tablaNotaCredito.setEnabled(true);
                RenglonNotaCredito rf = renglonNotaCredito.get(lin);
                Float cant = rf.getCantidad();
                Double impuestoUnitario = rf.getImpuesto() / cant;
                Double nuevoImporte = Double
                        .valueOf(nuevoPrecioTxt.getText()
                                .replaceAll("\\,", "\\."))
                        / (1 + (porcentualIva / 100));
                nuevoImporte = nuevoImporte * -1;
                calcularLinea(cant, nuevoImporte, impuestoUnitario.floatValue());
                rf.setGravado(gravado);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaNotaCredito.setValueAt(df.format(-precioFinal), lin, 3);
                tablaNotaCredito.setValueAt(df.format(gravado), lin, 4);
                tablaNotaCredito.setValueAt(df.format(impuesto), lin, 5);
                tablaNotaCredito.setValueAt(df.format(iva), lin, 6);
                tablaNotaCredito.setValueAt(df.format(totalLinea), lin, 8);
                nuevoPrecioTxt.setText("");
                nuevoPrecioTxt.setEnabled(true);
                nuevaCantidadTxt.setEnabled(true);
                borrarNoSeleccionadoBtn.setEnabled(true);
                terminarBtn.setEnabled(true);
                terminarBtn.requestFocus();
                calcularTotales();
            }
        }
    }//GEN-LAST:event_grabarPrecioBtnActionPerformed

    private void nuevoPrecioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevoPrecioTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
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
                    tablaNotaCredito.setEnabled(true);
                    RenglonNotaCredito rf = renglonNotaCredito.get(lin);
                    Float cant = rf.getCantidad();
                    Double impuestoUnitario = rf.getImpuesto() / cant;
                    Double nuevoImporte = Double.valueOf(nuevoPrecioTxt.getText()
                            .replaceAll("\\,", "\\."))
                            / (1 + (porcentualIva / 100));
                    nuevoImporte = nuevoImporte * -1;
                    calcularLinea(cant, nuevoImporte, impuestoUnitario.floatValue());
                    rf.setGravado(gravado);
                    rf.setIva(iva);
                    rf.setTotal(totalLinea);
                    tablaNotaCredito.setValueAt(df.format(-precioFinal), lin, 3);
                    tablaNotaCredito.setValueAt(df.format(gravado), lin, 4);
                    tablaNotaCredito.setValueAt(df.format(impuesto), lin, 5);
                    tablaNotaCredito.setValueAt(df.format(iva), lin, 6);
                    tablaNotaCredito.setValueAt(df.format(totalLinea), lin, 8);
                    nuevoPrecioTxt.setText("");
                    nuevoPrecioTxt.setEnabled(true);
                    nuevaCantidadTxt.setEnabled(true);
                    borrarNoSeleccionadoBtn.setEnabled(true);
                    terminarBtn.setEnabled(true);
                    terminarBtn.requestFocus();
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

    private void cod_prodTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cod_prodTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            agregar();
        }

    }//GEN-LAST:event_cod_prodTxtKeyPressed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        agregar();
    }//GEN-LAST:event_agregarBtnActionPerformed

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
            java.util.logging.Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new NotaCreditoWebDeFacturaFrame(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton borrarNoSeleccionadoBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cantidadAtadosMassalinTxt;
    private javax.swing.JTextField cantidadAtadosNoblezaTxt;
    private javax.swing.JTextField cantidadItemsTxt;
    private javax.swing.JTextField cod_prodTxt;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JTextField descuentoGlobalTxt;
    private javax.swing.JTextField facturaTxt;
    private javax.swing.JTextField fechaTxt;
    private javax.swing.JButton grabarCantidadBtn;
    private javax.swing.JButton grabarPrecioBtn;
    private javax.swing.JTextField importeMassalinTxt;
    private javax.swing.JTextField importeNoblezaTxt;
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
    private javax.swing.JTextField nuevaCantidadTxt;
    private javax.swing.JTextField nuevoPrecioTxt;
    private javax.swing.JTextField porcentajeDtoTxt;
    private javax.swing.JTextField razonSocialTxt;
    private javax.swing.JTextField saldoTxt;
    private javax.swing.JTable tablaNotaCredito;
    private javax.swing.JButton terminarBtn;
    private javax.swing.JTextField texto1PieNcTxt;
    private javax.swing.JTextField texto2PieNcTxt;
    private javax.swing.JTextField totalTxt;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        texto1PieNcTxt.setText("");
        texto2PieNcTxt.setText("");
        cantidadAtadosMassalinTxt.setText("");
        cantidadAtadosNoblezaTxt.setText("");
        ivaTxt.setText("");
        codigoTxt.setText("");
        fechaTxt.setText("");
        razonSocialTxt.setText("");
        totalTxt.setText("");
        saldoTxt.setText("0.00");
        totalTxt.setText("0.00");
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        cantidadItemsTxt.setText("");
        nuevaCantidadTxt.setText("");
        nuevoPrecioTxt.setText("");
    }

    private boolean isNumeric(KeyEvent evt) {
        String cod = String.valueOf(evt.getKeyChar());
        try {
            Integer.parseInt(cod);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private void bloquearCampos() {
        cancelarBtn.setEnabled(false);
        terminarBtn.setEnabled(false);
        ivaTxt.setEditable(false);
        codigoTxt.setEnabled(true);
        codigoTxt.setEditable(true);
        fechaTxt.setEditable(true);
        razonSocialTxt.setEditable(false);
        totalTxt.setEnabled(false);
        saldoTxt.setEditable(false);
        cantidadItemsTxt.setEnabled(false);
        texto1PieNcTxt.setEnabled(false);
        texto2PieNcTxt.setEnabled(false);
        cantidadAtadosMassalinTxt.setEditable(false);
        cantidadAtadosNoblezaTxt.setEditable(false);
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

    private void terminarNotaCredito() {
        numCae = "0";
        Date ff = new Date();
        try {
            ff = sdf.parse(fechaTxt.getText());
        } catch (ParseException ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha = ff;
        sucursalNotaCreditoPapel = "5";
        try {
            config = new ConfiguracionService().getFacturas(1L);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        vencCae = sdf.format(fecha);
        if (factura.getLetra().equals("A")) {// || categoriaIva.equals(2)) {
            numeroNotaCredito = config.getNumeroNotaCreditoA();
            numeroNotaCredito += 1;
            letraNotaCreditoPapel = "A";
        } else {
            numeroNotaCredito = config.getNumeroNotaCreditoB();
            numeroNotaCredito += 1;
            letraNotaCreditoPapel = "B";
        }
        numeroNotaCreditoPapel = numeroNotaCredito.toString();
        // presentacion web
        System.out.println(letraNotaCreditoPapel);
        System.out.println(-totalNotaCredito);
        System.out.println(-totalGravado);
        System.out.println(-totalIva);
        System.out.println(totalImpuesto);
        System.out.println(factura.getCodigoTipoDoc());
        System.out.println(factura.getNumeroFactura());
//        JOptionPane.showMessageDialog(this, "VER");
//        System.exit(0);
        if (tst == 0) {
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
                String tipo_cbte;
                if (factura.getLetra().equals("A")) { // || categoriaIva.equals(2)) {
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
                System.out.println("Ultimo comprobante: " + ult.toString());
                excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
                System.out.println("Excepcion: " + excepcion);

                // CAE 
                String fechaWs = new SimpleDateFormat("yyyyMMdd").format(ff);
                String concepto = "1";// producto 
//            System.out.println(clienteNotaCredito.getCuit());
                String cui = clienteNotaCredito.getCuit();
                String cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
                System.out.println(cuit1);
                String tipoD = tipoDoc;
                String tipo_doc = tipoD, nro_doc = cuit1; //tipo y numero
                int cbte_nro = Integer.parseInt(ult.toString()) + 1,
                        cbt_desde = cbte_nro,
                        cbt_hasta = cbte_nro;
                numeroNotaCredito = cbte_nro;
                numeroNotaCreditoPapel = String.valueOf(cbte_nro);
                int largo = ("00000000" + numeroNotaCreditoPapel).length();
                numeroNotaCreditoPapel = ("00000000" + numeroNotaCreditoPapel).substring(largo - 8, largo);
                System.out.println(df.format(-totalNotaCredito).toString().replaceAll("\\,", "\\."));
                String imp_total = df.format(-totalNotaCredito).toString().replaceAll("\\,", "\\.");//"124.00";
                String imp_tot_conc = "0.00";
                String imp_neto = df.format(-totalGravado).toString().replaceAll("\\,", "\\.");
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

                td_f = factura.getCodigoTipoDoc().toString();
                pv = "5";
                nc = factura.getNumeroFactura().toString();
//                 Agrego los comprobantes asociados: 
//                            if (false) { // solo nc/nd 

                Variant cbte_asoc_tipo = new Variant(td_f),
                        cbte_asoc_pto_vta = new Variant(pv),
                        cbte_asoc_nro = new Variant(nc);
                Dispatch.call(wsfev1, "AgregarCmpAsoc",
                        cbte_asoc_tipo, cbte_asoc_pto_vta, cbte_asoc_nro);
//                }
//                 Agrego impuestos varios 
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
                System.out.println("Excepcion: " + excepcion);

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
                numCae = cae.toString();
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
//            System.out.println(cadena);
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
                // texto2Cae = txtCadenaRP;
            } catch (Exception e) {
                e.printStackTrace();
                //guardarRepositorio();
                terminarBtn.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Error: 981");
                return;
            }
            // fin nc web
        }
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
        saldoCliente = clienteNotaCredito.getSaldo();
        saldoCliente += totalNotaCredito;
        clienteNotaCredito.setSaldo(saldoCliente);
        try {
            new ClienteService().updateCliente(clienteNotaCredito);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 986");
            return;
        }

        Long id = (long) 1;
        try {
            config = new ConfiguracionService().getFacturas(id);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1004");
            return;
        }
        config.setUltimaFechaSistema(fecha);
        if (factura.getLetra().equals("A")) {// || categoriaIva.equals(2)) {
//            letraNotaCredito = "A";
            // es inscriptp
//            sucursalNotaCredito = config.getSucursalA();
//            numeroNotaCredito = config.getNumeroFacturaA();
//            numeroNotaCredito += 0;
            config.setNumeroNotaCreditoA(numeroNotaCredito);
            try {
                config = new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1018");
                return;
            }
            ivaVentas.setCodigoTipoDoc(3);
        } else {
//            letraNotaCredito = "B";
            // el resto de las categorias
//            sucursalNotaCredito = config.getSucursalB();
//            numeroNotaCredito = config.getNumeroFacturaB();
//            numeroNotaCredito += 0;
            config.setNumeroNotaCreditoB(numeroNotaCredito);
            try {
                config = new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1032");
                return;
            }
            ivaVentas.setCodigoTipoDoc(8);
        }
        ivaVentas.setCliente(clienteNotaCredito);
        ivaVentas.setVendedor(v);
        ivaVentas.setDescuentoGlobal(totalDescuento);
        ivaVentas.setPorcentualDescuentoGlobal(factura.getPorcentualDescuentoGlobal());
        ivaVentas.setExento(0.0);
        ivaVentas.setFecha(fecha);
        ivaVentas.setDocAsociado(factura.getId());
//        System.out.println(vencCae);
//        System.exit(0);
        if (vencCae != null) {
            try {
                ivaVentas.setFechaCae(sdf.parse(vencCae));
            } catch (ParseException ex) {
                ivaVentas.setFechaCae(fecha);
            }
        }
        ivaVentas.setCae(Long.valueOf(numCae));
        ivaVentas.setGravado(totalGravado);
        ivaVentas.setImpuesto(totalImpuesto);
        ivaVentas.setIva(totalIva);
        ivaVentas.setNoGravado(0.0);
        ivaVentas.setTotal(totalNotaCredito);
        ivaVentas.setLetra(letraNotaCreditoPapel);
        ivaVentas.setNumeroSucursal(Integer.valueOf(sucursalNotaCreditoPapel));
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
                Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1411");
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
                Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 1427");
                return;
            }
        }
        try {
            new NotaCreditoService().saveFactura(ivaVentas, renglonNotaCredito);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1085");
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
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 1100");
            return;
        }
        desbloquearCliente();
        generarNotaCredito(ivaVentas);
        volver();
    }

    private void generarNotaCredito(IvaVentas i_v) {
        if (factura.getLetra().equals("A")) {
            modeloFcPapel += "3";
        } else {
            modeloFcPapel += "8";
        }
//        if (categoriaIva.equals(2)) {
//            
//        }
//        if (categoriaIva.equals(3)) {
//            modeloFcPapel += "8";
//        }
//        if (categoriaIva.equals(4)) {
//            modeloFcPapel += "8";
//        }
        String cui = clienteNotaCredito.getCuit();
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
        //cuit_qr = pri + med + fin;
        tipoComprobante_qr = modeloFcPapel;
        numeroComprobante_qr = i_v.getNumeroFactura().toString();
        importe_qr = df.format(i_v.getTotal());
        tipoDoc_qr = clienteNotaCredito.getTipo();
        numeroDoc_qr = pri + med + fin;
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
//        textoNotaCreditoPapel = "Nota de Crédito";
//        fechaNotaCreditoPapel = sdf.format(fecha);
//        clienteNotaCreditoPapel = razonSocialTxt.getText();
//        codigoClienteNotaCreditoPapel = clienteNotaCredito.getCodigo();
//        direccionNotaCreditoPapel = clienteNotaCredito.getDomicilio().getCalle() + " " + clienteNotaCredito.getDomicilio().getNumero() + " - " + clienteNotaCredito.getDomicilio().getLocalidad();
//        cuitNotaCreditoPapel = clienteNotaCredito.getCuit();
//        String condVta = "";
//        Date fechaVto = fecha;
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
//        if (clienteNotaCredito.getFormaDePago().equals(1)) {
////            condVta = "CONTADO               ";
//        }
//        if (clienteNotaCredito.getFormaDePago().equals(2)) {
////            condVta = "7 DIAS F.F            ";
//            cal.add(Calendar.DATE, 7);
////            fechaVto = cal.getTime();
//        }
//        if (clienteNotaCredito.getFormaDePago().equals(3)) {
////            condVta = "14 DIAS F.F.          ";
//            cal.add(Calendar.DATE, 14);
////            fechaVto = cal.getTime();
//        }
//        if (clienteNotaCredito.getFormaDePago().equals(4)) {
////            condVta = "OTRO                  ";
////            fechaVto = null;
//        }

//        vencimientoNotaCreditoPapel = sdf.format(fechaVto);
        modeloFcPapel = "Cod.Nro.";

//        inscripcionClienteNotaCreditoPapel = catego;
//        if (categoriaIva != 1) {
//            //                                    1         2         3         4         5         6         7         8         9        10
//            //                           1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
////            nombresColumnaNotaCreditoPapel = "  IT   CANT                 DETALLE                    P.UNIT.    DESC.   IMPORTE       IMP.     TOTAL     SUG";
//        } else {
////            nombresColumnaNotaCreditoPapel = "  IT   CANT                 DETALLE                   P.UNIT.    DESC.   GRAVADO       IVA       IMP.     TOTAL     SUG";
//        }
        // DecimalFormat df = new DecimalFormat("#0.00");
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
                if (factura.getLetra().equals("B")) {
//                  aqui detalle de importes no inscripto en IVA           *****
// Precio Unitario
                    str0 = tablaNotaCredito.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
                    str0 = tablaNotaCredito.getValueAt(r, 7).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
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
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Impuesto
                    str0 = tablaNotaCredito.getValueAt(r, 5).toString();
                    if (str0.equals("-0,00")) {
                        espacio = "       ";
                        doble = 0.0;
                    } else {
                        str0 = str0.replace(",", ".");
                        doble = Double.valueOf(str0);
                        largo = doble.intValue();
                        espacio = "        ";
                        largo = String.valueOf(largo).length();
                        espacio = espacio.substring(largo);
                    }
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaNotaCredito.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaNotaCredito.getValueAt(r, 9).toString();
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
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Gravado
                    str0 = tablaNotaCredito.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Iva
                    str0 = tablaNotaCredito.getValueAt(r, 6).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Impuesto
                    str0 = tablaNotaCredito.getValueAt(r, 5).toString();
                    if (str0.equals("-0,00")) {
                        espacio = "        ";
                        doble = 0.0;
                    } else {
                        str0 = str0.replace(",", ".");
                        doble = Double.valueOf(str0);
                        largo = doble.intValue();
                        espacio = "        ";
                        largo = String.valueOf(largo).length();
                        espacio = espacio.substring(largo);
                    }
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaNotaCredito.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaNotaCredito.getValueAt(r, 9).toString();
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
        String str0 = String.valueOf(saldoCliente - totalNotaCredito);
        str0 = str0.replace(",", ".");
        Double doble = Double.valueOf(str0);
        int largo = doble.intValue();
        String espacio = "           ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
//        totalDeudaNotaCreditoPapel = espacio + df.format(doble);
// Total Nota de Crédito
        str0 = String.valueOf(totalNotaCredito);
        str0 = str0.replace(",", ".");
        doble = Double.valueOf(str0);
        largo = doble.intValue();
        espacio = "         ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
//        importeTotalNotaCreditoPapel = espacio + df.format(doble);
// Linea Totales
        if (factura.getLetra().equals("B")) {
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                                                            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
//            lineaTotalesNotaCreditoPapel = espacio + df.format(doble);
        } else {
            str0 = String.valueOf(totalGravado);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
//            lineaTotalesNotaCreditoPapel = espacio + df.format(doble);
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
//            lineaTotalesNotaCreditoPapel += espacio + df.format(doble);
            str0 = String.valueOf(totalIva);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                 ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
//            lineaTotalesNotaCreditoPapel += espacio + df.format(doble);
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
//        totalPagarNotaCreditoPapel = espacio + df.format(doble);
// Cantidades atados
//        cantidadesNotaCreditoPapel = "                  CANT.ATADOS NOBLEZA: " + String.valueOf(cantidadAtadosNobleza);
//        cantidadesNotaCreditoPapel += "                 CANT ATADOS MASSALIN: " + String.valueOf(cantidadAtadosMassalin);
//        texto1NotaCreditoPapel = texto1PieNcTxt.getText();
//        texto2NotaCreditoPapel = texto2PieNcTxt.getText();
//        texto3NotaCreditoPapel = "-";
        pdf(i_v, renglonNotaCredito);
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.defaultPage();
        Paper paper = new Paper();

        double margin = 8;
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
        pf.setPaper(paper);
//        pj.setPrintable(new MyPrintable(), pf);
//        if (pj.printDialog()) {
//        try {
//            pj.print();
//        } catch (PrinterException e) {
//            System.out.println(e);
//        }
//        }
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
            nuevoPrecioTxt.setEnabled(true);
            nuevoPrecioTxt.setText(String.valueOf(df.format((prec + iva) * - 1)));
            leerPrecioBtn.setEnabled(false);
            grabarPrecioBtn.setEnabled(true);
            nuevoPrecioTxt.requestFocus();
            tablaNotaCredito.setEnabled(false);
            borrarNoSeleccionadoBtn.setEnabled(false);
            leerCantidadBtn.setEnabled(false);
            nuevaCantidadTxt.setEnabled(false);
            terminarBtn.setEnabled(false);
        }
    }

    private void agregarProducto() {
        if (nro > 0) {
            terminarBtn.setEnabled(true);
        } else {
            terminarBtn.setEnabled(false);
        }
        if (nro > 0) {
            cancelarBtn.setEnabled(true);
        }
    }

    private void calcularTotales() {
        totalGravado = 0.0;
        totalNoGravado = 0.0;
        totalGravadoCigarrillos = 0.0;
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
            if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
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
//            totalGravado += renFa.getGravado();
//            if (renFa.getProducto().getRubro().getCodigo().equals(1)) {
//                cantidadAtadosMassalin += renFa.getCantidad().intValue();
//                importeTotalMassalin += renFa.getTotal();
//            }
//            cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
//            importeMassalinTxt.setText(String.valueOf(df.format(importeTotalMassalin)));
//            if (renFa.getProducto().getRubro().getCodigo().equals(2)) {
//                cantidadAtadosNobleza += renFa.getCantidad().intValue();
//                importeTotalNobleza += renFa.getTotal();
//            }
//            if (factura.getLetra().equals("A")) {// || categoriaIva.equals(2)) {
////          IVA DISCRIMINADO                
//                if (renFa.getProducto().getRubro().getCodigo().equals(5)) {
//                    acumuladoParaDescuentoGlobalGravado += renFa.getGravado();
//                    acumuladoParaDescuentoGlobalNoGravado += renFa.getNoGravado();
//                }
//            } else {
////          CONSUMIDOR FINAL
//                if (renFa.getProducto().getRubro().getCodigo().equals(5)) {
//                    if (renFa.getProducto().getIvaCero()) {
//                        acumuladoParaDescuentoGlobalNoGravado += renFa.getTotal();
//                    } else {
//                        acumuladoParaDescuentoGlobalGravado += renFa.getTotal();
//                    }
//                }
//            }
//            cantidadAtadosNoblezaTxt.setText(String.valueOf(cantidadAtadosNobleza));
//            importeNoblezaTxt.setText(String.valueOf(df.format(importeTotalNobleza)));
//            totalIva += renFa.getIva();
//            totalImpuesto += renFa.getImpuesto();
//            nro += 1;
//            renFa.setItemNro(nro);
//            totalNotaCredito += renFa.getTotal();
        }
        String tgcig = df.format(totalGravadoCigarrillos);
        totalGravadoCigarrillos = Double.valueOf(tgcig.replace(",", "."));
        descuento = clienteNotaCredito.getDescuento();
        Double totalDescuentoGravado = acumuladoParaDescuentoGlobalGravado * descuento / 100;
        Double totalDescuentoNoGravado = acumuladoParaDescuentoGlobalNoGravado * descuento / 100;
        String td = df.format(totalDescuentoGravado);
        totalDescuentoGravado = Double.valueOf(td.replace(",", "."));
        td = df.format(totalDescuentoNoGravado);
        totalDescuentoNoGravado = Double.valueOf(td.replace(",", "."));
        descuentoGlobalTxt.setText(df.format(totalDescuentoGravado + totalDescuentoNoGravado));
        totalDescuento = totalDescuentoGravado + totalDescuentoNoGravado;
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
            Double t1 = totalNotaCredito - totalImpuesto - totalNoGravado;
            Double totalGravadoCompleto = t1 / (1 + (porcentualIva / 100));
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
        }
        cantidadAtadosNoblezaTxt.setText(String.valueOf(cantidadAtadosNobleza));
        importeNoblezaTxt.setText(String.valueOf(df.format(importeTotalNobleza)));
        cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
        importeMassalinTxt.setText(String.valueOf(df.format(importeTotalMassalin)));
        cantidadItemsTxt.setText(String.valueOf(nro));
        totalTxt.setText(String.valueOf(df.format(totalNotaCredito)));
//        clienteFactura.setImporteMostrador(totalFactura);
//        try {
//            new ClienteService().updateCliente(clienteFactura);
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, "Error: 2512");
//        }
//        totalTxt.setText(df.format(totalNotaCredito));
//        if (tieneDto) {
//            Double total2 = acumuladoParaDescuentoGlobalGravado + acumuladoParaDescuentoGlobalNoGravado;
//            Double total0 = total2 * factura.getPorcentualDescuentoGlobal() / 100;
//            Double total1 = total2 - total0;
//            totalIva = total1 * porcentualIva / 100;
//            totalNotaCredito = total1 + totalIva + totalImpuesto;
//            cantidadItemsTxt.setText(String.valueOf(nro));
//            totalDescuento = total0;
//            totalTxt.setText(String.valueOf(df.format(totalNotaCredito)));
//            //totalGravado = totalGravado - totalDescuento;
//            System.out.println("");
//            System.out.println(total2);
//            System.out.println(total0);
//            System.out.println(total1);
//            System.out.println(totalGravado);
//            System.out.println(totalIva);
//            System.out.println(totalImpuesto);
//            System.out.println(totalNotaCredito);
//        } else {
//            Double total0 = totalNotaCredito - totalImpuesto;
//            Double total1 = total0 / (1 + porcentualIva / 100);
//            totalIva = total0 - total1;
//            totalGravado = total1;
//            System.out.println("");
//            System.out.println(totalGravado);
//            System.out.println(totalIva);
//            System.out.println(totalImpuesto);
//            System.out.println(totalNotaCredito);
//        }
//        JOptionPane.showMessageDialog(this, "X");
    }

    private void cargarDatos() {
        // CLIENTEsELECCIONADO Y FACTURA
        String f = factura.getLetra()
                + " " + dfs.format(5)
                + " " + dfn.format(factura.getNumeroFactura());
        facturaTxt.setText(f);
        limpiarCampos();
        codigoTxt.setText(clienteSeleccionado.getCodigo());
        tipoDoc = clienteSeleccionado.getTipo();
        razonSocialTxt.setText(clienteSeleccionado.getRazonSocial());
        saldoTxt.setText(String.valueOf(df.format(clienteSeleccionado.getSaldo())));
        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha = Calendar.getInstance().getTime();
        categoriaIva = 4;
        clienteNotaCredito = clienteSeleccionado;
        if (factura.getPorcentualDescuentoGlobal() != null) {
            if (factura.getDescuentoGlobal() != null) {
                if (factura.getDescuentoGlobal() > 0.0) {
                    descuentoCliente = factura.getPorcentualDescuentoGlobal();
                    porcentajeDtoTxt.setText(df_desc.format(descuentoCliente));
                    descuentoGlobalTxt.setText(df.format(factura.getDescuentoGlobal()));
                    totalTxt.setText(df.format(factura.getTotal()));
                    tieneDto = true;
                } else {
                    descuentoCliente = 0F;
                    porcentajeDtoTxt.setText(df_desc.format(0));
                    descuentoGlobalTxt.setText(df.format(0));
                    tieneDto = false;
                }
            } else {
                descuentoCliente = 0F;
                porcentajeDtoTxt.setText(df_desc.format(0));
                descuentoGlobalTxt.setText(df.format(0));
                tieneDto = false;
            }
        } else {
            descuentoCliente = 0F;
            porcentajeDtoTxt.setText(df_desc.format(0));
            descuentoGlobalTxt.setText(df.format(0));
            tieneDto = false;
        }
        fechaTxt.setText(sdf.format(fecha));
        if (clienteNotaCredito.getCategoriaDeIva() != null) {
            categoriaIva = clienteNotaCredito.getCategoriaDeIva();
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
        } else {
            ivaTxt.setText("Consumidor Final");
        }
        cancelarBtn.setEnabled(true);
        codigoTxt.setEditable(false);
        totalTxt.setEnabled(true);
        totalTxt.setEditable(false);
        agregarProducto();
    }

    private void cargarRenglones() {
        try {
            renglonF = new RenglonFacturaService().getAllRenglonFacturaFromIvaVentas(factura);
            DefaultTableModel tabla = (DefaultTableModel) tablaNotaCredito.getModel();
            for (RenglonFactura rp : renglonF) {
                RenglonNotaCredito rf = new RenglonNotaCredito();
                rf.setCantidad(rp.getCantidad());
                rf.setProducto(rp.getProducto());
                rf.setItemNro(rp.getItemNro());
                rf.setDescripcion(rp.getDescripcion());
                rf.setGravado(-rp.getGravado());
                rf.setNoGravado(-rp.getNoGravado());
                rf.setExento(-rp.getExento());
                if (rp.getImpuesto() > 0.00) {
                    rf.setImpuesto(-rp.getImpuesto());
                } else {
                    rf.setImpuesto(0.00);
                }
                rf.setDescuento(-rp.getDescuento());
                rf.setIva(-rp.getIva());
                rf.setTotal(-rp.getTotal());
                rf.setSugerido(rp.getSugerido());
                renglonNotaCredito.add(rf);
                Object linea[] = new Object[10];
                linea[0] = rf.getProducto().getCodigo();
                linea[1] = rf.getCantidad().intValue();
                linea[2] = rf.getDescripcion();
                linea[3] = df.format((rf.getGravado() + rf.getImpuesto() + rf.getIva()) / rf.getCantidad());
                linea[4] = df.format(-rf.getGravado());
                if (rf.getImpuesto() > 0.00) {
                    linea[5] = df.format(-rf.getImpuesto());
                } else {
                    linea[5] = df.format(0.0);
                }
                linea[6] = df.format(-rf.getIva());
                linea[7] = 0.00;
                linea[8] = df.format(-rf.getTotal());
                linea[9] = df.format(rf.getSugerido());
                tabla.addRow(linea);
            }
            tablaNotaCredito.setModel(tabla);
            cancelarBtn.setEnabled(true);
            codigoTxt.setEditable(false);
            totalTxt.setEnabled(true);
            totalTxt.setEditable(false);
            calcularTotales();
            cancelarBtn.setEnabled(true);
            terminarBtn.setEnabled(true);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void borrarNoSeleccionado() {
        DefaultTableModel tabla = (DefaultTableModel) tablaNotaCredito.getModel();
        int cantidadSeleccionadas = tablaNotaCredito.getSelectedRowCount();
        int cantidadTabla = tablaNotaCredito.getRowCount();
        int a[] = tablaNotaCredito.getSelectedRows();
        if (a.length > 0) {
            for (int n = cantidadTabla - 1; n > -1; n--) {
                if (n != a[cantidadSeleccionadas - 1]) {
                    tabla.removeRow(n);
                    renglonNotaCredito.remove(n);
                } else {
                    cantidadSeleccionadas -= 1;
                    if (cantidadSeleccionadas < 1) {
                        cantidadSeleccionadas += 1;
                    }
                }
            }
        }
        tablaNotaCredito.setModel(tabla);
        calcularTotales();
    }

    private void agregar() {
        if (!cod_prodTxt.getText().isEmpty()) {
            String cd = cod_prodTxt.getText();
            Integer cdI = Integer.valueOf(cd);
            Producto p = null;
            try {
                p = new ProductoService().getProductoByCodigo(cdI);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWebDeFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (p != null) {
                DefaultTableModel tbl = (DefaultTableModel) tablaNotaCredito.getModel();
                RenglonNotaCredito rnc = new RenglonNotaCredito();
                rnc.setCantidad(1F);
                rnc.setDescripcion(p.getDetalle());
                rnc.setDescuento(0.0);
                rnc.setExento(0.0);
                rnc.setGravado(p.getPrecio());
                Double pr = p.getPrecio();
                Double iv = p.getPrecio() * .21;
                rnc.setIva(iv);
                rnc.setImpuesto(p.getImpuesto().doubleValue());
                Double im = p.getImpuesto().doubleValue();
                rnc.setImpuestoUnitario(im.floatValue());
                rnc.setPrecioUnitario(pr);
                rnc.setItemNro(0);
                rnc.setNoGravado(0.0);
                rnc.setProducto(p);
                rnc.setSugerido(0.0);
                rnc.setTotal(pr + iv + im);
                renglonNotaCredito.add(rnc);
                calcularTotales();
                Object[] fila = new Object[10];
                fila[0] = p.getCodigo();
                fila[1] = df1.format(1F);
                fila[2] = p.getDetalle();
                fila[3] = df.format(precioFinal);
                fila[4] = df.format(pr);
                fila[5] = df.format(im);
                fila[6] = df.format(iv);
                fila[7] = df.format(0);
                fila[8] = df.format(totalLinea);
                fila[9] = df.format(p.getSugerido());
                tbl.addRow(fila); // Agrego la fila a la tabla
                Rectangle rect = tablaNotaCredito.getCellRect(nro - 1, 0, true);
                tablaNotaCredito.scrollRectToVisible(rect);
                tablaNotaCredito.clearSelection();
                tablaNotaCredito.setRowSelectionInterval(nro - 1, nro - 1);
                tablaNotaCredito.setModel(tbl); // poner visible la tabla
            }
        }
    }

    private void guardarRepositorio() {
        FcReserved fcr = new FcReserved();
        fcr.setCliente(clienteNotaCredito);
        fcr.setFecha(fecha);
        fcr.setTotal(totalNotaCredito);
        fcr.setImpuesto(totalImpuesto);
        fcr.setGravado(totalGravado);
        fcr.setIva(totalIva);
        fcr.setPorcentualDescuentoGlobal(clienteNotaCredito.getDescuento());
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
        String nnc = dfn.format(numeroNotaCredito);
        FileOutputStream qrCode;
        qrCode = new FileOutputStream(ruta + nnc + extension);
        ImageIO.write(imagen, formato, qrCode);
        qrCode.close();
    }

    private void pdf(IvaVentas iv, List<RenglonNotaCredito> rf) {
        String code = iv.getCliente().getCodigo();
        Cliente cli = null;
        try {
            cli = new ClienteService().getClienteByCodigo(code);
        } catch (Exception ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (factura.getLetra().equals("A")) {// || categoriaIva.equals(2)) {
                File pdf = new PDFBuilder1().armarNcA(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            } else {
                File pdf = new PDFBuilder1().armarNcB(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            }
            JOptionPane.showMessageDialog(this, "PDF GENERADO CORRECTAMENTE");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err1");
            JOptionPane.showMessageDialog(this, "ERROR FILE 2383");
        } catch (DocumentException ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err2");
            JOptionPane.showMessageDialog(this, "ERROR DOCUMENT 2387");
        } catch (Exception ex) {
            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err3");
            JOptionPane.showMessageDialog(this, "ERROR EXCEPTION 2391");
        }
    }
}
