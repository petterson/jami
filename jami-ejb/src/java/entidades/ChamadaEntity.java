/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity 
public class ChamadaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_chamada;

    private String nome_chamada;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_criacao_chamada;
    @OneToOne
    private TurmaEntity turma;
    
    public Long getId() {
        return id_chamada;
    }

    public void setId(Long id) {
        this.id_chamada = id;
    }

    public String getNome_chamada() {
        return nome_chamada;
    }

    public void setNome_chamada(String nome_chamada) {
        this.nome_chamada = nome_chamada;
    }

    public Date getData_criacao_chamada() {
        return data_criacao_chamada;
    }

    public void setData_criacao_chamada(Date data_criacao_chamada) {
        this.data_criacao_chamada = data_criacao_chamada;
    }

    public TurmaEntity getTurma() {
        return turma;
    }

    public void setTurma(TurmaEntity turma) {
        this.turma = turma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_chamada != null ? id_chamada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChamadaEntity)) {
            return false;
        }
        ChamadaEntity other = (ChamadaEntity) object;
        if ((this.id_chamada == null && other.id_chamada != null) || (this.id_chamada != null && !this.id_chamada.equals(other.id_chamada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ChamadaEntity[ id=" + id_chamada + " ]";
    }
    
}
