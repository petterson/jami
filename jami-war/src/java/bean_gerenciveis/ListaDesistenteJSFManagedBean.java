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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import entidades.AluDesistenteEntity;
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
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petterson
 */
@Named(value = "listaDesistenteJSFManagedBean")
@SessionScoped
public class ListaDesistenteJSFManagedBean implements Serializable {

         @EJB
         GerenteSessionBean geb;
         @EJB
         OperacionalSessionBean ops;

         List<AluDesistenteEntity> list_desistente;
    
    public ListaDesistenteJSFManagedBean() {
            this.list_desistente = new ArrayList<>();
    }

    public List<AluDesistenteEntity> getList_desistente() {  
           this.list_desistente = this.buscaDesistentes();
        return list_desistente;
    }

    public void setList_desistente(List<AluDesistenteEntity> list_desistente) {
        this.list_desistente = list_desistente;
    }
    
    public List<AluDesistenteEntity> buscaDesistentes(){
           List<AlunoEntity> ufs = ops.selecioneAlunosDesistentes();   
           List<AluDesistenteEntity> el = new ArrayList<>();
           List<String> nome_ati = new ArrayList<>();
           List<String> n_a = new ArrayList<>();
            for(int i=0; i< ufs.size(); i++){
                AluDesistenteEntity al = new AluDesistenteEntity();
                al.setData_matricula(this.anoString());
                al.setNome(ufs.get(i).getPessoa().getNome());
                al.setSexo(ufs.get(i).getSexo());
                al.setSituacao(ufs.get(i).getSituacao());
                al.setEscola(ufs.get(i).getEscola().getNome_escola());
                al.setList_turma(geb.selecioneTurmasDesistente(ufs.get(i).getPessoa().getNome()));
              List<String> nome_turmas = new ArrayList<>(geb.selecioneTurmasDesistente(ufs.get(i).getPessoa().getNome()));
              for(int j=0; j< nome_turmas.size(); j++){
                  nome_ati.addAll(geb.selecioneAtividadesDesistente(nome_turmas.get(j)));
              }
               n_a = nome_ati.stream().distinct().collect(Collectors.toList());
               nome_ati.clear();
              al.setList_atividades(n_a);
              el.add(al);
          }
    return el;
    }
    
    public void imprimirLista() throws FileNotFoundException, MalformedURLException, IOException{
           PdfDocument pdf = new PdfDocument(new PdfWriter("/home/sueder/Documentos/desistente.pdf"));
           pdf.addNewPage(); 
           Document document = new Document(pdf);
           String imageFile = "/home/sueder/Imagens/Abdulbaha.jpg";
           ImageData data = ImageDataFactory.create(imageFile);
           Image img = new Image(data); 
           img.setMaxHeight(60);
           img.setMaxWidth(60);
           Paragraph header = new Paragraph()
           .add(img)
           .add("                                     Lista Desistente Por Ano")
           .setMarginBottom(20)
           .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
           .setFontSize(14)
           .setFontColor(ColorConstants.RED);
           float[] columnWidths = {90F, 40F, 40F, 40F, 90F};
           Table table = new Table(UnitValue.createPercentArray(columnWidths));
           table.setHorizontalAlignment(HorizontalAlignment.CENTER);
           PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
           Cell cell = new Cell(1, 5)
                .add(new Paragraph("Lista Desistente Por Ano")
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
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Sexo")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Situação")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Ano")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Escola")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    table.addHeaderCell(hfCell);
                } else {
                    table.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_desistente.size(); j++) {
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_desistente.get(j).getNome()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_desistente.get(j).getSexo()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_desistente.get(j).getSituacao()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDate(this.list_desistente.get(j).getData_matricula())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_desistente.get(j).getEscola()).setFontSize(10)));
        }
           //document.add(img);
           document.add(header);
           document.add(table);
           document.close();
           
           ////////////////////////////////ENVIA PARA O BROWSER////////////////////////
           
           HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();		
           File arquivo = new File("/home/sueder/Documentos/desistente.pdf");
           int tamanho = (int) arquivo.length();
           response.setContentType("application/pdf"); // tipo do conteúdo na resposta
           response.setContentLength(tamanho); // opcional. ajuda na barra de progresso
           response.setHeader("Content-Disposition", "attachment; filename=desistente.pdf");
           ServletOutputStream output = response.getOutputStream();
           Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
           FacesContext.getCurrentInstance().responseComplete();
    }
    
    public Date anoString(){
         Date hoje  = new Date();
     return hoje;
    }
    
    public String convertDate(Date e){
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    return dateFormat.format(e);
    }
    
}
