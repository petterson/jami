/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.PapelEntity;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author sueder
 */
@Named(value = "credenciaJSFManagedBean")
@SessionScoped
public class CredenciaJSFManagedBean implements Serializable {

     @EJB
      GerenteSessionBean geb;
      String usuario;
      String senha;
      @Inject
      CripitoSenha cs;
    public CredenciaJSFManagedBean() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
     public String mostraLogin() throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException, IOException{ 
        if(senha.equals("") || usuario.equals("")){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
               String papel = geb.verificarLogin(usuario, senha);
               if(papel.equals("GERENTE")){
                  return "/securi/gerente?faces-redirect=true";
               }else if(papel.equals("FINANCEIRO")){
                  return "/securif/financeiro?faces-redirect=true";
               }else if(papel.equals("OPERACIONAL")){
                  return "/securio/operacional?faces-redirect=true";
               }else{
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "Login ou Senha Inesistente";
              }
        }  
    }
    
    private String descripitar() throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException, IOException{
            String papel ="";
            Boolean chau = true;
            List<PapelEntity> pa = new ArrayList<> ();
            pa = geb.getPapel();
            int i=0;
            while(chau){
                  String s = cs.decrypt(pa.get(i).getSenha());
                  String l = cs.decrypt(pa.get(i).getLogin());
                  if(s.equals(senha) || l.equals(usuario)){
                     papel = pa.get(i).getPapel();
                     chau = false;
                  }
                  i++;
            }
            
    return papel;
    }
    
}
