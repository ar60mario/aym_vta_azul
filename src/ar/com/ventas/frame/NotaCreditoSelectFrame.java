/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Usuario;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.UsuarioService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Administrador
 */
public class NotaCreditoSelectFrame extends javax.swing.JFrame {

    private Usuario usuario;
    private final Integer nivel = 2;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form NotaCreditoSelectFrame
     */
    public NotaCreditoSelectFrame() {
        initComponents();
//        this.order_name = order_name;
//        this.order_num = order_num;
        setearBtn();
        notaCreditoBtn.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        notaCreditoBtn = new javax.swing.JRadioButton();
        notaCreditoDdeFacturaBtn = new javax.swing.JRadioButton();
        notaCreditoDdeClienteBtn = new javax.swing.JRadioButton();
        volverBtn = new javax.swing.JButton();
        generarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SELECCIONAR NOTA CREDITO");

        notaCreditoBtn.setText("Nota de Crédito - Web");
        notaCreditoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaCreditoBtnActionPerformed(evt);
            }
        });

        notaCreditoDdeFacturaBtn.setText("NC Web desde Factura Web");
        notaCreditoDdeFacturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaCreditoDdeFacturaBtnActionPerformed(evt);
            }
        });

        notaCreditoDdeClienteBtn.setText("Selección desde Cliente");
        notaCreditoDdeClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaCreditoDdeClienteBtnActionPerformed(evt);
            }
        });

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        generarBtn.setText("Generar");
        generarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notaCreditoBtn)
                    .addComponent(notaCreditoDdeFacturaBtn)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volverBtn))
                    .addComponent(notaCreditoDdeClienteBtn))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(notaCreditoDdeFacturaBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(notaCreditoBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(notaCreditoDdeClienteBtn)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volverBtn)
                    .addComponent(generarBtn))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void notaCreditoDdeFacturaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaCreditoDdeFacturaBtnActionPerformed
        notaCreditoDdeFacturaBtn.setSelected(true);
        notaCreditoDdeClienteBtn.setSelected(false);
        notaCreditoBtn.setSelected(false);
    }//GEN-LAST:event_notaCreditoDdeFacturaBtnActionPerformed

    private void notaCreditoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaCreditoBtnActionPerformed
        notaCreditoDdeFacturaBtn.setSelected(false);
        notaCreditoDdeClienteBtn.setSelected(false);
        notaCreditoBtn.setSelected(true);
    }//GEN-LAST:event_notaCreditoBtnActionPerformed

    private void notaCreditoDdeClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaCreditoDdeClienteBtnActionPerformed
        notaCreditoDdeFacturaBtn.setSelected(false);
        notaCreditoDdeClienteBtn.setSelected(true);
        notaCreditoBtn.setSelected(false);
    }//GEN-LAST:event_notaCreditoDdeClienteBtnActionPerformed

    private void generarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarBtnActionPerformed
        if (habilitado()) {
            if (notaCreditoBtn.isSelected()) {
                NotaCreditoWebFrame ncwf = new NotaCreditoWebFrame(null, null);
                ncwf.setVisible(true);
                this.dispose();
            } else {
                if (notaCreditoDdeClienteBtn.isSelected()) {
                    NotaCreditoPedirCliente ncpc = new NotaCreditoPedirCliente();
                    ncpc.setVisible(true);
                    this.dispose();
                } else {
                    if (notaCreditoDdeFacturaBtn.isSelected()) {
                        NotaCreditoPedirFactura ncpf = new NotaCreditoPedirFactura();
                        ncpf.setVisible(true);
                        this.dispose();
                    }
                }
            }
        }
    }//GEN-LAST:event_generarBtnActionPerformed

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
            java.util.logging.Logger.getLogger(NotaCreditoSelectFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoSelectFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoSelectFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoSelectFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotaCreditoSelectFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generarBtn;
    private javax.swing.JRadioButton notaCreditoBtn;
    private javax.swing.JRadioButton notaCreditoDdeClienteBtn;
    private javax.swing.JRadioButton notaCreditoDdeFacturaBtn;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void setearBtn() {
        notaCreditoBtn.setSelected(false);
        notaCreditoDdeFacturaBtn.setSelected(true);
        notaCreditoDdeClienteBtn.setSelected(false);
    }

    private boolean habilitado() {
        FileReader fr = null;
        try {
            fr = new FileReader("c:/ventas/permisos.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NotaCreditoSelectFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        String acceso = "";
        try {
            acceso = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(NotaCreditoSelectFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(NotaCreditoSelectFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(NotaCreditoSelectFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        }
    }

    private void volver() {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        this.dispose();
    }
}
