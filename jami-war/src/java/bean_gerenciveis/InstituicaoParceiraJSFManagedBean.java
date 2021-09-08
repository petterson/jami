/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.InstituicaoEntity;
import entidades.InstituicaoParceiraEntity;
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
@Named(value = "instituicaoParceiraJSFManagedBean")
@SessionScoped
public class InstituicaoParceiraJSFManagedBean implements Serializable {

       @EJB
       GerenteSessionBean geb;
       long id;
       //@CNPJ (message="Cnpj Inválido")
       String cnpj_parceira;
       String nome_instituicao_parceira;
       String fone_instituicao_parceira;
       String email_instituicao_parceira;
       String nome_tipo_parceira;
       Date data_inicial_parceria;
       List<String> list_nome_tipos;
       String nome_tipo_instituicao_entity;
       InstituicaoEntity i;
       @Inject
       ProjetoJSFManagedBean pro;
    
    public InstituicaoParceiraJSFManagedBean() {
        this.list_nome_tipos = new ArrayList();
        this.i = new InstituicaoEntity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCnpj_parceira() {
        return cnpj_parceira;
    }

    public void setCnpj_parceira(String cnpj_parceira) {
        this.cnpj_parceira = cnpj_parceira;
    }

    public String getNome_instituicao_parceira() {
        return nome_instituicao_parceira;
    }

    public void setNome_instituicao_parceira(String nome_instituicao_parceira) {
        this.nome_instituicao_parceira = nome_instituicao_parceira;
    }

    public String getFone_instituicao_parceira() {
        return fone_instituicao_parceira;
    }

    public void setFone_instituicao_parceira(String fone_instituicao_parceira) {
        this.fone_instituicao_parceira = fone_instituicao_parceira;
    }

    public String getEmail_instituicao_parceira() {
        return email_instituicao_parceira;
    }

    public void setEmail_instituicao_parceira(String email_instituicao_parceira) {
        this.email_instituicao_parceira = email_instituicao_parceira;
    }

    public String getNome_tipo_parceira() {
        return nome_tipo_parceira;
    }

    public void setNome_tipo_parceira(String nome_tipo_parceira) {
        this.nome_tipo_parceira = nome_tipo_parceira;
    }

    public Date getData_inicial_parceria() {
        return data_inicial_parceria;
    }

    public void setData_inicial_parceria(Date data_inicial_parceria) {
        this.data_inicial_parceria = data_inicial_parceria;
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
            this.list_nome_tipos.add(nome_tipo_instituicao_entity);
        this.nome_tipo_instituicao_entity = nome_tipo_instituicao_entity;
    }
    
    public String cadastrarInstituicaoParceira(){
    if(cnpj_parceira.equals("") || id > 1 || nome_instituicao_parceira.equals("") || fone_instituicao_parceira.equals("") 
       || email_instituicao_parceira.equals("") || nome_tipo_parceira.equals("") || data_inicial_parceria == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              TipoInstituicaoEntity t = geb.selecioneTipoInstituicaoAtualiza(nome_tipo_parceira);
              InstituicaoEntity it = new InstituicaoEntity();
              it.setId(id);
              it.setCnpj(cnpj_parceira);
              it.setNome_instituicao(nome_instituicao_parceira);
              it.setFone_instiuicao(fone_instituicao_parceira);
              it.setEmail_instituicao(email_instituicao_parceira);
              it.setTipoInstituicao(t);
              it.setData_parceria(data_inicial_parceria);
              if(geb.cadastreInstituicao(it)){
                  t.getIntituicoes().add(it);
                  geb.atualizaTipoInstituicao(t);
                  this.i = geb.selecioneInstituicaoAtualiza(cnpj_parceira);
                  InstituicaoParceiraEntity in = new InstituicaoParceiraEntity();
                  in.setId(id);
                  in.setInstituicao(this.i);
                  if(geb.cadastreInstituicaoParceira(in)){
                     pro.setInstituicao_parceira_entity(it.getNome_instituicao());
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                     this.apagaObjeto();
                  }
              }else{
                    InstituicaoParceiraEntity ins = geb.selecioneInstituicaoAtualizaParceira(cnpj_parceira);
                    if(ins.getId() == 1L){
                       this.i = geb.selecioneInstituicaoAtualiza(cnpj_parceira);
                       InstituicaoParceiraEntity in = new InstituicaoParceiraEntity();
                       in.setId(id);
                       in.setInstituicao(this.i);
                       if(geb.cadastreInstituicaoParceira(in)){
                          pro.setInstituicao_parceira_entity(it.getNome_instituicao());
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
    return "/securi/projeto?faces-redirect=true";
    }    
    
    public void buscarInstituicaoParceira(){
          if(cnpj_parceira.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            InstituicaoParceiraEntity p = new InstituicaoParceiraEntity();
         p = geb.selecioneInstituicaoAtualizaParceira(cnpj_parceira);
         if(p.getId() == 1L){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o CNPJ correto"));
         }else{
             id = p.getId();
             this.i = p.getInstituicao();
             nome_instituicao_parceira = this.i.getNome_instituicao();
             cnpj_parceira = this.i.getCnpj();
             fone_instituicao_parceira = this.i.getFone_instiuicao();
             email_instituicao_parceira = this.i.getEmail_instituicao();
             nome_tipo_parceira = this.i.getTipoInstituicao().getNome();
             data_inicial_parceria = this.i.getData_parceria();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
     }
    
    public void atualizarInstituicaoParceira(){
         if(cnpj_parceira.equals("") || id < 1 || nome_instituicao_parceira.equals("") || fone_instituicao_parceira.equals("") 
            || email_instituicao_parceira.equals("")|| nome_tipo_parceira.equals("") || data_inicial_parceria == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else{
             InstituicaoEntity is = new InstituicaoEntity();
             is.setId(this.i.getId());
             is.setNome_instituicao(this.i.getNome_instituicao());
             is.setCnpj(this.i.getCnpj());
             is.setFone_instiuicao(fone_instituicao_parceira);
             is.setEmail_instituicao(email_instituicao_parceira);
             is.setTipoInstituicao(this.i.getTipoInstituicao());
             is.setData_parceria(data_inicial_parceria);
             InstituicaoParceiraEntity p = new InstituicaoParceiraEntity();
             if(geb.atualizaInstituicao(is)){
                p.setId(id);
                p.setInstituicao(is);
                if(geb.atualizaInstituicaoParceira(p)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                   this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Instituição Parceira Não Atualizado!"));
             }
         }else{
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Instituição Não Atualizado!")); 
             }
         }  
    }
    
    public void excluirInstituicaoParceira(){
         if(cnpj_parceira.equals("") || id < 1 || nome_instituicao_parceira.equals("") || fone_instituicao_parceira.equals("") 
            || email_instituicao_parceira.equals("") || nome_tipo_parceira.equals("") || data_inicial_parceria == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
     }else{
             InstituicaoParceiraEntity i = new InstituicaoParceiraEntity();
             i.setId(id);
             i.setInstituicao(this.i);
                if(geb.removeInstituicaoParceira(i)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                    this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
       }
   }
    
     private void apagaObjeto(){
             id = 0;
             nome_instituicao_parceira = "";
             cnpj_parceira = "";
             fone_instituicao_parceira ="";
             email_instituicao_parceira ="";
             nome_tipo_parceira ="";
             data_inicial_parceria =null;
             this.i = null;
    }
    
}
