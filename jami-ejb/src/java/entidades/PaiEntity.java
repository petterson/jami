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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity 
public class PaiEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private PessoaEntity pessoa;
    private String rg_pai;
    private String profissao_pai;
    private String local_trabalho_pai;
    private String fone_pai;
    private String escolaridade_pai;
    @OneToOne(cascade=CascadeType.REMOVE)
    private EnderecoEntity rua_pai;
    private int numero_casa_pai;
    @OneToMany
    private List<AlunoEntity> list_aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRg_pai() {
        return rg_pai;
    }

    public void setRg_pai(String rg_pai) {
        this.rg_pai = rg_pai;
    }

    public String getProfissao_pai() {
        return profissao_pai;
    }

    public void setProfissao_pai(String profissao_pai) {
        this.profissao_pai = profissao_pai;
    }

    public String getLocal_trabalho_pai() {
        return local_trabalho_pai;
    }

    public void setLocal_trabalho_pai(String local_trabalho_pai) {
        this.local_trabalho_pai = local_trabalho_pai;
    }

    public String getFone_pai() {
        return fone_pai;
    }

    public void setFone_pai(String fone_pai) {
        this.fone_pai = fone_pai;
    }

    public String getEscolaridade_pai() {
        return escolaridade_pai;
    }

    public void setEscolaridade_pai(String escolaridade_pai) {
        this.escolaridade_pai = escolaridade_pai;
    }

    public EnderecoEntity getRua_pai() {
        return rua_pai;
    }

    public void setRua_pai(EnderecoEntity rua_pai) {
        this.rua_pai = rua_pai;
    }

    public int getNumero_casa_pai() {
        return numero_casa_pai;
    }

    public void setNumero_casa_pai(int numero_casa_pai) {
        this.numero_casa_pai = numero_casa_pai;
    }

    public List<AlunoEntity> getList_aluno() {
        return list_aluno;
    }

    public void setList_aluno(List<AlunoEntity> list_aluno) {
        this.list_aluno = list_aluno;
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
        if (!(object instanceof PaiEntity)) {
            return false;
        }
        PaiEntity other = (PaiEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PaiEntity[ id=" + id + " ]";
    }
    
}
