/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.CargoEntity;
import entidades.DepartamentoEntity;
import entidades.EnderecoEntity;
import entidades.FuncionarioEntity;
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
@Named(value = "funcionarioJSFManagedBean")
@SessionScoped
public class FuncionarioJSFManagedBean implements Serializable {

       @EJB
       GerenteSessionBean geb;
       @EJB
       OperacionalSessionBean ops;
       @EJB
       FinanceiroSessionBean fin;
       long id_funcionario;
       String nome_funcionario;
       //@CPF (message="Cpf Inválido")
       String cpf_funncionario;
       String rg_funcionario;
       String fone_funcionario;
       String rua_funcionario;
       int numero_funcionario;
       String clt_funcionario;
       String pis_funcionario;
       String cargo_funcionario;
       String departamento_funcionario;
       Date data_contratacao;
       Date data_nasc;
       List<String> nome_departamentos;
       ArrayList<String> nome_cargos;
       ArrayList<String> nome_ruas;
       String nome_cargo_entity;
       String nome_departamento_entity;
       String nome_rua_entity;
       PessoaEntity p;
       DepartamentoEntity d;
       CargoEntity c;
       @Inject
       DepartamentoJSFManagedBean dem;
       @Inject
       TurmaSFManagedBean tum;
    
    public FuncionarioJSFManagedBean() {
        this.d = new DepartamentoEntity();
        this.p = new PessoaEntity();
        this.nome_cargos = new ArrayList<>();
        this.nome_ruas = new ArrayList<>();
        this.nome_departamentos = new ArrayList<>();
    }

