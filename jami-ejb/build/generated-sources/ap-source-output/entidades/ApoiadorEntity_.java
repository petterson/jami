package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ApoiadorEntity.class)
public abstract class ApoiadorEntity_ {

	public static volatile SingularAttribute<ApoiadorEntity, String> email_apoiador;
	public static volatile SingularAttribute<ApoiadorEntity, PessoaEntity> pessoa;
	public static volatile SingularAttribute<ApoiadorEntity, String> fone_apoiador;
	public static volatile SingularAttribute<ApoiadorEntity, Long> id;

}

