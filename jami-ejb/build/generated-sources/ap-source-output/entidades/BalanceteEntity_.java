package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BalanceteEntity.class)
public abstract class BalanceteEntity_ {

	public static volatile SingularAttribute<BalanceteEntity, String> numero_conta;
	public static volatile ListAttribute<BalanceteEntity, EntradaEntity> entrada;
	public static volatile SingularAttribute<BalanceteEntity, String> descricao_balancete;
	public static volatile SingularAttribute<BalanceteEntity, Date> data_inicial;
	public static volatile ListAttribute<BalanceteEntity, SaidaEntity> saida;
	public static volatile SingularAttribute<BalanceteEntity, Long> id;
	public static volatile SingularAttribute<BalanceteEntity, Double> saldo;
	public static volatile SingularAttribute<BalanceteEntity, Date> data_final;

}

