/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity
public class AlunoDesiEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    private String sexo;
    private String situacao;
    private String escola;
    @ElementCollection
    private List<String> nome_turmas;
    @ElementCollection
    private List<String> nome_atividades;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_desistencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public List<String> getNome_turmas() {
        return nome_turmas;
    }

    public void setNome_turmas(List<String> nome_turmas) {
        this.nome_turmas = nome_turmas;
    }

    public List<String> getNome_atividades() {
        return nome_atividades;
    }

    public void setNome_atividades(List<String> nome_atividades) {
        this.nome_atividades = nome_atividades;
    }

    public Date getData_desistencia() {
        return data_desistencia;
    }

    public void setData_desistencia(Date data_desistencia) {
        this.data_desistencia = data_desistencia;
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
        if (!(object instanceof AlunoDesiEntity)) {
            return false;
        }
        AlunoDesiEntity other = (AlunoDesiEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AlunoDesiEntity[ id=" + id + " ]";
    }
    
}
