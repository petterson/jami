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
public class ApoiadorEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fone_apoiador;
    private String email_apoiador;
    @OneToOne
    private PessoaEntity pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFone_apoiador() {
        return fone_apoiador;
    }

    public void setFone_apoiador(String fone_apoiador) {
        this.fone_apoiador = fone_apoiador;
    }

    public String getEmail_apoiador() {
        return email_apoiador;
    }

    public void setEmail_apoiador(String email_apoiador) {
        this.email_apoiador = email_apoiador;
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
        if (!(object instanceof ApoiadorEntity)) {
            return false;
        }
        ApoiadorEntity other = (ApoiadorEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ApoiadorEntity[ id=" + id + " ]";
    }
    
}
