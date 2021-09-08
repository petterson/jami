/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.ChamadaEntity;
import entidades.EstuChaEntity;
import entidades.FrequenciaEntity;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author petterson
 */
@Named(value = "frequenciaJSFManagedBean")
@SessionScoped
public class FrequenciaJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops;
    @EJB
    GerenteSessionBean geb;
    long id_frequencia_chamada;
    String nome_frequencia_chamada;
    Date data_registro_frequencia;
    List<String> chamadas;
    List<EstuChaEntity> estu_cha;
    List<String> nomes_string;
    
    
    public FrequenciaJSFManagedBean() {
        this.chamadas = new ArrayList<>();
        this.estu_cha = new ArrayList<>();
    }

    public long getId_frequencia_chamada() {
        return id_frequencia_chamada;
    }

    public void setId_frequencia_chamada(long id_frequencia_chamada) {
        this.id_frequencia_chamada = id_frequencia_chamada;
    }

    public String getNome_frequencia_chamada() {
        return nome_frequencia_chamada;
    }

    public void setNome_frequencia_chamada(String nome_frequencia_chamada) {
        this.nome_frequencia_chamada = nome_frequencia_chamada;
    }

    public Date getData_registro_frequencia() {
        return data_registro_frequencia;
    }

    public void setData_registro_frequencia(Date data_registro_frequencia) {
        this.data_registro_frequencia = data_registro_frequencia;
    }

    public List<String> getChamadas() {
        return ops.selecioneChamadasComTurmas();
    }

    public void setChamadas(ArrayList<String> chamadas) {
        this.chamadas = chamadas;
    }
    
    public List<String> getNomes_string() {
        return nomes_string;
    }

    public void setNomes_string(List<String> nomes_string) {
        this.nomes_string = nomes_string;
    }

    public List<EstuChaEntity> getEstu_cha() {
        return estu_cha;
    }

    public void setEstu_cha(List<EstuChaEntity> estu_cha) {
        this.estu_cha = estu_cha;
    }
    
    public void cadastrarFrequencia(){
        if(nome_frequencia_chamada.equals("") || data_registro_frequencia == null || this.nomes_string.size() <=0){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
      }else{
            if(!ops.verificaFrequenciaCadastrada(nome_frequencia_chamada, data_registro_frequencia)){
            FrequenciaEntity fe = new FrequenciaEntity();
            for(int i=0; i<estu_cha.size();i++){
                EstuChaEntity es = new EstuChaEntity();
                es.setNome_estunte(this.estu_cha.get(i).getNome_estunte());
                es.setPresenca(this.estu_cha.get(i).isPresenca());
                es.setData_cadastro(this.dataHoje());
                es.setNome_chamada(nome_frequencia_chamada);
                ops.cadastreEstuChamada(es);
                        }
            List<EstuChaEntity> estudantes = ops.selecioneEstuCha(this.nomes_string, this.dataHoje(), nome_frequencia_chamada);
            fe.setId(id_frequencia_chamada);
            fe.setNome_chamada(nome_frequencia_chamada);
            fe.setEstudantes(estudantes);
            fe.setData_frequencia(data_registro_frequencia);
            if(ops.cadsatreFrequencia(fe, data_registro_frequencia)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já foi feito essa Chamada Hoje!"));
              }   
        }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já foi feito essa Chamada Hoje!"));
        }
    }
        this.apagaObjeto();
    }
    
    public void buscarFrequencia(){
           if(nome_frequencia_chamada.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
              this.estu_cha = new ArrayList<>();
              //ChamadaEntity c = ops.selecioneChamadaAtualiza(nome_frequencia_chamada);
              this.nomes_string = new ArrayList<>(geb.selecioneNomesAlunosTurmas(nome_frequencia_chamada));
              Collections.sort(nomes_string);
              if(this.data_registro_frequencia == null){
             for(int i=0; i < nomes_string.size(); i++){
                 EstuChaEntity e = new EstuChaEntity();
                 e.setNome_estunte(nomes_string.get(i));
                 e.setPresenca(true);
                 e.setNome_chamada(nome_frequencia_chamada);
                this.estu_cha.add(e);
             }                     
            }else{
             FrequenciaEntity f = new FrequenciaEntity();
             f = ops.selecioneFrequenciaAtualiza(nome_frequencia_chamada, data_registro_frequencia);
            if(f.getNome_chamada().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Chamada e Data"));
         }else{
                id_frequencia_chamada = f.getId();
                nome_frequencia_chamada = f.getNome_chamada();
                data_registro_frequencia = f.getData_frequencia();
                this.estu_cha = f.getEstudantes();
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
              }
           }
        }
    }
    
    public void atualizarFrequencia(){
           if(nome_frequencia_chamada.equals("") || id_frequencia_chamada < 10 || data_registro_frequencia == null 
              || estu_cha.size() <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           // return "preencha os campos";
      }else{
             List<EstuChaEntity> alunos = new ArrayList<>();
             FrequenciaEntity fe = new FrequenciaEntity();
             for(int i=0; i<estu_cha.size();i++){
                 EstuChaEntity es = new EstuChaEntity();
                 es.setId(this.estu_cha.get(i).getId());
                 es.setNome_estunte(this.estu_cha.get(i).getNome_estunte());
                 es.setPresenca(this.estu_cha.get(i).isPresenca());
                 es.setNome_chamada(nome_frequencia_chamada);
                 es.setData_cadastro(this.dataHoje());
                 ops.atualizaEstuCha(es);
                 alunos.add(es);
                        }
              fe.setId(id_frequencia_chamada);
              fe.setNome_chamada(nome_frequencia_chamada);
              fe.setEstudantes(alunos);
              fe.setData_frequencia(data_registro_frequencia);
              if(ops.atualizaFruequencia(fe)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
           }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite im Nome Válido");
                   ctx.addMessage(null, msg);
                   //return "preencha os campos";
              }
           }
          this.apagaObjeto();
    }
     
    public void excluirFrequencia(){
            if(nome_frequencia_chamada.equals("") || id_frequencia_chamada < 10 || data_registro_frequencia == null 
              || estu_cha.size() <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           // return "preencha os campos";
      }else{
             FrequenciaEntity fe = new FrequenciaEntity();
              fe.setId(id_frequencia_chamada);
              fe.setNome_chamada(nome_frequencia_chamada);
              fe.setEstudantes(this.estu_cha);
              fe.setData_frequencia(data_registro_frequencia);
              if(ops.removeFrequencia(fe)){
                  for(int i=0; i<this.estu_cha.size(); i++){
                      EstuChaEntity e = this.estu_cha.get(i);
                      ops.removeEstuCha(e);
                  }
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Excluido!"));
           }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não existe essa Data!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
                   ctx.addMessage(null, msg);
                   //return "preencha os campos";
              }
           }
          this.apagaObjeto();
    }
      
    private void apagaObjeto(){
             id_frequencia_chamada = 0;
             nome_frequencia_chamada = "";
             data_registro_frequencia = null;
             this.nomes_string.clear();
             this.estu_cha.clear();
    }
      
    public String dataHoje(){
          SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
      return data.format(data_registro_frequencia);
   }
    
}
