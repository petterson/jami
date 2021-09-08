/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.AluDesistenteEntity;
import entidades.AlunoEntity;
import entidades.EscolaEntity;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

/**
 *
 * @author petterson
 */
@Named(value = "turnoSexoJSFManagedBean")
@ViewScoped
public class TurnoSexoJSFManagedBean implements Serializable {

         @EJB
         OperacionalSessionBean ops;
         @EJB
         GerenteSessionBean geb;
         String sexo_busca;
         String turno_busca;
         List<String> list_sexo;
         List<String> list_turno;
         List<AlunoEntity> list_alunos;
         List<AluDesistenteEntity> list_alu_des;
    
    public TurnoSexoJSFManagedBean() {
        this.preencheListas();
    }
    
    private void preencheListas(){
          this.list_alu_des = new ArrayList<>();
          this.list_sexo = new ArrayList<>();
          this.list_sexo.add("Masculino");
          this.list_sexo.add("Feminino");
          this.list_turno = new ArrayList<>();
          this.list_turno.add("Manhã");
          this.list_turno.add("Tarde");
    }

    public String getSexo_busca() {
        return sexo_busca;
    }

    public void setSexo_busca(String sexo_busca) {
        this.sexo_busca = sexo_busca;
    }

    public String getTurno_busca() {
        return turno_busca;
    }

    public void setTurno_busca(String turno_busca) {
        this.turno_busca = turno_busca;
    }

    public List<String> getList_sexo() {
        return list_sexo;
    }

    public void setList_sexo(List<String> list_sexo) {
        this.list_sexo = list_sexo;
    }

    public List<String> getList_turno() {
        return list_turno;
    }

    public void setList_turno(List<String> list_turno) {
        this.list_turno = list_turno;
    }

    public List<AlunoEntity> getList_alunos() {
        return list_alunos;
    }

    public void setList_alunos(List<AlunoEntity> list_alunos) {
        this.list_alunos = list_alunos;
    }

    public List<AluDesistenteEntity> getList_alu_des() {
        return list_alu_des;
    }

    public void setList_alu_des(List<AluDesistenteEntity> list_alu_des) {
        this.list_alu_des = list_alu_des;
    }
    
    public void buscar(){
        this.list_alu_des = new ArrayList<>();
         List<String> nome_ati = new ArrayList<>();
         this.list_alunos = ops.selecioneAlunosPorTurnoSexo(sexo_busca, turno_busca);
         System.out.println(list_alunos.size());
          for(int i=0; i<list_alunos.size(); i++){
              AluDesistenteEntity al = new AluDesistenteEntity();
              al.setNome(list_alunos.get(i).getPessoa().getNome());
              al.setSexo(list_alunos.get(i).getSexo());
              al.setSituacao(list_alunos.get(i).getTurno());
              al.setData_matricula(this.mesString());
              al.setEscola(list_alunos.get(i).getEscola().getNome_escola());
              al.setList_turma(geb.selecioneTurmasPorTurno(turno_busca, list_alunos.get(i).getPessoa().getNome()));
              List<String> nome_turmas = new ArrayList<>(geb.selecioneTurmasPorTurno(turno_busca, list_alunos.get(i).getPessoa().getNome()));
              System.out.println(nome_turmas.size());
              for(int j=0; j< nome_turmas.size(); j++){
                  nome_ati.addAll(geb.selecioneAtividadesDesistente(nome_turmas.get(j)));
              }
              List<String> n_a = nome_ati.stream().distinct().collect(Collectors.toList());
              al.setList_atividades(n_a);
              this.list_alu_des.add(al);
        }
    }
    
    public void imprimir(){
    }
    
    public Date mesString(){
         Date hoje  = new Date();
     return hoje;
    }

}
