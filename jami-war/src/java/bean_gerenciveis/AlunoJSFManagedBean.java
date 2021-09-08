/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_gerenciveis;

import beans_sessao.FinanceiroSessionBean;
import beans_sessao.GerenteSessionBean;
import beans_sessao.OperacionalSessionBean;
import entidades.AlergiaEntity;
import entidades.AlunoEntity;
import entidades.CartorioEntity;
import entidades.CidadeEntity;
import entidades.EnderecoEntity;
import entidades.EscolaEntity;
import entidades.IrmaoEntity;
import entidades.MaeEntity;
import entidades.PaiEntity;
import entidades.PessoaEntity;
import entidades.ProblemaSaudeEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author petterson
 */
@Named(value = "alunoJSFManagedBean")
@SessionScoped
public class AlunoJSFManagedBean implements Serializable {

    @EJB
    OperacionalSessionBean ops; 
    @EJB
    FinanceiroSessionBean fin;
    @EJB
    GerenteSessionBean geb;
    long id;
    String nome;
    String erro;
    Date data_matricula;
    String fone;
    //@CPF (message="Cpf Inválido")
    String cpf_aluno;
    String rg;
    String orgaoexpedidor;
    Date dataexpedicao;
    String sexo;
    Date datanascimento;
    int certidaonascimento;
    int livro;
    int folha;
    String cartorio;
    String rua;
    int numero_casa;
    String referencia;
    String municipio_nascimento;
    String raca;
    String escola;
    int numeromatricula;
    String serie;
    String turno;
    String bolsafamilia;
    String pai;
    String mae;
    String situacao;
    ArrayList<String> list_problemas;
    String problema_entity;
    ArrayList<String> list_cartorios;
    String cartorio_entity;
    ArrayList<String> list_ruas;
    String rua_entity;
    ArrayList<String> list_municipio_nascimentos;
    String cidade_entity;
    ArrayList<String> list_escolas;
    String escola_entity;
    ArrayList<String> list_alergias;
    String alergia_entity;
    ArrayList<String> list_pais;
    String pai_entity;
    ArrayList<String> list_maes;
    String mae_entity;
    ArrayList<String> list_irmaos;
    String irmao_entity;
    ArrayList<String> list_irmaos_selecionados;
    ArrayList<String> list_alergias_selecionadas;
    ArrayList<String> list_problemas_saude_selecionados;
    ArrayList<String> list_sexo;
    ArrayList<String> list_racas;
    ArrayList<String> list_series;
    ArrayList<String> list_turnos;
    ArrayList<String> list_bolsa_familias;
    ArrayList<String> list_situacoes;
    ArrayList<String> list_orgaos_espedidor;
    PessoaEntity p;
    @Inject
    TurmaSFManagedBean tum;

    
    
    public AlunoJSFManagedBean() {
        this.list_cartorios = new ArrayList<>();
        this.list_problemas = new ArrayList<>();
        this.list_alergias = new ArrayList<>();
        this.list_escolas = new ArrayList<>();
        this.list_irmaos = new ArrayList<>();
        this.list_maes = new ArrayList<>();
        this.list_pais = new ArrayList<>();
        this.list_municipio_nascimentos = new ArrayList<>();
        this.list_ruas = new ArrayList<>();
        this.list_orgaos_espedidor = new ArrayList<>();
        this.list_orgaos_espedidor.add("Secretaria Segurança Publica SC");
        this.list_orgaos_espedidor.add("Outro");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    } 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public Date getData_matricula() {
        return data_matricula;
    }

