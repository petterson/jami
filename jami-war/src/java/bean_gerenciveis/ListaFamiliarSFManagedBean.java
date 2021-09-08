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
import entidades.IrmaoEntity;
import entidades.MaeEntity;
import entidades.PaiEntity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petterson
 */
@Named(value = "listaFamiliarSFManagedBean")
@SessionScoped
public class ListaFamiliarSFManagedBean implements Serializable {

         @EJB
         GerenteSessionBean geb;
         @EJB
         OperacionalSessionBean ops;
         List<AlunoEntity> list_alu_docu;
         AlunoEntity a;
    
    public ListaFamiliarSFManagedBean() {
           this.a = new AlunoEntity();
           this.list_alu_docu = new ArrayList<>();
    }

    public List<AlunoEntity> getList_alu_docu() {
           this.list_alu_docu = ops.selecioneAlunosDocumentos();
        return list_alu_docu;
    }

    public void setList_alu_docu(List<AlunoEntity> list_alu_docu) {
        this.list_alu_docu = list_alu_docu;
    }

    public AlunoEntity getA() {
        return a;
    }

    public void setA(AlunoEntity a) {
        this.a = a;
    }
    
    public void imprimirLista() throws FileNotFoundException, MalformedURLException, IOException{
           PdfDocument pdf = new PdfDocument(new PdfWriter("/home/sueder/Documentos/familiar.pdf"));
           pdf.addNewPage(); 
           Document document = new Document(pdf);
           String imageFile = "/home/sueder/Imagens/Abdulbaha.jpg";
           ImageData data = ImageDataFactory.create(imageFile);
           Image img = new Image(data); 
           img.setMaxHeight(60);
           img.setMaxWidth(60);
           Paragraph header = new Paragraph()
           .add(img)
           .add("                                        Lista Familiar")
           .setMarginBottom(20)
           .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
           .setFontSize(14)
           .setFontColor(ColorConstants.RED);
           float[] columnWidths = {90F, 40F, 80F, 80F, 80F};
           Table table = new Table(UnitValue.createPercentArray(columnWidths));
           
           PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
           Cell cell = new Cell(1, 5)
                .add(new Paragraph("Lista Familiar")
                .setBackgroundColor(new DeviceGray(0.50f))
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
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Sexo")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Pai")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Mae")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Irmão")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    table.addHeaderCell(hfCell);
                } else {
                    table.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_alu_docu.size(); j++) {
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_alu_docu.get(j).getPessoa().getNome()).setFontSize(10)));
            //table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_alu_docu.get(j).getCpf_aluno()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_alu_docu.get(j).getSexo()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertPai(this.list_alu_docu.get(j).getPai())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertMae(this.list_alu_docu.get(j).getMae())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertIrmao(this.list_alu_docu.get(j).getIrmaos())).setFontSize(10)));
        }
           //document.add(img);
           document.add(header);
           document.add(table);
           document.close();
           
           ////////////////////////////////ENVIA PARA O BROWSER////////////////////////
           
           HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();		
           File arquivo = new File("/home/sueder/Documentos/familiar.pdf");
           int tamanho = (int) arquivo.length();
           response.setContentType("application/pdf"); // tipo do conteúdo na resposta
           response.setContentLength(tamanho); // opcional. ajuda na barra de progresso
           response.setHeader("Content-Disposition", "attachment; filename=familiar.pdf");
           ServletOutputStream output = response.getOutputStream();
           Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
           FacesContext.getCurrentInstance().responseComplete();
    }
    
    public String convertInt(int in){
    return String.valueOf(in);    
    }
    
    public String convertIrmao(List<IrmaoEntity> s){
           String n="";
           for(int i=0; i<s.size();i++){
               n = n+s.get(i).getPessoa().getNome()+"\n";
           }
    return n;       
    }

    public String convertMae(MaeEntity e){
    return e.getPessoa().getNome();
    }
    
    public String convertPai(PaiEntity e){
    return e.getPessoa().getNome();
    }
    
}
