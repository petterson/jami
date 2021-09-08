package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DepartamentoEntity.class)
public abstract class DepartamentoEntity_ {

	public static volatile SingularAttribute<DepartamentoEntity, Long> id;
	public static volatile ListAttribute<DepartamentoEntity, FuncionarioEntity> funcionarios;
	public static volatile SingularAttribute<DepartamentoEntity, String> nome_departamento;

}

