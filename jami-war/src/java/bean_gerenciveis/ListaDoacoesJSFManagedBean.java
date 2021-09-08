/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.DoacaoEntity;
import entidades.DoadorEntity;
import entidades.TipoDoacaoEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author petterson
 */
@Named(value = "listaDoacoesJSFManagedBean")
@ViewScoped
public class ListaDoacoesJSFManagedBean implements Serializable {

        @EJB
        GerenteSessionBean geb;
        String doador_selecionado;
        List<String> nome_doadores;
        List<DoacaoEntity> list_doacoes;
        DoacaoEntity d;
        Date data_inicial;
        Date data_final;
    
    public ListaDoacoesJSFManagedBean() {
        d = new DoacaoEntity();
        this.list_doacoes = new ArrayList<>();
    }

    public String getDoador_selecionado() {
        return doador_selecionado;
    }

    public void setDoador_selecionado(String doador_selecionado) {
        this.doador_selecionado = doador_selecionado;
    }

    public List<String> getNome_doadores() {
        return geb.selecioneDoadoresComDoacoes();
    }

    public void setNome_doadores(ArrayList<String> nome_doadores) {
        this.nome_doadores = nome_doadores;
    }

    public List<DoacaoEntity> getList_doacoes() {
        return list_doacoes;
    }

    public void setList_doacoes(ArrayList<DoacaoEntity> list_doacoes) {
        this.list_doacoes = list_doacoes;
    }

    public DoacaoEntity getD() {
        return d;
    }

    public void setD(DoacaoEntity d) {
    this.d = d;
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
    
    public void buscarDoacoes(){
         if(doador_selecionado == null || data_inicial == null || data_final == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
        FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Preencha os Campos Corretamente!"));
            
      }else{
         if(doador_selecionado.contains("Todas")){
             this.list_doacoes = geb.getTodasDoacoes(data_inicial, data_final);
             if(list_doacoes.size() <= 0)
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("não contém Doações"));
         }else{
             this.list_doacoes = geb.getDoacoes(doador_selecionado, data_inicial, data_final);
             if(list_doacoes.size() <= 0)
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Esse não contém Doações"));
         }
       }
    }
    
    public void excluirDoacao(){
          if(doador_selecionado == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
      }else{
          if(d == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Não temlinha selecionada");
              ctx.addMessage(null, msg);
       }else{
              //DoadorEntity doa = d.getDoador();
              TipoDoacaoEntity t = d.getNome_doacao();
              if(t.getDoacoes().size() > 1){
                 t.getDoacoes().remove(d);
                 geb.atualizaTipoDoacao(t);
              }
              //if(doa.getDoacoes().size() > 1){
                // doa.getDoacoes().remove(d);
                 //geb.atualizaDoador(doa);
              //}
                 this.list_doacoes.remove(d);
                 if(geb.removeDoacao(d)){
                    d = null;
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Prencha os atributos"));
                }
             }
          }
     }
}
