/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.InstituicaoDoadoraEntity;
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
@Named(value = "instituicaoDoadoraJSFManagedBean")
@SessionScoped
public class InstituicaoDoadoraJSFManagedBean implements Serializable {

       @EJB
       GerenteSessionBean geb;
       long id;
       //@CNPJ (message="Cnpj Inválido")
       String cnpj_doadora;
       String nome_doadora;
       String fone_doadora;
       String email_doadora;
       String nome_tipo_instituicao;
       Date data_inicial_parceria;
       List<String> list_nome_tipos;
       String nome_tipo_instituicao_entity;
       InstituicaoEntity i;
       @Inject
       DoacaoJSFManagedBean doa;
    
    public InstituicaoDoadoraJSFManagedBean() {
        this.list_nome_tipos = new ArrayList();
        this.i = new InstituicaoEntity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCnpj_doadora() {
        return cnpj_doadora;
    }

    public void setCnpj_doadora(String cnpj_doadora) {
        this.cnpj_doadora = cnpj_doadora;
    }

    public String getNome_doadora() {
        return nome_doadora;
    }

    public void setNome_doadora(String nome_doadora) {
        this.nome_doadora = nome_doadora;
    }

    public String getFone_doadora() {
        return fone_doadora;
    }

    public void setFone_doadora(String fone_doadora) {
        this.fone_doadora = fone_doadora;
    }

    public String getEmail_doadora() {
        return email_doadora;
    }

    public void setEmail_doadora(String email_doadora) {
        this.email_doadora = email_doadora;
    }

    public String getNome_tipo_instituicao() {
        return nome_tipo_instituicao;
    }

    public void setNome_tipo_instituicao(String nome_tipo_instituicao) {
        this.nome_tipo_instituicao = nome_tipo_instituicao;
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

    public InstituicaoEntity getI() {
        return i;
    }

    public void setI(InstituicaoEntity i) {
        this.i = i;
    }
    
    public String cadastrarInstituicaoDoadora(){
    if(cnpj_doadora.equals("") || id > 1 || nome_doadora.equals("") || fone_doadora.equals("") || email_doadora.equals("") 
       || data_inicial_parceria == null || nome_tipo_instituicao.equals("")){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              TipoInstituicaoEntity t = geb.selecioneTipoInstituicaoAtualiza(nome_tipo_instituicao);
              InstituicaoEntity it = new InstituicaoEntity();
              it.setId(id);
              it.setCnpj(cnpj_doadora);
              it.setNome_instituicao(nome_doadora);
              it.setFone_instiuicao(fone_doadora);
              it.setEmail_instituicao(email_doadora);
              it.setTipoInstituicao(t);
              it.setData_parceria(data_inicial_parceria);
              if(geb.cadastreInstituicao(it)){
                  t.getIntituicoes().add(it);
                  geb.atualizaTipoInstituicao(t);
                  this.i = geb.selecioneInstituicaoAtualiza(cnpj_doadora);
                  InstituicaoDoadoraEntity in = new InstituicaoDoadoraEntity();
                  in.setId(id);
                  in.setInstituicao(this.i);
                  if(geb.cadastreInstituicaoDoadora(in)){
                     doa.setNome_doador_entity(it.getNome_instituicao());
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                     this.apagaObjeto();
                  }
              }else{
                    InstituicaoDoadoraEntity ins = geb.selecioneInstituicaoAtualizaDoadora(cnpj_doadora);
                    if(ins.getId() == 1L){
                       this.i = geb.selecioneInstituicaoAtualiza(cnpj_doadora);
                       InstituicaoDoadoraEntity in = new InstituicaoDoadoraEntity();
                       in.setId(id);
                       in.setInstituicao(this.i);
                       if(geb.cadastreInstituicaoDoadora(in)){
                          doa.setNome_doador_entity(it.getNome_instituicao());
                          FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                          this.apagaObjeto();
                        }
                 }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse CNPJ!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Instituição Doadora", "Já é Doadora");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              } 
           }
        } 
    return "/securi/doacao?faces-redirect=true";
    }
    
     public void buscarInstituicaoDoadora(){
          if(cnpj_doadora.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            InstituicaoDoadoraEntity p = new InstituicaoDoadoraEntity();
         p = geb.selecioneInstituicaoAtualizaDoadora(cnpj_doadora);
         if(p.getId() == 1L){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o CNPJ correto"));
         }else{
             id = p.getId();
             this.i = p.getInstituicao();
             nome_doadora = this.i.getNome_instituicao();
             cnpj_doadora = this.i.getCnpj();
             fone_doadora = this.i.getFone_instiuicao();
             email_doadora = this.i.getEmail_instituicao();
             nome_tipo_instituicao = this.i.getTipoInstituicao().getNome();
             data_inicial_parceria = this.i.getData_parceria();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
     }
    
     public void atualizarInstituicaoDoadora(){
         if(cnpj_doadora.equals("") || id < 1 || nome_doadora.equals("") || fone_doadora.equals("") || email_doadora.equals("")
            || nome_doadora.equals("") || data_inicial_parceria == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else{
             InstituicaoEntity is = new InstituicaoEntity();
             is.setId(this.i.getId());
             is.setNome_instituicao(this.i.getNome_instituicao());
             is.setCnpj(this.i.getCnpj());
             is.setFone_instiuicao(fone_doadora);
             is.setEmail_instituicao(email_doadora);
             is.setTipoInstituicao(this.i.getTipoInstituicao());
             is.setData_parceria(data_inicial_parceria);
             InstituicaoDoadoraEntity p = new InstituicaoDoadoraEntity();
             if(geb.atualizaInstituicao(is)){
                p.setId(id);
                p.setInstituicao(is);
                if(geb.atualizaInstituicaoDoadora(p)){
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
     
    public void excluirInstituicaoDoadora(){
         if(cnpj_doadora.equals("") || id < 1 || nome_doadora.equals("") || fone_doadora.equals("") || email_doadora.equals("")
            || nome_tipo_instituicao.equals("") || data_inicial_parceria == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
     }else{
             InstituicaoDoadoraEntity i = new InstituicaoDoadoraEntity();
             i.setId(id);
             i.setInstituicao(this.i);
                if(geb.removeInstituicaoDoadora(i)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                    this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
       }
   }
     
    private void apagaObjeto(){
             id = 0;
             nome_doadora = "";
             cnpj_doadora = "";
             fone_doadora ="";
             email_doadora ="";
             nome_tipo_instituicao ="";
             data_inicial_parceria =null;
             this.i = null;
    }
    
}
