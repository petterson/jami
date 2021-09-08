package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CidadeEntity.class)
public abstract class CidadeEntity_ {

	public static volatile SingularAttribute<CidadeEntity, String> uf_cidade;
	public static volatile SingularAttribute<CidadeEntity, String> nome_cidade;
	public static volatile ListAttribute<CidadeEntity, AlunoEntity> list_aluno;
	public static volatile SingularAttribute<CidadeEntity, Long> id;
	public static volatile ListAttribute<CidadeEntity, DistritoEntity> list_distrito;

}