    public void setData_matricula(Date data_matricula) {
        this.data_matricula = data_matricula;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    
    public String getCpf_aluno() {
        return cpf_aluno;
    }

    public void setCpf_aluno(String cpf_aluno) {
        this.cpf_aluno = cpf_aluno;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoexpedidor() {
        return orgaoexpedidor;
    }

    public void setOrgaoexpedidor(String orgaoexpedidor) {
        this.orgaoexpedidor = orgaoexpedidor;
    }

    public Date getDataexpedicao() {
        return dataexpedicao;
    }

    public void setDataexpedicao(Date dataexpedicao) {
        this.dataexpedicao = dataexpedicao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public int getCertidaonascimento() {
        return certidaonascimento;
    }

    public void setCertidaonascimento(int certidaonascimento) {
        this.certidaonascimento = certidaonascimento;
    }

    public int getLivro() {
        return livro;
    }

    public void setLivro(int livro) {
        this.livro = livro;
    }

    public int getFolha() {
        return folha;
    }

    public void setFolha(int folha) {
        this.folha = folha;
    }

    public String getCartorio() {
        return cartorio;
    }

    public void setCartorio(String cartorio) {
        this.cartorio = cartorio;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero_casa() {
        return numero_casa;
    }

    public void setNumero_casa(int numero_casa) {
        this.numero_casa = numero_casa;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMunicipio_nascimento() {
        return municipio_nascimento;
    }

    public void setMunicipio_nascimento(String municipio_nascimento) {
        this.municipio_nascimento = municipio_nascimento;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public int getNumeromatricula() {
        return numeromatricula;
    }

    public void setNumeromatricula(int numeromatricula) {
        this.numeromatricula = numeromatricula;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public ArrayList<String> getList_alergias_selecionadas() {
        return list_alergias_selecionadas;
    }

    public void setList_alergias_selecionadas(ArrayList<String> list_alergias_selecionadas) {
        this.list_alergias_selecionadas = list_alergias_selecionadas;
    }

    public ArrayList<String> getList_problemas_saude_selecionados() {
        return list_problemas_saude_selecionados;
    }

    public void setList_problemas_saude_selecionados(ArrayList<String> list_problemas_saude_selecionados) {
        this.list_problemas_saude_selecionados = list_problemas_saude_selecionados;
    }
    
    public String getBolsafamilia() {
        return bolsafamilia;
    }

    public void setBolsafamilia(String bolsafamilia) {
        this.bolsafamilia = bolsafamilia;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }
    
     public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<String> getList_problemas() {
        return ops.selecioneProblemaSaude();
    }

    public void setList_problemas(ArrayList<String> list_problemas) {
        this.list_problemas = list_problemas;
    }

    public List<String> getList_cartorios() {
        return ops.selecioneCartorios();
    }

    public void setList_cartorios(ArrayList<String> list_cartorios) {
        this.list_cartorios = list_cartorios;
    }

    public List<String> getList_ruas() {
        return ops.selecioneRuas();
    }

    public void setList_ruas(ArrayList<String> list_ruas) {
        this.list_ruas = list_ruas;
    }

    public List<String> getList_municipio_nascimentos() {
        return ops.selecioneCidades();
    }

    public void setList_municipio_nascimentos(ArrayList<String> list_municipio_nascimentos) {
        this.list_municipio_nascimentos = list_municipio_nascimentos;
    }

    public List<String> getList_escolas() {
        return ops.selecioneEscolas();
    }

    public void setList_escolas(ArrayList<String> list_escolas) {
        this.list_escolas = list_escolas;
    }

    public List<String> getList_alergias() {
        return ops.selecioneAlergias();
    }

    public void setList_alergias(ArrayList<String> list_alergias) {
        this.list_alergias = list_alergias;
    }

    public List<String> getList_pais() {
        return ops.selecionePais();
    }

    public void setList_pais(ArrayList<String> list_pais) {
        this.list_pais = list_pais;
    }

    public List<String> getList_maes() {
        return ops.selecioneMaes();
    }

    public void setList_maes(ArrayList<String> list_maes) {
        this.list_maes = list_maes;
    }

    public List<String> getList_irmaos() {
        return ops.selecioneIrmaos();
    }

    public void setList_irmaos(ArrayList<String> list_irmaos) {
        this.list_irmaos = list_irmaos;
    }

    public ArrayList<String> getList_irmaos_selecionados() {
        return list_irmaos_selecionados;
    }

    public void setList_irmaos_selecionados(ArrayList<String> list_irmaos_selecionados) {
        this.list_irmaos_selecionados = list_irmaos_selecionados;
    }

    public String getCartorio_entity() {
        return cartorio_entity;
    }

    public void setCartorio_entity(String cartorio_entity) { 
            this.list_cartorios.add(cartorio_entity);
        this.cartorio_entity = cartorio_entity;
    }

    public String getRua_entity() {
        return rua_entity;
    }

    public void setRua_entity(String rua_entity) {
             this.list_ruas.add(rua_entity);
        this.rua_entity = rua_entity;
    }

    public String getCidade_entity() {
        return cidade_entity;
    }

    public void setCidade_entity(String cidade_entity) {
            this.list_municipio_nascimentos.add(cidade_entity);
        this.cidade_entity = cidade_entity;
    }

    public String getEscola_entity() {
        return escola_entity;
    }

    public void setEscola_entity(String escola_entity) {
           this.list_escolas.add(escola_entity);
        this.escola_entity = escola_entity;
    }

    public String getAlergia_entity() {
        return alergia_entity;
    }

    public void setAlergia_entity(String alergia_entity) {
         this.list_alergias.add(alergia_entity);
     this.alergia_entity = alergia_entity;
    }

    public String getPai_entity() {
        return pai_entity;
    }

    public void setPai_entity(String pai_entity) {
        this.pai_entity = pai_entity;
    }

    public String getMae_entity() {
        return mae_entity;
    }

    public void setMae_entity(String mae_entity) {
          this.list_maes.add(mae_entity);
        this.mae_entity = mae_entity;
    }

    public String getIrmao_entity() {
        return irmao_entity;
    }

    public void setIrmao_entity(String irmao_entity) {
        this.irmao_entity = irmao_entity;
    }

    public String getProblema_entity() {
        return problema_entity;
    }

    public void setProblema_entity(String problema_entity) {
         this.list_problemas.add(problema_entity);
        this.problema_entity = problema_entity;
    }

    public ArrayList<String> getList_sexo() {
        return list_sexo;
    }

    public void setList_sexo(ArrayList<String> list_sexo) {
        this.list_sexo = list_sexo;
    }

    public ArrayList<String> getList_racas() {
        return list_racas;
    }

    public void setList_racas(ArrayList<String> list_racas) {
        this.list_racas = list_racas;
    }

    public ArrayList<String> getList_series() {
        return list_series;
    }

    public void setList_series(ArrayList<String> list_series) {
        this.list_series = list_series;
    }

    public ArrayList<String> getList_turnos() {
        return list_turnos;
    }

    public void setList_turnos(ArrayList<String> list_turnos) {
        this.list_turnos = list_turnos;
    }

    public ArrayList<String> getList_bolsa_familias() {
        return list_bolsa_familias;
    }

    public void setList_bolsa_familias(ArrayList<String> list_bolsa_familias) {
        this.list_bolsa_familias = list_bolsa_familias;
    }

    public ArrayList<String> getList_situacoes() {
        return list_situacoes;
    }

    public void setList_situacoes(ArrayList<String> list_situacoes) {
        this.list_situacoes = list_situacoes;
    }

    public ArrayList<String> getList_orgaos_espedidor() {
        return list_orgaos_espedidor;
    }

    public PessoaEntity getP() {
        return p;
    }

    public void setP(PessoaEntity p) {
        this.p = p;
    }

    public void setList_orgaos_espedidor(ArrayList<String> list_orgaos_espedidor) {
        this.list_orgaos_espedidor = list_orgaos_espedidor;
    }
    
    public String cadastrarAluno(){
     if(nome.equals("") || id > 1 || rg.equals("") || data_matricula == null || dataexpedicao == null || datanascimento == null
        || orgaoexpedidor == null || certidaonascimento <=0 || livro <= 0 || folha <=0 || cartorio == null || sexo == null
        || rua ==null || numero_casa <=0 || fone.equals("") || municipio_nascimento == null || raca ==null || situacao == null
        || list_alergias_selecionadas.size() <= 0 || list_problemas_saude_selecionados.size() <= 0 
             || list_irmaos_selecionados.size() <= 0|| pai == null || mae == null || bolsafamilia == null || escola == null 
             || numeromatricula <= 0 || serie == null || turno == null || cpf_aluno.equals("")){
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
        FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Preencha os Campos Corretamente!"));
            return "preencha os campos";
   }else{
            PessoaEntity pe = new PessoaEntity();
            pe.setId(id);
            pe.setNome(nome);
            pe.setCpf(cpf_aluno);
            pe.setData_nasc(data_matricula);
            if(geb.cadastrePessoa(pe)){
               this.p = geb.selecionePessoaAtualiza(nome);
            CartorioEntity c = ops.selecioneCartorioAtualiza(cartorio);
            EnderecoEntity e = fin.selecioneRuasPorNome(rua);
            EscolaEntity s = ops.selecioneEscolaAtualiza(escola);
            CidadeEntity ci = ops.selecioneCidadeAtualiza(municipio_nascimento);
            List<AlergiaEntity> l_a = ops.selecioneListAlergias(list_alergias_selecionadas);
            List<IrmaoEntity> l_i = ops.selecioneListaIrmaos(list_irmaos_selecionados);
            List<ProblemaSaudeEntity> p_s = ops.selecioneListaProblemas(list_problemas_saude_selecionados);
            PaiEntity p = ops.selecionePaiAtualiza(pai);
            MaeEntity m = ops.selecioneMaeAtualiza(mae);
        AlunoEntity alu = new AlunoEntity(); 
        alu.setId(id);
        alu.setData_matricula(data_matricula);
        alu.setFone_aluno(fone);
        alu.setRg(rg);
        alu.setOrgao_expedidor(orgaoexpedidor);
        alu.setData_expedicao(dataexpedicao);
        alu.setSexo(sexo);
        alu.setNumero_certidao(certidaonascimento);
        alu.setLivro_certidao(livro);
        alu.setFolha_certidao(folha);
        alu.setCartorio(c);
        alu.setRua_aluno(e);
        alu.setNumero_casa(numero_casa);
        alu.setPonto_referencia(referencia);
        alu.setCidade_nascimento(ci);
        alu.setCor_raca(raca);
        alu.setEscola(s);
        alu.setMatricula_escola(numeromatricula);
        alu.setSerie(serie);
        alu.setTurno(turno);
        alu.setAlergia(l_a);
        alu.setProblema_saude(p_s);
        alu.setBolsa_familia(bolsafamilia);
        alu.setIrmaos(l_i);
        alu.setPai(p);
        alu.setMae(m);
        alu.setPessoa(this.p);
        alu.setSituacao(situacao);
                s.getList_aluno().add(alu);
                c.getList_aluno().add(alu);
                ci.getList_aluno().add(alu);
                p.getList_aluno().add(alu);
                m.getList_aluno().add(alu);
                e.getList_aluno().add(alu);
        if(ops.cadastreAluno(alu)){
           ops.atualizaEscola(s);
           ops.atualizaCartorio(c);
           ops.atualizaCidade(ci);
           ops.atualizaPai(p);
           ops.atualizaMae(m);
           ops.atualizaEndereco(e);
        for(int i=0; i< l_a.size(); i++){
            AlergiaEntity a = l_a.get(i);
            a.getList_aluno().add(alu);
            ops.atualizaAlergia(a);
        }
        for(int i=0; i<l_i.size(); i++){
            IrmaoEntity ir = l_i.get(i);
            ir.getList_aluno().add(alu);
           ops.atualizaIrmao(ir);
        }
        for(int i=0; i<p_s.size(); i++){
            ProblemaSaudeEntity pr = p_s.get(i);
            pr.getList_aluno().add(alu);
            ops.atualizaProblema(pr);
        }
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
           tum.setNome_aluno_entity(this.p.getNome());
           this.apagaObjeto();
        }
       }else{
              AlunoEntity alu = new AlunoEntity();
                    alu = ops.selecioneAlunoAtualiza(nome);
                    if(alu.getFone_aluno().equals("")){
                       this.p = geb.selecionePessoaAtualiza(nome);
                       if(this.p.getNome().equals("")){
                          FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("O CPPF está errado!"));
                          return nome+" :  CPF está errado!";
                 }else{
                       CartorioEntity c = ops.selecioneCartorioAtualiza(cartorio);
            EnderecoEntity e = fin.selecioneRuasPorNome(rua);
            EscolaEntity s = ops.selecioneEscolaAtualiza(escola);
            CidadeEntity ci = ops.selecioneCidadeAtualiza(municipio_nascimento);
            List<AlergiaEntity> l_a = ops.selecioneListAlergias(list_alergias_selecionadas);
            List<IrmaoEntity> l_i = ops.selecioneListaIrmaos(list_irmaos_selecionados);
            List<ProblemaSaudeEntity> p_s = ops.selecioneListaProblemas(list_problemas_saude_selecionados);
            PaiEntity p = ops.selecionePaiAtualiza(pai);
            MaeEntity m = ops.selecioneMaeAtualiza(mae);
        //AlunoEntity alu = new AlunoEntity(); 
        alu.setId(id);
        alu.setData_matricula(data_matricula);
        alu.setFone_aluno(fone);
        alu.setRg(rg);
        alu.setOrgao_expedidor(orgaoexpedidor);
        alu.setData_expedicao(dataexpedicao);
        alu.setSexo(sexo);
        alu.setNumero_certidao(certidaonascimento);
        alu.setLivro_certidao(livro);
        alu.setFolha_certidao(folha);
        alu.setCartorio(c);
        alu.setRua_aluno(e);
        alu.setNumero_casa(numero_casa);
        alu.setPonto_referencia(referencia);
        alu.setCidade_nascimento(ci);
        alu.setCor_raca(raca);
        alu.setEscola(s);
        alu.setMatricula_escola(numeromatricula);
        alu.setSerie(serie);
        alu.setTurno(turno);
        alu.setAlergia(l_a);
        alu.setProblema_saude(p_s);
        alu.setBolsa_familia(bolsafamilia);
        alu.setIrmaos(l_i);
        alu.setPai(p);
        alu.setMae(m);
        alu.setPessoa(this.p);
        alu.setSituacao(situacao);
                s.getList_aluno().add(alu);
                c.getList_aluno().add(alu);
                ci.getList_aluno().add(alu);
                p.getList_aluno().add(alu);
                m.getList_aluno().add(alu);
                e.getList_aluno().add(alu);
        if(ops.cadastreAluno(alu)){
           ops.atualizaEscola(s);
           ops.atualizaCartorio(c);
           ops.atualizaCidade(ci);
           ops.atualizaPai(p);
           ops.atualizaMae(m);
           ops.atualizaEndereco(e);
        for(int i=0; i< l_a.size(); i++){
            AlergiaEntity a = l_a.get(i);
            a.getList_aluno().add(alu);
            ops.atualizaAlergia(a);
        }
        for(int i=0; i<l_i.size(); i++){
            IrmaoEntity ir = l_i.get(i);
            ir.getList_aluno().add(alu);
           ops.atualizaIrmao(ir);
        }
        for(int i=0; i<p_s.size(); i++){
            ProblemaSaudeEntity pr = p_s.get(i);
            pr.getList_aluno().add(alu);
            ops.atualizaProblema(pr);
        }
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Inserido!"));
           tum.setNome_aluno_entity(this.p.getNome());
           this.apagaObjeto();
        }
                       }
            }else{
                  FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage(nome+" :  Já é Aluno!"));
                  this.apagaObjeto();
                  return nome+" :  Já é Aluno!";      
                 }
            }
        }
    return "operacional?faces-redirect=true";
    }
    
    public void buscarAluno(){
         if(nome.equals("")){
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Digite um Nome Válido");
            ctx.addMessage(null, msg);
          }else{
            AlunoEntity a = new AlunoEntity();
         a = ops.selecioneAlunoAtualiza(nome);
         if(a.getFone_aluno().equals("")){
           FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Digite o Nome Correto"));
      }else{
             this.p = a.getPessoa();
             id = a.getId();
             nome = this.p.getNome();
             data_matricula = a.getData_matricula();
             fone = a.getFone_aluno();
             cpf_aluno = this.p.getCpf();
             rg = a.getRg();
             orgaoexpedidor = a.getOrgao_expedidor();
             dataexpedicao = a.getData_expedicao();
             sexo = a.getSexo();
             datanascimento = this.p.getData_nasc();
             certidaonascimento = a.getNumero_certidao();
             livro = a.getLivro_certidao();
             folha = a.getFolha_certidao();
             cartorio = a.getCartorio().getNome_cartorio();
             rua = a.getRua_aluno().getLogradouro();
             numero_casa = a.getNumero_casa();
             referencia = a.getPonto_referencia();
             municipio_nascimento = a.getCidade_nascimento().getNome_cidade();
             raca = a.getCor_raca();
             escola = a.getEscola().getNome_escola();
             numeromatricula = a.getMatricula_escola();
             serie = a.getSerie();
             turno = a.getTurno();
             list_alergias_selecionadas = this.preencheNomeAlergia(a.getAlergia());
             list_problemas_saude_selecionados = this.preencheNomeProblema(a.getProblema_saude());
             bolsafamilia = a.getBolsa_familia();
             list_irmaos_selecionados = this.preencheNomeIrmao(a.getIrmaos());
             pai = a.getPai().getPessoa().getNome();
             mae = a.getMae().getPessoa().getNome();
             situacao = a.getSituacao();
             FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Encontrado!"));
           }
        }
    }
    
    public void atualizarAluno(){
         if(nome.equals("") || id < 1 || rg.equals("") || data_matricula == null || dataexpedicao == null || datanascimento == null
           || orgaoexpedidor == null || certidaonascimento <=0 || livro <= 0 || folha <=0 || cartorio == null || sexo == null
           || rua ==null || numero_casa <=0 || fone.equals("") || municipio_nascimento == null || raca ==null || situacao == null
           || list_alergias_selecionadas.size() <= 0 || list_problemas_saude_selecionados.size() <= 0 || list_irmaos_selecionados.size() <= 0
           || pai == null || mae == null || bolsafamilia == null || escola == null || numeromatricula <= 0 || serie == null
           || turno == null || cpf_aluno.equals("")){
           FacesContext ctx = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
           ctx.addMessage(null, msg);
           // return "preencha os campos";
     }else {
            CartorioEntity c = ops.selecioneCartorioAtualiza(cartorio);
            EnderecoEntity e = fin.selecioneRuasPorNome(rua);
            EscolaEntity s = ops.selecioneEscolaAtualiza(escola);
            CidadeEntity ci = ops.selecioneCidadeAtualiza(municipio_nascimento);
            List<AlergiaEntity> l_a = ops.selecioneListAlergias(list_alergias_selecionadas);
            List<IrmaoEntity> l_i = ops.selecioneListaIrmaos(list_irmaos_selecionados);
            List<ProblemaSaudeEntity> p_s = ops.selecioneListaProblemas(list_problemas_saude_selecionados);
            PaiEntity p = ops.selecionePaiAtualiza(pai);
            MaeEntity m = ops.selecioneMaeAtualiza(mae);
             AlunoEntity a = new AlunoEntity();
             a.setId(id);
             a.setData_matricula(data_matricula);
             a.setFone_aluno(fone);
             a.setRg(rg);
             a.setOrgao_expedidor(orgaoexpedidor);
             a.setData_expedicao(dataexpedicao);
             a.setSexo(sexo);
             a.setNumero_certidao(certidaonascimento);
             a.setLivro_certidao(livro);
             a.setFolha_certidao(folha);
             a.setCartorio(c);
             a.setRua_aluno(e);
             a.setNumero_casa(numero_casa);
             a.setPonto_referencia(referencia);
             a.setCidade_nascimento(ci);
             a.setCor_raca(raca);
             a.setEscola(s);
             a.setMatricula_escola(numeromatricula);
             a.setSerie(serie);
             a.setTurno(turno);
             a.setAlergia(l_a);
             a.setProblema_saude(p_s);
             a.setBolsa_familia(bolsafamilia);
             a.setIrmaos(l_i);
             a.setPai(p);
             a.setMae(m);
             a.setSituacao(situacao);
             a.setPessoa(this.p);
             s.getList_aluno().add(a);
                c.getList_aluno().add(a);
                ci.getList_aluno().add(a);
                p.getList_aluno().add(a);
                m.getList_aluno().add(a);
                e.getList_aluno().add(a);
             if(ops.atualizaAluno(a)){
                 ops.atualizaEscola(s);
           ops.atualizaCartorio(c);
           ops.atualizaCidade(ci);
           ops.atualizaPai(p);
           ops.atualizaMae(m);
           ops.atualizaEndereco(e);
        for(int i=0; i< l_a.size(); i++){
            AlergiaEntity ale = l_a.get(i);
            ale.getList_aluno().add(a);
            ops.atualizaAlergia(ale);
        }
        for(int i=0; i<l_i.size(); i++){
            IrmaoEntity ir = l_i.get(i);
            ir.getList_aluno().add(a);
           ops.atualizaIrmao(ir);
        }
        for(int i=0; i<p_s.size(); i++){
            ProblemaSaudeEntity pr = p_s.get(i);
            pr.getList_aluno().add(a);
            ops.atualizaProblema(pr);
        }
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Atualizado!"));
                 this.apagaObjeto();
             }else{
                FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Não Atualizado!"));
             }
          }
    }
     
    public void excluirAluno(){
         if(nome.equals("") || id < 1 || rg.equals("") || data_matricula == null || dataexpedicao == null || datanascimento == null
           || orgaoexpedidor == null || certidaonascimento <=0 || livro <= 0 || folha <=0 || cartorio == null || sexo == null
           || rua ==null || numero_casa <=0 || fone.equals("") || municipio_nascimento == null || raca ==null || situacao == null
           || list_alergias_selecionadas.size() <= 0 || list_problemas_saude_selecionados.size() <= 0 || list_irmaos_selecionados.size() <= 0
           || pai == null || mae == null || bolsafamilia == null || escola == null || numeromatricula <= 0 || serie == null
           || turno == null || cpf_aluno.equals("")){
              FacesContext ctx = FacesContext.getCurrentInstance();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Campo Nulo");
              ctx.addMessage(null, msg);
       }else{
            CartorioEntity c = ops.selecioneCartorioAtualiza(cartorio);
            EnderecoEntity e = fin.selecioneRuasPorNome(rua);
            EscolaEntity s = ops.selecioneEscolaAtualiza(escola);
            CidadeEntity ci = ops.selecioneCidadeAtualiza(municipio_nascimento);
            List<AlergiaEntity> l_a = ops.selecioneListAlergias(list_alergias_selecionadas);
            List<IrmaoEntity> l_i = ops.selecioneListaIrmaos(list_irmaos_selecionados);
            List<ProblemaSaudeEntity> p_s = ops.selecioneListaProblemas(list_problemas_saude_selecionados);
            PaiEntity p = ops.selecionePaiAtualiza(pai);
            MaeEntity m = ops.selecioneMaeAtualiza(mae);
                AlunoEntity a = new AlunoEntity();
                a.setId(id);
                a.setData_matricula(data_matricula);
                a.setFone_aluno(fone);
                a.setRg(rg);
                a.setOrgao_expedidor(orgaoexpedidor);
                a.setData_expedicao(dataexpedicao);
                a.setSexo(sexo);
                a.setNumero_certidao(certidaonascimento);
                a.setLivro_certidao(livro);
                a.setFolha_certidao(folha);
                a.setCartorio(c);
                a.setRua_aluno(e);
                a.setNumero_casa(numero_casa);
                a.setPonto_referencia(referencia);
                a.setCidade_nascimento(ci);
                a.setCor_raca(raca);
                a.setEscola(s);
                a.setMatricula_escola(numeromatricula);
                a.setSerie(serie);
                a.setTurno(turno);
                a.setAlergia(l_a);
                a.setProblema_saude(p_s);
                a.setBolsa_familia(bolsafamilia);
                a.setIrmaos(l_i);
                a.setPai(p);
                a.setMae(m);
                a.setSituacao(situacao);
                a.setPessoa(this.p);
                if(ops.removeAluno(a))
                   FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("Removido!"));
              else
                 FacesContext.getCurrentInstance().addMessage("formPermissoes: xxx", new FacesMessage("ID não válido!"));
           }
           this.apagaObjeto();
    } 
    
    private ArrayList<String> preencheNomeIrmao(List<IrmaoEntity> m){
          ArrayList<String> al = new ArrayList<>();        
          for(int i=0; i<m.size(); i++){
              String s = m.get(i).getPessoa().getNome();
              al.add(s);
          }
      return al;
    } 
    
    private ArrayList<String> preencheNomeProblema(List<ProblemaSaudeEntity> p){
          ArrayList<String> al = new ArrayList<>();        
          for(int i=0; i<p.size(); i++){
              String s = p.get(i).getProblema_saude();
              al.add(s);
          }
      return al;
    } 
    
    private ArrayList<String> preencheNomeAlergia(List<AlergiaEntity> a){
          ArrayList<String> al = new ArrayList<>();        
          for(int i=0; i<a.size(); i++){
              String s = a.get(i).getNome_alergia();
              al.add(s);
          }
      return al;
    } 
    
    private void apagaObjeto(){
             id = 0;
             nome = "";
             cpf_aluno = "";
             rg ="";
             data_matricula = null;
             fone ="";
             orgaoexpedidor ="";
             dataexpedicao = null;
             sexo = "";
             datanascimento = null;
             certidaonascimento = 0;
             livro = 0;
             folha = 0;
             cartorio = "";
             rua = "";
             numero_casa = 0;
             referencia = "";
             municipio_nascimento ="";
             raca = "";
             escola = "";
             numeromatricula = 0;
             serie = "";
             turno = "";
             list_alergias_selecionadas.clear();
             list_problemas_saude_selecionados.clear();
             bolsafamilia = "";
             list_irmaos_selecionados.clear();
             pai = "";
             mae = "";
             situacao ="";
             this.p = null;
    }
    
     public Date dataAtual(){
      
      return new Date();
      }
}
