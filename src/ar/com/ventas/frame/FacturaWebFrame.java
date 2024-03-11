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
import ar.com.ventas.entities.EquipoBloqueado;
import ar.com.ventas.entities.FcReserved;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.Producto;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.entities.RenglonFcReserved;
import ar.com.ventas.entities.Rubro;
import ar.com.ventas.entities.Usuario;
import ar.com.ventas.entities.Vendedor;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.ClienteTrabaService;
import ar.com.ventas.services.ConfiguracionService;
import ar.com.ventas.services.EquipoBloqueadoService;
import ar.com.ventas.services.FacturaService;
import ar.com.ventas.services.FcReservedService;
import ar.com.ventas.services.ProductoService;
import ar.com.ventas.services.RenglonFcReservedService;
import ar.com.ventas.services.UsuarioService;
import ar.com.ventas.util.Constantes;
import ar.com.ventas.util.DesktopApi;
import ar.com.ventas.util.PDFBuilder2;
import ar.com.ventas.util.UtilFrame;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;

/**
 *
 * @author Mario
 */
public class FacturaWebFrame extends javax.swing.JFrame {

    private List<RenglonFactura> renglonFactura = new ArrayList<RenglonFactura>();
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
    private String letraFacturaPapel;
    private String sucursalFacturaPapel;
    private String numeroFacturaPapel;
    private String[] renglones = null;
    private Date fecha;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Cliente clienteFactura = null;
    private Producto producto = null;
    private DefaultTableModel tabla = null;
    private Double totalFactura = 0.00;
    private Double totalImpuesto = 0.00;
    private Double totalIva = 0.00;
    private Double totalGravado = 0.00;
    private Double totalGravadoCigarrillos = 0.00;
    private Double totalNoGravado = 0.0;
    private Double totalGravadoCompleto = 0.0;
    private Double totalDescuentoGravado = 0.0;
    private Double totalDescuentoNoGravado = 0.0;
    private Double gravado = 0.00;
    private Double noGravado = 0.00;
    private Double iva = 0.00;
    private Float porcentualIva;
    private Double impuesto = null;
    private Double totalLinea = 0.00;
    private Float descuento = 0F;
    private Cliente clienteSeleccionado;
    private Producto productoSeleccionado;
    private Float cantidad;
    private Integer categoriaIva = 4;
    private final DecimalFormat df = new DecimalFormat("#0.00");
//    private final DecimalFormat df_prn = new DecimalFormat("#0");
    private final DecimalFormat df1 = new DecimalFormat("#0");
    private final DecimalFormat df2 = new DecimalFormat("#0.0");
    private Double saldoCliente = 0.00;
    private String filtro = "";
    private Integer numeroFactura;
    private Configuracion config = null;
    private Double precioFinal = 0.0;
    private Double dto = 0.0;
    private Integer nro = 0;
    private final Integer maxNro = 41;
    private Integer cantidadAtadosMassalin;
    private Integer cantidadAtadosNobleza;
    public Boolean encontrado;
    private String numCae;
    private String vencCae = "";
    private String tipoComprob = "";
    private Usuario usuario;
    private final Integer nivel = 1;
    private final int tst = 0; // 1 esta en test
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form FacturaFrame
     *
     */
    public FacturaWebFrame() {
        getContentPane().setBackground(new java.awt.Color(135, 206, 235));
        initComponents();
        this.setLocationRelativeTo(null);
//        this.order_name = order_name;
//        this.order_num = order_num;
        limpiarCampos();
        bloquearCampos();
        imprimeChk.setVisible(false);
//      levanto el modelo creado del frame
        tabla = (DefaultTableModel) tablaFactura.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        volverBtn = new javax.swing.JButton();
        descuentoGlobalTxt = new javax.swing.JTextField();
        descuentoGlobalLbl = new javax.swing.JLabel();
        fechaTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cuitTxt = new javax.swing.JTextField();
        comboClientes = new javax.swing.JComboBox();
        buscarClienteXNombre = new javax.swing.JButton();
        nombreClienteABuscarTxt = new javax.swing.JTextField();
        razonSocialTxt = new javax.swing.JTextField();
        ivaTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        buscarClienteBtn = new javax.swing.JButton();
        codigoTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFactura = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        codigoBarrasTxt = new javax.swing.JTextField();
        nombreProductoABuscarTxt = new javax.swing.JTextField();
        buscarProductoXNombreBtn = new javax.swing.JButton();
        comboProductos = new javax.swing.JComboBox();
        nombreProductoConsultaTxt = new javax.swing.JTextField();
        precioProductoConsultaTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        codigoProductoTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cantidadTxt = new javax.swing.JTextField();
        descuentoLineaLbl = new javax.swing.JLabel();
        descuentoLineaTxt = new javax.swing.JTextField();
        descuentoBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        descuentoVolumenTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        totalTxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cantidadItemsTxt = new javax.swing.JTextField();
        incorporarAFacturaBtn = new javax.swing.JButton();
        agregarBtn = new javax.swing.JButton();
        eliminarItemBtn = new javax.swing.JButton();
        leerCantidadBtn = new javax.swing.JButton();
        nuevaCantidadTxt = new javax.swing.JTextField();
        grabarCantidadBtn = new javax.swing.JButton();
        leerPrecioBtn = new javax.swing.JButton();
        nuevoPrecioTxt = new javax.swing.JTextField();
        grabarPrecioBtn = new javax.swing.JButton();
        terminarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        imprimeChk = new javax.swing.JCheckBox();
        texto1PieFacturaTxt = new javax.swing.JTextField();
        texto2PieFacturaTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cantidadAtadosMassalinTxt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        importeMassalinTxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cantidadAtadosNoblezaTxt = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        importeNoblezaTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("AYM - Factura Web");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        descuentoGlobalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentoGlobalTxt.setText("DESC");

        descuentoGlobalLbl.setText("Descuento:");

        fechaTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaTxt.setText("FECHA");

        jLabel4.setText("Fecha:");

        cuitTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cuitTxt.setText("CUIT");

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

        buscarClienteXNombre.setText("Buscar x nombre");
        buscarClienteXNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteXNombreActionPerformed(evt);
            }
        });

        nombreClienteABuscarTxt.setText("NOMBRE CLIENTE A BUSCAR");
        nombreClienteABuscarTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreClienteABuscarTxtKeyPressed(evt);
            }
        });

        razonSocialTxt.setText("RAZON SOCIAL");

        ivaTxt.setText("IVA");

        jLabel2.setText("Iva:");

        buscarClienteBtn.setText("Buscar");
        buscarClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteBtnActionPerformed(evt);
            }
        });

        codigoTxt.setText("CODIGO");
        codigoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoTxtKeyPressed(evt);
            }
        });

        jLabel1.setText("Cliente:");

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

        jLabel6.setText("C.Barras:");

        codigoBarrasTxt.setText("COD BARRAS");
        codigoBarrasTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoBarrasTxtKeyPressed(evt);
            }
        });

        nombreProductoABuscarTxt.setText("NOMBRE PRODUCTO A BUSCAR");
        nombreProductoABuscarTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreProductoABuscarTxtKeyPressed(evt);
            }
        });

        buscarProductoXNombreBtn.setText("Buscar x nombre");
        buscarProductoXNombreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProductoXNombreBtnActionPerformed(evt);
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

        nombreProductoConsultaTxt.setText("NOMBRE PRODUCTO CONSULTA");

        precioProductoConsultaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        precioProductoConsultaTxt.setText("PRECIO PROD CONSU");

        jLabel5.setText("Código:");

        codigoProductoTxt.setText("CODIGO P");
        codigoProductoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoProductoTxtKeyPressed(evt);
            }
        });

        jLabel7.setText("Cantidad:");

        cantidadTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadTxt.setText("CANT");
        cantidadTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cantidadTxtKeyPressed(evt);
            }
        });

        descuentoLineaLbl.setText("Dscto:");

        descuentoLineaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentoLineaTxt.setText("DSCTO");
        descuentoLineaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descuentoLineaTxtKeyPressed(evt);
            }
        });

        descuentoBtn.setText("Dto");
        descuentoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoBtnActionPerformed(evt);
            }
        });

        jLabel12.setText("Descuento");

        descuentoVolumenTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentoVolumenTxt.setText("DESCUENTO");

        jLabel8.setText("TOTAL FACTURA:");

        totalTxt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalTxt.setForeground(new java.awt.Color(204, 0, 0));
        totalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTxt.setText("TOTAL");

        jLabel18.setText("Cant. Items:");

        cantidadItemsTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadItemsTxt.setText("Cantidad Items");

        incorporarAFacturaBtn.setText("Incorporar a Factura");
        incorporarAFacturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incorporarAFacturaBtnActionPerformed(evt);
            }
        });

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

        eliminarItemBtn.setText("Eliminar Item seleccionado");
        eliminarItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarItemBtnActionPerformed(evt);
            }
        });

        leerCantidadBtn.setText("Leer Cantidad");
        leerCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerCantidadBtnActionPerformed(evt);
            }
        });

        nuevaCantidadTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevaCantidadTxt.setText("NUE CANT");
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

        nuevoPrecioTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevoPrecioTxt.setText("NUE PREC");
        nuevoPrecioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevoPrecioTxtKeyPressed(evt);
            }
        });

        grabarPrecioBtn.setText("Grabar Precio");
        grabarPrecioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarPrecioBtnActionPerformed(evt);
            }
        });

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

        imprimeChk.setText("Imprime");

        texto1PieFacturaTxt.setText("TEXTO 1 PIE FACTURA");

        texto2PieFacturaTxt.setText("TEXTO 2 PIE FACTURA");

        jLabel15.setText("Massalin:");

        cantidadAtadosMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadAtadosMassalinTxt.setText("Cant.Mass.");

        jLabel19.setText("Importe:");

        importeMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeMassalinTxt.setText("IMP.MASS.");

        jLabel17.setText("Nobleza:");

        cantidadAtadosNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadAtadosNoblezaTxt.setText("Cant.Nobl.");

        jLabel20.setText("Importe:");

        importeNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeNoblezaTxt.setText("IMP.NOBL.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscarClienteBtn)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(razonSocialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nombreClienteABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscarClienteXNombre)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(descuentoGlobalLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(volverBtn))
                            .addComponent(comboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel7))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(codigoProductoTxt)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(8, 8, 8)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(descuentoLineaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addComponent(descuentoBtn)))))
                                    .addComponent(descuentoLineaLbl))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(buscarProductoXNombreBtn))
                                        .addComponent(comboProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(nombreProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(precioProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(texto1PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(texto2PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descuentoVolumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(terminarBtn)
                                .addComponent(cancelarBtn)
                                .addComponent(imprimeChk))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(incorporarAFacturaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(agregarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(eliminarItemBtn)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(leerCantidadBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(grabarCantidadBtn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(leerPrecioBtn)
                                .addGap(18, 18, 18)
                                .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(grabarPrecioBtn)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(razonSocialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarClienteBtn)
                    .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(volverBtn)
                    .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoGlobalLbl)
                    .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreClienteABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarClienteXNombre)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarProductoXNombreBtn)
                    .addComponent(jLabel12)
                    .addComponent(descuentoVolumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(codigoProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precioProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoLineaLbl)
                    .addComponent(descuentoLineaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoBtn)
                    .addComponent(leerCantidadBtn)
                    .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarCantidadBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(incorporarAFacturaBtn)
                    .addComponent(agregarBtn)
                    .addComponent(eliminarItemBtn)
                    .addComponent(leerPrecioBtn)
                    .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarPrecioBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imprimeChk)
                    .addComponent(texto1PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarBtn)
                    .addComponent(texto2PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(terminarBtn)
                    .addComponent(jLabel15)
                    .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteBtnActionPerformed
        buscar();
    }//GEN-LAST:event_buscarClienteBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la factura?", "Atencion", JOptionPane.YES_NO_OPTION);
        // 0 = si; 1 = no
        if (escape == 0) {
            if (totalFactura > 0.00) {
                guardarRepositorio();
            }
            abandonarFactura();
            clienteFactura.setImporteMostrador(0.0);
            try {
                new ClienteService().updateCliente(clienteFactura);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            desbloquearCliente();
        } else {
            prepararCampos();
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        agregarProducto();
    }//GEN-LAST:event_agregarBtnActionPerformed

    private void incorporarAFacturaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incorporarAFacturaBtnActionPerformed
        if (!cantidadTxt.getText().isEmpty()) {
            if (!codigoBarrasTxt.getText().isEmpty()) {
                int l = cantidadTxt.getText().length();
                if (l > 6) {
                    System.out.print("\007");
                    System.out.flush();
                    JOptionPane.showMessageDialog(this, "Utilice una cantidad menor");
                } else {
                    buscarProducto();
                }
            } else {
                if (!codigoProductoTxt.getText().isEmpty()) {
                    int l = cantidadTxt.getText().length();
                    if (l > 6) {
                        System.out.print("\007");
                        System.out.flush();
                        JOptionPane.showMessageDialog(this, "Utilice una cantidad menor");
                    } else {
                        buscarProducto();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código producto");
                    nombreProductoABuscarTxt.requestFocus();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad");
            cantidadTxt.requestFocus();
        }
    }//GEN-LAST:event_incorporarAFacturaBtnActionPerformed

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
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                    productoSeleccionado = new ProductoService()
                            .getProductosByFiltroSin90SinDepo(filtro)
                            .get(seleccion - 1);
                    codigoProductoTxt.setText(String.valueOf(productoSeleccionado.getCodigo()));
                    consultarProducto();
                    cantidadTxt.requestFocus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar producto");
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboProductosActionPerformed

    private void terminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarBtnActionPerformed
        double xSaldo = clienteFactura.getSaldo();
        double ySaldo = rint(xSaldo * 100);
        if (ySaldo != 0) {
            JOptionPane.showMessageDialog(this, "Cliente: "
                    + clienteFactura.getRazonSocial()
                    + "\n Saldo: " + df.format(xSaldo));
        }
        int escape = JOptionPane.showConfirmDialog(null, "Quiere ingresar un texto antes de Imprimir?",
                "Texto en el pie de Factura",
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
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void codigoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoTxt.getText().isEmpty()) {
                buscar();
            } else {
                nombreClienteABuscarTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                volver();
            } else {
                if (evt.getKeyCode() != 8) {
                    if (!isNumeric(evt)) {
                        JOptionPane.showMessageDialog(this, "Solo números");
                        codigoTxt.setText("");
                    }

                }
            }
        }
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


    private void codigoProductoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoProductoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoProductoTxt.getText().isEmpty()) {
                consultarProducto();
                incorporarAFacturaBtn.setEnabled(true);
            } else {
                codigoBarrasTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de abandonar la carga factura?",
                        "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape != 1) {
//                    if (totalFactura > 0.00) {
//                        guardarRepositorio();
//                    }
                    abandonarFactura();
                } else {
                    prepararCampos();
                }
            } else {
                if (evt.getKeyCode() != 8) {
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
                    int l = cantidadTxt.getText().length();
                    if (l > 6) {
                        System.out.print("\007");
                        System.out.flush();
                        JOptionPane.showMessageDialog(this, "Utilice una cantidad menor");
                    } else {
                        buscarProducto();
                    }
                } else {
                    if (!codigoProductoTxt.getText().isEmpty()) {
                        int l = cantidadTxt.getText().length();
                        if (l > 6) {
                            System.out.print("\007");
                            System.out.flush();
                            JOptionPane.showMessageDialog(this, "Utilice una cantidad menor");
                        } else {
                            buscarProducto();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código producto");
                        nombreProductoABuscarTxt.requestFocus();
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
            } else {
                if (evt.getKeyCode() != 8) {
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
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la factura?", "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape == 0) {
//                    if (totalFactura > 0.00) {
//                        guardarRepositorio();
//                    }
                    abandonarFactura();
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
                //cantidadTxt.requestFocus();
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
                    abandonarFactura();
                } else {
                    agregarProducto();
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
    }//GEN-LAST:event_codigoBarrasTxtKeyPressed

    private void leerCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerCantidadBtnActionPerformed
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            RenglonFactura rf = renglonFactura.get(lin);
            if (rf.getProducto().getIvaCero()) {
                JOptionPane.showMessageDialog(this, "No puede modificar Precio de este producto");
                return;
            }
            nuevaCantidadTxt.setText(String.valueOf(rf.getCantidad().intValue()));
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
                Double precioUnitario = rf.getPrecioUnitario();
                Float impuestoUnitario = rf.getImpuestoUnitario();

                tablaFactura.setValueAt(cant.intValue(), lin, 1);
                System.out.println(precioUnitario);
                System.out.println(impuestoUnitario);
                System.out.println(df.format(rf.getTotal()));
                calcularLinea(cant, precioUnitario, impuestoUnitario, rf.getProducto());

                if (cant == 0) {
                    JOptionPane.showMessageDialog(this, "Cantidad es cero");
                    return;
                }

                //tablaFactura.setValueAt(df.format(precioFinal), lin, 3);
                nuevaCantidadTxt.setText("");

                rf.setGravado(gravado);
                rf.setImpuesto(impuesto);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                System.out.println(precioUnitario);
                System.out.println(impuestoUnitario);
                System.out.println(df.format(rf.getTotal()));
                //System.exit(0);
                //renglonFactura.set(lin, rf);
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
    }//GEN-LAST:event_grabarCantidadBtnActionPerformed

    private void leerPrecioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerPrecioBtnActionPerformed
        int lin = tablaFactura.getSelectedRow();
        RenglonFactura rf = renglonFactura.get(lin);
//        if (rf.getProducto().getIvaCero()) {
//            JOptionPane.showMessageDialog(this, "No puede modificar Precio de este producto");
//            leerPrecioBtn.setEnabled(true);
//            grabarPrecioBtn.setEnabled(false);
//            leerCantidadBtn.setEnabled(true);
//            agregarBtn.requestFocus();
//            return;
//        }
        if (rf.getDescuento() > 0.0) {
            JOptionPane.showMessageDialog(this, "No puede modificar Precio de este producto");
            leerPrecioBtn.setEnabled(true);
            grabarPrecioBtn.setEnabled(false);
            leerCantidadBtn.setEnabled(true);
            agregarBtn.requestFocus();
            return;
        }
        if (habilitado()) {
            leerPrecio();
        }
    }//GEN-LAST:event_leerPrecioBtnActionPerformed

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
                buscarProductoXNombreBtn.setEnabled(false);
                try {
                    Integer seleccion = comboProductos.getSelectedIndex();
                    productoSeleccionado = new ProductoService()
                            .getProductosByFiltroSin90SinDepo(filtro)
                            .get(seleccion - 1);
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
                Float impuestoUnitario = rf.getImpuestoUnitario();
                Double nuevoImporte = Double
                        .valueOf(nuevoPrecioTxt.getText()
                                .replaceAll("\\,", "\\."))
                        / (1 + (porcentualIva / 100));
                if (nuevoImporte > 0.00) {
                    rf.setPrecioUnitario(nuevoImporte);

                    calcularLinea(cant, nuevoImporte, impuestoUnitario, rf.getProducto());

                    if (cant == 0) {
                        return;
                    }
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
                    JOptionPane.showMessageDialog(this, "VERIFIQUE IMPORTE MAYOR QUE CERO");
                }
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
                    Float impuestoUnitario = rf.getImpuestoUnitario();
                    renglonFactura.set(lin, rf);
                    tablaFactura.setValueAt(cant.intValue(), lin, 1);

                    calcularLinea(cant, precioUnitario, impuestoUnitario, rf.getProducto());
//                    if (cantidadTxt.getText().isEmpty()) {
//                        return;
//                    }
                    if (cant == 0) {
                        JOptionPane.showMessageDialog(this, "Cantidad es cero");
                        return;
                    }
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
                    agregarBtn.requestFocus();
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
                    Float impuestoUnitario = rf.getImpuestoUnitario();
                    Double nuevoImporte = Double.valueOf(nuevoPrecioTxt.getText()
                            .replaceAll("\\,", "\\."))
                            / (1 + (porcentualIva / 100));
                    rf.setPrecioUnitario(nuevoImporte);

                    calcularLinea(cant, nuevoImporte, impuestoUnitario, rf.getProducto());

                    if (cant == 0) {
                        return;
                    }
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

    private void descuentoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoBtnActionPerformed
        int row = tablaFactura.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un Producto");
            return;
        }
        if (renglonFactura.get(row).getDescuento() > 0.00) {
            JOptionPane.showMessageDialog(this, "DESCUENTO YA REALIZADO");
            return;
        }
        Producto p = renglonFactura.get(row).getProducto();
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
                tablaFactura.setEnabled(false);
                descuentoLineaTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_descuentoBtnActionPerformed

    private void descuentoLineaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoLineaTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (descuentoLineaTxt.getText().isEmpty()) {
                descuentoLineaTxt.setEnabled(false);
                agregarBtn.requestFocus();
                return;
            }
            descuentoBtn.setEnabled(true);
            int row = tablaFactura.getSelectedRow();
            Producto p = renglonFactura.get(row).getProducto();
//            if (p.getIvaCero()) {
//                JOptionPane.showMessageDialog(this, "No habilitado para descuentos");
//                descuentoLineaTxt.setEnabled(false);
//                agregarBtn.requestFocus();
//                return;
//            }
            if (p != null) {
                Rubro ru = p.getRubro();
                int cR = ru.getCodigo();
                if (cR != 5) {
                    JOptionPane.showMessageDialog(this, "DESCUENTO NO PERMITIDO");
                    return;
                }
            }
            if (categoriaIva.equals(1)) {
                int dtoLinea = Integer.valueOf(descuentoLineaTxt.getText());
                Double totNuevo0 = renglonFactura.get(row).getGravado();
                Double totNuevo1 = totNuevo0 * dtoLinea / 100;
                String tn1 = df.format(totNuevo1);
                Double totNuevo2 = totNuevo0 - totNuevo1;
                String tn2 = df.format(totNuevo2);
                Double desc = Double.valueOf(tn1.replace(",", "."));
                Double totL = Double.valueOf(tn2.replace(",", "."));
                RenglonFactura rf = renglonFactura.get(row);
                String n_iv = df.format(totL * porcentualIva / 100);
                Double d_iv = Double.valueOf(n_iv.replace(",", "."));
                Double d_tot = totL + d_iv + rf.getImpuesto();
                rf.setDescuento(desc);
                rf.setGravado(totL);
                rf.setIva(d_iv);
                rf.setTotal(d_tot);
                renglonFactura.set(row, rf);
                tablaFactura.setValueAt(df.format(desc), row, 7);
                tablaFactura.setValueAt(df.format(totL), row, 4);
                tablaFactura.setValueAt(df.format(d_tot), row, 8);
                tablaFactura.setValueAt(df.format(d_iv), row, 8);
                tablaFactura.setEnabled(true);
            } else {
                int dtoLinea = Integer.valueOf(descuentoLineaTxt.getText());
                Double totNuevo0 = renglonFactura.get(row).getTotal();
                Double totNuevo1 = totNuevo0 * dtoLinea / 100;
                String tn1 = df.format(totNuevo1);
                Double totNuevo2 = totNuevo0 - totNuevo1;
                String tn2 = df.format(totNuevo2);
                Double desc = Double.valueOf(tn1.replace(",", "."));
                Double totL = Double.valueOf(tn2.replace(",", "."));
                RenglonFactura rf = renglonFactura.get(row);
                rf.setDescuento(desc);
                rf.setTotal(totL);
                renglonFactura.set(row, rf);
                tablaFactura.setValueAt(df.format(desc), row, 7);
                tablaFactura.setValueAt(df.format(totL), row, 8);
                tablaFactura.setEnabled(true);
            }
            calcularTotales();
            descuentoLineaTxt.setText("");
            descuentoLineaTxt.setEnabled(false);
            descuentoBtn.setEnabled(true);
            agregarBtn.requestFocus();
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
        }
    }//GEN-LAST:event_descuentoLineaTxtKeyPressed

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
            java.util.logging.Logger.getLogger(FacturaWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturaWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturaWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturaWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturaWebFrame().setVisible(true);
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
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JTable tablaFactura;
    private javax.swing.JButton terminarBtn;
    private javax.swing.JTextField texto1PieFacturaTxt;
    private javax.swing.JTextField texto2PieFacturaTxt;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        texto1PieFacturaTxt.setText("");
        cuitTxt.setText("");
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
        comboClientes.removeAllItems();
        comboClientes.addItem("");
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        nombreClienteABuscarTxt.setText("");
        nombreProductoABuscarTxt.setText("");
        descuentoVolumenTxt.setText("0.00");
        descuentoLineaTxt.setText("");
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        nombreProductoConsultaTxt.setText("");
        precioProductoConsultaTxt.setText("");
        cantidadItemsTxt.setText("");
        descuentoGlobalTxt.setText("");
        nuevaCantidadTxt.setText("");
        nuevoPrecioTxt.setText("");
        nuevoPrecioTxt.setEnabled(false);
        importeMassalinTxt.setText("");
        importeNoblezaTxt.setText("");
        importeMassalinTxt.setEditable(false);
        importeNoblezaTxt.setEditable(false);
        imprimeChk.setSelected(false);
        imprimeChk.setVisible(false);
    }

    private void bloquearCampos() {
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
        nuevaCantidadTxt.setEditable(false);
        nuevaCantidadTxt.setEnabled(false);
        leerCantidadBtn.setEnabled(false);
        grabarCantidadBtn.setEnabled(false);
        leerPrecioBtn.setEnabled(false);
        grabarPrecioBtn.setEnabled(false);
    }

    private void llenarComboClientes() {
        filtro = nombreClienteABuscarTxt.getText();
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            clientes = new ClienteService().getClientesByFiltro(filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - leyendo Clientes");
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        List<Producto> productos = null;
        try {
            productos = new ProductoService().getProductosByFiltroSin90SinDepo(filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - leyendo Productos");
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (productos != null && !productos.isEmpty()) {
            for (Producto pro : productos) {
                comboProductos.addItem(pro.getDetalle());
            }
        }
    }

    private void buscar() {
        filtro = "";
        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha = Calendar.getInstance().getTime();
        //Cliente cli = new Cliente();
        try {
            clienteFactura = new ClienteService().getClienteByCodigo(codigoTxt.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - cliente");
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clienteFactura != null) {
            razonSocialTxt.setText(clienteFactura.getRazonSocial());
            if (clienteFactura.getDescuento() != null) {
                descuentoGlobalTxt.setText(df2.format(clienteFactura.getDescuento()));
                descuento = clienteFactura.getDescuento();
            } else {
                descuentoGlobalTxt.setText(df2.format(0F));
                descuento = 0F;
            }
            String s = clienteFactura.getCodigo();
            ClienteTraba ct = null;
            try {
                ct = new ClienteTrabaService().getClienteByCodigo(s);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ct.getTraba1() != null) {
                if (ct.getTraba1()) {
                    JOptionPane.showMessageDialog(this, "Este cliente esta bloqueado para esta operacion");
                    clienteFactura = null;
                    return;
                }
            }
            bloquearCliente();
            if (clienteFactura.getVendedor() == null) {
                JOptionPane.showMessageDialog(this, "Debe asignar un Vendedor al Cliente");
                razonSocialTxt.setText("");
                codigoTxt.setText("");
                codigoTxt.requestFocus();
                return;
            }
            cuitTxt.setText(clienteFactura.getCuit());
            fechaTxt.setText(sdf.format(fecha));
            double xSaldo = clienteFactura.getSaldo();
            double ySaldo = rint(xSaldo * 100);
            if (ySaldo != 0) {
                JOptionPane.showMessageDialog(this, "Cliente: "
                        + clienteFactura.getRazonSocial()
                        + "\n Saldo: " + df.format(xSaldo));
            }
            if (clienteFactura.getCuit().subSequence(0, 6).equals("11-111")) {
                JOptionPane.showMessageDialog(this, "Debe verificar la CUIT "
                        + "del Cliente: \n" + clienteFactura.getRazonSocial()
                        + "\nCodigo: " + clienteFactura.getCodigo());
                desbloquearCliente();
                volver();
            }
            if (clienteFactura.getCuit().length() != 13) {
                JOptionPane.showMessageDialog(this, "Debe verificar la CUIT "
                        + "del Cliente: \n" + clienteFactura.getRazonSocial()
                        + "\nCodigo: " + clienteFactura.getCodigo());
                volver();
            }
            if (clienteFactura.getCategoriaDeIva() != null) {
                categoriaIva = clienteFactura.getCategoriaDeIva();
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
            if (clienteFactura.getSaldo() != null) {
                saldoCliente = clienteFactura.getSaldo();
            } else {
                saldoCliente = 0.0;
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
            descuentoVolumenTxt.setEnabled(true);
            descuentoVolumenTxt.setEditable(false);
            totalTxt.setEnabled(true);
            totalTxt.setEditable(false);
            leerCantidadBtn.setEnabled(false);
            nuevaCantidadTxt.setEditable(true);
            nuevaCantidadTxt.setEnabled(false);
            cancelarBtn.setEnabled(true);
            agregarProducto();
        } else {
            JOptionPane.showMessageDialog(this, "Error - cliente no existe");
            buscarClienteBtn.setEnabled(true);
            codigoTxt.requestFocus();
        }
    }

    private void terminarFactura() {
        numCae = "0";
        sucursalFacturaPapel = "5";
//        try {
//            config = new ConfiguracionService().getFacturas(1L);
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        vencCae = sdf.format(fecha);
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
//            numeroFactura = config.getNumeroFacturaA();
//            numeroFactura += 1;
            letraFacturaPapel = "A";
        } else {
//            numeroFactura = config.getNumeroFacturaB();
//            numeroFactura += 1;
            letraFacturaPapel = "B";
        }
//        numeroFacturaPapel = numeroFactura.toString();
//        try {
//            config = new ConfiguracionService().updateConfiguracion(config);
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        // presentacion web  --  1 esta en test

        if (tst == 0) {
            try {
                LibraryLoader.loadJacobLibrary();
                ActiveXComponent wsaa = new ActiveXComponent("WSAA");
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
                if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
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
                String cui = clienteFactura.getCuit();
                String cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
                String tipoD = clienteFactura.getTipo();
                String tipo_doc = tipoD, nro_doc = cuit1; //tipo y numero
                int cbte_nro = Integer.parseInt(ult.toString()) + 1,
                        cbt_desde = cbte_nro,
                        cbt_hasta = cbte_nro;
                numeroFactura = cbte_nro;
                numeroFacturaPapel = String.valueOf(cbte_nro);
                int largo = ("00000000" + numeroFacturaPapel).length();
                numeroFacturaPapel = ("00000000" + numeroFacturaPapel).substring(largo - 8, largo);
                String imp_total = df.format(totalFactura).toString().replaceAll("\\,", "\\.");//"124.00";
                String imp_tot_conc = "0.00";
                String imp_neto = df.format(totalGravado + totalNoGravado).toString().replaceAll("\\,", "\\.");
                String imp_iva = df.format(totalIva).toString().replaceAll("\\,", "\\.");//"21.00"
                int internos = (int) rint(totalImpuesto * 100);
//                String tng = df.format(totalNoGravado);
//                tng = tng.replace(",", ".");
                String imp_trib = "", imp_op_ex = "0.00";
                if (internos > 0) {
                    imp_trib = df.format(totalImpuesto).toString().replaceAll("\\,", "\\.");
                } else {
                    imp_trib = "0.00";
                }
                String fecha_cbte = fechaWs, fecha_venc_pago = "";
                String fecha_serv_desde = "", fecha_serv_hasta = "";
                String moneda_id = "PES", moneda_ctz = "1.000";
//                System.out.println(imp_total);
//                System.out.println(imp_tot_conc);
//                System.out.println(imp_neto);
//                System.out.println(imp_op_ex);
//                System.out.println(imp_trib);
//                System.out.println(imp_iva);
//                System.out.println("");
                //JOptionPane.showMessageDialog(this, "ver");
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
                    Variant tributo_id = new Variant(4),//99 otros impuestos
                            tributo_desc = new Variant("Impuestos internos"),
                            tributo_base_imp = new Variant("0.00"),
                            tributo_alic = new Variant("0.00"),
                            tributo_importe = new Variant(df.format(totalImpuesto).toString().replaceAll("\\,", "\\."));
//                System.out.println(tributo_base_imp);
                    Dispatch.call(wsfev1, "AgregarTributo",
                            tributo_id, tributo_desc, tributo_base_imp,
                            tributo_alic, tributo_importe);
                }
                Variant iva_id = new Variant(5), // 21%
                        iva_base_imp = new Variant(df.format(totalGravado).toString().replaceAll("\\,", "\\.")),
                        iva_importe = new Variant(df.format(totalIva).toString().replaceAll("\\,", "\\."));
                Dispatch.call(wsfev1, "AgregarIva",
                        iva_id, iva_base_imp, iva_importe);
                if (totalNoGravado > 0) {
                    iva_id = new Variant(3); // 0%
                    iva_base_imp = new Variant(df.format(totalNoGravado).toString().replaceAll("\\,", "\\."));
                    iva_importe = new Variant(df.format(0.0).toString().replaceAll("\\,", "\\."));
                    Dispatch.call(wsfev1, "AgregarIva",
                            iva_id, iva_base_imp, iva_importe);
                }
//            System.out.println(iva_base_imp);
//            System.exit(0);
                Dispatch.put(wsfev1, "Reprocesar", new Variant(false));
                Variant cae = Dispatch.call(wsfev1, "CAESolicitar");
                String requ = Dispatch.get(wsfev1, "XmlRequest").toString();
                String resp = Dispatch.get(wsfev1, "XmlResponse").toString();
                excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
                String errmsg = Dispatch.get(wsfev1, "ErrMsg").toString();
                String obs = Dispatch.get(wsfev1, "Obs").toString();
                String vto = Dispatch.get(wsfev1, "Vencimiento").toString();
                String resultado = Dispatch.get(wsfev1, "Resultado").toString();
                numCae = cae.toString();
                if (!resultado.equals("A")) {
                    terminarBtn.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "Obs: " + obs + "\nError: " + errmsg);
                    JOptionPane.showMessageDialog(this, excepcion);
                    desbloquearCliente();
                    //guardarRepositorio();
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
                        int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                        suma1 += num;
                        x = 1;
                    } else {
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
                //e.printStackTrace();
                terminarBtn.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Error: 1996");
                return;
            }
        }
        // fin presentacion web
        
        // aqui va el bloqueo de equipo
        int bloqueado = bloquearEquipo();
        
        String codi = clienteFactura.getCodigo();
        try {
            clienteFactura = new ClienteService().getClienteByCodigo(codi);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        saldoCliente = clienteFactura.getSaldo();
        saldoCliente += totalFactura;
        clienteFactura.setSaldo(saldoCliente);
        Long id = (long) 1;
        try {
            config = new ConfiguracionService().getFacturas(id);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: 2205");
        }
        config.setUltimaFechaSistema(fecha);
        if (letraFacturaPapel.equals("B")) {
            config.setNumeroFacturaB(numeroFactura);
        } else {
            config.setNumeroFacturaA(numeroFactura);
        }
        IvaVentas ivaVentas = new IvaVentas();
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
            // es inscripto o Monotributo
            numeroFactura = config.getNumeroFacturaA();
//            numeroFactura += 0;
            //config.setNumeroFacturaA(numeroFactura);
            ivaVentas.setCodigoTipoDoc(1);
        } else {
            // el resto de las categorias
            numeroFactura = config.getNumeroFacturaB();
//            numeroFactura += 0;
            //config.setNumeroFacturaB(numeroFactura);
            ivaVentas.setCodigoTipoDoc(6);
        }

        Vendedor ve = clienteFactura.getVendedor();
        ivaVentas.setCliente(clienteFactura);
        ivaVentas.setVendedor(ve);
        ivaVentas.setDescuentoGlobal(totalDescuentoGravado + totalDescuentoNoGravado);
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
        ivaVentas.setIva(totalIva);
        ivaVentas.setNoGravado(0.0);
        ivaVentas.setGravadoCigarrillos(totalGravadoCigarrillos);
        ivaVentas.setTotal(totalFactura);
        ivaVentas.setLetra(letraFacturaPapel);
        ivaVentas.setNumeroSucursal(Integer.valueOf(sucursalFacturaPapel));
        ivaVentas.setNumeroFactura(Integer.valueOf(numeroFacturaPapel));
        if (texto1PieFacturaTxt.getText().isEmpty()) {
            ivaVentas.setTextoPieFactura1("");
        } else {
            ivaVentas.setTextoPieFactura1(texto1PieFacturaTxt.getText());
        }
        if (texto1PieFacturaTxt.getText().isEmpty()) {
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
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 2268");
            }
            Float stock;
            if (producto.getStock() != null) {
                stock = producto.getStock();
            } else {
                stock = 0.0F;
            }
            reFa.setProducto(producto);
            stock -= reFa.getCantidad();
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
                                JOptionPane.showMessageDialog(this, "Error: 2249");
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
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: 2261");
            }
        }
        CtaCteCliente ccc = new CtaCteCliente();
        ccc.setCliente(clienteFactura);
        ccc.setFactura(ivaVentas);
        ccc.setFecha(fecha);
        ccc.setDebe(totalFactura);
        ccc.setHaber(0.0);
        ccc.setTipo("FC");
        ccc.setSaldo(saldoCliente);
        
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
        generarFacturaPdf(ivaVentas, renglonFactura);
        volver();
    }

    private void agregarProducto() {
        eliminarItemBtn.setEnabled(false);
        texto1PieFacturaTxt.setEnabled(false);
        texto2PieFacturaTxt.setEnabled(false);
        codigoBarrasTxt.setText("");
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
        codigoBarrasTxt.requestFocus();
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
        Producto pro = null;
        encontrado = false;
        if (!codigoBarrasTxt.getText().isEmpty()) {
            try {
                pro = new ProductoService().getProductoByCodigoBarras(Long.valueOf(codigoBarrasTxt.getText()));
                encontrado = true;
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                nombreProductoABuscarTxt.requestFocus();
            }
        } else {
            if (!codigoProductoTxt.getText().isEmpty()) {
                try {
                    pro = new ProductoService().getProductoByCodigo(Integer.valueOf(codigoProductoTxt.getText()));
                    encontrado = true;
                } catch (Exception ex) {
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                    nombreProductoABuscarTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese Código de Producto o Código de Barras");
                nombreProductoABuscarTxt.requestFocus();
            }
        }
        if (pro != null) {
            if (pro.getInactivo() != null) {
                if (!pro.getInactivo()) {
                    if (!(pro.getSubRubro().getCodigo().equals(5099))) {
                        cantidad = Float.valueOf(cantidadTxt.getText());
                        Double prec = pro.getPrecio();
                        Float impu = pro.getImpuesto();
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
                        RenglonFactura rf = new RenglonFactura();
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
                        rf.setPrecioUnitario(pro.getPrecio());
                        rf.setImpuestoUnitario(pro.getImpuesto());
                        nro += 1;
                        renglonFactura.add(rf);
                        calcularTotales();
                        Object[] fila = new Object[10];
                        fila[0] = pro.getCodigo();
                        fila[1] = df1.format(cantidad);
                        fila[2] = pro.getDetalle();
                        fila[3] = df.format(precioFinal);
                        fila[4] = df.format(gravado);
                        fila[5] = df.format(impuesto);
                        fila[6] = df.format(iva);
                        fila[7] = df.format(dto);
                        fila[8] = df.format(totalLinea);
                        fila[9] = df.format(pro.getSugerido());
                        clienteFactura.setImporteMostrador(totalFactura);
                        try {
                            new ClienteService().updateCliente(clienteFactura);
                        } catch (Exception ex) {
                            //Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(this, "Error 2415 - Actualizando Datos Cliente " + ex);
                        }
                        tabla.addRow(fila); // Agrego la fila a la tabla
                        Rectangle rect = tablaFactura.getCellRect(nro - 1, 0, true);
                        tablaFactura.scrollRectToVisible(rect);
                        tablaFactura.clearSelection();
                        tablaFactura.setRowSelectionInterval(nro - 1, nro - 1);
                        tablaFactura.setModel(tabla); // poner visible la tabla
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
                        leerPrecioBtn.setEnabled(true);
                        leerCantidadBtn.setEnabled(true);
                        nuevoPrecioTxt.setEnabled(true);
                        nuevaCantidadTxt.setEnabled(true);
                        descuentoBtn.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error - producto no disponible");
                        codigoProductoTxt.setText("");
                        cantidadTxt.setText("");
                        nombreProductoABuscarTxt.setText("");
                        agregarBtn.setEnabled(false);
                        nombreProductoABuscarTxt.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                    codigoProductoTxt.setText("");
                    cantidadTxt.setText("");
                    buscarClienteBtn.setEnabled(false);
                    //codigoBarrasTxt.requestFocus();
                    nombreProductoABuscarTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                codigoProductoTxt.setText("");
                cantidadTxt.setText("");
                buscarClienteBtn.setEnabled(false);
                //codigoBarrasTxt.requestFocus();
                nombreProductoABuscarTxt.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error - producto no existe");
            codigoProductoTxt.setText("");
            //codigoBarrasTxt.requestFocus();
            nombreProductoABuscarTxt.requestFocus();
        }
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        agregarBtn.requestFocus();
    }

    private void borrarTablaProductos() {
        int cantidadRow = tablaFactura.getRowCount();
        DefaultTableModel model1 = (DefaultTableModel) tablaFactura.getModel();
        if (cantidadRow > 0) {
            for (int i = 0; i < cantidadRow; i++) {
                model1.removeRow(0);
            }
            tablaFactura.setModel(model1);
            nro = 0;
        }
        renglonFactura = new ArrayList<RenglonFactura>();
    }

    private void consultarProducto() {
        nombreProductoConsultaTxt.setEnabled(true);
        precioProductoConsultaTxt.setEnabled(true);
        if (!codigoProductoTxt.getText().isEmpty()) {
            Integer codi = Integer.valueOf(codigoProductoTxt.getText());
            try {
                Producto prod = new ProductoService().getProductoByCodigo(codi);
                Float pi = porcentualIva;
                if (!(prod.getSubRubro().getCodigo().equals(5099))) {
                    nombreProductoConsultaTxt.setText(prod.getDetalle());
                    if (prod.getIvaCero()) {
                        pi = 0F;
                    }
                    Double precioProductoConsulta = prod.getPrecio();
                    if (!prod.getIvaCero()) {
                        precioProductoConsulta += precioProductoConsulta * pi / 100;
                    }
                    if (prod.getImpuesto() != null) {
                        precioProductoConsulta += prod.getImpuesto();
                    }
                    precioProductoConsultaTxt.setText(String.valueOf(df.format(precioProductoConsulta)));
                    cantidadTxt.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "No existe Producto");
                    codigoProductoTxt.requestFocus();
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
        totalGravado = 0.0;
        totalNoGravado = 0.0;
        totalGravadoCigarrillos = 0.00;
        totalIva = 0.0;
        totalImpuesto = 0.0;
        totalFactura = 0.0;
        nro = 0;
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        Double acumuladoParaDescuentoGlobalGravado = 0.0;
        Double acumuladoParaDescuentoGlobalNoGravado = 0.0;
        Double importeTotalMassalin = 0.0;
        Double importeTotalNobleza = 0.0;
        for (RenglonFactura renFa : renglonFactura) {
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
                totalFactura += renFa.getTotal();
                if (renFa.getProducto().getRubro().getCodigo().equals(5)) {
                    acumuladoParaDescuentoGlobalGravado += renFa.getGravado();
                    acumuladoParaDescuentoGlobalNoGravado += renFa.getNoGravado();
                }
            } else {
//          CONSUMIDOR FINAL
                totalFactura += renFa.getTotal();
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
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
            Double t1 = totalFactura - totalImpuesto - totalNoGravado;
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
            totalFactura = totalGravado + totalNoGravado + totalIva
                    + totalImpuesto; // - totalDescuentoGravado - totalDescuentoNoGravado;
            String tf = df.format(totalFactura);
            totalFactura = Double.valueOf(tf.replace(",", "."));
        } else {
            String tf = df.format(totalFactura - totalDescuentoGravado - totalDescuentoNoGravado);
            totalFactura = Double.valueOf(tf.replace(",", "."));
            totalTxt.setText(tf);
            String ti = df.format(totalImpuesto);
            totalImpuesto = Double.valueOf(ti.replace(",", "."));
            totalGravado = (totalFactura - totalImpuesto - totalNoGravado) / (1 + (porcentualIva / 100));
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
        totalTxt.setText(String.valueOf(df.format(totalFactura)));
        clienteFactura.setImporteMostrador(totalFactura);
        try {
            new ClienteService().updateCliente(clienteFactura);
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
                if (prod.getIvaCero() != null) {
                    if (!prod.getIvaCero()) {
                        precioProductoConsulta += precioProductoConsulta * porcentualIva / 100;
                    }
                } else {
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
                codigoBarrasTxt.setText("");
                codigoBarrasTxt.requestFocus();
            }
        }
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
        fcr.setDescuentoGlobal(descuento.doubleValue());
        fcr.setPorcentualDescuentoGlobal(clienteFactura.getDescuento());
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

    private void calcularLinea(Float cantid, Double precio, Float impuest, Producto p) {
        Float desc_calc = 0F;
        Double dt = 0.0;
        Float pi = porcentualIva;
        if (!descuentoLineaTxt.getText().isEmpty()) {
            desc_calc = Float.valueOf(descuentoLineaTxt.getText());
            String p_calc = df.format(precio * (1 - desc_calc / 100));
            Double dp = Double.valueOf(p_calc.replace(",", "."));
            dt = precio - dp;
            precio = dp;
        }
        if (p.getIvaCero()) {
            pi = 0F;
            precioFinal = precio;
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
            String spf = df.format(precio
                    * (1 + (pi / 100)));
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
            s_cal = df.format(impuest * cantid);
            impuesto = Double.valueOf(s_cal.replace(",", "."));
            Double calculo = t_lin / (1 + pi / 100);
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

    private void abandonarFactura() {
        limpiarCampos();
        bloquearCampos();
        borrarTablaProductos();
        volverBtn.setEnabled(true);
        buscarClienteBtn.setEnabled(true);
        codigoTxt.requestFocus();
        leerCantidadBtn.setEnabled(false);
        nuevaCantidadTxt.setEnabled(false);
        nuevoPrecioTxt.setEnabled(false);
        nombreClienteABuscarTxt.setEnabled(true);
        buscarClienteXNombre.setEnabled(true);
        comboClientes.setEnabled(true);
        terminarBtn.setEnabled(false);
    }

    private void prepararCampos() {
        incorporarAFacturaBtn.setEnabled(false);
        eliminarItemBtn.setEnabled(true);
        agregarBtn.setEnabled(true);
        agregarBtn.requestFocus();
        codigoBarrasTxt.setEnabled(false);
        codigoProductoTxt.setEnabled(false);
        cantidadTxt.setEnabled(false);
        nombreProductoABuscarTxt.setEnabled(false);
        comboProductos.setEnabled(false);
        grabarPrecioBtn.setEnabled(false);
        grabarCantidadBtn.setEnabled(false);
        nuevoPrecioTxt.setText("");
        nuevaCantidadTxt.setText("");
        nuevaCantidadTxt.setEnabled(true);
        nuevoPrecioTxt.setEnabled(true);
        leerPrecioBtn.setEnabled(true);
        leerCantidadBtn.setEnabled(true);
        terminarBtn.setEnabled(true);
        tablaFactura.setEnabled(true);
        //nombreProductoConsultaTxt.setEnabled(false);
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
            Double prec = 0.0;
            if (rf.getPrecioUnitario() != null) {
                prec = rf.getPrecioUnitario();
            } else {
                prec = rf.getProducto().getPrecio();
            }
            iva = prec * porcentualIva / 100;
            nuevoPrecioTxt.setEnabled(true);
            nuevoPrecioTxt.setText(String.valueOf(df.format(prec + iva)));
            leerPrecioBtn.setEnabled(false);
            grabarPrecioBtn.setEnabled(true);
            nuevoPrecioTxt.requestFocus();
            tablaFactura.setEnabled(false);
            agregarBtn.setEnabled(false);
            eliminarItemBtn.setEnabled(false);
            leerCantidadBtn.setEnabled(false);
            nuevaCantidadTxt.setEnabled(false);
            terminarBtn.setEnabled(false);
        }
    }

    private void bloquearCliente() {
        String codi = clienteFactura.getCodigo();
        ClienteTraba xCli = null;
        try {
            xCli = new ClienteTrabaService().getClienteByCodigo(codi);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        xCli.setTraba1(true);
        try {
            new ClienteTrabaService().updateCliente(xCli);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al bloquear Cliente");
        }
    }

    private void desbloquearCliente() {
        String codi = clienteFactura.getCodigo();
        try {
            clienteFactura = new ClienteService().getClienteByCodigo(codi);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        clienteFactura.setImporteMostrador(0.0);
        try {
            new ClienteService().updateCliente(clienteFactura);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clienteFactura = new ClienteService().getClienteByCodigo(codi);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClienteTraba ct = null;
        try {
            ct = new ClienteTrabaService().getClienteByCodigo(codi);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al desbloquear Cliente - DEBE DESBLOQUEAR");
            return;
        }
        ct.setTraba1(false);
        try {
            new ClienteTrabaService().updateCliente(ct);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al desbloquear Cliente - DEBE DESBLOQUEAR");
            return;
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

//    private void generarQR(String data) throws Exception {
//        String cadenaCodificada = Base64.getEncoder().encodeToString(data.getBytes());
//        BitMatrix matriz;
//        Writer writer = new QRCodeWriter();
//        try {
//            matriz = writer.encode(url_qr + cadenaCodificada, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
//        } catch (WriterException e) {
//            e.printStackTrace(System.err);
//            return;
//        }
//        BufferedImage imagen = new BufferedImage(qrTamAncho,
//                qrTamAlto, BufferedImage.TYPE_INT_RGB);
//        for (int y = 0; y < qrTamAlto; y++) {
//            for (int x = 0; x < qrTamAncho; x++) {
//                int valor = (matriz.get(x, y) ? 0 : 1) & 0xff;
//                imagen.setRGB(x, y, (valor == 0 ? 0 : 0xFFFFFF));
//            }
//        }
//        //99
//        FileOutputStream qrCode;
//        String nf_qr = df_qr.format(numeroFactura);
//        qrCode = new FileOutputStream(ruta + nf_qr + extension);
//        ImageIO.write(imagen, formato, qrCode);
//        qrCode.close();
//    }
//    private void pdf(IvaVentas iv, List<RenglonFactura> rf) {
//        String code = iv.getCliente().getCodigo();
//        Cliente cli = null;
//        try {
//            cli = new ClienteService().getClienteByCodigo(code);
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
//                File pdf = new PdfBuilder().armarFcA(cli, iv, rf);  //.armarF(cli, iv, rf);
//                DesktopApi.open(pdf);
//            } else {
//                File pdf = new PdfBuilder().armarFcB(cli, iv, rf);  //.armarF(cli, iv, rf);
//                DesktopApi.open(pdf);
//            }
//            JOptionPane.showMessageDialog(this, "PDF GENERADO CORRECTAMENTE");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("err1");
//            JOptionPane.showMessageDialog(this, "ERROR FILE 3536");
//        } catch (DocumentException ex) {
//            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("err2");
//            JOptionPane.showMessageDialog(this, "ERROR DOCUMENT 3539");
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("err3");
//            JOptionPane.showMessageDialog(this, "ERROR EXCEPTION 3544");
//        }
//
//    }
    private void generarFacturaPdf(IvaVentas iv, List<RenglonFactura> rf) {
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
                File pdf = new PDFBuilder2().armarFcA(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            } else {
                File pdf = new PDFBuilder2().armarFcB(cli, iv, rf);  //.armarF(cli, iv, rf);
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

    private void volver() {
        MainFrame ff = new MainFrame();
        ff.setVisible(true);
        this.dispose();
    }

    private int bloquearEquipo() {
        String str0 = UtilFrame.getUsuario(); // + " " + str1;
        int largo = str0.length();
        Integer order_num = Integer.valueOf(str0.substring(0, 3));
        String order_name = str0.substring(6, largo);
        Boolean libres = true;
        try {
            libres = new EquipoBloqueadoService().getEquiposLibres();
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(libres){
            
        }
        return libres;
    }
}
