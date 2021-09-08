/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.AtividadeEntity;
import entidades.ProjetoEntity;
import entidades.TurmaEntity;
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

/**
 *
 * @author petterson
 */
@Named(value = "atividadeJSFManagedBean")
@SessionScoped
public class AtividadeJSFManagedBean implements Serializable {

       @EJB
       GerenteSessionBean geb;
       @EJB
       OperacionalSessionBean ops;
       long id_atividade;
       String nome_atividade;
       String nome_atividade_atualiza;
       Date data_inicio;
       String nome_turma_entity;
       ArrayList<String> nome_turmas;
       List<String> nome_turmas_selecionadas;
       List<String> nome_turmas_selecionadas_atualiza;
       ProjetoEntity p;
       @Inject
       ProjetoJSFManagedBean pro;
       
    
    public AtividadeJSFManagedBean() {
        this.p = new ProjetoEntity();
          this.nome_turmas = new ArrayList<>();
    }

    public long getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(long id_atividade) {
        this.id_atividade = id_atividade;
    }

    public List<String> getNome_turmas() {
        return geb.selecioneTurmas();
    }

    public void setNome_turmas(ArrayList<String> nome_turmas) {
        this.nome_turmas = nome_turmas;
    }
   
    public String getNome_atividade() {
        return nome_atividade;
    }

    public void setNome_atividade(String nome_atividade) {
        this.nome_atividade = nome_atividade;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getNome_turma_entity() {
        return nome_turma_entity;
    }

    public void setNome_turma_entity(String nome_turma_entity) {
            this.nome_turmas.add(nome_turma_entity);
        this.nome_turma_entity = nome_turma_entity;
    }

    public List<String> getNome_turmas_selecionadas() {
        return nome_turmas_selecionadas;
    }

    public void setNome_turmas_selecionadas(ArrayList<String> nome_turmas_selecionadas) {
        this.nome_turmas_selecionadas = nome_turmas_selecionadas;
    }

    public String getNome_atividade_atualiza() {
        return nome_atividade_atualiza;
    }

    public void setNome_atividade_atualiza(String nome_atividade_atualiza) {
        this.nome_atividade_atualiza = nome_atividade_atualiza;
    }

    public ProjetoEntity getP() {
        return p;
    }

    public void setP(ProjetoEntity p) {
        this.p = p;
    }

    public List<String> getNome_turmas_selecionadas_atualiza() {
        return nome_turmas_selecionadas_atualiza;
    }

    public void setNome_turmas_selecionadas_atualiza(List<String> nome_turmas_selecionadas_atualiza) {
        this.nome_turmas_selecionadas_atualiza = nome_turmas_selecionadas_atualiza;
    }

    public String cadastrarAtividade(){
        if(nome_atividade.equals("") || id_atividade > 1 || data_inicio == null || nome_turmas_selecionadas.size() <=0){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              if(geb.verificaTurmaCadastrado(nome_turmas_selecionadas)){
              List<TurmaEntity> t = geb.selecioneListaTurma(nome_turmas_selecionadas);
              AtividadeEntity at = new AtividadeEntity();
              at.setId(id_atividade);
              at.setNome_atividade(nome_atividade);
              at.setData_criacao(data_inicio);
              at.setNome_turmas(t);
              if(geb.cadastreAtividade(at)){
                 for(int i=0; i<t.size(); i++){
                     TurmaEntity tu = t.get(i);
                     tu.setAtividade(at);
                     geb.atualizaTurma(tu);
                 }
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Cadastrado!"));
                pro.setNome_atividade_entity(at.getNome_atividade());
                this.apagaObjeto();
              }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já existe Esse Nome!"));
                FacesContext ctx = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                ctx.addMessage(null, msg);
                return "preencha os campos";
              }
              }else{
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Tem turma ai Pertencente a outro Atividade!"));
             return "Tem turma ai Pertencente a outro Atividade!";
        }
        }
    return "projeto?faces-redirect=true";
    }
      
