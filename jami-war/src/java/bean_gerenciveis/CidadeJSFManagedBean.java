/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.OperacionalSessionBean;
import entidades.CidadeEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "cidadeJSFManagedBean")
@SessionScoped
public class CidadeJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops;
    long id_cidade;
    String estado;
    String nome_cidade;
    String nome_cidade_atualiza;
    List<String> list_estados;
    @Inject
    DistritoJSFManagedBean dsm;
    @Inject
    AlunoJSFManagedBean alm;
    
    
    
    public CidadeJSFManagedBean() {
        this.list_estados = new ArrayList<>();
        this.list_estados.add("AC");
        this.list_estados.add("AL");
        this.list_estados.add("AP");
        this.list_estados.add("AM");
        this.list_estados.add("BA");
        this.list_estados.add("CE");
        this.list_estados.add("DF");
        this.list_estados.add("ES");
        this.list_estados.add("GO");
        this.list_estados.add("MA");
        this.list_estados.add("MT");
        this.list_estados.add("MS");
        this.list_estados.add("MG");
        this.list_estados.add("PA");
        this.list_estados.add("PB");
        this.list_estados.add("PR");
        this.list_estados.add("PE");
        this.list_estados.add("PI");
        this.list_estados.add("RJ");
        this.list_estados.add("RN");
        this.list_estados.add("RS");
        this.list_estados.add("RO");
        this.list_estados.add("RR");
        this.list_estados.add("SC");
        this.list_estados.add("SP");
        this.list_estados.add("SE");
        this.list_estados.add("TO");
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(long id_cidade) {
        this.id_cidade = id_cidade;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    public List<String> getList_estados() {
        return list_estados;
    }

    public void setList_estados(List<String> list_estados) {
        this.list_estados = list_estados;
    }

    public String getNome_cidade_atualiza() {
        return nome_cidade_atualiza;
    }

    public void setNome_cidade_atualiza(String nome_cidade_atualiza) {
        this.nome_cidade_atualiza = nome_cidade_atualiza;
    }
    
    public String cadastrarCidade(){
        if(nome_cidade.equals("") || id_cidade > 1 || estado == null){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
            ctx.addMessage(null, msg);
            return "preencha os campos";
        }else {
               CidadeEntity ce = new CidadeEntity();
               ce.setId(id_cidade);
               ce.setNome_cidade(nome_cidade);
               ce.setUf_cidade(estado);
               if(ops.cadastreCidade(ce)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
               dsm.setNome_cidade_entity(ce.getNome_cidade());
               alm.setCidade_entity(ce.getNome_cidade());
               this.apagaObjeto();
               }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
               }
        } 
    return "distrito?faces-redirect=true";
    }
    
    public void buscarCidade(){
         if(nome_cidade.equals("")){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
          }else{
            CidadeEntity c = new CidadeEntity();
         c = ops.selecioneCidadeAtualiza(nome_cidade);
         if(c.getNome_cidade().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_cidade = c.getId();
             nome_cidade = c.getNome_cidade();
             nome_cidade_atualiza = nome_cidade;
             estado = c.getUf_cidade();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    }
    
    public void atualizarCidade(){
           if(nome_cidade.equals("") || id_cidade < 1 || estado == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
               CidadeEntity c = new CidadeEntity();
               c.setId(id_cidade);
               c.setNome_cidade(nome_cidade_atualiza);
               c.setUf_cidade(estado);
               if(ops.atualizaCidade(c))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
          }
          this.apagaObjeto();
    }
     
    /*public void excluirCidade(){
         if(nome_cidade.equals("") || id_cidade < 1){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           }else{
                CidadeEntity c = new CidadeEntity();
                c.setId(id_cidade);
                c.setNome_cidade(nome_cidade);
                c.setUf_cidade(estado);
                    if(ops.removeCidade(c))
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
               else
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           this.apagaObjeto();
    }*/  
    
    private void apagaObjeto(){
             id_cidade = 0;
             nome_cidade = "";
             nome_cidade_atualiza="";
             estado = "";
    }
}
