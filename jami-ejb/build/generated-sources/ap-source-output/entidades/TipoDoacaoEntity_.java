package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoDoacaoEntity.class)
public abstract class TipoDoacaoEntity_ {

	public static volatile SingularAttribute<TipoDoacaoEntity, String> nome_doacao;
	public static volatile SingularAttribute<TipoDoacaoEntity, Long> id;
	public static volatile ListAttribute<TipoDoacaoEntity, DoacaoEntity> doacoes;

}

