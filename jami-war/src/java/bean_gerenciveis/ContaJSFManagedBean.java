/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import entidades.AgenciaEntity;
import entidades.ContaEntity;
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
 * @author petterson
 */
@Named(value="contaJSFManagedBean")
@SessionScoped
public class ContaJSFManagedBean implements Serializable {

        @EJB
        FinanceiroSessionBean fin;
        long id_conta;
        String numero_conta;
        String nome_conta;
        String nome_conta_atualiza;
        String titular_conta;
        String numero_agencia;
        Date data_criacao_conta;
        double saldo;
        String numero_agencia_entity;
        List<String> numero_agencias;
        List<String> list_titular;
        @Inject
        BalanceteJSFManagedBean bam;
        
                
    public ContaJSFManagedBean() {
        this.numero_agencias = new ArrayList<>();
        this.list_titular = new ArrayList<>();
        this.list_titular.add("Associação Pró Brejaru");
    }

    public long getId_conta() {
        return id_conta;
    }

    public void setId_conta(long id_conta) {
        this.id_conta = id_conta;
    }

    public String getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(String numero_conta) {
        this.numero_conta = numero_conta;
    }

    public String getNome_conta() {
        return nome_conta;
    }

    public void setNome_conta(String nome_conta) {
        this.nome_conta = nome_conta;
    }

    public String getTitular_conta() {
        return titular_conta;
    }

    public void setTitular_conta(String titular_conta) {
        this.titular_conta = titular_conta;
    }

    public String getNumero_agencia() {
        return numero_agencia;
    }

    public void setNumero_agencia(String numero_agencia) {
        this.numero_agencia = numero_agencia;
    }

    public Date getData_criacao_conta() {
        return data_criacao_conta;
    }

    public void setData_criacao_conta(Date data_criacao_conta) {
        this.data_criacao_conta = data_criacao_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<String> getNumero_agencias() {
        return fin.selecioneNumeroAgencias();
    }

    public void setNumero_agencias(ArrayList<String> numero_agencias) {
        this.numero_agencias = numero_agencias;
    }

    public String getNumero_agencia_entity() {
        return numero_agencia_entity;
    }

    public void setNumero_agencia_entity(String numero_agencia_entity) {
           this.numero_agencias.add(numero_agencia_entity);
        this.numero_agencia_entity = numero_agencia_entity;
    }

    public List<String> getList_titular() {
        return list_titular;
    }

    public void setList_titular(List<String> list_titular) {
        this.list_titular = list_titular;
    }

    public String getNome_conta_atualiza() {
        return nome_conta_atualiza;
    }

    public void setNome_conta_atualiza(String nome_conta_atualiza) {
        this.nome_conta_atualiza = nome_conta_atualiza;
    }
    
    public String cadastrarConta(){
        if(nome_conta.equals("") || id_conta > 1 || numero_conta.equals("") || nome_conta.equals("") || titular_conta.equals("")
           || numero_agencia.equals("") || saldo <=0){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              AgenciaEntity a = fin.selecioneAgenciaConta(numero_agencia);
              ContaEntity co = new ContaEntity();
              co.setId(id_conta);
              co.setNumero_conta(numero_conta);
              co.setNome_conta(nome_conta);
              co.setTitular_conta(titular_conta);
              co.setAgencia(a);
              co.setData_criacao(data_criacao_conta);
              co.setSaldo(saldo);
              if(fin.cadastreConta(co)){
                 a.getContas().add(co);
                 fin.atualizaAgencia(a);
                 bam.setNumero_conta_entity(co.getNome_conta());
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
           }else{
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                 FacesContext ctx = FacesContext.getCurrentInstance();
                 FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                 ctx.addMessage(null, msg);
                 return "preencha os campos";
              }
        } 
        this.apagaObjeto();
    return "balancete?faces-redirect=true";
    }
    
    public void buscarConta(){
         if(nome_conta.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
      }else{
            ContaEntity c = new ContaEntity();
             c = fin.selecioneContaAtualiza(nome_conta);
           if(c.getNome_conta().equals("")){
              FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
       }else{
             id_conta = c.getId();
             numero_conta = c.getNumero_conta();
             nome_conta = c.getNome_conta();
             nome_conta_atualiza = nome_conta;
             numero_agencia = c.getAgencia().getNumero_agencia();
             titular_conta = c.getTitular_conta();
             data_criacao_conta = c.getData_criacao();
             saldo = c.getSaldo();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public void atualizarConta(){
         if(nome_conta.equals("") || id_conta < 1 || numero_conta.equals("") || nome_conta.equals("") || titular_conta.equals("")
           || numero_agencia.equals("") || saldo <=0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
      }else {
              AgenciaEntity a = fin.selecioneAgenciaConta(numero_agencia);
              ContaEntity co = new ContaEntity();
              co.setId(id_conta);
              co.setNumero_conta(numero_conta);
              co.setNome_conta(nome_conta_atualiza);
              co.setTitular_conta(titular_conta);
              co.setAgencia(a);
              co.setData_criacao(data_criacao_conta);
              co.setSaldo(saldo);
             if(fin.atualizaConta(co)){
                 a.getContas().add(co);
                 fin.atualizaAgencia(a);
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
          }
          this.apagaObjeto();
    }
     
    public void excluirConta(){
         if(nome_conta.equals("") || id_conta < 10 || numero_conta.equals("") || nome_conta.equals("") || titular_conta.equals("")
           || numero_agencia.equals("") || saldo <=0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
             AgenciaEntity a = fin.selecioneAgenciaConta(numero_agencia);
             ContaEntity co = new ContaEntity();
              co.setId(id_conta);
              co.setNumero_conta(numero_conta);
              co.setNome_conta(nome_conta_atualiza);
              co.setTitular_conta(titular_conta);
              co.setAgencia(a);
              co.setData_criacao(data_criacao_conta);
              co.setSaldo(saldo);
              if(a.getContas().size() > 1){
                  a.getContas().remove(co);
                  fin.atualizaAgencia(a);
                 if(fin.removeConta(co))
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
               else
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
         }else{
                if(fin.removeConta(co))
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
               else
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!")); 
                 }
         }
           this.apagaObjeto();
    }
    
    private void apagaObjeto(){
             id_conta = 0;
             numero_conta = "";
             nome_conta = "";
             nome_conta_atualiza="";
             numero_agencia ="";
             titular_conta = "";
             data_criacao_conta =null;
             saldo =0;
    }
      
    public Date dataAtual(){
      return new Date();
    }
    
}
