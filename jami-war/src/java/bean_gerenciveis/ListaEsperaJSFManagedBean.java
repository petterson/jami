/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import entidades.AlunoEntity;
import entidades.EscolaEntity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petterson
 */
@Named(value = "listaEsperaJSFManagedBean")
@SessionScoped
public class ListaEsperaJSFManagedBean implements Serializable {

         @EJB
         GerenteSessionBean geb;
         @EJB
         OperacionalSessionBean ops;
         List<AlunoEntity> list_espera; 
     
    public ListaEsperaJSFManagedBean() {
           this.list_espera = new ArrayList<>();
    }

    public List<AlunoEntity> getList_espera() {
        this.list_espera = ops.selecioneAlunosEmEspera();
    return list_espera;
    }

    public void setList_espera(List<AlunoEntity> list_espera) {
        this.list_espera = list_espera;
    }
    
    public void imprimirLista() throws FileNotFoundException, MalformedURLException, IOException{
           PdfDocument pdf = new PdfDocument(new PdfWriter("/home/sueder/Documentos/espera.pdf"));
           pdf.addNewPage(); 
           Document document = new Document(pdf);
           String imageFile = "/home/sueder/Imagens/Abdulbaha.jpg";
           ImageData data = ImageDataFactory.create(imageFile);
           Image img = new Image(data); 
           img.setMaxHeight(60);
           img.setMaxWidth(60);
           Paragraph header = new Paragraph()
           .add(img)
           .add("                                        Lista de Alunos em Espera")
           .setMarginBottom(20)
           .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
           .setFontSize(14)
           .setFontColor(ColorConstants.RED);
           float[] columnWidths = {80F, 30F, 30F, 30F, 30F, 70F, 30F};
           Table table = new Table(UnitValue.createPercentArray(columnWidths));
           
           PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
           Cell cell = new Cell(1, 7)
                .add(new Paragraph("Lista de Alunos em Espera")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           table.addHeaderCell(cell);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("D Nasc")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Sexo")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Situação")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Inscrição")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Escola")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Turno"))
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    table.addHeaderCell(hfCell);
                } else {
                    table.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_espera.size(); j++) {
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_espera.get(j).getPessoa().getNome()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDate(this.list_espera.get(j).getData_matricula())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_espera.get(j).getSexo()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_espera.get(j).getSituacao()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDate(this.list_espera.get(j).getPessoa().getData_nasc())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertEscola(this.list_espera.get(j).getEscola())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_espera.get(j).getTurno()).setFontSize(10)));
        }
           //document.add(img);
           document.add(header);
           document.add(table);
           document.close();
           
           ////////////////////////////////ENVIA PARA O BROWSER////////////////////////
           
           HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();		
           File arquivo = new File("/home/sueder/Documentos/espera.pdf");
           int tamanho = (int) arquivo.length();
           response.setContentType("application/pdf"); // tipo do conteúdo na resposta
           response.setContentLength(tamanho); // opcional. ajuda na barra de progresso
           response.setHeader("Content-Disposition", "attachment; filename=espera.pdf");
           ServletOutputStream output = response.getOutputStream();
           Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
           FacesContext.getCurrentInstance().responseComplete();
    }
    
    public String convertEscola(EscolaEntity e){
    return e.getNome_escola();
    }
    
    public String convertDate(Date e){
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    return dateFormat.format(e);
    }
}
