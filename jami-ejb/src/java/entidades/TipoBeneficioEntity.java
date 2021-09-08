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
public class TipoBeneficioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome_beneficio;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<BeneficioConcedidoEntity> beneficios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_beneficio() {
        return nome_beneficio;
    }

    public void setNome_beneficio(String nome_beneficio) {
        this.nome_beneficio = nome_beneficio;
    }

    public List<BeneficioConcedidoEntity> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(List<BeneficioConcedidoEntity> beneficios) {
        this.beneficios = beneficios;
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
        if (!(object instanceof TipoBeneficioEntity)) {
            return false;
        }
        TipoBeneficioEntity other = (TipoBeneficioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoBeneficioEntity[ id=" + id + " ]";
    }
    
}
