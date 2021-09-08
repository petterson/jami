/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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
public class BeneficioConcedidoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private double valor_beneficio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_beneficio;
    @OneToOne
    private SocialEntity beneficiario;
    @OneToOne
    private TipoBeneficioEntity nome_beneficio;

    public Long getId() {
        return id;
    }

    public double getValor_beneficio() {
        return valor_beneficio;
    }

    public void setValor_beneficio(double valor_beneficio) {
        this.valor_beneficio = valor_beneficio;
    }

    public Date getData_beneficio() {
        return data_beneficio;
    }

    public void setData_beneficio(Date data_beneficio) {
        this.data_beneficio = data_beneficio;
    }

    public SocialEntity getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(SocialEntity beneficiario) {
        this.beneficiario = beneficiario;
    }

    public TipoBeneficioEntity getNome_beneficio() {
        return nome_beneficio;
    }

    public void setNome_beneficio(TipoBeneficioEntity nome_beneficio) {
        this.nome_beneficio = nome_beneficio;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof BeneficioConcedidoEntity)) {
            return false;
        }
        BeneficioConcedidoEntity other = (BeneficioConcedidoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.BenifioConcedidoEntity[ id=" + id + " ]";
    }
    
}
