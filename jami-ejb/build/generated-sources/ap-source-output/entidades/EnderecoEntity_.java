package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EnderecoEntity.class)
public abstract class EnderecoEntity_ {

	public static volatile SingularAttribute<EnderecoEntity, DistritoEntity> distrito_endereco;
	public static volatile SingularAttribute<EnderecoEntity, String> logradouro;
	public static volatile ListAttribute<EnderecoEntity, AlunoEntity> list_aluno;
	public static volatile SingularAttribute<EnderecoEntity, Long> id;
	public static volatile SingularAttribute<EnderecoEntity, String> cep;

}

