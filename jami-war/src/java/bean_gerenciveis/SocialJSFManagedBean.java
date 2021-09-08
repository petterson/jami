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
import entidades.PessoaEntity;
import entidades.SocialEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author petterson
 */
@Named(value = "socialJSFManagedBean")
@ViewScoped
public class SocialJSFManagedBean implements Serializable {

        @EJB
        GerenteSessionBean geb;
        @EJB
        OperacionalSessionBean ops;
        @EJB
        FinanceiroSessionBean fin;
        long id_social;
        String nome_social;
        //@CPF (message="Cpf Inválido")
        String cpf_social;
        String rg_social;
        String fone_social;
        String profissao_social;
        String situacao_social;
        String rua_social;
        int numero_social;
        String complemento_social;
        String auxilio_governo;
        String gestante;
        String obs_gestante;
        String acamado;
        String obs_acamado;
        String deficiente;
        String obs_deficiente;
        String nro_adultos;
        String nro_jovens;
        String nro_adolecentes;
        String nro_criancas;
        String nro_bebes;
        String instrucao;
        String renda_mensal;
        Date data_nasc;
        Date data_cadastro_social;
        PessoaEntity p;
        ArrayList<String> nome_ruas;
        ArrayList<String> list_situacoes;
        ArrayList<String> list_auxilios;
        ArrayList<String> list_gestantes;
        ArrayList<String> list_acamados;
        ArrayList<String> list_deficientes;
        ArrayList<String> list_adultos;
        ArrayList<String> list_joves;
        ArrayList<String> list_adolecentes;
        ArrayList<String> list_criancas;
        ArrayList<String> list_beles;
        ArrayList<String> list_instrucoes;
        ArrayList<String> list_rendas;

    
    public SocialJSFManagedBean() {
            this.p = new PessoaEntity();
    }

    public String getNome_social() {
        return nome_social;
    }

    public void setNome_social(String nome_social) {
        this.nome_social = nome_social;
    }

    public String getCpf_social() {
        return cpf_social;
    }

    public void setCpf_social(String cpf_social) {
        this.cpf_social = cpf_social;
    }

    public String getRg_social() {
        return rg_social;
    }

    public void setRg_social(String rg_social) {
        this.rg_social = rg_social;
    }

    public String getFone_social() {
        return fone_social;
    }

    public void setFone_social(String fone_social) {
        this.fone_social = fone_social;
    }

    public String getProfissao_social() {
        return profissao_social;
    }

    public void setProfissao_social(String profissao_social) {
        this.profissao_social = profissao_social;
    }

    public String getSituacao_social() {
        return situacao_social;
    }

    public void setSituacao_social(String situacao_social) {
        this.situacao_social = situacao_social;
    }

    public String getRua_social() {
        return rua_social;
    }

    public void setRua_social(String rua_social) {
        this.rua_social = rua_social;
    }

    public int getNumero_social() {
        return numero_social;
    }

    public void setNumero_social(int numero_social) {
        this.numero_social = numero_social;
    }

    public String getComplemento_social() {
        return complemento_social;
    }

    public void setComplemento_social(String complemento_social) {
        this.complemento_social = complemento_social;
    }

    public String getAuxilio_governo() {
        return auxilio_governo;
    }

    public void setAuxilio_governo(String auxilio_governo) {
        this.auxilio_governo = auxilio_governo;
    }

    public String getGestante() {
        return gestante;
    }

    public void setGestante(String gestante) {
        this.gestante = gestante;
    }

    public String getObs_gestante() {
        return obs_gestante;
    }

    public void setObs_gestante(String obs_gestante) {
        this.obs_gestante = obs_gestante;
    }

    public String getAcamado() {
        return acamado;
    }

    public void setAcamado(String acamado) {
        this.acamado = acamado;
    }

    public String getObs_acamado() {
        return obs_acamado;
    }

    public void setObs_acamado(String obs_acamado) {
        this.obs_acamado = obs_acamado;
    }

    public String getDeficiente() {
        return deficiente;
    }

    public void setDeficiente(String deficiente) {
        this.deficiente = deficiente;
    }

    public String getObs_deficiente() {
        return obs_deficiente;
    }

    public void setObs_deficiente(String obs_deficiente) {
        this.obs_deficiente = obs_deficiente;
    }

    public String getNro_adultos() {
        return nro_adultos;
    }

    public void setNro_adultos(String nro_adultos) {
        this.nro_adultos = nro_adultos;
    }

    public String getNro_jovens() {
        return nro_jovens;
    }

    public void setNro_jovens(String nro_jovens) {
        this.nro_jovens = nro_jovens;
    }

