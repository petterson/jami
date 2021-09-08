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
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author petterson
 */
@Named(value = "instituicaoJSFManagedBean")
@SessionScoped
public class InstituicaoJSFManagedBean implements Serializable {

       @EJB
       GerenteSessionBean geb;
       @EJB
       OperacionalSessionBean ops;
       @EJB
       FinanceiroSessionBean fin;
       long id;
       //@CNPJ (message="Cnpj Inválido")
       String cnpj;
       String nome_instituicao;
       String nome_instituicao_atualiza;
       String fone_instituicao;
       String email_instituicao;
       TipoInstituicaoEntity tipo;
       String nome_tipo;
       Date data_inicial_parceria;
       List<String> list_nome_tipos;
       String nome_tipo_instituicao_entity;
       @Inject
       ProjetoJSFManagedBean pro;
       @Inject
       EntradaJSFManagedBean enm; 
       @Inject
       DoacaoJSFManagedBean doa;
    
    public InstituicaoJSFManagedBean() {
        this.tipo = new TipoInstituicaoEntity();
        this.list_nome_tipos = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome_instituicao() {
        return nome_instituicao;
    }

    public void setNome_instituicao(String nome_instituicao) {
        this.nome_instituicao = nome_instituicao;
    }

    public String getFone_instituicao() {
        return fone_instituicao;
    }

    public void setFone_instituicao(String fone_instituicao) {
        this.fone_instituicao = fone_instituicao;
    }

    public String getEmail_instituicao() {
        return email_instituicao;
    }

    public void setEmail_instituicao(String email_instituicao) {
        this.email_instituicao = email_instituicao;
    }

    public String getNome_instituicao_atualiza() {
        return nome_instituicao_atualiza;
    }

    public void setNome_instituicao_atualiza(String nome_instituicao_atualiza) {
        this.nome_instituicao_atualiza = nome_instituicao_atualiza;
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

    public String getNome_tipo() {
        return nome_tipo;
    }

    public void setNome_tipo(String nome_tipo) {
        this.nome_tipo = nome_tipo;
    }

    public TipoInstituicaoEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstituicaoEntity tipo) {
        this.tipo = tipo;
    }

    public Date getData_inicial_parceria() {
        return data_inicial_parceria;
    }

    public void setData_inicial_parceria(Date data_inicial_parceria) {
        this.data_inicial_parceria = data_inicial_parceria;
    }
    
    public String cadastrarInstituicao(){
        if(cnpj.equals("") || id > 1 || nome_instituicao.equals("") || fone_instituicao.equals("") || email_instituicao.equals("")
           || nome_tipo == null || data_inicial_parceria == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              TipoInstituicaoEntity t = geb.selecioneTipoInstituicaoAtualiza(nome_tipo);
              InstituicaoEntity it = new InstituicaoEntity();
              it.setId(id);
              it.setCnpj(cnpj);
              it.setNome_instituicao(nome_instituicao);
              it.setFone_instiuicao(fone_instituicao);
              it.setEmail_instituicao(email_instituicao);
              it.setTipoInstituicao(t);
              it.setData_parceria(data_inicial_parceria);
              if(geb.cadastreInstituicao(it)){
                  t.getIntituicoes().add(it);
                  geb.atualizaTipoInstituicao(t);
                  enm.setNome_descricao_entity(it.getNome_instituicao());
                  /*if(t.getNome().equals("EVENTOS") || t.getNome().equals("RECEITAS FINANCEIRAS")){
                  }else{
                        doa.setNome_doador_entity(t.getNome());
                  }*/
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                  this.apagaObjeto();
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse CNPJ!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }         
        } 
    return "/securif/entrada?faces-redirect=true";
    }
    
     public void buscarInstituicao(){
          if(cnpj.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            InstituicaoEntity i = new InstituicaoEntity();
         i = geb.selecioneInstituicaoAtualiza(cnpj);
         if(i.getNome_instituicao().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id = i.getId();
             nome_instituicao = i.getNome_instituicao();
             nome_instituicao_atualiza = nome_instituicao;
             cnpj = i.getCnpj();
             fone_instituicao = i.getFone_instiuicao();
             email_instituicao = i.getEmail_instituicao();
             nome_tipo = i.getTipoInstituicao().getNome();
             tipo = i.getTipoInstituicao();
             data_inicial_parceria = i.getData_parceria();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
     }
    
    public void atualizarInstituicao(){
         if(cnpj.equals("") || id < 1 || nome_instituicao.equals("") || fone_instituicao.equals("") || email_instituicao.equals("")
            || nome_tipo == null || data_inicial_parceria == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else{
             InstituicaoEntity i = new InstituicaoEntity();
             i.setId(id);
             i.setNome_instituicao(nome_instituicao_atualiza);
             i.setCnpj(cnpj);
             i.setFone_instiuicao(fone_instituicao);
             i.setEmail_instituicao(email_instituicao);
             i.setTipoInstituicao(tipo);
             i.setData_parceria(data_inicial_parceria);
             if(tipo.getNome().equals(nome_tipo)){
                if(geb.atualizaInstituicao(i)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
             }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não pode ser Atualizado: Cadastre nova Instituição"));
             }
          }
          this.apagaObjeto();
    }
     
    public void excluirInstituicao(){
         if(cnpj.equals("") || id < 1 || nome_instituicao.equals("") || fone_instituicao.equals("") || email_instituicao.equals("")
            || nome_tipo == null || data_inicial_parceria == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
     }else{
             InstituicaoEntity i = new InstituicaoEntity();
             i.setId(id);
             i.setNome_instituicao(nome_instituicao_atualiza);
             i.setCnpj(cnpj);
             i.setFone_instiuicao(fone_instituicao);
             i.setEmail_instituicao(email_instituicao);
             i.setData_parceria(data_inicial_parceria);
             if(tipo.getIntituicoes().size() >1){
                tipo.getIntituicoes().remove(i);
                geb.atualizaTipoInstituicao(tipo);
                if(geb.removeInstituicao(i)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                    this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
           }else{
                  if(geb.removeTipoInstituicao(tipo)){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                     this.apagaObjeto();
          }else{
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
          }
       }
   }
      
    private void apagaObjeto(){
             id = 0;
             nome_instituicao = "";
             nome_instituicao_atualiza="";
             cnpj = "";
             fone_instituicao ="";
             email_instituicao ="";
             nome_tipo ="";
             tipo=null;
             data_inicial_parceria =null;
    }
     
}
