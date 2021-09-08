package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EscolaEntity.class)
public abstract class EscolaEntity_ {

	public static volatile SingularAttribute<EscolaEntity, String> nome_escola;
	public static volatile ListAttribute<EscolaEntity, AlunoEntity> list_aluno;
	public static volatile SingularAttribute<EscolaEntity, EnderecoEntity> rua_escola;
	public static volatile SingularAttribute<EscolaEntity, Long> id;
	public static volatile SingularAttribute<EscolaEntity, Integer> numero_escola;

}

