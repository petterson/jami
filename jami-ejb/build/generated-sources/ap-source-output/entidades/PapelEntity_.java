package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PapelEntity.class)
public abstract class PapelEntity_ {

	public static volatile SingularAttribute<PapelEntity, String> senha;
	public static volatile SingularAttribute<PapelEntity, PessoaEntity> pessoa;
	public static volatile SingularAttribute<PapelEntity, Long> id;
	public static volatile SingularAttribute<PapelEntity, String> login;
	public static volatile SingularAttribute<PapelEntity, String> papel;

}

