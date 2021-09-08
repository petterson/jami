/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.DoadorEntity;
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
@Named(value = "doadorJSFManagedBean")
@SessionScoped
public class DoadorJSFManagedBean implements Serializable {

          @EJB
          GerenteSessionBean geb;
          long id_doador;
          String nome_doador;
          String fone_doador;
          String email_doador;
          String cpf_doador;
          Date data_nasc_doador;
          PessoaEntity p;
          @Inject
          DoacaoJSFManagedBean dom;
          
    public DoadorJSFManagedBean (){
           this.p = new PessoaEntity();
    }

    public long getId_doador() {
        return id_doador;
    }

    public void setId_doador(long id_doador) {
        this.id_doador = id_doador;
    }

    public void setId_doador(int id_doador) {
        this.id_doador = id_doador;
    }

    public String getNome_doador() {
        return nome_doador;
    }

    public void setNome_doador(String nome_doador) {
        this.nome_doador = nome_doador;
    }

    public String getFone_doador() {
        return fone_doador;
    }

    public void setFone_doador(String fone_doador) {
        this.fone_doador = fone_doador;
    }

    public String getEmail_doador() {
        return email_doador;
    }

    public void setEmail_doador(String email_doador) {
        this.email_doador = email_doador;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }

    public String getCpf_doador() {
        return cpf_doador;
    }

    public void setCpf_doador(String cpf_doador) {
        this.cpf_doador = cpf_doador;
    }

    public Date getData_nasc_doador() {
        return data_nasc_doador;
    }

    public void setData_nasc_doador(Date data_nasc_doador) {
        this.data_nasc_doador = data_nasc_doador;
    }
  
    public String cadastrarDoador(){
        if(nome_doador.equals("") || id_doador > 1 || fone_doador.equals("") || email_doador.equals("") || cpf_doador.equals("")
           || data_nasc_doador == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{ 
               PessoaEntity p = new PessoaEntity();
               p.setId(id_doador);
               p.setNome(nome_doador);
               p.setCpf(cpf_doador);
               p.setData_nasc(data_nasc_doador);
               if(geb.cadastrePessoa(p)){
                  this.p = geb.selecionePessoaAtualiza(nome_doador);
                  DoadorEntity dr = new DoadorEntity();
                  dr.setId(id_doador);
                  dr.setPessoa(p);
                  dr.setFone_doador(fone_doador);
                  dr.setEmail_doador(email_doador);
                  if(geb.cadastreDoador(dr)){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                     dom.setNome_doador_entity(this.p.getNome()); 
                     this.apagaObjeto();
               }
        }else{
                   DoadorEntity d = new DoadorEntity();
                    d = geb.selecioneDoadorAtualiza(nome_doador);
                    if(d.getFone_doador().equals("")){
                       this.p = geb.selecionePessoaAtualiza(nome_doador);
                       if(this.p.getNome().equals("")){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("O CPPF está errado!"));
                     return nome_doador+" :  CPF está errado!";
                 }else{
                          DoadorEntity ap = new DoadorEntity();
                          ap.setId(id_doador);
                          ap.setPessoa(this.p);
                          ap.setFone_doador(fone_doador);
                          ap.setEmail_doador(email_doador);
                          if(geb.cadastreDoador(ap)){
                             dom.setNome_doador_entity(this.p.getNome()); 
                             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                             this.apagaObjeto();
                        }
                       }
                    }else{
                              FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome_doador+" :  Já é Apoiador!"));
                              this.apagaObjeto();
                              return nome_doador+" :  Já é Apoiador!";
                         } 
               }
        }  
    return "doacao?faces-redirect=true";
    }
    
    public void buscarDoador(){
         if(nome_doador.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            DoadorEntity d = new DoadorEntity();
         d = geb.selecioneDoadorAtualiza(nome_doador);
         if(d.getFone_doador().equals("")){
            FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
      }else{
             this.p = d.getPessoa();
             nome_doador = this.p.getNome();
             cpf_doador = this.p.getCpf();
             data_nasc_doador = this.p.getData_nasc();
             id_doador = d.getId();
             fone_doador = d.getFone_doador();
             email_doador = d.getEmail_doador();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    }
    
    public void atualizarDoador(){
         if(nome_doador.equals("") || id_doador < 1 || fone_doador.equals("") || email_doador.equals("")|| cpf_doador.equals("")
           || data_nasc_doador == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else{
             DoadorEntity d = new DoadorEntity();
             d.setId(id_doador);
             d.setPessoa(this.p);
             d.setFone_doador(fone_doador);
             d.setEmail_doador(email_doador);
             if(geb.atualizaDoador(d)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
          }
    }
     
    public void excluirDoador(){
         if(nome_doador.equals("") || id_doador < 10 || fone_doador.equals("") || email_doador.equals("")|| cpf_doador.equals("")
           || data_nasc_doador == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           }else{
          DoadorEntity d = new DoadorEntity();
          d.setId(id_doador);
          d.setPessoa(p);
          d.setFone_doador(fone_doador);
          d.setEmail_doador(email_doador);
          if(geb.removeDoador(d)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
          }
       }
    }
    
   private void apagaObjeto(){
             id_doador = 0;
             nome_doador = "";
             fone_doador = "";
             email_doador ="";
             cpf_doador = "";
             data_nasc_doador = null;
             this.p = new PessoaEntity();
    }
 
}
