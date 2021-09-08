package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DoadorEntity.class)
public abstract class DoadorEntity_ {

	public static volatile SingularAttribute<DoadorEntity, PessoaEntity> pessoa;
	public static volatile SingularAttribute<DoadorEntity, String> fone_doador;
	public static volatile SingularAttribute<DoadorEntity, Long> id;
	public static volatile SingularAttribute<DoadorEntity, String> email_doador;
	public static volatile ListAttribute<DoadorEntity, DoacaoEntity> doacoes;

}

