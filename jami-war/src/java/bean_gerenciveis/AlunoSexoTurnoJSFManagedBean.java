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
@Named(value = "alunoSexoTurnoJSFManagedBean")
@SessionScoped
public class AlunoSexoTurnoJSFManagedBean implements Serializable {

    
          @EJB
         GerenteSessionBean geb;
         @EJB
         OperacionalSessionBean ops;
         List<AlunoEntity> list_masc_manha;
         List<AlunoEntity> list_masc_tarde;
         List<AlunoEntity> list_femi_manha;
         List<AlunoEntity> list_femi_tarde;
         List<IrmaoEntity> list_irmaos;
    
    public AlunoSexoTurnoJSFManagedBean() {
           this.list_masc_manha = new ArrayList<>();
           this.list_masc_tarde = new ArrayList<>();
           this.list_femi_manha = new ArrayList<>();
           this.list_femi_tarde = new ArrayList<>();
    }
    
    public List<AlunoEntity> getList_masc_manha() {
           this.list_masc_manha = ops.selecioneAlunoMasculinoManha();
     return this.list_masc_manha;
    }

    public void setList_masc_manha(List<AlunoEntity> list_masc_manha) {
        this.list_masc_manha = list_masc_manha;
    }

    public List<AlunoEntity> getList_masc_tarde() {
           this.list_masc_tarde = ops.selecioneAlunoMasculinoTarde();
     return this.list_masc_tarde;
    }

    public void setList_masc_tarde(List<AlunoEntity> list_masc_tarde) {
        this.list_masc_tarde = list_masc_tarde;
    }

    public List<AlunoEntity> getList_femi_manha() {
           this.list_femi_manha = ops.selecioneAlunoFemininoManha();
     return this.list_femi_manha;
    }

    public void setList_femi_manha(List<AlunoEntity> list_femi_manha) {
        this.list_femi_manha = list_femi_manha;
    }

    public List<AlunoEntity> getList_femi_tarde() {
           this.list_femi_tarde = ops.selecioneAlunoFemininoTarde();
     return this.list_femi_tarde;
    }

    public void setList_femi_tarde(List<AlunoEntity> list_femi_tarde) {
        this.list_femi_tarde = list_femi_tarde;
    }
    
