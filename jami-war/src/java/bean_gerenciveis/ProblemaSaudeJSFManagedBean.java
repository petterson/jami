/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.OperacionalSessionBean;
import entidades.ProblemaSaudeEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "problemaSaudeJSFManagedBean")
@SessionScoped
public class ProblemaSaudeJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops;
    long id_problema_saude;
    String descricao_problema;
    String decricao_problema_atualiza;
    String grau_perigo; 
    ArrayList<String> list_perigos;
    @Inject
    AlunoJSFManagedBean alm;
    
    public ProblemaSaudeJSFManagedBean(){
        this.list_perigos = new ArrayList<>();
        this.list_perigos.add("Baixo");
        this.list_perigos.add("Alto");
        this.list_perigos.add("Médio");
    }

    public long getId_problema_saude() {
        return id_problema_saude;
    }

    public void setId_problema_saude(long id_problema_saude) {
        this.id_problema_saude = id_problema_saude;
    }

    public String getDescricao_problema() {
        return descricao_problema;
    }

    public void setDescricao_problema(String descricao_problema) {
        this.descricao_problema = descricao_problema;
    }

    public String getGrau_perigo() {
        return grau_perigo;
    }

    public void setGrau_perigo(String grau_perigo) {
        this.grau_perigo = grau_perigo;
    }

    public ArrayList<String> getList_perigos() {
        return list_perigos;
    }

    public void setList_perigos(ArrayList<String> list_perigos) {
        this.list_perigos = list_perigos;
    }

    public String getDecricao_problema_atualiza() {
        return decricao_problema_atualiza;
    }

    public void setDecricao_problema_atualiza(String decricao_problema_atualiza) {
        this.decricao_problema_atualiza = decricao_problema_atualiza;
    }

    public String cadastrarProblemaSaude(){
        if(descricao_problema.equals("") || id_problema_saude > 1 || grau_perigo == null){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
            ctx.addMessage(null, msg);
            return "preencha os campos";
        }else {
            ProblemaSaudeEntity pse = new ProblemaSaudeEntity();
            pse.setId(id_problema_saude);
            pse.setProblema_saude(descricao_problema);
            pse.setGrau_perigo(grau_perigo);
            if(ops.cadastreProblemaSaude(pse)){
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                alm.setProblema_entity(pse.getProblema_saude());
            }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                FacesContext ctx = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                ctx.addMessage(null, msg);
                return "preencha os campos";
            }
        }
        this.apagaObjeto();
    return "aluno?faces-redirect=true";
    }
    
    public void buscarProblema(){
         if(descricao_problema.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             ProblemaSaudeEntity p = new ProblemaSaudeEntity();
             p = ops.selecioneProblemaAtualiza(descricao_problema);
            if(p.getProblema_saude().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Selecione um Nome"));
         }else{
             id_problema_saude = p.getId();
             descricao_problema = p.getProblema_saude();
             decricao_problema_atualiza = descricao_problema;
             grau_perigo = p.getGrau_perigo();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public String atualizarProblema(){
         if(descricao_problema.equals("") || id_problema_saude < 1 || grau_perigo == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Não Selecionado");
           ctx.addMessage(null, msg);
           return "preencha os campos";
       }else{
             ProblemaSaudeEntity p = new ProblemaSaudeEntity();
             p.setId(id_problema_saude);
             p.setProblema_saude(decricao_problema_atualiza);
             p.setGrau_perigo(grau_perigo);
             if(ops.atualizaProblema(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
          }
          return "aluno?faces-redirect=true";
    }
     
      public String excluirProblemaSaude(){
           if(descricao_problema.equals("") || id_problema_saude < 1 || grau_perigo == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
              return "Preencha os Campos!";
       }else{
             ProblemaSaudeEntity p = new ProblemaSaudeEntity();
             p.setId(id_problema_saude);
             p.setProblema_saude(decricao_problema_atualiza);
             p.setGrau_perigo(grau_perigo);
             if(ops.removeProblema(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                return "ID não Válido!";
             }
           }
           return "aluno?faces-redirect=true";
      } 
      
      private void apagaObjeto(){
             id_problema_saude = 0;
             descricao_problema = "";
             decricao_problema_atualiza="";
              grau_perigo = "";
    }
}
