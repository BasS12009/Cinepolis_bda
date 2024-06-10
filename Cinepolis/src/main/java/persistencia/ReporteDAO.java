/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Reporte;
import excepciones.cinepolisException;
import java.awt.Font;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.text.Document;
import javax.swing.text.Element;



/**
 *
 * @author stae
 */
public class ReporteDAO  implements IReporteDAO{
    
    private IConexionBD conexionBD;
    
    public ReporteDAO(){
    }
    public ReporteDAO(IConexionBD conexionBD){
        this.conexionBD=conexionBD;
    }
    
    
    @Override
    public void generarReporte(List<Reporte> reportes, int opcion) {
        Document doc = new Document() {};
        try {
            LocalDateTime ahora = LocalDateTime.now();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

            String ahorita = ahora.format(formato);
            
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Reporte_"+ahorita+".pdf"));
            doc.open();

            // Título del PDF
            // Fuente grande y en color rojo oscuro      
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, new BaseColor(182, 0, 0));
            
            Paragraph titulo = new Paragraph("Agencia Tránsito", fontTitulo);
            // Alineación centrada
            titulo.setAlignment(Element.ALIGN_CENTER);
            // Espacio después del título
            titulo.setSpacingAfter(20);
            
            doc.add(titulo);

            if (opcion == 1) {
                Font fontSubTitulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(182, 0, 0));
                Paragraph Subtitulo = new Paragraph("Reporte de tramites de la persona: " + reportes.get(0).getNombrePersona(), fontSubTitulo);
                // Alineación a la izquierda
                Subtitulo.setAlignment(Element.ALIGN_LEFT);
                // Espacio después del título
                Subtitulo.setSpacingAfter(20);
                doc.add(Subtitulo);
            }
            if (opcion == 2) {
                Font fontSubTitulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(182, 0, 0));
                Paragraph Subtitulo = new Paragraph("Reporte de tramites por periodo", fontSubTitulo);
                // Alineación a la izquierda
                Subtitulo.setAlignment(Element.ALIGN_LEFT);
                // Espacio después del título
                Subtitulo.setSpacingAfter(20);
                doc.add(Subtitulo);
            }
            if (opcion == 3) {
                Font fontSubTitulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(182, 0, 0));
                Paragraph Subtitulo = new Paragraph("Reporte de tramites por tipo: " + reportes.get(0).getTipoTramite(), fontSubTitulo);
                // Alineación a la izquierda
                Subtitulo.setAlignment(Element.ALIGN_LEFT);
                // Espacio después del título
                Subtitulo.setSpacingAfter(20);
                doc.add(Subtitulo);
            }

            PdfPTable tabla = new PdfPTable(5);
            // Ancho de la tabla al 100% de la página
            tabla.setWidthPercentage(100);
            // Espacio antes de la tabla
            tabla.setSpacingBefore(10);

            // Encabezados de la tabla
            Font fontHeader = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
            PdfPCell cellHeader = new PdfPCell();
            // Color de fondo blanco
            cellHeader.setBackgroundColor(new BaseColor(255, 255, 255));
            // Alineación centrada
            cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            // Alineación vertical centrada
            cellHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
            // Color de borde rojo oscuro
            cellHeader.setBorderColor(new BaseColor(182, 0, 0));
            // Grosor del borde
            cellHeader.setBorderWidth(1);
            cellHeader.setPadding(5);

            cellHeader.setPhrase(new Phrase("Nombre", fontHeader));
            tabla.addCell(cellHeader);

            cellHeader.setPhrase(new Phrase("RFC", fontHeader));
            tabla.addCell(cellHeader);

            cellHeader.setPhrase(new Phrase("Tipo de trámite", fontHeader));
            tabla.addCell(cellHeader);

            cellHeader.setPhrase(new Phrase("Fecha de expedición", fontHeader));
            tabla.addCell(cellHeader);

            cellHeader.setPhrase(new Phrase("Total", fontHeader));
            
            tabla.addCell(cellHeader);

            // Contenido de la tabla
            double totalSum = 0;
            Font fontContent = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            for (Reporte reporte : reportes) {
                tabla.addCell(new Phrase(reporte.getNombrePersona(), fontContent));
                tabla.addCell(new Phrase(reporte.getRFC(), fontContent));
                tabla.addCell(new Phrase(reporte.getTipoTramite(), fontContent));

                // Convertir la fecha de Calendar a String
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                tabla.addCell(new Phrase(sdf.format(reporte.getFechaExpedicion().getTime()), fontContent));

                // Convertir el costo de double a String
                tabla.addCell(new Phrase(String.valueOf(reporte.getCosto()), fontContent));
                totalSum += reporte.getCosto();
            }
            // celda para el total
            tabla.addCell(new Phrase(" ", fontContent));
            tabla.addCell(new Phrase(" ", fontContent));
            tabla.addCell(new Phrase(" ", fontContent));
            tabla.addCell(new Phrase(" ", fontContent));
            PdfPCell cellTotal = new PdfPCell(new Phrase(String.valueOf(totalSum), fontContent));
            tabla.addCell(cellTotal);

            // Agregar caja alrededor de la tabla
            PdfContentByte cb = writer.getDirectContent();
            // Posición y tamaño de la caja
            if (opcion == 1) {
                cb.rectangle(36, 42, 523, 700 - (reportes.size() * 20 + 40));
            } else {
                cb.rectangle(36, 50, 523, 700 - (reportes.size() * 20 + 40));
            }
            // Color del borde
            cb.setColorStroke(new BaseColor(182, 0, 0));
            // Grosor del borde
            cb.setLineWidth(3);
            cb.stroke();

            doc.add(tabla);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (doc.isOpen()) {
                doc.close();
            }
        }
    }
    
}
