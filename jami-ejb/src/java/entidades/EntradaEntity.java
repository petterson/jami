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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity
public class EntradaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_emissao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_conpensacao;
    private String nome_cheque;
    private String nome_descricao;
    private double valor_entrada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(Date data_emissao) {
        this.data_emissao = data_emissao;
    }

    public Date getData_conpensacao() {
        return data_conpensacao;
    }

    public void setData_conpensacao(Date data_conpensacao) {
        this.data_conpensacao = data_conpensacao;
    }

    public String getNome_cheque() {
        return nome_cheque;
    }

    public void setNome_cheque(String nome_cheque) {
        this.nome_cheque = nome_cheque;
    }

    public String getNome_descricao() {
        return nome_descricao;
    }

    public void setNome_descricao(String nome_descricao) {
        this.nome_descricao = nome_descricao;
    }

    public double getValor_entrada() {
        return valor_entrada;
    }

    public void setValor_entrada(double valor_entrada) {
        this.valor_entrada = valor_entrada;
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
        if (!(object instanceof EntradaEntity)) {
            return false;
        }
        EntradaEntity other = (EntradaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EntradaEntity[ id=" + id + " ]";
    }
    
}
