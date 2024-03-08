/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.MovimientoCaja;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.MovimientoCajaService;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class CierreCajaDiarioFrame extends javax.swing.JFrame {

    private Date fecha;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final DecimalFormat df = new DecimalFormat("#0.00");
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form CierreCajaDiarioFrame
     *

     */
    public CierreCajaDiarioFrame() {
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
        saldoDeudoresTxt = new javax.swing.JTextField();
        subtotalIngresosTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        fechaTxt = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        cerradoCbx = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cajaInicialTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        deudoresHoyTxt = new javax.swing.JTextField();
        descuentosTxt = new javax.swing.JTextField();
        depositosEfectivoTxt = new javax.swing.JTextField();
        valesTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        subtotalEgresosTxt = new javax.swing.JTextField();
        diferenciaTxt = new javax.swing.JTextField();
        totalFacturadoTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setText("Descuentos:");

        jLabel2.setText("Depósitos Efectivo:");

        jLabel3.setText("Vales:");

        saldoDeudoresTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saldoDeudoresTxt.setText("SALDO DEUDORES");

        subtotalIngresosTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        subtotalIngresosTxt.setText("SUBTOTAL");

        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Día:");

        fechaTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaTxt.setText("FECHA");
        fechaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fechaTxtKeyPressed(evt);
            }
        });

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        cerradoCbx.setText("Cerrado");

        jLabel5.setText("Subtotal:");

        jLabel6.setText("Saldo Deudores:");

        jLabel7.setText("Total Facturado:");

        jLabel8.setText("Subtotal:");

        jLabel9.setText("Caja inicial:");

        cajaInicialTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cajaInicialTxt.setText("CAJA INICIAL");

        jLabel10.setText("Deudores hoy:");

        deudoresHoyTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        deudoresHoyTxt.setText("DEUDORES HOY");

        descuentosTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentosTxt.setText("DESCUENTOS");

        depositosEfectivoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        depositosEfectivoTxt.setText("DEPOSITOS");

        valesTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        valesTxt.setText("VALES");

        jLabel11.setText("Diferencia:");

        subtotalEgresosTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        subtotalEgresosTxt.setText("SUBTOTAL");

        diferenciaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        diferenciaTxt.setText("DIFERENCIA");

        totalFacturadoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalFacturadoTxt.setText("TOTAL FACTURADO");

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Observaciones:");

        jTextField1.setText("jTextField1");

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(guardarBtn)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(subtotalIngresosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cajaInicialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(53, 53, 53)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cerradoCbx)
                                                .addComponent(buscarBtn))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton1)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(saldoDeudoresTxt)
                                                .addComponent(totalFacturadoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(75, 75, 75)
                                                    .addComponent(jLabel4)))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(depositosEfectivoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(valesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deudoresHoyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(descuentosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(subtotalEgresosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(diferenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 23, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalFacturadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saldoDeudoresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subtotalIngresosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(buscarBtn))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cajaInicialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cerradoCbx))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(deudoresHoyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardarBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(descuentosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(depositosEfectivoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(valesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(subtotalEgresosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(diferenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        buscarFecha();
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        guardarCaja();
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fechaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaTxtKeyPressed
        if (evt.getKeyCode() == 10) {
            if (fechaTxt.getText().isEmpty()) {
                fechaTxt.setText(sdf.format(new Date()));
                fechaTxt.requestFocus(); //mismo campo
            } else {
                int largo = fechaTxt.getText().length();
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
                    fechaTxt.setText(fechaTxt.getText() + "/" + f);
                    buscarFecha(); // request focus prox campo
                } else {
                    if (largo == 5) {
                        Calendar cal = Calendar.getInstance();
                        int anio = cal.get(Calendar.YEAR);
                        String an = "/" + String.valueOf(anio);
                        fechaTxt.setText(fechaTxt.getText() + an);
                        buscarFecha(); // request focus prox campo
                    } else {
                        if (largo == 8) {
                            String x_f = fechaTxt.getText();
                            String x_f1 = x_f.substring(0, 6);
                            String x_f2 = x_f.substring(6, 8);
                            fechaTxt.setText(x_f1 + "20" + x_f2);
                            buscarFecha(); // request focus prox campo
                        } else {
                            if (largo != 10) {
                                JOptionPane.showMessageDialog(this, "Error en fecha");
                                fechaTxt.setText("");
                                fechaTxt.requestFocus(); // request focus mismo campo
                            } else {
                                String veinte = fechaTxt.getText().substring(6, 8);
                                if (!veinte.equals("20")) {
                                    JOptionPane.showMessageDialog(this, "Error en AÑO");
                                    fechaTxt.requestFocus(); // request focus mismo campo
                                } else {
                                    buscarFecha(); // request focus prox campo
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_fechaTxtKeyPressed

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
            java.util.logging.Logger.getLogger(CierreCajaDiarioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CierreCajaDiarioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CierreCajaDiarioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CierreCajaDiarioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CierreCajaDiarioFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField cajaInicialTxt;
    private javax.swing.JCheckBox cerradoCbx;
    private javax.swing.JTextField depositosEfectivoTxt;
    private javax.swing.JTextField descuentosTxt;
    private javax.swing.JTextField deudoresHoyTxt;
    private javax.swing.JTextField diferenciaTxt;
    private javax.swing.JTextField fechaTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField saldoDeudoresTxt;
    private javax.swing.JTextField subtotalEgresosTxt;
    private javax.swing.JTextField subtotalIngresosTxt;
    private javax.swing.JTextField totalFacturadoTxt;
    private javax.swing.JTextField valesTxt;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        totalFacturadoTxt.setText("");
        saldoDeudoresTxt.setText("");
        deudoresHoyTxt.setText("");
        subtotalIngresosTxt.setText("");
        subtotalEgresosTxt.setText("");
        cajaInicialTxt.setText("");
        descuentosTxt.setText("");
        depositosEfectivoTxt.setText("");
        valesTxt.setText("");
        diferenciaTxt.setText("");
        fecha = new Date();
        fechaTxt.setText(sdf.format(fecha));
    }

    private void buscarFecha() {
        Date today = new Date();
        try {
            fecha = sdf.parse(fechaTxt.getText());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "formato erroneo de fecha");
            fechaTxt.requestFocus();
            return;
            //Logger.getLogger(CierreCajaDiarioFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fecha.before(today)) {
            MovimientoCaja movim = null;
            try {
                movim = new MovimientoCajaService().getMovimientoCajaByFecha(fecha);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se encuentra Movimiento");
                return;
                //Logger.getLogger(CierreCajaDiarioFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (movim != null) {
                Boolean cerrado = movim.getCerrado();
                if (cerrado) {
                    guardarBtn.setEnabled(false);
                    totalFacturadoTxt.setText(String.valueOf(df.format(movim.getTotalFacturado())));
                } else {
                    guardarBtn.setEnabled(true);

                }

            } else {
                JOptionPane.showMessageDialog(this, "No existe Movimiento");
            }

        }
    }

    private void guardarCaja() {

    }
}
