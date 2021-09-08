/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
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
import entidades.BalanceteEntity;
import entidades.ContaEntity;
import entidades.EntradaEntity;
import entidades.SaidaEntity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petterson
 */
@Named(value = "atualizaBalanceteJSFManagedBean")
@SessionScoped
public class AtualizaBalanceteJSFManagedBean implements Serializable{

        @EJB
        FinanceiroSessionBean fin;
        long id_balancete;
        String numero_conta;
        Date data_inicial;
        Date data_final;
        String saldo;
        String descricao;
        double valor_atual;
        BalanceteEntity be;
        ArrayList<String> nome_dos_balancetes;
        ArrayList<BalanceteEntity> os_balancetes;
        SaidaEntity sa;
        List<SaidaEntity> saidas;
        EntradaEntity en;
        List<EntradaEntity> entradas;
        ContaEntity con;
        @Inject
        BalanceteJSFManagedBean bam;
        
    
    public AtualizaBalanceteJSFManagedBean() {
        this.be = new BalanceteEntity();
        this.saidas = new ArrayList<>();
        this.os_balancetes = new ArrayList<>();
        this.entradas = new ArrayList<>();
        this.con = new ContaEntity();
        
    }

    public String getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(String numero_conta) {
        this.numero_conta = numero_conta;
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

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public List<SaidaEntity> getSaidas() {
        return saidas;
    }

    public void setSaidas(List<SaidaEntity> saidas) {
        
        this.saidas = saidas;
    }

    public List<EntradaEntity> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaEntity> entradas) {
        this.entradas = entradas;
    } 
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SaidaEntity getSa() {
        return sa;
    }

    public void setSa(SaidaEntity sa) {
        valor_atual = valor_atual - sa.getValor_saida();
        saidas.add(sa);
        be.setSaldo(valor_atual);
        be.setNumero_conta(numero_conta);
        be.setData_final(data_final);
        os_balancetes.clear();
        os_balancetes.add(be);
        this.sa = sa;
        this.atualizarBalancete();
    }

    public EntradaEntity getEn() {
        return en;
    }

    public void setEn(EntradaEntity en) {
        valor_atual = valor_atual + en.getValor_entrada();
        entradas.add(en);  
        be.setSaldo(valor_atual);
        be.setNumero_conta(numero_conta);
        be.setData_final(data_final);
        os_balancetes.clear();
        os_balancetes.add(be);
        this.en = en;
        this.atualizarBalancete();
    }
    
    public List<String> getNome_dos_balancetes() {
        return fin.selecioneNomeBalancetes();
    }

    public void setNome_dos_balancetes(ArrayList<String> nome_dos_balancetes) {
        this.nome_dos_balancetes = nome_dos_balancetes;
    }

    public ArrayList<BalanceteEntity> getOs_balancetes() {
        return os_balancetes;
    }

    public void setOs_balancetes(ArrayList<BalanceteEntity> os_balancetes) {
        this.os_balancetes = os_balancetes;
    }

    public double getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(double valor_atual) {
        this.valor_atual = valor_atual;
    }

    public BalanceteEntity getBe() {
        return be;
    }

    public void setBe(BalanceteEntity be) {
        this.be = be;
    }

    public long getId_balancete() {
        return id_balancete;
    }

    public void setId_balancete(long id_balancete) {
        this.id_balancete = id_balancete;
    }

    public ContaEntity getC() {
        return con;
    }

    public void setC(ContaEntity con) {
        this.con = con;
    }
    
     public void getBalancete(AjaxBehaviorEvent event){ 
                   try{
                    be = fin.selecioneBalanceteAtualiza(descricao);
                   }catch(NoResultException e){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Encontrado!"));
                   }
                   if(be == null){
                       FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Encontrado!"));
                   }else{
                   //con = fin.selecioneContaAtualiza(numero_conta);
                   os_balancetes.clear();
                   os_balancetes.add(be);
                   id_balancete = fin.buscaId(descricao);
                   numero_conta = be.getNumero_conta();
                   data_inicial = be.getData_inicial();
                   data_final = be.getData_final();
                   valor_atual = be.getSaldo();
                   descricao = be.getDescricao_balancete();  
                   saidas = be.getSaida();
                   entradas = be.getEntrada(); 
                   }
    }
     
