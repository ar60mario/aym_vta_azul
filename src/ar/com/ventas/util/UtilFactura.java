package ar.com.ventas.util;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.frame.FacturaWebFrame;
import ar.com.ventas.services.ClienteService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class UtilFactura {
    
    private static final int qrTamAncho = 150;
    private static final int qrTamAlto = 150;
    private static final String formato = "png";
    private static final String ruta = "c://qr//codigoQR";
    private static final String extension = ".png";
    private static final SimpleDateFormat sdf_qr = new SimpleDateFormat("yyyy-MM-dd");
    private static final DecimalFormat df_qr = new DecimalFormat("00000000");
    private static DecimalFormat df_matriz = new DecimalFormat("00000000");
    private static final String url_qr = "https://www.afip.gob.ar/fe/qr/?p=";
    private static final String ver_qr = "1";
    private static String fecha_qr;
    private static final String cuit_qr = "20124127581";
    private static String puntoVenta_qr = "5";
    private static String tipoComprobante_qr;
    private static String numeroComprobante_qr;
    private static String importe_qr;
    private static final String moneda_qr = "PES";
    private static final String cotiz_qr = "1";
    private static String tipoDoc_qr;
    private static String numeroDoc_qr;
    private static final String tipoCodigoAutoriz_qr = "E";
    private static String nroCae_qr;
    private static final DecimalFormat df = new DecimalFormat("#0.00");
    
    public static void generarFacturaPdf(IvaVentas iv, List<RenglonFactura> rf){
        fecha_qr = sdf_qr.format(iv.getFecha());
        String cui = iv.getCliente().getCuit();
        String pri = "";
        String med = "";
        String fin = "";
        int lgo = cui.length();
        if (lgo != 13) {
            cui = "0000000000000" + cui;
            int lgo1 = cui.length();
            fin = cui.substring(lgo1 - 11, lgo1);
        }
        if (lgo > 11) {
            pri = cui.substring(0, 2);
            med = cui.substring(3, 11);
            fin = cui.substring(12, 13);
        }
        numeroDoc_qr = pri + med + fin;
        puntoVenta_qr = iv.getNumeroSucursal().toString();
        tipoComprobante_qr = iv.getCodigoTipoDoc().toString();
        numeroComprobante_qr = iv.getNumeroFactura().toString();
        String nc = df_matriz.format(iv.getNumeroFactura());
        importe_qr = df.format(iv.getTotal());
        tipoDoc_qr = iv.getCliente().getTipo();
        nroCae_qr = iv.getCae().toString();
        String data = "{\"ver\":" + ver_qr
                + ",\"fecha\":\"" + fecha_qr + "\""
                + ",\"cuit\":" + cuit_qr
                + ",\"ptoVta\":" + puntoVenta_qr
                + ",\"tipoCmp\":" + tipoComprobante_qr
                + ",\"nroCmp\":" + numeroComprobante_qr
                + ",\"importe\":" + importe_qr
                + ",\"moneda\":\"" + moneda_qr + "\""
                + ",\"ctz\":" + cotiz_qr
                + ",\"tipoDocRec\":" + tipoDoc_qr
                + ",\"nroDocRec\":" + numeroDoc_qr
                + ",\"tipoCodAut\":\"" + tipoCodigoAutoriz_qr + "\""
                + ",\"codAut\":" + nroCae_qr + "}";
        try {
            generarQR(data, nc);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        String code = iv.getCliente().getCodigo();
        //String numeroFactura = iv.getNumeroFactura().toString();
        Cliente cli = null;
        try {
            cli = new ClienteService().getClienteByCodigo(code);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Integer categoriaIva = cli.getCategoriaDeIva();
        try {
            if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
                File pdf = new PDFBuilder2().armarFcA(cli, iv, rf);  //.armarF(cli, iv, rf);
                System.out.println(pdf);
                DesktopApi.open(pdf);
            } else {
                File pdf = new PDFBuilder2().armarFcB(cli, iv, rf);  //.armarF(cli, iv, rf);
                System.out.println(pdf);
                DesktopApi.open(pdf);
            }
            JOptionPane.showMessageDialog(null, "PDF GENERADO CORRECTAMENTE");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err1");
            JOptionPane.showMessageDialog(null, "ERROR FILE 3554");
//            JOptionPane.showMessageDialog(null, System.getProperty("user.dir"));
//            JOptionPane.showMessageDialog(this, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err2");
            JOptionPane.showMessageDialog(null, "ERROR DOCUMENT 3557");
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err3");
            JOptionPane.showMessageDialog(null, "ERROR EXCEPTION 3558");
        }
    }
    
    private static void generarQR(String data, String numeroFactura) throws Exception {
        String cadenaCodificada = Base64.getEncoder().encodeToString(data.getBytes());
        BitMatrix matriz;
        Writer writer = new QRCodeWriter();
        try {
            matriz = writer.encode(url_qr + cadenaCodificada, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "ERROR GENERANDO QR");
            return;
        }
        BufferedImage imagen = new BufferedImage(qrTamAncho,
                qrTamAlto, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < qrTamAlto; y++) {
            for (int x = 0; x < qrTamAncho; x++) {
                int valor = (matriz.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (valor == 0 ? 0 : 0xFFFFFF));
            }
        }
        //99
        FileOutputStream qrCode;
        //String nf_qr = numeroFactura;
        qrCode = new FileOutputStream(ruta + numeroFactura + extension);
        ImageIO.write(imagen, formato, qrCode);
        qrCode.close();
    }
}
