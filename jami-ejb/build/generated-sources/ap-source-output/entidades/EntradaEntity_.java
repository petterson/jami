package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EntradaEntity.class)
public abstract class EntradaEntity_ {

	public static volatile SingularAttribute<EntradaEntity, String> nome_descricao;
	public static volatile SingularAttribute<EntradaEntity, Date> data_conpensacao;
	public static volatile SingularAttribute<EntradaEntity, String> nome_cheque;
	public static volatile SingularAttribute<EntradaEntity, Long> id;
	public static volatile SingularAttribute<EntradaEntity, Date> data_emissao;
	public static volatile SingularAttribute<EntradaEntity, Double> valor_entrada;

}

