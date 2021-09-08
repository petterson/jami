/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.PapelEntity;
import entidades.PessoaEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "papelFManagedBean")
@SessionScoped
public class PapelFManagedBean implements Serializable {

         @EJB
         GerenteSessionBean geb;
         long id;
         String nome;
         String login_cadastro;
         String senha_cadastro;
         String papel_selecionado;
         String cpf_papel;
         Date data_nasc;
         PessoaEntity p;
         List<String> list_papel;
         @Inject
         CripitoSenha cs;
    
    public PapelFManagedBean() {
        this.list_papel = new ArrayList<>();
        this.list_papel.add("GERENTE");
        this.list_papel.add("FINANCEIRO");
        this.list_papel.add("OPERACIONAL");
        this.p = new PessoaEntity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin_cadastro() {
        return login_cadastro;
    }

    public void setLogin_cadastro(String login_cadastro) {
        this.login_cadastro = login_cadastro;
    }

    public String getSenha_cadastro() {
        return senha_cadastro;
    }

    public void setSenha_cadastro(String senha_cadastro) {
        this.senha_cadastro = senha_cadastro;
    }

    public String getPapel_selecionado() {
        return papel_selecionado;
    }

    public void setPapel_selecionado(String papel_selecionado) {
        this.papel_selecionado = papel_selecionado;
    }

    public List<String> getList_papel() {
        return list_papel;
    }

    public void setList_papel(List<String> list_papel) {
        this.list_papel = list_papel;
    }

    public String getCpf_papel() {
        return cpf_papel;
    }

    public void setCpf_papel(String cpf_papel) {
        this.cpf_papel = cpf_papel;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }
    
    public String cadastrarPapel() throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        if(nome.equals("") || id > 1 || login_cadastro.equals("") || senha_cadastro.equals("") || papel_selecionado == null
           || cpf_papel.equals("") || data_nasc == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
     }else{
             //String s = cs.encrypt(senha_cadastro);
             //String s1 = cs.encrypt(login_cadastro);
             //System.out.println(" "+ s);
             //System.out.println(" "+ s1);
             PessoaEntity pe = new PessoaEntity();
             pe.setId(id);
             pe.setCpf(cpf_papel);
             pe.setNome(nome);
             pe.setData_nasc(data_nasc);
             if(geb.cadastrePessoa(pe)){
                 this.p = geb.selecionePessoaAtualiza(nome);
                 PapelEntity p = new PapelEntity();
                 p.setId(id);
                 p.setPessoa(this.p);
                 p.setLogin(login_cadastro);
                 p.setSenha(senha_cadastro);
                 p.setPapel(papel_selecionado);
             if(geb.cadastrePapel(p)){
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                this.apagaObjeto();
              }
        }else{
                 PapelEntity a = new PapelEntity();
                 a = geb.selecionePapelAtualiza(nome);
                 if(a.getLogin().equals("")){
                    this.p = geb.selecionePessoaAtualiza(nome);
                    PapelEntity ap = new PapelEntity();
                    ap.setId(id);
                    ap.setPessoa(this.p);
                    ap.setLogin(login_cadastro);
                    ap.setSenha(senha_cadastro);
                    ap.setPapel(papel_selecionado);
                if(geb.cadastrePapel(ap)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   this.apagaObjeto();
              }
            }else{
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome+" :  Já tem Login!"));
                  this.apagaObjeto();
                  return nome+" :  Já tem Login!";
                 }
             }
        }
    return "gerente?faces-redirect=true";
    }
    
    public void buscarPapel(){
         if(nome.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            PapelEntity p = new PapelEntity();
            p = geb.selecionePapelAtualiza(nome);
         if(p.getLogin().equals("")){
            FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
     }else{
             this.p = p.getPessoa();
             id = p.getId();
             nome = this.p.getNome();
             cpf_papel = this.p.getCpf();
             login_cadastro = p.getLogin();
             senha_cadastro = p.getSenha();
             papel_selecionado = p.getPapel();
             data_nasc = this.p.getData_nasc();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado"));
           }
        }
    }
    
    public void atualizarPapel(){
         if(nome.equals("") || id < 1 || login_cadastro.equals("") || senha_cadastro.equals("") || papel_selecionado == null
           || cpf_papel.equals("") || data_nasc == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido: Digite nova Senha", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
               PapelEntity p = new PapelEntity();
               p.setId(id);
               p.setPessoa(this.p);
               p.setLogin(login_cadastro);
               p.setSenha(senha_cadastro);
               p.setPapel(papel_selecionado);
               if(geb.atualizaPapel(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
               }else{
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
               }
          }
    }
 
    public void excluirPapel(){
         if(nome.equals("") || id < 1 || login_cadastro.equals("") || papel_selecionado == null
           || cpf_papel.equals("") || data_nasc == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           }else{
                PapelEntity p = new PapelEntity();
                p.setId(id);
                p.setPessoa(this.p);
                p.setLogin(login_cadastro);
                p.setSenha(senha_cadastro);
                p.setPapel(papel_selecionado);
               if(geb.removePapel(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
               }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
               }
           }       
    }
    
    private void apagaObjeto(){
             id = 0;
             nome = "";
             login_cadastro ="";
             senha_cadastro ="";
             papel_selecionado = null;
             cpf_papel = "";
             data_nasc = null;
             this.p = null;
    }  
}
