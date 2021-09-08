/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.EnderecoEntity;
import entidades.PaiEntity;
import entidades.PessoaEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author petterson
 */
@Named(value = "paiJSFManagedBean")
@SessionScoped
public class PaiJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops;
    @EJB
    FinanceiroSessionBean fin;
    @EJB
    GerenteSessionBean geb;
    long id_pai;
    String nome_pai;
    String rg_pai;
    //@CPF (message="Cpf Inválido")
    String cpf_pai;
    Date data_nascimento_pai;
    String profissao_pai;
    String local_trabalho_pai;
    String fone_pai;
    String escolaridade_pai;
    String rua_pai;
    int numero_casa_pai;
    PessoaEntity p;
    ArrayList<String> nome_ruas;
    ArrayList<String> list_escolaridade;
    @Inject
    AlunoJSFManagedBean alm;

    
    public PaiJSFManagedBean() {
        this.list_escolaridade = new ArrayList<>();
        this.nome_ruas = new ArrayList<>();
        this.p = new PessoaEntity();
    }

    public long getId_pai() {
        return id_pai;
    }

    public void setId_pai(long id_pai) {
        this.id_pai = id_pai;
    }

    public String getNome_pai() {
        return nome_pai;
    }

    public void setNome_pai(String nome_pai) {
        this.nome_pai = nome_pai;
    }

    public String getRg_pai() {
        return rg_pai;
    }

    public void setRg_pai(String rg_pai) {
        this.rg_pai = rg_pai;
    }

    public String getCpf_pai() {
        return cpf_pai;
    }

    public void setCpf_pai(String cpf_pai) {
        this.cpf_pai = cpf_pai;
    }

    public Date getData_nascimento_pai() {
        return data_nascimento_pai;
    }

    public void setData_nascimento_pai(Date data_nascimento_pai) {
        this.data_nascimento_pai = data_nascimento_pai;
    }

    public String getProfissao_pai() {
        return profissao_pai;
    }

    public void setProfissao_pai(String profissao_pai) {
        this.profissao_pai = profissao_pai;
    }

    public String getLocal_trabalho_pai() {
        return local_trabalho_pai;
    }

    public void setLocal_trabalho_pai(String local_trabalho_pai) {
        this.local_trabalho_pai = local_trabalho_pai;
    }

    public String getFone_pai() {
        return fone_pai;
    }

    public void setFone_pai(String fone_pai) {
        this.fone_pai = fone_pai;
    }

    public String getEscolaridade_pai() {
        return escolaridade_pai;
    }

    public void setEscolaridade_pai(String escolaridade_pai) {
        this.escolaridade_pai = escolaridade_pai;
    }

    public String getRua_pai() {
        return rua_pai;
    }

    public void setRua_pai(String rua_pai) {
        this.rua_pai = rua_pai;
    }

    public int getNumero_casa_pai() {
        return numero_casa_pai;
    }

    public void setNumero_casa_pai(int numero_casa_pai) {
        this.numero_casa_pai = numero_casa_pai;
    }

    public ArrayList<String> getList_escolaridade() {
        return list_escolaridade;
    }

    public void setList_escolaridade(ArrayList<String> list_escolaridade) {
        this.list_escolaridade = list_escolaridade;
    }

    public List<String> getNome_ruas() {
        return ops.selecioneRuas();
    }

    public void setNome_ruas(ArrayList<String> nome_ruas) {
        this.nome_ruas = nome_ruas;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }
    
    public String cadastrarPai(){
        if(nome_pai.equals("") || id_pai > 1 || cpf_pai.equals("") || rg_pai.equals("") || data_nascimento_pai == null
           || fone_pai.equals("") || escolaridade_pai == null || local_trabalho_pai.equals("") || rua_pai == null 
           || numero_casa_pai <= 0){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
        return "preencha os campos";
    }else {
               PessoaEntity pe = new PessoaEntity();
               pe.setId(id_pai);
               pe.setNome(nome_pai);
               pe.setCpf(cpf_pai);
               pe.setData_nasc(data_nascimento_pai);
               if(geb.cadastrePessoa(pe)){
                  this.p = geb.selecionePessoaAtualiza(nome_pai);
               EnderecoEntity e = fin.selecioneRuasPorNome(rua_pai);
               PaiEntity p = new PaiEntity();
               p.setId(id_pai);
               p.setRg_pai(rg_pai);
               p.setFone_pai(fone_pai);
               p.setEscolaridade_pai(escolaridade_pai);
               p.setProfissao_pai(profissao_pai);
               p.setLocal_trabalho_pai(local_trabalho_pai);
               p.setRua_pai(e);
               p.setNumero_casa_pai(numero_casa_pai);
               p.setPessoa(this.p);
               if(ops.cadastrePai(p)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   alm.setPai(this.p.getNome());
                   this.apagaObjeto();
               }
        }else{
              PaiEntity p = new PaiEntity();
              p = ops.selecionePaiAtualiza(nome_pai);
              if(p.getFone_pai().equals("")){
                 this.p = geb.selecionePessoaAtualiza(nome_pai);
                 if(this.p.getNome().equals("")){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("O CPPF está errado!"));
                     return nome_pai+" :  CPF está errado!";
                 }else{
                 EnderecoEntity e = fin.selecioneRuasPorNome(rua_pai);
                 PaiEntity m = new PaiEntity();
                    p.setId(id_pai);
               p.setRg_pai(rg_pai);
               p.setFone_pai(fone_pai);
               p.setEscolaridade_pai(escolaridade_pai);
               p.setProfissao_pai(profissao_pai);
               p.setLocal_trabalho_pai(local_trabalho_pai);
               p.setRua_pai(e);
               p.setNumero_casa_pai(numero_casa_pai);
               p.setPessoa(this.p);
               if(ops.cadastrePai(p)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   alm.setPai(this.p.getNome());
                   this.apagaObjeto();
               }
               }
             }else{
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome_pai+" :  Já é Irmão!"));
                  this.apagaObjeto();
                  return nome_pai+" :  Já é Irmão!";   
              }
               }
        }
     return "aluno?faces-redirect=true";
    }
    
    public void buscarPai(){
         if(nome_pai.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
              PaiEntity p = new PaiEntity();
              p = ops.selecionePaiAtualiza(nome_pai);
         if(p.getFone_pai().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             this.p = p.getPessoa();
             id_pai = p.getId();
             nome_pai = this.p.getNome();
             cpf_pai = this.p.getCpf();
             rg_pai = p.getRg_pai();
             data_nascimento_pai = this.p.getData_nasc();
             fone_pai = p.getFone_pai();
             profissao_pai = p.getProfissao_pai();
             local_trabalho_pai = p.getLocal_trabalho_pai();
             escolaridade_pai = p.getEscolaridade_pai();
             rua_pai = p.getRua_pai().getLogradouro();
             numero_casa_pai = p.getNumero_casa_pai();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public String atualizarPai(){
          if(nome_pai.equals("") || id_pai < 1 || cpf_pai.equals("") || rg_pai.equals("") || data_nascimento_pai == null
           || fone_pai.equals("") || escolaridade_pai == null || local_trabalho_pai.equals("") || rua_pai == null 
           || numero_casa_pai <= 0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           return "preencha os campos";
       }else{
              EnderecoEntity e = fin.selecioneRuasPorNome(rua_pai);
              PaiEntity p = new PaiEntity();
               p.setId(id_pai); 
               p.setRg_pai(rg_pai);
               p.setFone_pai(fone_pai);
               p.setEscolaridade_pai(escolaridade_pai);
               p.setProfissao_pai(profissao_pai);
               p.setLocal_trabalho_pai(local_trabalho_pai);
               p.setRua_pai(e);
               p.setNumero_casa_pai(numero_casa_pai);
               p.setPessoa(this.p);
             if(ops.atualizaPai(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
          }
          return "aluno?faces-redirect=true";
    }
     
    public String excluirPai(){
          if(nome_pai.equals("") || id_pai < 10 || cpf_pai.equals("") || rg_pai.equals("") || data_nascimento_pai == null
           || fone_pai.equals("") || escolaridade_pai == null || local_trabalho_pai.equals("") || rua_pai == null 
           || numero_casa_pai <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
              return "Preencha os Campos!";
       }else{
              EnderecoEntity e = fin.selecioneRuasPorNome(rua_pai);
             PaiEntity p = new PaiEntity();
             p.setId(id_pai);
             p.setRg_pai(rg_pai);
             p.setFone_pai(fone_pai);
             p.setEscolaridade_pai(escolaridade_pai);
             p.setProfissao_pai(profissao_pai);
             p.setLocal_trabalho_pai(local_trabalho_pai);
             p.setRua_pai(e);
             p.setNumero_casa_pai(numero_casa_pai);
             p.setPessoa(this.p);
             if(ops.removePai(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                return " ID não Válido!";
             }
           }
          return "aluno?faces-redirect=true";
    }
      
    public Date dataHoje(){
      return new Date();
    } 
    
    private void apagaObjeto(){
             id_pai = 0;
             nome_pai = 
             cpf_pai = "";
             rg_pai ="";
             data_nascimento_pai = null;
             fone_pai ="";
             escolaridade_pai = null;
             profissao_pai = "";
             local_trabalho_pai ="";
             rua_pai= null;
             numero_casa_pai =0;
             this.p = null;
    }
}
