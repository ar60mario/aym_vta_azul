/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.frame;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.Vendedor;
import ar.com.ventas.main.MainFrame;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.IvaVentasService;
import ar.com.ventas.services.VendedorService;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class RecuperarDeAfipFrame extends javax.swing.JFrame {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
//    private final Integer order_num;
//    private final String order_name;

    /**
     * Creates new form AaaTestFrame
     */
    public RecuperarDeAfipFrame() {
        initComponents();
//        this.order_name = order_name;
//        this.order_num = order_num;
        limpiar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        go = new javax.swing.JButton();
        numeroTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboL = new javax.swing.JComboBox<>();
        comboT = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        volverBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        excepcion1Txt = new javax.swing.JTextField();
        excepcion2Txt = new javax.swing.JTextField();
        fechaCbteTxt = new javax.swing.JTextField();
        caeTxt = new javax.swing.JTextField();
        fechaCaeTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tipoDocTxt = new javax.swing.JTextField();
        cuitTxt = new javax.swing.JTextField();
        gravadoTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        impuestoTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        ivaTxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        totalTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        clienteTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        ultimoTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("RECUPERAR COMPROBANTE DE AFIP");

        go.setText("Recuperar Comprobante");
        go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goActionPerformed(evt);
            }
        });

        numeroTxt.setText("NUMERO");

        jLabel1.setText("Número:");

        jLabel2.setText("Letra:");

        comboL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "A", "B" }));

        comboT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "FC", "NC" }));

        jLabel3.setText("Tipo:");

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Excepción 1 Afip:");

        jLabel5.setText("Excepción 2 Afip:");

        jLabel6.setText("Fecha Comprobante:");

        jLabel7.setText("CAE:");

        jLabel8.setText("Fecha Vencim CAE:");

        excepcion1Txt.setText("jTextField1");

        excepcion2Txt.setText("jTextField1");

        fechaCbteTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaCbteTxt.setText("jTextField1");

        caeTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        caeTxt.setText("jTextField2");

        fechaCaeTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaCaeTxt.setText("jTextField1");

        jLabel9.setText("Tipo Documento Cliente:");

        jLabel10.setText("CUIT Cliente:");

        jLabel11.setText("Gravado:");

        tipoDocTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tipoDocTxt.setText("jTextField2");

        cuitTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cuitTxt.setText("jTextField3");

        gravadoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gravadoTxt.setText("jTextField4");

        jLabel12.setText("Impuesto:");

        impuestoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        impuestoTxt.setText("jTextField1");

        jLabel13.setText("IVA:");

        ivaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ivaTxt.setText("jTextField5");

        jLabel14.setText("Total:");

        totalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTxt.setText("jTextField6");

        jLabel15.setText("Cliente:");

        clienteTxt.setText("jTextField1");

        jLabel16.setText("Ultimo Autorizado:");

        ultimoTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ultimoTxt.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(50, 50, 50)
                        .addComponent(comboT, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboL, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(go)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(volverBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(excepcion1Txt)
                            .addComponent(excepcion2Txt)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fechaCbteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tipoDocTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(caeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fechaCaeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33)
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 105, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(36, 36, 36)
                                .addComponent(ultimoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel15))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(gravadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(impuestoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(clienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(volverBtn)
                    .addComponent(jLabel2)
                    .addComponent(comboL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(go))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(ultimoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(excepcion1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(excepcion2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fechaCbteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(caeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(fechaCaeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tipoDocTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(gravadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(impuestoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(clienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goActionPerformed
        go();
    }//GEN-LAST:event_goActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

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
            java.util.logging.Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecuperarDeAfipFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField caeTxt;
    private javax.swing.JTextField clienteTxt;
    private javax.swing.JComboBox<String> comboL;
    private javax.swing.JComboBox<String> comboT;
    private javax.swing.JTextField cuitTxt;
    private javax.swing.JTextField excepcion1Txt;
    private javax.swing.JTextField excepcion2Txt;
    private javax.swing.JTextField fechaCaeTxt;
    private javax.swing.JTextField fechaCbteTxt;
    private javax.swing.JButton go;
    private javax.swing.JTextField gravadoTxt;
    private javax.swing.JTextField impuestoTxt;
    private javax.swing.JTextField ivaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JTextField tipoDocTxt;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JTextField ultimoTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void go() {
        int l = comboL.getSelectedIndex();
        int t = comboT.getSelectedIndex();
        if (l < 1) {
            return;
        }
        if (t < 1) {
            return;
        }
        int si = 1;
        String letr;
        if (t == 1) {
            if (l == 1) {
                letr = "A";
            } else {
                letr = "B";
            }
        } else {
            if (l == 3) {
                letr = "A";
            } else {
                letr = "B";
            }
        }
//        IvaVentas ivaVentas = null;
//        try {
//            ivaVentas = new IvaVentasService().getFacturaByNumero(letr, 5, Integer.valueOf(numeroTxt.getText()));
//            si = 1;
//        } catch (Exception ex) {
//            Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(Level.SEVERE, null, ex);
//            si = 0;
//        }
//        if (si == 1) {
//            JOptionPane.showMessageDialog(this, "Comprobante existente");
//            return;
//        }
        LibraryLoader.loadJacobLibrary();
        ActiveXComponent wsaa = new ActiveXComponent("WSAA");
        String ver = Dispatch.get(wsaa, "InstallDir").toString() + " " + Dispatch.get(wsaa, "Version").toString();
        String wsdl = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
        String userdir = "c:/certifaym";
        Dispatch.call(wsaa, "Autenticar",
                new Variant("wsfe"),
                new Variant(userdir + "/aym2020_4232d12f5f615fc0.crt"),
                new Variant(userdir + "/clave_privada_20124127581_202010294251.key"),
                /*
                new Variant(userdir + "/nuevo_2018_52c1402efa235ce.crt"),
                new Variant(userdir + "/clave_privada_20124127581_201811014935.key"),
                 */
                new Variant(wsdl));
        String excepcion = Dispatch.get(wsaa, "Excepcion").toString();
        String token = Dispatch.get(wsaa, "Token").toString();
        String sign = Dispatch.get(wsaa, "Sign").toString();
        ActiveXComponent wsfev1 = new ActiveXComponent("WSFEv1");
        Dispatch.put(wsfev1, "Cuit", new Variant("20124127581"));
        Dispatch.put(wsfev1, "Token", new Variant(token));
        Dispatch.put(wsfev1, "Sign", new Variant(sign));
        String cache = "";
        wsdl = "https://servicios1.afip.gov.ar/wsfev1/service.asmx?WSDL";
        Dispatch.call(wsfev1, "Conectar",
                new Variant(cache),
                new Variant(wsdl)
        );
        String tipo_cbte = "6";
        String pto_vta = "5";
        if (t == 1) {
            if (l == 1) {
                tipo_cbte = "1";
            } else {
                tipo_cbte = "6";
            }
        } else {
            if (l == 1) {
                tipo_cbte = "3";
            } else {
                tipo_cbte = "8";
            }
        }
        String nro_cbte = numeroTxt.getText();
        Variant ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                new Variant(tipo_cbte),
                new Variant(pto_vta));
        Integer n1 = Integer.valueOf(nro_cbte);
        Integer n2 = Integer.valueOf(ult.toString());
        if (n1 > n2) {
            JOptionPane.showMessageDialog(this, "El número de Comprobante no fue generado todavía");
            return;
        }
        String excepcion1 = Dispatch.get(wsfev1, "Excepcion").toString();
        Variant cae = Dispatch.call(wsfev1, "CompConsultar", new Variant(tipo_cbte), new Variant(pto_vta), new Variant(nro_cbte));
        String s1 = cae.toString();
        String fvc = Dispatch.call(wsfev1, "Vencimiento").toString();
        String cc = Dispatch.call(wsfev1, "ObtenerCampoFactura", "tipo_doc").toString();
        String cc1 = Dispatch.call(wsfev1, "ObtenerCampoFactura", "nro_doc").toString();
        System.out.println(cc1);
        String ca0;
        String ca1;
        String ca2;
        String ca3 = "00000000000";
        if (!cc1.equals("0")) {
            ca0 = cc1.substring(0, 2);
            ca1 = cc1.substring(2, 10);
            ca2 = cc1.substring(10, 11);
            ca3 = ca0 + "-" + ca1 + "-" + ca2;
        }
        String s2 = Dispatch.call(wsfev1, "ObtenerCampoFactura", "imp_total").toString();
        String s3 = Dispatch.call(wsfev1, "ObtenerCampoFactura", "iva", 0, "importe").toString();
        String s4 = Dispatch.call(wsfev1, "ObtenerCampoFactura", "imp_neto").toString();
        String s5 = Dispatch.call(wsfev1, "ObtenerCampoFactura", "fecha_cbte").toString();
        Double d2 = Double.valueOf(s2);
        Double d3 = Double.valueOf(s3);
        Double d4 = Double.valueOf(s4);
        Double d5 = d2 - d3 - d4;
        DecimalFormat deFo = new DecimalFormat("#0.00");
        Cliente cl = null;
        try {
            cl = new ClienteService().getClienteByCuit(ca3);
        } catch (Exception ex) {
            Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(excepcion);
        System.out.println(excepcion1);
        System.out.println(ver);
        System.out.println(ult.toString());
        String fefa = s5.substring(6, 8) + "/" + s5.substring(4, 6) + "/" + s5.substring(0, 4);
        System.out.println("CAE: " + s1);
        System.out.println("Vencimiento CAE: " + fvc);
        System.out.println("Tipo CUIT cliente: " + cc);
        System.out.println("CUIT cliente: " + cc1);
        System.out.println("Gravado:      " + deFo.format(d4));
        System.out.println("Iva:          " + deFo.format(d3));
        System.out.println("Impuesto:     " + deFo.format(d5));
        System.out.println("Total Fc:     " + deFo.format(d2));
        System.out.println("Fecha Fc:     " + fefa);
        System.out.println("CUIT: " + ca3);
        ultimoTxt.setText(ult.toString());
        excepcion1Txt.setText(excepcion.toString());
        excepcion2Txt.setText(excepcion1.toString());
        fechaCbteTxt.setText(fefa);
        caeTxt.setText(s1);
        fechaCaeTxt.setText(fvc);
        tipoDocTxt.setText(cc.toString());
        cuitTxt.setText(ca3);
        gravadoTxt.setText(deFo.format(d4));
        impuestoTxt.setText(deFo.format(d5));
        ivaTxt.setText(deFo.format(d3));
        totalTxt.setText(deFo.format(d2));
        if (cl != null) {
            System.out.println("CLIENTE: " + cl.getRazonSocial());
            clienteTxt.setText(cl.getRazonSocial());
        }
        int a = JOptionPane.showConfirmDialog(this, "Guarda Comprobante?", "Atención", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            if (!verificar(l, nro_cbte)) {
                JOptionPane.showMessageDialog(this, "Comprobante existente");
                return;
            }
            IvaVentas iv = new IvaVentas();
            iv.setCae(Long.valueOf(s1));
            iv.setCliente(cl);
            iv.setCodigoTipoDoc(Integer.valueOf(tipo_cbte));
            iv.setDescuentoGlobal(0.0);
            iv.setDocAsociado(0L);
            iv.setExento(0.0);
            try {
                iv.setFecha(sdf.parse(fefa));
                iv.setFechaCae(sdf1.parse(fvc));
            } catch (ParseException ex) {
                Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            iv.setGravado(d4);
            iv.setGravadoCigarrillos(0.0);
            if (t == 1) {
                iv.setHd(true);
            } else {
                iv.setHd(false);
            }
            iv.setImpuesto(d5);
            iv.setIva(d3);
            if (t == 1) {
                if (l == 1) {
                    iv.setLetra("A");
                } else {
                    iv.setLetra("B");
                }
            } else {
                if (l == 1) {
                    iv.setLetra("A");
                } else {
                    iv.setLetra("B");
                }
            }
            iv.setNoGravado(0.0);
            iv.setNumeroFactura(Integer.valueOf(numeroTxt.getText()));
            Vendedor v = null;
            try {
                v = new VendedorService().getVendedorByCodigo(99);
            } catch (Exception ex) {
                Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            iv.setVendedor(v);
            iv.setTotal(d2);
            iv.setTextoPieFactura2("");
            iv.setTextoPieFactura1("");
            iv.setNumeroSucursal(5);
            try {
                new IvaVentasService().saveIvaVentas(iv);
            } catch (Exception ex) {
                Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Proceso Terminado");
        }
        limpiar();
    }

    private void volver() {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        this.dispose();
    }

    private void limpiar() {
        numeroTxt.setText("");
        ultimoTxt.setText("");
        comboT.setSelectedIndex(0);
        comboL.setSelectedIndex(0);
        excepcion1Txt.setText("");
        excepcion2Txt.setText("");
        fechaCbteTxt.setText("");
        caeTxt.setText("");
        fechaCaeTxt.setText("");
        tipoDocTxt.setText("");
        cuitTxt.setText("");
        gravadoTxt.setText("");
        impuestoTxt.setText("");
        ivaTxt.setText("");
        totalTxt.setText("");
        clienteTxt.setText("");
    }

    private boolean verificar(int l, String n) {
        IvaVentas i = null;
        String letr;
        if (l == 1) {
            letr = "A";
        } else {
            if (l == 3) {
                letr = "A";
            } else {
                letr = "B";
            }
        }
        int nu = Integer.valueOf(n);
        try {
            i = new IvaVentasService().getFacturaByNumero(letr, 5, nu);
        } catch (Exception ex) {
            Logger.getLogger(RecuperarDeAfipFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (i != null) {
            return false;
        }
        return true;
    }
}