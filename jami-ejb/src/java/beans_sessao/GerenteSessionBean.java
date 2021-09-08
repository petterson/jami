/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_sessao;

import entidades.AlunoEntity;
import entidades.ApoiadorEntity;
import entidades.AtividadeEntity;
import entidades.BeneficioConcedidoEntity;
import entidades.CargoEntity;
import entidades.DepartamentoEntity;
import entidades.DoacaoEntity;
import entidades.DoadorEntity;
import entidades.EnderecoEntity;
import entidades.FuncionarioEntity;
import entidades.InstituicaoEntity;
import entidades.ContribuinteEntity;
import entidades.InstituicaoContribuinteEntity;
import entidades.InstituicaoDoadoraEntity;
import entidades.InstituicaoParceiraEntity;
import entidades.PapelEntity;
import entidades.PessoaEntity;
import entidades.ProgramaEntity;
import entidades.ProjetoEntity;
import entidades.SalarioEntity;
import entidades.SocialEntity;
import entidades.TipoBeneficioEntity;
import entidades.TipoDoacaoEntity;
import entidades.TipoInstituicaoEntity;
import entidades.TurmaEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author petterson
 */
@Stateless
@LocalBean
public class GerenteSessionBean {

     @PersistenceContext(unitName="jami-ejbPU")
     EntityManager em ;
     
     
     
     
     
     
     ////////           SALARIO
     
     
     public boolean cadastreSalario(SalarioEntity salario){
          boolean ret=true;
          Query query = em.createQuery("Select s.valor_salario FROM SalarioEntity s");
          List<Double> valor_salario = query.getResultList();
          for(int j=0; j< valor_salario.size(); j++){
              double sa = valor_salario.get(j);
              if(sa == salario.getValor_salario())
                  return false;
              }
              if(ret == true)
                 em.persist(salario);
     return ret;
    }
     
     public List<Double> selecioneSalarios() {
            Query query = em.createQuery("Select s.valor_salario FROM SalarioEntity s");
            List<Double> ufs = query.getResultList();
     return ufs;
    }
    
     public boolean removeSalario(SalarioEntity salario){
         boolean ret=true;
         double s = salario.getValor_salario();
         SalarioEntity sal = new SalarioEntity();
         Query query = em.createQuery("Select s FROM SalarioEntity s WHERE s.valor_salario = :s");
         query.setParameter("s", s);
         try{
             sal =  (SalarioEntity) query.getSingleResult();
         }catch(NoResultException e){
                return false; 
         }
         try{
           em.remove(sal);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }     
     return ret;
     }
     
