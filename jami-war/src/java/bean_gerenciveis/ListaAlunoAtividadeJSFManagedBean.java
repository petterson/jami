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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petterson
 */
@Named(value = "listaAlunoAtividadeJSFManagedBean")
@SessionScoped
public class ListaAlunoAtividadeJSFManagedBean implements Serializable {

         @EJB
         GerenteSessionBean geb;
         @EJB
         OperacionalSessionBean ops;
         List<AluDesistenteEntity> list_aluno_atividade;
         List<String> list_atividades;
         String nome_atividade_selecionada;
         int total_alunos;
         boolean imprimir;
    
    public ListaAlunoAtividadeJSFManagedBean() {
            this.list_aluno_atividade = new ArrayList<>();
            this.list_atividades = new ArrayList<>();
            this.nome_atividade_selecionada = "";
            this.imprimir = false;
    }

    public boolean isImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }

    public List<AluDesistenteEntity> getList_aluno_atividade() {
        return list_aluno_atividade;
    }

    public void setList_aluno_atividade(List<AluDesistenteEntity> list_aluno_atividade) {
        this.list_aluno_atividade = list_aluno_atividade;
    }

    public List<String> getList_atividades() {
        return geb.selecioneAtividades();
    }

    public void setList_atividades(List<String> list_atividades) {
        this.list_atividades = list_atividades;
    }

    public String getNome_atividade_selecionada() {
        return nome_atividade_selecionada;
    }

    public void setNome_atividade_selecionada(String nome_atividade_selecionada) {
        this.nome_atividade_selecionada = nome_atividade_selecionada;
    }

    public int getTotal_alunos() {
        return total_alunos;
    }

    public void setTotal_alunos(int total_alunos) {
        this.total_alunos = total_alunos;
    }
    
    public void imprimir(){
      this.imprimir = true;
    }
    
    public void getAtividades(AjaxBehaviorEvent event){ 
        this.imprimir = true;
          List<AluDesistenteEntity> el = new ArrayList<>();
          List<String> nome_alunos = new ArrayList<>();
          List<String> nome_turmas = geb.selecioneTurmasPorAtividades(nome_atividade_selecionada);
          for(int i=0; i<nome_turmas.size(); i++){
              nome_alunos.addAll(geb.selecioneAlunosPorTurma(nome_turmas.get(i)));
          }
          List<String> n_alu = nome_alunos.stream().distinct().collect(Collectors.toList());
              for(int j=0; j<n_alu.size(); j++){
                  AlunoEntity e = ops.selecioneAlunosPorNome(n_alu.get(j));
                  if(e.getFone_aluno().equals("")){
                     el = el;
                  }else{
                      AluDesistenteEntity al = new AluDesistenteEntity();
                      al.setData_matricula(this.mesString());
                      al.setNome(e.getPessoa().getNome());
                      al.setSexo(e.getSexo());
                      al.setSituacao(e.getSituacao());
                      al.setEscola(e.getEscola().getNome_escola());
                      al.setList_turma(nome_turmas);
                      el.add(al);
                  }
              }
        
              this.list_aluno_atividade = el;
              this.setTotal_alunos(this.list_aluno_atividade.size());
    }
    
    public void imprimirLista() throws FileNotFoundException, MalformedURLException, 
                             IOException, ServletException{
           if(this.nome_atividade_selecionada == null){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Selecione a Turma"));
           }else{
           PdfDocument pdf = new PdfDocument(new PdfWriter("/home/sueder/Documentos/aluno_atividade.pdf"));
           pdf.addNewPage(); 
           Document document = new Document(pdf);
           String imageFile = "/home/sueder/Imagens/Abdulbaha.jpg";
           ImageData data = ImageDataFactory.create(imageFile);
           Image img = new Image(data); 
           img.setMaxHeight(60);
           img.setMaxWidth(60);
           Paragraph header = new Paragraph()
           .add(img)
           .add("         Total de Alunos Por Atividades: "+this.nome_atividade_selecionada+"  Total de Alunos: "+this.total_alunos)
           .setMarginBottom(20)
           .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
           .setFontSize(14)
           .setFontColor(ColorConstants.RED);
           float[] columnWidths = {80F, 30F, 30F, 30F, 80F, 60F};
           Table table = new Table(UnitValue.createPercentArray(columnWidths));
           
           PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
           Cell cell = new Cell(1, 6)
                .add(new Paragraph("Total de Alunos Por Atividades")
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
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Mes")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Escola")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Turma"))
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    table.addHeaderCell(hfCell);
                } else {
                    table.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_aluno_atividade.size(); j++) {
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_aluno_atividade.get(j).getNome()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_aluno_atividade.get(j).getSexo()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_aluno_atividade.get(j).getSituacao()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDate(this.dataAtual())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_aluno_atividade.get(j).getEscola()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertList(this.list_aluno_atividade.get(j).getList_turma())).setFontSize(10)));
        }
           //document.add(img);
           document.add(header);
           document.add(table);
           document.close();
           }
           
           ////////////////////////////////ENVIA PARA O BROWSER////////////////////////
           
           HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();		
           File arquivo = new File("/home/sueder/Documentos/aluno_atividade.pdf");
           int tamanho = (int) arquivo.length();
           response.setContentType("application/pdf"); // tipo do conteúdo na resposta
           response.setContentLength(tamanho); // opcional. ajuda na barra de progresso
           response.setHeader("Content-Disposition", "attachment; filename=aluno_atividade.pdf");
           ServletOutputStream output = response.getOutputStream();
           Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
           FacesContext.getCurrentInstance().responseComplete();
    }
    
     public String convertInt(int in){
    
    return String.valueOf(in);    
    }
    
    public String convertEscola(EscolaEntity e){
    return e.getNome_escola();
    }
    
    public Date dataAtual(){
     return new Date();
    }
    public String convertDate(Date e){
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    return dateFormat.format(e);
    }
    
     public Date mesString(){
         Date hoje  = new Date();
     return hoje;
    }
    
    public String convertList(List<String> s){
           String n="";
           for(int i=0; i<s.size();i++){
               n = n+s.get(i)+"\n";
           }
    return n;       
    }
    
}
