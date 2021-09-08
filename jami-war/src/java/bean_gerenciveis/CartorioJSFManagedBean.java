/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.CartorioEntity;
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
@Named(value = "cartorioJSFManagedBean")
@SessionScoped
public class CartorioJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops;
    @EJB
    FinanceiroSessionBean fin;
    long id_cartorio;
    String nome_cartorio;
    String nome_cartorio_atualiza;
    String rua_cartorio;
    int nro_cartorio;
    ArrayList<String> list_ruas;
    @Inject
    AlunoJSFManagedBean alm;
    
    public CartorioJSFManagedBean() {
        this.list_ruas = new ArrayList<>();
    }

    public long getId_cartorio() {
        return id_cartorio;
    }

    public void setId_cartorio(long id_cartorio) {
        this.id_cartorio = id_cartorio;
    }

    public String getNome_cartorio() {
        return nome_cartorio;
    }

    public void setNome_cartorio(String nome_cartorio) {
        this.nome_cartorio = nome_cartorio;
    }

    public String getRua_cartorio() {
        return rua_cartorio;
    }

    public void setRua_cartorio(String rua_cartorio) {
        this.rua_cartorio = rua_cartorio;
    }

    public int getNro_cartorio() {
        return nro_cartorio;
    }

    public void setNro_cartorio(int nro_cartorio) {
        this.nro_cartorio = nro_cartorio;
    }

    public List<String> getList_ruas() {
        return ops.selecioneRuas();
    }

    public void setList_ruas(ArrayList<String> list_ruas) {
        this.list_ruas = list_ruas;
    }

    public String getNome_cartorio_atualiza() {
        return nome_cartorio_atualiza;
    }

    public void setNome_cartorio_atualiza(String nome_cartorio_atualiza) {
        this.nome_cartorio_atualiza = nome_cartorio_atualiza;
    }
    
    public String cadastrarCartorio(){
        if(nome_cartorio.equals("") || id_cartorio > 1 || rua_cartorio == null || nro_cartorio <= 0){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
   }else {
               EnderecoEntity e = fin.selecioneRuasPorNome(rua_cartorio);
               CartorioEntity cr = new CartorioEntity();
               cr.setId(id_cartorio);
               cr.setNome_cartorio(nome_cartorio);
               cr.setRua_cartorio(e);
               cr.setNumero_caetorio(nro_cartorio);
               if(ops.cadastreCartorio(cr)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   alm.setCartorio_entity(cr.getNome_cartorio());
                   this.apagaObjeto();
               }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
               }
        }  
    return "aluno?faces-redirect=true";
    }
    
    public void buscarCartorio(){
          if(nome_cartorio.equals("") || rua_cartorio == null || nro_cartorio <= 0){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             CartorioEntity c = new CartorioEntity();
             c = ops.selecioneCartorioAtualiza(nome_cartorio);
            if(c.getNome_cartorio().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_cartorio = c.getId();
             nome_cartorio = c.getNome_cartorio();
             nome_cartorio_atualiza = nome_cartorio;
             rua_cartorio = c.getRua_cartorio().getLogradouro();
             nro_cartorio = c.getNumero_caetorio();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public String atualizarCartorio(){
          if(nome_cartorio.equals("") || id_cartorio < 1 || rua_cartorio == null || nro_cartorio <= 0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
            return "preencha os campos";
        }else {
               EnderecoEntity e = fin.selecioneRuasPorNome(rua_cartorio);
               CartorioEntity c = new CartorioEntity();
               c.setId(id_cartorio);
               c.setNome_cartorio(nome_cartorio_atualiza);
               c.setRua_cartorio(e);
               c.setNumero_caetorio(nro_cartorio);
             if(ops.atualizaCartorio(c)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
          }
          return "aluno?faces-redirect=true";
    }
     
      /*public void excluirCartorio(){
            if(nome_cartorio.equals("") || id_cartorio < 10 || rua_cartorio == null || nro_cartorio <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
               EnderecoEntity e = fin.selecioneRuasPorNome(rua_cartorio);
               CartorioEntity c = new CartorioEntity();
               c.setId(id_cartorio);
               c.setNome_cartorio(nome_cartorio);
               c.setRua_cartorio(e);
               c.setNumero_caetorio(nro_cartorio);
             if(ops.removeCartorio(c))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
           else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           this.apagaObjeto();
      }*/
      
      private void apagaObjeto(){
             id_cartorio = 0;
             nome_cartorio = "";
             nome_cartorio_atualiza="";
             rua_cartorio = "";
             nro_cartorio = 0;
    }
}
