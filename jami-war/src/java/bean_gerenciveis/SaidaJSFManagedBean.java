/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import entidades.SaidaEntity;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "saidaJSFManagedBean")
@ViewScoped
public class SaidaJSFManagedBean implements Serializable {

        @EJB
        FinanceiroSessionBean fin;
        long id_saida;
        Date data_emissao_saida;
        Date data_compensacao_saida;
        String nome_cheque_saida;
        String nome_descricao_saida;
        double valor_saida;
        String saldo_saida;
        String nome_descricao_saida_entity;
        List<String> nome_descricoes_saidas;
        String nome_cheque_entity;
        ArrayList<String> nome_cheques;
        List<SaidaEntity> saidas;
        SaidaEntity s;
        @Inject
        AtualizaBalanceteJSFManagedBean atm;
    
    public SaidaJSFManagedBean() {
        s = new SaidaEntity();
         this.saidas = new ArrayList<>();
         this.nome_cheques = new ArrayList<>();
         this.nome_descricoes_saidas = new ArrayList<>();
    }

    public Date getData_emissao_saida() {
        return data_emissao_saida;
    }

    public void setData_emissao_saida(Date data_emissao_saida) {
        this.data_emissao_saida = data_emissao_saida;
    }

    public Date getData_compensacao_saida() {
        return data_compensacao_saida;
    }

    public void setData_compensacao_saida(Date data_compensacao_saida) {
        this.data_compensacao_saida = data_compensacao_saida;
    }

    public String getNome_cheque_saida() {
        return nome_cheque_saida;
    }

    public void setNome_cheque_saida(String nome_cheque_saida) {
        this.nome_cheque_saida = nome_cheque_saida;
    }

    public String getNome_descricao_saida() {
        return nome_descricao_saida;
    }

    public void setNome_descricao_saida(String nome_descricao_saida) {
        this.nome_descricao_saida = nome_descricao_saida;
    }

    public String getNome_descricao_saida_entity() {
        return nome_descricao_saida_entity;
    }

    public void setNome_descricao_saida_entity(String nome_descricao_saida_entity) {
            this.nome_descricoes_saidas.add(nome_descricao_saida_entity);
    this.nome_descricao_saida_entity = nome_descricao_saida_entity;
    }

    public List<String> getNome_descricoes_saidas() {
        return fin.selecioneDescricoesSaida();
    }

    public void setNome_descricoes_saidas(ArrayList<String> nome_descricoes_saidas) {
        this.nome_descricoes_saidas = nome_descricoes_saidas;
    }

    public List<String> getNome_cheques() {
        return fin.selecioneCheques();
    }

    public void setNome_cheques(ArrayList<String> nome_cheques) {
        this.nome_cheques = nome_cheques;
    }

    public double getValor_saida() {
        return valor_saida;
    }

    public void setValor_saida(double valor_saida) {
        this.valor_saida = valor_saida;
    }

    public String getSaldo_saida() {
        return saldo_saida;
    }

    public void setSaldo_saida(String saldo_saida) {
        this.saldo_saida = saldo_saida;
    }

    public long getId_saida() {
        return id_saida;
    }

    public void setId_saida(long id_saida) {
        this.id_saida = id_saida;
    }

    public String getNome_cheque_entity() {
        return nome_cheque_entity;
    }

    public void setNome_cheque_entity(String nome_cheque_entity) {
        this.nome_cheques.add(nome_cheque_entity);
     this.nome_cheque_entity = nome_cheque_entity;
    }

    public List<SaidaEntity> getSaidas() {
        return fin.selecioneSaidasDate(atm.getData_inicial(), atm.getData_final());
    }

    public void setSaidas(List<SaidaEntity> saidas) {
        this.saidas = saidas;
    }

    public SaidaEntity getS() {
        return s;
    }

    public void setS(SaidaEntity s) {
        this.s = s;
    }

    public String cadastrarSaida(){
        if(data_emissao_saida ==null || data_compensacao_saida == null || nome_cheque_saida == null 
           || nome_descricao_saida == null || valor_saida <= 10){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              SaidaEntity sa = new SaidaEntity();
              sa.setId(id_saida);
              sa.setData_emissao(data_emissao_saida);
              sa.setData_compensacao(data_compensacao_saida);
              sa.setNome_cheque(nome_cheque_saida);
              sa.setNome_descricao(nome_descricao_saida);
              sa.setValor_saida(valor_saida);
              if(fin.cadastreSaida(sa)){
                  atm.setSa(sa);
                  this.saidas.add(sa);
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
              }
        } 
    return "atualizar_balancete?faces-redirect=true";
    }
     
      public String excluirSaida(){
           if(s == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Não temlinha selecionada");
              ctx.addMessage(null, msg);
       }else{
               if(atm.removeSaida(s)){
                 fin.removeSaida(s.getId());
                 this.saidas.remove(s);
                 s = null;
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                }
           }
           this.refresh();
           return "atualizar_balancete?faces-redirect=true";
      }
      
      public Date dataAtual(){
         SimpleDateFormat de = new SimpleDateFormat("dd/MM/yyyy");
              Date d = new Date();
              de.format(d);   
       return d;
     }
    
    public void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}
}
