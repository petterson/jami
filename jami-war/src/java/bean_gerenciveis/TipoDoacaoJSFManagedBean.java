/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.TipoDoacaoEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "tipoDoacaoJSFManagedBean")
@SessionScoped
public class TipoDoacaoJSFManagedBean implements Serializable {

          @EJB
          GerenteSessionBean geb;
          long id_tipo_doacao;
          String nome_doacao;
          String nome_doacao_atualiza;
          ArrayList<String> nome_doacoes;
          String remove_selecionado;
          @Inject
          DoacaoJSFManagedBean dam;
    
    public TipoDoacaoJSFManagedBean() {
    }

    public long getId_tipo_doacao() {
        return id_tipo_doacao;
    }

    public void setId_tipo_doacao(long id_tipo_doacao) {
        this.id_tipo_doacao = id_tipo_doacao;
    }

    public List<String> getNome_doacoes() {
        return geb.selecioneTipoDoacoes();
    }

    public void setNome_doacoes(ArrayList<String> nome_doacoes) {
        this.nome_doacoes = nome_doacoes;
    }

    public String getNome_doacao() {
        return nome_doacao;
    }

    public void setNome_doacao(String nome_doacao) {
        this.nome_doacao = nome_doacao;
    }

    public String getRemove_selecionado() {
        return remove_selecionado;
    }

    public void setRemove_selecionado(String remove_selecionado) {
        this.remove_selecionado = remove_selecionado;
    }

    public String getNome_doacao_atualiza() {
        return nome_doacao_atualiza;
    }

    public void setNome_doacao_atualiza(String nome_doacao_atualiza) {
        this.nome_doacao_atualiza = nome_doacao_atualiza;
    }
    
    public String cadastrarTipoDoacao(){
        if(nome_doacao.equals("") || id_tipo_doacao > 1){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              TipoDoacaoEntity tp= new TipoDoacaoEntity();
              tp.setId(id_tipo_doacao);
              tp.setNome_doacao(nome_doacao);
              if(geb.cadastreTipoDoacao(tp)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   dam.setNome_doacao_entity(tp.getNome_doacao());
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Salário!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
        } 
        this.apagaObjeto();
    return "doacao?faces-redirect=true";
    }
    
    public void buscarTipoDoacao(){
          if(nome_doacao.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             TipoDoacaoEntity t = new TipoDoacaoEntity();
             t = geb.selecioneTipoDoacaoAtualiza(nome_doacao);
            if(t.getNome_doacao().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_tipo_doacao = t.getId();
             nome_doacao = t.getNome_doacao();
             nome_doacao_atualiza = nome_doacao;
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
      }
     
      public void excluirTipoDoacao(){
           if(nome_doacao.equals("") || id_tipo_doacao <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "É preciso Buscar antes de Excluir");
              ctx.addMessage(null, msg);
      }else{
             TipoDoacaoEntity t = new TipoDoacaoEntity();
             t.setId(id_tipo_doacao);
             t.setNome_doacao(nome_doacao_atualiza);
             if(geb.removeTipoDoacao(t))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
           else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Nome já Removido"));
           }
           this.apagaObjeto();
      }
      
    private void apagaObjeto(){
             id_tipo_doacao= 0;
             nome_doacao = "";
             nome_doacao_atualiza="";
    } 
      
    public void listaTipoDoacao(AjaxBehaviorEvent event){
        nome_doacao = remove_selecionado;
    }
}
