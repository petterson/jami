
package bean_gerenciveis;

import beans_sessao.GerenteSessionBean;
import entidades.BeneficioConcedidoEntity;
import entidades.SocialEntity;
import entidades.TipoBeneficioEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author petterson
 */
@Named(value = "listaBeneficiarioJSFManagedBean")
@ViewScoped
public class ListaBeneficiarioJSFManagedBean implements Serializable {

        @EJB
        GerenteSessionBean geb;
        String beneficiario_selecionado;
        List<String> nome_beneficiarios;
        List<BeneficioConcedidoEntity> list_beneficios;
        BeneficioConcedidoEntity b;
        Date data_inicial;
        Date data_final;
    
    public ListaBeneficiarioJSFManagedBean() {
        this.b = new BeneficioConcedidoEntity();
        this.list_beneficios = new ArrayList<>();
    }

    public String getBeneficiario_selecionado() {
        return beneficiario_selecionado;
    }

    public void setBeneficiario_selecionado(String beneficiario_selecionado) {
        this.beneficiario_selecionado = beneficiario_selecionado;
    }

    public List<String> getNome_beneficiarios() {
        return geb.selecioneSociaisComBeneficios();
    }

    public void setNome_beneficiarios(List<String> nome_beneficiarios) {
        this.nome_beneficiarios = nome_beneficiarios;
    }

    public List<BeneficioConcedidoEntity> getList_beneficios() {
        return list_beneficios;
    }

    public void setList_beneficios(List<BeneficioConcedidoEntity> list_beneficios) {
        this.list_beneficios = list_beneficios;
    }

    public BeneficioConcedidoEntity getB() {
        return b;
    }

    public void setB(BeneficioConcedidoEntity b) {
        this.b = b;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }
    
    public void buscarBeneficioConcedidos(){
         if(beneficiario_selecionado == null || data_inicial == null || data_final == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
      }else{
         if(beneficiario_selecionado.contains("Todas")){
             this.list_beneficios = geb.getTodasBeneficioConcedidos(data_inicial, data_final);
             if(list_beneficios.size() <= 0)
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("não recebeu Beneficios"));
         }else{
             this.list_beneficios = geb.getBeneficioConcedidos(beneficiario_selecionado, data_inicial, data_final);
             if(list_beneficios.size() <= 0)
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Esse não recebeu Beneficio"));
         }
       }
    }
    
    public void excluirBeneficioConcedido(){
          if(beneficiario_selecionado == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
      }else{
          if(b == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Não temlinha selecionada");
              ctx.addMessage(null, msg);
       }else{
              SocialEntity so = b.getBeneficiario();
              TipoBeneficioEntity t = b.getNome_beneficio();
              if(t.getBeneficios().size() > 1){
                 t.getBeneficios().remove(b);
                 geb.atualizaTipoBeneficio(t);
              }
              if(so.getBeneficios().size() > 1){
                 so.getBeneficios().remove(b);
                 geb.atualizaSocial(so);
              }
                 this.list_beneficios.remove(b);
                 if(geb.removeBeneficioConcedido(b)){
                    b = null;
                    FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Prencha os atributos"));
                }
             }
          }
     }
}
    
