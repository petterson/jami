/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity 
public class AlunoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_matricula;
    private String fone_aluno;
    private String rg;
    private String orgao_expedidor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_expedicao;
    private String sexo;
    @OneToOne
    private PessoaEntity pessoa;
    private int numero_certidao;
    private int livro_certidao;
    private int folha_certidao;
    @OneToOne(cascade=CascadeType.REMOVE)
    private CartorioEntity cartorio;
    @OneToOne(cascade=CascadeType.REMOVE)
    private EnderecoEntity rua_aluno;
    private int numero_casa;
    private String ponto_referencia;
    @OneToOne(cascade=CascadeType.REMOVE)
    private CidadeEntity cidade_nascimento;
    private String cor_raca;
    @OneToOne(cascade=CascadeType.REMOVE) 
    private EscolaEntity escola;
    private int matricula_escola;
    private String serie;
    private String turno;
    @ManyToMany 
    private List<AlergiaEntity> alergia;
    @ManyToMany 
    private List<ProblemaSaudeEntity> problema_saude;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<IrmaoEntity> irmaos; 
    private String bolsa_familia;
    @OneToOne(cascade=CascadeType.REMOVE)
    private PaiEntity pai;
    @OneToOne(cascade=CascadeType.REMOVE) 
    private MaeEntity mae;
    private String situacao;
    
    
    public AlunoEntity(){}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_matricula() {
        return data_matricula;
    }

    public void setData_matricula(Date data_matricula) {
        this.data_matricula = data_matricula;
    }

    public String getFone_aluno() {
        return fone_aluno;
    }

    public void setFone_aluno(String fone_aluno) {
        this.fone_aluno = fone_aluno;
    }
    
    

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgao_expedidor() {
        return orgao_expedidor;
    }

    public void setOrgao_expedidor(String orgao_expedidor) {
        this.orgao_expedidor = orgao_expedidor;
    }

    public Date getData_expedicao() {
        return data_expedicao;
    }

    public void setData_expedicao(Date data_expedicao) {
        this.data_expedicao = data_expedicao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getNumero_certidao() {
        return numero_certidao;
    }

    public void setNumero_certidao(int numero_certidao) {
        this.numero_certidao = numero_certidao;
    }

    public int getLivro_certidao() {
        return livro_certidao;
    }

    public void setLivro_certidao(int livro_certidao) {
        this.livro_certidao = livro_certidao;
    }

    public int getFolha_certidao() {
        return folha_certidao;
    }

    public void setFolha_certidao(int folha_certidao) {
        this.folha_certidao = folha_certidao;
    }

    public int getNumero_casa() {
        return numero_casa;
    }

    public void setNumero_casa(int numero_casa) {
        this.numero_casa = numero_casa;
    }

    public String getPonto_referencia() {
        return ponto_referencia;
    }

    public void setPonto_referencia(String ponto_referencia) {
        this.ponto_referencia = ponto_referencia;
    }

    public String getCor_raca() {
        return cor_raca;
    }

    public void setCor_raca(String cor_raca) {
        this.cor_raca = cor_raca;
    }

    public int getMatricula_escola() {
        return matricula_escola;
    }

    public void setMatricula_escola(int matricula_escola) {
        this.matricula_escola = matricula_escola;
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

    public String getBolsa_familia() {
        return bolsa_familia;
    }

    public void setBolsa_familia(String bolsa_familia) {
        this.bolsa_familia = bolsa_familia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public CartorioEntity getCartorio() {
        return cartorio;
    }

    public void setCartorio(CartorioEntity cartorio) {
        this.cartorio = cartorio;
    }

    public EnderecoEntity getRua_aluno() {
        return rua_aluno;
    }

    public void setRua_aluno(EnderecoEntity rua_aluno) {
        this.rua_aluno = rua_aluno;
    }

    public CidadeEntity getCidade_nascimento() {
        return cidade_nascimento;
    }

    public void setCidade_nascimento(CidadeEntity cidade_nascimento) {
        this.cidade_nascimento = cidade_nascimento;
    }

    public EscolaEntity getEscola() {
        return escola;
    }

    public void setEscola(EscolaEntity escola) {
        this.escola = escola;
    }

    public List<AlergiaEntity> getAlergia() {
        return alergia;
    }

    public void setAlergia(List<AlergiaEntity> alergia) {
        this.alergia = alergia;
    }

    public List<ProblemaSaudeEntity> getProblema_saude() {
        return problema_saude;
    }

    public void setProblema_saude(List<ProblemaSaudeEntity> problema_saude) {
        this.problema_saude = problema_saude;
    }

    public List<IrmaoEntity> getIrmaos() {
        return irmaos;
    }

    public void setIrmaos(List<IrmaoEntity> irmaos) {
        this.irmaos = irmaos;
    }

    public PaiEntity getPai() {
        return pai;
    }

    public void setPai(PaiEntity pai) {
        this.pai = pai;
    }

    public MaeEntity getMae() {
        return mae;
    }

    public void setMae(MaeEntity mae) {
        this.mae = mae;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlunoEntity)) {
            return false;
        }
        AlunoEntity other = (AlunoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AlunoEntity[ id=" + id + " ]";
    }
    
}
