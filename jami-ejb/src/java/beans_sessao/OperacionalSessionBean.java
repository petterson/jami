/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_sessao;

import entidades.AlergiaEntity;
import entidades.AlunoEntity;
import entidades.CartorioEntity;
import entidades.ChamadaEntity;
import entidades.CidadeEntity;
import entidades.DistritoEntity;
import entidades.EnderecoEntity;
import entidades.EscolaEntity;
import entidades.EstuChaEntity;
import entidades.FrequenciaEntity;
import entidades.IrmaoEntity;
import entidades.MaeEntity;
import entidades.PaiEntity;
import entidades.ProblemaSaudeEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author petterson
 */
@Stateless
@LocalBean
public class OperacionalSessionBean {

    
    @PersistenceContext(unitName="jami-ejbPU")
    EntityManager em ;
    
    
    
    
    
    
    
    
    
    
    ////////////       ALUNOS
    
    
    public boolean cadastreAluno(AlunoEntity aluno){
                 em.persist(aluno);
     return true;
    }
    
    public List<String> selecioneNomeAlunos() {
          String s = "Ativo";
          Query query = em.createQuery("Select p.nome FROM AlunoEntity a, a.pessoa p WHERE a.situacao = :s");
          query.setParameter("s", s);
          List<String> ufs = query.getResultList();  
     return ufs;
    }
    
    public List<AlunoEntity> selecioneAlunos(List<String> nome_alunos) {
          String s = "Ativo";
          Query query = em.createQuery("Select a FROM AlunoEntity a, a.pessoa p WHERE p.nome in :nome_alunos AND a.situacao = :s");
          query.setParameter("s", s);
          query.setParameter("nome_alunos", nome_alunos);
          List<AlunoEntity> ufs = query.getResultList();  
     return ufs;
    }
    
    public List<AlunoEntity> selecioneAlunosDocumentos() {
            String s= "Ativo";
            Query query = em.createQuery("Select a FROM AlunoEntity a WHERE a.situacao = :s");
            query.setParameter("s", s);
            List<AlunoEntity> ufs = query.getResultList();  
     return ufs;
    }
    
    public List<AlunoEntity> selecioneTodosAlunos() {
            Query query = em.createQuery("Select a FROM AlunoEntity a");
            List<AlunoEntity> ufs = query.getResultList();  
     return ufs;
    }
    
    public List<AlunoEntity> selecioneAlunosEmEspera() {
            String s= "Espera";
            Query query = em.createQuery("Select a FROM AlunoEntity a WHERE a.situacao = :s");
            query.setParameter("s", s);
            List<AlunoEntity> ufs = query.getResultList();  
     return ufs;
    }
    
    public List<AlunoEntity> selecioneAlunosDesistentes() {
            String s = "Desistente";
            Query query = em.createQuery("Select a FROM AlunoEntity a WHERE a.situacao = :s");
            query.setParameter("s", s);
            List<AlunoEntity> ufs = query.getResultList(); 
    return ufs;
    }
    
    public List<AlunoEntity> selecioneAlunosPorTurnoSexo(String sexo, String turno) {
            String s = "Ativo";
            Query query = em.createQuery("Select a FROM AlunoEntity a WHERE a.situacao = :s AND a.sexo = :sexo AND a.turno = :turno");
            query.setParameter("s", s);
            query.setParameter("sexo", sexo);
            query.setParameter("turno", turno);
            List<AlunoEntity> ufs = query.getResultList(); 
    return ufs;
    } 
    
    public List<AlunoEntity> selecioneAlunoMasculinoManha() {
            String s = "Ativo";
            String sexo = "Masculino";
            String turno ="Manhã";
            Query query = em.createQuery("Select a FROM AlunoEntity a WHERE a.situacao = :s AND a.sexo = :sexo AND a.turno = :turno");
            query.setParameter("s", s);
            query.setParameter("sexo", sexo);
            query.setParameter("turno", turno);
            List<AlunoEntity> ufs = query.getResultList(); 
    return ufs;
    }
    
    public List<AlunoEntity> selecioneAlunoMasculinoTarde() {
            String s = "Ativo";
            String sexo = "Masculino";
            String turno ="Tarde";
            Query query = em.createQuery("Select a FROM AlunoEntity a WHERE a.situacao = :s AND a.sexo = :sexo AND a.turno = :turno");
            query.setParameter("s", s);
            query.setParameter("sexo", sexo);
            query.setParameter("turno", turno);
            List<AlunoEntity> ufs = query.getResultList(); 
    return ufs;
    }
    
    public List<AlunoEntity> selecioneAlunoFemininoManha() {
            String s = "Ativo";
            String sexo = "Feminino";
            String turno ="Manhã";
            Query query = em.createQuery("Select a FROM AlunoEntity a WHERE a.situacao = :s AND a.sexo = :sexo AND a.turno = :turno");
            query.setParameter("s", s);
            query.setParameter("sexo", sexo);
            query.setParameter("turno", turno);
            List<AlunoEntity> ufs = query.getResultList(); 
    return ufs;
    }
    
