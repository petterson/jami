/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_sessao;

import entidades.AgenciaEntity;
import entidades.BalanceteEntity;
import entidades.BalancoEntity;
import entidades.ChequeEntity;
import entidades.CidadeEntity;
import entidades.ContaEntity;
import entidades.DescricaoEntity;
import entidades.DescricaoSaidaEntity;
import entidades.DistritoEntity;
import entidades.EnderecoEntity;
import entidades.EntradaEntity;
import entidades.FornecedorEntity;
import entidades.PrestadorFisicoEntity;
import entidades.PrestadoraServicoEntity;
import entidades.SaidaEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author petterson
 */
@Stateless
@LocalBean
public class FinanceiroSessionBean {

     @PersistenceContext(unitName="jami-ejbPU")
     EntityManager em ;
     
     
     
         
     
     
     
     
      ////////           PRESTADORA
     
     
    public boolean cadastrePrestadoraServico(PrestadoraServicoEntity prestadora){
                 em.persist(prestadora);
     return true;
    }
     
     public List<String> selecionePrestadoraServicos() {
            Query query = em.createQuery("Select i.nome_instituicao FROM PrestadoraServicoEntity p, p.instituicao i");
            List<String> ufs = query.getResultList();
     return ufs;
    }
     
    public List<PrestadoraServicoEntity> selecioneListaPrestadora(List<String> prestadoras_selecionadas) {   
            Query query = em.createQuery("Select r FROM PrestadoraServicoEntity p, p.instituicao r WHERE r.nome_instituicao in :prestadoras_selecionadas");
            query.setParameter("prestadoras_selecionadas", prestadoras_selecionadas);
            List<PrestadoraServicoEntity> ufs = query.getResultList();              
     return ufs;
    }
     
