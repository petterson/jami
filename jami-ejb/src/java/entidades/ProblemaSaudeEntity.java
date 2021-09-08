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
public class ProblemaSaudeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String problema_saude; 
    private String grau_perigo;
    @ManyToMany
    private List<AlunoEntity> list_aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblema_saude() {
        return problema_saude;
    }

    public void setProblema_saude(String problema_saude) {
        this.problema_saude = problema_saude;
    }

    public String getGrau_perigo() {
        return grau_perigo;
    }

    public void setGrau_perigo(String grau_perigo) {
        this.grau_perigo = grau_perigo;
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
        if (!(object instanceof ProblemaSaudeEntity)) {
            return false;
        }
        ProblemaSaudeEntity other = (ProblemaSaudeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ProblemaSaudeEntity[ id=" + id + " ]";
    }
    
}
