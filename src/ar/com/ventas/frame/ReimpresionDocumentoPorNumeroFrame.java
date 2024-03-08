/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.main.MainFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class ReimpresionDocumentoPorNumeroFrame extends javax.swing.JFrame {

//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form ReimpresionDocumentoPorNumeroFrame
     */
    public ReimpresionDocumentoPorNumeroFrame() {
        initComponents();
//        this.order_name = order_name;
//        this.order_num = order_num;
        limpiarCampos();

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        facturaBtn = new javax.swing.JRadioButton();
        pedidoBtn = new javax.swing.JRadioButton();
        notaCreditoBtn = new javax.swing.JRadioButton();
        letraTxt = new javax.swing.JTextField();
        sucursalTxt = new javax.swing.JTextField();
        numeroTxt = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        confirmarBtn = new javax.swing.JButton();
        nombreClienteTxt = new javax.swing.JTextField();
        codigoClienteTxt = new javax.swing.JTextField();
        fechaDocumentoTxt = new javax.swing.JTextField();
        importeDocumentoTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setText("Letra:");

        jLabel2.setText("Sucursal:");

        jLabel3.setText("Número:");

        facturaBtn.setText("Factura");
        facturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturaBtnActionPerformed(evt);
            }
        });

        pedidoBtn.setText("Pedido");
        pedidoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedidoBtnActionPerformed(evt);
            }
        });

        notaCreditoBtn.setText("Nota de Crédito");
        notaCreditoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaCreditoBtnActionPerformed(evt);
            }
        });

        letraTxt.setText("LETRA");

        sucursalTxt.setText("SUCURSAL");

        numeroTxt.setText("NUMERO");

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Cliente:");

        jLabel5.setText("Fecha:");

        jLabel6.setText("Importe:");

        confirmarBtn.setText("Confirmar");

        nombreClienteTxt.setText("NOMBRE CLIENTE");

        codigoClienteTxt.setText("COD CLIENTE");

        fechaDocumentoTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaDocumentoTxt.setText("FECHA DOCUMENTO");

        importeDocumentoTxt.setText("IMPORTE DOCUMENTO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pedidoBtn)
                        .addGap(18, 18, 18)
                        .addComponent(facturaBtn)
                        .addGap(18, 18, 18)
                        .addComponent(notaCreditoBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(letraTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sucursalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(volverBtn)
                                    .addComponent(buscarBtn)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombreClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(codigoClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(fechaDocumentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(importeDocumentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(confirmarBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(facturaBtn)
                        .addComponent(pedidoBtn)
                        .addComponent(notaCreditoBtn))
                    .addComponent(volverBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(letraTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sucursalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nombreClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fechaDocumentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(confirmarBtn)
                    .addComponent(importeDocumentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pedidoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedidoBtnActionPerformed
        pedidoBtn.setSelected(true);
        facturaBtn.setSelected(false);
        notaCreditoBtn.setSelected(false);
        sucursalTxt.setText("");
        sucursalTxt.setEnabled(false);
    }//GEN-LAST:event_pedidoBtnActionPerformed

    private void facturaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturaBtnActionPerformed
        pedidoBtn.setSelected(false);
        facturaBtn.setSelected(true);
        notaCreditoBtn.setSelected(false);
        sucursalTxt.setEnabled(true);
    }//GEN-LAST:event_facturaBtnActionPerformed

    private void notaCreditoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaCreditoBtnActionPerformed
        pedidoBtn.setSelected(false);
        facturaBtn.setSelected(false);
        notaCreditoBtn.setSelected(true);
        sucursalTxt.setEnabled(true);
    }//GEN-LAST:event_notaCreditoBtnActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        if (pedidoBtn.isSelected()) {
            if (letraTxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe Indicar Letra de Pedido");
                letraTxt.requestFocus();
            } else {
                if (numeroTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe Indicar Número de Pedido");
                    numeroTxt.requestFocus();
                } else {
                    buscarPedido();
                }
            }
        } else {
            if (facturaBtn.isSelected()) {
                if (letraTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe Indicar Letra de Factura");
                    letraTxt.requestFocus();
                } else {
                    if (sucursalTxt.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Debe Indicar Sucursal de Factura");
                        sucursalTxt.requestFocus();
                    } else {
                        if (numeroTxt.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Debe Indicar Número de Factura");
                            numeroTxt.requestFocus();
                        } else {
                            buscarFactura();
                        }
                    }
                }
            } else {
                if (letraTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe Indicar Letra de Nota de Crédito");
                    letraTxt.requestFocus();
                } else {
                    if (sucursalTxt.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Debe Indicar Sucursal de Nota de Crédito");
                        sucursalTxt.requestFocus();
                    } else {
                        if (numeroTxt.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Debe Indicar Número de Nota de Crédito");
                            numeroTxt.requestFocus();
                        } else {
                            buscarNotaCredito();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_buscarBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ReimpresionDocumentoPorNumeroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReimpresionDocumentoPorNumeroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReimpresionDocumentoPorNumeroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReimpresionDocumentoPorNumeroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReimpresionDocumentoPorNumeroFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField codigoClienteTxt;
    private javax.swing.JButton confirmarBtn;
    private javax.swing.JRadioButton facturaBtn;
    private javax.swing.JTextField fechaDocumentoTxt;
    private javax.swing.JTextField importeDocumentoTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField letraTxt;
    private javax.swing.JTextField nombreClienteTxt;
    private javax.swing.JRadioButton notaCreditoBtn;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JRadioButton pedidoBtn;
    private javax.swing.JTextField sucursalTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        facturaBtn.setSelected(true);
        letraTxt.setText("");
        sucursalTxt.setText("");
        numeroTxt.setText("");
        nombreClienteTxt.setText("");
        codigoClienteTxt.setText("");
        fechaDocumentoTxt.setText("");
        importeDocumentoTxt.setText("");
        confirmarBtn.setEnabled(false);
    }

    private void buscarNotaCredito() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void buscarFactura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void buscarPedido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void volver() {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        this.dispose();
    }
}
