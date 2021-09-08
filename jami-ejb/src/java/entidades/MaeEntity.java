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
public class MaeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private PessoaEntity pessoa;
    private String rg_mae;
    private String fone_mae;
    private String escolaridade_mae;
    private String profissao_mae;
    private String local_trabalho_mae; 
    @OneToOne(cascade=CascadeType.REMOVE)
    private EnderecoEntity rua_mae;
    private int numero_casa_mae;
    @OneToMany
    private List<AlunoEntity> list_aluno;
   

    public String getRg_mae() {
        return rg_mae;
    }

    public void setRg_mae(String rg_mae) {
        this.rg_mae = rg_mae;
    }

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

    public String getProfissao_mae() {
        return profissao_mae;
    }

    public void setProfissao_mae(String profissao_mae) {
        this.profissao_mae = profissao_mae;
    }

    public String getLocal_trabalho_mae() {
        return local_trabalho_mae;
    }

    public void setLocal_trabalho_mae(String local_trabalho_mae) {
        this.local_trabalho_mae = local_trabalho_mae;
    }

    public String getFone_mae() {
        return fone_mae;
    }

    public void setFone_mae(String fone_mae) {
        this.fone_mae = fone_mae;
    }

    public String getEscolaridade_mae() {
        return escolaridade_mae;
    }

    public void setEscolaridade_mae(String escolaridade_mae) {
        this.escolaridade_mae = escolaridade_mae;
    }

    public EnderecoEntity getRua_mae() {
        return rua_mae;
    }

    public void setRua_mae(EnderecoEntity rua_mae) {
        this.rua_mae = rua_mae;
    }

    public int getNumero_casa_mae() {
        return numero_casa_mae;
    }

    public void setNumero_casa_mae(int numero_casa_mae) {
        this.numero_casa_mae = numero_casa_mae;
    }

    public List<AlunoEntity> getList_aluno() {
        return list_aluno;
    }

    public void setList_aluno(List<AlunoEntity> list_aluno) {
        this.list_aluno = list_aluno;
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
        if (!(object instanceof MaeEntity)) {
            return false;
        }
        MaeEntity other = (MaeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MaeEntity[ id=" + id + " ]";
    }
    
}
