/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import entidades.InstituicaoEntity;
import entidades.PrestadoraServicoEntity;
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
@Named(value = "prestadoraJSFManagedBean")
@SessionScoped
public class PrestadoraJSFManagedBean implements Serializable {

           @EJB
           FinanceiroSessionBean fin;
           @EJB
           GerenteSessionBean geb;
           long id_prestadora;
           //@CNPJ (message="Cnpj Inválido")
           String cnpj_prestadora;
           String nome_prestadora;
           String fone_prestadora;
           String email_prestadora;
           String nome_tipo_instituicao;
           Date data_cadastro;
           List<String> list_nome_tipos;
           String nome_tipo_instituicao_entity;
           InstituicaoEntity i;
           @Inject
           SaidaJSFManagedBean sam;

      
    public PrestadoraJSFManagedBean() {
        this.list_nome_tipos = new ArrayList();
        this.i = new InstituicaoEntity();
    }

    public long getId_prestadora() {
        return id_prestadora;
    }

    public void setId_prestadora(long id_prestadora) {
        this.id_prestadora = id_prestadora;
    }

    public String getCnpj_prestadora() {
        return cnpj_prestadora;
    }

    public void setCnpj_prestadora(String cnpj_prestadora) {
        this.cnpj_prestadora = cnpj_prestadora;
    }

    public String getNome_prestadora() {
        return nome_prestadora;
    }

    public void setNome_prestadora(String nome_prestadora) {
        this.nome_prestadora = nome_prestadora;
    }

    public String getFone_prestadora() {
        return fone_prestadora;
    }

    public void setFone_prestadora(String fone_prestadora) {
        this.fone_prestadora = fone_prestadora;
    }

    public String getEmail_prestadora() {
        return email_prestadora;
    }

    public void setEmail_prestadora(String email_prestadora) {
        this.email_prestadora = email_prestadora;
    } 

    public String getNome__tipo_instituicao() {
        return nome_tipo_instituicao;
    }

    public void setNome__tipo_instituicao(String nome__tipo_instituicao) {
        this.nome_tipo_instituicao = nome__tipo_instituicao;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
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
    
    public String cadastrarPrestadora(){
        if(cnpj_prestadora.equals("") || id_prestadora > 1 || nome_prestadora.equals("") || nome_tipo_instituicao.equals("")  
           || email_prestadora.equals("") || fone_prestadora.equals("") || data_cadastro == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              TipoInstituicaoEntity t = geb.selecioneTipoInstituicaoAtualiza(nome_tipo_instituicao);
              InstituicaoEntity it = new InstituicaoEntity();
              it.setId(id_prestadora);
              it.setCnpj(cnpj_prestadora);
              it.setNome_instituicao(nome_prestadora);
              it.setFone_instiuicao(fone_prestadora);
              it.setEmail_instituicao(email_prestadora);
              it.setTipoInstituicao(t);
              it.setData_parceria(data_cadastro);
              if(geb.cadastreInstituicao(it)){
                  t.getIntituicoes().add(it);
                  geb.atualizaTipoInstituicao(t);
                  this.i = geb.selecioneInstituicaoAtualiza(cnpj_prestadora);
                  PrestadoraServicoEntity p = new PrestadoraServicoEntity();
                  p.setId(id_prestadora);
                  p.setInstituicao(this.i);
                  if(fin.cadastrePrestadoraServico(p)){
                     sam.setNome_descricao_saida_entity(it.getNome_instituicao());
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                     this.apagaObjeto();
                  }
              }else{
                    PrestadoraServicoEntity ins = fin.selecionePrestadoraAtualiza(cnpj_prestadora);
                    if(ins.getId() == 1L){
                       this.i = geb.selecioneInstituicaoAtualiza(cnpj_prestadora);
                       PrestadoraServicoEntity p = new PrestadoraServicoEntity();
                       p.setId(id_prestadora);
                       p.setInstituicao(this.i);
                       if(fin.cadastrePrestadoraServico(p)){
                          sam.setNome_descricao_saida_entity(it.getNome_instituicao());
                          FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                          this.apagaObjeto();
                        }
                 }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse CNPJ!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Instituição Prestadora", "Já é Prestadora");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              } 
           }
        } 
    return "/securif/saida?faces-redirect=true";
    }
    
    public void buscarPrestadora(){
        if(cnpj_prestadora.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            PrestadoraServicoEntity p = new PrestadoraServicoEntity();
         p = fin.selecionePrestadoraAtualiza(cnpj_prestadora);
         if(p.getId() == 1L){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o CNPJ correto"));
         }else{
             id_prestadora = p.getId();
             this.i = p.getInstituicao();
             nome_prestadora = this.i.getNome_instituicao();
             cnpj_prestadora = this.i.getCnpj();
             fone_prestadora = this.i.getFone_instiuicao();
             email_prestadora = this.i.getEmail_instituicao();
             nome_tipo_instituicao = this.i.getTipoInstituicao().getNome();
             data_cadastro = this.i.getData_parceria();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public void atualizarPrestadora(){
         if(cnpj_prestadora.equals("") || id_prestadora < 1 || nome_prestadora.equals("") || nome_prestadora.equals("") 
            || email_prestadora.equals("") || fone_prestadora.equals("") || data_cadastro == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else{
             InstituicaoEntity is = new InstituicaoEntity();
             is.setId(this.i.getId());
             is.setNome_instituicao(this.i.getNome_instituicao());
             is.setCnpj(this.i.getCnpj());
             is.setFone_instiuicao(fone_prestadora);
             is.setEmail_instituicao(email_prestadora);
             is.setTipoInstituicao(this.i.getTipoInstituicao());
             is.setData_parceria(data_cadastro);
             PrestadoraServicoEntity p = new PrestadoraServicoEntity();
             if(geb.atualizaInstituicao(is)){
                p.setId(id_prestadora);
                p.setInstituicao(is);
                if(fin.atualizaInstituicaoPrestadora(p)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                   this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Instituição Prestadora Não Atualizado!"));
             }
         }else{
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Instituição Não Atualizado!")); 
             }
         }  
    }
     
    public void excluirPrestadora(){
         if(cnpj_prestadora.equals("") || id_prestadora < 1 || nome_prestadora.equals("") || data_cadastro == null 
            || email_prestadora.equals("") || fone_prestadora.equals("") || nome_tipo_instituicao.equals("")){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
     }else{
             PrestadoraServicoEntity p = new PrestadoraServicoEntity();
             p.setId(id_prestadora);
             p.setInstituicao(this.i);
                if(fin.removeInstituicaoPrestadora(p)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                    this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
       }
    }
    
    private void apagaObjeto(){
             id_prestadora = 0;
             cnpj_prestadora = "";
             nome_prestadora = "";
             nome_tipo_instituicao = "";
             fone_prestadora ="";
             email_prestadora = "";
             data_cadastro =null;
             this.i = null;
    }
      
    public Date dataAtual(){
      return new Date();
    }
}