    public String getNro_adolecentes() {
        return nro_adolecentes;
    }

    public void setNro_adolecentes(String nro_adolecentes) {
        this.nro_adolecentes = nro_adolecentes;
    }

    public String getNro_criancas() {
        return nro_criancas;
    }

    public void setNro_criancas(String nro_criancas) {
        this.nro_criancas = nro_criancas;
    }

    public String getNro_bebes() {
        return nro_bebes;
    }

    public void setNro_bebes(String nro_bebes) {
        this.nro_bebes = nro_bebes;
    }

    public String getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public String getRenda_mensal() {
        return renda_mensal;
    }

    public void setRenda_mensal(String renda_mensal) {
        this.renda_mensal = renda_mensal;
    }

    public Date getData_cadastro_social() {
        return data_cadastro_social;
    }

    public void setData_cadastro_social(Date data_cadastro_social) {
        this.data_cadastro_social = data_cadastro_social;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public List<String> getNome_ruas() {
        return ops.selecioneRuas();
    }

    public void setNome_ruas(ArrayList<String> nome_ruas) {
        this.nome_ruas = nome_ruas;
    }

    public long getId_social() {
        return id_social;
    }

    public void setId_social(long id_social) {
        this.id_social = id_social;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }

    public ArrayList<String> getList_situacoes() {
        return list_situacoes;
    }

    public void setList_situacoes(ArrayList<String> list_situacoes) {
        this.list_situacoes = list_situacoes;
    }

    public ArrayList<String> getList_auxilios() {
        return list_auxilios;
    }

    public void setList_auxilios(ArrayList<String> list_auxilios) {
        this.list_auxilios = list_auxilios;
    }

    public ArrayList<String> getList_gestantes() {
        return list_gestantes;
    }

    public void setList_gestantes(ArrayList<String> list_gestantes) {
        this.list_gestantes = list_gestantes;
    }

    public ArrayList<String> getList_acamados() {
        return list_acamados;
    }

    public void setList_acamados(ArrayList<String> list_acamados) {
        this.list_acamados = list_acamados;
    }

    public ArrayList<String> getList_deficientes() {
        return list_deficientes;
    }

    public void setList_deficientes(ArrayList<String> list_deficientes) {
        this.list_deficientes = list_deficientes;
    }

    public ArrayList<String> getList_adultos() {
        return list_adultos;
    }

    public void setList_adultos(ArrayList<String> list_adultos) {
        this.list_adultos = list_adultos;
    }

    public ArrayList<String> getList_joves() {
        return list_joves;
    }

    public void setList_joves(ArrayList<String> list_joves) {
        this.list_joves = list_joves;
    }

    public ArrayList<String> getList_adolecentes() {
        return list_adolecentes;
    }

    public void setList_adolecentes(ArrayList<String> list_adolecentes) {
        this.list_adolecentes = list_adolecentes;
    }

    public ArrayList<String> getList_criancas() {
        return list_criancas;
    }

    public void setList_criancas(ArrayList<String> list_criancas) {
        this.list_criancas = list_criancas;
    }

    public ArrayList<String> getList_beles() {
        return list_beles;
    }

    public void setList_beles(ArrayList<String> list_beles) {
        this.list_beles = list_beles;
    }

    public ArrayList<String> getList_instrucoes() {
        return list_instrucoes;
    }

    public void setList_instrucoes(ArrayList<String> list_instrucoes) {
        this.list_instrucoes = list_instrucoes;
    }

    public ArrayList<String> getList_rendas() {
        return list_rendas;
    }

    public void setList_rendas(ArrayList<String> list_rendas) {
        this.list_rendas = list_rendas;
    }
    
    public String cadastrarSocial(){
        if(nome_social.equals("") || id_social > 1 || cpf_social.equals("") || rg_social.equals("") || fone_social.equals("") 
           || profissao_social.equals("") || situacao_social == null || rua_social == null || numero_social <= 0 
           ||complemento_social.equals("") || auxilio_governo == null || gestante == null || acamado == null 
           || deficiente == null || nro_adultos == null || nro_jovens == null || nro_adolecentes == null || nro_criancas == null 
           || nro_bebes == null || instrucao == null || renda_mensal == null || data_cadastro_social == null
           || data_nasc == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
        return "preencha os campos";
        }else{
              PessoaEntity p = new PessoaEntity();
              p.setId(id_social);
              p.setNome(nome_social);
              p.setCpf(cpf_social);
              p.setData_nasc(data_nasc);
              if(geb.cadastrePessoa(p)){
              this.p = geb.selecionePessoaAtualiza(nome_social);
              EnderecoEntity e = fin.selecioneRuasPorNome(rua_social);
              SocialEntity so = new SocialEntity();
              so.setId(id_social);
              so.setRg_social(rg_social);
              so.setFone_social(fone_social);
              so.setProfissao_social(profissao_social);
              so.setSitucao_social(situacao_social);
              so.setRua_social(e);
              so.setNumero_casa(numero_social);
              so.setComplemento_rua(complemento_social);
              so.setAuxilio_governo(auxilio_governo);
              so.setGestante(gestante);
              so.setObs_gestante(obs_gestante);
              so.setAcamado(acamado);
              so.setObs_acamado(obs_acamado);
              so.setDeficiente(deficiente);
              so.setObs_deficiente(obs_deficiente);
              so.setNro_adultos(nro_adultos);
              so.setNro_joves(nro_jovens);
              so.setNro_adolecentes(nro_adolecentes);
              so.setNro_criancas(nro_criancas);
              so.setNro_bebes(nro_bebes);
              so.setGrau_instrucao(instrucao);
              so.setRenda_mensal_familiar(renda_mensal);
              so.setData_cadastro(data_cadastro_social);
              so.setPessoa(this.p);
              if(geb.cadastreSocial(so)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
              }
        }else{
                  SocialEntity a = new SocialEntity();
                    a = geb.selecioneSocialAtualiza(nome_social);
                    if(a.getFone_social().equals("")){
                       this.p = geb.selecionePessoaAtualiza(nome_social);
                       if(this.p.getNome().equals("")){
                          FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("O CPPF está errado!"));
                          return nome_social+" :  CPF está errado!";
                 }else{
                       EnderecoEntity e = fin.selecioneRuasPorNome(rua_social);
                       SocialEntity so = new SocialEntity();
                       so.setId(id_social);
                       so.setRg_social(rg_social);
                       so.setFone_social(fone_social);
                       so.setProfissao_social(profissao_social);
                       so.setSitucao_social(situacao_social);
                       so.setRua_social(e);
                       so.setNumero_casa(numero_social);
                       so.setComplemento_rua(complemento_social);
                       so.setAuxilio_governo(auxilio_governo);
                       so.setGestante(gestante);
                       so.setObs_gestante(obs_gestante);
                       so.setAcamado(acamado);
                       so.setObs_acamado(obs_acamado);
                       so.setDeficiente(deficiente);
                       so.setObs_deficiente(obs_deficiente);
                       so.setNro_adultos(nro_adultos);
                       so.setNro_joves(nro_jovens);
                       so.setNro_adolecentes(nro_adolecentes);
                       so.setNro_criancas(nro_criancas);
                       so.setNro_bebes(nro_bebes);
                       so.setGrau_instrucao(instrucao);
                       so.setRenda_mensal_familiar(renda_mensal);
                       so.setData_cadastro(data_cadastro_social);
                       so.setPessoa(this.p);
                       if(geb.cadastreSocial(so)){
                          FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                          this.apagaObjeto();
                    }
                       }
              }else{
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome_social+" :  Já é Apoiador!"));
                     this.apagaObjeto();
                     return nome_social+" :  Já é Apoiador!";
                    }
              }
        }     
    return "beneficio?faces-redirect=true";
    }
    
    public void buscarSocial(){
         if(nome_social.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
      }else{
            SocialEntity s = new SocialEntity();
            s = geb.selecioneSocialAtualiza(nome_social);
            if(s.getFone_social().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
      }else{
             this.p = s.getPessoa();
             nome_social = this.p.getNome();
             cpf_social = this.p.getCpf();
             data_nasc = this.p.getData_nasc();
             id_social = s.getId();
             rg_social = s.getRg_social();
             fone_social = s.getFone_social();
             profissao_social = s.getProfissao_social();
             situacao_social = s.getSitucao_social();
             rua_social = s.getRua_social().getLogradouro();
             numero_social = s.getNumero_casa();
             complemento_social = s.getComplemento_rua();
             auxilio_governo = s.getAuxilio_governo();
             gestante = s.getGestante();
             obs_gestante = s.getObs_gestante();
             acamado = s.getAcamado();
             obs_acamado = s.getObs_acamado();
             deficiente = s.getDeficiente();
             obs_deficiente = s.getObs_deficiente();
             nro_adultos = s.getNro_adultos();
             nro_jovens = s.getNro_joves();
             nro_adolecentes = s.getNro_adolecentes();
             nro_criancas = s.getNro_criancas();
             nro_bebes = s.getNro_bebes();
             instrucao = s.getGrau_instrucao();
             renda_mensal = s.getRenda_mensal_familiar();
             data_cadastro_social = s.getData_cadastro();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    }
    
    public void atualizarSocial(){
         if(nome_social.equals("") || id_social < 1 || cpf_social.equals("") || rg_social.equals("") || fone_social.equals("") 
           || profissao_social.equals("") || situacao_social == null || rua_social == null || numero_social <= 0 
           ||complemento_social.equals("") || auxilio_governo == null || gestante == null || acamado == null 
           || deficiente == null || nro_adultos == null || nro_jovens == null || nro_adolecentes == null || nro_criancas == null 
           || nro_bebes == null || instrucao == null || renda_mensal == null || data_cadastro_social == null
           || data_nasc == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
             EnderecoEntity e = fin.selecioneRuasPorNome(rua_social);
             SocialEntity s = new SocialEntity();
             s.setId(id_social);
             s.setPessoa(this.p);
             s.setRg_social(rg_social); 
             s.setFone_social(fone_social);
             s.setProfissao_social(profissao_social);
             s.setSitucao_social(situacao_social);
             s.setRua_social(e);
             s.setNumero_casa(numero_social);
             s.setComplemento_rua(complemento_social);
             s.setAuxilio_governo(auxilio_governo);
             s.setGestante(gestante);
             s.setObs_gestante(obs_gestante);
             s.setAcamado(acamado);
             s.setObs_acamado(obs_acamado);
             s.setDeficiente(deficiente);
             s.setObs_deficiente(obs_deficiente);
             s.setNro_adultos(nro_adultos);
             s.setNro_joves(nro_jovens);
             s.setNro_adolecentes(nro_adolecentes);
             s.setNro_criancas(nro_criancas);
             s.setNro_bebes(nro_bebes);
             s.setGrau_instrucao(instrucao);
             s.setRenda_mensal_familiar(renda_mensal);
             s.setData_cadastro(data_cadastro_social);
             if(geb.atualizaSocial(s)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
          }else{
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
          }
    }
     
    public void excluirSocial(){
         if(nome_social.equals("") || id_social < 1 || cpf_social.equals("") || rg_social.equals("") || fone_social.equals("") 
           || profissao_social.equals("") || situacao_social == null || rua_social == null || numero_social <= 0 
           ||complemento_social.equals("") || auxilio_governo == null || gestante == null || acamado == null 
           || deficiente == null || nro_adultos == null || nro_jovens == null || nro_adolecentes == null || nro_criancas == null 
           || nro_bebes == null || instrucao == null || renda_mensal == null || data_cadastro_social == null
           || data_nasc == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           }else{
             EnderecoEntity e = fin.selecioneRuasPorNome(rua_social);
             SocialEntity s = new SocialEntity();
             s.setId(id_social);
             s.setPessoa(this.p);
             s.setRg_social(rg_social);
             s.setFone_social(fone_social);
             s.setProfissao_social(profissao_social);
             s.setSitucao_social(situacao_social);
             s.setRua_social(e);
             s.setNumero_casa(numero_social);
             s.setComplemento_rua(complemento_social);
             s.setAuxilio_governo(auxilio_governo);
             s.setGestante(gestante);
             s.setObs_gestante(obs_gestante);
             s.setAcamado(acamado);
             s.setObs_acamado(obs_acamado);
             s.setDeficiente(deficiente);
             s.setObs_deficiente(obs_deficiente);
             s.setNro_adultos(nro_adultos);
             s.setNro_joves(nro_jovens);
             s.setNro_adolecentes(nro_adolecentes);
             s.setNro_criancas(nro_criancas);
             s.setNro_bebes(nro_bebes);
             s.setGrau_instrucao(instrucao);
             s.setRenda_mensal_familiar(renda_mensal);
             s.setData_cadastro(data_cadastro_social);
          if(geb.removeSocial(s)){
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
             this.apagaObjeto();
       }else{
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
          }
        }
    }
    
    private void apagaObjeto(){
             id_social = 0;
             nome_social = "";
             this.p = new PessoaEntity();
             cpf_social = "";
             rg_social = "";
             fone_social = "";
             profissao_social = "";
             situacao_social = "";
             rua_social = "";
             numero_social = 0;
             complemento_social = "";
             auxilio_governo = "";
             gestante = "";
             obs_gestante = "";
             acamado = "";
             deficiente = "";
             obs_deficiente = "";
             nro_adultos = "";
             nro_jovens = "";
             nro_adolecentes = "";
             nro_criancas = "";
             nro_bebes = "";
             instrucao = "";
             renda_mensal = "";
             data_cadastro_social = null;
             data_nasc = null;
    }
      
    public Date dataAtual(){ 
      return new Date();
    }  
}
