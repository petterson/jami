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
import javax.persistence.OneToMany;


/**
 *
 * @author petterson
 */
@Entity 
public class CidadeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome_cidade;
    private String uf_cidade;
    @OneToMany
    private List<AlunoEntity> list_aluno;
    @OneToMany(mappedBy = "cidade", cascade = CascadeType.REMOVE)
    private List<DistritoEntity> list_distrito;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    public String getUf_cidade() {
        return uf_cidade;
    }

    public void setUf_cidade(String uf_cidade) {
        this.uf_cidade = uf_cidade;
    }   

    public List<AlunoEntity> getList_aluno() {
        return list_aluno;
    }

    public void setList_aluno(List<AlunoEntity> list_aluno) {
        this.list_aluno = list_aluno;
    }

    public List<DistritoEntity> getList_distrito() {
        return list_distrito;
    }

    public void setList_distrito(List<DistritoEntity> list_distrito) {
        this.list_distrito = list_distrito;
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
        if (!(object instanceof CidadeEntity)) {
            return false;
        }
        CidadeEntity other = (CidadeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CidadeEntity[ id=" + id + " ]";
    }
    
}
