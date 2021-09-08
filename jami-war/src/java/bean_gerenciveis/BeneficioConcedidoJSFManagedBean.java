
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

/**
 *
 * @author petterson
 */
@Named(value = "beneficioConcedidoJSFManagedBean")
@SessionScoped
public class BeneficioConcedidoJSFManagedBean implements Serializable {

        @EJB
        GerenteSessionBean geb;
        long id_concedido;
        String nome_concedido;
        double valor_concedido;
        String nome_beneficiario;
        Date data_concedido;
        String nome_beneficiario_entity;
        List<String> nome_beneficiarios;
        String nome_concedido_entity;
        List<String> nome_concedidos;
    
    public BeneficioConcedidoJSFManagedBean() {
        this.nome_concedidos = new ArrayList<>();
        this.nome_beneficiarios = new ArrayList<>();
    }

    public long getId_concedido() {
        return id_concedido;
    }

    public void setId_concedido(long id_concedido) {
        this.id_concedido = id_concedido;
    }

    public String getNome_concedido() {
        return nome_concedido;
    }

    public void setNome_concedido(String nome_concedido) {
        this.nome_concedido = nome_concedido;
    }

    public double getValor_concedido() {
        return valor_concedido;
    }

    public void setValor_concedido(double valor_concedido) {
        this.valor_concedido = valor_concedido;
    }

    public String getNome_beneficiario() {
        return nome_beneficiario;
    }

    public void setNome_beneficiario(String nome_beneficiario) {
        this.nome_beneficiario = nome_beneficiario;
    }

    public Date getData_concedido() {
        return data_concedido;
    }

    public void setData_concedido(Date data_concedido) {
        this.data_concedido = data_concedido;
    }

    public String getNome_beneficiario_entity() {
        return nome_beneficiario_entity;
    }

    public void setNome_beneficiario_entity(String nome_beneficiario_entity) {
           this.nome_beneficiarios.add(nome_beneficiario_entity);
    this.nome_beneficiario_entity = nome_beneficiario_entity;
    }

    public List<String> getNome_beneficiarios() {
        return geb.selecioneSociais();
    }

    public void setNome_beneficiarios(ArrayList<String> nome_beneficiarios) {
        this.nome_beneficiarios = nome_beneficiarios;
    }

    public String getNome_concedido_entity() {
        return nome_concedido_entity;
    }

    public void setNome_concedido_entity(String nome_concedido_entity) {
            this.nome_concedidos.add(nome_concedido_entity);
    this.nome_concedido_entity = nome_concedido_entity;
    }

    public List<String> getNome_concedidos() {
        return geb.selecioneTipoNomeBeneficioConcedidos();
    }

    public void setNome_concedidos(ArrayList<String> nome_concedidos) {
        this.nome_concedidos = nome_concedidos;
    }
    
    public String cadastrarBeneficioConcedido(){
        if(nome_concedido == null || id_concedido > 1 || valor_concedido <= 0 || nome_beneficiario == null || data_concedido == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
            return "preencha os campos";
      }else{
              SocialEntity d = geb.selecioneSocialAtualiza(nome_beneficiario);
              TipoBeneficioEntity t = geb.selecioneTipoBeneficioAtualiza(nome_concedido);
              BeneficioConcedidoEntity da = new BeneficioConcedidoEntity();
              da.setId(id_concedido);
              da.setNome_beneficio(t);
              da.setValor_beneficio(valor_concedido);
              da.setBeneficiario(d);
              da.setData_beneficio(data_concedido);
              if(geb.cadastreBeneficioConcedido(da)){
                   d.getBeneficios().add(da);
                   geb.atualizaSocial(d);
                   t.getBeneficios().add(da);
                   geb.atualizaTipoBeneficio(t);
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                   this.apagaObjeto();
              }else{
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                   FacesContext ctx = FacesContext.getCurrentInstance();
                   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
                   ctx.addMessage(null, msg);
                   return "preencha os campos";
              }
        }     
    return "gerente?faces-redirect=true";
    }
    
    private void apagaObjeto(){
             id_concedido = 0;
             nome_concedido = "";
             valor_concedido = 0;
             nome_beneficiario ="";
             data_concedido = null;
    }
    
}
