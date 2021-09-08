/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.IrmaoEntity;
import entidades.PessoaEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "irmaoJSFManagedBean")
@SessionScoped
public class IrmaoJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ope;
    @EJB
    GerenteSessionBean geb; 
    long id_irmao;
    String nome_irmao;
    String cpf_irmao;
    String fone_irmao;
    Date data_nascimento_irmao;
    PessoaEntity p;
    @Inject
    AlunoJSFManagedBean alu;
    
    public IrmaoJSFManagedBean() {
           this.p = new PessoaEntity();
    }

    public long getId_irmao() {
        return id_irmao;
    }

    public void setId_irmao(long id_irmao) {
        this.id_irmao = id_irmao;
    }

    public String getNome_irmao() {
        return nome_irmao;
    }

    public void setNome_irmao(String nome_irmao) {
        this.nome_irmao = nome_irmao;
    }

    public Date getData_nascimento_irmao() {
        return data_nascimento_irmao;
    }

    public void setData_nascimento_irmao(Date data_nascimento_irmao) {
        this.data_nascimento_irmao = data_nascimento_irmao;
    }

    public String getCpf_irmao() {
        return cpf_irmao;
    }

    public void setCpf_irmao(String cpf_irmao) {
        this.cpf_irmao = cpf_irmao;
    }

    public String getFone_irmao() {
        return fone_irmao;
    }

    public void setFone_irmao(String fone_irmao) {
        this.fone_irmao = fone_irmao;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }
    
    public String cadastrarIrmao(){
        if(nome_irmao.equals("") || id_irmao > 1 || data_nascimento_irmao == null || cpf_irmao.equals("") 
           || fone_irmao.equals("")){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              PessoaEntity p = new PessoaEntity();
              p.setId(id_irmao);
              p.setNome(nome_irmao);
              p.setCpf(cpf_irmao);
              p.setData_nasc(data_nascimento_irmao);
              if(geb.cadastrePessoa(p)){
                  this.p = geb.selecionePessoaAtualiza(nome_irmao);
              IrmaoEntity en = new IrmaoEntity();
              en.setId(id_irmao);
              en.setPessoa(this.p);
              en.setFone(fone_irmao);
              if(ope.cadastreIrmao(en)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                    alu.setIrmao_entity(this.p.getNome());
                    this.apagaObjeto();
              }
        }else{
              IrmaoEntity en = new IrmaoEntity();
              en = ope.selecioneIrmaoAtualiza(nome_irmao);
              if(en.getFone().equals("")){
                 this.p = geb.selecionePessoaAtualiza(nome_irmao);
                 if(this.p.getNome().equals("")){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("O CPF está errado!"));
                     return nome_irmao+" :  CPF está errado!";
                 }else{
                 en.setId(id_irmao);
                 en.setPessoa(this.p);
                 en.setFone(fone_irmao);
                 if(ope.cadastreIrmao(en)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                    alu.setIrmao_entity(this.p.getNome());
                    this.apagaObjeto();
                 } 
                 }
              }else{
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome_irmao+" :  Já é Irmão!"));
                    this.apagaObjeto();
                    return nome_irmao+" :  Já é Irmão!";
                   }
              }
        }
    return "aluno?faces-redirect=true";
    }
    
    public void buscarIrmao(){
          if(nome_irmao.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            IrmaoEntity i = new IrmaoEntity();
            i = ope.selecioneIrmaoAtualiza(nome_irmao);
         if(i.getFone().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
     }else{
             this.p = i.getPessoa();
             id_irmao = i.getId();
             nome_irmao = this.p.getNome();
             cpf_irmao = this.p.getCpf();
             data_nascimento_irmao = this.p.getData_nasc();
             fone_irmao = i.getFone();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    }
    
     public String atualizarIrmao(){
        if(nome_irmao.equals("") || id_irmao < 1 || data_nascimento_irmao == null || cpf_irmao.equals("") 
           || fone_irmao.equals("")){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
                IrmaoEntity en = new IrmaoEntity();
                en.setId(id_irmao);
                en.setPessoa(this.p);
                en.setFone(fone_irmao);
                if(ope.atualizaIrmao(en)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                   this.apagaObjeto();
            }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
          }
        return "aluno?faces-redirect=true";
     }
     
    /*public void excluirIrmao(){
           if(nome_irmao.equals("") || id_irmao < 10){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
             IrmaoEntity i = new IrmaoEntity();
             i.setId(id_irmao);
             i.setNone_irmao(nome_irmao);
             i.setData_nascimento_irmao(data_nascimento_irmao);
             if(ope.removeIrmao(i))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
           else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           this.apagaObjeto();
      }*/
      
      private void apagaObjeto(){
             id_irmao = 0;
             nome_irmao = "";
             cpf_irmao = "";
             fone_irmao = "";
             data_nascimento_irmao = null;
             this.p = null;
    }
 
}
