/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author petterson
 */
@Entity 
public class AlergiaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome_alergia;
    private String causa_alergia;
    private String grau_perigo_alergia;
    @ManyToMany
    private List<AlunoEntity> list_aluno;
    
    public AlergiaEntity(){}
    
    public AlergiaEntity(long id_alergia, String nome, String causa_alegia, String grau_perigo){
    
        this.id = id_alergia;
        this.nome_alergia = nome;
        this.causa_alergia = causa_alegia;
        this.grau_perigo_alergia = grau_perigo;
      
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_alergia() {
        return nome_alergia;
    }

    public void setNome_alergia(String nome_alergia) {
        this.nome_alergia = nome_alergia;
    }

    public String getCausa_alergia() {
        return causa_alergia;
    }

    public void setCausa_alergia(String causa_alergia) {
        this.causa_alergia = causa_alergia;
    }

    public String getGrau_perigo_alergia() {
        return grau_perigo_alergia;
    }

    public void setGrau_perigo_alergia(String grau_perigo_alergia) {
        this.grau_perigo_alergia = grau_perigo_alergia;
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
        if (!(object instanceof AlergiaEntity)) {
            return false;
        }
        AlergiaEntity other = (AlergiaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AlergiaEntity[ id=" + id + " ]";
    }
    
}
