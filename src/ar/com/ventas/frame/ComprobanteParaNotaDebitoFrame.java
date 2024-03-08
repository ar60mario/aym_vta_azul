/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.services.IvaVentasService;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author argia
 */
public class ComprobanteParaNotaDebitoFrame extends javax.swing.JFrame {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DecimalFormat df = new DecimalFormat("#0.00");
//    private String order_name;
//    private Integer order_num;
    private IvaVentas iv = null;

    /**
     * Creates new form ComprobanteParaNotaDebitoFrame
     *
     */
    public ComprobanteParaNotaDebitoFrame() {
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
        combo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        letraTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        numeroTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        generarNdBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();
        fechaTxt = new javax.swing.JTextField();
        clienteTxt = new javax.swing.JTextField();
        totalTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("TIPO COMPROBANTE:");

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        jLabel2.setText("LETRA:");

        letraTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        letraTxt.setText("LET");
        letraTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                letraTxtKeyPressed(evt);
            }
        });

        jLabel3.setText("NÚMERO:");

        numeroTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numeroTxt.setText("NUM");
        numeroTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numeroTxtKeyPressed(evt);
            }
        });

        jLabel4.setText("FECHA:");

        jLabel5.setText("CLIENTE:");

        jLabel6.setText("TOTAL:");

        generarNdBtn.setText("GENERAR ND");
        generarNdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarNdBtnActionPerformed(evt);
            }
        });

        volverBtn.setText("VOLVER");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        fechaTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaTxt.setText("FECHA");

        clienteTxt.setText("CLIENTE");

        totalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTxt.setText("TOTAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generarNdBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(volverBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(letraTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 174, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clienteTxt)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(letraTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(clienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generarNdBtn)
                    .addComponent(volverBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void generarNdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarNdBtnActionPerformed
        if (iv.getCodigoTipoDoc().equals(3)) {
            NotaDebitoWebDeNcFrame ndwdnf = new NotaDebitoWebDeNcFrame(iv);;
            ndwdnf.setVisible(true);
            this.dispose();
        } else {
            if (iv.getCodigoTipoDoc().equals(8)) {
                NotaDebitoWebDeNcFrame ndwdnf = new NotaDebitoWebDeNcFrame(iv);;
                ndwdnf.setVisible(true);
                this.dispose();
            } else {
                String letra1 = letraTxt.getText();
                Integer numeroComprobante = Integer.valueOf(numeroTxt.getText());
                Integer tipo = combo.getSelectedIndex();
                Integer tc = 3;
                /*
                1 ("NC");
                2 ("FC");
                3 ("ND");
                 */
                if (letra1.equals("A")) {
                    switch (tipo) {
                        case 1:
                            tc = 3;
                            break;
                        case 2:
                            tc = 1;
                            break;
                        case 3:
                            tc = 2;
                            break;
                        default:
                            tc = 3;
                    }
                }
                if (letra1.equals("B")) {
                    switch (tipo) {
                        case 1:
                            tc = 8;
                            break;
                        case 2:
                            tc = 6;
                            break;
                        case 3:
                            tc = 7;
                            break;
                        default:
                            tc = 8;
                    }
                }
                NotaDebitoWebFrame ndwf = new NotaDebitoWebFrame(
                        tc, letra1, numeroComprobante);
                ndwf.setVisible(true);
                this.dispose();
            }
        }

    }//GEN-LAST:event_generarNdBtnActionPerformed

    private void letraTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_letraTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (!letraTxt.getText().isEmpty()) {
                numeroTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_letraTxtKeyPressed

    private void numeroTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (!numeroTxt.getText().isEmpty()) {
                buscarComprobante();
            }
        }
    }//GEN-LAST:event_numeroTxtKeyPressed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        int row = combo.getSelectedIndex();
        if (row > 0) {
            letraTxt.requestFocus();
        }
    }//GEN-LAST:event_comboActionPerformed

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
            java.util.logging.Logger.getLogger(ComprobanteParaNotaDebitoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComprobanteParaNotaDebitoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComprobanteParaNotaDebitoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComprobanteParaNotaDebitoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComprobanteParaNotaDebitoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clienteTxt;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JTextField fechaTxt;
    private javax.swing.JButton generarNdBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField letraTxt;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        combo.removeAllItems();
        combo.addItem("");
        combo.addItem("NC");
//        combo.addItem("FC");
//        combo.addItem("ND");
        letraTxt.setText("");
        numeroTxt.setText("");
        clienteTxt.setText("");
        fechaTxt.setText("");
        totalTxt.setText("");
    }

    private void volver() {
        NotaDebitoWebSelectFrame ndwsf = new NotaDebitoWebSelectFrame();
        ndwsf.setVisible(true);
        this.dispose();
    }

    private void buscarComprobante() {
        Integer numero = Integer.valueOf(numeroTxt.getText());
        String letra = letraTxt.getText();
        Integer tipo = combo.getSelectedIndex();
        Integer tc = 3;
        /*
                1 ("NC");
                2 ("FC");
                3 ("ND");
         */
        if (letra.equals("A")) {
            switch (tipo) {
                case 1:
                    tc = 3;
                    break;
                case 2:
                    tc = 1;
                    break;
                case 3:
                    tc = 2;
                    break;
                default:
                    tc = 3;
            }
        }
        if (letra.equals("B")) {
            switch (tipo) {
                case 1:
                    tc = 8;
                    break;
                case 2:
                    tc = 6;
                    break;
                case 3:
                    tc = 7;
                    break;
                default:
                    tc = 8;
            }
        }
        if (!letraTxt.getText().isEmpty()) {
            letra = letraTxt.getText();
        } else {
            JOptionPane.showMessageDialog(this, "DEBE INGRESAR LETRA DE COMPROBANTE");
            return;
        }
        if (!letra.equals("A")) {
            if (!letra.equals("B")) {
                JOptionPane.showMessageDialog(this, "LETRA DEBE SER A o B");
                return;
            }
//            JOptionPane.showMessageDialog(this, "LETRA DEBE SER A o B");
//            return;
        }
//        else {
//            
//        }
        iv = null;
        try {
            iv = new IvaVentasService().getByLetraNumeroAndTipo(letra, numero, tc);
        } catch (Exception ex) {
            // Logger.getLogger(ComprobanteParaNotaDebitoFrame.class.getName()).log(Level.SEVERE, null, ex);
            clienteTxt.setText("COMPROBANTE NO ENCONTRADO");
            return;
        }
        if (iv != null) {
            fechaTxt.setText(sdf.format(iv.getFecha()));
            clienteTxt.setText(iv.getCliente().getRazonSocial());
            totalTxt.setText(df.format(iv.getTotal()));
        }
    }
}