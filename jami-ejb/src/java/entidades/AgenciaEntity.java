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
public class AgenciaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numero_agencia;
    private String nome_agencia;
    private String fone_agencia;
    private String email_agencia;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ContaEntity> contas;
    @OneToOne 
    private EnderecoEntity rua_agencia;
    private int numero_rua_agencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero_agencia() {
        return numero_agencia;
    }

    public void setNumero_agencia(String numero_agencia) {
        this.numero_agencia = numero_agencia;
    }

    public String getNome_agencia() {
        return nome_agencia;
    }

    public void setNome_agencia(String nome_agencia) {
        this.nome_agencia = nome_agencia;
    }

    public String getFone_agencia() {
        return fone_agencia;
    }

    public void setFone_agencia(String fone_agencia) {
        this.fone_agencia = fone_agencia;
    }

    public String getEmail_agencia() {
        return email_agencia;
    }

    public void setEmail_agencia(String email_agencia) {
        this.email_agencia = email_agencia;
    }

    public EnderecoEntity getRua_agencia() {
        return rua_agencia;
    }

    public void setRua_agencia(EnderecoEntity rua_agencia) {
        this.rua_agencia = rua_agencia;
    }

    public int getNumero_rua_agencia() {
        return numero_rua_agencia;
    }

    public void setNumero_rua_agencia(int numero_rua_agencia) {
        this.numero_rua_agencia = numero_rua_agencia;
    } 

    public List<ContaEntity> getContas() {
        return contas;
    }

    public void setContas(List<ContaEntity> contas) {
        this.contas = contas;
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
        if (!(object instanceof AgenciaEntity)) {
            return false;
        }
        AgenciaEntity other = (AgenciaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AgenciaEntity[ id=" + id + " ]";
    }
    
}
