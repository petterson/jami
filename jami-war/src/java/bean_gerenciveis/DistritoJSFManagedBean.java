/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.CidadeEntity;
import entidades.DistritoEntity;
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
@Named(value = "distritoJSFManagedBean")
@SessionScoped
public class DistritoJSFManagedBean implements Serializable {

         @EJB
         OperacionalSessionBean ops;
         @EJB
         FinanceiroSessionBean fin;
         long id_distrito;
         String nome_distrito;
         String nome_distrito_atualiza;
         String cidade;
         ArrayList<String> list_nome_cidades;
         String nome_cidade_entity;
         @Inject
         EnderecoJSFManagedBean enm;
    
    public DistritoJSFManagedBean() {
        this.list_nome_cidades = new ArrayList<>();
    }

    public long getId_distrito() {
        return id_distrito;
    }

    public void setId_distrito(long id_distrito) {
        this.id_distrito = id_distrito;
    }

    public String getNome_distrito() {
        return nome_distrito;
    }

    public void setNome_distrito(String nome_distrito) {
        this.nome_distrito = nome_distrito;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<String> getList_nome_cidades() {
        return ops.selecioneCidades();
    }

    public void setList_nome_cidades(ArrayList<String> list_nome_cidades) {
        this.list_nome_cidades = list_nome_cidades;
    }

    public String getNome_cidade_entity() {
        return nome_cidade_entity;
    }

    public void setNome_cidade_entity(String nome_cidade_entity) {
            this.list_nome_cidades.add(nome_cidade_entity);
        this.nome_cidade_entity = nome_cidade_entity;
    }

    public String getNome_distrito_atualiza() {
        return nome_distrito_atualiza;
    }

    public void setNome_distrito_atualiza(String nome_distrito_atualiza) {
        this.nome_distrito_atualiza = nome_distrito_atualiza;
    }
    
    public String cadastrarDistrito(){
        if(nome_distrito.equals("") || id_distrito > 1 || cidade == null){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else {
              CidadeEntity c = fin.selecioneCidadePorNome(cidade);
              DistritoEntity d = new DistritoEntity();
              d.setId(id_distrito);
              d.setNome_distrito(nome_distrito);
              d.setCidade(c);
              c.getList_distrito().add(d);
              if(ops.cadastreDistrito(d)){
                  ops.atualizaCidade(c);
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   enm.setNome_distrito_entity(d.getNome_distrito());
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
        } 
        this.apagaObjeto();
    return "endereco?faces-redirect=true";
    }
    
    public void buscarDistrito(){
         if(nome_distrito.equals("")){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
          }else{
            DistritoEntity d = new DistritoEntity();
         d = ops.selecioneDistritoAtualiza(nome_distrito);
         if(d.getNome_distrito().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_distrito = d.getId();
             nome_distrito = d.getNome_distrito();
             nome_distrito_atualiza = nome_distrito;
             cidade = d.getCidade().getNome_cidade();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    }
    
    public void atualizarDistrito(){
         if(nome_distrito.equals("") || id_distrito < 1 || cidade == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
           // return "preencha os campos";
        }else {
               CidadeEntity c = fin.selecioneCidadePorNome(cidade);
               DistritoEntity d = new DistritoEntity();
               d.setId(id_distrito);
               d.setNome_distrito(nome_distrito_atualiza);
               d.setCidade(c);
               if(ops.atualizaDistrito(d))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
             else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
          }
          this.apagaObjeto();
    }
     
    public void excluirDistrito(){
         if(nome_distrito.equals("") || id_distrito < 1 || cidade == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
                CidadeEntity c = fin.selecioneCidadePorNome(cidade);
                DistritoEntity d = new DistritoEntity();
                d.setId(id_distrito);
                d.setNome_distrito(nome_distrito_atualiza);
                d.setCidade(c);
                if(ops.removeDistritoAtualizaCidade(d))
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
               else
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
         }
           this.apagaObjeto();
    }
    
    private void apagaObjeto(){
             id_distrito = 0;
             nome_distrito = "";
             nome_distrito_atualiza="";
             cidade = "";
    }
}
