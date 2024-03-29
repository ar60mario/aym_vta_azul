/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Pedido;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.PedidoService;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class PedidosPendientes extends javax.swing.JFrame {

    public String filtro = "";
    private Date desdeFecha;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DecimalFormat df = new DecimalFormat("#0.00");
    private List<Cliente> clientesActivos = null;
    private Cliente clienteSeleccionado = null;
    private List<Pedido> pedidos = null;
    private List<Pedido> pedidosFiltrado = new ArrayList<>();

    /**
     * Creates new form PedidosPendientes
     */
    public PedidosPendientes() {
        initComponents();
        this.setLocationRelativeTo(null);
        limpiarCampos();
        codigoClienteTxt.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        desdeFechaTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        volverBtn = new javax.swing.JButton();
        facturarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        filtroClientesTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboClientes = new javax.swing.JComboBox();
        pendientesRBtn = new javax.swing.JRadioButton();
        todosRBtn = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        buscarClienteBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        codigoClienteTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setText("Desde Fecha:");

        desdeFechaTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        desdeFechaTxt.setText("FECHA");
        desdeFechaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                desdeFechaTxtKeyPressed(evt);
            }
        });

        jLabel2.setText("dd/MM/aaaa");

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        facturarBtn.setText("Facturar");
        facturarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturarBtnActionPerformed(evt);
            }
        });

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Fecha", "Importe", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tablaPedidos);

        jLabel3.setText("Cliente:");

        filtroClientesTxt.setText("FILTRO CLIENTES");
        filtroClientesTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filtroClientesTxtKeyPressed(evt);
            }
        });

        jLabel4.setText("Indique Nombre y Enter");

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClientesActionPerformed(evt);
            }
        });

        pendientesRBtn.setText("Pendientes de Facturar");
        pendientesRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendientesRBtnActionPerformed(evt);
            }
        });

        todosRBtn.setText("Todos");
        todosRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todosRBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Código:");

        buscarClienteBtn.setText("Buscar");
        buscarClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Código Cliente y Enter");

        codigoClienteTxt.setText("CODIGO CLIENTE");
        codigoClienteTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoClienteTxtKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pendientesRBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(todosRBtn)
                                        .addGap(116, 116, 116)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(filtroClientesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscarClienteBtn)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(desdeFechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(facturarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(volverBtn)
                        .addGap(100, 100, 100)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(desdeFechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(buscarClienteBtn)
                    .addComponent(jLabel6)
                    .addComponent(codigoClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroClientesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pendientesRBtn)
                    .addComponent(todosRBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volverBtn)
                    .addComponent(facturarBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        FacturarFrame ff = new FacturarFrame();
        ff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void facturarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturarBtnActionPerformed
        facturarPedido();
    }//GEN-LAST:event_facturarBtnActionPerformed

    private void filtroClientesTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroClientesTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!filtroClientesTxt.getText().isEmpty()) {
                llenarComboClientes();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                FacturarFrame ff = new FacturarFrame();
                ff.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_filtroClientesTxtKeyPressed

    private void pendientesRBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendientesRBtnActionPerformed
        if (pendientesRBtn.isSelected()) {
            todosRBtn.setSelected(false);
        } else {
            todosRBtn.setSelected(true);
        }
    }//GEN-LAST:event_pendientesRBtnActionPerformed

    private void todosRBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todosRBtnActionPerformed
        if (todosRBtn.isSelected()) {
            pendientesRBtn.setSelected(false);
        } else {
            pendientesRBtn.setSelected(true);
        }
    }//GEN-LAST:event_todosRBtnActionPerformed

    private void comboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClientesActionPerformed
        borrarTabla();
        cargarCodigo();
        buscarCliente();
    }//GEN-LAST:event_comboClientesActionPerformed

    private void buscarClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteBtnActionPerformed
        borrarTabla();
        buscarCliente();
    }//GEN-LAST:event_buscarClienteBtnActionPerformed

    private void codigoClienteTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoClienteTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            borrarTabla();
            buscarCliente();
        }
    }//GEN-LAST:event_codigoClienteTxtKeyPressed

    private void desdeFechaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desdeFechaTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (desdeFechaTxt.getText().isEmpty()) {
                desdeFechaTxt.setText(sdf.format(new Date()));
                desdeFechaTxt.requestFocus(); //mismo campo
            } else {
                int largo = desdeFechaTxt.getText().length();
                if (largo == 2) {
                    Calendar cal = Calendar.getInstance();
                    int mes = cal.get(Calendar.MONTH) + 1;
                    int anio = cal.get(Calendar.YEAR);
                    String an = String.valueOf(anio);
                    String f = "0" + String.valueOf(mes);
                    if (f.length() > 2) {
                        f = f.substring(1, 3);
                    }
                    f = f + "/" + an;
                    desdeFechaTxt.setText(desdeFechaTxt.getText() + "/" + f);
                    codigoClienteTxt.requestFocus(); // request focus prox campo
                } else {
                    if (largo == 5) {
                        Calendar cal = Calendar.getInstance();
                        int anio = cal.get(Calendar.YEAR);
                        String an = "/" + String.valueOf(anio);
                        desdeFechaTxt.setText(desdeFechaTxt.getText() + an);
                        codigoClienteTxt.requestFocus(); // request focus prox campo
                    } else {
                        if (largo == 8) {
                            String x_f = desdeFechaTxt.getText();
                            String x_f1 = x_f.substring(0, 6);
                            String x_f2 = x_f.substring(6, 8);
                            desdeFechaTxt.setText(x_f1 + "20" + x_f2);
                            codigoClienteTxt.requestFocus(); // request focus prox campo
                        } else {
                            if (largo != 10) {
                                JOptionPane.showMessageDialog(this, "Error en fecha");
                                desdeFechaTxt.setText("");
                                desdeFechaTxt.requestFocus(); // request focus mismo campo
                            } else {
                                String veinte = desdeFechaTxt.getText().substring(6, 8);
                                if (!veinte.equals("20")) {
                                    JOptionPane.showMessageDialog(this, "Error en AÑO");
                                    desdeFechaTxt.requestFocus(); // request focus mismo campo
                                } else {
                                    codigoClienteTxt.requestFocus();// request focus prox campo
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_desdeFechaTxtKeyPressed

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
            java.util.logging.Logger.getLogger(PedidosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosPendientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarClienteBtn;
    private javax.swing.JTextField codigoClienteTxt;
    private javax.swing.JComboBox comboClientes;
    private javax.swing.JTextField desdeFechaTxt;
    private javax.swing.JButton facturarBtn;
    private javax.swing.JTextField filtroClientesTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton pendientesRBtn;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JRadioButton todosRBtn;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void facturarPedido() {
        if (comboClientes.getSelectedIndex() > 0) {
            clienteSeleccionado = clientesActivos.get(comboClientes.getSelectedIndex() - 1);
            if (!(tablaPedidos.getSelectedRow() < 0)) {
                Pedido pedidoSeleccionado = pedidosFiltrado.get(tablaPedidos.getSelectedRow());
                System.out.println(pedidoSeleccionado.getId());
                
                FacturaPedidoFrame fpf = new FacturaPedidoFrame(clienteSeleccionado, pedidoSeleccionado);
                fpf.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un Pedido para Facturar");
            }
        } else {
            if (clienteSeleccionado != null) {
                if (!(tablaPedidos.getSelectedRow() < 0)) {
                    Pedido pedidoSeleccionado = pedidos.get(tablaPedidos.getSelectedRow());
                    FacturaPedidoFrame fpf = new FacturaPedidoFrame(clienteSeleccionado, pedidoSeleccionado);
                    fpf.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un Pedido para Facturar");
                }
            }
        }
    }

    private void limpiarCampos() {
        filtroClientesTxt.setText("");
        codigoClienteTxt.setText("");
        comboClientes.removeAllItems();
        comboClientes.addItem("");
        pendientesRBtn.setSelected(true);
        todosRBtn.setSelected(false);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        desdeFechaTxt.setText(sdf.format(calendar.getTime()));
        desdeFecha = calendar.getTime();
        desdeFechaTxt.requestFocus();
    }

    private void llenarComboClientes() {
        filtro = filtroClientesTxt.getText();
        try {
            clientesActivos = new ClienteService().getClientesByFiltro(filtro);
        } catch (Exception ex) {
            Logger.getLogger(PedidosPendientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboClientes.getModel();
        if (!clientesActivos.isEmpty() && clientesActivos != null) {
            for (Cliente cli : clientesActivos) {
                model.addElement(cli.getRazonSocial());
            }
            comboClientes.setModel(model);
        }
    }

    private void cargarPedidos() {
        int i = comboClientes.getSelectedIndex();
        if (i > 0) {
            clienteSeleccionado = clientesActivos.get(i - 1);
            codigoClienteTxt.setText(clienteSeleccionado.getCodigo());
        }
    }

    private void buscarCliente() {
        if (!codigoClienteTxt.getText().isEmpty()) {
            String codi = codigoClienteTxt.getText();
            try {
                clienteSeleccionado = new ClienteService().getClienteByCodigo(codi);
                filtroClientesTxt.setText(clienteSeleccionado.getRazonSocial());
                cargarPedidosByCliente();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado");
                Logger.getLogger(PedidosPendientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cargarPedidosByCliente() {
        try {
            desdeFecha = sdf.parse(desdeFechaTxt.getText());
        } catch (ParseException ex) {
            Logger.getLogger(PedidosPendientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date hastaFecha = new Date();
        try {
            if (pendientesRBtn.isSelected()) {
                //pedidos = new PedidoService().getAllPedidosByCliente(clienteSeleccionado);
                pedidos = new PedidoService().getPedidosDisponiblesByClienteAndFechas(clienteSeleccionado, desdeFecha, hastaFecha);
            } else {
                pedidos = new PedidoService().getAllPedidosByCliente(clienteSeleccionado);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidosPendientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pedidos != null) {
            DefaultTableModel tabla = (DefaultTableModel) tablaPedidos.getModel();
            for (Pedido pe : pedidos) {
                Object[] fila = new Object[4];
                fila[0] = pe.getNumeroPedido();
                fila[1] = sdf.format(pe.getFecha());
                fila[2] = df.format(pe.getTotal());
                if (pe.getAnulado() != null) {
                    if (pe.getAnulado()) {
                        fila[3] = "Anulado";
                    } else {
                        if (pe.getFacturado()) {
                            fila[3] = "Facturado";
                        } else {
                            fila[3] = "Disponible";
                        }
                    }
                } else {
                    if (pe.getFacturado() != null) {
                        if (pe.getFacturado()) {
                            fila[3] = "Facturado";
                        } else {
                            fila[3] = "Disponible";
                        }
                    } else {
                        fila[3] = "Disponible";
                    }
                }
                tabla.addRow(fila);
            }
            tablaPedidos.setModel(tabla);
        } else {
            JOptionPane.showMessageDialog(this, "No hay pedidos disponibles para esta Solicitud");
        }
    }

    private void borrarTabla() {
        DefaultTableModel model = (DefaultTableModel) tablaPedidos.getModel();
        int row = tablaPedidos.getRowCount();
        if (row > 0) {
            for (int n = 0; n < row; n++) {
                model.removeRow(0);
            }
        }
    }

    private void cargarCodigo() {
        //Cliente cliente = clientesActivos.get(comboClientes.getSelectedIndex());
        if (clientesActivos != null && !clientesActivos.isEmpty()) {
            codigoClienteTxt.setText(clientesActivos.get(comboClientes.getSelectedIndex()-1).getCodigo());
        }
    }

}
