
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
public class InstituicaoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cnpj;
    private String nome_instituicao;
    private String fone_instiuicao;
    private String email_instituicao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_parceria;
    @OneToOne
    private TipoInstituicaoEntity tipoInstituicao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_instituicao() {
        return nome_instituicao;
    }

    public void setNome_instituicao(String nome_instituicao) {
        this.nome_instituicao = nome_instituicao;
    }

    public String getFone_instiuicao() {
        return fone_instiuicao;
    }

    public void setFone_instiuicao(String fone_instiuicao) {
        this.fone_instiuicao = fone_instiuicao;
    }

    public String getEmail_instituicao() {
        return email_instituicao;
    }

    public void setEmail_instituicao(String email_instituicao) {
        this.email_instituicao = email_instituicao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public TipoInstituicaoEntity getTipoInstituicao() {
        return tipoInstituicao;
    }

    public void setTipoInstituicao(TipoInstituicaoEntity tipoInstituicao) {
        this.tipoInstituicao = tipoInstituicao;
    }

    public Date getData_parceria() {
        return data_parceria;
    }

    public void setData_parceria(Date data_parceria) {
        this.data_parceria = data_parceria;
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
        if (!(object instanceof InstituicaoEntity)) {
            return false;
        }
        InstituicaoEntity other = (InstituicaoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InstituicaoEntity[ id=" + id + " ]";
    }
    
}
