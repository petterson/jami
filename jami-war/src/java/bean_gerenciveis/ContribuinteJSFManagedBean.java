/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.ContribuinteEntity;
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
@Named(value = "parceiroJSFManagedBean")
@SessionScoped
public class ContribuinteJSFManagedBean implements Serializable {

       @EJB
       GerenteSessionBean geb;
       long id_parceiro;
       String nome_parceiro;
       String fone_parceiro;
       String email_parceiro;
       Date data_nascimento;
       String cpf;
       PessoaEntity p;
       @Inject
       EntradaJSFManagedBean ent;
       
    
    public ContribuinteJSFManagedBean() {
        this.p = new PessoaEntity();
    }

    public long getId_parceiro() {
        return id_parceiro;
    }

    public void setId_parceiro(long id_parceiro) {
        this.id_parceiro = id_parceiro;
    }

    public String getNome_parceiro() {
        return nome_parceiro;
    }

    public void setNome_parceiro(String nome_parceiro) {
        this.nome_parceiro = nome_parceiro;
    }

    public String getFone_parceiro() {
        return fone_parceiro;
    }

    public void setFone_parceiro(String fone_parceiro) {
        this.fone_parceiro = fone_parceiro;
    }

    public String getEmail_parceiro() {
        return email_parceiro;
    }

    public void setEmail_parceiro(String email_parceiro) {
        this.email_parceiro = email_parceiro;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String cadastrarParceiro(){
        if(nome_parceiro.equals("") || id_parceiro > 1 || fone_parceiro.equals("") || email_parceiro.equals("")){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              PessoaEntity p = new PessoaEntity();
               p.setId(id_parceiro);
               p.setNome(nome_parceiro);
               p.setCpf(cpf);
               p.setData_nasc(data_nascimento);
               if(geb.cadastrePessoa(p))
                   this.p = geb.selecionePessoaAtualiza(nome_parceiro);
                  ContribuinteEntity pe = new ContribuinteEntity();
                  pe.setId(id_parceiro);
                  pe.setPessoa(this.p);
                  pe.setFone(fone_parceiro);
                  pe.setEmail(email_parceiro);
                  if(geb.cadastreParceiro(pe)){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                     ent.setNome_descricao_entrada(this.p.getNome());
          }else{
                  ContribuinteEntity d = new ContribuinteEntity();
                    d = geb.selecioneParceiroAtualiza(nome_parceiro);
                    if(d.getFone().equals("")){
                       this.p = geb.selecionePessoaAtualiza(nome_parceiro);
                       if(this.p.getNome().equals("")){
                          FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("O CPPF está errado!"));
                          return nome_parceiro+" :  CPF está errado!";
                 }else{
                       ContribuinteEntity ap = new ContribuinteEntity();
                          ap.setId(id_parceiro);
                          ap.setPessoa(this.p);
                          ap.setFone(fone_parceiro);
                          ap.setEmail(email_parceiro);
                          if(geb.cadastreParceiro(pe)){
                             ent.setNome_descricao_entrada(this.p.getNome());
                             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                             this.apagaObjeto();
                        }
                       }
                    }else{
                              FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome_parceiro+" :  Já é Contribuinte!"));
                              this.apagaObjeto();
                              return nome_parceiro+" :  Já é Apoiador!";
                         } 
              }
        }    
        this.apagaObjeto();
    return "entrada?faces-redirect=true";
    }
      
      public void buscarParceiro(){
           if(nome_parceiro.equals("")){
             FacesContext ctx = FacesContext.getCurrentInstance();
             FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
         ContribuinteEntity p = new ContribuinteEntity();
         p = geb.selecioneParceiroAtualiza(nome_parceiro);
         if(p.getFone().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             this.p = p.getPessoa();
             id_parceiro = this.p.getId();
             nome_parceiro = this.p.getNome();
             cpf = this.p.getCpf();
             fone_parceiro = p.getFone();
             email_parceiro = p.getEmail();
             data_nascimento = this.p.getData_nasc();
         }
        }
      }

    public void excluirParceiro(){
           if(nome_parceiro.equals("") || id_parceiro < 1 || fone_parceiro.equals("") || email_parceiro.equals("")){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
          ContribuinteEntity p = new ContribuinteEntity();
             p.setId(id_parceiro);
             p.setPessoa(this.p);
             p.setFone(fone_parceiro);
             p.setEmail(email_parceiro);  
          if(geb.removeParceiro(p))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Removido!"));
           }
           this.apagaObjeto();
      }
    
    private void apagaObjeto(){
             id_parceiro = 0;
             nome_parceiro = "";
             fone_parceiro = "";
             email_parceiro ="";
    }
     
}
