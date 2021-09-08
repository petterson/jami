package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AgenciaEntity.class)
public abstract class AgenciaEntity_ {

	public static volatile SingularAttribute<AgenciaEntity, String> nome_agencia;
	public static volatile SingularAttribute<AgenciaEntity, EnderecoEntity> rua_agencia;
	public static volatile SingularAttribute<AgenciaEntity, String> email_agencia;
	public static volatile SingularAttribute<AgenciaEntity, String> numero_agencia;
	public static volatile SingularAttribute<AgenciaEntity, Long> id;
	public static volatile ListAttribute<AgenciaEntity, ContaEntity> contas;
	public static volatile SingularAttribute<AgenciaEntity, String> fone_agencia;
	public static volatile SingularAttribute<AgenciaEntity, Integer> numero_rua_agencia;

}

