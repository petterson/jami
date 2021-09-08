/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import entidades.DescricaoEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "descricaoJSFManagedBean")
@SessionScoped
public class DescricaoJSFManagedBean implements Serializable {

       @EJB 
       FinanceiroSessionBean fin;
       long id_descricao;
       String nome_descricao;
       String nome_descricao_atualiza;
       @Inject
       EntradaJSFManagedBean enm;
 
    
    public DescricaoJSFManagedBean() {
    }

    public long getId_descricao() {
        return id_descricao;
    }

    public void setId_descricao(long id_descricao) {
        this.id_descricao = id_descricao;
    }

    public String getNome_descricao() {
        return nome_descricao;
    }

    public void setNome_descricao(String nome_descricao) {
        this.nome_descricao = nome_descricao;
    }

    public String getNome_descricao_atualiza() {
        return nome_descricao_atualiza;
    }

    public void setNome_descricao_atualiza(String nome_descricao_atualiza) {
        this.nome_descricao_atualiza = nome_descricao_atualiza;
    }

    public String cadastrarDescricao(){
        if(nome_descricao.equals("") || id_descricao > 1){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            //return "preencha os campos";
        }else{
              DescricaoEntity de = new DescricaoEntity();
              de.setId(id_descricao);
              de.setDescricao(nome_descricao);
              if(fin.cadastreDescricao(de)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   this.apagaObjeto();
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
              enm.setNome_descricao_entity(de.getDescricao());
              this.apagaObjeto();
              }
        } 
    return "entrada?faces-redirect=true";
    }
     
    public void buscarDescricao(){
         if(nome_descricao.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            DescricaoEntity d = new DescricaoEntity();
         d = fin.selecioneDescricaoAtualiza(nome_descricao);
         if(d.getDescricao().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_descricao = d.getId();
             nome_descricao = d.getDescricao();
             nome_descricao_atualiza = nome_descricao;
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public void atualizarDescricao(){
         if(nome_descricao.equals("") || id_descricao < 1){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else{
              DescricaoEntity d = new DescricaoEntity();
              d.setId(id_descricao);
              d.setDescricao(nome_descricao_atualiza);
             if(fin.atualizaDescricao(d)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
        }
    }
     
    public void excluirDescricao(){
         if(nome_descricao.equals("") || id_descricao < 10){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
             DescricaoEntity d = new DescricaoEntity();
             d.setId(id_descricao);
             d.setDescricao(nome_descricao_atualiza);
             if(fin.removeDescricao(d)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
           }
    } 
    
    private void apagaObjeto(){
             id_descricao= 0;
             nome_descricao = "";
             nome_descricao_atualiza = "";
    }
}
