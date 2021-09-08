/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author petterson
 */
@Entity 
public class EnderecoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String logradouro;
    private String cep;
    @OneToMany
    private List<AlunoEntity> list_aluno;
    @OneToOne(cascade = CascadeType.REMOVE)
    private DistritoEntity distrito_endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public DistritoEntity getDistrito_endereco() {
        return distrito_endereco;
    }

    public void setDistrito_endereco(DistritoEntity distrito_endereco) {
        this.distrito_endereco = distrito_endereco;
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
        if (!(object instanceof EnderecoEntity)) {
            return false;
        }
        EnderecoEntity other = (EnderecoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnderecoEntity[ id=" + id + " ]";
    }
    
}
