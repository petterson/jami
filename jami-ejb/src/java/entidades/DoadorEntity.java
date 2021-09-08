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
import javax.persistence.OneToOne;

/**
 *
 * @author petterson
 */
@Entity
public class DoadorEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fone_doador;
    private String email_doador;
    @OneToOne
    private PessoaEntity pessoa;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<DoacaoEntity> doacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFone_doador() {
        return fone_doador;
    }

    public void setFone_doador(String fone_doador) {
        this.fone_doador = fone_doador;
    }

    public String getEmail_doador() {
        return email_doador;
    }

    public void setEmail_doador(String email_doador) {
        this.email_doador = email_doador;
    }

    public List<DoacaoEntity> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<DoacaoEntity> doacoes) {
        this.doacoes = doacoes;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
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
        if (!(object instanceof DoadorEntity)) {
            return false;
        }
        DoadorEntity other = (DoadorEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DoadorEntity[ id=" + id + " ]";
    }
    
}
