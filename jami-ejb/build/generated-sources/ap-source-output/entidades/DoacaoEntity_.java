package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DoacaoEntity.class)
public abstract class DoacaoEntity_ {

	public static volatile SingularAttribute<DoacaoEntity, Double> valor_doacao;
	public static volatile SingularAttribute<DoacaoEntity, String> nome_doador;
	public static volatile SingularAttribute<DoacaoEntity, TipoDoacaoEntity> nome_doacao;
	public static volatile SingularAttribute<DoacaoEntity, Long> id;
	public static volatile SingularAttribute<DoacaoEntity, Date> data_doacao;

}

