/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.ClienteIdentificado;
import ar.com.ventas.entities.ClienteTraba;
import ar.com.ventas.entities.CustomerTraba;
import ar.com.ventas.entities.Domicilio;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.ClienteTrabaService;
import ar.com.ventas.services.CustomerTrabaService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author argia
 */
public class IngresoDniFrame extends javax.swing.JFrame {

    private Cliente cli = new Cliente();
//    private ClienteIdentificado ci;

    /**
     * Creates new form IngresoDniFrame
     *
     *
     */
    public IngresoDniFrame() {
        initComponents();
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
        dniRb = new javax.swing.JRadioButton();
        cuilRb = new javax.swing.JRadioButton();
        cuitRb = new javax.swing.JRadioButton();
        dniTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombreTxt = new javax.swing.JTextField();
        calleTxt = new javax.swing.JTextField();
        numeroTxt = new javax.swing.JTextField();
        localidadTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        provinciaTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        codigoPostalTxt = new javax.swing.JTextField();
        codigoTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("INGRESAR IDENTIFICACION");

        jLabel1.setText("NUMERO DOCUMENTO:");

        dniRb.setText("DNI");
        dniRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dniRbActionPerformed(evt);
            }
        });

        cuilRb.setText("CUIL");
        cuilRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuilRbActionPerformed(evt);
            }
        });

        cuitRb.setText("CUIT");
        cuitRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuitRbActionPerformed(evt);
            }
        });

        dniTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dniTxt.setText("DNI");
        dniTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dniTxtKeyPressed(evt);
            }
        });

        guardarBtn.setText("GUARDAR");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("SALIR");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("APELL.NOMBRE:");

        jLabel3.setText("CALLE:");

        jLabel4.setText("NÚMERO:");

        jLabel5.setText("LOCALIDAD:");

        nombreTxt.setText("APELLIDO NOMBRE");
        nombreTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreTxtKeyPressed(evt);
            }
        });

        calleTxt.setText("CALLE");
        calleTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                calleTxtKeyPressed(evt);
            }
        });

        numeroTxt.setText("NUMERO");
        numeroTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numeroTxtKeyPressed(evt);
            }
        });

        localidadTxt.setText("LOCALIDAD");
        localidadTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                localidadTxtKeyPressed(evt);
            }
        });

        jLabel6.setText("PROVINCIA:");

        provinciaTxt.setText("PROVINCIA");
        provinciaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                provinciaTxtKeyPressed(evt);
            }
        });

        jLabel7.setText("CÓDIGO POSTAL:");

        codigoPostalTxt.setText("COD.POSTAL");
        codigoPostalTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoPostalTxtKeyPressed(evt);
            }
        });

        codigoTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codigoTxt.setText("CODIGO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(guardarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dniRb)
                                .addGap(18, 18, 18)
                                .addComponent(cuilRb)
                                .addGap(18, 18, 18)
                                .addComponent(cuitRb))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(calleTxt)
                                    .addComponent(nombreTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(codigoTxt))
                                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(provinciaTxt)
                                    .addComponent(localidadTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                                    .addComponent(codigoPostalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(calleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(codigoPostalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(localidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(provinciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dniRb)
                    .addComponent(cuilRb)
                    .addComponent(cuitRb))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn)
                    .addComponent(cancelarBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dniRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniRbActionPerformed
        dniRb.setSelected(true);
        cuilRb.setSelected(false);
        cuitRb.setSelected(false);
    }//GEN-LAST:event_dniRbActionPerformed

    private void cuilRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuilRbActionPerformed
        dniRb.setSelected(false);
        cuilRb.setSelected(true);
        cuitRb.setSelected(false);
    }//GEN-LAST:event_cuilRbActionPerformed

    private void cuitRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuitRbActionPerformed
        dniRb.setSelected(false);
        cuilRb.setSelected(false);
        cuitRb.setSelected(true);
    }//GEN-LAST:event_cuitRbActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        if (validar()) {
            guardar();
        }
