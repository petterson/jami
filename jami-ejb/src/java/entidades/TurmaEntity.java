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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity 
public class TurmaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_turma;

    private String nome_turma;
    private String nome_professor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_criacao;
    @OneToOne(cascade = CascadeType.REMOVE)
    private ChamadaEntity chamada;
    @ManyToMany
    private List<AlunoEntity> alunos;
    private String turno;
    @OneToOne
    private AtividadeEntity atividade;

    public Long getId_turma() {
        return id_turma;
    }

    public void setId_turma(Long id_turma) {
        this.id_turma = id_turma;
    }

    public String getNome_turma() {
        return nome_turma;
    }

    public void setNome_turma(String nome_turma) {
        this.nome_turma = nome_turma;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getNome_professor() {
        return nome_professor;
    }

    public void setNome_professor(String nome_professor) {
        this.nome_professor = nome_professor;
    }

    public ChamadaEntity getChamada() {
        return chamada;
    }

    public void setChamada(ChamadaEntity chamada) {
        this.chamada = chamada;
    }

    public List<AlunoEntity> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoEntity> alunos) {
        this.alunos = alunos;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public AtividadeEntity getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeEntity atividade) {
        this.atividade = atividade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_turma != null ? id_turma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurmaEntity)) {
            return false;
        }
        TurmaEntity other = (TurmaEntity) object;
        if ((this.id_turma == null && other.id_turma != null) || (this.id_turma != null && !this.id_turma.equals(other.id_turma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TurmaEntity[ id=" + id_turma + " ]";
    }
    
}