    public SalarioEntity selecioneSalarioAtualiza(double s) { 
            SalarioEntity sa = new SalarioEntity();
            Query query = em.createQuery("Select s FROM SalarioEntity s WHERE s.valor_salario = :s");
            query.setParameter("s", s);
            try{
            sa = (SalarioEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               sa.setValor_salario(0);
            }
            sa.getId();
            sa.getValor_salario();
     return sa;
    }
     
     
     
     
     
     
     
     
     
     
      ////////           CARGO
     
     
     public boolean cadastreCargo(CargoEntity cargo){
          boolean ret=true;
          Query query = em.createQuery("Select c.nome_cargo FROM CargoEntity c");
          List<String> nome_cargos = query.getResultList();
          for(int j=0; j< nome_cargos.size(); j++){
              String sa = nome_cargos.get(j);
              if(sa.equals(cargo.getNome_cargo()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(cargo);
     return ret;
    }
     
     public List<String> selecioneCargos() {
            Query query = em.createQuery("Select c.nome_cargo FROM CargoEntity c");
            List<String> ufs = query.getResultList();       
     return ufs;
    }
     
      public CargoEntity selecioneCargoAtualiza(String s) { 
            CargoEntity c = new CargoEntity();
            Query query = em.createQuery("Select c FROM CargoEntity c WHERE c.nome_cargo = :s");
            query.setParameter("s", s);
            try{
            c = (CargoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               c.setNome_cargo("");
            }
            c.getId();
            c.getNome_cargo();
            c.getValor_salario();
     return c;
    }
    
     public boolean removeCargo(CargoEntity c){
         boolean ret=true;
         CargoEntity ca = new CargoEntity();
         try{
           ca = em.find(CargoEntity.class, c.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(ca.getId() == null)
                 return false;
         }
         try{
           em.remove(ca);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }    
     return ret;
     }
     
     public boolean atualizaCargo(CargoEntity c){
          boolean ret=true;
         try{
           em.merge(c);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
     
     
     
     
     
     
     
     
     
     ////////////////////////       FUNCIONARIO

     
     public boolean cadastreFuncionario(FuncionarioEntity funcionario){
                    em.persist(funcionario);
     return true;
    }
     
     public FuncionarioEntity selecioneFuncionarioAtualiza(String s) {  
         FuncionarioEntity f = new FuncionarioEntity();
            Query query = em.createQuery("Select f FROM FuncionarioEntity f, f.pessoa p WHERE p.nome = :s");
            query.setParameter("s", s);
            try{
            f = (FuncionarioEntity) query.getSingleResult();
            }catch(NoResultException e){
                   f.setFone_funcionario("");
            }
            f.getId();
            f.getRg();
            f.getFone_funcionario();
            f.getPis_funcionario();
            f.getClt_funcionario();
            f.getRua_funcionario();
            f.getNumero_casa();
            f.getCargo_funcionario();
            f.getData_contratacao();
            f.getDepartamento();
            f.getPessoa();
     return f;
    }
     
     public List<String> selecioneFuncionarios() { 
            Query query = em.createQuery("Select p.nome FROM FuncionarioEntity f, f.pessoa p");
            List<String> ufs = query.getResultList();          
     return ufs;
    }
     
    public List<FuncionarioEntity> selecioneFuncionariosDepart() { 
            Query query = em.createQuery("Select f FROM FuncionarioEntity f, f.departamento d ORDER BY d.nome_departamento");
            List<FuncionarioEntity> ufs = query.getResultList();          
     return ufs;
    }
     
    public List<String> selecioneProfessores() {  
            String n= "Professor";
            Query query = em.createQuery("Select p.nome FROM FuncionarioEntity f, f.cargo_funcionario c, f.pessoa p WHERE c.nome_cargo = :n");
            query.setParameter("n", n);
            List<String> ufs = query.getResultList();          
     return ufs;
    }
     
    public List<FuncionarioEntity> selecioneListaFuncionario(List<String> funcionarios_selecionadas) {   
            Query query = em.createQuery("Select f FROM FuncionarioEntity f, f.pessoa p WHERE p.nome in :funcionarios_selecionadas");
            query.setParameter("funcionarios_selecionadas", funcionarios_selecionadas);
            List<FuncionarioEntity> ufs = query.getResultList();              
     return ufs;
    }
    
    public boolean removeFuncionario(FuncionarioEntity u){
         boolean ret=true;
         DepartamentoEntity d = u.getDepartamento();
         if(d.getFuncionarios().size() > 1){
            d.getFuncionarios().remove(u);
            u.setDepartamento(null);
            try{
                em.merge(u);
       }catch(java.lang.IllegalArgumentException e){System.out.println("erro d");return false;}
            try{
                em.merge(d);
        }catch(java.lang.IllegalArgumentException e){System.out.println("erro d");return false;}
                   FuncionarioEntity fr = new FuncionarioEntity();
            try{
                fr = em.find(FuncionarioEntity.class, u.getId());
       }catch(java.lang.IllegalArgumentException e){System.out.println("fr b");return false;}
            try{
                em.remove(fr);
       }catch(java.lang.IllegalArgumentException e){System.out.println("fr r");return false;}
         }else{
                DepartamentoEntity de = new DepartamentoEntity();
            try{
                de = em.find(DepartamentoEntity.class, d.getId());
       }catch(java.lang.IllegalArgumentException e){System.out.println("fr b");return false;}
            try{
                 em.remove(de);
         }catch(java.lang.IllegalArgumentException e){System.out.println("rem d");return false;}
       }
     return ret;
     }
     
     public boolean atualizaFuncionario(FuncionarioEntity f){
          boolean ret=true;
         try{
           em.merge(f);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
     
     
     
     
     
     
     
     
     
     
     ////////////////////     INSTITUICAO

     public boolean cadastreInstituicao(InstituicaoEntity instituicao){
         boolean ret=false;
         InstituicaoEntity i = new InstituicaoEntity();
         String cnpj = instituicao.getCnpj();
          Query query1 = em.createQuery("Select i FROM InstituicaoEntity i where i.cnpj = :cnpj");
          query1.setParameter("cnpj", cnpj);
          try{
            i = (InstituicaoEntity) query1.getSingleResult(); 
            }catch(NoResultException e){
                   i.setCnpj("");
            }
          
          /*Query query = em.createQuery("Select i.cnpj FROM InstituicaoEntity i");
          List<String> nome_instituicoes = query.getResultList();
          for(int j=0; j< nome_instituicoes.size(); j++){
              String sa = nome_instituicoes.get(j);
              if(sa.equals(instituicao.getCnpj()))
                  ret = false;
              }*/
              if(i.getCnpj().equals("")){
                 em.persist(instituicao);
                 ret=true;
              }
     return ret;
    }
     
    /*public List<String> selecioneNomeInstituicoesDoadoras() {    
            String e ="EVENTOS";
            String r="RECEITAS FINANCEIRAS";
            Query query = em.createQuery("Select i.nome_instituicao FROM InstituicaoEntity i where i.tipoInstituicao.nome <> :e AND i.tipoInstituicao.nome <> :r");
            query.setParameter("e", e);
            query.setParameter("r", r);
            List<String> ufs = query.getResultList();             
     return ufs;
    }*/
     
     public List<String> selecioneInstituicoes() {            
            Query query = em.createQuery("Select n.nome_instituicao FROM InstituicaoParceiraEntity i, i.instituicao n");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
     
    public List<InstituicaoEntity> selecioneListaInstituicao(List<String> parceiros_selecionadas) {   
            Query query = em.createQuery("Select i FROM InstituicaoEntity i WHERE i.nome_instituicao in :parceiros_selecionadas");
            query.setParameter("parceiros_selecionadas", parceiros_selecionadas);
            List<InstituicaoEntity> ufs = query.getResultList();              
     return ufs;
    }
     
    public InstituicaoEntity selecioneInstituicaoAtualiza(String s) { 
            InstituicaoEntity i = new InstituicaoEntity();
            Query query = em.createQuery("Select i FROM InstituicaoEntity i WHERE i.cnpj = :s");
            query.setParameter("s", s);
            try{
            i = (InstituicaoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               i.setNome_instituicao("");
            }
            i.getId();
            i.getNome_instituicao();
            i.getCnpj();
            i.getFone_instiuicao();
            i.getFone_instiuicao();
            i.getTipoInstituicao();
            i.getData_parceria();
     return i;
    }
     
    public boolean atualizaInstituicao(InstituicaoEntity i){
          boolean ret=true;
         try{
           em.merge(i);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     } 
     
    public boolean removeInstituicao(InstituicaoEntity i){
         boolean ret=true;
         InstituicaoEntity in = new InstituicaoEntity();
         try{
           in = em.find(InstituicaoEntity.class, i.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(in.getId() == null)
                 return false;
         }
         try{
           em.remove(in);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
    }
    
    public String getTipoInstituicao(String nome){
        String u = "";
        Query query = em.createQuery("Select DISTINCT n.nome FROM InstituicaoEntity i, i.tipoInstituicao n WHERE i.nome_instituicao =:nome");
        query.setParameter("nome", nome);
        try{
                u =  (String) query.getSingleResult();
       }catch(NoResultException e){
              return "nao";
       }
    return u;
    }
    
    
    
    
    
    
    
    
    
    
    
    ////////////////////     INSTITUICAO PARCEIRAS

     public boolean cadastreInstituicaoParceira(InstituicaoParceiraEntity instituicao){
                 em.persist(instituicao);
     return true;
    }
     
     
     public List<String> selecioneInstituicoesParceiras() {            
            Query query = em.createQuery("Select r.nome_instituicao FROM InstituicaoParceiraEntity i, i.instituicao r");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
     
    public List<InstituicaoParceiraEntity> selecioneListaInstituicaoParceira(List<String> parceiros_selecionadas) {   
            Query query = em.createQuery("Select r FROM InstituicaoParceiraEntity i, i.instituicao r WHERE r.nome_instituicao in :parceiros_selecionadas");
            query.setParameter("parceiros_selecionadas", parceiros_selecionadas);
            List<InstituicaoParceiraEntity> ufs = query.getResultList();              
     return ufs;
    }
     
    public InstituicaoParceiraEntity selecioneInstituicaoAtualizaParceira(String s) { 
            Long um =1L;
            InstituicaoParceiraEntity i = new InstituicaoParceiraEntity();
            Query query = em.createQuery("Select i FROM InstituicaoParceiraEntity i, i.instituicao r WHERE r.cnpj = :s");
            query.setParameter("s", s);
            try{
            i = (InstituicaoParceiraEntity) query.getSingleResult(); 
            }catch(NoResultException e){
                i.setId(um);
            }
            i.getId();
            i.getInstituicao();
     return i;
    }
     
    public boolean atualizaInstituicaoParceira(InstituicaoParceiraEntity i){
          boolean ret=true;
         try{
           em.merge(i);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     } 
     
    public boolean removeInstituicaoParceira(InstituicaoParceiraEntity i){
         boolean ret=true;
         InstituicaoParceiraEntity in = new InstituicaoParceiraEntity();
         try{
           in = em.find(InstituicaoParceiraEntity.class, i.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(in.getId() == null)
                 return false;
         }
         try{
           em.remove(in);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
    }
    
    public String getTipoInstituicaoParceira(String nome){
        String u = "";
           Query query = em.createQuery("Select n.nome FROM InstituicaoParceiraEntity i, i.instituicao r, r.tipoInstituicao n WHERE n.nome =:nome");
           query.setParameter("nome", nome);
           try{
                u =  (String) query.getSingleResult();
       }catch(NoResultException e){
              return "nao";
       }
    return u;
    }
    
    
    

    
    
    
    
    
    
    
    
    
    
    ////////////////////     INSTITUICAO DOADORA

     public boolean cadastreInstituicaoDoadora(InstituicaoDoadoraEntity instituicao){
                 em.persist(instituicao);
     return true;
    }
     
     
     public List<String> selecioneInstituicoesDoadora() {            
            Query query = em.createQuery("Select r.nome_instituicao FROM InstituicaoDoadoraEntity i, i.instituicao r");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
     
    public List<InstituicaoDoadoraEntity> selecioneListaInstituicaoDoadora(List<String> doadoras_selecionadas) {   
            Query query = em.createQuery("Select r FROM InstituicaoDoadoraEntity i, i.instituicao r WHERE r.nome_instituicao in :doadoras_selecionadas");
            query.setParameter("doadoras_selecionadas", doadoras_selecionadas);
            List<InstituicaoDoadoraEntity> ufs = query.getResultList();              
     return ufs;
    }
     
    public InstituicaoDoadoraEntity selecioneInstituicaoAtualizaDoadora(String s) { 
            Long um =1L;
            InstituicaoDoadoraEntity i = new InstituicaoDoadoraEntity();
            Query query = em.createQuery("Select i FROM InstituicaoDoadoraEntity i, i.instituicao r WHERE r.cnpj = :s");
            query.setParameter("s", s);
            try{
            i = (InstituicaoDoadoraEntity) query.getSingleResult(); 
            }catch(NoResultException e){
                i.setId(um);
            }
            i.getId();
            i.getInstituicao();
     return i;
    }
     
    public boolean atualizaInstituicaoDoadora(InstituicaoDoadoraEntity i){
          boolean ret=true;
         try{
           em.merge(i);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     } 
     
    public boolean removeInstituicaoDoadora(InstituicaoDoadoraEntity i){
         boolean ret=true;
         InstituicaoDoadoraEntity in = new InstituicaoDoadoraEntity();
         try{
           in = em.find(InstituicaoDoadoraEntity.class, i.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(in.getId() == null)
                 return false;
         }
         try{
           em.remove(in);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
    }
    
    public String getTipoInstituicaoDoadora(String nome){
        String u = "";
           Query query = em.createQuery("Select n.nome FROM InstituicaoDoadoraEntity i, i.instituicao r, r.tipoInstituicao n WHERE n.nome =:nome");
           query.setParameter("nome", nome);
           try{
                u =  (String) query.getSingleResult();
       }catch(NoResultException e){
              return "nao";
       }
    return u;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    ////////////////////     INSTITUICAO CONTRIBUINTES

     public boolean cadastreInstituicaoContribuinte(InstituicaoContribuinteEntity instituicao){
                 em.persist(instituicao);
     return true;
    }
     
     
     public List<String> selecioneInstituicoesContribuinte() {            
            Query query = em.createQuery("Select r.nome_instituicao FROM InstituicaoContribuinteEntity i, i.instituicao r");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
     
    public List<InstituicaoContribuinteEntity> selecioneListaInstituicaoContribuinte(List<String> contribuintes_selecionadas) {   
            Query query = em.createQuery("Select r FROM InstituicaoContribuinteEntity i, i.instituicao r WHERE r.nome_instituicao in :contribuintes_selecionadas");
            query.setParameter("contribuintes_selecionadas", contribuintes_selecionadas);
            List<InstituicaoContribuinteEntity> ufs = query.getResultList();              
     return ufs;
    }
     
    public InstituicaoContribuinteEntity selecioneInstituicaoAtualizaContribuinte(String s) { 
            Long um =1L;
            InstituicaoContribuinteEntity i = new InstituicaoContribuinteEntity();
            Query query = em.createQuery("Select i FROM InstituicaoContribuinteEntity i, i.instituicao r WHERE r.cnpj = :s");
            query.setParameter("s", s);
            try{
            i = (InstituicaoContribuinteEntity) query.getSingleResult(); 
            }catch(NoResultException e){
                i.setId(um);
            }
            i.getId();
            i.getInstituicao();
     return i;
    }
     
    public boolean atualizaInstituicaoContribuinte(InstituicaoContribuinteEntity i){
          boolean ret=true;
         try{
           em.merge(i);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     } 
     
    public boolean removeInstituicaoContribuinte(InstituicaoContribuinteEntity i){
         boolean ret=true;
         InstituicaoContribuinteEntity in = new InstituicaoContribuinteEntity();
         try{
           in = em.find(InstituicaoContribuinteEntity.class, i.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(in.getId() == null)
                 return false;
         }
         try{
           em.remove(in);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
    }
    
    public String getTipoInstituicaoContribuinte(String nome){
        String u = "";
           Query query = em.createQuery("Select n.nome FROM InstituicaoContribuinteEntity i, i.instituicao r, r.tipoInstituicao n WHERE n.nome =:nome");
           query.setParameter("nome", nome);
           try{
                u =  (String) query.getSingleResult();
       }catch(NoResultException e){
              return "nao";
       }
    return u;
    }
    
    
    
    
    
    
    
    
    
    
    
    
     /////////////////////TIPO INSTITUICAO//////////////////////
    
    public boolean cadastreTipoInstituicao(TipoInstituicaoEntity tipoInstituicao){
        boolean ret=true;
        Query query = em.createQuery("Select t.nome FROM TipoInstituicaoEntity t");
          List<String> nome_instituicoes = query.getResultList();
          for(int j=0; j< nome_instituicoes.size(); j++){
              String s = nome_instituicoes.get(j);
              if(s.equals(tipoInstituicao.getNome()))
                  return false;
              }
              if(ret == true)
                 em.persist(tipoInstituicao);
     return ret;
    }
    
    public List<String> selecioneTipoInstituicao() {                
            Query query = em.createQuery("Select t.nome FROM TipoInstituicaoEntity t");
            List<String> ufs = query.getResultList();
     return ufs;
    }
     
    public TipoInstituicaoEntity selecioneTipoInstituicaoAtualiza(String s) { 
            TipoInstituicaoEntity t = new TipoInstituicaoEntity();
            Query query = em.createQuery("Select t FROM TipoInstituicaoEntity t WHERE t.nome = :s");
            query.setParameter("s", s);
            try{
            t = (TipoInstituicaoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               t.setNome("");
            }
            t.getId();
            t.getNome();
            t.getIntituicoes();
     return t;
    }
     
    public boolean removeTipoInstituicao(TipoInstituicaoEntity t){
         boolean ret=true;
         String s = t.getNome();
         TipoInstituicaoEntity ti = new TipoInstituicaoEntity();
         Query query = em.createQuery("Select t FROM TipoInstituicaoEntity t WHERE t.nome = :s");
         query.setParameter("s", s);
         try{
             ti =  (TipoInstituicaoEntity) query.getSingleResult();
         }catch(NoResultException e){
                return false; 
         }
         try{
           em.remove(ti);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }
         return ret;
    }
    
    public boolean atualizaTipoInstituicao(TipoInstituicaoEntity t){
          boolean ret=true;
         try{
           em.merge(t);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
    }
    
    
    
    
    
    
     
     
     
     
     
     
     //////////////////       DOADOR

     
   public boolean cadastreDoador(DoadorEntity doador){
                 em.persist(doador);
     return true;
    }
     
    public List<String> selecioneDoadores() {                
            Query query = em.createQuery("Select p.nome FROM DoadorEntity d, d.pessoa p");
            List<String> ufs = query.getResultList();
            Query query2 = em.createQuery("Select n.nome_instituicao FROM InstituicaoDoadoraEntity i, i.instituicao n");
            List<String> ufs2 = query2.getResultList();
            ufs.addAll(ufs2);
     return ufs;
    }
     
    public List<String> selecioneDoadoresComDoacoes() {                
            Query query = em.createQuery("Select DISTINCT d.nome_doador FROM DoacaoEntity d");
            List<String> ufs = query.getResultList();    
     return ufs;
    }
    
    public DoadorEntity selecioneDoadorAtualiza(String s) { 
            DoadorEntity d = new DoadorEntity();
            Query query = em.createQuery("Select o FROM DoadorEntity o, o.pessoa p WHERE p.nome = :s");
            query.setParameter("s", s);
            try{
            d = (DoadorEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               d.setFone_doador("");
            }
            d.getId();
            d.getPessoa();
            d.getFone_doador();
            d.getEmail_doador();
     return d;
    }
    
    public boolean removeDoador(DoadorEntity d){
         boolean ret=true;
         DoadorEntity dr = new DoadorEntity();
         try{
           dr = em.find(DoadorEntity.class, d.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(dr.getId() == null)
                 return false;
         }
         try{
           em.remove(dr);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
    
    public boolean atualizaDoador(DoadorEntity d){
          boolean ret=true;
         try{
           em.merge(d);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
    
    
    
    
    
    
    
    /////////////////////TIPO BENEFICIO//////////////////////
    
    public boolean cadastreTipoBeneficio(TipoBeneficioEntity tipobeneficio){
        boolean ret=true;
        Query query = em.createQuery("Select t.nome_beneficio FROM TipoBeneficioEntity t");
          List<String> nome_beneficios = query.getResultList();
          for(int j=0; j< nome_beneficios.size(); j++){
              String s = nome_beneficios.get(j);
              if(s.equals(tipobeneficio.getNome_beneficio()))
                  return false;
              }
              if(ret == true)
                 em.persist(tipobeneficio);
     return ret;
    }
    
    public List<String> selecioneTipoNomeBeneficioConcedidos() {                
            Query query = em.createQuery("Select t.nome_beneficio FROM TipoBeneficioEntity t");
            List<String> ufs = query.getResultList();
     return ufs;
    }
     
    public TipoBeneficioEntity selecioneTipoBeneficioAtualiza(String s) { 
            TipoBeneficioEntity t = new TipoBeneficioEntity();
            Query query = em.createQuery("Select t FROM TipoBeneficioEntity t WHERE t.nome_beneficio = :s");
            query.setParameter("s", s);
            try{
            t = (TipoBeneficioEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               t.setNome_beneficio("");
            }
            t.getId();
            t.getNome_beneficio();
     return t;
    }
     
    public boolean removeTipoBeneficio(TipoBeneficioEntity t){
         boolean ret=true;
         String s = t.getNome_beneficio();
         TipoBeneficioEntity ti = new TipoBeneficioEntity();
         Query query = em.createQuery("Select t FROM TipoBeneficioEntity t WHERE t.nome_beneficio = :s");
         query.setParameter("s", s);
         try{
             ti =  (TipoBeneficioEntity) query.getSingleResult();
         }catch(NoResultException e){
                return false; 
         }
         try{
           em.remove(ti);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }
         return ret;
    }
    
    public boolean atualizaTipoBeneficio(TipoBeneficioEntity t){
          boolean ret=true;
         try{
           em.merge(t);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
    }
    
    
    
    
    
    
     
     
     
     
     
     
     
      //////////////////       TIPO DOAÇAO
     
     
     
   public boolean cadastreTipoDoacao(TipoDoacaoEntity tipodoacao){
        boolean ret=true;
        Query query = em.createQuery("Select t.nome_doacao FROM TipoDoacaoEntity t");
          List<String> nome_doacoes = query.getResultList();
          for(int j=0; j< nome_doacoes.size(); j++){
              String s = nome_doacoes.get(j);
              if(s.equals(tipodoacao.getNome_doacao()))
                  return false;
              }
              if(ret == true)
                 em.persist(tipodoacao);
     return ret;

}
     public List<String> selecioneTipoDoacoes() {                
            Query query = em.createQuery("Select t.nome_doacao FROM TipoDoacaoEntity t");
            List<String> ufs = query.getResultList();
     return ufs;
    }
     
    public TipoDoacaoEntity selecioneTipoDoacaoAtualiza(String s) { 
            TipoDoacaoEntity t = new TipoDoacaoEntity();
            Query query = em.createQuery("Select t FROM TipoDoacaoEntity t WHERE t.nome_doacao = :s");
            query.setParameter("s", s);
            try{
            t = (TipoDoacaoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               t.setNome_doacao("");
            }
            t.getId();
            t.getNome_doacao();
     return t;
    }
     
    public boolean removeTipoDoacao(TipoDoacaoEntity t){
         boolean ret=true;
         String s = t.getNome_doacao();
         TipoDoacaoEntity ti = new TipoDoacaoEntity();
         Query query = em.createQuery("Select t FROM TipoDoacaoEntity t WHERE t.nome_doacao = :s");
         query.setParameter("s", s);
         try{
             ti =  (TipoDoacaoEntity) query.getSingleResult();
         }catch(NoResultException e){
                return false; 
         }
         try{
           em.remove(ti);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }
         return ret;
    }
    
    public boolean atualizaTipoDoacao(TipoDoacaoEntity t){
          boolean ret=true;
         try{
           em.merge(t);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
     
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    /////////////////////BeneficioConcedido//////////////////////
    
    
    public boolean cadastreBeneficioConcedido(BeneficioConcedidoEntity beneficio){
        boolean ret=true;
                 em.persist(beneficio);
     return ret;
    }
    
    public List<String> selecioneBeneficioConcedidos() {               
            Query query = em.createQuery("Select b.nome_beneficio FROM BeneficioConcedidoEntity b");
            List<String> ufs = query.getResultList();              
     return ufs;
    }
     
    public List<BeneficioConcedidoEntity> getBeneficioConcedidos(String s, Date ini, Date fin){
          Query query = em.createQuery("Select b FROM BeneficioConcedidoEntity b, b.beneficiario e WHERE e.pessoa.nome = :s AND b.data_beneficio BETWEEN :ini AND :fin");
          query.setParameter("s", s);
          query.setParameter("ini",ini , TemporalType.DATE);
           query.setParameter("fin",fin , TemporalType.DATE);
          List<BeneficioConcedidoEntity> d = (List<BeneficioConcedidoEntity>) query.getResultList(); 
     return d;
    }
    
    public List<BeneficioConcedidoEntity> getTodasBeneficioConcedidos(Date ini, Date fin){
          Query query = em.createQuery("Select b FROM BeneficioConcedidoEntity b WHERE b.data_beneficio BETWEEN :ini AND :fin");
          query.setParameter("ini",ini , TemporalType.DATE);
           query.setParameter("fin",fin , TemporalType.DATE);
          List<BeneficioConcedidoEntity> d = query.getResultList();
     return d;
    }
   
    public BeneficioConcedidoEntity selecioneBeneficioConcedidoAtualiza(String s) { 
            BeneficioConcedidoEntity d = new BeneficioConcedidoEntity();
            Query query = em.createQuery("Select b FROM BeneficioConcedidoEntity b WHERE b.nome_beneficio = :s");
            query.setParameter("s", s);
            try{
            d = (BeneficioConcedidoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               d.getNome_beneficio().setNome_beneficio("");
            }
            d.getId();
            d.getNome_beneficio();
            d.getBeneficiario();
            d.getData_beneficio();
            d.getValor_beneficio();
     return d;
    }
    
    public boolean removeBeneficioConcedido(BeneficioConcedidoEntity b){
         boolean ret=true;
         BeneficioConcedidoEntity da = new BeneficioConcedidoEntity();
         try{
           da = em.find(BeneficioConcedidoEntity.class, b.getId());
         }catch(java.lang.IllegalArgumentException e){
                 return false;
         }
         try{
           em.remove(da);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
     
    public boolean atualizaBeneficioConcedido(BeneficioConcedidoEntity b){
          boolean ret=true;
         try{
           em.merge(b);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
     
     
     
     
     
     //////////////////        DOAÇAO
     

   public boolean cadastreDoacao(DoacaoEntity doacao){
        boolean ret=true;
                 em.persist(doacao);
     return ret;
    }
     
    public List<String> selecioneDoacoes() {               
            Query query = em.createQuery("Select d.nome_doacao FROM DoacaoEntity d");
            List<String> ufs = query.getResultList();              
     return ufs;
    }
     
    public List<DoacaoEntity> getDoacoes(String s, Date ini, Date fin){
          Query query = em.createQuery("Select d FROM DoacaoEntity d WHERE d.nome_doador = :s AND d.data_doacao BETWEEN :ini AND :fin");
          query.setParameter("s", s);
          query.setParameter("ini",ini , TemporalType.DATE);
           query.setParameter("fin",fin , TemporalType.DATE);
          List<DoacaoEntity> d = (List<DoacaoEntity>) query.getResultList(); 
     return d;
    }
    
    public List<DoacaoEntity> getTodasDoacoes( Date ini, Date fin){
          Query query = em.createQuery("Select d FROM DoacaoEntity d WHERE d.data_doacao BETWEEN :ini AND :fin");
          query.setParameter("ini",ini , TemporalType.DATE);
           query.setParameter("fin",fin , TemporalType.DATE);
          List<DoacaoEntity> d = query.getResultList();
     return d;
    }
   
    public DoacaoEntity selecioneDoacaoAtualiza(String s) { 
            DoacaoEntity d = new DoacaoEntity();
            Query query = em.createQuery("Select d FROM DoacaoEntity d WHERE d.nome_doacao = :s");
            query.setParameter("s", s);
            try{
            d = (DoacaoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               d.getNome_doacao().setNome_doacao("");
            }
            d.getId();
            d.getNome_doacao();
            d.getNome_doador();
            d.getData_doacao();
            d.getValor_doacao();
     return d;
    }
    
    public boolean removeDoacao(DoacaoEntity d){
         boolean ret=true;
         DoacaoEntity da = new DoacaoEntity();
         try{
           da = em.find(DoacaoEntity.class, d.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(da.getId() == null)
                 return false;
         }
         try{
           em.remove(da);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
     
    public boolean atualizaDoacao(DoacaoEntity d){
          boolean ret=true;
         try{
           em.merge(d);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
     
     
     
     
     
     
     
     
     //////////////////        PARCEIRO   
     
   public boolean cadastreParceiro(ContribuinteEntity parceiro){
       boolean ret=true;
                em.persist(parceiro);
     return ret;
    }
     
     public List<String> selecioneParceiros() {     
            Query query = em.createQuery("Select p.nome FROM ContribuinteEntity c, c.pessoa p");
            List<String> ufs = query.getResultList();           
     return ufs;
    }
     
    public List<ContribuinteEntity> selecioneListaParceiro(List<String> parceiros_selecionadas) {   
            Query query = em.createQuery("Select p FROM ContribuinteEntity c, c.pessoa p WHERE p.nome in :parceiros_selecionadas");
            query.setParameter("parceiros_selecionadas", parceiros_selecionadas);
            List<ContribuinteEntity> ufs = query.getResultList();              
     return ufs;
    }
     
    public ContribuinteEntity selecioneParceiroAtualiza(String s) {  
            ContribuinteEntity pa = new ContribuinteEntity();
            Query query = em.createQuery("Select e FROM ContribuinteEntity e, e.pessoa p WHERE p.nome = :s");
            query.setParameter("s", s);
            try{
            pa = (ContribuinteEntity) query.getSingleResult(); 
            }catch(NoResultException e){
                  pa.setFone("");
            }
             pa.getId();
             pa.getPessoa();
             pa.getFone();
             pa.getEmail();         
     return pa;
    }
    
     public boolean removeParceiro(ContribuinteEntity p){
         boolean ret=true;
         ContribuinteEntity pa = new ContribuinteEntity();
         try{
          pa = em.find(ContribuinteEntity.class, p.getId());      
         }catch(java.lang.IllegalArgumentException e){
               if(pa.getId() == null)
                 return false;
         }
         try{
             em.remove(pa);
         }catch(EJBException e){
              e.getMessage();
       }     
     return ret;
     }
     
   
     
     
     
     
     
     
     //////////////////        APOIADOR
     
     
     
   public boolean cadastreApoiador(ApoiadorEntity apoiador){
                 em.persist(apoiador);
     return true;
    }
     
     public List<String> selecioneApoiadores() {              
            Query query = em.createQuery("Select p.nome FROM ApoiadorEntity a, a.pessoa p");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
     
    public ApoiadorEntity selecioneApoiadorAtualiza(String s) { 
            ApoiadorEntity a = new ApoiadorEntity();
            Query query = em.createQuery("Select a FROM ApoiadorEntity a, a.pessoa p WHERE p.nome = :s");
            query.setParameter("s", s);
            try{
            a = (ApoiadorEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               a.setFone_apoiador("");
            }
            a.getId();
            a.getFone_apoiador();
            a.getEmail_apoiador();
            a.getPessoa();
     return a;
    }
    
    public boolean removeApoiador(ApoiadorEntity a){
         boolean ret=true;
         ApoiadorEntity ap = new ApoiadorEntity();
         try{
           ap = em.find(ApoiadorEntity.class, a.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(ap.getId() == null)
                 return false;
         } 
         try{
           em.remove(ap);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
    
    public boolean atualizaApoiador(ApoiadorEntity a){
          boolean ret=true;
         try{
           em.merge(a);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
     
     
     
     
     
     //////////////////        DEPARTAMENTO
     
     
     
   public boolean cadastreDepartamento(DepartamentoEntity departamento){
        boolean ret=true;
        Query query = em.createQuery("Select d.nome_departamento FROM DepartamentoEntity d");
        //try{
         List<String> nome_departamentos = query.getResultList();
        //}catch(){
        //}
         for(int j=0; j< nome_departamentos.size(); j++){
              String sa = nome_departamentos.get(j);
              if(sa.equals(departamento.getNome_departamento()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(departamento);
     return ret;
    }
     
     public List<String> selecioneDepartamentos() {          
            Query query = em.createQuery("Select d.nome_departamento FROM DepartamentoEntity d");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
     
    public DepartamentoEntity selecioneDepartamentoAtualiza(String s) { 
            DepartamentoEntity d = new DepartamentoEntity();
            Query query = em.createQuery("Select d FROM DepartamentoEntity d WHERE d.nome_departamento = :s");
            query.setParameter("s", s);
            try{
            d = (DepartamentoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               d.setNome_departamento("");
            }
            d.getId();
            d.getNome_departamento();
            d.getFuncionarios();
     return d;
    }
    
    public boolean removeDepartamento(DepartamentoEntity d){
         boolean ret=true;
         DepartamentoEntity pr = new DepartamentoEntity();
         try{
           pr = em.find(DepartamentoEntity.class, d.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(pr.getId() == null)
                 return false;
         }
         try{
           em.remove(pr);
         }catch(java.lang.IllegalArgumentException e){
              return false;
         }   
     return ret;
     }
     
    public boolean atualizaDepartamento(DepartamentoEntity d){
          boolean ret=true;
         try{
           em.merge(d);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
     
    
    
    
    
    ////////////////               TURMA
    
    public boolean cadastreTurma(TurmaEntity turma){
         boolean ret=true;
         Query query = em.createQuery("Select t.nome_turma FROM TurmaEntity t");
         List<String> nome_turmas = query.getResultList();
         for(int j=0; j< nome_turmas.size(); j++){
              String sa = nome_turmas.get(j);
              if(sa.equals(turma.getNome_turma()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(turma);
     return ret;
    }
    
    public List<String> selecioneTurmas() {              
            Query query = em.createQuery("Select t.nome_turma FROM TurmaEntity t");
            List<String> ufs = query.getResultList();              
     return ufs;
    }
    
     public List<String> selecioneTurmasComFrequencia() { 
            Query query = em.createQuery("Select DISTINCT f.nome_chamada  FROM FrequenciaEntity f");
            List<String> ufs = query.getResultList();
            
            Query query2 = em.createQuery("Select t.nome_turma FROM TurmaEntity t, t.chamada c where c.nome_chamada in :ufs");
            query2.setParameter("ufs", ufs);
            List<String> turmas = query2.getResultList();
     return turmas;
    }
    
    public List<String> selecioneTurmasPorAtividades(String nome_atividades) {         
            Query query = em.createQuery("Select a.nome_turma FROM TurmaEntity a WHERE a.atividade.nome_atividade = :nome_atividades");
            query.setParameter("nome_atividades", nome_atividades);
            List<String> ufs = query.getResultList();              
     return ufs;
    }
    
    public List<String> selecioneAlunosPorTurma(String nome_turma) {         
            Query query = em.createQuery("Select p.nome FROM TurmaEntity a JOIN a.alunos s, s.pessoa p WHERE a.nome_turma = :nome_turma");
            query.setParameter("nome_turma", nome_turma);
            List<String> ufs = query.getResultList();              
     return ufs;
    }
    
    public List<TurmaEntity> selecioneListaTurma(List<String> turmas_selecionadas) {   
            Query query = em.createQuery("Select a FROM TurmaEntity a WHERE a.nome_turma in :turmas_selecionadas");
            query.setParameter("turmas_selecionadas", turmas_selecionadas);
            List<TurmaEntity> ufs = query.getResultList();              
     return ufs;
    }
    
    public String selecioneChamada(String turma_selecionada){
           Query query = em.createQuery("Select a.chamada.nome_chamada FROM TurmaEntity a WHERE a.nome_turma = :turma_selecionada");
            query.setParameter("turma_selecionada", turma_selecionada);
           String c = (String) query.getSingleResult();
    return c;
    }
    
    public List<String> selecioneTurmasDesistente(String nome_aluno) { 
            Query query = em.createQuery("Select t.nome_turma FROM TurmaEntity t JOIN t.alunos a, a.pessoa p WHERE p.nome = :nome_aluno");
            query.setParameter("nome_aluno", nome_aluno);
            List<String> turmas = query.getResultList(); 
     return turmas;
    }
    
    public List<String> selecioneTurmasPorTurno(String turno,String nome_aluno) { 
            List<String> turmas = new ArrayList<>();
            Query query = em.createQuery("Select t FROM TurmaEntity t WHERE t.turno = :turno");
            query.setParameter("turno", turno);
            List<TurmaEntity> ufs = query.getResultList(); 
            for(int i=0; i< ufs.size(); i++){
                List<AlunoEntity> no_alunos = ufs.get(i).getAlunos();
                for(int j=0; j< no_alunos.size(); j++){
                    if(no_alunos.get(j).getPessoa().getNome().equals(nome_aluno))
                       turmas.add(ufs.get(i).getNome_turma());
                }
            }   
     return turmas;
    }
    
    public List<String> selecioneNomesAlunosTurmas(String s){
          Query query = em.createQuery("select p.nome from TurmaEntity t, t.alunos a, a.pessoa p, t.chamada c where c.nome_chamada = :s");
		 query.setParameter("s", s).getResultList();
                 List<String> ufs = query.getResultList();   
    return ufs;
    }
    
    public TurmaEntity selecioneTurmaAtualiza(String s) {  
            TurmaEntity t = new TurmaEntity();
            Query query = em.createQuery("Select t FROM TurmaEntity t WHERE t.nome_turma = :s");
            query.setParameter("s", s);
            try{
            t = (TurmaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
                if(t.getTurno()==null)
                    t.setTurno("");
                else
                  t.setNome_turma("");
            }
            t.getId_turma();
            t.getNome_turma();
            t.getNome_professor();
            t.getData_criacao();
            t.getAlunos();
            t.getChamada();  
            t.getTurno();
            t.getAtividade();
     return t;
    }
    
    public boolean atualizaTurma(TurmaEntity t){
          boolean ret=true;
         try{
          em.merge(t);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }     
     return ret;
    }
    
    public boolean removeTurma(TurmaEntity t){
         boolean ret=true;
         TurmaEntity tu = new TurmaEntity();
         try{
                 tu = em.find(TurmaEntity.class, t.getId_turma());
      }catch(java.lang.IllegalArgumentException e){
                 System.out.println("erro tu f");
                 return false;
         }
         try{
              em.remove(tu);
       }catch(java.lang.IllegalArgumentException e){
              System.out.println("erro tu r");
                 return false;
       }
     return ret;
   }
    
    
    
    
    
    
    ////////////////               ATIVIDADE
    
    public boolean cadastreAtividade(AtividadeEntity atividade){
         boolean ret=true;
          Query query = em.createQuery("Select a.nome_atividade FROM AtividadeEntity a");
          List<String> nome_atividades = query.getResultList();
          for(int j=0; j< nome_atividades.size(); j++){
              String sa = nome_atividades.get(j);
              if(sa.equals(atividade.getNome_atividade()))
                  ret = false; 
          } 
          if(ret == true)
             em.persist(atividade);
          
     return ret;
    }
    
    public AtividadeEntity selecioneAtividadeAtualiza(String s) { 
            AtividadeEntity a = new AtividadeEntity();
            Query query = em.createQuery("Select s FROM AtividadeEntity s WHERE s.nome_atividade = :s");
            query.setParameter("s", s);
            try{
            a = (AtividadeEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               a.setNome_atividade("");
            }
            a.getId();
            a.getNome_atividade();
            a.getNome_turmas();
            a.getData_criacao();
            a.getProjeto();
     return a;
    }
    
    public List<String> selecioneAtividades() {  
            Query query = em.createQuery("Select a.nome_atividade FROM AtividadeEntity a");
            List<String> ufs = query.getResultList();
     return ufs;
    }
    
    /*public List<String> selecioneTurmasPorAtividades(String nome_atividades) {         
            Query query = em.createQuery("Select a.nome_turmas FROM AtividadeEntity a JOIN a.nome_turmas t WHERE t.nome_turma in :nome_atividades");
            query.setParameter("nome_atividades", nome_atividades);
            List<String> ufs = query.getResultList();              
     return ufs;
    }*/
    
     public List<AtividadeEntity> selecioneListaAtividade(List<String> ativis_selecionadas) {   
            Query query = em.createQuery("Select a FROM AtividadeEntity a WHERE a.nome_atividade in :ativis_selecionadas");
            query.setParameter("ativis_selecionadas", ativis_selecionadas);
            List<AtividadeEntity> ufs = query.getResultList();              
     return ufs;
    }
    
    /*public List<String> selecioneAtividadesDesistente(String s) {         
            List<String> atividades = new ArrayList<>();
            Query query = em.createQuery("Select a FROM AtividadeEntity a");
            List<AtividadeEntity> ufs = query.getResultList(); 
            for(int i=0; i< ufs.size(); i++){
                List<TurmaEntity> no_turmas = ufs.get(i).getNome_turmas();
                for(int j=0; j< no_turmas.size(); j++){
                    if(no_turmas.get(j).getNome_turma().equals(s))
                       atividades.add(ufs.get(i).getNome_atividade());
                }
            } 
     return atividades;
    }*/
     
    public List<String> selecioneAtividadesDesistente(String s) {         
            Query query = em.createQuery("Select a.nome_atividade FROM AtividadeEntity a JOIN a.nome_turmas t WHERE t.nome_turma = :s");
            query.setParameter("s", s);
            List<String> atividades = query.getResultList(); 
     return atividades;
    }
     
    public boolean verificaTurmaCadastrado(List<String> nome_turmas){
          if(nome_turmas.size() > 0){
          Query query = em.createQuery("Select a FROM AtividadeEntity a");
          List<AtividadeEntity> a = query.getResultList();
          for(int i=0; i<a.size(); i++){
              List<TurmaEntity> tu = a.get(i).getNome_turmas();
              for(int j=0; j<tu.size(); j++){
                  for(int x=0; x<nome_turmas.size(); x++){
                      if(tu.get(j).getNome_turma().equals(nome_turmas.get(x)))
                          return false;
                  }
              }
          }
          }else{
                return true;
          }
    return true;      
    }
    
    public boolean removeAtividade(AtividadeEntity a){
         boolean ret=true;
         AtividadeEntity p = new AtividadeEntity();
         try{       
             p = em.find(AtividadeEntity.class, a.getId());
         }catch(java.lang.IllegalArgumentException e){
               if(p.getId() == null)
                 return false;
         }
         try{
           em.remove(p);
       }catch(java.lang.IllegalArgumentException e){
               if(p.getId() == null)
                 return false;
       }     
     return ret;
     }
     
     public boolean atualizaAtividade(AtividadeEntity a){
          boolean ret=true;
         try{
           em.merge(a);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }     
     return ret;
     }

    
    
    
    
    
    
    
    
    
    //////////////////        PROJETO
     
     
   public boolean cadastreProjeto(ProjetoEntity projeto){
        boolean ret=true;
        Query query = em.createQuery("Select p.nome_projeto FROM ProjetoEntity p");
         List<String> nome_projetos = query.getResultList();
         for(int j=0; j< nome_projetos.size(); j++){
              String sa = nome_projetos.get(j);
              if(sa.equals(projeto.getNome_projeto()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(projeto);
     return ret;
    }
    
   public boolean verificaAtividadeCadastrada(List<String> nome_atividades){
          if(nome_atividades.size() > 0){
          Query query = em.createQuery("Select p FROM ProjetoEntity p");
          List<ProjetoEntity> ps = query.getResultList();
          for(int i=0; i<ps.size(); i++){
              List<AtividadeEntity> as = ps.get(i).getNome_atividades();
              for(int j=0; j<as.size(); j++){
                  for(int x=0; x<nome_atividades.size(); x++){
                      if(as.get(j).getNome_atividade().equals(nome_atividades.get(x)))
                          return false;
                  }
              }
          }
          }else{
                return true;  
         }
    return true;      
   }
     
     public List<String> selecioneProjetos() {             
            Query query = em.createQuery("Select p.nome_projeto FROM ProjetoEntity p");
            List<String> ufs = query.getResultList();           
     return ufs;
    }
     
    public List<ProjetoEntity> selecioneListaProjeto(List<String> projeto_selecionadas) {   
            Query query = em.createQuery("Select a FROM ProjetoEntity a WHERE a.nome_projeto in :projeto_selecionadas");
            query.setParameter("projeto_selecionadas", projeto_selecionadas);
            List<ProjetoEntity> ufs = query.getResultList();              
     return ufs;
    }
     
    public ProjetoEntity selecioneProjetoAtualiza(String s) { 
            ProjetoEntity p = new ProjetoEntity();
            Query query = em.createQuery("Select p FROM ProjetoEntity p WHERE p.nome_projeto = :s");
            query.setParameter("s", s);
            try{
            p = (ProjetoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               p.setNome_projeto("");
            }
            p.getId();
            p.getNome_projeto();
            p.getData_inicio();
            p.getNome_atividades();
            p.getInstituicoes();
            p.getCust_anual();
     return p;
    }
    
    public boolean removeProjeto(ProjetoEntity pro){
         boolean ret=true;
         ProjetoEntity pr = new ProjetoEntity();
         try{
           pr = em.find(ProjetoEntity.class, pro.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(pr.getId() == null)
                 return false;
         }
         try{
           em.remove(pr);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
     
    public boolean atualizaProjeto(ProjetoEntity p){
          boolean ret=true;
         try{
           em.merge(p);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
     
     
     
     
     
     
     
     
     
     
     
     //////////////////        PROGRAMA
     
     
     
   public boolean cadastrePrograma(ProgramaEntity programa){
        boolean ret=true;
        Query query = em.createQuery("Select p.nome_programa FROM ProgramaEntity p");
         List<String> nome_programas = query.getResultList();
         for(int j=0; j< nome_programas.size(); j++){
              String sa = nome_programas.get(j);
              if(sa.equals(programa.getNome_programa()))
                  return false;
              }
              if(ret == true)
                 em.persist(programa);
       
     em.persist(programa);
     return ret;
    }
     
     public List<String> selecioneProgramas() {              
            Query query = em.createQuery("Select p.nome_programa FROM ProgramaEntity p");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
     
      public ProgramaEntity selecioneProgramaAtualiza(String s) { 
            ProgramaEntity p = new ProgramaEntity();
            Query query = em.createQuery("Select p FROM ProgramaEntity p WHERE p.nome_programa = :s");
            query.setParameter("s", s);
            try{
            p = (ProgramaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               p.setNome_programa("");
            }
            p.getId();
            p.getNome_programa();
            p.getData_criacao();
            p.getNome_projetos();
            p.getDescricao_programa();
     return p;
    }
    
     public boolean removePrograma(ProgramaEntity po){
         boolean ret=true;
         ProgramaEntity pr = new ProgramaEntity();
         try{
           pr = em.find(ProgramaEntity.class, po.getId());
         }catch(java.lang.IllegalArgumentException e){
                if(pr.getId() == null)
                 return false;
         }
         try{
           em.remove(pr);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       } 
     return ret;
     }
     
    public boolean verificaProjetoCadastrado(List<String> nome_projetos){
          if(nome_projetos.size() > 0){
          Query query = em.createQuery("Select p FROM ProgramaEntity p");
          List<ProgramaEntity> ps = query.getResultList();
          for(int i=0; i<ps.size(); i++){
              List<ProjetoEntity> as = ps.get(i).getNome_projetos();
              for(int j=0; j<as.size(); j++){
                  for(int x=0; x<nome_projetos.size(); x++){
                      if(as.get(j).getNome_projeto().equals(nome_projetos.get(x)))
                          return false;
                  }
              }
          }
          }else{
                return true;
          }
    return true;      
    }
     
     public boolean atualizaPrograma(ProgramaEntity p){
          boolean ret=true;
         try{
           em.merge(p);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }     
     return ret;
     }
     
     
     
     
     
     
     
     
     
      //////////////////        DADASTRO SOCIAL
     
     
     
   public boolean cadastreSocial(SocialEntity social){
                 em.persist(social);            
     return true;
    }
     
    public List<String> selecioneSociais() {          
            Query query = em.createQuery("Select p.nome FROM SocialEntity s, s.pessoa p");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
    
    public List<String> selecioneSociaisComBeneficios() {          
            Query query = em.createQuery("Select DISTINCT p.nome FROM BeneficioConcedidoEntity b, b.beneficiario e, e.pessoa p");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
     
    public SocialEntity selecioneSocialAtualiza(String s) { 
            SocialEntity i = new SocialEntity();
            Query query = em.createQuery("Select o FROM SocialEntity o, o.pessoa p WHERE p.nome = :s");
            query.setParameter("s", s);
            try{
            i = (SocialEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               i.setFone_social("");
            }
            i.getId();
            i.getPessoa();
            i.getRg_social();
            i.getFone_social();
            i.getProfissao_social();
            i.getSitucao_social();
            i.getRua_social();
            i.getNumero_casa();
            i.getComplemento_rua();
            i.getAuxilio_governo();
            i.getGestante();
            i.getObs_gestante();
            i.getAcamado();
            i.getObs_acamado();
            i.getDeficiente();
            i.getObs_deficiente();
            i.getNro_adultos();
            i.getNro_joves();
            i.getNro_adolecentes();
            i.getNro_criancas();
            i.getNro_bebes();
            i.getGrau_instrucao();
            i.getRenda_mensal_familiar();
            i.getData_cadastro();
     return i;
    }
     
    public boolean removeSocial(SocialEntity s){
         boolean ret=true;
         SocialEntity so = new SocialEntity();
         try{
           so = em.find(SocialEntity.class, s.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(so.getId() == null)
                 return false;
         }
         try{
           em.remove(so);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
     
     public boolean atualizaSocial(SocialEntity s){
          boolean ret=true;
         try{
           em.merge(s);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
     











   //////////////////        CADASTRO PAPEL
     
   public boolean cadastrePapel(PapelEntity papel){
                 em.persist(papel); 
     return true;
    }
   
    public PapelEntity selecionePapelAtualiza(String s) { 
            PapelEntity p = new PapelEntity();
            Query query = em.createQuery("Select p FROM PapelEntity p, p.pessoa a WHERE a.nome = :s");
            query.setParameter("s", s);
            try{
            p = (PapelEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               p.setLogin("");
            }
            p.getId();
            p.getPessoa();
            p.getLogin();
            p.getSenha();
            p.getPapel();
     return p;
    }
    
     public boolean removePapel(PapelEntity po){
         boolean ret=true;
         PapelEntity pr = new PapelEntity();
         try{
           pr = em.find(PapelEntity.class, po.getId());
         }catch(java.lang.IllegalArgumentException e){
                if(pr.getId() == null)
                 return false;
         }
         try{
           em.remove(pr);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       } 
     return ret;
     }
     
     
     public boolean atualizaPapel(PapelEntity p){
          boolean ret=true;
         try{
           em.merge(p);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }     
     return ret;
     }
     
     public String verificarLogin(String login, String senha){
            String papel="";
            Query query = em.createQuery("Select p.papel FROM PapelEntity p WHERE p.login = :login AND p.senha =:senha");
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            try{
            papel = (String) query.getSingleResult(); 
            }catch(NoResultException e){
               return papel;
            }
     return papel;
     }
     
     public List<PapelEntity> getPapel(){
            List<PapelEntity> papel = new ArrayList<>();
            Query query = em.createQuery("Select p FROM PapelEntity p");
            try{
            papel = query.getResultList();
            }catch(NoResultException e){
               return papel;
            }
       return papel;
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     //////////////////        CADASTRO PAPEL
     
   public boolean cadastrePessoa(PessoaEntity pessoa){
        boolean ret=true;
        PessoaEntity p = new PessoaEntity();
        Query query = em.createQuery("Select p.cpf FROM PessoaEntity p");
         List<String> cpfs = query.getResultList();
         for(int j=0; j< cpfs.size(); j++){
              if(cpfs.get(j).equals(pessoa.getCpf())){
                  ret = false;
                  break;
              }
         }
            if(ret == true)
               em.persist(pessoa);
     return ret;
    }
   
    public PessoaEntity selecionePessoaAtualiza(String s) { 
            PessoaEntity p = new PessoaEntity();
            Query query = em.createQuery("Select p FROM PessoaEntity p WHERE p.nome = :s");
            query.setParameter("s", s);
            try{
            p = (PessoaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               p.setNome("");
            }
            p.getId();
            p.getNome();
            p.getCpf();
            p.getData_nasc();
     return p;
    }
    
    public PessoaEntity selecionePessoaCPFAtualiza(String cpf) { 
            PessoaEntity p = new PessoaEntity();
            Query query = em.createQuery("Select p FROM PessoaEntity p WHERE p.cpf = :cpf");
            query.setParameter("cpf", cpf);
            try{
            p = (PessoaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               p.setNome("");
            }
            p.getId();
            p.getNome();
            p.getCpf();
            p.getData_nasc();
     return p;
    }
    
     public boolean removePessoa(PessoaEntity po){
         boolean ret=true;
         PessoaEntity pr = new PessoaEntity();
         try{
           pr = em.find(PessoaEntity.class, po.getId());
         }catch(java.lang.IllegalArgumentException e){
                if(pr.getId() == null)
                 return false;
         }
         try{
           em.remove(pr);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       } 
     return ret;
     }
     
     
     public boolean atualizaPessoa(PessoaEntity p){
          boolean ret=true;
         try{
           em.merge(p);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }     
     return ret;
     }

}