    public List<AlunoEntity> selecioneAlunoFemininoTarde() {
            String s = "Ativo";
            String sexo = "Feminino";
            String turno ="Tarde";
            Query query = em.createQuery("Select a FROM AlunoEntity a WHERE a.situacao = :s AND a.sexo = :sexo AND a.turno = :turno");
            query.setParameter("s", s);
            query.setParameter("sexo", sexo);
            query.setParameter("turno", turno);
            List<AlunoEntity> ufs = query.getResultList(); 
    return ufs;
    }
    
    public AlunoEntity selecioneAlunosPorNome(String nome_aluno) {
            String ativo = "Ativo";
            AlunoEntity ufs = new AlunoEntity();
            Query query = em.createQuery("Select a FROM AlunoEntity a, a.pessoa p WHERE p.nome = :nome_aluno AND a.situacao = :Ativo");
            query.setParameter("nome_aluno", nome_aluno);
            query.setParameter("Ativo", ativo);
            try{
                  ufs = (AlunoEntity) query.getSingleResult();
            }catch(NoResultException e){
               ufs.setFone_aluno("");
            }
    return ufs;
    }
    
    public AlunoEntity selecioneAlunoAtualiza(String s) { 
            AlunoEntity a = new AlunoEntity();
            Query query = em.createQuery("Select a FROM AlunoEntity a, a.pessoa p WHERE p.nome = :s");
            query.setParameter("s", s);
            try{
            a = (AlunoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               a.setFone_aluno("");
            }
            a.getId();
            a.getFone_aluno();
            a.getRg();
            a.getOrgao_expedidor();
            a.getData_expedicao();
            a.getSexo();
            a.getData_matricula();
            a.getNumero_certidao();
            a.getLivro_certidao();
            a.getFolha_certidao();
            a.getCartorio();
            a.getRua_aluno();
            a.getNumero_casa();
            a.getPonto_referencia();
            a.getCidade_nascimento();
            a.getCor_raca();
            a.getEscola();
            a.getMatricula_escola();
            a.getSerie();
            a.getTurno();
            a.getAlergia();
            a.getProblema_saude();
            a.getBolsa_familia();
            a.getIrmaos();
            a.getPai();
            a.getMae();
            a.getSituacao();
            a.getPessoa();
     return a;
    }
    
    public boolean removeAluno(AlunoEntity a){
         AlunoEntity al = new AlunoEntity();
         CartorioEntity c = a.getCartorio();
         EscolaEntity es = a.getEscola();
         CidadeEntity ci = a.getCidade_nascimento();
         EnderecoEntity en = a.getRua_aluno();
         PaiEntity p = a.getPai();
         MaeEntity m = a.getMae();
         List<ProblemaSaudeEntity> list_pro = a.getProblema_saude();
         List<AlergiaEntity> list_aler = a.getAlergia();
         List<IrmaoEntity> list_irm = a.getIrmaos();
         try{
           al = em.find(AlunoEntity.class, a.getId());
           //System.out.println("aluno"+al.getNome_aluno());
         }catch(java.lang.IllegalArgumentException e){
                 System.out.println("erro aluno");
                 return false;
         }
         try{
             for(int i=0; i< list_irm.size(); i++){
               IrmaoEntity ir = list_irm.get(i);
               if(ir.getList_aluno().size() > 1){
                  ir.getList_aluno().remove(al);
                  al.getIrmaos().remove(ir);
                  try{em.merge(al);}catch(java.lang.IllegalArgumentException e){System.out.println("erro pai");return false;}
                  try{em.merge(ir);}catch(java.lang.IllegalArgumentException e){System.out.println("erro ir");return false;}
               }else{try{em.remove(ir);}catch(java.lang.IllegalArgumentException e){System.out.println("erro ir");return false;};}
           } 
           for(int i=0; i< list_aler.size(); i++){
               AlergiaEntity aler = list_aler.get(i);
               if(aler.getList_aluno().size() > 1){
                  aler.getList_aluno().remove(al);
                  al.getAlergia().remove(aler);
                  try{em.merge(al);}catch(java.lang.IllegalArgumentException e){System.out.println("erro pai");return false;}
                  try{em.merge(aler);}catch(java.lang.IllegalArgumentException e){System.out.println("erro aler");return false;}
               }else{try{em.remove(aler);}catch(java.lang.IllegalArgumentException e){System.out.println("erro aler");return false;};}
           }
           for(int i=0; i< list_pro.size(); i++){
               ProblemaSaudeEntity proble = list_pro.get(i);
               if(proble.getList_aluno().size() > 1){
                  proble.getList_aluno().remove(al);
                  al.getProblema_saude().remove(proble);
                  try{em.merge(al);}catch(java.lang.IllegalArgumentException e){System.out.println("erro pai");return false;}
                  try{em.merge(proble);}catch(java.lang.IllegalArgumentException e){System.out.println("erro proble");return false;}
               }else{try{em.remove(proble);}catch(java.lang.IllegalArgumentException e){System.out.println("erro proble");return false;};}
           }
           if(c.getList_aluno().size() >1){
               c.getList_aluno().remove(al);
               al.setCartorio(null);
               try{em.merge(al);}catch(java.lang.IllegalArgumentException e){System.out.println("erro pai");return false;}
               try{em.merge(c);}catch(java.lang.IllegalArgumentException e){System.out.println("erro c");return false;}
           }else{try{em.remove(c);}catch(java.lang.IllegalArgumentException e){System.out.println("erro c");return false;};}
           if(es.getList_aluno().size() > 1){
              es.getList_aluno().remove(al);
              al.setEscola(null);
              try{em.merge(al);}catch(java.lang.IllegalArgumentException e){System.out.println("erro pai");return false;}
              try{em.merge(es);}catch(java.lang.IllegalArgumentException e){System.out.println("erro es");return false;}
           }else{try{em.remove(es);}catch(java.lang.IllegalArgumentException e){System.out.println("erro es");return false;};}
           if(ci.getList_aluno().size() > 1){
              ci.getList_aluno().remove(al);
              al.setCidade_nascimento(null);
              try{em.merge(al);}catch(java.lang.IllegalArgumentException e){System.out.println("erro pai");return false;}
              try{em.merge(ci);}catch(java.lang.IllegalArgumentException e){System.out.println("erro ci");return false;}
           }else{try{em.remove(ci);}catch(java.lang.IllegalArgumentException e){System.out.println("erro ci");return false;};}
           if(en.getList_aluno().size() >1){
              en.getList_aluno().remove(al);
              al.setRua_aluno(null);
              try{em.merge(al);}catch(java.lang.IllegalArgumentException e){System.out.println("erro pai");return false;}
              try{em.merge(en);}catch(java.lang.IllegalArgumentException e){System.out.println("erro en");return false;}
           }else{try{em.remove(en);}catch(java.lang.IllegalArgumentException e){System.out.println("erro en");return false;};}
           if(m.getList_aluno().size() > 1){
              m.getList_aluno().remove(al);
              al.setMae(null);
              try{em.merge(al);}catch(java.lang.IllegalArgumentException e){System.out.println("erro pai");return false;}
              try{em.merge(m);}catch(java.lang.IllegalArgumentException e){System.out.println("erro m");return false;}
           }else{try{em.remove(m);}catch(java.lang.IllegalArgumentException e){System.out.println("erro m");return false;};} 
           System.out.println(p.getList_aluno().size());
           if(p.getList_aluno().size() > 1){
              p.getList_aluno().remove(al);
              al.setPai(null);
              try{em.merge(al);}catch(java.lang.IllegalArgumentException e){System.out.println("erro pai");return false;}
              try{em.merge(p);}catch(java.lang.IllegalArgumentException e){System.out.println("erro p a");return false;}
           }else{try{em.remove(p);}catch(java.lang.IllegalArgumentException e){System.out.println("erro p r");return false;};}
           em.remove(al);
       }catch(java.lang.IllegalArgumentException e){
           System.out.println("erro aluno");
              return false;
       }   
     return true;
     }
    
    public boolean atualizaAluno(AlunoEntity a){
          boolean ret=true;
         try{
           em.merge(a);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
    
    
    
    
     ///////////       EstuChamad
   
    public boolean cadastreEstuChamada(EstuChaEntity a){
          boolean ret=true;
                 em.persist(a);
     return ret;
    }
    
    public List<EstuChaEntity> selecioneEstuCha(List<String> nomes, String data_hoje , String nome_chamada){
          Query query = em.createQuery("Select e FROM EstuChaEntity e WHERE e.data_cadastro = :data_hoje AND "
                  + "e.nome_chamada = :nome_chamada AND e.nome_estunte in :nomes");
          query.setParameter("data_hoje", data_hoje);
          query.setParameter("nome_chamada", nome_chamada);
          query.setParameter("nomes", nomes);
          List<EstuChaEntity> ufs = query.getResultList(); 
    return ufs;
    }

    public boolean atualizaEstuCha(EstuChaEntity es){
          boolean ret=true;
         try{
           em.merge(es);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    public boolean removeEstuCha(EstuChaEntity es){
         boolean ret=true;
         EstuChaEntity est = new EstuChaEntity();
         try{
           est = em.find(EstuChaEntity.class, es.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(est.getId() == null)
                 return false;
         }
         try{
           em.remove(est);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
    
    
    
    
    
    
    
    
    
    ///////////       ALERGIAS

    public boolean cadastreAlergia(AlergiaEntity alergia){
         boolean ret=true;
         Query query = em.createQuery("Select a.nome_alergia FROM AlergiaEntity a");
         List<String> nome_alergias = query.getResultList();
         for(int j=0; j< nome_alergias.size(); j++){
              String sa = nome_alergias.get(j);
              if(sa.equals(alergia.getNome_alergia()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(alergia);
     return ret;
    }
    
    public List<String> selecioneAlergias() {
            Query query = em.createQuery("Select a.nome_alergia FROM AlergiaEntity a");
            List<String> ufs = query.getResultList();
     return ufs;
    }
    
    public List<AlergiaEntity> selecioneListAlergias(ArrayList<String> nome_alergias) {
          Query query = em.createQuery("Select a FROM AlergiaEntity a WHERE a.nome_alergia in :nome_alergias");
          query.setParameter("nome_alergias", nome_alergias);
          List<AlergiaEntity> ufs = query.getResultList();  
     return ufs;
    }
    
    public AlergiaEntity selecioneAlergiaAtualiza(String s) { 
            AlergiaEntity a = new AlergiaEntity();
            Query query = em.createQuery("Select p FROM AlergiaEntity p WHERE p.nome_alergia = :s");
            query.setParameter("s", s);
            try{
            a = (AlergiaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               a.setNome_alergia("");
            }
            a.getId();
            a.getNome_alergia();
            a.getGrau_perigo_alergia();
     return a;
    }
    
    public boolean removeAlergia(AlergiaEntity a){
         boolean ret=true;
         AlergiaEntity al = new AlergiaEntity();
         try{
           al = em.find(AlergiaEntity.class, a.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(al.getId() == null)
                 return false;
         }
         try{
           em.remove(al);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
    
    public boolean atualizaAlergia(AlergiaEntity a){
          boolean ret=true;
         try{
           em.merge(a);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
  
    
    
    
    
    
    
    
    
    
    
    // PROBLEMA SAUDE
     
    
    public boolean cadastreProblemaSaude(ProblemaSaudeEntity problema){
         boolean ret=true;
         Query query = em.createQuery("Select p.problema_saude FROM ProblemaSaudeEntity p");
         List<String> nome_problemas = query.getResultList();
         for(int j=0; j< nome_problemas.size(); j++){
              String sa = nome_problemas.get(j);
              if(sa.equals(problema.getProblema_saude()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(problema);
     return ret;
    }
    
   public List<String> selecioneProblemaSaude() {
            Query query = em.createQuery("Select p.problema_saude FROM ProblemaSaudeEntity p");
            List<String> ufs = query.getResultList();       
     return ufs;
    } 
   
   public List<ProblemaSaudeEntity> selecioneListaProblemas(List<String> nome_problemas) {
          Query query = em.createQuery("Select p FROM ProblemaSaudeEntity p WHERE p.problema_saude in :nome_problemas");
          query.setParameter("nome_problemas", nome_problemas);
          List<ProblemaSaudeEntity> ufs = query.getResultList();  
     return ufs;
    }
    
    public ProblemaSaudeEntity selecioneProblemaAtualiza(String s) { 
            ProblemaSaudeEntity p = new ProblemaSaudeEntity();
            Query query = em.createQuery("Select p FROM ProblemaSaudeEntity p WHERE p.problema_saude = :s");
            query.setParameter("s", s);
            try{
            p = (ProblemaSaudeEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               p.setProblema_saude("");
            }
            p.getId();
            p.getProblema_saude();
            p.getGrau_perigo();
     return p;
    }
    
      public boolean removeProblema(ProblemaSaudeEntity p){
           boolean ret=true;
           ProblemaSaudeEntity po = new ProblemaSaudeEntity();
           try{
           po = em.find(ProblemaSaudeEntity.class, p.getId());
           }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(po.getId() == null)
                 return false;
           }
           try{
           em.remove(po);
           }catch(java.lang.IllegalArgumentException e){
                  return false;
           }   
     return ret;
     }
    
    public boolean atualizaProblema(ProblemaSaudeEntity p){
          boolean ret=true;
         try{
           em.merge(p);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
      
   
   
   
   
   
   
    // CIDADES 
    
    public boolean cadastreCidade(CidadeEntity cidade){
         boolean ret=true;
         Query query = em.createQuery("Select c.nome_cidade FROM CidadeEntity c");
         List<String> nome_cidades = query.getResultList();
         for(int j=0; j< nome_cidades.size(); j++){
              String ci = nome_cidades.get(j);
              if(ci.equals(cidade.getNome_cidade()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(cidade);
     return ret;
   
    }
    
    public List<String> selecioneCidades() {
            Query query = em.createQuery("Select c.nome_cidade FROM CidadeEntity c");
            List<String> nome_cidades = query.getResultList();   
     return nome_cidades;
    }
    
    public CidadeEntity selecioneCidadeAtualiza(String s) { 
            CidadeEntity c = new CidadeEntity();
            Query query = em.createQuery("Select c FROM CidadeEntity c WHERE c.nome_cidade = :s");
            query.setParameter("s", s);
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
    
    public boolean removeAlunoAtualizaCidade(AlunoEntity a){
           CidadeEntity c=a.getCidade_nascimento();
           String nome = c.getNome_cidade();
           List<String> n_a = new ArrayList<>();
           Query query = em.createQuery("Select a.nome_aluno FROM AlunoEntity a JOIN a.cidade_nascimento c WHERE c.nome_cidade = :nome");
           query.setParameter("nome", nome);
            try{
            n_a = query.getResultList();
            if(n_a.size() > 1){
               a.setCidade_nascimento(null);
               try{
                   em.merge(a);
               }catch(java.lang.IllegalArgumentException e){
                      System.out.println("Atualiza Aluno");
                          return true;
               }
            }else{
                  return true;
            }
            }catch(NoResultException e){
                   return false;
            }
    return true;
    }
    
    /*public boolean removeCidadeAtualizaDistrito(CidadeEntity c){
           boolean res=true;
           String nome = c.getNome_cidade();
           CidadeEntity ci = new CidadeEntity();
           List<DistritoEntity> l_d = new ArrayList<>();
           Query query = em.createQuery("Select d FROM DistritoEntity d JOIN d.cidade c WHERE c.nome_cidade = :nome");
           query.setParameter("nome", nome);
           try{
               l_d = query.getResultList();
               if(l_d.size() >= 1){
                 for(int i=0; i<l_d.size(); i++){
                     this.removeDistrito(l_d.get(i));
                 }
               }else{
                      try{
                          ci = em.find(CidadeEntity.class, c.getId());
                      }catch(java.lang.IllegalArgumentException e){
                              System.out.println("busca cidade");
                             return false;
                      }
                      try{
                          em.remove(ci);
                      }catch(java.lang.IllegalArgumentException e){
                              System.out.println("remove");
                             return false;
                      }
               }
           }catch(java.lang.IllegalArgumentException e){
                      System.out.println("busca distrito");
                          return true;
           }
    return res;
    }*/
    
    public boolean removeDistritoAtualizaCidade(DistritoEntity d){
           CidadeEntity di = d.getCidade();
           DistritoEntity dis = new DistritoEntity();
               if(di.getList_distrito().size() > 1){
                   di.getList_distrito().remove(d);
                   try{
                       em.merge(di);
                   }catch(java.lang.IllegalArgumentException e){
                       System.out.println("erro cidade");
                           return false;
                   }
                   try{
                          dis = em.find(DistritoEntity.class, d.getId());
                          dis.setCidade(null);
                }catch(java.lang.IllegalArgumentException e){
                              System.out.println("busca cidade");
                             return false;
                      }
                      try{
                          em.remove(dis);
                      }catch(java.lang.IllegalArgumentException e){
                              System.out.println("remove");
                             return false;
                      }
                      }else{
                      try{
                          em.remove(di);
                      }catch(java.lang.IllegalArgumentException e){
                              System.out.println("remove");
                             return false;
                      }
               }
    return true;
    }
    
    public boolean removeCidade(CidadeEntity c){
         boolean ret=true;
         CidadeEntity ci = new CidadeEntity();
         try{
           ci = em.find(CidadeEntity.class, c.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(ci.getId() == null)
                 return false;
         }
         try{
           em.remove(ci);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
    
    public boolean atualizaCidade(CidadeEntity c){
          boolean ret=true;
         try{
           em.merge(c);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
    
    
    
    
    
    //        DISTRITO

    public boolean cadastreDistrito(DistritoEntity distrito){
         boolean ret=true;
         Query query = em.createQuery("Select d.nome_distrito FROM DistritoEntity d");
         List<String> nome_distritos = query.getResultList();
         for(int j=0; j< nome_distritos.size(); j++){
              String ci = nome_distritos.get(j);
              if(ci.equals(distrito.getNome_distrito()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(distrito);
     return ret;
    }
    
    public List<String> selecioneDistritos() {    
            Query query = em.createQuery("Select d.nome_distrito FROM DistritoEntity d");
            List<String> nome_distritos = query.getResultList();
     return nome_distritos;
    }
    
    public DistritoEntity selecioneDistritoAtualiza(String s) { 
            DistritoEntity d = new DistritoEntity();
            Query query = em.createQuery("Select d FROM DistritoEntity d WHERE d.nome_distrito = :s");
            query.setParameter("s", s);
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
    
    public boolean removeDistrito(DistritoEntity d){
         boolean ret=true;
         DistritoEntity di = new DistritoEntity();
         try{
           di = em.find(DistritoEntity.class, d.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(di.getId() == null)
                 return false;
         }
         try{
             em.remove(di);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
    
    public boolean atualizaDistrito(DistritoEntity d){
          boolean ret=true;
         try{
           em.merge(d);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
    
    
    
    
    
    
    
    //                RUAS
 
    public boolean cadastreEndereco(EnderecoEntity endereco){
          boolean ret=true;
          Query query = em.createQuery("Select e.logradouro FROM EnderecoEntity e");
          List<String> nome_ruas = query.getResultList();
          for(int j=0; j< nome_ruas.size(); j++){
              String ci = nome_ruas.get(j);
              if(ci.equals(endereco.getLogradouro()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(endereco);
     return ret;
    
    }
    
    public List<String> selecioneRuas() {        
            Query query = em.createQuery("Select e.logradouro FROM EnderecoEntity e");
            List<String> ufs = query.getResultList();
     return ufs;
    }
    
    public EnderecoEntity selecioneEnderecoAtualiza(String s) { 
            EnderecoEntity n = new EnderecoEntity();
            Query query = em.createQuery("Select e FROM EnderecoEntity e WHERE e.logradouro = :s");
            query.setParameter("s", s);
            try{
            n = (EnderecoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               n.setLogradouro("");
            }
            n.getId();
            n.getLogradouro();
            n.getCep();
           n.getDistrito_endereco();
     return n;
    }
    
    public boolean removeAlunoAtualizaRua(AlunoEntity a){
           EnderecoEntity en=a.getRua_aluno();
           String nome = en.getLogradouro();
           List<String> n_a = new ArrayList<>();
           Query query = em.createQuery("Select a.nome_aluno FROM AlunoEntity a JOIN a.rua_aluno r WHERE r.logradouro = :nome");
           query.setParameter("nome", nome);
            try{
            n_a = query.getResultList();
            if(n_a.size() > 1){
               a.setRua_aluno(null);
               try{
                   em.merge(a);
               }catch(java.lang.IllegalArgumentException e){
                      System.out.println("Atualiza Aluno");
                          return true;
               }
            }else{
                //System.out.println(a.getPai().getNome_pai()+"return");
                  return true;
            }
            }catch(NoResultException e){
                   return false;
            }
    return true;
    }
    
    public boolean removeEndereco(EnderecoEntity n){
         boolean ret=true;
         EnderecoEntity en = new EnderecoEntity();
         try{
           en = em.find(EnderecoEntity.class, n.getId());
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
    
    public boolean atualizaEndereco(EnderecoEntity n){
          boolean ret=true;
         try{
           em.merge(n);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
    
    
    //       ESCOLAS

    
    public boolean cadastreEscola(EscolaEntity escola){
          boolean ret=true;
          Query query = em.createQuery("Select e.nome_escola FROM EscolaEntity e");
          List<String> nome_escolas = query.getResultList();
          for(int j=0; j< nome_escolas.size(); j++){
              String sa = nome_escolas.get(j);
              if(sa.equals(escola.getNome_escola()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(escola);
     return ret;
    }
    
    public List<String> selecioneEscolas() {
            Query query = em.createQuery("Select e.nome_escola FROM EscolaEntity e");
            List<String> ufs = query.getResultList();
     return ufs;
    }
    
    public EscolaEntity selecioneEscolaAtualiza(String s) { 
            EscolaEntity e = new EscolaEntity();
            Query query = em.createQuery("Select e FROM EscolaEntity e WHERE e.nome_escola = :s");
            query.setParameter("s", s);
            try{
            e = (EscolaEntity) query.getSingleResult(); 
            }catch(NoResultException ex){
               e.setNome_escola("");
            }
            e.getId();
            e.getNome_escola();
            e.getRua_escola();
            e.getNumero_escola();
     return e;
    }
    
    public boolean removeEscola(EscolaEntity e){
         boolean ret=true;
         EscolaEntity es = new EscolaEntity();
         try{
           es = em.find(EscolaEntity.class, e.getId());
         }catch(java.lang.IllegalArgumentException ex){
                if(es.getId() == null)
                 return false;
         }
         try{
           em.remove(es);
       }catch(java.lang.IllegalArgumentException ex){
              return false;
       }   
     return ret;
     }
     
    public boolean atualizaEscola(EscolaEntity e){
          boolean ret=true;
         try{
           em.merge(e);
       }catch(java.lang.IllegalArgumentException xe){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
    
    
    
    
    ////      CARTORIOS
    
   
    public boolean cadastreCartorio(CartorioEntity cartorio){
          boolean ret=true;
          Query query = em.createQuery("Select c.nome_cartorio FROM CartorioEntity c");
          List<String> nome_cartorios = query.getResultList();
          for(int j=0; j< nome_cartorios.size(); j++){
              String sa = nome_cartorios.get(j);
              if(sa.equals(cartorio.getNome_cartorio()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(cartorio);
     return ret;
    }
    
    public List<String> selecioneCartorios() {
            Query query = em.createQuery("Select c.nome_cartorio FROM CartorioEntity c");
            List<String> ufs = query.getResultList();         
     return ufs;
    }
    
    public CartorioEntity selecioneCartorioAtualiza(String s) { 
            CartorioEntity c = new CartorioEntity();
            Query query = em.createQuery("Select c FROM CartorioEntity c WHERE c.nome_cartorio = :s");
            query.setParameter("s", s);
            try{
            c = (CartorioEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               c.setNome_cartorio("");
            }
            c.getId();
            c.getNome_cartorio();
            c.getRua_cartorio();
            c.getNumero_caetorio();
     return c;
    }
    
     public boolean removeCartorio(CartorioEntity c){
           boolean ret=true;
           if(c != null){
           CartorioEntity ca = new CartorioEntity();
           EnderecoEntity en = c.getRua_cartorio();
         try{
           ca = em.find(CartorioEntity.class, c.getId());
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
           }else{
                return true;
           }
     return ret;
     }
     
    public boolean atualizaCartorio(CartorioEntity c){
          boolean ret=true;
         try{
           em.merge(c);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
    
    
    
    
    
    
    
    //////////////    MAE
    
    public boolean cadastreMae(MaeEntity mae){
                 em.persist(mae);
     return true;
    }
    
    public List<String> selecioneMaes() { 
            Query query = em.createQuery("Select p.nome FROM MaeEntity a, a.pessoa p");
            List<String> ufs = query.getResultList();
     return ufs;
    }
    
    public MaeEntity selecioneMaeAtualiza(String s) { 
            MaeEntity m = new MaeEntity();
            Query query = em.createQuery("Select m FROM MaeEntity m, m.pessoa p WHERE p.nome = :s");
            query.setParameter("s", s);
            try{
            m = (MaeEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               m.setFone_mae("");
            }
            m.getId();
            m.getRua_mae();
            m.getProfissao_mae();
            m.getLocal_trabalho_mae();
            m.getFone_mae();
            m.getEscolaridade_mae();
            m.getRua_mae();
            m.getNumero_casa_mae();
            m.getPessoa();
            m.getList_aluno();
     return m;
    }
    
    public boolean atualizaMae(MaeEntity m){
          boolean ret=true;
         try{
           em.merge(m);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    public boolean removeMae(MaeEntity p){
         boolean ret=true;
         MaeEntity pi = new MaeEntity();
         EnderecoEntity pa = p.getRua_mae();
         try{
           pi = em.find(MaeEntity.class, p.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(pi.getFone_mae().equals(""))
                  return false;
         }
         try{
         
             if(pa.getList_aluno().size() > 1){
                pa.setLogradouro(null);
               this.atualizaEndereco(pa);
             }else{this.removeEndereco(pa);}
           em.remove(pi);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
    }
    
    
    
    
    
    
    
    
    
    
    
      
    ////      IRMÃOS
    
   
    public boolean cadastreIrmao(IrmaoEntity irmao){
                 em.persist(irmao);
     return true;
    }
    
    public List<String> selecioneIrmaos() {
            Query query = em.createQuery("Select p.nome FROM IrmaoEntity a, a.pessoa p");
            List<String> ufs = query.getResultList();       
     return ufs;
    }
    
     public List<IrmaoEntity> selecioneListaIrmaos(List<String> nome_irmaos) {
          Query query = em.createQuery("Select i FROM IrmaoEntity i, i.pessoa p WHERE p.nome in :nome_irmaos");
          query.setParameter("nome_irmaos", nome_irmaos);
          List<IrmaoEntity> ufs = query.getResultList();  
     return ufs;
     }
     
    public IrmaoEntity selecioneIrmaoAtualiza(String s) { 
            IrmaoEntity i = new IrmaoEntity();
            Query query = em.createQuery("Select i FROM IrmaoEntity i, i.pessoa p WHERE p.nome = :s");
            query.setParameter("s", s);
            try{
            i = (IrmaoEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               i.setFone("");
            }
            i.getId();
            i.getPessoa();
            i.getList_aluno();
            i.getFone();
     return i;
    }
    
    public boolean removeIrmao(IrmaoEntity i){
         boolean ret=true;
         IrmaoEntity ir = new IrmaoEntity();
         try{
           ir = em.find(IrmaoEntity.class, i.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(ir.getId() == null)
                 return false;
         }
         try{
           em.remove(ir);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
     }
    
    public boolean atualizaIrmao(IrmaoEntity i){
          boolean ret=true;
         try{
           em.merge(i);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    
    
    
    
    
    
    //////////   PAI
    
    public boolean cadastrePai(PaiEntity pai){
                 em.persist(pai);
     return true;
    }
    
    public List<String> selecionePais() {
            Query query = em.createQuery("Select a.nome FROM PaiEntity p, p.pessoa a");
            List<String> ufs = query.getResultList();
     return ufs;
    }
    
     public PaiEntity selecionePaiAtualiza(String s) { 
            PaiEntity p = new PaiEntity();
            Query query = em.createQuery("Select p FROM PaiEntity p, p.pessoa a WHERE a.nome = :s");
            query.setParameter("s", s);
            try{
            p = (PaiEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               p.setFone_pai("");
            }
            p.getId();
            p.getRg_pai();
            p.getFone_pai();
            p.getProfissao_pai();
            p.getLocal_trabalho_pai();
            p.getEscolaridade_pai();
            p.getRua_pai();
            p.getNumero_casa_pai();
            p.getPessoa();
            p.getList_aluno();
     return p;
    }
    
    public boolean removePai(PaiEntity p){
         boolean ret=true;
         PaiEntity pi = new PaiEntity();
         EnderecoEntity pa = p.getRua_pai();
         try{
           pi = em.find(PaiEntity.class, p.getId());
         }catch(java.lang.IllegalArgumentException e){
             e.getMessage();
                if(pi.getFone_pai().equals(""))
                  return false;
         }
         try{
         
             if(pa.getList_aluno().size() > 1){
                pa.setLogradouro(null);
               this.atualizaEndereco(pa);
             }else{this.removeEndereco(pa);}
           em.remove(pi);
       }catch(java.lang.IllegalArgumentException e){
              return false;
       }   
     return ret;
    }
     
     public boolean atualizaPai(PaiEntity p){
          boolean ret=true;
         try{
           em.merge(p);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
     
     
     
     
     
     
    
    
    
    
    
    
    
    
    
    
    //////////////       CHAMADA
    
    public boolean cadastreChamada(ChamadaEntity chamada){
         boolean ret=true;
         Query query = em.createQuery("Select c.nome_chamada FROM ChamadaEntity c");
         List<String> nome_chamadas = query.getResultList();
         for(int j=0; j< nome_chamadas.size(); j++){
              String sa = nome_chamadas.get(j);
              if(sa.equals(chamada.getNome_chamada()))
                  ret = false;
              }
              if(ret == true)
                 em.persist(chamada);
     return ret;
    }
    
    public List<String> selecioneChamadas() {  
            Query query = em.createQuery("Select c.nome_chamada FROM ChamadaEntity c");
            List<String> ufs = query.getResultList();
     return ufs;
    }
    
    public List<String> selecioneChamadasComTurmas() {  
            Query query = em.createQuery("Select t.chamada.nome_chamada FROM TurmaEntity t");
            List<String> ufs = query.getResultList();
     return ufs;
    }
   
    public boolean removeChamada(ChamadaEntity c){
         boolean ret=true;
         ChamadaEntity ca = new ChamadaEntity();
         try{
          ca = em.find(ChamadaEntity.class, c.getId());
         }catch(java.lang.IllegalArgumentException e){
               if(ca.getId() == null)
                 return false;
         }
         try{
           em.remove(ca);
       }catch(java.lang.IllegalArgumentException e){
              if(ca.getId() == null)
                 return false;
       }     
     return ret;
     }
      
      public ChamadaEntity selecioneChamadaAtualiza(String s){
          ChamadaEntity c = new ChamadaEntity();
          Query query = em.createQuery("Select c FROM ChamadaEntity c WHERE c.nome_chamada = :s");
            query.setParameter("s", s);
            try{
            c = (ChamadaEntity) query.getSingleResult();
            }catch(NoResultException e){
                c.setNome_chamada("");
            }
            c.getId();
            c.getNome_chamada();
            c.getData_criacao_chamada();
     return c;
    }
      
   
   public boolean atualizaChamada(ChamadaEntity c){
          boolean ret=true;
         try{
           em.merge(c);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
       
    

    
    
    
    
      
      
    
    
    /////////////  FREQUENCIA
    
    public boolean cadsatreFrequencia(FrequenciaEntity frequencia, Date data_hoje){
           boolean ret=true;
           FrequenciaEntity f = new FrequenciaEntity();
           String nome= frequencia.getNome_chamada();
           Query query = em.createQuery("Select f FROM FrequenciaEntity f WHERE f.nome_chamada = :nome AND f.data_frequencia = :data_hoje");
           query.setParameter("data_hoje", data_hoje);
           query.setParameter("nome", nome);
           try{
           f = (FrequenciaEntity) query.getSingleResult();
           }catch(NoResultException e){
               f.setNome_chamada("");
           }
            if(f.getNome_chamada().equals(""))
               em.persist(frequencia);
            else 
                return false;
     return ret;
    }
    
    public List<FrequenciaEntity> selecioneFrequenciaCalcula(Date di, Date df, String chamada){
           List<FrequenciaEntity> f = new ArrayList<>();
           Query query = em.createQuery("Select f FROM FrequenciaEntity f WHERE f.nome_chamada = :chamada AND f.data_frequencia BETWEEN :di AND :df ");
           query.setParameter("di", di);
           query.setParameter("df", df);
           query.setParameter("chamada", chamada);
           try{
           f = query.getResultList();
           //System.out.println(f.size());
           }catch(NoResultException e){
               f = null;
           }
    return f;
    }
    
    public List<EstuChaEntity> selecioneEstuCha(Date di, Date df, String nome){
           List<EstuChaEntity> f = new ArrayList<>();
           Query query = em.createQuery("Select f FROM EstuChaEntity f WHERE f.nome_estunte = :nome AND f.data_cadastro BETWEEN :di AND :df ");
           query.setParameter("di", di);
           query.setParameter("df", df);
           query.setParameter("nome", nome);
           try{
           f = query.getResultList();
           //System.out.println(f.size());
           }catch(NoResultException e){
               f = null;
           }
    return f;
    }
    
    public boolean verificaFrequenciaCadastrada(String chamada, Date data_hoje){
           boolean ret=true;
           FrequenciaEntity f = new FrequenciaEntity();
           Query query = em.createQuery("Select f FROM FrequenciaEntity f WHERE f.nome_chamada = :chamada AND"
                                        + " f.data_frequencia = :data_hoje");
           query.setParameter("data_hoje", data_hoje);
           query.setParameter("chamada", chamada);
           try{
           f = (FrequenciaEntity) query.getSingleResult();
           }catch(NoResultException e){
               return false;
           }
     return ret;
    }
    
    public FrequenciaEntity selecioneFrequenciaAtualiza(String s, Date d) { 
            FrequenciaEntity f = new FrequenciaEntity();
            Query query = em.createQuery("Select f FROM FrequenciaEntity f WHERE f.nome_chamada = :s AND f.data_frequencia = :d");
            query.setParameter("s", s);
            query.setParameter("d", d);
            try{
            f = (FrequenciaEntity) query.getSingleResult(); 
            }catch(NoResultException e){
               f.setNome_chamada("");
            }
            f.getId();
            f.getNome_chamada();
            f.getData_frequencia();
            f.getEstudantes();
     return f;
    }
    
    public boolean atualizaFruequencia(FrequenciaEntity f){
          boolean ret=true;
         try{
           em.merge(f);
       }catch(java.lang.IllegalArgumentException e){
             return false;
       }     
     return ret;
     }
    
    public boolean removeFrequencia(FrequenciaEntity c){
         boolean ret=true;
         FrequenciaEntity fr = new FrequenciaEntity();
         try{
          fr = em.find(FrequenciaEntity.class, c.getId());
         }catch(java.lang.IllegalArgumentException e){
               if(fr.getId() == null)
                 return false;
         }
         try{
           em.remove(fr);
       }catch(java.lang.IllegalArgumentException e){
              if(fr.getId() == null)
                 return false;
       }     
     return ret;
     }
}