    public void imprimirLista() throws FileNotFoundException, MalformedURLException, IOException{
           PdfDocument pdf = new PdfDocument(new PdfWriter("/home/petterson/Documentos/balancete.pdf"));
           pdf.addNewPage(); 
           Document document = new Document(pdf);
           String imageFile = "/home/petterson/Imagens/Abdulbaha.jpg";
           ImageData data = ImageDataFactory.create(imageFile);
           Image img = new Image(data); 
           img.setMaxHeight(60);
           img.setMaxWidth(60);
           Paragraph header = new Paragraph()
           .add(img)
           .add("                        "+this.descricao)
           .setMarginBottom(20)
           .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
           .setFontSize(14)
           .setFontColor(ColorConstants.RED);
           
    /////////////////////TABELA ENTRADA////////////////////////////////////
           
           float[] columnWidths = {30F, 40F, 80F, 100F, 55F};
           Table table = new Table(UnitValue.createPercentArray(columnWidths));  
           table.setMarginBottom(40);
           PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
           Cell cell = new Cell(1, 5)
                .add(new Paragraph("Tabela Entrada")
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
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Data Emissão")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Data Compensação")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("N Cheque ou Transferência")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Descrição")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("valor")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    table.addHeaderCell(hfCell);
                } else {
                    table.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.entradas.size(); j++) {
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDate(this.entradas.get(j).getData_emissao())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDate(this.entradas.get(j).getData_conpensacao())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.entradas.get(j).getNome_cheque()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.entradas.get(j).getNome_descricao()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDoble(this.entradas.get(j).getValor_entrada())).setFontSize(10)));
        }
        
    /////////////////////TABELA SAIDA////////////////////////////////////
    
        Table tables = new Table(UnitValue.createPercentArray(columnWidths));  
        tables.setMarginBottom(40);
           Cell cells = new Cell(1, 5)
                .add(new Paragraph("Tabela Saida")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           tables.addHeaderCell(cells);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Data Emissão")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Data Compensação")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("N Cheque ou Transferência")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Descrição")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("valor")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    tables.addHeaderCell(hfCell);
                } else {
                    tables.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.saidas.size(); j++) {
            tables.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDate(this.saidas.get(j).getData_emissao())).setFontSize(10)));
            tables.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDate(this.saidas.get(j).getData_compensacao())).setFontSize(10)));
            tables.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.saidas.get(j).getNome_cheque()).setFontSize(10)));
            tables.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.saidas.get(j).getNome_descricao()).setFontSize(10)));
            tables.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDoble(this.saidas.get(j).getValor_saida())).setFontSize(10)));
        }
        
    /////////////////////TABELA SALDO////////////////////////////////////
    
         float[] columnWidthse = {100F, 100F, 100F};
        Table tablee = new Table(UnitValue.createPercentArray(columnWidthse));  
        tablee.setMarginBottom(40);
        tablee.setHorizontalAlignment(HorizontalAlignment.CENTER);
           Cell celle = new Cell(1, 3)
                .add(new Paragraph("Saldo em Conta")
                .setBackgroundColor(new DeviceGray(0.25f))
                .setBorder(Border.NO_BORDER))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
           tablee.addHeaderCell(celle);
           for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Data")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Conta")),
                    new Cell().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Saldo")),
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    tablee.addHeaderCell(hfCell);
                } else {
                    tablee.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.os_balancetes.size(); j++) {
            tablee.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDate(this.os_balancetes.get(j).getData_final())).setFontSize(10)));
            tablee.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.os_balancetes.get(j).getNumero_conta()).setFontSize(10)));
            tablee.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDoble(this.os_balancetes.get(j).getSaldo())).setFontSize(10)));
        }
        
           //document.add(img);
           document.add(header);
           document.add(table);
           document.add(tables);
           document.add(tablee);
           document.close();
           
           ////////////////////////////////ENVIA PARA O BROWSER////////////////////////
           
           HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();		
           File arquivo = new File("/home/petterson/Documentos/balancete.pdf");
           int tamanho = (int) arquivo.length();
           response.setContentType("application/pdf"); // tipo do conteúdo na resposta
           response.setContentLength(tamanho); // opcional. ajuda na barra de progresso
           response.setHeader("Content-Disposition", "attachment; filename=balancete.pdf");
           ServletOutputStream output = response.getOutputStream();
           Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
           FacesContext.getCurrentInstance().responseComplete();
    }
    
    public String convertLong(Long in){
    return String.valueOf(in);    
    }
    
    public String convertDoble(double v){
    return String.valueOf("$ "+v+" Reais");    
    }
    
    public String convertDate(Date e){
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    return dateFormat.format(e);
    }
    
    public void atualizarBalancete(){
         if(descricao.equals("") || data_final == null || numero_conta == null || data_inicial == null || valor_atual <= 0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
        }else{
             con.setNumero_conta(numero_conta);
             con.setSaldo(valor_atual);
             fin.atualizaContaValor(con);
             
             BalanceteEntity b = new BalanceteEntity();
             b.setId(id_balancete);
             b.setNumero_conta(numero_conta);
             b.setData_inicial(data_inicial);
             b.setData_final(data_final);
             b.setDescricao_balancete(descricao);
             b.setSaldo(valor_atual);
             b.setEntrada(entradas);
             b.setSaida(saidas);
             if(fin.atualizaBalancete(b)){
                 os_balancetes.clear();
                 os_balancetes.add(b);
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
             }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
            }
     }
     
     public boolean removeEntrada(EntradaEntity e){
          boolean rep=true;
          int i=0;
          while(rep){
               long l = this.entradas.get(i).getId();
               if(l == e.getId()){
                  e = this.entradas.get(i);
                  valor_atual = valor_atual - e.getValor_entrada();
                  be.setSaldo(valor_atual);
                  be.setNumero_conta(numero_conta);
                  be.setData_final(data_final);
                  os_balancetes.clear();
                  os_balancetes.add(be);
                  this.entradas.remove(e);
                  this.atualizarBalancete();
                  rep = false;
               }else{
                    i++;
               }
         }
    return true;
    }
     
    public boolean removeSaida(SaidaEntity s){
             boolean rep =true;
             int i=0;
             while(rep){
               long l = this.saidas.get(i).getId();
               if(l == s.getId()){
                  s = this.saidas.get(i);
                  valor_atual = valor_atual + s.getValor_saida();
                  be.setSaldo(valor_atual);
                  be.setNumero_conta(numero_conta);
                  be.setData_final(data_final);
                  os_balancetes.clear();
                  os_balancetes.add(be);
                  this.saidas.remove(s);
                  this.atualizarBalancete();
                  rep = false;
               }else{
                    i++;
               }
         }
    return true;
    }
}
