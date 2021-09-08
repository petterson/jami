/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.AlunoEntity;
import entidades.AtividadeEntity;
import entidades.ChamadaEntity;
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
@Named(value = "turmaSFManagedBean")
@SessionScoped
public class TurmaSFManagedBean implements Serializable {

     @EJB
     OperacionalSessionBean ops;
     @EJB
     GerenteSessionBean geb;
     long id_turma;
     String nome_turma;
     String nomme_turma_atualiza;
     String professor_turma;
     String nome_professor_entity;
     ArrayList<String> nome_professores;
     String nome_chamada_entity;
     ArrayList<String> nome_chamadas;
     String nome_aluno_entity;
     List<String> nome_alunos;
     List<AlunoEntity> list_alunos;
     ArrayList<String> nome_alunos_selecionados;
     Date data_ciracao;
     List<String> list_turno;
     String turno_turma;
     String chamada_turma;
     AtividadeEntity a;
     @Inject
     AtividadeJSFManagedBean atm;
     
     
    public TurmaSFManagedBean() {
        this.a=new AtividadeEntity();
        this.nome_professores = new ArrayList<>();
        this.nome_chamadas = new ArrayList<>();
        this.nome_alunos = new ArrayList<>();
        this.list_turno = new ArrayList<>();
        this.list_turno.add("Manhã");
        this.list_turno.add("Tarde");
    }

    public long getId_turma() {
        return id_turma;
    }

    public void setId_turma(long id_turma) {
        this.id_turma = id_turma;
    }

    public String getNome_turma() {
        return nome_turma;
    }

    public void setNome_turma(String nome_turma) {
        this.nome_turma = nome_turma;
    }

    public String getProfessor_turma() {
        return professor_turma;
    }

    public void setProfessor_turma(String professor_turma) {
        this.professor_turma = professor_turma;
    }

    public Date getData_ciracao() {
        return data_ciracao;
    }

    public void setData_ciracao(Date data_ciracao) {
        this.data_ciracao = data_ciracao;
    }

    public String getChamada_turma() {
        return chamada_turma;
    }

    public void setChamada_turma(String chamada_turma) {
        this.chamada_turma = chamada_turma;
    }

    public List<String> getNome_professores() {
        return geb.selecioneProfessores();
    }

    public void setNome_professores(ArrayList<String> nome_professores) {
        this.nome_professores = nome_professores;
    }

    public List<String> getNome_chamadas() {
        return ops.selecioneChamadas();
    }

    public void setNome_chamadas(ArrayList<String> nome_chamadas) {
        this.nome_chamadas = nome_chamadas;
    }

    public List<String> getNome_alunos() {
        return ops.selecioneNomeAlunos();
    }

    public void setNome_alunos(List<String> nome_alunos) {
        this.nome_alunos = nome_alunos;
    }

    public ArrayList<String> getNome_alunos_selecionados() {
        return nome_alunos_selecionados;
    }

    public void setNome_alunos_selecionados(ArrayList<String> nome_alunos_selecionados) {
        this.nome_alunos_selecionados = nome_alunos_selecionados;
    }

    public String getNome_professor_entity() {
        return nome_professor_entity;
    }

    public void setNome_professor_entity(String nome_professor_entity) {
            this.nome_professores.add(nome_professor_entity);
        this.nome_professor_entity = nome_professor_entity;
    }

    public String getNome_chamada_entity() {
        return nome_chamada_entity;
    }

    public void setNome_chamada_entity(String nome_chamada_entity) {
            this.nome_chamadas.add(nome_chamada_entity);
        this.nome_chamada_entity = nome_chamada_entity;
    }

    public String getNome_aluno_entity() {
        return nome_aluno_entity;
    }

    public void setNome_aluno_entity(String nome_aluno_entity) {
            this.nome_alunos.add(nome_aluno_entity);
        this.nome_aluno_entity = nome_aluno_entity;
    }

    public String getTurno_turma() {
        return turno_turma;
    }

    public void setTurno_turma(String turno_turma) {
        this.turno_turma = turno_turma;
    }

    public List<AlunoEntity> getList_alunos() {
        return list_alunos;
    }

    public void setList_alunos(ArrayList<AlunoEntity> list_alunos) {
        this.list_alunos = list_alunos;
    }

    public List<String> getList_turno() {
        return list_turno;
    }

    public void setList_turno(List<String> list_turno) {
        this.list_turno = list_turno;
    }

    public String getNomme_turma_atualiza() {
        return nomme_turma_atualiza;
    }

    public void setNomme_turma_atualiza(String nomme_turma_atualiza) {
        this.nomme_turma_atualiza = nomme_turma_atualiza;
    }

    public AtividadeEntity getA() {
        return a;
    }

    public void setA(AtividadeEntity a) {
        this.a = a;
    }
    
