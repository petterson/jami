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
import entidades.EstuChaEntity;
import entidades.FrequenciaEntity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petterson
 */
@Named(value = "calculoFrequenciaJSFManagedBean")
@ViewScoped
public class CalculoFrequenciaJSFManagedBean implements Serializable {

          @EJB
          GerenteSessionBean geb;
          @EJB
          OperacionalSessionBean ops;
          List<EstuChaEntity> list_estchaentity;
          List<String> nome_turmas;
          String nome_turma_selecionada;
          Date data_inicio;
          Date data_final;
          int total_alunos;
          List<EstuChaEntity> es_nomes;
          boolean imprimir;
    
    public CalculoFrequenciaJSFManagedBean() {
        this.list_estchaentity = new ArrayList<>();
        this.es_nomes = new ArrayList<>();
        this.imprimir = false;
    }

    public List<EstuChaEntity> getEs_nomes() {
        return es_nomes;
    }

    public void setEs_nomes(List<EstuChaEntity> es_nomes) {
        this.es_nomes = es_nomes;
    }

    public List<EstuChaEntity> getList_estchaentity() {
        return list_estchaentity;
    }

    public void setList_estchaentity(List<EstuChaEntity> list_estchaentity) {
        this.list_estchaentity = list_estchaentity;
    }

    public List<String> getNome_turmas() {
        return geb.selecioneTurmasComFrequencia();
    }

    public void setNome_turmas(List<String> nome_turmas) {
        this.nome_turmas = nome_turmas;
    }

    public String getNome_turma_selecionada() {
        return nome_turma_selecionada;
    }

    public void setNome_turma_selecionada(String nome_turma_selecionada) {
        this.nome_turma_selecionada = nome_turma_selecionada;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public int getTotal_alunos() {
        return total_alunos;
    }

    public void setTotal_alunos(int total_alunos) {
        this.total_alunos = total_alunos;
    }

    public boolean isImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }
    
    public void buscar(){
           if(this.nome_turma_selecionada == null || data_inicio == null || data_final == null){
              FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Selecione os Canpos"));
           }else{
               this.imprimir = true;
               this.es_nomes.clear();
           String chamada = geb.selecioneChamada(nome_turma_selecionada);
           List<FrequenciaEntity> f = ops.selecioneFrequenciaCalcula(data_inicio, data_final, chamada);
           if(f.size() >0){
           for(int i=0; i<f.size(); i++){
               this.es_nomes.addAll(f.get(i).getEstudantes());
           }
           this.list_estchaentity.clear();
           List<String> nomes = geb.selecioneAlunosPorTurma(nome_turma_selecionada);
           this.total_alunos = nomes.size();
           List<EstuChaEntity> estu = new ArrayList<>();
           for(int i=0; i<nomes.size(); i++){
               for(int j=0; j<this.es_nomes.size(); j++){
                   if(nomes.get(i).equals(this.es_nomes.get(j).getNome_estunte())){
                      estu.add(this.es_nomes.get(j));
               }else{

               }
              }
               EstuChaEntity es = new EstuChaEntity();
               es.setNome_estunte(nomes.get(i));
               es.setData_cadastro(this.es_nomes.get(i).getData_cadastro());
               es.setNome_chamada(this.calculaFrequemcia2(estu));
               this.list_estchaentity.add(es);
               estu.clear();
           }
        }else{
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não tem Frequência nessa data"));
           }
           }
    }
    
    public String calculaFrequemcia2(List<EstuChaEntity> e){
           int total=0;
           String f="";
           for(int i=0; i<e.size(); i++){
               if(e.get(i).isPresenca()){
                  total +=1;
               }else{
                   total +=0;
               }
           }
           String s=" %";
           if(e.size()>0){
           double r = (total * 100)/e.size();
           String v = String.valueOf(r);
           String t = v+"00";
           String p = t.substring(0, 4);
           f = p+s;
           }else{
               return "0.00";
           }
    return f;
    }
    
    public void imprimir() throws FileNotFoundException, MalformedURLException, IOException{
           if(this.nome_turma_selecionada == null || data_inicio == null || data_final == null){
              FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Selecione os Canpos"));
           }else{
            PdfDocument pdf = new PdfDocument(new PdfWriter("/home/sueder/Documentos/frequencia.pdf"));
           pdf.addNewPage(); 
           Document document = new Document(pdf);
           String imageFile = "/home/sueder/Imagens/Abdulbaha.jpg";
           ImageData data = ImageDataFactory.create(imageFile);
           Image img = new Image(data); 
           img.setMaxHeight(60);
           img.setMaxWidth(60);
           Paragraph header = new Paragraph()
           .add(img)
           .add("               Frequência dos Alunos na: "+ this.nome_turma_selecionada)
           .setMarginBottom(20)
           .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
           .setFontSize(14)
           .setFontColor(ColorConstants.RED);
           float[] columnWidths = {100F, 100F, 100F};
           Table table = new Table(UnitValue.createPercentArray(columnWidths));
           table.setHorizontalAlignment(HorizontalAlignment.CENTER);
           PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
           Cell cell = new Cell(1, 3)
                .add(new Paragraph("Frequência dos Alunos Por Turma")
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
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("Nome")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("Mês")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("Frequência")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    table.addHeaderCell(hfCell);
                } else {
                    table.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_estchaentity.size(); j++) {
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_estchaentity.get(j).getNome_estunte()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_estchaentity.get(j).getData_cadastro()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_estchaentity.get(j).getNome_chamada()).setFontSize(10)));
        }
           //document.add(img);
           document.add(header);
           document.add(table);
           document.close();
           
           ////////////////////////////////ENVIA PARA O BROWSER////////////////////////
           
           HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();		
           File arquivo = new File("/home/sueder/Documentos/frequencia.pdf");
           int tamanho = (int) arquivo.length();
           response.setContentType("application/pdf"); // tipo do conteúdo na resposta
           response.setContentLength(tamanho); // opcional. ajuda na barra de progresso
           response.setHeader("Content-Disposition", "attachment; filename=frequencia.pdf");
           ServletOutputStream output = response.getOutputStream();
           Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
           FacesContext.getCurrentInstance().responseComplete();
           }
    }
    
    public void refresh(){
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}
}
