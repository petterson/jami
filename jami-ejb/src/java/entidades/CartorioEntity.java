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
public class CartorioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome_cartorio;
    @OneToOne(cascade=CascadeType.REMOVE)
    private EnderecoEntity rua_cartorio;
    private int numero_caetorio;
    @OneToMany
    private List<AlunoEntity> list_aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_cartorio() {
        return nome_cartorio;
    }

    public void setNome_cartorio(String nome_cartorio) {
        this.nome_cartorio = nome_cartorio;
    }

    public EnderecoEntity getRua_cartorio() {
        return rua_cartorio;
    }

    public void setRua_cartorio(EnderecoEntity rua_cartorio) {
        this.rua_cartorio = rua_cartorio;
    }

    public int getNumero_caetorio() {
        return numero_caetorio;
    }

    public void setNumero_caetorio(int numero_caetorio) {
        this.numero_caetorio = numero_caetorio;
    }

    public List<AlunoEntity> getList_aluno() {
        return list_aluno;
    }

    public void setList_aluno(List<AlunoEntity> list_aluno) {
        this.list_aluno = list_aluno;
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
        if (!(object instanceof CartorioEntity)) {
            return false;
        }
        CartorioEntity other = (CartorioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CartorioEntity[ id=" + id + " ]";
    }
    
}
