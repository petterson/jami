package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AlergiaEntity.class)
public abstract class AlergiaEntity_ {

	public static volatile SingularAttribute<AlergiaEntity, String> grau_perigo_alergia;
	public static volatile SingularAttribute<AlergiaEntity, String> causa_alergia;
	public static volatile ListAttribute<AlergiaEntity, AlunoEntity> list_aluno;
	public static volatile SingularAttribute<AlergiaEntity, Long> id;
	public static volatile SingularAttribute<AlergiaEntity, String> nome_alergia;

}

