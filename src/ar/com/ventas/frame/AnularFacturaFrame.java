/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.IvaVentasService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class AnularFacturaFrame extends javax.swing.JFrame {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form AnularFacturaFrame
     *
     
     */
    public AnularFacturaFrame() {
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        letraTxt = new javax.swing.JTextField();
        sucursalTxt = new javax.swing.JTextField();
        numeroTxt = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nombreClienteTxt = new javax.swing.JTextField();
        importeFacturaTxt = new javax.swing.JTextField();
        anularBtn = new javax.swing.JButton();
        asignarNumeroBtn = new javax.swing.JButton();
        generaFcNcBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        fechaFcNcTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Número Factura:");

        jLabel2.setText("Letra:");

        jLabel3.setText("Sucursal:");

        jLabel4.setText("Número:");

        letraTxt.setText("LETRA");

        sucursalTxt.setText("SUCUR");

        numeroTxt.setText("NUMERO");

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Cliente:");

        jLabel6.setText("Importe Fc:");

        nombreClienteTxt.setText("NOMBRE CLIENTE");

        importeFacturaTxt.setText("IMPORTE");

        anularBtn.setText("Anular");
        anularBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anularBtnActionPerformed(evt);
            }
        });

        asignarNumeroBtn.setText("Asignar Núm.nuevo");
        asignarNumeroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asignarNumeroBtnActionPerformed(evt);
            }
        });

        generaFcNcBtn.setText("Nota de Crédito / Factura");
        generaFcNcBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generaFcNcBtnActionPerformed(evt);
            }
        });

        jLabel7.setText("Fecha Factura/Nota Crédito:");

        fechaFcNcTxt.setText("FECHA FC NC");
        fechaFcNcTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fechaFcNcTxtKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(anularBtn)
                        .addGap(44, 44, 44)
                        .addComponent(asignarNumeroBtn)
                        .addGap(52, 52, 52)
                        .addComponent(generaFcNcBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(97, 97, 97))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(letraTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(sucursalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buscarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fechaFcNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombreClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(importeFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(letraTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sucursalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(nombreClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importeFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fechaFcNcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anularBtn)
                    .addComponent(asignarNumeroBtn)
                    .addComponent(generaFcNcBtn)
                    .addComponent(jButton1))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void anularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anularBtnActionPerformed
        anularFcNc();
    }//GEN-LAST:event_anularBtnActionPerformed

    private void asignarNumeroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignarNumeroBtnActionPerformed
        asignarNuevoNumero();
    }//GEN-LAST:event_asignarNumeroBtnActionPerformed

    private void generaFcNcBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generaFcNcBtnActionPerformed
        generaFcNc();
    }//GEN-LAST:event_generaFcNcBtnActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        buscarFcNc();
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void fechaFcNcTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaFcNcTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (fechaFcNcTxt.getText().isEmpty()) {
                fechaFcNcTxt.setText(sdf.format(new Date()));
                fechaFcNcTxt.requestFocus(); //mismo campo
            } else {
                int largo = fechaFcNcTxt.getText().length();
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
                    fechaFcNcTxt.setText(fechaFcNcTxt.getText() + "/" + f);
                    anularFcNc(); // request focus prox campo
                } else {
                    if (largo == 5) {
                        Calendar cal = Calendar.getInstance();
                        int anio = cal.get(Calendar.YEAR);
                        String an = "/" + String.valueOf(anio);
                        fechaFcNcTxt.setText(fechaFcNcTxt.getText() + an);
                        anularFcNc(); // request focus prox campo
                    } else {
                        if (largo == 8) {
                            String x_f = fechaFcNcTxt.getText();
                            String x_f1 = x_f.substring(0, 6);
                            String x_f2 = x_f.substring(6, 8);
                            fechaFcNcTxt.setText(x_f1 + "20" + x_f2);
                            anularFcNc(); // request focus prox campo
                        } else {
                            if (largo != 10) {
                                JOptionPane.showMessageDialog(this, "Error en fecha");
                                fechaFcNcTxt.setText("");
                                fechaFcNcTxt.requestFocus(); // request focus mismo campo
                            } else {
                                String veinte = fechaFcNcTxt.getText().substring(6, 8);
                                if (!veinte.equals("20")) {
                                    JOptionPane.showMessageDialog(this, "Error en AÑO");
                                    fechaFcNcTxt.requestFocus(); // request focus mismo campo
                                } else {
                                    anularFcNc(); // request focus prox campo
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_fechaFcNcTxtKeyPressed

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
            java.util.logging.Logger.getLogger(AnularFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnularFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnularFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnularFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnularFacturaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anularBtn;
    private javax.swing.JButton asignarNumeroBtn;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField fechaFcNcTxt;
    private javax.swing.JButton generaFcNcBtn;
    private javax.swing.JTextField importeFacturaTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField letraTxt;
    private javax.swing.JTextField nombreClienteTxt;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JTextField sucursalTxt;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        letraTxt.setText("");
        sucursalTxt.setText("");
        numeroTxt.setText("");
        nombreClienteTxt.setText("");
        fechaFcNcTxt.setText("");

    }

    private void anularFcNc() {

    }

    private void asignarNuevoNumero() {

    }

    private void generaFcNc() {

    }

    private void buscarFcNc() {
        String letra = letraTxt.getText();
        Integer sucursal = Integer.valueOf(sucursalTxt.getText());
        Integer numero = Integer.valueOf(numeroTxt.getText());
        IvaVentas ivaVentas = null;
        try {
            ivaVentas = new IvaVentasService().getFacturaByNumero(letra, sucursal, numero);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se encuantran Facturas o Nc con ese número");
            //Logger.getLogger(AnularFacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        nombreClienteTxt.setText(ivaVentas.getCliente().getRazonSocial());
        importeFacturaTxt.setText(String.valueOf(ivaVentas.getTotal()));
    }
}