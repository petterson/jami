/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.AtividadeEntity;
import entidades.InstituicaoEntity;
import entidades.ContribuinteEntity;
import entidades.ProgramaEntity;
import entidades.ProjetoEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "projetoJSFManagedBean")
@SessionScoped
public class ProjetoJSFManagedBean implements Serializable {

        @EJB
        GerenteSessionBean geb;
        long id_projeto;
        long id_busca;
        String nome_projeto;
        String nome_projeto_atualiza;
        Date data_inicio;
        ArrayList<String> nome_atividades;
        String nome_atividade_entity;
        ArrayList<String> nome_atividades_selecionadas;
        List<String> nome_atividade_selecionadas_atualiza;
        ArrayList<String> nome_parceiros;
        String instituicao_parceira_entity;
        ArrayList<String> nome_parceiros_selecionados;
        String custo_projeto;
        ProgramaEntity pro;
        @Inject
        ProgramaJSFManagedBean prm;
        
    
    public ProjetoJSFManagedBean() {
        this.pro = new ProgramaEntity();
        this.nome_atividades = new ArrayList<>();
        this.nome_parceiros = new ArrayList<>();
        this.nome_atividade_selecionadas_atualiza = new ArrayList<>();
    }

    public long getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(long id_projeto) {
        this.id_projeto = id_projeto;
    }

    public String getNome_projeto() {
        return nome_projeto;
    }

