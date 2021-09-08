
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import entidades.BalancoEntity;
import entidades.EntradaEntity;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author petterson
 */
@Named(value = "gerarBalanceteJSFManagedBean")
@ViewScoped
public class GerarBalanceteJSFManagedBean implements Serializable {

    @EJB
    FinanceiroSessionBean fin;
    @EJB
    GerenteSessionBean geb;
    Date data_inicial;
    Date data_final;
    double valor_percentual;
    String nome_total;
    String percentual_total;
    boolean imprimir;
    List<String> nome_descricoes;
    List<BalancoEntity> balancos;
    List<BalancoEntity> governof;
    List<BalancoEntity> governom;
    List<BalancoEntity> governoe;
    List<BalancoEntity> empresas;
    List<BalancoEntity> eventos;
    List<BalancoEntity> receitas;
    List<BalancoEntity> institutos_fundacoes;
    List<BalancoEntity> resultado;

    
    public GerarBalanceteJSFManagedBean() {
            this.nome_descricoes = new ArrayList<>();
            this.balancos = new ArrayList<>();
            this.governoe = new ArrayList<>();
            this.governof = new ArrayList<>();
            this.governom = new ArrayList<>();
            this.empresas = new ArrayList<>();
            this.eventos = new ArrayList<>();
            this.receitas = new ArrayList<>();
            this.institutos_fundacoes = new ArrayList<>();
            this.resultado = new ArrayList<>();
            this.imprimir = false;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public List<String> getNome_descricoes() {
        return nome_descricoes;
    }

    public void setNome_descricoes(List<String> nome_descricoes) {
        this.nome_descricoes = nome_descricoes;
    }

    public List<BalancoEntity> getBalancos() {
        return balancos;
    }

    public void setBalancos(List<BalancoEntity> balancos) {
        this.balancos = balancos;
    }

    public double getValor_percentual() {
        return valor_percentual;
    }

    public void setValor_percentual(double valor_percentual) {
        this.valor_percentual = valor_percentual;
    }

    public List<BalancoEntity> getGovernof() {
        return governof;
    }

    public void setGovernof(List<BalancoEntity> governof) {
        this.governof = governof;
    }

    public List<BalancoEntity> getGovernom() {
        return governom;
    }

    public void setGovernom(List<BalancoEntity> governom) {
        this.governom = governom;
    }

    public List<BalancoEntity> getGovernoe() {
        return governoe;
    }

    public void setGovernoe(List<BalancoEntity> governoe) {
        this.governoe = governoe;
    }

    public List<BalancoEntity> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<BalancoEntity> empresas) {
        this.empresas = empresas;
    }

