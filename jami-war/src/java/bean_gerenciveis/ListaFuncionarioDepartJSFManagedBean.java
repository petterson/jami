/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
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
import entidades.CargoEntity;
import entidades.DepartamentoEntity;
import entidades.FuncionarioEntity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petterson
 */
@Named(value = "listaFuncionarioDepartJSFManagedBean")
@SessionScoped
public class ListaFuncionarioDepartJSFManagedBean implements Serializable {
    
           @EJB
           GerenteSessionBean geb;
           List<FuncionarioEntity> list_funcionarios;
    
    public ListaFuncionarioDepartJSFManagedBean() {
    }

    public List<FuncionarioEntity> getList_funcionarios() {
           this.list_funcionarios = geb.selecioneFuncionariosDepart();
     return list_funcionarios;
    }

    public void setList_funcionarios(List<FuncionarioEntity> list_funcionarios) {
        this.list_funcionarios = list_funcionarios;
    }
    
    public void imprimir() throws FileNotFoundException, MalformedURLException, IOException{
            PdfDocument pdf = new PdfDocument(new PdfWriter("/home/sueder/Documentos/funcionario_departamento.pdf"));
           pdf.addNewPage(); 
           Document document = new Document(pdf);
           String imageFile = "/home/sueder/Imagens/Abdulbaha.jpg";
           ImageData data = ImageDataFactory.create(imageFile);
           Image img = new Image(data); 
           img.setMaxHeight(60);
           img.setMaxWidth(60);
           Paragraph header = new Paragraph()
           .add(img)
           .add("                           Lista de Funcionário Por Departamento")
           .setMarginBottom(20)
           .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
           .setFontSize(14)
           .setFontColor(ColorConstants.RED);
           float[] columnWidths = {80F, 45F, 45F, 35F, 60F, 25F};
           Table table = new Table(UnitValue.createPercentArray(columnWidths));
           
           PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
           Cell cell = new Cell(1, 6)
                .add(new Paragraph("Lista de Funcionário Por Departamento")
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
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("CPF")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Fone")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("CLT")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Cargo")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Departamento"))
            };
            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    table.addHeaderCell(hfCell);
                } else {
                    table.addFooterCell(hfCell);
                }
            }
        }

        for (int j = 0; j < this.list_funcionarios.size(); j++) {
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_funcionarios.get(j).getPessoa().getNome()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_funcionarios.get(j).getPessoa().getCpf()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_funcionarios.get(j).getFone_funcionario()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.list_funcionarios.get(j).getClt_funcionario()).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertCargo(this.list_funcionarios.get(j).getCargo_funcionario())).setFontSize(10)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(this.convertDepartamento(this.list_funcionarios.get(j).getDepartamento())).setFontSize(10)));
        }
           //document.add(img);
           document.add(header);
           document.add(table);
           document.close();
           
           ////////////////////////////////ENVIA PARA O BROWSER////////////////////////
           
           HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();		
           File arquivo = new File("/home/sueder/Documentos/funcionario_departamento.pdf");
           int tamanho = (int) arquivo.length();
           response.setContentType("application/pdf"); // tipo do conteúdo na resposta
           response.setContentLength(tamanho); // opcional. ajuda na barra de progresso
           response.setHeader("Content-Disposition", "attachment; filename=funcionario_departamento.pdf");
           ServletOutputStream output = response.getOutputStream();
           Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
           FacesContext.getCurrentInstance().responseComplete();
    }
    
    public String convertCargo(CargoEntity e){
    return e.getNome_cargo();
    }
    
    public String convertDepartamento(DepartamentoEntity e){
    return e.getNome_departamento();
    }
}
