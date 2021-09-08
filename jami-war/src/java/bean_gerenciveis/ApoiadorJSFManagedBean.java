/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.ApoiadorEntity;
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
@Named(value = "apoiadorJSFManagedBean")
@SessionScoped
public class ApoiadorJSFManagedBean implements Serializable {

       @EJB
       GerenteSessionBean geb;
       long id_apoiador;
       String nome_apoiador;
       String fone_apoiador;
       String email_apoiador;
       String cpf_apoiador;
       Date data_nasc_apoi;
       PessoaEntity p;
       @Inject
       EntradaJSFManagedBean enm;
    
    public ApoiadorJSFManagedBean() {
        this.p = new PessoaEntity();
    }

    public long getId_apoiador() {
        return id_apoiador;
    }

    public void setId_apoiador(long id_apoiador) {
        this.id_apoiador = id_apoiador;
    }

    public String getNome_apoiador() {
        return nome_apoiador;
    }

    public void setNome_apoiador(String nome_apoiador) {
        this.nome_apoiador = nome_apoiador;
    }

    public String getFone_apoiador() {
        return fone_apoiador;
    }

    public void setFone_apoiador(String fone_apoiador) {
        this.fone_apoiador = fone_apoiador;
    }

    public String getEmail_apoiador() {
        return email_apoiador;
    }

    public void setEmail_apoiador(String email_apoiador) {
        this.email_apoiador = email_apoiador;
    }
    
    public String getCpf_apoiador() {
        return cpf_apoiador;
    }

    public void setCpf_apoiador(String cpf_apoiador) {
        this.cpf_apoiador = cpf_apoiador;
    }

    public Date getData_nasc_apoi() {
        return data_nasc_apoi;
    }

    public void setData_nasc_apoi(Date data_nasc_apoi) {
        this.data_nasc_apoi = data_nasc_apoi;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }
    
    public String cadastrarApoiador(){
        if(nome_apoiador.equals("") || id_apoiador >1 || fone_apoiador.equals("") || email_apoiador.equals("") || cpf_apoiador.equals("")
           || data_nasc_apoi == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            //return "preencha os campos";
        }else{
              PessoaEntity p = new PessoaEntity();
              p.setId(id_apoiador);
              p.setNome(nome_apoiador);
              p.setCpf(cpf_apoiador);
              p.setData_nasc(data_nasc_apoi);
              if(geb.cadastrePessoa(p)){
              this.p = geb.selecionePessoaAtualiza(nome_apoiador);
              ApoiadorEntity ap = new ApoiadorEntity();
              System.out.println(this.p);
              ap.setId(id_apoiador);
              ap.setPessoa(this.p);
              ap.setFone_apoiador(fone_apoiador);
              ap.setEmail_apoiador(email_apoiador);
              if(geb.cadastreApoiador(ap)){
                  enm.setNome_descricao_entity(this.p.getNome());
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   this.apagaObjeto();
              }
              }else{
                    ApoiadorEntity a = new ApoiadorEntity();
                    a = geb.selecioneApoiadorAtualiza(nome_apoiador);
                    if(a.getFone_apoiador().equals("")){
                       this.p = geb.selecionePessoaAtualiza(nome_apoiador);
                          ApoiadorEntity ap = new ApoiadorEntity();
                          ap.setId(id_apoiador);
                          ap.setPessoa(this.p);
                          ap.setFone_apoiador(fone_apoiador);
                          ap.setEmail_apoiador(email_apoiador);
                          if(geb.cadastreApoiador(ap)){
                             enm.setNome_descricao_entity(this.p.getNome());
                             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                             this.apagaObjeto();
                        }
                    }else{
                              FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome_apoiador+" :  Já é Apoiador!"));
                              this.apagaObjeto();
                              return nome_apoiador+" :  Já é Apoiador!";
                         }
              }
        }    
    return "entrada?faces-redirect=true";
    }
    
    public void buscarApoiador(){
         if(nome_apoiador.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
              ApoiadorEntity a = new ApoiadorEntity();
              a = geb.selecioneApoiadorAtualiza(nome_apoiador);
              if(a.getFone_apoiador().equals("")){
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
      }else{
            this.p = a.getPessoa();
             nome_apoiador = this.p.getNome();
             cpf_apoiador = this.p.getCpf();
             data_nasc_apoi = this.p.getData_nasc();
             id_apoiador = a.getId();
             fone_apoiador = a.getFone_apoiador();
             email_apoiador = a.getEmail_apoiador();  
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public void atualizarApoiador(){
         if(nome_apoiador.equals("") || id_apoiador < 1 || fone_apoiador.equals("") || email_apoiador.equals("") || cpf_apoiador.equals("")
           || data_nasc_apoi == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else {
             ApoiadorEntity a = new ApoiadorEntity();
             a.setId(id_apoiador);
             a.setFone_apoiador(fone_apoiador);
             a.setEmail_apoiador(email_apoiador);
             a.setPessoa(this.p);
             if(geb.atualizaApoiador(a)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
          }
    }
     
    public void excluirApoiador(){
         if(nome_apoiador.equals("") || id_apoiador < 10 || fone_apoiador.equals("") || email_apoiador.equals("") || cpf_apoiador.equals("")
           || data_nasc_apoi == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
             ApoiadorEntity a = new ApoiadorEntity();
             a.setId(id_apoiador);
             a.setFone_apoiador(fone_apoiador);
             a.setEmail_apoiador(email_apoiador);
             a.setPessoa(this.p);
          if(geb.removeApoiador(a)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
          }
       }
    }   
    
    private void apagaObjeto(){
             id_apoiador = 0;
             nome_apoiador = "";
             fone_apoiador = "";
             email_apoiador ="";
             cpf_apoiador = "";
             data_nasc_apoi = null;
             this.p = new PessoaEntity();
    }
}
