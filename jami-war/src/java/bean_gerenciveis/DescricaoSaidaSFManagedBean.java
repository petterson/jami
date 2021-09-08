
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import entidades.DescricaoSaidaEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "descricaoSaidaSFManagedBean")
@SessionScoped
public class DescricaoSaidaSFManagedBean implements Serializable {

         @EJB 
         FinanceiroSessionBean fin;
         long id_descricao_saida;
         String nome_descricao_saida;
         String nome_descricao_saida_atualiza;
         @Inject 
         SaidaJSFManagedBean sam;
    
    public DescricaoSaidaSFManagedBean() {
    }

    public long getId_descricao_saida() {
        return id_descricao_saida;
    }

    public void setId_descricao_saida(long id_descricao_saida) {
        this.id_descricao_saida = id_descricao_saida;
    }

    public String getNome_descricao_saida() {
        return nome_descricao_saida;
    }

    public void setNome_descricao_saida(String nome_descricao_saida) {
        this.nome_descricao_saida = nome_descricao_saida;
    }

    public String getNome_descricao_saida_atualiza() {
        return nome_descricao_saida_atualiza;
    }

    public void setNome_descricao_saida_atualiza(String nome_descricao_saida_atualiza) {
        this.nome_descricao_saida_atualiza = nome_descricao_saida_atualiza;
    }
    
    public String cadastrarDescricaoSaida(){
        if(nome_descricao_saida.equals("") || id_descricao_saida > 1){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
            //return "preencha os campos";
        }else{
              DescricaoSaidaEntity de = new DescricaoSaidaEntity();
              de.setId(id_descricao_saida);
              de.setDescricao_saida(nome_descricao_saida);
              if(fin.cadastreDescricaoSaida(de)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
          }else{
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
                 sam.setNome_descricao_saida_entity(de.getDescricao_saida());
                 this.apagaObjeto();
              }
        } 
    return "saida?faces-redirect=true";
    }
    
    public void buscarDescricaoSaida(){
         if(nome_descricao_saida.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            DescricaoSaidaEntity d = new DescricaoSaidaEntity();
         d = fin.selecioneDescricaoSaidaAtualiza(nome_descricao_saida);
         if(d.getDescricao_saida().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_descricao_saida = d.getId();
             nome_descricao_saida = d.getDescricao_saida();
             nome_descricao_saida_atualiza = nome_descricao_saida;
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    }
    
    public void atualizarDescricaoSaida(){
         if(nome_descricao_saida.equals("") || id_descricao_saida < 1){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else{
              DescricaoSaidaEntity d = new DescricaoSaidaEntity();
              d.setId(id_descricao_saida);
              d.setDescricao_saida(nome_descricao_saida_atualiza);
             if(fin.atualizaDescricaoSaida(d)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
        }
    }
    
    public void excluirDescricaoSaida(){
         if(nome_descricao_saida.equals("") || id_descricao_saida < 10){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
             DescricaoSaidaEntity d = new DescricaoSaidaEntity();
             d.setId(id_descricao_saida);
             d.setDescricao_saida(nome_descricao_saida_atualiza);
             if(fin.removeDescricaoSaida(d)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
             }
           }
    }
    
    private void apagaObjeto(){
             id_descricao_saida= 0;
             nome_descricao_saida = "";
             nome_descricao_saida_atualiza = "";
    }
}
