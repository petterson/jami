package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PessoaEntity.class)
public abstract class PessoaEntity_ {

	public static volatile SingularAttribute<PessoaEntity, String> cpf;
	public static volatile SingularAttribute<PessoaEntity, String> nome;
	public static volatile SingularAttribute<PessoaEntity, Long> id;
	public static volatile SingularAttribute<PessoaEntity, Date> data_nasc;

}

