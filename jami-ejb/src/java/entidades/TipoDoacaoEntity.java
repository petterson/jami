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
public class TipoDoacaoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome_doacao;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<DoacaoEntity> doacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_doacao() {
        return nome_doacao;
    }

    public void setNome_doacao(String nome_doacao) {
        this.nome_doacao = nome_doacao;
    }

    public List<DoacaoEntity> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<DoacaoEntity> doacoes) {
        this.doacoes = doacoes;
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
        if (!(object instanceof TipoDoacaoEntity)) {
            return false;
        }
        TipoDoacaoEntity other = (TipoDoacaoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoDoacaoEntity[ id=" + id + " ]";
    }
    
}
