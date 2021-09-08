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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author petterson
 */
@Entity 
public class DistritoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome_distrito;
    @OneToOne//(cascade=CascadeType.REMOVE)
    private CidadeEntity cidade;
    @OneToMany(mappedBy = "distrito_endereco",cascade = CascadeType.REMOVE)
    private List<EnderecoEntity> list_endereco;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_distrito() {
        return nome_distrito;
    }

    public void setNome_distrito(String nome_distrito) {
        this.nome_distrito = nome_distrito;
    }

    public CidadeEntity getCidade() {
        return cidade;
    }

    public void setCidade(CidadeEntity cidade) {
        this.cidade = cidade;
    }

    public List<EnderecoEntity> getList_endereco() {
        return list_endereco;
    }

    public void setList_endereco(List<EnderecoEntity> list_endereco) {
        this.list_endereco = list_endereco;
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
        if (!(object instanceof DistritoEntity)) {
            return false;
        }
        DistritoEntity other = (DistritoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DistritoEntity[ id=" + id + " ]";
    }
    
}
