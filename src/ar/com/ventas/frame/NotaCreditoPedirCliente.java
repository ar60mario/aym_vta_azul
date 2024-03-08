/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.ClienteTraba;
import ar.com.ventas.entities.CustomerTraba;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.ClienteTrabaService;
import ar.com.ventas.services.CustomerTrabaService;
import ar.com.ventas.services.IvaVentasService;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class NotaCreditoPedirCliente extends javax.swing.JFrame {

    private Cliente cliente = null;
    private List<Cliente> clientes = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DecimalFormat df = new DecimalFormat("#0.00");
    private Date fecha = new Date();
//    private Calendar calendar;
    private List<IvaVentas> fact = null;
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form NotaCreditoPedirCliente
     */
    public NotaCreditoPedirCliente() {
        initComponents();
//        this.order_name = order_name;
//        this.order_num = order_num;
        limpiarCampos();
        borrarTabla();
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

        codigoClienteTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buscarPorCodigoBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nombreClienteTxt = new javax.swing.JTextField();
        buscarPorNombreBtn = new javax.swing.JButton();
        comboClientes = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        desdeFechaTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFacturas = new javax.swing.JTable();
        seleccionarBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CLIENTE DE NOTA DE CREDITO");

        codigoClienteTxt.setText("CODIGO CLIENTE");
        codigoClienteTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoClienteTxtKeyPressed(evt);
            }
        });

        jLabel1.setText("Código Cliente:");

        buscarPorCodigoBtn.setText("Buscar");
        buscarPorCodigoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPorCodigoBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre Cliente:");

        nombreClienteTxt.setText("NOMBRE CLIENTE");
        nombreClienteTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreClienteTxtKeyPressed(evt);
            }
        });

        buscarPorNombreBtn.setText("Buscar");
        buscarPorNombreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPorNombreBtnActionPerformed(evt);
            }
        });

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

        jLabel3.setText("Seleccione:");

        jLabel4.setText("Desde Fecha:");

        desdeFechaTxt.setText("FECHA DESDE");

        tablaFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Número", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaFacturas);

        seleccionarBtn.setText("Generar");
        seleccionarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarBtnActionPerformed(evt);
            }
        });

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(seleccionarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(volverBtn)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscarPorCodigoBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(desdeFechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 248, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombreClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscarPorNombreBtn)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(comboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(desdeFechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(buscarPorCodigoBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarPorNombreBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seleccionarBtn)
                    .addComponent(volverBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seleccionarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarBtnActionPerformed
        if (tablaFacturas.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una factura");
            return;
        } else {
            seleccionar();
        }
    }//GEN-LAST:event_seleccionarBtnActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        NotaCreditoSelectFrame ncsf = new NotaCreditoSelectFrame();
        ncsf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void buscarPorNombreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPorNombreBtnActionPerformed
        if (!nombreClienteTxt.getText().isEmpty()) {
            llenarComboClientes();
        }
    }//GEN-LAST:event_buscarPorNombreBtnActionPerformed

    private void buscarPorCodigoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPorCodigoBtnActionPerformed
        buscarClientePorCodigo();
    }//GEN-LAST:event_buscarPorCodigoBtnActionPerformed

    private void codigoClienteTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoClienteTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoClienteTxt.getText().isEmpty()) {
                buscarClientePorCodigo();
            }
        }
    }//GEN-LAST:event_codigoClienteTxtKeyPressed

    private void comboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClientesActionPerformed
        if (evt.getModifiers() == 16) {
            int row = comboClientes.getSelectedIndex();
            if (row > 0) {
                String cod = clientes.get(row - 1).getCodigo();
                Cliente cli = null;
                try {
                    cli = new ClienteService().getClienteByCodigo(cod);
                } catch (Exception ex) {
                    Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (cli != null) {
                    codigoClienteTxt.setText(cli.getCodigo());
                    String c = cli.getCodigo();
                    ClienteTraba ct = null;
                    try {
                        ct = new ClienteTrabaService().getClienteByCodigo(c);
                    } catch (Exception ex) {
                        Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (ct.getTraba1() != null) {
                        if (ct.getTraba1()) {
                            JOptionPane.showMessageDialog(this, "Cliente bloqueado para esta Operación");
                            return;
                        }
                    }
                    CustomerTraba cuTr = null;
                    try {
                        cuTr = new CustomerTrabaService().getClienteByCodigo(c);
                    } catch (Exception ex) {
                        Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        cuTr = new CustomerTrabaService().getClienteByCodigo(c);
                    } catch (Exception ex) {
                        Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (cuTr.getTraba2() != null) {
                        if (cuTr.getTraba2()) {
                            JOptionPane.showMessageDialog(this, "Cliente bloqueado para esta Operación");
                            return;
                        }
                    }
                    buscarClientePorCodigo();
                }
            }
        }
    }//GEN-LAST:event_comboClientesActionPerformed

    private void nombreClienteTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreClienteTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (!nombreClienteTxt.getText().isEmpty()) {
                llenarComboClientes();
            }
        }
    }//GEN-LAST:event_nombreClienteTxtKeyPressed

    private void comboClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboClientesKeyPressed
        if (evt.getKeyCode() == 10) {
            int row = comboClientes.getSelectedIndex();
            if (row > 0) {
                cliente = clientes.get(row - 1);
                codigoClienteTxt.setText(cliente.getCodigo());
                buscarClientePorCodigo();
            }
        }
    }//GEN-LAST:event_comboClientesKeyPressed

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
            java.util.logging.Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotaCreditoPedirCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarPorCodigoBtn;
    private javax.swing.JButton buscarPorNombreBtn;
    private javax.swing.JTextField codigoClienteTxt;
    private javax.swing.JComboBox comboClientes;
    private javax.swing.JTextField desdeFechaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreClienteTxt;
    private javax.swing.JButton seleccionarBtn;
    private javax.swing.JTable tablaFacturas;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        codigoClienteTxt.setText("");
        nombreClienteTxt.setText("");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        desdeFechaTxt.setText(sdf.format(calendar.getTime()));
        comboClientes.removeAllItems();
        comboClientes.addItem("");
    }

    private void borrarTabla() {
        int lineas = tablaFacturas.getRowCount();
        DefaultTableModel model1 = (DefaultTableModel) tablaFacturas.getModel();
        if (lineas > 0) {
            for (int n = 0; n < lineas; n++) {
                model1.removeRow(0);
            }
            tablaFacturas.setModel(model1);
        }
    }

    private void llenarComboClientes() {
        String filtro = null;
        comboClientes.removeAllItems();
        comboClientes.addItem("");
        clientes = null;
        if (!nombreClienteTxt.getText().isEmpty()) {
            filtro = nombreClienteTxt.getText();
            try {
                clientes = new ClienteService().getClientesByFiltro(filtro);
            } catch (Exception ex) {
                Logger.getLogger(VerCuentaCorrienteClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Cliente cli : clientes) {
                comboClientes.addItem(cli.getRazonSocial());
            }
            comboClientes.addFocusListener(null);
            comboClientes.showPopup();
            comboClientes.requestFocus();
        }
    }

    private void seleccionar() {
        int i = tablaFacturas.getSelectedRow();
        IvaVentas iv = fact.get(i);
        String cod = cliente.getCodigo();
        Cliente cli = null;
        ClienteTraba ct = null;
        CustomerTraba cuTr = null;
        try {
            cli = new ClienteService().getClienteByCodigo(cod);
            ct = new ClienteTrabaService().getClienteByCodigo(cod);
            cuTr = new CustomerTrabaService().getClienteByCodigo(cod);
        } catch (Exception ex) {
            Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        if (ct.getTraba1() != null) {
            if (ct.getTraba1()) {
                JOptionPane.showMessageDialog(this, "Cliente bloqueado para este Proceso");
                return;
            }
        }
        if (cuTr.getTraba2() != null) {
            if (cuTr.getTraba2()) {
                JOptionPane.showMessageDialog(this, "Cliente bloqueado para este Proceso");
                return;
            }
        }
        Double saldoCliente;
        if (cli.getSaldo() != null) {
            saldoCliente = cli.getSaldo();
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
        NotaCreditoWebDeFacturaFrame ncf = new NotaCreditoWebDeFacturaFrame(cliente, iv);
        ncf.setVisible(true);
        this.dispose();
    }

    private void buscarClientePorCodigo() {
        if (!codigoClienteTxt.getText().isEmpty()) {
            String codigo = codigoClienteTxt.getText();
            cliente = null;
            ClienteTraba ct = null;
            CustomerTraba cuTr = null;
            try {
                cliente = new ClienteService().getClienteByCodigo(codigo);
                ct = new ClienteTrabaService().getClienteByCodigo(codigo);
                cuTr = new CustomerTrabaService().getClienteByCodigo(codigo);
            } catch (Exception ex) {
                Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            if (cliente != null) {
                nombreClienteTxt.setText(cliente.getRazonSocial());
                if (ct.getTraba1() != null) {
                    if (ct.getTraba1()) {
                        JOptionPane.showMessageDialog(this, "Cliente bloqueado para este Proceso");
                        return;
                    }
                    if (cuTr.getTraba2() != null) {
                        if (cuTr.getTraba2()) {
                            JOptionPane.showMessageDialog(this, "Cliente bloqueado para este Proceso");
                            return;
                        }
                    }
                }
                List<IvaVentas> facturas = null;
                fact = new ArrayList<IvaVentas>();
                Date fechaA = new Date();
                fecha = new Date();
                try {
                    fechaA = sdf.parse(desdeFechaTxt.getText());
                } catch (ParseException ex) {
                    Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
//                JOptionPane.showMessageDialog(this, fechaA);
//                JOptionPane.showMessageDialog(this, fecha);
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(fechaA);
//                calendar.add(Calendar.DATE, -30);
//                fechaA = calendar.getTime();
                try {
                    facturas = new IvaVentasService().getAllIvaVentasByCodigoYFecha(cliente, fechaA, fecha);
                } catch (Exception ex) {
                    Logger.getLogger(NotaCreditoPedirCliente.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
//                JOptionPane.showMessageDialog(this, facturas);
                DefaultTableModel model = (DefaultTableModel) tablaFacturas.getModel();
                for (IvaVentas movimientos : facturas) {
                    Object lin[] = new Object[3];
                    if (movimientos.getTotal() > 0.0) {
                        fact.add(movimientos);
                        lin[0] = movimientos.getFecha();
                        lin[1] = movimientos.getNumeroFactura();
                        lin[2] = df.format(movimientos.getTotal());
                        model.addRow(lin);
                    }
                }
                tablaFacturas.setModel(model);
            }
        }
    }
}