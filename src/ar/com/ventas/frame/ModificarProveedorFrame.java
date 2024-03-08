/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Domicilio;
import ar.com.ventas.entities.Proveedor;
import ar.com.ventas.services.ProveedorService;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class ModificarProveedorFrame extends javax.swing.JFrame {

    private Proveedor proveedor = null;
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form ModificarProveedorFrame
     *

     */
    public ModificarProveedorFrame(Proveedor prove) {
        initComponents();
        this.proveedor = prove;
        this.llenarCampo(prove);
//        this.order_name = order_name;
//        this.order_num = order_num;
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        codigoTxt = new javax.swing.JTextField();
        nombreTxt = new javax.swing.JTextField();
        calleTxt = new javax.swing.JTextField();
        pisoTxt = new javax.swing.JTextField();
        departamentoTxt = new javax.swing.JTextField();
        numeroTxt = new javax.swing.JTextField();
        cpTxt = new javax.swing.JTextField();
        localidadTxt = new javax.swing.JTextField();
        provinciaTxt = new javax.swing.JTextField();
        cuitTxt = new javax.swing.JTextField();
        telTxt = new javax.swing.JTextField();
        celularTxt = new javax.swing.JTextField();
        mailTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        guardarBtn = new javax.swing.JButton();
        vovlerBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        obsTxt = new javax.swing.JTextArea();
        comboCategoria = new javax.swing.JComboBox();
        activoChk = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setText("Código");

        jLabel2.setText("Razón Social");

        jLabel3.setText("Categoría iva");

        jLabel4.setText("Calle ");

        jLabel5.setText("Número");

        jLabel6.setText("Código Postal");

        jLabel7.setText("Provincia");

        jLabel8.setText("Teléfono");

        jLabel9.setText("Mail");

        jLabel10.setText("Piso");

        jLabel11.setText("Departamento");

        jLabel12.setText("Localidad");

        jLabel13.setText("Cuit");

        jLabel14.setText("Celular");

        codigoTxt.setText("id");

        nombreTxt.setText("Nombre");

        calleTxt.setText("Domicilio");

        pisoTxt.setText("Piso");

        departamentoTxt.setText("Departamento");

        numeroTxt.setText("numero");
        numeroTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroTxtActionPerformed(evt);
            }
        });

        cpTxt.setText("C.P");

        localidadTxt.setText("Localidad");

        provinciaTxt.setText("Provincia");

        cuitTxt.setText("Cuit");

        telTxt.setText("Telefono");
        telTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telTxtActionPerformed(evt);
            }
        });

        celularTxt.setText("Celular");

        mailTxt.setText("Mail");

        jLabel15.setText("Observaciones");

        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        vovlerBtn.setText("Volver");
        vovlerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vovlerBtnActionPerformed(evt);
            }
        });

        obsTxt.setColumns(20);
        obsTxt.setRows(5);
        jScrollPane1.setViewportView(obsTxt);

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        activoChk.setText("Activo");

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Guardar");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Salir");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cpTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel12))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(provinciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel13))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(telTxt)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jLabel14))
                                                        .addComponent(mailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 6, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(calleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(pisoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel11))
                                            .addComponent(localidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(departamentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(activoChk)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cuitTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(celularTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(codigoTxt)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10)
                                        .addGap(324, 324, 324))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1)))
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(guardarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vovlerBtn)
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(calleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(pisoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(departamentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cpTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(localidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(provinciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel14)
                    .addComponent(telTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celularTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(mailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(activoChk)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn)
                    .addComponent(vovlerBtn))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numeroTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroTxtActionPerformed

    private void telTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telTxtActionPerformed

    private void vovlerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vovlerBtnActionPerformed
        AbmProveedorFrame apf = new AbmProveedorFrame();
        apf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_vovlerBtnActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        this.guardar();
    }//GEN-LAST:event_guardarBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ModificarProveedorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarProveedorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarProveedorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarProveedorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarProveedorFrame(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activoChk;
    private javax.swing.JTextField calleTxt;
    private javax.swing.JTextField celularTxt;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JComboBox comboCategoria;
    private javax.swing.JTextField cpTxt;
    private javax.swing.JTextField cuitTxt;
    private javax.swing.JTextField departamentoTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField localidadTxt;
    private javax.swing.JTextField mailTxt;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JTextArea obsTxt;
    private javax.swing.JTextField pisoTxt;
    private javax.swing.JTextField provinciaTxt;
    private javax.swing.JTextField telTxt;
    private javax.swing.JButton vovlerBtn;
    // End of variables declaration//GEN-END:variables

    private void guardar() {
        if (validarCampos()) {
            Domicilio domicilio = proveedor.getDomicilio();
            domicilio.setDepartamento(departamentoTxt.getText());
            domicilio.setCalle(calleTxt.getText());
            domicilio.setCodigoPostal(cpTxt.getText());
            domicilio.setNumero(numeroTxt.getText());
            domicilio.setLocalidad(localidadTxt.getText());
            domicilio.setProvincia(provinciaTxt.getText());
            domicilio.setDepartamento(departamentoTxt.getText());
            proveedor.setCodigo(Integer.valueOf(codigoTxt.getText()));
            proveedor.setRazonSocial(nombreTxt.getText());
            proveedor.setCuit(cuitTxt.getText());
            proveedor.setTelefono(telTxt.getText());
            proveedor.setCelular(celularTxt.getText());
            proveedor.setMail(mailTxt.getText());
            proveedor.setObservaciones(obsTxt.getText());
            proveedor.setDomicilio(domicilio);
            proveedor.setCategoriaIva(comboCategoria.getSelectedIndex());
            if (activoChk.isSelected()) {
                proveedor.setActivo(true);
            } else {
                proveedor.setActivo(false);
            }
            try {
                new ProveedorService().updateProveedor(proveedor);
                JOptionPane.showMessageDialog(this, "Proveedor - Guardado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Proveedor - No se puede guardar el proveedor");
            }
            AbmProveedorFrame apf = new AbmProveedorFrame();
            apf.setVisible(true);
            this.dispose();
        }
    }

    private void llenarCampo(Proveedor prove) {
        Domicilio domicilio = prove.getDomicilio();
        codigoTxt.setText(String.valueOf(prove.getCodigo()));
        nombreTxt.setText(prove.getRazonSocial());
        calleTxt.setText(domicilio.getCalle());
        numeroTxt.setText(domicilio.getNumero());
        departamentoTxt.setText(domicilio.getDepartamento());
        cpTxt.setText(domicilio.getCodigoPostal());
        localidadTxt.setText(domicilio.getLocalidad());
        provinciaTxt.setText(domicilio.getProvincia());
        cuitTxt.setText(prove.getCuit());
        telTxt.setText(prove.getTelefono());
        celularTxt.setText(prove.getCelular());
        mailTxt.setText(prove.getMail());
        obsTxt.setText(prove.getObservaciones());
        if (prove.getActivo()) {
            activoChk.setSelected(true);
        } else {
            activoChk.setSelected(false);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboCategoria.getModel();
        model.removeAllElements();
        model.addElement("");
        model.addElement("1- Inscripto");
        model.addElement("2- Monotributo");
        model.addElement("3- Exento");
        model.addElement("4- Consumidor Final");

        comboCategoria.setModel(model);

        if (proveedor.getCategoriaIva() != null) {
            comboCategoria.setSelectedIndex(proveedor.getCategoriaIva());
        }
    }

    public boolean validarCampos() {
        if (nombreTxt.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Proveedor - Falta completar la razon social");
            return false;
        }
        if (codigoTxt.getText().trim().length() == 0) {
            JOptionPane.showConfirmDialog(this, "Proveedor - Falta completar el codigo");
            return false;
        }
        if (comboCategoria.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Proveedor - Seleccione categoría de iva");
            return false;
        }
        if (calleTxt.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Proveedor - Falta completar nombre de la calle");
            return false;
        }
        if (numeroTxt.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Proveedor - Falta completar numero de la direccion");
            return false;
        }
        if (cuitTxt.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Proveedor - Falta completar cuit ");
            return false;
        }
        return true;
    }

}
