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
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity 
public class FrequenciaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome_chamada;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_frequencia;
    @OneToMany  
    private List<EstuChaEntity> estudantes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_frequencia() {
        return data_frequencia;
    }

    public void setData_frequencia(Date data_frequencia) {
        this.data_frequencia = data_frequencia;
    }

    public List<EstuChaEntity> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<EstuChaEntity> estudantes) {
        this.estudantes = estudantes;
    }

    public String getNome_chamada() {
        return nome_chamada;
    }

    public void setNome_chamada(String nome_chamada) {
        this.nome_chamada = nome_chamada;
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
        if (!(object instanceof FrequenciaEntity)) {
            return false;
        }
        FrequenciaEntity other = (FrequenciaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FrequenciaEntity[ id=" + id + " ]";
    }
    
}
