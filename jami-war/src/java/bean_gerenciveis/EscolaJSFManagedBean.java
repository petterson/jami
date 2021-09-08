/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.EnderecoEntity;
import entidades.EscolaEntity;
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
@Named(value = "escolaJSFManagedBean")
@SessionScoped
public class EscolaJSFManagedBean implements Serializable {

         @EJB
         OperacionalSessionBean ops;
         @EJB
         FinanceiroSessionBean fin;
         long id_escola;
         String nome_escola;
         String nome_escola_atualiza;
         String rua_escola;
         int numero_escola;
         ArrayList<String> nome_das_ruas;
         @Inject
         AlunoJSFManagedBean alm;
    
    public EscolaJSFManagedBean() {
    }

    public long getId_escola() {
        return id_escola;
    }

    public void setId_escola(long id_escola) {
        this.id_escola = id_escola;
    }

    public String getNome_escola() {
        return nome_escola;
    }

    public void setNome_escola(String nome_escola) {
        this.nome_escola = nome_escola;
    }

    public String getRua_escola() {
        return rua_escola;
    }

    public void setRua_escola(String rua_escola) {
        this.rua_escola = rua_escola;
    }

    public int getNumero_escola() {
        return numero_escola;
    }

    public void setNumero_escola(int numero_escola) {
        this.numero_escola = numero_escola;
    }

    public List<String> getNome_das_ruas() {
        return ops.selecioneRuas();
    }

    public void setNome_das_ruas(ArrayList<String> nome_das_ruas) {
        this.nome_das_ruas = nome_das_ruas;
    }

    public String getNome_escola_atualiza() {
        return nome_escola_atualiza;
    }

    public void setNome_escola_atualiza(String nome_escola_atualiza) {
        this.nome_escola_atualiza = nome_escola_atualiza;
    }

     public String cadastrarEscola(){
        if(nome_escola.equals("") || id_escola > 1 || rua_escola.equals("") || numero_escola <= 0){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else {
               EnderecoEntity en = fin.selecioneRuasPorNome(rua_escola);
               EscolaEntity e = new EscolaEntity();
               e.setId(id_escola);
               e.setNome_escola(nome_escola);
               e.setRua_escola(en);
               e.setNumero_escola(numero_escola);
               if(ops.cadastreEscola(e)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   alm.setEscola_entity(e.getNome_escola());
               } else{
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
     
    public void buscarEscola(){
          if(nome_escola.equals("")|| rua_escola.equals("") || numero_escola <= 0){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             EscolaEntity e = new EscolaEntity();
             e = ops.selecioneEscolaAtualiza(nome_escola);
            if(e.getNome_escola().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
                
             id_escola = e.getId();
             nome_escola = e.getNome_escola();
             nome_escola_atualiza = nome_escola;
             rua_escola = e.getRua_escola().getLogradouro();
             numero_escola = e.getNumero_escola();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public String atualizarEscola(){
          if(nome_escola.equals("") || id_escola < 1 || rua_escola.equals("") || numero_escola <= 0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              EnderecoEntity en = fin.selecioneRuasPorNome(rua_escola);
               EscolaEntity e = new EscolaEntity();
               e.setId(id_escola);
               e.setNome_escola(nome_escola_atualiza);
               e.setRua_escola(en);
               e.setNumero_escola(numero_escola);
             if(ops.atualizaEscola(e)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
          }
          return "aluno?faces-redirect=true";
    }
     
    /*public void excluirEscola(){
            if(nome_escola.equals("") || id_escola < 10 || rua_escola.equals("") || numero_escola <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
                EnderecoEntity en = fin.selecioneRuasPorNome(rua_escola);
               EscolaEntity e = new EscolaEntity();
               e.setId(id_escola);
               e.setNome_escola(nome_escola);
               e.setRua_escola(en);
               e.setNumero_escola(numero_escola);
             if(ops.removeEscola(e))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
           else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           this.apagaObjeto();
    }*/ 
      
      private void apagaObjeto(){
             id_escola = 0;
             nome_escola = "";
             nome_escola_atualiza="";
             rua_escola = "";
             numero_escola = 0;
    
    }
}