    public void imprimirLista() throws FileNotFoundException, MalformedURLException, IOException{
           PdfDocument pdf = new PdfDocument(new PdfWriter("/home/sueder/Documentos/aluno_sexo_turno.pdf"));
           pdf.addNewPage(); 
           Document document = new Document(pdf);
           String imageFile = "/home/sueder/Imagens/Abdulbaha.jpg";
           ImageData data = ImageDataFactory.create(imageFile);
           Image img = new Image(data); 
           img.setMaxHeight(60);
           img.setMaxWidth(60);
           Paragraph header = new Paragraph()
           .add(img)
           .add("                             Lista de Alunos Por Sexo e Turno")
           .setMarginBottom(20)
           .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
           .setFontSize(14)
           .setFontColor(ColorConstants.RED);
           
   ////////////////////TABELA MASCULINO MANHÃ////////////////////////   
   
           float[] columnWidths = {90F, 45F, 30F, 30F, 80F, 30F};        
           Table tablemm = new Table(UnitValue.createPercentArray(columnWidths));  
           tablemm.setMarginBottom(50);
           PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
           Cell cell = new Cell(1, 6)
                .add(new Paragraph("Lista de Masculino Manhã")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           tablemm.addHeaderCell(cell);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("CPF")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Sexo")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Situação")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Escola")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Turno")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    tablemm.addHeaderCell(hfCell);
                } else {
                    tablemm.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_masc_manha.size(); j++) {
            tablemm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_manha.get(j).getPessoa().getNome()).setFontSize(10)));
            tablemm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_manha.get(j).getPessoa().getCpf()).setFontSize(10)));
            tablemm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_manha.get(j).getSexo()).setFontSize(10)));
            tablemm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_manha.get(j).getSituacao()).setFontSize(10)));
            tablemm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertEscola(this.list_masc_manha.get(j).getEscola())).setFontSize(10)));
            tablemm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_manha.get(j).getTurno()).setFontSize(10)));
        }
        
        ///////////////////TABELA MASCULINO TARDE/////////////////////////////////////////////////////
        
        Table tablemt = new Table(UnitValue.createPercentArray(columnWidths)); 
        tablemt.setMarginBottom(50);
           Cell cell2 = new Cell(1, 6)
                .add(new Paragraph("Lista de Masculino Tarde")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           tablemt.addHeaderCell(cell2);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("CPF")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Sexo")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Situação")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Escola")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Turno")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    tablemt.addHeaderCell(hfCell);
                } else {
                    tablemt.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_masc_tarde.size(); j++) {
            tablemt.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_tarde.get(j).getPessoa().getNome()).setFontSize(10)));
            tablemt.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_tarde.get(j).getPessoa().getCpf()).setFontSize(10)));
            tablemt.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_tarde.get(j).getSexo()).setFontSize(10)));
            tablemt.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_tarde.get(j).getSituacao()).setFontSize(10)));
            tablemt.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertEscola(this.list_masc_tarde.get(j).getEscola())).setFontSize(10)));
            tablemt.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_masc_tarde.get(j).getTurno()).setFontSize(10)));
        }
        
        ///////////////////TABELA FEMININO MANHÃ/////////////////////////////////////////////////////
        
        Table tablefm = new Table(UnitValue.createPercentArray(columnWidths)); 
        tablefm.setMarginBottom(50);
           Cell cell3 = new Cell(1, 6)
                .add(new Paragraph("Lista de Feminino Manhã")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           tablefm.addHeaderCell(cell3);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("CPF")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Sexo")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Situação")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Escola")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Turno")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    tablefm.addHeaderCell(hfCell);
                } else {
                    tablefm.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_femi_manha.size(); j++) {
            tablefm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_manha.get(j).getPessoa().getNome()).setFontSize(10)));
            tablefm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_manha.get(j).getPessoa().getCpf()).setFontSize(10)));
            tablefm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_manha.get(j).getSexo()).setFontSize(10)));
            tablefm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_manha.get(j).getSituacao()).setFontSize(10)));
            tablefm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertEscola(this.list_femi_manha.get(j).getEscola())).setFontSize(10)));
            tablefm.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_manha.get(j).getTurno()).setFontSize(10)));
        }
        
         ///////////////////TABELA FEMININO TARDE/////////////////////////////////////////////////////
        
        Table tableft = new Table(UnitValue.createPercentArray(columnWidths)); 
        tablemt.setMarginBottom(50);
           Cell cell4 = new Cell(1, 6)
                .add(new Paragraph("Lista de Feminino Tarde")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           tableft.addHeaderCell(cell4);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("CPF")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Sexo")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Situação")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Escola")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Turno")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    tableft.addHeaderCell(hfCell);
                } else {
                    tableft.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_femi_tarde.size(); j++) {
            tableft.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_tarde.get(j).getPessoa().getNome()).setFontSize(10)));
            tableft.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_tarde.get(j).getPessoa().getCpf()).setFontSize(10)));
            tableft.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_tarde.get(j).getSexo()).setFontSize(10)));
            tableft.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_tarde.get(j).getSituacao()).setFontSize(10)));
            tableft.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertEscola(this.list_femi_tarde.get(j).getEscola())).setFontSize(10)));
            tableft.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_femi_tarde.get(j).getTurno()).setFontSize(10)));
        }
        
           //document.add(img);
           document.add(header);
           document.add(tablemm);
           document.add(tablemt);
           document.add(tablefm);
           document.add(tableft);
           document.close();
           
           ////////////////////////////////ENVIA PARA O BROWSER////////////////////////
           
           HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();		
           File arquivo = new File("/home/sueder/Documentos/aluno_sexo_turno.pdf");
           int tamanho = (int) arquivo.length();
           response.setContentType("application/pdf"); // tipo do conteúdo na resposta
           response.setContentLength(tamanho); // opcional. ajuda na barra de progresso
           response.setHeader("Content-Disposition", "attachment; filename=aluno_sexo_turno.pdf");
           ServletOutputStream output = response.getOutputStream();
           Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
           FacesContext.getCurrentInstance().responseComplete();
    }
    
    public String convertEscola(EscolaEntity e){
    return e.getNome_escola();
    }
    
}
