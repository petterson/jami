/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.CargoEntity;
import entidades.SalarioEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "cargoJSFManagedBean")
@SessionScoped
public class CargoJSFManagedBean implements Serializable {

        @EJB
        GerenteSessionBean geb;
        long id_cargo;
        String nome_cargo;
        String nome_cargo_atualiza;
        double salario_cargo;
        double novo_valor_entity;
        List<Double> list_salarios;
        @Inject
        FuncionarioJSFManagedBean fum;
    
    public CargoJSFManagedBean() {
        this.list_salarios = new ArrayList<>();
    }

    public long getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(long id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getNome_cargo() {
        return nome_cargo;
    }

    public void setNome_cargo(String nome_cargo) {
        this.nome_cargo = nome_cargo;
    }

    public double getSalario_cargo() {
        return salario_cargo;
    }

    public void setSalario_cargo(double salario_cargo) {
        this.salario_cargo = salario_cargo;
    }

    public double getNovo_valor_entity() {
        return novo_valor_entity;
    }

    public void setNovo_valor_entity(double novo_valor_entity) {
         this.list_salarios.add(novo_valor_entity);
        this.novo_valor_entity = novo_valor_entity;
    }

    public List<Double> getList_salarios() {
        return geb.selecioneSalarios();
    }

    public void setList_salarios(List<Double> list_salarios) {
        this.list_salarios = list_salarios;
    }

    public String getNome_cargo_atualiza() {
        return nome_cargo_atualiza;
    }

    public void setNome_cargo_atualiza(String nome_cargo_atualiza) {
        this.nome_cargo_atualiza = nome_cargo_atualiza;
    }
    
    public String cadastrarCargo(){
        if( nome_cargo.equals("") || salario_cargo <=500 || id_cargo > 1){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
            ctx.addMessage(null, msg);
            //return "preencha os campos";
        }else{
            SalarioEntity s = geb.selecioneSalarioAtualiza(salario_cargo);
            CargoEntity ca = new CargoEntity();
            ca.setId(id_cargo);
            ca.setNome_cargo(nome_cargo);
            ca.setValor_salario(s);
            if(geb.cadastreCargo(ca)){
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                fum.setNome_cargo_entity(ca.getNome_cargo());
            }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                FacesContext ctx = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                ctx.addMessage(null, msg);
                return "preencha os campos";
            }
        }
        this.apagaObjeto();
    return "funcionario?faces-redirect=true";
    }
    
    public void buscarCargo(){
         if(nome_cargo.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            CargoEntity c = new CargoEntity();
         c = geb.selecioneCargoAtualiza(nome_cargo);
         if(c.getNome_cargo().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_cargo = c.getId();
             nome_cargo = c.getNome_cargo();
             nome_cargo_atualiza = nome_cargo;
             salario_cargo = c.getValor_salario().getValor_salario();
           }
         }
    }
    
    public void atualizarCargo(){
         if(nome_cargo.equals("") || id_cargo < 10){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
    }else {
             SalarioEntity s = geb.selecioneSalarioAtualiza(salario_cargo);
             CargoEntity c = new CargoEntity();
             c.setId(id_cargo);
             c.setNome_cargo(nome_cargo_atualiza);
             c.setValor_salario(s);
             if(geb.atualizaCargo(c))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
          }
          this.apagaObjeto();
    }
    
    public void excluirCargo(){
         if(nome_cargo.equals("") || id_cargo < 10){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
             SalarioEntity s = geb.selecioneSalarioAtualiza(salario_cargo);
             CargoEntity c = new CargoEntity();
             c.setId(id_cargo);
             c.setNome_cargo(nome_cargo_atualiza);
             c.setValor_salario(s);
          if(geb.removeCargo(c))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           this.apagaObjeto();
      }
    
    private void apagaObjeto(){
             id_cargo = 0;
             nome_cargo = "";
             nome_cargo_atualiza="";
             salario_cargo = 0;
    }
}
