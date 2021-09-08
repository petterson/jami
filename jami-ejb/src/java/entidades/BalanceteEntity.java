
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author petterson
 */
@Entity
public class BalanceteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    private String numero_conta;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_inicial;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_final;
    private double saldo;
    private String descricao_balancete;
    @OneToMany(cascade = CascadeType.REMOVE)
    List<EntradaEntity> entrada;
    @OneToMany(cascade = CascadeType.REMOVE)
    List<SaidaEntity> saida;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDescricao_balancete() {
        return descricao_balancete;
    }

    public void setDescricao_balancete(String descricao_balancete) {
        this.descricao_balancete = descricao_balancete;
    }

    public List<EntradaEntity> getEntrada() {
        return entrada;
    }

    public void setEntrada(List<EntradaEntity> entrada) {
        this.entrada = entrada;
    }

    public List<SaidaEntity> getSaida() {
        return saida;
    }

    public void setSaida(List<SaidaEntity> saida) {
        this.saida = saida;
    }

    public String getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(String numero_conta) {
        this.numero_conta = numero_conta;
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
        if (!(object instanceof BalanceteEntity)) {
            return false;
        }
        BalanceteEntity other = (BalanceteEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.BalanceteEntity[ id=" + id + " ]";
    }
    
}
