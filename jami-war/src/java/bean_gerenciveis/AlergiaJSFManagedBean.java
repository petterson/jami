/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.OperacionalSessionBean;
import entidades.AlergiaEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "alergiaJSFManagedBean")
@SessionScoped
public class AlergiaJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops;
    long id_alergia;
    String nome_alergia;
    String nome_alergia_atualiza;
    String causa_alergia;
    String grau_perigo_alergia;
    ArrayList<String> list_graus;
    @Inject
    AlunoJSFManagedBean alm;
    
    public AlergiaJSFManagedBean() {
        this.list_graus = new ArrayList<>();
    }

    public long getId_alergia() {
        return id_alergia;
    }

    public void setId_alergia(long id_alergia) {
        this.id_alergia = id_alergia;
    }

    public String getNome_alergia() {
        return nome_alergia;
    }

    public void setNome_alergia(String nome_alergia) {
        this.nome_alergia = nome_alergia;
    }

    public String getCausa_alergia() {
        return causa_alergia;
    }

    public void setCausa_alergia(String causa_alergia) {
        this.causa_alergia = causa_alergia;
    }

    public String getGrau_perigo_alergia() {
        return grau_perigo_alergia;
    }

    public void setGrau_perigo_alergia(String grau_perigo_alergia) {
        this.grau_perigo_alergia = grau_perigo_alergia;
    }

    public ArrayList<String> getList_graus() {
        return list_graus;
    }

    public void setList_graus(ArrayList<String> list_graus) {
        this.list_graus = list_graus;
    }

    public String getNome_alergia_atualiza() {
        return nome_alergia_atualiza;
    }

    public void setNome_alergia_atualiza(String nome_alergia_atualiza) {
        this.nome_alergia_atualiza = nome_alergia_atualiza;
    }
    
    public String cadastrarProblemaAlergia(){
        if(nome_alergia.equals("") || id_alergia >1 || causa_alergia.equals("") || grau_perigo_alergia == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              AlergiaEntity alergia = new AlergiaEntity();
              alergia.setId(id_alergia);
              alergia.setNome_alergia(nome_alergia);
              alergia.setCausa_alergia(causa_alergia);
              alergia.setGrau_perigo_alergia(grau_perigo_alergia);
              if(ops.cadastreAlergia(alergia)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
              alm.setAlergia_entity(alergia.getNome_alergia());
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
        } 
        this.apagaObjeto();
    return "aluno?faces-redirect=true";
    }
    
    public void buscarProblemaAlergia(){
          if(nome_alergia.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             AlergiaEntity a = new AlergiaEntity();
             a = ops.selecioneAlergiaAtualiza(nome_alergia);
            if(a.getNome_alergia().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_alergia = a.getId();
             nome_alergia = a.getNome_alergia();
             nome_alergia_atualiza = nome_alergia;
             grau_perigo_alergia = a.getGrau_perigo_alergia();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public String atualizarProblemaAlergia(){
          if(nome_alergia.equals("") || id_alergia < 1 || causa_alergia.equals("") || grau_perigo_alergia == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           return "preencha os campos";
      }else{
             AlergiaEntity a = new AlergiaEntity();
             a.setId(id_alergia);
             a.setNome_alergia(nome_alergia_atualiza);
             a.setGrau_perigo_alergia(grau_perigo_alergia);
             if(ops.atualizaAlergia(a)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
          }
          return "aluno?faces-redirect=true";
    }
     
    public String excluirProblemaAlergia(){
          if(nome_alergia.equals("") || id_alergia < 10 || causa_alergia.equals("") || grau_perigo_alergia == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
             AlergiaEntity a = new AlergiaEntity();
             a.setId(id_alergia);
             a.setNome_alergia(nome_alergia_atualiza);
             a.setGrau_perigo_alergia(grau_perigo_alergia);
             if(ops.removeAlergia(a)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                return "ID Não Válido!";
             }
           }
           return "aluno?faces-redirect=true";
    }
    
     private void apagaObjeto(){
             id_alergia = 0;
             nome_alergia = "";
             nome_alergia_atualiza = "";
             causa_alergia ="";
             grau_perigo_alergia = "";
    }
}
