/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.ProgramaEntity;
import entidades.ProjetoEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author petterson
 */
@Named(value = "programaJSFManagedBean")
@SessionScoped
public class ProgramaJSFManagedBean implements Serializable {

        @EJB
        GerenteSessionBean geb;
        long id_programa;
        String nome_programa;
        String nome_programa_atualiza;
        Date data_inicio;
        String nome_projeto_entity;
        ArrayList<String> nome_projetos;
        ArrayList<String> nome_prjetos_selecionados;
        List<String> nome_projeto_selecionados_atualiza;
        String descricao_programa;
        
    
    public ProgramaJSFManagedBean() {
        this.nome_projetos = new ArrayList<>();
    }

    public long getId_programa() {
        return id_programa;
    }

    public void setId_programa(long id_programa) {
        this.id_programa = id_programa;
    }

    public List<String> getNome_projetos() {
        return geb.selecioneProjetos();
    }

    public void setNome_projetos(ArrayList<String> nome_projetos) {
        this.nome_projetos = nome_projetos;
    }

    public String getNome_programa() {
        return nome_programa;
    }

    public void setNome_programa(String nome_programa) {
        this.nome_programa = nome_programa;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public ArrayList<String> getNome_prjetos_selecionados() {
        return nome_prjetos_selecionados;
    }

    public void setNome_prjetos_selecionados(ArrayList<String> nome_prjetos_selecionados) {
        this.nome_prjetos_selecionados = nome_prjetos_selecionados;
    }

    public String getDescricao_programa() {
        return descricao_programa;
    }

    public void setDescricao_programa(String descricao_programa) {
        this.descricao_programa = descricao_programa;
    }

    public String getNome_projeto_entity() {
        return nome_projeto_entity;
    }

    public void setNome_projeto_entity(String nome_projeto_entity) {
            this.nome_projetos.add(nome_projeto_entity);
        this.nome_projeto_entity = nome_projeto_entity;
    }

    public List<String> getNome_projeto_selecionados_atualiza() {
        return nome_projeto_selecionados_atualiza;
    }

    public void setNome_projeto_selecionados_atualiza(List<String> nome_projeto_selecionados_atualiza) {
        this.nome_projeto_selecionados_atualiza = nome_projeto_selecionados_atualiza;
    }

    public String getNome_programa_atualiza() {
        return nome_programa_atualiza;
    }

    public void setNome_programa_atualiza(String nome_programa_atualiza) {
        this.nome_programa_atualiza = nome_programa_atualiza;
    }
    
    public String cadastrarPrograma(){
        if(nome_programa.equals("") || id_programa > 1 || data_inicio == null || nome_prjetos_selecionados.size() <= 0
           || descricao_programa.equals("")){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
     }else{
              if(geb.verificaProjetoCadastrado(nome_prjetos_selecionados)){
              List<ProjetoEntity> p = geb.selecioneListaProjeto(nome_prjetos_selecionados);
              ProgramaEntity pa = new ProgramaEntity();
              pa.setId(id_programa);
              pa.setNome_programa(nome_programa);
              pa.setData_criacao(data_inicio);
              pa.setNome_projetos(p);
              pa.setDescricao_programa(descricao_programa);
              if(geb.cadastrePrograma(pa)){
                 for(int i=0; i< p.size(); i++){
                     ProjetoEntity pro = p.get(i);
                     pro.setPrograma(pa);
                     geb.atualizaProjeto(pro);
                 }
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   this.apagaObjeto();
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
              }else{
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Um Projeto só pode Pertencer a um Programa"));
             }
        } 
    return "gerente?faces-redirect=true";
    }
    
    public void buscaPrograma(){
         if(nome_programa.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            ProgramaEntity p = new ProgramaEntity();
            p = geb.selecioneProgramaAtualiza(nome_programa);
         if(p.getNome_programa().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_programa = p.getId();
             nome_programa = p.getNome_programa();
             nome_programa_atualiza = nome_programa;
             data_inicio = p.getData_criacao();
             nome_prjetos_selecionados = this.preencheNomeProjeto(p.getNome_projetos());
             nome_projeto_selecionados_atualiza = this.preencheNomeProjeto(p.getNome_projetos());
             descricao_programa = p.getDescricao_programa();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    } 
    
    public String atualisaPrograma(){
         if(nome_programa.equals("") || id_programa < 1 || data_inicio == null || nome_prjetos_selecionados.size() <= 0
           || descricao_programa.equals("")){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
             List<String> nova = this.listaAtualiza();
             if(geb.verificaProjetoCadastrado(nova)){
                nome_prjetos_selecionados.addAll(nova);
             List<ProjetoEntity> pr = geb.selecioneListaProjeto(nome_prjetos_selecionados);
             ProgramaEntity p = new ProgramaEntity();
             p.setId(id_programa);
             p.setNome_programa(nome_programa_atualiza);
             p.setData_criacao(data_inicio);
             p.setNome_projetos(pr);
             p.setDescricao_programa(descricao_programa);
             if(geb.atualizaPrograma(p)){
                 for(int i=0; i< pr.size(); i++){
                     ProjetoEntity pro = pr.get(i);
                     pro.setPrograma(p);
                     geb.atualizaProjeto(pro);
                 }
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
             }
             }else{
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Um Projeto só pode Pertencer a um Programa"));
             return "Um Projeto só pode Pertencer a um Programa!";
             }
          }
         return "gerente?faces-redirect=true";
    }
     
      public String excluirPrograma(){
           if(nome_programa.equals("") || id_programa < 10 || data_inicio == null || nome_prjetos_selecionados.size() <= 0
           || descricao_programa.equals("")){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
              return "Preencha os Campos!";
        }else{
               List<ProjetoEntity> pr = geb.selecioneListaProjeto(nome_prjetos_selecionados);
             ProgramaEntity p = new ProgramaEntity();
             p.setId(id_programa);
             p.setNome_programa(nome_programa_atualiza);
             p.setData_criacao(data_inicio);
             p.setNome_projetos(pr);
             p.setDescricao_programa(descricao_programa);
                if(geb.removePrograma(p)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
            }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                return "ID não válido!";
                }
           }
     return "gerente?faces-redirect=true";
    }
      
    private List<String> listaAtualiza(){
            List<String> diferenca = new ArrayList<>();
            for(int i=0; i<nome_prjetos_selecionados.size();i++){
                String aux= nome_prjetos_selecionados.get(i);
                if(!nome_projeto_selecionados_atualiza.contains(aux))
                   diferenca.add(aux);
            }
    return diferenca;
    }
      
    private void apagaObjeto(){
             id_programa = 0;
             nome_programa = "";
             nome_programa_atualiza="";
             data_inicio = null;
             nome_prjetos_selecionados.clear();
             descricao_programa = null;
    }
      
      public Date dataAtual(){
      
      return new Date();
      }
     
    private ArrayList<String> preencheNomeProjeto(List<ProjetoEntity> a){
          ArrayList<String> al = new ArrayList<>();        
          for(int i=0; i<a.size(); i++){
              String s = a.get(i).getNome_projeto();
              al.add(s);
          }
      return al;
    } 
}
