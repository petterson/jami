/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.OperacionalSessionBean;
import entidades.ChamadaEntity;
import entidades.TurmaEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "chamadaJSFManagedBean")
@SessionScoped
public class ChamadaJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops;
    long id_chamada;
    String nome_chamada;
    String nome_chamada_atualiza;
    Date data_chamada;
    ArrayList<String> nome_chamadas;
    @Inject
    TurmaSFManagedBean tum;
    TurmaEntity t;
    
    public ChamadaJSFManagedBean() {
        t = new TurmaEntity();
    }

    public long getId_chamada() {
        return id_chamada;
    }

    public void setId_chamada(long id_chamada) {
        this.id_chamada = id_chamada;
    }

    public String getNome_chamada() {
        return nome_chamada;
    }

    public void setNome_chamada(String nome_chamada) {
        this.nome_chamada = nome_chamada;
    }

    public Date getData_chamada() {
        return data_chamada;
    }

    public void setData_chamada(Date data_chamada) {
        this.data_chamada = data_chamada;
    }

    public String getNome_chamada_atualiza() {
        return nome_chamada_atualiza;
    }

    public void setNome_chamada_atualiza(String nome_chamada_atualiza) {
        this.nome_chamada_atualiza = nome_chamada_atualiza;
    }

    public TurmaEntity getT() {
        return t;
    }

    public void setT(TurmaEntity t) {
        this.t = t;
    }
    
    public String cadastrarchamada(){
        if(nome_chamada.equals("") || id_chamada > 1 || data_chamada == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
        ctx.addMessage(null, msg);
            //return "preencha os campos";
        }else {
               ChamadaEntity cha = new ChamadaEntity();
               cha.setId(id_chamada);
               cha.setNome_chamada(nome_chamada);
               cha.setData_criacao_chamada(data_chamada);
               if(ops.cadastreChamada(cha)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   nome_chamadas = new ArrayList<>(ops.selecioneChamadas());
                   tum.setNome_chamada_entity(cha.getNome_chamada());
                   this.apagaObjeto();
               }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
               }
               nome_chamadas = new ArrayList<>(ops.selecioneChamadas());
               tum.setNome_chamada_entity(cha.getNome_chamada());      
        }
    return "turma?faces-redirect=true";
    }
     
    public void excluirchamada(){
           if(nome_chamada.equals("") || id_chamada < 1 || data_chamada == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           }else{
          ChamadaEntity c = new ChamadaEntity();
             c.setId(id_chamada);
             c.setNome_chamada(nome_chamada_atualiza);
             c.getData_criacao_chamada();
             if(t == null){
             if(ops.removeChamada(c)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
           }else{
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não pode ser Removida!")); 
                }
           }
           this.apagaObjeto();
    }
      
    public void buscarChamada(){
          if(nome_chamada.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            ChamadaEntity c = new ChamadaEntity();
         c = ops.selecioneChamadaAtualiza(nome_chamada);
         if(c.getNome_chamada().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
               
             id_chamada = c.getId();
             nome_chamada = c.getNome_chamada();
             nome_chamada_atualiza = nome_chamada;
             data_chamada = c.getData_criacao_chamada();
             t = c.getTurma();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    }
    
     private void apagaObjeto(){
             id_chamada = 0;
             nome_chamada = "";
             nome_chamada_atualiza ="";
             data_chamada = null;
             t=null;
    }
      
    public Date dataAtual(){
      return new Date();
    }
}