    public void setNome_projeto(String nome_projeto) {
        this.nome_projeto = nome_projeto;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public List<String> getNome_atividades() {
        return geb.selecioneAtividades();
    }

    public void setNome_atividades(ArrayList<String> nome_atividades) {
        this.nome_atividades = nome_atividades;
    }

    public ArrayList<String> getNome_atividades_selecionadas() {
        return nome_atividades_selecionadas;
    }

    public void setNome_atividades_selecionadas(ArrayList<String> nome_atividades_selecionadas) {
        this.nome_atividades_selecionadas = nome_atividades_selecionadas;
    }

    public List<String> getNome_parceiros() {
        return geb.selecioneInstituicoes();
    }

    public void setNome_parceiros(ArrayList<String> nome_parceiros) {
        this.nome_parceiros = nome_parceiros;
    }

    public ArrayList<String> getNome_parceiros_selecionados() {
        return nome_parceiros_selecionados;
    }

    public void setNome_parceiros_selecionados(ArrayList<String> nome_parceiros_selecionados) {
        this.nome_parceiros_selecionados = nome_parceiros_selecionados;
    }

    public String getCusto_projeto() {
        return custo_projeto;
    }

    public void setCusto_projeto(String custo_projeto) {
        this.custo_projeto = custo_projeto;
    }

    public String getNome_atividade_entity() {
        return nome_atividade_entity;
    }

    public void setNome_atividade_entity(String nome_atividade_entity) {
        this.nome_atividades.add(nome_atividade_entity);
        this.nome_atividade_entity = nome_atividade_entity;
    }

    public String getInstituicao_parceira_entity() {
        return instituicao_parceira_entity;
    }

    public void setInstituicao_parceira_entity(String instituicao_parceira_entity) {
          this.nome_parceiros.add(instituicao_parceira_entity);
      this.instituicao_parceira_entity = instituicao_parceira_entity;
    }

    public List<String> getNome_atividade_selecionadas_atualiza() {
        return nome_atividade_selecionadas_atualiza;
    }

    public void setNome_atividade_selecionadas_atualiza(List<String> nome_atividade_selecionadas_atualiza) {
        this.nome_atividade_selecionadas_atualiza = nome_atividade_selecionadas_atualiza;
    }

    public long getId_busca() {
        return id_busca;
    }

    public void setId_busca(long id_busca) {
        this.id_busca = id_busca;
    }

    public String getNome_projeto_atualiza() {
        return nome_projeto_atualiza;
    }

    public void setNome_projeto_atualiza(String nome_projeto_atualiza) {
        this.nome_projeto_atualiza = nome_projeto_atualiza;
    }

    public ProgramaEntity getPro() {
        return pro;
    }

    public void setPro(ProgramaEntity pro) {
        this.pro = pro;
    }
    
    public String cadastrarProjeto(){
        if(nome_projeto.equals("") || id_projeto > 1 || data_inicio == null || nome_atividades_selecionadas.size() <= 0
           || nome_parceiros_selecionados.size() <= 0 || custo_projeto.equals("")){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite im Nome Válido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
            if(geb.verificaAtividadeCadastrada(nome_atividades_selecionadas)){
              List<AtividadeEntity> a = geb.selecioneListaAtividade(nome_atividades_selecionadas);
              List<InstituicaoEntity> is = geb.selecioneListaInstituicao(nome_parceiros_selecionados);
              ProjetoEntity po = new ProjetoEntity();
              po.setId(id_projeto);
              po.setNome_projeto(nome_projeto);
              po.setData_inicio(data_inicio);
              po.setNome_atividades(a);
              po.setInstituicoes(is);
              po.setCust_anual(custo_projeto);
              if(geb.cadastreProjeto(po)){
                  for(int i=0; i<a.size(); i++){
                      AtividadeEntity at = a.get(i);
                      at.setProjeto(po);
                      geb.atualizaAtividade(at);
                  }
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   prm.setNome_projeto_entity(po.getNome_projeto());
                   this.apagaObjeto();
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite im Nome Válido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
        }else{
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Uma Atividade só pode Pertencer a Um projeto!"));
        }
    }
    return "programa?faces-redirect=true";
    }
      
    public void buscarProjeto(){
          if(nome_projeto.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             ProjetoEntity p = new ProjetoEntity();
             p = geb.selecioneProjetoAtualiza(nome_projeto);
            if(p.getNome_projeto().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_projeto = p.getId();
             //id_busca = p.getId();
             nome_projeto = p.getNome_projeto();
             nome_projeto_atualiza = nome_projeto;
             data_inicio = p.getData_inicio();
             nome_atividades_selecionadas = this.preencheNomeAtividades(p.getNome_atividades());
             nome_atividade_selecionadas_atualiza = this.preencheNomeAtividades(p.getNome_atividades());
             nome_parceiros_selecionados = this.preencheNomeParceiros(p.getInstituicoes());
             custo_projeto = p.getCust_anual();
             this.pro = p.getPrograma();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
      }
      
      public String atualizarProjeto(){
          if(nome_projeto.equals("") || id_projeto <1 || data_inicio == null || nome_atividades_selecionadas.size() <= 0
             || nome_parceiros_selecionados.size() <= 0 || custo_projeto == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo ou Só pode ser Atualizado");
           ctx.addMessage(null, msg);
           return "Preencha os Campos!";
      }else{
             List<String> nova = this.listaAtualiza();
             if(geb.verificaAtividadeCadastrada(nova)){
                nome_atividades_selecionadas.addAll(nova);
             List<AtividadeEntity> a = geb.selecioneListaAtividade(nome_atividades_selecionadas);
             List<InstituicaoEntity> is = geb.selecioneListaInstituicao(nome_parceiros_selecionados);
             ProjetoEntity p = new ProjetoEntity();
             p.setId(id_projeto);
             p.setNome_projeto(nome_projeto_atualiza);
             p.setData_inicio(data_inicio);
             p.setNome_atividades(a);
             p.setInstituicoes(is);
             p.setCust_anual(custo_projeto);
             p.setPrograma(this.pro);
             if(geb.atualizaProjeto(p)){
                for(int i=0; i<a.size(); i++){
                      AtividadeEntity at = a.get(i);
                      at.setProjeto(p);
                      geb.atualizaAtividade(at);
                  }
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
        }else{
        FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Uma Atividade só pode Pertencer a Um projeto!"));
        return "Uma Atividade só pode Pertencer a Um projeto!!";
        }
          }
          return "programa?faces-redirect=true";
      }
      
      public String excluirProjeto(){
           if(nome_projeto.equals("") || id_projeto <10 || data_inicio == null || nome_atividades_selecionadas.size() <= 0
              || nome_parceiros_selecionados.size() <= 0 || custo_projeto.equals("")){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
              return "Preencha os Campos!";
       }else{
             List<AtividadeEntity> a = geb.selecioneListaAtividade(nome_atividades_selecionadas);
             List<InstituicaoEntity> is = geb.selecioneListaInstituicao(nome_parceiros_selecionados);
             ProjetoEntity p = new ProjetoEntity();
             p.setId(id_projeto);
             p.setNome_projeto(nome_projeto_atualiza);
             p.setData_inicio(data_inicio);
             p.setNome_atividades(a);
             p.setInstituicoes(is);
             p.setCust_anual(custo_projeto);
             p.setPrograma(this.pro);
             if(p.getPrograma() != null){
             if(this.pro.getNome_projetos().size() > 1){
                 this.pro.getNome_projetos().remove(p);
                 geb.atualizaPrograma(this.pro);
                 if(geb.removeProjeto(p)){
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                    this.apagaObjeto();
          }else{
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                    return "Id Não valido!";
             }
             }
             }else{
             if(geb.removeProjeto(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
          }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                return "Id Não valido!";
             }
          }
        }
           return "programa?faces-redirect=true";
    }
      
    private List<String> listaAtualiza(){
            List<String> diferenca = new ArrayList<>();
            for(int i=0; i<nome_atividades_selecionadas.size();i++){
                String aux= nome_atividades_selecionadas.get(i);
                if(!nome_atividade_selecionadas_atualiza.contains(aux))
                   diferenca.add(aux);
            }
    return diferenca;
    }
      
    private void apagaObjeto(){
             id_projeto = 0;
             nome_projeto = "";
             nome_projeto_atualiza="";
             data_inicio = null;
             custo_projeto = "";
             this.pro = null;
             nome_atividades_selecionadas.clear();
             nome_parceiros_selecionados.clear();
             nome_atividade_selecionadas_atualiza.clear();
    }
      
    public Date dataAtual(){
      
      return new Date();
      }
      
    private ArrayList<String> preencheNomeAtividades(List<AtividadeEntity> a){
          ArrayList<String> al = new ArrayList<>();        
          for(int i=0; i<a.size(); i++){
              String s = a.get(i).getNome_atividade();
              al.add(s);
          }
      return al;
    } 
    
    private ArrayList<String> preencheNomeParceiros(List<InstituicaoEntity> a){
          ArrayList<String> al = new ArrayList<>();        
          for(int i=0; i<a.size(); i++){
              String s = a.get(i).getNome_instituicao();
              al.add(s);
          }
      return al;
    } 
}