    public PrestadoraServicoEntity selecionePrestadoraAtualiza(String s) { 
            Long um =1L;
            PrestadoraServicoEntity p = new PrestadoraServicoEntity();
            Query query = em.createQuery("Select f FROM PrestadoraServicoEntity f, f.instituicao r WHERE r.cnpj = :s");
            query.setParameter("s", s);
            try{
            p = (PrestadoraServicoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
                p.setId(um);
            }
            p.getId();
            p.getInstituicao();
     return p;
    }
     
    public boolean atualizaInstituicaoPrestadora(PrestadoraServicoEntity f){
          boolean ret=true;
         try{
           em.merge(f);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     } 
     
    public boolean removeInstituicaoPrestadora(PrestadoraServicoEntity f){
         boolean ret=true;
         PrestadoraServicoEntity pr = new PrestadoraServicoEntity();
         try{
           pr = em.find(PrestadoraServicoEntity.class, f.getId());
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
    
    public String getTipoInstituicaoPrestadora(String nome){
        String u = "";
           Query query = em.createQuery("Select n.nome FROM PrestadoraServicoEntity p, p.instituicao r, r.tipoInstituicao n WHERE n.nome =:nome");
           query.setParameter("nome", nome);
           try{
                u =  (String) query.getSingleResult();
       }catch(NoResultException e){
              return "nao";
       }
    return u;
    }
     
    
    
     
     
     
     
     
     
     ////////           PRESTADOR FISICO
     
     
     public boolean cadastrePrestadorFisico(PrestadorFisicoEntity prestador){
                 em.persist(prestador);
     return true;
    }
     
     public List<String> selecionePrestadoraFisicos() {
            Query query = em.createQuery("Select a.nome FROM PrestadorFisicoEntity p, p.pessoa a");
            List<String> ufs = query.getResultList();       
     return ufs;
    }
     
    public PrestadorFisicoEntity selecionePrestadorFisicoAtualiza(String s) { 
            PrestadorFisicoEntity p = new PrestadorFisicoEntity();
            Query query = em.createQuery("Select p FROM PrestadorFisicoEntity p, p.pessoa a WHERE a.nome = :s");
            query.setParameter("s", s);
            try{
            p = (PrestadorFisicoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               p.setFone_fisico("");
            }
            p.getId();
            p.getPessoa();
            p.getFone_fisico();
            p.getEmail_fisico();
     return p;
    }
     
    public boolean removePrestadorFisico(PrestadorFisicoEntity p){
         boolean ret=true;
         PrestadorFisicoEntity pr = new PrestadorFisicoEntity();
         try{
           pr = em.find(PrestadorFisicoEntity.class, p.getId());
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
     
    public boolean atualizaPrestadorFisico(PrestadorFisicoEntity p){
          boolean ret=true;
         try{
           em.merge(p);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
    } 
    
     
     
     
     
     
     
     
     
     
     
     
     ////////           FORNECEDOR
     
     
     public boolean cadastreFornecedor(FornecedorEntity fornecedor){
                  em.persist(fornecedor);
     return true;
    }
     
    public List<String> selecioneFornecedor() {            
            Query query = em.createQuery("Select r.nome_instituicao FROM FornecedorEntity f, f.instituicao r");
            List<String> ufs = query.getResultList();             
     return ufs;
    }
     
    public List<FornecedorEntity> selecioneListaFornecedor(List<String> fornecedores_selecionadas) {   
            Query query = em.createQuery("Select r FROM FornecedorEntity f, f.instituicao r WHERE r.nome_instituicao in :fornecedores_selecionadas");
            query.setParameter("fornecedores_selecionadas", fornecedores_selecionadas);
            List<FornecedorEntity> ufs = query.getResultList();              
     return ufs;
    }
     
    public FornecedorEntity selecioneFornecedorAtualiza(String s) { 
            Long um =1L;
            FornecedorEntity i = new FornecedorEntity();
            Query query = em.createQuery("Select f FROM FornecedorEntity f, f.instituicao r WHERE r.cnpj = :s");
            query.setParameter("s", s);
            try{
            i = (FornecedorEntity) query.getSingleResult(); 
            }catch(NoResultException e){
                i.setId(um);
            }
            i.getId();
            i.getInstituicao();
     return i;
    }
     
    public boolean atualizaInstituicaoFornecedor(FornecedorEntity f){
          boolean ret=true;
         try{
           em.merge(f);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     } 
     
    public boolean removeInstituicaoFornecedor(FornecedorEntity f){
         boolean ret=true;
         FornecedorEntity fo = new FornecedorEntity();
         try{
           fo = em.find(FornecedorEntity.class, f.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(fo.getId() == null)
                 return false;
         }
         try{
           em.remove(fo);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
    }
    
    public String getTipoInstituicaoFornecedor(String nome){
        String u = "";
           Query query = em.createQuery("Select n.nome FROM FornecedorEntity f, f.instituicao r, r.tipoInstituicao n WHERE n.nome =:nome");
           query.setParameter("nome", nome);
           try{
                u =  (String) query.getSingleResult();
       }catch(NoResultException e){
              return "nao";
       }
    return u;
    }
     
     
     
     
     
     
     
     
     
     
     
     ////////           AGÊNCIA
     
     
     public boolean cadastreAgencia(AgenciaEntity agencia){
          boolean ret=true;
          Query query = em.createQuery("Select a.nome_agencia FROM AgenciaEntity a");
          List<String> nome_agencias = query.getResultList();
          for(int j=0; j< nome_agencias.size(); j++){
              String sa = nome_agencias.get(j);
              if(sa.equals(agencia.getNome_agencia()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(agencia);
     return ret;
    }
     
     public List<String> selecioneAgencias() {
            Query query = em.createQuery("Select a.nome_agencia FROM AgenciaEntity a");
            List<String> ufs = query.getResultList();
     return ufs;
    }
     
    public List<String> selecioneNumeroAgencias() {
            Query query = em.createQuery("Select a.numero_agencia FROM AgenciaEntity a");
            List<String> ufs = query.getResultList();
     return ufs;
    }
     
    public AgenciaEntity selecioneAgenciaAtualiza(String s) { 
            AgenciaEntity a = new AgenciaEntity();
            Query query = em.createQuery("Select a FROM AgenciaEntity a WHERE a.nome_agencia = :s");
            query.setParameter("s", s);
            try{
            a = (AgenciaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               a.setNome_agencia("");
            }
            a.getId();
            a.getNumero_agencia();
            a.getNome_agencia();
            a.getFone_agencia();
            a.getEmail_agencia();
            a.getRua_agencia();
            a.getNumero_rua_agencia();
     return a;
    }
    
    public AgenciaEntity selecioneAgenciaConta(String s) { 
            AgenciaEntity a = new AgenciaEntity();
            Query query = em.createQuery("Select a FROM AgenciaEntity a WHERE a.numero_agencia = :s");
            query.setParameter("s", s);
            try{
            a = (AgenciaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               a.setNome_agencia("");
            }
            a.getId();
            a.getNumero_agencia();
            a.getNome_agencia();
            a.getFone_agencia();
            a.getEmail_agencia();
            a.getRua_agencia();
            a.getNumero_rua_agencia();
     return a;
    }
     
     public boolean removeAgencia(AgenciaEntity a){
          boolean ret=true;
          AgenciaEntity ag = new AgenciaEntity();
         try{
           ag = em.find(AgenciaEntity.class, a.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(ag.getId() == null)
                 return false;
         }
         try{
           em.remove(ag);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     
     }
     
    public boolean atualizaAgencia(AgenciaEntity a){
          boolean ret=true;
         try{
           em.merge(a);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     ////////           CONTA
     
     
     public boolean cadastreConta(ContaEntity conta){
          boolean ret=true;
          Query query = em.createQuery("Select c.nome_conta FROM ContaEntity c");
          List<String> nome_contas = query.getResultList();
          for(int j=0; j< nome_contas.size(); j++){
              String sa = nome_contas.get(j);
              if(sa.equals(conta.getNome_conta()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(conta);
     return ret;
    }
     
     public List<String> selecioneContas() {
            Query query = em.createQuery("Select c.nome_conta FROM ContaEntity c");
            List<String> numero_contas = query.getResultList();
     return numero_contas;
    }
     
    public List<String> selecioneNumeroContas() {
            Query query = em.createQuery("Select c.numero_conta FROM ContaEntity c");
            List<String> numero_contas = query.getResultList();
     return numero_contas;
    }
    
    public String selecioneNomeConta(ContaEntity c) {
            String n = c.getNome_conta();
            String numero="";
            Query query = em.createQuery("Select c.nome_conta FROM ContaEntity c WHERE c.nome_conta =:n");
            query.setParameter("n", n);
            //String numero = (String) query.getSingleResult();
            try{
                numero = (String) query.getSingleResult();
            }catch(NoResultException e){
               c.setNome_conta("");
            }
     return numero;
    }
    
    public ContaEntity selecioneContaId(long id) { 
            ContaEntity c = new ContaEntity();
            Query query = em.createQuery("Select c FROM ContaEntity c WHERE c.id = :id");
            query.setParameter("id", id);
            try{
            c = (ContaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               c.setNome_conta("");
            }
            c.getId();
            c.getNumero_conta();
            c.getNome_conta();
            c.getAgencia();
            c.getTitular_conta();
            c.getData_criacao();
            c.getSaldo();
            c.getBalancetes();
     return c;
    }
    
    public ContaEntity selecioneContaAtualiza(String s) { 
            ContaEntity c = new ContaEntity();
            Query query = em.createQuery("Select c FROM ContaEntity c WHERE c.numero_conta = :s");
            query.setParameter("s", s);
            try{
            c = (ContaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               c.setNome_conta("");
            }
            c.getId();
            c.getNumero_conta();
            c.getNome_conta();
            c.getAgencia();
            c.getTitular_conta();
            c.getData_criacao();
            c.getSaldo();
            c.getBalancetes();
     return c;
    }
     
     public boolean removeConta(ContaEntity c){
          boolean ret=true;
          ContaEntity co = new ContaEntity();
         try{
           co = em.find(ContaEntity.class,c.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(co.getId() == null)
                 return false;
         }
         try{
           em.remove(co);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       } 
     return ret;
     
     }
     
    public boolean atualizaConta(ContaEntity c){
          boolean ret=true;
         try{
           em.merge(c);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    public boolean atualizaContaValor(ContaEntity c){
          boolean ret=true;
          ContaEntity co = new ContaEntity();
          String n =c.getNumero_conta();
          long l=0;
          Query quer = em.createQuery("Select c.id FROM ContaEntity c WHERE c.numero_conta = :n");
          quer.setParameter("n", n);
          try{
            l =  (long) quer.getSingleResult(); 
            }catch(NoResultException e){
                return false;
            }
          try{
            co = em.find(ContaEntity.class, l);
            co.getId();
            co.setNumero_conta(c.getNumero_conta());
            co.getNome_conta();
            co.getAgencia();
            co.getTitular_conta();
            co.getData_criacao();
            co.setSaldo(c.getSaldo());
            co.getBalancetes();
            }catch(NoResultException e){
                   return false;
            }
         try{
           em.merge(co);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
      }
     
    
    
    
    
     
     
     
     
     
     
     ////////           BALANCETE
     
     
     public boolean cadastreBalancete(BalanceteEntity balancete){
          boolean ret=true;
          Query query = em.createQuery("Select b.descricao_balancete FROM BalanceteEntity b");
          List<String> nome_balancetes = query.getResultList();
          for(int j=0; j< nome_balancetes.size(); j++){
              String sa = nome_balancetes.get(j);
              if(sa.equals(balancete.getDescricao_balancete()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(balancete);
     return ret;
    }
     
     public List<String> selecioneNomeBalancetes() {
            Query query = em.createQuery("Select b.descricao_balancete FROM BalanceteEntity b");
             List<String> nome_balancetes = query.getResultList();
     return nome_balancetes;
    }
     
      public List<BalanceteEntity> selecioneBalancetes() {
            Query query = em.createQuery("Select b FROM BalanceteEntity b");
             List<BalanceteEntity> nome_balancetes = query.getResultList();
     return nome_balancetes;
    }
      
    public BalanceteEntity selecioneBalanceteAtualiza(String s) { 
            BalanceteEntity b = new BalanceteEntity();
            Query query = em.createQuery("Select b FROM BalanceteEntity b WHERE b.descricao_balancete = :s");
            query.setParameter("s", s);
            try{
            b = (BalanceteEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               b.setDescricao_balancete("");
            }
            b.getId();
            b.getNumero_conta();
            b.getData_inicial();
            b.getData_final();
            b.getDescricao_balancete();
            b.getSaldo();
     return b;
    }
     
     public boolean removeBalancete(BalanceteEntity b){
          boolean ret=true;
          BalanceteEntity ba = new BalanceteEntity();
          String nc = b.getNumero_conta();
          ContaEntity c = new ContaEntity();
          Query query = em.createQuery("Select c FROM ContaEntity c WHERE c.numero_conta = :nc");
          query.setParameter("nc", nc);
          try{
           c = (ContaEntity) query.getSingleResult();
           if(c.getBalancetes().size() > 1){
              c.getBalancetes().remove(b);
              em.merge(c);
              try{
                  ba = em.find(BalanceteEntity.class, b.getId());
         }catch(java.lang.IllegalArgumentException e){
                 return false;
         }
              try{
                  em.remove(ba);
         }catch(java.lang.IllegalArgumentException e){
              return false;
         }
        }else{
               c.getBalancetes().remove(b);
               em.merge(c);
               try{
                  ba = em.find(BalanceteEntity.class, b.getId());
         }catch(java.lang.IllegalArgumentException e){
                 return false;
         }
             try{
                  em.remove(ba);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }
           }
         }catch(java.lang.IllegalArgumentException e){
                 return false;
         }
         
     return ret;
     }
      
      public boolean atualizaBalancete(BalanceteEntity b){
          boolean ret=true;
         try{
           em.merge(b);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
    }
      
    public long buscaId(String balancete){
          long l=0;
          Query quer = em.createQuery("Select b.id FROM BalanceteEntity b WHERE b.descricao_balancete = :balancete");
          quer.setParameter("balancete", balancete);
          try{
            l =  (long) quer.getSingleResult(); 
            }catch(NoResultException e){
                return 0;
            } 
     return l;
    }
      
     
    
    
    
    
    
      
     
     
     
     
     ////////           CHEQUE
     
     
     public boolean cadastreCheque(ChequeEntity cheque){
          boolean ret=true;
          Query query = em.createQuery("Select c.numero_cheque FROM ChequeEntity c");
          List<String> nome_cheques = query.getResultList();
          for(int j=0; j< nome_cheques.size(); j++){
              String sa = nome_cheques.get(j);
              if(sa.equals(cheque.getNumero_cheque()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(cheque);
     return ret;
    }
     
     public List<String> selecioneCheques() {
            Query query = em.createQuery("Select c.numero_cheque FROM ChequeEntity c");
            List<String> ufs = query.getResultList();      
     return ufs;
    }
     
    public ChequeEntity selecioneChequeAtualiza(String s) { 
            ChequeEntity c = new ChequeEntity();
            Query query = em.createQuery("Select c FROM ChequeEntity c WHERE c.numero_cheque = :s");
            query.setParameter("s", s);
            try{
            c = (ChequeEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               c.setNumero_cheque("");
            }
            c.getId();
            c.getNumero_cheque();
     return c;
    }
     
     public boolean removeCheque(ChequeEntity c){
         boolean ret=true;
         ChequeEntity ce = new ChequeEntity();
         try{
           ce = em.find(ChequeEntity.class, c.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(ce.getId() == null)
                 return false;
         }
         try{
           em.remove(ce);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
     
    public boolean atualizaCheque(ChequeEntity c){
          boolean ret=true;
         try{
           em.merge(c);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     } 
    
     
     
    
    
    
    
    
    
    
    
     
     ////////           DESCRIÇAO
     
     
    public boolean cadastreDescricao(DescricaoEntity descricao){
         boolean ret=true;
         Query query = em.createQuery("Select d.descricao FROM DescricaoEntity d");
         List<String> nome_descricoes = query.getResultList();
         for(int j=0; j< nome_descricoes.size(); j++){
              String sa = nome_descricoes.get(j);
              if(sa.equals(descricao.getDescricao()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(descricao);
     return ret;
    }
     
     public List<String> selecioneDescricoes() {
            Query query = em.createQuery("Select d.descricao FROM DescricaoEntity d");
            List<String> ufs = query.getResultList();
            Query query2 = em.createQuery("Select n.nome_instituicao FROM InstituicaoParceiraEntity i, i.instituicao n");
            List<String> ufs2 = query2.getResultList();
            Query query3 = em.createQuery("Select p.nome FROM ApoiadorEntity a, a.pessoa p");
            List<String> ufs3 = query3.getResultList();
            Query query4 = em.createQuery("Select n.nome_instituicao FROM InstituicaoContribuinteEntity i, i.instituicao n");
            List<String> ufs4 = query4.getResultList();
            ufs.addAll(ufs2);
            ufs.addAll(ufs3);
            ufs.addAll(ufs4);
            List<String>nome_descricoes = ufs.stream().distinct().collect(Collectors.toList());
     return nome_descricoes;
    }
     
    public DescricaoEntity selecioneDescricaoAtualiza(String s) { 
            DescricaoEntity d = new DescricaoEntity();
            Query query = em.createQuery("Select d FROM DescricaoEntity d WHERE d.descricao = :s");
            query.setParameter("s", s);
            try{
            d = (DescricaoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               d.setDescricao("");
            }
            d.getId();
            d.getDescricao();
     return d;
    }
     
     public boolean removeDescricao(DescricaoEntity d){
          boolean ret=true;
         DescricaoEntity de = new DescricaoEntity();
         try{
           de = em.find(DescricaoEntity.class, d.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(de.getId() == null)
                 return false;
         }
         try{
           em.remove(de);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
     
    public boolean atualizaDescricao(DescricaoEntity d){
          boolean ret=true;
         try{
           em.merge(d);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     ////////           DESCRIÇAO SAIDA
     
     
    public boolean cadastreDescricaoSaida(DescricaoSaidaEntity descricaoSaida){
         boolean ret=true;
         Query query = em.createQuery("Select s.descricao_saida FROM DescricaoSaidaEntity s");
         List<String> nome_descricoes_saidas = query.getResultList();
         for(int j=0; j< nome_descricoes_saidas.size(); j++){
              String sa = nome_descricoes_saidas.get(j);
              if(sa.equals(descricaoSaida.getDescricao_saida()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(descricaoSaida);
     return ret;
    }
     
     public List<String> selecioneDescricoesSaida() {
            Query query = em.createQuery("Select s.descricao_saida FROM DescricaoSaidaEntity s");
            List<String> ufs = query.getResultList();
            Query query2 = em.createQuery("Select a.nome FROM PrestadorFisicoEntity p, p.pessoa a");
            List<String> ufs2 = query2.getResultList();
            Query query3 = em.createQuery("Select p.nome FROM FuncionarioEntity f, f.pessoa p");
            List<String> ufs3 = query3.getResultList();
            Query query4 = em.createQuery("Select i.nome_instituicao FROM FornecedorEntity f, f.instituicao i");
            List<String> ufs4 = query4.getResultList();
            Query query5 = em.createQuery("Select i.nome_instituicao FROM PrestadoraServicoEntity p, p.instituicao i");
            List<String> ufs5 = query5.getResultList();
            ufs.addAll(ufs2);
            ufs.addAll(ufs3);
            ufs.addAll(ufs4);
            ufs.addAll(ufs5);
     return ufs;
    }
     
    public DescricaoSaidaEntity selecioneDescricaoSaidaAtualiza(String s) { 
            DescricaoSaidaEntity d = new DescricaoSaidaEntity();
            Query query = em.createQuery("Select d FROM DescricaoSaidaEntity d WHERE d.descricao_saida = :s");
            query.setParameter("s", s);
            try{
            d = (DescricaoSaidaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               d.setDescricao_saida("");
            }
            d.getId();
            d.getDescricao_saida();
     return d;
    }
     
     public boolean removeDescricaoSaida(DescricaoSaidaEntity d){
          boolean ret=true;
         DescricaoSaidaEntity de = new DescricaoSaidaEntity();
         try{
           de = em.find(DescricaoSaidaEntity.class, d.getId());
         }catch(java.lang.IllegalArgumentException e){
                 return false;
         }
         try{
           em.remove(de);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
     
    public boolean atualizaDescricaoSaida(DescricaoSaidaEntity d){
          boolean ret=true;
         try{
           em.merge(d);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
     
     
     
     
    
    
    
    
    
    
    
     
     
     
      ////////           SAIDA
     
     
    public boolean cadastreSaida(SaidaEntity saida){
         boolean ret=true;
         if(saida == null)
             return false;
         else
             em.persist(saida);
     return ret;
    }
     
    public List<SaidaEntity> selecioneSaidasDate(Date d1, Date d2) {
            Query query = em.createQuery("SELECT s FROM SaidaEntity s WHERE s.data_emissao >= :d1 AND s.data_compensacao <= :d2");
            query.setParameter("d1",d1 , TemporalType.DATE);
            query.setParameter("d2",d2 , TemporalType.DATE);
            List<SaidaEntity> ufs = query.getResultList();        
     return ufs;
    }
    
    public List<SaidaEntity> selecioneSaidaMes(){
          Query query = em.createQuery("SELECT s FROM SaidaEntity s");
            List<SaidaEntity> ufs = query.getResultList();   
    return ufs;
    }
     
     public boolean removeSaida(Long id_remova){
          boolean ret=true;
          SaidaEntity sa = new SaidaEntity();
         try{
           sa = em.find(SaidaEntity.class, id_remova);
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(sa.getId() == null)
                 return false;
             }
             try{
                 em.remove(sa);
                }catch(java.lang.IllegalArgumentException e){
                       return false;
               } 
     return ret;
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     ////////           BALANÇO
     
    public List<String> selecioneNomeDescricao(Date d1, Date d2){
           Query query = em.createQuery("SELECT e.nome_descricao FROM EntradaEntity e WHERE e.data_emissao >= :d1 AND e.data_conpensacao <= :d2");
           query.setParameter("d1",d1 , TemporalType.DATE);
           query.setParameter("d2",d2 , TemporalType.DATE);
           List<String> ufs = query.getResultList();   
    return ufs;
    }
     
    public List<EntradaEntity> selecioneBalancoEntrada(Date d1, Date d2, String nome) {  
            Query query = em.createQuery("SELECT e FROM EntradaEntity e WHERE e.data_emissao >= :d1 AND e.data_conpensacao <= :d2 AND e.nome_descricao = :nome");
            query.setParameter("d1",d1 , TemporalType.DATE);
            query.setParameter("d2",d2 , TemporalType.DATE);
            query.setParameter("nome",nome);
            List<EntradaEntity> ufs = query.getResultList();           
     return ufs;
    }
      
     
     
     
     
     
     
     
     
     
     
     
      ////////           ENTRADA
     
     
     public boolean cadastreEntrada(EntradaEntity entrada){
          boolean ret=true;
          if(entrada == null)
              return false;
          else
              em.persist(entrada);
     return ret;
    }
     
      public List<EntradaEntity> selecioneEntradaDate(Date d1, Date d2) {  
            Query query = em.createQuery("SELECT e FROM EntradaEntity e WHERE e.data_emissao >= :d1 AND e.data_conpensacao <= :d2");
            query.setParameter("d1",d1 , TemporalType.DATE);
            query.setParameter("d2",d2 , TemporalType.DATE);
            List<EntradaEntity> ufs = query.getResultList();           
     return ufs;
    }
     
     public List<Long> getIdesEntrada(){
     
           Query query = em.createQuery("Select e FROM EntradaEntity e");
            List<EntradaEntity> ufs = query.getResultList();
            ArrayList<Long> ides_entradas = new ArrayList<>();
              for(int i=0;i<ufs.size();i++){
                  ides_entradas.add(ufs.get(i).getId());               
              }  
     
     return ides_entradas;
     }
     
     public List<EntradaEntity> selecioneEntradaMes(){
          Query query = em.createQuery("SELECT s FROM EntradaEntity s");
            List<EntradaEntity> ufs = query.getResultList();   
    return ufs;
    }
     
    public boolean removeEntrada(long id_remova){
          boolean ret=true;
          EntradaEntity en = new EntradaEntity();
         try{
           en = em.find(EntradaEntity.class, id_remova);
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(en.getId() == null)
                 return false;
             }
             try{
                 em.remove(en);
                }catch(java.lang.IllegalArgumentException e){
                       return false;
               }
     return ret; 
    }
     
 
     
     
     
     
     
     
     
     
     
     
     
      // CIDADES
    
    
    
    public boolean cadastreCidade(CidadeEntity cidade){
         boolean ret=true;
       
     em.persist(cidade);
     return ret;
   
    }
    
    public List<String> selecioneCidades() {         
            Query query = em.createQuery("Select c.nome_cidade FROM CidadeEntity c");
            List<String> nome_cidades = query.getResultList();
     return nome_cidades;
    }

    
    public CidadeEntity selecioneCidadePorNome(String nome_cid) {  
            CidadeEntity c = new CidadeEntity();
            Query query = em.createQuery("Select c FROM CidadeEntity c WHERE c.nome_cidade = :nome_cid");
            query.setParameter("nome_cid", nome_cid);
            try{
            c = (CidadeEntity) query.getSingleResult();
            }catch(NoResultException e){
               c.setNome_cidade("");
            }
            c.getId();
            c.getNome_cidade();
            c.getUf_cidade();
     return c;
    }
    
    
    
    
    
    
   
    
    
    
    
    
    //        DISTRITO
      
    
    public boolean cadastreDistrito(DistritoEntity distrito){
        boolean ret=true;
       
     em.persist(distrito);
     return ret;
    }
    
    public List<String> selecioneDistritos() {   
            
            Query query = em.createQuery("Select d.nome_distrito FROM DistritoEntity d");
            List<String> nome_distritos = query.getResultList();
           
     return nome_distritos;
    }
    
    public DistritoEntity selecioneDistritoPorNome(String nome_dis) {  
            DistritoEntity d = new DistritoEntity();
            Query query = em.createQuery("Select d FROM DistritoEntity d WHERE d.nome_distrito = :nome_dis");
            query.setParameter("nome_dis", nome_dis);
            try{
            d = (DistritoEntity) query.getSingleResult();
            }catch(NoResultException e){
               d.setNome_distrito("");
            }
            d.getId();
            d.getNome_distrito();
            d.getCidade();
     return d;
    }
    
    
    
    
    
    
    
    
    
    
    //                RUAS
    
    public boolean cadastreEndereco(EnderecoEntity endereco){
          boolean ret=true;
       
     em.persist(endereco);
     return ret;
    
    }
    
    public List<String> selecioneRuas() {         
            Query query = em.createQuery("Select e.logradouro FROM EnderecoEntity e");
            List<String> nome_ruas = query.getResultList();
     return nome_ruas;
    }
    
    public EnderecoEntity selecioneRuasPorNome(String nome_rua) {  
            EnderecoEntity rua = new EnderecoEntity();
            Query query = em.createQuery("Select e FROM EnderecoEntity e WHERE e.logradouro = :nome_rua");
            query.setParameter("nome_rua", nome_rua);
            try{
            rua = (EnderecoEntity) query.getSingleResult();
            }catch(NoResultException e){
               rua.setLogradouro("");
            }
            rua.getId();
            rua.getLogradouro();
            rua.getCep();
            //rua.getDistrito_endereco();
     return rua;
    }
     
     
}