      public void buscarAtividade(){
          if(nome_atividade.equals("")){
             FacesContext ctx = FacesContext.getCurrentInstance();
             FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
          AtividadeEntity at = new AtividadeEntity();
          at = geb.selecioneAtividadeAtualiza(nome_atividade);
        if(at.getNome_atividade().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite um Nome Válido"));
        }else{
            id_atividade = at.getId();
            nome_atividade = at.getNome_atividade();
            nome_atividade_atualiza = nome_atividade;
            nome_turmas_selecionadas = this.preencheStringTurma(at.getNome_turmas());
            nome_turmas_selecionadas_atualiza = this.preencheStringTurma(at.getNome_turmas());
            data_inicio = at.getData_criacao();
            this.p = at.getProjeto();
            FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
        }
      }       
   }
      
      public String atualizarAtividade(){
          if(nome_atividade.equals("") || id_atividade < 10 || data_inicio == null || nome_turmas_selecionadas.size() <=0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           return "preencha os campos";
      }else{
             List<String> nova = this.listaAtualiza();
             if(geb.verificaAtividadeCadastrada(nova)){
                nome_turmas_selecionadas.addAll(nova);
             List<TurmaEntity> t = geb.selecioneListaTurma(nome_turmas_selecionadas);
             AtividadeEntity a = new AtividadeEntity();
             a.setId(id_atividade);
             a.setNome_atividade(nome_atividade_atualiza);
             a.setData_criacao(data_inicio);
             a.setNome_turmas(t);
             a.setProjeto(this.p);
             if(geb.atualizaAtividade(a)){
                 for(int i=0; i<t.size(); i++){
                     TurmaEntity tu = t.get(i);
                     tu.setAtividade(a);
                     geb.atualizaTurma(tu);
                 }
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
         }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
              }else{
        FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Uma Turma só pode pertencer a uma Atividade!"));
        return "Uma Turma só pode pertencer a uma Atividade!";
                  }
          }
          return "projeto?faces-redirect=true";
      }
      
    public String excluirAtividade(){
          if(nome_atividade.equals("") || id_atividade < 1 || data_inicio == null || nome_turmas_selecionadas.size() <=0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
             List<TurmaEntity> t = geb.selecioneListaTurma(nome_turmas_selecionadas);
             AtividadeEntity at = new AtividadeEntity();
             at.setId(id_atividade);
             at.setNome_atividade(nome_atividade_atualiza);
             at.setNome_turmas(t);
             at.setData_criacao(data_inicio);
             at.setProjeto(this.p);
             if(at.getProjeto() != null){
                if(this.p.getNome_atividades().size() > 1){
                    this.p.getNome_atividades().remove(at);
                    geb.atualizaProjeto(this.p);
                    if(geb.removeAtividade(at)){
                       FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                       this.apagaObjeto();
                 }else{
                       FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atividade não existe!"));
                       return "Atividade não Existe!";
                 }
                }
          }else{
                    if(geb.removeAtividade(at)){
                        FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                        this.apagaObjeto();
                     }else{
                        FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atividade não existe!"));
                        return "Atividade não Existe!";
                     }
          }
       }
          return "projeto?faces-redirect=true";
   }
    private List<String> listaAtualiza(){
            List<String> diferenca = new ArrayList<>();
            for(int i=0; i<nome_turmas_selecionadas.size();i++){
                String aux= nome_turmas_selecionadas.get(i);
                if(!nome_turmas_selecionadas_atualiza.contains(aux))
                   diferenca.add(aux);
            }
    return diferenca;
    }
      
    public Date dataAtual(){
      return new Date();
    }
      
    private List<String> preencheStringTurma(List<TurmaEntity> a){
           List<String> turmas = new ArrayList<>();
           for(int i=0; i< a.size(); i++){
               String s = a.get(i).getNome_turma();
               turmas.add(s);
           }
     return turmas;    
    }
    
    private void apagaObjeto(){
             id_atividade = 0;
             nome_atividade = "";
             nome_atividade_atualiza="";
             data_inicio = null;
             nome_turmas_selecionadas.clear();

    }
}
