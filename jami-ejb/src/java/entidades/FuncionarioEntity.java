/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity
public class FuncionarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private PessoaEntity pessoa;
    private String rg;
    private String fone_funcionario;
    @OneToOne
    private EnderecoEntity rua_funcionario;
    private int numero_casa;
    private String clt_funcionario;
    private String pis_funcionario;
    @OneToOne//(cascade = CascadeType.REMOVE)
    private DepartamentoEntity departamento;
    @OneToOne
    private CargoEntity cargo_funcionario;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_contratacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getFone_funcionario() {
        return fone_funcionario;
    }

    public void setFone_funcionario(String fone_funcionario) {
        this.fone_funcionario = fone_funcionario;
    }

    public EnderecoEntity getRua_funcionario() {
        return rua_funcionario;
    }

    public void setRua_funcionario(EnderecoEntity rua_funcionario) {
        this.rua_funcionario = rua_funcionario;
    }

    public int getNumero_casa() {
        return numero_casa;
    }

    public void setNumero_casa(int numero_casa) {
        this.numero_casa = numero_casa;
    }

    public String getClt_funcionario() {
        return clt_funcionario;
    }

    public void setClt_funcionario(String clt_funcionario) {
        this.clt_funcionario = clt_funcionario;
    }

    public String getPis_funcionario() {
        return pis_funcionario;
    }

    public void setPis_funcionario(String pis_funcionario) {
        this.pis_funcionario = pis_funcionario;
    }

    public CargoEntity getCargo_funcionario() {
        return cargo_funcionario;
    }

    public void setCargo_funcionario(CargoEntity cargo_funcionario) {
        this.cargo_funcionario = cargo_funcionario;
    }

    public Date getData_contratacao() {
        return data_contratacao;
    }

    public void setData_contratacao(Date data_contratacao) {
        this.data_contratacao = data_contratacao;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
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
        if (!(object instanceof FuncionarioEntity)) {
            return false;
        }
        FuncionarioEntity other = (FuncionarioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FumcionarioEntity[ id=" + id + " ]";
    }
    
}
