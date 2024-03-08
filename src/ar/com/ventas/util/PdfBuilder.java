/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.util;

import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.IvaVentas;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.entities.RenglonNotaCredito;
import com.itextpdf.text.BaseColor;
import java.io.File;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import static java.lang.Math.rint;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Mario
 */
public class PdfBuilder {

    private final BaseColor FONDO_BLANCO = WebColors.getRGBColor("#FFFFFF");
    private final BaseColor NEGRO = WebColors.getRGBColor("#000000");

    private DecimalFormat dfs = new DecimalFormat("0000");
    private DecimalFormat dfn = new DecimalFormat("00000000");
    private DecimalFormat df = new DecimalFormat("#0.00");
    private DecimalFormat dfc = new DecimalFormat("#0");
    private DecimalFormat df_ali = new DecimalFormat("#0.0");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat sdff = new SimpleDateFormat("ddMMyyyy");

    private final int BOLD = Font.BOLD;
    private final int PLAIN = Font.PLAIN;

    private String getFileNameFormatted(IvaVentas cons) {
        String filename = "Fc_"
                + cons.getLetra() + dfs.format(cons.getNumeroSucursal()) + "_"
                + dfn.format(cons.getNumeroFactura())
                + "_" + sdff.format(cons.getFecha())+".pdf";
        return filename;
    }

    private String getFileNameFormattedNc(IvaVentas cons) {
        String filename = "Nc_"
                + cons.getLetra() + dfs.format(cons.getNumeroSucursal()) + "_"
                + dfn.format(cons.getNumeroFactura())
                + "_" + sdff.format(cons.getFecha())+".pdf";
        return filename;
    }

    private String getFileNameFormattedNd(IvaVentas cons) {
        String filename = "Nd_"
                + cons.getLetra() + dfs.format(cons.getNumeroSucursal()) + "_"
                + dfn.format(cons.getNumeroFactura())
                + "_" + sdff.format(cons.getFecha())+".pdf";
        return filename;
    }

