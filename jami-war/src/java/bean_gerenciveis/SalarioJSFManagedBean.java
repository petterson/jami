/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.SalarioEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "salarioJSFManagedBean")
@SessionScoped
public class SalarioJSFManagedBean implements Serializable {

        @EJB
        GerenteSessionBean geb;
        long id_salario;
        double valor_salario;
        String salario_selecionado;
        double troca_calor;
        ArrayList<String> salarios;
        ArrayList<Double> salarios_double;
        @Inject
        CargoJSFManagedBean cam;
        
    
    public SalarioJSFManagedBean() {
        this.salarios = new ArrayList<>();
        this.salarios_double = new ArrayList<>();
    }

    public long getId_salario() {
        return id_salario;
    }

    public void setId_salario(long id_salario) {
        this.id_salario = id_salario;
    }

    public double getValor_salario() {
        return valor_salario;
    }

    public void setValor_salario(double valor_salario) {
        this.valor_salario = valor_salario;
    }

    public String getSalario_selecionado() {
        return salario_selecionado;
    }

    public void setSalario_selecionado(String salario_selecionado) {
        this.salario_selecionado = salario_selecionado;
    }

    public List<Double> getSalarios_double() {
        return geb.selecioneSalarios();
    }

    public void setSalarios_double(ArrayList<Double> salarios_double) {
        this.salarios_double = salarios_double;
    }

    public double getTroca_calor() {
        return troca_calor;
    }

    public void setTroca_calor(double troca_calor) {
        this.troca_calor = troca_calor;
    }
    
    public String cadastrarSalario(){
        if(valor_salario <= 500){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              SalarioEntity sa = new SalarioEntity();
              sa.setId(id_salario);
              sa.setValor_salario(valor_salario);
              if(geb.cadastreSalario(sa)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   cam.setNovo_valor_entity(sa.getValor_salario());
              }else{
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Salário!"));
                  FacesContext ctx = FacesContext.getCurrentInstance();
                  FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                  ctx.addMessage(null, msg);
                  return "preencha os campos";
              } 
        }
        this.apagaObjeto();
    return "cargo?faces-redirect=true";
    }
    
    public void buscarSalario(){
           if(valor_salario < 10){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             SalarioEntity s = new SalarioEntity();
             s = geb.selecioneSalarioAtualiza(valor_salario);
            if(s.getValor_salario()== 0){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_salario = s.getId();
             valor_salario = s.getValor_salario();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    } 
     
      public void excluirSalario(){
           if(valor_salario < 1000){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           }else{
          SalarioEntity s = new SalarioEntity();
             s.setId(id_salario);
             s.setValor_salario(valor_salario);
          if(geb.removeSalario(s))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           this.apagaObjeto();
    }
      
    private void apagaObjeto(){
             id_salario = 0;
             valor_salario = 0;
    }
      
    public void listaSalarioSpynner(AjaxBehaviorEvent event){
           valor_salario = troca_calor;
    }
      
    public List<String> converteString(List<Double> d){
          salarios = new ArrayList<>();
          String s="$ ";
          String f ="0";
          for(int i=0; i< d.size();i++){
             String n = String.valueOf(d.get(i));
             String h = s+n+f;
             salarios.add(h);
          }
          return salarios;
    }
      
}
