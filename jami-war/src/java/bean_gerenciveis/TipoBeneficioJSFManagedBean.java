
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.TipoBeneficioEntity;
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
@Named(value = "tipoBeneficioJSFManagedBean")
@SessionScoped
public class TipoBeneficioJSFManagedBean implements Serializable {

          @EJB
          GerenteSessionBean geb;
          long id_tipo_beneficio;
          String nome_beneficio;
          String nome_beneficio_atualiza;
          List<String> nome_beneficios;
          String remove_selecionado;
          @Inject
          BeneficioConcedidoJSFManagedBean bem;
    
    public TipoBeneficioJSFManagedBean() {
    }

    public long getId_tipo_beneficio() {
        return id_tipo_beneficio;
    }

    public void setId_tipo_beneficio(long id_tipo_beneficio) {
        this.id_tipo_beneficio = id_tipo_beneficio;
    }

    public String getNome_beneficio() {
        return nome_beneficio;
    }

    public void setNome_beneficio(String nome_beneficio) {
        this.nome_beneficio = nome_beneficio;
    }

    public String getNome_beneficio_atualiza() {
        return nome_beneficio_atualiza;
    }

    public void setNome_beneficio_atualiza(String nome_beneficio_atualiza) {
        this.nome_beneficio_atualiza = nome_beneficio_atualiza;
    }

    public List<String> getNome_beneficios() {
        return geb.selecioneTipoNomeBeneficioConcedidos();
    }

    public void setNome_beneficios(ArrayList<String> nome_beneficios) {
        this.nome_beneficios = nome_beneficios;
    }

    public String getRemove_selecionado() {
        return remove_selecionado;
    }

    public void setRemove_selecionado(String remove_selecionado) {
        this.remove_selecionado = remove_selecionado;
    }
    
    public String cadastrarTipoBeneficioConcedido(){
        if(nome_beneficio.equals("") || id_tipo_beneficio > 1){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            return "preencha os campos";
        }else{
              TipoBeneficioEntity tp= new TipoBeneficioEntity();
              tp.setId(id_tipo_beneficio);
              tp.setNome_beneficio(nome_beneficio);
              if(geb.cadastreTipoBeneficio(tp)){
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   bem.setNome_concedido_entity(tp.getNome_beneficio());
                   this.apagaObjeto();
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Salário!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
        } 
    return "beneficio?faces-redirect=true";
    }
    
    public void buscarTipoBeneficio(){
          if(nome_beneficio.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
       }else{
             TipoBeneficioEntity t = new TipoBeneficioEntity();
             t = geb.selecioneTipoBeneficioAtualiza(nome_beneficio);
            if(t.getNome_beneficio().equals("")){
               FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_tipo_beneficio = t.getId();
             nome_beneficio = t.getNome_beneficio();
             nome_beneficio_atualiza = nome_beneficio;
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
         }
    }
    
    public void excluirTipoBeneficio(){
           if(nome_beneficio.equals("") || id_tipo_beneficio <= 0){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "É preciso Buscar antes de Excluir");
              ctx.addMessage(null, msg);
      }else{
             TipoBeneficioEntity t = new TipoBeneficioEntity();
             t.setId(id_tipo_beneficio);
             t.setNome_beneficio(nome_beneficio_atualiza);
             if(geb.removeTipoBeneficio(t))
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
           else
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Nome já Removido"));
           }
           this.apagaObjeto();
    }
      
    private void apagaObjeto(){
             id_tipo_beneficio = 0;
             nome_beneficio = "";
             nome_beneficio_atualiza="";
    } 
    
    public void listaTipoBeneficio(AjaxBehaviorEvent event){
        nome_beneficio = remove_selecionado;
    }
}
