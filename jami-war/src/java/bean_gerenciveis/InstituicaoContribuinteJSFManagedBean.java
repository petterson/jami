/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.InstituicaoContribuinteEntity;
import entidades.InstituicaoEntity;
import entidades.TipoInstituicaoEntity;
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
 * @author sueder
 */
@Named(value = "instituicaoContribuinteJSFManagedBean")
@SessionScoped
public class InstituicaoContribuinteJSFManagedBean implements Serializable {

       @EJB
       GerenteSessionBean geb;
       //@EJB
       //OperacionalSessionBean ops;
      // @EJB
       //FinanceiroSessionBean fin;
       long id;
       //@CNPJ (message="Cnpj Inválido")
       String cnpj_contribuinte;
       String nome_instituicao_contribuinte;
       String fone_instituicao_contribuinte;
       String email_instituicao_contribuinte;
       String nome_tipo_contribuinte;
       Date data_inicial_contribuinte;
       List<String> list_nome_tipos;
       String nome_tipo_instituicao_entity;
       InstituicaoEntity i;
       @Inject
       EntradaJSFManagedBean enm;
    
    public InstituicaoContribuinteJSFManagedBean() {
        this.list_nome_tipos = new ArrayList();
        this.i = new InstituicaoEntity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCnpj_contribuinte() {
        return cnpj_contribuinte;
    }

    public void setCnpj_contribuinte(String cnpj_contribuinte) {
        this.cnpj_contribuinte = cnpj_contribuinte;
    }

    public String getNome_instituicao_contribuinte() {
        return nome_instituicao_contribuinte;
    }

    public void setNome_instituicao_contribuinte(String nome_instituicao_contribuinte) {
        this.nome_instituicao_contribuinte = nome_instituicao_contribuinte;
    }

    public String getFone_instituicao_contribuinte() {
        return fone_instituicao_contribuinte;
    }

    public void setFone_instituicao_contribuinte(String fone_instituicao_contribuinte) {
        this.fone_instituicao_contribuinte = fone_instituicao_contribuinte;
    }

    public String getEmail_instituicao_contribuinte() {
        return email_instituicao_contribuinte;
    }

    public void setEmail_instituicao_contribuinte(String email_instituicao_contribuinte) {
        this.email_instituicao_contribuinte = email_instituicao_contribuinte;
    }

    public String getNome_tipo_contribuinte() {
        return nome_tipo_contribuinte;
    }

    public void setNome_tipo_contribuinte(String nome_tipo_contribuinte) {
        this.nome_tipo_contribuinte = nome_tipo_contribuinte;
    }

    public Date getData_inicial_contribuinte() {
        return data_inicial_contribuinte;
    }

    public void setData_inicial_contribuinte(Date data_inicial_contribuinte) {
        this.data_inicial_contribuinte = data_inicial_contribuinte;
    }

    public List<String> getList_nome_tipos() {
        return geb.selecioneTipoInstituicao();
    }

    public void setList_nome_tipos(List<String> list_nome_tipos) {
        this.list_nome_tipos = list_nome_tipos;
    }

    public String getNome_tipo_instituicao_entity() {
        return nome_tipo_instituicao_entity;
    }

    public void setNome_tipo_instituicao_entity(String nome_tipo_instituicao_entity) {
        this.nome_tipo_instituicao_entity = nome_tipo_instituicao_entity;
    }

    public InstituicaoEntity getI() {
        return i;
    }

    public void setI(InstituicaoEntity i) {
        this.i = i;
    }
    
    public String cadastrarInstituicaoContribuinte(){
    if(cnpj_contribuinte.equals("") || id > 1 || nome_instituicao_contribuinte.equals("") || nome_tipo_contribuinte.equals("")  
       || email_instituicao_contribuinte.equals("") || fone_instituicao_contribuinte.equals("") 
       || data_inicial_contribuinte == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              TipoInstituicaoEntity t = geb.selecioneTipoInstituicaoAtualiza(nome_tipo_contribuinte);
              InstituicaoEntity it = new InstituicaoEntity();
              it.setId(id);
              it.setCnpj(cnpj_contribuinte);
              it.setNome_instituicao(nome_instituicao_contribuinte);
              it.setFone_instiuicao(fone_instituicao_contribuinte);
              it.setEmail_instituicao(email_instituicao_contribuinte);
              it.setTipoInstituicao(t);
              it.setData_parceria(data_inicial_contribuinte);
              if(geb.cadastreInstituicao(it)){
                  t.getIntituicoes().add(it);
                  geb.atualizaTipoInstituicao(t);
                  this.i = geb.selecioneInstituicaoAtualiza(cnpj_contribuinte);
                  InstituicaoContribuinteEntity in = new InstituicaoContribuinteEntity();
                  in.setId(id);
                  in.setInstituicao(this.i);
                  if(geb.cadastreInstituicaoContribuinte(in)){
                     enm.setNome_descricao_entity(it.getNome_instituicao());
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                     this.apagaObjeto();
                  }
              }else{
                    InstituicaoContribuinteEntity ins = geb.selecioneInstituicaoAtualizaContribuinte(cnpj_contribuinte);
                    if(ins.getId() == 1L){
                       this.i = geb.selecioneInstituicaoAtualiza(cnpj_contribuinte);
                       InstituicaoContribuinteEntity in = new InstituicaoContribuinteEntity();
                       in.setId(id);
                       in.setInstituicao(this.i);
                       if(geb.cadastreInstituicaoContribuinte(in)){
                          enm.setNome_descricao_entity(this.i.getNome_instituicao());
                          FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                          this.apagaObjeto();
                        }
                 }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse CNPJ!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Instituição Parceira", "Já é Parceira");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              } 
           }
        } 
    return "/securif/entrada?faces-redirect=true";
    }
    
    public void buscarInstituicaoContribuinte(){
          if(cnpj_contribuinte.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            InstituicaoContribuinteEntity p = new InstituicaoContribuinteEntity();
         p = geb.selecioneInstituicaoAtualizaContribuinte(cnpj_contribuinte);
         if(p.getId() == 1L){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o CNPJ correto"));
         }else{
             id = p.getId();
             this.i = p.getInstituicao();
             nome_instituicao_contribuinte = this.i.getNome_instituicao();
             cnpj_contribuinte = this.i.getCnpj();
             fone_instituicao_contribuinte = this.i.getFone_instiuicao();
             email_instituicao_contribuinte = this.i.getEmail_instituicao();
             nome_tipo_contribuinte = this.i.getTipoInstituicao().getNome();
             data_inicial_contribuinte = this.i.getData_parceria();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
     }
    
    public void atualizarInstituicaoContribuinte(){
       if(cnpj_contribuinte.equals("") || id < 1 || nome_instituicao_contribuinte.equals("") || nome_tipo_contribuinte.equals("") 
            || email_instituicao_contribuinte.equals("") || fone_instituicao_contribuinte.equals("") 
            || data_inicial_contribuinte == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else{
             InstituicaoEntity is = new InstituicaoEntity();
             is.setId(this.i.getId());
             is.setNome_instituicao(this.i.getNome_instituicao());
             is.setCnpj(this.i.getCnpj());
             is.setFone_instiuicao(fone_instituicao_contribuinte);
             is.setEmail_instituicao(email_instituicao_contribuinte);
             is.setTipoInstituicao(this.i.getTipoInstituicao());
             is.setData_parceria(data_inicial_contribuinte);
             InstituicaoContribuinteEntity p = new InstituicaoContribuinteEntity();
             if(geb.atualizaInstituicao(is)){
                p.setId(id);
                p.setInstituicao(is);
                if(geb.atualizaInstituicaoContribuinte(p)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                   this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Instituição Contribuinte Não Atualizado!"));
             }
         }else{
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Instituição Não Atualizado!")); 
             }
         }  
    }
    
    public void excluirInstituicaoContribuinte(){
       if(cnpj_contribuinte.equals("") || id < 1 || nome_instituicao_contribuinte.equals("") || data_inicial_contribuinte == null 
            || email_instituicao_contribuinte.equals("") || fone_instituicao_contribuinte.equals("") 
            || nome_tipo_contribuinte.equals("") ){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
     }else{
             InstituicaoContribuinteEntity i = new InstituicaoContribuinteEntity();
             i.setId(id);
             i.setInstituicao(this.i);
                if(geb.removeInstituicaoContribuinte(i)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                    this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
       }
   }
    
    private void apagaObjeto(){
             id = 0;
             nome_instituicao_contribuinte = "";
             cnpj_contribuinte = "";
             fone_instituicao_contribuinte ="";
             email_instituicao_contribuinte ="";
             nome_tipo_contribuinte ="";
             data_inicial_contribuinte =null;
             this.i = null;
    }
}
