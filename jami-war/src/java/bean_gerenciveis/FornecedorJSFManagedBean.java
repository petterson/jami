/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import entidades.FornecedorEntity;
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
@Named(value = "fornecedorJSFManagedBean")
@SessionScoped
public class FornecedorJSFManagedBean implements Serializable {

           @EJB
           FinanceiroSessionBean fin;
           @EJB
           GerenteSessionBean geb;
           long id_fornecedor;
           //@CNPJ (message="Cnpj Inválido")
           String cnpj_fornecedor;
           String nome_fornecedor;
           String fone_fornecedor;
           String email_fornecedor;
           String nome_tipo_instituicao;
           Date data_cadastro;
           List<String> list_nome_tipos;
           String nome_tipo_instituicao_entity;
           InstituicaoEntity i;
           @Inject
           SaidaJSFManagedBean sam;
    
    public FornecedorJSFManagedBean() {
        this.list_nome_tipos = new ArrayList();
        this.i = new InstituicaoEntity();
    }

    public long getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(long id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public String getCnpj_fornecedor() {
        return cnpj_fornecedor;
    }

    public void setCnpj_fornecedor(String cnpj_fornecedor) {
        this.cnpj_fornecedor = cnpj_fornecedor;
    }

    public String getNome_fornecedor() {
        return nome_fornecedor;
    }

    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }

    public String getFone_fornecedor() {
        return fone_fornecedor;
    }

    public void setFone_fornecedor(String fone_fornecedor) {
        this.fone_fornecedor = fone_fornecedor;
    }

    public String getEmail_fornecedor() {
        return email_fornecedor;
    }

    public void setEmail_fornecedor(String email_fornecedor) {
        this.email_fornecedor = email_fornecedor;
    }

    public String getNome_tipo_instituicao() {
        return nome_tipo_instituicao;
    }

    public void setNome_tipo_instituicao(String nome_tipo_instituicao) {
        this.nome_tipo_instituicao = nome_tipo_instituicao;
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
    
    public String cadastrarFornecedor(){
    if(cnpj_fornecedor.equals("") || id_fornecedor > 1 || nome_fornecedor.equals("") || nome_tipo_instituicao.equals("")  
       || email_fornecedor.equals("") || fone_fornecedor.equals("") || data_cadastro == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              TipoInstituicaoEntity t = geb.selecioneTipoInstituicaoAtualiza(nome_tipo_instituicao);
              InstituicaoEntity it = new InstituicaoEntity();
              it.setId(id_fornecedor);
              it.setCnpj(cnpj_fornecedor);
              it.setNome_instituicao(nome_fornecedor);
              it.setFone_instiuicao(fone_fornecedor);
              it.setEmail_instituicao(email_fornecedor);
              it.setTipoInstituicao(t);
              it.setData_parceria(data_cadastro);
              if(geb.cadastreInstituicao(it)){
                  t.getIntituicoes().add(it);
                  geb.atualizaTipoInstituicao(t);
                  this.i = geb.selecioneInstituicaoAtualiza(cnpj_fornecedor);
                  FornecedorEntity f = new FornecedorEntity();
                  f.setId(id_fornecedor);
                  f.setInstituicao(this.i);
                  if(fin.cadastreFornecedor(f)){
                     sam.setNome_descricao_saida_entity(it.getNome_instituicao());
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                     this.apagaObjeto();
                  }
              }else{
                    FornecedorEntity ins = fin.selecioneFornecedorAtualiza(cnpj_fornecedor);
                    if(ins.getId() == 1L){
                       this.i = geb.selecioneInstituicaoAtualiza(cnpj_fornecedor);
                       FornecedorEntity f = new FornecedorEntity();
                       f.setId(id_fornecedor);
                       f.setInstituicao(this.i);
                       if(fin.cadastreFornecedor(f)){
                          sam.setNome_descricao_saida_entity(it.getNome_instituicao());
                          FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                          this.apagaObjeto();
                        }
                 }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse CNPJ!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Instituição Fornecedor", "Já é Fornecedor");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              } 
           }
        } 
    return "/securif/saida?faces-redirect=true";
    }
    
     public void buscarFornecedor(){
          if(cnpj_fornecedor.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            FornecedorEntity f = new FornecedorEntity();
         f = fin.selecioneFornecedorAtualiza(cnpj_fornecedor);
         if(f.getId() == 1L){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o CNPJ correto"));
         }else{
             id_fornecedor = f.getId();
             this.i = f.getInstituicao();
             nome_fornecedor = this.i.getNome_instituicao();
             cnpj_fornecedor = this.i.getCnpj();
             fone_fornecedor = this.i.getFone_instiuicao();
             email_fornecedor = this.i.getEmail_instituicao();
             nome_tipo_instituicao = this.i.getTipoInstituicao().getNome();
             data_cadastro = this.i.getData_parceria();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
     
    public void atualizarFornecedor(){
       if(cnpj_fornecedor.equals("") || id_fornecedor < 1 || nome_fornecedor.equals("") || nome_fornecedor.equals("") 
            || email_fornecedor.equals("") || fone_fornecedor.equals("") || data_cadastro == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else{
             InstituicaoEntity is = new InstituicaoEntity();
             is.setId(this.i.getId());
             is.setNome_instituicao(this.i.getNome_instituicao());
             is.setCnpj(this.i.getCnpj());
             is.setFone_instiuicao(fone_fornecedor);
             is.setEmail_instituicao(email_fornecedor);
             is.setTipoInstituicao(this.i.getTipoInstituicao());
             is.setData_parceria(data_cadastro);
             FornecedorEntity f = new FornecedorEntity();
             if(geb.atualizaInstituicao(is)){
                f.setId(id_fornecedor);
                f.setInstituicao(is);
                if(fin.atualizaInstituicaoFornecedor(f)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                   this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Instituição Fornecedor Não Atualizado!"));
             }
         }else{
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Instituição Não Atualizado!")); 
             }
         }  
    }
    
    public void excluirFornecedor(){
       if(cnpj_fornecedor.equals("") || id_fornecedor < 1 || nome_fornecedor.equals("") || data_cadastro == null 
          || email_fornecedor.equals("") || fone_fornecedor.equals("") || nome_tipo_instituicao.equals("")){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
     }else{
             FornecedorEntity f = new FornecedorEntity();
             f.setId(id_fornecedor);
             f.setInstituicao(this.i);
                if(fin.removeInstituicaoFornecedor(f)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                    this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
       }
   }
    
    private void apagaObjeto(){
             id_fornecedor = 0;
             nome_fornecedor = "";
             cnpj_fornecedor = "";
             fone_fornecedor ="";
             email_fornecedor ="";
             nome_tipo_instituicao ="";
             data_cadastro =null;
             this.i = null;
    }
    
}