    public List<BalancoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<BalancoEntity> eventos) {
        this.eventos = eventos;
    }

    public List<BalancoEntity> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<BalancoEntity> receitas) {
        this.receitas = receitas;
    }

    public List<BalancoEntity> getInstitutos_fundacoes() {
        return institutos_fundacoes;
    }

    public List<BalancoEntity> getResultado() {
        return resultado;
    }

    public void setResultado(List<BalancoEntity> resultado) {
        this.resultado = resultado;
    }

    public void setInstitutos_fundacoes(List<BalancoEntity> institutos_fundacoes) {
        this.institutos_fundacoes = institutos_fundacoes;
    }

    public String getNome_total() {
        return "TOTAL";
    }

    public void setNome_total(String nome_total) {
        this.nome_total = nome_total;
    }

    public String getPercentual_total() {
        return "100%";
    }

    public void setPercentual_total(String percentual_total) {
        this.percentual_total = percentual_total;
    }

    public boolean isImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }

    public void buscar(){
           if(data_inicial == null || data_final == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione as Datas", "Selecione as Datas");
              ctx.addMessage(null, msg);
            //return "preencha os campos";
        }else{
               this.imprimir = true;
               List<BalancoEntity> bl = new ArrayList<>();
               List<String> nomes = fin.selecioneNomeDescricao(data_inicial, data_final);
               this.nome_descricoes = nomes.stream().distinct().collect(Collectors.toList());
               for(int i=0; i<this.nome_descricoes.size(); i++){
               List<EntradaEntity> l = fin.selecioneBalancoEntrada(data_inicial, data_final, this.nome_descricoes.get(i));
               BalancoEntity b = new BalancoEntity();
               b.setNome(this.nome_descricoes.get(i));
               b.setValor(this.calculaValor(l));
               b.setValor_sttring(this.convertDoubleValor(this.calculaValor(l)));
               this.valor_percentual += b.getValor();
               bl.add(b);
               }
               List<BalancoEntity> bala = new ArrayList<>();
               BalancoEntity balanco = new BalancoEntity();
               balanco.setNome(this.getNome_total());
               balanco.setPercentual(this.getPercentual_total());
               balanco.setValor(this.valor_percentual);
               balanco.setValor_sttring(this.convertDoubleValor(this.valor_percentual));
               this.resultado.add(balanco);
               bala = this.calculaPercentual(bl, this.valor_percentual);   
               this.defineTabela(bala);
           }
    }
    
    public void imprimirLista() throws FileNotFoundException, MalformedURLException, IOException{
           PdfDocument pdf = new PdfDocument(new PdfWriter("/home/sueder/Documentos/balanco.pdf"));
           pdf.addNewPage(); 
           Document document = new Document(pdf);
           String imageFile = "/home/sueder/Imagens/Abdulbaha.jpg";
           ImageData data = ImageDataFactory.create(imageFile);
           Image img = new Image(data); 
           img.setMaxHeight(60);
           img.setMaxWidth(60);
           Paragraph header = new Paragraph()
           .add(img)
           .add("                                             Balanço Anual")
           .setMarginBottom(20)
           .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
           .setFontSize(14)
           .setFontColor(ColorConstants.RED);
           
    ///////////////GOVERNO MUNICIPAL////////////////////////////////
           
           float[] columnWidths = {100F, 100F, 100F};
           Table t0 = new Table(UnitValue.createPercentArray(columnWidths));
           t0.setMarginBottom(20);
           t0.setHorizontalAlignment(HorizontalAlignment.CENTER);
           PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
           Cell cell = new Cell(1, 3)
                .add(new Paragraph("Governo Municipal")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           t0.addHeaderCell(cell);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Valores")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Percentual")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    t0.addHeaderCell(hfCell);
                } else {
                    t0.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.governom.size(); j++) {
            if(j==0){
               t0.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governom.get(j).getNome()).setFontSize(10)));
               t0.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governom.get(j).getValor_sttring()).setFontSize(10)));
               t0.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governom.get(j).getPercentual()).setFontSize(10)));
         }else{
               t0.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governom.get(j).getNome()).setFontSize(10)));
            t0.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governom.get(j).getValor_sttring()).setFontSize(10)));
            t0.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governom.get(j).getPercentual()).setFontSize(10)));
            }
        }
        
    ///////////////GOVERNO ESTADUAL////////////////////////////////
    
        Table t1 = new Table(UnitValue.createPercentArray(columnWidths));
           t1.setMarginBottom(20);
           t1.setHorizontalAlignment(HorizontalAlignment.CENTER);
           Cell cell1 = new Cell(1, 3)
                .add(new Paragraph("Governo Estadual")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           t1.addHeaderCell(cell1);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Valores")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Percentual")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    t1.addHeaderCell(hfCell);
                } else {
                    t1.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.governoe.size(); j++) {
            if(j==0){
               t1.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governoe.get(j).getNome()).setFontSize(10)));
               t1.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governoe.get(j).getValor_sttring()).setFontSize(10)));
               t1.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governoe.get(j).getPercentual()).setFontSize(10)));
         }else{
               t1.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governoe.get(j).getNome()).setFontSize(10)));
               t1.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governoe.get(j).getValor_sttring()).setFontSize(10)));
               t1.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governoe.get(j).getPercentual()).setFontSize(10)));
            }
        }
        
    ///////////////GOVERNO FEDERAL////////////////////////////////
    
        Table t2 = new Table(UnitValue.createPercentArray(columnWidths));
           t2.setMarginBottom(20);
           t2.setHorizontalAlignment(HorizontalAlignment.CENTER);
           Cell cell2 = new Cell(1, 3)
                .add(new Paragraph("Governo Federal")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           t2.addHeaderCell(cell2);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Valores")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Percentual")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    t2.addHeaderCell(hfCell);
                } else {
                    t2.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.governof.size(); j++) {
            if(j==0){
               t2.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governof.get(j).getNome()).setFontSize(10)));
               t2.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governof.get(j).getValor_sttring()).setFontSize(10)));
               t2.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governof.get(j).getPercentual()).setFontSize(10)));
         }else{
               t2.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governof.get(j).getNome()).setFontSize(10)));
               t2.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governof.get(j).getValor_sttring()).setFontSize(10)));
               t2.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.governof.get(j).getPercentual()).setFontSize(10)));
            }
        }
        
    ///////////////INSTITUTOS E FUNDAÇÕES////////////////////////////////
    
        Table t3 = new Table(UnitValue.createPercentArray(columnWidths));
           t3.setMarginBottom(20);
           t3.setHorizontalAlignment(HorizontalAlignment.CENTER);
           Cell cell3 = new Cell(1, 3)
                .add(new Paragraph("Institutos e Fundações")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           t3.addHeaderCell(cell3);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Valores")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Percentual")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    t3.addHeaderCell(hfCell);
                } else {
                    t3.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.institutos_fundacoes.size(); j++) {
            if(j==0){
               t3.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.institutos_fundacoes.get(j).getNome()).setFontSize(10)));
               t3.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.institutos_fundacoes.get(j).getValor_sttring()).setFontSize(10)));
               t3.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.institutos_fundacoes.get(j).getPercentual()).setFontSize(10)));
         }else{
               t3.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.institutos_fundacoes.get(j).getNome()).setFontSize(10)));
               t3.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.institutos_fundacoes.get(j).getValor_sttring()).setFontSize(10)));
               t3.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.institutos_fundacoes.get(j).getPercentual()).setFontSize(10)));
            }
        }
        
    ///////////////EMPRESAS PRIVADAS////////////////////////////////
    
        Table t4 = new Table(UnitValue.createPercentArray(columnWidths));
           t4.setMarginBottom(20);
           t4.setHorizontalAlignment(HorizontalAlignment.CENTER);
           Cell cell4 = new Cell(1, 3)
                .add(new Paragraph("Empresas Privadas")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           t4.addHeaderCell(cell4);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Valores")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Percentual")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    t4.addHeaderCell(hfCell);
                } else {
                    t4.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.empresas.size(); j++) {
            if(j==0){
               t4.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.empresas.get(j).getNome()).setFontSize(10)));
               t4.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.empresas.get(j).getValor_sttring()).setFontSize(10)));
               t4.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.empresas.get(j).getPercentual()).setFontSize(10)));
         }else{
               t4.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.empresas.get(j).getNome()).setFontSize(10)));
               t4.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.empresas.get(j).getValor_sttring()).setFontSize(10)));
               t4.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.empresas.get(j).getPercentual()).setFontSize(10)));
            }
        }
        
    ///////////////RECEITAS FINANCEIRAS////////////////////////////////
    
        Table t5 = new Table(UnitValue.createPercentArray(columnWidths));
           t5.setMarginBottom(20);
           t5.setHorizontalAlignment(HorizontalAlignment.CENTER);
           Cell cell5 = new Cell(1, 3)
                .add(new Paragraph("Receitas Financeiras")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           t5.addHeaderCell(cell5);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Valores")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Percentual")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    t5.addHeaderCell(hfCell);
                } else {
                    t5.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.receitas.size(); j++) {
            if(j==0){
               t5.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.receitas.get(j).getNome()).setFontSize(10)));
               t5.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.receitas.get(j).getValor_sttring()).setFontSize(10)));
               t5.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.receitas.get(j).getPercentual()).setFontSize(10)));
         }else{
               t5.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.receitas.get(j).getNome()).setFontSize(10)));
               t5.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.receitas.get(j).getValor_sttring()).setFontSize(10)));
               t5.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.receitas.get(j).getPercentual()).setFontSize(10)));
            }
        }
        
    ///////////////BAZAR E EVENTOS////////////////////////////////
    
        Table t6 = new Table(UnitValue.createPercentArray(columnWidths));
           t6.setMarginBottom(20);
           t6.setHorizontalAlignment(HorizontalAlignment.CENTER);
           Cell cell6 = new Cell(1, 3)
                .add(new Paragraph("Bazar e Eventos")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           t6.addHeaderCell(cell6);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Valores")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Percentual")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    t6.addHeaderCell(hfCell);
                } else {
                    t6.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.eventos.size(); j++) {
            if(j==0){
               t6.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.eventos.get(j).getNome()).setFontSize(10)));
               t6.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.eventos.get(j).getValor_sttring()).setFontSize(10)));
               t6.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.eventos.get(j).getPercentual()).setFontSize(10)));
         }else{
               t6.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.eventos.get(j).getNome()).setFontSize(10)));
               t6.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.eventos.get(j).getValor_sttring()).setFontSize(10)));
               t6.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.eventos.get(j).getPercentual()).setFontSize(10)));
            }
        }
        
        ///////////////PESSOAS FÍSICAS////////////////////////////////
    
        Table t7 = new Table(UnitValue.createPercentArray(columnWidths));
           t7.setMarginBottom(20);
           t7.setHorizontalAlignment(HorizontalAlignment.CENTER);
           Cell cell7 = new Cell(1, 3)
                .add(new Paragraph("Pessoas Físicas")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           t7.addHeaderCell(cell7);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Valores")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Percentual")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    t7.addHeaderCell(hfCell);
                } else {
                    t7.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.balancos.size(); j++) {
            if(j==0){
               t7.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.balancos.get(j).getNome()).setFontSize(10)));
               t7.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.balancos.get(j).getValor_sttring()).setFontSize(10)));
               t7.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.balancos.get(j).getPercentual()).setFontSize(10)));
         }else{
               t7.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.balancos.get(j).getNome()).setFontSize(10)));
               t7.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.balancos.get(j).getValor_sttring()).setFontSize(10)));
               t7.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.balancos.get(j).getPercentual()).setFontSize(10)));
            }
        }
        
    ///////////////TOTAL////////////////////////////////
    
        Table t8 = new Table(UnitValue.createPercentArray(columnWidths));
           t8.setMarginBottom(20);
           t8.setHorizontalAlignment(HorizontalAlignment.CENTER);
           Cell cell8 = new Cell(1, 3)
                .add(new Paragraph("Total")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           t8.addHeaderCell(cell8);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nome")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Valor Total")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Percentual Completo")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    t8.addHeaderCell(hfCell);
                } else {
                    t8.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.resultado.size(); j++) {
               t8.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.resultado.get(j).getNome()).setFontSize(10)));
               t8.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.resultado.get(j).getValor_sttring()).setFontSize(10)));
               t8.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.resultado.get(j).getPercentual()).setFontSize(10)));
        }
        
           //document.add(img);
           document.add(header);
           document.add(t0);
           document.add(t1);
           document.add(t2);
           document.add(t3);
           document.add(t4);
           document.add(t5);
           document.add(t6);
           document.add(t7);
           document.add(t8);
           document.close();
           
           ////////////////////////////////ENVIA PARA O BROWSER////////////////////////
           
           HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();		
           File arquivo = new File("/home/sueder/Documentos/balanco.pdf");
           int tamanho = (int) arquivo.length();
           response.setContentType("application/pdf"); // tipo do conteúdo na resposta
           response.setContentLength(tamanho); // opcional. ajuda na barra de progresso
           response.setHeader("Content-Disposition", "attachment; filename=balanco.pdf");
           ServletOutputStream output = response.getOutputStream();
           Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
           FacesContext.getCurrentInstance().responseComplete();
    }
    
    private void defineTabela(List<BalancoEntity> b){
            List<String> nomes = new ArrayList<>();
            for(int i=0; i<b.size(); i++){
                BalancoEntity bb = b.get(i);
                if(bb.getNome().equals("Bingo") || bb.getNome().equals("Bazar/Brecho")){
                   this.eventos.add(bb);
                   nomes.add("EVENTOS");
                }else{
                        String n = geb.getTipoInstituicao(bb.getNome());
                    if(n.equals("nao")){
                            this.balancos.add(bb);
                            nomes.add("PESSOAS FISICAS");
                    }else if(n.equals("GOVERNO FEDERAL")){
                              this.governof.add(bb);
                              nomes.add("GOVERNO FEDERAL");
                      }else if(n.equals("GOVERNO MUNICIPAL")){
                               this.governom.add(bb);
                               nomes.add("GOVERNO MUNICIPAL");
                      }else if(n.equals("GOVERNO ESTADUAL")){
                               this.governoe.add(bb);
                               nomes.add("GOVERNO ESTADUAL");
                      }else if(n.equals("INSTITUTO FUNDAÇÕES")){
                               this.institutos_fundacoes.add(bb);
                               nomes.add("INSTITUTO FUNDAÇÕES");
                      }else if(n.equals("EMPRESAS PRIVADAS")){
                               this.empresas.add(bb);
                               nomes.add("EMPRESAS PRIVADAS");
                     }else if(n.equals("RECEITAS FINANCEIRAS")){
                               this.receitas.add(bb);
                               nomes.add("RECEITAS FINANCEIRAS");
                      }
                }
          }
            List<BalancoEntity> balan = new ArrayList<>();
            this.nome_descricoes = nomes.stream().distinct().collect(Collectors.toList());
            for(int i=0; i< this.nome_descricoes.size(); i++){
                if(this.nome_descricoes.get(i).equals("EVENTOS")){
                   BalancoEntity e = this.defineCabecario(this.eventos, this.nome_descricoes.get(i));
                   balan.add(e);
                   balan.addAll(this.eventos);
                   this.eventos.clear();
                   this.eventos.addAll(balan);
                   balan.clear();
                } else if(this.nome_descricoes.get(i).equals("PESSOAS FISICAS")){
                   BalancoEntity e = this.defineCabecario(this.balancos, this.nome_descricoes.get(i));
                   balan.add(e);
                   balan.addAll(this.balancos);
                   this.balancos.clear();
                   this.balancos.addAll(balan);
                   balan.clear();
                } else if(this.nome_descricoes.get(i).equals("GOVERNO FEDERAL")){
                   BalancoEntity e = this.defineCabecario(this.governof, this.nome_descricoes.get(i));
                   balan.add(e);
                   balan.addAll(this.governof);
                   this.governof.clear();
                   this.governof.addAll(balan);
                   balan.clear();
                } else if(this.nome_descricoes.get(i).equals("GOVERNO ESTADUAL")){
                   BalancoEntity e = this.defineCabecario(this.governoe, this.nome_descricoes.get(i));
                   balan.add(e);
                   balan.addAll(this.governoe);
                   this.governoe.clear();
                   this.governoe.addAll(balan);
                   balan.clear();
                } else if(this.nome_descricoes.get(i).equals("GOVERNO MUNICIPAL")){
                   BalancoEntity e = this.defineCabecario(this.governom, this.nome_descricoes.get(i));
                   balan.add(e);
                   balan.addAll(this.governom);
                   this.governom.clear();
                   this.governom.addAll(balan);
                   balan.clear();
                } else if(this.nome_descricoes.get(i).equals("EMPRESAS PRIVADAS")){
                   BalancoEntity e = this.defineCabecario(this.empresas, this.nome_descricoes.get(i));
                   balan.add(e);
                   balan.addAll(this.empresas);
                   this.empresas.clear();
                   this.empresas.addAll(balan);
                   balan.clear();
                } else if(this.nome_descricoes.get(i).equals("INSTITUTO FUNDAÇÕES")){
                   BalancoEntity e = this.defineCabecario(this.institutos_fundacoes, this.nome_descricoes.get(i));
                   balan.add(e);
                   balan.addAll(this.institutos_fundacoes);
                   this.institutos_fundacoes.clear();
                   this.institutos_fundacoes.addAll(balan);
                   balan.clear();
                } else if(this.nome_descricoes.get(i).equals("RECEITAS FINANCEIRAS")){
                   BalancoEntity e = this.defineCabecario(this.receitas, this.nome_descricoes.get(i));
                   balan.add(e);
                   balan.addAll(this.receitas);
                   this.receitas.clear();
                   this.receitas.addAll(balan);
                   balan.clear();
                }
            }
    }
    
    private BalancoEntity defineCabecario(List<BalancoEntity> b, String nome){
            double valor =0;
            String percentualgeral="";
            BalancoEntity ba = new BalancoEntity();
            ba.setNome(nome);
            for(int i=0; i<b.size(); i++){
                valor += b.get(i).getValor();
                double v = (valor * 100)/this.valor_percentual;
                percentualgeral = this.convertDouble(v);
            }
            ba.setPercentual(percentualgeral);
            ba.setValor_sttring(this.convertDoubleValor(valor));
    return ba;
    }
    
    private double calculaValor(List<EntradaEntity> entradas){
            double valor=0;
            for(int i=0; i<entradas.size(); i++){
                EntradaEntity e = entradas.get(i);
                valor += e.getValor_entrada();
            }
    return valor;
    }
    
    private List<BalancoEntity> calculaPercentual(List<BalancoEntity> bas, double valorTotal){
            List<BalancoEntity> b = new ArrayList<>();
            for(int i=0; i<bas.size(); i++){
                BalancoEntity ba = bas.get(i);
                double v = (ba.getValor() * 100)/valorTotal;
                ba.setPercentual(this.convertDouble(v));
                b.add(ba);
            }
    return b;
    }
    
    private String convertDouble(double va){
            String p = " %";
            String v = String.valueOf(va);
            //System.out.println(v);
            if(v.length() < 4){
               v += v +00;
            }
            String s = v.substring(0, 4);
            String f = s+p;
    return f;
    }
    
    private String convertDoubleValor(double va){
            String p = "R$ ";
            String v = String.valueOf(va);
            String s = "0";
            String f = p+v+s;
    return f;
    }
}