//        this.dispose();
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void dniTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dniTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (!dniTxt.getText().isEmpty()) {
                nombreTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_dniTxtKeyPressed

    private void nombreTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (!nombreTxt.getText().isEmpty()) {
                calleTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_nombreTxtKeyPressed

    private void calleTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_calleTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (!calleTxt.getText().isEmpty()) {
                numeroTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_calleTxtKeyPressed

    private void numeroTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (!numeroTxt.getText().isEmpty()) {
                codigoPostalTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_numeroTxtKeyPressed

    private void codigoPostalTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoPostalTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (!codigoPostalTxt.getText().isEmpty()) {
                localidadTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_codigoPostalTxtKeyPressed

    private void localidadTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_localidadTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (!localidadTxt.getText().isEmpty()) {
                provinciaTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_localidadTxtKeyPressed

    private void provinciaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_provinciaTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            dniRb.requestFocus();
        }
    }//GEN-LAST:event_provinciaTxtKeyPressed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
//        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

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
            java.util.logging.Logger.getLogger(IngresoDniFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoDniFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoDniFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoDniFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoDniFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField calleTxt;
    public javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField codigoPostalTxt;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JRadioButton cuilRb;
    private javax.swing.JRadioButton cuitRb;
    private javax.swing.JRadioButton dniRb;
    private javax.swing.JTextField dniTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField localidadTxt;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JTextField provinciaTxt;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        dniTxt.setText("");
        dniRb.setSelected(true);
        cuilRb.setSelected(false);
        cuitRb.setSelected(false);
        calleTxt.setText("");
        numeroTxt.setText("");
        codigoPostalTxt.setText("");
        localidadTxt.setText("");
        provinciaTxt.setText("");
        nombreTxt.setText("");
    }

    public String getData() {
        Cliente cliente;
        String codigo = codigoTxt.getText();
        return codigo;
    }

    private boolean validar() {
        Boolean volver = true;
        if (dniTxt.getText().isEmpty()) {
            dniTxt.requestFocus();
            volver = false;
        }
        if (nombreTxt.getText().isEmpty()) {
            nombreTxt.requestFocus();
            volver = false;
        }
        if (calleTxt.getText().isEmpty()) {
            calleTxt.requestFocus();
            volver = false;
        }
        if (numeroTxt.getText().isEmpty()) {
            numeroTxt.requestFocus();
            volver = false;
        }
        if (codigoPostalTxt.getText().isEmpty()) {
            codigoPostalTxt.requestFocus();
            volver = false;
        }
        if (localidadTxt.getText().isEmpty()) {
            localidadTxt.requestFocus();
            volver = false;
        }
        if (provinciaTxt.getText().isEmpty()) {
            provinciaTxt.requestFocus();
            volver = false;
        }
        return volver;
    }

    private void guardar() {
        cli.setCategoriaDeIva(4);
        cli.setActivo(true);
        cli.setAlias("");
        Long ultimo_id;
        try {
            ultimo_id = new ClienteService().getUltimoId();
        } catch (Exception ex) {
            //Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR nro. 271 - GRABANDO NUEVO CLIENTE");
            return;
        }
        ultimo_id += 1;
        cli.setCodigo("999 " + ultimo_id.toString());
        cli.setCuit(dniTxt.getText());
        cli.setDescuento(0.0F);
        Domicilio dm = new Domicilio();
        dm.setCalle(calleTxt.getText());
        dm.setNumero(numeroTxt.getText());
        dm.setCodigoPostal(codigoPostalTxt.getText());
        dm.setLocalidad(localidadTxt.getText());
        dm.setProvincia(provinciaTxt.getText());
        cli.setDomicilio(dm);
        cli.setEntrega("");
        cli.setFormaDePago(1);
        cli.setImporteMostrador(0.0);
        cli.setRazonSocial(nombreTxt.getText());
        cli.setSaldo(0.0);
        cli.setTieneDescuento(false);
        String tipo = "96";
        if (dniRb.isSelected()) {
            tipo = "96";
        }
        if (cuilRb.isSelected()) {
            tipo = "86";
        }
        if (cuitRb.isSelected()) {
            tipo = "80";
        }
        if (dniRb.isSelected()) {

        }
        cli.setTipo(tipo);
        try {
            cli = new ClienteService().saveCliente(cli);
        } catch (Exception ex) {
            //Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR nro. 961 - GRABANDO NUEVO CLIENTE");
        }
        ClienteTraba ct1 = new ClienteTraba();
        CustomerTraba ct2 = new CustomerTraba();
        ct1.setCodigo(cli.getCodigo());
        ct1.setTraba1(false);
        ct2.setCodigo(cli.getCodigo());
        ct2.setTraba2(false);
        try {
            new ClienteTrabaService().saveCliente(ct1);
            new CustomerTrabaService().saveCliente(ct2);
        } catch (Exception ex) {
            Logger.getLogger(IngresoDniFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        codigoTxt.setText(cli.getCodigo());
    }
}
