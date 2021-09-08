/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.DepartamentoEntity;
import entidades.FuncionarioEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "departamentoJSFManagedBean")
@ViewScoped
public class DepartamentoJSFManagedBean implements Serializable {

        @EJB
        GerenteSessionBean geb;
        long id_departamento;
        String nome_dapartamento;
        String nome_departamento_atualiza;
        List<FuncionarioEntity> f;
        //ArrayList<String> nome_funcinarios;
        //String nome_funcionario_entity;
        //ArrayList<String> nome_funcionarios_selecionados;
        @Inject
        FuncionarioJSFManagedBean fum;
    
    public DepartamentoJSFManagedBean() {
        //this.nome_funcinarios = new ArrayList<>();
        this.f = new ArrayList<>();
    }

    public long getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(long id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNome_dapartamento() {
        return nome_dapartamento;
    }

    public void setNome_dapartamento(String nome_dapartamento) {
        this.nome_dapartamento = nome_dapartamento;
    }

    public List<String> getNome_funcinarios() {
        return geb.selecioneFuncionarios();
    }

    public List<FuncionarioEntity> getF() {
        return f;
    }

    public void setF(List<FuncionarioEntity> f) {
        this.f = f;
    }

    /*public void setNome_funcinarios(ArrayList<String> nome_funcinarios) {
        this.nome_funcinarios = nome_funcinarios;
    }

    public ArrayList<String> getNome_funcionarios_selecionados() {
        return nome_funcionarios_selecionados;
    }

    public void setNome_funcionarios_selecionados(ArrayList<String> nome_funcionarios_selecionados) {
        this.nome_funcionarios_selecionados = nome_funcionarios_selecionados;
    }

    public String getNome_funcionario_entity() {
        return nome_funcionario_entity;
    }

    public void setNome_funcionario_entity(String nome_funcionario_entity) {
          this.nome_funcinarios.add(nome_funcionario_entity);
        this.nome_funcionario_entity = nome_funcionario_entity;
    }*/

    public String getNome_departamento_atualiza() {
        return nome_departamento_atualiza;
    }

    public void setNome_departamento_atualiza(String nome_departamento_atualiza) {
        this.nome_departamento_atualiza = nome_departamento_atualiza;
    }
    
    public String cadastrarDepartamento(){
        if(nome_dapartamento.equals("") || id_departamento > 1){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido!");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              //List<FuncionarioEntity> f = geb.selecioneListaFuncionario(nome_funcionarios_selecionados);
              DepartamentoEntity dp = new DepartamentoEntity();
              dp.setId(id_departamento);
              dp.setNome_departamento(nome_dapartamento);
              dp.setFuncionarios(this.f);
              //List<FuncionarioEntity> l=f;
              /*for(int i=0; i<l.size(); i++){
                  if(f.get(i).getDepartamento() == null){
                      System.out.println();
               }else{
                     f.remove(l.get(i));
                  }
              }
              if(f.size() <= 0){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Existe Funcionário!"));
              }else{
              dp.setFuncionarios(f);*/
              if(geb.cadastreDepartamento(dp)){
                  //for(int i=0; i<f.size(); i++){
                 // FuncionarioEntity fu = f.get(i);
                  //fu.setDepartamento(dp);
                  //geb.atualizaFuncionario(fu);
              //}
                   fum.setNome_departamento_entity(dp.getNome_departamento());
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   this.apagaObjeto();
           }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido!");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
             // }
          }
        }
    return "funcionario?faces-redirect=true";
    }
    
    public void buscarDepartamento(){
         if(nome_dapartamento.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            DepartamentoEntity d = new DepartamentoEntity();
         d = geb.selecioneDepartamentoAtualiza(nome_dapartamento);
         if(d.getNome_departamento().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_departamento = d.getId();
             nome_dapartamento = d.getNome_departamento();
             nome_departamento_atualiza = nome_dapartamento;
             this.f = d.getFuncionarios();
             //nome_funcionarios_selecionados = this.preencheNomeFuncionario(d.getFuncionarios());
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    /*public void atualizarDepartamento(){
         if(nome_dapartamento.equals("") || id_departamento < 10 || nome_funcionarios_selecionados.size() <= 0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
    }else{
           List<FuncionarioEntity> f = geb.selecioneListaFuncionario(nome_funcionarios_selecionados);
           DepartamentoEntity d = new DepartamentoEntity();
           d.setId(id_departamento);
           d.setNome_departamento(nome_departamento_atualiza);
           List<FuncionarioEntity> l=f;
              for(int i=0; i<l.size(); i++){
                  if(f.get(i).getDepartamento() == null){
                      System.out.println();
             }else{
                    if(f.get(i).getDepartamento().getNome_departamento().equals(nome_departamento_atualiza))
                       System.out.println();
                  else
                        f.remove(l.get(i));
                  }
              }
           d.setFuncionarios(f);
             if(geb.atualizaDepartamento(d)){
                 for(int i=0; i< f.size(); i++){
                     FuncionarioEntity fu = f.get(i);
                     fu.setDepartamento(d);
                     geb.atualizaFuncionario(fu);
                 }
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
         }
    }*/
     
    public void excluirDepartamento(){
         if(nome_dapartamento.equals("") || id_departamento < 10){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
      }else{
             DepartamentoEntity d = new DepartamentoEntity();
             d.setId(id_departamento);
             d.setNome_departamento(nome_departamento_atualiza);
             d.setFuncionarios(this.f);
          if(geb.removeDepartamento(d))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           this.apagaObjeto();
    }
    
    private void apagaObjeto(){
             id_departamento = 0;
             nome_dapartamento = "";
             nome_departamento_atualiza="";
             f.clear();
    }
    
     private ArrayList<String> preencheNomeFuncionario(List<FuncionarioEntity> a){
          ArrayList<String> al = new ArrayList<>();        
          for(int i=0; i<a.size(); i++){
              String s = a.get(i).getPessoa().getNome();
              al.add(s);
          }
      return al;
    } 
}
