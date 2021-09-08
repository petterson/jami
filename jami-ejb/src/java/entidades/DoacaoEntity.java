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
public class DoacaoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne 
    private TipoDoacaoEntity nome_doacao;
    private double valor_doacao;
    private String nome_doador;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_doacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDoacaoEntity getNome_doacao() {
        return nome_doacao;
    }

    public void setNome_doacao(TipoDoacaoEntity nome_doacao) {
        this.nome_doacao = nome_doacao;
    }

    public double getValor_doacao() {
        return valor_doacao;
    }

    public void setValor_doacao(double valor_doacao) {
        this.valor_doacao = valor_doacao;
    }

    public String getNome_doador() {
        return nome_doador;
    }

    public void setNome_doador(String nome_doador) {
        this.nome_doador = nome_doador;
    }

    public Date getData_doacao() {
        return data_doacao;
    }

    public void setData_doacao(Date data_doacao) {
        this.data_doacao = data_doacao;
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
        if (!(object instanceof DoacaoEntity)) {
            return false;
        }
        DoacaoEntity other = (DoacaoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DoacaoEntity[ id=" + id + " ]";
    }
    
}
