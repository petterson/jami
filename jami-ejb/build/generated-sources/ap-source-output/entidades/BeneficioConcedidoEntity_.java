package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BeneficioConcedidoEntity.class)
public abstract class BeneficioConcedidoEntity_ {

	public static volatile SingularAttribute<BeneficioConcedidoEntity, Double> valor_beneficio;
	public static volatile SingularAttribute<BeneficioConcedidoEntity, Date> data_beneficio;
	public static volatile SingularAttribute<BeneficioConcedidoEntity, Long> id;
	public static volatile SingularAttribute<BeneficioConcedidoEntity, TipoBeneficioEntity> nome_beneficio;
	public static volatile SingularAttribute<BeneficioConcedidoEntity, SocialEntity> beneficiario;

}

