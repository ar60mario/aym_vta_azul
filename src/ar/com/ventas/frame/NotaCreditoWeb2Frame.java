/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Configuracion;
import ar.com.ventas.entities.CtaCteCliente;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.Producto;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.entities.RenglonNotaCredito;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.ConfiguracionService;
import ar.com.ventas.services.CtaCteClienteService;
import ar.com.ventas.services.NotaCreditoService;
import ar.com.ventas.services.ProductoService;
import ar.com.ventas.services.RenglonFacturaService;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class NotaCreditoWeb2Frame extends javax.swing.JFrame {

    public List<RenglonNotaCredito> renglonNotaCredito = new ArrayList<RenglonNotaCredito>();
    private String textoNotaCreditoPapel;
    private String fechaNotaCreditoPapel;
    private String clienteNotaCreditoPapel;
    private String codigoClienteNotaCreditoPapel;
    private String direccionNotaCreditoPapel;
    private String cuitNotaCreditoPapel;
    private String condicionVentaNotaCreditoPapel;
    private String vencimientoNotaCreditoPapel;
    private String inscripcionClienteNotaCreditoPapel;
    private String nombresColumnaNotaCreditoPapel;
    private String[] renglones = null;
    private String texto1NotaCreditoPapel;
    private String texto2NotaCreditoPapel;
    private String texto3NotaCreditoPapel;
    private String totalDeudaNotaCreditoPapel;
    private String lineaTotalesNotaCreditoPapel;
    private String totalPagarNotaCreditoPapel;
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
    private Cliente clienteSeleccionado;
    private Producto productoSeleccionado;
    private Float cantidad;
    private Integer categoriaIva = 1;
    private DecimalFormat df = new DecimalFormat("#0.00");
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
    private String vencCae;
    private String numeroFacturaPapel;
    private String letraFacturaPapel;
    private String sucursalFacturaPapel;
    private String numCae;
    private String texto1Cae = "";
    private String texto2Cae = "";
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form NotaCreditoFrame
     *
     * @param cli
     * @param fac
     */
    public NotaCreditoWeb2Frame(Cliente cli, IvaVentas fac) {
        getContentPane().setBackground(new java.awt.Color(255, 250, 205));
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
        borrarSeleccionadoBtn = new javax.swing.JButton();
        leerPrecioBtn = new javax.swing.JButton();
        grabarPrecioBtn = new javax.swing.JButton();
        nuevoPrecioTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("A Y M . FACTURA");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tablaNotaCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cant", "Detalle", "P.Unit", "Gravado", "Impuesto", "IVA", "dto.", "SubTotal", "Sug."
            }
        ));
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

        cantidadAtadosMassalinTxt.setText("Cantidad Massalin");

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

        grabarCantidadBtn.setText("Grabar Cantidad");
        grabarCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarCantidadBtnActionPerformed(evt);
            }
        });

        borrarSeleccionadoBtn.setText("Borrar Seleccionado");
        borrarSeleccionadoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarSeleccionadoBtnActionPerformed(evt);
            }
        });

        leerPrecioBtn.setText("Leer Precio");

        grabarPrecioBtn.setText("Grabar Precio");

        nuevoPrecioTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevoPrecioTxt.setText("NUEVO PRECIO");

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
                                .addComponent(saldoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(texto1PieNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(texto2PieNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                .addComponent(grabarPrecioBtn)))
                                        .addGap(193, 193, 193)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel8)))
                                    .addComponent(borrarSeleccionadoBtn))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel15)
                .addGap(38, 38, 38)
                .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158)
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
                    .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saldoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrarNoSeleccionadoBtn)
                    .addComponent(leerCantidadBtn)
                    .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarCantidadBtn)
                    .addComponent(jLabel8)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrarSeleccionadoBtn)
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
                    .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la NC?", "Atencion", JOptionPane.YES_NO_OPTION);
        // 0 = si; 1 = no
        if (escape == 0) {
            NotaCreditoSelectFrame ncpf = new NotaCreditoSelectFrame();
            ncpf.setVisible(true);
            this.dispose();
        } else {
            //nombreProductoConsultaTxt.setEnabled(false);
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

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
                tablaNotaCredito.setValueAt(df.format(precioFinal), lin, 3);
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

    private void borrarSeleccionadoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarSeleccionadoBtnActionPerformed
        borrarSeleccionado();
    }//GEN-LAST:event_borrarSeleccionadoBtnActionPerformed

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
            java.util.logging.Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new NotaCreditoWeb2Frame(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarNoSeleccionadoBtn;
    private javax.swing.JButton borrarSeleccionadoBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cantidadAtadosMassalinTxt;
    private javax.swing.JTextField cantidadAtadosNoblezaTxt;
    private javax.swing.JTextField cantidadItemsTxt;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JTextField fechaTxt;
    private javax.swing.JButton grabarCantidadBtn;
    private javax.swing.JButton grabarPrecioBtn;
    private javax.swing.JTextField ivaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leerCantidadBtn;
    private javax.swing.JButton leerPrecioBtn;
    private javax.swing.JTextField nuevaCantidadTxt;
    private javax.swing.JTextField nuevoPrecioTxt;
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
    }

    private void bloquearCampos() {
        cancelarBtn.setEnabled(false);
        terminarBtn.setEnabled(false);
        ivaTxt.setEditable(false);
        codigoTxt.setEnabled(true);
        codigoTxt.setEditable(true);
        fechaTxt.setEditable(false);
        razonSocialTxt.setEditable(false);
        totalTxt.setEnabled(false);
        saldoTxt.setEditable(false);
        cantidadItemsTxt.setEnabled(false);
        texto1PieNcTxt.setEnabled(false);
        texto2PieNcTxt.setEnabled(false);
        cantidadAtadosMassalinTxt.setEditable(false);
        cantidadAtadosNoblezaTxt.setEditable(false);
    }

    private void buscar() {
        filtro = "";
        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        fecha = Calendar.getInstance().getTime();
        Cliente cli = new Cliente();
        categoriaIva = 4;
        try {
            clienteNotaCredito = new ClienteService().getClienteByCodigo(codigoTxt.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - cliente");
            Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clienteNotaCredito != null) {
            razonSocialTxt.setText(clienteNotaCredito.getRazonSocial());
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
            if (clienteNotaCredito.getSaldo() != null) {
                saldoTxt.setText(String.valueOf(df.format(clienteNotaCredito.getSaldo())));
                saldoCliente = clienteNotaCredito.getSaldo();
            } else {
                saldoTxt.setText("0.00");
                saldoCliente = 0.0;
            }
            Boolean terminar = false;
            cancelarBtn.setEnabled(true);
            codigoTxt.setEditable(false);
            totalTxt.setEnabled(true);
            totalTxt.setEditable(false);
            agregarProducto();
        } else {
            JOptionPane.showMessageDialog(this, "Error - cliente no existe");
            codigoTxt.requestFocus();
        }
    }

    private void terminarNotaCredito() {
        // nc web inicio
        // presentacion web
        try {
            LibraryLoader.loadJacobLibrary();
            /* Crear objeto WSAA: Web Service de Autenticación y Autorización */
            ActiveXComponent wsaa = new ActiveXComponent("WSAA");
            System.out.println(Dispatch.get(wsaa, "InstallDir").toString()
                    + " "
                    + Dispatch.get(wsaa, "Version").toString()
            );
            /* Solicitar Ticket de Acceso a AFIP (cambiar URL producción) */
            String wsdl = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
            //https://servicios1.afip.gov.ar/wsfev1/service.asmx
            //String userdir = System.getProperty("user.dir");
            String userdir = "c:/certifaym";
            ///AliasVentas_fbd285a15e3f318.crt
            ///certificado_jose_wsaa.crt
            Dispatch.call(wsaa, "Autenticar",
                    new Variant("wsfe"),
                    new Variant(userdir + "/aym2020_4232d12f5f615fc0.crt"),
                    new Variant(userdir + "/clave_privada_20124127581_202010294251.key"),
                    /*
                    new Variant(userdir + "/AliasVentas_fbd285a15e3f318.crt"),
                    new Variant(userdir + "/clave_privada_20124127581_201603255925.key"),
                     */
                    new Variant(wsdl));
            String excepcion = Dispatch.get(wsaa, "Excepcion").toString();
            //System.out.println("Excepcion: " + excepcion);
            String token = Dispatch.get(wsaa, "Token").toString();
            String sign = Dispatch.get(wsaa, "Sign").toString();
            //System.out.println("Token: " + token + "Sign: " + sign);

            /* Instanciar WSFEv1: WebService de Factura Electrónica version 1 */
            ActiveXComponent wsfev1 = new ActiveXComponent("WSFEv1");

            /* Establecer parametros de uso: */
            Dispatch.put(wsfev1, "Cuit", new Variant("20124127581"));
            Dispatch.put(wsfev1, "Token", new Variant(token));
            Dispatch.put(wsfev1, "Sign", new Variant(sign));

            /* Conectar al websrvice (cambiar URL para producción) */
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
            /* Consultar último comprobante autorizado en AFIP */
            String tipo_cbte = "1";
            if (categoriaIva.equals(1)) {
                tipo_cbte = "3"; //Nc A
                letraFacturaPapel = "A";
            } else {
                tipo_cbte = "8"; //Nc B
                letraFacturaPapel = "B";
            }

            String pto_vta = "5"; // Sucursal declarada WS
            sucursalFacturaPapel = "0005";
            Variant ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                    new Variant(tipo_cbte),
                    new Variant(pto_vta));
            System.out.println("Ultimo comprobante: " + ult.toString());
            excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
            System.out.println("Excepcion: " + excepcion);

            /* CAE */
            String fechaWs = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String concepto = "1";// producto 
            System.out.println(clienteNotaCredito.getCuit());
            String cui = clienteNotaCredito.getCuit();
            String cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
            System.out.println(cuit1);
            String tipoD = clienteNotaCredito.getTipo();
            String tipo_doc = tipoD, nro_doc = cuit1; //tipo y numero
            int cbte_nro = Integer.parseInt(ult.toString()) + 1,
                    cbt_desde = cbte_nro,
                    cbt_hasta = cbte_nro;
            numeroFacturaPapel = String.valueOf(cbte_nro);
            int largo = ("00000000" + numeroFacturaPapel).length();
            numeroFacturaPapel = ("00000000" + numeroFacturaPapel).substring(largo - 8, largo);
            System.out.println(df.format(-totalNotaCredito).toString().replaceAll("\\,", "\\."));
            String imp_total = df.format(-totalNotaCredito).toString().replaceAll("\\,", "\\.");//"124.00";
            String imp_tot_conc = "0.00";
            String imp_neto = df.format(-totalGravado).toString().replaceAll("\\,", "\\.");
            String imp_iva = df.format(-totalIva).toString().replaceAll("\\,", "\\.");//"21.00"
            int internos = (int) rint(totalImpuesto * 100);
            System.out.println(internos);
            String imp_trib = "", imp_op_ex = "0.00";
            if (internos < 0) {
                imp_trib = df.format(-totalImpuesto).toString().replaceAll("\\,", "\\.");
                //String.valueOf(totalImpuesto);
            } else {
                imp_trib = "0.00";
            }
            String fecha_cbte = fechaWs, fecha_venc_pago = "";
            /* Fechas período del servicio facturado (solo si concepto> 1) */
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

            /* Agrego los comprobantes asociados: */
//            if (false) { /* solo nc/nd */
//
//                Variant cbte_asoc_tipo = new Variant(""),
//                        cbte_asoc_pto_vta = new Variant(""),
//                        cbte_asoc_nro = new Variant("");
//                Dispatch.call(wsfev1, "AgregarCmpAsoc",
//                        cbte_asoc_tipo, cbte_asoc_pto_vta, cbte_asoc_nro);
//            }

            /* Agrego impuestos varios */
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
            /* Agrego tasas de IVA */
            Variant iva_id = new Variant(5), /* 21% */
                    iva_base_imp = new Variant(df.format(-totalGravado).toString().replaceAll("\\,", "\\.")),
                    iva_importe = new Variant(df.format(-totalIva).toString().replaceAll("\\,", "\\."));
            Dispatch.call(wsfev1, "AgregarIva",
                    iva_id, iva_base_imp, iva_importe);

            /* Habilito reprocesamiento automático (predeterminado): */
            Dispatch.put(wsfev1, "Reprocesar", new Variant(false));

            /* Solicito CAE (llamando al webservice de AFIP): */
            Variant cae = Dispatch.call(wsfev1, "CAESolicitar");
//            Variant vto = Dispatch.call(wsfev1, "Vencimiento");
//            System.out.println(vto);
            /* Mostrar mensajes XML enviados y recibidos (depuración) */
            String requ = Dispatch.get(wsfev1, "XmlRequest").toString();
            String resp = Dispatch.get(wsfev1, "XmlResponse").toString();
            System.out.println("XmlRequest: " + requ);
            System.out.println("XmlResponse: " + resp);

            excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
            System.out.println("Excepcion: " + excepcion);

            String errmsg = Dispatch.get(wsfev1, "ErrMsg").toString();
            System.out.println("ErrMsg: " + errmsg);
            String obs = Dispatch.get(wsfev1, "Obs").toString();
            String vto = Dispatch.get(wsfev1, "Vencimiento").toString();

            System.out.println("Obs: " + obs);
            System.out.println(vto);
            /* datos devueltos */
            System.out.println("CAE: " + cae.toString());
            String resultado = Dispatch.get(wsfev1, "Resultado").toString();
            System.out.println("Resultado: " + resultado);
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
            System.out.println(cadena);
            for (int i = 0; i < 39; i++) {
                if (x == 0) {
                    System.out.println(cadena.substring(i, i + 1).toString());
                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                    suma1 += num;
                    x = 1;
                } else {
                    System.out.println(cadena.substring(i, i + 1).toString());
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
        }
        // fin nc web
        Integer categoriaIva = 0;
        IvaVentas ivaVentas = new IvaVentas();

        categoriaIva = clienteNotaCredito.getCategoriaDeIva();
        saldoCliente = clienteNotaCredito.getSaldo();
        saldoCliente += totalNotaCredito;
        clienteNotaCredito.setSaldo(saldoCliente);
        try {
            new ClienteService().updateCliente(clienteNotaCredito);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Long id = (long) 1;
        try {
            config = new ConfiguracionService().getFacturas(id);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (categoriaIva.equals(1)) {
            letraNotaCredito = "A";
            // es inscriptp
            sucursalNotaCredito = config.getSucursalA();
            numeroNotaCredito = config.getNumeroFacturaA();
            numeroNotaCredito += 0;
            config.setNumeroFacturaA(numeroNotaCredito);
            try {
                new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            letraNotaCredito = "B";
            // el resto de las categorias
            sucursalNotaCredito = config.getSucursalB();
            numeroNotaCredito = config.getNumeroFacturaB();
            numeroNotaCredito += 0;
            config.setNumeroFacturaB(numeroNotaCredito);
            try {
                new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ivaVentas.setCliente(clienteNotaCredito);
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
        ivaVentas.setLetra(letraFacturaPapel);
        ivaVentas.setNumeroSucursal(Integer.valueOf(sucursalFacturaPapel));
        ivaVentas.setNumeroFactura(Integer.valueOf(numeroFacturaPapel));
        for (RenglonNotaCredito reFa : renglonNotaCredito) {
            reFa.setIvaVentas(ivaVentas);
            Integer cod = reFa.getProducto().getCodigo();
            try {
                producto = new ProductoService().getProductoByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            Float stock = (float) 0.0;
            if (producto.getStock() != null) {
                stock = producto.getStock();
            } else {
                stock = (float) 0.0;
            }
            reFa.setProducto(producto);
            stock -= reFa.getCantidad();
            producto.setStock(stock);
            try {
                new ProductoService().updateProducto(producto);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            new NotaCreditoService().saveFactura(ivaVentas, renglonNotaCredito);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        CtaCteCliente ccc = new CtaCteCliente();
        ccc.setCliente(clienteNotaCredito);
        ccc.setNotaCredito(ivaVentas);
        ccc.setFecha(fecha);
        ccc.setDebe(0.0);
        ccc.setHaber(totalNotaCredito);
        ccc.setTipo("NC");
        ccc.setSaldo(saldoCliente);
        try {
            new CtaCteClienteService().saveCtaCteCliente(ccc);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        generarNotaCredito();
        volver();
    }

    private void generarNotaCredito() {
        renglones = new String[maxNro];
        textoNotaCreditoPapel = "Nota de Crédito";
        fechaNotaCreditoPapel = sdf.format(fecha);
        clienteNotaCreditoPapel = razonSocialTxt.getText();
        codigoClienteNotaCreditoPapel = clienteNotaCredito.getDomicilio().getNumero();
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
        vencimientoNotaCreditoPapel = sdf.format(fechaVto);
        String catego = "";
        if (clienteNotaCredito.getCategoriaDeIva().equals(1)) {
            catego = "Responsable Inscripto       ";
        }
        if (clienteNotaCredito.getCategoriaDeIva().equals(2)) {
            catego = "Monotributo                 ";
        }
        if (clienteNotaCredito.getCategoriaDeIva().equals(3)) {
            catego = "Exento                      ";
        }
        if (clienteNotaCredito.getCategoriaDeIva().equals(4)) {
            catego = "Consumidor Final            ";
        }
        inscripcionClienteNotaCreditoPapel = catego;
        if (categoriaIva != 1) {
            //                                    1         2         3         4         5         6         7         8         9        10
            //                           1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            nombresColumnaNotaCreditoPapel = "  IT CANT                    DETALLE                    P.UNIT.    DESC.   IMPORTE       IMP.     TOTAL     SUG";
        } else {
            nombresColumnaNotaCreditoPapel = "  IT CANT                   DETALLE                   P.UNIT.    DESC.   GRAVADO      IVA       IMP.    TOTAL      SUG";
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
                    renglones[r] = renglones[r] + "   " + str0;
                }
                if (largo == 2) {
                    renglones[r] = renglones[r] + "  " + str0;
                }
                if (largo == 3) {
                    renglones[r] = renglones[r] + " " + str0;
                }
                if (largo == 4) {
                    renglones[r] = renglones[r] + str0;
                }
                str0 = tablaNotaCredito.getValueAt(r, 2).toString();
                String espacio = " ";
                largo = str0.length();
                if (largo > 42) {
                    str0 = str0.substring(0, 42);
                    tablaNotaCredito.setValueAt(str0, r, 2);
                } else {
                    for (int l = largo; l < 42; l++) {
                        espacio += " ";
                    }
                }
                renglones[r] = renglones[r] + "  " + tablaNotaCredito.getValueAt(r, 2) + espacio;
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
                    espacio = "     ";
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
        totalDeudaNotaCreditoPapel = espacio + df.format(doble);
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
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                                                            ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesNotaCreditoPapel = espacio + df.format(doble);
        } else {
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
        totalPagarNotaCreditoPapel = espacio + df.format(doble);
// Cantidades atados
        cantidadesNotaCreditoPapel = "                  CANT.ATADOS NOBLEZA: " + String.valueOf(cantidadAtadosNobleza);
        cantidadesNotaCreditoPapel += "                 CANT ATADOS MASSALIN: " + String.valueOf(cantidadAtadosMassalin);
        texto1NotaCreditoPapel = texto1PieNcTxt.getText();
        texto2NotaCreditoPapel = texto2PieNcTxt.getText();
        texto3NotaCreditoPapel = "-";
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
        if (nro > 0) {
            terminarBtn.setEnabled(true);
        } else {
            terminarBtn.setEnabled(false);
        }
        if (nro > 0) {
            cancelarBtn.setEnabled(true);
        }
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

    private void calcularTotales() {
        totalGravado = 0.0;
        totalIva = 0.0;
        totalImpuesto = 0.0;
        totalNotaCredito = 0.0;
        nro = 0;
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        for (RenglonNotaCredito renFa : renglonNotaCredito) {
            totalGravado += renFa.getGravado();
            if (renFa.getProducto().getRubro().getCodigo().equals(1)) {
                cantidadAtadosMassalin += renFa.getCantidad().intValue();
            }
            cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
            if (renFa.getProducto().getRubro().getCodigo().equals(2)) {
                cantidadAtadosNobleza += renFa.getCantidad().intValue();
            }
            cantidadAtadosNoblezaTxt.setText(String.valueOf(cantidadAtadosNobleza));
            totalIva += renFa.getIva();
            totalImpuesto += renFa.getImpuesto();
            nro += 1;
            renFa.setItemNro(nro);
        }
        cantidadItemsTxt.setText(String.valueOf(nro));
        totalNotaCredito = rint((totalGravado + totalImpuesto + totalIva) * 100) / 100;
        totalTxt.setText(String.valueOf(df.format(totalNotaCredito)));
    }

    private void cargarDatos() {
        // CLIENTEsELECCIONADO Y FACTURA
        limpiarCampos();
        codigoTxt.setText(clienteSeleccionado.getCodigo());
        razonSocialTxt.setText(clienteSeleccionado.getRazonSocial());
        saldoTxt.setText(String.valueOf(df.format(clienteSeleccionado.getSaldo())));

        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
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
        cancelarBtn.setEnabled(true);
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
            cancelarBtn.setEnabled(true);
            codigoTxt.setEditable(false);
            totalTxt.setEnabled(true);
            totalTxt.setEditable(false);
            calcularTotales();
            cancelarBtn.setEnabled(true);
            terminarBtn.setEnabled(true);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoWeb2Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void borrarSeleccionado() {
        DefaultTableModel model = (DefaultTableModel) tablaNotaCredito.getModel();
        int cantidadSeleccionadas = tablaNotaCredito.getSelectedRowCount();
        int a[] = tablaNotaCredito.getSelectedRows();
        if (a.length > 0) {
            for (int n = cantidadSeleccionadas - 1; n > -1; n--) {
                model.removeRow(a[n]);
                renglonNotaCredito.remove(a[n]);
            }
        }
        tablaNotaCredito.setModel(model);
        calcularTotales();
    }

    private void borrarNoSeleccionado() {
        DefaultTableModel model = (DefaultTableModel) tablaNotaCredito.getModel();
        int cantidadSeleccionadas = tablaNotaCredito.getSelectedRowCount();
        int cantidadTabla = tablaNotaCredito.getRowCount();
        int a[] = tablaNotaCredito.getSelectedRows();
        if (a.length > 0) {
            for (int n = cantidadTabla - 1; n > -1; n--) {
                if (n != a[cantidadSeleccionadas - 1]) {
                    model.removeRow(n);
                    renglonNotaCredito.remove(n);
                } else {
                    cantidadSeleccionadas -= 1;
                    if (cantidadSeleccionadas < 1) {
                        cantidadSeleccionadas += 1;
                    }
                }
            }
        }
        tablaNotaCredito.setModel(model);
        calcularTotales();
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
            String espacio = "                                                         ";
            g2.drawString(espacio + textoNotaCreditoPapel, 30, row);
            g2.drawString("Distribuidora A & M", 50, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 8));
            row += 12;
            g2.drawString("Av.San Martín 3284", 50, row);
            row += 12;
            g2.drawString("1678 - Caseros Prov. Buenos Aires", 50, row);
            row += 12;
            g2.drawString("Tel: 4759-6677", 50, row);
            row += 12;
            g2.drawString("distaym@yahoo.com.ar", 50, row);
            row = 70;
            //         123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            espacio = "                                                                           ";
            g2.drawString(espacio + letraFacturaPapel + " "
                    + sucursalFacturaPapel + "-"
                    + numeroFacturaPapel, 30, row);
            row += 15;
            g2.drawString(espacio + fechaNotaCreditoPapel, 30, row);
            espacio = "            ";
            row += 30;
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
            //g2.drawString("Ref.Interna:" + letraNotaCredito + " " + sucursalNotaCredito + "-" + numeroNotaCredito, 350, row);
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
            row += 40;
            g2.drawString(lineaTotalesNotaCreditoPapel, 30, row);
            g2.setFont(new Font("Monospaced", Font.BOLD, 11));
            g2.drawString(importeTotalNotaCreditoPapel, 490, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            row += 21;
            g2.drawString("SALDO ANTERIOR: " + totalDeudaNotaCreditoPapel, 403, row);
            row += 10;
            g2.drawString("SALDO TOTAL:    " + totalPagarNotaCreditoPapel, 403, row);
            row += 10;
            espacio = "     ";
            g2.drawString(espacio + texto1NotaCreditoPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto2NotaCreditoPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto3NotaCreditoPapel, 30, row);
            row += 15;
            g2.drawString(cantidadesNotaCreditoPapel, 30, row);
            row += 20;
            g2.drawString(" CAE " + texto1Cae + "  Venc. CAE " + vencCae, 30, row);
            g2.setFont(new Font("PF Interleavev 2 of 5 Text", Font.PLAIN, 18));
            g2.drawString("           " + texto2Cae, 160, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
//            g2.drawString(espacio + texto4FacturaPapel, 30, row);
//            row += 20;

            return PAGE_EXISTS;
        }
    }
}
