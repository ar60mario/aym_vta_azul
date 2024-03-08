/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.ClienteTraba;
import ar.com.ventas.entities.Pedido;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.ClienteTrabaService;
import ar.com.ventas.services.PedidoService;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class PedidosPendientesFrame extends javax.swing.JFrame {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DecimalFormat df = new DecimalFormat("#0.00");
    private List<Pedido> pedidos = null;
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form PedidosPendientesFrame
     */
    public PedidosPendientesFrame() {
        initComponents();
//        this.order_name = order_name;
//        this.order_num = order_num;
        llenarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        volverBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        facturaWebBtn = new javax.swing.JButton();
        eliminarPedidoBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PEDIDOS PENDIENTES DE FACTURAR");

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Número", "Código", "Cliente", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPedidos);
        if (tablaPedidos.getColumnModel().getColumnCount() > 0) {
            tablaPedidos.getColumnModel().getColumn(3).setPreferredWidth(250);
        }

        facturaWebBtn.setText("Factura Web");
        facturaWebBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturaWebBtnActionPerformed(evt);
            }
        });

        eliminarPedidoBtn.setText("Eliminar");
        eliminarPedidoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPedidoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eliminarPedidoBtn)
                        .addGap(18, 18, 18)
                        .addComponent(facturaWebBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(volverBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volverBtn)
                    .addComponent(facturaWebBtn)
                    .addComponent(eliminarPedidoBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void facturaWebBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturaWebBtnActionPerformed
        int row = tablaPedidos.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido");
        } else {
            Pedido pedido = pedidos.get(row);
            Cliente cliente = pedido.getCliente();
            if (cliente.getVendedor() == null) {
                JOptionPane.showMessageDialog(this, "Cliente sin Vendedor Asignado");
                volver();
            } else {
                ClienteTraba ct = null;
                try {
                    ct = new ClienteTrabaService().getClienteByCodigo(cliente.getCodigo());
                } catch (Exception ex) {
                    Logger.getLogger(PedidosPendientesFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ct.getTraba1() != null) {
                    if (ct.getTraba1()) {
                        JOptionPane.showMessageDialog(this, "Cliente bloqueado para esta Operación.");
                        return;
                    }
                }
                if (cliente.getCuit().subSequence(0, 6).equals("11-111")) {
                    JOptionPane.showMessageDialog(this, "Debe verificar la CUIT "
                            + "del Cliente: \n" + cliente.getRazonSocial()
                            + "\nCodigo: " + cliente.getCodigo());
                    volver();
                } else {
                    FacturaWebPedidoFrame fwpf = new FacturaWebPedidoFrame(cliente, pedido);
                    fwpf.setVisible(true);
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_facturaWebBtnActionPerformed

    private void eliminarPedidoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPedidoBtnActionPerformed
        int row = tablaPedidos.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Pedido para eliminar");
        } else {
            Pedido pedido = pedidos.get(row);
            int escape = JOptionPane.showConfirmDialog(null,
                    "Esta seguro de eliminar el Pedido seleccionado?",
                    "Atencion",
                    JOptionPane.YES_NO_OPTION);
            if (escape == 0) {
                pedido.setFacturado(true);
                try {
                    new PedidoService().updatePedido(pedido);
                    borrarTabla();
                    llenarTabla();
                } catch (Exception ex) {
                    Logger.getLogger(PedidosPendientesFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

// 
    }//GEN-LAST:event_eliminarPedidoBtnActionPerformed

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
            java.util.logging.Logger.getLogger(PedidosPendientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosPendientesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eliminarPedidoBtn;
    private javax.swing.JButton facturaWebBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void llenarTabla() {
        try {
            pedidos = new PedidoService().getAllPedidosDisponibles();
        } catch (Exception ex) {
            Logger.getLogger(PedidosPendientesFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pedidos != null && !pedidos.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tablaPedidos.getModel();
            for (Pedido pe : pedidos) {
                Object ob[] = new Object[5];
                ob[0] = sdf.format(pe.getFecha());
                ob[1] = pe.getNumeroPedido();
                ob[2] = pe.getCliente().getCodigo();
                ob[3] = pe.getCliente().getRazonSocial();
                ob[4] = df.format(pe.getTotal());
                model.addRow(ob);
            }
            tablaPedidos.setModel(model);
        }
    }

    private void borrarTabla() {
        int rows = tablaPedidos.getRowCount();
        if (rows > 0) {
            DefaultTableModel tbl = (DefaultTableModel) tablaPedidos.getModel();
            pedidos = null;
            for (int i = 0; i < rows; i++) {
                tbl.removeRow(0);
            }
            tablaPedidos.setModel(tbl);
        }
    }

    private void volver() {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        this.dispose();
    }
}