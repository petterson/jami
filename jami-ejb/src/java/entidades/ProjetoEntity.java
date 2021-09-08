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
public class ProjetoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome_projeto;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_inicio;
    @OneToMany(cascade=CascadeType.REMOVE)
    private List<AtividadeEntity> nome_atividades;
    @OneToMany
    private List<InstituicaoEntity> instituicoes;
    @OneToOne
    private ProgramaEntity programa;
    private String cust_anual;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_projeto() {
        return nome_projeto;
    }

    public void setNome_projeto(String nome_projeto) {
        this.nome_projeto = nome_projeto;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public List<AtividadeEntity> getNome_atividades() {
        return nome_atividades;
    }

    public void setNome_atividades(List<AtividadeEntity> nome_atividades) {
        this.nome_atividades = nome_atividades;
    }

    public List<InstituicaoEntity> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<InstituicaoEntity> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public String getCust_anual() {
        return cust_anual;
    }

    public void setCust_anual(String cust_anual) {
        this.cust_anual = cust_anual;
    }

    public ProgramaEntity getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaEntity programa) {
        this.programa = programa;
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
        if (!(object instanceof ProjetoEntity)) {
            return false;
        }
        ProjetoEntity other = (ProjetoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ProjetoEntity[ id=" + id + " ]";
    }
    
}
