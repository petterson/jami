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
import entidades.MaeEntity;
import entidades.PessoaEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author petterson
 */
@Named(value = "maeJSFManagedBean")
@SessionScoped
public class MaeJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops;
    @EJB
    FinanceiroSessionBean fin;
    @EJB
    GerenteSessionBean geb;
    long id_mae;
    String nome_mae;
    String rg_mae;
    //@CPF (message="Cpf Inválido")
    String cpf_mae;
    Date data_nascimento_mae;
    String profissao_mae;
    String local_trabalho_mae;
    String fone_mae;
    String escolaridade_mae;
    String rua_mae;
    int numero_casa_mae;
    PessoaEntity p;
    ArrayList<String> nome_ruas;
    ArrayList<String> list_escolaridade;
    @Inject
    AlunoJSFManagedBean alm;
    
    public MaeJSFManagedBean() {
        this.list_escolaridade = new ArrayList<>();
        this.nome_ruas = new ArrayList<>();
        this.p = new PessoaEntity();
    }

    public String getNome_mae() {
        return nome_mae;
    }

    public void setNome_mae(String nome_mae) {
        this.nome_mae = nome_mae;
    }

    public String getRg_mae() {
        return rg_mae;
    }

    public void setRg_mae(String rg_mae) {
        this.rg_mae = rg_mae;
    }

    public String getCpf_mae() {
        return cpf_mae;
    }

    public void setCpf_mae(String cpf_mae) {
        this.cpf_mae = cpf_mae;
    }

    public Date getData_nascimento_mae() {
        return data_nascimento_mae;
    }

    public void setData_nascimento_mae(Date data_nascimento_mae) {
        this.data_nascimento_mae = data_nascimento_mae;
    }

    public String getProfissao_mae() {
        return profissao_mae;
    }

    public void setProfissao_mae(String profissao_mae) {
        this.profissao_mae = profissao_mae;
    }

    public String getLocal_trabalho_mae() {
        return local_trabalho_mae;
    }

    public void setLocal_trabalho_mae(String local_trabalho_mae) {
        this.local_trabalho_mae = local_trabalho_mae;
    }

    public String getFone_mae() {
        return fone_mae;
    }

    public void setFone_mae(String fone_mae) {
        this.fone_mae = fone_mae;
    }

    public String getEscolaridade_mae() {
        return escolaridade_mae;
    }

    public void setEscolaridade_mae(String escolaridade_mae) {
        this.escolaridade_mae = escolaridade_mae;
    }

    public String getRua_mae() {
        return rua_mae;
    }

    public void setRua_mae(String rua_mae) {
        this.rua_mae = rua_mae;
    }

    public int getNumero_casa_mae() {
        return numero_casa_mae;
    }

    public void setNumero_casa_mae(int numero_casa_mae) {
        this.numero_casa_mae = numero_casa_mae;
    }

    public long getId_mae() {
        return id_mae;
    }

    public void setId_mae(long id_mae) {
        this.id_mae = id_mae;
    }

    public List<String> getNome_ruas() {
        return ops.selecioneRuas();
    }

    public void setNome_ruas(ArrayList<String> nome_ruas) {
        this.nome_ruas = nome_ruas;
    }

    public ArrayList<String> getList_escolaridade() {
        return list_escolaridade;
    }

    public void setList_escolaridade(ArrayList<String> list_escolaridade) {
        this.list_escolaridade = list_escolaridade;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }
    
    public String cadastrarMae(){
         
        if(nome_mae.equals("") || id_mae > 1 || cpf_mae.equals("") || rg_mae.equals("") || data_nascimento_mae == null
           || fone_mae.equals("") || escolaridade_mae == null || local_trabalho_mae.equals("") || rua_mae == null
           || numero_casa_mae <= 0){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
        return "preencha os campos";
      }else {
               PessoaEntity p = new PessoaEntity();
               p.setId(id_mae);
               p.setNome(nome_mae);
               p.setCpf(cpf_mae);
               p.setData_nasc(data_nascimento_mae);
               if(geb.cadastrePessoa(p)){
                  //this.p = geb.selecionePessoaAtualiza(nome_mae);
               EnderecoEntity e = fin.selecioneRuasPorNome(rua_mae);
               MaeEntity m = new MaeEntity();
               m.setId(id_mae);
               m.setRg_mae(rg_mae);
               m.setFone_mae(fone_mae);
               m.setEscolaridade_mae(escolaridade_mae);
               m.setProfissao_mae(profissao_mae);
               m.setLocal_trabalho_mae(local_trabalho_mae);
               m.setRua_mae(e);
               m.setNumero_casa_mae(numero_casa_mae);
               this.p = geb.selecionePessoaAtualiza(nome_mae);
               m.setPessoa(this.p);
               if(ops.cadastreMae(m)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   alm.setMae_entity(this.p.getNome());
                   this.apagaObjeto();
               }
        }else{
              MaeEntity en = new MaeEntity();
              en = ops.selecioneMaeAtualiza(nome_mae);
              if(en.getFone_mae().equals("")){
                 this.p = geb.selecionePessoaAtualiza(nome_mae);
                 if(this.p.getNome().equals("")){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("O CPPF está errado!"));
                     return nome_mae+" :  CPF está errado!";
                 }else{
                 EnderecoEntity e = fin.selecioneRuasPorNome(rua_mae);
               MaeEntity m = new MaeEntity();
               m.setId(id_mae);
               m.setRg_mae(rg_mae);
               m.setFone_mae(fone_mae);
               m.setEscolaridade_mae(escolaridade_mae);
               m.setProfissao_mae(profissao_mae);
               m.setLocal_trabalho_mae(local_trabalho_mae);
               m.setRua_mae(e);
               m.setNumero_casa_mae(numero_casa_mae);
               m.setPessoa(this.p);
               if(ops.cadastreMae(m)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   alm.setMae_entity(this.p.getNome());
                   this.apagaObjeto();
               }
              }
             }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome_mae+" :  Já é Mãe!"));
                   this.apagaObjeto();
                   return nome_mae+" :  Já é Mãe!";
                  }
               }
        }
    return "aluno?faces-redirect=true";
    }
    
    public void buscarMae(){
          if(nome_mae.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            MaeEntity m = new MaeEntity();
           m = ops.selecioneMaeAtualiza(nome_mae);
         if(m.getFone_mae().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
      }else{
             this.p = m.getPessoa();
             id_mae = m.getId();
             nome_mae = this.p.getNome();
             data_nascimento_mae = this.p.getData_nasc();
             cpf_mae = this.p.getCpf();
             rg_mae = m.getRg_mae();
             fone_mae = m.getFone_mae();
             profissao_mae = m.getProfissao_mae();
             local_trabalho_mae = m.getEscolaridade_mae();
             escolaridade_mae = m.getEscolaridade_mae();
             rua_mae = m.getRua_mae().getLogradouro();
             numero_casa_mae = m.getNumero_casa_mae();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public String atualizarMae(){
         if(nome_mae.equals("") || id_mae <= 1 || cpf_mae.equals("") || rg_mae.equals("") || data_nascimento_mae == null
           || fone_mae.equals("") || escolaridade_mae == null || local_trabalho_mae.equals("") || rua_mae == null
           || numero_casa_mae <= 0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
             EnderecoEntity e = fin.selecioneRuasPorNome(rua_mae);
              MaeEntity m = new MaeEntity();
               m.setId(id_mae);
               m.setRg_mae(rg_mae);
               m.setFone_mae(fone_mae);
               m.setEscolaridade_mae(escolaridade_mae);
               m.setProfissao_mae(profissao_mae);
               m.setLocal_trabalho_mae(local_trabalho_mae);
               m.setRua_mae(e);
               m.setNumero_casa_mae(numero_casa_mae);
               m.setPessoa(this.p);
             if(ops.atualizaMae(m)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
          }
         return "aluno?faces-redirect=true";
    }
     
    public String excluirMae(){
         if(nome_mae.equals("") || id_mae <=10 || cpf_mae.equals("") || rg_mae.equals("") || data_nascimento_mae == null
           || fone_mae.equals("") || escolaridade_mae == null || local_trabalho_mae.equals("") || rua_mae == null
           || numero_casa_mae <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
              return "Preencha os Campos!";
       }else{
             EnderecoEntity e = fin.selecioneRuasPorNome(rua_mae);
             MaeEntity m = new MaeEntity();
               m.setId(id_mae);
               m.setRg_mae(rg_mae);
               m.setFone_mae(fone_mae);
               m.setEscolaridade_mae(escolaridade_mae);
               m.setProfissao_mae(profissao_mae);
               m.setLocal_trabalho_mae(local_trabalho_mae);
               m.setRua_mae(e);
               m.setNumero_casa_mae(numero_casa_mae);
               m.setPessoa(this.p);
             if(ops.removeMae(m)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                return "ID não Atualizado!";
             }
           }
         return "aluno?faces-redirect=true";
    }
      
    public Date dataAtual(){
      return new Date();
    }
    
    private void apagaObjeto(){
             id_mae = 0;
             nome_mae = "";
             cpf_mae = "";
             rg_mae ="";
             data_nascimento_mae = null;
             fone_mae ="";
             escolaridade_mae = null;
             profissao_mae = "";
             local_trabalho_mae ="";
             rua_mae= null;
             numero_casa_mae =0;
             this.p = null;
    }

}