    public File armarFcA(Cliente cli, IvaVentas iv, List<RenglonFactura> rf) throws FileNotFoundException, DocumentException, Exception {
        String nr0 = dfn.format(iv.getNumeroFactura());
        String fileNameFormatted = getFileNameFormatted(iv);
        Document pdf = new Document();
        Rectangle hojaA4 = new Rectangle((float) 690, (float) 990);//890
        pdf.setPageSize(hojaA4);
        pdf.setMargins(10, 50, 30, 30);
        pdf.setMarginMirroringTopBottom(true);

        FileOutputStream ficheroPdf = new FileOutputStream(fileNameFormatted);

        PdfWriter writer = PdfWriter.getInstance(pdf, ficheroPdf);
        writer.setInitialLeading(20);

        // ABRO EL DOCUMENTO
        pdf.open();

        // INICIO ENCABEZADO FACTURA
        String cod = " 1";;
        String fex = sdf.format(iv.getFecha());
        String nro = "A_"
                + dfs.format(iv.getNumeroSucursal()) + "-"
                + dfn.format(iv.getNumeroFactura());
        PdfPTable encabezado = new PdfPTable(3);
        encabezado.setWidthPercentage(100);
        PdfPCell celdaEncabezado1 = new PdfPCell(new Paragraph("DISTRIBUIDORA A & M", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado2 = new PdfPCell(new Paragraph("A", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado3 = new PdfPCell(new Paragraph("FACTURA", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado4 = new PdfPCell(new Paragraph("Av. San Martin 3284", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado5 = new PdfPCell(new Paragraph("Cod.nro:" + cod, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado6 = new PdfPCell(new Paragraph(nro, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado7 = new PdfPCell(new Paragraph("1678 - Caseros Prov.Buenos Aires", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado8 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado9 = new PdfPCell(new Paragraph("Fecha: " + fex, FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado10 = new PdfPCell(new Paragraph("Tel: 4759-6677 - mail: distaym@yahoo.com.ar", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado11 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado12 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado13 = new PdfPCell(new Paragraph("CUIT:20-12412758-1 inic.activ.18/04/2005", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado14 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado15 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado16 = new PdfPCell(new Paragraph("IVA - RESPONSABLE INSCRIPTO", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado17 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado18 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

        celdaEncabezado1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado9.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado10.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado12.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado13.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado15.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado16.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado17.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado18.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        encabezado.addCell(celdaEncabezado1).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado2).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado3).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado4).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado5).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado6).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado7).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado8).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado9).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado10).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado11).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado12).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado13).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado14).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado15).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado16).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado17).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado18).setBorder(PdfPCell.NO_BORDER);

        pdf.add(encabezado);
        // FIN ENCABEZADO

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        String nombre = cli.getRazonSocial();
        String direcc = cli.getDomicilio().getCalle();
        if (iv.getCliente().getDomicilio().getNumero() != null) {
            direcc += " " + iv.getCliente().getDomicilio().getNumero();
        }
        if (iv.getCliente().getDomicilio().getLocalidad() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getLocalidad();
        }
        if (iv.getCliente().getDomicilio().getProvincia() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getProvincia();
        }
        String cod_cli = iv.getCliente().getCodigo().toString();
        String cui = iv.getCliente().getCuit();
        String cat_iva;
        if (iv.getCliente().getCategoriaDeIva().equals(1)) {
            cat_iva = "Responsable Inscripto";
        } else {
            if (iv.getCliente().getCategoriaDeIva().equals(2)) {
                cat_iva = "Responsable Monotributo";
            } else {
                if (iv.getCliente().getCategoriaDeIva().equals(3)) {
                    cat_iva = "Responsable Exento";
                } else {
                    cat_iva = "Consumidor Final";
                }
            }
        }
        PdfPTable client = new PdfPTable(2);
        client.setWidthPercentage(100);
        PdfPCell celdaClient1 = new PdfPCell(new Paragraph("   Razón Social: " + nombre, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient2 = new PdfPCell(new Paragraph(" " + cod_cli, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient3 = new PdfPCell(new Paragraph("   Domicilio: " + direcc, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient5 = new PdfPCell(new Paragraph("   C.U.I.T.: " + cui, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient6 = new PdfPCell(new Paragraph("   IVA: " + cat_iva, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaClient1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

        client.addCell(celdaClient1).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient2).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient3).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient4).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient5).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(client);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        //     pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        Integer col = 8;
        PdfPTable detalle = new PdfPTable(col);
        detalle.setWidthPercentage(100);

        float[] anchos = new float[8];
        anchos[0] = 30;
        anchos[1] = 30;
        anchos[2] = 290;
        anchos[3] = 60;
        anchos[4] = 70;
        anchos[5] = 70;
        anchos[6] = 80;
        anchos[7] = 60;

        PdfPTable tablaDetalle = new PdfPTable(col);
        tablaDetalle.setWidthPercentage(100);
        tablaDetalle.setWidths(anchos);

        PdfPCell celdaTitulos1 = new PdfPCell(new Paragraph("IT", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos2 = new PdfPCell(new Paragraph("CA", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos3 = new PdfPCell(new Paragraph("DETALLE", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos4 = new PdfPCell(new Paragraph("PRECIO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos5 = new PdfPCell(new Paragraph("GRAVADO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos6 = new PdfPCell(new Paragraph("IMPUESTO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos7 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos8 = new PdfPCell(new Paragraph("SUGERIDO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaTitulos1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaTitulos4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

        tablaDetalle.addCell(celdaTitulos1).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos2).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos3).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos4).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos5).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos6).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos7).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos8).setBorder(PdfPCell.NO_BORDER);
        pdf.add(tablaDetalle);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 5, PLAIN, FONDO_BLANCO)));

        int coun = 0;
        for (RenglonFactura re : rf) {
            PdfPTable tablaProd = new PdfPTable(col);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            coun += 1;
            String c_nt = String.valueOf(coun);
            //String c_ntd = String.valueOf(re.getCantidad());

            String prec = df.format(re.getTotal() / re.getCantidad());
            String can = dfc.format(re.getCantidad());
            String grav = df.format(re.getGravado());
            String impu = df.format(re.getImpuesto());
            String tota = df.format(re.getTotal());
            String suge = df.format(re.getSugerido());
            String a1 = can;
            String a2 = prec;
            String a3 = tota;
            String a4 = suge;
            String a5 = grav;
            String a6 = impu;
            PdfPCell tablaProd1 = new PdfPCell(new Paragraph(c_nt, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd2 = new PdfPCell(new Paragraph(a1, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd3 = new PdfPCell(new Paragraph(re.getDescripcion(), FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd4 = new PdfPCell(new Paragraph(a2, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd5 = new PdfPCell(new Paragraph(a5, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd6 = new PdfPCell(new Paragraph(a6, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd7 = new PdfPCell(new Paragraph(a3, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd8 = new PdfPCell(new Paragraph(a4, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

            tablaProd1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            tablaProd4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            tablaProd.addCell(tablaProd1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd6).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd7).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd8).setBorder(PdfPCell.NO_BORDER);

            pdf.add(tablaProd);
//            
        }
        for (int i = 1; i < 41 - coun; i++) {
            PdfPTable tablaProd = new PdfPTable(8);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            PdfPCell tP1 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP3 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP5 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP6 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP7 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP8 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            tP1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd.addCell(tP1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP6).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP7).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP8).setBorder(PdfPCell.NO_BORDER);
            pdf.add(tablaProd);
//            
//            pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        }

        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        // pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        PdfPTable pieToFc = new PdfPTable(5);
        pieToFc.setWidthPercentage(100);
        PdfPCell pieToFc1 = new PdfPCell(new Paragraph("SUBTOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc3 = new PdfPCell(new Paragraph("IMPUESTO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc4 = new PdfPCell(new Paragraph("IVA", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc5 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        pieToFc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc.addCell(pieToFc1).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc2).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc3).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc4).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc5).setBorder(PdfPCell.NO_BORDER);
        pdf.add(pieToFc);
        PdfPTable pieFc = new PdfPTable(5);
        pieFc.setWidthPercentage(100);
        String a1 = df.format(iv.getGravado());
        String a2 = df.format(iv.getImpuesto());
        String a3 = df.format(iv.getIva());
        String a4 = df.format(iv.getTotal());
        PdfPCell pieFc1 = new PdfPCell(new Paragraph(a1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc3 = new PdfPCell(new Paragraph(a2, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc4 = new PdfPCell(new Paragraph(a3, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc5 = new PdfPCell(new Paragraph(a4, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        pieFc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc.addCell(pieFc1).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc2).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc3).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc4).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc5).setBorder(PdfPCell.NO_BORDER);
        pdf.add(pieFc);

        PdfPTable pie2Fc = new PdfPTable(2);
        pie2Fc.setWidthPercentage(100);

        String f_venc_cae = sdf.format(iv.getFechaCae());
        String cae_nro = dfc.format(iv.getCae());
        String tpd = iv.getCliente().getTipo().toString();
        String vto = sdf2.format(iv.getFechaCae());
        String cuit1;
        tpd = "80";
        if (tpd.equals("96")) {
            cuit1 = cui.trim();
            tpd = "96";
        } else {
            cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
        }
        int x = 0;
        if (tpd.equals("96")) {
            String s = "0000000000" + cuit1;
            int lar = s.length();
            cuit1 = s.substring(lar - 11, lar);
        }

        Integer suma1 = 0;
        Integer suma2 = 0;
        String sucu = dfs.format(iv.getNumeroSucursal());
        String cae = iv.getCae().toString();
        int largo = cae.length();
        String txtCadenaRP = "";
        String t1 = "";
        String t2 = "";
        if (t1 != null) {
            t1 = iv.getTextoPieFactura1();
        }
        if (t2 != null) {
            t2 = iv.getTextoPieFactura2();
        }
        if (largo > 8) {
            String cadena = cuit1 + "0" + tpd + sucu + cae + vto;
            for (int i = 0; i < 39; i++) {
                if (x == 0) {
                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                    suma1 += num;
                    x = 1;
                } else {
                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                    suma2 += num;
                    x = 0;
                }
            }
            suma1 = suma1 * 3;
            int total = suma1 + suma2;
            int dv = (int) (rint(total / 10 + .9) * 10);
            dv = dv - total;
            cadena += String.valueOf(dv);

            for (int i = 0; i < 40; i = i + 2) {
                String charNum = cadena.substring(i, i + 2);
                int numChar = Integer.valueOf(charNum);
                if (numChar < 50) {
                    numChar += 48;
                } else {
                    numChar += 142;
                }
                char c = (char) numChar;
                txtCadenaRP = txtCadenaRP + c;
            }
        }
        txtCadenaRP = (char) 40 + txtCadenaRP + (char) 41;

        PdfPCell pie2Fc3 = new PdfPCell(new Paragraph("  Fecha Vencimiento CAE: " + f_venc_cae, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pie2Fc4 = new PdfPCell(new Paragraph(t1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pie2Fc5 = new PdfPCell(new Paragraph("  CAE Nro.: " + cae_nro, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pie2Fc6 = new PdfPCell(new Paragraph(t2, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        pie2Fc3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pie2Fc4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pie2Fc5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pie2Fc6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        pie2Fc.addCell(pie2Fc3).setBorder(PdfPCell.NO_BORDER);
        pie2Fc.addCell(pie2Fc4).setBorder(PdfPCell.NO_BORDER);
        pie2Fc.addCell(pie2Fc5).setBorder(PdfPCell.NO_BORDER);
        pie2Fc.addCell(pie2Fc6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(pie2Fc);
        Image imagen = Image.getInstance("c:/qr/CodigoQR" + nr0 + ".png");
        Image img2 = Image.getInstance("c:/qr/afip.png");
        imagen.setAbsolutePosition(10f, 50f);
        img2.setAbsolutePosition(230f, 140f);
        pdf.add(imagen);
        pdf.add(img2);

        pdf.close();
        return new File(fileNameFormatted);
    }

    public File armarFcB(Cliente cli, IvaVentas iv, List<RenglonFactura> rf) throws FileNotFoundException, DocumentException, Exception {
        String nr0 = dfn.format(iv.getNumeroFactura());
        String fileNameFormatted = getFileNameFormatted(iv);
        Document pdf = new Document();
        Rectangle hojaA4 = new Rectangle((float) 690, (float) 990);//890
        pdf.setPageSize(hojaA4);
        pdf.setMargins(10, 30, 30, 30);
        pdf.setMarginMirroringTopBottom(true);

        //FileOutputStream ficheroPdf = new FileOutputStream("c:/pdf/" + fileNameFormatted + ".pdf");
        FileOutputStream ficheroPdf = new FileOutputStream(fileNameFormatted);

        PdfWriter writer = PdfWriter.getInstance(pdf, ficheroPdf);
        writer.setInitialLeading(20);

        // ABRO EL DOCUMENTO
        pdf.open();

        // INICIO ENCABEZADO FACTURA
        String cod = " 6";;
        String fex = sdf.format(iv.getFecha());
        String nro = "B "
                + dfs.format(iv.getNumeroSucursal()) + "-"
                + dfn.format(iv.getNumeroFactura());
        PdfPTable encabezado = new PdfPTable(3);
        encabezado.setWidthPercentage(100);
        PdfPCell celdaEncabezado1 = new PdfPCell(new Paragraph("DISTRIBUIDORA A & M", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado2 = new PdfPCell(new Paragraph("B", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado3 = new PdfPCell(new Paragraph("FACTURA", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado4 = new PdfPCell(new Paragraph("Av. San Martin 3284", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado5 = new PdfPCell(new Paragraph("Cod.nro:" + cod, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado6 = new PdfPCell(new Paragraph(nro, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado7 = new PdfPCell(new Paragraph("1678 - Caseros Prov.Buenos Aires", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado8 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado9 = new PdfPCell(new Paragraph("Fecha: " + fex, FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado10 = new PdfPCell(new Paragraph("Tel: 4759-6677 - mail: distaym@yahoo.com.ar", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado11 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado12 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado13 = new PdfPCell(new Paragraph("CUIT:20-12412758-1 inic.activ.18/04/2005", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado14 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado15 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado16 = new PdfPCell(new Paragraph("IVA - RESPONSABLE INSCRIPTO", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado17 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado18 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

        celdaEncabezado1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado9.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado10.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado12.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado13.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado15.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado16.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado17.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado18.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        encabezado.addCell(celdaEncabezado1).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado2).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado3).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado4).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado5).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado6).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado7).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado8).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado9).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado10).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado11).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado12).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado13).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado14).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado15).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado16).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado17).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado18).setBorder(PdfPCell.NO_BORDER);

        pdf.add(encabezado);
        // FIN ENCABEZADO

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        String nombre = cli.getRazonSocial();
        String direcc = cli.getDomicilio().getCalle();
        if (iv.getCliente().getDomicilio().getNumero() != null) {
            direcc += " " + iv.getCliente().getDomicilio().getNumero();
        }
        if (iv.getCliente().getDomicilio().getLocalidad() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getLocalidad();
        }
        if (iv.getCliente().getDomicilio().getProvincia() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getProvincia();
        }
        String cod_cli = iv.getCliente().getCodigo().toString();
        String cui = iv.getCliente().getCuit();
        String cat_iva;
        if (iv.getCliente().getCategoriaDeIva().equals(1)) {
            cat_iva = "Responsable Inscripto";
        } else {
            if (iv.getCliente().getCategoriaDeIva().equals(2)) {
                cat_iva = "Responsable Monotributo";
            } else {
                if (iv.getCliente().getCategoriaDeIva().equals(3)) {
                    cat_iva = "Responsable Exento";
                } else {
                    cat_iva = "Consumidor Final";
                }
            }
        }
        PdfPTable client = new PdfPTable(2);
        client.setWidthPercentage(100);
        PdfPCell celdaClient1 = new PdfPCell(new Paragraph("   RAZON SOCIAL: " + nombre, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient2 = new PdfPCell(new Paragraph(" " + cod_cli, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient3 = new PdfPCell(new Paragraph("   DOMICILIO: " + direcc, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient5 = new PdfPCell(new Paragraph("   C.U.I.T.: " + cui, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient6 = new PdfPCell(new Paragraph("   IVA: " + cat_iva, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaClient1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaClient6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        client.addCell(celdaClient1).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient2).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient3).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient4).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient5).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(client);

        // pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        Integer col = 6;

        PdfPTable detalle = new PdfPTable(col);
        detalle.setWidthPercentage(100);

        float[] anchos = new float[col];
        anchos[0] = 30;
        anchos[1] = 30;
        anchos[2] = 360;
        anchos[3] = 70;
        anchos[4] = 70;
        anchos[5] = 80;

        PdfPTable tablaDetalle = new PdfPTable(col);
        tablaDetalle.setWidthPercentage(100);
        tablaDetalle.setWidths(anchos);

        PdfPCell celdaTitulos1 = new PdfPCell(new Paragraph("IT", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos2 = new PdfPCell(new Paragraph("CA", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos3 = new PdfPCell(new Paragraph("DETALLE", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos4 = new PdfPCell(new Paragraph("PRECIO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos5 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos6 = new PdfPCell(new Paragraph("SUGERIDO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaTitulos1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaTitulos4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

        tablaDetalle.addCell(celdaTitulos1).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos2).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos3).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos4).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos5).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(tablaDetalle);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 5, PLAIN, FONDO_BLANCO)));

        int coun = 0;
        for (RenglonFactura re : rf) {
            PdfPTable tablaProd = new PdfPTable(6);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            coun += 1;
            String c_nt = String.valueOf(coun);
            //String c_ntd = String.valueOf(re.getCantidad());
            String prec;
            if (re.getTotal() > 1) {
                prec = df.format(re.getTotal() / re.getCantidad());
            } else {
                prec = " ";
            }
            Float can = re.getCantidad();
            String tota = df.format(re.getTotal());
            String suge = df.format(re.getSugerido());
            String a1 = dfc.format(can);
            String a2 = prec;
            String a3 = tota;
            String a4 = suge;
            PdfPCell tablaProd1 = new PdfPCell(new Paragraph(c_nt, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd2 = new PdfPCell(new Paragraph(a1, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd3 = new PdfPCell(new Paragraph(re.getDescripcion(), FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd4 = new PdfPCell(new Paragraph(a2, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd5 = new PdfPCell(new Paragraph(a3, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd6 = new PdfPCell(new Paragraph(a4, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

            tablaProd1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            tablaProd4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            tablaProd.addCell(tablaProd1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd6).setBorder(PdfPCell.NO_BORDER);

            pdf.add(tablaProd);
//            
        }
        for (int i = 1; i < 41 - coun; i++) {
            PdfPTable tablaProd = new PdfPTable(6);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            //String nro_lin = String.valueOf(i + coun);
            PdfPCell tP1 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP3 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP5 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP6 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            tP1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd.addCell(tP1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP6).setBorder(PdfPCell.NO_BORDER);
            pdf.add(tablaProd);
//            
//            pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        }

        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        String totFc = df.format(iv.getTotal());
        String impu = df.format(iv.getImpuesto());
        String f_venc_cae = sdf.format(iv.getFechaCae());
        String cae_nro = dfc.format(iv.getCae());
        if (iv.getImpuesto() > 0.00) {
            impu = "Total Impuesto: " + df.format(iv.getImpuesto());
        }

        String tpd = iv.getCliente().getTipo().toString();
        String vto = sdf2.format(iv.getFechaCae());
        String cuit1;
        tpd = "80";
        if (tpd.equals("96")) {
            cuit1 = "00000000000";//cui.trim();
            tpd = "96";
        } else {
            cuit1 = "00000000000";//cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
        }
        int x = 0;
        if (tpd.equals("96")) {
//            String s = "0000000000" + cuit1;
//            int lar = s.length();
//            cuit1 = s.substring(lar - 11, lar);
        }

        Integer suma1 = 0;
        Integer suma2 = 0;
        String sucu = dfs.format(iv.getNumeroSucursal());
        String cae = iv.getCae().toString();
        int largo = cae.length();
        String txtCadenaRP = "";
        String t1 = "";
        String t2 = "";
        if (t1 != null) {
            t1 = iv.getTextoPieFactura1();
        }
        if (t2 != null) {
            t2 = iv.getTextoPieFactura2();
        }
        if (largo > 8) {
//            String cadena = cuit1 + "0" + tpd + sucu + cae + vto;
//            for (int i = 0; i < 39; i++) {
//                if (x == 0) {
//                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
//                    suma1 += num;
//                    x = 1;
//                } else {
//                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
//                    suma2 += num;
//                    x = 0;
//                }
//            }
//            suma1 = suma1 * 3;
//            int total = suma1 + suma2;
//            int dv = (int) (rint(total / 10 + .9) * 10);
//            dv = dv - total;
//            cadena += String.valueOf(dv);

//            for (int i = 0; i < 40; i = i + 2) {
//                String charNum = cadena.substring(i, i + 2);
//                int numChar = Integer.valueOf(charNum);
//                if (numChar < 50) {
//                    numChar += 48;
//                } else {
//                    numChar += 142;
//                }
//                char c = (char) numChar;
//                txtCadenaRP = txtCadenaRP + c;
//            }
        }
        //txtCadenaRP = (char) 40 + txtCadenaRP + (char) 41;

        PdfPTable pieFc = new PdfPTable(2);
        pieFc.setWidthPercentage(100);
        PdfPCell pieFc1 = new PdfPCell(new Paragraph(impu, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc2 = new PdfPCell(new Paragraph(" TOTAL: " + totFc, FontFactory.getFont("arial", 10, Font.BOLD, NEGRO)));
        PdfPCell pieFc3 = new PdfPCell(new Paragraph(" VENCIMIENTO CAE: " + f_venc_cae, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc4 = new PdfPCell(new Paragraph(t1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc5 = new PdfPCell(new Paragraph(" CAE " + cae_nro, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc6 = new PdfPCell(new Paragraph(t2, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        Image imagen = Image.getInstance("c:/qr/CodigoQR" + nr0 + ".png");
        Image img2 = Image.getInstance("c:/qr/afip.png");

        pieFc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pieFc4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pieFc5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pieFc6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        pieFc7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        pieFc8.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        pieFc.addCell(pieFc1).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc2).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc3).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc4).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc5).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc6).setBorder(PdfPCell.NO_BORDER);

        pdf.add(pieFc);

        imagen.setAbsolutePosition(10f, 50f);
        img2.setAbsolutePosition(230f, 140f);
        pdf.add(imagen);
        pdf.add(img2);

        pdf.close();
        System.out.println(ficheroPdf);
        return new File(fileNameFormatted);
    }

    public File armarNcA(Cliente cli, IvaVentas iv, List<RenglonNotaCredito> rf) throws FileNotFoundException, DocumentException, Exception {
        String nr0 = dfn.format(iv.getNumeroFactura());
        String fileNameFormatted = getFileNameFormattedNc(iv);
        Document pdf = new Document();
        Rectangle hojaA4 = new Rectangle((float) 690, (float) 990);//890
        pdf.setPageSize(hojaA4);
        pdf.setMargins(10, 50, 30, 30);
        pdf.setMarginMirroringTopBottom(true);

        FileOutputStream ficheroPdf = new FileOutputStream(fileNameFormatted);

        PdfWriter writer = PdfWriter.getInstance(pdf, ficheroPdf);
        writer.setInitialLeading(20);

        // ABRO EL DOCUMENTO
        pdf.open();

        // INICIO ENCABEZADO FACTURA
        String cod = " 3";;
        String fex = sdf.format(iv.getFecha());
        String nro = "A_"
                + dfs.format(iv.getNumeroSucursal()) + "-"
                + dfn.format(iv.getNumeroFactura());
        PdfPTable encabezado = new PdfPTable(3);
        encabezado.setWidthPercentage(100);
        PdfPCell celdaEncabezado1 = new PdfPCell(new Paragraph("DISTRIBUIDORA A & M", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado2 = new PdfPCell(new Paragraph("A", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado3 = new PdfPCell(new Paragraph("NOTA DE CREDITO", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado4 = new PdfPCell(new Paragraph("Av. San Martin 3284", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado5 = new PdfPCell(new Paragraph("Cod.nro:" + cod, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado6 = new PdfPCell(new Paragraph(nro, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado7 = new PdfPCell(new Paragraph("1678 - Caseros Prov.Buenos Aires", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado8 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado9 = new PdfPCell(new Paragraph("Fecha: " + fex, FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado10 = new PdfPCell(new Paragraph("Tel: 4759-6677 - mail: distaym@yahoo.com.ar", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado11 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado12 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado13 = new PdfPCell(new Paragraph("CUIT:20-12412758-1 inic.activ.18/04/2005", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado14 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado15 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado16 = new PdfPCell(new Paragraph("IVA - RESPONSABLE INSCRIPTO", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado17 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado18 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

        celdaEncabezado1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado9.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado10.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado12.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado13.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado15.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado16.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado17.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado18.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        encabezado.addCell(celdaEncabezado1).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado2).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado3).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado4).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado5).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado6).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado7).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado8).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado9).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado10).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado11).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado12).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado13).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado14).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado15).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado16).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado17).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado18).setBorder(PdfPCell.NO_BORDER);

        pdf.add(encabezado);
        // FIN ENCABEZADO

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        String nombre = cli.getRazonSocial();
        String direcc = cli.getDomicilio().getCalle();
        if (iv.getCliente().getDomicilio().getNumero() != null) {
            direcc += " " + iv.getCliente().getDomicilio().getNumero();
        }
        if (iv.getCliente().getDomicilio().getLocalidad() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getLocalidad();
        }
        if (iv.getCliente().getDomicilio().getProvincia() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getProvincia();
        }
        String cod_cli = iv.getCliente().getCodigo().toString();
        String cui = iv.getCliente().getCuit();
        String cat_iva;
        if (iv.getCliente().getCategoriaDeIva().equals(1)) {
            cat_iva = "Responsable Inscripto";
        } else {
            if (iv.getCliente().getCategoriaDeIva().equals(2)) {
                cat_iva = "Responsable Monotributo";
            } else {
                if (iv.getCliente().getCategoriaDeIva().equals(3)) {
                    cat_iva = "Responsable Exento";
                } else {
                    cat_iva = "Consumidor Final";
                }
            }
        }
        PdfPTable client = new PdfPTable(2);
        client.setWidthPercentage(100);
        PdfPCell celdaClient1 = new PdfPCell(new Paragraph("   RAZON SOCIAL: " + nombre, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient2 = new PdfPCell(new Paragraph(" " + cod_cli, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient3 = new PdfPCell(new Paragraph("   DOMICILIO: " + direcc, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient5 = new PdfPCell(new Paragraph("   C.U.I.T.: " + cui, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient6 = new PdfPCell(new Paragraph("   IVA: " + cat_iva, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaClient1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

        client.addCell(celdaClient1).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient2).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient3).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient4).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient5).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(client);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        // pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        Integer col = 8;
        PdfPTable detalle = new PdfPTable(col);
        detalle.setWidthPercentage(100);

        float[] anchos = new float[8];
        anchos[0] = 30;
        anchos[1] = 30;
        anchos[2] = 290;
        anchos[3] = 60;
        anchos[4] = 70;
        anchos[5] = 70;
        anchos[6] = 80;
        anchos[7] = 60;

        PdfPTable tablaDetalle = new PdfPTable(col);
        tablaDetalle.setWidthPercentage(100);
        tablaDetalle.setWidths(anchos);

        PdfPCell celdaTitulos1 = new PdfPCell(new Paragraph("IT", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos2 = new PdfPCell(new Paragraph("CA", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos3 = new PdfPCell(new Paragraph("DETALLE", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos4 = new PdfPCell(new Paragraph("PRECIO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos5 = new PdfPCell(new Paragraph("GRAVADO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos6 = new PdfPCell(new Paragraph("IMPUESTO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos7 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos8 = new PdfPCell(new Paragraph("SUGERIDO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaTitulos1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaTitulos4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

        tablaDetalle.addCell(celdaTitulos1).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos2).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos3).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos4).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos5).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos6).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos7).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos8).setBorder(PdfPCell.NO_BORDER);
        pdf.add(tablaDetalle);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 5, PLAIN, FONDO_BLANCO)));

        int coun = 0;
        for (RenglonNotaCredito re : rf) {
            PdfPTable tablaProd = new PdfPTable(col);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            coun += 1;
            String c_nt = String.valueOf(coun);
            //String c_ntd = String.valueOf(re.getCantidad());

            String prec = df.format(re.getTotal() / re.getCantidad());
            String can = dfc.format(re.getCantidad());
            String grav = df.format(re.getGravado());
            String impu = df.format(re.getImpuesto());
            String tota = df.format(re.getTotal());
            String suge = df.format(re.getSugerido());
            String a1 = can;
            String a2 = prec;
            String a3 = tota;
            String a4 = suge;
            String a5 = grav;
            String a6 = impu;
            PdfPCell tablaProd1 = new PdfPCell(new Paragraph(c_nt, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd2 = new PdfPCell(new Paragraph(a1, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd3 = new PdfPCell(new Paragraph(re.getDescripcion(), FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd4 = new PdfPCell(new Paragraph(a2, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd5 = new PdfPCell(new Paragraph(a5, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd6 = new PdfPCell(new Paragraph(a6, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd7 = new PdfPCell(new Paragraph(a3, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd8 = new PdfPCell(new Paragraph(a4, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

            tablaProd1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            tablaProd4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            tablaProd.addCell(tablaProd1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd6).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd7).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd8).setBorder(PdfPCell.NO_BORDER);

            pdf.add(tablaProd);
//            
        }
        for (int i = 1; i < 41 - coun; i++) {
            PdfPTable tablaProd = new PdfPTable(8);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            PdfPCell tP1 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP3 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP5 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP6 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP7 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP8 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            tP1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd.addCell(tP1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP6).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP7).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP8).setBorder(PdfPCell.NO_BORDER);
            pdf.add(tablaProd);
//            
//            pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        }

        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        PdfPTable pieToFc = new PdfPTable(5);
        pieToFc.setWidthPercentage(100);
        PdfPCell pieToFc1 = new PdfPCell(new Paragraph("SUBTOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc3 = new PdfPCell(new Paragraph("IMPUESTO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc4 = new PdfPCell(new Paragraph("IVA", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc5 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        pieToFc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc.addCell(pieToFc1).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc2).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc3).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc4).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc5).setBorder(PdfPCell.NO_BORDER);
        pdf.add(pieToFc);
        PdfPTable pieFc = new PdfPTable(5);
        pieFc.setWidthPercentage(100);
        String a1 = df.format(iv.getGravado());
        String a2 = df.format(iv.getImpuesto());
        String a3 = df.format(iv.getIva());
        String a4 = df.format(iv.getTotal());
        PdfPCell pieFc1 = new PdfPCell(new Paragraph(a1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc3 = new PdfPCell(new Paragraph(a2, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc4 = new PdfPCell(new Paragraph(a3, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc5 = new PdfPCell(new Paragraph(a4, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        pieFc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc.addCell(pieFc1).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc2).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc3).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc4).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc5).setBorder(PdfPCell.NO_BORDER);
        pdf.add(pieFc);

        PdfPTable pie2Fc = new PdfPTable(2);
        pie2Fc.setWidthPercentage(100);

        String f_venc_cae = sdf.format(iv.getFechaCae());
        String cae_nro = dfc.format(iv.getCae());
        String tpd = iv.getCliente().getTipo().toString();
        String vto = sdf2.format(iv.getFechaCae());
        String cuit1;
        tpd = "80";
        if (tpd.equals("96")) {
            cuit1 = cui.trim();
            tpd = "96";
        } else {
            cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
        }
        int x = 0;
        if (tpd.equals("96")) {
            String s = "0000000000" + cuit1;
            int lar = s.length();
            cuit1 = s.substring(lar - 11, lar);
        }

        Integer suma1 = 0;
        Integer suma2 = 0;
        String sucu = dfs.format(iv.getNumeroSucursal());
        String cae = iv.getCae().toString();
        int largo = cae.length();
        String txtCadenaRP = "";
        String t1 = "";
        String t2 = "";
        if (t1 != null) {
            t1 = iv.getTextoPieFactura1();
        }
        if (t2 != null) {
            t2 = iv.getTextoPieFactura2();
        }
        if (largo > 8) {
            String cadena = cuit1 + "0" + tpd + sucu + cae + vto;
            for (int i = 0; i < 39; i++) {
                if (x == 0) {
                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                    suma1 += num;
                    x = 1;
                } else {
                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                    suma2 += num;
                    x = 0;
                }
            }
            suma1 = suma1 * 3;
            int total = suma1 + suma2;
            int dv = (int) (rint(total / 10 + .9) * 10);
            dv = dv - total;
            cadena += String.valueOf(dv);

            for (int i = 0; i < 40; i = i + 2) {
                String charNum = cadena.substring(i, i + 2);
                int numChar = Integer.valueOf(charNum);
                if (numChar < 50) {
                    numChar += 48;
                } else {
                    numChar += 142;
                }
                char c = (char) numChar;
                txtCadenaRP = txtCadenaRP + c;
            }
        }
        txtCadenaRP = (char) 40 + txtCadenaRP + (char) 41;

        PdfPCell pie2Fc3 = new PdfPCell(new Paragraph("  Fecha Vencimiento CAE: " + f_venc_cae, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pie2Fc4 = new PdfPCell(new Paragraph(t1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pie2Fc5 = new PdfPCell(new Paragraph("  CAE Nro.: " + cae_nro, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pie2Fc6 = new PdfPCell(new Paragraph(t2, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        pie2Fc3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pie2Fc4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pie2Fc5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pie2Fc6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        pie2Fc.addCell(pie2Fc3).setBorder(PdfPCell.NO_BORDER);
        pie2Fc.addCell(pie2Fc4).setBorder(PdfPCell.NO_BORDER);
        pie2Fc.addCell(pie2Fc5).setBorder(PdfPCell.NO_BORDER);
        pie2Fc.addCell(pie2Fc6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(pie2Fc);
        Image imagen = Image.getInstance("c:/qr/CodigoQR" + nr0 + ".png");
        Image img2 = Image.getInstance("c:/qr/afip.png");
        imagen.setAbsolutePosition(10f, 50f);
        img2.setAbsolutePosition(230f, 140f);
        pdf.add(imagen);
        pdf.add(img2);

        pdf.close();
        return new File(fileNameFormatted);
    }

    public File armarNcB(Cliente cli, IvaVentas iv, List<RenglonNotaCredito> rf) throws FileNotFoundException, DocumentException, Exception {
        String nr0 = dfn.format(iv.getNumeroFactura());
        String fileNameFormatted = getFileNameFormattedNc(iv);
        Document pdf = new Document();
        Rectangle hojaA4 = new Rectangle((float) 690, (float) 990);//890
        pdf.setPageSize(hojaA4);
        pdf.setMargins(10, 30, 30, 30);
        pdf.setMarginMirroringTopBottom(true);

        //FileOutputStream ficheroPdf = new FileOutputStream("c:/pdf/" + fileNameFormatted + ".pdf");
        FileOutputStream ficheroPdf = new FileOutputStream(fileNameFormatted);

        PdfWriter writer = PdfWriter.getInstance(pdf, ficheroPdf);
        writer.setInitialLeading(20);

        // ABRO EL DOCUMENTO
        pdf.open();

        // INICIO ENCABEZADO FACTURA
        String cod = " 8";;
        String fex = sdf.format(iv.getFecha());
        String nro = "B "
                + dfs.format(iv.getNumeroSucursal()) + "-"
                + dfn.format(iv.getNumeroFactura());
        PdfPTable encabezado = new PdfPTable(3);
        encabezado.setWidthPercentage(100);
        PdfPCell celdaEncabezado1 = new PdfPCell(new Paragraph("DISTRIBUIDORA A & M", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado2 = new PdfPCell(new Paragraph("B", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado3 = new PdfPCell(new Paragraph("NOTA DE CREDITO", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado4 = new PdfPCell(new Paragraph("Av. San Martin 3284", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado5 = new PdfPCell(new Paragraph("Cod.nro:" + cod, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado6 = new PdfPCell(new Paragraph(nro, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado7 = new PdfPCell(new Paragraph("1678 - Caseros Prov.Buenos Aires", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado8 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado9 = new PdfPCell(new Paragraph("Fecha: " + fex, FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado10 = new PdfPCell(new Paragraph("Tel: 4759-6677 - mail: distaym@yahoo.com.ar", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado11 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado12 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado13 = new PdfPCell(new Paragraph("CUIT:20-12412758-1 inic.activ.18/04/2005", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado14 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado15 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado16 = new PdfPCell(new Paragraph("IVA - RESPONSABLE INSCRIPTO", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado17 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado18 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

        celdaEncabezado1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado9.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado10.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado12.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado13.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado15.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado16.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado17.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado18.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        encabezado.addCell(celdaEncabezado1).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado2).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado3).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado4).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado5).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado6).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado7).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado8).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado9).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado10).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado11).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado12).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado13).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado14).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado15).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado16).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado17).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado18).setBorder(PdfPCell.NO_BORDER);

        pdf.add(encabezado);
        // FIN ENCABEZADO

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        String nombre = cli.getRazonSocial();
        String direcc = cli.getDomicilio().getCalle();
        if (iv.getCliente().getDomicilio().getNumero() != null) {
            direcc += " " + iv.getCliente().getDomicilio().getNumero();
        }
        if (iv.getCliente().getDomicilio().getLocalidad() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getLocalidad();
        }
        if (iv.getCliente().getDomicilio().getProvincia() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getProvincia();
        }
        String cod_cli = iv.getCliente().getCodigo().toString();
        String cui = iv.getCliente().getCuit();
        String cat_iva;
        if (iv.getCliente().getCategoriaDeIva().equals(1)) {
            cat_iva = "Responsable Inscripto";
        } else {
            if (iv.getCliente().getCategoriaDeIva().equals(2)) {
                cat_iva = "Responsable Monotributo";
            } else {
                if (iv.getCliente().getCategoriaDeIva().equals(3)) {
                    cat_iva = "Responsable Exento";
                } else {
                    cat_iva = "Consumidor Final";
                }
            }
        }
        PdfPTable client = new PdfPTable(2);
        client.setWidthPercentage(100);
        PdfPCell celdaClient1 = new PdfPCell(new Paragraph("   RAZON SOCIAL: " + nombre, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient2 = new PdfPCell(new Paragraph(" " + cod_cli, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient3 = new PdfPCell(new Paragraph("   DOMICILIO: " + direcc, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient5 = new PdfPCell(new Paragraph("   C.U.I.T.: " + cui, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient6 = new PdfPCell(new Paragraph("   IVA: " + cat_iva, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaClient1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaClient6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        client.addCell(celdaClient1).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient2).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient3).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient4).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient5).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(client);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        Integer col = 6;

        PdfPTable detalle = new PdfPTable(col);
        detalle.setWidthPercentage(100);

        float[] anchos = new float[col];
        anchos[0] = 30;
        anchos[1] = 30;
        anchos[2] = 360;
        anchos[3] = 70;
        anchos[4] = 70;
        anchos[5] = 80;

        PdfPTable tablaDetalle = new PdfPTable(col);
        tablaDetalle.setWidthPercentage(100);
        tablaDetalle.setWidths(anchos);

        PdfPCell celdaTitulos1 = new PdfPCell(new Paragraph("IT", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos2 = new PdfPCell(new Paragraph("CA", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos3 = new PdfPCell(new Paragraph("DETALLE", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos4 = new PdfPCell(new Paragraph("PRECIO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos5 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos6 = new PdfPCell(new Paragraph("SUGERIDO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaTitulos1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaTitulos4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

        tablaDetalle.addCell(celdaTitulos1).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos2).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos3).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos4).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos5).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(tablaDetalle);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 5, PLAIN, FONDO_BLANCO)));

        int coun = 0;
        for (RenglonNotaCredito re : rf) {
            PdfPTable tablaProd = new PdfPTable(6);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            coun += 1;
            String c_nt = String.valueOf(coun);
            //String c_ntd = String.valueOf(re.getCantidad());
            String prec;
            if (re.getTotal() > 1) {
                prec = df.format(re.getTotal() / re.getCantidad());
            } else {
                prec = " ";
            }
            Float can = re.getCantidad();
            String tota = df.format(re.getTotal());
            String suge = df.format(re.getSugerido());
            String a1 = dfc.format(can);
            String a2 = prec;
            String a3 = tota;
            String a4 = suge;
            PdfPCell tablaProd1 = new PdfPCell(new Paragraph(c_nt, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd2 = new PdfPCell(new Paragraph(a1, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd3 = new PdfPCell(new Paragraph(re.getDescripcion(), FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd4 = new PdfPCell(new Paragraph(a2, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd5 = new PdfPCell(new Paragraph(a3, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd6 = new PdfPCell(new Paragraph(a4, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

            tablaProd1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            tablaProd4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            tablaProd.addCell(tablaProd1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd6).setBorder(PdfPCell.NO_BORDER);

            pdf.add(tablaProd);
//            
        }
        for (int i = 1; i < 41 - coun; i++) {
            PdfPTable tablaProd = new PdfPTable(6);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            //String nro_lin = String.valueOf(i + coun);
            PdfPCell tP1 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP3 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP5 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP6 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            tP1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd.addCell(tP1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP6).setBorder(PdfPCell.NO_BORDER);
            pdf.add(tablaProd);
//            
//            pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        }

        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        String totFc = df.format(iv.getTotal());
        String impu = df.format(iv.getImpuesto());
        String f_venc_cae = sdf.format(iv.getFechaCae());
        String cae_nro = dfc.format(iv.getCae());
        if (iv.getImpuesto() > 0.00) {
            impu = df.format(iv.getImpuesto());
        }

        String tpd = iv.getCliente().getTipo().toString();
        String vto = sdf2.format(iv.getFechaCae());
        String cuit1;
        tpd = "80";
//        if (tpd.equals("96")) {
//            cuit1 = "";//cui.trim();
//            tpd = "96";
//        } else {
//            cuit1 = "";//cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
//        }
        int x = 0;
        if (tpd.equals("96")) {
//            String s = "0000000000" + cuit1;
//            int lar = s.length();
//            cuit1 = s.substring(lar - 11, lar);
        }

        Integer suma1 = 0;
        Integer suma2 = 0;
        String sucu = dfs.format(iv.getNumeroSucursal());
        String cae = iv.getCae().toString();
        int largo = cae.length();
//        String txtCadenaRP = "";
        String t1 = "";
        String t2 = "";
        if (t1 != null) {
            t1 = iv.getTextoPieFactura1();
        }
        if (t2 != null) {
            t2 = iv.getTextoPieFactura2();
        }
        if (largo > 8) {
//            String cadena = cuit1 + "0" + tpd + sucu + cae + vto;
//            for (int i = 0; i < 39; i++) {
//                if (x == 0) {
//                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
//                    suma1 += num;
//                    x = 1;
//                } else {
//                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
//                    suma2 += num;
//                    x = 0;
//                }
//            }
//            suma1 = suma1 * 3;
//            int total = suma1 + suma2;
//            int dv = (int) (rint(total / 10 + .9) * 10);
//            dv = dv - total;
//            cadena += String.valueOf(dv);

//            for (int i = 0; i < 40; i = i + 2) {
//                String charNum = cadena.substring(i, i + 2);
//                int numChar = Integer.valueOf(charNum);
//                if (numChar < 50) {
//                    numChar += 48;
//                } else {
//                    numChar += 142;
//                }
//                char c = (char) numChar;
//                txtCadenaRP = txtCadenaRP + c;
//            }
        }
//        txtCadenaRP = (char) 40 + txtCadenaRP + (char) 41;

        PdfPTable pieFc = new PdfPTable(2);
        pieFc.setWidthPercentage(100);
        String st1 = impu;
        String st2 = totFc;
        PdfPCell pieFc1 = new PdfPCell(new Paragraph(" IMPUESTO: " + st1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc2 = new PdfPCell(new Paragraph(" TOTAL: " + st2, FontFactory.getFont("arial", 10, Font.BOLD, NEGRO)));
        PdfPCell pieFc3 = new PdfPCell(new Paragraph(" VENCIMIENTO CAE: " + f_venc_cae, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc4 = new PdfPCell(new Paragraph(t1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc5 = new PdfPCell(new Paragraph(" CAE Nro.:" + cae_nro, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc6 = new PdfPCell(new Paragraph(t2, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        Image imagen = Image.getInstance("c:/qr/CodigoQR" + nr0 + ".png");
        Image img2 = Image.getInstance("c:/qr/afip.png");

        pieFc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pieFc4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pieFc5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pieFc6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        pieFc7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        pieFc8.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        pieFc.addCell(pieFc1).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc2).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc3).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc4).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc5).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc6).setBorder(PdfPCell.NO_BORDER);

        pdf.add(pieFc);

        imagen.setAbsolutePosition(10f, 50f);
        img2.setAbsolutePosition(230f, 140f);
        pdf.add(imagen);
        pdf.add(img2);

        pdf.close();
        System.out.println(ficheroPdf);
        return new File(fileNameFormatted);
    }

    public File armarNdA(Cliente cli, IvaVentas iv, List<RenglonFactura> rf) throws FileNotFoundException, DocumentException, Exception {
        String nr0 = dfn.format(iv.getNumeroFactura());
        String fileNameFormatted = getFileNameFormatted(iv);
        Document pdf = new Document();
        Rectangle hojaA4 = new Rectangle((float) 690, (float) 990);//890
        pdf.setPageSize(hojaA4);
        pdf.setMargins(10, 50, 30, 30);
        pdf.setMarginMirroringTopBottom(true);

        FileOutputStream ficheroPdf = new FileOutputStream(fileNameFormatted);

        PdfWriter writer = PdfWriter.getInstance(pdf, ficheroPdf);
        writer.setInitialLeading(20);

        // ABRO EL DOCUMENTO
        pdf.open();

        // INICIO ENCABEZADO FACTURA
        String cod = " 2";;
        String fex = sdf.format(iv.getFecha());
        String nro = "A_"
                + dfs.format(iv.getNumeroSucursal()) + "-"
                + dfn.format(iv.getNumeroFactura());
        PdfPTable encabezado = new PdfPTable(3);
        encabezado.setWidthPercentage(100);
        PdfPCell celdaEncabezado1 = new PdfPCell(new Paragraph("DISTRIBUIDORA A & M", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado2 = new PdfPCell(new Paragraph("A", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado3 = new PdfPCell(new Paragraph("NOTA DE DEBITO", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado4 = new PdfPCell(new Paragraph("Av. San Martin 3284", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado5 = new PdfPCell(new Paragraph("Cod.nro:" + cod, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado6 = new PdfPCell(new Paragraph(nro, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado7 = new PdfPCell(new Paragraph("1678 - Caseros Prov.Buenos Aires", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado8 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado9 = new PdfPCell(new Paragraph("Fecha: " + fex, FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado10 = new PdfPCell(new Paragraph("Tel: 4759-6677 - mail: distaym@yahoo.com.ar", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado11 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado12 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado13 = new PdfPCell(new Paragraph("CUIT:20-12412758-1 inic.activ.18/04/2005", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado14 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado15 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado16 = new PdfPCell(new Paragraph("IVA - RESPONSABLE INSCRIPTO", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado17 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado18 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

        celdaEncabezado1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado9.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado10.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado12.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado13.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado15.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado16.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado17.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado18.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        encabezado.addCell(celdaEncabezado1).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado2).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado3).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado4).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado5).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado6).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado7).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado8).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado9).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado10).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado11).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado12).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado13).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado14).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado15).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado16).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado17).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado18).setBorder(PdfPCell.NO_BORDER);

        pdf.add(encabezado);
        // FIN ENCABEZADO

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        String nombre = cli.getRazonSocial();
        String direcc = cli.getDomicilio().getCalle();
        if (iv.getCliente().getDomicilio().getNumero() != null) {
            direcc += " " + iv.getCliente().getDomicilio().getNumero();
        }
        if (iv.getCliente().getDomicilio().getLocalidad() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getLocalidad();
        }
        if (iv.getCliente().getDomicilio().getProvincia() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getProvincia();
        }
        String cod_cli = iv.getCliente().getCodigo().toString();
        String cui = iv.getCliente().getCuit();
        String cat_iva;
        if (iv.getCliente().getCategoriaDeIva().equals(1)) {
            cat_iva = "Responsable Inscripto";
        } else {
            if (iv.getCliente().getCategoriaDeIva().equals(2)) {
                cat_iva = "Responsable Monotributo";
            } else {
                if (iv.getCliente().getCategoriaDeIva().equals(3)) {
                    cat_iva = "Responsable Exento";
                } else {
                    cat_iva = "Consumidor Final";
                }
            }
        }
        PdfPTable client = new PdfPTable(2);
        client.setWidthPercentage(100);
        PdfPCell celdaClient1 = new PdfPCell(new Paragraph("   RAZON SOCIAL: " + nombre, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient2 = new PdfPCell(new Paragraph(" " + cod_cli, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient3 = new PdfPCell(new Paragraph("   DOMICILIO: " + direcc, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient5 = new PdfPCell(new Paragraph("   C.U.I.T.: " + cui, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient6 = new PdfPCell(new Paragraph("   IVA: " + cat_iva, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaClient1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

        client.addCell(celdaClient1).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient2).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient3).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient4).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient5).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(client);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        //     pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        Integer col = 8;
        PdfPTable detalle = new PdfPTable(col);
        detalle.setWidthPercentage(100);

        float[] anchos = new float[8];
        anchos[0] = 30;
        anchos[1] = 30;
        anchos[2] = 290;
        anchos[3] = 60;
        anchos[4] = 70;
        anchos[5] = 70;
        anchos[6] = 80;
        anchos[7] = 60;

        PdfPTable tablaDetalle = new PdfPTable(col);
        tablaDetalle.setWidthPercentage(100);
        tablaDetalle.setWidths(anchos);

        PdfPCell celdaTitulos1 = new PdfPCell(new Paragraph("IT", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos2 = new PdfPCell(new Paragraph("CA", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos3 = new PdfPCell(new Paragraph("DETALLE", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos4 = new PdfPCell(new Paragraph("PRECIO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos5 = new PdfPCell(new Paragraph("GRAVADO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos6 = new PdfPCell(new Paragraph("IMPUESTO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos7 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos8 = new PdfPCell(new Paragraph("SUGERIDO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaTitulos1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaTitulos4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

        tablaDetalle.addCell(celdaTitulos1).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos2).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos3).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos4).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos5).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos6).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos7).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos8).setBorder(PdfPCell.NO_BORDER);
        pdf.add(tablaDetalle);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 5, PLAIN, FONDO_BLANCO)));

        int coun = 0;
        for (RenglonFactura re : rf) {
            PdfPTable tablaProd = new PdfPTable(col);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            coun += 1;
            String c_nt = String.valueOf(coun);
            //String c_ntd = String.valueOf(re.getCantidad());

            String prec = df.format(re.getTotal() / re.getCantidad());
            String can = dfc.format(re.getCantidad());
            String grav = df.format(re.getGravado());
            String impu = df.format(re.getImpuesto());
            String tota = df.format(re.getTotal());
            String suge = df.format(re.getSugerido());
            String a1 = can;
            String a2 = prec;
            String a3 = tota;
            String a4 = suge;
            String a5 = grav;
            String a6 = impu;
            PdfPCell tablaProd1 = new PdfPCell(new Paragraph(c_nt, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd2 = new PdfPCell(new Paragraph(a1, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd3 = new PdfPCell(new Paragraph(re.getDescripcion(), FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd4 = new PdfPCell(new Paragraph(a2, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd5 = new PdfPCell(new Paragraph(a5, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd6 = new PdfPCell(new Paragraph(a6, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd7 = new PdfPCell(new Paragraph(a3, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd8 = new PdfPCell(new Paragraph(a4, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

            tablaProd1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            tablaProd4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            tablaProd.addCell(tablaProd1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd6).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd7).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd8).setBorder(PdfPCell.NO_BORDER);

            pdf.add(tablaProd);
//            
        }
        for (int i = 1; i < 41 - coun; i++) {
            PdfPTable tablaProd = new PdfPTable(8);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            PdfPCell tP1 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP3 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP5 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP6 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP7 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP8 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            tP1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd.addCell(tP1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP6).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP7).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP8).setBorder(PdfPCell.NO_BORDER);
            pdf.add(tablaProd);
//            
//            pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        }

        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        // pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        PdfPTable pieToFc = new PdfPTable(5);
        pieToFc.setWidthPercentage(100);
        PdfPCell pieToFc1 = new PdfPCell(new Paragraph("SUBTOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc3 = new PdfPCell(new Paragraph("IMPUESTO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc4 = new PdfPCell(new Paragraph("IVA", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieToFc5 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        pieToFc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieToFc.addCell(pieToFc1).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc2).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc3).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc4).setBorder(PdfPCell.NO_BORDER);
        pieToFc.addCell(pieToFc5).setBorder(PdfPCell.NO_BORDER);
        pdf.add(pieToFc);
        PdfPTable pieFc = new PdfPTable(5);
        pieFc.setWidthPercentage(100);
        String a1 = dfc.format(iv.getGravado());
        String a2 = df.format(iv.getImpuesto());
        String a3 = df.format(iv.getIva());
        String a4 = df.format(iv.getTotal());
        PdfPCell pieFc1 = new PdfPCell(new Paragraph(a1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc3 = new PdfPCell(new Paragraph(a2, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc4 = new PdfPCell(new Paragraph(a3, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc5 = new PdfPCell(new Paragraph(a4, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        pieFc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc.addCell(pieFc1).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc2).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc3).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc4).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc5).setBorder(PdfPCell.NO_BORDER);
        pdf.add(pieFc);

        PdfPTable pie2Fc = new PdfPTable(2);
        pie2Fc.setWidthPercentage(100);

        String f_venc_cae = sdf.format(iv.getFechaCae());
        String cae_nro = dfc.format(iv.getCae());
        String tpd = iv.getCliente().getTipo().toString();
        String vto = sdf2.format(iv.getFechaCae());
        String cuit1;
        tpd = "80";
        if (tpd.equals("96")) {
            cuit1 = cui.trim();
            tpd = "96";
        } else {
            cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
        }
        int x = 0;
        if (tpd.equals("96")) {
            String s = "0000000000" + cuit1;
            int lar = s.length();
            cuit1 = s.substring(lar - 11, lar);
        }

        Integer suma1 = 0;
        Integer suma2 = 0;
        String sucu = dfs.format(iv.getNumeroSucursal());
        String cae = iv.getCae().toString();
        int largo = cae.length();
        String txtCadenaRP = "";
        String t1 = "";
        String t2 = "";
        if (t1 != null) {
            t1 = iv.getTextoPieFactura1();
        }
        if (t2 != null) {
            t2 = iv.getTextoPieFactura2();
        }
        if (largo > 8) {
            String cadena = cuit1 + "0" + tpd + sucu + cae + vto;
            for (int i = 0; i < 39; i++) {
                if (x == 0) {
                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                    suma1 += num;
                    x = 1;
                } else {
                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                    suma2 += num;
                    x = 0;
                }
            }
            suma1 = suma1 * 3;
            int total = suma1 + suma2;
            int dv = (int) (rint(total / 10 + .9) * 10);
            dv = dv - total;
            cadena += String.valueOf(dv);

            for (int i = 0; i < 40; i = i + 2) {
                String charNum = cadena.substring(i, i + 2);
                int numChar = Integer.valueOf(charNum);
                if (numChar < 50) {
                    numChar += 48;
                } else {
                    numChar += 142;
                }
                char c = (char) numChar;
                txtCadenaRP = txtCadenaRP + c;
            }
        }
        txtCadenaRP = (char) 40 + txtCadenaRP + (char) 41;

        PdfPCell pie2Fc3 = new PdfPCell(new Paragraph("  Fecha Vencimiento CAE: " + f_venc_cae, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pie2Fc4 = new PdfPCell(new Paragraph(t1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pie2Fc5 = new PdfPCell(new Paragraph("  CAE Nro.: " + cae_nro, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pie2Fc6 = new PdfPCell(new Paragraph(t2, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        pie2Fc3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pie2Fc4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pie2Fc5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pie2Fc6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        pie2Fc.addCell(pie2Fc3).setBorder(PdfPCell.NO_BORDER);
        pie2Fc.addCell(pie2Fc4).setBorder(PdfPCell.NO_BORDER);
        pie2Fc.addCell(pie2Fc5).setBorder(PdfPCell.NO_BORDER);
        pie2Fc.addCell(pie2Fc6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(pie2Fc);
        Image imagen = Image.getInstance("c:/qr/CodigoQR" + nr0 + ".png");
        Image img2 = Image.getInstance("c:/qr/afip.png");
        imagen.setAbsolutePosition(10f, 50f);
        img2.setAbsolutePosition(230f, 140f);
        pdf.add(imagen);
        pdf.add(img2);

        pdf.close();
        return new File(fileNameFormatted);
    }

    public File armarNdB(Cliente cli, IvaVentas iv, List<RenglonFactura> rf) throws FileNotFoundException, DocumentException, Exception {
        String nr0 = dfn.format(iv.getNumeroFactura());
        String fileNameFormatted = getFileNameFormattedNd(iv);
        Document pdf = new Document();
        Rectangle hojaA4 = new Rectangle((float) 690, (float) 990);//890
        pdf.setPageSize(hojaA4);
        pdf.setMargins(10, 30, 30, 30);
        pdf.setMarginMirroringTopBottom(true);

        //FileOutputStream ficheroPdf = new FileOutputStream("c:/pdf/" + fileNameFormatted + ".pdf");
        FileOutputStream ficheroPdf = new FileOutputStream(fileNameFormatted);

        PdfWriter writer = PdfWriter.getInstance(pdf, ficheroPdf);
        writer.setInitialLeading(20);

        // ABRO EL DOCUMENTO
        pdf.open();

        // INICIO ENCABEZADO FACTURA
        String cod = " 7";;
        String fex = sdf.format(iv.getFecha());
        String nro = "B "
                + dfs.format(iv.getNumeroSucursal()) + "-"
                + dfn.format(iv.getNumeroFactura());
        PdfPTable encabezado = new PdfPTable(3);
        encabezado.setWidthPercentage(100);
        PdfPCell celdaEncabezado1 = new PdfPCell(new Paragraph("DISTRIBUIDORA A & M", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado2 = new PdfPCell(new Paragraph("B", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado3 = new PdfPCell(new Paragraph("NOTA DE DEBITO", FontFactory.getFont("arial", 9, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado4 = new PdfPCell(new Paragraph("Av. San Martin 3284", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado5 = new PdfPCell(new Paragraph("Cod.nro:" + cod, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado6 = new PdfPCell(new Paragraph(nro, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado7 = new PdfPCell(new Paragraph("1678 - Caseros Prov.Buenos Aires", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado8 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado9 = new PdfPCell(new Paragraph("Fecha: " + fex, FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado10 = new PdfPCell(new Paragraph("Tel: 4759-6677 - mail: distaym@yahoo.com.ar", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado11 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado12 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado13 = new PdfPCell(new Paragraph("CUIT:20-12412758-1 inic.activ.18/04/2005", FontFactory.getFont("arial", 8, Font.BOLD, NEGRO)));
        PdfPCell celdaEncabezado14 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado15 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado16 = new PdfPCell(new Paragraph("IVA - RESPONSABLE INSCRIPTO", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado17 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaEncabezado18 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

        celdaEncabezado1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado9.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado10.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado12.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado13.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaEncabezado15.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado16.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado17.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaEncabezado18.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        encabezado.addCell(celdaEncabezado1).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado2).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado3).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado4).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado5).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado6).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado7).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado8).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado9).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado10).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado11).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado12).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado13).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado14).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado15).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado16).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado17).setBorder(PdfPCell.NO_BORDER);
        encabezado.addCell(celdaEncabezado18).setBorder(PdfPCell.NO_BORDER);

        pdf.add(encabezado);
        // FIN ENCABEZADO

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        String nombre = cli.getRazonSocial();
        String direcc = cli.getDomicilio().getCalle();
        if (iv.getCliente().getDomicilio().getNumero() != null) {
            direcc += " " + iv.getCliente().getDomicilio().getNumero();
        }
        if (iv.getCliente().getDomicilio().getLocalidad() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getLocalidad();
        }
        if (iv.getCliente().getDomicilio().getProvincia() != null) {
            direcc += " - " + iv.getCliente().getDomicilio().getProvincia();
        }
        String cod_cli = iv.getCliente().getCodigo().toString();
        String cui = iv.getCliente().getCuit();
        String cat_iva;
        if (iv.getCliente().getCategoriaDeIva().equals(1)) {
            cat_iva = "Responsable Inscripto";
        } else {
            if (iv.getCliente().getCategoriaDeIva().equals(2)) {
                cat_iva = "Responsable Monotributo";
            } else {
                if (iv.getCliente().getCategoriaDeIva().equals(3)) {
                    cat_iva = "Responsable Exento";
                } else {
                    cat_iva = "Consumidor Final";
                }
            }
        }
        PdfPTable client = new PdfPTable(2);
        client.setWidthPercentage(100);
        PdfPCell celdaClient1 = new PdfPCell(new Paragraph("   RAZON SOCIAL: " + nombre, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient2 = new PdfPCell(new Paragraph(" " + cod_cli, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient3 = new PdfPCell(new Paragraph("   DOMICILIO: " + direcc, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient5 = new PdfPCell(new Paragraph("   C.U.I.T.: " + cui, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaClient6 = new PdfPCell(new Paragraph("   IVA: " + cat_iva, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaClient1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celdaClient4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaClient5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaClient6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        client.addCell(celdaClient1).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient2).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient3).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient4).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient5).setBorder(PdfPCell.NO_BORDER);
        client.addCell(celdaClient6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(client);

        // pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        Integer col = 6;

        PdfPTable detalle = new PdfPTable(col);
        detalle.setWidthPercentage(100);

        float[] anchos = new float[col];
        anchos[0] = 30;
        anchos[1] = 30;
        anchos[2] = 360;
        anchos[3] = 70;
        anchos[4] = 70;
        anchos[5] = 80;

        PdfPTable tablaDetalle = new PdfPTable(col);
        tablaDetalle.setWidthPercentage(100);
        tablaDetalle.setWidths(anchos);

        PdfPCell celdaTitulos1 = new PdfPCell(new Paragraph("IT", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos2 = new PdfPCell(new Paragraph("CA", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos3 = new PdfPCell(new Paragraph("DETALLE", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos4 = new PdfPCell(new Paragraph("PRECIO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos5 = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell celdaTitulos6 = new PdfPCell(new Paragraph("SUGERIDO", FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        celdaTitulos1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celdaTitulos4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celdaTitulos6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

        tablaDetalle.addCell(celdaTitulos1).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos2).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos3).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos4).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos5).setBorder(PdfPCell.NO_BORDER);
        tablaDetalle.addCell(celdaTitulos6).setBorder(PdfPCell.NO_BORDER);
        pdf.add(tablaDetalle);

        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 5, PLAIN, FONDO_BLANCO)));

        int coun = 0;
        for (RenglonFactura re : rf) {
            PdfPTable tablaProd = new PdfPTable(6);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            coun += 1;
            String c_nt = String.valueOf(coun);
            //String c_ntd = String.valueOf(re.getCantidad());
            String prec;
            if (re.getTotal() > 1) {
                prec = df.format(re.getTotal() / re.getCantidad());
            } else {
                prec = " ";
            }
            Float can = re.getCantidad();
            String tota = df.format(re.getTotal());
            String suge = df.format(re.getSugerido());
            String a1 = dfc.format(can);
            String a2 = prec;
            String a3 = tota;
            String a4 = suge;
            PdfPCell tablaProd1 = new PdfPCell(new Paragraph(c_nt, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd2 = new PdfPCell(new Paragraph(a1, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd3 = new PdfPCell(new Paragraph(re.getDescripcion(), FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd4 = new PdfPCell(new Paragraph(a2, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd5 = new PdfPCell(new Paragraph(a3, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tablaProd6 = new PdfPCell(new Paragraph(a4, FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));

            tablaProd1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            tablaProd4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            tablaProd.addCell(tablaProd1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tablaProd6).setBorder(PdfPCell.NO_BORDER);

            pdf.add(tablaProd);
//            
        }
        for (int i = 1; i < 41 - coun; i++) {
            PdfPTable tablaProd = new PdfPTable(6);
            tablaProd.setWidthPercentage(100);
            tablaProd.setWidths(anchos);
            //String nro_lin = String.valueOf(i + coun);
            PdfPCell tP1 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP2 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP3 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP4 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP5 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            PdfPCell tP6 = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 8, Font.PLAIN, NEGRO)));
            tP1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tP6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tablaProd.addCell(tP1).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP2).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP3).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP4).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP5).setBorder(PdfPCell.NO_BORDER);
            tablaProd.addCell(tP6).setBorder(PdfPCell.NO_BORDER);
            pdf.add(tablaProd);
//            
//            pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        }

        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 8, PLAIN, FONDO_BLANCO)));
        //pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));
        pdf.add(new Paragraph("ESPACIO EN BLANCO", FontFactory.getFont("arial", 6, PLAIN, FONDO_BLANCO)));

        String totFc = df.format(iv.getTotal());
        String impu = df.format(iv.getImpuesto());
        String f_venc_cae = sdf.format(iv.getFechaCae());
        String cae_nro = dfc.format(iv.getCae());
        if (iv.getImpuesto() > 0.00) {
            impu = "Total Impuesto: " + df.format(iv.getImpuesto());
        }

        String tpd = iv.getCliente().getTipo().toString();
        String vto = sdf2.format(iv.getFechaCae());
        String cuit1;
        tpd = "80";
        if (tpd.equals("96")) {
            cuit1 = "00000000000";//cui.trim();
            tpd = "96";
        } else {
            cuit1 = "00000000000";//cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
        }
        int x = 0;
        if (tpd.equals("96")) {
//            String s = "0000000000" + cuit1;
//            int lar = s.length();
//            cuit1 = s.substring(lar - 11, lar);
        }

        Integer suma1 = 0;
        Integer suma2 = 0;
        String sucu = dfs.format(iv.getNumeroSucursal());
        String cae = iv.getCae().toString();
        int largo = cae.length();
        String txtCadenaRP = "";
        String t1 = "";
        String t2 = "";
        if (t1 != null) {
            t1 = iv.getTextoPieFactura1();
        }
        if (t2 != null) {
            t2 = iv.getTextoPieFactura2();
        }
        if (largo > 8) {
//            String cadena = cuit1 + "0" + tpd + sucu + cae + vto;
//            for (int i = 0; i < 39; i++) {
//                if (x == 0) {
//                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
//                    suma1 += num;
//                    x = 1;
//                } else {
//                    int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
//                    suma2 += num;
//                    x = 0;
//                }
//            }
//            suma1 = suma1 * 3;
//            int total = suma1 + suma2;
//            int dv = (int) (rint(total / 10 + .9) * 10);
//            dv = dv - total;
//            cadena += String.valueOf(dv);

//            for (int i = 0; i < 40; i = i + 2) {
//                String charNum = cadena.substring(i, i + 2);
//                int numChar = Integer.valueOf(charNum);
//                if (numChar < 50) {
//                    numChar += 48;
//                } else {
//                    numChar += 142;
//                }
//                char c = (char) numChar;
//                txtCadenaRP = txtCadenaRP + c;
//            }
        }
        //txtCadenaRP = (char) 40 + txtCadenaRP + (char) 41;

        PdfPTable pieFc = new PdfPTable(2);
        pieFc.setWidthPercentage(100);
        PdfPCell pieFc1 = new PdfPCell(new Paragraph(" IMPUESTO: " + impu, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc2 = new PdfPCell(new Paragraph(" TOTAL: " + totFc, FontFactory.getFont("arial", 10, Font.BOLD, NEGRO)));
        PdfPCell pieFc3 = new PdfPCell(new Paragraph(" VENCIMIENTO CAE: " + f_venc_cae, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc4 = new PdfPCell(new Paragraph(t1, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc5 = new PdfPCell(new Paragraph(" CAE Nro.: " + cae_nro, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));
        PdfPCell pieFc6 = new PdfPCell(new Paragraph(t2, FontFactory.getFont("arial", 9, Font.PLAIN, NEGRO)));

        Image imagen = Image.getInstance("c:/qr/CodigoQR" + nr0 + ".png");
        Image img2 = Image.getInstance("c:/qr/afip.png");

        pieFc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pieFc3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pieFc4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pieFc5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pieFc6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        pieFc7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        pieFc8.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

        pieFc.addCell(pieFc1).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc2).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc3).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc4).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc5).setBorder(PdfPCell.NO_BORDER);
        pieFc.addCell(pieFc6).setBorder(PdfPCell.NO_BORDER);

        pdf.add(pieFc);

        imagen.setAbsolutePosition(10f, 50f);
        img2.setAbsolutePosition(230f, 140f);
        pdf.add(imagen);
        pdf.add(img2);

        pdf.close();
        //System.out.println(ficheroPdf);
        return new File(fileNameFormatted);
    }
}