     public String cadastrarTurma(){
        if(nome_turma.equals("") || id_turma > 1 || professor_turma == null || this.nome_alunos_selecionados.size() <= 0
           || data_ciracao == null || turno_turma == null || chamada_turma == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else {
               this.list_alunos = ops.selecioneAlunos(nome_alunos_selecionados);
               ChamadaEntity c = ops.selecioneChamadaAtualiza(chamada_turma);
               TurmaEntity tma = new TurmaEntity();
               tma.setId_turma(id_turma);
               tma.setNome_turma(nome_turma);
               tma.setNome_professor(professor_turma);
               tma.setAlunos(this.list_alunos);
               tma.setData_criacao(data_ciracao);
               tma.setChamada(c);
               tma.setTurno(turno_turma);
               c.setTurma(tma);
               if(geb.cadastreTurma(tma)){
                  ops.atualizaChamada(c);
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                  atm.setNome_turma_entity(tma.getNome_turma()); 
                  this.apagaObjeto();
               }else{
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já existe essa Turma!")); 
               }
        }
    return "operacional?faces-redirect=true";
    }
     
     public void buscarTurma(){
          if(nome_turma.equals("")){
             FacesContext ctx = FacesContext.getCurrentInstance();
             FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
         TurmaEntity t = new TurmaEntity();
         t = geb.selecioneTurmaAtualiza(nome_turma);
         if(t.getNome_turma() == null){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             
           id_turma = t.getId_turma();
           nome_turma = t.getNome_turma();
           nomme_turma_atualiza = nome_turma;
           professor_turma = t.getNome_professor();
           nome_alunos_selecionados = this.preencheNomes(t.getAlunos());
           data_ciracao = t.getData_criacao();
           chamada_turma = t.getChamada().getNome_chamada();
           turno_turma = t.getTurno();
           this.a= t.getAtividade();
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
         }
        }
      }
     
     public String atualizarTurma(){
          if(nome_turma.equals("") || id_turma < 1 || professor_turma == null || this.nome_alunos_selecionados.size() <= 0
           || data_ciracao == null || turno_turma == null || chamada_turma == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
            return "preencha os campos";
     }else {
             this.list_alunos = ops.selecioneAlunos(nome_alunos_selecionados);
             ChamadaEntity c = ops.selecioneChamadaAtualiza(chamada_turma);
             TurmaEntity t = new TurmaEntity();
             t.setId_turma(id_turma);
             t.setNome_turma(nomme_turma_atualiza);
             t.setNome_professor(professor_turma);
             t.setAlunos(this.list_alunos);
             t.setData_criacao(data_ciracao);
             t.setChamada(c);
             t.setTurno(turno_turma);
             t.setAtividade(this.a);
             if(geb.atualizaTurma(t)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
          }
          return "operacional?faces-redirect=true";
      }
     
      public String excluirTurma(){
        if(nome_turma.equals("") || id_turma < 1 || professor_turma == null || this.nome_alunos_selecionados.size() <= 0
           || data_ciracao == null || turno_turma == null || chamada_turma == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
             this.list_alunos = ops.selecioneAlunos(nome_alunos_selecionados);
             ChamadaEntity c = ops.selecioneChamadaAtualiza(chamada_turma);
             TurmaEntity t = new TurmaEntity();
             t.setId_turma(id_turma);
             t.setNome_turma(nomme_turma_atualiza);
             t.setNome_professor(professor_turma);
             t.setAlunos(this.list_alunos);
             t.setData_criacao(data_ciracao);
             t.setChamada(c);  
             t.setTurno(turno_turma);
             t.setAtividade(this.a);
             if(t.getAtividade() != null){
             if(this.a.getNome_turmas().size() > 1){
                 this.a.getNome_turmas().remove(t);
                 geb.atualizaAtividade(this.a);
                 if(geb.removeTurma(t)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
            }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Removido!"));
                return "Não Removido!";
                }
             }
             }else{
                  if(geb.removeTurma(t)){
                      FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                       this.apagaObjeto();
            }else{
                     FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Removido!"));
                     return "Não Removido!";
                }
           }
      }
        return "operacional?faces-redirect=true";
    }
      
    private void apagaObjeto(){
             id_turma = 0;
             nome_turma = "";
             nomme_turma_atualiza="";
             professor_turma = "";
             this.nome_alunos_selecionados.clear();
             data_ciracao = null;
             chamada_turma = "";
             turno_turma = "";
             this.a=null;
    } 
      
    public Date dataAtual(){
      return new Date();
      }
      
    private ArrayList<String> preencheNomes(List<AlunoEntity> a){
          ArrayList<String> al = new ArrayList<>();        
          for(int i=0; i<a.size(); i++){
              String s = a.get(i).getPessoa().getNome();
              al.add(s);
          }
      return al;
    } 
}
