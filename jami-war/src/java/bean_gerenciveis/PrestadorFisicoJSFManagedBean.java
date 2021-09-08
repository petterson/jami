/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import entidades.PessoaEntity;
import entidades.PrestadorFisicoEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author petterson
 */
@Named(value = "prestadorFisicoJSFManagedBean")
@SessionScoped
public class PrestadorFisicoJSFManagedBean implements Serializable {

           @EJB
           FinanceiroSessionBean fin;
           @EJB
           GerenteSessionBean geb;
           long id_prestador;
           //@CPF (message="Cpf Inválido")
           String cpf_prestador;
           String nome_prestador;
           String fone_prestador;
           String email_prestador;
           Date data_nasc;
           PessoaEntity p;
           @Inject
           SaidaJSFManagedBean sam;
    
    public PrestadorFisicoJSFManagedBean() {
        this.p = new PessoaEntity();
    }

    public long getId_prestador() {
        return id_prestador;
    }

    public void setId_prestador(long id_prestador) {
        this.id_prestador = id_prestador;
    }

    public String getCpf_prestador() {
        return cpf_prestador;
    }

    public void setCpf_prestador(String cpf_prestador) {
        this.cpf_prestador = cpf_prestador;
    }

    public String getNome_prestador() {
        return nome_prestador;
    }

    public void setNome_prestador(String nome_prestador) {
        this.nome_prestador = nome_prestador;
    }

    public String getFone_prestador() {
        return fone_prestador;
    }

    public void setFone_prestador(String fone_prestador) {
        this.fone_prestador = fone_prestador;
    }

    public String getEmail_prestador() {
        return email_prestador;
    }

    public void setEmail_prestador(String email_prestador) {
        this.email_prestador = email_prestador;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }
    
    public String cadastrarPrestadorFisico(){
        if(nome_prestador.equals("") || id_prestador > 1 || cpf_prestador.equals("") || fone_prestador.equals("") 
           || email_prestador.equals("") || data_nasc == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
    }else{
             PessoaEntity p = new PessoaEntity();
             p.setId(id_prestador);
             p.setNome(nome_prestador);
             p.setCpf(cpf_prestador);
             p.setData_nasc(data_nasc);
             if(geb.cadastrePessoa(p)){
               this.p = geb.selecionePessoaAtualiza(nome_prestador);
             PrestadorFisicoEntity pf = new PrestadorFisicoEntity();
             pf.setId(id_prestador);
             pf.setPessoa(this.p);
             pf.setFone_fisico(fone_prestador);
             pf.setEmail_fisico(email_prestador);
             if(fin.cadastrePrestadorFisico(pf)){
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                sam.setNome_descricao_saida_entity(this.p.getNome());
                this.apagaObjeto();
         }
        }else{
                PrestadorFisicoEntity a = new PrestadorFisicoEntity();
                a = fin.selecionePrestadorFisicoAtualiza(nome_prestador);
                if(a.getFone_fisico().equals("")){
                       this.p = geb.selecionePessoaAtualiza(nome_prestador);
                       if(this.p.getNome().equals("")){
                          FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("O CPPF está errado!"));
                          return nome_prestador+" :  CPF está errado!";
                 }else{
                          PrestadorFisicoEntity ap = new PrestadorFisicoEntity();
                          ap.setId(id_prestador);
                          ap.setPessoa(this.p);
                          ap.setFone_fisico(fone_prestador);
                          ap.setEmail_fisico(email_prestador);
                          if(fin.cadastrePrestadorFisico(ap)){
                             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                             sam.setNome_descricao_saida_entity(this.p.getNome());
                             this.apagaObjeto();
                          }
                       }
             }else{
                      FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome_prestador+" :  Já é Apoiador!"));
                      return nome_prestador+" :  Já é Apoiador!";
                    }
             }
       }
    return "saida?faces-redirect=true";
    }
    
    public void buscarPrestadorFisico(){
         if(nome_prestador.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            PrestadorFisicoEntity p = new PrestadorFisicoEntity();
         p = fin.selecionePrestadorFisicoAtualiza(nome_prestador);
         if(p.getFone_fisico().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
      }else{
             this.p = p.getPessoa();
             id_prestador = p.getId();
             cpf_prestador = this.p.getCpf();
             nome_prestador = this.p.getNome();
             fone_prestador = p.getFone_fisico();
             email_prestador = p.getEmail_fisico();
             data_nasc = this.getData_nasc();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado"));
           }
        }
    }
    
    public void atualizarPrestadorFisico(){
         if(nome_prestador.equals("") || id_prestador < 10 || cpf_prestador.equals("") || fone_prestador.equals("") 
            || email_prestador.equals("") || data_nasc == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
               PrestadorFisicoEntity p = new PrestadorFisicoEntity();
               p.setId(id_prestador);
               p.setPessoa(this.p);
               p.setFone_fisico(fone_prestador);
               p.setEmail_fisico(email_prestador);
               if(fin.atualizaPrestadorFisico(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
               }else{
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
               }
          }   
    }
 
    public void excluirPrestadorFisico(){
         if(nome_prestador.equals("") || id_prestador < 10 || cpf_prestador.equals("") || fone_prestador.equals("") 
            || email_prestador.equals("") || data_nasc == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           }else{
                PrestadorFisicoEntity p = new PrestadorFisicoEntity();
                p.setId(id_prestador);
                p.setPessoa(this.p);
                p.setFone_fisico(fone_prestador);
                p.setEmail_fisico(email_prestador);
               if(fin.removePrestadorFisico(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
               }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
               }
           }  
    }
    
    private void apagaObjeto(){
             id_prestador = 0;
             cpf_prestador = "";
             nome_prestador = "";
             data_nasc = null;
             fone_prestador ="";
             email_prestador = "";
             this.p = null;
    }
      
    public Date dataAtual(){
      return new Date();
    }
    
}
