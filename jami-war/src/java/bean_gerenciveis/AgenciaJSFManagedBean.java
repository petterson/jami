/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import entidades.AgenciaEntity;
import entidades.EnderecoEntity;
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
@Named(value = "agenciaJSFManagedBean")
@SessionScoped
public class AgenciaJSFManagedBean implements Serializable {

           @EJB
           FinanceiroSessionBean fin;
           long id_agencia;
           String numero_agencia;
           String nome_agencia;
           String nome_gencia_atualiza;
           String fone_agencia;
           String email_agencia;
           String rua_agencia;
           int numero_rua_agencia;
           ArrayList<String> nome_ruas;
           @Inject
           ContaJSFManagedBean com;
    
    public AgenciaJSFManagedBean() {
        this.nome_ruas = new ArrayList<>();
    }

    public long getId_agencia() {
        return id_agencia;
    }

    public void setId_agencia(long id_agencia) {
        this.id_agencia = id_agencia;
    }

    public String getNumero_agencia() {
        return numero_agencia;
    }

    public void setNumero_agencia(String numero_agencia) {
        this.numero_agencia = numero_agencia;
    }
    
    public String getNome_agencia() {
        return nome_agencia;
    }

    public void setNome_agencia(String nome_agencia) {
        this.nome_agencia = nome_agencia;
    }

    public String getFone_agencia() {
        return fone_agencia;
    }

    public void setFone_agencia(String fone_agencia) {
        this.fone_agencia = fone_agencia;
    }

    public String getEmail_agencia() {
        return email_agencia;
    }

    public void setEmail_agencia(String email_agencia) {
        this.email_agencia = email_agencia;
    }

    public String getRua_agencia() {
        return rua_agencia;
    }

    public void setRua_agencia(String rua_agencia) {
        this.rua_agencia = rua_agencia;
    }

    public int getNumero_rua_agencia() {
        return numero_rua_agencia;
    }

    public void setNumero_rua_agencia(int numero_rua_agencia) {
        this.numero_rua_agencia = numero_rua_agencia;
    }

    public List<String> getNome_ruas() {
        return fin.selecioneRuas();
    }

    public void setNome_ruas(ArrayList<String> nome_ruas) {
        this.nome_ruas = nome_ruas;
    }

    public String getNome_gencia_atualiza() {
        return nome_gencia_atualiza;
    }

    public void setNome_gencia_atualiza(String nome_gencia_atualiza) {
        this.nome_gencia_atualiza = nome_gencia_atualiza;
    }
    
    public String cadastrarAgencia(){
        if(nome_agencia.equals("") || id_agencia > 1 || numero_agencia.equals("") || nome_agencia.equals("") || email_agencia.equals("")
           || numero_rua_agencia <=0){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              EnderecoEntity en = fin.selecioneRuasPorNome(rua_agencia);
              AgenciaEntity ag = new AgenciaEntity();
              ag.setId(id_agencia);
              ag.setNumero_agencia(numero_agencia);
              ag.setNome_agencia(nome_agencia);
              ag.setFone_agencia(fone_agencia);
              ag.setEmail_agencia(email_agencia);
              ag.setRua_agencia(en);
              ag.setNumero_rua_agencia(numero_rua_agencia);
              if(fin.cadastreAgencia(ag)){
                 com.setNumero_agencia_entity(numero_agencia);
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
    return "conta?faces-redirect=true";
    }
    
    public void buscarAgencia(){
         if(nome_agencia.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            AgenciaEntity a = new AgenciaEntity();
         a = fin.selecioneAgenciaAtualiza(nome_agencia);
         if(a.getNome_agencia().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_agencia = a.getId();
             numero_agencia = a.getNumero_agencia();
             nome_agencia = a.getNome_agencia();
             nome_gencia_atualiza = nome_agencia;
             fone_agencia = a.getFone_agencia();
             email_agencia = a.getEmail_agencia();
             rua_agencia = a.getRua_agencia().getLogradouro();
             numero_rua_agencia = a.getNumero_rua_agencia();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
          }
    }
    
    
    public void atualizarAgencia(){
         if(nome_agencia.equals("") || id_agencia < 1 || numero_agencia.equals("") || nome_agencia.equals("") 
           || email_agencia.equals("") || numero_rua_agencia <=0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
      }else{ 
             EnderecoEntity en = fin.selecioneRuasPorNome(rua_agencia);
              AgenciaEntity a = new AgenciaEntity();
              a.setId(id_agencia);
              a.setNumero_agencia(numero_agencia);
              a.setNome_agencia(nome_gencia_atualiza);
              a.setFone_agencia(fone_agencia);
              a.setEmail_agencia(email_agencia);
              a.setRua_agencia(en);
              a.setNumero_rua_agencia(numero_rua_agencia);
              if(fin.atualizaAgencia(a))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
          }
          this.apagaObjeto();
    }
     
    public void excluirAgencia(){
         if(nome_agencia.equals("") || id_agencia < 1 || numero_agencia.equals("") || nome_agencia.equals("") 
            || email_agencia.equals("") || numero_rua_agencia <=0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
              EnderecoEntity en = fin.selecioneRuasPorNome(rua_agencia);
              AgenciaEntity a = new AgenciaEntity();
              a.setId(id_agencia);
              a.setNumero_agencia(numero_agencia);
              a.setNome_agencia(nome_gencia_atualiza);
              a.setFone_agencia(fone_agencia);
              a.setEmail_agencia(email_agencia);
              a.setRua_agencia(en);
              a.setNumero_rua_agencia(numero_rua_agencia);
              if(fin.removeAgencia(a))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
            else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           this.apagaObjeto();
    }
    
    private void apagaObjeto(){
             id_agencia = 0;
             numero_agencia = "";
             nome_agencia = "";
             nome_gencia_atualiza = "";
             fone_agencia = "";
             email_agencia ="";
             rua_agencia ="";
             numero_rua_agencia = 0;
    }
      
}
