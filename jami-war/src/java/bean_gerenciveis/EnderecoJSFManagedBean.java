
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.DistritoEntity;
import entidades.EnderecoEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Named(value = "enderecoJSFManagedBean")
@SessionScoped
public class EnderecoJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops;   
    @EJB
    FinanceiroSessionBean fin;
    long id_endereco;
    String logradouro;
    String cep;
    String distrito_endereco;
    ArrayList<String> list_nome_distritos;
    String nome_distrito_entity;
    HttpServletRequest request;
    //HttpSession newCurrentPage = ((HttpServletRequest) request).getSession();
    @Inject
    AlunoJSFManagedBean alm;
    @Inject
    SocialJSFManagedBean social;
    @Inject
    AlunoJSFManagedBean aluno;
   // @Inject
   // CartorioJSFManagedBean cam;
   // @Inject
    //EscolaJSFManagedBean esm;
    //@Inject
    //FuncionarioJSFManagedBean fum;
    
    public EnderecoJSFManagedBean() {
        this.list_nome_distritos = new ArrayList<>();
       // this.list_nome_ruas = new ArrayList<>();
    }

    public long getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(long id_endereco) {
        this.id_endereco = id_endereco;
    }
    
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getDistrito_endereco() {
        return distrito_endereco;
    }

    public void setDistrito_endereco(String distrito_endereco) {
        this.distrito_endereco = distrito_endereco;
    }

    public List<String> getList_nome_distritos() {
        return ops.selecioneDistritos();
    }

    public void setList_nome_distritos(ArrayList<String> list_nome_distritos) {
        this.list_nome_distritos = list_nome_distritos;
    }

    public String getNome_distrito_entity() {
        return nome_distrito_entity;
    }

    public void setNome_distrito_entity(String nome_distrito_entity) {
            this.list_nome_distritos.add(nome_distrito_entity);
        this.nome_distrito_entity = nome_distrito_entity;
    }

    public String cadastrarEndereco(){
        if(logradouro.equals("") || cep.equals("") || distrito_endereco == null){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
           ctx.addMessage(null, msg);
            return "preencha os campos";
        }else {
              DistritoEntity d = fin.selecioneDistritoPorNome(distrito_endereco);
              EnderecoEntity e = new EnderecoEntity();
              e.setId(id_endereco);
              e.setLogradouro(logradouro);
              e.setCep(cep);
              e.setDistrito_endereco(d);
              d.getList_endereco().add(e);
              if(ops.cadastreEndereco(e)){
                  ops.atualizaDistrito(d);
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
                 alm.setRua_entity(e.getLogradouro());
                 this.apagaObjeto();
              }else{
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Já Existe esse Nome!"));
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
              ctx.addMessage(null, msg);
            return "preencha os campos";
        }
        }
    return "/securio/operacional?faces-redirect=true";
    }
    
    public void buscarEndereco(){
         if(logradouro.equals("")){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
          }else{
            EnderecoEntity e = new EnderecoEntity();
         e = ops.selecioneEnderecoAtualiza(logradouro);
         if(e.getLogradouro().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
         }else{
             id_endereco = e.getId();
             logradouro = e.getLogradouro();
             cep = e.getCep();
             distrito_endereco = e.getDistrito_endereco().getNome_distrito();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    }
    
    public String atualizarEndereco(){
         if(logradouro.equals("") || id_endereco < 10 || cep.equals("") || distrito_endereco == null){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
            return "preencha os campos";
        }else {
               DistritoEntity d = fin.selecioneDistritoPorNome(distrito_endereco);
               EnderecoEntity e = new EnderecoEntity();
               e.setId(id_endereco);
               e.setLogradouro(logradouro);
               e.setCep(cep);
               e.setDistrito_endereco(d);
               if(ops.atualizaEndereco(e)){
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
               }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
                return "Não Atualizado!";
               }
          }
          return "operacional?faces-redirect=true";
    }
     
    public void excluirEndereco(){
         if(logradouro.equals("") || cep.equals("") || id_endereco < 1){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
        }else{
                 DistritoEntity d = fin.selecioneDistritoPorNome(distrito_endereco);
                 EnderecoEntity e = new EnderecoEntity();
                 e.setId(id_endereco);
                 e.setLogradouro(logradouro);
                 e.setCep(cep);
                 if(d.getList_endereco().size() > 1){
                     d.getList_endereco().remove(e);
                     ops.atualizaDistrito(d);
                     e.setDistrito_endereco(null);
                     ops.atualizaEndereco(e);
                    if(ops.removeEndereco(e))
                       FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                   else
                       FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
            }else{
                   if(ops.removeEndereco(e))
                       FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
                   else
                       FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
                 }
         }
           this.apagaObjeto();
    }
    
    private void apagaObjeto(){
             id_endereco = 0;
             logradouro = "";
             cep = "";
             distrito_endereco = "";
    }
}
