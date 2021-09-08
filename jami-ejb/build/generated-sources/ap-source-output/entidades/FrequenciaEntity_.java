package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FrequenciaEntity.class)
public abstract class FrequenciaEntity_ {

	public static volatile SingularAttribute<FrequenciaEntity, Date> data_frequencia;
	public static volatile ListAttribute<FrequenciaEntity, EstuChaEntity> estudantes;
	public static volatile SingularAttribute<FrequenciaEntity, String> nome_chamada;
	public static volatile SingularAttribute<FrequenciaEntity, Long> id;

}

