package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SaidaEntity.class)
public abstract class SaidaEntity_ {

	public static volatile SingularAttribute<SaidaEntity, String> nome_descricao;
	public static volatile SingularAttribute<SaidaEntity, String> nome_cheque;
	public static volatile SingularAttribute<SaidaEntity, Double> valor_saida;
	public static volatile SingularAttribute<SaidaEntity, Long> id;
	public static volatile SingularAttribute<SaidaEntity, Date> data_compensacao;
	public static volatile SingularAttribute<SaidaEntity, Date> data_emissao;

}

