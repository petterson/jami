
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import entidades.TipoInstituicaoEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "tipoInstituicaoSFManagedBean")
@SessionScoped
public class TipoInstituicaoSFManagedBean implements Serializable {

          @EJB
          GerenteSessionBean geb;
          long id_tipo_instituicao;
          String nome_instituicao;
          String nome_instituicao_atualiza;
          List<String> list_nomes;
          String remove_selecionado;
          @Inject
          InstituicaoJSFManagedBean ins;
    
    public TipoInstituicaoSFManagedBean() {
        this.list_nomes = new ArrayList<>();
    }

    public long getId_tipo_instituicao() {
        return id_tipo_instituicao;
    }

    public void setId_tipo_instituicao(long id_tipo_instituicao) {
        this.id_tipo_instituicao = id_tipo_instituicao;
    }

    public String getNome_instituicao() {
        return nome_instituicao;
    }

    public void setNome_instituicao(String nome_instituicao) {
        this.nome_instituicao = nome_instituicao;
    }

    public String getNome_instituicao_atualiza() {
        return nome_instituicao_atualiza;
    }

    public void setNome_instituicao_atualiza(String nome_instituicao_atualiza) {
        this.nome_instituicao_atualiza = nome_instituicao_atualiza;
    }

    public List<String> getList_nomes() {
        return geb.selecioneTipoInstituicao();
    }

    public void setList_nomes(List<String> list_nomes) {
        this.list_nomes = list_nomes;
    }

    public String getRemove_selecionado() {
        return remove_selecionado;
    }

    public void setRemove_selecionado(String remove_selecionado) {
        this.remove_selecionado = remove_selecionado;
    }
    
    public String cadastrarTipoInstituicao(){
        if(nome_instituicao.equals("") || id_tipo_instituicao > 1){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              TipoInstituicaoEntity tp= new TipoInstituicaoEntity();
              tp.setId(id_tipo_instituicao);
              tp.setNome(nome_instituicao);
              if(geb.cadastreTipoInstituicao(tp)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   ins.setNome_tipo_instituicao_entity(tp.getNome());
                   this.apagaObjeto();
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Salário!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
        } 
    return "/securi/instituicaoParceira?faces-redirect=true";
    }
    
    public void buscarTipoInstituicao(){
          if(nome_instituicao.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             TipoInstituicaoEntity t = new TipoInstituicaoEntity();
             t = geb.selecioneTipoInstituicaoAtualiza(nome_instituicao);
            if(t.getNome().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_tipo_instituicao = t.getId();
             nome_instituicao = t.getNome();
             nome_instituicao_atualiza = nome_instituicao;
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public void excluirTipoInstituicao(){
           if(nome_instituicao.equals("") || id_tipo_instituicao <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "É preciso Buscar antes de Excluir");
              ctx.addMessage(null, msg);
      }else{
             TipoInstituicaoEntity t = new TipoInstituicaoEntity();
             t.setId(id_tipo_instituicao);
             t.setNome(nome_instituicao_atualiza);
             if(geb.removeTipoInstituicao(t))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
           else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Nome já Removido"));
           }
           this.apagaObjeto();
    }
    
    private void apagaObjeto(){
             id_tipo_instituicao =0;
             nome_instituicao = "";
             nome_instituicao_atualiza="";
    }
    
    public void listaTipoInstituicao(AjaxBehaviorEvent event){
        nome_instituicao = remove_selecionado;
    }
    
}
