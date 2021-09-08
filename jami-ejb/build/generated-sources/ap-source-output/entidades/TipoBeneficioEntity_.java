package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoBeneficioEntity.class)
public abstract class TipoBeneficioEntity_ {

	public static volatile SingularAttribute<TipoBeneficioEntity, Long> id;
	public static volatile SingularAttribute<TipoBeneficioEntity, String> nome_beneficio;
	public static volatile ListAttribute<TipoBeneficioEntity, BeneficioConcedidoEntity> beneficios;

}

