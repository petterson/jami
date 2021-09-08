
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import entidades.BalanceteEntity;
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

/**
 *
 * @author petterson
 */
@Named(value="balanceteJSFManagedBean")
@SessionScoped
public class BalanceteJSFManagedBean implements Serializable {

          @EJB
          FinanceiroSessionBean fin;
          long id_balancete;
          String numero_conta;
          Date data_inicial;
          Date data_final;
          double saldo_inicial;
          String descricao;
          String numero_conta_entity;
          List<String> numero_contas;
          Date data_inicial_atualiza;
          double saldo_valor_atualiza;
          String descricao_atualiza;
          String numero_conta_atualiza;
          
          
    
    public BalanceteJSFManagedBean() {
       this.numero_contas = new ArrayList<>();
    }

    public long getId_balancete() {
        return id_balancete;
    }

    public void setId_balancete(long id_balancete) {
        this.id_balancete = id_balancete;
    }

    public String getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(String numero_conta) {
        this.numero_conta = numero_conta;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }   

    public List<String> getNumero_contas() {
        return fin.selecioneNumeroContas();
    }

    public void setNumero_contas(List<String> numero_contas) {
        this.numero_contas = numero_contas;
    }

    public double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public String getNumero_conta_entity() {
        return numero_conta_entity;
    }

    public void setNumero_conta_entity(String numero_conta_entity) {
           this.numero_contas.add(numero_conta_entity);
        this.numero_conta_entity = numero_conta_entity;
    }

    public Date getData_inicial_atualiza() {
        return data_inicial_atualiza;
    }

    public void setData_inicial_atualiza(Date data_inicial_atualiza) {
        this.data_inicial_atualiza = data_inicial_atualiza;
    }

    public double getSaldo_valor_atualiza() {
        return saldo_valor_atualiza;
    }

    public void setSaldo_valor_atualiza(double saldo_valor_atualiza) {
        this.saldo_valor_atualiza = saldo_valor_atualiza;
    }

    public String getDescricao_atualiza() {
        return descricao_atualiza;
    }

    public void setDescricao_atualiza(String descricao_atualiza) {
        this.descricao_atualiza = descricao_atualiza;
    }

    public String getNumero_conta_atualiza() {
        return numero_conta_atualiza;
    }

    public void setNumero_conta_atualiza(String numero_conta_atualiza) {
        this.numero_conta_atualiza = numero_conta_atualiza;
    }
    
    public String cadastrarBalancete(){
        if(descricao ==null || id_balancete > 1 || numero_conta ==null || data_inicial == null || data_final ==null || saldo_inicial <= 0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
            return "preencha os campos";
    }else{
              ContaEntity c = fin.selecioneContaAtualiza(numero_conta);
              BalanceteEntity ba = new BalanceteEntity();
              ba.setId(id_balancete);
              ba.setNumero_conta(numero_conta);
              ba.setData_inicial(data_inicial);
              ba.setData_final(data_final);
              ba.setSaldo(saldo_inicial);
              ba.setDescricao_balancete(descricao);
              if(fin.cadastreBalancete(ba)){
                  c.getBalancetes().add(ba);
                  fin.atualizaConta(c);
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   id_balancete=0;
              }  
        }
        this.apagaObjeto();
    return "financeiro?faces-redirect=true";
    }
    
    public void buscarBalancete(){
         if(descricao.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
      }else{
            BalanceteEntity b = new BalanceteEntity();
            b = fin.selecioneBalanceteAtualiza(descricao);
            if(b.getDescricao_balancete().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
        }else{
               id_balancete = b.getId();
               numero_conta = b.getNumero_conta();
               numero_conta_atualiza = numero_conta;
               data_inicial = b.getData_inicial();
               data_inicial_atualiza = data_inicial;
               data_final = b.getData_final();
               descricao = b.getDescricao_balancete();
               descricao_atualiza = descricao;
               saldo_inicial = b.getSaldo();
               saldo_valor_atualiza = saldo_inicial;
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
     }
    
    public String atualizarBalancete(){
         if(descricao.equals("") || id_balancete < 10 || numero_conta ==null || data_inicial == null || data_final ==null
            || saldo_inicial <= 0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           return "preencha os campos";
      }else{
              ContaEntity c = fin.selecioneContaAtualiza(numero_conta);
              BalanceteEntity b = new BalanceteEntity();
              b.setId(id_balancete);
              b.setNumero_conta(numero_conta_atualiza);
              b.setData_inicial(data_inicial_atualiza);
              b.setData_final(data_final);
              b.setSaldo(saldo_valor_atualiza);
              b.setDescricao_balancete(descricao_atualiza);
             if(fin.atualizaBalancete(b)){
                c.getBalancetes().add(b);
                  fin.atualizaConta(c);
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
          }
          this.apagaObjeto();
          return "financeiro?faces-redirect=true";
    }
     
    public String excluirBalancete(){
         if(descricao.equals("") || id_balancete < 10 || numero_conta ==null || data_inicial == null || data_final ==null 
            || saldo_inicial <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
              ContaEntity c = fin.selecioneContaAtualiza(numero_conta);
              BalanceteEntity b = new BalanceteEntity();
              b.setId(id_balancete);
              b.setNumero_conta(numero_conta_atualiza);
              b.setData_inicial(data_inicial_atualiza);
              b.setData_final(data_final);
              b.setSaldo(saldo_valor_atualiza);
              b.setDescricao_balancete(descricao_atualiza);
                 if(fin.removeBalancete(b)){
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                    this.apagaObjeto();
             }else{
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                 }
         }
           return "financeiro?faces-redirect=true";
    }
    
    private void apagaObjeto(){
             id_balancete = 0;
             numero_conta = "";
             descricao = "";
             data_inicial = null;
             data_final =null;
             saldo_inicial =0;
    }
      
    public Date dataAtual(){  
      return new Date();
    }    
}
