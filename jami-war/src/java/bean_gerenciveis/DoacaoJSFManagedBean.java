/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.DoacaoEntity;
import entidades.DoadorEntity;
import entidades.InstituicaoEntity;
import entidades.TipoDoacaoEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "doacaoJSFManagedBean")
@SessionScoped
public class DoacaoJSFManagedBean implements Serializable {
        
        @EJB
        GerenteSessionBean geb;
        long id_doacao;
        String nome_doacao;
        double valor_doacao;
        String nome_doador;
        Date data_doacao;
        String nome_doador_entity;
        List<String> nome_doadores;
        String nome_doacao_entity;
        ArrayList<String> nome_doacoes;
        //List<String> nome_empresas_doadoras;
    
    public DoacaoJSFManagedBean() {
        this.nome_doacoes = new ArrayList<>();
        this.nome_doadores = new ArrayList<>();
        //this.nome_empresas_doadoras = new ArrayList<>();
    }

    public long getId_doacao() {
        return id_doacao;
    }

    public void setId_doacao(long id_doacao) {
        this.id_doacao = id_doacao;
    }

    public List<String> getNome_doadores() {   
        return geb.selecioneDoadores();
    }

    public void setNome_doadores(ArrayList<String> nome_doadores) {
        this.nome_doadores = nome_doadores;
    }

    public String getNome_doacao() {
        return nome_doacao;
    }

    public void setNome_doacao(String nome_doacao) {
        this.nome_doacao = nome_doacao;
    }

    public double getValor_doacao() {
        return valor_doacao;
    }

    public void setValor_doacao(double valor_doacao) {
        this.valor_doacao = valor_doacao;
    }

    public String getNome_doador() {
        return nome_doador;
    }

    public void setNome_doador(String nome_doador) {
        this.nome_doador = nome_doador;
    }

    public List<String> getNome_doacoes() {
        return geb.selecioneTipoDoacoes();
    }

    public void setNome_doacoes(ArrayList<String> nome_doacoes) {
        this.nome_doacoes = nome_doacoes;
    }

    public Date getData_doacao() {
        return data_doacao;
    }

    public void setData_doacao(Date data_doacao) {
        this.data_doacao = data_doacao;
    }

    public String getNome_doador_entity() {
        return nome_doador_entity;
    }

    public void setNome_doador_entity(String nome_doador_entity) {
           this.nome_doadores.add(nome_doador_entity);
        this.nome_doador_entity = nome_doador_entity;
    }

    public String getNome_doacao_entity() {
        return nome_doacao_entity;
    }

    public void setNome_doacao_entity(String nome_doacao_entity) {
            this.nome_doacoes.add(nome_doacao_entity);
        this.nome_doacao_entity = nome_doacao_entity;
    }

    /*public List<String> getNome_empresas_doadoras() {
        return nome_empresas_doadoras;
    }

    public void setNome_empresas_doadoras(List<String> nome_empresas_doadoras) {
        this.nome_empresas_doadoras = nome_empresas_doadoras;
    }*/
    
    public String cadastrarDoacao(){
        if(nome_doacao == null || id_doacao > 1 || valor_doacao <= 0 || nome_doador == null || data_doacao == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              //DoadorEntity d = geb.selecioneDoadorAtualiza(nome_doador);
              TipoDoacaoEntity t = geb.selecioneTipoDoacaoAtualiza(nome_doacao);
              DoacaoEntity da = new DoacaoEntity();
              da.setId(id_doacao);
              da.setNome_doacao(t);
              da.setValor_doacao(valor_doacao);
              da.setNome_doador(nome_doador);
              da.setData_doacao(data_doacao);
              if(geb.cadastreDoacao(da)){
                   //d.getDoacoes().add(da);
                   //geb.atualizaDoador(d);
                   t.getDoacoes().add(da);
                   geb.atualizaTipoDoacao(t);
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   this.apagaObjeto();
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
        }     
    return "gerente?faces-redirect=true";
    }
    
    /*public void buscarDoacao(){
          if(nome_doacao.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             DoacaoEntity d = new DoacaoEntity();
             d = geb.selecioneDoacaoAtualiza(nome_doacao);
            if(d.getNome_doacao().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_doacao = d.getId();
             nome_doacao = d.getNome_doacao().getNome_doacao();
             nome_doador = d.getDoador().getNome_doador();
             data_doacao = d.getData_doacao();
             valor_doacao = d.getValor_doacao();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
      }
      
      public void atualizarDoacao(){
          if(nome_doacao.equals("") || id_doacao < 1 || valor_doacao <= 0 || nome_doador == null || data_doacao == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
      }else{
              DoadorEntity d = geb.selecioneDoadorAtualiza(nome_doador);
              TipoDoacaoEntity t = geb.selecioneTipoDoacaoAtualiza(nome_doacao);
              DoacaoEntity da = new DoacaoEntity();
              da.setId(id_doacao);
              da.setNome_doacao(t);
              da.setValor_doacao(valor_doacao);
              da.setDoador(d);
              da.setData_doacao(data_doacao);
             if(geb.atualizaDoacao(da)){
                  d.getDoacoes().add(da);
                  geb.atualizaDoador(d);
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
          }  
      }
      
      public void excluirDoacao(){
           if(nome_doacao.equals("") || id_doacao < 10 || valor_doacao <= 0 || nome_doador == null || data_doacao == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
              DoadorEntity d = geb.selecioneDoadorAtualiza(nome_doador);
              TipoDoacaoEntity t = geb.selecioneTipoDoacaoAtualiza(nome_doacao);
              DoacaoEntity da = new DoacaoEntity();
              da.setId(id_doacao);
              da.setNome_doacao(t);
              da.setValor_doacao(valor_doacao);
              da.setDoador(d);
              da.setData_doacao(data_doacao);
              if(d.getDoacoes().size() > 1){
                 d.getDoacoes().remove(da);
                 geb.atualizaDoador(d);
                  if(geb.removeDoacao(da)){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                     this.apagaObjeto();
              }else{
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
              }
           }else{
                  if(geb.removeDoador(d)){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                     this.apagaObjeto();
              }else{
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
              }
     
}*/
      
    private void apagaObjeto(){
             id_doacao = 0;
             nome_doacao = "";
             valor_doacao = 0;
             nome_doador ="";
             data_doacao = null;
    }
}
