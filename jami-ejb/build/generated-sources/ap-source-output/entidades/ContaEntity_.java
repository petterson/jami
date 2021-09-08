package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContaEntity.class)
public abstract class ContaEntity_ {

	public static volatile SingularAttribute<ContaEntity, String> numero_conta;
	public static volatile SingularAttribute<ContaEntity, Date> data_criacao;
	public static volatile ListAttribute<ContaEntity, BalanceteEntity> balancetes;
	public static volatile SingularAttribute<ContaEntity, String> nome_conta;
	public static volatile SingularAttribute<ContaEntity, Long> id;
	public static volatile SingularAttribute<ContaEntity, Double> saldo;
	public static volatile SingularAttribute<ContaEntity, AgenciaEntity> agencia;
	public static volatile SingularAttribute<ContaEntity, String> titular_conta;

}

