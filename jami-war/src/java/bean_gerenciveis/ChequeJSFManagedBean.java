/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import entidades.ChequeEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "chequeJSFManagedBean")
@SessionScoped
public class ChequeJSFManagedBean implements Serializable {

      @EJB
      FinanceiroSessionBean fin;
      long id_cheque;
      String nome_cheque;
      String nome_cheque_atualiza;
      @Inject
      SaidaJSFManagedBean sam;
      @Inject 
      EntradaJSFManagedBean enm;
     
    public ChequeJSFManagedBean() {
    }

    public long getId_cheque() {
        return id_cheque;
    }

    public void setId_cheque(long id_cheque) {
        this.id_cheque = id_cheque;
    }

    public String getNome_cheque() {
        return nome_cheque;
    }

    public void setNome_cheque(String nome_cheque) {
        this.nome_cheque = nome_cheque;
    }

    public String getNome_cheque_atualiza() {
        return nome_cheque_atualiza;
    }

    public void setNome_cheque_atualiza(String nome_cheque_atualiza) {
        this.nome_cheque_atualiza = nome_cheque_atualiza;
    }
    
    public String cadastrarCheque(){
        if(nome_cheque.equals("") || id_cheque > 1){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else{
              ChequeEntity ch = new ChequeEntity();
              ch.setId(id_cheque);
              ch.setNumero_cheque(nome_cheque);
              if(fin.cadastreCheque(ch))
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                else
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
              sam.setNome_cheque_entity(ch.getNumero_cheque());
              enm.setNome_cheque_entity(ch.getNumero_cheque());
        } 
        id_cheque =0;
        nome_cheque="";
        nome_cheque_atualiza="";
     return "saida?faces-redirect=true";
    }
    
    public void buscarCheque(){
         if(nome_cheque.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
      }else{
            ChequeEntity c = new ChequeEntity();
            c = fin.selecioneChequeAtualiza(nome_cheque);
         if(c.getNumero_cheque().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
     }else{
             id_cheque = c.getId();
             nome_cheque = c.getNumero_cheque();
             nome_cheque_atualiza = nome_cheque;
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public void atualizarCheque(){
         if(nome_cheque.equals("") || id_cheque < 1){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
          ChequeEntity c = new ChequeEntity();
             c.setId(id_cheque);
             c.setNumero_cheque(nome_cheque_atualiza);
             if(fin.atualizaCheque(c))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
          }
          id_cheque=0;
          nome_cheque="";
           nome_cheque_atualiza="";
    }
     
    public void excluirCheque(){
         if(nome_cheque.equals("") || id_cheque < 1){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
             ChequeEntity c = new ChequeEntity();
             c.setId(id_cheque);
             c.setNumero_cheque(nome_cheque_atualiza);
             if(fin.removeCheque(c))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
           else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           id_cheque =0; 
           nome_cheque="";
           nome_cheque_atualiza="";
    } 
}