    public long getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(long id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getCpf_funncionario() {
        return cpf_funncionario;
    }

    public void setCpf_funncionario(String cpf_funncionario) {
        this.cpf_funncionario = cpf_funncionario;
    }

    public String getRg_funcionario() {
        return rg_funcionario;
    }

    public void setRg_funcionario(String rg_funcionario) {
        this.rg_funcionario = rg_funcionario;
    }

    public String getFone_funcionario() {
        return fone_funcionario;
    }

    public void setFone_funcionario(String fone_funcionario) {
        this.fone_funcionario = fone_funcionario;
    }

    public String getRua_funcionario() {
        return rua_funcionario;
    }

    public void setRua_funcionario(String rua_funcionario) {
        this.rua_funcionario = rua_funcionario;
    }

    public int getNumero_funcionario() {
        return numero_funcionario;
    }

    public void setNumero_funcionario(int numero_funcionario) {
        this.numero_funcionario = numero_funcionario;
    }

    public String getClt_funcionario() {
        return clt_funcionario;
    }

    public void setClt_funcionario(String clt_funcionario) {
        this.clt_funcionario = clt_funcionario;
    }

    public String getPis_funcionario() {
        return pis_funcionario;
    }

    public void setPis_funcionario(String pis_funcionario) {
        this.pis_funcionario = pis_funcionario;
    }

    public String getCargo_funcionario() {
        return cargo_funcionario;
    }

    public void setCargo_funcionario(String cargo_funcionario) {
        this.cargo_funcionario = cargo_funcionario;
    }

    public Date getData_contratacao() {
        return data_contratacao;
    }

    public void setData_contratacao(Date data_contratacao) {
        this.data_contratacao = data_contratacao;
    }

    public List<String> getNome_cargos() {
        return geb.selecioneCargos();
    }

    public void setNome_cargos(ArrayList<String> nome_cargos) {
        this.nome_cargos = nome_cargos;
    }

    public List<String> getNome_ruas() {
        return ops.selecioneRuas();
    }

    public void setNome_ruas(ArrayList<String> nome_ruas) {
        this.nome_ruas = nome_ruas;
    }
    
    public String getNome_cargo_entity() {
        return nome_cargo_entity;
    }

    public void setNome_cargo_entity(String nome_cargo_entity) {
           this.nome_cargos.add(nome_cargo_entity);
        this.nome_cargo_entity = nome_cargo_entity;
    }

    public String getNome_rua_entity() {
        return nome_rua_entity;
    }

    public void setNome_rua_entity(String nome_rua_entity) {
           this.nome_ruas.add(nome_rua_entity);
        this.nome_rua_entity = nome_rua_entity;
    }

    public DepartamentoEntity getD() {
        return d;
    }

    public void setD(DepartamentoEntity d) {
        this.d = d;
    }

    public CargoEntity getC() {
        return c;
    }

    public void setC(CargoEntity c) {
        this.c = c;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }

    public String getDepartamento_funcionario() {
        return departamento_funcionario;
    }

    public void setDepartamento_funcionario(String departamento_funcionario) {
        this.departamento_funcionario = departamento_funcionario;
    }

    public List<String> getNome_departamentos() {
        return geb.selecioneDepartamentos();
    }

    public void setNome_departamentos(List<String> nome_departamentos) {
        this.nome_departamentos = nome_departamentos;
    }

    public String getNome_departamento_entity() {
        return nome_departamento_entity;
    }

    public void setNome_departamento_entity(String nome_departamento_entity) {
           this.nome_departamentos.add(nome_departamento_entity);
    this.nome_departamento_entity = nome_departamento_entity;
    }
    
    public String cadastrarFuncionario(){
        if(cpf_funncionario.equals("") || id_funcionario > 1 || nome_funcionario.equals("") || rg_funcionario.equals("") || fone_funcionario.equals("")
           || rua_funcionario == null || numero_funcionario <= 0 || clt_funcionario.equals("") || pis_funcionario.equals("")
           || cargo_funcionario == null || data_contratacao == null || departamento_funcionario == null || data_nasc == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
             PessoaEntity p = new PessoaEntity();
             p.setId(id_funcionario);
             p.setNome(nome_funcionario);
             p.setCpf(cpf_funncionario);
             p.setData_nasc(data_nasc);
             if(geb.cadastrePessoa(p)){
             this.p = geb.selecionePessoaAtualiza(nome_funcionario);
             CargoEntity ca = geb.selecioneCargoAtualiza(cargo_funcionario);
             DepartamentoEntity de= geb.selecioneDepartamentoAtualiza(departamento_funcionario);
             EnderecoEntity e = fin.selecioneRuasPorNome(rua_funcionario);
             FuncionarioEntity fu = new FuncionarioEntity();
             fu.setId(id_funcionario);
             fu.setRg(rg_funcionario);
             fu.setFone_funcionario(fone_funcionario);
             fu.setRua_funcionario(e);
             fu.setNumero_casa(numero_funcionario);
             fu.setClt_funcionario(clt_funcionario);
             fu.setPis_funcionario(pis_funcionario);
             fu.setCargo_funcionario(ca);
             fu.setData_contratacao(data_contratacao);
             fu.setDepartamento(de);
             fu.setPessoa(this.p);
             if(geb.cadastreFuncionario(fu)){
                 de.getFuncionarios().add(fu);
                 geb.atualizaDepartamento(de);
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                 tum.setNome_professor_entity(this.p.getNome());
                 this.apagaObjeto();
             }
      }else{
             FuncionarioEntity a = new FuncionarioEntity();
             a = geb.selecioneFuncionarioAtualiza(nome_funcionario);
             if(a.getFone_funcionario().equals("")){
                this.p = geb.selecionePessoaAtualiza(nome_funcionario);
                if(this.p.getNome().equals("")){
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("O CPPF está errado!"));
                     return nome_funcionario+" :  CPF está errado!";
                 }else{
                CargoEntity ca = geb.selecioneCargoAtualiza(cargo_funcionario);
                DepartamentoEntity de= geb.selecioneDepartamentoAtualiza(departamento_funcionario);
                EnderecoEntity e = fin.selecioneRuasPorNome(rua_funcionario);
                FuncionarioEntity fu = new FuncionarioEntity();
                fu.setId(id_funcionario);
                fu.setRg(rg_funcionario);
                fu.setFone_funcionario(fone_funcionario);
                fu.setRua_funcionario(e);
                fu.setNumero_casa(numero_funcionario);
                fu.setClt_funcionario(clt_funcionario);
                fu.setPis_funcionario(pis_funcionario);
                fu.setCargo_funcionario(ca);
                fu.setData_contratacao(data_contratacao);
                fu.setDepartamento(de);
                fu.setPessoa(this.p);
                if(geb.cadastreFuncionario(fu)){
                   de.getFuncionarios().add(fu);
                   geb.atualizaDepartamento(de);
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   tum.setNome_professor_entity(this.p.getNome());
                   this.apagaObjeto();
                  }
                }
                }else{
                       FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome_funcionario+" :  Já é Apoiador!"));
                       this.apagaObjeto();
                      return nome_funcionario+" :  Já é Apoiador!";
                    } 
             }
        } 
    return "gerente?faces-redirect=true";
    }
    
    public void buscarFuncionario(){
        if(nome_funcionario.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
        FuncionarioEntity f = new FuncionarioEntity();
        f = geb.selecioneFuncionarioAtualiza(nome_funcionario);
        if(f.getFone_funcionario().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Funcionário Inexistente!"));
    }else{
           this.p = f.getPessoa();
           id_funcionario = f.getId();
           nome_funcionario = this.p.getNome();
           cpf_funncionario = this.p.getCpf();
           rg_funcionario = f.getRg();
           fone_funcionario = f.getFone_funcionario();
           rua_funcionario = f.getRua_funcionario().getLogradouro();
           numero_funcionario = f.getNumero_casa();
           clt_funcionario = f.getClt_funcionario();
           pis_funcionario = f.getPis_funcionario();
           cargo_funcionario = f.getCargo_funcionario().getNome_cargo();
           data_contratacao = f.getData_contratacao();
           departamento_funcionario = f.getDepartamento().getNome_departamento();
           data_nasc = this.p.getData_nasc();
           this.d = f.getDepartamento();
           this.c = f.getCargo_funcionario();
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
          }
        }
    }
    
    public String atualizaFuncionario(){
        if(cpf_funncionario.equals("") || id_funcionario < 10 || nome_funcionario.equals("") || rg_funcionario.equals("") 
                || fone_funcionario.equals("") || rua_funcionario == null || numero_funcionario <= 0 || clt_funcionario.equals("") 
                || pis_funcionario.equals("") || cargo_funcionario == null || data_contratacao == null || data_nasc == null 
                || departamento_funcionario == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
            return "preencha os campos";
    }else {
             CargoEntity ca = geb.selecioneCargoAtualiza(cargo_funcionario);
             DepartamentoEntity de= geb.selecioneDepartamentoAtualiza(departamento_funcionario);
             EnderecoEntity e = fin.selecioneRuasPorNome(rua_funcionario);
             FuncionarioEntity fu = new FuncionarioEntity();
             fu.setId(id_funcionario);
             fu.setRg(rg_funcionario);
             fu.setFone_funcionario(fone_funcionario);
             fu.setRua_funcionario(e);
             fu.setNumero_casa(numero_funcionario);
             fu.setClt_funcionario(clt_funcionario);
             fu.setPis_funcionario(pis_funcionario);
             fu.setCargo_funcionario(ca);
             fu.setData_contratacao(data_contratacao);
             fu.setDepartamento(de);
             fu.setPessoa(this.p);
             if(geb.atualizaFuncionario(fu)){
                 de.getFuncionarios().add(fu);
                 geb.atualizaDepartamento(de);
                 if(!this.d.getNome_departamento().equals(de.getNome_departamento())){
                     this.d.getFuncionarios().remove(fu);
                     geb.atualizaDepartamento(this.d);
                 }
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
        }
        return "gerente?faces-redirect=true";
    }
    
      public void excluirFuncionario(){
          if(cpf_funncionario.equals("") || id_funcionario < 10 || nome_funcionario.equals("") || rg_funcionario.equals("") 
                || fone_funcionario.equals("") || rua_funcionario == null || numero_funcionario <= 0 || clt_funcionario.equals("") 
                || pis_funcionario.equals("") || cargo_funcionario == null || data_contratacao == null || data_nasc == null
                || departamento_funcionario == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
      }else{
             EnderecoEntity e = fin.selecioneRuasPorNome(rua_funcionario);
             CargoEntity ca = geb.selecioneCargoAtualiza(cargo_funcionario);
             //DepartamentoEntity de= geb.selecioneDepartamentoAtualiza(departamento_funcionario);
             FuncionarioEntity fu = new FuncionarioEntity();
             fu.setId(id_funcionario);
             fu.setRg(rg_funcionario);
             fu.setFone_funcionario(fone_funcionario);
             fu.setRua_funcionario(e);
             fu.setNumero_casa(numero_funcionario);
             fu.setClt_funcionario(clt_funcionario);
             fu.setPis_funcionario(pis_funcionario);
             fu.setCargo_funcionario(ca);
             fu.setData_contratacao(data_contratacao);
             fu.setDepartamento(this.d);
             fu.setPessoa(this.p);
          if(geb.removeFuncionario(fu))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Removido!"));
          }
          this.apagaObjeto();
      }
      
      public Date dataAtual(){
      
      return new Date();
      }
      
     private void apagaObjeto(){
             id_funcionario = 0;
             nome_funcionario = "";
             cpf_funncionario = "";
             rg_funcionario ="";
             data_contratacao = null;
             fone_funcionario ="";
             rua_funcionario = null;
             numero_funcionario = 0;
             clt_funcionario ="";
             pis_funcionario= "";
             cargo_funcionario = null;
             departamento_funcionario = null;
             data_nasc = null;
             d=null;
             c=null;
             this.p = null;
    }
}
