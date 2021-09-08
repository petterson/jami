package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IrmaoEntity.class)
public abstract class IrmaoEntity_ {

	public static volatile SingularAttribute<IrmaoEntity, String> fone;
	public static volatile SingularAttribute<IrmaoEntity, PessoaEntity> pessoa;
	public static volatile ListAttribute<IrmaoEntity, AlunoEntity> list_aluno;
	public static volatile SingularAttribute<IrmaoEntity, Long> id;

}

