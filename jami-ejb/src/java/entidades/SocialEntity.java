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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity
public class SocialEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private PessoaEntity pessoa;
    private String rg_social;
    private String fone_social;
    private String profissao_social;
    private String situcao_social;
    @OneToOne
    private EnderecoEntity rua_social;
    private int numero_casa;
    private String complemento_rua;
    private String auxilio_governo;
    private String gestante;
    private String obs_gestante;
    private String acamado;
    private String obs_acamado;
    private String deficiente;
    private String obs_deficiente;
    private String nro_adultos;
    private String nro_joves;      
    private String nro_adolecentes;
    private String nro_criancas;
    private String nro_bebes;
    private String grau_instrucao;
    private String renda_mensal_familiar;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_cadastro; 
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<BeneficioConcedidoEntity> beneficios;           
                
            
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public String getRg_social() {
        return rg_social;
    }

    public void setRg_social(String rg_social) {
        this.rg_social = rg_social;
    }

    public String getFone_social() {
        return fone_social;
    }

    public void setFone_social(String fone_social) {
        this.fone_social = fone_social;
    }

    public String getProfissao_social() {
        return profissao_social;
    }

    public void setProfissao_social(String profissao_social) {
        this.profissao_social = profissao_social;
    }

    public String getSitucao_social() {
        return situcao_social;
    }

    public void setSitucao_social(String situcao_social) {
        this.situcao_social = situcao_social;
    }

    public EnderecoEntity getRua_social() {
        return rua_social;
    }

    public void setRua_social(EnderecoEntity rua_social) {
        this.rua_social = rua_social;
    }

    public int getNumero_casa() {
        return numero_casa;
    }

    public void setNumero_casa(int numero_casa) {
        this.numero_casa = numero_casa;
    }

    public String getComplemento_rua() {
        return complemento_rua;
    }

    public void setComplemento_rua(String complemento_rua) {
        this.complemento_rua = complemento_rua;
    }

    public String getAuxilio_governo() {
        return auxilio_governo;
    }

    public void setAuxilio_governo(String auxilio_governo) {
        this.auxilio_governo = auxilio_governo;
    }

    public String getGestante() {
        return gestante;
    }

    public void setGestante(String gestante) {
        this.gestante = gestante;
    }

    public String getObs_gestante() {
        return obs_gestante;
    }

    public void setObs_gestante(String obs_gestante) {
        this.obs_gestante = obs_gestante;
    }

    public String getAcamado() {
        return acamado;
    }

    public void setAcamado(String acamado) {
        this.acamado = acamado;
    }

    public String getObs_acamado() {
        return obs_acamado;
    }

    public void setObs_acamado(String obs_acamado) {
        this.obs_acamado = obs_acamado;
    }

    public String getDeficiente() {
        return deficiente;
    }

    public void setDeficiente(String deficiente) {
        this.deficiente = deficiente;
    }

    public String getObs_deficiente() {
        return obs_deficiente;
    }

    public void setObs_deficiente(String obs_deficiente) {
        this.obs_deficiente = obs_deficiente;
    }

    public String getNro_adultos() {
        return nro_adultos;
    }

    public void setNro_adultos(String nro_adultos) {
        this.nro_adultos = nro_adultos;
    }

    public String getNro_joves() {
        return nro_joves;
    }

    public void setNro_joves(String nro_joves) {
        this.nro_joves = nro_joves;
    }

    public String getNro_adolecentes() {
        return nro_adolecentes;
    }

    public void setNro_adolecentes(String nro_adolecentes) {
        this.nro_adolecentes = nro_adolecentes;
    }

    public String getNro_criancas() {
        return nro_criancas;
    }

    public void setNro_criancas(String nro_criancas) {
        this.nro_criancas = nro_criancas;
    }

    public String getNro_bebes() {
        return nro_bebes;
    }

    public void setNro_bebes(String nro_bebes) {
        this.nro_bebes = nro_bebes;
    }

    public String getGrau_instrucao() {
        return grau_instrucao;
    }

    public void setGrau_instrucao(String grau_instrucao) {
        this.grau_instrucao = grau_instrucao;
    }

    public String getRenda_mensal_familiar() {
        return renda_mensal_familiar;
    }

    public void setRenda_mensal_familiar(String renda_mensal_familiar) {
        this.renda_mensal_familiar = renda_mensal_familiar;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public List<BeneficioConcedidoEntity> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(List<BeneficioConcedidoEntity> beneficios) {
        this.beneficios = beneficios;
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
        if (!(object instanceof SocialEntity)) {
            return false;
        }
        SocialEntity other = (SocialEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SocialEntity[ id=" + id + " ]";
    }
    
}
