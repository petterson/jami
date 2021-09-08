/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import entidades.EntradaEntity;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "entradaJSFManagedBean")
@ViewScoped
public class EntradaJSFManagedBean implements Serializable {

        @EJB
        FinanceiroSessionBean fin;
        @EJB
        GerenteSessionBean geb;
        long id_entrada;
        Date data_emissao_entrada;
        Date data_compensacao_entrada;
        String nome_cheque_entrada;
        String nome_descricao_entrada;
        double valor_entrada;
        String saldo_entrada;
        String nome_cheque_entity;
        ArrayList<String> nome_cheques;
        String nome_descricao_entity;
        List<String> nome_descricoes;
        ArrayList<EntradaEntity> entradas;
        EntradaEntity e;
        @Inject
        AtualizaBalanceteJSFManagedBean atm;
    
    public EntradaJSFManagedBean() {
        e = new EntradaEntity();
        this.entradas = new ArrayList<>();
        this.nome_cheques = new ArrayList<>();
        this.nome_descricoes = new ArrayList<>();
    }

    public long getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(long id_entrada) {
        this.id_entrada = id_entrada;
    }

    public Date getData_emissao_entrada() {
        return data_emissao_entrada;
    }

    public void setData_emissao_entrada(Date data_emissao_entrada) {
        this.data_emissao_entrada = data_emissao_entrada;
    }

    public Date getData_compensacao_entrada() {
        return data_compensacao_entrada;
    }

    public void setData_compensacao_entrada(Date data_compensacao_entrada) {
        this.data_compensacao_entrada = data_compensacao_entrada;
    }

    public String getNome_cheque_entrada() {
        return nome_cheque_entrada;
    }

    public void setNome_cheque_entrada(String nome_cheque_entrada) {
        this.nome_cheque_entrada = nome_cheque_entrada;
    }

    public String getNome_descricao_entrada() {
        return nome_descricao_entrada;
    }

    public void setNome_descricao_entrada(String nome_descricao_entrada) {
        this.nome_descricao_entrada = nome_descricao_entrada;
    }

    public double getValor_entrada() {
        return valor_entrada;
    }

    public void setValor_entrada(double valor_entrada) {
        this.valor_entrada = valor_entrada;
    }

    public String getSaldo_entrada() {
        return saldo_entrada;
    }

    public void setSaldo_entrada(String saldo_entrada) {
        this.saldo_entrada = saldo_entrada;
    }

    public List<String> getNome_cheques() {
        return fin.selecioneCheques();
    }

    public void setNome_cheques(ArrayList<String> nome_cheques) {
        this.nome_cheques = nome_cheques;
    }

    public List<String> getNome_descricoes() {    
      return fin.selecioneDescricoes();
    }

    public void setNome_descricoes(ArrayList<String> nome_descricoes) {
        this.nome_descricoes = nome_descricoes;
    }

    public List<EntradaEntity> getEntradas() {
        return fin.selecioneEntradaDate(atm.getData_inicial(), atm.getData_final());
    }

    public void setEntradas(ArrayList<EntradaEntity> entradas) {
        this.entradas = entradas;
    }

    public String getNome_cheque_entity() {
        return nome_cheque_entity;
    }

    public void setNome_cheque_entity(String nome_cheque_entity) {
        this.nome_cheques.add(nome_cheque_entity);
    this.nome_cheque_entity = nome_cheque_entity;
    }

    public String getNome_descricao_entity() {
        return nome_descricao_entity;
    }

    public void setNome_descricao_entity(String nome_descricao_entity) {
         this.nome_descricoes.add(nome_descricao_entity);
     this.nome_descricao_entity = nome_descricao_entity;
    }

    public EntradaEntity getE() {
        return e;
    }

    public void setE(EntradaEntity e) {
        this.e = e;
    }
    
    public String cadastrarEntrada(){
        if(data_emissao_entrada == null || data_compensacao_entrada == null || nome_descricao_entrada == null
           || nome_cheque_entrada == null || valor_entrada <= 10){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              EntradaEntity en = new EntradaEntity();
              en.setId(id_entrada);
              en.setData_emissao(data_emissao_entrada);
              en.setData_conpensacao(data_compensacao_entrada);
              en.setNome_cheque(nome_cheque_entrada);
              en.setNome_descricao(nome_descricao_entrada);
              en.setValor_entrada(valor_entrada);
              //e=en;
              if(fin.cadastreEntrada(en)){
                  atm.setEn(en);
                  this.entradas.add(en);
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Preencha os campos!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
        }
    return "atualizar_balancete?faces-redirect=true";
    }
     
     public String excluirEntrada(){
          if(e == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Não temlinha selecionada");
              ctx.addMessage(null, msg);
       }else{  
              if(atm.removeEntrada(e)){
                 fin.removeEntrada(e.getId());
                 this.entradas.remove(e);
                 e = null;
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Selecione Uma Linha"));
                }
           }
           this.refresh();
      return "atualizar_balancete?faces-redirect=true";
     }
      
    public Date dataAtual(){ 
         SimpleDateFormat de = new SimpleDateFormat("dd/MM/yyyy");
              Date d = new Date();
              de.format(d);
      return d;
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
